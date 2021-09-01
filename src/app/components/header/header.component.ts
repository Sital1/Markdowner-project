import { Component, OnInit, Output,EventEmitter} from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { SidenavComponent } from '../sidenav/sidenav.component';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  @Output() toggleSideBarForMe:EventEmitter<any>=new EventEmitter

  username : string = '';

  
  loggedIn = false;

  constructor(private authenticationService:AuthenticationService,
    private router:Router) { 

    this.authenticationService.currentUser$.subscribe(
      userModel => {
        if(Object.keys(userModel).length !== 0){
          this.loggedIn = true;
          this.username = userModel.username
        }
      }
    )

  }

  ngOnInit(): void {
    this.authenticationService.currentLogin$.subscribe(
      data => this.loggedIn = data
    )
  }



    toggleSideBar(){
      this.toggleSideBarForMe.emit();
    }
    
    logOutClick(event:any){
      event.preventDefault();
      this.authenticationService.logout();
      this.authenticationService.setLoggedIn(false);
      
      this.router.navigate(['/home']);
     
    }

}
