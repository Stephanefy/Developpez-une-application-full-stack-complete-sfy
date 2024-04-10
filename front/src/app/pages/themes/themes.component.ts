import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme } from 'src/app/interfaces/theme.interface';
import { ThemeApiService } from 'src/app/services/theme-api.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {

  public themes$: Observable<Theme[]> = this.themeApiService.all();
  private subscribeSuccessStatus = false;


  constructor(
    private themeApiService: ThemeApiService,
    private snackBar: MatSnackBar
  
  ) { }

  ngOnInit(): void {
  }

  setSubscribeStatusEvent(subscribeSuccessStatus: boolean): void {
    this.subscribeSuccessStatus = subscribeSuccessStatus;

    if (this.subscribeSuccessStatus) {
      this.snackBar.open('Abonnement enregisté', 'Fermer', {
        duration: 2000,
        panelClass: ['custom-snack-bar']
      });
    }
    
  }

}
