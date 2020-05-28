import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserRegistrationDetails } from 'src/app/model/user-registration';
import { Observable } from 'rxjs';
import { UserName } from 'src/app/model/usernames.model';
import { Follow } from 'src/app/model/follow.model';
import { NewsFeedModel } from 'src/app/model/newsfeed.model';
const API_URL = "http://localhost:8765/user-service/users";
const REG_URL = "http://localhost:8765/user-service/register";
const NAMES_URL = "http://localhost:8765/user-service/usernames"
const SEARCH_URL = "http://localhost:8765/misc-plumbing";
const FOLLOW_URL = "http://localhost:8765/follow-service"
const NEWS_FEED = "http://localhost:4562/newsfeeds/"

@Injectable({
  providedIn: 'root'
})


export class UserserviceService {

  constructor(public http : HttpClient) {

   }
   
   doFollow(follow : Follow):any{
    return this.http.post(FOLLOW_URL+"/follow",follow);
   }
   doUnFollow(other : number):any{
     console.log(other);
     return this.http.delete(FOLLOW_URL+"/unfollow/mine/"+sessionStorage.getItem("userid")+"/other/"+other);
   }
   getSearchedUsers(searchText : string)
   {
    return this.http.get(SEARCH_URL + "/searched-users/" + searchText + "/myid/" + sessionStorage.getItem("userid"));
   }

   getUserNames():Observable<UserName[]>
   {
     return this.http.get<UserName[]>(NAMES_URL);
   }
   getAllUsers():any
   {
    return this.http.get(API_URL);
   }

   getUser(id:number):any
   {
    return this.http.get(API_URL+"/"+id);
   }

   addUser(user:UserRegistrationDetails):any
   {
    return this.http.post(REG_URL,user);
   }

   delete(id:number)
   {
    this.http.delete(API_URL+"/"+id);
   }

   update(id:number,user:UserRegistrationDetails):any
   {
    return this.http.put(API_URL+"/"+id,user);
   }

   getAllFeed(userId : string) : Observable<NewsFeedModel[]>
  {
  return this.http.get<NewsFeedModel[]>(NEWS_FEED+userId);
  }
}
