import { Component, OnInit, OnDestroy } from '@angular/core';
import { Platform } from '@ionic/angular';
import { SplashScreen } from '@ionic-native/splash-screen/ngx';
import { StatusBar } from '@ionic-native/status-bar/ngx';
import { Router, RouterEvent, NavigationEnd } from '@angular/router';
import { LoadingService } from './services/loading/loading.service'
import { Location } from '@angular/common';
import { ConfigurationsService } from './services/configurations/configurations.service'

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html'
})
export class AppComponent implements OnInit, OnDestroy {

  predictions = [
    {
      title: 'Predicciones',
      url: '/tabs/predictions',
      icon: 'football'
    },
    {
      title: 'Rankings',
      url: '/tabs/rankings',
      icon: 'ribbon'
    },
    {
      title: 'Noticias',
      url: '/tabs/news',
      icon: 'paper'
    },
    {
      title: 'Mis Puntos',
      url: '/tabs/my-points',
      icon: 'stats'
    }
  ];

   statistics = [
   {
      title: 'Partidos',
      url: '/tabs-statistics/matches',
      icon: 'football'
    },
    {
      title: 'En vivo',
      url: '/tabs-statistics/live',
      icon: 'alarm'
    }
  ];

  session = true;
  current_url: any;
  client_id = localStorage.getItem("id_client")

  constructor(
    private platform: Platform,
    private splashScreen: SplashScreen,
    private statusBar: StatusBar,
    private router: Router,
    private loading: LoadingService,
    private ionRouter : Location,
    private config : ConfigurationsService,
  ) {
    this.initializeApp();
  }

  ngOnInit() {

    this.router.events.subscribe((event: RouterEvent) => {
      if (event instanceof NavigationEnd) {
        this.predictions.map( p => {
          return p['active'] = (event.url === p.url);
        });
      }
    });
    this.inStatistics ();
  }

  inStatistics() {
     this.router.events.subscribe((event: RouterEvent) => {
      if (event instanceof NavigationEnd) {
       this.statistics.map( p => {
          return p['active'] = (event.url === p.url);
        });
      }
    });
  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
      this.current_url = localStorage.getItem('location')
    // active hardware back button
      document.addEventListener("backbutton", () => { 
        if (this.router.url === '/tabs/predictions' || this.router.url === '/tabs-statistics/live' || this.router.url === '/tabs-statistics/matches'  || this.router.url === '/tabs/news' || this.router.url === '/tabs/rankings' || this.router.url === '/tabs/my-points') {
          this.loading.presentAlertConfirm(
            'Alerta',
            '',
            '¿Estás seguro que quieres salir de la aplicación?',
            [
              {
                text: 'Cancelar',
                handler: () => {         
                  console.log('Cancel')
                }
              },
              {
                text: 'Aceptar',
                handler: () => {
                  navigator['app'].exitApp();
                }
              }
            ]
          ) 
        }else {
          this.goBack()
        } 
      });


       if(this.client_id !== null && this.session === true && this.current_url !== null && this.current_url !== undefined){
          console.log("inicia app sin deslogueo") 
          console.log(this.current_url)   
          this.router.navigateByUrl(this.current_url);
          
        }

      // pause and resume sesion
      this.platform.resume.subscribe(() => {
        this.current_url = localStorage.getItem('location')
        if(this.client_id !== null && this.session === true && this.current_url !== null){
          console.log("resumen metodo") 
          console.log(this.current_url)   
          this.router.navigateByUrl(this.current_url);
          
        }

      });

      this.platform.pause.subscribe(() => {
        console.log("pause metodo")
        localStorage.setItem("location",this.router.url);
        this.current_url = localStorage.getItem('location')
        console.log(this.current_url)
      });

    });
  }

  logOut() {
    this.router.navigate(['/login']);  
    this.ngOnDestroy();
  }

  ngOnDestroy() {
    this.session= false;
    localStorage.removeItem('id_client');
  }

  goBack(){
    this.ionRouter.back()
  }

}
