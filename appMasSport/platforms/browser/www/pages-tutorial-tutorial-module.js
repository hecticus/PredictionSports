(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["pages-tutorial-tutorial-module"],{

/***/ "./src/app/pages/tutorial/tutorial.module.ts":
/*!***************************************************!*\
  !*** ./src/app/pages/tutorial/tutorial.module.ts ***!
  \***************************************************/
/*! exports provided: TutorialPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TutorialPageModule", function() { return TutorialPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/dist/fesm5.js");
/* harmony import */ var _tutorial_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./tutorial.page */ "./src/app/pages/tutorial/tutorial.page.ts");







var routes = [
    {
        path: '',
        component: _tutorial_page__WEBPACK_IMPORTED_MODULE_6__["TutorialPage"]
    }
];
var TutorialPageModule = /** @class */ (function () {
    function TutorialPageModule() {
    }
    TutorialPageModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
                _ionic_angular__WEBPACK_IMPORTED_MODULE_5__["IonicModule"],
                _angular_router__WEBPACK_IMPORTED_MODULE_4__["RouterModule"].forChild(routes)
            ],
            declarations: [_tutorial_page__WEBPACK_IMPORTED_MODULE_6__["TutorialPage"]]
        })
    ], TutorialPageModule);
    return TutorialPageModule;
}());



/***/ }),

/***/ "./src/app/pages/tutorial/tutorial.page.html":
/*!***************************************************!*\
  !*** ./src/app/pages/tutorial/tutorial.page.html ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<ion-header no-border>\n  <ion-toolbar>\n    <div class=\"center\">\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\n    </div>\n  </ion-toolbar>\n</ion-header>\n<ion-content color=\"medium\">\n    <ion-slides pager=\"true\">\n      <ion-slide *ngFor=\"let slide of slides\">\n        <div class=\"slide-padding\">\n          <img [src]=\"slide.img\">\n        </div> \n      </ion-slide>\n    </ion-slides>\n    <img class=\"tutorial \"src=\"assets/img/tutorial/es/skip.png\"  (click)=\"tabPredictions()\" >\n</ion-content>\n"

/***/ }),

/***/ "./src/app/pages/tutorial/tutorial.page.scss":
/*!***************************************************!*\
  !*** ./src/app/pages/tutorial/tutorial.page.scss ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".slide-padding {\n  padding-top: 20px;\n  padding-bottom: 30px;\n  padding-left: 48px;\n  padding-right: 48px; }\n\n.slide-full {\n  height: 100%; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.padding-left-right {\n  padding-left: 25px;\n  padding-right: 25px;\n  margin: 0 auto; }\n\n.tutorial {\n  margin-top: -25px;\n  float: right;\n  width: 60px;\n  height: 60px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIi9ob21lL3lzaWRyby9wb3J0YWZvbGlvIGRlIHByb2dyYW1hY2lvbi9hcHBNYXNTcG9ydC9zcmMvYXBwL3BhZ2VzL3R1dG9yaWFsL3R1dG9yaWFsLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLGlCQUFpQjtFQUNqQixvQkFBb0I7RUFDcEIsa0JBQWtCO0VBQ2xCLG1CQUFtQixFQUFBOztBQUVyQjtFQUNFLFlBQ0YsRUFBQTs7QUFDQTtFQUNFLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFcEI7RUFDRSxrQkFBa0I7RUFDbEIsbUJBQW1CO0VBQ25CLGNBQWMsRUFBQTs7QUFFaEI7RUFDRSxpQkFBaUI7RUFDakIsWUFBWTtFQUNaLFdBQVc7RUFDWCxZQUFZLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9wYWdlcy90dXRvcmlhbC90dXRvcmlhbC5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuc2xpZGUtcGFkZGluZyB7XG4gIHBhZGRpbmctdG9wOiAyMHB4O1xuICBwYWRkaW5nLWJvdHRvbTogMzBweDtcbiAgcGFkZGluZy1sZWZ0OiA0OHB4O1xuICBwYWRkaW5nLXJpZ2h0OiA0OHB4O1xufVxuLnNsaWRlLWZ1bGx7XG4gIGhlaWdodDogMTAwJVxufVxuLmNlbnRlcntcbiAgbWFyZ2luOiAwIGF1dG87XG4gIHRleHQtYWxpZ246IGNlbnRlcjtcbn1cbi5wYWRkaW5nLWxlZnQtcmlnaHQge1xuICBwYWRkaW5nLWxlZnQ6IDI1cHg7XG4gIHBhZGRpbmctcmlnaHQ6IDI1cHg7XG4gIG1hcmdpbjogMCBhdXRvO1xuICB9XG4udHV0b3JpYWwge1xuICBtYXJnaW4tdG9wOiAtMjVweDtcbiAgZmxvYXQ6IHJpZ2h0O1xuICB3aWR0aDogNjBweDtcbiAgaGVpZ2h0OiA2MHB4O1xufVxuIl19 */"

/***/ }),

/***/ "./src/app/pages/tutorial/tutorial.page.ts":
/*!*************************************************!*\
  !*** ./src/app/pages/tutorial/tutorial.page.ts ***!
  \*************************************************/
/*! exports provided: TutorialPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "TutorialPage", function() { return TutorialPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");



var TutorialPage = /** @class */ (function () {
    function TutorialPage(router) {
        this.router = router;
        this.slides = [
            {
                img: 'assets/img/tutorial/es/0.png'
            },
            {
                img: 'assets/img/tutorial/es/1.png'
            },
            {
                img: 'assets/img/tutorial/es/2.png'
            },
            {
                img: 'assets/img/tutorial/es/3.png'
            }
        ];
    }
    TutorialPage.prototype.ngOnInit = function () {
    };
    TutorialPage.prototype.tabPredictions = function () {
        this.router.navigateByUrl('/tabs/predictions');
    };
    TutorialPage.prototype.ngOnDestroy = function () {
        console.log("Exit");
    };
    TutorialPage = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-tutorial',
            template: __webpack_require__(/*! ./tutorial.page.html */ "./src/app/pages/tutorial/tutorial.page.html"),
            styles: [__webpack_require__(/*! ./tutorial.page.scss */ "./src/app/pages/tutorial/tutorial.page.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_router__WEBPACK_IMPORTED_MODULE_2__["Router"]])
    ], TutorialPage);
    return TutorialPage;
}());



/***/ })

}]);
//# sourceMappingURL=pages-tutorial-tutorial-module.js.map