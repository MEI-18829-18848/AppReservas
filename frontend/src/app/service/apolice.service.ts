import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {IDashboardGestor} from "../components/ManaLynx/dashboard/dashboardInterface";
import {Observable} from "rxjs";
import {
    IApolice,
    IPessoa,
    IApolicePessoal
} from "../components/ManaLynx/apolices/apolicesInterface";
import { UrlService } from './url.service';
import {Cliente} from "../data/Cliente";

interface AgenteChange{
    agenteId: number,
    apoliceId: number
}


@Injectable({
  providedIn: 'root'
})
export class ApoliceService {

    headers= new HttpHeaders();
    headersPost =new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});


    constructor(private http: HttpClient, private url: UrlService) {
    }

    getSeguros() : Observable<IApolice[]>{
        return this.http.get<IApolice[]>(this.url.backend + "Apolice", { 'headers': this.headers })
    }

    getPessoaByAgenteId(id) : Observable<IPessoa[]>{
        return this.http.get<IPessoa[]>(this.url.backend + "Agente/AgentePessoaId/" + id, { 'headers': this.headers })
    }

    getApolicebyId(id): Observable<IApolice>{
        return  this.http.get<IApolice>(this.url.backend + "Apolice/"+ id, { 'headers': this.headers } )
    }

    putPagamento(id) : Observable<number>{
        return  this.http.put<number>(this.url.backend + "Pagamento/pagar?apoliceId=" + id, { 'headers': this.headers })
    }

    assignAgenteApolice(idAgente, idApolice){

        let obj = new class implements AgenteChange{
            agenteId: number = idAgente;
            apoliceId: number = idApolice;
        }
        return this.http.put<any>(this.url.backend + "UpdateAgenteApolice", obj)
    }

    getCobertura(id) : Observable<any>{
        return  this.http.get(this.url.backend+ "Cobertura/ViewById?Id="+ id,{ 'headers': this.headers } )
    }

    cancelarApolicePessoalSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apolicePessoalId": '+id+'}');
        return this.http.post(this.url.backend+ "ApolicePessoal/CancelarSimulacao", body, { 'headers': this.headersPost })
    }

    acceptApolicePessoalSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apolicePessoalId": '+id+'}');
        return this.http.post(this.url.backend+ "ApolicePessoal/AcceptSimulacao", body, { 'headers': this.headersPost })
    }

    validarApolicePessoalSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apolicePessoalId": '+id+'}');
        return this.http.post(this.url.backend+ "ApolicePessoal/ValidateSimulacao", body, { 'headers': this.headersPost })
    }

    cancelarApoliceVeiculoSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apoliceVeiculoId": '+id+'}');
        return this.http.post(this.url.backend+ "ApoliceVeiculo/CancelarSimulacao", body, { 'headers': this.headersPost })
    }

    acceptApoliceVeiculoSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apoliceVeiculoId": '+id+'}');
        return this.http.post(this.url.backend+ "ApoliceVeiculo/AcceptSimulacao", body, { 'headers': this.headersPost })
    }

    validarApoliceVeiculoSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apoliceVeiculoId": '+id+'}');
        return this.http.post(this.url.backend+ "ApoliceVeiculo/ValidateSimulacao", body, { 'headers': this.headersPost })
    }

    cancelarApoliceSaudeSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apoliceSaudeId": '+id+'}');
        return this.http.post(this.url.backend+ "ApoliceSaude/CancelarSimulacao", body, { 'headers': this.headersPost })
    }

    acceptApoliceSaudeSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apoliceSaudeId": '+id+'}');
        console.log(body)
        return this.http.post(this.url.backend+ "ApoliceSaude/AcceptSimulacao", body, { 'headers': this.headersPost })
    }

    validarApoliceSaudeSimulacao(id):Observable<any>{
        let body= JSON.parse('{"apoliceSaudeId": '+id+'}');
        return this.http.post(this.url.backend+ "ApoliceSaude/ValidateSimulacao", body, { 'headers': this.headersPost })
    }

    getCliente(id): Observable<Cliente>{
        return this.http.get<any>(this.url.backend+ "Cliente/Index/"+id, { 'headers': this.headers })
    }

}
