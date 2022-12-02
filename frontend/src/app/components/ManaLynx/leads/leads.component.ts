import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/data/Cliente';
import { LeadsService } from 'src/app/service/leads.service';
import { EstadoCivil, Pessoa } from 'src/app/data/Pessoa';
import { SelectItem } from 'primeng/api';
import { Contacto, Tipo } from 'src/app/data/Contacto';
import { DadosPessoaisComponent } from '../dados-pessoais/dados-pessoais.component';
import { VeiculoService } from 'src/app/service/veiculo.service';
import { Veiculo } from 'src/app/data/Veiculo';
import { AuthService } from 'src/app/auth/auth.service';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Router } from '@angular/router';

@Component({
  selector: 'app-leads',
  templateUrl: './leads.component.html',
})
export class LeadsComponent implements OnInit {
  leads: Cliente[] = [];
  clientes: Cliente[] = [];

  newLead: Cliente = new Cliente();
  contacto: Contacto = new Contacto();
  newContacto: Contacto = new Contacto();
  contactos: Contacto[] = [];

  newVeiculo: Veiculo = new Veiculo();
  veiculos: Veiculo[] = [];

  userRole: string;

  createCliente: boolean = false;

  estadoCivil: any;
  categoria: any;
  tipo: any;

  estados: SelectItem[] = [];
  categorias: SelectItem[] = [];
  tiposContacto: SelectItem[] = [];

  constructor(private leadsService: LeadsService, private veiculoService: VeiculoService
    , private router: Router) { }
  
  ngOnInit(): void {
    this.getEstadosCivis();
    this.getTipoContacto();
    this.getCategorias();
    this.estadoCivil = this.estados[0].value;
    this.tipo = this.tiposContacto[0].value;
    this.getLeads();
  }

  //#region Enum Start
  getEstadosCivis(){
    for(let i:number = EstadoCivil.Solteiro; i <= EstadoCivil.Viuvo; i++){
      this.estados.push({label: EstadoCivil[i], value: {id: i, name: EstadoCivil[i]}});
    }
  }

  getTipoContacto(){
    for(let i:number = Tipo.Email; i <= Tipo.Telefone; i++){
      this.tiposContacto.push({label: Tipo[i], value: {id: i, name: Tipo[i]}});
    }
  }

  getCategorias(){
    this.veiculoService.getCategorias().subscribe(res => {
      if(res['error'] === undefined){
        res.forEach(element => {
          this.categorias.push({label: element.categoria, value: {id: element.id, name:element.categoria}})
        });
        this.categoria = this.categorias[0].value;
      }
    });
  }
  //#endregion

  //#region Leads
  getLeads(){
    this.clientes = [];
    this.leads = [];
    this.leadsService.getClientes().subscribe(res => {
      if(res !== undefined && res.length > 0){
        res.forEach(element => {
          if(element.isLead === 1){
            this.leads.push(element);
          }
          else if(element.isLead === 0){
            this.newLead = element;
            if(!this.isLeadEmpty()){
              this.clientes.push(element);
            }
            this.newLead = new Cliente();
          }
        });
      }
    });
  }

  getLeadInfo(lead: Cliente){
    this.createCliente = false;
    this.newLead = new Cliente();
    this.leadsService.getCliente(lead.id.valueOf()).subscribe(res => {
      this.newLead = res[0];
      // Set DropDown
      this.estados.forEach(element => {
        if(element.label === this.newLead.pessoa.estadoCivil){
          this.estadoCivil = element.value;
        }
      });
      // Set DateTime
      this.newLead.pessoa.dataNascimento = this.newLead.pessoa.dataNascimento.split("T")[0];
    });
    this.leadsService.getContacto(lead.pessoa.id).subscribe(res => {
      this.contacto = res[0];
      if(this.contacto == undefined){
        this.contacto = new Contacto();
        return;
      }
      // Set DropDown
      this.tiposContacto.forEach(element => {
        if(element.label === this.contacto.tipo){
          this.tipo = element.value;
        }
      });
    });
  }

  addNewLead(){
    this.leadsService.postCliente(this.newLead).subscribe(res => {
      if(res['error'] === undefined){
        this.contacto.pessoaId = res['pessoaId'];
        this.contacto.tipo = this.tipo.name;
        this.leadsService.postContacto(this.contacto).subscribe(res => {
          if(res['error'] === undefined){
            this.resetNewLead();
            this.getLeads();
          }
          else{
            // Do modal for error
          }
        });
      }
      else{
        // Do modal for error
      }
    });
  }

  resetNewLead(){
    this.newLead = new Cliente();
    this.contacto = new Contacto();
  }

  saveLead(){
    // console.log(this.newLead)
    this.newLead.isLead = 1;
    this.newLead.pessoa.estadoCivil = this.estadoCivil.name;
    // If Exists: Update
    // Else     : Create
    this.leadsService.getCliente(this.newLead.id).subscribe(res => {
        console.log(res)
      if(res.length == 1 ){
        this.leadsService.updateCliente(this.newLead).subscribe(res => {
          if(res['error'] === undefined){
            this.saveContacto();
          }
        });
      }
      else{
        this.addNewLead();
      }
      this.getLeads();
    });
  }

  deleteCliente(cliente:Cliente){
    this.createCliente = false;
    this.newLead = new Cliente();
    this.leadsService.deleteCliente(cliente).subscribe(res => {
        this.getLeads();
      });
    window.location.reload();
  }
  //#endregion

  //#region Contactos
  getContactos(){
    this.leadsService.getContacto(0).subscribe(res => {
      if(res['error'] == undefined){
        this.contactos = res;
      }
    });
  }

  saveContacto(){
    this.newContacto.tipo = this.tipo.name;
    this.newContacto.pessoaId = this.newLead.pessoa.id;
    this.newContacto.tipo = this.tipo.name;
    if(this.newContacto.id === 0){
      this.leadsService.postContacto(this.newContacto).subscribe(res => {
        if(res['error'] === undefined){
        }
      });
      return;
    }
    this.leadsService.getContacto(this.newContacto.id).subscribe(res => {
      if(res['error'] === undefined){
        this.leadsService.updateContacto(this.newContacto).subscribe(res =>{
          if(res['error'] === undefined){
          }
        });
      }
      this.getContactos();
    });
  }

  selectContacto(contacto: Contacto){
    this.newContacto = contacto;
    // Set DropDown
    this.tiposContacto.forEach(element => {
      if(element.label === this.newContacto.tipo){
        this.tipo = element.value;
      }
    });
  }

  deleteContacto(contacto: Contacto){
    this.leadsService.deleteContacto(contacto.id).subscribe(res => {
      if(res['error'] === undefined){
        this.getContactos();
      }
    });
  }

  resetContacto(){
    this.newContacto = new Contacto();
  }
  //#endregion

  //#region Veiculo
  getVeiculos(){
    this.veiculoService.getVeiculos().subscribe(res => {
      if(res['error'] == undefined){
        res.forEach(element => {
          if(element.clienteId === this.newLead.id){
            this.veiculos.push(element);
          }
        });
      }
    });
  }

  saveVeiculo(){
    this.newVeiculo.clienteId = this.newLead.id;
    this.newVeiculo.categoriaVeiculoId = this.categoria.id;
    this.veiculoService.getVeiculos().subscribe(res => {
      if(res.find(x => x.id == this.newVeiculo.id) === undefined){
        this.veiculoService.postVeiculo(this.newVeiculo).subscribe(res => {
          if(res['error'] === undefined){
            this.resetVeiculo();
            this.getVeiculos();
          }
        });
      }
      else{
        this.veiculoService.updateVeiculo(this.newVeiculo).subscribe(res => {
          if(res['error'] === undefined){
            this.resetVeiculo();
            this.getVeiculos();
          }
        });
      }
      this.getVeiculos();
    });
  }

  resetVeiculo(){
    this.newVeiculo = new Veiculo();
    this.categoria = null;
  }

  selectVeiculo(veiculo: Veiculo){
    this.newVeiculo = veiculo;
    this.categoria = this.categorias[this.newVeiculo.categoriaVeiculo.id - 1].value;
  }

  deleteVeiculo(veiculo: Veiculo){
    this.veiculoService.deleteVeiculo(veiculo.id).subscribe(res => {
      if(res['error'] === undefined){
        this.getVeiculos();
      }
    });
  }
  //#endregion

  //#region Cliente
  getDadosPessoais(cliente: Cliente){
    this.leadsService.getCliente(cliente.id).subscribe(res => {
      this.newLead = res[0];
      this.newLead.pessoa.dataNascimento = this.newLead.pessoa.dataNascimento.split("T")[0];
      this.newLead.pessoa.validadeCc = this.newLead.pessoa.validadeCc.split("T")[0];
    })
  }

  addCliente(){
    this.createCliente = true;
  }

  selectCliente(cliente: Cliente){
    this.newLead = cliente;
    this.getDadosPessoais(cliente);
    if(this.newLead != null){
      this.createCliente = true;
    }
    this.leadsService.getContacto(cliente.pessoaId).subscribe(res => {
      this.contactos = res;
    });
    this.veiculoService.getVeiculos().subscribe(res => {
      res.forEach(element => {
        if(element.clienteId === this.newLead.id){
          this.veiculos.push(element);
        }
      });
    });
  }

  saveCliente(){
    this.newLead.isLead = 0;
    if(this.newLead.profissaoRisco === null) this.newLead.profissaoRisco = false;
    if(this.newLead.id === 0 && !this.isLeadEmpty()){
      this.leadsService.postCliente(this.newLead).subscribe(res => {
        if(res['error'] === undefined){
        }
      });
    }
    else if(!this.isLeadEmpty()){
        console.log('ola')
      this.leadsService.updateCliente(this.newLead).subscribe(res => {
        if(res['error'] === undefined){
        }
      });
    }
    this.cancelCliente();
  }

  cancelCliente(){
    this.createCliente = false;
    this.newLead = new Cliente();
    this.leads = [];
    this.clientes = [];
    this.getLeads();
    this.contactos = [];
    this.veiculos = [];
  }
  //#endregion

  isLeadEmpty(){
    if(this.newLead.pessoa.nome === null || this.newLead.pessoa.dataNascimento === null || this.newLead.pessoa.nacionalidade === null
      || this.newLead.pessoa.cc === null || this.newLead.pessoa.validadeCc === null || this.newLead.pessoa.nif === null
      || this.newLead.pessoa.nss === null || this.newLead.pessoa.nus === null || this.newLead.pessoa.estadoCivil === null
      || this.newLead.profissao === null|| this.newLead.profissaoRisco === null){
          return true;
      }
      return false;
  }
}
