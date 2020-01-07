import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: '',
    component: TabsPage,
    children : [
      {
        path: 'predictions',
        loadChildren: '../predictions/predictions.module#PredictionsPageModule'
      },
      {
        path: 'rankings',
        loadChildren: '../rankings/rankings.module#RankingsPageModule'
      },
      {
        path: 'news',
        loadChildren: '../news/news.module#NewsPageModule'
      },
      {
        path: 'my-points',
        loadChildren: '../my-points/my-points.module#MyPointsPageModule'
      },
      {
        path: 'live',
        loadChildren: '../live/live.module#LivePageModule'
      }
    ]
  }
];


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [TabsPage]
})
export class TabsPageModule {}
