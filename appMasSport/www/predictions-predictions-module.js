(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["predictions-predictions-module"],{

/***/ "./src/app/pages/predictions/predictions.module.ts":
/*!*********************************************************!*\
  !*** ./src/app/pages/predictions/predictions.module.ts ***!
  \*********************************************************/
/*! exports provided: PredictionsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PredictionsPageModule", function() { return PredictionsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _predictions_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./predictions.page */ "./src/app/pages/predictions/predictions.page.ts");







var routes = [
    {
        path: '',
        component: _predictions_page__WEBPACK_IMPORTED_MODULE_6__["PredictionsPage"]
    }
];
var PredictionsPageModule = /** @class */ (function () {
    function PredictionsPageModule() {
    }
    PredictionsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
            ],
            declarations: [_predictions_page__WEBPACK_IMPORTED_MODULE_6__["PredictionsPage"]]
        })
    ], PredictionsPageModule);
    return PredictionsPageModule;
}());



/***/ }),

/***/ "./src/app/pages/predictions/predictions.page.html":
/*!*********************************************************!*\
  !*** ./src/app/pages/predictions/predictions.page.html ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\" *ngIf=\"tab === 0 \">\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\n    </ion-buttons>\n    <ion-buttons slot=\"start\" *ngIf=\"tab !== 0 \">\n      <ion-icon name=\"arrow-round-back\" color=\"primary\" class=\"arrow-back\" size=\"large\" (click)=\"mainPage()\"></ion-icon>\n    </ion-buttons>\n    <div class=\"center\">\n      <img src=\"assets/img/logo.png\" height=\"30\" />\n    </div>\n  </ion-toolbar>\n</ion-header>\n<ion-content *ngIf=\"tab === 0\">\n  <div class=\"background\">\n    <ion-label class=\"white margin-top-m\"></ion-label>\n    <div style=\"position:absolute; top: 95px; background: #ffffff; width: 100%; height:2px;\"></div>\n    <ion-label class=\"white margin-top-m text-size-g\">{{nickname}}</ion-label>\n    <div class=\"circle-red\">\n      <div class=\"white margin-top-m\">\n        <ion-label class=\"white margin-top-m text-size-m\">{{points}}</ion-label>\n        <ion-label class=\"white margin-top-s text-size-s\">{{date}}</ion-label>\n        <ion-label class=\"white margin-top-s text-size-s\">{{hour}}</ion-label>\n      </div>\n    </div>\n  </div>\n  <ion-item color=\"danger\">\n    <ion-grid>\n      <ion-row>\n        <ion-col size=\"6\">\n          <ion-label class=\"text-size-m\">Torneo</ion-label>\n        </ion-col>\n        <ion-col size=\"6\">\n          <ion-label class=\"text-size-m white-right\">Próximo Juego</ion-label>\n        </ion-col>\n      </ion-row>\n    </ion-grid>\n  </ion-item>\n  <ion-list *ngFor=\"let data of competitionsFootball; let i= index\">\n    <ion-item *ngIf=\"data.show === 1 && data.orderby !== 99\"\n      (click)=\"openBetsMatch(data.id_competitions, data.sport_id, i)\">\n      <ion-grid>\n        <ion-row>\n          <ion-col size=\"2\">\n            <img class=\"img-size-s\" src=\"{{data.competiton_type.competition_logo}}.svg\">\n          </ion-col>\n          <ion-col size=\"5\">\n            <div class=\"div\">\n              <ion-label class=\"text-size-s\">{{data.competiton_type.name}}</ion-label>\n            </div>\n          </ion-col>\n          <ion-col size=\"5\">\n            <div *ngIf=\"data.match !== null\" class=\"div right div-width\">\n              <span class=\"text-size-r right\" [innerHTML]=\"dateMatchsLeagues(data.match.date)\"></span>\n              <ion-icon name=\"arrow-dropright\" color=\"primary\"></ion-icon>\n            </div>\n            <div *ngIf=\"data.match === null\" class=\"div right div-width\">\n              <span class=\"text-size-r right\">Temporada finalizada</span>\n              <ion-icon name=\"arrow-dropright\" color=\"primary\"></ion-icon>\n            </div>\n          </ion-col>\n        </ion-row>\n      </ion-grid>\n    </ion-item>\n  </ion-list>\n</ion-content>\n<ion-content *ngIf=\"tab !== 0\">\n  <ion-item color=\"new\">\n    <ion-icon name=\"arrow-round-back\" class=\"arrow-back\" size=\"large\" (click)=\"prevPage()\" *ngIf=\"index !== 0\">\n    </ion-icon>\n    <ion-label class=\"text-size-g\">{{league}} </ion-label>\n    <ion-icon name=\"arrow-round-forward\" class=\"arrow-next\" size=\"large\" (click)=\"nextPage()\"\n      *ngIf=\"index >= 0 && info !== undefined \"></ion-icon>\n  </ion-item>\n  <ion-item color=\"danger\">\n    <ion-label class=\"text-size-m\">{{bets}} Predicciones / {{matchs}} Partidos</ion-label>\n  </ion-item>\n  <ion-list class=\"prediction\" *ngFor=\"let item of fixtures\">\n    <ion-item color=\"danger-primary\">\n      <div class=\"center-text\">\n        <span class=\"text-size-m\" [innerHTML]=\"matchDate(item.date)\"></span>\n      </div>\n    </ion-item>\n    <ion-list class=\"prediction-top\" *ngFor=\"let data of item.matches; let i= index\">\n      <ion-item class=\"item-grid\" *ngIf=\"sport_id === 1\" lines=\"full\">\n        <ion-grid class=\"padding\">\n          <ion-row>\n            <ion-col class=\"padding-col\" size=\"4\"\n              (click)=\"selectedPredictionHome(data.id_game_matches,i,data.status.id_status,data.date)\">\n              <div class=\"center-text-selected\" *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.home_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.home_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.home_team.short_name}}</ion-label>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.home_team.short_name}}</ion-label>\n              </div>\n            </ion-col>\n            <ion-col class=\"padding-col\" size=\"4\"\n              (click)=\"selectedPredictionEqual(data.id_game_matches,i,data.status.id_status,data.date)\">\n              <div class=\"center-text-selected\" *ngIf=\"data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 1 ? mycolorHome : ''\">\n                <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">\n                  {{data.home_team_goals}} - {{data.away_team_goals}}\n                </ion-label>\n                <div class=\"center\">\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\n                </div>\n                <div *ngIf=\"data.status.id_status === 1\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 2\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 3\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data.bet === undefined\">\n                <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">\n                  {{data.home_team_goals}} - {{data.away_team_goals}}\n                </ion-label>\n                <div class=\"center\">\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\n                </div>\n                <div *ngIf=\"data.status.id_status === 1\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 2\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 3\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n              </div>\n            </ion-col>\n            <ion-col class=\"padding-col\" size=\"4\"\n              (click)=\"selectedPredictionAway(data.id_game_matches,i,data.status.id_status,data.date)\">\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.away_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.away_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.away_team.short_name}}</ion-label>\n              </div>\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.away_team.short_name}}</ion-label>\n              </div>\n            </ion-col>\n          </ion-row>\n        </ion-grid>\n      </ion-item>\n      <ion-item class=\"item-grid\" *ngIf=\"sport_id !== 1\">\n        <ion-grid class=\"padding\">\n          <ion-row>\n            <ion-col class=\"padding-col\" size=\"4\"\n              (click)=\"selectedPredictionHome(data.id_game,i,data.status.id_status,data.date)\">\n              <div class=\"center-text-selected\" *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.home_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.home_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.home_team.short_name}}</ion-label>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.home_team.short_name}}</ion-label>\n              </div>\n            </ion-col>\n            <ion-col class=\"padding-col\" size=\"4\">\n              <div class=\"center-text-selected\" *ngIf=\"data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 1 ? mycolorHome : ''\">\n                <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">\n                  {{data.home_team_goals}} - {{data.away_team_goals}}\n                </ion-label>\n                <div class=\"center\">\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\n                </div>\n                <div *ngIf=\"data.status.id_status === 1\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 2\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 3\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n              </div>\n              <div class=\"center-text-selected\" *ngIf=\"data.bet === undefined\">\n                <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">\n                  {{data.home_team_goals}} - {{data.away_team_goals}}\n                </ion-label>\n                <div class=\"center\">\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\n                </div>\n                <div *ngIf=\"data.status.id_status === 1\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 2\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                <div *ngIf=\"data.status.id_status === 3\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\n                    <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\n                  </ion-label>\n                </div>\n              </div>\n            </ion-col>\n            <ion-col class=\"padding-col\" size=\"4\"\n              (click)=\"selectedPredictionAway(data.id_game,i,data.status.id_status,data.date)\">\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.away_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\n                <ion-label class=\"text-size-m-color\">\n                  {{data.away_team.short_name}}\n                </ion-label>\n              </div>\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\n                [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.away_team.short_name}}</ion-label>\n              </div>\n              <div class=\"center-text-selected\"\n                *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\n                <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\n                <ion-label class=\"text-size-m-color\">{{data.away_team.short_name}}</ion-label>\n              </div>\n            </ion-col>\n          </ion-row>\n        </ion-grid>\n      </ion-item>\n    </ion-list>\n  </ion-list>\n  <ion-content *ngIf=\"sizeData === 0\">\n    <div class=\"div-alert\">\n      <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\n    </div>\n    <div class=\"div-alert\">\n      Todavía no has hecho predicciones o no se han Jugado los partidos.\n    </div>\n  </ion-content>\n</ion-content>"

/***/ }),

/***/ "./src/app/pages/predictions/predictions.page.scss":
/*!*********************************************************!*\
  !*** ./src/app/pages/predictions/predictions.page.scss ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  size: 0.3em; }\n\n.text-size-r {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px;\n  margin-top: 1rem; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  height: 100%;\n  border: none; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  size: 0.8em;\n  text-align: center; }\n\n.text-size-m-color {\n  font-size: 13px;\n  color: #061259;\n  text-align: center; }\n\n.text-size-g-color {\n  font-size: 18px;\n  color: #061259;\n  text-align: center; }\n\n.text-size-status {\n  font-size: 13px; }\n\n.text-size-g {\n  font-size: 20px;\n  text-align: center; }\n\n.center {\n  margin: 0 auto;\n  padding-top: 20px;\n  text-align: center; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.arrow-next {\n  float: right;\n  margin-right: 10px; }\n\n.arrow-back {\n  float: left;\n  margin-left: 10px; }\n\n.center-text {\n  text-align: center;\n  width: 100%; }\n\n.icon-alert {\n  margin-top: 15px;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n.div-alert {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n.prediction {\n  padding-top: 0rem; }\n\n.prediction-top {\n  padding-top: 0rem;\n  padding-bottom: 0rem;\n  padding-left: 0rem;\n  padding-right: 0rem;\n  margin-top: 0rem;\n  margin-bottom: 0rem; }\n\n.padding {\n  padding-top: 0rem;\n  padding-bottom: 0rem;\n  padding-left: 0rem;\n  padding-right: -10px;\n  margin-right: -10px;\n  margin-left: 0rem;\n  margin-bottom: 0rem;\n  margin-top: 0rem; }\n\n.padding-col {\n  padding-top: 0rem;\n  padding-bottom: 0erm;\n  padding-left: 0erm;\n  padding-right: 0erm;\n  margin-right: -10px;\n  margin-left: 0rem;\n  margin-bottom: -5px;\n  margin-top: 0erm; }\n\n.center-text-selected {\n  text-align: center;\n  width: 100%;\n  height: 100%;\n  margin-left: 0rem;\n  margin-right: 0rem; }\n\n.item-grid {\n  margin-left: -20px;\n  margin-right: -35px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL2JyYXlhbi9hcHBNYXNTcG9ydC9zcmMvYXBwL3BhZ2VzL3ByZWRpY3Rpb25zL3ByZWRpY3Rpb25zLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGdCQUFnQjtFQUNoQiwwQkFBc0I7VUFBdEIsc0JBQXNCO0VBQ3RCLGVBQWUsRUFBQTs7QUFFbkI7RUFDSSxZQUFZO0VBQ1osZUFBZTtFQUNmLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixXQUFXLEVBQUE7O0FBRWY7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixjQUFjLEVBQUE7O0FBRWxCO0VBQ0ksZUFBZSxFQUFBOztBQUduQjtFQUNJLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxXQUFXO0VBQ1gsWUFBWTtFQUNaLGdCQUFnQixFQUFBOztBQUVwQjtFQUNHLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFlBQVk7RUFDWixZQUFZLEVBQUE7O0FBRWY7RUFDSSxXQUFXO0VBQ1gsaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLGtCQUFrQjtFQUNsQixhQUFhO0VBQ2IscUNBQXlEO0VBQ3pELHNCQUFzQjtFQUN0QiwyQkFBMkIsRUFBQTs7QUFFL0I7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxTQUFTLEVBQUE7O0FBRWI7RUFDSSxXQUFXO0VBQ1gsa0JBQWtCLEVBQUE7O0FBRXRCO0VBQ0ksZUFBZTtFQUNmLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFdEI7RUFDSSxlQUFlO0VBQ2YsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUV0QjtFQUNFLGVBQWUsRUFBQTs7QUFFakI7RUFDSSxlQUFlO0VBQ2Ysa0JBQWtCLEVBQUE7O0FBRXRCO0VBQ0UsY0FBYztFQUNkLGlCQUFpQjtFQUNqQixrQkFBa0IsRUFBQTs7QUFFcEI7RUFDSSxrQkFBaUI7RUFDakIsU0FBUztFQUNULE9BQU07RUFDTixRQUFRO0VBQ1IsY0FBYTtFQUNiLFlBQVc7RUFDWCxhQUFhO0VBQ2IsaUJBQWlCO0VBQ2pCLG1EQUFpRDtFQUNqRCxrQkFBa0I7RUFDbEIsa0JBQWlCO0VBQ2pCLGNBQW9CO0VBQ3BCLGdCQUFlO0VBQ2YseUJBQXlCO0VBQ3pCLG1CQUFtQjtFQUNuQixpQkFBaUI7RUFDakIscUJBQXFCLEVBQUE7O0FBRXpCO0VBQ0UsWUFBWTtFQUNaLGtCQUFrQixFQUFBOztBQUVwQjtFQUNFLFdBQVc7RUFDWCxpQkFBaUIsRUFBQTs7QUFFbkI7RUFDRSxrQkFBa0I7RUFDbEIsV0FBVyxFQUFBOztBQUVaO0VBQ0MsZ0JBQWdCO0VBQ2hCLFdBQVc7RUFDWCxZQUFZO0VBQ1osZUFBZTtFQUNmLGlCQUFpQjtFQUNqQixjQUFjLEVBQUE7O0FBRWhCO0VBQ0csYUFBYTtFQUNiLHVCQUF1QjtFQUN2QixtQkFBbUI7RUFDbkIsV0FBVztFQUNYLFlBQVk7RUFDWixlQUFlO0VBQ2YsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUVyQjtFQUNJLGlCQUFrQixFQUFBOztBQUV0QjtFQUNJLGlCQUFrQjtFQUNsQixvQkFBb0I7RUFDcEIsa0JBQWtCO0VBQ2xCLG1CQUFtQjtFQUNuQixnQkFBZ0I7RUFDaEIsbUJBQW1CLEVBQUE7O0FBRXZCO0VBQ0ksaUJBQWlCO0VBQ2pCLG9CQUFvQjtFQUNwQixrQkFBa0I7RUFDbEIsb0JBQW9CO0VBQ3BCLG1CQUFtQjtFQUNuQixpQkFBbUI7RUFDbkIsbUJBQW1CO0VBQ25CLGdCQUFnQixFQUFBOztBQUVwQjtFQUNJLGlCQUFpQjtFQUNqQixvQkFBb0I7RUFDcEIsa0JBQWtCO0VBQ2xCLG1CQUFtQjtFQUNuQixtQkFBbUI7RUFDbkIsaUJBQWlCO0VBQ2pCLG1CQUFtQjtFQUNuQixnQkFBZ0IsRUFBQTs7QUFFcEI7RUFDSSxrQkFBa0I7RUFDbEIsV0FBVztFQUNYLFlBQVk7RUFDWixpQkFBaUI7RUFDakIsa0JBQWtCLEVBQUE7O0FBRXRCO0VBQ0ksa0JBQWtCO0VBQ2xCLG1CQUFtQixFQUFBIiwiZmlsZSI6InNyYy9hcHAvcGFnZXMvcHJlZGljdGlvbnMvcHJlZGljdGlvbnMucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmJhY2tncm91bmQtcmVkIHtcbiAgICBwYWRkaW5nLXRvcDogNXB4O1xuICAgIHBhZGRpbmctYmxvY2stZW5kOiA1cHg7XG4gICAgYmFja2dyb3VuZDogcmVkO1xufVxuLndoaXRlLXJpZ2h0IHtcbiAgICBjb2xvcjogd2hpdGU7XG4gICAgZm9udC1zaXplOiAxNnB4O1xuICAgIGZsb2F0OiByaWdodDsgXG59XG4udGV4dC1zaXplLXN7XG4gICAgZm9udC1zaXplOiAxM3B4O1xuICAgIHdpZHRoOiAxMDAlO1xuICAgIGZsb2F0OiByaWdodDtcbiAgICBzaXplOiAwLjNlbTtcbn1cbi50ZXh0LXNpemUtcntcbiAgICBmb250LXNpemU6IDEzcHg7XG4gICAgd2lkdGg6IDEwMCU7XG4gICAgZmxvYXQ6IHJpZ2h0O1xuICAgIGNvbG9yOiAjMDM3N0I0O1xufVxuLmlvbi1pY29uIHtcbiAgICBmb250LXNpemU6IDY0cHg7XG4gIH1cblxuLndoaXRlLWxlZnR7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIHBhZGRpbmctbGVmdDogMThweDtcbiAgICBmb250LXNpemU6IDE2cHg7XG59XG4ud2hpdGV7XG4gICAgY29sb3I6IHdoaXRlO1xufVxuLmltZy1zaXplLXN7XG4gICAgd2lkdGg6IDQwcHg7XG4gICAgaGVpZ2h0OiA0MHB4O1xuICAgIG1hcmdpbi10b3A6IDFyZW07XG59XG4uZGl2e1xuICAgZGlzcGxheTogZmxleDtcbiAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xuICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcbiAgIGhlaWdodDogMTAwJTtcbiAgIGJvcmRlcjogbm9uZTtcbn1cbi5kaXYtd2lkdGh7XG4gICAgd2lkdGg6IDEwMCU7XG4gICAgdGV4dC1hbGlnbjogcmlnaHQ7XG59XG4ucmlnaHR7XG4gICAgZmxvYXQ6IHJpZ2h0O1xufVxuLmJhY2tncm91bmR7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xuICAgIGhlaWdodDogMTkwcHg7XG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiLi4vLi4vLi4vYXNzZXRzL2ltZy9iYWNrLXB0cy5wbmdcIik7XG4gICAgYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcbiAgICBiYWNrZ3JvdW5kLXBvc2l0aW9uOiBjZW50ZXI7XG59XG4ubWFyZ2luLXRvcC1te1xuICAgIG1hcmdpbi10b3A6IDAuOWVtO1xufVxuLm1hcmdpbi10b3Atc3tcbiAgICBtYXJnaW4tdG9wOiAwLjVlbTtcbn1cbi5tYXJnaW4tdG9wLXhze1xuICAgIG1hcmdpbi10b3A6IDAuOGVtO1xufVxuLnRleHQtc2l6ZS1se1xuICAgIHNpemU6IDFlbTtcbn1cbi50ZXh0LXNpemUtbXtcbiAgICBzaXplOiAwLjhlbTtcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XG59XG4udGV4dC1zaXplLW0tY29sb3J7XG4gICAgZm9udC1zaXplOiAxM3B4O1xuICAgIGNvbG9yOiAjMDYxMjU5O1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cbi50ZXh0LXNpemUtZy1jb2xvcntcbiAgICBmb250LXNpemU6IDE4cHg7XG4gICAgY29sb3I6ICMwNjEyNTk7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLnRleHQtc2l6ZS1zdGF0dXN7XG4gIGZvbnQtc2l6ZTogMTNweDtcbn1cbi50ZXh0LXNpemUtZ3tcbiAgICBmb250LXNpemU6IDIwcHg7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLmNlbnRlcntcbiAgbWFyZ2luOiAwIGF1dG87XG4gIHBhZGRpbmctdG9wOiAyMHB4O1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG59XG4uY2lyY2xlLXJlZHtcbiAgICBwb3NpdGlvbjphYnNvbHV0ZTtcbiAgICB0b3A6IDMwcHg7XG4gICAgbGVmdDowO1xuICAgIHJpZ2h0OiAwO1xuICAgIGRpc3BsYXk6YmxvY2s7XG4gICAgd2lkdGg6MTMwcHg7XG4gICAgaGVpZ2h0OiAxMzBweDtcbiAgICBsaW5lLWhlaWdodDogMjVweDtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZ2JhKDIyNywzMCw0OCwwLjUpICAhaW1wb3J0YW50O1xuICAgIGJvcmRlci1yYWRpdXM6IDUwJTtcbiAgICB0ZXh0LWFsaWduOmNlbnRlcjtcbiAgICBjb2xvcjogcmdiKDAsNDAsMTIwKTtcbiAgICBmb250LXNpemU6MS44ZW07XG4gICAgbWFyZ2luOiAwIGF1dG8gIWltcG9ydGFudDtcbiAgICBib3JkZXItc3R5bGU6IHNvbGlkO1xuICAgIGJvcmRlci13aWR0aDogMnB4O1xuICAgIGJvcmRlci1jb2xvcjogI2ZmZmZmZjtcbn1cbi5hcnJvdy1uZXh0IHtcbiAgZmxvYXQ6IHJpZ2h0O1xuICBtYXJnaW4tcmlnaHQ6IDEwcHg7XG59XG4uYXJyb3ctYmFjayB7ICAgXG4gIGZsb2F0OiBsZWZ0O1xuICBtYXJnaW4tbGVmdDogMTBweDtcbn1cbi5jZW50ZXItdGV4dHtcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xuICB3aWR0aDogMTAwJTtcbiB9XG4gLmljb24tYWxlcnR7XG4gIG1hcmdpbi10b3A6IDE1cHg7XG4gIHdpZHRoOiA2MHB4O1xuICBoZWlnaHQ6IDYwcHg7XG4gIGZvbnQtc2l6ZTogMzVweDtcbiAgZm9udC13ZWlnaHQ6IGJvbGQ7XG4gIGNvbG9yOiAjNzc3Nzc3O1xufVxuLmRpdi1hbGVydHtcbiAgIGRpc3BsYXk6IGZsZXg7XG4gICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcbiAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XG4gICB3aWR0aDogMTAwJTtcbiAgIGJvcmRlcjogbm9uZTtcbiAgIGZvbnQtc2l6ZTogMzBweDtcbiAgIGNvbG9yOiAjNzc3Nzc3O1xuICAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLnByZWRpY3Rpb24ge1xuICAgIHBhZGRpbmctdG9wOiAwcmVtIDtcbn1cbi5wcmVkaWN0aW9uLXRvcCB7XG4gICAgcGFkZGluZy10b3A6IDByZW0gO1xuICAgIHBhZGRpbmctYm90dG9tOiAwcmVtO1xuICAgIHBhZGRpbmctbGVmdDogMHJlbTtcbiAgICBwYWRkaW5nLXJpZ2h0OiAwcmVtO1xuICAgIG1hcmdpbi10b3A6IDByZW07XG4gICAgbWFyZ2luLWJvdHRvbTogMHJlbTtcbn1cbi5wYWRkaW5nIHtcbiAgICBwYWRkaW5nLXRvcDogMHJlbTtcbiAgICBwYWRkaW5nLWJvdHRvbTogMHJlbTtcbiAgICBwYWRkaW5nLWxlZnQ6IDByZW07XG4gICAgcGFkZGluZy1yaWdodDogLTEwcHg7XG4gICAgbWFyZ2luLXJpZ2h0OiAtMTBweDtcbiAgICBtYXJnaW4tbGVmdDogMHJlbSAgO1xuICAgIG1hcmdpbi1ib3R0b206IDByZW07ICAgIFxuICAgIG1hcmdpbi10b3A6IDByZW07XG59XG4ucGFkZGluZy1jb2wge1xuICAgIHBhZGRpbmctdG9wOiAwcmVtO1xuICAgIHBhZGRpbmctYm90dG9tOiAwZXJtO1xuICAgIHBhZGRpbmctbGVmdDogMGVybTtcbiAgICBwYWRkaW5nLXJpZ2h0OiAwZXJtO1xuICAgIG1hcmdpbi1yaWdodDogLTEwcHg7XG4gICAgbWFyZ2luLWxlZnQ6IDByZW07XG4gICAgbWFyZ2luLWJvdHRvbTogLTVweDtcbiAgICBtYXJnaW4tdG9wOiAwZXJtO1xufVxuLmNlbnRlci10ZXh0LXNlbGVjdGVke1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgICB3aWR0aDogMTAwJTtcbiAgICBoZWlnaHQ6IDEwMCU7XG4gICAgbWFyZ2luLWxlZnQ6IDByZW07XG4gICAgbWFyZ2luLXJpZ2h0OiAwcmVtO1xufVxuLml0ZW0tZ3JpZCB7XG4gICAgbWFyZ2luLWxlZnQ6IC0yMHB4O1xuICAgIG1hcmdpbi1yaWdodDogLTM1cHg7XG59Il19 */"

/***/ }),

/***/ "./src/app/pages/predictions/predictions.page.ts":
/*!*******************************************************!*\
  !*** ./src/app/pages/predictions/predictions.page.ts ***!
  \*******************************************************/
/*! exports provided: PredictionsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PredictionsPage", function() { return PredictionsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_client_client_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _services_predictions_predictions_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/predictions/predictions.service */ "./src/app/services/predictions/predictions.service.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! moment */ "./node_modules/moment/moment.js");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_5___default = /*#__PURE__*/__webpack_require__.n(moment__WEBPACK_IMPORTED_MODULE_5__);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");









var PredictionsPage = /** @class */ (function () {
    function PredictionsPage(client, predictions, http, router, loading, ionRouter) {
        this.client = client;
        this.predictions = predictions;
        this.http = http;
        this.router = router;
        this.loading = loading;
        this.ionRouter = ionRouter;
        this.clientId = localStorage.getItem('id_client');
        this.tab = 0;
        this.mycolorHome = "#FED100";
        this.status_color1 = '#db7f1c';
        this.status_color2 = '#92d822';
        this.status_color3 = '#061259';
    }
    PredictionsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.loading.presentLoading();
        /********** Get Data of client **********/
        this.http.get(this.client.getClientById(this.clientId))
            .subscribe(function (resp) {
            _this.data = resp['response'];
            _this.loginUser = _this.data.login;
            _this.statusUser = _this.data.status;
            _this.nickname = _this.data.nickname;
        });
        /********** Get list of leagues **********/
        this.http.get(this.predictions.competitionsPrediction(this.clientId))
            .toPromise()
            .then(function (resp) {
            _this.information = resp['response'];
            _this.date = _this.information.ddate;
            _this.points = _this.information.points;
            _this.hour = _this.information.dhour;
            _this.competitionsFootball = _this.information.competitions.competitions_football;
            _this.competitionsBaseball = _this.information.competitions.competitions_baseball;
            _this.competitionsBaseball.map(function (competition) {
                _this.competitionsFootball.push(competition);
            });
            _this.info = {};
        });
    };
    PredictionsPage.prototype.dateMatchsLeagues = function (date) {
        if (date === undefined || date === null) {
            return "Temporada Finalizada";
        }
        else {
            moment__WEBPACK_IMPORTED_MODULE_5__["locale"]('es');
            return moment__WEBPACK_IMPORTED_MODULE_5__(date, 'YYYY/MM/DD HH:mm:ss').endOf(date).fromNow();
        }
    };
    PredictionsPage.prototype.openBetsMatch = function (id_competition, sport_id, i) {
        var _this = this;
        this.index = i;
        var separator = "2019/2020";
        this.loading.presentLoading();
        this.tab++;
        this.sport_id = sport_id;
        this.id_tournament = id_competition;
        /********** Se valida si es liga de baseball o football **********/
        if (sport_id === 1) {
            this.http.get(this.predictions.getBetsById(id_competition, this.clientId))
                .subscribe(function (resp) {
                _this.dataBets = resp['response'];
                _this.tournament = _this.dataBets.id_competitions;
                _this.matchs = _this.dataBets.total_bets;
                _this.bets = _this.dataBets.client_bets;
                _this.data_league = _this.dataBets.name;
                _this.league = _this.data_league.split(separator, 1);
                _this.fixtures = _this.dataBets.fixtures;
                _this.sizeData = _this.fixtures.length;
            });
            if (this.competitionsFootball[this.index + 1].show !== 1) {
                this.info = undefined;
            }
        }
        else {
            this.http.get(this.predictions.getBaseballPhase(id_competition, this.clientId))
                .subscribe(function (resp) {
                _this.dataBets = resp['response'];
                _this.tournament = _this.dataBets.id_competition;
                _this.matchs = _this.dataBets.total_bets;
                _this.bets = _this.dataBets.client_bets;
                _this.data_league = _this.dataBets.name;
                _this.league = _this.data_league.split(separator, 1);
                _this.fixtures = _this.dataBets.fixtures;
                _this.sizeData = _this.fixtures.length;
            });
            if (this.competitionsFootball[this.index + 1].show !== 1 || this.competitionsFootball[this.index + 1].orderby !== 0) {
                this.info = undefined;
            }
        }
    };
    PredictionsPage.prototype.nextPage = function () {
        console.log(this.competitionsFootball);
        this.tab++;
        this.index++;
        this.info = this.competitionsFootball[this.index];
        this.sport_id = this.info.sport_id;
        if (this.sport_id === 1 && this.info.show === 1) {
            this.id_competition = this.info.id_competitions;
            this.openBetsMatch(this.id_competition, this.sport_id, this.index);
            if (this.competitionsFootball[this.index + 1].sport_id === 1 && this.competitionsFootball[this.index + 1].show !== 1) {
                this.info = undefined;
            }
        }
        else {
            this.id_competition = this.info.id_competitions;
            this.openBetsMatch(this.id_competition, this.sport_id, this.index);
            if (this.competitionsFootball[this.index + 1].sport_id !== 1 && (this.competitionsFootball[this.index + 1].show !== 1 || this.competitionsFootball[this.index + 1].orderby !== 0)) {
                this.info = undefined;
            }
        }
    };
    PredictionsPage.prototype.prevPage = function () {
        this.tab--;
        this.index--;
        this.info = this.competitionsFootball[this.index];
        this.sport_id = this.info.sport_id;
        if (this.sport_id === 1 && this.info.show === 1) {
            this.id_competition = this.info.id_competitions;
            this.openBetsMatch(this.id_competition, this.sport_id, this.index);
        }
        else if (this.sport_id !== 1 && this.info.show === 1 && this.info.orderby !== 99) {
            this.id_competition = this.info.id_competitions;
            this.openBetsMatch(this.id_competition, this.sport_id, this.index);
        }
        else {
            this.info === undefined;
        }
        if (this.tab === 0) {
            this.info === undefined;
        }
    };
    PredictionsPage.prototype.mainPage = function () {
        this.tab = 0;
        this.ngOnInit();
    };
    PredictionsPage.prototype.matchDate = function (date) {
        moment__WEBPACK_IMPORTED_MODULE_5__["locale"]('es');
        return moment__WEBPACK_IMPORTED_MODULE_5__(date).format("DD MMM YYYY");
    };
    PredictionsPage.prototype.compareDate = function (date) {
        moment__WEBPACK_IMPORTED_MODULE_5__["locale"]('es');
        return moment__WEBPACK_IMPORTED_MODULE_5__(date, 'YYYY/MM/DD HH:mm:ss').format("YYYY/MM/DD HH:mm:ss");
    };
    PredictionsPage.prototype.matchTime = function (date) {
        moment__WEBPACK_IMPORTED_MODULE_5__["locale"]('es');
        return moment__WEBPACK_IMPORTED_MODULE_5__(date, 'YYYY/MM/DD HH:mm:ss').format('HH:mm');
    };
    PredictionsPage.prototype.macthStatus = function (status) {
        if (status === 1) {
            return "Jugado";
        }
        else if (status === 2) {
            return "Jugando";
        }
        else {
            return "Programado";
        }
    };
    PredictionsPage.prototype.timeNow = function () {
        moment__WEBPACK_IMPORTED_MODULE_5__["locale"]('es');
        return moment__WEBPACK_IMPORTED_MODULE_5__().format("YYYY/MM/DD HH:mm:ss");
    };
    PredictionsPage.prototype.selectedPredictionHome = function (id_match, index, status_match, date) {
        var _this = this;
        if (this.timeNow() > this.compareDate(date)) {
            this.loading.presentAlertConfirm("Predicciones", "Fecha desafada", "La fecha del partido está desafada", [
                {
                    text: 'Seguir',
                    handler: function () {
                        console.log('Confirm Okay');
                    }
                }
            ]);
        }
        else {
            if (status_match === 3) {
                if (this.sport_id !== 2) {
                    var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]();
                    headers.append("Accept", 'application/json');
                    headers.append('Content-Type', 'application/json');
                    var httpOptions = {
                        headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({
                            'Content-Type': 'application/json'
                        })
                    };
                    var data = {
                        bet: {
                            client_bet: 0,
                            id_game_match: id_match,
                            id_tournament: this.tournament
                        }
                    };
                    if (this.loginUser === 'guest' && this.statusUser !== 1) {
                        this.http.post(this.predictions.createBets(this.clientId), data, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.countPrediction = resp['description'];
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                            if (_this.countPrediction !== "ok") {
                                _this.loading.presentAlertConfirm('Alert', '', 'Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?', [
                                    {
                                        text: 'Cancelar',
                                        handler: function () {
                                            console.log("exit");
                                        }
                                    },
                                    {
                                        text: 'Aceptar',
                                        handler: function () {
                                            _this.router.navigateByUrl('/get-password');
                                            _this.ngOnDestroy();
                                        }
                                    }
                                ]);
                            }
                        });
                    }
                    else {
                        console.log(index);
                        this.http.post(this.predictions.createBets(this.clientId), data, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                        });
                    }
                }
                else {
                    var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]();
                    headers.append("Accept", 'application/json');
                    headers.append('Content-Type', 'application/json');
                    var httpOptions = {
                        headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({
                            'Content-Type': 'application/json'
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
                    if (this.loginUser === 'guest' && this.statusUser !== 1) {
                        this.http.post(this.predictions.createBets(this.clientId), data2, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.countPrediction = resp['description'];
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                            if (_this.countPrediction !== "ok") {
                                _this.loading.presentAlertConfirm('Alert', '', 'Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?', [
                                    {
                                        text: 'Cancelar',
                                        handler: function () {
                                            console.log("exit");
                                        }
                                    },
                                    {
                                        text: 'Aceptar',
                                        handler: function () {
                                            _this.router.navigateByUrl('/get-password');
                                            _this.ngOnDestroy();
                                        }
                                    }
                                ]);
                            }
                        });
                    }
                    else {
                        this.http.post(this.predictions.createBets(this.clientId), data2, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                        });
                    }
                }
            }
            else {
                this.loading.presentAlert('No se puede seleccionar predicción,ya el partido ha iniciado !.');
            }
        }
    };
    PredictionsPage.prototype.selectedPredictionEqual = function (id_match, index, status_match, date) {
        var _this = this;
        if (this.timeNow() > this.compareDate(date)) {
            this.loading.presentAlertConfirm("Predicciones", "Fecha desafada", "La fecha del partido está desafada", [
                {
                    text: 'Seguir',
                    handler: function () {
                        console.log('Confirm Okay');
                    }
                }
            ]);
        }
        else {
            if (status_match === 3) {
                if (this.sport_id !== 2) {
                    var data = {
                        bet: {
                            client_bet: 1,
                            id_game_match: id_match,
                            id_tournament: this.tournament
                        }
                    };
                    var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]();
                    headers.append("Accept", 'application/json');
                    headers.append('Content-Type', 'application/json');
                    var httpOptions = {
                        headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({
                            'Content-Type': 'application/json'
                        })
                    };
                    if (this.loginUser === 'guest' && this.statusUser !== 1) {
                        this.http.post(this.predictions.createBets(this.clientId), data, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.countPrediction = resp['description'];
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                            if (_this.countPrediction !== "ok") {
                                _this.loading.presentAlertConfirm('Alert', '', 'Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?', [
                                    {
                                        text: 'Cancelar',
                                        handler: function () {
                                            console.log("exit");
                                        }
                                    },
                                    {
                                        text: 'Aceptar',
                                        handler: function () {
                                            _this.router.navigateByUrl('/get-password');
                                            _this.ngOnDestroy();
                                        }
                                    }
                                ]);
                            }
                        });
                    }
                    else {
                        this.http.post(this.predictions.createBets(this.clientId), data, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                        });
                    }
                }
                else {
                    console.log("No se permite el empate");
                }
            }
            else {
                this.loading.presentAlert('No se puede seleccionar predicción,ya el partido ha iniciado !.');
            }
        }
    };
    PredictionsPage.prototype.selectedPredictionAway = function (id_match, index, status_match, date) {
        var _this = this;
        if (this.timeNow() > this.compareDate(date)) {
            this.loading.presentAlertConfirm("Predicciones", "Fecha desafada", "La fecha del partido está desafada", [
                {
                    text: 'Seguir',
                    handler: function () {
                        console.log('Confirm Okay');
                    }
                }
            ]);
        }
        else {
            if (status_match === 3) {
                if (this.sport_id !== 2) {
                    var data = {
                        bet: {
                            client_bet: 2,
                            id_game_match: id_match,
                            id_tournament: this.tournament
                        }
                    };
                    var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]();
                    headers.append("Accept", 'application/json');
                    headers.append('Content-Type', 'application/json');
                    var httpOptions = {
                        headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({
                            'Content-Type': 'application/json'
                        })
                    };
                    if (this.loginUser === 'guest' && this.statusUser !== 1) {
                        this.http.post(this.predictions.createBets(this.clientId), data, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.countPrediction = resp['description'];
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                            if (_this.countPrediction !== "ok") {
                                _this.loading.presentAlertConfirm('Alert', '', 'Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?', [
                                    {
                                        text: 'Cancelar',
                                        handler: function () {
                                            console.log("exit");
                                        }
                                    },
                                    {
                                        text: 'Aceptar',
                                        handler: function () {
                                            _this.router.navigateByUrl('/get-password');
                                            _this.ngOnDestroy();
                                        }
                                    }
                                ]);
                            }
                        });
                    }
                    else {
                        this.http.post(this.predictions.createBets(this.clientId), data, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                        });
                    }
                }
                else {
                    var data2 = {
                        bet: {
                            client_bet: 2,
                            id_game_match: id_match,
                            id_tournament: this.tournament,
                            sport_id: this.sport_id
                        }
                    };
                    var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]();
                    headers.append("Accept", 'application/json');
                    headers.append('Content-Type', 'application/json');
                    var httpOptions = {
                        headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({
                            'Content-Type': 'application/json'
                        })
                    };
                    if (this.loginUser === 'guest' && this.statusUser !== 1) {
                        this.http.post(this.predictions.createBets(this.clientId), data2, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.countPrediction = resp['description'];
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                            if (_this.countPrediction !== "ok") {
                                _this.loading.presentAlertConfirm('Alert', '', 'Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?', [
                                    {
                                        text: 'Cancelar',
                                        handler: function () {
                                            console.log("exit");
                                            _this.ngOnDestroy();
                                        }
                                    },
                                    {
                                        text: 'Aceptar',
                                        handler: function () {
                                            _this.router.navigateByUrl('/get-password');
                                            _this.ngOnDestroy();
                                        }
                                    }
                                ]);
                            }
                        });
                    }
                    else {
                        this.http.post(this.predictions.createBets(this.clientId), data2, httpOptions)
                            .toPromise()
                            .then(function (resp) {
                            _this.infoTeam = resp['response'];
                            _this.teamSelected = _this.infoTeam.status;
                            _this.openBetsMatch(_this.tournament, _this.sport_id, _this.index);
                        });
                    }
                }
            }
            else {
                this.loading.presentAlert('No se puede seleccionar predicción,ya el partido ha iniciado !.');
            }
        }
    };
    PredictionsPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    PredictionsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-predictions',
            template: __webpack_require__(/*! ./predictions.page.html */ "./src/app/pages/predictions/predictions.page.html"),
            styles: [__webpack_require__(/*! ./predictions.page.scss */ "./src/app/pages/predictions/predictions.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_client_client_service__WEBPACK_IMPORTED_MODULE_2__["ClientService"], _services_predictions_predictions_service__WEBPACK_IMPORTED_MODULE_3__["PredictionsService"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"], _angular_router__WEBPACK_IMPORTED_MODULE_6__["Router"], _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_7__["LoadingService"],
            _angular_common__WEBPACK_IMPORTED_MODULE_8__["Location"]])
    ], PredictionsPage);
    return PredictionsPage;
}());



/***/ }),

/***/ "./src/app/services/predictions/predictions.service.ts":
/*!*************************************************************!*\
  !*** ./src/app/services/predictions/predictions.service.ts ***!
  \*************************************************************/
/*! exports provided: PredictionsService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "PredictionsService", function() { return PredictionsService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _client_client_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _configurations_configurations_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../configurations/configurations.service */ "./src/app/services/configurations/configurations.service.ts");




var PredictionsService = /** @class */ (function () {
    function PredictionsService(client, config) {
        this.client = client;
        this.config = config;
    }
    /********** Competitions **********/
    PredictionsService.prototype.competitions = function () {
        return this.client.apiUrl + 'footballapi/'
            + this.client.apiVersion + '/competitions/list/'
            + this.client.appId + '/'
            + this.client.getLang()
            + this.client.getSecurityToken('?');
    };
    PredictionsService.prototype.competitionsPrediction = function (clientId) {
        return this.client.apiUrl + 'sportsapi/'
            + this.client.apiVersion + '/clients/dashboard/' + clientId + '/' + this.client.getLang()
            + this.config.getGMT('?') + '&new=1' + this.client.getSecurityToken('&');
    };
    /**********  Bets **********/
    PredictionsService.prototype.getBetsById = function (id_competition, id_client) {
        return this.client.apiUrl + 'sportsapi/'
            + this.client.apiVersion + '/clients/bets/get/'
            + id_client + '/' + id_competition
            + this.config.getGMT('?')
            + this.client.getSecurityToken('&');
    };
    PredictionsService.prototype.getTodayBets = function (date, id_client) {
        return this.client.apiUrl + 'sportsapi/'
            + this.client.apiVersion + '/clients/bets/get/date/'
            + id_client + '/' + date
            + this.config.getGMT('?')
            + this.client.getSecurityToken('&');
    };
    PredictionsService.prototype.createBets = function (id_client) {
        return this.client.apiUrl + 'sportsapi/'
            + 'v2' + '/client/' + id_client + '/bet' + this.client.getSecurityToken('?');
    };
    PredictionsService.prototype.getBaseballPhase = function (id_competition, id_client) {
        return 'https://plussports.hecticus.com/' + 'baseballapi/'
            + this.client.apiVersion + '/clients/bets/get/'
            + id_client + '/' + id_competition
            + this.client.getGMT('?')
            + this.client.getSecurityToken('&');
    };
    PredictionsService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_client_client_service__WEBPACK_IMPORTED_MODULE_2__["ClientService"], _configurations_configurations_service__WEBPACK_IMPORTED_MODULE_3__["ConfigurationsService"]])
    ], PredictionsService);
    return PredictionsService;
}());



/***/ })

}]);
//# sourceMappingURL=predictions-predictions-module.js.map