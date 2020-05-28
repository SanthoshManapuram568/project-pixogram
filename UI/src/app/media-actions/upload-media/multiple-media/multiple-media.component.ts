import { Component, OnInit } from '@angular/core';
import{FormGroup, FormControl, FormBuilder, Validators} from'@angular/forms';

@Component({
  selector: 'app-multiple-media',
  templateUrl: './multiple-media.component.html',
  styleUrls: ['./multiple-media.component.css']
})
export class MultipleMediaComponent implements OnInit {
  title1:string;
  descpri1 : string;
  tag1: string;
  title2:string;
  descpri2 : string;
  tag2: string;
  myFormGroup:FormGroup;
  constructor(formBuilder: FormBuilder) {
    console.log("in form bilder of miltiple media");
    this.myFormGroup=formBuilder.group({
      "title1":new FormControl(""),
      "descp1":new FormControl(""),
      "tags1":new FormControl(""),
      "title2":new FormControl(""),
      "descp2":new FormControl(""),
      "tags2":new FormControl("")
    });

  }
  mediaDetails(){
    console.log("Media Details method");
    this.title1= this.myFormGroup.controls['title1'].value;
    this.descpri1=this.myFormGroup.controls['descp1'].value;
    this.tag1=this.myFormGroup.controls['tags1'].value;
    this.title2= this.myFormGroup.controls['title2'].value;
    this.descpri2=this.myFormGroup.controls['descp2'].value;
    this.tag2=this.myFormGroup.controls['tags2'].value;
    console.log("Title : "+this.title1+"\n"+"Description : "+this.descpri1+"\n"+"Tags : "+this.tag1);
    console.log("Title : "+this.title2+"\n"+"Description : "+this.descpri2+"\n"+"Tags : "+this.tag2);
  }
  message(){
    alert("you have uploaded the media & redirecting you to gallery")
  }
  ngOnInit() {
  }

}
