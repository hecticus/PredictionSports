import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'rankings', loadChildren: './pages/rankings/rankings.module#RankingsPageModule' },
  { path: 'my-points', loadChildren: './pages/my-points/my-points.module#MyPointsPageModule' },
  { path: 'predictions', loadChildren: './pages/predictions/predictions.module#PredictionsPageModule' },
  { path: 'news', loadChildren: './pages/news/news.module#NewsPageModule' },
  { path: 'configurations', loadChildren: './pages/configurations/configurations.module#ConfigurationsPageModule' },
  { path: 'tabs', loadChildren: './pages/tabs/tabs.module#TabsPageModule' },
  { path: 'login', loadChildren: './pages/login/login.module#LoginPageModule' },
  { path: 'get-password', loadChildren: './pages/get-password/get-password.module#GetPasswordPageModule' },
  { path: 'tutorial', loadChildren: './pages/tutorial/tutorial.module#TutorialPageModule' },
  { path: 'tabs-statistics', loadChildren: './pages/tabs-statistics/tabs.module#TabsPageModule' },
  { path: 'live', loadChildren: './pages/live/live.module#LivePageModule' },
  { path: 'matches', loadChildren: './pages/matches/matches.module#MatchesPageModule' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
