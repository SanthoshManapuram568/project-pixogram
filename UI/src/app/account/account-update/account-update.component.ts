import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from'@angular/forms';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { UserserviceService } from 'src/app/services/DataServices/userservice.service';
//import { AuthenticationService } from 'src/app/services/authentication.service';
@Component({
  selector: 'app-account-update',
  templateUrl: './account-update.component.html',
  styleUrls: ['./account-update.component.css']
})
export class AccountUpdateComponent implements OnInit {
  user:string;
  pass:string;
  repass:string;
  mail:string;
  myFormGroup : FormGroup;
  file : Response;
  constructor(public auth : UserAuthService, public formBuilder: FormBuilder, public userService : UserserviceService) {
    console.log("in form bilder of re-reg");
    //this.userService
    this.myFormGroup=formBuilder.group({
      "username":new FormControl(""),
      "password": new FormControl(""),
      "repassword":new FormControl(""),
      "email":new FormControl("")

    });

  }
  reReg(){
    console.log("Re-registration method");
    this.user= this.myFormGroup.controls['username'].value;
    this.pass=this.myFormGroup.controls['password'].value;
    this.repass=this.myFormGroup.controls['repassword'].value;
    this.mail=this.myFormGroup.controls['email'].value;
   console.log("Username : "+this.user+"\n"+"Password : "+this.pass+"\n"+"Re-password : "+this.repass+"\n"+"Email : "+this.mail);
  }

message():void{

  alert("Logged out!!!");
  this.auth.logout();
}
  ngOnInit() {
    
  }

}
