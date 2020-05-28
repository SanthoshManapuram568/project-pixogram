import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor{

  constructor(public auth : UserAuthService) { }

  // request generated will be auto intercepted and info about request is available in req object
  intercept(request: HttpRequest<any>, next: HttpHandler){
    console.log("Inside interceptor!!!");
    if(this.auth.getAuthenticationToken()){
      // request object cannot be directly manipulated
      // it has to be cloned
      let authenticationToken = this.auth.getAuthenticationToken();    
      request = request.clone({
        setHeaders : {
          Authorization : authenticationToken
        }
      });
    }

    // return back next step : continue to server
    return next.handle(request);
  }
  
}
