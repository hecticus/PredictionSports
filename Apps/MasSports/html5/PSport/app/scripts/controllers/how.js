'use strict';

/**
 * @ngdoc function
 * @name psportApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the psportApp
 */
angular.module('psportApp')
  .controller('HowCtrl', ["$scope", function ($scope)  {
  	$.backstretch("destroy", false);
	$scope.shortcode = "9090" ;
	$scope.msg = "INFO MAS" ;

	$("#play").on("click", function() {
	    window.location= "https://play.google.com/store/apps/details?id=com.hecticus.massports";
	});
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
}]);
