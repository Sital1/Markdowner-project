import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { map} from 'rxjs/operators';
import { UserModel } from '../models/user-model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {



  static USER_INFO = 'USER_INFO'; 

  private userInfoSubject:BehaviorSubject<UserModel>// other object can subscribe to and whenever value changes

  public currentUser$: Observable<UserModel>;


  private loggedIn = new  BehaviorSubject(false);
  currentLogin$ = this.loggedIn.asObservable();

  setLoggedIn(data:boolean){
    this.loggedIn.next(data);
  }


  constructor(private httpClient:HttpClient,
    private cookieService:CookieService) {

      const jsonString = this.cookieService.get(AuthenticationService.USER_INFO);
      console.log("ss: " + jsonString);

      if(jsonString === ''){
        console.log("ss2323: " + jsonString);
        this.userInfoSubject = new BehaviorSubject<UserModel>({} as UserModel );
      }else{
        this.userInfoSubject = new BehaviorSubject<UserModel>(JSON.parse(this.cookieService.get(AuthenticationService.USER_INFO)));
      }
      
      this.currentUser$ = this.userInfoSubject.asObservable();

     }

  login(username:string,password:string):Observable<UserModel> {
    const url = 'http://demo5898413.mockable.io/login';

   return this.httpClient.post<UserModel>( url, {username,password})
   .pipe(
     map(userModel => {
      // create a cookie with JWT token
     this.cookieService.set(AuthenticationService.USER_INFO,JSON.stringify(userModel))
     // notify all the subscriber., use the next method
      this.userInfoSubject.next(userModel);
      
    
      return userModel;
     })
   )

  }



  logout(){
    // logout logic

    // delete the cookie
    this.cookieService.delete(AuthenticationService.USER_INFO);

    // notify the subscriber of the subject
    this.userInfoSubject.next({} as UserModel);


    // logged in as false
  

  }

  public get currentUserValue():any{

    return this.userInfoSubject.value;
  }


}
