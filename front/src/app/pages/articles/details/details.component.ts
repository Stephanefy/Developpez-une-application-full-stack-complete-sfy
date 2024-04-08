import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommentFormComponent } from 'src/app/components/comments/comment-form/comment-form.component';
import { Article } from 'src/app/interfaces/article.interface';
import { ArticleApiService } from 'src/app/services/article-api.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
 
})
export class DetailsComponent implements OnInit {

  public article!: Article;

  constructor(private articleApiService: ArticleApiService, private route: ActivatedRoute) { }

  ngOnInit(): void {

    let id = this.route.snapshot.params['id'];
    this.articleApiService.detail(id).subscribe((article) => {
      this.article = article;
    });

  }

}
