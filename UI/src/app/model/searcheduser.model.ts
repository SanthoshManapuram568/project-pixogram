export class SearchedUser {
    userId : number ;
    name : string;
    profileUrl : string;
    followed : boolean;
    constructor(userId,name,profileUrl,followed){
         
        this.userId = userId;
        this.name =  name;
        this.profileUrl = profileUrl;
        this.followed = followed;

     }
}