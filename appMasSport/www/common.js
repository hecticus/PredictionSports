(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["common"],{

/***/ "./node_modules/@ionic/core/dist/esm/legacy/chunk-4e92c885.js":
/*!********************************************************************!*\
  !*** ./node_modules/@ionic/core/dist/esm/legacy/chunk-4e92c885.js ***!
  \********************************************************************/
/*! exports provided: a, b, c, h */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return hapticSelectionStart; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return hapticSelectionChanged; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return hapticSelectionEnd; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "h", function() { return hapticSelection; });
/**
 * Check to see if the Haptic Plugin is available
 * @return Returns `true` or false if the plugin is available
 */
/**
 * Trigger a selection changed haptic event. Good for one-time events
 * (not for gestures)
 */
var hapticSelection = function () {
    var engine = window.TapticEngine;
    if (engine) {
        engine.selection();
    }
};
/**
 * Tell the haptic engine that a gesture for a selection change is starting.
 */
var hapticSelectionStart = function () {
    var engine = window.TapticEngine;
    if (engine) {
        engine.gestureSelectionStart();
    }
};
/**
 * Tell the haptic engine that a selection changed during a gesture.
 */
var hapticSelectionChanged = function () {
    var engine = window.TapticEngine;
    if (engine) {
        engine.gestureSelectionChanged();
    }
};
/**
 * Tell the haptic engine we are done with a gesture. This needs to be
 * called lest resources are not properly recycled.
 */
var hapticSelectionEnd = function () {
    var engine = window.TapticEngine;
    if (engine) {
        engine.gestureSelectionEnd();
    }
};



/***/ }),

/***/ "./node_modules/@ionic/core/dist/esm/legacy/chunk-ba834eff.js":
/*!********************************************************************!*\
  !*** ./node_modules/@ionic/core/dist/esm/legacy/chunk-ba834eff.js ***!
  \********************************************************************/
/*! exports provided: c, g, h, o */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return createColorClasses; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "g", function() { return getClassMap; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "h", function() { return hostContext; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "o", function() { return openURL; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
var _this = undefined;

var hostContext = function (selector, el) {
    return el.closest(selector) !== null;
};
/**
 * Create the mode and color classes for the component based on the classes passed in
 */
var createColorClasses = function (color) {
    var _a;
    return (typeof color === 'string' && color.length > 0) ? (_a = {
            'ion-color': true
        },
        _a["ion-color-" + color] = true,
        _a) : undefined;
};
var getClassList = function (classes) {
    if (classes !== undefined) {
        var array = Array.isArray(classes) ? classes : classes.split(' ');
        return array
            .filter(function (c) { return c != null; })
            .map(function (c) { return c.trim(); })
            .filter(function (c) { return c !== ''; });
    }
    return [];
};
var getClassMap = function (classes) {
    var map = {};
    getClassList(classes).forEach(function (c) { return map[c] = true; });
    return map;
};
var SCHEME = /^[a-z][a-z0-9+\-.]*:/;
var openURL = function (url, ev, direction) { return tslib__WEBPACK_IMPORTED_MODULE_0__["__awaiter"](_this, void 0, void 0, function () {
    var router;
    return tslib__WEBPACK_IMPORTED_MODULE_0__["__generator"](this, function (_a) {
        switch (_a.label) {
            case 0:
                if (!(url != null && url[0] !== '#' && !SCHEME.test(url))) return [3 /*break*/, 2];
                router = document.querySelector('ion-router');
                if (!router) return [3 /*break*/, 2];
                if (ev != null) {
                    ev.preventDefault();
                }
                return [4 /*yield*/, router.componentOnReady()];
            case 1:
                _a.sent();
                return [2 /*return*/, router.push(url, direction)];
            case 2: return [2 /*return*/, false];
        }
    });
}); };



/***/ }),

/***/ "./node_modules/@ionic/core/dist/esm/legacy/chunk-c90aaa66.js":
/*!********************************************************************!*\
  !*** ./node_modules/@ionic/core/dist/esm/legacy/chunk-c90aaa66.js ***!
  \********************************************************************/
/*! exports provided: a, b, c, d, e, f, h, i, n, p, r */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return rIC; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "b", function() { return assert; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "c", function() { return clamp; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "d", function() { return debounceEvent; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "e", function() { return debounce; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "f", function() { return findItemLabel; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "h", function() { return hasShadowDom; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "i", function() { return isEndSide; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "n", function() { return now; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "p", function() { return pointerCoord; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "r", function() { return renderHiddenInput; });
var rIC = function (callback) {
    if ('requestIdleCallback' in window) {
        window.requestIdleCallback(callback);
    }
    else {
        setTimeout(callback, 32);
    }
};
var hasShadowDom = function (el) {
    return !!el.shadowRoot && !!el.attachShadow;
};
var findItemLabel = function (componentEl) {
    var itemEl = componentEl.closest('ion-item');
    if (itemEl) {
        return itemEl.querySelector('ion-label');
    }
    return null;
};
var renderHiddenInput = function (always, container, name, value, disabled) {
    if (always || hasShadowDom(container)) {
        var input = container.querySelector('input.aux-input');
        if (!input) {
            input = container.ownerDocument.createElement('input');
            input.type = 'hidden';
            input.classList.add('aux-input');
            container.appendChild(input);
        }
        input.disabled = disabled;
        input.name = name;
        input.value = value || '';
    }
};
var clamp = function (min, n, max) {
    return Math.max(min, Math.min(n, max));
};
var assert = function (actual, reason) {
    if (!actual) {
        var message = 'ASSERT: ' + reason;
        console.error(message);
        debugger; // tslint:disable-line
        throw new Error(message);
    }
};
var now = function (ev) {
    return ev.timeStamp || Date.now();
};
var pointerCoord = function (ev) {
    // get X coordinates for either a mouse click
    // or a touch depending on the given event
    if (ev) {
        var changedTouches = ev.changedTouches;
        if (changedTouches && changedTouches.length > 0) {
            var touch = changedTouches[0];
            return { x: touch.clientX, y: touch.clientY };
        }
        if (ev.pageX !== undefined) {
            return { x: ev.pageX, y: ev.pageY };
        }
    }
    return { x: 0, y: 0 };
};
/**
 * @hidden
 * Given a side, return if it should be on the end
 * based on the value of dir
 * @param side the side
 * @param isRTL whether the application dir is rtl
 */
var isEndSide = function (side) {
    var isRTL = document.dir === 'rtl';
    switch (side) {
        case 'start': return isRTL;
        case 'end': return !isRTL;
        default:
            throw new Error("\"" + side + "\" is not a valid value for [side]. Use \"start\" or \"end\" instead.");
    }
};
var debounceEvent = function (event, wait) {
    var original = event._original || event;
    return {
        _original: event,
        emit: debounce(original.emit.bind(original), wait)
    };
};
var debounce = function (func, wait) {
    if (wait === void 0) { wait = 0; }
    var timer;
    return function () {
        var args = [];
        for (var _i = 0; _i < arguments.length; _i++) {
            args[_i] = arguments[_i];
        }
        clearTimeout(timer);
        timer = setTimeout.apply(void 0, [func, wait].concat(args));
    };
};



/***/ }),

/***/ "./node_modules/@ionic/core/dist/esm/legacy/chunk-cae2ca23.js":
/*!********************************************************************!*\
  !*** ./node_modules/@ionic/core/dist/esm/legacy/chunk-cae2ca23.js ***!
  \********************************************************************/
/*! exports provided: s */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "s", function() { return sanitizeDOMString; });
/**
 * Does a simple sanitization of all elements
 * in an untrusted string
 */
var sanitizeDOMString = function (untrustedString) {
    try {
        if (typeof untrustedString !== 'string' || untrustedString === '') {
            return untrustedString;
        }
        /**
         * Create a document fragment
         * separate from the main DOM,
         * create a div to do our work in
         */
        var documentFragment_1 = document.createDocumentFragment();
        var workingDiv = document.createElement('div');
        documentFragment_1.appendChild(workingDiv);
        workingDiv.innerHTML = untrustedString;
        /**
         * Remove any elements
         * that are blocked
         */
        blockedTags.forEach(function (blockedTag) {
            var getElementsToRemove = documentFragment_1.querySelectorAll(blockedTag);
            for (var elementIndex = getElementsToRemove.length - 1; elementIndex >= 0; elementIndex--) {
                var element = getElementsToRemove[elementIndex];
                if (element.parentNode) {
                    element.parentNode.removeChild(element);
                }
                else {
                    documentFragment_1.removeChild(element);
                }
                /**
                 * We still need to sanitize
                 * the children of this element
                 * as they are left behind
                 */
                var childElements = getElementChildren(element);
                /* tslint:disable-next-line */
                for (var childIndex = 0; childIndex < childElements.length; childIndex++) {
                    sanitizeElement(childElements[childIndex]);
                }
            }
        });
        /**
         * Go through remaining elements and remove
         * non-allowed attribs
         */
        // IE does not support .children on document fragments, only .childNodes
        var documentFragmentChildren = getElementChildren(documentFragment_1);
        /* tslint:disable-next-line */
        for (var childIndex = 0; childIndex < documentFragmentChildren.length; childIndex++) {
            sanitizeElement(documentFragmentChildren[childIndex]);
        }
        // Append document fragment to div
        var fragmentDiv = document.createElement('div');
        fragmentDiv.appendChild(documentFragment_1);
        // First child is always the div we did our work in
        var getInnerDiv = fragmentDiv.querySelector('div');
        return (getInnerDiv !== null) ? getInnerDiv.innerHTML : fragmentDiv.innerHTML;
    }
    catch (err) {
        console.error(err);
        return '';
    }
};
/**
 * Clean up current element based on allowed attributes
 * and then recursively dig down into any child elements to
 * clean those up as well
 */
var sanitizeElement = function (element) {
    // IE uses childNodes, so ignore nodes that are not elements
    if (element.nodeType && element.nodeType !== 1) {
        return;
    }
    for (var i = element.attributes.length - 1; i >= 0; i--) {
        var attribute = element.attributes[i];
        var attributeName = attribute.name;
        // remove non-allowed attribs
        if (!allowedAttributes.includes(attributeName.toLowerCase())) {
            element.removeAttribute(attributeName);
            continue;
        }
        // clean up any allowed attribs
        // that attempt to do any JS funny-business
        var attributeValue = attribute.value;
        /* tslint:disable-next-line */
        if (attributeValue != null && attributeValue.toLowerCase().includes('javascript:')) {
            element.removeAttribute(attributeName);
        }
    }
    /**
     * Sanitize any nested children
     */
    var childElements = getElementChildren(element);
    /* tslint:disable-next-line */
    for (var i = 0; i < childElements.length; i++) {
        sanitizeElement(childElements[i]);
    }
};
/**
 * IE doesn't always support .children
 * so we revert to .childNodes instead
 */
var getElementChildren = function (element) {
    return (element.children != null) ? element.children : element.childNodes;
};
var allowedAttributes = ['class', 'id', 'href', 'src', 'name', 'slot'];
var blockedTags = ['script', 'style', 'iframe', 'meta', 'link', 'object', 'embed'];



/***/ }),

/***/ "./src/app/services/client/client.service.ts":
/*!***************************************************!*\
  !*** ./src/app/services/client/client.service.ts ***!
  \***************************************************/
/*! exports provided: ClientService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ClientService", function() { return ClientService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _configurations_configurations_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../configurations/configurations.service */ "./src/app/services/configurations/configurations.service.ts");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! moment */ "./node_modules/moment/moment.js");
/* harmony import */ var moment__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(moment__WEBPACK_IMPORTED_MODULE_3__);




var companyName = '';
var buildVersion = '';
var serverVersion = '';
var bundleVersion = '0.0.2';
var bundleId = '';
var updateInfo = {};
var prefix = '507';
var hasToUpdateRegId = false;
var clientPushAlerts = [];
var clientDataSafe = false;
var currentVersion = 0;
var ClientService = /** @class */ (function () {
    /**
     * Constructor of the Service with Dependency Injection
     * @param http The standard Angular HttpClient to make requests
     */
    function ClientService(app) {
        this.app = app;
        this.apiUrl = 'http://plussports.hecticus.com/';
        this.apiVersion = 'v1';
        this.appId = '1';
        this.event = '0';
        this.provisionalLang = 'es';
        this.client = {
            id_client: '',
            nickname: '',
            session: '',
            auth_token: '',
            password: '',
            msisdn: '',
            regId: '',
            user_id: '',
            login: '',
            language: ''
        };
    }
    ClientService.prototype.getAppender = function (index) {
        switch (index) {
            case '1': return '|';
            case '2': return '@';
            case '3': return '#';
            case '4': return '$';
            case '5': return '%';
            case '6': return '&';
            case '7': return '/';
            case '8': return '(';
            case '9': return ')';
            case '0': return '=';
            default: return '-';
        }
    };
    ClientService.prototype.getHeadersAuth = function () {
        var companyName = this.app.getCompanyName();
        var buildVersion = this.app.getBuildVersion();
        var serverVersion = this.app.getServerVersion();
        var auth = companyName;
        auth += this.getAppender('9');
        auth += buildVersion;
        auth += this.getAppender('1');
        auth += serverVersion;
        return auth;
    };
    ClientService.prototype.getHeadersAuthMatch = function () {
        var auth = this.getAppender('');
        auth += this.getAppender('');
        return auth;
    };
    ClientService.prototype.getSecurityToken = function (prefix) {
        return prefix + 'upstreamChannel=' + this.app.getUpstreamChannel()
            + '&api_password=' + this.getHeadersAuth();
    };
    ClientService.prototype.getSecurityTokenMatch = function (prefix) {
        return prefix + 'upstreamChannel=' + this.app.getUpstreamChannel()
            + '&api_password=' + this.getHeadersAuthMatch();
    };
    ClientService.prototype.getLang = function () {
        //antes estaba 405 (portigues) ahora es 300 (español)
        return 300;
    };
    ClientService.prototype.getGMT = function (prefix) {
        var tz = moment__WEBPACK_IMPORTED_MODULE_3__().format('[GMT]ZZ').replace(/\s/g, '');
        tz = encodeURIComponent(tz);
        //return prefix + 'timezoneName=' + tz;
        return prefix + 'timezoneName=GMT-0500'; //Cable!
    };
    /**********   Methods API for create, update , getClient   **********/
    ClientService.prototype.create = function () {
        return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/create' + this.getSecurityToken('?');
    };
    ClientService.prototype.upstream = function (client_id) {
        return this.apiUrl + 'sportsapi/v2/client/' + client_id + '/upstream' + this.getSecurityToken('?');
    };
    ClientService.prototype.update = function (id_client, remind) {
        //console.log('client->update');}
        if (!remind)
            remind = false;
        return this.apiUrl + 'sportsapi/'
            + this.apiVersion + '/clients/update/' + id_client + this.getSecurityToken('?') + '&remind=' + remind;
    };
    ClientService.prototype.getClientById = function (clientId) {
        return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/get/' + clientId + '/' + this.app.getUpstreamChannel() + this.getSecurityToken('?');
    };
    ClientService.prototype.checkpin = function () {
        return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/checkpin';
    };
    /**********   Service for RAnking and points of client **********/
    ClientService.prototype.getPoints = function (clientId) {
        return this.apiUrl
            + 'sportsapi/'
            + this.apiVersion + '/clients/leaderboard/personal/tournament/'
            + clientId
            + this.getGMT('?')
            + this.getSecurityToken('&');
    };
    ClientService.prototype.downClient = function (phone) {
        return this.apiUrl + 'sportsapi/' + this.apiVersion + '/clients/down/' + phone;
    };
    ClientService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_configurations_configurations_service__WEBPACK_IMPORTED_MODULE_2__["ConfigurationsService"]])
    ], ClientService);
    return ClientService;
}());



/***/ }),

/***/ "./src/app/services/competitions/competitions.service.ts":
/*!***************************************************************!*\
  !*** ./src/app/services/competitions/competitions.service.ts ***!
  \***************************************************************/
/*! exports provided: CompetitionsService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CompetitionsService", function() { return CompetitionsService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _client_client_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../client/client.service */ "./src/app/services/client/client.service.ts");



var CompetitionsService = /** @class */ (function () {
    function CompetitionsService(client) {
        this.client = client;
    }
    CompetitionsService.prototype.getCompetitionsFootball = function () {
        return this.client.apiUrl + 'footballapi/'
            + this.client.apiVersion + '/competitions/list/'
            + this.client.appId + '/'
            + this.client.getLang()
            + this.client.getSecurityToken('?');
    };
    CompetitionsService.prototype.getCompetitionsBaseball = function () {
        return this.client.apiUrl + 'baseballapi/'
            + this.client.apiVersion + '/competitions/list'
            + this.client.getSecurityToken('?');
    };
    CompetitionsService.prototype.totalRanking = function (id_client) {
        return this.client.apiUrl
            + 'sportsapi/v1/clients/leaderboard/total/' + id_client
            + this.client.getSecurityToken('?');
    };
    CompetitionsService.prototype.getLeagues = function (id_client, id_competition) {
        return this.client.apiUrl + 'sportsapi/'
            + this.client.apiVersion + '/clients/leaderboard/global/get/'
            + id_client + '/' + id_competition
            + this.client.getGMT('?')
            + this.client.getSecurityToken('&');
    };
    CompetitionsService.prototype.getPhase = function (id_client, id_competition, id_phase) {
        return this.client.apiUrl + 'sportsapi/'
            + this.client.apiVersion + '/clients/leaderboard/get/'
            + id_client + '/' + id_competition + '/' + id_phase
            + this.client.getGMT('?')
            + this.client.getSecurityToken('&');
    };
    CompetitionsService.prototype.getAllTeamByCompetition = function (id_competition) {
        return this.client.apiUrl + 'footballapi/'
            + this.client.apiVersion + '/team/competition/all/' + id_competition + this.client.getSecurityToken('?');
    };
    CompetitionsService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_client_client_service__WEBPACK_IMPORTED_MODULE_2__["ClientService"]])
    ], CompetitionsService);
    return CompetitionsService;
}());



/***/ }),

/***/ "./src/app/services/live/live.service.ts":
/*!***********************************************!*\
  !*** ./src/app/services/live/live.service.ts ***!
  \***********************************************/
/*! exports provided: LiveService */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "LiveService", function() { return LiveService; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _client_client_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../client/client.service */ "./src/app/services/client/client.service.ts");
/* harmony import */ var _configurations_configurations_service__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ../configurations/configurations.service */ "./src/app/services/configurations/configurations.service.ts");




var LiveService = /** @class */ (function () {
    function LiveService(client, config) {
        this.client = client;
        this.config = config;
    }
    LiveService.prototype.allMatchesLive = function (date) {
        return this.client.apiUrl + 'footballapi/'
            + this.client.apiVersion + '/matches/date/paged/'
            + this.client.appId + '/'
            + this.client.getLang() + '/' + date
            + this.config.getGMT('?')
            + this.client.getSecurityToken('&')
            + '&page=0&pageSize=100';
    };
    LiveService.prototype.matchLive = function (competition, match) {
        return this.client.apiUrl + 'footballapi/'
            + this.client.apiVersion + '/matches/mam/next/'
            + this.client.appId + '/' + competition + '/' + match + '/' +
            +this.client.getLang() + '/' + this.client.event
            + this.config.getGMT('?')
            + this.client.getSecurityToken('&');
    };
    LiveService = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Injectable"])({
            providedIn: 'root'
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_client_client_service__WEBPACK_IMPORTED_MODULE_2__["ClientService"], _configurations_configurations_service__WEBPACK_IMPORTED_MODULE_3__["ConfigurationsService"]])
    ], LiveService);
    return LiveService;
}());



/***/ })

}]);
//# sourceMappingURL=common.js.map