import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article, CreateArticle } from '../interfaces/article.interface';
import { environment } from '../environments/environment';

  

@Injectable({
  providedIn: 'root'
})
export class ArticleApiService {

  private baseUrl = environment.baseUrl;

  public path: string = 'api/article';

  constructor(private httpClient: HttpClient) {}

  public all(): Observable<Article[]> {
    return this.httpClient.get<Article[]>(this.baseUrl + this.path);
  }

  public detail(id: string): Observable<Article> {
    return this.httpClient.get<Article>(`${this.baseUrl}${this.path}/${id}`);
  }

  public create(article: CreateArticle): Observable<CreateArticle> {
    return this.httpClient.post<CreateArticle>(`${this.baseUrl}${this.path}`, article);
  }
}
