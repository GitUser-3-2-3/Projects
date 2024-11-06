import {Component} from '@angular/core';
import {AuthRequest} from "../../services/models/auth-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

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
        // another service
    ) {
    }

    login() {
        this.errorMsg = [];
        this.authService.verifyUser({body: this.authRequest}).subscribe({
            next: (result) => {
                // todo save the token
                this.router.navigate(['api/v1/books']).then(success => {
                    success ? console.log("successful")
                        : console.log("failed")
                })
            },
            error: (err) => {
                console.log(err)
                if (err.error.validationErrors) {
                    this.errorMsg = err.error.validationErrors
                } else {
                    this.errorMsg.push(err.error.errorBody)
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








