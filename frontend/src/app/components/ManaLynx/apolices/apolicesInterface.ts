export interface ISeguro {
    id: number;
    nome: string;
    ativo: boolean;
    tipo: string;
}

export interface IAgente {
    id: number;
    nAgente: number;
    pessoaId: number;
    equipaId: number;
}

export interface IApolicePessoals {
    id: number;
    apoliceId: number;
    clienteId: number;
    valor: string;
}

export interface IApoliceSaudes {
    id: number;
    apoliceId: number;
    clienteId: number;
}

export interface IApoliceVeiculos {
    id: number;
    apoliceId: number;
    veiculoId: number;
    dataCartaConducao: Date;
    acidentesRecentes: number;
}

export interface IPessoa {
    id: number,
    nome: string,
    dataNascimento: string,
    nacionalidade: string,
    cc: string,
    validadeCC: Date,
    nif: string,
    nss: string,
    nus: string,
    estadoCivil: string
}

export interface IApolice {
    id: number;
    ativa: boolean;
    premio: number;
    validade: Date;
    fracionamento: string;
    simulacao: string;
    seguro: ISeguro;
    agente: IAgente;
    apolicePessoals: IApolicePessoals;
    apoliceSaudes: IApoliceSaudes;
    apoliceVeiculos: IApoliceVeiculos;
}

export interface IApolicePessoal {
    id: number;
    cliente: number
}

export interface IApoliceVeiculo {
    id: number;
    veiculo: IVeiculo
}

export interface IVeiculo {
    id: number;
    clienteId: number;
}