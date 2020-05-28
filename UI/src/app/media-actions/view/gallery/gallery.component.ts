import { Component, OnInit } from '@angular/core';
import { Media } from 'src/app/model/media.model';
import { MediaserviceService } from 'src/app/services/mediaservice/mediaservice.service';
import { Router } from '@angular/router';
import { UserserviceService } from 'src/app/services/DataServices/userservice.service';
import { MediaListModel } from 'src/app/model/medialist.model';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
}) 
export class GalleryComponent implements OnInit {
  medialist : Array<Media>;
  constructor(public mediaService : MediaserviceService , public router  : Router , public userService : UserserviceService) { }

  getDetails(id : number){
    console.log(id);
    this.router.navigate(['/singleimage/'+id])
   }

  ngOnInit() { 
    this.mediaService.getAllMedia().subscribe((responce : MediaListModel)=>{
      this.medialist=responce.filelist.map(media => {
        media.url = "http://localhost:8765/media-service/" + media.url;
        return media;
      
      });
      console.log(this.medialist);
    });
    
  }

}
