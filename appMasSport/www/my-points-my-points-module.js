(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["my-points-my-points-module"],{

/***/ "./src/app/pages/my-points/my-points.module.ts":
/*!*****************************************************!*\
  !*** ./src/app/pages/my-points/my-points.module.ts ***!
  \*****************************************************/
/*! exports provided: MyPointsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MyPointsPageModule", function() { return MyPointsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _my_points_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./my-points.page */ "./src/app/pages/my-points/my-points.page.ts");







var routes = [
    {
        path: '',
        component: _my_points_page__WEBPACK_IMPORTED_MODULE_6__["MyPointsPage"]
    }
];
var MyPointsPageModule = /** @class */ (function () {
    function MyPointsPageModule() {
    }
    MyPointsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes)
            ],
            declarations: [_my_points_page__WEBPACK_IMPORTED_MODULE_6__["MyPointsPage"]]
        })
    ], MyPointsPageModule);
    return MyPointsPageModule;
}());



/***/ }),

/***/ "./src/app/pages/my-points/my-points.page.html":
/*!*****************************************************!*\
  !*** ./src/app/pages/my-points/my-points.page.html ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\n  <ion-toolbar>\n    <ion-buttons slot=\"start\">\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\n    </ion-buttons>\n    <div class=\"center\">\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\n    </div>\n  </ion-toolbar>\n</ion-header>\n<ion-content *ngIf=\"sizeData > 0\">\n  <div class=\"background\">\n    <ion-label class=\"white margin-top-m\"></ion-label>\n    <div style=\"position:absolute; top: 95px; background: #ffffff; width: 100%; height:2px;\"></div>\n     <ion-label class=\"white margin-top-m text-size-g\">{{nickname}}</ion-label>\n    <div class=\"circle-red\">\n      <div  class=\"white margin-top-m\">\n        <ion-label class=\"white margin-top-m text-size-g\">{{totalPoints}}</ion-label>\n        <ion-label class=\"white margin-top-s text-size-r\">Puntos</ion-label>\n      </div>\n    </div>  \n  </div>\n  <ion-item color=\"danger\">\n    <ion-grid>\n      <ion-row>\n        <ion-col size=\"6\">\n          <ion-label class=\"text-size-m\">Torneo</ion-label>\n        </ion-col>\n        <ion-col size=\"3\">\n          <div class=\"center-text right\">\n            <ion-label class=\"text-size-m\">Aciertos</ion-label>\n          </div>\n        </ion-col>\n        <ion-col size=\"3\">\n          <div class=\"center-text right\">\n            <ion-label class=\"text-size-m\">Puntos</ion-label>\n          </div>\n        </ion-col>\n      </ion-row>\n    </ion-grid>\n  </ion-item>\n    <ion-list none *ngFor =\"let resp of leaderboard; let i = index \">\n    <ion-item *ngIf=\"resp.sport_id === 1 && resp.show === 1\">\n      <ion-grid>\n        <ion-row >\n          <ion-col size=\"2\">\n          <img class=\"img-size-s\" src=\"{{resp.logo}}.svg\">\n          </ion-col>\n          <ion-col size=\"5\">\n          <div class=\"divItem\">\n            <ion-label class=\"text-size-s\">{{resp.name}}</ion-label>\n          </div>\n          </ion-col>\n          <ion-col size=\"3\">\n            <div class=\"divItem\">\n              <ion-label class=\"text-size-s\">{{resp.hits}}</ion-label>\n            </div>\n          </ion-col>\n          <ion-col size=\"2\">\n            <div class=\"divItem\">\n              <span class=\"text-size-s right\" >{{resp.score}}</span>\n            </div>\n          </ion-col>\n        </ion-row>\n      </ion-grid>\n    </ion-item>\n    <ion-item *ngIf=\"resp.sport_id === 2 && resp.show === 1 && resp.status === 1\">\n      <ion-grid>\n        <ion-row >\n          <ion-col size=\"2\">\n          <img class=\"img-size-s\" src=\"{{resp.logo}}.svg\">\n          </ion-col>\n          <ion-col size=\"5\">\n          <div class=\"divItem\">\n            <ion-label class=\"text-size-s\">{{resp.name}}</ion-label>\n          </div>\n          </ion-col>\n          <ion-col size=\"3\">\n            <div class=\"divItem\">\n              <ion-label class=\"text-size-s\">{{resp.hits}}</ion-label>\n            </div>\n          </ion-col>\n          <ion-col size=\"2\">\n            <div class=\"divItem\">\n              <span class=\"text-size-s right\" >{{resp.score}}</span>\n            </div>\n          </ion-col>\n        </ion-row>\n      </ion-grid>\n    </ion-item>\n  </ion-list>\n</ion-content>\n<ion-content *ngIf=\"sizeData === 0\">\n  <div class=\"div\">\n    <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\n  </div>\n  <div class=\"div\">\n     Todavía no has hecho predicciones o no se han Jugado los partidos. \n  </div>\n</ion-content>\n"

/***/ }),

/***/ "./src/app/pages/my-points/my-points.page.scss":
/*!*****************************************************!*\
  !*** ./src/app/pages/my-points/my-points.page.scss ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 15px;\n  width: 100%;\n  float: right;\n  size: 0.3em;\n  color: #002878;\n  justify-content: center;\n  align-items: center; }\n\n.text-size-r {\n  font-size: 20px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n.divItem {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  height: 100%;\n  border: none; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  font-size: 15px; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.text-size-g {\n  font-size: 25px; }\n\n.icon-alert {\n  margin-top: 15px;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL2JyYXlhbi9hcHBNYXNTcG9ydC9zcmMvYXBwL3BhZ2VzL215LXBvaW50cy9teS1wb2ludHMucGFnZS5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0ksZ0JBQWdCO0VBQ2hCLDBCQUFzQjtVQUF0QixzQkFBc0I7RUFDdEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVk7RUFDWixlQUFlO0VBQ2YsWUFBWSxFQUFBOztBQUVoQjtFQUNJLGVBQWU7RUFDZixXQUFXO0VBQ1gsWUFBWTtFQUNaLFdBQVc7RUFDWCxjQUFjO0VBQ2QsdUJBQXVCO0VBQ3ZCLG1CQUFtQixFQUFBOztBQUV2QjtFQUNJLGVBQWU7RUFDZixXQUFXO0VBQ1gsWUFBWTtFQUNaLGNBQWMsRUFBQTs7QUFFbEI7RUFDSSxlQUFlLEVBQUE7O0FBR25CO0VBQ0ksWUFBWTtFQUNaLGtCQUFrQjtFQUNsQixlQUFlLEVBQUE7O0FBRW5CO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLFdBQVc7RUFDWCxZQUFZLEVBQUE7O0FBRWhCO0VBQ0csYUFBYTtFQUNiLHVCQUF1QjtFQUN2QixtQkFBbUI7RUFDbkIsV0FBVztFQUNYLFlBQVk7RUFDWixlQUFlO0VBQ2YsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUVyQjtFQUNHLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFlBQVk7RUFDWixZQUFZLEVBQUE7O0FBRWY7RUFDSSxXQUFXO0VBQ1gsaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksWUFBWSxFQUFBOztBQUVoQjtFQUNJLGtCQUFpQjtFQUNqQixTQUFTO0VBQ1QsT0FBTTtFQUNOLFFBQVE7RUFDUixjQUFhO0VBQ2IsWUFBVztFQUNYLGFBQWE7RUFDYixpQkFBaUI7RUFDakIsbURBQWlEO0VBQ2pELGtCQUFrQjtFQUNsQixrQkFBaUI7RUFDakIsY0FBb0I7RUFDcEIsZ0JBQWU7RUFDZix5QkFBeUI7RUFDekIsbUJBQW1CO0VBQ25CLGlCQUFpQjtFQUNqQixxQkFBcUIsRUFBQTs7QUFFekI7RUFDSSxrQkFBa0I7RUFDbEIsYUFBYTtFQUNiLHFDQUF5RDtFQUN6RCxzQkFBc0I7RUFDdEIsMkJBQTJCLEVBQUE7O0FBRS9CO0VBQ0ksaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksaUJBQWlCLEVBQUE7O0FBRXJCO0VBQ0ksU0FBUyxFQUFBOztBQUViO0VBQ0ksZUFBZSxFQUFBOztBQUVuQjtFQUNFLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFcEI7RUFDSSxlQUFlLEVBQUE7O0FBRW5CO0VBQ0UsZ0JBQWdCO0VBQ2hCLFdBQVc7RUFDWCxZQUFZO0VBQ1osZUFBZTtFQUNmLGlCQUFpQjtFQUNqQixjQUFjLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9wYWdlcy9teS1wb2ludHMvbXktcG9pbnRzLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5iYWNrZ3JvdW5kLXJlZCB7XG4gICAgcGFkZGluZy10b3A6IDVweDtcbiAgICBwYWRkaW5nLWJsb2NrLWVuZDogNXB4O1xuICAgIGJhY2tncm91bmQ6IHJlZDtcbn1cbi53aGl0ZS1yaWdodCB7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIGZvbnQtc2l6ZTogMTZweDtcbiAgICBmbG9hdDogcmlnaHQ7IFxufVxuLnRleHQtc2l6ZS1ze1xuICAgIGZvbnQtc2l6ZTogMTVweDtcbiAgICB3aWR0aDogMTAwJTtcbiAgICBmbG9hdDogcmlnaHQ7XG4gICAgc2l6ZTogMC4zZW07XG4gICAgY29sb3I6ICMwMDI4Nzg7XG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XG4gICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcbn1cbi50ZXh0LXNpemUtcntcbiAgICBmb250LXNpemU6IDIwcHg7XG4gICAgd2lkdGg6IDEwMCU7XG4gICAgZmxvYXQ6IHJpZ2h0O1xuICAgIGNvbG9yOiAjMDM3N0I0O1xufVxuLmlvbi1pY29uIHtcbiAgICBmb250LXNpemU6IDY0cHg7XG4gIH1cblxuLndoaXRlLWxlZnR7XG4gICAgY29sb3I6IHdoaXRlO1xuICAgIHBhZGRpbmctbGVmdDogMThweDtcbiAgICBmb250LXNpemU6IDE2cHg7XG59XG4ud2hpdGV7XG4gICAgY29sb3I6IHdoaXRlO1xufVxuLmltZy1zaXplLXN7XG4gICAgd2lkdGg6IDQwcHg7XG4gICAgaGVpZ2h0OiA0MHB4O1xufVxuLmRpdntcbiAgIGRpc3BsYXk6IGZsZXg7XG4gICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcbiAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XG4gICB3aWR0aDogMTAwJTtcbiAgIGJvcmRlcjogbm9uZTtcbiAgIGZvbnQtc2l6ZTogMzBweDtcbiAgIGNvbG9yOiAjNzc3Nzc3O1xuICAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLmRpdkl0ZW17XG4gICBkaXNwbGF5OiBmbGV4O1xuICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XG4gICBhbGlnbi1pdGVtczogY2VudGVyO1xuICAgaGVpZ2h0OiAxMDAlO1xuICAgYm9yZGVyOiBub25lO1xufVxuLmRpdi13aWR0aHtcbiAgICB3aWR0aDogMTAwJTtcbiAgICB0ZXh0LWFsaWduOiByaWdodDtcbn1cbi5yaWdodHtcbiAgICBmbG9hdDogcmlnaHQ7XG59XG4uY2lyY2xlLXJlZHtcbiAgICBwb3NpdGlvbjphYnNvbHV0ZTtcbiAgICB0b3A6IDMwcHg7XG4gICAgbGVmdDowO1xuICAgIHJpZ2h0OiAwO1xuICAgIGRpc3BsYXk6YmxvY2s7XG4gICAgd2lkdGg6MTMwcHg7XG4gICAgaGVpZ2h0OiAxMzBweDtcbiAgICBsaW5lLWhlaWdodDogMjVweDtcbiAgICBiYWNrZ3JvdW5kLWNvbG9yOiByZ2JhKDIyNywzMCw0OCwwLjUpICAhaW1wb3J0YW50O1xuICAgIGJvcmRlci1yYWRpdXM6IDUwJTtcbiAgICB0ZXh0LWFsaWduOmNlbnRlcjtcbiAgICBjb2xvcjogcmdiKDAsNDAsMTIwKTtcbiAgICBmb250LXNpemU6MS44ZW07XG4gICAgbWFyZ2luOiAwIGF1dG8gIWltcG9ydGFudDtcbiAgICBib3JkZXItc3R5bGU6IHNvbGlkO1xuICAgIGJvcmRlci13aWR0aDogMnB4O1xuICAgIGJvcmRlci1jb2xvcjogI2ZmZmZmZjtcbn1cbi5iYWNrZ3JvdW5ke1xuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgICBoZWlnaHQ6IDE5MHB4O1xuICAgIGJhY2tncm91bmQtaW1hZ2U6IHVybChcIi4uLy4uLy4uL2Fzc2V0cy9pbWcvYmFjay1wdHMucG5nXCIpO1xuICAgIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XG4gICAgYmFja2dyb3VuZC1wb3NpdGlvbjogY2VudGVyO1xufVxuLm1hcmdpbi10b3AtbXtcbiAgICBtYXJnaW4tdG9wOiAwLjllbTtcbn1cbi5tYXJnaW4tdG9wLXN7XG4gICAgbWFyZ2luLXRvcDogMC41ZW07XG59XG4ubWFyZ2luLXRvcC14c3tcbiAgICBtYXJnaW4tdG9wOiAwLjhlbTtcbn1cbi50ZXh0LXNpemUtbHtcbiAgICBzaXplOiAxZW07XG59XG4udGV4dC1zaXplLW17XG4gICAgZm9udC1zaXplOiAxNXB4O1xufVxuLmNlbnRlcntcbiAgbWFyZ2luOiAwIGF1dG87XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cbi50ZXh0LXNpemUtZ3tcbiAgICBmb250LXNpemU6IDI1cHg7XG59XG4uaWNvbi1hbGVydHtcbiAgbWFyZ2luLXRvcDogMTVweDtcbiAgd2lkdGg6IDYwcHg7XG4gIGhlaWdodDogNjBweDtcbiAgZm9udC1zaXplOiAzNXB4O1xuICBmb250LXdlaWdodDogYm9sZDtcbiAgY29sb3I6ICM3Nzc3Nzc7XG59XG5cbiJdfQ== */"

/***/ }),

/***/ "./src/app/pages/my-points/my-points.page.ts":
/*!***************************************************!*\
  !*** ./src/app/pages/my-points/my-points.page.ts ***!
  \***************************************************/
/*! exports provided: MyPointsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MyPointsPage", function() { return MyPointsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _services_client_client_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _services_competitions_competitions_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../services/competitions/competitions.service */ "./src/app/services/competitions/competitions.service.ts");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");






var MyPointsPage = /** @class */ (function () {
    function MyPointsPage(http, client, competitions, loading) {
        this.http = http;
        this.client = client;
        this.competitions = competitions;
        this.loading = loading;
        this.clientId = localStorage.getItem('id_client');
        this.totalPoints = 0;
    }
    MyPointsPage.prototype.ngOnInit = function () {
        var _this = this;
        /**********  GetInfoClient **********/
        this.loading.presentLoading();
        this.http.get(this.client.getClientById(this.clientId))
            .subscribe(function (resp) {
            _this.infoClient = resp['response'];
            _this.nickname = _this.infoClient.nickname;
        });
        /***********  Get the points List ***********/
        this.http.get(this.client.getPoints(this.clientId))
            .toPromise()
            .then(function (resp) {
            _this.data = resp['response'];
            _this.leaderboard = _this.data.leaderboard;
            _this.sizeData = _this.leaderboard.length;
            /***********  Get the points of baseball league ***********/
            _this.http.get(_this.competitions.getCompetitionsBaseball())
                .toPromise()
                .then(function (respBase) {
                _this.responseBaseball = respBase['response'];
                _this.dataBaseball = _this.responseBaseball.competitions;
                _this.leaderboard.map(function (score) {
                    _this.dataBaseball.some(function (competition) {
                        if (competition.match !== undefined && competition.id_competitions === score.id_tournament) {
                            score.name = competition.competiton_type.name;
                            score.logo = competition.competiton_type.competition_logo;
                            score.show = competition.show;
                            score.status = competition.status;
                            _this.totalPoints += score.score;
                            return true;
                        }
                    });
                });
            });
            /***********  Get the points of football league ***********/
            _this.http.get(_this.competitions.getCompetitionsFootball())
                .toPromise()
                .then(function (response) {
                _this.info = response['response'];
                _this.allCompetitions = _this.info.competitions;
                _this.leaderboard.map(function (score) {
                    _this.allCompetitions.some(function (competition) {
                        if (competition.id_competitions === score.id_tournament) {
                            score.name = competition.competiton_type.name;
                            score.logo = competition.competiton_type.competition_logo;
                            score.show = competition.show;
                            _this.totalPoints += score.score;
                            return true;
                        }
                    });
                });
            });
        });
    };
    MyPointsPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    MyPointsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-my-points',
            template: __webpack_require__(/*! ./my-points.page.html */ "./src/app/pages/my-points/my-points.page.html"),
            styles: [__webpack_require__(/*! ./my-points.page.scss */ "./src/app/pages/my-points/my-points.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _services_client_client_service__WEBPACK_IMPORTED_MODULE_3__["ClientService"], _services_competitions_competitions_service__WEBPACK_IMPORTED_MODULE_4__["CompetitionsService"], _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_5__["LoadingService"]])
    ], MyPointsPage);
    return MyPointsPage;
}());



/***/ })

}]);
//# sourceMappingURL=my-points-my-points-module.js.map