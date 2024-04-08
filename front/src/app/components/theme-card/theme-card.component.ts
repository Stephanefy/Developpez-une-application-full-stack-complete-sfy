import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme } from 'src/app/interfaces/theme.interface';
import { ThemeApiService } from 'src/app/services/theme-api.service';

@Component({
  selector: 'app-theme-card',
  templateUrl: './theme-card.component.html',
  styleUrls: ['./theme-card.component.scss']
})
export class ThemeCardComponent implements OnInit {

  @Input() theme!: Theme;

  constructor(private themeApiService: ThemeApiService) { }

  ngOnInit(): void {

  }

}
