import { createReducer, on } from '@ngrx/store';

import { login, register, logout, loginSuccess, loginFailure, clearErrors, updateUser} from './auth.actions';
import { AuthState } from '../interfaces/auth.interface';
import { getInitialState } from '../utils';


export const initialState: AuthState = getInitialState();

export const authReducer = createReducer(
  initialState,
  on(updateUser, (state, { user }) => ({ ...state, user })),
  on(loginSuccess, (state, { token, user }) => ({ ...state, token, user, isAuthenticated: true })),
  on(loginFailure, (state, { error }) => ({ ...state, user: null, isAuthenticated: false, error })),  
  on(logout, (state) => ({ ...state, user: null, isAuthenticated: false })),
  on(clearErrors, (state) => ({ ...state, error: null })),

);

