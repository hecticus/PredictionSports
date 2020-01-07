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

module.exports = "<ion-header no-border>\r\n  <ion-toolbar>\r\n    <div class=\"center\">\r\n      <img src=\"assets/img/logo.png\" height=\"30\"/>\r\n    </div>\r\n  </ion-toolbar>\r\n</ion-header>\r\n<ion-content color=\"medium\">\r\n    <ion-slides pager=\"true\">\r\n      <ion-slide *ngFor=\"let slide of slides\">\r\n        <div class=\"slide-padding\">\r\n          <img [src]=\"slide.img\">\r\n        </div> \r\n      </ion-slide>\r\n    </ion-slides>\r\n    <img class=\"tutorial \"src=\"assets/img/tutorial/es/skip.png\"  (click)=\"tabPredictions()\" >\r\n</ion-content>\r\n"

/***/ }),

/***/ "./src/app/pages/tutorial/tutorial.page.scss":
/*!***************************************************!*\
  !*** ./src/app/pages/tutorial/tutorial.page.scss ***!
  \***************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = ".slide-padding {\n  padding-top: 20px;\n  padding-bottom: 30px;\n  padding-left: 48px;\n  padding-right: 48px; }\n\n.slide-full {\n  height: 100%; }\n\n.center {\n  margin: 0 auto;\n  text-align: center; }\n\n.padding-left-right {\n  padding-left: 25px;\n  padding-right: 25px;\n  margin: 0 auto; }\n\n.tutorial {\n  margin-top: -25px;\n  float: right;\n  width: 60px;\n  height: 60px; }\n\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInNyYy9hcHAvcGFnZXMvdHV0b3JpYWwvRDpcXERvY3VtZW50c1xccHJvZ3JhbW1pbmcgcHJvamVjdHNcXGFwcE1hc1Nwb3J0L3NyY1xcYXBwXFxwYWdlc1xcdHV0b3JpYWxcXHR1dG9yaWFsLnBhZ2Uuc2NzcyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTtFQUNFLGlCQUFpQjtFQUNqQixvQkFBb0I7RUFDcEIsa0JBQWtCO0VBQ2xCLG1CQUFtQixFQUFBOztBQUVyQjtFQUNFLFlBQ0YsRUFBQTs7QUFDQTtFQUNFLGNBQWM7RUFDZCxrQkFBa0IsRUFBQTs7QUFFcEI7RUFDRSxrQkFBa0I7RUFDbEIsbUJBQW1CO0VBQ25CLGNBQWMsRUFBQTs7QUFFaEI7RUFDRSxpQkFBaUI7RUFDakIsWUFBWTtFQUNaLFdBQVc7RUFDWCxZQUFZLEVBQUEiLCJmaWxlIjoic3JjL2FwcC9wYWdlcy90dXRvcmlhbC90dXRvcmlhbC5wYWdlLnNjc3MiLCJzb3VyY2VzQ29udGVudCI6WyIuc2xpZGUtcGFkZGluZyB7XHJcbiAgcGFkZGluZy10b3A6IDIwcHg7XHJcbiAgcGFkZGluZy1ib3R0b206IDMwcHg7XHJcbiAgcGFkZGluZy1sZWZ0OiA0OHB4O1xyXG4gIHBhZGRpbmctcmlnaHQ6IDQ4cHg7XHJcbn1cclxuLnNsaWRlLWZ1bGx7XHJcbiAgaGVpZ2h0OiAxMDAlXHJcbn1cclxuLmNlbnRlcntcclxuICBtYXJnaW46IDAgYXV0bztcclxuICB0ZXh0LWFsaWduOiBjZW50ZXI7XHJcbn1cclxuLnBhZGRpbmctbGVmdC1yaWdodCB7XHJcbiAgcGFkZGluZy1sZWZ0OiAyNXB4O1xyXG4gIHBhZGRpbmctcmlnaHQ6IDI1cHg7XHJcbiAgbWFyZ2luOiAwIGF1dG87XHJcbiAgfVxyXG4udHV0b3JpYWwge1xyXG4gIG1hcmdpbi10b3A6IC0yNXB4O1xyXG4gIGZsb2F0OiByaWdodDtcclxuICB3aWR0aDogNjBweDtcclxuICBoZWlnaHQ6IDYwcHg7XHJcbn1cclxuIl19 */"

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