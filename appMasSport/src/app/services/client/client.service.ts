import { Injectable } from '@angular/core';
import { ConfigurationsService } from '../configurations/configurations.service';
import * as moment from 'moment';

var companyName = '';
var buildVersion = '';
var serverVersion = '';
var bundleVersion = '0.0.2';
var bundleId = '';
var updateInfo = {};
var prefix = '507';
var hasToUpdateRegId = false;
var clientPushAlerts = [];
var clientDataSafe = false;

var currentVersion = 0;


@Injectable({
  providedIn: 'root'
})


export class ClientService {

  public apiUrl = 'http://plussports.hecticus.com/';
  public apiVersion = 'v1';
  public appId = '1';
  public event = '0';
  public provisionalLang = 'es';
  public client = {
  id_client: '',
  nickname: '',
  session: '',
  auth_token: '',
  password: '',
  msisdn: '',
  regId: '',
  user_id: '',
  login: '',
  language: ''
};

  /**
   * Constructor of the Service with Dependency Injection
   * @param http The standard Angular HttpClient to make requests
   */

  constructor( private app : ConfigurationsService) { }
  
  getAppender(index) {
    switch (index) {
      case '1': return '|';
      case '2': return '@';
      case '3': return '#';
      case '4': return '$';
      case '5': return '%';
      case '6': return '&';
      case '7': return '/';
      case '8': return '(';
      case '9': return ')';
      case '0': return '=';
      default: return '-';
    }
  }


  getHeadersAuth() {
    var companyName = this.app.getCompanyName();
    var buildVersion = this.app.getBuildVersion();
    var serverVersion = this.app.getServerVersion();
    var auth = companyName;
    auth += this.getAppender('9');
    auth += buildVersion;
    auth += this.getAppender('1');
    auth += serverVersion;
    return auth;
  }

  getHeadersAuthMatch() {
    var auth = this.getAppender('');
    auth += this.getAppender('');
    return auth;
  }


  getSecurityToken(prefix) {
    return prefix + 'upstreamChannel=' + this.app.getUpstreamChannel()
      + '&api_password=' + this.getHeadersAuth();
  }

  getSecurityTokenMatch(prefix) {
    return prefix + 'upstreamChannel=' + this.app.getUpstreamChannel()
      + '&api_password=' + this.getHeadersAuthMatch();
  }
  
  getLang(){
    //antes estaba 405 (portigues) ahora es 300 (español)
    return 300;
  }

  getGMT(prefix){
    var tz =  moment().format('[GMT]ZZ').replace(/\s/g, '');
    tz = encodeURIComponent(tz);
    //return prefix + 'timezoneName=' + tz;
    return prefix + 'timezoneName=GMT-0500'; //Cable!
  }

  /**********   Methods API for create, update , getClient   **********/

  create() {
    return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/create' + this.getSecurityToken('?');
  }

   upstream(client_id) {              
      return this.apiUrl + 'sportsapi/v2/client/' + client_id + '/upstream' + this.getSecurityToken('?');         
   }

  update(id_client , remind) {
    //console.log('client->update');}
    if (!remind) remind = false;
    return this.apiUrl + 'sportsapi/'
      + this.apiVersion + '/clients/update/' + id_client + this.getSecurityToken('?') + '&remind=' + remind;
  }

  getClientById(clientId: string){
    return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/get/' + clientId + '/' + this.app.getUpstreamChannel() + this.getSecurityToken('?')
  }

  checkpin() {
    return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/checkpin';
  }

  /**********   Service for RAnking and points of client **********/

  getPoints(clientId) {
    return this.apiUrl
        + 'sportsapi/'
        + this.apiVersion + '/clients/leaderboard/personal/tournament/'
        + clientId
        + this.getGMT('?')
        + this.getSecurityToken('&');
  }

  downClient(phone) {
    return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/down/' + phone;
  }

}