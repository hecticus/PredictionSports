(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["matches-matches-module"],{

/***/ "./src/app/pages/matches/matches.module.ts":
/*!*************************************************!*\
  !*** ./src/app/pages/matches/matches.module.ts ***!
  \*************************************************/
/*! exports provided: MatchesPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatchesPageModule", function() { return MatchesPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _matches_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./matches.page */ "./src/app/pages/matches/matches.page.ts");







var routes = [
    {
        path: '',
        component: _matches_page__WEBPACK_IMPORTED_MODULE_6__["MatchesPage"]
    }
];
var MatchesPageModule = /** @class */ (function () {
    function MatchesPageModule() {
    }
    MatchesPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
            ],
            declarations: [_matches_page__WEBPACK_IMPORTED_MODULE_6__["MatchesPage"]]
        })
    ], MatchesPageModule);
    return MatchesPageModule;
}());



/***/ }),

/***/ "./src/app/pages/matches/matches.page.html":
/*!*************************************************!*\
  !*** ./src/app/pages/matches/matches.page.html ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\n    </ion-buttons>   \n    <div class=\"center\">\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\n    </div>\n  </ion-toolbar>\n</ion-header>\n<ion-content *ngIf=\"sizeData > 0\">\n  <ion-item class=\"h-header green text-size-g\" >\n      <ion-grid>\n          <ion-row >\n            <ion-col size=\"4\" class=\"col-m\" (click)=\"prevPage(old_date)\">\n              <div class=\"h-header green col text-size-g\">\n                  <ion-label class=\"text-size-s font\">\n                    {{old_date}}\n                  </ion-label>\n              </div>               \n            </ion-col>  \n            <ion-col size=\"4\" class=\"col-m\">\n              <div class=\"h-header green col text-size-g\">\n                  <ion-label class=\"text-size-s font\" [innerHTML]=\"currentDate()\"> \n                  </ion-label>\n              </div>               \n            </ion-col> \n            <ion-col size=\"4\" class=\"col-m\" (click)=\"nextPage(new_date)\">\n              <div class=\"h-header green col text-size-g\">\n                  <ion-label class=\"text-size-s font\">\n                    {{new_date}}\n                  </ion-label>\n              </div>               \n            </ion-col>                       \n          </ion-row>\n      </ion-grid>\n  </ion-item>\n  \n  <ion-list *ngFor =\"let leagues of data\" class=\"list-padding\">\n\n    <ion-item color=\"danger\">\n      <ion-grid>\n        <ion-row >\n          <ion-col size=\"12\" class=\"col-m\">\n            <img class=\"img-size-s padding\" src=\"{{leagues.competiton_type.competition_logo}}.svg\">         \n            <div class=\"div-title\">\n                <ion-label class=\"text-size-s font\">{{leagues.competiton_type.name}}</ion-label>\n            </div>               \n          </ion-col>                        \n        </ion-row>\n      </ion-grid>\n    </ion-item>    \n    <ion-list *ngFor =\"let team of leagues.fixtures\">\n      <ion-item >          \n        <ion-grid >\n          <ion-row >\n            <ion-col size=\"4\">\n              <div class=\"center-text\">\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.homeTeam.team_logo}}\">\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\n                <ion-label class=\"text-size-m-color\">\n                    {{team.homeTeam.short_name}}                \n                </ion-label>\n              </div>\n            </ion-col>\n            <ion-col size=\"4\" >\n              <div class=\"center-text\">\n                <ion-label class=\"text-size-g-color\" *ngIf=\"team.id_status === 1 || team.id_status === 2\"> \n                      {{team.home_team_goals}} - {{team.away_team_goals}}\n                </ion-label>\n                <div class=\"center-text\">\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(team.date)\"></span>\n                </div>\n                <div  *ngIf=\"team.id_status === 1\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                  <div  *ngIf=\"team.id_status === 2\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\n                  </ion-label>\n                </div>\n                  <div *ngIf=\"team.id_status === 3\">\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\n                  </ion-label>\n                </div>\n              </div>\n            </ion-col>\n            <ion-col size=\"4\">\n              <div class=\"center-text\">\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.awayTeam.team_logo}}\">\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\n                <ion-label class=\"text-size-m-color\">\n                    {{team.awayTeam.short_name}}\n                </ion-label>\n              </div>\n            </ion-col>\n          </ion-row>\n        </ion-grid>\n      </ion-item>\n    </ion-list>\n  </ion-list>  \n</ion-content>\n<ion-content *ngIf=\"sizeData === 0\">\n  <ion-item class=\"h-header green text-size-g\" >\n  \n    <ion-grid>\n        <ion-row >\n            <ion-col size=\"4\" class=\"col-m\" (click)=\"prevPage(old_date)\">\n              <div class=\"h-header green col text-size-g\">\n                  <ion-label class=\"text-size-s font\">\n                    {{old_date}}\n                  </ion-label>\n              </div>               \n            </ion-col>  \n            <ion-col size=\"4\" class=\"col-m\">\n              <div class=\"h-header green col text-size-g\">\n                  <ion-label class=\"text-size-s font\"> \n                    {{date_day}}\n                  </ion-label>\n              </div>               \n            </ion-col> \n            <ion-col size=\"4\" class=\"col-m\" (click)=\"nextPage(new_date)\">\n              <div class=\"h-header green col text-size-g\">\n                  <ion-label class=\"text-size-s font\">\n                    {{new_date}}\n                  </ion-label>\n              </div>               \n            </ion-col>                       \n        </ion-row>\n    </ion-grid>\n\n  </ion-item>    \n  <div class=\"div\">\n    <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\n  </div>\n  <div class=\"div\">\n     No hay juegos programados para este día. \n  </div>      \n</ion-content>\n\n"

/***/ }),

/***/ "./src/app/pages/matches/matches.page.scss":
/*!*************************************************!*\
  !*** ./src/app/pages/matches/matches.page.scss ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".col-m {\n  display: flex;\n  justify-content: center; }\n\n.list-padding {\n  padding-top: 0px !important; }\n\n.h-header {\n  position: relative;\n  text-align: center;\n  height: 48px !important;\n  line-height: 24px !important;\n  font-weight: normal !important;\n  z-index: 10;\n  font-size: 1.4em;\n  padding-top: 0px !important;\n  padding-bottom: 0px !important; }\n\n.h-header.col {\n  height: 25px !important; }\n\n.div-title {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  border: none;\n  font-size: 30px;\n  color: white;\n  text-align: center;\n  padding: 10px; }\n\n.h-header.green {\n  --background: #0196d0;\n  color: #ffffff; }\n\n.h-header.light-blue {\n  --background: #b81515;\n  color: #ffffff; }\n\n.background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  size: 0.3em; }\n\n.text-size-s.font {\n  font-size: 1rem !important; }\n\n.text-size-r {\n  font-size: 15px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px; }\n\n.img-size-s.padding {\n  padding: 1px 1px 1px 1px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  size: 0.8em; }\n\n.text-size-m-color {\n  font-size: 13px;\n  color: #061259; }\n\n.text-size-g {\n  font-size: 20px; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.arrow-next {\n  text-align: center;\n  float: right; }\n\n.arrow-back {\n  text-align: center;\n  float: left; }\n\n.center-text {\n  width: 100%;\n  text-align: center;\n  margin-right: 10px; }\n\n.icon-alert {\n  margin-top: 25%;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL3lzaWRyby9wb3J0YWZvbGlvIGRlIHByb2dyYW1hY2lvbi9hcHBNYXNTcG9ydC9zcmMvYXBwL3BhZ2VzL21hdGNoZXMvbWF0Y2hlcy5wYWdlLnNjc3MiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IkFBQ0E7RUFDSSxhQUFhO0VBQ2IsdUJBQXVCLEVBQUE7O0FBRzNCO0VBQ0ksMkJBQTJCLEVBQUE7O0FBRy9CO0VBQ0ksa0JBQWtCO0VBQ2xCLGtCQUFrQjtFQUNsQix1QkFBdUI7RUFDdkIsNEJBQTRCO0VBQzVCLDhCQUE4QjtFQUM5QixXQUFXO0VBQ1gsZ0JBQWdCO0VBQ2hCLDJCQUEyQjtFQUMzQiw4QkFBOEIsRUFBQTs7QUFJbEM7RUFDSSx1QkFBdUIsRUFBQTs7QUFHM0I7RUFDSSxhQUFhO0VBQ2IsdUJBQXVCO0VBQ3ZCLG1CQUFtQjtFQUNuQixZQUFZO0VBQ1osZUFBZTtFQUNmLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsYUFBYSxFQUFBOztBQUdqQjtFQUNJLHFCQUFhO0VBQ2IsY0FBYyxFQUFBOztBQUdsQjtFQUNJLHFCQUFhO0VBQ2IsY0FBYyxFQUFBOztBQUdsQjtFQUNJLGdCQUFnQjtFQUNoQiwwQkFBc0I7VUFBdEIsc0JBQXNCO0VBQ3RCLGVBQWUsRUFBQTs7QUFFbkI7RUFDSSxZQUFZO0VBQ1osZUFBZTtFQUNmLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixXQUFXLEVBQUE7O0FBR2Y7RUFDSSwwQkFBMEIsRUFBQTs7QUFHOUI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixjQUFjLEVBQUE7O0FBRWxCO0VBQ0ksZUFBZSxFQUFBOztBQUduQjtFQUNJLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxXQUFXO0VBQ1gsWUFBWSxFQUFBOztBQUdoQjtFQUNJLHdCQUF3QixFQUFBOztBQUc1QjtFQUNHLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFdBQVc7RUFDWCxZQUFZO0VBQ1osZUFBZTtFQUNmLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFckI7RUFDSSxXQUFXO0VBQ1gsaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLGtCQUFrQjtFQUNsQixhQUFhO0VBQ2IscUNBQXlEO0VBQ3pELHNCQUFzQjtFQUN0QiwyQkFBMkIsRUFBQTs7QUFFL0I7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxTQUFTLEVBQUE7O0FBRWI7RUFDSSxXQUFXLEVBQUE7O0FBRWY7RUFDSSxlQUFlO0VBQ2YsY0FBYyxFQUFBOztBQUVsQjtFQUNJLGVBQWUsRUFBQTs7QUFFbkI7RUFDRSxjQUFjO0VBQ2Qsa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0ksa0JBQWlCO0VBQ2pCLFNBQVM7RUFDVCxPQUFNO0VBQ04sUUFBUTtFQUNSLGNBQWE7RUFDYixZQUFXO0VBQ1gsYUFBYTtFQUNiLGlCQUFpQjtFQUNqQixtREFBaUQ7RUFDakQsa0JBQWtCO0VBQ2xCLGtCQUFpQjtFQUNqQixjQUFvQjtFQUNwQixnQkFBZTtFQUNmLHlCQUF5QjtFQUN6QixtQkFBbUI7RUFDbkIsaUJBQWlCO0VBQ2pCLHFCQUFxQixFQUFBOztBQUV6QjtFQUNFLGtCQUFrQjtFQUNsQixZQUFZLEVBQUE7O0FBRWQ7RUFDRSxrQkFBa0I7RUFDbEIsV0FBVyxFQUFBOztBQUViO0VBQ0UsV0FBVztFQUNYLGtCQUFrQjtFQUNqQixrQkFBa0IsRUFBQTs7QUFFckI7RUFDRSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixlQUFlO0VBQ2YsaUJBQWlCO0VBQ2pCLGNBQWMsRUFBQSIsImZpbGUiOiJzcmMvYXBwL3BhZ2VzL21hdGNoZXMvbWF0Y2hlcy5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyJcbi5jb2wtbSB7XG4gICAgZGlzcGxheTogZmxleDsgXG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XG59XG5cbi5saXN0LXBhZGRpbmcge1xuICAgIHBhZGRpbmctdG9wOiAwcHggIWltcG9ydGFudDtcbn1cblxuLmgtaGVhZGVyIHtcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xuICAgIGhlaWdodDogNDhweCAhaW1wb3J0YW50O1xuICAgIGxpbmUtaGVpZ2h0OiAyNHB4ICFpbXBvcnRhbnQ7XG4gICAgZm9udC13ZWlnaHQ6IG5vcm1hbCAhaW1wb3J0YW50O1xuICAgIHotaW5kZXg6IDEwO1xuICAgIGZvbnQtc2l6ZTogMS40ZW07XG4gICAgcGFkZGluZy10b3A6IDBweCAhaW1wb3J0YW50O1xuICAgIHBhZGRpbmctYm90dG9tOiAwcHggIWltcG9ydGFudDtcbiAgICBcbn1cblxuLmgtaGVhZGVyLmNvbCB7XG4gICAgaGVpZ2h0OiAyNXB4ICFpbXBvcnRhbnQ7XG59XG5cbi5kaXYtdGl0bGUge1xuICAgIGRpc3BsYXk6IGZsZXg7XG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcbiAgICBib3JkZXI6IG5vbmU7XG4gICAgZm9udC1zaXplOiAzMHB4O1xuICAgIGNvbG9yOiB3aGl0ZTtcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gICAgcGFkZGluZzogMTBweDtcbn1cblxuLmgtaGVhZGVyLmdyZWVuIHtcbiAgICAtLWJhY2tncm91bmQ6ICMwMTk2ZDA7XG4gICAgY29sb3I6ICNmZmZmZmY7XG59XG5cbi5oLWhlYWRlci5saWdodC1ibHVlIHtcbiAgICAtLWJhY2tncm91bmQ6ICNiODE1MTU7XG4gICAgY29sb3I6ICNmZmZmZmY7XG59XG5cbi5iYWNrZ3JvdW5kLXJlZCB7XG4gICAgcGFkZGluZy10b3A6IDVweDtcbiAgICBwYWRkaW5nLWJsb2NrLWVuZDogNXB4O1xuICAgIGJhY2tncm91bmQ6IHJlZDtcbn1cbi53aGl0ZS1yaWdodCB7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIGZvbnQtc2l6ZTogMTZweDtcbiAgICBmbG9hdDogcmlnaHQ7IFxufVxuLnRleHQtc2l6ZS1ze1xuICAgIGZvbnQtc2l6ZTogMTNweDtcbiAgICB3aWR0aDogMTAwJTtcbiAgICBmbG9hdDogcmlnaHQ7XG4gICAgc2l6ZTogMC4zZW07XG59XG5cbi50ZXh0LXNpemUtcy5mb250e1xuICAgIGZvbnQtc2l6ZTogMXJlbSAhaW1wb3J0YW50O1xufVxuXG4udGV4dC1zaXplLXJ7XG4gICAgZm9udC1zaXplOiAxNXB4O1xuICAgIHdpZHRoOiAxMDAlO1xuICAgIGZsb2F0OiByaWdodDtcbiAgICBjb2xvcjogIzAzNzdCNDtcbn1cbi5pb24taWNvbiB7XG4gICAgZm9udC1zaXplOiA2NHB4O1xuICB9XG5cbi53aGl0ZS1sZWZ0e1xuICAgIGNvbG9yOiB3aGl0ZTtcbiAgICBwYWRkaW5nLWxlZnQ6IDE4cHg7XG4gICAgZm9udC1zaXplOiAxNnB4O1xufVxuLndoaXRle1xuICAgIGNvbG9yOiB3aGl0ZTtcbn1cbi5pbWctc2l6ZS1ze1xuICAgIHdpZHRoOiA0MHB4O1xuICAgIGhlaWdodDogNDBweDtcbn1cblxuLmltZy1zaXplLXMucGFkZGluZyB7XG4gICAgcGFkZGluZzogMXB4IDFweCAxcHggMXB4O1xufVxuXG4uZGl2e1xuICAgZGlzcGxheTogZmxleDtcbiAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xuICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcbiAgIHdpZHRoOiAxMDAlO1xuICAgYm9yZGVyOiBub25lO1xuICAgZm9udC1zaXplOiAzMHB4O1xuICAgY29sb3I6ICM3Nzc3Nzc7XG4gICB0ZXh0LWFsaWduOiBjZW50ZXI7XG59XG4uZGl2LXdpZHRoe1xuICAgIHdpZHRoOiAxMDAlO1xuICAgIHRleHQtYWxpZ246IHJpZ2h0O1xufVxuLnJpZ2h0e1xuICAgIGZsb2F0OiByaWdodDtcbn1cbi5iYWNrZ3JvdW5ke1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgICBoZWlnaHQ6IDE5MHB4O1xuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIi4uLy4uLy4uL2Fzc2V0cy9pbWcvYmFjay1wdHMucG5nXCIpO1xuICAgIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XG4gICAgYmFja2dyb3VuZC1wb3NpdGlvbjogY2VudGVyO1xufVxuLm1hcmdpbi10b3AtbXtcbiAgICBtYXJnaW4tdG9wOiAwLjllbTtcbn1cbi5tYXJnaW4tdG9wLXN7XG4gICAgbWFyZ2luLXRvcDogMC41ZW07XG59XG4ubWFyZ2luLXRvcC14c3tcbiAgICBtYXJnaW4tdG9wOiAwLjhlbTtcbn1cbi50ZXh0LXNpemUtbHtcbiAgICBzaXplOiAxZW07XG59XG4udGV4dC1zaXplLW17XG4gICAgc2l6ZTogMC44ZW07XG59XG4udGV4dC1zaXplLW0tY29sb3J7XG4gICAgZm9udC1zaXplOiAxM3B4O1xuICAgIGNvbG9yOiAjMDYxMjU5O1xufVxuLnRleHQtc2l6ZS1ne1xuICAgIGZvbnQtc2l6ZTogMjBweDtcbn1cbi5jZW50ZXJ7XG4gIG1hcmdpbjogMCBhdXRvO1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG59XG4uY2lyY2xlLXJlZHtcbiAgICBwb3NpdGlvbjphYnNvbHV0ZTtcbiAgICB0b3A6IDMwcHg7XG4gICAgbGVmdDowO1xuICAgIHJpZ2h0OiAwO1xuICAgIGRpc3BsYXk6YmxvY2s7XG4gICAgd2lkdGg6MTMwcHg7XG4gICAgaGVpZ2h0OiAxMzBweDtcbiAgICBsaW5lLWhlaWdodDogMjVweDtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZ2JhKDIyNywzMCw0OCwwLjUpICAhaW1wb3J0YW50O1xuICAgIGJvcmRlci1yYWRpdXM6IDUwJTtcbiAgICB0ZXh0LWFsaWduOmNlbnRlcjtcbiAgICBjb2xvcjogcmdiKDAsNDAsMTIwKTtcbiAgICBmb250LXNpemU6MS44ZW07XG4gICAgbWFyZ2luOiAwIGF1dG8gIWltcG9ydGFudDtcbiAgICBib3JkZXItc3R5bGU6IHNvbGlkO1xuICAgIGJvcmRlci13aWR0aDogMnB4O1xuICAgIGJvcmRlci1jb2xvcjogI2ZmZmZmZjtcbn1cbi5hcnJvdy1uZXh0IHtcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xuICBmbG9hdDogcmlnaHQ7XG59XG4uYXJyb3ctYmFjayB7XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgZmxvYXQ6IGxlZnQ7XG59XG4uY2VudGVyLXRleHR7XG4gIHdpZHRoOiAxMDAlO1xuICB0ZXh0LWFsaWduOiBjZW50ZXI7XG4gICBtYXJnaW4tcmlnaHQ6IDEwcHg7ICBcbn1cbi5pY29uLWFsZXJ0e1xuICBtYXJnaW4tdG9wOiAyNSU7XG4gIHdpZHRoOiA2MHB4O1xuICBoZWlnaHQ6IDYwcHg7XG4gIGZvbnQtc2l6ZTogMzVweDtcbiAgZm9udC13ZWlnaHQ6IGJvbGQ7XG4gIGNvbG9yOiAjNzc3Nzc3O1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/pages/matches/matches.page.ts":
/*!***********************************************!*\
  !*** ./src/app/pages/matches/matches.page.ts ***!
  \***********************************************/
/*! exports provided: MatchesPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MatchesPage", function() { return MatchesPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! moment */ "./node_modules/moment/moment.js");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(moment__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_live_live_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services/live/live.service */ "./src/app/services/live/live.service.ts");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");
/* harmony import */ var _services_client_client_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../services/client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");










var MatchesPage = /** @class */ (function () {
    function MatchesPage(liveService, http, router, loading, zone, client, ionRouter) {
        this.liveService = liveService;
        this.http = http;
        this.router = router;
        this.loading = loading;
        this.zone = zone;
        this.client = client;
        this.ionRouter = ionRouter;
        this.tab = 0;
        this.index = 0;
        this.status_color1 = '#db7f1c';
        this.status_color2 = '#92d822';
        this.status_color3 = '#061259';
        this.clientId = localStorage.getItem('id_client');
    }
    MatchesPage.prototype.ngOnInit = function () {
        var _this = this;
        moment__WEBPACK_IMPORTED_MODULE_3__["locale"]('es');
        this.current_date = moment__WEBPACK_IMPORTED_MODULE_3__().format('DD/MM/YY');
        this.date_day = moment__WEBPACK_IMPORTED_MODULE_3__().format('DD/MM/YY');
        this.old_date = moment__WEBPACK_IMPORTED_MODULE_3__().subtract(1, 'd').format('DD/MM/YY');
        this.new_date = moment__WEBPACK_IMPORTED_MODULE_3__().add(1, 'd').format('DD/MM/YY');
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
                _this.liveMatch(_this.date_day);
            }
        });
    };
    MatchesPage.prototype.liveMatch = function (date) {
        var _this = this;
        // tslint:disable-next-line:variable-name
        var date_check = moment__WEBPACK_IMPORTED_MODULE_3__(date, 'DD/MM/YY', true).format('YYYYMMDD');
        this.loading.presentLoading();
        this.http.get(this.liveService.allMatchesLive(date_check))
            .subscribe(function (resp) {
            _this.data = resp['response'] ? resp['response'].leagues : [];
            _this.sizeData = _this.data.length;
        });
    };
    MatchesPage.prototype.matchTime = function (date) {
        moment__WEBPACK_IMPORTED_MODULE_3__["locale"]('es');
        return moment__WEBPACK_IMPORTED_MODULE_3__(date, 'YYYY/MM/DD HH:mm:ss').format('HH:mm');
    };
    MatchesPage.prototype.macthStatus = function (status) {
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
    MatchesPage.prototype.nextPage = function (date) {
        this.old_date = this.date_day;
        this.date_day = date;
        this.new_date = moment__WEBPACK_IMPORTED_MODULE_3__(date, 'DD/MM/YY', true).add(1, 'd').format('DD/MM/YY');
        this.liveMatch(this.date_day);
        this.refresh();
    };
    MatchesPage.prototype.prevPage = function (date) {
        this.new_date = this.date_day;
        this.date_day = date;
        this.old_date = moment__WEBPACK_IMPORTED_MODULE_3__(date, 'DD/MM/YY', true).subtract(1, 'd').format('DD/MM/YY');
        this.liveMatch(this.date_day);
        this.refresh();
    };
    MatchesPage.prototype.refresh = function () {
        this.zone.run(function () {
            console.log('force update the screen');
        });
    };
    MatchesPage.prototype.currentDate = function () {
        if (this.date_day === this.current_date) {
            return 'Hoy';
        }
        else {
            return this.date_day;
        }
    };
    MatchesPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    MatchesPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-matches',
            template: __webpack_require__(/*! ./matches.page.html */ "./src/app/pages/matches/matches.page.html"),
            styles: [__webpack_require__(/*! ./matches.page.scss */ "./src/app/pages/matches/matches.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_live_live_service__WEBPACK_IMPORTED_MODULE_5__["LiveService"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"],
            _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"],
            _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_6__["LoadingService"],
            _angular_core__WEBPACK_IMPORTED_MODULE_1__["NgZone"],
            _services_client_client_service__WEBPACK_IMPORTED_MODULE_7__["ClientService"],
            _angular_common__WEBPACK_IMPORTED_MODULE_8__["Location"]])
    ], MatchesPage);
    return MatchesPage;
}());



/***/ })

}]);
//# sourceMappingURL=matches-matches-module.js.map