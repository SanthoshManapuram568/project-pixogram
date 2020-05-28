import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
//import { AuthenticationService } from './authentication.service';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  // recieve authentication service injected
  constructor(public auth : UserAuthService, public router : Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      if(this.auth.isUserLoggedIn())
          return true;
      else{
        this.router.navigate(['/login']);
        return false;
      }    
          
  }

}
