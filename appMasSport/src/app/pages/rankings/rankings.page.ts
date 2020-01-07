import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CompetitionsService } from '../../services/competitions/competitions.service';
import { LoadingService } from '../../services/loading/loading.service'

@Component({
  selector: 'app-rankings',
  templateUrl: './rankings.page.html',
  styleUrls: ['./rankings.page.scss'],
})
export class RankingsPage implements OnInit, OnDestroy {

	data : any;
	ranking : any;
	clientId = localStorage.getItem('id_client');
	info : any;
	dataFootball : any;
  infoClient: any;
  date: any;
  score: any;
  index: any;
  leagues : any;
  league : any;
  count =0;
  countMax =0;
  matchs : any;
  tab = -1;
  name_league : any;
  id_competition : any;
  myColor ="danger-bold"
  myColor2 ="#C4171D"
  nickname : any;
  dataClient : any;
  positionClient: any;

  constructor(private http : HttpClient, private competitions : CompetitionsService, private loading : LoadingService) {  }

  ngOnInit() {
  	this.http.get(this.competitions.totalRanking(this.clientId))
  		.toPromise()
  		.then(resp =>{
        this.data = resp['response']
  			this.ranking = this.data.leaderboard
        this.infoClient = this.data.client
        this.date = this.infoClient.ddate
        this.score = this.infoClient.score
        this.index = this.infoClient.index
        this.nickname = this.infoClient.client
  	})

  	this.http.get(this.competitions.getCompetitionsFootball())
  		.toPromise()
  		.then( info => {
  			this.info = info['response']
  			this.dataFootball = this.info.competitions
        this.countMax =this.dataFootball.length
  		}) 

	}

  nextPage(){
    this.myColor="danger-bold"
    this.myColor2 ="#C4171D"
    this.tab++
    if(this.count < this.dataFootball.length){
      this.id_competition = this.dataFootball[this.count].id_competitions
      this.loading.presentLoading()
      this.http.get(this.competitions.getLeagues(this.clientId , this.id_competition))
        .toPromise()
        .then( response => {
          this.leagues = response['response']
          this.dataClient = this.leagues.client
          this.positionClient = this.dataClient.index
          this.score = this.dataClient.score
          this.nickname = this.dataClient.client
          this.matchs = this.leagues.leaderboard
          this.matchs.map((score) => {
            this.dataFootball.some((competition) =>{
                if(competition.id_competitions === this.id_competition){
                    score.name_competition = competition.competiton_type.name;
                    this.name_league = competition.competiton_type.name;
                    return true;
                }
            });
          });
        })
      this.count++  
    }
  }

  prevPage(){
    this.myColor="danger-bold"
    this.myColor2 ="#C4171D"
      this.count--
      this.tab--
      this.loading.presentLoading()
      if(this.count ===0){
        if(this.dataFootball.length >= this.count){
          this.id_competition = this.dataFootball[this.count].id_competitions
          this.http.get(this.competitions.getLeagues(this.clientId , this.id_competition))
            .toPromise()
            .then( response => {
              this.leagues = response['response']
              this.matchs = this.leagues.leaderboard
              this.matchs.map((score) => {
                this.dataFootball.some((competition) =>{
                  if(competition.id_competitions === this.id_competition){
                      score.name_competition = competition.competiton_type.name;
                      this.name_league = competition.competiton_type.name;
                      return true;
                  }
                });
              });
          })
        }   
      }else{
         if(this.dataFootball.length >= this.count){
        this.id_competition = this.dataFootball[this.count-1].id_competitions
        this.http.get(this.competitions.getLeagues(this.clientId , this.id_competition))
          .toPromise()
          .then( response => {
            this.leagues = response['response']
            this.dataClient = this.leagues.client
            this.positionClient = this.dataClient.index
            this.score = this.dataClient.score
            this.nickname = this.dataClient.client
            this.matchs = this.leagues.leaderboard
            this.matchs.map((score) => {
              this.dataFootball.some((competition) =>{
                if(competition.id_competitions === this.id_competition){
                    score.name_competition = competition.competiton_type.name;
                    this.name_league = competition.competiton_type.name;
                    return true;
                }
              });
            });
        })
      }   
    }
  }

  tournament(){
    this.myColor2="#C4171D"
    this.myColor="danger-bold"
    this.id_competition = this.dataFootball[this.count-1].id_competitions
   this.http.get(this.competitions.getLeagues(this.clientId , this.id_competition))
      .toPromise()
      .then( response => {
        this.leagues = response['response']
        this.matchs = this.leagues.leaderboard
      })
  }

  getPhase() {
    this.myColor="#C4171D"
    this.myColor2="danger-bold"
    let phase = 0
    this.id_competition = this.dataFootball[this.count-1].id_competitions
    this.http.get(this.competitions.getPhase(this.clientId , this.id_competition, phase))
      .toPromise()
      .then( response => {
        this.leagues = response['response']
        this.matchs = this.leagues.leaderboard
      })
  }


  ngOnDestroy() {
    console.log("Exit")
  }

}
