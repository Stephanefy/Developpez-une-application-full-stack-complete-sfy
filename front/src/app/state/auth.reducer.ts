import { createReducer, on } from '@ngrx/store';

import { login, register, logout, loginSuccess, loginFailure} from './auth.actions';
import { AuthState } from '../interfaces/auth.interface';
import { getInitialState } from '../utils';


export const initialState: AuthState = getInitialState();

export const authReducer = createReducer(
  initialState,
 
  on(loginSuccess, (state, { user }) => ({ ...state, user, isAuthenticated: true })),
  on(loginFailure, (state, { error }) => ({ ...state, error })),  
  on(logout, (state) => ({ ...state, user: null, isAuthenticated: false }),

  ),
);
