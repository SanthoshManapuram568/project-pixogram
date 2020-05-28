import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
//import { AuthenticationService } from '../authentication.service';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';
import { UserAuthService } from '../user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UploadMediaService {

  baseUrl: string = 'http://localhost:8765/user-service/register';
  constructor(private http: HttpClient, public auth : UserAuthService) { }
  pushFileToStorage(file: File,  userName,firstName,lastName,password,email, profilePic,) : Observable<HttpEvent<{}>>{
    const formdata: FormData = new FormData();
    formdata.append('file', file, profilePic);
    formdata.append('userId', this.auth.getId());
    formdata.append('profilePic', profilePic);
    formdata.append('userName',userName);
    formdata.append('firstName',firstName);
    formdata.append('lastName',lastName);
    formdata.append('password',password);
    formdata.append('email',email);
    const req = new HttpRequest('POST', `${this.baseUrl}`, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }

}
