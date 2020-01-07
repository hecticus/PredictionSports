import { Injectable } from '@angular/core';
import { ClientService } from '../client/client.service';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor( private client: ClientService) { }

  getNewsUpdate(){
    return this.client.apiUrl +'fifa';
  }
  
  getNews() {
    return this.client.apiUrl+ 'newsapi/'
        + this.client.apiVersion + '/news/scroll/'
        + this.client.appId + '/' + this.client.getLang()
        + this.client.getGMT('?')  + this.client.getSecurityToken('&');
  }

  getNewsUp(_news) {
    return this.client.apiUrl + 'newsapi/'
        + this.client.apiVersion + '/news/scroll/up/rest/'
        + this.client.appId + '/' + this.client.getLang() + '/' + _news
        + this.client.getGMT('?') + this.client.getSecurityToken('&');
  }

  getNewsDown(_news) {
	  return this.client.apiUrl + 'newsapi/'
	      + this.client.apiVersion + '/news/scroll/down/rest/'
	      + this.client.appId + '/' + this.client.getLang() + '/' + _news
	      + this.client.getGMT('?')  + this.client.getSecurityToken('&');
  }

}
