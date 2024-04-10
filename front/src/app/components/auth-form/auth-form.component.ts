import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginRequest } from './interfaces/LoginRequest';
import { RegisterRequest } from './interfaces/registerRequest';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Store } from '@ngrx/store';
import { selectAuth } from 'src/app/state/auth.selectors';
import { register, login } from 'src/app/state/auth.actions';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.scss']
})
export class AuthFormComponent implements OnInit {

  public isLogin: boolean = false;
  public auth$ = this.store.select(selectAuth);
  public authForm!: FormGroup;



  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private store: Store,
  ) { }

  ngOnInit(): void {

    this.authForm = this.fb.group({
      usernameOrEmail: [''],
      username: [''],
      email: [''],
      password: [''],
    });

    this.isLogin = this.route.snapshot.routeConfig?.path === 'connexion' && true
  
  }


  submit(): void {


    if (this.isLogin) {
      const loginRequest = this.authForm.value as LoginRequest;

      console.log("loginRequest", loginRequest)

      const request = {
        usernameOrEmail: loginRequest.usernameOrEmail,
        password: loginRequest.password
      }
      this.store.dispatch(login(request))
    } else {
      const registerRequest = this.authForm.value as RegisterRequest;

      console.log(registerRequest)
      const request = {
        email: registerRequest.email,
        username: registerRequest.username,
        password: registerRequest.password
      }
      this.store.dispatch(register(request))
    }
  }
}
