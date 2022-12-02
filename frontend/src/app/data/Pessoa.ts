export enum EstadoCivil{
    Solteiro,
    Casado,
    "Uniao De Facto",
    Divorciado,
    Viuvo,
}

export interface IPessoa{
    id: number;
    nome: string;
    dataNascimento: any;
    nacionalidade: string;
    cc: string;
    validadeCc: any;
    nif: string;
    nss: string;
    nus: string;
    estadoCivil: string;
    agentes: number[];
    clientes: number[];
    contactos: number[];
    manaUsers: number[];
}

export class Pessoa implements IPessoa{
    id: number = 0;
    nome: string = '';
    dataNascimento: string = null;
    nacionalidade: string = '';
    cc: string = '';
    validadeCc: string = null;
    nif: string = '';
    nss: string = '';
    nus: string = '';
    estadoCivil: string = "";
    agentes: any = [];
    clientes: any = [];
    contactos: any = [];
    manaUsers: any = [];
}