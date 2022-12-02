import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from "rxjs";
import { Router } from "@angular/router";
import { Veiculo } from '../data/Veiculo';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {
  headers= new HttpHeaders({
    'content-type':  'application/json',
    Authorization: 'my-auth-token',
  });

  constructor(private http: HttpClient,public router: Router, private url: UrlService) { }

  getVeiculos(){
    return this.http.get<Veiculo[]>(this.url.backend + "Veiculo", { headers: this.headers });
  }

  getCategorias(){
    return this.http.get<any[]>(this.url.backend + "CategoriaVeiculo", { headers: this.headers });
  }

  postVeiculo(veiculo: Veiculo){
    return this.http.post(this.url.backend + "Veiculo", veiculo,{ headers: this.headers });
  }

  updateVeiculo(veiculo: Veiculo){
    return this.http.put(this.url.backend + "Veiculo/" + veiculo.id.toString(), veiculo,{ headers: this.headers });
  }

  deleteVeiculo(id: number){
    return this.http.delete(this.url.backend + "Veiculo/" + id.toString() , { headers: this.headers });
  }

    getVeiculosId(id){
        return this.http.get<Veiculo[]>(this.url.backend + "Veiculo/"+ id, { headers: this.headers });
    }

}
