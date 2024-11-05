import {Component} from '@angular/core';
import {AuthRequest} from "../../services/models/auth-request";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent {
    authRequest: AuthRequest = {userEmail: '', password: ''}
    errorMsg: Array<string> = [];
}
