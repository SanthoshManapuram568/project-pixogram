import { Component, OnInit } from '@angular/core';
import { Media } from 'src/app/model/media.model';
import { ActivatedRoute } from '@angular/router';
import { MediaserviceService } from 'src/app/services/mediaservice/mediaservice.service';

@Component({
  selector: 'app-single-image',
  templateUrl: './single-image.component.html',
  styleUrls: ['./single-image.component.css']
})
export class SingleImageComponent implements OnInit {
  id : number; 
  file : Media; 
 
  constructor(public activatedRoute : ActivatedRoute ,  public mediaService : MediaserviceService) { 
  } 

  ngOnInit() {
    this.activatedRoute.params.subscribe((parameter) => this.id = parameter["mid"]);
    console.log(this.id);
    this.mediaService.getMedia(this.id).subscribe(data=>{
      data.url="http://localhost:8765/media-service/" + data.url;
      this.file=data;
      console.log(this.file);
    })
  }
}