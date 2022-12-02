import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { IDashboardGestor } from "../components/ManaLynx/dashboard/dashboardInterface";
import { Observable } from "rxjs";
import { ISeguro } from "../components/ManaLynx/seguros/segurosInterface";
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class SeguroService {

    headers= new HttpHeaders();

    constructor(private http: HttpClient, private url: UrlService) { }

    getSeguros() : Observable<ISeguro[]>{
        return this.http.get<ISeguro[]>(this.url.backend + "Seguro", { 'headers': this.headers })
    }
    
    createSeguro(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "Seguro/create", body, { 'headers': this.headers })
    }

}
