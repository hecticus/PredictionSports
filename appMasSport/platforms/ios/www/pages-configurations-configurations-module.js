(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["pages-configurations-configurations-module"],{

/***/ "./src/app/pages/configurations/configurations.module.ts":
/*!***************************************************************!*\
  !*** ./src/app/pages/configurations/configurations.module.ts ***!
  \***************************************************************/
/*! exports provided: ConfigurationsPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConfigurationsPageModule", function() { return ConfigurationsPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _configurations_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./configurations.page */ "./src/app/pages/configurations/configurations.page.ts");







var routes = [
    {
        path: '',
        component: _configurations_page__WEBPACK_IMPORTED_MODULE_6__["ConfigurationsPage"]
    }
];
var ConfigurationsPageModule = /** @class */ (function () {
    function ConfigurationsPageModule() {
    }
    ConfigurationsPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes)
            ],
            declarations: [_configurations_page__WEBPACK_IMPORTED_MODULE_6__["ConfigurationsPage"]]
        })
    ], ConfigurationsPageModule);
    return ConfigurationsPageModule;
}());



/***/ }),

/***/ "./src/app/pages/configurations/configurations.page.html":
/*!***************************************************************!*\
  !*** ./src/app/pages/configurations/configurations.page.html ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n      <ion-buttons slot=\"start\" *ngIf=\"tab === 0 && login !== 'guest'\">\r\n        <ion-icon name=\"arrow-round-back\" class=\"arrow-back\" size=\"large\"  color=\"primary\" (click)=\"goBack()\"></ion-icon>\r\n      </ion-buttons>\r\n       <ion-buttons slot=\"start\" *ngIf=\"tab !== 0 && login !== 'guest'\">\r\n         <ion-icon name=\"arrow-round-back\" class=\"arrow-back\" size=\"large\"  color=\"primary\" (click)=\"prevPage()\"></ion-icon>\r\n      </ion-buttons>\r\n      <ion-title>\r\n        <div class=\"center\">\r\n          <img src=\"assets/img/logo.png\" height=\"30\"/>\r\n        </div>\r\n    </ion-title> \r\n    <ion-buttons slot=\"end\" >\r\n     <ion-icon name=\"arrow-round-forward\" color=\"primary\" class=\"arrow-next\" size=\"large\" (click)=\"nextPagePredictions()\" \r\n      *ngIf=\"login === 'guest' \"></ion-icon>\r\n    </ion-buttons>\r\n  </ion-toolbar>\r\n</ion-header>\r\n<ion-content *ngIf=\"tab === 0\" class=\"padding\">\r\n  <div class=\"toolbar-div\">\r\n    <ion-title class=\"title\">Configuraciones</ion-title>\r\n  </div>\r\n  <div>\r\n      <ion-chip color=\"light\">\r\n        <ion-avatar>\r\n          <ion-icon class=\"icon-m\" name=\"person\" color=\"primary\"></ion-icon>\r\n        </ion-avatar>\r\n        <ion-label color=\"primary\">Apodo</ion-label>\r\n      </ion-chip>\r\n      <ion-grid>\r\n      <ion-row >\r\n      <ion-col size=\"9\">\r\n        <ion-input type=\"text\" size=\"6\" placeholder=\"Introduzca su apodo\" [(ngModel)]=\"nickname\" \r\n        [disabled]=\" mydisabled\" *ngIf=\"login !=='guest'\"></ion-input>\r\n        <ion-input type=\"text\" size=\"6\" placeholder=\"Introduzca su apodo\" [(ngModel)]=\"nickname\" \r\n        [disabled]=\"disabled_invited\" *ngIf=\"login ==='guest'\"></ion-input>\r\n      </ion-col>\r\n       <ion-col size=\"3\">\r\n          <ion-button fill=\"outline\" color=\"danger\" size=\"default\" *ngIf=\"mydisabled === true && login !== 'guest'\" \r\n            (click)=\"setDisabled()\"><ion-icon size=\"large\" name=\"create\" ></ion-icon></ion-button>\r\n          <ion-button fill=\"outline\" color=\"danger\" size=\"default\" *ngIf=\"mydisabled === false && login !== 'guest'\" \r\n            (click)=\"setName()\"><ion-icon size=\"large\" name=\"save\"></ion-icon></ion-button>\r\n          <ion-button fill=\"outline\" color=\"danger\" size=\"default\" \r\n            *ngIf=\"login === 'guest' && disabled_invited === false\" \r\n            (click)=\"setName()\">\r\n          <ion-icon size=\"large\" name=\"save\"></ion-icon>\r\n          </ion-button>\r\n          <ion-button fill=\"outline\" color=\"danger\" size=\"default\" \r\n            *ngIf=\"disabled_invited === true && login === 'guest'\" \r\n            (click)=\"setDisabled()\"><ion-icon size=\"large\" name=\"create\" ></ion-icon>\r\n          </ion-button>\r\n       </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n  </div>\r\n   <div >\r\n      <ion-chip color=\"light\">\r\n          <ion-avatar>\r\n            <ion-icon class=\"icon-m\" name=\"star\" color=\"primary\"></ion-icon>\r\n          </ion-avatar>\r\n          <ion-label color=\"primary\">Mis Equipos Favoritos</ion-label>\r\n        </ion-chip>\r\n    </div>\r\n      <div >\r\n        <ion-grid>\r\n          <ion-row >\r\n            <ion-col class=\"center\" size=\"4\" *ngIf=\"team_logo1 === undefined\">\r\n              <ion-icon src=\"assets/img/add.svg\" color=\"danger\" class=\"icon-xl\" (click)=\"selectLeagues()\" ></ion-icon>\r\n            <div class=\"center\">\r\n              <ion-label class=\"text-size-s\" color=\"dark\">Agregar Equipo</ion-label>\r\n            </div>\r\n            </ion-col>\r\n            <ion-col class=\"center\" size=\"4\" *ngIf=\"team_logo1 !== undefined\" >\r\n              <div class=\"center\">\r\n                <ion-icon name=\"close-circle\" class=\"remove\" color=\"primary\" (click)=\"removeTeam(id_team1)\"></ion-icon>\r\n                <img [src]=\"team_logo1\" class=\"team\"/>\r\n              </div>\r\n              <div class=\"center\">\r\n                <ion-label class=\"text-size-s\" color=\"dark\">{{name_team1}}</ion-label>\r\n              </div> \r\n            </ion-col>\r\n             <ion-col class=\"center\" size=\"4\" *ngIf=\"team_logo2 === undefined\">\r\n              <ion-icon src=\"assets/img/add.svg\" color=\"danger\" class=\"icon-xl\" (click)=\"selectLeagues()\" ></ion-icon>\r\n              <div class=\"center\">\r\n                <ion-label class=\"text-size-s\" color=\"dark\">Agregar Equipo</ion-label>\r\n            </div>\r\n            </ion-col>\r\n            <ion-col class=\"center\" size=\"4\" *ngIf=\"team_logo2 !== undefined\">\r\n              <div class=\"center\">\r\n                <ion-icon name=\"close-circle\" class=\"remove\" color=\"primary\" (click)=\"removeTeam(id_team2)\"></ion-icon>\r\n                <img [src]=\"team_logo2\" class=\"team\"/>\r\n              </div>\r\n              <div class=\"center\">\r\n                <ion-label class=\"text-size-s\" color=\"dark\">{{name_team2}}</ion-label>\r\n              </div> \r\n            </ion-col>\r\n             <ion-col  class=\"center\" size=\"4\" *ngIf=\"team_logo3 === undefined\">\r\n              <ion-icon src=\"assets/img/add.svg\" color=\"danger\" class=\"icon-xl\" (click)=\"selectLeagues()\"></ion-icon>\r\n            <div class=\"center\">\r\n              <ion-label class=\"text-size-s\" color=\"dark\">Agregar Equipo</ion-label>\r\n            </div>\r\n            </ion-col>\r\n            <ion-col class=\"center\" size=\"4\" *ngIf=\"name_team3 !== undefined\">\r\n              <div class=\"center\">\r\n                <ion-icon name=\"close-circle\" class=\"remove\" color=\"primary\" (click)=\"removeTeam(id_team3)\"></ion-icon>\r\n                <img [src]=\"team_logo3\" class=\"team\"/>\r\n              </div>\r\n              <div class=\"center\">\r\n                <ion-label class=\"text-size-s\" color=\"dark\">{{name_team3}}</ion-label>\r\n              </div>  \r\n            </ion-col>\r\n          </ion-row>\r\n        </ion-grid>\r\n    </div>\r\n    <div>\r\n      <ion-chip color=\"light\">\r\n        <ion-avatar>\r\n          <ion-icon class=\"icon-m\" name=\"notifications\" color=\"primary\"></ion-icon>\r\n        </ion-avatar>\r\n        <ion-label color=\"primary\">Notificaciones</ion-label>\r\n      </ion-chip>\r\n       <ion-row >\r\n      <ion-col>\r\n        <ion-item>\r\n            <ion-label>Predicciones</ion-label>\r\n            <ion-toggle slot=\"start\" color=\"danger\"></ion-toggle>\r\n        </ion-item>\r\n      </ion-col>\r\n    </ion-row>\r\n    <ion-row >\r\n        <ion-col>\r\n          <ion-item>\r\n              <ion-label>En Vivo</ion-label>\r\n              <ion-toggle slot=\"start\" color=\"danger\"></ion-toggle>\r\n          </ion-item>\r\n        </ion-col>\r\n      </ion-row>\r\n    </div>\r\n    <div >\r\n      <ion-chip color=\"light\">\r\n        <ion-avatar>\r\n          <ion-icon class=\"icon-m\" name=\"contacts\" color=\"primary\"></ion-icon>\r\n        </ion-avatar>\r\n        <ion-label color=\"primary\">Redes Sociales</ion-label>\r\n      </ion-chip>\r\n    <ion-row >\r\n      <ion-col size=\"12\">\r\n        <ion-button  *ngIf=\"isLoggedIn === false\" shape=\"round\" size=\"small\" color=\"primary\" expand=\"block\" \r\n        (click)=\"fbLogin()\">CONECTAR CON FACEBOOK </ion-button>\r\n      </ion-col>\r\n       <ion-col size=\"12\">\r\n        <ion-button *ngIf=\"isLoggedIn === true\" shape=\"round\" size=\"small\" color=\"danger\" expand=\"block\" \r\n        (click)=\"logout()\" >CONECTADO CON FACEBOOK, {{users.name}}</ion-button>\r\n      </ion-col>\r\n    </ion-row>\r\n    </div>\r\n    <div >\r\n      <ion-chip color=\"light\">\r\n        <ion-avatar>\r\n          <ion-icon class=\"icon-m\" name=\"build\" color=\"primary\"></ion-icon>\r\n        </ion-avatar>\r\n        <ion-label color=\"primary\">Aplicación</ion-label>\r\n      </ion-chip>\r\n      <ion-row >\r\n        <ion-col size=\"12\">\r\n          <ion-button fill=\"outline\" color=\"danger\" routerLink=\"/tutorial\"  size=\"small\" expand=\"block\">TUTORIAL</ion-button>\r\n        </ion-col>\r\n     \r\n        <ion-col size=\"12\">\r\n          <ion-button (click)=\"downClient()\" fill=\"outline\" color=\"danger\"  size=\"small\" expand=\"block\" >BAJA</ion-button>\r\n        </ion-col>\r\n      </ion-row>\r\n    </div>\r\n</ion-content>\r\n<ion-content *ngIf=\"tab === 1\" class=\"padding\">\r\n  <ion-list *ngFor=\"let item of leagues; let i = index\">\r\n    <ion-item (click)=\"selectTeam(item.id_competitions,i)\"> \r\n      <ion-grid>\r\n        <ion-row>\r\n           <ion-col size=\"2\">\r\n            <img class=\"img-size-s\" src=\"{{item.competiton_type.competition_logo}}.svg\">\r\n          </ion-col>\r\n          <ion-col size=\"10\">\r\n            <div class=\"div\">\r\n            <ion-label class=\"text-size-m\">{{item.competiton_type.name}}</ion-label>\r\n          </div>\r\n          </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>\r\n  </ion-list>\r\n</ion-content>\r\n<ion-content *ngIf=\"tab === 2\" class=\"padding\">\r\n  <ion-list *ngFor=\"let item of teams; let i= index\">\r\n    <ion-item (click)=\"saveTeam(i, item)\"> \r\n      <ion-grid>\r\n        <ion-row>\r\n           <ion-col size=\"2\">\r\n            <img class=\"img-size-s\" *ngIf=\"id_league !== 67\" src=\"{{item.team_logo}}\">\r\n            <img class=\"img-size-s\" *ngIf=\"id_league === 67\" src=\"./assets/img/favicon.png\">\r\n          </ion-col>\r\n          <ion-col size=\"10\">\r\n            <div class=\"div\">\r\n            <ion-label class=\"text-size-l\">{{item.short_name}}</ion-label>\r\n          </div>\r\n          </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>\r\n  </ion-list>\r\n</ion-content>"

/***/ }),

/***/ "./src/app/pages/configurations/configurations.page.scss":
/*!***************************************************************!*\
  !*** ./src/app/pages/configurations/configurations.page.scss ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".center {\n  margin: 0 auto;\n  text-align: center; }\n\n.text-size-s {\n  font-size: 0.6em;\n  text-align: center;\n  margin-top: 10px; }\n\n.icon-xl {\n  font-size: 45px; }\n\n.icon-m {\n  font-size: 25px; }\n\n.toolbar-div {\n  margin: 16px;\n  width: 90%;\n  height: 60px;\n  background-color: #0377B4; }\n\n.title {\n  padding-top: 16px;\n  text-align: center;\n  color: #ffffff; }\n\n.img-size-s {\n  width: 50px;\n  height: 50px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  height: 100%;\n  border: none; }\n\n.text-size-m {\n  font-size: 15px;\n  width: 100%;\n  float: right;\n  size: 0.3em; }\n\n.text-size-l {\n  font-size: 20px;\n  width: 100%;\n  float: right;\n  size: 0.3em;\n  color: #002878; }\n\n.arrow-back {\n  text-align: center;\n  float: left;\n  margin-left: 15px; }\n\n.remove {\n  float: right;\n  width: 20px;\n  height: 20px; }\n\n.team {\n  height: 50px;\n  width: 50px; }\n\n.div_team {\n  width: 250px;\n  text-align: left; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGFnZXMvY29uZmlndXJhdGlvbnMvRDpcXERvY3VtZW50c1xccHJvZ3JhbW1pbmcgcHJvamVjdHNcXGFwcE1hc1Nwb3J0L3NyY1xcYXBwXFxwYWdlc1xcY29uZmlndXJhdGlvbnNcXGNvbmZpZ3VyYXRpb25zLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFdEI7RUFDSSxnQkFBZ0I7RUFDaEIsa0JBQWtCO0VBQ2xCLGdCQUFnQixFQUFBOztBQUVwQjtFQUNJLGVBQWUsRUFBQTs7QUFFbkI7RUFDSSxlQUFlLEVBQUE7O0FBRW5CO0VBQ0UsWUFBWTtFQUNaLFVBQVU7RUFDVixZQUFZO0VBQ1oseUJBQXlCLEVBQUE7O0FBRTNCO0VBQ0UsaUJBQWlCO0VBQ2pCLGtCQUFrQjtFQUNsQixjQUFjLEVBQUE7O0FBRWhCO0VBQ0ksV0FBVztFQUNYLFlBQVksRUFBQTs7QUFFaEI7RUFDRyxhQUFhO0VBQ2IsdUJBQXVCO0VBQ3ZCLG1CQUFtQjtFQUNuQixZQUFZO0VBQ1osWUFBWSxFQUFBOztBQUVmO0VBQ0ksZUFBZTtFQUNmLFdBQVc7RUFDWCxZQUFZO0VBQ1osV0FBVyxFQUFBOztBQUVmO0VBQ0ksZUFBZTtFQUNmLFdBQVc7RUFDWCxZQUFZO0VBQ1osV0FBVztFQUNYLGNBQWMsRUFBQTs7QUFFbEI7RUFDRSxrQkFBa0I7RUFDbEIsV0FBVztFQUNYLGlCQUFpQixFQUFBOztBQUVuQjtFQUNFLFlBQVk7RUFDWixXQUFXO0VBQ1gsWUFBWSxFQUFBOztBQUVkO0VBQ0UsWUFBWTtFQUNaLFdBQVcsRUFBQTs7QUFFYjtFQUNFLFlBQVk7RUFDWixnQkFBZ0IsRUFBQSIsImZpbGUiOiJzcmMvYXBwL3BhZ2VzL2NvbmZpZ3VyYXRpb25zL2NvbmZpZ3VyYXRpb25zLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5jZW50ZXJ7XHJcbiAgICBtYXJnaW46IDAgYXV0bztcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxufVxyXG4udGV4dC1zaXplLXN7XHJcbiAgICBmb250LXNpemU6IDAuNmVtO1xyXG4gICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG4gICAgbWFyZ2luLXRvcDogMTBweDtcclxufVxyXG4uaWNvbi14bCB7XHJcbiAgICBmb250LXNpemU6IDQ1cHg7XHJcbn1cclxuLmljb24tbSB7XHJcbiAgICBmb250LXNpemU6IDI1cHg7XHJcbn1cclxuLnRvb2xiYXItZGl2IHtcclxuICBtYXJnaW46IDE2cHg7XHJcbiAgd2lkdGg6IDkwJTtcclxuICBoZWlnaHQ6IDYwcHg7XHJcbiAgYmFja2dyb3VuZC1jb2xvcjogIzAzNzdCNDtcclxufVxyXG4udGl0bGUge1xyXG4gIHBhZGRpbmctdG9wOiAxNnB4O1xyXG4gIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICBjb2xvcjogI2ZmZmZmZjtcclxuIH1cclxuLmltZy1zaXplLXN7XHJcbiAgICB3aWR0aDogNTBweDtcclxuICAgIGhlaWdodDogNTBweDtcclxuIH1cclxuLmRpdntcclxuICAgZGlzcGxheTogZmxleDtcclxuICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgIGFsaWduLWl0ZW1zOiBjZW50ZXI7XHJcbiAgIGhlaWdodDogMTAwJTtcclxuICAgYm9yZGVyOiBub25lO1xyXG59XHJcbi50ZXh0LXNpemUtbXtcclxuICAgIGZvbnQtc2l6ZTogMTVweDtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgZmxvYXQ6IHJpZ2h0O1xyXG4gICAgc2l6ZTogMC4zZW07XHJcbn1cclxuLnRleHQtc2l6ZS1se1xyXG4gICAgZm9udC1zaXplOiAyMHB4O1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbiAgICBmbG9hdDogcmlnaHQ7XHJcbiAgICBzaXplOiAwLjNlbTtcclxuICAgIGNvbG9yOiAjMDAyODc4O1xyXG59XHJcbi5hcnJvdy1iYWNrIHtcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbiAgZmxvYXQ6IGxlZnQ7XHJcbiAgbWFyZ2luLWxlZnQ6IDE1cHg7XHJcbn1cclxuLnJlbW92ZSB7XHJcbiAgZmxvYXQ6IHJpZ2h0O1xyXG4gIHdpZHRoOiAyMHB4O1xyXG4gIGhlaWdodDogMjBweDtcclxufVxyXG4udGVhbSB7XHJcbiAgaGVpZ2h0OiA1MHB4O1xyXG4gIHdpZHRoOiA1MHB4O1xyXG59XHJcbi5kaXZfdGVhbXtcclxuICB3aWR0aDogMjUwcHg7XHJcbiAgdGV4dC1hbGlnbjogbGVmdDtcclxufVxyXG4iXX0= */"

/***/ }),

/***/ "./src/app/pages/configurations/configurations.page.ts":
/*!*************************************************************!*\
  !*** ./src/app/pages/configurations/configurations.page.ts ***!
  \*************************************************************/
/*! exports provided: ConfigurationsPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ConfigurationsPage", function() { return ConfigurationsPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _services_client_client_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _services_configurations_configurations_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../services/configurations/configurations.service */ "./src/app/services/configurations/configurations.service.ts");
/* harmony import */ var _services_competitions_competitions_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ../../services/competitions/competitions.service */ "./src/app/services/competitions/competitions.service.ts");
/* harmony import */ var _ionic_native_facebook_ngx__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ionic-native/facebook/ngx */ "./node_modules/@ionic-native/facebook/ngx/index.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_9__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");










var ConfigurationsPage = /** @class */ (function () {
    function ConfigurationsPage(http, client, config, competitions, fb, router, loading, ionRouter) {
        var _this = this;
        this.http = http;
        this.client = client;
        this.config = config;
        this.competitions = competitions;
        this.fb = fb;
        this.router = router;
        this.loading = loading;
        this.ionRouter = ionRouter;
        this.clientId = localStorage.getItem('id_client');
        this.mydisabled = true;
        this.tab = 0;
        this.isLoggedIn = false;
        this.disabled_invited = false;
        this.users = { id: '', name: '', email: '', picture: { data: { url: '' } } };
        this.fb.getLoginStatus()
            .then(function (res) {
            console.log(res.status);
            if (res.status === 'connect') {
                _this.isLoggedIn = true;
            }
            else {
                _this.isLoggedIn = false;
            }
        })
            .catch(function (e) { return console.log(e); });
    }
    ConfigurationsPage.prototype.ngOnInit = function () {
        var _this = this;
        this.http.get(this.client.getClientById(this.clientId))
            .subscribe(function (resp) {
            _this.data = resp['response'];
            _this.nickname = _this.data.nickname;
            _this.login = _this.data.login;
            _this.user_team = _this.data.push_alerts_teams;
            _this.user_team.map(function (value, index) {
                if (index === 0) {
                    _this.team_logo1 = value.push_alert.team_logo;
                    _this.id_team1 = value.push_alert.id_ext;
                    _this.name_team1 = value.push_alert.short_name;
                }
                else if (index === 1) {
                    _this.team_logo2 = value.push_alert.team_logo;
                    _this.id_team2 = value.push_alert.id_ext;
                    _this.name_team2 = value.push_alert.short_name;
                }
                else if (index === 2) {
                    _this.team_logo3 = value.push_alert.team_logo;
                    _this.id_team3 = value.push_alert.id_ext;
                    _this.name_team3 = value.push_alert.short_name;
                }
            });
            if (_this.login === "guest") {
                _this.loading.presentAlertConfirm('Información de perfil', 'Selección de nombre de usuario', 'Por favor ingrese un nombre de usuario para su cuenta', [
                    {
                        text: 'Seguir',
                        handler: function () {
                            console.log('Confirm Okay');
                        }
                    }
                ]);
            }
        });
    };
    ConfigurationsPage.prototype.setDisabled = function () {
        this.mydisabled = false;
        this.disabled_invited = false;
    };
    ConfigurationsPage.prototype.setName = function () {
        var _this = this;
        var jData = {
            nickname: this.nickname,
        };
        var url = this.client.update(this.clientId, false);
        console.log('createOrUpdateClient -> Payload -> ' + JSON.stringify(jData));
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]();
        headers.append("Accept", 'application/json');
        headers.append('Content-Type', 'application/json');
        var httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
                'Content-Type': 'application/json'
            })
        };
        this.http.post(url, jData, httpOptions)
            .subscribe(function (data) {
            _this.responseInfo = data['response'];
            _this.mydisabled = true;
            _this.disabled_invited = true;
        }, function (error) {
            console.log(error);
        });
    };
    ConfigurationsPage.prototype.prevPage = function () {
        this.tab--;
    };
    ConfigurationsPage.prototype.selectLeagues = function () {
        var _this = this;
        this.tab++;
        this.http.get(this.competitions.getCompetitionsFootball())
            .toPromise()
            .then(function (resp) {
            _this.infoFootball = resp['response'];
            _this.leagues = _this.infoFootball.competitions;
        });
    };
    ConfigurationsPage.prototype.selectTeam = function (id_competition, i) {
        var _this = this;
        this.tab++;
        this.id_league = this.leagues[i].id_competitions;
        console.log(this.id_league);
        this.http.get(this.competitions.getAllTeamByCompetition(id_competition))
            .toPromise()
            .then(function (resp) {
            _this.dataTeams = resp['response'];
            _this.teams = _this.dataTeams.teams;
        });
    };
    ConfigurationsPage.prototype.saveTeam = function (index, value) {
        var _this = this;
        this.http.get(this.client.getClientById(this.clientId))
            .subscribe(function (resp) {
            _this.data = resp['response'];
            console.log(_this.data);
            _this.nickname = _this.data.nickname;
            _this.user_team = _this.data.push_alerts_teams;
            /**********  Save team and dhow in the buttoms  **********/
            var dataArray = [];
            var add_push_alert = _this.user_team;
            dataArray.push(value.id_teams);
            if (_this.user_team.length === 0) {
                _this.team_logo1 = value.team_logo;
                _this.id_team1 = value.id_teams;
                _this.name_team1 = value.short_name;
            }
            else if (_this.user_team.length === 1) {
                _this.team_logo2 = value.team_logo;
                _this.id_team2 = value.id_teams;
                _this.name_team2 = value.short_name;
            }
            else if (_this.user_team.length === 2) {
                _this.team_logo3 = value.team_logo;
                _this.id_team3 = value.id_teams;
                _this.name_team3 = value.short_name;
            }
            var jData = {
                add_push_alert: dataArray
            };
            var url = _this.client.update(_this.clientId, false);
            var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]();
            headers.append("Accept", 'application/json');
            headers.append('Content-Type', 'application/json');
            var httpOptions = {
                headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
                    'Content-Type': 'application/json'
                })
            };
            _this.http.post(url, jData, httpOptions)
                .subscribe(function (data) {
                _this.result = data['response'];
            }, function (error) {
                console.log(error);
            });
            _this.tab = 0;
        });
    };
    ConfigurationsPage.prototype.removeTeam = function (id_team) {
        var _this = this;
        var i = 0;
        /***********  Declarations of variables for remove teams of the list **********/
        var dataArray = [];
        var remove_push_alert = this.user_team;
        dataArray.push(id_team);
        var jData = {
            remove_push_alert: dataArray
        };
        var url = this.client.update(this.clientId, false);
        console.log('createOrUpdateClient -> Payload -> ' + JSON.stringify(jData));
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]();
        headers.append("Accept", 'application/json');
        headers.append('Content-Type', 'application/json');
        var httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
                'Content-Type': 'application/json'
            })
        };
        this.http.post(url, jData, httpOptions)
            .subscribe(function (data) {
            _this.result = data['response'];
            /********** Get infoClient for consult teams add**********/
            _this.http.get(_this.client.getClientById(_this.clientId))
                .subscribe(function (resp) {
                _this.data = resp['response'];
                _this.nickname = _this.data.nickname;
                _this.user_team = _this.data.push_alerts_teams;
                if (_this.user_team !== null && _this.user_team.length === 1) {
                    _this.team_logo1 = _this.user_team[0].push_alert.team_logo;
                    _this.id_team1 = _this.user_team[0].push_alert.id_ext;
                    _this.name_team1 = _this.user_team[0].push_alert.short_name;
                    _this.team_logo2 = undefined;
                    _this.id_team2 = undefined;
                    _this.name_team2 = undefined;
                    _this.team_logo3 = undefined;
                    _this.id_team3 = undefined;
                    _this.name_team3 = undefined;
                }
                else if (_this.user_team !== null && _this.user_team.length === 2) {
                    _this.team_logo1 = _this.user_team[0].push_alert.team_logo;
                    _this.id_team1 = _this.user_team[0].push_alert.id_ext;
                    _this.name_team1 = _this.user_team[0].push_alert.short_name;
                    _this.team_logo2 = _this.user_team[1].push_alert.team_logo;
                    _this.id_team2 = _this.user_team[1].push_alert.id_ext;
                    _this.name_team2 = _this.user_team[1].push_alert.short_name;
                    _this.team_logo3 = undefined;
                    _this.id_team3 = undefined;
                    _this.name_team3 = undefined;
                }
                else {
                    _this.team_logo1 = undefined;
                    _this.id_team1 = undefined;
                    _this.name_team1 = undefined;
                }
            });
        }, function (error) {
            console.log(error);
        });
        this.tab = 0;
    };
    ConfigurationsPage.prototype.fbLogin = function () {
        var _this = this;
        this.fb.login(['public_profile', 'user_friends', 'email'])
            .then(function (res) {
            if (res.status === 'connected') {
                _this.isLoggedIn = true;
                _this.getUserDetail(res.authResponse.userID);
            }
            else {
                _this.isLoggedIn = false;
            }
        })
            .catch(function (e) { return console.log('Error logging into Facebook', e); });
    };
    ConfigurationsPage.prototype.getUserDetail = function (userid) {
        var _this = this;
        this.fb.api('/' + userid + '/?fields=id,email,name,picture', ['public_profile'])
            .then(function (res) {
            console.log(res);
            _this.users = res;
        })
            .catch(function (e) {
            console.log(e);
        });
    };
    ConfigurationsPage.prototype.logout = function () {
        var _this = this;
        this.fb.logout()
            .then(function (res) {
            _this.isLoggedIn = false;
        })
            .catch(function (e) { return console.log('Error logout from Facebook', e); });
    };
    ConfigurationsPage.prototype.downClient = function () {
        var _this = this;
        this.http.get(this.client.downClient(this.login))
            .subscribe(function (resp) {
            _this.infoDown = resp['response'];
            console.log(_this.infoDown);
            _this.router.navigateByUrl('/');
        });
    };
    ConfigurationsPage.prototype.nextPagePredictions = function () {
        if (this.nickname === null || this.nickname === undefined) {
            this.loading.presentAlertConfirm('Información de perfil', 'Selección de nombre de usuario', 'Por favor ingrese un nombre de usuario para su cuenta', [
                {
                    text: 'Seguir',
                    handler: function () {
                        console.log('Confirm Okay');
                    }
                }
            ]);
        }
        else {
            this.router.navigateByUrl('/tabs/predictions');
        }
    };
    ConfigurationsPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    ConfigurationsPage.prototype.goBack = function () {
        this.ionRouter.back();
    };
    ConfigurationsPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-configurations',
            template: __webpack_require__(/*! ./configurations.page.html */ "./src/app/pages/configurations/configurations.page.html"),
            styles: [__webpack_require__(/*! ./configurations.page.scss */ "./src/app/pages/configurations/configurations.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _services_client_client_service__WEBPACK_IMPORTED_MODULE_3__["ClientService"], _services_configurations_configurations_service__WEBPACK_IMPORTED_MODULE_4__["ConfigurationsService"],
            _services_competitions_competitions_service__WEBPACK_IMPORTED_MODULE_5__["CompetitionsService"], _ionic_native_facebook_ngx__WEBPACK_IMPORTED_MODULE_6__["Facebook"], _angular_router__WEBPACK_IMPORTED_MODULE_7__["Router"],
            _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_8__["LoadingService"], _angular_common__WEBPACK_IMPORTED_MODULE_9__["Location"]])
    ], ConfigurationsPage);
    return ConfigurationsPage;
}());



/***/ })

}]);
//# sourceMappingURL=pages-configurations-configurations-module.js.map