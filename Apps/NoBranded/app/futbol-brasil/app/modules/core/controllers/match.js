'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.MatchCtrl
 * @description MatchCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('MatchCtrl', ['$http','$rootScope','$scope', '$window'
        ,'WebManager', 'Domain', 'Moment', 'iScroll', 'Notification',
        function($http, $rootScope, $scope, $window, WebManager,
                 Domain, Moment, iScroll, Notification) {

            var _limit = 100;
            var _currentPage = 0;
            var _start = true;
            var _index = 0;
            var _formatDate = 'MMM Do YY';
            var hScroll = null;
            var vScrolls = [];
            var width = $window.innerWidth;
            var widthTotal = $window.innerWidth;

            $scope.showSource = false;
            $scope.wrapper = {
                name:'wrapperV',
                getName : function(_index) {
                    return this.name + _index;
                }
            };

            $scope.getTime = function(_date) {
                return Moment.date(_date).format('HH:mm');
            };

            $scope.pagesBefore = [];
            $scope.pagesAfter = [];




            $scope.getWidth = function(){
                return { 'width': width + 'px'}
            };

            $scope.getTotalWidth = function(){
                return { 'width': widthTotal + 'px'}
            };

            function setEmptyDayFlag(day){
                if(day.leagues.length > 0){
                    var leagueReduce = day.leagues.reduce(
                        function(previousValue, currentValue, index) {
                            if(index > 1){
                                return previousValue + currentValue.fixtures.length;
                            }else{
                                return previousValue.fixtures.length + currentValue.fixtures.length;
                            }
                        }
                    );
                    day.empty = leagueReduce <= 0;
                } else {
                    day.empty = true;
                }
            }

            function setGameStatus(day){
                day.leagues.forEach(function(league){
                    league.fixtures.forEach(function(match){
                        match.status = 'MATCH.STATUS.' + match.id_status;
                    });
                });
            }

            function getDayMatches(_item, _index){
                $scope.$emit('load');
                var config = WebManager.getFavoritesConfig($rootScope.isFavoritesFilterActive());
                config.params.pageSize = _limit;
                config.params.page = 0;

                $http.get(Domain.match(_item.date), config)
                    .then(function (data) {
                        data = data.data;
                        var day = data.response;
                        setEmptyDayFlag(day);
                        setGameStatus(day);
                        $scope.pages[_index].matches = day;
                        $scope.$emit('unload');
                    }, function () {
                        Notification.showNetworkErrorAlert();
                        $scope.$emit('unload');
                    }
                );
            }

            function addNewPage(){
                _index = $scope.pagesAfter.length + 3;
                var newPage = {
                    id: ($scope.pages.length + 1),
                    name: Moment.date().add(_index, 'days').format(_formatDate),
                    date: Moment.date().add(_index, 'days').format('YYYYMMDD')
                };

                $scope.pagesAfter.push(newPage);
                $scope.pages.push(newPage);

                widthTotal = ($window.innerWidth * $scope.pages.length);

                _index = $scope.pages.length - 1;

                $scope.$emit('load');
                getDayMatches($scope.pages[_index], _index);
            }

            function setUpIScroll(){
                hScroll = iScroll.horizontal('wrapperH');

                $scope.nextPage = function(){
                    hScroll.next();
                };

                $scope.prevPage = function(){
                    hScroll.prev();
                };

                $scope.$on('onRepeatLast', function(scope, element, attrs) {
                    $scope.showSource = true;
                    if (_start) {
                        hScroll.refresh();
                        hScroll.goToPage(2,0);
                        _start = false;
                        $scope.pages.forEach(function(_item, _index) {
                            vScrolls[_index] = iScroll.vertical($scope.wrapper.getName(_index));
                        });
                    } else {
                      var _index = $scope.pages.length-1;
                      vScrolls[_index] = iScroll.vertical($scope.wrapper.getName(_index));
                    }
                });

                hScroll.on('beforeScrollStart', function () {
                    this.refresh();
                });

                hScroll.on('scrollStart', function () {
                    _currentPage = this.currentPage.pageX;
                });

                hScroll.on('scroll', function () {
                    if (this.currentPage.pageX != _currentPage) {
                        _currentPage = this.currentPage.pageX;
                    }

                    if (this.currentPage.pageX  == ($scope.pages.length - 1)) {
                        addNewPage();
                        $scope.showSource = true;
                    }
                });

                /*$scope.$on('$destroy', function() {
                    hScroll.destroy();
                    hScroll = null;

                    vScrolls.forEach(function(scroll){
                        scroll.destroy();
                        scroll = null;
                    });
                });*/
            }

            function init(){

              $scope.$emit('unload');
              $scope.pages = [
                {id: 1, name: Moment.date().subtract(2, 'days').format(_formatDate), date:Moment.date().subtract(2, 'days').format('YYYYMMDD')},
                {id: 2, name: Moment.date().subtract(1, 'days').calendar(), date:Moment.date().subtract(1, 'days').format('YYYYMMDD')},
                {id: 3, name: Moment.date().calendar(), date:Moment.date().format('YYYYMMDD')},
                {id: 4, name: Moment.date().add(1, 'days').calendar(), date:Moment.date().add(1, 'days').format('YYYYMMDD')},
                {id: 5, name: Moment.date().add(2, 'days').format(_formatDate), date:Moment.date().add(2, 'days').format('YYYYMMDD')}
              ];


              $scope.pages.forEach(function(_item, _index) {
                getDayMatches(_item, _index);
              });

              width = $window.innerWidth;
              widthTotal = ($window.innerWidth * $scope.pages.length);
              setUpIScroll();

            } init();

        }
    ]);
