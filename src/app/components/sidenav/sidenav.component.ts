import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/models/user-model';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {



  loggedIn = false;

  constructor(private authenticationService:AuthenticationService,
    private router:Router) { 
    this.authenticationService.currentUser$.subscribe(
      userModel => {
        if(Object.keys(userModel).length !== 0){
          this.loggedIn = true;
        }
      }
    )
    console.log(this.loggedIn);
  }

  ngOnInit(): void {
    this.authenticationService.currentLogin$.subscribe(
      data => this.loggedIn = data
    )
  }

  logOutClick(event:any){
    event.preventDefault();
    
    this.authenticationService.logout();
    this.authenticationService.setLoggedIn(false);
   
    this.router.navigate(['/home']);
    
  

  }

}
