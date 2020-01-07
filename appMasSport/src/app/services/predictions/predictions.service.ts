import { Injectable } from '@angular/core';
import { ClientService } from '../client/client.service';
import { ConfigurationsService } from '../configurations/configurations.service';

@Injectable({
  providedIn: 'root'
})
export class PredictionsService {

  constructor( private client: ClientService , private config : ConfigurationsService) { }


  /********** Competitions **********/
  competitions() {
   return this.client.apiUrl + 'footballapi/'
        + this.client.apiVersion + '/competitions/list/'
        + this.client.appId + '/'
        + this.client.getLang()
        + this.client.getSecurityToken('?');
  }

  competitionsPrediction(clientId) {
    return this.client.apiUrl + 'sportsapi/'
        + this.client.apiVersion + '/clients/dashboard/' +  clientId + '/' + this.client.getLang()
        +  this.config.getGMT('?') + '&new=1' +this.client.getSecurityToken('&') ;

  }


  /**********  Bets **********/
  getBetsById(id_competition,id_client) {
    return this.client.apiUrl + 'sportsapi/'
        + this.client.apiVersion + '/clients/bets/get/'
        + id_client + '/' + id_competition
        + this.config.getGMT('?')
        + this.client.getSecurityToken('&');
  }

  getTodayBets(date, id_client) {
    return this.client.apiUrl + 'sportsapi/'
        + this.client.apiVersion + '/clients/bets/get/date/'
        + id_client + '/' + date
        + this.config.getGMT('?')
        + this.client.getSecurityToken('&');
  }

  createBets(id_client) {
    return this.client.apiUrl + 'sportsapi/'
        + 'v2' + '/client/' + id_client + '/bet' + this.client.getSecurityToken('?');
  }

  getBaseballPhase(id_competition, id_client){
    return 'https://plussports.hecticus.com/' + 'baseballapi/'
        + this.client.apiVersion + '/clients/bets/get/'
        + id_client + '/' + id_competition
        + this.client.getGMT('?')
        + this.client.getSecurityToken('&');
  } 

}
