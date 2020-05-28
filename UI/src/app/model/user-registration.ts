export class UserRegistrationDetails{
        id : number;
        firstName : string;
        lastName : string;
        userName : string;
        email : string;
        password : string;
        profilePic : any;
    
        constructor(firstName,lastName,userName,email,password,profilePic){
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName =  userName;
            this.email = email;
            this.password = password;
            this.profilePic = profilePic;
        }
    }