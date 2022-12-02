import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SinistroService } from "../../../service/sinistro.service";
import { AuthService } from "../../../auth/auth.service";

@Component({
  selector: 'app-sinistros',
  templateUrl: './sinistros.component.html',
  styleUrls: ['./sinistros.component.scss']
})
export class SinistrosComponent implements OnInit {
  role: string;

  sinistros: any;
  provas: any[];
  relatorios: any[];
  objectSinistro: any;

  estados: any[];
  sn: any[];
  is_deferido: any[];

  table_prova: boolean;
  table_relatorio: boolean;
  display: boolean;
  display_content: string;

  id: number;
  data: string;
  estado: string;
  descricao: string;
  reembolso: string;
  valido: string;
  deferido: string;

  descricao_prova: string;
  descricao_relatorio: string;
  descricao_deferido: string;

  constructor(private sinistroService: SinistroService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.role = this.authService.getRole();

    this.estados = ["Reportado", "Aguardar Validação", "Resultado Emitido"];
    this.sn = ["Sim", "Não"];
    this.is_deferido = ["Sim", "Não"];
    this.table_prova = false;
    this.table_relatorio = false;

    this.sinistroService.getSinistros().subscribe(res => {
      this.sinistros = res
      this.getThisSinistro(this.sinistros[0]);
      console.log(res)
    });

    if(this.authService.getRole() === "Cliente"){
      this.authService.loggedCliente().subscribe(res => {
        if(this.authService.isNewRegister(res[0])){
            this.router.navigate(['dadospessoais']);
        }
      });
  }
  }

  toggleProva(){
    if (this.table_prova == true)
      this.table_prova = false;
    else
      this.table_prova = true;
  }  
  
  toggleRelatorio(){
    if (this.table_relatorio == true)
      this.table_relatorio = false;
    else
      this.table_relatorio = true;
  }
  
  getThisSinistro(obj){
    this.objectSinistro = obj;
    this.id = obj.id;
    this.data = obj.dataSinistro.split("T", 1);
    this.estado = obj.estado;
    this.descricao = obj.descricao;
    this
    if (obj.reembolso == null) {
      this.reembolso = "0.00"} else { this.reembolso = obj.reembolso}
    if (obj.valido == null || obj.valido == false){
      this.valido = "Não" } else { this.valido = "Sim" }
    if (obj.deferido == null || obj.deferido == false){
      this.deferido = "Não" } else { this.deferido = "Sim" }

      console.log(obj)
    this.provas = obj.provas;
    this.relatorios = obj.relatorioPeritagems;
  }

  putSinistro(){
    let valido: boolean;
    let deferido: boolean;

    if (this.valido === "Não"){ valido = false } else { valido = true };
    if (this.deferido === "Não"){ deferido = false } else { deferido = true };
    
    console.log(valido)
    console.log(this.valido)

    let body = {
      "id": this.id,
      "descricao": this.descricao,
      "reembolso": parseInt(this.reembolso),
      "valido": valido,
      "deferido": deferido,
      "estado": ""
    }

    console.log(body)

    this.sinistroService.putSinistro(this.id, body).subscribe(res => {
      console.log(res);
    });
  }

  submitProva(){
    let body = {
      "conteudo": this.descricao_prova,
      "dataSubmissao": new Date().toISOString().slice(0, 10),
      "sinistroId": this.id
    }

    this.sinistroService.createProva(body).subscribe(res => {
      this.provas.push(res);
      this.getThisSinistro(this.objectSinistro[0]);
    });
  }

  submitRelatorio(){

    let deferido: boolean;
    if (this.descricao_deferido === "Não"){ deferido = false } else { deferido = true };

    let body = {
      "conteudo": this.descricao_relatorio,
      "deferido": deferido,
      "dataRelatorio": new Date().toISOString().slice(0, 10),
      "sinistroId": this.id
    }

    console.log(body)

    this.sinistroService.createRelatorio(body).subscribe(res => {
      console.log(res)
      this.relatorios.push(res)
      this.getThisSinistro(this.objectSinistro[0])
    });
  }

  deleteThisProva(prova){
    console.log("Eliminar o id" + prova)
    /*this.sinistroService.removeProva(prova).subscribe(res => {
      console.log(res)
      this.getThisSinistro(this.objectSinistro[0])
    });*/
  }

  deleteThisRelatorio(relatorio){
    console.log("Eliminar o id" + relatorio)
    /*this.sinistroService.removeRelatorio(relatorio).subscribe(res => {
      console.log(res)
      this.getThisSinistro(this.objectSinistro[0])
    });*/
  }
}
