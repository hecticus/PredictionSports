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

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\" *ngIf=\"tab === 0 \">\r\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\r\n    </ion-buttons>\r\n    <ion-buttons slot=\"start\" *ngIf=\"tab !== 0 \">\r\n         <ion-icon name=\"arrow-round-back\" color=\"primary\" class=\"arrow-back\" size=\"large\" (click)=\"mainPage()\"></ion-icon>\r\n    </ion-buttons>\r\n    <div class=\"center\">\r\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\r\n    </div>\r\n  </ion-toolbar>\r\n</ion-header>\r\n<ion-content *ngIf=\"tab === 0\">\r\n  <div class=\"background\">\r\n    <ion-label class=\"white margin-top-m\"></ion-label>\r\n    <div style=\"position:absolute; top: 95px; background: #ffffff; width: 100%; height:2px;\"></div>\r\n    <ion-label class=\"white margin-top-m text-size-g\">{{nickname}}</ion-label>\r\n    <div class=\"circle-red\">\r\n      <div  class=\"white margin-top-m\">\r\n        <ion-label class=\"white margin-top-m text-size-m\">{{points}}</ion-label>\r\n        <ion-label class=\"white margin-top-s text-size-s\">{{date}}</ion-label>\r\n        <ion-label class=\"white margin-top-s text-size-s\">{{hour}}</ion-label>\r\n      </div>\r\n    </div>  \r\n  </div>\r\n  <ion-item color=\"danger\">\r\n    <ion-grid>\r\n      <ion-row>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m\">Torneo</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m white-right\">Próximo Juego</ion-label>\r\n        </ion-col>\r\n      </ion-row>\r\n    </ion-grid>\r\n  </ion-item>\r\n  <ion-list  *ngFor =\"let data of competitionsFootball; let i= index\">\r\n    <ion-item *ngIf=\"data.show === 1 && data.orderby !== 99\" (click)=\"openBetsMatch(data.id_competitions, data.sport_id, i)\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"2\">\r\n            <img class=\"img-size-s\" src=\"{{data.competiton_type.competition_logo}}.svg\">\r\n          </ion-col>\r\n          <ion-col size=\"5\">\r\n            <div class=\"div\">\r\n              <ion-label class=\"text-size-s\">{{data.competiton_type.name}}</ion-label>\r\n            </div>\r\n          </ion-col>\r\n          <ion-col size=\"5\">\r\n            <div *ngIf=\"data.match !== null\" class=\"div right div-width\" >\r\n              <span class=\"text-size-r right\" [innerHTML]=\"dateMatchsLeagues(data.match.date)\"></span>\r\n              <ion-icon  name=\"arrow-dropright\" color=\"primary\"></ion-icon>\r\n            </div>\r\n            <div *ngIf=\"data.match === null\" class=\"div right div-width\" >\r\n              <span class=\"text-size-r right\" >Temporada finalizada</span>\r\n              <ion-icon  name=\"arrow-dropright\" color=\"primary\"></ion-icon>\r\n            </div>\r\n          </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>\r\n  </ion-list>\r\n</ion-content>\r\n<ion-content *ngIf=\"tab !== 0\">\r\n  <ion-item color=\"new\" >\r\n      <ion-icon name=\"arrow-round-back\" class=\"arrow-back\" size=\"large\" (click)=\"prevPage()\" *ngIf=\"index !== 0\"></ion-icon>\r\n      <ion-label class=\"text-size-g\">{{league}} </ion-label>\r\n      <ion-icon name=\"arrow-round-forward\" class=\"arrow-next\" size=\"large\" (click)=\"nextPage()\" \r\n      *ngIf=\"index >= 0 && info !== undefined \"></ion-icon>\r\n  </ion-item>\r\n  <ion-item color=\"danger\">\r\n    <ion-label class=\"text-size-m\">{{bets}} Predicciones / {{matchs}} Partidos</ion-label>\r\n  </ion-item>\r\n    <ion-list *ngFor =\"let item of fixtures\">\r\n    <ion-item color=\"danger-primary\">\r\n      <div class=\"center-text\">\r\n         <span class=\"text-size-m\" [innerHTML]=\"matchDate(item.date)\"></span>\r\n      </div>\r\n    </ion-item>\r\n    <ion-list *ngFor=\"let data of item.matches; let i= index\">  \r\n    <ion-item *ngIf=\"sport_id === 1\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"4\" (click)=\"selectedPredictionHome(data.id_game_matches,i,data.status.id_status,data.date)\">\r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\r\n              <ion-label class=\"text-size-m-color\">\r\n                {{data.home_team.short_name}}\r\n              </ion-label>\r\n            </div>      \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\r\n                <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\r\n                <ion-label class=\"text-size-m-color\">\r\n                  {{data.home_team.short_name}}\r\n                </ion-label>\r\n            </div>\r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.home_team.short_name}}</ion-label>\r\n            </div>    \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.home_team.short_name}}</ion-label>\r\n            </div>\r\n          </ion-col>\r\n          <ion-col size=\"4\" (click)=\"selectedPredictionEqual(data.id_game_matches,i,data.status.id_status,data.date)\" >\r\n            <div class=\"center-text\" *ngIf=\"data.bet !== undefined\"\r\n            [style.background-color]=\"data.bet.client_bet === 1 ? mycolorHome : ''\" >\r\n              <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">              {{data.home_team_goals}} - {{data.away_team_goals}}\r\n              </ion-label>\r\n              <div class=\"center\">\r\n                <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\r\n              </div>\r\n              <div  *ngIf=\"data.status.id_status === 1\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div  *ngIf=\"data.status.id_status === 2\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div *ngIf=\"data.status.id_status === 3\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n          </div>\r\n          <div class=\"center-text\" *ngIf=\"data.bet === undefined\">\r\n              <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">              {{data.home_team_goals}} - {{data.away_team_goals}}\r\n              </ion-label>\r\n              <div class=\"center\">\r\n                <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\r\n              </div>\r\n              <div  *ngIf=\"data.status.id_status === 1\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div  *ngIf=\"data.status.id_status === 2\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div *ngIf=\"data.status.id_status === 3\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n          </div>\r\n          </ion-col>\r\n        <ion-col size=\"4\"  (click)=\"selectedPredictionAway(data.id_game_matches,i,data.status.id_status,data.date)\">\r\n             <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\r\n              <ion-label class=\"text-size-m-color\">\r\n                {{data.away_team.short_name}}\r\n              </ion-label>\r\n            </div>      \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\r\n                <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\r\n                <ion-label class=\"text-size-m-color\">\r\n                  {{data.away_team.short_name}}\r\n                </ion-label>\r\n            </div>\r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.away_team.short_name}}</ion-label>\r\n            </div>    \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.away_team.short_name}}</ion-label>\r\n            </div>\r\n          </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>\r\n    <ion-item *ngIf=\"sport_id !== 1\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"4\" (click)=\"selectedPredictionHome(data.id_game,i,data.status.id_status,data.date)\">\r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\r\n              <ion-label class=\"text-size-m-color\">\r\n                {{data.home_team.short_name}}\r\n              </ion-label>\r\n            </div>      \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\r\n                <img class=\"img-size-s\" src=\"{{data.home_team.team_logo}}\">\r\n                <ion-label class=\"text-size-m-color\">\r\n                  {{data.home_team.short_name}}\r\n                </ion-label>\r\n            </div>\r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 0 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.home_team.short_name}}</ion-label>\r\n            </div>    \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.home_team.short_name}}</ion-label>\r\n            </div>\r\n          </ion-col>\r\n          <ion-col size=\"4\" >\r\n            <div class=\"center-text\" *ngIf=\"data.bet !== undefined\"\r\n            [style.background-color]=\"data.bet.client_bet === 1 ? mycolorHome : ''\" >\r\n              <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">              {{data.home_team_goals}} - {{data.away_team_goals}}\r\n              </ion-label>\r\n              <div class=\"center\">\r\n                <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\r\n              </div>\r\n              <div  *ngIf=\"data.status.id_status === 1\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div  *ngIf=\"data.status.id_status === 2\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div *ngIf=\"data.status.id_status === 3\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n          </div>\r\n          <div class=\"center-text\" *ngIf=\"data.bet === undefined\">\r\n              <ion-label class=\"text-size-g-color\" *ngIf=\"data.status.id_status === 1 || data.status.id_status === 2\">              {{data.home_team_goals}} - {{data.away_team_goals}}\r\n              </ion-label>\r\n              <div class=\"center\">\r\n                <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(data.date)\"></span>\r\n              </div>\r\n              <div  *ngIf=\"data.status.id_status === 1\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div  *ngIf=\"data.status.id_status === 2\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n               <div *ngIf=\"data.status.id_status === 3\">\r\n                <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\r\n                  <strong [innerHTML]=\"macthStatus(data.status.id_status)\"></strong>\r\n                </ion-label>\r\n              </div>\r\n          </div>\r\n          </ion-col>\r\n          <ion-col size=\"4\"  (click)=\"selectedPredictionAway(data.id_game,i,data.status.id_status,data.date)\">\r\n             <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020'  && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\r\n              <ion-label class=\"text-size-m-color\">\r\n                {{data.away_team.short_name}}\r\n              </ion-label>\r\n            </div>      \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league !== 'Serie A 2019/2020' && data.bet === undefined\">\r\n                <img class=\"img-size-s\" src=\"{{data.away_team.team_logo}}\">\r\n                <ion-label class=\"text-size-m-color\">\r\n                  {{data.away_team.short_name}}\r\n                </ion-label>\r\n            </div>\r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet !== undefined\"\r\n              [style.background-color]=\"data.bet.client_bet === 2 ? mycolorHome : ''\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.away_team.short_name}}</ion-label>\r\n            </div>    \r\n            <div class=\"center-text\" \r\n              *ngIf=\"data_league === 'Serie A 2019/2020' && data.bet === undefined\">\r\n              <img class=\"img-size-s\" src=\"./assets/img/favicon.png\">\r\n              <ion-label class=\"text-size-m-color\" >{{data.away_team.short_name}}</ion-label>\r\n            </div>\r\n          </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>\r\n  </ion-list>\r\n  </ion-list>\r\n  <ion-content  *ngIf=\"sizeData === 0\">\r\n     <div class=\"div-alert\">\r\n    <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\r\n    </div>\r\n    <div class=\"div-alert\" >\r\n      Todavía no has hecho predicciones o no se han Jugado los partidos. \r\n    </div>\r\n  </ion-content>\r\n</ion-content>\r\n"

/***/ }),

/***/ "./src/app/pages/predictions/predictions.page.scss":
/*!*********************************************************!*\
  !*** ./src/app/pages/predictions/predictions.page.scss ***!
  \*********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  size: 0.3em; }\n\n.text-size-r {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  height: 100%;\n  border: none; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  size: 0.8em;\n  text-align: center; }\n\n.text-size-m-color {\n  font-size: 13px;\n  color: #061259;\n  text-align: center; }\n\n.text-size-g-color {\n  font-size: 18px;\n  color: #061259;\n  text-align: center; }\n\n.text-size-status {\n  font-size: 13px; }\n\n.text-size-g {\n  font-size: 20px;\n  text-align: center; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.arrow-next {\n  float: right;\n  margin-right: 10px; }\n\n.arrow-back {\n  float: left;\n  margin-left: 10px; }\n\n.center-text {\n  width: 100%;\n  height: 100%;\n  text-align: center; }\n\n.icon-alert {\n  margin-top: 15px;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n.div-alert {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGFnZXMvcHJlZGljdGlvbnMvRDpcXERvY3VtZW50c1xccHJvZ3JhbW1pbmcgcHJvamVjdHNcXGFwcE1hc1Nwb3J0L3NyY1xcYXBwXFxwYWdlc1xccHJlZGljdGlvbnNcXHByZWRpY3Rpb25zLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGdCQUFnQjtFQUNoQiwwQkFBc0I7VUFBdEIsc0JBQXNCO0VBQ3RCLGVBQWUsRUFBQTs7QUFFbkI7RUFDSSxZQUFZO0VBQ1osZUFBZTtFQUNmLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixXQUFXLEVBQUE7O0FBRWY7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixjQUFjLEVBQUE7O0FBRWxCO0VBQ0ksZUFBZSxFQUFBOztBQUduQjtFQUNJLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxXQUFXO0VBQ1gsWUFBWSxFQUFBOztBQUVoQjtFQUNHLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFlBQVk7RUFDWixZQUFZLEVBQUE7O0FBRWY7RUFDSSxXQUFXO0VBQ1gsaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLGtCQUFrQjtFQUNsQixhQUFhO0VBQ2IscUNBQXlEO0VBQ3pELHNCQUFzQjtFQUN0QiwyQkFBMkIsRUFBQTs7QUFFL0I7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxTQUFTLEVBQUE7O0FBRWI7RUFDSSxXQUFXO0VBQ1gsa0JBQWtCLEVBQUE7O0FBRXRCO0VBQ0ksZUFBZTtFQUNmLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFdEI7RUFDSSxlQUFlO0VBQ2YsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUV0QjtFQUNFLGVBQWUsRUFBQTs7QUFFakI7RUFDSSxlQUFlO0VBQ2Ysa0JBQWtCLEVBQUE7O0FBRXRCO0VBQ0UsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUVwQjtFQUNJLGtCQUFpQjtFQUNqQixTQUFTO0VBQ1QsT0FBTTtFQUNOLFFBQVE7RUFDUixjQUFhO0VBQ2IsWUFBVztFQUNYLGFBQWE7RUFDYixpQkFBaUI7RUFDakIsbURBQWlEO0VBQ2pELGtCQUFrQjtFQUNsQixrQkFBaUI7RUFDakIsY0FBb0I7RUFDcEIsZ0JBQWU7RUFDZix5QkFBeUI7RUFDekIsbUJBQW1CO0VBQ25CLGlCQUFpQjtFQUNqQixxQkFBcUIsRUFBQTs7QUFFekI7RUFDRSxZQUFZO0VBQ1osa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0UsV0FBVztFQUNYLGlCQUFpQixFQUFBOztBQUVuQjtFQUNFLFdBQVc7RUFDWCxZQUFZO0VBQ1osa0JBQWtCLEVBQUE7O0FBRW5CO0VBQ0MsZ0JBQWdCO0VBQ2hCLFdBQVc7RUFDWCxZQUFZO0VBQ1osZUFBZTtFQUNmLGlCQUFpQjtFQUNqQixjQUFjLEVBQUE7O0FBRWhCO0VBQ0csYUFBYTtFQUNiLHVCQUF1QjtFQUN2QixtQkFBbUI7RUFDbkIsV0FBVztFQUNYLFlBQVk7RUFDWixlQUFlO0VBQ2YsY0FBYztFQUNkLGtCQUFrQixFQUFBIiwiZmlsZSI6InNyYy9hcHAvcGFnZXMvcHJlZGljdGlvbnMvcHJlZGljdGlvbnMucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmJhY2tncm91bmQtcmVkIHtcclxuICAgIHBhZGRpbmctdG9wOiA1cHg7XHJcbiAgICBwYWRkaW5nLWJsb2NrLWVuZDogNXB4O1xyXG4gICAgYmFja2dyb3VuZDogcmVkO1xyXG59XHJcbi53aGl0ZS1yaWdodCB7XHJcbiAgICBjb2xvcjogd2hpdGU7XHJcbiAgICBmb250LXNpemU6IDE2cHg7XHJcbiAgICBmbG9hdDogcmlnaHQ7IFxyXG59XHJcbi50ZXh0LXNpemUtc3tcclxuICAgIGZvbnQtc2l6ZTogMTNweDtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgZmxvYXQ6IHJpZ2h0O1xyXG4gICAgc2l6ZTogMC4zZW07XHJcbn1cclxuLnRleHQtc2l6ZS1ye1xyXG4gICAgZm9udC1zaXplOiAxM3B4O1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbiAgICBmbG9hdDogcmlnaHQ7XHJcbiAgICBjb2xvcjogIzAzNzdCNDtcclxufVxyXG4uaW9uLWljb24ge1xyXG4gICAgZm9udC1zaXplOiA2NHB4O1xyXG4gIH1cclxuXHJcbi53aGl0ZS1sZWZ0e1xyXG4gICAgY29sb3I6IHdoaXRlO1xyXG4gICAgcGFkZGluZy1sZWZ0OiAxOHB4O1xyXG4gICAgZm9udC1zaXplOiAxNnB4O1xyXG59XHJcbi53aGl0ZXtcclxuICAgIGNvbG9yOiB3aGl0ZTtcclxufVxyXG4uaW1nLXNpemUtc3tcclxuICAgIHdpZHRoOiA0MHB4O1xyXG4gICAgaGVpZ2h0OiA0MHB4O1xyXG59XHJcbi5kaXZ7XHJcbiAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICBoZWlnaHQ6IDEwMCU7XHJcbiAgIGJvcmRlcjogbm9uZTtcclxufVxyXG4uZGl2LXdpZHRoe1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbiAgICB0ZXh0LWFsaWduOiByaWdodDtcclxufVxyXG4ucmlnaHR7XHJcbiAgICBmbG9hdDogcmlnaHQ7XHJcbn1cclxuLmJhY2tncm91bmR7XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbiAgICBoZWlnaHQ6IDE5MHB4O1xyXG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiLi4vLi4vLi4vYXNzZXRzL2ltZy9iYWNrLXB0cy5wbmdcIik7XHJcbiAgICBiYWNrZ3JvdW5kLXNpemU6IGNvdmVyO1xyXG4gICAgYmFja2dyb3VuZC1wb3NpdGlvbjogY2VudGVyO1xyXG59XHJcbi5tYXJnaW4tdG9wLW17XHJcbiAgICBtYXJnaW4tdG9wOiAwLjllbTtcclxufVxyXG4ubWFyZ2luLXRvcC1ze1xyXG4gICAgbWFyZ2luLXRvcDogMC41ZW07XHJcbn1cclxuLm1hcmdpbi10b3AteHN7XHJcbiAgICBtYXJnaW4tdG9wOiAwLjhlbTtcclxufVxyXG4udGV4dC1zaXplLWx7XHJcbiAgICBzaXplOiAxZW07XHJcbn1cclxuLnRleHQtc2l6ZS1te1xyXG4gICAgc2l6ZTogMC44ZW07XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnRleHQtc2l6ZS1tLWNvbG9ye1xyXG4gICAgZm9udC1zaXplOiAxM3B4O1xyXG4gICAgY29sb3I6ICMwNjEyNTk7XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnRleHQtc2l6ZS1nLWNvbG9ye1xyXG4gICAgZm9udC1zaXplOiAxOHB4O1xyXG4gICAgY29sb3I6ICMwNjEyNTk7XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnRleHQtc2l6ZS1zdGF0dXN7XHJcbiAgZm9udC1zaXplOiAxM3B4O1xyXG59XHJcbi50ZXh0LXNpemUtZ3tcclxuICAgIGZvbnQtc2l6ZTogMjBweDtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4uY2VudGVye1xyXG4gIG1hcmdpbjogMCBhdXRvO1xyXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4uY2lyY2xlLXJlZHtcclxuICAgIHBvc2l0aW9uOmFic29sdXRlO1xyXG4gICAgdG9wOiAzMHB4O1xyXG4gICAgbGVmdDowO1xyXG4gICAgcmlnaHQ6IDA7XHJcbiAgICBkaXNwbGF5OmJsb2NrO1xyXG4gICAgd2lkdGg6MTMwcHg7XHJcbiAgICBoZWlnaHQ6IDEzMHB4O1xyXG4gICAgbGluZS1oZWlnaHQ6IDI1cHg7XHJcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZ2JhKDIyNywzMCw0OCwwLjUpICAhaW1wb3J0YW50O1xyXG4gICAgYm9yZGVyLXJhZGl1czogNTAlO1xyXG4gICAgdGV4dC1hbGlnbjpjZW50ZXI7XHJcbiAgICBjb2xvcjogcmdiKDAsNDAsMTIwKTtcclxuICAgIGZvbnQtc2l6ZToxLjhlbTtcclxuICAgIG1hcmdpbjogMCBhdXRvICFpbXBvcnRhbnQ7XHJcbiAgICBib3JkZXItc3R5bGU6IHNvbGlkO1xyXG4gICAgYm9yZGVyLXdpZHRoOiAycHg7XHJcbiAgICBib3JkZXItY29sb3I6ICNmZmZmZmY7XHJcbn1cclxuLmFycm93LW5leHQge1xyXG4gIGZsb2F0OiByaWdodDtcclxuICBtYXJnaW4tcmlnaHQ6IDEwcHg7XHJcbn1cclxuLmFycm93LWJhY2sge1xyXG4gIGZsb2F0OiBsZWZ0O1xyXG4gIG1hcmdpbi1sZWZ0OiAxMHB4O1xyXG59XHJcbi5jZW50ZXItdGV4dHtcclxuICB3aWR0aDogMTAwJTtcclxuICBoZWlnaHQ6IDEwMCU7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gfVxyXG4gLmljb24tYWxlcnR7XHJcbiAgbWFyZ2luLXRvcDogMTVweDtcclxuICB3aWR0aDogNjBweDtcclxuICBoZWlnaHQ6IDYwcHg7XHJcbiAgZm9udC1zaXplOiAzNXB4O1xyXG4gIGZvbnQtd2VpZ2h0OiBib2xkO1xyXG4gIGNvbG9yOiAjNzc3Nzc3O1xyXG59XHJcbi5kaXYtYWxlcnR7XHJcbiAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICB3aWR0aDogMTAwJTtcclxuICAgYm9yZGVyOiBub25lO1xyXG4gICBmb250LXNpemU6IDMwcHg7XHJcbiAgIGNvbG9yOiAjNzc3Nzc3O1xyXG4gICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn0iXX0= */"

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