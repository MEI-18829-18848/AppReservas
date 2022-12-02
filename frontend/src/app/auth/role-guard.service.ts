import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class RoleGuardService {

    constructor(public auth: AuthService, public router: Router) { }

    canActivate(): boolean {
        if (this.auth.isLoggedIn()) {
            if (this.auth.getRole()!='Admin'){
                this.router.navigate(['']);
                return false;
            }
        }
        return true;
    }
}
