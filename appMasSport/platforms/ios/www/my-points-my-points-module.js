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

module.exports = "<ion-header>\r\n  <ion-toolbar>\r\n    <ion-buttons slot=\"start\">\r\n      <ion-menu-button color=\"primary\" menu=\"menuprincipal\"></ion-menu-button>\r\n    </ion-buttons>\r\n    <div class=\"center\">\r\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\r\n    </div>\r\n  </ion-toolbar>\r\n</ion-header>\r\n<ion-content *ngIf=\"sizeData > 0\">\r\n  <div class=\"background\">\r\n    <ion-label class=\"white margin-top-m\"></ion-label>\r\n    <div style=\"position:absolute; top: 95px; background: #ffffff; width: 100%; height:2px;\"></div>\r\n     <ion-label class=\"white margin-top-m text-size-g\">{{nickname}}</ion-label>\r\n    <div class=\"circle-red\">\r\n      <div  class=\"white margin-top-m\">\r\n        <ion-label class=\"white margin-top-m text-size-g\">{{totalPoints}}</ion-label>\r\n        <ion-label class=\"white margin-top-s text-size-r\">Puntos</ion-label>\r\n      </div>\r\n    </div>  \r\n  </div>\r\n  <ion-item color=\"danger\">\r\n    <ion-grid>\r\n      <ion-row>\r\n        <ion-col size=\"6\">\r\n          <ion-label class=\"text-size-m\">Torneo</ion-label>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n          <div class=\"center-text right\">\r\n            <ion-label class=\"text-size-m\">Aciertos</ion-label>\r\n          </div>\r\n        </ion-col>\r\n        <ion-col size=\"3\">\r\n          <div class=\"center-text right\">\r\n            <ion-label class=\"text-size-m\">Puntos</ion-label>\r\n          </div>\r\n        </ion-col>\r\n      </ion-row>\r\n    </ion-grid>\r\n  </ion-item>\r\n    <ion-list none *ngFor =\"let resp of leaderboard; let i = index \">\r\n    <ion-item *ngIf=\"resp.sport_id === 1 && resp.show === 1\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"2\">\r\n          <img class=\"img-size-s\" src=\"{{resp.logo}}.svg\">\r\n          </ion-col>\r\n          <ion-col size=\"5\">\r\n          <div class=\"divItem\">\r\n            <ion-label class=\"text-size-s\">{{resp.name}}</ion-label>\r\n          </div>\r\n          </ion-col>\r\n          <ion-col size=\"3\">\r\n            <div class=\"divItem\">\r\n              <ion-label class=\"text-size-s\">{{resp.hits}}</ion-label>\r\n            </div>\r\n          </ion-col>\r\n          <ion-col size=\"2\">\r\n            <div class=\"divItem\">\r\n              <span class=\"text-size-s right\" >{{resp.score}}</span>\r\n            </div>\r\n          </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>\r\n    <ion-item *ngIf=\"resp.sport_id === 2 && resp.show === 1 && resp.status === 1\">\r\n      <ion-grid>\r\n        <ion-row >\r\n          <ion-col size=\"2\">\r\n          <img class=\"img-size-s\" src=\"{{resp.logo}}.svg\">\r\n          </ion-col>\r\n          <ion-col size=\"5\">\r\n          <div class=\"divItem\">\r\n            <ion-label class=\"text-size-s\">{{resp.name}}</ion-label>\r\n          </div>\r\n          </ion-col>\r\n          <ion-col size=\"3\">\r\n            <div class=\"divItem\">\r\n              <ion-label class=\"text-size-s\">{{resp.hits}}</ion-label>\r\n            </div>\r\n          </ion-col>\r\n          <ion-col size=\"2\">\r\n            <div class=\"divItem\">\r\n              <span class=\"text-size-s right\" >{{resp.score}}</span>\r\n            </div>\r\n          </ion-col>\r\n        </ion-row>\r\n      </ion-grid>\r\n    </ion-item>\r\n  </ion-list>\r\n</ion-content>\r\n<ion-content *ngIf=\"sizeData === 0\">\r\n  <div class=\"div\">\r\n    <ion-icon name=\"information-circle-outline\" class=\"icon-alert\"></ion-icon>\r\n  </div>\r\n  <div class=\"div\">\r\n     Todavía no has hecho predicciones o no se han Jugado los partidos. \r\n  </div>\r\n</ion-content>\r\n"

/***/ }),

/***/ "./src/app/pages/my-points/my-points.page.scss":
/*!*****************************************************!*\
  !*** ./src/app/pages/my-points/my-points.page.scss ***!
  \*****************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".background-red {\n  padding-top: 5px;\n  -webkit-padding-after: 5px;\n          padding-block-end: 5px;\n  background: red; }\n\n.white-right {\n  color: white;\n  font-size: 16px;\n  float: right; }\n\n.text-size-s {\n  font-size: 15px;\n  width: 100%;\n  float: right;\n  size: 0.3em;\n  color: #002878;\n  justify-content: center;\n  align-items: center; }\n\n.text-size-r {\n  font-size: 20px;\n  width: 100%;\n  float: right;\n  color: #0377B4; }\n\n.ion-icon {\n  font-size: 64px; }\n\n.white-left {\n  color: white;\n  padding-left: 18px;\n  font-size: 16px; }\n\n.white {\n  color: white; }\n\n.img-size-s {\n  width: 40px;\n  height: 40px; }\n\n.div {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  width: 100%;\n  border: none;\n  font-size: 30px;\n  color: #777777;\n  text-align: center; }\n\n.divItem {\n  display: flex;\n  justify-content: center;\n  align-items: center;\n  height: 100%;\n  border: none; }\n\n.div-width {\n  width: 100%;\n  text-align: right; }\n\n.right {\n  float: right; }\n\n.circle-red {\n  position: absolute;\n  top: 30px;\n  left: 0;\n  right: 0;\n  display: block;\n  width: 130px;\n  height: 130px;\n  line-height: 25px;\n  background-color: rgba(227, 30, 48, 0.5) !important;\n  border-radius: 50%;\n  text-align: center;\n  color: #002878;\n  font-size: 1.8em;\n  margin: 0 auto !important;\n  border-style: solid;\n  border-width: 2px;\n  border-color: #ffffff; }\n\n.background {\n  text-align: center;\n  height: 190px;\n  background-image: url('back-pts.png');\n  background-size: cover;\n  background-position: center; }\n\n.margin-top-m {\n  margin-top: 0.9em; }\n\n.margin-top-s {\n  margin-top: 0.5em; }\n\n.margin-top-xs {\n  margin-top: 0.8em; }\n\n.text-size-l {\n  size: 1em; }\n\n.text-size-m {\n  font-size: 15px; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.text-size-g {\n  font-size: 25px; }\n\n.icon-alert {\n  margin-top: 15px;\n  width: 60px;\n  height: 60px;\n  font-size: 35px;\n  font-weight: bold;\n  color: #777777; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGFnZXMvbXktcG9pbnRzL0Q6XFxEb2N1bWVudHNcXHByb2dyYW1taW5nIHByb2plY3RzXFxhcHBNYXNTcG9ydC9zcmNcXGFwcFxccGFnZXNcXG15LXBvaW50c1xcbXktcG9pbnRzLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGdCQUFnQjtFQUNoQiwwQkFBc0I7VUFBdEIsc0JBQXNCO0VBQ3RCLGVBQWUsRUFBQTs7QUFFbkI7RUFDSSxZQUFZO0VBQ1osZUFBZTtFQUNmLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixXQUFXO0VBQ1gsY0FBYztFQUNkLHVCQUF1QjtFQUN2QixtQkFBbUIsRUFBQTs7QUFFdkI7RUFDSSxlQUFlO0VBQ2YsV0FBVztFQUNYLFlBQVk7RUFDWixjQUFjLEVBQUE7O0FBRWxCO0VBQ0ksZUFBZSxFQUFBOztBQUduQjtFQUNJLFlBQVk7RUFDWixrQkFBa0I7RUFDbEIsZUFBZSxFQUFBOztBQUVuQjtFQUNJLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxXQUFXO0VBQ1gsWUFBWSxFQUFBOztBQUVoQjtFQUNHLGFBQWE7RUFDYix1QkFBdUI7RUFDdkIsbUJBQW1CO0VBQ25CLFdBQVc7RUFDWCxZQUFZO0VBQ1osZUFBZTtFQUNmLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFckI7RUFDRyxhQUFhO0VBQ2IsdUJBQXVCO0VBQ3ZCLG1CQUFtQjtFQUNuQixZQUFZO0VBQ1osWUFBWSxFQUFBOztBQUVmO0VBQ0ksV0FBVztFQUNYLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLFlBQVksRUFBQTs7QUFFaEI7RUFDSSxrQkFBaUI7RUFDakIsU0FBUztFQUNULE9BQU07RUFDTixRQUFRO0VBQ1IsY0FBYTtFQUNiLFlBQVc7RUFDWCxhQUFhO0VBQ2IsaUJBQWlCO0VBQ2pCLG1EQUFpRDtFQUNqRCxrQkFBa0I7RUFDbEIsa0JBQWlCO0VBQ2pCLGNBQW9CO0VBQ3BCLGdCQUFlO0VBQ2YseUJBQXlCO0VBQ3pCLG1CQUFtQjtFQUNuQixpQkFBaUI7RUFDakIscUJBQXFCLEVBQUE7O0FBRXpCO0VBQ0ksa0JBQWtCO0VBQ2xCLGFBQWE7RUFDYixxQ0FBeUQ7RUFDekQsc0JBQXNCO0VBQ3RCLDJCQUEyQixFQUFBOztBQUUvQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLGlCQUFpQixFQUFBOztBQUVyQjtFQUNJLFNBQVMsRUFBQTs7QUFFYjtFQUNJLGVBQWUsRUFBQTs7QUFFbkI7RUFDRSxjQUFjO0VBQ2Qsa0JBQWtCLEVBQUE7O0FBRXBCO0VBQ0ksZUFBZSxFQUFBOztBQUVuQjtFQUNFLGdCQUFnQjtFQUNoQixXQUFXO0VBQ1gsWUFBWTtFQUNaLGVBQWU7RUFDZixpQkFBaUI7RUFDakIsY0FBYyxFQUFBIiwiZmlsZSI6InNyYy9hcHAvcGFnZXMvbXktcG9pbnRzL215LXBvaW50cy5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuYmFja2dyb3VuZC1yZWQge1xyXG4gICAgcGFkZGluZy10b3A6IDVweDtcclxuICAgIHBhZGRpbmctYmxvY2stZW5kOiA1cHg7XHJcbiAgICBiYWNrZ3JvdW5kOiByZWQ7XHJcbn1cclxuLndoaXRlLXJpZ2h0IHtcclxuICAgIGNvbG9yOiB3aGl0ZTtcclxuICAgIGZvbnQtc2l6ZTogMTZweDtcclxuICAgIGZsb2F0OiByaWdodDsgXHJcbn1cclxuLnRleHQtc2l6ZS1ze1xyXG4gICAgZm9udC1zaXplOiAxNXB4O1xyXG4gICAgd2lkdGg6IDEwMCU7XHJcbiAgICBmbG9hdDogcmlnaHQ7XHJcbiAgICBzaXplOiAwLjNlbTtcclxuICAgIGNvbG9yOiAjMDAyODc4O1xyXG4gICAganVzdGlmeS1jb250ZW50OiBjZW50ZXI7XHJcbiAgICBhbGlnbi1pdGVtczogY2VudGVyO1xyXG59XHJcbi50ZXh0LXNpemUtcntcclxuICAgIGZvbnQtc2l6ZTogMjBweDtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgZmxvYXQ6IHJpZ2h0O1xyXG4gICAgY29sb3I6ICMwMzc3QjQ7XHJcbn1cclxuLmlvbi1pY29uIHtcclxuICAgIGZvbnQtc2l6ZTogNjRweDtcclxuICB9XHJcblxyXG4ud2hpdGUtbGVmdHtcclxuICAgIGNvbG9yOiB3aGl0ZTtcclxuICAgIHBhZGRpbmctbGVmdDogMThweDtcclxuICAgIGZvbnQtc2l6ZTogMTZweDtcclxufVxyXG4ud2hpdGV7XHJcbiAgICBjb2xvcjogd2hpdGU7XHJcbn1cclxuLmltZy1zaXplLXN7XHJcbiAgICB3aWR0aDogNDBweDtcclxuICAgIGhlaWdodDogNDBweDtcclxufVxyXG4uZGl2e1xyXG4gICBkaXNwbGF5OiBmbGV4O1xyXG4gICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgd2lkdGg6IDEwMCU7XHJcbiAgIGJvcmRlcjogbm9uZTtcclxuICAgZm9udC1zaXplOiAzMHB4O1xyXG4gICBjb2xvcjogIzc3Nzc3NztcclxuICAgdGV4dC1hbGlnbjogY2VudGVyO1xyXG59XHJcbi5kaXZJdGVte1xyXG4gICBkaXNwbGF5OiBmbGV4O1xyXG4gICBqdXN0aWZ5LWNvbnRlbnQ6IGNlbnRlcjtcclxuICAgYWxpZ24taXRlbXM6IGNlbnRlcjtcclxuICAgaGVpZ2h0OiAxMDAlO1xyXG4gICBib3JkZXI6IG5vbmU7XHJcbn1cclxuLmRpdi13aWR0aHtcclxuICAgIHdpZHRoOiAxMDAlO1xyXG4gICAgdGV4dC1hbGlnbjogcmlnaHQ7XHJcbn1cclxuLnJpZ2h0e1xyXG4gICAgZmxvYXQ6IHJpZ2h0O1xyXG59XHJcbi5jaXJjbGUtcmVke1xyXG4gICAgcG9zaXRpb246YWJzb2x1dGU7XHJcbiAgICB0b3A6IDMwcHg7XHJcbiAgICBsZWZ0OjA7XHJcbiAgICByaWdodDogMDtcclxuICAgIGRpc3BsYXk6YmxvY2s7XHJcbiAgICB3aWR0aDoxMzBweDtcclxuICAgIGhlaWdodDogMTMwcHg7XHJcbiAgICBsaW5lLWhlaWdodDogMjVweDtcclxuICAgIGJhY2tncm91bmQtY29sb3I6IHJnYmEoMjI3LDMwLDQ4LDAuNSkgICFpbXBvcnRhbnQ7XHJcbiAgICBib3JkZXItcmFkaXVzOiA1MCU7XHJcbiAgICB0ZXh0LWFsaWduOmNlbnRlcjtcclxuICAgIGNvbG9yOiByZ2IoMCw0MCwxMjApO1xyXG4gICAgZm9udC1zaXplOjEuOGVtO1xyXG4gICAgbWFyZ2luOiAwIGF1dG8gIWltcG9ydGFudDtcclxuICAgIGJvcmRlci1zdHlsZTogc29saWQ7XHJcbiAgICBib3JkZXItd2lkdGg6IDJweDtcclxuICAgIGJvcmRlci1jb2xvcjogI2ZmZmZmZjtcclxufVxyXG4uYmFja2dyb3VuZHtcclxuICAgIHRleHQtYWxpZ246IGNlbnRlcjtcclxuICAgIGhlaWdodDogMTkwcHg7XHJcbiAgICBiYWNrZ3JvdW5kLWltYWdlOiB1cmwoXCIuLi8uLi8uLi9hc3NldHMvaW1nL2JhY2stcHRzLnBuZ1wiKTtcclxuICAgIGJhY2tncm91bmQtc2l6ZTogY292ZXI7XHJcbiAgICBiYWNrZ3JvdW5kLXBvc2l0aW9uOiBjZW50ZXI7XHJcbn1cclxuLm1hcmdpbi10b3AtbXtcclxuICAgIG1hcmdpbi10b3A6IDAuOWVtO1xyXG59XHJcbi5tYXJnaW4tdG9wLXN7XHJcbiAgICBtYXJnaW4tdG9wOiAwLjVlbTtcclxufVxyXG4ubWFyZ2luLXRvcC14c3tcclxuICAgIG1hcmdpbi10b3A6IDAuOGVtO1xyXG59XHJcbi50ZXh0LXNpemUtbHtcclxuICAgIHNpemU6IDFlbTtcclxufVxyXG4udGV4dC1zaXplLW17XHJcbiAgICBmb250LXNpemU6IDE1cHg7XHJcbn1cclxuLmNlbnRlcntcclxuICBtYXJnaW46IDAgYXV0bztcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnRleHQtc2l6ZS1ne1xyXG4gICAgZm9udC1zaXplOiAyNXB4O1xyXG59XHJcbi5pY29uLWFsZXJ0e1xyXG4gIG1hcmdpbi10b3A6IDE1cHg7XHJcbiAgd2lkdGg6IDYwcHg7XHJcbiAgaGVpZ2h0OiA2MHB4O1xyXG4gIGZvbnQtc2l6ZTogMzVweDtcclxuICBmb250LXdlaWdodDogYm9sZDtcclxuICBjb2xvcjogIzc3Nzc3NztcclxufVxyXG5cclxuIl19 */"

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