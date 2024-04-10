import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { selectAuth } from '../state/auth.selectors';
import { AuthState } from '../interfaces/auth.interface';

@Injectable({
  providedIn: 'root'
})
export class CanActivateGuard implements CanActivate {

  constructor(
    private router: Router,
    private store: Store
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

   
    return this.store.select(selectAuth).pipe(
      map((authState: AuthState) => {
        if (authState.isAuthenticated) {
          return true;
        } else {
          this.router.navigate(['/connexion']);
          return false;
        }
      })
    );
  }
  
}
