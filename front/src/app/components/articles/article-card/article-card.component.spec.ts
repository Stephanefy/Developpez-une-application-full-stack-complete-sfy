import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideMockStore } from '@ngrx/store/testing';

import { ArticleCardComponent } from './article-card.component';
import { MatCardModule } from '@angular/material/card';
import { Article } from 'src/app/interfaces/article.interface';
import { Router } from '@angular/router';

describe('ArticleCardComponent', () => {
  let component: ArticleCardComponent;
  let fixture: ComponentFixture<ArticleCardComponent>;
  let article: Article = {
    id: 1,
    title: "title",
    content: "content",
    description: "description",
    publicationDate: new Date(),
    author: {
      id: 1,
      username: "John",
      email: "john@example.com"
  },
    themes: [ {id: 1,
      name: "Angular",
      description: "description of Angular"
    }],
    comments: "test"
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MatCardModule],
      declarations: [ ArticleCardComponent ],
      providers: [provideMockStore({}), 
        { provide: Router, useValue: Router }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleCardComponent);
    component = fixture.componentInstance;
    component.article = article;
    fixture.detectChanges();

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
