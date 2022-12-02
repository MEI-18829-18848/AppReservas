import { NumberSymbol } from "@angular/common";

export interface ClientesAgente{
    nomeAgente: string;
    amountCliente: number;
}

export interface GanhoTempo {
    data: Date;
    montante: number;
}

export interface ClientesList {
    id: number;
    nome: string;
    profissao: string;
    nApolices: number;
}

export interface AgenteList {
    id: number;
    nAgente: number;
    nome: string;
}

export interface ApoliceTipo{
    tipo: string;
    amount: number;
}

export interface ApoliceList{
    nome: string;
    tipo: string;
    estado: string;
    premio: number;
    fracionamento: string;
    validade: Date;
}


export interface IDashboardGestor{
    ganhoEquipa: number;
    nomeEquipa: string;
    regiaoEquipa: string;
    elementosEquipa: number;
    apolicesEquipa: number;
    leads: number;
    clientesEquipa: number;
    ganhoTempo: GanhoTempo[];
    clientesAgente: ClientesAgente[];
    clientesList: ClientesList[];
    agenteList: AgenteList[];
}

export interface IDashboardAdmin{
    ganhoTotal: number;
    elementosEquipa: number;
    apoliceTotal: number;
    leads: number;
    clientes: number;
    ganhoTempo: GanhoTempo[];
    clientesList: ClientesList[];
    clientesAgente: ClientesAgente[];
    agenteList: AgenteList[];
}


export interface IDashboardAgente{
    ganhoPessoal: number
    nomeEquipa: string;
    regiaoEquipa: string;
    elementosEquipa: number;
    apolicesEncargo: number
    leads: number
    clientesEncargo: number
    ganhoTempo: GanhoTempo[];
    clientesAgente: ClientesAgente[];
    clientesList: ClientesList[];
}

export interface IDashboardCliente{
    gastoPessoal: number;
    nif: string
    nss: string;
    nus: string;
    qtdApolices: number;
    qtdPagamentosPagar: number;
    qtdVeiculos: number
    gastoTempo: GanhoTempo[];
    apoliceTipo: ApoliceTipo[];
    apoliceList: ApoliceList[];
}
