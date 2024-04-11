import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { filter } from 'rxjs';
import { selectAuth } from 'src/app/state/auth.selectors';
import { toggleSidebar } from 'src/app/state/ui.action';
import { selectUI } from 'src/app/state/ui.selectors';

@Component({
  selector: 'app-mobile-sidebar',
  templateUrl: './mobile-sidebar.component.html',
  styleUrls: ['./mobile-sidebar.component.scss']
})
export class MobileSidebarComponent implements OnInit {

  public auth$ = this.store.select(selectAuth);
  public showSidebar!: boolean;
  public isUserProfilActive: boolean = false;



  constructor(
    private store: Store,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.isUserProfilActive = event.url.includes('user') ? true : false;
    });

    this.store.select(selectUI).subscribe(uiState => {
      this.showSidebar = uiState.showSidebar;
    });
  }

  showSideBar() {
    this.store.dispatch(toggleSidebar({ showSidebar: true }));
  }

}
