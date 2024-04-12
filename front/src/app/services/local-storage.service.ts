import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';


/**
 * Service for local storage
 * to cache olympics data so that once first initialized on home page it won't need to be fetched again
 */
@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private key: string = 'user';

  constructor() { }

  // Set an item in local storage
  setItem<T>(key: string, value: T): void {
    try {
      const stringValue = JSON.stringify(value);
      localStorage.setItem(key, stringValue);
    } catch (err) {
      console.error('Error saving to localStorage', err);
    }
  }

  // Get an item from local storage
  getItem<T>(key: string): Observable<T>{
    try {
      const item = localStorage.getItem(key);
      if (item === null) {
        return throwError(() => new Error(`Item not found: ${key}`));
      }
      return of(JSON.parse(item) as T);
    } catch (err) {
      return throwError(() => new Error('Error getting data from localStorage'));
    }
  }

  // Remove an item from local storage
  removeItem(key: string): void {
    try {
      localStorage.removeItem(key);
    } catch (err) {
      console.error('Error removing item from localStorage', err);
    }
  }


  isLoggedIn(): boolean {
    const authStatus = localStorage.getItem('authStatus');
    if (authStatus) {
      return true
    }
    return false
  }

  // Clear all local storage
  clear(): void {
    try {
      localStorage.clear();
    } catch (err) {
      console.error('Error clearing localStorage', err);
    }
  }
}