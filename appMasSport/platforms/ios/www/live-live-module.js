(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["live-live-module"],{

/***/ "./src/app/pages/live/live.module.ts":
/*!*******************************************!*\
  !*** ./src/app/pages/live/live.module.ts ***!
  \*******************************************/
/*! exports provided: LivePageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LivePageModule", function() { return LivePageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _live_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./live.page */ "./src/app/pages/live/live.page.ts");







var routes = [
    { path: '', component: _live_page__WEBPACK_IMPORTED_MODULE_6__["LivePage"] }
];
var LivePageModule = /** @class */ (function () {
    function LivePageModule() {
    }
    LivePageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
            ],
            declarations: [_live_page__WEBPACK_IMPORTED_MODULE_6__["LivePage"]]
        })
    ], LivePageModule);
    return LivePageModule;
}());



/***/ }),

/***/ "./src/app/pages/live/live.page.html":
/*!*******************************************!*\
  !*** ./src/app/pages/live/live.page.html ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\" *ngIf=\"tab === 0 \">\r\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\r\n    </ion-buttons>   \r\n    <ion-buttons slot=\"start\" *ngIf=\"tab !== 0 \">\r\n       <ion-icon name=\"arrow-round-back\" color=\"primary\" \r\n                 class=\"arrow-back\" size=\"large\" \r\n                 (click)=\"mainPage()\"></ion-icon>\r\n    </ion-buttons>  \r\n    <div class=\"center\">\r\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\r\n    </div>\r\n  </ion-toolbar>\r\n</ion-header>\r\n<ion-content *ngIf=\"sizeData > 0 && tab === 0\">\r\n   <ion-item class=\"h-header green text-size-g\">\r\n    <ion-label> {{date_day}}  </ion-label>\r\n  </ion-item>\r\n  <ion-list *ngFor =\"let leagues of data\" class=\"list-padding\">\r\n    <ion-item color=\"danger\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"12\" class=\"col-m\">\r\n            <img class=\"img-size-s padding\" src=\"{{leagues.competiton_type.competition_logo}}.svg\">\r\n            <div class=\"div-title\">\r\n                <ion-label class=\"text-size-s font\">{{leagues.competiton_type.name}}</ion-label>\r\n            </div>               \r\n          </ion-col>                        \r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>    \r\n    <ion-list *ngFor =\"let team of leagues.fixtures\">\r\n      <ion-item (click)=\"detailMatchLive(leagues, team)\">          \r\n        <ion-grid >\r\n          <ion-row >\r\n            <ion-col size=\"4\">\r\n              <div class=\"center-text\">\r\n                <img class=\"img-size-s\"  *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.homeTeam.team_logo}}\">\r\n                 <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\r\n                <ion-label class=\"text-size-m-color\">\r\n                    {{team.homeTeam.short_name}}                \r\n                </ion-label>\r\n              </div>\r\n            </ion-col>\r\n\r\n            <ion-col size=\"4\" >\r\n              <div class=\"center-text\">\r\n                <ion-label class=\"text-size-g-color\" *ngIf=\"team.id_status === 1 || team.id_status === 2\"> \r\n                      {{team.home_team_goals}} - {{team.away_team_goals}}\r\n                </ion-label>\r\n                <div class=\"center-text\">\r\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(team.date)\"></span>\r\n                </div>\r\n                <div  *ngIf=\"team.id_status === 1\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\r\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n                  <div  *ngIf=\"team.id_status === 2\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\r\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n                  <div *ngIf=\"team.id_status === 3\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\r\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n              </div>\r\n            </ion-col>\r\n\r\n            <ion-col size=\"4\">\r\n              <div class=\"center-text\">\r\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.awayTeam.team_logo}}\">\r\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\r\n                <ion-label class=\"text-size-m-color\">\r\n                    {{team.awayTeam.short_name}}\r\n                </ion-label>\r\n              </div>\r\n            </ion-col>\r\n          </ion-row>\r\n        </ion-grid>\r\n      </ion-item>\r\n    </ion-list>\r\n  </ion-list>  \r\n</ion-content>\r\n<ion-content *ngIf=\"sizeData === 0\">\r\n  <ion-item class=\"h-header green text-size-g\">\r\n      <ion-label> {{date_day}}  </ion-label>\r\n  </ion-item>\r\n  <div class=\"div\">\r\n    <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\r\n  </div>\r\n  <div class=\"div\">\r\n     No hay juegos programados para hoy. \r\n  </div>      \r\n</ion-content>\r\n\r\n<ion-content *ngIf=\"tab !== 0 && info_match !== undefined \">  \r\n  <ion-list class=\"list-padding\">\r\n    <ion-item color=\"danger\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"11\" class=\"col-m\">\r\n            <img class=\"img-size-s padding\"  src=\"{{logo_leagues}}.svg\">\r\n            <div class=\"div-title\">\r\n                <ion-label class=\"text-size-s font\">{{name_leagues}}</ion-label>\r\n            </div>               \r\n          </ion-col> \r\n           <ion-col size=\"1\" class=\"col-m refresh\"  (click)=\"refreshGame()\"> \r\n            <ion-icon ios=\"ios-refresh\" md=\"md-refresh\" size=\"large\"></ion-icon>\r\n           </ion-col>                       \r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>    \r\n   <ion-item>          \r\n        <ion-grid >\r\n          <ion-row >\r\n            <ion-col size=\"4\">\r\n              <div class=\"center-text\">\r\n                <img class=\"img-size-s\" *ngIf=\"id_leagues !== 67\" src=\"{{info_match.home_team.team_logo}}\">\r\n                <img class=\"img-size-s\" *ngIf=\"id_leagues === 67\" src=\"./assets/img/favicon.png\" >\r\n                <ion-label class=\"text-size-m-color\">\r\n                    {{info_match.home_team.short_name}}                \r\n                </ion-label>\r\n              </div>\r\n            </ion-col>\r\n            <ion-col size=\"4\" *ngIf=\"date_match.status\">\r\n              <div class=\"center-text\">\r\n                <ion-label class=\"text-size-g-color\" \r\n                *ngIf=\"info_match.status?.id_status === 1 || info_match.status?.id_status === 2\"> \r\n                      {{info_match.home_team_goals}} - {{info_match.away_team_goals}}\r\n                </ion-label>\r\n                <ion-label class=\"text-size-g-color\" \r\n                *ngIf=\"info_match.status?.id_status === 3\"> \r\n                       {{info_match.home_team_goals}} - {{info_match.away_team_goals}}\r\n                </ion-label>\r\n                <div class=\"center-text\">\r\n                  <span class=\"text-size-m-color\"></span>\r\n                </div>\r\n                <div  *ngIf=\"info_match.status?.id_status === 1\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\r\n                    <strong [innerHTML]=\"macthStatus(info_match.status?.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n                  <div  *ngIf=\"info_match.status?.id_status === 2\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\r\n                    <strong [innerHTML]=\"macthStatus(info_match.status?.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n                  <div *ngIf=\"info_match.status?.id_status === 3\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\r\n                    <strong [innerHTML]=\"macthStatus(info_match.status?.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n              </div>\r\n            </ion-col>\r\n            <ion-col size=\"4\">\r\n              <div class=\"center-text\">\r\n                <img class=\"img-size-s\" *ngIf=\"id_leagues !== 67\" src=\"{{info_match.away_team.team_logo}}\">\r\n                <img class=\"img-size-s\" *ngIf=\"id_leagues === 67\" src=\"./assets/img/favicon.png\" >\r\n                <ion-label class=\"text-size-m-color\">\r\n                    {{info_match.away_team.short_name}}\r\n                </ion-label>\r\n              </div>\r\n            </ion-col>\r\n          </ion-row>\r\n        </ion-grid>\r\n      </ion-item>  \r\n  </ion-list>\r\n</ion-content>"

/***/ }),

/***/ "./src/app/pages/live/live.page.scss":
/*!*******************************************!*\
  !*** ./src/app/pages/live/live.page.scss ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".col-m {\n  display: flex;\n  justify-content: center; }\n\n.col-m.refresh {\n  align-items: center; }\n\n.list-padding {\n  padding-top: 0px !important; }\n\n.h-header {\n  position: relative;\n  text-align: center;\n  height: 48px !important;\n  line-height: 24px !important;\n  font-weight: normal !important;\n  z-index: 10;\n  font-size: 1.4em;\n  padding-top: 0px !important;\n  padding-bottom: 0px !important; }\n\n.div-title {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  border: none;\n  font-size: 30px;\n  color: white;\n  text-align: center;\n  padding: 10px; }\n\n.h-header.green {\n  --background: #0196d0;\n  color: #ffffff; }\n\n.h-header.light-blue {\n  --background: #b81515;\n  color: #ffffff; }\n\n.background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  size: 0.3em; }\n\n.text-size-s.font {\n  font-size: 1rem !important; }\n\n.text-size-r {\n  font-size: 15px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px; }\n\n.img-size-s.padding {\n  padding: 1px 1px 1px 1px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  size: 0.8em; }\n\n.text-size-m-color {\n  font-size: 13px;\n  color: #061259; }\n\n.text-size-g {\n  font-size: 20px; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.arrow-next {\n  text-align: center;\n  float: right; }\n\n.arrow-back {\n  text-align: center;\n  float: left;\n  margin-left: 10px; }\n\n.center-text {\n  width: 100%;\n  text-align: center;\n  margin-right: 10px; }\n\n.icon-alert {\n  margin-top: 25%;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGFnZXMvbGl2ZS9EOlxcRG9jdW1lbnRzXFxwcm9ncmFtbWluZyBwcm9qZWN0c1xcYXBwTWFzU3BvcnQvc3JjXFxhcHBcXHBhZ2VzXFxsaXZlXFxsaXZlLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFDQTtFQUNJLGFBQWE7RUFDYix1QkFBdUIsRUFBQTs7QUFFM0I7RUFDTSxtQkFBbUIsRUFBQTs7QUFFekI7RUFDSSwyQkFBMkIsRUFBQTs7QUFHL0I7RUFDSSxrQkFBa0I7RUFDbEIsa0JBQWtCO0VBQ2xCLHVCQUF1QjtFQUN2Qiw0QkFBNEI7RUFDNUIsOEJBQThCO0VBQzlCLFdBQVc7RUFDWCxnQkFBZ0I7RUFDaEIsMkJBQTJCO0VBQzNCLDhCQUE4QixFQUFBOztBQUlsQztFQUNJLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFlBQVk7RUFDWixlQUFlO0VBQ2YsWUFBWTtFQUNaLGtCQUFrQjtFQUNsQixhQUFhLEVBQUE7O0FBR2pCO0VBQ0kscUJBQWE7RUFDYixjQUFjLEVBQUE7O0FBR2xCO0VBQ0kscUJBQWE7RUFDYixjQUFjLEVBQUE7O0FBR2xCO0VBQ0ksZ0JBQWdCO0VBQ2hCLDBCQUFzQjtVQUF0QixzQkFBc0I7RUFDdEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVk7RUFDWixlQUFlO0VBQ2YsWUFBWSxFQUFBOztBQUVoQjtFQUNJLGVBQWU7RUFDZixXQUFXO0VBQ1gsWUFBWTtFQUNaLFdBQVcsRUFBQTs7QUFHZjtFQUNJLDBCQUEwQixFQUFBOztBQUc5QjtFQUNJLGVBQWU7RUFDZixXQUFXO0VBQ1gsWUFBWTtFQUNaLGNBQWMsRUFBQTs7QUFFbEI7RUFDSSxlQUFlLEVBQUE7O0FBR25CO0VBQ0ksWUFBWTtFQUNaLGtCQUFrQjtFQUNsQixlQUFlLEVBQUE7O0FBRW5CO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLFdBQVc7RUFDWCxZQUFZLEVBQUE7O0FBR2hCO0VBQ0ksd0JBQXdCLEVBQUE7O0FBRzVCO0VBQ0csYUFBYTtFQUNiLHVCQUF1QjtFQUN2QixtQkFBbUI7RUFDbkIsV0FBVztFQUNYLFlBQVk7RUFDWixlQUFlO0VBQ2YsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUVyQjtFQUNJLFdBQVc7RUFDWCxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxZQUFZLEVBQUE7O0FBRWhCO0VBQ0ksa0JBQWtCO0VBQ2xCLGFBQWE7RUFDYixxQ0FBeUQ7RUFDekQsc0JBQXNCO0VBQ3RCLDJCQUEyQixFQUFBOztBQUUvQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLFNBQVMsRUFBQTs7QUFFYjtFQUNJLFdBQVcsRUFBQTs7QUFFZjtFQUNJLGVBQWU7RUFDZixjQUFjLEVBQUE7O0FBRWxCO0VBQ0ksZUFBZSxFQUFBOztBQUVuQjtFQUNFLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFcEI7RUFDSSxrQkFBaUI7RUFDakIsU0FBUztFQUNULE9BQU07RUFDTixRQUFRO0VBQ1IsY0FBYTtFQUNiLFlBQVc7RUFDWCxhQUFhO0VBQ2IsaUJBQWlCO0VBQ2pCLG1EQUFpRDtFQUNqRCxrQkFBa0I7RUFDbEIsa0JBQWlCO0VBQ2pCLGNBQW9CO0VBQ3BCLGdCQUFlO0VBQ2YseUJBQXlCO0VBQ3pCLG1CQUFtQjtFQUNuQixpQkFBaUI7RUFDakIscUJBQXFCLEVBQUE7O0FBRXpCO0VBQ0Usa0JBQWtCO0VBQ2xCLFlBQVksRUFBQTs7QUFFZDtFQUNFLGtCQUFrQjtFQUNsQixXQUFXO0VBQ1gsaUJBQWlCLEVBQUE7O0FBRW5CO0VBQ0UsV0FBVztFQUNYLGtCQUFrQjtFQUNqQixrQkFBa0IsRUFBQTs7QUFFckI7RUFDRSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixlQUFlO0VBQ2YsaUJBQWlCO0VBQ2pCLGNBQWMsRUFBQSIsImZpbGUiOiJzcmMvYXBwL3BhZ2VzL2xpdmUvbGl2ZS5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyJcclxuLmNvbC1tIHtcclxuICAgIGRpc3BsYXk6IGZsZXg7IFxyXG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbn1cclxuLmNvbC1tLnJlZnJlc2gge1xyXG4gICAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG59XHJcbi5saXN0LXBhZGRpbmcge1xyXG4gICAgcGFkZGluZy10b3A6IDBweCAhaW1wb3J0YW50O1xyXG59XHJcblxyXG4uaC1oZWFkZXIge1xyXG4gICAgcG9zaXRpb246IHJlbGF0aXZlO1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiA0OHB4ICFpbXBvcnRhbnQ7XHJcbiAgICBsaW5lLWhlaWdodDogMjRweCAhaW1wb3J0YW50O1xyXG4gICAgZm9udC13ZWlnaHQ6IG5vcm1hbCAhaW1wb3J0YW50O1xyXG4gICAgei1pbmRleDogMTA7XHJcbiAgICBmb250LXNpemU6IDEuNGVtO1xyXG4gICAgcGFkZGluZy10b3A6IDBweCAhaW1wb3J0YW50O1xyXG4gICAgcGFkZGluZy1ib3R0b206IDBweCAhaW1wb3J0YW50O1xyXG4gICAgXHJcbn1cclxuXHJcbi5kaXYtdGl0bGUge1xyXG4gICAgZGlzcGxheTogZmxleDtcclxuICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgIGJvcmRlcjogbm9uZTtcclxuICAgIGZvbnQtc2l6ZTogMzBweDtcclxuICAgIGNvbG9yOiB3aGl0ZTtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICAgIHBhZGRpbmc6IDEwcHg7XHJcbn1cclxuXHJcbi5oLWhlYWRlci5ncmVlbiB7XHJcbiAgICAtLWJhY2tncm91bmQ6ICMwMTk2ZDA7XHJcbiAgICBjb2xvcjogI2ZmZmZmZjtcclxufVxyXG5cclxuLmgtaGVhZGVyLmxpZ2h0LWJsdWUge1xyXG4gICAgLS1iYWNrZ3JvdW5kOiAjYjgxNTE1O1xyXG4gICAgY29sb3I6ICNmZmZmZmY7XHJcbn1cclxuXHJcbi5iYWNrZ3JvdW5kLXJlZCB7XHJcbiAgICBwYWRkaW5nLXRvcDogNXB4O1xyXG4gICAgcGFkZGluZy1ibG9jay1lbmQ6IDVweDtcclxuICAgIGJhY2tncm91bmQ6IHJlZDtcclxufVxyXG4ud2hpdGUtcmlnaHQge1xyXG4gICAgY29sb3I6IHdoaXRlO1xyXG4gICAgZm9udC1zaXplOiAxNnB4O1xyXG4gICAgZmxvYXQ6IHJpZ2h0OyBcclxufVxyXG4udGV4dC1zaXplLXN7XHJcbiAgICBmb250LXNpemU6IDEzcHg7XHJcbiAgICB3aWR0aDogMTAwJTtcclxuICAgIGZsb2F0OiByaWdodDtcclxuICAgIHNpemU6IDAuM2VtO1xyXG59XHJcblxyXG4udGV4dC1zaXplLXMuZm9udHtcclxuICAgIGZvbnQtc2l6ZTogMXJlbSAhaW1wb3J0YW50O1xyXG59XHJcblxyXG4udGV4dC1zaXplLXJ7XHJcbiAgICBmb250LXNpemU6IDE1cHg7XHJcbiAgICB3aWR0aDogMTAwJTtcclxuICAgIGZsb2F0OiByaWdodDtcclxuICAgIGNvbG9yOiAjMDM3N0I0O1xyXG59XHJcbi5pb24taWNvbiB7XHJcbiAgICBmb250LXNpemU6IDY0cHg7XHJcbiAgfVxyXG5cclxuLndoaXRlLWxlZnR7XHJcbiAgICBjb2xvcjogd2hpdGU7XHJcbiAgICBwYWRkaW5nLWxlZnQ6IDE4cHg7XHJcbiAgICBmb250LXNpemU6IDE2cHg7XHJcbn1cclxuLndoaXRle1xyXG4gICAgY29sb3I6IHdoaXRlO1xyXG59XHJcbi5pbWctc2l6ZS1ze1xyXG4gICAgd2lkdGg6IDQwcHg7XHJcbiAgICBoZWlnaHQ6IDQwcHg7XHJcbn1cclxuXHJcbi5pbWctc2l6ZS1zLnBhZGRpbmcge1xyXG4gICAgcGFkZGluZzogMXB4IDFweCAxcHggMXB4O1xyXG59XHJcblxyXG4uZGl2e1xyXG4gICBkaXNwbGF5OiBmbGV4O1xyXG4gICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgd2lkdGg6IDEwMCU7XHJcbiAgIGJvcmRlcjogbm9uZTtcclxuICAgZm9udC1zaXplOiAzMHB4O1xyXG4gICBjb2xvcjogIzc3Nzc3NztcclxuICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG59XHJcbi5kaXYtd2lkdGh7XHJcbiAgICB3aWR0aDogMTAwJTtcclxuICAgIHRleHQtYWxpZ246IHJpZ2h0O1xyXG59XHJcbi5yaWdodHtcclxuICAgIGZsb2F0OiByaWdodDtcclxufVxyXG4uYmFja2dyb3VuZHtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICAgIGhlaWdodDogMTkwcHg7XHJcbiAgICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCIuLi8uLi8uLi9hc3NldHMvaW1nL2JhY2stcHRzLnBuZ1wiKTtcclxuICAgIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XHJcbiAgICBiYWNrZ3JvdW5kLXBvc2l0aW9uOiBjZW50ZXI7XHJcbn1cclxuLm1hcmdpbi10b3AtbXtcclxuICAgIG1hcmdpbi10b3A6IDAuOWVtO1xyXG59XHJcbi5tYXJnaW4tdG9wLXN7XHJcbiAgICBtYXJnaW4tdG9wOiAwLjVlbTtcclxufVxyXG4ubWFyZ2luLXRvcC14c3tcclxuICAgIG1hcmdpbi10b3A6IDAuOGVtO1xyXG59XHJcbi50ZXh0LXNpemUtbHtcclxuICAgIHNpemU6IDFlbTtcclxufVxyXG4udGV4dC1zaXplLW17XHJcbiAgICBzaXplOiAwLjhlbTtcclxufVxyXG4udGV4dC1zaXplLW0tY29sb3J7XHJcbiAgICBmb250LXNpemU6IDEzcHg7XHJcbiAgICBjb2xvcjogIzA2MTI1OTtcclxufVxyXG4udGV4dC1zaXplLWd7XHJcbiAgICBmb250LXNpemU6IDIwcHg7XHJcbn1cclxuLmNlbnRlcntcclxuICBtYXJnaW46IDAgYXV0bztcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLmNpcmNsZS1yZWR7XHJcbiAgICBwb3NpdGlvbjphYnNvbHV0ZTtcclxuICAgIHRvcDogMzBweDtcclxuICAgIGxlZnQ6MDtcclxuICAgIHJpZ2h0OiAwO1xyXG4gICAgZGlzcGxheTpibG9jaztcclxuICAgIHdpZHRoOjEzMHB4O1xyXG4gICAgaGVpZ2h0OiAxMzBweDtcclxuICAgIGxpbmUtaGVpZ2h0OiAyNXB4O1xyXG4gICAgYmFja2dyb3VuZC1jb2xvcjogcmdiYSgyMjcsMzAsNDgsMC41KSAgIWltcG9ydGFudDtcclxuICAgIGJvcmRlci1yYWRpdXM6IDUwJTtcclxuICAgIHRleHQtYWxpZ246Y2VudGVyO1xyXG4gICAgY29sb3I6IHJnYigwLDQwLDEyMCk7XHJcbiAgICBmb250LXNpemU6MS44ZW07XHJcbiAgICBtYXJnaW46IDAgYXV0byAhaW1wb3J0YW50O1xyXG4gICAgYm9yZGVyLXN0eWxlOiBzb2xpZDtcclxuICAgIGJvcmRlci13aWR0aDogMnB4O1xyXG4gICAgYm9yZGVyLWNvbG9yOiAjZmZmZmZmO1xyXG59XHJcbi5hcnJvdy1uZXh0IHtcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbiAgZmxvYXQ6IHJpZ2h0O1xyXG59XHJcbi5hcnJvdy1iYWNrIHtcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbiAgZmxvYXQ6IGxlZnQ7XHJcbiAgbWFyZ2luLWxlZnQ6IDEwcHg7XHJcbn1cclxuLmNlbnRlci10ZXh0e1xyXG4gIHdpZHRoOiAxMDAlO1xyXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICAgbWFyZ2luLXJpZ2h0OiAxMHB4OyAgXHJcbn1cclxuLmljb24tYWxlcnR7XHJcbiAgbWFyZ2luLXRvcDogMjUlO1xyXG4gIHdpZHRoOiA2MHB4O1xyXG4gIGhlaWdodDogNjBweDtcclxuICBmb250LXNpemU6IDM1cHg7XHJcbiAgZm9udC13ZWlnaHQ6IGJvbGQ7XHJcbiAgY29sb3I6ICM3Nzc3Nzc7XHJcbn0iXX0= */"

/***/ }),

/***/ "./src/app/pages/live/live.page.ts":
/*!*****************************************!*\
  !*** ./src/app/pages/live/live.page.ts ***!
  \*****************************************/
/*! exports provided: LivePage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LivePage", function() { return LivePage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _services_live_live_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/live/live.service */ "./src/app/services/live/live.service.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! moment */ "./node_modules/moment/moment.js");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_4___default = /*#__PURE__*/__webpack_require__.n(moment__WEBPACK_IMPORTED_MODULE_4__);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");
/* harmony import */ var _services_client_client_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../services/client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");









var LivePage = /** @class */ (function () {
    function LivePage(liveService, http, router, loading, zone, client, ionRouter) {
        this.liveService = liveService;
        this.http = http;
        this.router = router;
        this.loading = loading;
        this.zone = zone;
        this.client = client;
        this.ionRouter = ionRouter;
        this.tab = 0;
        this.status_color1 = '#db7f1c';
        this.status_color2 = '#92d822';
        this.status_color3 = '#061259';
        this.clientId = localStorage.getItem('id_client');
    }
    LivePage.prototype.ngOnInit = function () {
        var _this = this;
        this.loading.presentLoading();
        this.http.get(this.client.getClientById(this.clientId))
            .subscribe(function (resp) {
            _this.info = resp['response'];
            _this.login = _this.info.login;
            _this.status = _this.info.status;
            if (_this.login === "guest" && _this.status !== 1) {
                _this.loading.presentAlertConfirm('Alert', '', 'Esta sección es sólo para usuarios +Móvil que se han suscrito a +Sports. ¿Le gustaría suscribirse para desbloquear esta sección?', [
                    {
                        text: 'Cancelar',
                        handler: function () {
                            _this.ionRouter.back();
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
            else {
                _this.tab = 0;
                moment__WEBPACK_IMPORTED_MODULE_4__["locale"]('es');
                _this.date_day = moment__WEBPACK_IMPORTED_MODULE_4__().format('dddd D[,] YYYY');
                _this.liveMatch();
            }
        });
    };
    LivePage.prototype.liveMatch = function () {
        var _this = this;
        this.tab = 0;
        var date = moment__WEBPACK_IMPORTED_MODULE_4__().format('YYYYMMDD');
        this.loading.presentLoading();
        this.http.get(this.liveService.allMatchesLive(date))
            .subscribe(function (resp) {
            _this.data = resp['response'].leagues;
            _this.sizeData = _this.data.length;
        });
    };
    LivePage.prototype.matchDate = function (date) {
        moment__WEBPACK_IMPORTED_MODULE_4__["locale"]('es');
        return moment__WEBPACK_IMPORTED_MODULE_4__(date).format('DD MMM YYYY');
    };
    LivePage.prototype.matchTime = function (date) {
        moment__WEBPACK_IMPORTED_MODULE_4__["locale"]('es');
        return moment__WEBPACK_IMPORTED_MODULE_4__(date, 'YYYY/MM/DD HH:mm:ss').format('HH:mm');
    };
    LivePage.prototype.macthStatus = function (status) {
        if (status === 1) {
            return 'Jugado';
        }
        else if (status === 2) {
            return 'Jugando';
        }
        else {
            return 'Programado';
        }
    };
    LivePage.prototype.detailMatchLive = function (leagues, match) {
        var _this = this;
        this.tab++;
        this.loading.presentLoading();
        this.refresh();
        this.http.get(this.liveService.matchLive(leagues.id_competitions, match.id_game_matches))
            .subscribe(function (resp) {
            _this.date_match = resp['response'];
            _this.info_match = _this.date_match;
            if (_this.date_match) {
                _this.name_leagues = leagues.competiton_type.name;
                _this.logo_leagues = leagues.competiton_type.competition_logo;
                _this.id_leagues = leagues.id_competitions;
                _this.leaguesOn = leagues;
                _this.matchOn = match;
            }
        });
    };
    LivePage.prototype.mainPage = function () {
        this.liveMatch();
        this.refresh();
    };
    LivePage.prototype.refresh = function () {
        this.zone.run(function () {
            console.log('force update the screen');
        });
    };
    LivePage.prototype.refreshGame = function () {
        this.detailMatchLive(this.leaguesOn, this.matchOn);
        this.refresh();
    };
    LivePage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    LivePage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-live',
            template: __webpack_require__(/*! ./live.page.html */ "./src/app/pages/live/live.page.html"),
            styles: [__webpack_require__(/*! ./live.page.scss */ "./src/app/pages/live/live.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_live_live_service__WEBPACK_IMPORTED_MODULE_2__["LiveService"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_3__["HttpClient"],
            _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"],
            _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_6__["LoadingService"],
            _angular_core__WEBPACK_IMPORTED_MODULE_1__["NgZone"],
            _services_client_client_service__WEBPACK_IMPORTED_MODULE_7__["ClientService"],
            _angular_common__WEBPACK_IMPORTED_MODULE_8__["Location"]])
    ], LivePage);
    return LivePage;
}());



/***/ })

}]);
//# sourceMappingURL=live-live-module.js.map