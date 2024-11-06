import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
    selector: 'app-activate-account',
    templateUrl: './activate-account.component.html',
    styleUrls: ['./activate-account.component.scss']
})
export class ActivateAccountComponent {
    isOkay: boolean = true;
    submitted: boolean = false;

    constructor(
        private router: Router,
        private authService: AuthenticationService
    ) {
    }

    onCodeCompletion(actToken: string) {
        this.confirmAccount(actToken)
    }

    redirectToLogin() {
        this.router.navigate(['login']).then(res => {
            res ? console.log("successful")
                : console.log("un-successful")
        })
    }

    private confirmAccount(actToken: string) {
        this.authService.activateAccount({token: actToken}).subscribe({
            next: () => {
                this.isOkay = true;
                this.submitted = true;
            },
            error: () => {
                this.isOkay = false;
                this.submitted = true;
            }
        })
    }
}








