import { Component, OnInit, OnDestroy } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { ClientService } from '../../services/client/client.service'
import { ConfigurationsService } from '../../services/configurations/configurations.service'
import { CompetitionsService } from '../../services/competitions/competitions.service'
import { Facebook, FacebookLoginResponse } from '@ionic-native/facebook/ngx';
import { Router} from '@angular/router';
import { LoadingService } from '../../services/loading/loading.service'
import { Location } from '@angular/common';

@Component({
  selector: 'app-configurations',
  templateUrl: './configurations.page.html',
  styleUrls: ['./configurations.page.scss'],
})
export class ConfigurationsPage implements OnInit, OnDestroy {
responseInfo: any;
clientId = localStorage.getItem('id_client');
mydisabled = true;
nickname : string;
data : any;
infoFootball : any;
leagues : any;
tab = 0;
dataTeams : any;
teams : any;
result : any;
user_team : any;
team_logo1 : any;
team_logo2 : any;
team_logo3 : any;
id_team1 : any;
id_team2 : any;
id_team3 : any;
name_team1 : any;
name_team2 : any; 
name_team3 : any;
login : any;
infoDown : any;
isLoggedIn = false;
disabled_invited = false;
users = { id: '', name: '', email: '', picture: { data: { url: '' } } };
id_league : any;

  constructor(
    private http: HttpClient, private client : ClientService, private config : ConfigurationsService,
    private competitions : CompetitionsService, private fb: Facebook, private  router: Router, 
    private loading: LoadingService, private ionRouter: Location) { 
	    this.fb.getLoginStatus()
	      .then(res => {
	      	console.log(res)
	        console.log(res.status);
	        if (res.status == 'connected') {
	          this.isLoggedIn = true;
	          this.getUserDetail(res.authResponse.userID);
	          console.log("está logueado con facebook")
	        } else {
	          this.isLoggedIn = false;
	           console.log("No está logueado con facebook")
	        }
	      })
	      .catch(e => console.log(e));
      }

  ngOnInit() {
    this.http.get(this.client.getClientById(this.clientId))
      .subscribe(resp =>{
        this.data = resp['response']
        this.nickname = this.data.nickname
        this.login = this.data.login
        this.user_team = this.data.push_alerts_teams
          this.user_team.map((value,index) =>{
            if(index === 0){
              this.team_logo1 = value.push_alert.team_logo
              this.id_team1 = value.push_alert.id_ext
              this.name_team1 = value.push_alert.short_name
            }else if(index === 1){
              this.team_logo2 = value.push_alert.team_logo
              this.id_team2 = value.push_alert.id_ext
              this.name_team2 = value.push_alert.short_name
            }else if(index === 2){
              this.team_logo3 = value.push_alert.team_logo
              this.id_team3 = value.push_alert.id_ext
              this.name_team3 = value.push_alert.short_name
            }
          })

          if(this.login === "guest"){
            this.loading.presentAlertConfirm(
              'Información de perfil',
              'Selección de nombre de usuario',
              'Por favor ingrese un nombre de usuario para su cuenta',
              [
                {
                  text: 'Seguir',
                  handler: () => {
                    console.log('Confirm Okay');
                  }
                }
              ]
            ) 
          }
      })  
  }

  setDisabled(){
    this.mydisabled = false
    this.disabled_invited = false
  }

  setName(){
    var jData = {
      nickname: this.nickname,
    };

    var url = this.client.update(this.clientId,false);
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
      .subscribe(data => {
        this.responseInfo = data['response'];    
        this.mydisabled = true
        this.disabled_invited = true
      }, error => {
        console.log(error);
      });

    
  }


  prevPage(){
    this.tab --
  }


  selectLeagues(){
    this.tab ++
    this.http.get(this.competitions.getCompetitionsFootball())
      .toPromise()
      .then(resp =>{
        this.infoFootball = resp['response']
        this.leagues = this.infoFootball.competitions
    })
  }
  
  selectTeam(id_competition,i){
    this.tab++
    this.id_league = this.leagues[i].id_competitions
    console.log(this.id_league)
    this.http.get(this.competitions.getAllTeamByCompetition(id_competition))
      .toPromise()
      .then(resp =>{
        this.dataTeams = resp['response']
        this.teams = this.dataTeams.teams
    })
  }

  saveTeam(index, value){


    this.http.get(this.client.getClientById(this.clientId))
      .subscribe(resp =>{
        this.data = resp['response']
        console.log(this.data)
        this.nickname = this.data.nickname
        this.user_team = this.data.push_alerts_teams
   

        /**********  Save team and dhow in the buttoms  **********/
        var dataArray = []
        var add_push_alert = this.user_team
        dataArray.push(value.id_teams)
          if(this.user_team.length === 0){
            this.team_logo1 = value.team_logo
            this.id_team1 = value.id_teams
            this.name_team1 = value.short_name
          }else if(this.user_team.length === 1){
            this.team_logo2 = value.team_logo
            this.id_team2 = value.id_teams
            this.name_team2 = value.short_name
          }else if(this.user_team.length === 2){
            this.team_logo3 = value.team_logo
            this.id_team3 = value.id_teams
            this.name_team3 = value.short_name
          }

          var jData = {
              add_push_alert : dataArray
          };

          var url = this.client.update(this.clientId,false);

          var headers = new HttpHeaders();
          headers.append("Accept", 'application/json');
          headers.append('Content-Type', 'application/json' );
          const httpOptions = {
            headers: new HttpHeaders({
            'Content-Type':  'application/json'
          })
          };
          this.http.post(url, jData, httpOptions)
            .subscribe(data => {
              this.result = data['response'];    
            }, error => {
              console.log(error);
            });
          this.tab = 0 
          })
  }

 

  removeTeam(id_team){
    var i = 0
    /***********  Declarations of variables for remove teams of the list **********/
    var dataArray = []
    var remove_push_alert = this.user_team
    dataArray.push(id_team)
    var jData = {
        remove_push_alert : dataArray
    };

    var url = this.client.update(this.clientId,false);
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
      .subscribe(data => {
        this.result = data['response'];  

    /********** Get infoClient for consult teams add**********/

          this.http.get(this.client.getClientById(this.clientId))
          .subscribe(resp =>{
          this.data = resp['response']
          this.nickname = this.data.nickname
          this.user_team = this.data.push_alerts_teams
          if(this.user_team !== null && this.user_team.length === 1){
            this.team_logo1 = this.user_team[0].push_alert.team_logo
            this.id_team1 = this.user_team[0].push_alert.id_ext
            this.name_team1 = this.user_team[0].push_alert.short_name
            this.team_logo2 = undefined
            this.id_team2 = undefined
            this.name_team2 = undefined
            this.team_logo3 = undefined
            this.id_team3 = undefined
            this.name_team3 = undefined
          }else if(this.user_team !== null && this.user_team.length === 2){
            this.team_logo1 = this.user_team[0].push_alert.team_logo
            this.id_team1 = this.user_team[0].push_alert.id_ext
            this.name_team1 = this.user_team[0].push_alert.short_name
            this.team_logo2 = this.user_team[1].push_alert.team_logo
            this.id_team2 = this.user_team[1].push_alert.id_ext
            this.name_team2 = this.user_team[1].push_alert.short_name
            this.team_logo3 = undefined
            this.id_team3 = undefined
            this.name_team3 = undefined
          } else {
            this.team_logo1 = undefined
            this.id_team1 = undefined
            this.name_team1 = undefined
          }
        })

      }, error => {
        console.log(error);
      });      
    this.tab = 0
  }  

  fbLogin() {
    this.fb.login(['public_profile', 'user_friends', 'email'])
      .then(res => {
        if (res.status === 'connected') {
          this.isLoggedIn = true;
          this.getUserDetail(res.authResponse.userID);
        } else {
          this.isLoggedIn = false;
        }
      })
      .catch(e => console.log('Error logging into Facebook', e));
     
  }

  getUserDetail(userid: any) {
    this.fb.api('/' + userid + '/?fields=id,email,name,picture', ['public_profile'])
      .then(res => {
        this.users = res;
        console.log(this.users);
      })
      .catch(e => {
        console.log(e);
      });
  }

  fbLogout() {
  this.fb.logout()
    .then( res => {
      this.isLoggedIn = false   
    } )
    .catch(e => console.log('Error logout from Facebook', e));
  }

  downClient(){
    this.http.get(this.client.downClient(this.login))
      .subscribe(resp =>{
        this.infoDown = resp['response']
        this.router.navigateByUrl('/'); 
    })  
  }

  nextPagePredictions(){
    if(this.nickname === null || this.nickname === undefined){
     this.loading.presentAlertConfirm(
      'Información de perfil',
      'Selección de nombre de usuario',
      'Por favor ingrese un nombre de usuario para su cuenta',
      [
        {
          text: 'Seguir',
          handler: () => {
            console.log('Confirm Okay');
          }
        }
      ]
    ) 
    }else{
      this.router.navigateByUrl('/tabs/predictions'); 
    }
  }

  ngOnDestroy() {
    console.log("Exit")
  }
  
  goBack(){
    this.ionRouter.back()
  }

}
