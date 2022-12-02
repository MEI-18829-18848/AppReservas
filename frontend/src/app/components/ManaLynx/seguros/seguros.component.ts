import { Component, OnInit } from '@angular/core';
import { SeguroService } from "../../../service/seguro.service";

@Component({
  selector: 'app-seguros',
  templateUrl: './seguros.component.html',
  styleUrls: ['./seguros.component.scss']
})

export class SegurosComponent implements OnInit {
  seguros: any[];
  tipos_seguro: any[];


  tipo_seguro: string;
  nome_seguro: string;

  cobertura_seguro:string;

  constructor(private seguroService: SeguroService) { }

  ngOnInit(): void {
    this.tipos_seguro = ["Veiculo", "Pessoal", "Saude"];

    this.seguroService.getSeguros().subscribe(res => {
      this.seguros = res
      console.log()
    });
  }

  postSeguro(){
    let body = {
      "nome": this.nome_seguro,
      "ativo": true,
      "tipo": this.tipo_seguro
    }

    let dunno = this.seguroService.createSeguro(body).subscribe(res => {
			console.log(res);
		});
  }

}
