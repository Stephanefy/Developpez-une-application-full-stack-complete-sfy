import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { Theme } from '../interfaces/theme.interface';

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  public subscriptionsSubject = new BehaviorSubject<Theme[]>([]);


  private baseUrl = environment.baseUrl;

  public path: string = 'api/users';

  constructor(private httpClient: HttpClient) { }


  public detail(id: string): Observable<User> {
    return this.httpClient.get<User>(`${this.baseUrl}${this.path}/${id}`);
  }

  public update(id: number, requestBody: {username: string, email: string}): Observable<User> {
    return this.httpClient.put<User>(`${this.baseUrl}${this.path}/${id}`, requestBody);
  }

  public getSubscriptions(id: number): Observable<Theme[]> {
    return this.httpClient.get<Theme[]>(`${this.baseUrl}${this.path}/subscriptions/${id}`).pipe(
      tap(subscriptions => this.subscriptionsSubject.next(subscriptions))
    );
  }

  public unsubscribe(id: number, userId: number): Observable<any> {
    console.log(`${this.baseUrl}${this.path}/${id}/unsubscribe/${userId}`)

    return this.httpClient.delete(`${this.baseUrl}${this.path}/${id}/unsubscribe/${userId}`).pipe(
      tap(() => {
        // Assuming successful unsubscription, filter out the unsubscribed item
        const currentSubscriptions = this.subscriptionsSubject.value;
        const updatedSubscriptions = currentSubscriptions.filter(sub => sub.id !== id);
        this.subscriptionsSubject.next(updatedSubscriptions); // Emit the updated array
      })
    );
  }

  public subscribe(id: number, userId: number): Observable<any> {

    return this.httpClient.post(`${this.baseUrl}${this.path}/${id}/subscribe/${userId}`, {}).pipe(
      tap(() => {
        // Assuming successful unsubscription, filter out the unsubscribed item
        const currentSubscriptions = this.subscriptionsSubject.value;
        const updatedSubscriptions = currentSubscriptions.filter(sub => sub.id !== id);
        this.subscriptionsSubject.next(updatedSubscriptions); // Emit the updated array
      })
    );
  }
}
