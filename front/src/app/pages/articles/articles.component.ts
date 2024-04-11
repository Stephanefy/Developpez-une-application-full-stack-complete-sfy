import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable, startWith } from 'rxjs';
import { Article } from 'src/app/interfaces/article.interface';
import { User } from 'src/app/interfaces/user.interface';
import { ArticleApiService } from 'src/app/services/article-api.service';
import { selectAuth } from 'src/app/state/auth.selectors';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {

  public articles$!: Observable<Article[]>;
  public sortingOrder: "asc" | "desc" = 'desc';
  private currentUser!: User;
  

  constructor(
    private articleApiService: ArticleApiService,
    private store: Store 
  ) { }

  ngOnInit(): void {

    this.store.select(selectAuth).subscribe(value => 
      this.currentUser = value.user!
    );
    this.articles$ = this.articleApiService.allRelatedThemeArticles(this.currentUser.userId);

  

  //  this.articles$ = this.articleApiService.all();

  }

  toggleSortingOrder() {
    this.sortingOrder = this.sortingOrder === 'asc' ? 'desc' : 'asc';
  }

}
