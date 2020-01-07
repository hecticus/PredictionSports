import { Injectable } from '@angular/core';
import { ClientService } from '../client/client.service';
import { ConfigurationsService } from '../configurations/configurations.service';
@Injectable({
  providedIn: 'root'
})
export class LiveService {

  constructor( private client: ClientService , private config: ConfigurationsService) { }

  allMatchesLive(date) {
    return this.client.apiUrl + 'footballapi/'
         + this.client.apiVersion + '/matches/date/paged/'
         + this.client.appId + '/'
         + this.client.getLang() + '/' + date
         + this.config.getGMT('?')
         + this.client.getSecurityToken('&')
         + '&page=0&pageSize=100';
  }

  matchLive(competition, match) {
    return this.client.apiUrl + 'footballapi/'
         + this.client.apiVersion + '/matches/mam/next/'
         + this.client.appId + '/' + competition + '/' + match + '/' + 
         + this.client.getLang() + '/' + this.client.event 
         + this.config.getGMT('?')
         + this.client.getSecurityToken('&');
  }
}