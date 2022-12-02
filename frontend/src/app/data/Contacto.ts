export enum Tipo{
    Email,
    Telemovel,
    Telefone
}

export interface IContacto{
    id: number;
    valor: string;
    tipo: string;
    pessoaId: number;
}

export class Contacto implements IContacto{
    id: number = 0;
    valor: string = "";
    tipo: string = "";
    pessoaId: number = 0;
}