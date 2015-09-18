'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.DashboardController
 * @description DashboardController
 * @requires ng.$scope
*/
angular
    .module('core')
    .controller('DashboardController', ['$http', '$rootScope', '$scope', '$state', '$localStorage', '$translate',
        'Client', 'WebManager', '$window','Bets', 'Moment', 'iScroll', 'Competitions', 'Notification', 'Domain',
        function($http, $rootScope, $scope, $state, $localStorage, $translate, Client, WebManager, $window,
                 Bets, Moment, iScroll, Competitions, Notification, Domain) {

            $rootScope.$storage.settings = true;
            var listScroll = null;
            $scope.leagues = {};
            var _Match = -1;
            var _mBet = -1;
            var strings = {};

            var width = $window.innerWidth;
            var widthTotal = $window.innerWidth;

            $scope.getNameClient = Client.getNickname();

            function getTranslations(){

              $translate(['ALERT.SET_BET.TITLE',
                          'ALERT.SET_BET.SUBTITLE',
                          'ALERT.SET_BET.MSG'])
              .then(function(translation){
                  strings['SET_BET_TITLE'] = translation['ALERT.SET_BET.TITLE'];
                  strings['SET_BET_SUBTITLE'] = translation['ALERT.SET_BET.SUBTITLE'];
                  strings['SET_BET_MSG'] = translation['ALERT.SET_BET.MSG'];
              });

            };

            $scope.vWrapper = {
                name:'wrapperV',
                getName : function(_index) {
                    return this.name + _index;
                }
            };



            $scope.getWidth = function(){
                return { 'width': width + 'px'}
            };

            $scope.getTotalWidth = function(){
                return { 'width': widthTotal + 'px'}
            };

            $scope.getDate = function (_date) {
                return Moment.dateNoUTC(_date).format('ll');
            };

            $scope.getTime = function (_date) {
                return Moment.date(_date).format('HH:mm');
            };

            $scope.setBet = function (_status, _bet, _iMatch) {

              if (_status == 3) {

                var _jMatch = $scope.leagues.fixtures[_iMatch];
                var diffHours = Moment.date(_jMatch.date,'YYYYMMDDHHmmss').diff(Moment.date(), 'hours');

                if (diffHours > 1) {
                  if (( _jMatch.id_game_matches != _Match) || (_bet != _mBet)) {

                    $scope.$emit('load');

                    if (_jMatch.bet) {
                        _jMatch.bet.client_bet = _bet;
                    } else {
                        _jMatch.bet = {client_bet: _bet};
                    }


                    $scope.leagues.fixtures[_iMatch] = _jMatch;

                    var _jBet = {
                        'bet': {
                            'id_tournament': _jMatch.competition.id_competitions,
                            'id_game_match': _jMatch.id_game_matches,
                            'client_bet': _jMatch.bet.client_bet
                        }
                    };


                    Bets.create(_jBet, function() {
                        $scope.$emit('unload');
                      },
                      function () {
                        $scope.$emit('unload');
                    });

                    _Match = _jMatch.id_game_matches;
                    _mBet = _bet;

                  }

                }

              }

            };

            function mapEmptyTeamNames(fixtures){
                fixtures.forEach(function(fixture){
                    fixture.matches.map(function(match){
                        if(!match.home_team.name)
                            match.home_team.name = $scope.strings.NOT_AVAILABLE;
                        if(!match.away_team.name)
                            match.away_team.name = $scope.strings.NOT_AVAILABLE;
                    });
                });
            }

            function setEmptyLeagueFlag(league){
                if(league.fixtures.length > 0){
                    var leagueReduce = league.fixtures.reduce(function(previousValue, currentValue, index) {
                        if(index > 1){
                            return previousValue + currentValue.matches.length;
                        }else{
                            return previousValue.matches.length + currentValue.matches.length;
                        }
                    });
                    league.empty = leagueReduce <= 0;
                } else {
                    league.empty = true;
                }
            }

            function getBets() {
              $http.get(Domain.bets.getToday(Moment.date().format('YYYYMMDD')))
                .then(function (data) {
                    data = data.data.response;
                    $scope.leagues = data;
                }).finally(function(){
                  $scope.$emit('unload');
                });
            }


            function setGameStatus(data){
              data.fixtures.forEach(function(league){
                league.matches.forEach(function(match){
                    match.status.name = 'MATCH.STATUS.' + match.status.id_status;
                });
              });
            }

            function init(){
                $scope.$emit('load');
                getTranslations();
                listScroll = iScroll.vertical('wrapper');
                getBets();
            }

            init();

            $scope.$on('$destroy', function() {
              listScroll.destroy();
            });

        }
    ]);

