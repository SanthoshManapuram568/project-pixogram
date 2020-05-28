import { Injectable } from '@angular/core';
import { UserserviceService } from './DataServices/userservice.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserRegistrationDetails } from '../model/user-registration';
import {map} from 'rxjs/operators';
import { Response } from '../model/response.model';
const VALIDATION_URL = "http://localhost:8765/user-service/login";

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
  Id : number ;
  userlist : Array<UserRegistrationDetails>;
  constructor(public ser : UserserviceService,public http : HttpClient) { 
    
  }
  authenticate(username : string, password : string) {
    // create a security token
    // create a security token
    let authenticationToken = "Basic " + window.btoa(username + ":" + password);
    console.log(authenticationToken);
 
    let headers = new HttpHeaders({
      Authorization : authenticationToken
    });
    console.log("calling server")
    // send the request
    return this.http.get(VALIDATION_URL, {headers}).pipe(
      // success function
      map((successData : Response)=>{
        sessionStorage.setItem("user",username);
        // save the token
        let response : Response =successData;
        sessionStorage.setItem("token", authenticationToken);
        sessionStorage.setItem("userid", response.userId + "");
        sessionStorage.setItem("profilePic",response.profilePic)
        sessionStorage.setItem("firstName",response.firstName);
        sessionStorage.setItem("lastName",response.lastName);
        sessionStorage.setItem("Email",response.email);
        return successData;
      }),
      // failure function
      map(failureData=>{
        // console message 
        return failureData;
      })
    );
   }
   getPic():string{
    let a = sessionStorage.getItem('profilePic');
    return a;
  }
 
   getAuthenticationToken(){
     if(this.isUserLoggedIn())
       return sessionStorage.getItem("token");
     return null; 
   }
 
   isUserLoggedIn(): boolean{
     let user = sessionStorage.getItem('user');
     if(user == null)
       return false;
     return true;  
   }
   
   /*getId():number{
     let a = Number(sessionStorage.getItem('uid'));
     console.log("in getid"+this.Id)
     return a;
   }*/
   getId():string{
    let user = sessionStorage.getItem('userid');
    return user;
  }
   
   logout(){
   sessionStorage.clear();  
   }
 
   getUserDetails():string{
     let user = sessionStorage.getItem('user');
     return user;
   }
  
 }
 