(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["pages-get-password-get-password-module"],{

/***/ "./src/app/pages/get-password/get-password.module.ts":
/*!***********************************************************!*\
  !*** ./src/app/pages/get-password/get-password.module.ts ***!
  \***********************************************************/
/*! exports provided: GetPasswordPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GetPasswordPageModule", function() { return GetPasswordPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _get_password_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./get-password.page */ "./src/app/pages/get-password/get-password.page.ts");







var routes = [
    {
        path: '',
        component: _get_password_page__WEBPACK_IMPORTED_MODULE_6__["GetPasswordPage"]
    }
];
var GetPasswordPageModule = /** @class */ (function () {
    function GetPasswordPageModule() {
    }
    GetPasswordPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes)
            ],
            declarations: [_get_password_page__WEBPACK_IMPORTED_MODULE_6__["GetPasswordPage"]]
        })
    ], GetPasswordPageModule);
    return GetPasswordPageModule;
}());



/***/ }),

/***/ "./src/app/pages/get-password/get-password.page.html":
/*!***********************************************************!*\
  !*** ./src/app/pages/get-password/get-password.page.html ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header no-border>\n    <ion-toolbar>\n      <ion-buttons slot=\"start\" >\n        <ion-icon name=\"arrow-round-back\" class=\"arrow-back\" size=\"large\"  color=\"primary\" (click)=\"goBack()\"></ion-icon>\n      </ion-buttons>\n      <ion-title>\n      <div class=\"center\">\n        <img src=\"assets/img/logo.png\" height=\"30\"/>\n      </div>\n      </ion-title>\n    </ion-toolbar>\n</ion-header>\n<ion-content>\n    <ion-card>\n      <ion-card-header>\n        <ion-card-title>¿Olvidó su clave o no se ha suscrito?</ion-card-title>\n      </ion-card-header>\n      <ion-card-content>\n        Para obtener su contraseña por favor ingrese su número de teléfono y presione \"OBTENER CONTRASEÑA\".\n      </ion-card-content>\n      <div class=\"padding-left-right\">\n        <ion-label color=\"danger\">\n          *El servicio está disponible sólo para suscriptores de +Móvil\n        </ion-label>\n      </div>\n    </ion-card>\n    <ion-card>\n      <ion-card-header>\n        <ion-card-title>Introduzca su número de teléfono móvil</ion-card-title>\n      </ion-card-header>\n      <ion-card-content>\n        <ion-item>\n          <ion-label>+507 </ion-label>\n          <ion-input placeholder=\" #12345678\" type=\"tel\" [(ngModel)]=\"msisdn\"></ion-input>\n        </ion-item>\n        <div class=\"padding-top-xs\">\n          <ion-button (click)=\"getPassword()\" shape=\"round\" size=\"default\" color=\"danger\" expand=\"block\">Obtener Contraseña</ion-button>\n        </div>\n        <div class=\"center\">\n          <a class=\"text-size-s\" href=\"http://www.masmovil.com.pa/\">Términos y Condiciones</a>\n        </div>\n      </ion-card-content>\n    </ion-card>\n</ion-content>\n"

/***/ }),

/***/ "./src/app/pages/get-password/get-password.page.scss":
/*!***********************************************************!*\
  !*** ./src/app/pages/get-password/get-password.page.scss ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".center {\n  margin: 0 auto;\n  text-align: center; }\n\n.text-size-s {\n  font-size: 0.8em;\n  text-align: center; }\n\n.padding-top-xs {\n  padding-top: 2px;\n  margin: 0 auto; }\n\n.padding-left-right {\n  padding-left: 15px;\n  padding-right: 15px;\n  margin: 0 auto; }\n\n.text-color-red {\n  color: #FF0000; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL3lzaWRyby9wb3J0YWZvbGlvIGRlIHByb2dyYW1hY2lvbi9hcHBNYXNTcG9ydC9zcmMvYXBwL3BhZ2VzL2dldC1wYXNzd29yZC9nZXQtcGFzc3dvcmQucGFnZS5zY3NzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiJBQUFBO0VBQ0UsY0FBYztFQUNkLGtCQUFrQixFQUFBOztBQUVwQjtFQUNFLGdCQUFnQjtFQUNoQixrQkFBa0IsRUFBQTs7QUFFcEI7RUFDRSxnQkFBZ0I7RUFDaEIsY0FBYyxFQUFBOztBQUVoQjtFQUNFLGtCQUFrQjtFQUNsQixtQkFBbUI7RUFDbkIsY0FBYyxFQUFBOztBQUVoQjtFQUNFLGNBQWMsRUFBQSIsImZpbGUiOiJzcmMvYXBwL3BhZ2VzL2dldC1wYXNzd29yZC9nZXQtcGFzc3dvcmQucGFnZS5zY3NzIiwic291cmNlc0NvbnRlbnQiOlsiLmNlbnRlcntcbiAgbWFyZ2luOiAwIGF1dG87XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cbi50ZXh0LXNpemUtc3tcbiAgZm9udC1zaXplOiAwLjhlbTtcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLnBhZGRpbmctdG9wLXhzIHtcbiAgcGFkZGluZy10b3A6IDJweDtcbiAgbWFyZ2luOiAwIGF1dG87XG59XG4ucGFkZGluZy1sZWZ0LXJpZ2h0IHtcbiAgcGFkZGluZy1sZWZ0OiAxNXB4O1xuICBwYWRkaW5nLXJpZ2h0OiAxNXB4O1xuICBtYXJnaW46IDAgYXV0bztcbn1cbi50ZXh0LWNvbG9yLXJlZCB7XG4gIGNvbG9yOiAjRkYwMDAwO1xufSJdfQ== */"

/***/ }),

/***/ "./src/app/pages/get-password/get-password.page.ts":
/*!*********************************************************!*\
  !*** ./src/app/pages/get-password/get-password.page.ts ***!
  \*********************************************************/
/*! exports provided: GetPasswordPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "GetPasswordPage", function() { return GetPasswordPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _services_client_client_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _services_configurations_configurations_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../services/configurations/configurations.service */ "./src/app/services/configurations/configurations.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");









var GetPasswordPage = /** @class */ (function () {
    function GetPasswordPage(client, config, http, toastController, router, loading, location) {
        this.client = client;
        this.config = config;
        this.http = http;
        this.toastController = toastController;
        this.router = router;
        this.loading = loading;
        this.location = location;
        this.prefix = '507';
        this.clientId = localStorage.getItem('id_client');
    }
    GetPasswordPage.prototype.ngOnInit = function () {
        var _this = this;
        this.loading.presentLoading();
        this.http.get(this.client.getClientById(this.clientId))
            .subscribe(function (resp) {
            _this.info = resp['response'];
            _this.loginUser = _this.info.login;
            _this.status = _this.info.status;
        });
    };
    GetPasswordPage.prototype.getPassword = function () {
        var _this = this;
        var subscribe = true;
        var devices = [];
        var device = {
            msisdn: this.prefix + this.msisdn,
            upstreamChannel: '',
            platform: '',
            language: '',
            cordova: '',
            model: '',
            uuid: '',
            version: '',
            serial: '',
            manufacturer: '',
            id_language: '',
        };
        var lang = {
            name: 'español',
            id_language: 300,
            short_name: 'es'
        };
        var log = {
            msisdn: this.prefix + this.msisdn,
            upstreamChannel: '',
            platform: '',
            language: '',
            cordova: '',
            model: '',
            uuid: '',
            version: '',
            serial: '',
            manufacturer: '',
            id_language: 300
        };
        log.upstreamChannel = this.config.getUpstreamChannel();
        this.loading.presentLoading();
        if (this.config.getPlatform() === 'Web') {
            log.platform = navigator.platform;
            log.language = navigator.language;
        }
        else {
            log.cordova = device.cordova;
            log.model = device.model;
            log.platform = device.platform;
            log.uuid = device.uuid;
            log.version = device.version;
            log.manufacturer = device.manufacturer;
            log.serial = device.serial;
        }
        //antes id_language era 405 para portugues ahora es 300 para español
        var jData = {
            country: 4,
            language: lang ? lang.id_language : 300,
            upstreamChannel: this.config.getUpstreamChannel(),
            log: log,
            login: this.prefix + this.msisdn,
            subscribe: true
        };
        var url = this.client.create();
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
            .toPromise()
            .then(function (data) {
            _this.responseInfo = data['response'];
            _this.loading.presentAlert('Pronto recibirá un mensaje de texto con su clave para ingresar como usuario suscrito a MasSport.');
            // localStorage.setItem('id_client', this.responseInfo.id_client)
            _this.router.navigateByUrl('/');
        }, function (error) {
            console.log(error);
        });
    };
    GetPasswordPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    GetPasswordPage.prototype.goBack = function () {
        if (this.loginUser === 'guest' && this.status === 2) {
            this.router.navigateByUrl('/tabs/predictions');
        }
        else {
            this.location.back();
        }
    };
    GetPasswordPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-get-password',
            template: __webpack_require__(/*! ./get-password.page.html */ "./src/app/pages/get-password/get-password.page.html"),
            styles: [__webpack_require__(/*! ./get-password.page.scss */ "./src/app/pages/get-password/get-password.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_client_client_service__WEBPACK_IMPORTED_MODULE_3__["ClientService"], _services_configurations_configurations_service__WEBPACK_IMPORTED_MODULE_4__["ConfigurationsService"],
            _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], _ionic_angular__WEBPACK_IMPORTED_MODULE_6__["ToastController"],
            _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"], _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_7__["LoadingService"],
            _angular_common__WEBPACK_IMPORTED_MODULE_8__["Location"]])
    ], GetPasswordPage);
    return GetPasswordPage;
}());



/***/ })

}]);
//# sourceMappingURL=pages-get-password-get-password-module.js.map