import { AuthState } from "./interfaces/auth.interface";

/**
 * Retrieves the initial state of the authentication data from the local storage.
 *
 * @return {AuthState} The initial state of the authentication data.
 */
export const getInitialState = (): AuthState => {
    // Attempt to parse the stored auth data
    const storedUser = localStorage.getItem('user');
    const authStatus = localStorage.getItem('authStatus');
    if (storedUser && authStatus) {
      try {storedUser
        const user = JSON.parse(storedUser);
        const isAuthenticated = JSON.parse(authStatus);
        // Ensure the shape of the parsed data matches AuthState interface
        if (authStatus) {
          return { user: user, isAuthenticated }; // Assuming your AuthState has an `error` field.
        }
      } catch (error) {
        console.error('Error parsing stored auth data', error);
      }
    } 
    // Return default state if no valid stored auth data
    return { token: '', user: null, isAuthenticated: false };
  }
  