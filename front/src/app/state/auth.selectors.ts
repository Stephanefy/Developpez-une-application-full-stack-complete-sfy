import { createSelector, createFeatureSelector } from '@ngrx/store';
import { AuthState } from '../interfaces/auth.interface';

export const selectAuth = createFeatureSelector<AuthState>('auth');

export const selectAuthError = createSelector(
    selectAuth,
    auth => auth.error
  );



