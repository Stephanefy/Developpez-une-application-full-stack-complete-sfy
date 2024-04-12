import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TranslationService {

  private translations: { [key: string]: string } = {
    "A user registered with this email is already there": "email ou nom d'utilisateur déjà enregistré"
  };

  translate(key: string): string {
    return this.translations[key] || key;
  }
}
