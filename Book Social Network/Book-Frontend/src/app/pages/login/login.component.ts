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
    validErrorMsg: { NotEmpty: string, Size: string, Email: string } = {NotEmpty: '', Size: '', Email: ''}
    errorMsg: Array<string> = [];

    constructor(
        private router: Router,
        private authService: AuthenticationService,
        private tokenService: TokenService
    ) {
    }

    login() {
        this.validErrorMsg = {NotEmpty: '', Size: '', Email: ''}
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
                    this.validErrorMsg.Email = errRes.error.validationErrors.Email
                    this.validErrorMsg.Size = errRes.error.validationErrors.Size
                    this.validErrorMsg.NotEmpty = errRes.error.validationErrors.NotEmpty
                } else {
                    this.errorMsg.push(errRes.error.errorBody)
                }
            }
        });
    }

    register() {
        this.router.navigate(['register']).then(success => {
            success ? console.log("successful")
                : console.log("failed")
        })
    }
}








