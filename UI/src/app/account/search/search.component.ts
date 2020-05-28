import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from'@angular/forms';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { UserserviceService } from 'src/app/services/DataServices/userservice.service';
import { SearchedUser } from 'src/app/model/searcheduser.model';
import { SearchedUserList } from 'src/app/model/searcheduserlist.model';
import { Follow } from 'src/app/model/follow.model';
//import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
  export class SearchComponent implements OnInit {
  userSearchText : string;
  myFormGroup : FormGroup;
  searchedUsers : Array<SearchedUser>;
  result : string;
  follow : Follow;
  constructor(public auth : UserAuthService, public formBuilder : FormBuilder , public userService : UserserviceService ) {
    console.log("in form bilder of search");
    this.myFormGroup=formBuilder.group({
      "search":new FormControl("")
    });

  }
  search(){
    console.log("Search method");
    this.userSearchText = this.myFormGroup.controls['search'].value;
    this.result = "You have Searched for : "+this.userSearchText;
    this.userService.getSearchedUsers(this.userSearchText).subscribe(
      (response : SearchedUserList) => {
        this.searchedUsers = response.userList;
        this.searchedUsers = this.searchedUsers.map(user =>{
          user.profileUrl = "http://localhost:8765/user-service/"+user.profileUrl;
          return user;
        });
        console.log(response);
      });
  }

  message():void{
    
    alert("Logged out!!!");
    this.auth.logout();
  
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
  ngOnInit() {
  }

}
