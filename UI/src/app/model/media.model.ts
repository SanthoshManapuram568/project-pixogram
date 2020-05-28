export class Media {
    
    id : number ;
    url : string;
    title : string;
    description : string;
    tags : string;
    comments : number;
    likes : number;
    disLikes : number;
    unlikes :number;

     constructor(url,title,description,tags){
         
         this.url = url ;
         this.title = title;
         this.description = description;
         this.tags = tags;
      }
} 