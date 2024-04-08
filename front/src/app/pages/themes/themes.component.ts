import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme } from 'src/app/interfaces/theme.interface';
import { ThemeApiService } from 'src/app/services/theme-api.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {

  public themes$: Observable<Theme[]> = this.themeApiService.all();


  constructor(private themeApiService: ThemeApiService) { }

  ngOnInit(): void {
  }

}
