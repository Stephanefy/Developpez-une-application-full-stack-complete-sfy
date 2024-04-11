import { TestBed } from '@angular/core/testing';

import { CanActivateGuard } from './auth.guard';

describe('CanActivateGuard', () => {
  let guard: CanActivateGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CanActivateGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
