import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';
import { IDashboardGestor } from '../components/ManaLynx/dashboard/dashboardInterface';
import { IDashboardAgente } from '../components/ManaLynx/dashboard/dashboardInterface';
import { IDashboardAdmin } from '../components/ManaLynx/dashboard/dashboardInterface';
import { IDashboardCliente } from '../components/ManaLynx/dashboard/dashboardInterface';
import { Observable } from 'rxjs';
import { UrlConstants } from '../app.main.component';
import { UrlSerializer } from '@angular/router';
import { UrlService } from './url.service';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  headers= new HttpHeaders();

  constructor(private http: HttpClient, private url: UrlService) { }

  getDashboardAdmin() : Observable<IDashboardAdmin>{
    return this.http.get<IDashboardAdmin>(this.url.backend + "DadosEstatisticos/DashboardAdmin");
  }

  getDashboardGestor() : Observable<IDashboardGestor>{
    return this.http.get<IDashboardGestor>(this.url.backend + "DadosEstatisticos/DashboardGestor");
  }

  getDashboardAgente() : Observable<IDashboardAgente>{
    return this.http.get<IDashboardAgente>(this.url.backend + "DadosEstatisticos/DashboardAgente");
  }

  getDashboardCliente(): Observable<IDashboardCliente>{
    return this.http.get<IDashboardCliente>(this.url.backend + "DadosEstatisticos/DashboardCliente")
  }

}
