import { createSelector, createFeatureSelector } from '@ngrx/store';
import { AuthState } from '../interfaces/auth.interface';

export const selectAuth = createFeatureSelector<AuthState>('auth');



