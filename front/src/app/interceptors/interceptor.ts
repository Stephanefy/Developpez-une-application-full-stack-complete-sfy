import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { AuthState } from '../interfaces/auth.interface';
import { selectAuth } from '../state/auth.selectors';
import { catchError, Observable, switchMap, take, throwError } from 'rxjs';
import { AuthApiService } from '../services/auth-api.service';
import { StorageService } from '../services/local-storage.service';
import { User } from '../interfaces/user.interface';
import { loginSuccess } from '../state/auth.actions';

@Injectable({ providedIn: 'root' })
export class JwtInterceptor implements HttpInterceptor {
  private isRefreshing: boolean = false;

  public authStatus!: AuthState;

  constructor(
    private store: Store,
    private storageService: StorageService,
    private authApiService: AuthApiService
  ) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    
    const preferredLanguage = navigator.language || 'en'; // Fallback to 'en' if unavailable

    this.store.select(selectAuth).pipe(take(1)).subscribe(auth => this.authStatus = auth)
    // check if token exist in AuthState store so that it would not trigger another request to renew the token
    if (this.authStatus.token) {
        request = request.clone({
          setHeaders: {
            Authorization: `Bearer ${this.authStatus.token}`,
            'Accept-Language': preferredLanguage
          },
        });
        return next.handle(request);

    } else {
        return next.handle(request).pipe(
            catchError((error: any) => {
              console.log('reached');
              if (
                error instanceof HttpErrorResponse &&
                !request.url.includes('api/auth/connexion') &&
                error.status === 403
              ) {
                console.log('reached 2');
                return this.handle403Error(request, next, preferredLanguage);
              }
      
              return throwError(() => error);
            })
          );
    }
  }

  private handle403Error(
    request: HttpRequest<any>,
    next: HttpHandler,
    preferredLanguage: string
  ): Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      this.isRefreshing = true;

      if (this.storageService.isLoggedIn()) {
        // Instead of subscribing, use switchMap to chain the observable
        return this.storageService.getItem<User>('user').pipe(
          switchMap((user) => {
            return this.authApiService.renew(user.userId).pipe(
              switchMap((value) => {
                console.log('value', value);
                this.isRefreshing = false;
                this.store.dispatch(
                  loginSuccess({
                    token: value.token,
                    user: user,
                    isAuthenticated: true,
                  })
                );
                
                const updatedRequest = request.clone({
                    setHeaders: {
                        Authorization: `Bearer ${value.token}`, // Use the new token
                        'Accept-Language': preferredLanguage
                    },
                });
                console.log("request inside error handler",updatedRequest)

                return next.handle(updatedRequest);
              }),
              catchError((error) => {
                this.isRefreshing = false;
                // Handle error as needed
                return throwError(() => error);
              })
            );
          }),
          catchError((error) => {
            // Handle case where getItem fails
            this.isRefreshing = false;
            return throwError(() => error);
          })
        );
      }
    }

    // Proceed with the original request if not refreshing
    return next.handle(request);
  }
}
