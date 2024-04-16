import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginRequest } from './interfaces/LoginRequest';
import { RegisterRequest } from './interfaces/RegisterRequest';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { selectAuth, selectAuthError } from 'src/app/state/auth.selectors';
import { register, login, clearErrors } from 'src/app/state/auth.actions';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.scss'],
})
export class AuthFormComponent implements OnInit {
  public isLogin: boolean = false;
  public auth$ = this.store.select(selectAuth);
  public authForm = this.fb.group({
    usernameOrEmail: ['', [Validators.required]],
    username: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]],
  });
  public error$ = this.store.select(selectAuthError)
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private store: Store
  ) {}

  ngOnInit(): void {
    this.isLogin =
      this.route.snapshot.routeConfig?.path === 'connexion' && true;

  }

  submit(): void {
    if (this.isLogin) {
      const loginRequest = this.authForm.value as LoginRequest;

      

      const request = {
        usernameOrEmail: loginRequest.usernameOrEmail,
        password: loginRequest.password,
      };
      this.store.dispatch(login(request));
     
    } else {
      const registerRequest = this.authForm.value as RegisterRequest;

      
      const request = {
        email: registerRequest.email,
        username: registerRequest.username,
        password: registerRequest.password,
      };
      this.store.dispatch(register(request));
    }
  }

  clearError(): void {
    
    this.store.dispatch(clearErrors())
  }
}
