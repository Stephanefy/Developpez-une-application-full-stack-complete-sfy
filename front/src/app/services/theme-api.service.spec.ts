import { TestBed } from '@angular/core/testing';

import { ThemeApiService } from './theme-api.service';

describe('ThemeApiService', () => {
  let service: ThemeApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ThemeApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
