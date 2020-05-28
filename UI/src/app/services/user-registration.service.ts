import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserRegistrationDetails } from '../model/user-registration';
export const API_URL="http://localhost:3000/user-details";


@Injectable({
  providedIn: 'root'
})
export class UserRegistrationService {

  getAllUsers(){
    return this.http.get(API_URL);
  }

  getOneUser(id:number){
    return this.http.get(API_URL + "/" + id);
  }
  constructor(public http:HttpClient) { }
  addNewUser(details:UserRegistrationDetails){
    return this.http.post(API_URL,details);

   }

  updateUserDetails(details:UserRegistrationDetails,id:number){
    return this.http.post(API_URL+"/"+id,details);
  }
   getUserDetails():any{
   return this.http.get("API_URL");
   }
}
