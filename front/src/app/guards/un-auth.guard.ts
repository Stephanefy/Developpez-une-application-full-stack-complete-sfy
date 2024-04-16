import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Store } from '@ngrx/store';
import { map, Observable } from 'rxjs';
import { selectAuth } from '../state/auth.selectors';
import { AuthState } from '../interfaces/auth.interface';

@Injectable({
  providedIn: 'root'
})
export class UnAuthGuard implements CanActivate {


  constructor(
    private store: Store,
    private router: Router
  ) {}

    /**
   * Determines if unauthenticated route can be activated.
   *
   * @param {ActivatedRouteSnapshot} route - The route to activate.
   * @param {RouterStateSnapshot} state - The state of the router.
   * @return {Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree} - An observable, promise, or boolean indicating if the route can be activated.
   */
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      return this.store.select(selectAuth).pipe(
        map((authState: AuthState) => {
          if (!authState.isAuthenticated) {
            return true;
          } else {
            this.router.navigate(['/articles']);
            return false;
          }
        })
      );
  }
  
}
