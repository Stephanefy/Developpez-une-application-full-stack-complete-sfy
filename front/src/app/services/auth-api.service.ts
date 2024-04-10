import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {
  private baseUrl = environment.baseUrl;

  public path: string = 'api/auth/';

  constructor(private httpClient: HttpClient, private router: Router) {}

  public login(usernameOrEmail: string, password: string): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}${this.path}login`, { usernameOrEmail, password }).pipe(
      tap(() => this.router.navigate(['/articles'])),
    );
  }

  public register(email: string, username: string ,password: string): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}${this.path}register`, { email, username, password }).pipe(
      tap(() => this.router.navigate(['/articles'])),
    );
  }

}
