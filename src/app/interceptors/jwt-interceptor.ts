import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserModel } from "../models/user-model";
import { AuthenticationService } from "../services/authentication.service";



@Injectable()
export class JwtInterceptor implements HttpInterceptor{
    
    constructor(private authenticationService: AuthenticationService){

    }
    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const currentUser: UserModel = this.authenticationService.currentUserValue;
        

        if(currentUser && currentUser.jwtToken){
            req = req.clone(
                {
                    setHeaders:{
                        Authorization:`Bearer ${currentUser.jwtToken}`
                    }
                }
            )
        }


        return next.handle(req);

    }

}