import {Component} from '@angular/core';
import {AuthRequest} from "../../services/models/auth-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {TokenService} from "../../services/token/token.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {
    authRequest: AuthRequest = {userEmail: '', password: ''}
    errorMsg: Array<string> = [];

    constructor(
        private router: Router,
        private authService: AuthenticationService,
        private tokenService: TokenService
    ) {
    }

    login() {
        this.errorMsg = [];
        this.authService.verifyUser({body: this.authRequest}).subscribe({

            next: (response) => {
                this.tokenService.token = response.token as string;

                this.router.navigate(['api/v1/books']).then(success => {
                    success ? console.log("successful")
                        : console.log("failed")
                })
            },
            error: (errRes) => {
                console.log(errRes)

                if (errRes.error.validationErrors) {
                    this.errorMsg = errRes.error.validationErrors
                } else {
                    this.errorMsg.push(errRes.error.errorBody)
                }
            }
        });
    }

    register() {
        this.router.navigate(['api/v1/auth/register']).then(success => {
            success ? console.log("successful")
                : console.log("failed")
        })
    }
}








