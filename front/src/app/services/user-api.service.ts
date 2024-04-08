import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { Theme } from '../interfaces/theme.interface';

@Injectable({
  providedIn: 'root'
})
export class UserApiService {
  private baseUrl = environment.baseUrl;

  public path: string = 'api/users';

  constructor(private httpClient: HttpClient) { }


  public detail(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}${this.path}/${id}`);
  }

  public getSubscriptions(id: string): Observable<Theme[]> {
    return this.httpClient.get<Theme[]>(`${this.baseUrl}${this.path}/subscriptions/${id}`);
  }
}
