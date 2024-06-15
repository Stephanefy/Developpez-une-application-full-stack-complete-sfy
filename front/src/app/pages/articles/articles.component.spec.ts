import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { provideMockStore } from '@ngrx/store/testing';
import { SortByDatePipe } from 'src/app/pipes/sortByDatePipe';

import { ArticlesComponent } from './articles.component';

describe('ArticlesComponent', () => {
  let component: ArticlesComponent;
  let fixture: ComponentFixture<ArticlesComponent>;
  let currentUser =   {
    "userId": 1,
    "sub": "user1-uuid",
    "username": "AliceJohnson",
    "email": "alice.johnson@example.com"
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticlesComponent, SortByDatePipe ],
      imports: [HttpClientTestingModule],
      providers: [provideMockStore({})]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticlesComponent);
    component = fixture.componentInstance;
    component["currentUser"] = currentUser;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
