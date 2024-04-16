import { NgModule, APP_INITIALIZER } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthFormComponent } from './components/auth-form/auth-form.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ArticleCardComponent } from './components/articles/article-card/article-card.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { CommonModule } from '@angular/common';
import { ThemesComponent } from './pages/themes/themes.component';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';
import { CreateComponent } from './pages/articles/create/create.component';
import { DetailsComponent } from './pages/articles/details/details.component';
import { ProfileComponent } from './pages/user/profile/profile.component';
import { CommentFormComponent } from './components/comments/comment-form/comment-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateFormComponent } from './components/articles/create-form/create-form.component';
import { UserProfileFormComponent } from './components/users/user-profile-form/user-profile-form.component';
import { Store, StoreModule } from '@ngrx/store';
import { authReducer } from './state/auth.reducer';
import { EffectsModule } from '@ngrx/effects';
import { AuthEffects } from './state/auth.effects';
import { SortByDatePipe } from './pipes/sortByDatePipe';
import { MobileSidebarComponent } from './components/mobile-sidebar/mobile-sidebar.component';
import { uiReducer } from './state/ui.reducer';
import { JwtInterceptor } from './interceptors/interceptor';
import { renew } from './state/auth.actions';
import { TranslatePipe } from './pipes/translate.pipe';
import { BackButtonComponent } from './components/back-button/back-button.component';
import { MaterialModule } from './material/material.module';

export function initializeApp(store: Store) {
  return (): Promise<void> => {
    return new Promise(resolve => {
      store.dispatch(renew());
      
      resolve();
    });
  };
}


@NgModule({
  declarations: [AppComponent, HomeComponent, InscriptionComponent, ConnexionComponent, NavbarComponent, AuthFormComponent, ArticlesComponent, ArticleCardComponent, ThemesComponent, ThemeCardComponent, CreateComponent, DetailsComponent, ProfileComponent, CommentFormComponent, CreateFormComponent, UserProfileFormComponent, SortByDatePipe, MobileSidebarComponent, TranslatePipe, BackButtonComponent],
  imports: [
    StoreModule.forRoot({ auth: authReducer, ui: uiReducer }),
    EffectsModule.forRoot([AuthEffects]),
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    // { provide: APP_INITIALIZER, useFactory: initializeApp, deps: [Store], multi: true }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
