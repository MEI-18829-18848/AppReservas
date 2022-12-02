import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { IDashboardGestor } from "../components/ManaLynx/dashboard/dashboardInterface";
import { Observable } from "rxjs";
import {
    ICategoriaVeiculo,
    ISeguro,
    IVeiculo,
    ICliente,
    IAgenteSimu,
    IAgentesSimu
} from "../components/ManaLynx/simulacoes/simulacoesInterface";
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class SimulacaoService {

    headers= new HttpHeaders();

    constructor(private http: HttpClient, private url: UrlService) { }

    getSeguros() : Observable<ISeguro[]>{
        return this.http.get<ISeguro[]>(this.url.backend + "Seguro", { 'headers': this.headers })
    }

    getVeiculos() : Observable<IVeiculo[]>{
        return this.http.get<IVeiculo[]>(this.url.backend + "veiculo", { 'headers': this.headers })
    }

    getCategoriasVeiculos() : Observable<ICategoriaVeiculo[]>{
        return this.http.get<ICategoriaVeiculo[]>(this.url.backend + "CategoriaVeiculo", { 'headers': this.headers })
    }

    getClientes() : Observable<ICliente[]>{
        return this.http.get<ICliente[]>(this.url.backend + "Cliente/Index/0", { 'headers': this.headers })
    }

    getAgente() : Observable<IAgenteSimu[]>{
        return  this.http.get<IAgenteSimu[]>(this.url.backend + "Agente/AgenteByUserId",{ 'headers': this.headers })
    }

    getAgentes() : Observable<IAgentesSimu[]>{
        return  this.http.get<IAgentesSimu[]>(this.url.backend + "Agente",{ 'headers': this.headers })
    }

    createApolicePessoal(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "ApolicePessoal/Create",body,{ 'headers': this.headers })
    }

    createApoliceSaude(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "ApoliceSaude/Create",body,{ 'headers': this.headers })
    }

    createApoliceVeiculo(body) : Observable<number>{
        return  this.http.post<number>(this.url.backend + "ApoliceVeiculo/Create",body,{ 'headers': this.headers })
    }

    getValorPremioSaude(clienteId) : Observable<any>{
        return  this.http.get<any>(this.url.backend + "ApoliceSaude/CalculatePremio?clienteId="+clienteId,{ 'headers': this.headers })
    }

    getValorPremioVeiculo(veiculoId, acidentesRecentes, dataCartaConducao) : Observable<any>{
        let body = {
            "veiculoId": veiculoId,
            "sinistros": acidentesRecentes,
            "cartaConducao": dataCartaConducao
        }
        return  this.http.post<any>(this.url.backend + "ApoliceVeiculo/CalculatePremio",body,{ 'headers': this.headers })
    }

    getValorPremioPessoal(clienteId,valor) : Observable<any>{
        let body = {
            "clienteId": clienteId,
            "valor":valor
        }
        return  this.http.post<any>(this.url.backend + "ApolicePessoal/CalculatePremio",body,{ 'headers': this.headers })
    }
}
