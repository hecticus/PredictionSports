'use strict';

/**
 * @ngdoc directive
 * @name core.Directives.menuItem
 * @description menuItem directive
 */
angular
    .module('core')
    .directive('menuItem', ['$rootScope',
        function($rootScope) {
            return {
                restrict: "E",
                templateUrl: 'modules/core/views/templates/menu-item.html',
                transclude: true,
                scope: {
                    section: "@",
                    icon: "@",
                    action: "@"
                },
                link: function($scope) {

                    $scope.navigate = function(){
                        if(!!$scope.action){
                            $rootScope.executeAction($scope.action);
                        } else {
                            $rootScope.showSection($scope.section);
                        }
                    }
                }
            };
        }
    ]);



