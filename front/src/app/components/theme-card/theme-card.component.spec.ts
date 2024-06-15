import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { provideMockStore } from '@ngrx/store/testing';

import { ThemeCardComponent } from './theme-card.component';
import { MatCardModule } from '@angular/material/card';

describe('ThemeCardComponent', () => {
  let component: ThemeCardComponent;
  let fixture: ComponentFixture<ThemeCardComponent>;
  let theme = {
    "id": 1,
    "name": "Technology",
    "description": "Explore the latest advancements in technology, including artificial intelligence, cybersecurity, and web development."
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ThemeCardComponent ],
      imports: [HttpClientTestingModule, MatCardModule],
      providers: [provideMockStore({})]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ThemeCardComponent);
    component = fixture.componentInstance;
    component.theme = theme;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
