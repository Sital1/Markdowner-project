import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { UserModel } from "../models/user-model";
import { AuthenticationService } from "../services/authentication.service";



@Injectable()
export class ErrorInterceptor implements HttpInterceptor{
    
    constructor(private authenticationService: AuthenticationService,
        private router: Router){

    }
    
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

           return next.handle(req)
                .pipe(
                    catchError(err => {
                        if(err.status === 401 || err.status === 403){
                            this.authenticationService.logout();
                            this.router.navigate(['/login'])
                        }
                        return throwError(err);
                    })
                )

    }

}