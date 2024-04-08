import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from 'src/app/interfaces/article.interface';
import { ArticleApiService } from 'src/app/services/article-api.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  public articles$: Observable<Article[]> = this.articleApiService.all();

  constructor(private articleApiService: ArticleApiService ) { }

  ngOnInit(): void {

  //  this.articles$ = this.articleApiService.all();

  }

}
