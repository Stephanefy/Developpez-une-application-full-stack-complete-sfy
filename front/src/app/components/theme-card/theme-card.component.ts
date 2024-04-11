import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Theme } from 'src/app/interfaces/theme.interface';
import { User } from 'src/app/interfaces/user.interface';
import { ThemeApiService } from 'src/app/services/theme-api.service';
import { UserApiService } from 'src/app/services/user-api.service';
import { selectAuth } from 'src/app/state/auth.selectors';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss']
})
export class ThemeCardComponent implements OnInit {

  @Input() theme!: Theme;
  @Output() subscribeSuccessEmitter = new EventEmitter<true>();

  public currentUser!: User;

  constructor(
    private themeApiService: ThemeApiService,
    private userApiService: UserApiService,
    private store: Store
  
  ) { }

  ngOnInit(): void {
    this.store.select(selectAuth).subscribe(value => 
      this.currentUser = value.user!
    );
  }

  subscribe(themeId: number): void {
    //TODO once authentication is implemented changed 1 to user.id
    this.userApiService.subscribe(themeId, 1).subscribe(
      {
        next: (response) => {
          console.log(response)
          this.userApiService.getSubscriptions(this.currentUser.userId)
          this.subscribeSuccessEmitter.emit(true);
        },
        error: (error) => {
          console.error('Subscribe failed', error);
        }
      }
    );
  }

}
