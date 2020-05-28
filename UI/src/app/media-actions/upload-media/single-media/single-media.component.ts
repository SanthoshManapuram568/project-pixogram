import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from'@angular/forms';
import { MediaserviceService } from 'src/app/services/mediaservice/mediaservice.service';
import { Router } from '@angular/router';
import { Media } from 'src/app/model/media.model';
@Component({
  selector: 'app-single-media',
  templateUrl: './single-media.component.html',
  styleUrls: ['./single-media.component.css']
})
export class SingleMediaComponent implements OnInit {
  
  title1 : string;
  descpri1 : string;
  tag1 : string;
  file : any
  myFormGroup : FormGroup;
  selectedFiles: FileList;
  selectedFile : File;
  date : Date;
  progress: { percentage: number } = { percentage: 0 };
  public imagePath;
  imgURL: any;
  public message1: string;

  constructor(public formBuilder: FormBuilder, public mediaService : MediaserviceService,public router : Router ) {
    console.log("in form bilder of single media");
    this.myFormGroup=formBuilder.group({
      "title":new FormControl("",Validators.required),
      "description":new FormControl(""),
      "tags":new FormControl("")
    });

  }
  
onImageLoad(event){
  this.selectedFiles = event.target.files;
  this.selectedFile = this.selectedFiles.item(0);
  this.preview(this.selectedFiles);
}

mediaDetails(){
    console.log("Single Media Details method");
    this.title1= this.myFormGroup.controls['title'].value;
    this.descpri1=this.myFormGroup.controls['description'].value;
    this.tag1=this.myFormGroup.controls['tags'].value;
    this.date=new Date();
    let dateString = `_${this.date.getTime()}_${this.date.getDate()}_${this.date.getFullYear()}`
    if (this.selectedFile.type == 'image/png') {
      this.file = `${this.title1}${dateString}.png`;
    }
    if (this.selectedFile.type == 'image/jpeg' || this.selectedFile.type == 'image/jpg') {
      this.file = `${this.title1}${dateString}.jpeg`;
    }
  
    console.log(this.file+"\n"+this.title1+"\n"+this.descpri1+"\n"+this.tag1);
    let uploadfile = new Media(this.file,this.title1,this.descpri1,this.tag1)

    this.mediaService.pushFileToStorage(this.selectedFile,this.title1,this.descpri1,this.tag1,this.file,this.selectedFile.type).subscribe(
      (responce)=>{this.router.navigate(['/mymedia/'])});
  }

  preview(files){
    if (files.length === 0)
      return;

      // loop around to work on all files
    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.message1 = "Only images are supported.";
      return;
    }

    // reads the content of file, so that it can be used for preview
    // without saving it to angular application folder
    var reader = new FileReader();
    this.imagePath = files;
    // reading file contents for preview
    reader.readAsDataURL(files[0]);
    // when images is loaded call the callback function
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    }
  }


message(){
  alert("you have uploaded the media & redirecting you to gallery")
}
  ngOnInit() {
  }

}
