import { Component, OnInit, NgZone, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';
import { LiveService } from '../../services/live/live.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as moment from 'moment';
import { Router} from '@angular/router';
import { LoadingService } from '../../services/loading/loading.service';
import { ClientService } from '../../services/client/client.service'
import { Location } from '@angular/common';

@Component({
  selector: 'app-live',
  templateUrl: './live.page.html',
  styleUrls: ['./live.page.scss'],
})
export class LivePage implements OnInit , OnDestroy {

  private routerEvents: any;
  private previousUrl: string;
  private currentUrl: string;
  private canGoBack: boolean;
  // tslint:disable-next-line:variable-name
  date_day: any;
  date_match: any;
  name_leagues: any;
  id_leagues : any;
  data: any;
  logo_leagues: any;
  leaguesOn: any;
  matchOn: any;
  tab = 0;
  // tslint:disable-next-line:ban-types
  params: Object;
  pushPage: any;
  status_color1 = '#db7f1c';
  status_color2 = '#92d822';
  status_color3 = '#061259';
  sizeData : any;
  info_match : any;

  clientId = localStorage.getItem('id_client');
  info: any;
  login: any;
  status: any;


  constructor(
    private liveService: LiveService,
    private http: HttpClient,
    private router: Router,
    private loading: LoadingService,
    private zone: NgZone,
    private client : ClientService,
    private ionRouter: Location
  ) { }

  ngOnInit() {
  
    this.loading.presentLoading();
    this.http.get(this.client.getClientById(this.clientId))
      .subscribe(resp =>{
        this.info = resp['response']
        this.login = this.info.login
        this.status= this.info.status
          if(this.login === "guest" && this.status !== 1){
            this.loading.presentAlertConfirm(
              'Alert',
              '',
              'Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?',
              [
                {
                  text: 'Cancelar',
                  handler: () => {
                    this.ionRouter.back()
                    this.ngOnDestroy()
                  }
                },
                {
                  text: 'Aceptar',
                  handler: () => {
                    this.router.navigateByUrl('/get-password'); 
                    this.ngOnDestroy()
                  }
                }
              ]
          ) 
          }else{
            this.tab = 0;
            moment.locale('es');
            this.date_day = moment().format('dddd D[,] YYYY');
            this.liveMatch();
          }
      })  
  }

  liveMatch() {
    this.tab = 0;
    const date = moment().format('YYYYMMDD');
    this.loading.presentLoading();
    this.http.get(this.liveService.allMatchesLive(date))
      .subscribe(resp => {
        this.data = resp['response'].leagues
        this.sizeData = this.data.length
      });
  }

  matchDate(date) {
    moment.locale('es');
    return moment(date).format('DD MMM YYYY');
  }

  matchTime(date) {
    moment.locale('es');
    return moment(date, 'YYYY/MM/DD HH:mm:ss').format('HH:mm');
  }

  macthStatus(status) {
    if (status === 1) {
      return 'Jugado';
    } else if (status === 2) {
      return 'Jugando';
    } else {
      return 'Programado';
    }
  }

  detailMatchLive(leagues, match) {
    this.tab++;   
    this.loading.presentLoading();
    this.refresh();
    this.http.get(this.liveService.matchLive(leagues.id_competitions, match.id_game_matches))
      .subscribe(resp => {
        this.date_match =  resp['response']
        this.info_match = this.date_match
        if(this.date_match) {
          this.name_leagues =  leagues.competiton_type.name;
          this.logo_leagues  =  leagues.competiton_type.competition_logo;
          this.id_leagues = leagues.id_competitions
          this.leaguesOn = leagues;
          this.matchOn = match;
        }
      });
  }

   mainPage() {  
     this.liveMatch()
     this.refresh();
  }

  refresh() {
  this.zone.run(() => {
    console.log('force update the screen');
    });
  }

  refreshGame() {  
  this.detailMatchLive(this.leaguesOn, this.matchOn);
  this.refresh();
  }

 ngOnDestroy() {
    console.log("Exit")
  }

}
