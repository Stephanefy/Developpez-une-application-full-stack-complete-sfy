import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TranslationService {

  private translations: { [key: string]: string } = {
    "A user registered with this email is already there": "email ou nom d'utilisateur déjà enregistré",
    "Duplicate": "un élement existe déjà à ce nom",
    "undefined-user": "utilisateur non enregistré",
    "generic": "Une erreur est survenue"
  };

  translate(key: string): string {


    console.log(key)

    const splitErrorMessage = key.split(" ")

    console.log(splitErrorMessage)



    if(splitErrorMessage.includes("Duplicate")) {
      return this.translations["Duplicate"] 
    } else if (splitErrorMessage.includes("\"user\"") && splitErrorMessage.includes("null")) {
      console.log('reached ?')
      return this.translations["undefined-user"]
    }
    
    return this.translations[key] || key;

  }
}
