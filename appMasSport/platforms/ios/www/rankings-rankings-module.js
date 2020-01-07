(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["rankings-rankings-module"],{

/***/ "./src/app/pages/rankings/rankings.module.ts":
/*!***************************************************!*\
  !*** ./src/app/pages/rankings/rankings.module.ts ***!
  \***************************************************/
/*! exports provided: RankingsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RankingsPageModule", function() { return RankingsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _rankings_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./rankings.page */ "./src/app/pages/rankings/rankings.page.ts");







var routes = [
    {
        path: '',
        component: _rankings_page__WEBPACK_IMPORTED_MODULE_6__["RankingsPage"]
    }
];
var RankingsPageModule = /** @class */ (function () {
    function RankingsPageModule() {
    }
    RankingsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes)
            ],
            declarations: [_rankings_page__WEBPACK_IMPORTED_MODULE_6__["RankingsPage"]]
        })
    ], RankingsPageModule);
    return RankingsPageModule;
}());



/***/ }),

/***/ "./src/app/pages/rankings/rankings.page.html":
/*!***************************************************!*\
  !*** ./src/app/pages/rankings/rankings.page.html ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\r\n    </ion-buttons>\r\n    <div class=\"center\">\r\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\r\n    </div>\r\n  </ion-toolbar>\r\n</ion-header>\r\n <ion-item color=\"new\" *ngIf=\"tab === -1\">\r\n      <ion-icon name=\"arrow-round-back\" class=\"arrow-back\" size=\"large\" (click)=\"prevPage()\" *ngIf=\"tab !== -1\"></ion-icon>\r\n      <ion-label class=\"text-size-g\">Actualizado al {{date}}</ion-label>\r\n      <ion-icon name=\"arrow-round-forward\" class=\"arrow-next\" size=\"large\" (click)=\"nextPage()\" *ngIf=\"count < countMax\"></ion-icon>\r\n  </ion-item>\r\n<ion-content *ngIf=\"tab === -1\">\r\n  <ion-item color=\"danger\">\r\n    <ion-grid>\r\n      <ion-row>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-m\">##</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m\">Jugador</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n            <ion-label class=\"text-size-m\">Puntos</ion-label>\r\n        </ion-col>\r\n      </ion-row>\r\n    </ion-grid>\r\n  </ion-item>\r\n   <ion-item >\r\n    <ion-grid>\r\n      <ion-row>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-bold\">{{index}}</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-bold\">{{nickname}}</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n            <ion-label class=\"text-size-bold\">{{score}}</ion-label>\r\n        </ion-col>\r\n         <ion-col size=\"3\">\r\n            <ion-label class=\"text-size-m\">...</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m\">...</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-m\">...</ion-label>\r\n        </ion-col>\r\n      </ion-row>\r\n    </ion-grid>\r\n  </ion-item>\r\n  <ion-list  *ngFor=\"let item of ranking; let i = index\">\r\n    <ion-item>\r\n      <ion-grid>\r\n          <ion-row>\r\n            <ion-col size=\"3\">\r\n              <ion-label class=\"text-size-m\">{{i+1}}</ion-label>\r\n            </ion-col>\r\n            <ion-col size=\"6\">\r\n              <ion-label class=\"text-size-m\">{{item.client}}</ion-label>\r\n            </ion-col>\r\n            <ion-col size=\"3\">\r\n              <ion-label class=\"text-size-m\">{{item.score}}</ion-label>\r\n            </ion-col>\r\n          </ion-row>\r\n        </ion-grid>\r\n    </ion-item>\r\n  </ion-list>\r\n</ion-content>\r\n<ion-item color=\"new\" *ngIf=\"tab !== -1\">\r\n      <ion-icon name=\"arrow-round-back\" class=\"arrow-back\" size=\"large\" (click)=\"prevPage()\" *ngIf=\"tab !== -1 \"></ion-icon>\r\n      <ion-label class=\"text-size-g\">{{name_league}}{{' '}}{{date}}</ion-label>\r\n      <ion-icon name=\"arrow-round-forward\" class=\"arrow-next\" size=\"large\" (click)=\"nextPage()\" *ngIf=\"count < countMax\"></ion-icon>\r\n  </ion-item>\r\n<ion-content *ngIf=\"tab !== -1\">\r\n  <ion-item color=\"danger\">\r\n    <ion-grid>\r\n      <ion-row>\r\n        <ion-col size=\"2\">\r\n        </ion-col>  \r\n        <ion-col size=\"2\">\r\n        </ion-col> \r\n        <ion-col size=\"8\">\r\n          <div class=\"center-text\">\r\n            <ion-button  shape=\"round\" size=\"small\" [color]=\"myColor\" class=\"btn-league\" (click)=\"tournament()\">Torneo</ion-button>\r\n            <ion-button shape=\"round\" size=\"small\" [color]=\"myColor2\" class=\"btn-league\" (click)=\"getPhase()\">Jornada</ion-button>\r\n          </div>\r\n        </ion-col> \r\n      </ion-row>\r\n      <ion-row>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-m\">##</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m\">Jugador</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-m\">Puntos</ion-label>\r\n        </ion-col>\r\n      </ion-row>\r\n    </ion-grid>\r\n  </ion-item>\r\n  <ion-item  *ngIf=\"positionClient >= 10\">\r\n    <ion-grid>\r\n      <ion-row>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-bold\">{{positionClient + 1}}</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-bold\">{{nickname}}</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-bold\">{{score}}</ion-label>\r\n        </ion-col>\r\n         <ion-col size=\"3\">\r\n            <ion-label class=\"text-size-m\">...</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m\">...</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n          <ion-label class=\"text-size-m\">...</ion-label>\r\n        </ion-col>\r\n      </ion-row>\r\n    </ion-grid>\r\n  </ion-item>\r\n  <ion-list  *ngFor=\"let item of matchs; let i = index\">\r\n    <ion-item>\r\n      <ion-grid>\r\n        <ion-row>\r\n          <ion-col size=\"3\">\r\n            <ion-label class=\"text-size-m\" *ngIf=\"item.id_client == clientId\"><strong >{{i+1}}</strong></ion-label>\r\n            <ion-label class=\"text-size-m\"  *ngIf=\"item.id_client != clientId\">{{i+1}}</ion-label>\r\n          </ion-col>\r\n          <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m\" *ngIf=\"item.id_client == clientId\"><strong >{{item.client}}</strong></ion-label>\r\n              <ion-label class=\"text-size-m\" *ngIf=\"item.id_client != clientId\">{{item.client}}</ion-label>\r\n          </ion-col>\r\n          <ion-col size=\"3\">\r\n            <ion-label class=\"text-size-m\" *ngIf=\"item.id_client == clientId\"><strong >{{item.score}}</strong></ion-label>\r\n            <ion-label class=\"text-size-m\" *ngIf=\"item.id_client != clientId\">{{item.score}}</ion-label>\r\n          </ion-col>\r\n        </ion-row>\r\n        </ion-grid>\r\n    </ion-item>\r\n  </ion-list>\r\n</ion-content>"

/***/ }),

/***/ "./src/app/pages/rankings/rankings.page.scss":
/*!***************************************************!*\
  !*** ./src/app/pages/rankings/rankings.page.scss ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".mainDiv {\n  background: blue;\n  height: 50px; }\n\n.center-text {\n  width: 100%;\n  text-align: center; }\n\n.text-size-s {\n  font-size: 13px;\n  width: 100%;\n  text-align: center; }\n\n.text-size-m {\n  font-size: 16px;\n  width: 100%;\n  text-align: center; }\n\n.text-size-g {\n  font-size: 18px;\n  width: 100%;\n  text-align: center; }\n\n.text-size-bold {\n  font-size: 15px;\n  font-weight: bold;\n  width: 100%;\n  text-align: center; }\n\n.arrow-next {\n  text-align: center;\n  float: right; }\n\n.arrow-back {\n  text-align: center;\n  float: left; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.btn-league {\n  text-align: center;\n  font-size: 13px;\n  font-weight: bold; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGFnZXMvcmFua2luZ3MvRDpcXERvY3VtZW50c1xccHJvZ3JhbW1pbmcgcHJvamVjdHNcXGFwcE1hc1Nwb3J0L3NyY1xcYXBwXFxwYWdlc1xccmFua2luZ3NcXHJhbmtpbmdzLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLGdCQUFnQjtFQUNoQixZQUFZLEVBQUE7O0FBRWQ7RUFDRSxXQUFXO0VBQ1gsa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0UsZUFBZTtFQUNmLFdBQVc7RUFDWCxrQkFBa0IsRUFBQTs7QUFFcEI7RUFDRSxlQUFlO0VBQ2YsV0FBVztFQUNYLGtCQUFrQixFQUFBOztBQUVwQjtFQUNFLGVBQWU7RUFDZixXQUFXO0VBQ1gsa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0UsZUFBZTtFQUNmLGlCQUFpQjtFQUNqQixXQUFXO0VBQ1gsa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0Usa0JBQWtCO0VBQ2xCLFlBQVksRUFBQTs7QUFFZDtFQUNFLGtCQUFrQjtFQUNsQixXQUFXLEVBQUE7O0FBRWI7RUFDRSxjQUFjO0VBQ2Qsa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0Usa0JBQWtCO0VBQ2xCLGVBQWU7RUFDZixpQkFBaUIsRUFBQSIsImZpbGUiOiJzcmMvYXBwL3BhZ2VzL3JhbmtpbmdzL3JhbmtpbmdzLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5tYWluRGl2IHtcclxuICBiYWNrZ3JvdW5kOiBibHVlO1xyXG4gIGhlaWdodDogNTBweDtcclxufVxyXG4uY2VudGVyLXRleHR7XHJcbiAgd2lkdGg6IDEwMCU7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyOyAgIFxyXG59XHJcbi50ZXh0LXNpemUtc3tcclxuICBmb250LXNpemU6IDEzcHg7XHJcbiAgd2lkdGg6IDEwMCU7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyOyBcclxufVxyXG4udGV4dC1zaXplLW17XHJcbiAgZm9udC1zaXplOiAxNnB4O1xyXG4gIHdpZHRoOiAxMDAlO1xyXG4gIHRleHQtYWxpZ246IGNlbnRlcjsgXHJcbn1cclxuLnRleHQtc2l6ZS1ne1xyXG4gIGZvbnQtc2l6ZTogMThweDtcclxuICB3aWR0aDogMTAwJTtcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7IFxyXG59XHJcbi50ZXh0LXNpemUtYm9sZHtcclxuICBmb250LXNpemU6IDE1cHg7XHJcbiAgZm9udC13ZWlnaHQ6IGJvbGQ7XHJcbiAgd2lkdGg6IDEwMCU7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyOyBcclxufVxyXG4uYXJyb3ctbmV4dCB7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gIGZsb2F0OiByaWdodDtcclxufVxyXG4uYXJyb3ctYmFjayB7XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gIGZsb2F0OiBsZWZ0O1xyXG59XHJcbi5jZW50ZXJ7XHJcbiAgbWFyZ2luOiAwIGF1dG87XHJcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG59XHJcbi5idG4tbGVhZ3VlIHtcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbiAgZm9udC1zaXplOiAxM3B4O1xyXG4gIGZvbnQtd2VpZ2h0OiBib2xkO1xyXG5cclxufVxyXG4iXX0= */"

/***/ }),

/***/ "./src/app/pages/rankings/rankings.page.ts":
/*!*************************************************!*\
  !*** ./src/app/pages/rankings/rankings.page.ts ***!
  \*************************************************/
/*! exports provided: RankingsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RankingsPage", function() { return RankingsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _services_competitions_competitions_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/competitions/competitions.service */ "./src/app/services/competitions/competitions.service.ts");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");





var RankingsPage = /** @class */ (function () {
    function RankingsPage(http, competitions, loading) {
        this.http = http;
        this.competitions = competitions;
        this.loading = loading;
        this.clientId = localStorage.getItem('id_client');
        this.count = 0;
        this.countMax = 0;
        this.tab = -1;
        this.myColor = "danger-bold";
        this.myColor2 = "#C4171D";
    }
    RankingsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.http.get(this.competitions.totalRanking(this.clientId))
            .toPromise()
            .then(function (resp) {
            _this.data = resp['response'];
            _this.ranking = _this.data.leaderboard;
            _this.infoClient = _this.data.client;
            _this.date = _this.infoClient.ddate;
            _this.score = _this.infoClient.score;
            _this.index = _this.infoClient.index;
            _this.nickname = _this.infoClient.client;
        });
        this.http.get(this.competitions.getCompetitionsFootball())
            .toPromise()
            .then(function (info) {
            _this.info = info['response'];
            _this.dataFootball = _this.info.competitions;
            _this.countMax = _this.dataFootball.length;
        });
    };
    RankingsPage.prototype.nextPage = function () {
        var _this = this;
        this.myColor = "danger-bold";
        this.myColor2 = "#C4171D";
        this.tab++;
        if (this.count < this.dataFootball.length) {
            this.id_competition = this.dataFootball[this.count].id_competitions;
            this.loading.presentLoading();
            this.http.get(this.competitions.getLeagues(this.clientId, this.id_competition))
                .toPromise()
                .then(function (response) {
                _this.leagues = response['response'];
                _this.dataClient = _this.leagues.client;
                _this.positionClient = _this.dataClient.index;
                _this.score = _this.dataClient.score;
                _this.nickname = _this.dataClient.client;
                _this.matchs = _this.leagues.leaderboard;
                _this.matchs.map(function (score) {
                    _this.dataFootball.some(function (competition) {
                        if (competition.id_competitions === _this.id_competition) {
                            score.name_competition = competition.competiton_type.name;
                            _this.name_league = competition.competiton_type.name;
                            return true;
                        }
                    });
                });
            });
            this.count++;
        }
    };
    RankingsPage.prototype.prevPage = function () {
        var _this = this;
        this.myColor = "danger-bold";
        this.myColor2 = "#C4171D";
        this.count--;
        this.tab--;
        this.loading.presentLoading();
        if (this.count === 0) {
            if (this.dataFootball.length >= this.count) {
                this.id_competition = this.dataFootball[this.count].id_competitions;
                this.http.get(this.competitions.getLeagues(this.clientId, this.id_competition))
                    .toPromise()
                    .then(function (response) {
                    _this.leagues = response['response'];
                    _this.matchs = _this.leagues.leaderboard;
                    _this.matchs.map(function (score) {
                        _this.dataFootball.some(function (competition) {
                            if (competition.id_competitions === _this.id_competition) {
                                score.name_competition = competition.competiton_type.name;
                                _this.name_league = competition.competiton_type.name;
                                return true;
                            }
                        });
                    });
                });
            }
        }
        else {
            if (this.dataFootball.length >= this.count) {
                this.id_competition = this.dataFootball[this.count - 1].id_competitions;
                this.http.get(this.competitions.getLeagues(this.clientId, this.id_competition))
                    .toPromise()
                    .then(function (response) {
                    _this.leagues = response['response'];
                    _this.dataClient = _this.leagues.client;
                    _this.positionClient = _this.dataClient.index;
                    _this.score = _this.dataClient.score;
                    _this.nickname = _this.dataClient.client;
                    _this.matchs = _this.leagues.leaderboard;
                    _this.matchs.map(function (score) {
                        _this.dataFootball.some(function (competition) {
                            if (competition.id_competitions === _this.id_competition) {
                                score.name_competition = competition.competiton_type.name;
                                _this.name_league = competition.competiton_type.name;
                                return true;
                            }
                        });
                    });
                });
            }
        }
    };
    RankingsPage.prototype.tournament = function () {
        var _this = this;
        this.myColor2 = "#C4171D";
        this.myColor = "danger-bold";
        this.id_competition = this.dataFootball[this.count - 1].id_competitions;
        this.http.get(this.competitions.getLeagues(this.clientId, this.id_competition))
            .toPromise()
            .then(function (response) {
            _this.leagues = response['response'];
            _this.matchs = _this.leagues.leaderboard;
        });
    };
    RankingsPage.prototype.getPhase = function () {
        var _this = this;
        this.myColor = "#C4171D";
        this.myColor2 = "danger-bold";
        var phase = 0;
        this.id_competition = this.dataFootball[this.count - 1].id_competitions;
        this.http.get(this.competitions.getPhase(this.clientId, this.id_competition, phase))
            .toPromise()
            .then(function (response) {
            _this.leagues = response['response'];
            _this.matchs = _this.leagues.leaderboard;
        });
    };
    RankingsPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    RankingsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-rankings',
            template: __webpack_require__(/*! ./rankings.page.html */ "./src/app/pages/rankings/rankings.page.html"),
            styles: [__webpack_require__(/*! ./rankings.page.scss */ "./src/app/pages/rankings/rankings.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _services_competitions_competitions_service__WEBPACK_IMPORTED_MODULE_3__["CompetitionsService"], _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_4__["LoadingService"]])
    ], RankingsPage);
    return RankingsPage;
}());



/***/ })

}]);
//# sourceMappingURL=rankings-rankings-module.js.map