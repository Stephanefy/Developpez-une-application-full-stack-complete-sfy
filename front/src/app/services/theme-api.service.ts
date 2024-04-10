import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Theme } from '../interfaces/theme.interface';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ThemeApiService {

  private baseUrl = environment.baseUrl;

  private path: string = 'api/themes';

  constructor(private httpClient: HttpClient) { 
  }

  public all(): Observable<Theme[]> {

    return this.httpClient.get<Theme[]>(this.baseUrl + this.path);
  }





}
