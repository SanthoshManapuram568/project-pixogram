import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { SingleMediaComponent } from './media-actions/upload-media/single-media/single-media.component';
import { GalleryComponent } from './media-actions/view/gallery/gallery.component';
import { RegisterComponent } from './authentication/register/register.component';
import { MultipleMediaComponent } from './media-actions/upload-media/multiple-media/multiple-media.component';
import { SingleImageComponent } from './media-actions/view/single-image/single-image.component';
import { FollowersComponent } from './profile/followers/followers.component';
import { NewsfeedsComponent } from './account/newsfeeds/newsfeeds.component';
import { BlockedAccountsComponent } from './account/blocked-accounts/blocked-accounts.component';
import { AccountUpdateComponent } from './account/account-update/account-update.component';
import { SearchComponent } from './account/search/search.component';
import { AuthGuardService } from './services/auth-guard.service';
import { ErrorComponent } from './error/error.component';



const routes: Routes = [
  {path:"upload",component:SingleMediaComponent,canActivate : [AuthGuardService]},
  {path:"mymedia",component:GalleryComponent,canActivate : [AuthGuardService]},
  {path:"follow",component:FollowersComponent,canActivate : [AuthGuardService]},
  {path:"register",component:RegisterComponent},
  {path:"media",component:GalleryComponent,canActivate : [AuthGuardService]},
  {path:"login",component:LoginComponent},
  {path:"loginpage",component:LoginComponent},
  {path:"singlemedia",component:SingleMediaComponent,canActivate : [AuthGuardService]},
  {path:"multiplemedia",component:MultipleMediaComponent,canActivate : [AuthGuardService]},
  {path:"singleimage/:mid",component:SingleImageComponent,canActivate : [AuthGuardService]},
  {path:"news-feeds",component:NewsfeedsComponent,canActivate : [AuthGuardService]},
  {path:"blocked-accounts",component:BlockedAccountsComponent,canActivate : [AuthGuardService]},
  {path:"account-update",component:AccountUpdateComponent,canActivate : [AuthGuardService]},
  {path:"search",component:SearchComponent,canActivate : [AuthGuardService]},
  {path:"logout",component:LoginComponent,canActivate : [AuthGuardService]},
  {path:"",redirectTo:"login",pathMatch:"full"},
  {path:"**", component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
