'use strict';

/**
 * @ngdoc overview
 * @name psportApp
 * @description
 * # psportApp
 *
 * Main module of the application.
 */
angular
  .module('psportApp', [
    'ngAnimate',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/how', {
        templateUrl: 'views/how.html',
        controller: 'HowCtrl',
        controllerAs: 'how'
      })
      .when('/price', {
        templateUrl: 'views/price.html',
        controller: 'PriceCtrl',
        controllerAs: 'price'
      })
      .when('/win', {
        templateUrl: 'views/win.html',
        controller: 'WinCtrl',
        controllerAs: 'win'
      })
      .when('/promo', {
        templateUrl: 'views/promo.html',
        controller: 'PromoCtrl',
        controllerAs: 'promo'
      })
      .when('/qa', {
        templateUrl: 'views/qa.html',
        controller: 'QaCtrl',
        controllerAs: 'qa'
      })
      .when('/old', {
        templateUrl: 'views/old.html',
        controller: 'OldCtrl',
        controllerAs: 'old'
      })
      .otherwise({
        redirectTo: '/views/promo.html'
      });
  });
