import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from "rxjs";
import {Router} from "@angular/router";
import { Pessoa } from '../data/Pessoa';
import { Contacto } from '../data/Contacto';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {
  headers= new HttpHeaders({
    'content-type':  'application/json',
    Authorization: 'my-auth-token',
  });

  constructor(private http: HttpClient,public router: Router, private url: UrlService) { }

  getPessoa(){
    return this.http.get(this.url.backend + "Pessoa/Index/0", { headers: this.headers });
  }
  
  postPessoa(pessoa: Pessoa){
    return this.http.post(this.url.backend + "Pessoa/Create", pessoa,{ headers: this.headers });
  }
  
  updatePessoa(pessoa: Pessoa){
    return this.http.post(this.url.backend + "Pessoa/Update", pessoa,{ headers: this.headers });
  }
}
