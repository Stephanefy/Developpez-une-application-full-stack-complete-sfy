import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { selectAuth } from 'src/app/state/auth.selectors';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss']
})
export class ConnexionComponent implements OnInit {

  public auth$ = this.store.select(selectAuth);

  constructor(
    private store: Store,
    private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
  }

  

}
