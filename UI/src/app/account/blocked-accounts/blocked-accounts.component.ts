import { Component, OnInit } from '@angular/core';
import { UserAuthService } from 'src/app/services/user-auth.service';
//import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-blocked-accounts',
  templateUrl: './blocked-accounts.component.html',
  styleUrls: ['./blocked-accounts.component.css']
})
export class BlockedAccountsComponent implements OnInit {
 
  state :string = 'dark';
  color:string='#fff'
  constructor(public auth : UserAuthService) { 
   
   
  }
  message():void{
    
    alert("Logged out!!!");
    this.auth.logout();
  }
  
  ngOnInit() {


    

  }

}
