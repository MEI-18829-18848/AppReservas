export interface ICobertura {
    id: number;
    descricaoCobertura: string;
}

export interface ISeguro {
    id: number;
    nome: string;
    tipo: string;
    ativo: boolean;
    cobertura: ICobertura
}