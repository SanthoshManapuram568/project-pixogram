import { Component, OnInit } from '@angular/core';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { FormGroup } from '@angular/forms';
import { SearchedUser } from 'src/app/model/searcheduser.model';
import { Follow } from 'src/app/model/follow.model';
import { UserserviceService } from 'src/app/services/DataServices/userservice.service';

@Component({
  selector: 'app-searched-user-profile',
  templateUrl: './searched-user-profile.component.html',
  styleUrls: ['./searched-user-profile.component.css']
})
export class SearchedUserProfileComponent implements OnInit {
  username : string;
  fullName  : string;
  profileImg : string;
  email : string;
  searchedUsers : Array<SearchedUser>;
  result : string;
  follow : Follow;

  constructor(public auth : UserAuthService , public userService : UserserviceService) { 
    this.profileImg="http://localhost:8765/user-service/"+this.auth.getPic();
  }
  doFollow(otherId : HTMLInputElement){
    this.follow = new Follow(Number(otherId.value),Number(sessionStorage.getItem("userid")));
    console.log(this.follow);
    this.userService.doFollow(this.follow).subscribe((response)=>{
     
    })

  }
  doUnFollow(otherId : HTMLInputElement){
    console.log(Number(otherId.value));
    this.userService.doUnFollow(Number(otherId.value)).subscribe((response)=>{
      
      //window.location.reload();
    });
  }
  ngOnInit(): void {
    this.username = this.auth.getUserDetails();
    //this.fullName = this.auth.getUserName();
    //this.email = this.auth.getEmail();
  }

}
