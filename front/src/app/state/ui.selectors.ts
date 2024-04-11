import { createSelector, createFeatureSelector } from '@ngrx/store';
import { UIState } from '../interfaces/ui.interface';

export const selectUI = createFeatureSelector<UIState>('ui');