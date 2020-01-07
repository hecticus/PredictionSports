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

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\" *ngIf=\"tab === 0 \">\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\n    </ion-buttons>   \n    <ion-buttons slot=\"start\" *ngIf=\"tab !== 0 \">\n       <ion-icon name=\"arrow-round-back\" color=\"primary\" \n                 class=\"arrow-back\" size=\"large\" \n                 (click)=\"mainPage()\"></ion-icon>\n    </ion-buttons>  \n    <div class=\"center\">\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\n    </div>\n  </ion-toolbar>\n</ion-header>\n<ion-content *ngIf=\"sizeData > 0 && tab === 0\">\n   <ion-item class=\"h-header green text-size-g\">\n    <ion-label> {{date_day}}  </ion-label>\n  </ion-item>\n  <ion-list *ngFor =\"let leagues of data\" class=\"list-padding\">\n    <ion-item color=\"danger\">\n      <ion-grid>\n        <ion-row >\n          <ion-col size=\"12\" class=\"col-m\">\n            <img class=\"img-size-s padding\" src=\"{{leagues.competiton_type.competition_logo}}.svg\">\n            <div class=\"div-title\">\n                <ion-label class=\"text-size-s font\">{{leagues.competiton_type.name}}</ion-label>\n            </div>               \n          </ion-col>                        \n        </ion-row>\n      </ion-grid>\n    </ion-item>    \n    <ion-list *ngFor =\"let team of leagues.fixtures\">\n      <ion-item (click)=\"detailMatchLive(leagues, team)\">          \n        <ion-grid >\n          <ion-row >\n            <ion-col size=\"4\">\n              <div class=\"center-text\">\n                <img class=\"img-size-s\"  *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.homeTeam.team_logo}}\">\n                 <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\n                <ion-label class=\"text-size-m-color\">\n                    {{team.homeTeam.short_name}}                \n                </ion-label>\n              </div>\n            </ion-col>\n\n            <ion-col size=\"4\" >\n              <div class=\"center-text\">\n                <ion-label class=\"text-size-g-color\" *ngIf=\"team.id_status === 1 || team.id_status === 2\"> \n                      {{team.home_team_goals}} - {{team.away_team_goals}}\n                </ion-label>\n                <div class=\"center-text\">\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(team.date)\"></span>\n                </div>\n                <div  *ngIf=\"team.id_status === 1\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                  <div  *ngIf=\"team.id_status === 2\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                  <div *ngIf=\"team.id_status === 3\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\n                  </ion-label>\n                </div>\n              </div>\n            </ion-col>\n\n            <ion-col size=\"4\">\n              <div class=\"center-text\">\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.awayTeam.team_logo}}\">\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\n                <ion-label class=\"text-size-m-color\">\n                    {{team.awayTeam.short_name}}\n                </ion-label>\n              </div>\n            </ion-col>\n          </ion-row>\n        </ion-grid>\n      </ion-item>\n    </ion-list>\n  </ion-list>  \n</ion-content>\n<ion-content *ngIf=\"sizeData === 0\">\n  <ion-item class=\"h-header green text-size-g\">\n      <ion-label> {{date_day}}  </ion-label>\n  </ion-item>\n  <div class=\"div\">\n    <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\n  </div>\n  <div class=\"div\">\n     No hay juegos programados para hoy. \n  </div>      \n</ion-content>\n\n<ion-content *ngIf=\"tab !== 0 && info_match !== undefined \">  \n  <ion-list class=\"list-padding\">\n    <ion-item color=\"danger\">\n      <ion-grid>\n        <ion-row >\n          <ion-col size=\"11\" class=\"col-m\">\n            <img class=\"img-size-s padding\"  src=\"{{logo_leagues}}.svg\">\n            <div class=\"div-title\">\n                <ion-label class=\"text-size-s font\">{{name_leagues}}</ion-label>\n            </div>               \n          </ion-col> \n           <ion-col size=\"1\" class=\"col-m refresh\"  (click)=\"refreshGame()\"> \n            <ion-icon ios=\"ios-refresh\" md=\"md-refresh\" size=\"large\"></ion-icon>\n           </ion-col>                       \n        </ion-row>\n      </ion-grid>\n    </ion-item>    \n   <ion-item>          \n        <ion-grid >\n          <ion-row >\n            <ion-col size=\"4\">\n              <div class=\"center-text\">\n                <img class=\"img-size-s\" *ngIf=\"id_leagues !== 67\" src=\"{{info_match.home_team.team_logo}}\">\n                <img class=\"img-size-s\" *ngIf=\"id_leagues === 67\" src=\"./assets/img/favicon.png\" >\n                <ion-label class=\"text-size-m-color\">\n                    {{info_match.home_team.short_name}}                \n                </ion-label>\n              </div>\n            </ion-col>\n            <ion-col size=\"4\" *ngIf=\"date_match.status\">\n              <div class=\"center-text\">\n                <ion-label class=\"text-size-g-color\" \n                *ngIf=\"info_match.status?.id_status === 1 || info_match.status?.id_status === 2\"> \n                      {{info_match.home_team_goals}} - {{info_match.away_team_goals}}\n                </ion-label>\n                <ion-label class=\"text-size-g-color\" \n                *ngIf=\"info_match.status?.id_status === 3\"> \n                       {{info_match.home_team_goals}} - {{info_match.away_team_goals}}\n                </ion-label>\n                <div class=\"center-text\">\n                  <span class=\"text-size-m-color\"></span>\n                </div>\n                <div  *ngIf=\"info_match.status?.id_status === 1\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\n                    <strong [innerHTML]=\"macthStatus(info_match.status?.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                  <div  *ngIf=\"info_match.status?.id_status === 2\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\n                    <strong [innerHTML]=\"macthStatus(info_match.status?.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                  <div *ngIf=\"info_match.status?.id_status === 3\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\n                    <strong [innerHTML]=\"macthStatus(info_match.status?.id_status)\"></strong>\n                  </ion-label>\n                </div>\n              </div>\n            </ion-col>\n            <ion-col size=\"4\">\n              <div class=\"center-text\">\n                <img class=\"img-size-s\" *ngIf=\"id_leagues !== 67\" src=\"{{info_match.away_team.team_logo}}\">\n                <img class=\"img-size-s\" *ngIf=\"id_leagues === 67\" src=\"./assets/img/favicon.png\" >\n                <ion-label class=\"text-size-m-color\">\n                    {{info_match.away_team.short_name}}\n                </ion-label>\n              </div>\n            </ion-col>\n          </ion-row>\n        </ion-grid>\n      </ion-item>  \n  </ion-list>\n</ion-content>"

/***/ }),

/***/ "./src/app/pages/live/live.page.scss":
/*!*******************************************!*\
  !*** ./src/app/pages/live/live.page.scss ***!
  \*******************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".col-m {\n  display: flex;\n  justify-content: center; }\n\n.col-m.refresh {\n  align-items: center; }\n\n.list-padding {\n  padding-top: 0px !important; }\n\n.h-header {\n  position: relative;\n  text-align: center;\n  height: 48px !important;\n  line-height: 24px !important;\n  font-weight: normal !important;\n  z-index: 10;\n  font-size: 1.4em;\n  padding-top: 0px !important;\n  padding-bottom: 0px !important; }\n\n.div-title {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  border: none;\n  font-size: 30px;\n  color: white;\n  text-align: center;\n  padding: 10px; }\n\n.h-header.green {\n  --background: #0196d0;\n  color: #ffffff; }\n\n.h-header.light-blue {\n  --background: #b81515;\n  color: #ffffff; }\n\n.background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  size: 0.3em; }\n\n.text-size-s.font {\n  font-size: 1rem !important; }\n\n.text-size-r {\n  font-size: 15px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px; }\n\n.img-size-s.padding {\n  padding: 1px 1px 1px 1px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  size: 0.8em; }\n\n.text-size-m-color {\n  font-size: 13px;\n  color: #061259; }\n\n.text-size-g {\n  font-size: 20px; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.arrow-next {\n  text-align: center;\n  float: right; }\n\n.arrow-back {\n  text-align: center;\n  float: left;\n  margin-left: 10px; }\n\n.center-text {\n  width: 100%;\n  text-align: center;\n  margin-right: 10px; }\n\n.icon-alert {\n  margin-top: 25%;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL2JyYXlhbi9hcHBNYXNTcG9ydC9zcmMvYXBwL3BhZ2VzL2xpdmUvbGl2ZS5wYWdlLnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQ0E7RUFDSSxhQUFhO0VBQ2IsdUJBQXVCLEVBQUE7O0FBRTNCO0VBQ00sbUJBQW1CLEVBQUE7O0FBRXpCO0VBQ0ksMkJBQTJCLEVBQUE7O0FBRy9CO0VBQ0ksa0JBQWtCO0VBQ2xCLGtCQUFrQjtFQUNsQix1QkFBdUI7RUFDdkIsNEJBQTRCO0VBQzVCLDhCQUE4QjtFQUM5QixXQUFXO0VBQ1gsZ0JBQWdCO0VBQ2hCLDJCQUEyQjtFQUMzQiw4QkFBOEIsRUFBQTs7QUFJbEM7RUFDSSxhQUFhO0VBQ2IsdUJBQXVCO0VBQ3ZCLG1CQUFtQjtFQUNuQixZQUFZO0VBQ1osZUFBZTtFQUNmLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsYUFBYSxFQUFBOztBQUdqQjtFQUNJLHFCQUFhO0VBQ2IsY0FBYyxFQUFBOztBQUdsQjtFQUNJLHFCQUFhO0VBQ2IsY0FBYyxFQUFBOztBQUdsQjtFQUNJLGdCQUFnQjtFQUNoQiwwQkFBc0I7VUFBdEIsc0JBQXNCO0VBQ3RCLGVBQWUsRUFBQTs7QUFFbkI7RUFDSSxZQUFZO0VBQ1osZUFBZTtFQUNmLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixXQUFXLEVBQUE7O0FBR2Y7RUFDSSwwQkFBMEIsRUFBQTs7QUFHOUI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixjQUFjLEVBQUE7O0FBRWxCO0VBQ0ksZUFBZSxFQUFBOztBQUduQjtFQUNJLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxXQUFXO0VBQ1gsWUFBWSxFQUFBOztBQUdoQjtFQUNJLHdCQUF3QixFQUFBOztBQUc1QjtFQUNHLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFdBQVc7RUFDWCxZQUFZO0VBQ1osZUFBZTtFQUNmLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFckI7RUFDSSxXQUFXO0VBQ1gsaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLGtCQUFrQjtFQUNsQixhQUFhO0VBQ2IscUNBQXlEO0VBQ3pELHNCQUFzQjtFQUN0QiwyQkFBMkIsRUFBQTs7QUFFL0I7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxTQUFTLEVBQUE7O0FBRWI7RUFDSSxXQUFXLEVBQUE7O0FBRWY7RUFDSSxlQUFlO0VBQ2YsY0FBYyxFQUFBOztBQUVsQjtFQUNJLGVBQWUsRUFBQTs7QUFFbkI7RUFDRSxjQUFjO0VBQ2Qsa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0ksa0JBQWlCO0VBQ2pCLFNBQVM7RUFDVCxPQUFNO0VBQ04sUUFBUTtFQUNSLGNBQWE7RUFDYixZQUFXO0VBQ1gsYUFBYTtFQUNiLGlCQUFpQjtFQUNqQixtREFBaUQ7RUFDakQsa0JBQWtCO0VBQ2xCLGtCQUFpQjtFQUNqQixjQUFvQjtFQUNwQixnQkFBZTtFQUNmLHlCQUF5QjtFQUN6QixtQkFBbUI7RUFDbkIsaUJBQWlCO0VBQ2pCLHFCQUFxQixFQUFBOztBQUV6QjtFQUNFLGtCQUFrQjtFQUNsQixZQUFZLEVBQUE7O0FBRWQ7RUFDRSxrQkFBa0I7RUFDbEIsV0FBVztFQUNYLGlCQUFpQixFQUFBOztBQUVuQjtFQUNFLFdBQVc7RUFDWCxrQkFBa0I7RUFDakIsa0JBQWtCLEVBQUE7O0FBRXJCO0VBQ0UsZUFBZTtFQUNmLFdBQVc7RUFDWCxZQUFZO0VBQ1osZUFBZTtFQUNmLGlCQUFpQjtFQUNqQixjQUFjLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9wYWdlcy9saXZlL2xpdmUucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiXG4uY29sLW0ge1xuICAgIGRpc3BsYXk6IGZsZXg7IFxuICAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xufVxuLmNvbC1tLnJlZnJlc2gge1xuICAgICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcbn1cbi5saXN0LXBhZGRpbmcge1xuICAgIHBhZGRpbmctdG9wOiAwcHggIWltcG9ydGFudDtcbn1cblxuLmgtaGVhZGVyIHtcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xuICAgIGhlaWdodDogNDhweCAhaW1wb3J0YW50O1xuICAgIGxpbmUtaGVpZ2h0OiAyNHB4ICFpbXBvcnRhbnQ7XG4gICAgZm9udC13ZWlnaHQ6IG5vcm1hbCAhaW1wb3J0YW50O1xuICAgIHotaW5kZXg6IDEwO1xuICAgIGZvbnQtc2l6ZTogMS40ZW07XG4gICAgcGFkZGluZy10b3A6IDBweCAhaW1wb3J0YW50O1xuICAgIHBhZGRpbmctYm90dG9tOiAwcHggIWltcG9ydGFudDtcbiAgICBcbn1cblxuLmRpdi10aXRsZSB7XG4gICAgZGlzcGxheTogZmxleDtcbiAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcbiAgICBhbGlnbi1pdGVtczogY2VudGVyO1xuICAgIGJvcmRlcjogbm9uZTtcbiAgICBmb250LXNpemU6IDMwcHg7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgICBwYWRkaW5nOiAxMHB4O1xufVxuXG4uaC1oZWFkZXIuZ3JlZW4ge1xuICAgIC0tYmFja2dyb3VuZDogIzAxOTZkMDtcbiAgICBjb2xvcjogI2ZmZmZmZjtcbn1cblxuLmgtaGVhZGVyLmxpZ2h0LWJsdWUge1xuICAgIC0tYmFja2dyb3VuZDogI2I4MTUxNTtcbiAgICBjb2xvcjogI2ZmZmZmZjtcbn1cblxuLmJhY2tncm91bmQtcmVkIHtcbiAgICBwYWRkaW5nLXRvcDogNXB4O1xuICAgIHBhZGRpbmctYmxvY2stZW5kOiA1cHg7XG4gICAgYmFja2dyb3VuZDogcmVkO1xufVxuLndoaXRlLXJpZ2h0IHtcbiAgICBjb2xvcjogd2hpdGU7XG4gICAgZm9udC1zaXplOiAxNnB4O1xuICAgIGZsb2F0OiByaWdodDsgXG59XG4udGV4dC1zaXplLXN7XG4gICAgZm9udC1zaXplOiAxM3B4O1xuICAgIHdpZHRoOiAxMDAlO1xuICAgIGZsb2F0OiByaWdodDtcbiAgICBzaXplOiAwLjNlbTtcbn1cblxuLnRleHQtc2l6ZS1zLmZvbnR7XG4gICAgZm9udC1zaXplOiAxcmVtICFpbXBvcnRhbnQ7XG59XG5cbi50ZXh0LXNpemUtcntcbiAgICBmb250LXNpemU6IDE1cHg7XG4gICAgd2lkdGg6IDEwMCU7XG4gICAgZmxvYXQ6IHJpZ2h0O1xuICAgIGNvbG9yOiAjMDM3N0I0O1xufVxuLmlvbi1pY29uIHtcbiAgICBmb250LXNpemU6IDY0cHg7XG4gIH1cblxuLndoaXRlLWxlZnR7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIHBhZGRpbmctbGVmdDogMThweDtcbiAgICBmb250LXNpemU6IDE2cHg7XG59XG4ud2hpdGV7XG4gICAgY29sb3I6IHdoaXRlO1xufVxuLmltZy1zaXplLXN7XG4gICAgd2lkdGg6IDQwcHg7XG4gICAgaGVpZ2h0OiA0MHB4O1xufVxuXG4uaW1nLXNpemUtcy5wYWRkaW5nIHtcbiAgICBwYWRkaW5nOiAxcHggMXB4IDFweCAxcHg7XG59XG5cbi5kaXZ7XG4gICBkaXNwbGF5OiBmbGV4O1xuICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XG4gICBhbGlnbi1pdGVtczogY2VudGVyO1xuICAgd2lkdGg6IDEwMCU7XG4gICBib3JkZXI6IG5vbmU7XG4gICBmb250LXNpemU6IDMwcHg7XG4gICBjb2xvcjogIzc3Nzc3NztcbiAgIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cbi5kaXYtd2lkdGh7XG4gICAgd2lkdGg6IDEwMCU7XG4gICAgdGV4dC1hbGlnbjogcmlnaHQ7XG59XG4ucmlnaHR7XG4gICAgZmxvYXQ6IHJpZ2h0O1xufVxuLmJhY2tncm91bmR7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xuICAgIGhlaWdodDogMTkwcHg7XG4gICAgYmFja2dyb3VuZC1pbWFnZTogdXJsKFwiLi4vLi4vLi4vYXNzZXRzL2ltZy9iYWNrLXB0cy5wbmdcIik7XG4gICAgYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcbiAgICBiYWNrZ3JvdW5kLXBvc2l0aW9uOiBjZW50ZXI7XG59XG4ubWFyZ2luLXRvcC1te1xuICAgIG1hcmdpbi10b3A6IDAuOWVtO1xufVxuLm1hcmdpbi10b3Atc3tcbiAgICBtYXJnaW4tdG9wOiAwLjVlbTtcbn1cbi5tYXJnaW4tdG9wLXhze1xuICAgIG1hcmdpbi10b3A6IDAuOGVtO1xufVxuLnRleHQtc2l6ZS1se1xuICAgIHNpemU6IDFlbTtcbn1cbi50ZXh0LXNpemUtbXtcbiAgICBzaXplOiAwLjhlbTtcbn1cbi50ZXh0LXNpemUtbS1jb2xvcntcbiAgICBmb250LXNpemU6IDEzcHg7XG4gICAgY29sb3I6ICMwNjEyNTk7XG59XG4udGV4dC1zaXplLWd7XG4gICAgZm9udC1zaXplOiAyMHB4O1xufVxuLmNlbnRlcntcbiAgbWFyZ2luOiAwIGF1dG87XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cbi5jaXJjbGUtcmVke1xuICAgIHBvc2l0aW9uOmFic29sdXRlO1xuICAgIHRvcDogMzBweDtcbiAgICBsZWZ0OjA7XG4gICAgcmlnaHQ6IDA7XG4gICAgZGlzcGxheTpibG9jaztcbiAgICB3aWR0aDoxMzBweDtcbiAgICBoZWlnaHQ6IDEzMHB4O1xuICAgIGxpbmUtaGVpZ2h0OiAyNXB4O1xuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMjI3LDMwLDQ4LDAuNSkgICFpbXBvcnRhbnQ7XG4gICAgYm9yZGVyLXJhZGl1czogNTAlO1xuICAgIHRleHQtYWxpZ246Y2VudGVyO1xuICAgIGNvbG9yOiByZ2IoMCw0MCwxMjApO1xuICAgIGZvbnQtc2l6ZToxLjhlbTtcbiAgICBtYXJnaW46IDAgYXV0byAhaW1wb3J0YW50O1xuICAgIGJvcmRlci1zdHlsZTogc29saWQ7XG4gICAgYm9yZGVyLXdpZHRoOiAycHg7XG4gICAgYm9yZGVyLWNvbG9yOiAjZmZmZmZmO1xufVxuLmFycm93LW5leHQge1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gIGZsb2F0OiByaWdodDtcbn1cbi5hcnJvdy1iYWNrIHtcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xuICBmbG9hdDogbGVmdDtcbiAgbWFyZ2luLWxlZnQ6IDEwcHg7XG59XG4uY2VudGVyLXRleHR7XG4gIHdpZHRoOiAxMDAlO1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gICBtYXJnaW4tcmlnaHQ6IDEwcHg7ICBcbn1cbi5pY29uLWFsZXJ0e1xuICBtYXJnaW4tdG9wOiAyNSU7XG4gIHdpZHRoOiA2MHB4O1xuICBoZWlnaHQ6IDYwcHg7XG4gIGZvbnQtc2l6ZTogMzVweDtcbiAgZm9udC13ZWlnaHQ6IGJvbGQ7XG4gIGNvbG9yOiAjNzc3Nzc3O1xufSJdfQ== */"

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