'use strict';

/**
 * @ngdoc function
 * @name psportApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the psportApp
 */
angular.module('psportApp')
  .controller('QaCtrl', ["$scope", function ($scope)  {
  	$.backstretch("destroy", false);
	$scope.shortcode = "9090" ;
	$scope.msg = "INFO MAS" ;


    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
}]);
