import { Component, OnInit } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { AuthService } from './auth/auth.service';
import { ImageService } from './service/image.service';
import { BehaviorSubject } from 'rxjs';

@Component({
    selector: 'app-menu',
    template: `
        <div class="layout-menu-container">
            <ul class="layout-menu" role="menu" (keydown)="onKeydown($event)">
                <li app-menu class="layout-menuitem-category" *ngFor="let item of model; let i = index;" [item]="item" [index]="i" [root]="true" role="none">
                    <div class="layout-menuitem-root-text" [attr.aria-label]="item.label">{{item.label}}</div>
                    <ul role="menu">
                        <li app-menuitem *ngFor="let child of item.items" [item]="child" [index]="i" role="none"></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="logout grid">
            <img *ngIf="this.image" src={{image}} class="col-3">
            <img *ngIf="!this.image" class="col-3">
            <div class="col-4 text-300">{{this.role}}</div>
            <button pButton pRipple type="button" icon="pi pi-user-edit" iconPos="right" class="p-button-secondary col-2 mr-2" routerLink="/dadospessoais"></button>
            <button pButton pRipple type="button" icon="pi pi-power-off" iconPos="right" class="p-button-danger col-2" (click)="logout()"></button>
        </div>
    `
})
export class AppMenuComponent implements OnInit {

    role: string;
    model: any[];
    image : string = null;

    constructor(public appMain: AppMainComponent, private auth: AuthService, private imageService: ImageService) { }

    ngOnInit() {

        let role = this.auth.getRole();
        this.defineMenuItems(role);
        this.image = this.imageService.getImage()
        this.role = this.auth.getRole();

    }

    defineMenuItems(role: string){

        switch(role){
            case "Admin":{
                this.model = [
                    {
                        label: 'Menu Lateral',
                        items:[
                            {label: 'Dashboard',icon: 'pi pi-fw pi-home', routerLink: ['/']},
                            {label: 'Apólices' ,icon: 'pi pi-fw pi-ticket', routerLink: ['/apolices']},
                            {label: 'Sinistros' ,icon: 'pi pi-fw pi-wallet', routerLink: ['/sinistros']},
                            {label: 'Simulações' ,icon: 'pi pi-fw pi-pencil', routerLink: ['/simulacoes']},
                            {label: 'Leads' ,icon: 'pi pi-fw pi-user-plus', routerLink: ['/leads']},
                            {label: 'Agentes' ,icon: 'pi pi-fw pi-id-card', routerLink: ['/agentes']},
                            {label: 'Seguros' ,icon: 'pi pi-fw pi-book', routerLink: ['/seguros']},
                            {label: 'Dúvidas' ,icon: 'pi pi-fw pi-comments', routerLink: ['/duvidas']}
                        ]
                    }
                ];
                break;
            }
            case "Gestor":{
                this.model = [
                    {
                        label: 'Menu Lateral',
                        items:[
                            {label: 'Dashboard',icon: 'pi pi-fw pi-home', routerLink: ['/']},
                            {label: 'Apólices' ,icon: 'pi pi-fw pi-ticket', routerLink: ['/apolices']},
                            {label: 'Sinistros' ,icon: 'pi pi-fw pi-wallet', routerLink: ['/sinistros']},
                            {label: 'Simulações' ,icon: 'pi pi-fw pi-pencil', routerLink: ['/simulacoes']},
                            {label: 'Leads' ,icon: 'pi pi-fw pi-user-plus', routerLink: ['/leads']},
                            {label: 'Dúvidas' ,icon: 'pi pi-fw pi-comments', routerLink: ['/duvidas']}
                        ]
                    }
                ]
                break;
            }
            case "Agente":{
                this.model = [
                    {
                        label: 'Menu Lateral',
                        items:[
                            {label: 'Dashboard',icon: 'pi pi-fw pi-home', routerLink: ['/']},
                            {label: 'Apólices' ,icon: 'pi pi-fw pi-ticket', routerLink: ['/apolices']},
                            {label: 'Sinistros' ,icon: 'pi pi-fw pi-wallet', routerLink: ['/sinistros']},
                            {label: 'Simulações' ,icon: 'pi pi-fw pi-pencil', routerLink: ['/simulacoes']},
                            {label: 'Leads' ,icon: 'pi pi-fw pi-user-plus', routerLink: ['/leads']},
                            {label: 'Dúvidas' ,icon: 'pi pi-fw pi-comments', routerLink: ['/duvidas']}
                        ]
                    },
                ]
                break;
            }
            case "Cliente":{
                this.model = [
                    {
                        label: 'Menu Lateral',
                        items:[
                            {label: 'Dashboard',icon: 'pi pi-fw pi-home', routerLink: ['/']},
                            {label: 'Apólices' ,icon: 'pi pi-fw pi-ticket', routerLink: ['/apolices']},
                            {label: 'Sinistros' ,icon: 'pi pi-fw pi-wallet', routerLink: ['/sinistros']},
                            {label: 'Simulações' ,icon: 'pi pi-fw pi-pencil', routerLink: ['/simulacoes']},
                            {label: 'Dúvidas' ,icon: 'pi pi-fw pi-comments', routerLink: ['/duvidas']}
                        ]
                    },
                ]
                break;
            }
        }
    }

    logout(){
        this.auth.logout();
    }

    onKeydown(event: KeyboardEvent) {
        const nodeElement = (<HTMLDivElement> event.target);
        if (event.code === 'Enter' || event.code === 'Space') {
            nodeElement.click();
            event.preventDefault();
        }
    }
}
