import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import {
    ISinistro
} from "../components/ManaLynx/sinistros/sinistrosInterface";
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class SinistroService {

    headers= new HttpHeaders();

    constructor(private http: HttpClient, private url: UrlService) { }

    getSinistros() : Observable<ISinistro[]>{
        return this.http.get<ISinistro[]>(this.url.backend + "Sinistro", { 'headers': this.headers })
    }
    
    createSinistroPessoal(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "SinistroPessoal", body, { 'headers': this.headers })
    }
    
    createSinistroVeiculo(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "SinistroVeiculo", body, { 'headers': this.headers })
    }
    
    createProva(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "Prova", body, { 'headers': this.headers })
    }
    
    createRelatorio(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "RelatorioPeritagem", body, { 'headers': this.headers })
    }

    putSinistro(id, body) : Observable<number>{
        return  this.http.put<number>(this.url.backend + "Sinistro/" + id, body, { 'headers': this.headers })
    }

    removeProva(id) : Observable<number>{
        return  this.http.delete<number>(this.url.backend + "Sinistro/" + id, { 'headers': this.headers })
    }

    removeRelatorio(id) : Observable<number>{
        return  this.http.delete<number>(this.url.backend + "RelatorioPeritagem/" + id, { 'headers': this.headers })
    }

}
