import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from "rxjs";
import { Cliente } from '../data/Cliente';
import {Router} from "@angular/router";
import { Contacto } from '../data/Contacto';
import { GalleriaThumbnails } from 'primeng/galleria';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class LeadsService {
  headers= new HttpHeaders({
    'content-type':  'application/json',
    Authorization: 'my-auth-token',
  });

  constructor(private http: HttpClient,public router: Router, private url: UrlService) { }

  getClientes() {
    return this.http.get<Cliente[]>(this.url.backend + "Cliente/Index/0", { headers: this.headers });
  }

  getCliente(id: number) {
    return this.http.get<Cliente[]>(this.url.backend + "Cliente/Index/" + id.toString(), { headers: this.headers });
  }

  getContacto(id: number) {
    return this.http.get<Contacto[]>(this.url.backend + "Contacto/Index/" + id.toString(), { headers: this.headers });
  }

  postCliente(lead: Cliente){
    console.log(lead)
    return this.http.post(this.url.backend + "Cliente/Create", lead, { headers: this.headers });
  }

  updateCliente(lead: Cliente){
    return this.http.post(this.url.backend + "Cliente/Update", lead, {headers: this.headers});
  }

  deleteCliente(cliente: Cliente){
    return this.http.delete(this.url.backend + "Cliente/Delete?clienteId=" + cliente.id.toString(), {headers: this.headers});
  }

  postContacto(lead: Contacto){
    return this.http.post(this.url.backend + "Contacto/Create", lead, { headers: this.headers });
  }
  
  updateContacto(lead: Contacto){
    return this.http.post(this.url.backend + "Contacto/Update", lead, {headers: this.headers});
  }

  deleteContacto(id: number){
    return this.http.delete(this.url.backend + "Contacto/Delete/" + id.toString(), {headers: this.headers});
  }
}
