import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { toggleSidebar } from '../../.history/src/app/state/ui.action_20240411142434';
import { selectUI } from './state/ui.selectors';
import { map } from 'rxjs';
import { selectAuth } from './state/auth.selectors';
import { AuthApiService } from './services/auth-api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'front';

  public showSidebar!: boolean;

  constructor(
    private store: Store,
    private authApiService: AuthApiService
  ) {}

  
  ngOnInit(): void {
    console.log(this.store.select(selectUI))
    this.store.select(selectUI).subscribe(uiState => {
      this.showSidebar = uiState.showSidebar;
      // this.showSidebar = uiState.showSidebar;
      // console.log(uiState.showSidebar)
    });

  }


  hideSidebar() {
    this.store.dispatch(toggleSidebar({ showSidebar: false }));
  }
}
