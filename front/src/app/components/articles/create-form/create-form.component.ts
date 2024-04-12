import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Article, CreateArticle } from 'src/app/interfaces/article.interface';
import { Theme } from 'src/app/interfaces/theme.interface';
import { ThemeApiService } from 'src/app/services/theme-api.service';
import { ArticleApiService } from 'src/app/services/article-api.service';

@Component({
  selector: 'app-create-form',
  templateUrl: './create-form.component.html',
  styleUrls: ['./create-form.component.scss']
})
export class CreateFormComponent implements OnInit {

  @Output() articleCreatedEmitter = new EventEmitter<boolean>();

  public articleForm!: FormGroup;;
  public themes$: Observable<Theme[]> = this.themeApiService.all();

  constructor(
    private themeApiService: ThemeApiService,
    private formBuilder: FormBuilder,
    private articleApiService: ArticleApiService
  ) { }

  ngOnInit(): void {

    this.articleForm = this.formBuilder.group({
      theme_ids: [
        [],
        [Validators.required]
      ],
      title: [
        '',
        [
          Validators.required,
          Validators.maxLength(50)
        ]
      ],
      content: [
       '',
        [
          Validators.required,
          Validators.minLength(1000)
        ]
      ],
    });
  }


  submit(): void {
    const formValues = this.articleForm?.value;

    const newArticle = {
      themes: [formValues.theme_ids],
      title: formValues.title,
      description: formValues.content.slice(0, 500),
      author: "1",
      content: formValues.content
    }

    console.log(newArticle)
      this.articleApiService.create(newArticle)
        .subscribe({
          next: (response) => {
            this.articleCreatedEmitter.emit(true);
          },
          error: (error) => {
            console.error('Create failed', error);
          }
        });
  }



}
