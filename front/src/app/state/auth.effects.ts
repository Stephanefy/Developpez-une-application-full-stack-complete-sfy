import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { catchError, map, exhaustMap, tap, switchMap } from 'rxjs/operators';
import { AuthApiService } from '../services/auth-api.service';
import * as AuthActions from './auth.actions';
import { jwtDecode } from 'jwt-decode';
import { Router } from '@angular/router';
import {StorageService} from "../services/local-storage.service";
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthState } from '../interfaces/auth.interface';
import { User } from '../interfaces/user.interface';

@Injectable()
export class AuthEffects {

  login$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.login),
      exhaustMap(action =>
        this.authApiService.login(action.usernameOrEmail, action.password).pipe(
        map(({token}) => {
          this.localStorageService.setItem('user', jwtDecode(token));
          this.localStorageService.setItem('authStatus', true);
          return AuthActions.loginSuccess({ token: token, user: jwtDecode(token), isAuthenticated: true });
          }),
        tap(() => {
            this.snackBar.open('Connexion réussie!', 'Close', {
                duration: 3000,
              });
        }),
        //   map(user => AuthActions.loginSuccess({ user })),
          catchError(error => of(AuthActions.loginFailure({ error })))
        )
      )
    )
  );

  register$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AuthActions.register),
      exhaustMap(action =>
        this.authApiService.register(action.email, action.username, action.password).pipe(
          map(({token}) => {
            this.localStorageService.setItem('user', jwtDecode(token));
            this.localStorageService.setItem('authStatus', true);
            return AuthActions.loginSuccess({ token: token, user: jwtDecode(token), isAuthenticated: true });
            }),
            tap(() => {
                this.snackBar.open('Inscription réussie!', 'Fermer', {
                    duration: 3000,
                  });
            }),
        //   map(user => AuthActions.loginSuccess({ user })),
          catchError(error => {
            console.log(error)

            return of(AuthActions.loginFailure({ error })) 
          
          })
        )
      )
    ));

    renew$ = createEffect(() =>
      this.actions$.pipe(
        ofType(AuthActions.renew),
        switchMap(() =>
          this.localStorageService.getItem<User>('user').pipe(
            switchMap((value) => {
              if (!value || !value.userId) {
                // Handle the case where user or userId is not available
                return of(AuthActions.loginFailure({ error: 'User ID not found' }));
              }
              return this.authApiService.renew(value.userId).pipe(
                map(({ token }) => {
                  const user = jwtDecode(token); // Ensure jwtDecode is correctly typed to return User
                  this.localStorageService.setItem('user', user);
                  this.localStorageService.setItem('authStatus', true);
                  return AuthActions.loginSuccess({
                    token: token,
                    //@ts-ignore
                    user: user,
                    isAuthenticated: true,
                  });
                }),
                tap(() => {
                    this.router.navigate(['/connexion']);
                }),
                catchError((error) => of(AuthActions.loginFailure({ error })))
              );
            }),
            catchError((error) => of(AuthActions.loginFailure({ error })))
          )
        )
      )
    );
        

      
    ;

    logout$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(AuthActions.logout),
        tap(() => {
          this.localStorageService.removeItem('user');
          this.localStorageService.removeItem('authStatus');
          this.snackBar.open('Deconnexion!', 'Fermer', {
            duration: 3000,
          });
          this.router.navigate(['/connexion']);
        })
      ),
      {dispatch: false}
  );
  

  constructor(
    private actions$: Actions,
    private authApiService: AuthApiService,
    private router: Router,
    private localStorageService: StorageService,
    private snackBar: MatSnackBar
  ) {}
}
