import { Component, OnInit, OnDestroy } from "@angular/core";
import { Observable } from "rxjs";
import { ClientService } from "../../services/client/client.service";
import { PredictionsService } from "../../services/predictions/predictions.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import * as moment from "moment";
import { Router } from "@angular/router";
import { LoadingService } from "../../services/loading/loading.service";
import { Location } from "@angular/common";

@Component({
  selector: "app-predictions",
  templateUrl: "./predictions.page.html",
  styleUrls: ["./predictions.page.scss"]
})
export class PredictionsPage implements OnInit, OnDestroy {
  dataBets: any;
  clientId = localStorage.getItem("id_client");
  information: any;
  date: any;
  points: any;
  hour: any;
  competitionsFootball: any;
  competitionsBaseball: any;
  betsCompetitions: any;
  data: any;
  nickname: any;
  tab = 0;
  matchs: any;
  bets: any;
  league: any;
  name_league: any;
  fixtures: any;
  index: any;
  info: any;
  id_competition: any;
  mycolorHome = "#FED100";
  data_league: any;
  tournament: any;
  status_color1 = "#db7f1c";
  status_color2 = "#92d822";
  status_color3 = "#061259";
  itemSelected: any;
  dateMatch: any;
  infoTeam: any;
  teamSelected: any;
  sport_id: any;
  sizeData: any;
  loginUser: any;
  statusUser: any;
  id_tournament: any;
  countPrediction: any;
  backButtonSubscription: any;

  constructor(
    private client: ClientService,
    private predictions: PredictionsService,
    private http: HttpClient,
    private router: Router,
    private loading: LoadingService,
    private ionRouter: Location
  ) {}

  ngOnInit() {
    this.loading.presentLoading();
    /********** Get Data of client **********/
    this.http.get(this.client.getClientById(this.clientId)).subscribe(resp => {
      this.data = resp["response"];
      this.loginUser = this.data.login;
      this.statusUser = this.data.status;
      this.nickname = this.data.nickname;
    });

    /********** Get list of leagues **********/

    this.http
      .get(this.predictions.competitionsPrediction(this.clientId))
      .toPromise()
      .then(resp => {
        this.information = resp["response"];
        this.date = this.information.ddate;
        this.points = this.information.points;
        this.hour = this.information.dhour;
        this.competitionsFootball = this.information.competitions.competitions_football;
        this.competitionsBaseball = this.information.competitions.competitions_baseball;
        this.competitionsBaseball.map(competition => {
          this.competitionsFootball.push(competition);
        });
        this.info = {};
      });
  }

  dateMatchsLeagues(date) {
    if (date === undefined || date === null) {
      return "Temporada Finalizada";
    } else {
      moment.locale("es");
      return moment(date, "YYYY/MM/DD HH:mm:ss")
        .endOf(date)
        .fromNow();
    }
  }

  openBetsMatch(id_competition, sport_id, i) {
    this.index = i;
    let separator = "2019/2020";
    this.loading.presentLoading();
    this.tab++;
    this.sport_id = sport_id;
    this.id_tournament = id_competition;

    /********** Se valida si es liga de baseball o football **********/

    if (sport_id === 1) {
      this.http
        .get(this.predictions.getBetsById(id_competition, this.clientId))
        .subscribe(resp => {
          this.dataBets = resp["response"];
          this.tournament = this.dataBets.id_competitions;
          this.matchs = this.dataBets.total_bets;
          this.bets = this.dataBets.client_bets;
          this.data_league = this.dataBets.name;
          this.league = this.data_league.split(separator, 1);
          this.fixtures = this.dataBets.fixtures;
          this.sizeData = this.fixtures.length;
        });
      if (this.competitionsFootball[this.index + 1].show !== 1) {
        this.info = undefined;
      }
    } else {
      this.http
        .get(this.predictions.getBaseballPhase(id_competition, this.clientId))
        .subscribe(resp => {
          this.dataBets = resp["response"];
          this.tournament = this.dataBets.id_competition;
          this.matchs = this.dataBets.total_bets;
          this.bets = this.dataBets.client_bets;
          this.data_league = this.dataBets.name;
          this.league = this.data_league.split(separator, 1);
          this.fixtures = this.dataBets.fixtures;
          this.sizeData = this.fixtures.length;
        });
      if (
        this.competitionsFootball[this.index + 1].show !== 1 ||
        this.competitionsFootball[this.index + 1].orderby !== 0
      ) {
        this.info = undefined;
      }
    }
  }

  nextPage() {
    console.log(this.competitionsFootball);
    this.tab++;
    this.index++;
    this.info = this.competitionsFootball[this.index];
    this.sport_id = this.info.sport_id;
    if (this.sport_id === 1 && this.info.show === 1) {
      this.id_competition = this.info.id_competitions;
      this.openBetsMatch(this.id_competition, this.sport_id, this.index);
      if (
        this.competitionsFootball[this.index + 1].sport_id === 1 &&
        this.competitionsFootball[this.index + 1].show !== 1
      ) {
        this.info = undefined;
      }
    } else {
      this.id_competition = this.info.id_competitions;
      this.openBetsMatch(this.id_competition, this.sport_id, this.index);
      if (
        this.competitionsFootball[this.index + 1].sport_id !== 1 &&
        (this.competitionsFootball[this.index + 1].show !== 1 ||
          this.competitionsFootball[this.index + 1].orderby !== 0)
      ) {
        this.info = undefined;
      }
    }
  }

  prevPage() {
    this.tab--;
    this.index--;
    this.info = this.competitionsFootball[this.index];
    this.sport_id = this.info.sport_id;
    if (this.sport_id === 1 && this.info.show === 1) {
      this.id_competition = this.info.id_competitions;
      this.openBetsMatch(this.id_competition, this.sport_id, this.index);
    } else if (
      this.sport_id !== 1 &&
      this.info.show === 1 &&
      this.info.orderby !== 99
    ) {
      this.id_competition = this.info.id_competitions;
      this.openBetsMatch(this.id_competition, this.sport_id, this.index);
    } else {
      this.info === undefined;
    }

    if (this.tab === 0) {
      this.info === undefined;
    }
  }

  mainPage() {
    this.tab = 0;
    this.ngOnInit();
  }

  matchDate(date) {
    moment.locale("es");
    return moment(date).format("DD MMM YYYY");
  }

  compareDate(date) {
    moment.locale("es");
    return moment(date, "YYYY/MM/DD HH:mm:ss").format("YYYY/MM/DD HH:mm:ss");
  }

  matchTime(date) {
    moment.locale("es");
    return moment(date, "YYYY/MM/DD HH:mm:ss").format("HH:mm");
  }

  macthStatus(status) {
    if (status === 1) {
      return "Jugado";
    } else if (status === 2) {
      return "Jugando";
    } else {
      return "Programado";
    }
  }

  timeNow() {
    moment.locale("es");
    return moment().format("YYYY/MM/DD HH:mm:ss");
  }

  selectedPredictionHome(id_match, index, status_match, date) {
    if (this.timeNow() > this.compareDate(date)) {
      this.loading.presentAlertConfirm(
        "Predicciones",
        "Fecha desafada",
        "La fecha del partido está desafada",
        [
          {
            text: "Seguir",
            handler: () => {
              console.log("Confirm Okay");
            }
          }
        ]
      );
    } else {
      if (status_match === 3) {
        if (this.sport_id !== 2) {
          var headers = new HttpHeaders();
          headers.append("Accept", "application/json");
          headers.append("Content-Type", "application/json");

          const httpOptions = {
            headers: new HttpHeaders({
              "Content-Type": "application/json"
            })
          };

          var data = {
            bet: {
              client_bet: 0,
              id_game_match: id_match,
              id_tournament: this.tournament
            }
          };

          if (this.loginUser === "guest" && this.statusUser !== 1) {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.countPrediction = resp["description"];
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);

                if (this.countPrediction !== "ok") {
                  this.loading.presentAlertConfirm(
                    "Alert",
                    "",
                    "Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?",
                    [
                      {
                        text: "Cancelar",
                        handler: () => {
                          console.log("exit");
                        }
                      },
                      {
                        text: "Aceptar",
                        handler: () => {
                          this.router.navigateByUrl("/get-password");
                          this.ngOnDestroy();
                        }
                      }
                    ]
                  );
                }
              });
          } else {
            console.log(index);
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);
              });
          }
        } else {
          var headers = new HttpHeaders();
          headers.append("Accept", "application/json");
          headers.append("Content-Type", "application/json");

          const httpOptions = {
            headers: new HttpHeaders({
              "Content-Type": "application/json"
            })
          };

          var data2 = {
            bet: {
              client_bet: 0,
              id_game_match: id_match,
              id_tournament: this.tournament,
              sport_id: this.sport_id
            }
          };

          if (this.loginUser === "guest" && this.statusUser !== 1) {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data2,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.countPrediction = resp["description"];
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);

                if (this.countPrediction !== "ok") {
                  this.loading.presentAlertConfirm(
                    "Alert",
                    "",
                    "Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?",
                    [
                      {
                        text: "Cancelar",
                        handler: () => {
                          console.log("exit");
                        }
                      },
                      {
                        text: "Aceptar",
                        handler: () => {
                          this.router.navigateByUrl("/get-password");
                          this.ngOnDestroy();
                        }
                      }
                    ]
                  );
                }
              });
          } else {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data2,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);
              });
          }
        }
      } else {
        this.loading.presentAlert(
          "No se puede seleccionar predicción,ya el partido ha iniciado !."
        );
      }
    }
  }

  selectedPredictionEqual(id_match, index, status_match, date) {
    if (this.timeNow() > this.compareDate(date)) {
      this.loading.presentAlertConfirm(
        "Predicciones",
        "Fecha desafada",
        "La fecha del partido está desafada",
        [
          {
            text: "Seguir",
            handler: () => {
              console.log("Confirm Okay");
            }
          }
        ]
      );
    } else {
      if (status_match === 3) {
        if (this.sport_id !== 2) {
          var data = {
            bet: {
              client_bet: 1,
              id_game_match: id_match,
              id_tournament: this.tournament
            }
          };

          var headers = new HttpHeaders();
          headers.append("Accept", "application/json");
          headers.append("Content-Type", "application/json");

          const httpOptions = {
            headers: new HttpHeaders({
              "Content-Type": "application/json"
            })
          };

          if (this.loginUser === "guest" && this.statusUser !== 1) {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.countPrediction = resp["description"];
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);

                if (this.countPrediction !== "ok") {
                  this.loading.presentAlertConfirm(
                    "Alert",
                    "",
                    "Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?",
                    [
                      {
                        text: "Cancelar",
                        handler: () => {
                          console.log("exit");
                        }
                      },
                      {
                        text: "Aceptar",
                        handler: () => {
                          this.router.navigateByUrl("/get-password");
                          this.ngOnDestroy();
                        }
                      }
                    ]
                  );
                }
              });
          } else {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);
              });
          }
        } else {
          console.log("No se permite el empate");
        }
      } else {
        this.loading.presentAlert(
          "No se puede seleccionar predicción,ya el partido ha iniciado !."
        );
      }
    }
  }

  selectedPredictionAway(id_match, index, status_match, date) {
    if (this.timeNow() > this.compareDate(date)) {
      this.loading.presentAlertConfirm(
        "Predicciones",
        "Fecha desafada",
        "La fecha del partido está desafada",
        [
          {
            text: "Seguir",
            handler: () => {
              console.log("Confirm Okay");
            }
          }
        ]
      );
    } else {
      if (status_match === 3) {
        if (this.sport_id !== 2) {
          var data = {
            bet: {
              client_bet: 2,
              id_game_match: id_match,
              id_tournament: this.tournament
            }
          };

          var headers = new HttpHeaders();
          headers.append("Accept", "application/json");
          headers.append("Content-Type", "application/json");

          const httpOptions = {
            headers: new HttpHeaders({
              "Content-Type": "application/json"
            })
          };

          if (this.loginUser === "guest" && this.statusUser !== 1) {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.countPrediction = resp["description"];
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);
                if (this.countPrediction !== "ok") {
                  this.loading.presentAlertConfirm(
                    "Alert",
                    "",
                    "Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?",
                    [
                      {
                        text: "Cancelar",
                        handler: () => {
                          console.log("exit");
                        }
                      },
                      {
                        text: "Aceptar",
                        handler: () => {
                          this.router.navigateByUrl("/get-password");
                          this.ngOnDestroy();
                        }
                      }
                    ]
                  );
                }
              });
          } else {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);
              });
          }
        } else {
          var data2 = {
            bet: {
              client_bet: 2,
              id_game_match: id_match,
              id_tournament: this.tournament,
              sport_id: this.sport_id
            }
          };

          var headers = new HttpHeaders();
          headers.append("Accept", "application/json");
          headers.append("Content-Type", "application/json");

          const httpOptions = {
            headers: new HttpHeaders({
              "Content-Type": "application/json"
            })
          };

          if (this.loginUser === "guest" && this.statusUser !== 1) {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data2,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.countPrediction = resp["description"];
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);

                if (this.countPrediction !== "ok") {
                  this.loading.presentAlertConfirm(
                    "Alert",
                    "",
                    "Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?",
                    [
                      {
                        text: "Cancelar",
                        handler: () => {
                          console.log("exit");
                          this.ngOnDestroy();
                        }
                      },
                      {
                        text: "Aceptar",
                        handler: () => {
                          this.router.navigateByUrl("/get-password");
                          this.ngOnDestroy();
                        }
                      }
                    ]
                  );
                }
              });
          } else {
            this.http
              .post(
                this.predictions.createBets(this.clientId),
                data2,
                httpOptions
              )
              .toPromise()
              .then(resp => {
                this.infoTeam = resp["response"];
                this.teamSelected = this.infoTeam.status;
                this.openBetsMatch(this.tournament, this.sport_id, this.index);
              });
          }
        }
      } else {
        this.loading.presentAlert(
          "No se puede seleccionar predicción,ya el partido ha iniciado !."
        );
      }
    }
  }

  ngOnDestroy() {
    console.log("Exit");
  }
}
