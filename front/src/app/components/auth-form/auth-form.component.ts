import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginRequest } from './interfaces/LoginRequest';
import { RegisterRequest } from './interfaces/RegisterRequest';
import { FormBuilder, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { selectAuth, selectAuthError } from 'src/app/state/auth.selectors';
import { register, login, clearErrors } from 'src/app/state/auth.actions';

@Component({
  selector: 'app-auth-form',
  templateUrl: './auth-form.component.html',
  styleUrls: ['./auth-form.component.scss'],
})
export class AuthFormComponent implements OnInit {
  public isLoginForm: boolean = false;
  public auth$ = this.store.select(selectAuth);
  public authForm = this.fb.group({
    usernameOrEmail: [
      '',
      [this.isLoginForm ? Validators.required : Validators.nullValidator],
    ],
    username: [
      '',
      [this.isLoginForm ? Validators.required : Validators.nullValidator],
    ],
    email: [
      '',
      [this.isLoginForm ? Validators.required : Validators.nullValidator],
    ],
    password: ['', [Validators.required, Validators.minLength(8)]],
  });
  public error$ = this.store.select(selectAuthError);
  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private store: Store
  ) {}

  ngOnInit(): void {
    clearErrors();
    this.isLoginForm =
      this.route.snapshot.routeConfig?.path === 'connexion' && true;
  }

  submit(): void {
    if (this.isLoginForm) {
      const loginRequest = this.authForm.value as LoginRequest;

      const request = {
        usernameOrEmail: loginRequest.usernameOrEmail as string,
        password: loginRequest.password,
      };

      this.authForm.status !== 'INVALID' && this.store.dispatch(login(request));
    } else if (!this.isLoginForm) {
      const registerRequest = this.authForm.value as RegisterRequest;

      const request = {
        email: registerRequest.email,
        username: registerRequest.username,
        password: registerRequest.password,
      };

      this.authForm.status !== 'INVALID' &&
        this.store.dispatch(register(request));
    }
  }

  clearErrors(): void {
    this.store.dispatch(clearErrors());
  }
}
