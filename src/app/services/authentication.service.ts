import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { map} from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { UserModel } from '../models/user-model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
 

  static USER_INFO = 'USER_INFO'; 
  static LOGGED_IN = "LOGGED_IN";

  private userInfoSubject:BehaviorSubject<UserModel>// other object can subscribe to and whenever value changes

  public currentUser$: Observable<UserModel>;


  private loggedIn = new  BehaviorSubject(this.cookieService.get(AuthenticationService.USER_INFO).length>0);
  
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
    const url = `${environment.ENDPOINTS.USER_LOGIN}`;
    this.setLoggedIn(true);
   return this.httpClient.post<UserModel>( url, {username,password})
   .pipe(
     map(userModel => {
      // create a cookie with JWT token
     this.cookieService.set(AuthenticationService.USER_INFO,JSON.stringify(userModel))

     // notify all the subscriber., use the next method
      this.userInfoSubject.next(userModel);
      
      
      console.log("inside login auth")
      return userModel;
     })
   )
   
    
  }



  logout(){
    // logout logic

    // delete the cookie
    this.cookieService.delete(AuthenticationService.USER_INFO);
    this.setLoggedIn(false);
    // notify the subscriber of the subject
    this.userInfoSubject.next({} as UserModel);


    // logged in as false
  

  }

  public get currentUserValue():any{

    return this.userInfoSubject.value;
  }

  public get currentLoginValue():any{

    return this.loggedIn.value;
  }

  registerUser(formValue:any):Observable<UserModel> {
    const url = `${environment.ENDPOINTS.USER_CREATION}`

    return this.httpClient.post<UserModel>( url, formValue)
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



}
