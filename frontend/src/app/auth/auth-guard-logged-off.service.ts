import { Injectable } from '@angular/core';
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardLoggedOffService {

    constructor(public auth: AuthService, public router: Router) { }

    canActivate(): boolean {
        if (this.auth.isLoggedIn()) {
            this.router.navigate(['']);
            return false;
        }
        return true;
    }
}
