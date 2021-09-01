import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MydocsComponent } from './components/mydocs/mydocs.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  {path:'home',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'mydocs',component:MydocsComponent},
  {path:'',redirectTo:"/home",pathMatch:"full"},
  {path:'**',redirectTo:"/home",pathMatch:"full"},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
