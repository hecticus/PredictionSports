'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.LeaderboardCtrl
 * @description LeaderboardCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('LeaderboardCtrl', ['$http','$rootScope','$scope','$state', '$window',
        'Client', 'WebManager', 'Domain', 'FacebookManager', 'iScroll', 'Competitions', 'Notification', 'Moment',
        function($http, $rootScope, $scope, $state, $window, Client, WebManager, Domain
            , FacebookManager, iScroll, Competitions, Notification, Moment) {



            var config = WebManager.getFavoritesConfig($rootScope.isFavoritesFilterActive());

            var _currentPage = 0;
            var friendsMode = false;
            var active = 'competition';
            var width = $window.innerWidth;
            var widthTotal = $window.innerWidth;
            var scroll = null;
            var vScrolls = [];
            $scope.item = {};
            $scope.hasFriends = true;
            $scope.hasLeaderboard = true;

            $scope.isContentLeader = true;



            function setActive(type) {
                active = type;
            }

            $scope.isActive = function(type) {
                return type === active;
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

            $scope.nextPage = function(){
                scroll.next();
            };

            $scope.prevPage = function(){
                scroll.prev();
            };

            $scope.showPhase = function(){
                setActive('phase'+ scroll.currentPage.pageX);
                var idCompetitions = $scope.item.competitions[ scroll.currentPage.pageX].id_competitions;
                var phase = $scope.item.competitions[scroll.currentPage.pageX].phase;
                if (!phase) phase = 0;
                 getLeaderboardIndex(Domain.leaderboard.phase(idCompetitions, phase));
            };

            $scope.showTournament = function(){

                setActive('competition' + scroll.currentPage.pageX);
                var idCompetition = $scope.item.competitions[scroll.currentPage.pageX].id_competitions;

                if (idCompetition === 0) {
                   getLeaderboardIndex(Domain.leaderboard.total());
                } else {
                  getLeaderboardIndex(Domain.leaderboard.competition(idCompetition));
                }

            };

            function getLeaderboardIndex(_url){

                $scope.$emit('load');
                $scope.hasLeaderboard = true;
                var _page =  scroll.currentPage.pageX;
                var competition = $scope.item.competitions[_page];
                competition.leaderboard = [];

                $http.get(_url, config)
                    .then(function (data) {
                       if(data.data.error === 0){

                         $scope.hasLeaderboard = true;
                         data = data.data;
                         competition.leaderboard = data.response.leaderboard;
                         competition.leaderboard.forEach(function(item, index) {
                            item.index = (index + 1);
                         });

                         competition.client = data.response.client;
                         if (competition.leaderboard.length === 1) {
                            if (competition.leaderboard[0].id_client !== competition.client.id_client) {
                              competition.client.index = competition.client.index + 1;
                              competition.leaderboard.push(competition.client);
                            }
                         } else if(competition.leaderboard.length <= competition.client.index) {
                            competition.client.index = competition.client.index + 1;
                            competition.leaderboard.unshift({client:'...',score:'...', index: '...'});
                            competition.leaderboard.unshift(competition.client);
                         }


                       } else {
                          $scope.hasLeaderboard = false;
                       }

                        $scope.$emit('unload');
                    }, function (data){
                        $scope.hasLeaderboard = false;
                        $scope.$emit('unload');

                        console.log('data.data -> ' + JSON.stringify(data.data));
                        if(data.data.error === 3){
                        } else {
                            Notification.showNetworkErrorAlert();
                        }
                    }).finally(function() {
                        $scope.$emit('unload');
                    });
            }

            function getFbFriends(){
                //if(!!$window.facebookConnectPlugin){
                    FacebookManager.getFriends(function(friends){
                        $scope.hasFriends = friends && (Client.getFriendsIds().length > 0);
                    });
                    config.params.friends = Client.getFriendsIds();
               /* } else {
                    console.log('facebookconnectPlugin Object not available. Are you directly on a browser?');
                }*/
            }

            function getCompetitions(){

                Competitions.get().then(function(competitions){


                      var arrTotal = {'id_competitions' : 0, 'name': 'General', 'competiton_type': {'name': 'General','competition_logo':'img/shield-2.png'}};
                      competitions.unshift(arrTotal);


                      $scope.item.competitions = competitions;
                      widthTotal = ($window.innerWidth * competitions.length);

                     $scope.item.competitions.forEach(function(competition) {
                          var date = Moment.date().format('YYYYMMDD');
                          Competitions.leaderboard.personal.phase.latest(competition.id_competitions,date)
                          .then(function (phases) {
                              if (phases.last_phase) {
                                competition.phase = phases.last_phase.id_phases;
                                if (competition.phase.type != 1) $scope.showTournament();
                              }
                          });
                      });

                }, function(){
                  Notification.showNetworkErrorAlert();
                }).finally(function(){
                  $scope.$emit('unload');
                });
            }

            function setUpIScroll(){
                scroll = iScroll.horizontal('wrapperH');

                scroll.on('beforeScrollStart', function () {
                    this.refresh();
                });

                scroll.on('scrollStart', function () {
                    _currentPage = this.currentPage.pageX;
                });

                scroll.on('scroll', function () {
                    if (this.currentPage.pageX != _currentPage) {
                      $scope.showTournament(this.currentPage.pageX);
                      _currentPage = this.currentPage.pageX;
                    }
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
                if($state.current.data.contentClass === 'content-friends'){
                    $scope.isContentLeader = false;
                    friendsMode = true;
                    getFbFriends();
                }

                getCompetitions();
                setUpIScroll();

                $scope.$on('onRepeatLast', function(scope, element, attrs) {
                    vScrolls[_currentPage] = iScroll.vertical($scope.vWrapper.getName(_currentPage));
                });

            } init();
        }
    ]);
