import {Component, OnInit, OnDestroy, HostListener} from '@angular/core';
import {ConfigService} from '../../service/app.config.service';
import {AppConfig} from '../../api/appconfig';
import {Subscription} from 'rxjs';
import {AuthService} from "../../auth/auth.service";
import {Router} from '@angular/router';
 

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        :host ::ng-deep .p-password input {
            width: 100%;
            padding: 1rem;
        }

        :host ::ng-deep .pi-eye {
            transform: scale(1.6);
            margin-right: 1rem;
            color: var(--secundary-color) !important;
        }

        :host ::ng-deep .pi-eye-slash {
            transform: scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }
    `]
})

export class LoginComponent implements OnInit, OnDestroy {

    username: string;

    password: string;

    config: AppConfig;

    subscription: Subscription;


    constructor(public configService: ConfigService,
                public authService: AuthService,
                private router: Router) {
    }



    ngOnInit(): void {
        this.config = this.configService.config;
        this.subscription = this.configService.configUpdate$.subscribe(config => {
            this.config = config;
        });

    }

    logIn(){
        this.authService.login(this.username, this.password);
    }

    
    //Press enter to login
    @HostListener('window:keyup.enter', ['$event'])
    keyEvent(event: KeyboardEvent){
        this.logIn();
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}
