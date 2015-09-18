'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.PointsCtrl
 * @description PointsCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('PointsCtrl', ['$rootScope', '$scope', '$window', '$translate', 'Competitions', 'Notification',
        'Moment', 'iScroll', 'Client', 'Domain',
        function($rootScope, $scope, $window, $translate, Competitions, Notification, Moment, iScroll, Client, Domain) {

            $scope.tournaments = [];
            $scope.hasScore = true;
            var tournamentScroll = null;

            var width = $window.innerWidth;
            var widthTotal = ($window.innerWidth * 2);
            var height = ($window.innerHeight);

            $scope.getNameClient = Client.getNickname();
            $scope.getTotalPointsClient = 0;

            $scope.getHeight = function(){
                return { 'height': (height-140) + 'px'}
            };

            $scope.getWidth = function(){
                return { 'width': width + 'px'}
            };

            $scope.getTotalWidth = function(){
                return { 'width': widthTotal + 'px'}
            };

            function getPoints(){
              $scope.$emit('load');
              Competitions.leaderboard.personal.tournamentAll().then(function(response){
                  response.forEach(function(competition){ $scope.getTotalPointsClient += competition.score; });
                  $scope.tournaments = response;
                  $scope.hasScore = $scope.tournaments.length > 0;
              }, function(){
                  Notification.showNetworkErrorAlert();
              }).finally(function(){
                $scope.$emit('unload');
              });
            }

            function setUpIScroll(){
                tournamentScroll = iScroll.vertical('wrapper');
                $scope.$on('$destroy', function() {
                    tournamentScroll.destroy();
                    tournamentScroll = null;
                });
            }

            function init(){
                $scope.$emit('load');
                setUpIScroll();
                getPoints();
            } init();
        }
    ]);
