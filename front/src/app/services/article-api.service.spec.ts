import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { ArticleApiService } from './article-api.service';

describe('ArticleApiService', () => {
  let service: ArticleApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(ArticleApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
