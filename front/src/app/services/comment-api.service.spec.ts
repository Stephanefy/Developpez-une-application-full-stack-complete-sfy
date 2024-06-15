import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { CommentApiService } from './comment-api.service';

describe('CommentApiService', () => {
  let service: CommentApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(CommentApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
