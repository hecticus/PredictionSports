(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["pages-login-login-module"],{

/***/ "./src/app/pages/login/login.module.ts":
/*!*********************************************!*\
  !*** ./src/app/pages/login/login.module.ts ***!
  \*********************************************/
/*! exports provided: LoginPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginPageModule", function() { return LoginPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _login_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./login.page */ "./src/app/pages/login/login.page.ts");
/* harmony import */ var _ionic_native_device_ngx__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ionic-native/device/ngx */ "./node_modules/@ionic-native/device/ngx/index.js");








var routes = [
    {
        path: '',
        component: _login_page__WEBPACK_IMPORTED_MODULE_6__["LoginPage"]
    }
];
var LoginPageModule = /** @class */ (function () {
    function LoginPageModule() {
    }
    LoginPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes),
            ],
            providers: [
                _ionic_native_device_ngx__WEBPACK_IMPORTED_MODULE_7__["Device"]
            ],
            declarations: [_login_page__WEBPACK_IMPORTED_MODULE_6__["LoginPage"]]
        })
    ], LoginPageModule);
    return LoginPageModule;
}());



/***/ }),

/***/ "./src/app/pages/login/login.page.html":
/*!*********************************************!*\
  !*** ./src/app/pages/login/login.page.html ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header no-border>\n  <ion-toolbar>\n    <div class=\"center\">\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\n    </div>\n  </ion-toolbar>\n</ion-header>\n<ion-content >\n    <div class=\"toolbar-div\">\n    <ion-title class=\"title\">¿Ya estás suscrito?</ion-title>\n  </div>\n  <ion-label position=\"stacked\" class=\"title_label\" color=\"danger-primary\">Número de teléfono</ion-label>\n  <ion-item class=\"item\">\n    <ion-label>+507 </ion-label>\n    <ion-input class=\"input_login\" type=\"tel\" placeholder=\"#12345678\" [(ngModel)]=\"msisdn\"></ion-input>\n  </ion-item>\n  <ion-label class=\"title_label\" position=\"stacked\" color=\"danger-primary\">Contraseña</ion-label>\n  <ion-item class=\"item\">\n    <ion-input placeholder=\"Ingrese el código recibido por SMS.\" type=\"password\" [(ngModel)]=\"password\"></ion-input>\n  </ion-item>\n  <div class=\"padding-left-right\">\n    <div class=\"padding-top-m\">\n      <ion-button shape=\"round\" size=\"default\" color=\"danger\" expand=\"block\" [disabled]=\"(msisdn !== undefined && password !== undefined) ? false : true\" (click)=\"createOrUpdateClient()\">\n      Iniciar sesion\n    </ion-button>\n    </div>\n    <div class=\"padding-top-xs\">\n      <ion-button routerLink='/get-password' shape=\"round\" size=\"default\" color=\"danger\" expand=\"block\">Obtener Contraseña</ion-button>\n    </div>\n    <div class=\"padding-top-xs padding-botton-m\">\n      <ion-button (click)=\"loginSessionInvited()\" shape=\"round\" size=\"default\" color=\"danger\" expand=\"block\">Entrar como invitado</ion-button>\n    </div>\n    <div class=\"center\">\n      <a class=\"text-size-s\" href=\"http://www.masmovil.com.pa/\">Términos y Condiciones</a>\n    </div>\n  </div>\n</ion-content>\n"

/***/ }),

/***/ "./src/app/pages/login/login.page.scss":
/*!*********************************************!*\
  !*** ./src/app/pages/login/login.page.scss ***!
  \*********************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".error-messages {\n  font-size: 0.9em;\n  color: red;\n  text-align: left; }\n\n.padding-left-right {\n  padding-left: 15px;\n  padding-right: 15px;\n  margin: 0 auto; }\n\n.padding-top-xs {\n  padding-top: 2px;\n  margin: 0 auto; }\n\n.padding-top-m {\n  padding-top: 10px;\n  margin: 0 auto; }\n\n.padding-botton-m {\n  padding-bottom: 10px;\n  margin: 0 auto; }\n\n.text-size-s {\n  font-size: 0.8em;\n  text-align: center; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.toolbar-div {\n  margin: 16px;\n  width: 92%;\n  height: 60px;\n  background-color: #0377B4; }\n\n.title {\n  padding-top: 16px;\n  text-align: center;\n  color: #ffffff; }\n\n.title_label {\n  margin-top: 1em;\n  padding-left: 1em; }\n\n.item {\n  margin-top: 1em; }\n\n.input_login {\n  width: 20px;\n  margin-left: 3em; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL2JyYXlhbi9hcHBNYXNTcG9ydC9zcmMvYXBwL3BhZ2VzL2xvZ2luL2xvZ2luLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNJLGdCQUFnQjtFQUNoQixVQUFVO0VBQ1YsZ0JBQWdCLEVBQUE7O0FBRXBCO0VBQ0Usa0JBQWtCO0VBQ2xCLG1CQUFtQjtFQUNuQixjQUFjLEVBQUE7O0FBRWhCO0VBQ0UsZ0JBQWdCO0VBQ2hCLGNBQWMsRUFBQTs7QUFFaEI7RUFDRSxpQkFBaUI7RUFDakIsY0FBYyxFQUFBOztBQUVoQjtFQUNFLG9CQUFvQjtFQUNwQixjQUFjLEVBQUE7O0FBRWhCO0VBQ0UsZ0JBQWdCO0VBQ2hCLGtCQUFrQixFQUFBOztBQUVwQjtFQUNFLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFcEI7RUFDRSxZQUFZO0VBQ1osVUFBVTtFQUNWLFlBQVk7RUFDWix5QkFBeUIsRUFBQTs7QUFFM0I7RUFDRSxpQkFBaUI7RUFDakIsa0JBQWtCO0VBQ2xCLGNBQWMsRUFBQTs7QUFFaEI7RUFDRSxlQUFlO0VBQ2YsaUJBQWlCLEVBQUE7O0FBRW5CO0VBQ0UsZUFBYyxFQUFBOztBQUVoQjtFQUNFLFdBQVc7RUFDWCxnQkFBZ0IsRUFBQSIsImZpbGUiOiJzcmMvYXBwL3BhZ2VzL2xvZ2luL2xvZ2luLnBhZ2Uuc2NzcyIsInNvdXJjZXNDb250ZW50IjpbIi5lcnJvci1tZXNzYWdlcyB7XG4gICAgZm9udC1zaXplOiAwLjllbTtcbiAgICBjb2xvcjogcmVkO1xuICAgIHRleHQtYWxpZ246IGxlZnQ7XG4gIH1cbi5wYWRkaW5nLWxlZnQtcmlnaHQge1xuICBwYWRkaW5nLWxlZnQ6IDE1cHg7XG4gIHBhZGRpbmctcmlnaHQ6IDE1cHg7XG4gIG1hcmdpbjogMCBhdXRvO1xufVxuLnBhZGRpbmctdG9wLXhzIHtcbiAgcGFkZGluZy10b3A6IDJweDtcbiAgbWFyZ2luOiAwIGF1dG87XG59XG4ucGFkZGluZy10b3AtbXtcbiAgcGFkZGluZy10b3A6IDEwcHg7XG4gIG1hcmdpbjogMCBhdXRvO1xufVxuLnBhZGRpbmctYm90dG9uLW17XG4gIHBhZGRpbmctYm90dG9tOiAxMHB4O1xuICBtYXJnaW46IDAgYXV0bztcbn1cbi50ZXh0LXNpemUtc3tcbiAgZm9udC1zaXplOiAwLjhlbTtcbiAgdGV4dC1hbGlnbjogY2VudGVyO1xufVxuLmNlbnRlcntcbiAgbWFyZ2luOiAwIGF1dG87XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cbi50b29sYmFyLWRpdiB7XG4gIG1hcmdpbjogMTZweDtcbiAgd2lkdGg6IDkyJTtcbiAgaGVpZ2h0OiA2MHB4O1xuICBiYWNrZ3JvdW5kLWNvbG9yOiAjMDM3N0I0O1xufVxuLnRpdGxlIHtcbiAgcGFkZGluZy10b3A6IDE2cHg7XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbiAgY29sb3I6ICNmZmZmZmY7XG59XG4udGl0bGVfbGFiZWwge1xuICBtYXJnaW4tdG9wOiAxZW07XG4gIHBhZGRpbmctbGVmdDogMWVtO1xufVxuLml0ZW0ge1xuICBtYXJnaW4tdG9wOjFlbTtcbn1cbi5pbnB1dF9sb2dpbiB7XG4gIHdpZHRoOiAyMHB4OyBcbiAgbWFyZ2luLWxlZnQ6IDNlbTtcbn1cbiJdfQ== */"

/***/ }),

/***/ "./src/app/pages/login/login.page.ts":
/*!*******************************************!*\
  !*** ./src/app/pages/login/login.page.ts ***!
  \*******************************************/
/*! exports provided: LoginPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LoginPage", function() { return LoginPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _services_configurations_configurations_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../../services/configurations/configurations.service */ "./src/app/services/configurations/configurations.service.ts");
/* harmony import */ var _services_client_client_service__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ../../services/client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ../../services/loading/loading.service */ "./src/app/services/loading/loading.service.ts");
/* harmony import */ var _ionic_native_device_ngx__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ionic-native/device/ngx */ "./node_modules/@ionic-native/device/ngx/index.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");









var AUTH_TOKEN_PREFIX = 'Basic ';
var LoginPage = /** @class */ (function () {
    function LoginPage(config, client, http, router, loading, device, platform) {
        this.config = config;
        this.client = client;
        this.http = http;
        this.router = router;
        this.loading = loading;
        this.device = device;
        this.platform = platform;
        this.prefix = '507';
    }
    LoginPage.prototype.ngOnInit = function () { };
    LoginPage.prototype.createOrUpdateClient = function () {
        var _this = this;
        var lang = {
            name: 'español',
            id_language: 300,
            short_name: 'es'
        };
        var log = {
            msisdn: this.prefix + this.msisdn,
            password: this.password,
            upstreamChannel: '',
            ua: '',
            platform: '',
            appCodeName: '',
            appName: '',
            language: '',
            cordova: '',
            model: '',
            uuid: '',
            version: '',
            isVirtual: false,
            serial: '',
            onLine: false,
            manufacturer: '',
            id_language: 300
        };
        log.upstreamChannel = this.config.getUpstreamChannel();
        this.loading.presentLoading();
        if (this.config.getPlatform() === 'Web') {
            log.ua = navigator.userAgent;
            log.platform = navigator.platform;
            log.onLine = navigator.onLine;
            log.appCodeName = navigator.appCodeName;
            log.appName = navigator.appName;
            log.language = navigator.language;
        }
        else if (this.config.getPlatform() === 'Android' || this.config.getPlatform() === 'iOS') {
            log.cordova = this.device.cordova;
            log.model = this.device.model;
            log.platform = this.device.platform;
            log.uuid = this.device.uuid;
            log.version = this.device.version;
            log.manufacturer = this.device.manufacturer;
            log.isVirtual = this.device.isVirtual;
            log.serial = this.device.serial;
        }
        //antes id_language era 405 para portugues ahora es 300 para español
        var jData = {
            country: 4,
            language: lang ? lang.id_language : 300,
            device_id: this.config.getDeviceId(),
            upstreamChannel: this.config.getUpstreamChannel(),
            log: log,
            login: this.prefix + this.msisdn,
            password: this.password,
            devices: {},
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
            _this.statusResponse = data['description'];
            _this.responseInfo = data['response'];
            _this.id_client = _this.responseInfo.id_client;
            if (_this.statusResponse === 'OK') {
                localStorage.setItem('id_client', _this.responseInfo.id_client);
                if (localStorage.length > 1) {
                    _this.old_User = localStorage.getItem('session');
                    localStorage.setItem('session', _this.responseInfo.user_id);
                }
                else {
                    localStorage.setItem('session', _this.responseInfo.user_id);
                }
            }
            _this.tokenUser = _this.responseInfo.auth_token;
            _this.upstream();
        }, function (error) {
            _this.loading.presentToast('Teléfono o contraseña incorrecto, intente nuevamente!!!.', 'danger');
            console.log(error);
        });
        this.msisdn = undefined;
        this.password = undefined;
    };
    LoginPage.prototype.upstream = function () {
        var _this = this;
        this.session = localStorage.getItem('session');
        var headers = new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]();
        headers.append("Accept", 'application/json');
        headers.append('Content-Type', 'application/json');
        var httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
                'Content-Type': 'application/json'
            })
        };
        if (this.tokenUser !== undefined) {
            headers.append("Authorization", AUTH_TOKEN_PREFIX + this.tokenUser);
        }
        var config = {
            headers: headers
        };
        var body = {
            event_type: 'LOGIN',
            metadata: {
                appVersion: '0.0.2',
                channel: this.config.getPlatform()
            },
            user_id: this.id_client
        };
        if (this.id_client !== undefined && this.client !== null) {
            var upstream = this.client.upstream(this.id_client);
            this.http.post(upstream, body, config)
                .toPromise()
                .then(function (data) {
                _this.responseUpstream = data['response'];
                if (_this.session !== _this.old_User) {
                    _this.router.navigateByUrl('/tutorial');
                }
                else {
                    _this.router.navigateByUrl('/tabs/predictions');
                }
            }, function (error) {
                _this.loading.presentToast('Teléfono o contraseña incorrecto, intente nuevamente!!!.', 'danger');
                console.log(error);
            });
        }
    };
    LoginPage.prototype.loginSessionInvited = function () {
        var _this = this;
        var lang = {
            name: 'español',
            id_language: 300,
            short_name: 'es'
        };
        var log = {
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
            log.cordova = this.device.cordova;
            log.model = this.device.model;
            log.platform = this.device.platform;
            log.uuid = this.device.uuid;
            log.version = this.device.version;
            log.manufacturer = this.device.manufacturer;
            log.serial = this.device.serial;
        }
        var jData = {
            country: 4,
            language: lang ? lang.id_language : 300,
            device_id: this.config.getDeviceId(),
            upstreamChannel: this.config.getUpstreamChannel(),
            log: log,
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
            localStorage.setItem('id_client', _this.responseInfo.id_client);
            _this.router.navigateByUrl('/configurations');
        }, function (error) {
            console.log(error);
        });
    };
    LoginPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    LoginPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-login',
            template: __webpack_require__(/*! ./login.page.html */ "./src/app/pages/login/login.page.html"),
            styles: [__webpack_require__(/*! ./login.page.scss */ "./src/app/pages/login/login.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_services_configurations_configurations_service__WEBPACK_IMPORTED_MODULE_3__["ConfigurationsService"],
            _services_client_client_service__WEBPACK_IMPORTED_MODULE_4__["ClientService"], _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"],
            _angular_router__WEBPACK_IMPORTED_MODULE_5__["Router"], _services_loading_loading_service__WEBPACK_IMPORTED_MODULE_6__["LoadingService"],
            _ionic_native_device_ngx__WEBPACK_IMPORTED_MODULE_7__["Device"], _ionic_angular__WEBPACK_IMPORTED_MODULE_8__["Platform"]])
    ], LoginPage);
    return LoginPage;
}());



/***/ })

}]);
//# sourceMappingURL=pages-login-login-module.js.map