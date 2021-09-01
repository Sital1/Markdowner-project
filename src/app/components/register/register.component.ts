import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  loading = false;
  submitted = false;

  
  constructor(private router:Router,
    private authenticationService:AuthenticationService,
    private formBuilder:FormBuilder) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username:['',Validators.required],
      displayName:['',Validators.required],
      email:['',Validators.required],
      password:['',Validators.required],
    });
  }


  get f(){
    return this.registerForm.controls;
  }

  onSubmit(){
    this.submitted=true;

    if(this.registerForm.invalid){
      return;
    }
    this.loading = true;
    this.authenticationService.registerUser(this.registerForm.value)
    .subscribe(
      data => {
          this.router.navigate(['/home'])
      },
      error => {
        this.loading = false;
        alert(`Registration failed ${error.message}`)
      }
    )
      this.authenticationService.setLoggedIn(true);
  }

}
