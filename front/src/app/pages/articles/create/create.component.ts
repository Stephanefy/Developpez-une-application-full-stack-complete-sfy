import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.scss']
})
export class CreateComponent implements OnInit {

  constructor(private snackBar: MatSnackBar) { }

  ngOnInit(): void {
  }


  articleCreatedEvent(event: boolean) {
    if (event) {
      this.snackBar.open('Article créé', 'Fermer', {
        duration: 3000
      });
    }
  }
}
