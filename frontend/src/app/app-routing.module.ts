import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';
import { ErrorComponent } from './components/error/error.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { AccessComponent } from './components/access/access.component';
import { DashboardComponent } from './components/ManaLynx/dashboard/dashboard.component';
import { AgentesComponent } from './components/ManaLynx/agentes/agentes.component';
import { ApolicesComponent } from './components/ManaLynx/apolices/apolices.component';
import { LeadsComponent } from './components/ManaLynx/leads/leads.component';
import { SegurosComponent } from './components/ManaLynx/seguros/seguros.component';
import { SimulacoesFormComponent } from './components/ManaLynx/simulacoes/simulacoesForm.component';
import { SinistrosComponent } from './components/ManaLynx/sinistros/sinistros.component';
import { RegisterComponent } from './components/register/register.component';
import { RegisterClienteComponent } from './components/register/register-cliente.component';
import { DadosPessoaisComponent } from './components/ManaLynx/dados-pessoais/dados-pessoais.component';
import { DuvidasComponent } from './components/ManaLynx/duvidas/duvidas.component';
import { AuthGuardService as AuthGuard } from './auth/auth-guard.service';
import { AuthGuardLoggedOffService as AuthGuardLoggedOff } from './auth/auth-guard-logged-off.service';
import {AprovarApoliceComponent} from "./components/ManaLynx/apolices/aprovar-apolice/aprovar-apolice.component";
import {ValidarApoliceComponent} from "./components/ManaLynx/apolices/validar-apolice/validar-apolice.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppMainComponent, canActivate: [AuthGuard],
                children: [
                    {path: '', component: DashboardComponent},
                    {path: 'dadospessoais', component: DadosPessoaisComponent},
                    {path: 'agentes', component: AgentesComponent},
                    {path: 'apolices', component: ApolicesComponent},
                    {path: 'leads', component: LeadsComponent},
                    {path: 'seguros', component: SegurosComponent},
                    {path: "simulacoes", component: SimulacoesFormComponent},
                    {path: "sinistros", component: SinistrosComponent},
                    {path: "duvidas", component: DuvidasComponent},
                    {path: "aprovarApolice/:id", component: AprovarApoliceComponent},
                    {path: "validarApolice/:id", component: ValidarApoliceComponent}
                ],
            },
            {path:'pages/landing', component: LandingComponent},
            {path:'pages/login', component: LoginComponent, canActivate: [AuthGuardLoggedOff]},
            {path:'pages/register', component: RegisterComponent, canActivate: [AuthGuardLoggedOff]},
            {path:'pages/register/cliente', component: RegisterClienteComponent, canActivate: [AuthGuard]},
            {path:'pages/error', component: ErrorComponent},
            {path:'pages/notfound', component: NotfoundComponent},
            {path:'pages/access', component: AccessComponent},
            {path: '**', redirectTo: 'pages/notfound'},
        ], {scrollPositionRestoration: 'enabled', anchorScrolling:'enabled'})
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
