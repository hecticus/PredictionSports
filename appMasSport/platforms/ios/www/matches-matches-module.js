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

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\r\n    </ion-buttons>   \r\n    <div class=\"center\">\r\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\r\n    </div>\r\n  </ion-toolbar>\r\n</ion-header>\r\n<ion-content *ngIf=\"sizeData > 0\">\r\n  <ion-item class=\"h-header green text-size-g\" >\r\n      <ion-grid>\r\n          <ion-row >\r\n            <ion-col size=\"4\" class=\"col-m\" (click)=\"prevPage(old_date)\">\r\n              <div class=\"h-header green col text-size-g\">\r\n                  <ion-label class=\"text-size-s font\">\r\n                    {{old_date}}\r\n                  </ion-label>\r\n              </div>               \r\n            </ion-col>  \r\n            <ion-col size=\"4\" class=\"col-m\">\r\n              <div class=\"h-header green col text-size-g\">\r\n                  <ion-label class=\"text-size-s font\" [innerHTML]=\"currentDate()\"> \r\n                  </ion-label>\r\n              </div>               \r\n            </ion-col> \r\n            <ion-col size=\"4\" class=\"col-m\" (click)=\"nextPage(new_date)\">\r\n              <div class=\"h-header green col text-size-g\">\r\n                  <ion-label class=\"text-size-s font\">\r\n                    {{new_date}}\r\n                  </ion-label>\r\n              </div>               \r\n            </ion-col>                       \r\n          </ion-row>\r\n      </ion-grid>\r\n  </ion-item>\r\n  \r\n  <ion-list *ngFor =\"let leagues of data\" class=\"list-padding\">\r\n\r\n    <ion-item color=\"danger\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"12\" class=\"col-m\">\r\n            <img class=\"img-size-s padding\" src=\"{{leagues.competiton_type.competition_logo}}.svg\">         \r\n            <div class=\"div-title\">\r\n                <ion-label class=\"text-size-s font\">{{leagues.competiton_type.name}}</ion-label>\r\n            </div>               \r\n          </ion-col>                        \r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>    \r\n    <ion-list *ngFor =\"let team of leagues.fixtures\">\r\n      <ion-item >          \r\n        <ion-grid >\r\n          <ion-row >\r\n            <ion-col size=\"4\">\r\n              <div class=\"center-text\">\r\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.homeTeam.team_logo}}\">\r\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\r\n                <ion-label class=\"text-size-m-color\">\r\n                    {{team.homeTeam.short_name}}                \r\n                </ion-label>\r\n              </div>\r\n            </ion-col>\r\n            <ion-col size=\"4\" >\r\n              <div class=\"center-text\">\r\n                <ion-label class=\"text-size-g-color\" *ngIf=\"team.id_status === 1 || team.id_status === 2\"> \r\n                      {{team.home_team_goals}} - {{team.away_team_goals}}\r\n                </ion-label>\r\n                <div class=\"center-text\">\r\n                  <span class=\"text-size-m-color\" [innerHTML]=\"matchTime(team.date)\"></span>\r\n                </div>\r\n                <div  *ngIf=\"team.id_status === 1\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color1\">\r\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n                  <div  *ngIf=\"team.id_status === 2\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color2\">\r\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n                  <div *ngIf=\"team.id_status === 3\">\r\n                  <ion-label class=\"text-size-status\" [style.color]=\"status_color3\">\r\n                    <strong [innerHTML]=\"macthStatus(team.id_status)\"></strong>\r\n                  </ion-label>\r\n                </div>\r\n              </div>\r\n            </ion-col>\r\n            <ion-col size=\"4\">\r\n              <div class=\"center-text\">\r\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions !== 67\" src=\"{{team.awayTeam.team_logo}}\">\r\n                <img class=\"img-size-s\" *ngIf=\"leagues.id_competitions === 67\" src=\"./assets/img/favicon.png\" >\r\n                <ion-label class=\"text-size-m-color\">\r\n                    {{team.awayTeam.short_name}}\r\n                </ion-label>\r\n              </div>\r\n            </ion-col>\r\n          </ion-row>\r\n        </ion-grid>\r\n      </ion-item>\r\n    </ion-list>\r\n  </ion-list>  \r\n</ion-content>\r\n<ion-content *ngIf=\"sizeData === 0\">\r\n  <ion-item class=\"h-header green text-size-g\" >\r\n  \r\n    <ion-grid>\r\n        <ion-row >\r\n            <ion-col size=\"4\" class=\"col-m\" (click)=\"prevPage(old_date)\">\r\n              <div class=\"h-header green col text-size-g\">\r\n                  <ion-label class=\"text-size-s font\">\r\n                    {{old_date}}\r\n                  </ion-label>\r\n              </div>               \r\n            </ion-col>  \r\n            <ion-col size=\"4\" class=\"col-m\">\r\n              <div class=\"h-header green col text-size-g\">\r\n                  <ion-label class=\"text-size-s font\"> \r\n                    {{date_day}}\r\n                  </ion-label>\r\n              </div>               \r\n            </ion-col> \r\n            <ion-col size=\"4\" class=\"col-m\" (click)=\"nextPage(new_date)\">\r\n              <div class=\"h-header green col text-size-g\">\r\n                  <ion-label class=\"text-size-s font\">\r\n                    {{new_date}}\r\n                  </ion-label>\r\n              </div>               \r\n            </ion-col>                       \r\n        </ion-row>\r\n    </ion-grid>\r\n\r\n  </ion-item>    \r\n  <div class=\"div\">\r\n    <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\r\n  </div>\r\n  <div class=\"div\">\r\n     No hay juegos programados para este día. \r\n  </div>      \r\n</ion-content>\r\n\r\n"

/***/ }),

/***/ "./src/app/pages/matches/matches.page.scss":
/*!*************************************************!*\
  !*** ./src/app/pages/matches/matches.page.scss ***!
  \*************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".col-m {\n  display: flex;\n  justify-content: center; }\n\n.list-padding {\n  padding-top: 0px !important; }\n\n.h-header {\n  position: relative;\n  text-align: center;\n  height: 48px !important;\n  line-height: 24px !important;\n  font-weight: normal !important;\n  z-index: 10;\n  font-size: 1.4em;\n  padding-top: 0px !important;\n  padding-bottom: 0px !important; }\n\n.h-header.col {\n  height: 25px !important; }\n\n.div-title {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  border: none;\n  font-size: 30px;\n  color: white;\n  text-align: center;\n  padding: 10px; }\n\n.h-header.green {\n  --background: #0196d0;\n  color: #ffffff; }\n\n.h-header.light-blue {\n  --background: #b81515;\n  color: #ffffff; }\n\n.background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 13px;\n  width: 100%;\n  float: right;\n  size: 0.3em; }\n\n.text-size-s.font {\n  font-size: 1rem !important; }\n\n.text-size-r {\n  font-size: 15px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px; }\n\n.img-size-s.padding {\n  padding: 1px 1px 1px 1px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  size: 0.8em; }\n\n.text-size-m-color {\n  font-size: 13px;\n  color: #061259; }\n\n.text-size-g {\n  font-size: 20px; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.arrow-next {\n  text-align: center;\n  float: right; }\n\n.arrow-back {\n  text-align: center;\n  float: left; }\n\n.center-text {\n  width: 100%;\n  text-align: center;\n  margin-right: 10px; }\n\n.icon-alert {\n  margin-top: 25%;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGFnZXMvbWF0Y2hlcy9EOlxcRG9jdW1lbnRzXFxwcm9ncmFtbWluZyBwcm9qZWN0c1xcYXBwTWFzU3BvcnQvc3JjXFxhcHBcXHBhZ2VzXFxtYXRjaGVzXFxtYXRjaGVzLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFDQTtFQUNJLGFBQWE7RUFDYix1QkFBdUIsRUFBQTs7QUFHM0I7RUFDSSwyQkFBMkIsRUFBQTs7QUFHL0I7RUFDSSxrQkFBa0I7RUFDbEIsa0JBQWtCO0VBQ2xCLHVCQUF1QjtFQUN2Qiw0QkFBNEI7RUFDNUIsOEJBQThCO0VBQzlCLFdBQVc7RUFDWCxnQkFBZ0I7RUFDaEIsMkJBQTJCO0VBQzNCLDhCQUE4QixFQUFBOztBQUlsQztFQUNJLHVCQUF1QixFQUFBOztBQUczQjtFQUNJLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFlBQVk7RUFDWixlQUFlO0VBQ2YsWUFBWTtFQUNaLGtCQUFrQjtFQUNsQixhQUFhLEVBQUE7O0FBR2pCO0VBQ0kscUJBQWE7RUFDYixjQUFjLEVBQUE7O0FBR2xCO0VBQ0kscUJBQWE7RUFDYixjQUFjLEVBQUE7O0FBR2xCO0VBQ0ksZ0JBQWdCO0VBQ2hCLDBCQUFzQjtVQUF0QixzQkFBc0I7RUFDdEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVk7RUFDWixlQUFlO0VBQ2YsWUFBWSxFQUFBOztBQUVoQjtFQUNJLGVBQWU7RUFDZixXQUFXO0VBQ1gsWUFBWTtFQUNaLFdBQVcsRUFBQTs7QUFHZjtFQUNJLDBCQUEwQixFQUFBOztBQUc5QjtFQUNJLGVBQWU7RUFDZixXQUFXO0VBQ1gsWUFBWTtFQUNaLGNBQWMsRUFBQTs7QUFFbEI7RUFDSSxlQUFlLEVBQUE7O0FBR25CO0VBQ0ksWUFBWTtFQUNaLGtCQUFrQjtFQUNsQixlQUFlLEVBQUE7O0FBRW5CO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLFdBQVc7RUFDWCxZQUFZLEVBQUE7O0FBR2hCO0VBQ0ksd0JBQXdCLEVBQUE7O0FBRzVCO0VBQ0csYUFBYTtFQUNiLHVCQUF1QjtFQUN2QixtQkFBbUI7RUFDbkIsV0FBVztFQUNYLFlBQVk7RUFDWixlQUFlO0VBQ2YsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUVyQjtFQUNJLFdBQVc7RUFDWCxpQkFBaUIsRUFBQTs7QUFFckI7RUFDSSxZQUFZLEVBQUE7O0FBRWhCO0VBQ0ksa0JBQWtCO0VBQ2xCLGFBQWE7RUFDYixxQ0FBeUQ7RUFDekQsc0JBQXNCO0VBQ3RCLDJCQUEyQixFQUFBOztBQUUvQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLFNBQVMsRUFBQTs7QUFFYjtFQUNJLFdBQVcsRUFBQTs7QUFFZjtFQUNJLGVBQWU7RUFDZixjQUFjLEVBQUE7O0FBRWxCO0VBQ0ksZUFBZSxFQUFBOztBQUVuQjtFQUNFLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFcEI7RUFDSSxrQkFBaUI7RUFDakIsU0FBUztFQUNULE9BQU07RUFDTixRQUFRO0VBQ1IsY0FBYTtFQUNiLFlBQVc7RUFDWCxhQUFhO0VBQ2IsaUJBQWlCO0VBQ2pCLG1EQUFpRDtFQUNqRCxrQkFBa0I7RUFDbEIsa0JBQWlCO0VBQ2pCLGNBQW9CO0VBQ3BCLGdCQUFlO0VBQ2YseUJBQXlCO0VBQ3pCLG1CQUFtQjtFQUNuQixpQkFBaUI7RUFDakIscUJBQXFCLEVBQUE7O0FBRXpCO0VBQ0Usa0JBQWtCO0VBQ2xCLFlBQVksRUFBQTs7QUFFZDtFQUNFLGtCQUFrQjtFQUNsQixXQUFXLEVBQUE7O0FBRWI7RUFDRSxXQUFXO0VBQ1gsa0JBQWtCO0VBQ2pCLGtCQUFrQixFQUFBOztBQUVyQjtFQUNFLGVBQWU7RUFDZixXQUFXO0VBQ1gsWUFBWTtFQUNaLGVBQWU7RUFDZixpQkFBaUI7RUFDakIsY0FBYyxFQUFBIiwiZmlsZSI6InNyYy9hcHAvcGFnZXMvbWF0Y2hlcy9tYXRjaGVzLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIlxyXG4uY29sLW0ge1xyXG4gICAgZGlzcGxheTogZmxleDsgXHJcbiAgICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxufVxyXG5cclxuLmxpc3QtcGFkZGluZyB7XHJcbiAgICBwYWRkaW5nLXRvcDogMHB4ICFpbXBvcnRhbnQ7XHJcbn1cclxuXHJcbi5oLWhlYWRlciB7XHJcbiAgICBwb3NpdGlvbjogcmVsYXRpdmU7XHJcbiAgICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbiAgICBoZWlnaHQ6IDQ4cHggIWltcG9ydGFudDtcclxuICAgIGxpbmUtaGVpZ2h0OiAyNHB4ICFpbXBvcnRhbnQ7XHJcbiAgICBmb250LXdlaWdodDogbm9ybWFsICFpbXBvcnRhbnQ7XHJcbiAgICB6LWluZGV4OiAxMDtcclxuICAgIGZvbnQtc2l6ZTogMS40ZW07XHJcbiAgICBwYWRkaW5nLXRvcDogMHB4ICFpbXBvcnRhbnQ7XHJcbiAgICBwYWRkaW5nLWJvdHRvbTogMHB4ICFpbXBvcnRhbnQ7XHJcbiAgICBcclxufVxyXG5cclxuLmgtaGVhZGVyLmNvbCB7XHJcbiAgICBoZWlnaHQ6IDI1cHggIWltcG9ydGFudDtcclxufVxyXG5cclxuLmRpdi10aXRsZSB7XHJcbiAgICBkaXNwbGF5OiBmbGV4O1xyXG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICAgYm9yZGVyOiBub25lO1xyXG4gICAgZm9udC1zaXplOiAzMHB4O1xyXG4gICAgY29sb3I6IHdoaXRlO1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gICAgcGFkZGluZzogMTBweDtcclxufVxyXG5cclxuLmgtaGVhZGVyLmdyZWVuIHtcclxuICAgIC0tYmFja2dyb3VuZDogIzAxOTZkMDtcclxuICAgIGNvbG9yOiAjZmZmZmZmO1xyXG59XHJcblxyXG4uaC1oZWFkZXIubGlnaHQtYmx1ZSB7XHJcbiAgICAtLWJhY2tncm91bmQ6ICNiODE1MTU7XHJcbiAgICBjb2xvcjogI2ZmZmZmZjtcclxufVxyXG5cclxuLmJhY2tncm91bmQtcmVkIHtcclxuICAgIHBhZGRpbmctdG9wOiA1cHg7XHJcbiAgICBwYWRkaW5nLWJsb2NrLWVuZDogNXB4O1xyXG4gICAgYmFja2dyb3VuZDogcmVkO1xyXG59XHJcbi53aGl0ZS1yaWdodCB7XHJcbiAgICBjb2xvcjogd2hpdGU7XHJcbiAgICBmb250LXNpemU6IDE2cHg7XHJcbiAgICBmbG9hdDogcmlnaHQ7IFxyXG59XHJcbi50ZXh0LXNpemUtc3tcclxuICAgIGZvbnQtc2l6ZTogMTNweDtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgZmxvYXQ6IHJpZ2h0O1xyXG4gICAgc2l6ZTogMC4zZW07XHJcbn1cclxuXHJcbi50ZXh0LXNpemUtcy5mb250e1xyXG4gICAgZm9udC1zaXplOiAxcmVtICFpbXBvcnRhbnQ7XHJcbn1cclxuXHJcbi50ZXh0LXNpemUtcntcclxuICAgIGZvbnQtc2l6ZTogMTVweDtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgZmxvYXQ6IHJpZ2h0O1xyXG4gICAgY29sb3I6ICMwMzc3QjQ7XHJcbn1cclxuLmlvbi1pY29uIHtcclxuICAgIGZvbnQtc2l6ZTogNjRweDtcclxuICB9XHJcblxyXG4ud2hpdGUtbGVmdHtcclxuICAgIGNvbG9yOiB3aGl0ZTtcclxuICAgIHBhZGRpbmctbGVmdDogMThweDtcclxuICAgIGZvbnQtc2l6ZTogMTZweDtcclxufVxyXG4ud2hpdGV7XHJcbiAgICBjb2xvcjogd2hpdGU7XHJcbn1cclxuLmltZy1zaXplLXN7XHJcbiAgICB3aWR0aDogNDBweDtcclxuICAgIGhlaWdodDogNDBweDtcclxufVxyXG5cclxuLmltZy1zaXplLXMucGFkZGluZyB7XHJcbiAgICBwYWRkaW5nOiAxcHggMXB4IDFweCAxcHg7XHJcbn1cclxuXHJcbi5kaXZ7XHJcbiAgIGRpc3BsYXk6IGZsZXg7XHJcbiAgIGp1c3RpZnktY29udGVudDogY2VudGVyO1xyXG4gICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG4gICB3aWR0aDogMTAwJTtcclxuICAgYm9yZGVyOiBub25lO1xyXG4gICBmb250LXNpemU6IDMwcHg7XHJcbiAgIGNvbG9yOiAjNzc3Nzc3O1xyXG4gICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLmRpdi13aWR0aHtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgdGV4dC1hbGlnbjogcmlnaHQ7XHJcbn1cclxuLnJpZ2h0e1xyXG4gICAgZmxvYXQ6IHJpZ2h0O1xyXG59XHJcbi5iYWNrZ3JvdW5ke1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gICAgaGVpZ2h0OiAxOTBweDtcclxuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIi4uLy4uLy4uL2Fzc2V0cy9pbWcvYmFjay1wdHMucG5nXCIpO1xyXG4gICAgYmFja2dyb3VuZC1zaXplOiBjb3ZlcjtcclxuICAgIGJhY2tncm91bmQtcG9zaXRpb246IGNlbnRlcjtcclxufVxyXG4ubWFyZ2luLXRvcC1te1xyXG4gICAgbWFyZ2luLXRvcDogMC45ZW07XHJcbn1cclxuLm1hcmdpbi10b3Atc3tcclxuICAgIG1hcmdpbi10b3A6IDAuNWVtO1xyXG59XHJcbi5tYXJnaW4tdG9wLXhze1xyXG4gICAgbWFyZ2luLXRvcDogMC44ZW07XHJcbn1cclxuLnRleHQtc2l6ZS1se1xyXG4gICAgc2l6ZTogMWVtO1xyXG59XHJcbi50ZXh0LXNpemUtbXtcclxuICAgIHNpemU6IDAuOGVtO1xyXG59XHJcbi50ZXh0LXNpemUtbS1jb2xvcntcclxuICAgIGZvbnQtc2l6ZTogMTNweDtcclxuICAgIGNvbG9yOiAjMDYxMjU5O1xyXG59XHJcbi50ZXh0LXNpemUtZ3tcclxuICAgIGZvbnQtc2l6ZTogMjBweDtcclxufVxyXG4uY2VudGVye1xyXG4gIG1hcmdpbjogMCBhdXRvO1xyXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4uY2lyY2xlLXJlZHtcclxuICAgIHBvc2l0aW9uOmFic29sdXRlO1xyXG4gICAgdG9wOiAzMHB4O1xyXG4gICAgbGVmdDowO1xyXG4gICAgcmlnaHQ6IDA7XHJcbiAgICBkaXNwbGF5OmJsb2NrO1xyXG4gICAgd2lkdGg6MTMwcHg7XHJcbiAgICBoZWlnaHQ6IDEzMHB4O1xyXG4gICAgbGluZS1oZWlnaHQ6IDI1cHg7XHJcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZ2JhKDIyNywzMCw0OCwwLjUpICAhaW1wb3J0YW50O1xyXG4gICAgYm9yZGVyLXJhZGl1czogNTAlO1xyXG4gICAgdGV4dC1hbGlnbjpjZW50ZXI7XHJcbiAgICBjb2xvcjogcmdiKDAsNDAsMTIwKTtcclxuICAgIGZvbnQtc2l6ZToxLjhlbTtcclxuICAgIG1hcmdpbjogMCBhdXRvICFpbXBvcnRhbnQ7XHJcbiAgICBib3JkZXItc3R5bGU6IHNvbGlkO1xyXG4gICAgYm9yZGVyLXdpZHRoOiAycHg7XHJcbiAgICBib3JkZXItY29sb3I6ICNmZmZmZmY7XHJcbn1cclxuLmFycm93LW5leHQge1xyXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICBmbG9hdDogcmlnaHQ7XHJcbn1cclxuLmFycm93LWJhY2sge1xyXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICBmbG9hdDogbGVmdDtcclxufVxyXG4uY2VudGVyLXRleHR7XHJcbiAgd2lkdGg6IDEwMCU7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gICBtYXJnaW4tcmlnaHQ6IDEwcHg7ICBcclxufVxyXG4uaWNvbi1hbGVydHtcclxuICBtYXJnaW4tdG9wOiAyNSU7XHJcbiAgd2lkdGg6IDYwcHg7XHJcbiAgaGVpZ2h0OiA2MHB4O1xyXG4gIGZvbnQtc2l6ZTogMzVweDtcclxuICBmb250LXdlaWdodDogYm9sZDtcclxuICBjb2xvcjogIzc3Nzc3NztcclxufSJdfQ== */"

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