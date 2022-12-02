import { Component, OnInit } from '@angular/core';
import { SelectItem } from 'primeng/api';
import {Cliente} from 'src/app/data/Cliente';
import { Contacto, Tipo } from 'src/app/data/Contacto';
import { Pessoa, EstadoCivil } from 'src/app/data/Pessoa';
import { AuthService } from 'src/app/auth/auth.service';
import { LeadsService } from 'src/app/service/leads.service';
import { PessoaService } from 'src/app/service/pessoa.service';
import { Veiculo } from 'src/app/data/Veiculo';
import { VeiculoService } from 'src/app/service/veiculo.service';
import { ImageService } from 'src/app/service/image.service';

@Component({
  selector: 'app-dados-pessoais',
  templateUrl: './dados-pessoais.component.html',
})
export class DadosPessoaisComponent implements OnInit {
  cliente: Cliente = new Cliente();
  pessoa: Pessoa = new Pessoa();
  newContacto: Contacto = new Contacto();
  contactos: Contacto[] = [];
  newVeiculo: Veiculo = new Veiculo();
  veiculos: Veiculo[] = [];

  userRole: string;
  
  estadoCivil: any;
  categoria: any;
  tipo: any;
  
  estados: SelectItem[] = [];
  categorias: SelectItem[] = [];
  tiposContacto: SelectItem[] = [];

  image: string;

  constructor(private auth: AuthService, private leadsService: LeadsService, 
    private pessoaService: PessoaService, private veiculoService: VeiculoService,
    private imageService: ImageService) { }

  ngOnInit(): void {
    this.image = this.imageService.getImage();
    this.userRole = this.auth.getRole();
    this.getEstadosCivis();
    this.getTipoContacto();
    this.getCategorias();
    this.estadoCivil = this.estados[0].value;
    this.tipo = this.tiposContacto[0].value;
    this.getDadosPessoais();
    this.getContactos();
    this.getVeiculos();
  }

  //#region Enum Starters
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
  
  //#region DadosPessoais
  getDadosPessoais(){
    if(this.userRole === "Cliente"){
      this.leadsService.getCliente(0).subscribe(res => {
        this.cliente = res[0];
        this.pessoa = this.cliente.pessoa;
        this.pessoa.dataNascimento = this.pessoa.dataNascimento.split("T")[0];
        this.pessoa.validadeCc = this.pessoa.validadeCc.split("T")[0];
      })
    }
    else{
      this.pessoaService.getPessoa().subscribe(res => {
        if(res['error'] == undefined){
          this.pessoa = res[0];
          this.pessoa.dataNascimento = this.pessoa.dataNascimento.split("T")[0];
          this.pessoa.validadeCc = this.pessoa.validadeCc.split("T")[0];
        }
      });
    }
  }

  saveDadosPessoais(){
    this.pessoa.estadoCivil = this.estadoCivil.name;
    this.cliente.pessoa = this.pessoa;
    if(this.userRole === "Cliente"){
      console.log(this.cliente);
      this.saveCliente();
    }
    else{
      this.savePessoa();
    }
  }

  saveCliente(){
    this.leadsService.updateCliente(this.cliente).subscribe(res => {
      if(res['error'] === undefined){
        window.location.reload();
      }
    });
  }

  savePessoa(){
    this.pessoaService.updatePessoa(this.pessoa).subscribe(res =>{
      if(res['error'] === undefined){
      }
    });
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
    this.newContacto.pessoaId = this.pessoa.id;
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
        this.veiculos = res;
      }
    });
  }

  saveVeiculo(){
    this.newVeiculo.clienteId = this.cliente.id;
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
}
