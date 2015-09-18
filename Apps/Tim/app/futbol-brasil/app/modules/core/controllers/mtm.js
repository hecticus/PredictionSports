'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.MtmCtrl
 * @description MtmCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('MtmCtrl', ['$http','$rootScope','$scope','$state','$localStorage', '$interval',  '$stateParams', 'WebManager',
        'Domain', 'Moment', 'iScroll', 'Notification',
        function($http, $rootScope, $scope, $state, $localStorage, $interval, $stateParams, WebManager,
                 Domain, Moment, iScroll, Notification) {

            $rootScope.refreshInterval = null;
            var listScroll = null;
            var matchScroll = null;
            var _event = {
                first: 0,
                last: 0,
                reset: function() {
                    _event.first = 0;
                    _event.last = 0;
                }
            };

            $scope.showSource = false;
            $scope.item = {};
            $scope.item.mtm = [];
            $scope.item.mtm.actions = [];

            $scope.hasGamesForToday = true;

            $scope.refreshIconClass = '';

            $scope.interval = false;
            $scope.competitionId = 0;
            $scope.matchId = 0;

            $scope.date = Moment.date().format('dddd Do YYYY');

            $scope.getTime = function (_date) {
                return Moment.date(_date).format('HH:mm');
            };

            function refreshSuccess(data){

                data = data.data;
                var response = data.response;
                if (data.error === 0) {

                    if (!!$scope.item.mtm && $scope.item.mtm.length == 0) {
                        $scope.item.mtm = response;
                    } else {


                        if (response.actions.length >= 1) {
                          response.actions[0].events.forEach(function(_event) {
                              $scope.item.mtm.actions[0].events.unshift(_event);
                          });
                        }

                    }

                    if (response.actions.length >= 1) {
                      _event.first = response.actions[0].events[0].id_game_match_events;
                    }

                    $scope.item.match.home.goals = response.home_team_goals;
                    $scope.item.match.away.goals = response.away_team_goals;
                    $scope.item.match.status = {id_status:response.status.id_status,name: 'MATCH.STATUS.' + response.status.id_status};
                }

                $scope.$emit('unload');
                $scope.refreshIconClass = '';

            }

            function refreshError(){
                $scope.refreshIconClass = '';
                $scope.$emit('unload');
                Notification.showNetworkErrorAlert();
            }

            $scope.refreshEvents = function (competitionId, matchId) {

                $interval.cancel($rootScope.refreshInterval);
                $rootScope.refreshInterval = null;



                  $scope.refreshIconClass = ' icon-refresh-animate';
                  $scope.$emit('load');

                  var config = WebManager.getFavoritesConfig($rootScope.isFavoritesFilterActive());

                  $http.get(Domain.mtm(competitionId, matchId, _event.first), config)
                      .then(refreshSuccess, refreshError);

                  if ($scope.item.match.status.id_status === 2) {
                     $rootScope.refreshInterval = $interval(function () {
                         console.warn('$interval refreshEvents triggered.');
                         $scope.refreshEvents(competitionId, matchId);
                     },20000);
                  }

            };

            $scope.showContentEvents = function (_league, _match) {

                if ((_match.id_status === 1) ||  (_match.id_status === 2)) {

                  $scope.item.mtm = [];
                  $scope.item.league = _league;

                  $scope.item.match = {
                      home: {name:_match.homeTeam.name, goals:_match.home_team_goals, logo:_match.homeTeam.team_logo},
                      away: {name:_match.awayTeam.name, goals:_match.away_team_goals, logo:_match.awayTeam.team_logo},
                      status: {id_status:_match.id_status,name:_match.status}
                  };

                  $rootScope.transitionPageBack('#wrapper2','left');
                  matchScroll.scrollTo(0,0,0);

                  //TODO check request cableado
                  var competitionId = _league.id_competitions;
                  var matchId = _match.id_game_matches;
                  $scope.competitionId = competitionId;
                  $scope.matchId = matchId;
                  _event.reset();
                  $scope.refreshEvents(competitionId, matchId);
                }
            };

            function mapLeagues(leagues){
                leagues.forEach(function(league){
                    league.fixtures.map(function(match){
                        if(!match.awayTeam.name){
                            match.awayTeam.name = $scope.strings.NOT_AVAILABLE;
                        }
                        if(!match.homeTeam.name){
                            match.homeTeam.name = $scope.strings.NOT_AVAILABLE;
                        }
                        match.status = 'MATCH.STATUS.' + match.id_status;
                    });
                });
            }

            function getMatchesForToday(){
                var date = Moment.date().format('YYYYMMDD');

                var config = WebManager.getFavoritesConfig($rootScope.isFavoritesFilterActive());
                config.params.pageSize = 100;
                config.params.page = 0;


                //date = '20150520';

                $http.get(Domain.match(date), config).then(
                    function (data) {

                        var response = data.data.response;
                        if(response && response.leagues.length > 0){
                            $scope.hasGamesForToday = true;
                            mapLeagues(response.leagues);
                            $scope.item = response;

                            if($stateParams.matchId){
                              $scope.item.leagues.forEach(function(league){
                                league.fixtures.forEach(function(match){
                                      if ($stateParams.matchId ==match.id_game_matches) {
                                        $scope.showContentEvents(league, match);
                                      }
                                });
                              });
                            }


                        } else {
                            $scope.hasGamesForToday = false;
                            console.log('No info on response');
                        }

                        $scope.$emit('unload');

                    }, function () {
                        $scope.hasGamesForToday = false;
                        Notification.showNetworkErrorAlert();
                        $scope.$emit('unload');
                    }
                );
            }

            function setUpIScroll(){
                listScroll = iScroll.vertical('wrapper');
                matchScroll = iScroll.vertical('wrapper2');

                $scope.$on('$destroy', function() {
                    listScroll.destroy();
                    listScroll = null;

                    matchScroll.destroy();
                    matchScroll = null;
                });
            }

            $scope.$on('onRepeatLast', function(scope, element, attrs) {
              $scope.showSource = true;
            });

            function init(){

                $scope.$emit('load');
                setUpIScroll();
                getMatchesForToday();

                $scope.$on('$destroy', function() {
                    $interval.cancel($rootScope.refreshInterval);
                    $rootScope.refreshInterval = null;
                });

            } init();
        }
    ]);
