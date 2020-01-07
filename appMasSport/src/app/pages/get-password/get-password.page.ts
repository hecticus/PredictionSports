import { Component, OnInit,  OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClientService } from '../../services/client/client.service';
import { ConfigurationsService } from '../../services/configurations/configurations.service';
import { Router} from '@angular/router';
import { ToastController } from '@ionic/angular';
import { LoadingService } from '../../services/loading/loading.service'
import { Location } from '@angular/common';


@Component({
  selector: 'app-get-password',
  templateUrl: './get-password.page.html',
  styleUrls: ['./get-password.page.scss'],
})
export class GetPasswordPage implements OnInit,  OnDestroy {

	prefix = '507';
  msisdn: string;
  responseInfo : any;	
  clientId = localStorage.getItem('id_client');
  info: any;
  loginUser: any;
  status: any;

  constructor(private client: ClientService, private config : ConfigurationsService,
    private http : HttpClient, public toastController: ToastController,
    private  router: Router, private loading: LoadingService,
    private location: Location) { }


  ngOnInit() {

    this.loading.presentLoading();
    this.http.get(this.client.getClientById(this.clientId))
      .subscribe(resp =>{
        this.info = resp['response']
        this.loginUser = this.info.login
        this.status= this.info.status
      })
  }

	getPassword(){
    var subscribe = true
    var devices = [];
    var device = {
      msisdn: this.prefix + this.msisdn,
      upstreamChannel:'',
      platform:'',
      language: '',
      cordova:'',
      model:'',
      uuid:'',
      version:'',
      serial:'',
      manufacturer: '',
      id_language:'',
    };

    var lang = {
      name: 'español',
      id_language:300,
      short_name:'es'
    };

    var log = {
      msisdn: this.prefix + this.msisdn,
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
        log.cordova = device.cordova;
        log.model = device.model;
        log.platform = device.platform;
        log.uuid = device.uuid;
        log.version = device.version;
        log.manufacturer = device.manufacturer;
        log.serial = device.serial;   
    }
    //antes id_language era 405 para portugues ahora es 300 para español
    var jData = {
        country : 4,
        language: lang ? lang.id_language : 300,
        upstreamChannel : this.config.getUpstreamChannel(),
        log: log,
        login: this.prefix + this.msisdn,
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
      	this.loading.presentAlert('Pronto recibirá un mensaje de texto con su clave para ingresar como usuario suscrito a MasSport.')
        // localStorage.setItem('id_client', this.responseInfo.id_client)
        this.router.navigateByUrl('/'); 
      }, error => {
        console.log(error);
        
      });
  }

  ngOnDestroy() {
    console.log("Exit")
  }

  goBack(){
    if(this.loginUser === 'guest' && this.status === 2){
      this.router.navigateByUrl('/tabs/predictions');  
    }else{
      this.location.back()
    }
    
  }

}
