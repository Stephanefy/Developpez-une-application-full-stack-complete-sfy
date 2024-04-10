import { Component, OnInit } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Theme } from 'src/app/interfaces/theme.interface';
import { User } from 'src/app/interfaces/user.interface';
import { ThemeApiService } from 'src/app/services/theme-api.service';
import { UserApiService } from 'src/app/services/user-api.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Store } from '@ngrx/store';
import { logout } from 'src/app/state/auth.actions';
import { selectAuth } from 'src/app/state/auth.selectors';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {


  // public user$ = new Observable();
  public user$: Observable<User> = this.userApiService.detail("1");
  public auth$ = this.store.select(selectAuth);
  public subscriptions$!: Observable<Theme[]>;
  //TODO FIX THIS TYPE ISSUE
  //@ts-ignore
  public currentUser: User;


  constructor(
    private userApiService: UserApiService,
    private matSnackBar: MatSnackBar,
    private store: Store,
  
  ) { }

  ngOnInit(): void {
    this.store.select(selectAuth).subscribe(value => 
     
      this.currentUser = value.user!
    );
    //@ts-ignore
    this.subscriptions$ = this.userApiService.getSubscriptions(this.currentUser.userId.toString());

    this.subscriptions$.pipe(
      tap(console.log)
    ).subscribe();
  }

  unsubscribe(themeId: number): void {
    this.userApiService.unsubscribe(themeId, 1).subscribe({
      next: (response) => {
        //@ts-ignore
        this.subscriptions$ = this.userApiService.getSubscriptions(this.currentUser.userId.toString());
        this.matSnackBar.open('Désabonnement enregistré', 'Fermer', {
          duration: 2000,
          panelClass: ['custom-snack-bar']
        });
      },
      error: (error) => {
        console.error('Unsubscribe failed', error);
      }
    })
  }

  logout(): void {
    this.store.dispatch(logout());
  }
}
