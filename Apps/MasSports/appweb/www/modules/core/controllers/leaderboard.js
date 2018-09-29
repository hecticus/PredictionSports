'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.LeaderboardCtrl
 * @description LeaderboardCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('LeaderboardCtrl', ['$http','$rootScope','$scope','$state', '$window', '$timeout',
        'Client', 'WebManager', 'Domain', 'FacebookManager', 'iScroll', 'Competitions', 'Notification', 'Moment',
        function($http, $rootScope, $scope, $state, $window, $timeout, Client, WebManager, Domain
            , FacebookManager, iScroll, Competitions, Notification, Moment) {

            var hideLoading = false;
            var setLoading = function() {
              $scope.$emit('load');
              var setInterval = function () {
                if (hideLoading) $scope.$emit('unload');
                else setTimeout(setInterval, 1);
              };

              setInterval(setInterval, 1);
            };

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
            $scope.dhour = '';
            $scope.ddate = '';

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
                setLoading();
                setActive('phase'+ scroll.currentPage.pageX);
                var idCompetitions = $scope.item.competitions[ scroll.currentPage.pageX].id_competitions;
                var phase = $scope.item.competitions[scroll.currentPage.pageX].phase;
                if (!phase) phase = 0;
                getLeaderboardIndex(Domain.leaderboard.phase(idCompetitions, phase));
            };

            $scope.showTournament = function(){
                setLoading();
                setActive('competition' + scroll.currentPage.pageX);
                var idCompetition = $scope.item.competitions[scroll.currentPage.pageX].id_competitions;

                if (idCompetition === 0) {
                   getLeaderboardIndex(Domain.leaderboard.total());
                } else {
                  getLeaderboardIndex(Domain.leaderboard.competition(idCompetition));
                }
                hideLoading = true;
            };

            function getLeaderboardIndex(_url){

                //console.log('entering getLeaderboard');
                $scope.hasLeaderboard = true;
                var _page =  scroll.currentPage.pageX;
                var competition = $scope.item.competitions[_page];
                competition.leaderboard = [];

                $http.get(_url, config)
                    .then(function (data) {
                       if(data.data.error === 0){
                        //console.log('data.data -> ' + JSON.stringify(data.data));
                         $scope.hasLeaderboard = true;
                         data = data.data;
                         competition.leaderboard = data.response.leaderboard;
                         competition.leaderboard.forEach(function(item, index) {
                            item.index = (index + 1);
                         });

                         competition.client = data.response.client;
                         $scope.dhour = data.response.client.dhour;
                         $scope.ddate = data.response.client.ddate;
                         competition.client = data.response.client;
                         if (competition.leaderboard.length === 0) {
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
                          competition.leaderboard = false
                          $scope.hasLeaderboard = false;
                       }


                    }, function (data){
                        competition.leaderboard = false
                        $scope.hasLeaderboard = false;
                        hideLoading = true;
                        //console.log('data.data -> ' + JSON.stringify(data.data));
                        if(data.data.error === 3){
                        } else {
                            Notification.showNetworkErrorAlert();
                        }

                    });

            }

            function getFbFriends(){

              FacebookManager.getFriends(function(friends){
                  $scope.hasFriends = friends; //&& (Client.getFriendsIds().length > 0);
              });

              config.params.friends = Client.getFriendsIds();


            }

            function getCompetitions(){

                Competitions.get().then(function(competitions){

                  //console.log("received competitions " + JSON.stringify(competitions));

                      var arrTotal = {'id_competitions' : 0, 'name': 'General', 'competiton_type': {'name': 'General','competition_logo':'img/shield-2.png'}};
                      competitions.unshift(arrTotal);

                      $scope.item.competitions = competitions;
                      widthTotal = ($window.innerWidth * competitions.length);

                     $scope.item.competitions.forEach(function(competition) {
                          var date = Moment.date().format('YYYYMMDD');
                          Competitions.leaderboard.personal.phase.latest(competition.id_competitions,date)
                          .then(function (phases) {
                              if (phases) {
                                if (phases.last_phase) {
                                  competition.phase = phases.last_phase.id_phases;
                                  if (competition.phase.type != 1) $scope.showTournament();
                                }
                              }
                          });
                      });

                    getLeaderboardIndex(Domain.leaderboard.total());


                }, function(){
                  Notification.showNetworkErrorAlert();
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

                if($state.current.data.contentClass === 'content-friends'){
                    $scope.isContentLeader = false;
                    friendsMode = true;
                    getFbFriends();
                }

                getCompetitions();
                setUpIScroll();
                
                $scope.$on('onRepeatLast', function(scope, element, attrs) {
                    vScrolls[_currentPage] = iScroll.vertical($scope.vWrapper.getName(_currentPage));
                    hideLoading = true;
                });
            } 

            init();


            //setLoading();

        }
    ]);
