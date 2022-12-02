import { Pessoa } from "./Pessoa";

export enum Roles{
    Admin,
    Gestor,
    Agente,
    Cliente
}

export interface User{
    name: string,
    token: string,
    expiration: string
}

export class  RegisterRequest{
    username: string = '';
    password: string = '';
    userRole: string = '';
    email: string = '';
}

export class ManaUser{
    id: number = 0;
    username: string = "";
    email: string = "";
    userRole: Roles = null;
    // loginCredentialId: number = 0;
    pessoaId: number = 0;
    pessoa: Pessoa = new Pessoa();
}