import { Pessoa } from "./Pessoa";

export interface ICliente{
    id: Number;
    profissao: string;
    profissaoRisco: Boolean;
    agenteId: number;
    agente: any;
    dadoClinicoId: number;
    dadoClinico: any;
    isLead: number;
    pessoaId: number;
    pessoa: Pessoa;
    apolicePessoals: any;
    apoliceSaudes: any;
    veiculos: any;
}

export class Cliente implements ICliente{
    id: number = 0;
    profissao: string = '';
    profissaoRisco: Boolean = null;
    isLead: number = null;
    agenteId: number = null;
    agente: any = null;
    dadoClinicoId: number = null;
    dadoClinico: any = null;
    pessoaId: number = null;
    pessoa: Pessoa = new Pessoa();
    apolicePessoals: any = [];
    apoliceSaudes: any = [];
    veiculos: any = [];

    constructor(){}
}
