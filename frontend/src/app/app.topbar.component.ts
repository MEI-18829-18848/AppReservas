import { Component, OnDestroy , OnInit} from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { Observable, Subscription } from 'rxjs';
import { MenuItem } from 'primeng/api';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth/auth.service';
import { PrimeNGConfig } from 'primeng/api';
import { AppConfig } from './api/appconfig';
import { AppComponent } from './app.component';
import { ConfigService } from './service/app.config.service';
import { AppMenuComponent } from './app.menu.component';
import { ImageService } from './service/image.service';
import { UrlService } from './service/url.service';

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent implements OnInit{

    image : string;
    dashboardRole : string;
    token: string = localStorage.getItem("id_token")
    items: MenuItem[];
    name: string;
    private baseUrl:string;
    headers= new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      });


    active: boolean;

    constructor(public appMain: AppMainComponent, public http: HttpClient, public auth: AuthService, private imageService: ImageService,private urlService:UrlService) {
        this.dashboardRole = this.auth.getRole();
        console.log(this.dashboardRole)
        this.baseUrl = urlService.backend
    }

    onConfigButtonClick(event) {
        this.appMain.configActive = !this.appMain.configActive;
        this.appMain.configClick = true;
        event.preventDefault();
    }

    logout(){
        this.auth.logout();
    }

    getName(): Observable<string>{
        return this.http.get<string>(this.baseUrl + "Pessoa/GetPessoaNameByToken", { 'headers': this.headers })
    }


    ngOnInit(): void {
        this.getName().subscribe(data=> {this.name = data;})
        this.image = this.imageService.getImage()
    }


}
