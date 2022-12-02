import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User, RegisterRequest, ManaUser} from "../data/User";
import { Observable, of } from "rxjs";
// @ts-ignore
import * as moment from "moment";
import jwt_decode from "jwt-decode";
import {Router} from "@angular/router";
import { Cliente } from '../data/Cliente';
import { Pessoa } from '../data/Pessoa';
import { UrlService } from '../service/url.service';

interface MyToken {
    Id: string;
    aud: string;
    email: string;
    exp: number;
    iat: number;
    iss: string;
    jti: string;
    name: string;
    nbf: number;
    role: string;
    // whatever else is in the JWT.
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    cliente: Cliente;

    headers= new HttpHeaders({
            'content-type':  'application/json',
            Authorization: 'my-auth-token'
    });
    constructor(private http: HttpClient, public router: Router, private url: UrlService) { }

	register(user:RegisterRequest): void{
        this.http.post<RegisterRequest>(this.url.backend + "ManaUser/Register", user, { 'headers': this.headers })
                 .subscribe(res => this.login(user.username, user.password));
	}

    login(username:string, password:string ) {
        this.http.post<User>(this.url.backend + "ManaUser/Login", {username, password},{ 'headers': this.headers })
        .subscribe(res => this.setSession(res) );
    }

    isPessoaEmpty(id: number = 0): Observable<boolean>{
        this.http.get<Cliente[]>(this.url.backend + "Cliente/Index/" + id.toString(), {'headers': this.headers})
        .subscribe(res => {
            this.cliente = res[0];
            console.log(this.cliente);
            if(this.cliente.pessoa.nome === null || this.cliente.pessoa.dataNascimento === null || this.cliente.pessoa.nacionalidade === null
            || this.cliente.pessoa.cc === null || this.cliente.pessoa.validadeCc === null || this.cliente.pessoa.nif 
            || this.cliente.pessoa.nss === null || this.cliente.pessoa.nus === null || this.cliente.pessoa.estadoCivil === null){
                return true;
            }
            return false;
        });
        return of(false);
    }

    loggedCliente(){
        return this.http.get<Cliente[]>(this.url.backend + "Cliente/Index/0", {'headers': this.headers});
    }

    isNewRegister(cliente: Cliente){
        console.log(cliente);
        if(cliente == null || cliente.pessoa == null) return true;
        if(cliente.pessoa.nif === null || cliente.pessoa.dataNascimento === null || cliente.pessoa.estadoCivil === null 
            || cliente.pessoa.nss === null || cliente.pessoa.validadeCc === null || cliente.pessoa.nacionalidade === null
            || cliente.pessoa.nus === null || cliente.pessoa.nome === null || cliente.pessoa.cc === null
            || cliente.profissao === null || cliente.profissaoRisco === null){
                return true;
        }
        return false;
    }

    private setSession(authResult) {
        localStorage.setItem('id_token', authResult.token);
        if (this.isLoggedIn() == true) {
            this.router.navigate([''])
        }
    }

    logout() {
        localStorage.removeItem("id_token");
        window.location.reload();
    }

    public isLoggedIn() {
        var token = localStorage.getItem("id_token")
        if (token == null){return false}
        var decodedHeader = jwt_decode<MyToken>(localStorage.getItem("id_token"))
        return (Math.floor((new Date).getTime() / 1000)) < decodedHeader.exp;
    }

    public getRole(){
        var decodedHeader = jwt_decode<MyToken>(localStorage.getItem("id_token"))
        return decodedHeader.role;
    }

    public getUserId(){
        var decodedHeader = jwt_decode<MyToken>(localStorage.getItem("id_token"))
        return decodedHeader.Id;
    }

    isLoggedOut() {
        return !this.isLoggedIn();
    }

    getExpiration() {
        const expiration = localStorage.getItem("expires_at");
        const expiresAt = JSON.parse(expiration);
        return moment(expiresAt);
    }
}
