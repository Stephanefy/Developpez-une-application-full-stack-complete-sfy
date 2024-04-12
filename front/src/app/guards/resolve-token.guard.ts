import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable, EMPTY } from 'rxjs';
import { catchError, map, take } from 'rxjs/operators';
import { Store } from '@ngrx/store';
import { renew } from '../state/auth.actions';
import { selectAuth } from '../state/auth.selectors';

@Injectable({
  providedIn: 'root',
})
export class TokenResolver implements Resolve<boolean> {
  constructor(private store: Store, private router: Router) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> {

    this.store.dispatch(renew());

    return this.store.select(selectAuth).pipe(
      map(authState => {
        if (!authState.isAuthenticated) {
          this.router.navigate(['/login']); // Redirection if not authenticated
          return false;
        }
        return true;
      }),
      take(1), // Ensure the observable completes
      catchError(() => {
        this.router.navigate(['/error']); // Handle errors or implement specific logic
        return EMPTY;
      })
    );
  }
}
