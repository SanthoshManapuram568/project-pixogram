import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from'@angular/forms';

import { Router } from '@angular/router';
import { UserRegistrationService } from 'src/app/services/user-registration.service';
import { UserRegistrationDetails } from 'src/app/model/user-registration';
import { UserserviceService } from 'src/app/services/DataServices/userservice.service';

//import { AuthenticationService } from 'src/app/services/authentication.service';
import { HttpResponse, HttpEventType,HttpEvent } from '@angular/common/http';
import { UploadMediaService } from 'src/app/services/upload/upload-media.service';
import { UserName } from 'src/app/model/usernames.model';
import { UserAuthService } from 'src/app/services/user-auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  public imagePath;
  imgURL: any;
  public message: string;

  selectedFiles: FileList;
  currentFileUpload: File;
  // dynamic class containing only one field 
  // anonymous class
  progress: { percentage: number } = { percentage: 0 };

  
  userId : string;
  url : string;
  date : Date;

  submitted : boolean = false;
  unamestatus : boolean = false;

  firstName : string;
  lastName : string;
  userName : string;
  email : string; 
  password : string;
  repassword : string;
  profilePic : any;
  avilable:string="";
  validate : boolean=false;
  myFormGroup : FormGroup;
  userlist : Array<UserRegistrationDetails>;
  usernames : Array<UserName>;
  errorPassword : string ="";
  errorUser : string="";
  userNameValidate : boolean = false;
  passwordValidate : boolean = false;
  constructor(public details : UserRegistrationService,public auth : UserAuthService,public uploadService : UploadMediaService  ,public router : Router, formBuilder: FormBuilder,public ser : UserserviceService) {
    console.log("in form bilder of reg");
    this.myFormGroup=formBuilder.group({
      "firstName" : new FormControl("",[Validators.required,Validators.pattern('^[A-Za-z]{3,15}$')]),
      "lastName" : new FormControl("",[Validators.required,Validators.pattern('^[A-Za-z]{3,15}$')]),
      "userName" : new FormControl("",[Validators.required,Validators.pattern('^[A-Za-z0-9]{3,15}$')]),
      "email" : new FormControl("",[Validators.email,Validators.required]),
      "password" : new FormControl("",[Validators.required,Validators.minLength(8),Validators.maxLength(12)]),
      "repassword" : new FormControl("",[Validators.required,Validators.minLength(8),Validators.maxLength(12)]),
      "profilePic" : new FormControl("",Validators.required)
    });

  }
  
  get f(){return this.myFormGroup.controls;}
  
  //checking user name is available or not

  getUserNames():void
  {
    this.ser.getUserNames().subscribe(data => {
      this.usernames = data;
     
      for( let name of this.usernames)
      {
       if(name.userName==this.myFormGroup.get('userName').value)
       {
         this.errorUser="Username not Available!!"
         this.userNameValidate=true; 
         break;
       }
       else
       this.userNameValidate=false;
       this.errorUser="Username Available"
      }})
  }

//checking password if matched with repeat password

  checkPassword(){
    console.log("in check password");
    this.password = this.myFormGroup.controls['password'].value;
    this.repassword = this.myFormGroup.controls['repassword'].value;
    if (this.repassword === this.password){
      console.log("in if loop"+ this.password+":"+this.repassword);
      
      this.errorPassword = "";
      this.passwordValidate = true;
    }
    else{
      console.log("in else loop"+this.password+":"+this.repassword);
      this.passwordValidate=false;
      this.errorPassword = "Password not matched !!! ";
    }
   }


  reg(){
    console.log("Registration method");
    this.submitted = true;
    if(this.myFormGroup.valid && this.passwordValidate){
    this.firstName = this.myFormGroup.controls['firstName'].value;
    this.lastName = this.myFormGroup.controls['lastName'].value;
   
    this.password=this.myFormGroup.controls['password'].value;
    console.log(this.password);
    this.repassword=this.myFormGroup.controls['repassword'].value;
    this.userName=this.myFormGroup.controls['userName'].value;
    
    this.email=this.myFormGroup.controls['email'].value;
    
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);
   
    this.date = new Date();
    let dateString = `_${this.date.getTime()}_${this.date.getDate()}_${this.date.getFullYear()}`
    if (this.currentFileUpload.type == 'image/png') {
      this.profilePic = `${this.userName}${dateString}.png`;
    }
    if (this.currentFileUpload.type == 'image/jpeg' || this.currentFileUpload.type == 'image/jpg') {
      this.profilePic = `${this.userName}${dateString}.jpeg`;
    }
    console.log(this.profilePic);
    console.log(this.userName);
    this.uploadService.pushFileToStorage(this.currentFileUpload,this.userName,this.firstName,this.lastName,this.password,this.email,this.profilePic,).subscribe((event:HttpEvent<{}>) => {
  
    if (event.type === HttpEventType.UploadProgress) {
  
      this.progress.percentage = Math.round(100 * event.loaded / event.total);
    } else if (event instanceof HttpResponse) {
  
      this.router.navigate(['/login']);
      console.log('File is completely uploaded!');
    }
  });

   }else{
     return alert("Please enter valid details..")
   }
  
}

selectFile(event) {
  this.selectedFiles = event.target.files;
  this.preview(this.selectedFiles);
}

// list of files selected
preview(files){
  if (files.length === 0)
    return;

    // loop around to work on all files
  var mimeType = files[0].type;
  if (mimeType.match(/image\/*/) == null) {
    this.message = "Only images are supported.";
    return;
  }

  // reads the content of file, so that it can be used for preview
  // without saving it to angular application folder
  var reader = new FileReader();
  this.imagePath = files;
  // reading file contents for preview
  reader.readAsDataURL(files[0]);
  // when images is loaded call the callback function
  reader.onload = (_event) => {
    this.imgURL = reader.result;
  }
}

  ngOnInit() {
    this.userId = this.auth.getUserDetails();
    this.ser.getAllUsers().subscribe((responce:any)=>{this.userlist=responce;console.log(responce)});
  }

}
