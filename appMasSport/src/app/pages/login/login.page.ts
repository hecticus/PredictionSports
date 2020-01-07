import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConfigurationsService } from '../../services/configurations/configurations.service';
import { ClientService } from '../../services/client/client.service';
import { Router} from '@angular/router';
import { LoadingService } from '../../services/loading/loading.service'
import { Device } from '@ionic-native/device/ngx';
import { Platform } from '@ionic/angular';


var AUTH_TOKEN_PREFIX = 'Basic ';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
 
export class LoginPage implements OnInit, OnDestroy {
  prefix = '507';
  msisdn: string;
  password: string;
  clientJson: any;
  responseInfo: any;
  tokenUser : any;
  responseUpstream : any;
  id_client : any;
  session : any; 
  statusResponse: any;
  old_User: any;
  new_User: any;

  constructor(  
    private config: ConfigurationsService, 
    private client: ClientService, private http : HttpClient,
    private  router: Router, private loading: LoadingService,
    private device : Device, public platform : Platform ) { }

  ngOnInit() { }

  createOrUpdateClient() {
    var lang = {
      name: 'español',
      id_language:300,
      short_name:'es'
    };
    var log = {
      msisdn: this.prefix + this.msisdn,
      password: this.password,
      upstreamChannel: '' ,
      ua: '',
      platform:'',
      appCodeName:'',
      appName:'',
      language: '',
      cordova:'',
      model:'',
      uuid:'',
      version:'',
      isVirtual: false,
      serial:'',
      onLine: false,
      manufacturer: '',
      id_language: 300
      };

    log.upstreamChannel =  this.config.getUpstreamChannel();
    this.loading.presentLoading() 
    if (this.config.getPlatform() === 'Web') { 
        log.ua =  navigator.userAgent;
        log.platform =  navigator.platform;
        log.onLine  =  navigator.onLine;
        log.appCodeName = navigator.appCodeName;
        log.appName = navigator.appName;
        log.language = navigator.language; 

    } else if (this.config.getPlatform() === 'Android' || this.config.getPlatform() === 'iOS'){
        log.cordova = this.device.cordova;
        log.model = this.device.model;
        log.platform = this.device.platform;
        log.uuid = this.device.uuid;
        log.version = this.device.version;
        log.manufacturer = this.device.manufacturer;
        log.isVirtual = this.device.isVirtual;
        log.serial = this.device.serial;   
    }
    //antes id_language era 405 para portugues ahora es 300 para español
    var jData = {
        country : 4,
        language: lang ? lang.id_language : 300,
        device_id : this.config.getDeviceId(),
        upstreamChannel : this.config.getUpstreamChannel(),
        log: log,
        login: this.prefix + this.msisdn,
        password: this.password,
        devices:{},
        subscribe: true
    };

    var url = this.client.create();
    console.log('createOrUpdateClient -> Payload -> ' + JSON.stringify(jData));

    var headers = new HttpHeaders();
    headers.append("Accept", 'application/json');
    headers.append('Content-Type', 'application/json' );
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type':  'application/json'
      })
    };

    this.http.post(url, jData, httpOptions)
      .toPromise()
      .then(data => {
        this.statusResponse  = data['description']
        this.responseInfo = data['response'];
        this.id_client = this.responseInfo.id_client

        if(this.statusResponse === 'OK' ){
          localStorage.setItem('id_client',this.responseInfo.id_client)
          if(localStorage.length >1){
            this.old_User = localStorage.getItem('session')
            localStorage.setItem('session',this.responseInfo.user_id)
          }else{
            localStorage.setItem('session',this.responseInfo.user_id)
          }      
        }

        this.tokenUser = this.responseInfo.auth_token
        this.upstream()
      }, error => {
        this.loading.presentToast('Teléfono o contraseña incorrecto, intente nuevamente!!!.', 'danger')
        console.log(error);
        
      });    
      
      this.msisdn = undefined
      this.password = undefined
  }

  upstream(){

    this.session = localStorage.getItem('session');
    var headers = new HttpHeaders();
    headers.append("Accept", 'application/json');
    headers.append('Content-Type', 'application/json' );
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type':  'application/json'
      })
    };

  
  if(this.tokenUser !== undefined ){
    headers.append("Authorization", AUTH_TOKEN_PREFIX + this.tokenUser)
  } 

  var config = {
    headers: headers
  };  

  var body = {
    event_type : 'LOGIN',
    metadata : {
      appVersion : '0.0.2',
      channel : this.config.getPlatform()
    },
    user_id: this.id_client
  }

  if(this.id_client !== undefined && this.client !== null ){
    var upstream = this.client.upstream(this.id_client)
    this.http.post(upstream, body, config)
      .toPromise()
      .then(data => {
        this.responseUpstream = data['response'];
          
        if(this.session !== this.old_User) {
          this.router.navigateByUrl('/tutorial');
        }else{
          this.router.navigateByUrl('/tabs/predictions');
        }
         
      }, error => {
        this.loading.presentToast('Teléfono o contraseña incorrecto, intente nuevamente!!!.', 'danger')
        console.log(error);
        
      });
    }
  }

  loginSessionInvited() {

    var lang = {
      name: 'español',
      id_language:300,
      short_name:'es'
    };
    var log = {
      upstreamChannel: '' ,
      platform:'',
      language: '',
      cordova:'',
      model:'',
      uuid:'',
      version:'',
      serial:'',
      manufacturer: '',
      id_language: 300
      };

    log.upstreamChannel =  this.config.getUpstreamChannel();
    this.loading.presentLoading() 
    if (this.config.getPlatform() === 'Web') { 
        log.platform =  navigator.platform;
        log.language = navigator.language; 

    } else {
        log.cordova = this.device.cordova;
        log.model = this.device.model;
        log.platform = this.device.platform;
        log.uuid = this.device.uuid;
        log.version = this.device.version;
        log.manufacturer = this.device.manufacturer;
        log.serial = this.device.serial;   
    }

    var jData = {
        country : 4,
        language: lang ? lang.id_language : 300,
        device_id : this.config.getDeviceId(),
        upstreamChannel : this.config.getUpstreamChannel(),
        log: log,
        subscribe: true
    };

    var url = this.client.create();
    console.log('createOrUpdateClient -> Payload -> ' + JSON.stringify(jData));

    var headers = new HttpHeaders();
    headers.append("Accept", 'application/json');
    headers.append('Content-Type', 'application/json' );
    const httpOptions = {
      headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
    };
    this.http.post(url, jData, httpOptions)
      .toPromise()
      .then(data => {
        this.responseInfo = data['response'];
        localStorage.setItem('id_client', this.responseInfo.id_client)
        this.router.navigateByUrl('/configurations'); 
      }, error => {
        console.log(error);
        
      });
  }

  ngOnDestroy() {
    console.log("Exit")
  }

}
