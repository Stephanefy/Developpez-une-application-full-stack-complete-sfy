import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentApiService {

  private baseUrl = environment.baseUrl;

  private path: string = 'api/comments';

  constructor(private httpClient: HttpClient) { }



  public create(comment: {
    content: string,
    authorId: number
  }, articleId: string): Observable<any> {
    return this.httpClient.post<any>(`${this.baseUrl}${this.path}/article/${articleId}`, comment);
  }


}
