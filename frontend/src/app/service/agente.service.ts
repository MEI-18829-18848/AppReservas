import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, retry } from 'rxjs';
import { Pessoa } from '../data/Pessoa';
import { UrlService } from './url.service';

export interface IAgente{
  id:number,
  nome: string,
  nAgente: number,
  apolices: number,
  isGestor: boolean
}

export interface IPessoaAgente{
  id:number;
  nome:string;
  dataNascimento: Date;
  nacionalidade: string;
  cc: string;
  validadeCc: Date;
  nif:string;
  nss: string;
  nus: string;
  estadoCivil: string;
}

export interface ICreateAgente{
  id: number;
  equipaId: number;
  nAgente: number;
  pessoa : IPessoaAgente;
  pessoaId : number;
}

export interface ICreateAgenteUser{
  username: string;
  email: string;
  userRole: string;
  pessoaId: number;
  password: string;
}


@Injectable({
  providedIn: 'root'
})
export class AgenteService {

  headers= new HttpHeaders();
  
  constructor(private http: HttpClient, private url: UrlService) { }

  getAgentes():Observable<Array<any>>{
    return this.http.get<Array<any>>(this.url.backend + "Agente");
  }

  getAgenteByEquipa(equipaId: number) :Observable<Array<IAgente>>{
    return this.http.get<Array<IAgente>>(this.url.backend + "Agente/" + equipaId);
  }

  createAgente(agente: ICreateAgente): Observable<ICreateAgente>{
    return this.http.post<ICreateAgente>(this.url.backend + "Agente/create", agente)
  }

  createUserForAgente(user: ICreateAgenteUser){
    return this.http.post<ICreateAgenteUser>(this.url.backend + "manauser/registerrole", user)
  }


}
