'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.TeamSelectionController
 * @description TeamSelectionController
 * @requires ng.$scope
*/
angular
    .module('core')
    .filter('startFrom', function () {
    	return function (input, start) {
    		if (input) {
    			start = +start;
    			return input.slice(start);
    		}
    		return [];
    	};
    })
    .controller('TeamSelectionController', ['$rootScope', '$scope', '$state', '$stateParams', 'TeamsManager', 'iScroll', 'Competitions', 'filterFilter',
        function($rootScope, $scope, $state, $stateParams, TeamsManager, iScroll, Competitions, filterFilter) {

            $scope.scroll = null;
            $scope.scrollL = null;
            var teams = [];
            var maxItems = 10;
            var page = {};
            var isDirtySearchQuery = false;

            $scope.teams = [];
            $scope.searchQuery = '';
            $scope.hasTeams = true;
            $scope.teamSelected = teamSelected;
            $scope.refreshScroll = refreshScroll;

            //////////////////////////////////////////////////////////////

            function teamSelected(team){
              TeamsManager.addFavoriteTeam(team, function(){
                $state.go('settings',{newClient:$stateParams.newClient});
              });
            }

            function refreshScroll(){
            $scope.scrollL.scrollTo(0,0,0);
            }

            function getTeamClass(team){
                if(team.selected){
                    return 'mdi-action-favorite mdi-material-lime';
                }else{
                    return 'mdi-action-favorite-outline';
                }
            }

            function processTeams(teams){
                teams.sort(function(teamA, teamB){
                    var nameA = teamA.name.toUpperCase();
                    var nameB = teamB.name.toUpperCase();
                    if(!nameA){
                        return 1;
                    } else if(!nameB){
                        return -1;
                    } else {
                        if(nameA > nameB){
                            return 1;
                        } else if(nameA < nameB) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });

                teams.map(function(team){
                    if(team.name === '' || !team.name){
                        team.name = $scope.strings.NOT_AVAILABLE;
                    }
                });
            }

            function getTeams(id_competition, offset, pageSize){

                $scope.$emit('load');

                TeamsManager.getTeamsIdCompetition(id_competition, offset, pageSize).then(function(pTeams){

                    $scope.hasTeams = true;

                    processTeams(pTeams);
                    teams = pTeams;
                    getFilterFavoriteTeams();
                    $scope.teams = teams.slice(page.first, page.last);
                    getFavTeams();
                    $scope.teams = pTeams;

                    // pagination controls
                    $scope.currentPage = 1;
                    $scope.totalItems = $scope.teams.length;
                    $scope.entryLimit = 10; // items per page
                    $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);

                    // $watch search to update pagination
                    $scope.$watch('search', function (newVal, oldVal) {
                      $scope.filtered = filterFilter($scope.teams, newVal);
                      $scope.totalItems = $scope.filtered.length;
                      $scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
                      $scope.currentPage = 1;
                      $scope.scrollL.scrollTo(0,0,0);
                    }, true);

                    //create empty search model (object) to trigger $watch on update
                    $scope.search = {};
                    $scope.resetFilters = function () {
                      // needs to be a function or it won't trigger a $watch
                      $scope.search = {};
                    };

                }, function(){
                    $scope.hasTeams = false;
                    $scope.teams = [];
                }).finally(function(){
                  $scope.$emit('unload');
                });

            }

            function getFilterFavoriteTeams(){
                var favTeams = TeamsManager.getFavoriteTeams();
                favTeams.forEach(function(fTeams){
                  teams.forEach(function(lTeams,index){
                    if (fTeams.id_teams == lTeams.id_teams) {
                      teams.splice(index, 1);
                      console.log(JSON.stringify(lTeams) + ' -> index -> ' + index);
                    }
                  });
                });
            }


            function getFavTeams(){
                var favTeams = TeamsManager.getFavoriteTeams();
                favTeams.forEach(function(elem){
                    var index = $scope.teams.indexOf(elem);
                    if(index > -1){
                        $scope.teams.splice(index, 1);
                    }
                });
            }

            function showPrevPage(){
                if(!$scope.searchQuery){
                    var start = page.first - maxItems;
                    if(start < 0){
                        start = 0;
                    }

                    var end = page.first - 1;
                    if(end < 0){
                        end = ((teams.length - 1) < (start + maxItems))? start + maxItems: teams.length - 1;
                    }

                    updateVisibleTeams(start, end);
                }
            }

            function showNextPage(){
                if(!$scope.searchQuery){
                    var start = page.first + maxItems;
                    if(start > teams.length){
                        start = teams.length - (1 + maxItems);
                    }

                    console.log('page.last: ' + page.last);
                    var end = page.last + maxItems;
                    if(end >= teams.length){
                        end = teams.length === 0 ? 0 : teams.length - 1;
                    }

                    updateVisibleTeams(start, end);
                }
            }

            function updateVisibleTeams(start, end){
                if(start !== page.first && end !== page.last){
                    console.log('updateVisibleTeams. Loading more teams. [' + start + ', ' + end +']');
                    $scope.teams = teams.slice(start, end);
                    page.first = start;
                    page.last = end;

                    $scope.hasPrev = page.first > 0;
                    $scope.hasNext = page.last  < (teams.length - 1);
                }
            }

            function setUpIScroll() {
                $scope.scrollL = iScroll.vertical('wrapperL');

                $scope.scrollL.on('beforeScrollStart', function () {
                    this.refresh();
                });

                $scope.$on('$destroy', function() {
                    $scope.scrollL.destroy();
                    $scope.scrollL = null;
                });
            }

            $scope.showContentTeams = function(league) {
                $scope.hasTeams = true;
                $scope.teams = null;
                $rootScope.transitionPageBack('#wrapper2', 'left');
                $scope.scroll.scrollTo(0,0,0);
                getTeams(league.id_competitions);
                setUpIScroll();
            }

           function getCompetitions(){
                Competitions.get(true).then(function(data){
                    $scope.leagues  = data;
                    $scope.scroll = iScroll.vertical('wrapper');
                    $scope.$emit('unload');
                });
            }

            function init(){
              $scope.scroll = iScroll.vertical('wrapper');
              getCompetitions();
            } init();



        }
]);
