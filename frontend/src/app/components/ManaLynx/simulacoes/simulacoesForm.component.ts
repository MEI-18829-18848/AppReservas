import {Component, OnInit} from '@angular/core';
import {SelectItem} from "primeng/api";
import {DashboardService} from "../../../service/dashboard.service";
import {SimulacaoService} from "../../../service/simulacao.service";
import {
    IApolice,
    ICategoriaVeiculo,
    ICoberturaHasApolices,
    ISeguro,
    ISubmitApolicePessoal, ISubmitApoliceSaude, ISubmitApoliceVeiculo,
    IVeiculo
} from "./simulacoesInterface";
import {registerLocaleData} from '@angular/common';
import {AuthService} from "../../../auth/auth.service";
import {prepareSyntheticPropertyName} from "@angular/compiler/src/render3/util";
import {FormBuilder, FormGroup, Validator, Validators} from "@angular/forms";
import {invalid} from "@angular/compiler/src/render3/view/util";
import { Router } from '@angular/router';

@Component({
    selector: 'app-simulacoes',
    templateUrl: './simulacoesForm.component.html',
    styleUrls: ['./simulacoesForm.component.scss']
})
export class SimulacoesFormComponent implements OnInit {
    role: any

    seguroTipos: SelectItem[];
    selectedSeguroTiposDrop: any;

    seguros: SelectItem[] = [];
    selectedSegurosDrop: any;
    segurosPossible: any;

    selectedDataConducao: any;
    acidentesRecentes: number;

    fracionamento: SelectItem[];
    selectedFracionamentoDrop: any;

    clienteId: any;
    clientes: SelectItem[] = [];
    clientesPossible: any;

    agenteId: any;
    agentes: SelectItem[] = [];
    agentesPossible: any;

    categoriasVeiculo: SelectItem[] = []
    selectedCategoriasVeiculoDrop: any
    categoriasVeiculoPossible: ICategoriaVeiculo[]

    veiculos: SelectItem[] = [];
    selectedVeiculosDrop: any;
    veiculosPossible: IVeiculo[];

    displayCoberturasModal: boolean;

    coberturas: any[] = []
    coberturtasSelected: any[] = []

    valorApolicePessoal: number = 0;

    valorPrevisto: number = 0;

    valorAgravamento: number = 0;

    //formValidations Variables
    tipoApoliceInvalid: boolean = false;
    seguroInvalid: boolean = false;
    agenteInvalid: boolean = false;
    clienteInvalid: boolean = false;
    fracionamentoInvalid: boolean = false;

    dataCartaConducaoInvalid: boolean = false;
    acidentesRecentesInvalid: boolean = false;
    categoriaVeiculoInvalid: boolean = false;
    veiculoInvalid: boolean = false;

    valorInvalid: boolean = false;

    invalidFormModal = false;

    constructor(private simulacaoService: SimulacaoService, private authService: AuthService,
        private router: Router) {
    }

    ngOnInit(): void {
        this.simulacaoService.getSeguros().subscribe(res => {
            this.segurosPossible = res
        });
        this.seguroTipos = [
            {label: 'Pessoal', value: {id: 1, name: 'Pessoal'}},
            {label: 'Saúde', value: {id: 2, name: 'Saude'}},
            {label: 'Veiculos', value: {id: 3, name: 'Veiculo'}}
        ];
        this.fracionamento = [
            {label: 'Mensal', value: {id: 1, name: 'Mensal'}},
            {label: 'Trimestral', value: {id: 2, name: 'Trimestral'}},
            {label: 'Semestral', value: {id: 3, name: 'Semestral'}},
            {label: 'Anual', value: {id: 4, name: 'Anual'}},
        ]
        this.getVeiculos()
        this.getCategoriasVeiculos()
        this.role = this.authService.getRole()
        if (this.role == 'Cliente') {
            this.authService.loggedCliente().subscribe(res => {
                if(this.authService.isNewRegister(res[0])){
                    this.router.navigate(['dadospessoais']);
                }
            });
            this.simulacaoService.getClientes().subscribe(res => {
                //console.log(res)
                this.clienteId = res[0].id
            })
        } else {
            this.simulacaoService.getClientes().subscribe(res => {
                this.clientesPossible = res;
                this.populateClientesSelect()
            })
        }

        if (this.role == 'Admin') {
            this.simulacaoService.getAgentes().subscribe(res => {
                    this.agentesPossible = res
                    this.populateAgentes()
                }
            )
        }
        if (this.role == 'Gestor' || this.role == 'Agente') {
            this.simulacaoService.getAgente().subscribe(res => {
                    this.agenteId = res[0].id
                }
            )
        }
    }

    updateSeguros() {
        let segurosAux: any = []
        let selected = this.selectedSeguroTiposDrop
        if (selected != undefined) {
            this.segurosPossible.forEach(function (value) {
                if (value.tipo == selected.name && value.ativo == true) {
                    segurosAux.push({label: value.nome, value: {id: value.id, name: value.nome}})
                }
            })
        }
        this.seguros = segurosAux
    }

    getVeiculos() {
        this.simulacaoService.getVeiculos().subscribe(res => {
            this.veiculosPossible = res
            this.populateVeiculoOptions()
        });
    }


    populateVeiculoOptions() {
        let id = this.clienteId
        let aux = []
        let categoria
        let seguro = this.selectedSegurosDrop
        if (this.selectedCategoriasVeiculoDrop != undefined) categoria = this.selectedCategoriasVeiculoDrop.name

        function veiculoSegCheck(categoria: string, any) {
            switch (seguro.name) {
                case 'Motociclos':
                    return categoria.includes('Motociclo')
                    break
                case 'Ciclomotores':
                    return categoria.includes('Ciclomotor')
                case 'Veiculos de Passageiros':
                    return categoria.includes('Passageiros')
                case  'Veiculos de Mercadorias':
                    return categoria.includes('Mercadorias')
            }
            return false
        }

        this.veiculosPossible.forEach(function (value) {
                if (value.clienteId == id || id == 0 || id == undefined) {

                    if ((categoria == undefined && seguro == undefined) || (categoria == undefined && veiculoSegCheck(value.categoriaVeiculo.categoria, seguro)
                        || value.categoriaVeiculo.categoria == categoria)
                    ) {
                        aux.push({
                            label: value.matricula + ' - ' + value.marca + ' ' + value.modelo + '- ' + value.pessoa.nome,
                            value: {id: value.id, name: value.matricula}
                        })
                    }

                }
            }
        )
        this.veiculos = aux
    }

    getCategoriasVeiculos() {
        this.simulacaoService.getCategoriasVeiculos().subscribe(res => {
            this.categoriasVeiculoPossible = res
        })
    }

    updateCategoriasVeiculo() {
        if (this.selectedSeguroTiposDrop.name == 'Veiculo') {
            this.veiculos = []
            let auxList: SelectItem[] = []
            switch (this.selectedSegurosDrop.name) {
                case('Motociclos'):
                    this.categoriasVeiculoPossible.forEach(function (value) {
                            if (value.categoria.includes('Motociclo')) {
                                auxList.push({label: value.categoria, value: {id: value.id, name: value.categoria}})
                            }
                        }
                    )
                    break;
                case ('Veiculos de Mercadorias'):
                    this.categoriasVeiculoPossible.forEach(function (value) {
                            if (value.categoria.includes('Mercadorias')) {
                                auxList.push({label: value.categoria, value: {id: value.id, name: value.categoria}})
                            }
                        }
                    )
                    break;
                case('Ciclomotores'):
                    this.categoriasVeiculoPossible.forEach(function (value) {
                            if (value.categoria.includes('Ciclomotor')) {
                                auxList.push({label: value.categoria, value: {id: value.id, name: value.categoria}})
                            }
                        }
                    )
                    break;
                case ('Veiculos de Passageiros'):
                    this.categoriasVeiculoPossible.forEach(function (value) {
                            if (value.categoria.includes('Passageiros')) {
                                auxList.push({label: value.categoria, value: {id: value.id, name: value.categoria}})
                            }
                        }
                    )
                    break;
            }
            this.populateVeiculoOptions()
            this.categoriasVeiculo = auxList
        }
    }

    updateCoberturasTable() {
        this.coberturtasSelected = []
        let seguro = this.selectedSegurosDrop
        let coberturas = []
        if (seguro != undefined) {
            this.segurosPossible.forEach(function (value) {
                if (value.id == seguro.id) {
                    value.coberturas.forEach(function (cob) {
                        coberturas.push(cob)
                    })
                }
            })
        }
        this.coberturas = coberturas
    }

    populateClientesSelect() {
        let clientesList = []
        this.clientesPossible.forEach(function (value) {
            clientesList.push({label: value.id + ' - ' + value.pessoa.nome, value: value.id})
        })
        this.clientes = clientesList
    }

    submitForm() {
        if (this.isValidForm() == true) {
            let tipoSeguro: string = this.selectedSeguroTiposDrop.name;
            switch (tipoSeguro) {
                case 'Pessoal':
                    let submitObjectPessoal: ISubmitApolicePessoal = new class implements ISubmitApolicePessoal {
                        apolice: IApolice;
                        clienteId: number;
                        valor: number;
                    };
                    submitObjectPessoal.valor = this.valorApolicePessoal;
                    submitObjectPessoal.clienteId = this.clienteId;
                    let seguroPessoalApolice: IApolice = new class implements IApolice {
                        agenteId: number;
                        fracionamento: string;
                        premio: number;
                        seguroId: number;
                        simulacao: string;
                        validade: any;
                        coberturaHasApolices: ICoberturaHasApolices[];
                    };
                    seguroPessoalApolice.premio = this.valorPrevisto+this.valorAgravamento;
                    if(this.role == "Cliente") seguroPessoalApolice.simulacao = "Não Validada"; else seguroPessoalApolice.simulacao = "Validada";
                    seguroPessoalApolice.agenteId = this.agenteId
                    seguroPessoalApolice.seguroId = this.selectedSegurosDrop.id
                    seguroPessoalApolice.fracionamento = this.selectedFracionamentoDrop.name;
                    seguroPessoalApolice.coberturaHasApolices = []
                    this.coberturtasSelected.forEach(function (value) {
                        let cob: ICoberturaHasApolices = new class implements ICoberturaHasApolices {
                            apoliceId: number;
                            coberturaId: number;
                            id: number;
                        }
                        cob.coberturaId = value
                        //console.log(cob)
                        seguroPessoalApolice.coberturaHasApolices.push(cob)
                    })
                    submitObjectPessoal.apolice = seguroPessoalApolice
                    console.log(submitObjectPessoal)
                    this.simulacaoService.createApolicePessoal(submitObjectPessoal).subscribe(res => console.log(res));
                    break;
                case 'Veiculo':
                    let submitObjectVeiculo: ISubmitApoliceVeiculo = new class implements ISubmitApoliceVeiculo {
                        acidentesRecentes: number;
                        apolice: IApolice;
                        dataCartaConducao: string;
                        veiculoId: number;
                    }
                    submitObjectVeiculo.dataCartaConducao = this.selectedDataConducao;
                    submitObjectVeiculo.acidentesRecentes = this.acidentesRecentes;
                    submitObjectVeiculo.veiculoId = this.selectedVeiculosDrop.id;
                    let seguroVeiculosApolice: IApolice = new class implements IApolice {
                        agenteId: number;
                        fracionamento: string;
                        premio: number;
                        seguroId: number;
                        simulacao: string;
                        validade: any;
                        coberturaHasApolices: ICoberturaHasApolices[];
                    };
                    if(this.role == "Cliente") seguroVeiculosApolice.simulacao = "Não Validada"; else seguroVeiculosApolice.simulacao = "Validada";
                    seguroVeiculosApolice.premio = this.valorPrevisto+this.valorAgravamento;
                    seguroVeiculosApolice.agenteId = this.agenteId
                    seguroVeiculosApolice.seguroId = this.selectedSegurosDrop.id
                    seguroVeiculosApolice.fracionamento = this.selectedFracionamentoDrop.name;
                    seguroVeiculosApolice.coberturaHasApolices = []
                    this.coberturtasSelected.forEach(function (value) {
                        let cob: ICoberturaHasApolices = new class implements ICoberturaHasApolices {
                            apoliceId: number;
                            coberturaId: number;
                            id: number;
                        }
                        cob.coberturaId = value
                        //console.log(cob)
                        seguroVeiculosApolice.coberturaHasApolices.push(cob)
                    })
                    submitObjectVeiculo.apolice = seguroVeiculosApolice
                    console.log(submitObjectVeiculo)
                    this.simulacaoService.createApoliceVeiculo(submitObjectVeiculo).subscribe(res => console.log(res));
                    break;
                case 'Saude':
                    let submitObjectSaude: ISubmitApoliceSaude = new class implements ISubmitApoliceSaude {
                        apolice: IApolice;
                        clienteId: number;
                    }
                    submitObjectSaude.clienteId = this.clienteId;
                    let seguroSaudeApolice: IApolice = new class implements IApolice {
                        agenteId: number;
                        fracionamento: string;
                        premio: number;
                        seguroId: number;
                        simulacao: string;
                        validade: any;
                        coberturaHasApolices: ICoberturaHasApolices[];
                    };
                    seguroSaudeApolice.premio = this.valorPrevisto+this.valorAgravamento;
                    seguroSaudeApolice.agenteId = this.agenteId
                    seguroSaudeApolice.seguroId = this.selectedSegurosDrop.id
                    seguroSaudeApolice.fracionamento = this.selectedFracionamentoDrop.name;
                    if(this.role == "Cliente") seguroSaudeApolice.simulacao = "Não Validada"; else seguroSaudeApolice.simulacao = "Validada";
                    seguroSaudeApolice.coberturaHasApolices = []
                    this.coberturtasSelected.forEach(function (value) {
                        let cob: ICoberturaHasApolices = new class implements ICoberturaHasApolices {
                            apoliceId: number;
                            coberturaId: number;
                            id: number;
                        }
                        cob.coberturaId = value
                        //console.log(cob)
                        seguroSaudeApolice.coberturaHasApolices.push(cob)
                    })
                    submitObjectSaude.apolice = seguroSaudeApolice
                    console.log(submitObjectSaude)
                    this.simulacaoService.createApoliceSaude(submitObjectSaude).subscribe(res => console.log(res));
                    break;
            }
            this.router.navigateByUrl('/#/apolice');
        } else {
            this.invalidFormModal = true
        }
    }

    getValorPrevisto() {
        if (this.isValidForm() == true) {
            let tipoSeguro: string = this.selectedSeguroTiposDrop.name;
            let fracionamento = this.selectedFracionamentoDrop.name;
            let valorCalc: number;
            switch (tipoSeguro) {
                case 'Pessoal':
                    let valor = this.valorApolicePessoal;
                    let clienteIdPessoal = this.clienteId;

                    this.simulacaoService.getValorPremioPessoal(clienteIdPessoal,valor).subscribe(res => {
                        valorCalc = res
                        valorCalc+= this.coberturtasSelected.length * 20
                        valorCalc = this.getValorWithAddedFracionamento(valorCalc, fracionamento)
                        this.valorPrevisto= valorCalc+ this.valorAgravamento
                    })
                    break;
                case 'Veiculo':
                    let dataCartaConducao = this.selectedDataConducao;
                    let acidentesRecentes = this.acidentesRecentes;
                    let veiculoId = this.selectedVeiculosDrop.id;
                    this.simulacaoService.getValorPremioVeiculo(veiculoId,acidentesRecentes,dataCartaConducao).subscribe(res => {
                        valorCalc = res
                        valorCalc+= this.coberturtasSelected.length * 15
                        valorCalc = this.getValorWithAddedFracionamento(valorCalc, fracionamento)
                        this.valorPrevisto= valorCalc+ this.valorAgravamento
                    })
                    break;
                case 'Saude':

                    let clienteIdSaude = this.clienteId;
                    console.log(clienteIdSaude)
                    this.simulacaoService.getValorPremioSaude(clienteIdSaude).subscribe(res => {
                        valorCalc = res
                        valorCalc+= this.coberturtasSelected.length * 10
                        valorCalc = this.getValorWithAddedFracionamento(valorCalc, fracionamento)
                        this.valorPrevisto= valorCalc+ this.valorAgravamento
                    })
                    break;
            }
        }
    }

    getValorWithAddedFracionamento(valor, fracioamento):number{
        switch (fracioamento) {
            case 'Mensal':
                return (valor+(valor*0.25))/12
                break;
            case 'Trimestral':
                return (valor+(valor*0.10))/4
                break;
            case 'Semestral':
                return (valor+(valor*0.05))/2
                break;
            case 'Anual':
                return valor
                break;
        }
          return 0
    }

    coberturaClick(id: number) {
        if (this.coberturtasSelected.includes(id)) {
            const index = this.coberturtasSelected.indexOf(id, 0);
            if (index > -1) {
                this.coberturtasSelected.splice(index, 1);
            }
        } else {
            this.coberturtasSelected.push(id)
        }
        console.log(this.coberturtasSelected)
        this.getValorPrevisto()
    }

    populateAgentes() {
        let agentesList = []
        this.agentesPossible.forEach(function (value) {
            agentesList.push({label: value.id, value: value.id})
        })
        this.agentes = agentesList
    }




    //Form Validation
    inputSeguroTipoInvalid() {
        if (this.selectedSeguroTiposDrop == undefined) {
            this.tipoApoliceInvalid = true
        } else {
            this.tipoApoliceInvalid = false
        }
    }

    inputSeguroInvalid() {
        if (this.selectedSegurosDrop == undefined) {
            this.seguroInvalid = true
        } else {
            this.seguroInvalid = false
        }
    }

    inputAgenteInvalid() {
        if (this.agenteId == undefined && this.role == 'Admin') {
            this.agenteInvalid = true
        } else {
            this.agenteInvalid = false
        }
    }

    inputClienteInvalid() {
        if (this.clienteId == undefined && this.role != 'Cliente') {
            this.clienteInvalid = true
        } else {
            this.clienteInvalid = false
        }
    }

    inputFracionamentoInvalid() {
        if (this.selectedFracionamentoDrop == undefined) {
            this.fracionamentoInvalid = true
        } else {
            this.fracionamentoInvalid = false
        }
    }

    inputCartaConducaoInvalid() {
        if (this.selectedSeguroTiposDrop != undefined) {
            if (this.selectedSeguroTiposDrop.name == 'Veiculo' && this.selectedDataConducao == undefined) {
                this.dataCartaConducaoInvalid = true
            } else {
                this.dataCartaConducaoInvalid = false
            }
        } else {
            this.dataCartaConducaoInvalid = false
        }
    }

    inputAcidentesRecentesInvalid() {
        if (this.selectedSeguroTiposDrop != undefined) {
            if (this.selectedSeguroTiposDrop.name == 'Veiculo' && this.acidentesRecentes == null) {
                this.acidentesRecentesInvalid = true
            } else {
                this.acidentesRecentesInvalid = false
            }
        } else {
            this.acidentesRecentesInvalid = false
        }
    }

    inputCategoriaVeiculoInvalid() {
        if (this.selectedSeguroTiposDrop != undefined) {
            if (this.selectedSeguroTiposDrop.name == 'Veiculo' && this.selectedCategoriasVeiculoDrop == undefined) {
                this.categoriaVeiculoInvalid = true
            } else {
                this.categoriaVeiculoInvalid = false
            }
        } else {
            this.categoriaVeiculoInvalid = false
        }
    }

    inputVeiculoSelectInvalid() {
        //console.log(this.selectedVeiculosDrop)
        if (this.selectedSeguroTiposDrop != undefined) {
            if (this.selectedSeguroTiposDrop.name == 'Veiculo' && this.selectedVeiculosDrop == undefined) {
                this.veiculoInvalid = true
            } else {
                this.veiculoInvalid = false
            }
        } else {
            this.veiculoInvalid = false
        }
    }

    inputValorInvalid() {
        if (this.selectedSeguroTiposDrop != undefined) {
            if (this.selectedSeguroTiposDrop.name == 'Pessoal' && this.valorApolicePessoal == null) {
                this.valorInvalid = true
            } else {
                this.valorInvalid = false
            }
        } else {
            this.valorInvalid = false
        }
    }

    isValidForm() {
        let apoliceTipo = this.selectedSeguroTiposDrop;
        if (apoliceTipo == undefined) return false;
        switch (apoliceTipo.name) {
            case 'Saude':
                this.inputSeguroInvalid()
                this.inputAgenteInvalid()
                this.inputClienteInvalid()
                this.inputFracionamentoInvalid()
                if (this.seguroInvalid == false &&
                    this.agenteInvalid == false &&
                    this.clienteInvalid == false &&
                    this.fracionamentoInvalid == false) {
                    return true;
                }
                return false;
            case 'Veiculo':
                this.inputSeguroInvalid()
                this.inputAgenteInvalid()
                this.inputClienteInvalid()
                this.inputFracionamentoInvalid()
                this.inputCartaConducaoInvalid()
                this.inputAcidentesRecentesInvalid()
                this.inputCategoriaVeiculoInvalid()
                this.inputVeiculoSelectInvalid()
                if (this.seguroInvalid == false &&
                    this.agenteInvalid == false &&
                    this.clienteInvalid == false &&
                    this.fracionamentoInvalid == false &&
                    this.dataCartaConducaoInvalid == false &&
                    this.acidentesRecentesInvalid == false &&
                    this.categoriaVeiculoInvalid == false &&
                    this.veiculoInvalid == false) {
                    return true;
                }
                return false;
            case 'Pessoal':
                this.inputSeguroInvalid()
                this.inputAgenteInvalid()
                this.inputClienteInvalid()
                this.inputFracionamentoInvalid()
                this.inputValorInvalid()
                if (this.seguroInvalid == false &&
                    this.agenteInvalid == false &&
                    this.clienteInvalid == false &&
                    this.fracionamentoInvalid == false &&
                    this.valorInvalid == false) {
                    return true;
                }
                return false;
        }
        return false

    }
}
