import { Component, OnInit, ViewChild, ChangeDetectorRef, ElementRef } from '@angular/core';
import { Table } from 'primeng/table';
import { MessageService, ConfirmationService } from 'primeng/api'
import { EquipaService, IEquipa, IGestor, ICreateEquipa} from 'src/app/service/equipa.service';
import { AgenteService, IAgente, ICreateAgente, IPessoaAgente, ICreateAgenteUser } from 'src/app/service/agente.service';

@Component({
	selector: 'app-agentes',
	templateUrl: './agentes.component.html',
	providers: [MessageService, ConfirmationService],
	styles: [`
        :host ::ng-deep  .p-frozen-column {
            font-weight: bold;
        }

        :host ::ng-deep .p-datatable-frozen-tbody {
            font-weight: bold;
        }

        :host ::ng-deep .p-progressbar {
            height:.5rem;
        }
    `]
})

export class AgentesComponent implements OnInit {

	estadosCivis: string[] = [
		"Solteiro",  
		"Casado",
		"Uniao de Facto",
		"Divorciado",
		"Viuvo"
	]

  	equipas : Array<IEquipa> = [];

  	agentes : Array<IAgente> = [];

  	currentAgente : IAgente;

  	currentEquipa : {
		id: number,
        nome : string
  	}

	submitEquipaObj : ICreateEquipa = new class implements ICreateEquipa{
		id: number;
		nome: string = "";
		regiao: string = "";
		gestorId: number;
	}

	submitAgenteObj : ICreateAgente = new class implements ICreateAgente{
		id: number;
		nAgente: number = 0;
		equipaId: number = 0;
		pessoa : IPessoaAgente = new class implements IPessoaAgente{
			id:number;
			nome:string = "";
			dataNascimento: Date = null;
			nacionalidade: string = "";
			cc: string = "";
			validadeCc: Date = null;
			nif:string = "";
			nss: string = "";
			nus: string = "";
			estadoCivil: string = "";
		};
		pessoaId = 0;
	}

	submitGestorObj : ICreateAgenteUser = new class implements ICreateAgenteUser{
		username: string = "";
		email: string = "";
		userRole: string = "Agente";
		pessoaId: number;
		password: string = "";
	}

	formState: boolean;

	buttonAgenteState: boolean;


	rowGroupMetadata: any;

	@ViewChild('dt') table: Table;

	@ViewChild('filter') filter: ElementRef;

	constructor(private messageService: MessageService, 
				private confirmService: ConfirmationService, 
				private equipaService : EquipaService,
				private agenteService : AgenteService,
				private confirmationService: ConfirmationService) {}

	ngOnInit() {
			
		//Initialize needed variables
		this.currentEquipa = {id:0, nome:"Nenhuma"};
		this.currentAgente = null;

		//Get list of equipas
		this.equipaService.getEquipas().subscribe(data=> {this.equipas = data})

		//Define the default state for forms
		this.formState = false;
		this.buttonAgenteState = false;

	}

	changeFormState(state:boolean){
		this.formState = state
		this.buttonAgenteState = state
	}

	changeCurrentAgente(agente: IAgente){
		this.currentAgente = agente;
		console.log(this.currentAgente);
	}

	getAgentesFromEquipa(equipaId: number, nomeEquipa: string){

			//Gets all agentes from a certain equipa
			this.agenteService.getAgenteByEquipa(equipaId).subscribe(data=> {this.agentes = data})
			this.currentEquipa.id = equipaId; 
			this.currentEquipa.nome = nomeEquipa;

	}

	confirmEquipa(event: Event) {
		this.confirmationService.confirm({
			key: 'confirmEquipa',
			target: event.target,
			message: 'Tem a certeza que deseja prosseguir?',
			icon: 'pi pi-exclamation-triangle',
			accept: () => {
				//Form validation before submit
				let flag = 0;

				if(this.submitEquipaObj.nome.length > 40 || this.submitEquipaObj.nome == "") {
					flag = 1
					this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Nome Inválido'});
				}
				if(this.submitEquipaObj.regiao.length > 40 || this.submitEquipaObj.regiao == "") {
					flag = 1
					this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Região Inválida'});
				}
				if(this.currentAgente == undefined) {
					flag = 1
					this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Agente Inválido'});
				}
				
				//Proceed with the creation of Equipa
				if (flag == 0){
					this.createEquipa(this.submitEquipaObj, this.currentAgente.id)
					this.messageService.add({severity: 'success', summary: 'Confirmado', detail: 'Equipa Adicionada'});
				}
			},
			reject: () => {
				
			}
		});
	}

	createEquipa(equipa: ICreateEquipa, agenteId:number){
		console.log(equipa)
		console.log(agenteId)
		this.equipaService.createEquipa(equipa)
			.subscribe(x=> {this.equipaService.getEquipas().subscribe(e => this.equipas = e);
							let gestor = new class implements IGestor { 
								agenteId: number = agenteId; 
								equipaId: number = x.id;
							};
							this.equipaService.createGestor(gestor).subscribe(e=> console.log(e));})
	}

	confirmAgente(event: Event){
		this.confirmationService.confirm({
		key: 'confirmAgente',
		target: event.target,
		message: 'Tem a certeza que deseja prosseguir?',
		icon: 'pi pi-exclamation-triangle',
		accept: () => {
			//Form Validation Before Submit
			let flag = 0;
			if(this.currentEquipa.id == 0 || this.currentEquipa.id == undefined){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Equipa Inválida'});
			}
			if(this.submitAgenteObj.nAgente == 0 || this.submitAgenteObj.nAgente == undefined){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Numero Agente Inválido'});
			}
			if(this.submitAgenteObj.pessoa.nome.length > 40 || this.submitAgenteObj.pessoa.nome == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Nome Inválido'});
			}
			if(this.submitAgenteObj.pessoa.dataNascimento == null){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Data Nascimento Inválida'});
			}
			if(this.submitAgenteObj.pessoa.nacionalidade.length > 40 || this.submitAgenteObj.pessoa.nacionalidade == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Nacionalidade Inválida'});
			}
			if(this.submitAgenteObj.pessoa.validadeCc == null){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Validade CC Inválida'});
			}
			if(!/^[0-9]+$/.test(this.submitAgenteObj.pessoa.cc) || this.submitAgenteObj.pessoa.cc == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'CC inválido'});
			}
			if(!/^[0-9]+$/.test(this.submitAgenteObj.pessoa.nif) || this.submitAgenteObj.pessoa.nif == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'NIF inválido'});
			}
			if(!/^[0-9]+$/.test(this.submitAgenteObj.pessoa.nss) || this.submitAgenteObj.pessoa.nss == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'NSS inválido'});
			}
			if(!/^[0-9]+$/.test(this.submitAgenteObj.pessoa.nus) || this.submitAgenteObj.pessoa.nus == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'NUS inválido'});
			}
			if(this.submitAgenteObj.pessoa.estadoCivil == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Estado Civil Inválido'})
			}
			if(this.submitGestorObj.email.length > 40 || this.submitGestorObj.email == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Email Inválido'})
			}
			if(this.submitGestorObj.username.length > 20 || this.submitGestorObj.email == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Username Inválido'})
			}
			if(this.submitGestorObj.password.length > 20 || this.submitGestorObj.password == ""){
				flag = 1
				this.messageService.add({severity: 'error', summary: 'Rejected', detail: 'Password Inválida'})
			}
		

			//Proceed with the creation of agente
			if (flag == 0){
				this.submitAgenteObj.equipaId = this.currentEquipa.id;
				this.createAgente(this.submitAgenteObj, this.submitGestorObj)
				this.messageService.add({severity: 'success', summary: 'Confirmado', detail: 'Agente Adicionado'});
			}
			
		},
		reject: () => {
			
		}
	});
	}


	createAgente(agente: ICreateAgente, user: ICreateAgenteUser){
		this.agenteService.createAgente(agente)
		.subscribe(x=>{this.agenteService.getAgenteByEquipa(x.equipaId).subscribe(x=> this.agentes = x);
						user.pessoaId = x.pessoaId;
						this.agenteService.createUserForAgente(user).subscribe();})
	}

}
