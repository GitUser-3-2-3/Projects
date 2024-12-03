import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {TokenService} from "../token/token.service";

export const authGuard: CanActivateFn = () => {
    const tokenService = inject(TokenService);
    const router = inject(Router);

    if (tokenService.isNotValid()) {
        router.navigate(['login']).then(
            _ => console.log('invalid token!')
        )
        return false;
    }
    return true;
};
