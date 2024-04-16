import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { Store } from '@ngrx/store';
import { selectAuth } from 'src/app/state/auth.selectors';
import { toggleSidebar } from 'src/app/state/ui.action';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {


  public auth$ = this.store.select(selectAuth);
  public isUserProfilActive: boolean = false;
  public isAuthRoutes!: boolean;


  constructor(
    private router: Router, 
    private store: Store,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {


    this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.isAuthRoutes = event.url.includes('connexion' || 'inscription' ) ? true : false;
    });


    this.router.events.pipe(
      filter((event): event is NavigationEnd => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.isUserProfilActive = event.url.includes('user') ? true : false;
    });


  }

  showSideBar() {
    console.log("reached")
    this.store.dispatch(toggleSidebar({ showSidebar: true }));
  }

}
