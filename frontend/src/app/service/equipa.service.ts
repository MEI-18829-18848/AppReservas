import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { UrlService } from './url.service';

export interface IEquipa{
  id:number,
  nome: string,
  regiao: string,
  gestorId: number,
  elementos: number
}

export interface ICreateEquipa{
  id:number,
  nome: string,
  regiao: string,
  gestorId: number
}

export interface IGestor{
  agenteId: number,
  equipaId: number
}

@Injectable({
  providedIn: 'root'
})

export class EquipaService {

  headers= new HttpHeaders();
  
  constructor(private http: HttpClient, private url: UrlService) { }

  getEquipas() :Observable<Array<IEquipa>>{
    return this.http.get<Array<IEquipa>>(this.url.backend + "Equipa");
  }

  createEquipa(equipa: ICreateEquipa) :Observable<ICreateEquipa>{
    return this.http.post<ICreateEquipa>(this.url.backend + "Equipa", equipa);
  } 

  createGestor(gestor: IGestor) :Observable<IGestor>{
    return this.http.post<IGestor>(this.url.backend + "Gestor/create", gestor)   
  }
}
