'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.ScorersCtrl
 * @description ScorersCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('ScorersCtrl',  ['$rootScope','$scope', '$state', '$window',
        'iScroll', 'Competitions', 'Notification',
        function($rootScope, $scope, $state, $window,
                 iScroll, Competitions, Notification) {

            var scroll = null;
            var vScrolls = [];
            var _currentPage = 0;
            var width = $window.innerWidth;
            var widthTotal = $window.innerWidth;

            $scope.showSource = false;
            $scope.getWidth = function(){
                return { 'width': width + 'px'}
            };

            $scope.getTotalWidth = function(){
                return { 'width': widthTotal + 'px'}
            };

            $rootScope.$storage.scorers = false;

            $scope.vWrapper = {
                name:'wrapperV',
                getName : function(_index) {
                    return this.name + _index;
                }
            };

            function getScorers(){
                var _index = scroll.currentPage.pageX;
                var league = $scope.leagues[_index];
                if (!league.scorers) {
                    $scope.$emit('load');
                    Competitions.getScorers(league.id_competitions)
                        .then(function (scorers) {
                            if(scorers.length > 0){
                                scorers.map(function(scorer){
                                    if(!scorer.team.name){
                                        scorer.team.name = $scope.strings.NOT_AVAILABLE;
                                    }
                                });
                                league.scorers = scorers;
                                league.empty = false;
                            } else {
                                league.empty = true;
                            }
                            $scope.$emit('unload');
                        }, function () {
                            league.empty = true;
                            $scope.$emit('unload');
                            Notification.showNetworkErrorAlert();
                        }
                    );
                }
            }

            function setScroll() {
                scroll = iScroll.horizontal('wrapperH');
                $scope.nextPage = function(){
                    scroll.next();
                };
                $scope.prevPage = function(){
                    scroll.prev();
                };
                scroll.on('beforeScrollStart', function () {
                    this.refresh();
                });
                scroll.on('scrollStart', function () {
                    _currentPage = this.currentPage.pageX;
                });
                scroll.on('scroll', function () {
                    if (this.currentPage.pageX != _currentPage) {
                        getScorers();
                        _currentPage = this.currentPage.pageX;
                    }
                });

                $scope.$on('onRepeatLast', function(scope, element, attrs) {
                    vScrolls[_currentPage] = iScroll.vertical($scope.vWrapper.getName(_currentPage));
                    $scope.showSource = true;
                });

                $scope.$on('$destroy', function() {
                    scroll.destroy();
                    scroll = null;

                    vScrolls.forEach(function(scroll){
                        scroll.destroy();
                        scroll = null;
                    });
                });
            }

            function init(){
                $scope.$emit('load');
                Competitions.get().then(function(data){
                    $scope.leagues  = data;
                    widthTotal = ($window.innerWidth * $scope.leagues.length);
                    setScroll();
                    getScorers();
                }, function(){
                  Notification.showNetworkErrorAlert();
                }).finally(function(){
                  $scope.$emit('unload');
                });
            } init();

        }
    ]);
