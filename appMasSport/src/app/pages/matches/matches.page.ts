import { Component, OnInit, NgModule, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import * as moment from 'moment';
import { Router} from '@angular/router';
import { LiveService } from '../../services/live/live.service';
import { LoadingService } from '../../services/loading/loading.service';
import { NgZone } from '@angular/core';
import { ClientService } from '../../services/client/client.service'
import { Location } from '@angular/common';


@Component({
  selector: 'app-matches',
  templateUrl: './matches.page.html',
  styleUrls: ['./matches.page.scss'],
})
export class MatchesPage implements OnInit, OnDestroy {

  date_day: any;
  old_date: any;
  new_date: any;
  data: any;
  tab = 0;
  sizeData: any;
  index = 0;
  status_color1 = '#db7f1c';
  status_color2 = '#92d822';
  status_color3 = '#061259';
  minDate: any;
  current_date: any;
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
    private ionRouter : Location
    ) { }

  ngOnInit() {
    moment.locale('es');
    this.current_date = moment().format('DD/MM/YY');
    this.date_day = moment().format('DD/MM/YY');
    this.old_date = moment().subtract(1, 'd').format('DD/MM/YY');
    this.new_date = moment().add(1, 'd').format('DD/MM/YY');
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
            this.liveMatch(this.date_day);
          }
      })
    
  }

  liveMatch(date) {
    // tslint:disable-next-line:variable-name
    const date_check = moment(date, 'DD/MM/YY', true).format('YYYYMMDD');
    this.loading.presentLoading();
    this.http.get(this.liveService.allMatchesLive(date_check))
      .subscribe(resp => {
        this.data = resp['response'] ? resp['response'].leagues : [];
        this.sizeData = this.data.length;
      });
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

  nextPage(date) {
    this.old_date = this.date_day;
    this.date_day = date;
    this.new_date = moment(date, 'DD/MM/YY', true).add(1, 'd').format('DD/MM/YY');
    this.liveMatch(this.date_day);
    this.refresh();
  }

  prevPage(date) {
    this.new_date = this.date_day;
    this.date_day = date;
    this.old_date = moment(date, 'DD/MM/YY', true).subtract(1, 'd').format('DD/MM/YY');
    this.liveMatch(this.date_day);
    this.refresh();
  }

  refresh() {
  this.zone.run(() => {
    console.log('force update the screen');
    });
  }

  currentDate(){
    if(this.date_day === this.current_date){
      return 'Hoy'
    }else{
      return this.date_day;
    }
  }

  ngOnDestroy() {
    console.log("Exit")
  }
}
