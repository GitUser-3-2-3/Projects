import {Component} from '@angular/core';
import {RegisterRequest} from "../../services/models/register-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
    regRequest: RegisterRequest = {firstname: '', lastname: '', dateOfBirth: '', userEmail: '', password: ''}
    validErrorMsg: { Size: string, NotBlank: string, Email: string, NotEmpty: string } =
        {Size: '', NotBlank: '', Email: '', NotEmpty: ''}

    errorMsg: Array<string> = []

    constructor(
        private router: Router,
        private authService: AuthenticationService
    ) {
    }

    register() {
        this.validErrorMsg = {Size: '', NotBlank: '', Email: '', NotEmpty: ''}
        this.errorMsg = [];

        this.authService.registerUser({body: this.regRequest}).subscribe({
            next: () => {
                this.router.navigate(['activate-account']).then(res => {
                    res ? console.log("successful")
                        : console.log("un-successful")
                })
            },
            error: (errRes) => {
                if (errRes.error.validationErrors) {
                    this.validErrorMsg.Email = errRes.error.validationErrors.Email
                    this.validErrorMsg.NotEmpty= errRes.error.validationErrors.NotEmpty
                    this.validErrorMsg.Size = errRes.error.validationErrors.Size
                    this.validErrorMsg.NotBlank = errRes.error.validationErrors.NotBlank
                } else {
                    this.errorMsg = errRes.error.validationErrors;
                }
            }
        });
    }

    login() {
        this.router.navigate(['login']).then(res => {
            res ? console.log("successful")
                : console.log("failed")
        })
    }
}







