import { Component, OnInit } from '@angular/core';
/*import { AuthenticationService } from 'src/app/services/authentication.service';*/
import { UserAuthService } from 'src/app/services/user-auth.service';

@Component({
  selector: 'app-header-part-two',
  templateUrl: './header-part-two.component.html',
  styleUrls: ['./header-part-two.component.css']
})
export class HeaderPartTwoComponent implements OnInit {
  username : string;
  profileImg : string;

  constructor(public auth : UserAuthService , public userName : UserAuthService) {
    this.profileImg="http://localhost:8765/user-service/"+this.userName.getPic();
    this.username=this.auth.getUserDetails();
  }

  ngOnInit() {
  }

}
