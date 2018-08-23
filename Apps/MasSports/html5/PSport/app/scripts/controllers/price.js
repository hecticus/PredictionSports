'use strict';

/**
 * @ngdoc function
 * @name psportApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the psportApp
 */
angular.module('psportApp')
  .controller('PriceCtrl', ["$scope", function ($scope)  {
  	$.backstretch("destroy", false);



    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
}]);
