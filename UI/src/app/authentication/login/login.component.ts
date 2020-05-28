import { Component, OnInit } from '@angular/core';

import{FormGroup, FormControl, FormBuilder, Validators} from'@angular/forms';
import { Router } from '@angular/router';
import { UserRegistrationService } from 'src/app/services/user-registration.service';
import { UserRegistrationDetails } from 'src/app/model/user-registration';
import { UserserviceService } from 'src/app/services/DataServices/userservice.service';
import { UserAuthService } from 'src/app/services/user-auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username:string;
  password:string;
  submitted=false;
  errorMessage : string;
  autherized : boolean;
  myFormGroup : FormGroup;
  
  details:Array<UserRegistrationDetails>;
  constructor(public auth :UserAuthService,public router : Router, public ser:UserserviceService ,formBuilder: FormBuilder,public userdetails :UserRegistrationService) {
    this.errorMessage = "Invalid Credentials";
    this.autherized = true;
    console.log("in form bilder of login");
    this.myFormGroup=formBuilder.group({
     
    "username":new FormControl("",Validators.required),
        "password": new FormControl("",Validators.required)
   });
  }
 
   
  get f(){
    return this.myFormGroup.controls;
  }

 
  
  login(){
    
    this.submitted = true;
    
    if(this.myFormGroup.valid){
    console.log("login method");
    this.username= this.myFormGroup.controls['username'].value;
    this.password=this.myFormGroup.controls['password'].value;
    this.submitted = true;
    this.auth.authenticate(this.username, this.password).subscribe(
      // success function
      successData=>{
        console.log("SUCCESS...");
        console.log(successData);
        this.autherized = true;
        
        this.router.navigate(['/media']);
      },
      // failure function
      failureData => {
        console.log("FAILED!!!");
        this.autherized = false;
      }
    );
      console.log(this.auth.authenticate(this.username,this.password));
      console.log("Username : "+this.username+"\n"+"Password : "+this.password);
   }
  }
  
  ngOnInit() {
   
  }

}
