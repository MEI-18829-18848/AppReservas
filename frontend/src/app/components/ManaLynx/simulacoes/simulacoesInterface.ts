export interface ISeguro{
    id: number;
    nome: string;
    tipo: string;
    ativo:boolean;
    coberturas: ICobertura[];
}

export interface ICobertura{
    id:number;
    descricaoCobertura: string;
    seguroId: number;
    seguro: any;
    coberturasHasApolices: any;
}

export interface IVeiculo{
    id: number,
    vin: string,
    matricula: string,
    ano: number,
    mes: number,
    marca: string,
    modelo: string,
    cilindrada: number,
    portas: number,
    lugare: number,
    potencia: number,
    peso: number,
    categoriaVeiculo: ICategoriaVeiculo,
    clienteId: number,
    pessoa: IPessoa
}

export interface ICategoriaVeiculo{
    id: number,
    categoria: string
}

export interface IPessoa{
    id: number,
    nome: string,
    dataNascimento: string,
    nacionalidade: string,
    cc: string,
    validadeCc: string,
    nif: number,
    nss: string,
    nus: string,
    estadoCivil: string,
    agentes: any,
    clientes: any,
    contactos: any,
    manaUsers: any
}

export interface ICliente{
    id: number,
    profissao: string,
    profissaoRisco: boolean,
    isLead: boolean,
    agenteId: number,
    agente: any,
    dadoClinicoId: number,
    dadoClinico: any,
    pessoaId: number,
    pessoa: IPessoa,
    apolicePessoals: any[],
    apoliceSaudes: any[],
    veiculos: any[]
}

export interface ISubmitApolicePessoal{
    clienteId: number;
    valor: number;
    apolice: IApolice;
}

export interface ISubmitApoliceVeiculo{
    veiculoId: number;
    dataCartaConducao: string;
    acidentesRecentes: number;
    apolice: IApolice;
}

export interface ISubmitApoliceSaude{
    clienteId: number;
    apolice: IApolice;
}

export interface IApolice{
    premio: number;
    validade: any;
    fracionamento: string;
    seguroId: number;
    agenteId: number;
    simulacao: string;
    coberturaHasApolices: ICoberturaHasApolices[];
}
export interface ICoberturaHasApolices{
    id: number;
    coberturaId: number;
    apoliceId: number;
}

export interface ISubmitApolicePessoal{
    clienteId: number;
    valor: number;
    apolice: IApolice;
}

export interface IAgenteSimu{
    id:number,
    nAgente: number,
    equipaid: number,
    pessoaId: number
}

export interface IAgentesSimu{
    id:number,
    nAgente: number,
    equipaid: number,
    pessoa: IPessoa
}
