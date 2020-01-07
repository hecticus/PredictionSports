import { Injectable } from '@angular/core';
import { ClientService } from '../client/client.service';


@Injectable({
  providedIn: 'root'
})
export class CompetitionsService {

  constructor( private client : ClientService) { }

  getCompetitionsFootball() {
   	return this.client.apiUrl + 'footballapi/'
          + this.client.apiVersion + '/competitions/list/'
          + this.client.appId + '/'
          + this.client.getLang()
          + this.client.getSecurityToken('?');
  }

  getCompetitionsBaseball() {
   	return this.client.apiUrl + 'baseballapi/'
          + this.client.apiVersion + '/competitions/list'
          + this.client.getSecurityToken('?');
  }

  totalRanking(id_client) {
    return this.client.apiUrl
          + 'sportsapi/v1/clients/leaderboard/total/' + id_client
          + this.client.getSecurityToken('?');
  }

  getLeagues(id_client , id_competition) {
    return this.client.apiUrl + 'sportsapi/'
        + this.client.apiVersion + '/clients/leaderboard/global/get/'
        + id_client + '/' + id_competition
        + this.client.getGMT('?')
        + this.client.getSecurityToken('&');
  }

  getPhase(id_client , id_competition , id_phase) {
    return this.client.apiUrl + 'sportsapi/'
        + this.client.apiVersion + '/clients/leaderboard/get/'
        + id_client + '/' + id_competition + '/' + id_phase
        + this.client.getGMT('?')
        + this.client.getSecurityToken('&');
  }
  getAllTeamByCompetition(id_competition) {
    return this.client.apiUrl + 'footballapi/'
           + this.client.apiVersion + '/team/competition/all/' + id_competition + this.client.getSecurityToken('?');
  }
                
}
