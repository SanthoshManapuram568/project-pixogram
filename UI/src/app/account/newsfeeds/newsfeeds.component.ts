import { Component, OnInit } from '@angular/core';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { NewsFeedModel } from 'src/app/model/newsfeed.model';
import { UserserviceService } from 'src/app/services/DataServices/userservice.service';
//import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-newsfeeds',
  templateUrl: './newsfeeds.component.html',
  styleUrls: ['./newsfeeds.component.css']
})
export class NewsfeedsComponent implements OnInit {

  feed : NewsFeedModel[];
  userId : string;

  constructor(public auth : UserAuthService , public userService : UserserviceService) { }
  message():void{
    
    alert("Logged out!!!");
    this.auth.logout();
  }
  
  ngOnInit() {
   this.userId = this.auth.getId();
   this.userService.getAllFeed(this.userId).subscribe(data=>
    {
      this.feed = data;
    })
    console.log(this.feed);
  }

}
