import { Component, OnInit } from '@angular/core';
import {SelectItem} from "primeng/api";
import {ApoliceService} from "../../../service/apolice.service";
import {SinistroService} from "../../../service/sinistro.service";
import {AuthService} from "../../../auth/auth.service";
import { Router } from '@angular/router';
import { AgenteService, IAgente} from 'src/app/service/agente.service';

@Component({
    selector: 'app-apolices',
    templateUrl: './apolices.component.html',
    styleUrls: ['./apolices.component.scss']
})

export class ApolicesComponent implements OnInit {
    role: any;

    apolices: any;

    currentAgenteList : any = null;

    id: number;
    cliente_id: number;
    tipo: string;
    seguro: string;
    agente: string;
    fracionamento: string;
    data: string;
    valor: string;
    estadoSimulacao: string;

    is_reportarSinistro: boolean;
    descricao: string;
    dataSinistro: string;

    atribuirAgenteStatus: boolean = false;

    detailApoliceId: number

    constructor(private apoliceService: ApoliceService, private authService: AuthService, private sinistroService: SinistroService, private router: Router, private agenteService: AgenteService) {

    }

    ngOnInit(): void {
        this.role = this.authService.getRole()
        this.is_reportarSinistro = false;

        this.apoliceService.getSeguros().subscribe(res => {
            this.apolices = res
            this.getThisApolice(this.apolices[0])
        });

        if(this.role != 'Cliente') this.agenteService.getAgentes().subscribe(x=> {this.currentAgenteList = x; console.log(x)});

        if(this.authService.getRole() === "Cliente"){
            this.authService.loggedCliente().subscribe(res => {
            if(this.authService.isNewRegister(res[0])){
                this.router.navigate(['dadospessoais']);
            }
            });
    }
  }

  async getThisApolice(obj){

    //Close forms when changing selected apolice
    this.is_reportarSinistro = false;
    this.atribuirAgenteStatus = false;

    let agentePessoa;
    if (obj.agente != null){
        this.apoliceService.getPessoaByAgenteId(obj.agente.id).subscribe(res => {
            agentePessoa = res[0];
            this.agente = obj.agente.id + " - " + agentePessoa.nome;
        });
    }else {
        this.agente = ""
    }


        this.id = obj.id;
        this.tipo = obj.seguro.tipo;
        this.seguro = obj.seguro.nome;
        this.estadoSimulacao = obj.simulacao;

        this.fracionamento = obj.fracionamento;
        if (obj.validade != null) this.data = obj.validade.split("T", 1);
        if (obj.premio != null ) this.valor = obj.premio + "€";
        if(obj.apoliceVeiculos.length>0){
            this.detailApoliceId = obj.apoliceVeiculos[0].id
        }
        if(obj.apolicePessoals.length>0){
          this.detailApoliceId = obj.apolicePessoals[0].id
        }
    }

    cancelarApolice() {
    }

  pagarApolice(){
    this.apoliceService.putPagamento(this.id).subscribe(res => {
      console.log(res);
    });
  }

  reportarSinistro(){
      this.is_reportarSinistro = true;
      this.atribuirAgenteStatus = false;
      console.log("reportarSinistro");
  }

    submeterSinistro() {
        this.is_reportarSinistro = false;

        let body;

        if (this.tipo == "Veiculo") {
            body = {
                "apoliceVeiculoId": this.detailApoliceId,
                "sinistro": {
                    "descricao": this.descricao,
                    "dataSinistro": this.dataSinistro,
                    "estado": ""
                }
            }

            console.log(body);

            let dunno = this.sinistroService.createSinistroVeiculo(body).subscribe(res =>console.log(res));
            console.log(dunno);

        } else {
            body = {
                "apolicePessoalId": this.detailApoliceId,
                "sinistro": {
                    "descricao": this.descricao,
                    "dataSinistro": this.dataSinistro,
                    "estado": ""
                }
            }

            console.log(body);

            let dunno = this.sinistroService.createSinistroPessoal(body).subscribe(res =>console.log(res));
            console.log(dunno);
        }


        console.log(this.descricao);
        console.log(this.dataSinistro);
    }

    atribuirAgenteButtonClick(){
        this.is_reportarSinistro = false
        this.atribuirAgenteStatus = true;

    }

    atribuirAgenteFormButtonClick(agenteId){
        this.is_reportarSinistro = false
        this.atribuirAgenteStatus = false;
        let res;
        this.apoliceService.assignAgenteApolice( agenteId ,this.id).subscribe(x=> {res = x
            this.apoliceService.getSeguros().subscribe(res => {
                this.apolices = res
                this.getThisApolice(this.apolices.find(x => x.id == this.id))
            });
        });

    }

    validarApoliceButtonClick(){
        this.router.navigateByUrl('/validarApolice/'+ this.id);
    }
    aprovarApoliceButtonClick(){
        this.router.navigateByUrl('/aprovarApolice/'+ this.id);
    }
}
