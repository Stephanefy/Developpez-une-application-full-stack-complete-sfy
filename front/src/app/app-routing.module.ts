import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { DetailsComponent } from './pages/articles/details/details.component';
import { CreateComponent } from './pages/articles/create/create.component';
import { ProfileComponent } from './pages/user/profile/profile.component';
import { CanActivateGuard } from './guards/can-activate.guard';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'articles', component: ArticlesComponent, canActivate: [CanActivateGuard] },
  { path: 'themes', component: ThemesComponent, canActivate: [CanActivateGuard] },
  { path: 'articles/create', component: CreateComponent, canActivate: [CanActivateGuard] },
  { path: 'articles/:id', component: DetailsComponent, canActivate: [CanActivateGuard] },
  { path: 'user/profile', component: ProfileComponent, canActivate: [CanActivateGuard] },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
