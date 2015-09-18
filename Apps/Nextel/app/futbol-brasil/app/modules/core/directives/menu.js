'use strict';

/**
 * @ngdoc directive
 * @name core.Directives.menu
 * @description menu directive
 */
angular
    .module('core')
    .directive('hMenu', ['$rootScope','iScroll',
        function($rootScope, iScroll) {
            return {
                restrict: "E",
                templateUrl: 'modules/core/views/templates/menu.html',
                transclude: true,
                link: function($scope) {
                    var menuScroll = null;
                    function setUpIScroll(){
                        $rootScope.menuScroll = iScroll.vertical('wrapperM');

                        $scope.$on('$destroy', function() {
                            $rootScope.menuScroll.destroy();
                            $rootScope.menuScroll = null;
                        });

                    } setUpIScroll();
                }
            };
        }
    ]);
