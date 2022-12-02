import {Component, OnInit, OnDestroy} from '@angular/core';
import {ConfigService} from '../../service/app.config.service';
import {AppConfig} from '../../api/appconfig';
import {Subscription} from 'rxjs';
import {AuthService} from "../../auth/auth.service";
import {Router} from '@angular/router';
import { RegisterRequest } from 'src/app/data/User';
import { Validation } from 'src/app/data/Validation';

@Component({
    selector: 'app-register-cliente',
    templateUrl: './register-cliente.component.html',
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

        .invalid-info::-webkit-input-placeholder{
          color: red;
        }
        
        .invalid-password{
          color: red;
        }

        .hide{
          display: block;
          height: 0; 
          visibility: hidden;
        }
    `]
})

export class RegisterClienteComponent implements OnInit, OnDestroy {
    user = new RegisterRequest();
    password: string = '';

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
        this.user.username.sub
    }

    register(){
      let invalid:Boolean = false;
      let element:any;
      if(this.user.username === ''){
        element = document.getElementById("username");
        element.placeholder = "Nome de utilizador é necessário";
        element.classList.add('invalid-info', 'ng-invalid', 'ng-dirty');
        invalid = true;
      }

      if(this.user.email === ''){
        element = document.getElementById("email");
        element.placeholder = "Email é necessário";
        element.classList.add('invalid-info', 'ng-invalid', 'ng-dirty');
        invalid = true;
      }

      let validation = this.isPasswordValid();
      if(validation.success == false){
        element = document.getElementById("passwordAux");
        element.classList.remove("hide");
        element.classList.add('invalid-password');
        element.innerHTML = validation.message;
        invalid = true;
      }
      
      if(invalid) return;
      
      this.authService.register(this.user);
      this.router.navigate(['dadospessoais']);
    }
    
    //#region Password Validations
    passwordIsEqual(): boolean{
      if(this.user.password === this.password) return true;
      return false;
    }

    isPasswordValid(): Validation{
      let result = new Validation();
      
      let regex = new RegExp("^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
      let element:any;
      
      if(!this.passwordIsEqual()){
        element.classList.add('ng-invalid', 'ng-dirty');
        result.message = "As passwords são diferentes.";
        return result;
      }
      
      if(this.user.password === '' || this.password === ''){
        element = document.getElementById("password");        
        element.classList.add('ng-invalid', 'ng-dirty');
        element = document.getElementById("passwordRep");
        element.classList.add('ng-invalid', 'ng-dirty');
        result.message = "A password não pode estar vazia.";
        return result;
      }
      // // Password Regex
      // let test = regex.test(this.user.password);
      // if(!test){
      //   result.message = "A password não respeita alguma das regras."
      //   return result;
      // }
      
      result.success = true;
      return result;
    }
    //#endregion

    passwordUpdate():void{
      let element = document.getElementById("password");
      element.classList.remove('ng-invalid', 'ng-dirty');
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}
