import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-followers',
  templateUrl: './followers.component.html',
  styleUrls: ['./followers.component.css']
})
export class FollowersComponent implements OnInit {

  constructor() { }
follow():void{
alert("You are following mr.xyz")
}
unfollow():void{
  alert("You have stopped following mr.xyz")
  }
  
ngOnInit() {
  }

}
