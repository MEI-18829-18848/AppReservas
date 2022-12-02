import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ApoliceService} from "../../../../service/apolice.service";
import {VeiculoService} from "../../../../service/veiculo.service";

@Component({
    selector: 'app-aprovar-apolice',
    templateUrl: './aprovar-apolice.component.html',
    styleUrls: ['./aprovar-apolice.component.scss']
})
export class AprovarApoliceComponent implements OnInit {
    id: any;

    //data from DB
    tipoSeguro: any;
    seguro: any;
    agente: any;
    cliente: any;
    fracionamento: any;
    valor: any = 0;
    coberturas: any[] = [];

    //apolice Veiculo
    dataCartaConducao: any;
    acidentesRecentes: any;
    categoriaVeiculo: any;
    veiculo: any;

    //apolice Pessoal
    valorPessoal:any

    apoliceDetailsId:any

    constructor(private route: ActivatedRoute, private apoliceService: ApoliceService, private router: Router, private veiculoService: VeiculoService) {
    }

    ngOnInit(): void {
        this.id = this.route.snapshot.paramMap.get('id')
        this.apoliceService.getApolicebyId(this.id).subscribe(
            res => {
                let apolice
                if(res != null && res != undefined) apolice = res[0];
                if (apolice == null) this.router.navigateByUrl('/#/apolice');
                console.log(apolice)
                this.tipoSeguro = apolice.seguro.tipo
                this.seguro= apolice.seguro.nome
                this.valor = apolice.premio
                let AgenteDetails: any
                if (apolice.agente != null){
                    this.apoliceService.getPessoaByAgenteId(apolice.agente.id).subscribe(resu => {
                        AgenteDetails = resu [0]
                        this.agente = (apolice.agente.id + ' - ' + AgenteDetails.nome)
                    })
                }
                this.fracionamento = apolice.fracionamento
                if(apolice.apolicePessoals.length>0){
                    this.apoliceDetailsId= apolice.apolicePessoals[0].id
                    this.valorPessoal = apolice.apolicePessoals[0].valor
                    this.apoliceService.getCliente(apolice.apolicePessoals[0].clienteId).subscribe(cliRes =>{
                        this.cliente = cliRes[0].id + ' - ' + cliRes[0].pessoa.nome
                    })
                }
                if(apolice.apoliceSaudes.length>0){
                    this.apoliceDetailsId= apolice.apoliceSaudes[0].id
                    this.apoliceService.getCliente(apolice.apoliceSaudes[0].clienteId).subscribe(cliRes =>{
                        this.cliente = cliRes[0].id + ' - ' + cliRes[0].pessoa.nome
                    })
                }
                if(apolice.apoliceVeiculos.length>0){
                    this.apoliceDetailsId= apolice.apoliceVeiculos[0].id
                    this.dataCartaConducao = apolice.apoliceVeiculos[0].dataCartaConducao
                    this.acidentesRecentes = apolice.apoliceVeiculos[0].acidentesRecentes
                    this.veiculoService.getVeiculosId(apolice.apoliceVeiculos[0].veiculoId).subscribe(
                        resuV => {
                            this.categoriaVeiculo = resuV[0].categoriaVeiculo.categoria
                            this.veiculo = resuV[0].matricula + ' - ' + resuV[0].marca + ' ' +resuV[0].modelo
                            this.cliente = resuV[0].clienteId + ' - ' + resuV[0].pessoa.nome
                        }
                    )
                }
                if (apolice.coberturaHasApolices != null){
                    console.log(apolice)
                    apolice.coberturaHasApolices.forEach(element => this.getCoberturaDetails(element));

                }
            }
        )
    }

    getCoberturaDetails(cob){
        console.log(cob)
        this.apoliceService.getCobertura(cob.coberturaId).subscribe(res => {
            console.log(res)
            this.coberturas.push(res)
            console.log(this.coberturas)
        })
    }

    cancelarApolice(){
        switch (this.tipoSeguro){
            case 'Pessoal':
                this.apoliceService.cancelarApolicePessoalSimulacao(this.apoliceDetailsId).subscribe();
                break;
            case 'Veiculo':
                this.apoliceService.cancelarApoliceVeiculoSimulacao(this.apoliceDetailsId).subscribe();
                break;
            case 'Saude':
                this.apoliceService.cancelarApoliceSaudeSimulacao(this.apoliceDetailsId).subscribe();
                break;
        }
    }
    aprovarApolice(){
        switch (this.tipoSeguro){
            case 'Pessoal':
                this.apoliceService.acceptApolicePessoalSimulacao(this.apoliceDetailsId).subscribe(response => {
                    if(response.status == 'Ok') this.router.navigateByUrl('/#/apolice');
                });
                break;
            case 'Veiculo':
                this.apoliceService.acceptApoliceVeiculoSimulacao(this.apoliceDetailsId).subscribe(response => {
                    if(response.status == 'Ok') this.router.navigateByUrl('/#/apolice');
                });
                break;
            case 'Saude':
                this.apoliceService.acceptApoliceSaudeSimulacao(this.apoliceDetailsId).subscribe(response => {
                    if(response.status == 'Ok') this.router.navigateByUrl('/#/apolice');
                });
                break;
        }
    }
}
