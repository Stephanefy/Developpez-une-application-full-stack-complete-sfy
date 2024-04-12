import { createAction, props } from '@ngrx/store';
import { User } from '../interfaces/user.interface';

export const login = createAction('[Auth] Login', props<{ usernameOrEmail: string, password: string }>());
export const register = createAction('[Auth] Register', props<{ email: string, username: string, password: string }>());
export const renew = createAction('[Auth] Renew');
export const loginSuccess = createAction(
    '[Auth] Login Success',
    props<{ token: string, user: User, isAuthenticated: boolean }>()
  );

  
  export const loginFailure = createAction(
    '[Auth] Login Failure',
    props<{ error: string }>()
  );
export const logout = createAction('[Auth] Logout');
export const clearErrors = createAction('[Auth] Clear Errors');