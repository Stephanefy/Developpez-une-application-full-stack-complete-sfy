import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AuthFormComponent } from './components/auth-form/auth-form.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ArticleCardComponent } from './components/articles/article-card/article-card.component';
import {MatCardModule} from '@angular/material/card';
import { HttpClientModule } from '@angular/common/http'; // Import HttpClientModule
import { CommonModule } from '@angular/common';
import { ThemesComponent } from './pages/themes/themes.component';
import { ThemeCardComponent } from './components/theme-card/theme-card.component';
import { CreateComponent } from './pages/articles/create/create.component';
import { DetailsComponent } from './pages/articles/details/details.component';
import { ProfileComponent } from './pages/user/profile/profile.component';
import {MatSelectModule} from '@angular/material/select';
import { CommentFormComponent } from './components/comments/comment-form/comment-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateFormComponent } from './components/articles/create-form/create-form.component';
import { UserProfileFormComponent } from './components/users/user-profile-form/user-profile-form.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { StoreModule } from '@ngrx/store';
import { authReducer } from './state/auth.reducer';
import { EffectsModule } from '@ngrx/effects';
import { AuthEffects } from './state/auth.effects';


@NgModule({
  declarations: [AppComponent, HomeComponent, InscriptionComponent, ConnexionComponent, NavbarComponent, AuthFormComponent, ArticlesComponent, ArticleCardComponent, ThemesComponent, ThemeCardComponent, CreateComponent, DetailsComponent, ProfileComponent, CommentFormComponent, CreateFormComponent, UserProfileFormComponent],
  imports: [
    StoreModule.forRoot({ auth: authReducer }),
    EffectsModule.forRoot([AuthEffects]),
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatSelectModule,
    MatSnackBarModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
