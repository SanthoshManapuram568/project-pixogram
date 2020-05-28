import { Component, OnInit } from '@angular/core';
import { UserAuthService } from 'src/app/services/user-auth.service';
//import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-header-part-one',
  templateUrl: './header-part-one.component.html',
  styleUrls: ['./header-part-one.component.css']
})
export class HeaderPartOneComponent implements OnInit {

  constructor(public auth: UserAuthService) { }

  ngOnInit() {
  }

}
