'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.TutorialController
 * @description TutorialController
 * @requires ng.$scope
*/
angular
    .module('core')
    .controller('TutorialController', ['$rootScope',
        '$scope', '$localStorage', '$state', '$window', 'iScroll', 'Client',
        function($rootScope, $scope, $localStorage, $state, $window, iScroll, Client) {


            $scope.language = Client.getLanguage().short_name;
            var width = $window.innerWidth;
            var widthTotal = $window.innerWidth;
            var hScroll = null;

            $scope.getWidth = function(){
                return { 'width': width + 'px'}
            };

            $scope.getTotalWidth = function(){
                return { 'width': widthTotal + 'px'}
            };

            $scope.goToIndex = function(){
                $state.go($rootScope.defaultPage);
            };

            $scope.getToggleOnClass = function(page) {
                if (page === hScroll.currentPage.pageX)
                  return 'mdi-toggle-radio-button-on';
            }

            $scope.getHiddenClass = function(page) {
                if (page === hScroll.currentPage.pageX)
                  return 'hidden';
            }



            function setScroll() {
                hScroll = iScroll.horizontal('wrapperH');

                $scope.nextPage = function(){
                  hScroll.refresh();
                  hScroll.next();
                };
                $scope.prevPage = function(){
                    hScroll.refresh();
                    hScroll.prev();
                };

                $scope.$on('$destroy', function() {
                    hScroll.destroy();
                    hScroll = null;
                });
            }


            function init(){
                widthTotal = ($window.innerWidth * 4);
                setScroll();
                $scope.$emit('unload');
            } init();

        }
]);
