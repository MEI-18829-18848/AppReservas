import { Component, OnDestroy, OnInit } from '@angular/core';
import { elementAt, Subscription } from 'rxjs';
import { AppConfig } from 'src/app/api/appconfig';
import { ConfigService } from 'src/app/service/app.config.service';
import { DashboardService } from 'src/app/service/dashboard.service';
import { AgenteList, IDashboardAdmin, IDashboardAgente, IDashboardCliente, IDashboardGestor} from './dashboardInterface'; 
import { ClientesList } from './dashboardInterface';
import { AuthService } from 'src/app/auth/auth.service';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { UrlConstants } from 'src/app/app.main.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, OnDestroy{
  endpoint = "https://localhost:4200";
   
  public dashboardGestor: IDashboardGestor = null;
  
  public dashboardAdmin: IDashboardAdmin = null;

  public dashboardCliente: IDashboardCliente = null;

  public dashboardAgente: IDashboardAgente = null;

  clientesList: ClientesList[]
  agenteList: AgenteList[]

  dashboardRole: any;

  rowGroupMetadata: any;

  lineData: any;

  barData: any;

  lineOptions: any;

  barOptions: any;

  config: AppConfig;

  subscription: Subscription;
  
  constructor(public configService: ConfigService, 
              private dashService: DashboardService,
              private authService: AuthService,
              private datePipe: DatePipe,
              private router: Router) { }

  ngOnInit() {
    //Configs
    this.config = this.configService.config;
    this.subscription = this.configService.configUpdate$.subscribe(config => {this.config = config;});0

    this.dashboardRole = this.authService.getRole();

    //switch dashboard based on user role acessing
    switch(this.dashboardRole){
        case "Admin":{
            this.dashService.getDashboardAdmin()
                            .subscribe(data=> {this.dashboardAdmin = data; 
                                        this.clientesList = this.dashboardAdmin.clientesList     
                                        this.agenteList = this.dashboardAdmin.agenteList;                                               
                                        this.buildChart();});
            break;
        }
        case "Gestor":{
            this.dashService.getDashboardGestor()
                            .subscribe(data=> {this.dashboardGestor = data;  
                                                this.clientesList = this.dashboardGestor.clientesList; 
                                                this.agenteList = this.dashboardGestor.agenteList;
                                                this.buildChart();});
            break;
        }
        case "Agente":{
            this.dashService.getDashboardAgente()
                            .subscribe(data=> {this.dashboardAgente = data; 
                                                this.clientesList = this.dashboardAgente.clientesList;
                                                this.buildChart();});
            break;
        }  
        case "Cliente":{
            this.authService.loggedCliente().subscribe(res => {
                if(this.authService.isNewRegister(res[0])){
                    this.router.navigate(['dadospessoais']);
                }
                else{
                    this.dashService.getDashboardCliente().subscribe(data=> {this.dashboardCliente = data; this.buildChart();});
                }
            });
            break;
        }

    }
  }

  buildChart(){

    //Defines the labels for line graph
    var labelsLineGraph : string[] = [];
    //Initializes data for line graph
    var lineData : number[] = [];

    //Defines labels for bar graph
    var labelsBarGraph : string[] = [];
    //Initializes data for bar graph
    var barData : number[] = [];


    //Build Line Chart 
    switch(this.dashboardRole){
        case "Admin":{
            this.dashboardAdmin.ganhoTempo.forEach(element=>{
                lineData.push(element.montante);
                labelsLineGraph.push(this.datePipe.transform(element.data, 'yyyy-MM'));
            });
            this.dashboardAdmin.clientesAgente.forEach(element=>{
                barData.push(element.amountCliente);
                labelsBarGraph.push(element.nomeAgente);
            })
            break;
        }
        case "Gestor":{
            //Defines the data for line graph
            this.dashboardGestor.ganhoTempo.forEach(element=>{
                lineData.push(element.montante);
                labelsLineGraph.push(this.datePipe.transform(element.data, 'yyyy-MM'));
            });
            this.dashboardGestor.clientesAgente.forEach(element=>{
                barData.push(element.amountCliente);
                labelsBarGraph.push(element.nomeAgente);
            })

            break;
        }
        case "Agente":{
            //Defines the data for line graph
            this.dashboardAgente.ganhoTempo.forEach(element=>{
                lineData.push(element.montante);
                labelsLineGraph.push(this.datePipe.transform(element.data, 'yyyy-MM'));
            });
            this.dashboardAgente.clientesAgente.forEach(element=>{
                barData.push(element.amountCliente);
                labelsBarGraph.push(element.nomeAgente);
            })
            break;
        }
        case "Cliente":{
            //Defines the data for line graph
            this.dashboardCliente.gastoTempo.forEach(element=>{
                lineData.push(element.montante);
                labelsLineGraph.push(this.datePipe.transform(element.data, 'yyyy-MM'));
            });

            this.dashboardCliente.apoliceTipo.forEach(element=>{
                barData.push(element.amount);
                labelsBarGraph.push(element.tipo);
            })
            break;
        }
    }
    
    this.lineData = {
        labels: labelsLineGraph,
        datasets: [
            {
                label: 'Valor em €',
                data: lineData,
                fill: false,
                backgroundColor: '#2f4860',
                borderColor: '#2f4860',
                tension: .4
            },
        ]
    };

    //Build Bar Chart 

    this.barData = {
        labels: labelsBarGraph,
        datasets: [
            {
                label: "Quantidade de Clientes",
                backgroundColor: '#2f4860',
                data: barData
            }
        ]
    };

    this.barOptions = {
        plugins: {
            legend: {
                labels: {
                    color: '#495057'
                }
            }
        },
        scales: {
            x: {
                ticks: {
                    color: '#495057'
                },
                grid: {
                    color:  '#ebedef',
                }
            },
            y: {
                ticks: {
                    color: '#495057'
                },
                grid: {
                    color:  '#ebedef',
                }
            },
        },
    };
  }

  ngOnDestroy() {
      if (this.subscription) {
          this.subscription.unsubscribe();
      }
  }
}
