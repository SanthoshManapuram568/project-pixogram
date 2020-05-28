import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Media } from 'src/app/model/media.model';
@Injectable({
  providedIn: 'root'
})
export class MediaserviceService {
  baseUrl : string = 'http://localhost:8765/media-plumbing/media';
  baseUrlForSingleImage = 'http://localhost:8765/media-plumbing/mediadetails/'
  constructor(public http : HttpClient ) { }
  getAllMedia() : any{
    return this.http.get(this.baseUrl+"/"+sessionStorage.getItem("userid"));
  }

  getMedia(id : number) : Observable<Media>{
   
    return this.http.get<Media>(this.baseUrlForSingleImage+id);
  }
  
  pushFileToStorage(file : File,title,description,tags,url,type) : Observable<HttpEvent<{}>>{
    
    const formdata: FormData = new FormData();


    formdata.append('file', file, url);
    formdata.append('title',title)
    formdata.append('description',description)
    formdata.append('type',type)
    console.log("userid in media : "+sessionStorage.getItem("userid"))
    formdata.append('userid',sessionStorage.getItem("userid"))
    formdata.append("tags",tags)
    formdata.append('url',url)
    


    const req = new HttpRequest('POST', `${this.baseUrl}`, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }
}

