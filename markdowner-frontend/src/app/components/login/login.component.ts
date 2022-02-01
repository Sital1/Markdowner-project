import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  submitted = false;

  loading = false;

  get f(){
    return this.loginForm.controls;
  }

  constructor(private formBuilder:FormBuilder,
    private authenticationService:AuthenticationService,
    private router:Router
    ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group(
      {
        username:['',Validators.required],
        password:['',Validators.required]
      }
    );

    if(this.authenticationService.currentUserValue !== null && this.authenticationService.currentUserValue!= undefined
      && Object.keys(this.authenticationService.currentUserValue).length !== 0){
        this.router.navigate(['/home'])
      }
  }

  onSubmit(){
      this.submitted=true;

      if(this.loginForm.invalid){
        return;
      }

      this.loading=true;

      this.authenticationService.login(this.f.username.value,this.f.password.value)
      .subscribe(
          data => {
          
            this.router.navigate(['/home']);
          },
          error => {
            this.loading=false
          }
      )

  }

 
}
