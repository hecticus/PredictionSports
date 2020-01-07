import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClientService } from '../../services/client/client.service';
import { CompetitionsService } from '../../services/competitions/competitions.service';
import { LoadingService } from '../../services/loading/loading.service'

@Component({
  selector: 'app-my-points',
  templateUrl: './my-points.page.html',
  styleUrls: ['./my-points.page.scss'],
})
export class MyPointsPage implements OnInit, OnDestroy {

clientId = localStorage.getItem('id_client');
leaderboard : any;
data : any;
info : any;
allCompetitions : any;
dataBaseball : any;
responseBaseball : any;
totalPoints = 0;
infoClient : any;
nickname : any;
sizeData : any;

  constructor(private http: HttpClient, private client : ClientService, private competitions : CompetitionsService, private loading : LoadingService) { }

  ngOnInit() {

  /**********  GetInfoClient **********/
   this.loading.presentLoading()
   this.http.get(this.client.getClientById(this.clientId))
      .subscribe(resp =>{
        this.infoClient = resp['response']
        this.nickname = this.infoClient.nickname
    })

  /***********  Get the points List ***********/
  	this.http.get(this.client.getPoints(this.clientId))
      .toPromise()
      .then((resp) => { 
      this.data = resp['response']
      this.leaderboard = this.data.leaderboard
      this.sizeData = this.leaderboard.length
        
      /***********  Get the points of baseball league ***********/
      this.http.get(this.competitions.getCompetitionsBaseball())
        .toPromise()
        .then((respBase) => { 
          this.responseBaseball = respBase['response']
          this.dataBaseball = this.responseBaseball.competitions
          this.leaderboard.map((score) => {
            this.dataBaseball.some((competition) =>{
                if(competition.match !== undefined && competition.id_competitions === score.id_tournament){
                    score.name = competition.competiton_type.name;
                    score.logo = competition.competiton_type.competition_logo;
                    score.show = competition.show
                    score.status = competition.status
                    this.totalPoints += score.score;
                    return true;
                }
            });
          });
      }) 
      /***********  Get the points of football league ***********/
      this.http.get(this.competitions.getCompetitionsFootball())
        .toPromise()
        .then((response) => { 
            this.info = response['response']
            this.allCompetitions = this.info.competitions
                this.leaderboard.map((score) => {
                  this.allCompetitions.some((competition) =>{
                      if(competition.id_competitions === score.id_tournament){
                          score.name = competition.competiton_type.name;
                          score.logo = competition.competiton_type.competition_logo;
                          score.show = competition.show
                          this.totalPoints += score.score;
                          return true;
                      }
                  });
                });  
       })  
   }) 
  }

  ngOnDestroy() {
    console.log("Exit")
  }

}
