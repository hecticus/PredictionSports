'use strict';

/**
 * @ngdoc service
 * @name core.Services.TeamsManager
 * @description TeamsManager Factory
 */
angular
    .module('core')
    .factory('TeamsManager', ['$http', '$localStorage', '$q', 'Domain', 'Client', 'Settings',
        function($http, $localStorage, $q, Domain, Client, Settings) {
            var KEY_TEAMS_LIST = 'TEAMS_LIST';
            var KEY_FAVORITE_TEAMS_LIST = 'FAVORITE_TEAMS_LIST';
            var teams = [];
            var favTeams = [];
            var remoteTeams = [];

            function getTeamsFromServerIdCompetition(id_competition){
                var page = 0;
                var pageSize = 1000;
                var config = {
                    params: {
                        page: page,
                        pageSize: pageSize
                    }
                };

                remoteTeams = [];
                console.log(Domain.teams.competition(id_competition));
                return $http.get(Domain.teams.competition(id_competition), config).then(
                    function(data){
                        console.log("Teams READY!!!!");
                        data = data.data;
                        //remoteTeams = remoteTeams.concat(data.response.teams);
                        return data.response.teams;
                    },
                    function(data){
                        console.log('getTeamsFromServer. error getting teams. last values: page='
                            + page + 'pageSize=' + pageSize);
                        console.log('getTeamsFromServer. data: ');
                        console.log(data);
                        return $q.reject(data);
                    }
                );
            }



            function getTeamsFromServer(isDone){
                var page = 0;
                var pageSize = 1000;
                var config = {
                    params: {
                        page: page,
                        pageSize: pageSize
                    }
                };

                remoteTeams = [];
                console.log(Domain.teams.index);
                return $http.get(Domain.teams.index, config).then(
                    function(data){
                        console.log("Teams READY!!!!");
                        data = data.data;
                        isDone = data.response.teams.length < pageSize;
                        remoteTeams = remoteTeams.concat(data.response.teams);
                        if(isDone){
                            saveTeams(remoteTeams);
                            return teams;
                        } else {
                            return getTeamsFromServer(isDone);
                        }
                    },
                    function(data){
                        console.log('getTeamsFromServer. error getting teams. last values: page='
                            + page + 'pageSize=' + pageSize);
                        console.log('getTeamsFromServer. data: ');
                        console.log(data);
                        return $q.reject(data);
                    }
                );
            }











            function saveTeams(sTeams){
                $localStorage[KEY_TEAMS_LIST] = JSON.stringify(sTeams);
                teams = sTeams.slice();
                return teams;
            }

            function loadTeams(){
                if($localStorage[KEY_TEAMS_LIST]){
                    teams = JSON.parse($localStorage[KEY_TEAMS_LIST]);
                    return true;
                } else {
                    teams = [];
                    return false;
                }
            }

            /*function setFavoriteTeams(pushAlerts){
               getTeams().then(function(){
                   console.log("Seteando favorite teams.....");
                    if(!pushAlerts){ return; }
                    var favoriteTeams = teams.filter(function(element){
                        var teamId = element.id_teams;
                        var found = false;
                        pushAlerts.forEach(function(pushAlert){
                            var pushAlertId = pushAlert.push_alert.id_ext;
                            found |= pushAlertId === teamId;
                        });
                        return found;
                    });
                    persistFavoriteTeams(favoriteTeams);
               });
            }*/

            function setFavoriteTeams(pushAlerts){

                var arrTeams = [];
                var status = false;

                pushAlerts.forEach(function(pushAlert){
                    //console.log("setFavoriteTeams:",pushAlert);
                    pushAlert.push_alert.id_teams =  pushAlert.push_alert.id_ext;
                    arrTeams.push(pushAlert.push_alert);
                    status = status || pushAlert.status;
               });

               //console.log("vivo status:",status);

                Settings.setMtmStatus(status);
                persistFavoriteTeams(arrTeams);
            }



            function persistFavoriteTeams(favoriteTeams){
                if(favoriteTeams){
                    $localStorage[KEY_FAVORITE_TEAMS_LIST] = JSON.stringify(favoriteTeams);
                    favTeams = favoriteTeams.slice();
                    if(favoriteTeams.length > 0){
                        Client.setHasFavorites(true);
                    } else {
                        Client.setHasFavorites(false);
                    }
                }
            }

            function loadFavoriteTeams(){
                if($localStorage[KEY_FAVORITE_TEAMS_LIST]) {
                    favTeams = JSON.parse($localStorage[KEY_FAVORITE_TEAMS_LIST]);
                } else {
                    console.log('loadFavoriteTeams. No records found for favorite teams.');
                    favTeams = [];
                }

                if(favTeams.length > 0){
                    Client.setHasFavorites(true);
                } else {
                    Client.setHasFavorites(false);
                }
            }

            function isTeamFavoriteById(idTeam){
                for(var i=0; i < favTeams.length; i++){
                    if(favTeams[i].id_teams == idTeam){
                        return true;
                    }
                }
                return false;
            }

            function isTeamFavorite(team){
                return (favTeams.indexOf(team) > -1);
            }

            function addFavoriteTeam(team, callback){
                var index = favTeams.indexOf(team);
                for(var i = 0; i < favTeams.length; i++){
                    if(team.id_teams === favTeams[i].id_teams){
                        index = i;
                        break;
                    }
                }
                if(index > -1){
                    console.log('Team. name: ' + team.name + ', id: ' + team.id_teams
                        + '. Is already a Favorite');
                } else {
                    favTeams.push(team);
                    persistFavoriteTeams(favTeams);
                    saveFavoriteTeamToServer({'add_push_alert' : [team.id_teams]}, callback);
                }

                Settings.setMtmStatus(true);
            }

            function removeFavoriteTeam(team, callback){
                console.log('removeFavoriteTeam. ');
                var index = favTeams.indexOf(team);
                for(var i = 0; i < favTeams.length; i++){
                    if(team.id_teams === favTeams[i].id_teams){
                        index = i;
                        break;
                    }
                }
                if(index > -1){
                    favTeams.splice(index, 1);
                    persistFavoriteTeams(favTeams);
                    saveFavoriteTeamToServer({'remove_push_alert' : [team.id_teams]}, callback);
                } else {
                    console.log('removeFavoriteTeam. Team to remove not found');
                }

               if(favTeams.length == 0){
                    Settings.setMtmStatus(false);
                }
            }

            function saveFavoriteTeamToServer(jsonData, callback){
                $http.post(Domain.client.update(Client.getClientId()), jsonData, {timeout : 60000})
                    .success(function(data) {
                        var errorCode = data.error;
                        var response = data.response;
                        if(errorCode == 0 && response != null){
                            console.log("Favoritos actualizados con éxito en el servidor. ");
                            persistFavoriteTeams(favTeams);
                            typeof callback == "function" && callback();
                        }else{
                            console.log("Error guardando nuevos favoritos");
                        }
                    })
                    .error (function(data, status) {
                    console.log("error save favorite");
                });
            }

            function getTeams(offset, pageSize) {
                console.log("getTeams.....");
                if(!offset){ offset = 0; }
                if(!pageSize){ pageSize = 1000; }

                if(teams && teams.length > 0){
                    var deferred = $q.defer();
                    deferred.notify();
                    console.log('teams on localStorage');
                    var end = offset+pageSize;
                    end = end >= teams.length? teams.length : end;
                    deferred.resolve(teams.slice(offset, end));
                    return deferred.promise;
                } else {
                    console.log('getTeams. No teams. Trying to get teams from server.');
                    teams = [];
                    return getTeamsFromServer().then(function(pTeams){

                        teams = pTeams;
                        return teams;

                    }, function(){

                        teams = [];
                        return $q.reject(teams);

                    }).then(function(pTeams){

                        var end = offset+pageSize;
                        console.log('pTeams from server');
                        end = end >= pTeams.length? pTeams.length : end;
                        return pTeams.slice(offset, end);

                    });
                }
            }


            function getTeamsIdCompetition(id_competition, offset, pageSize) {

                if(!offset){ offset = 0; }
                if(!pageSize){ pageSize = 1000; }

                teams = [];
                return getTeamsFromServerIdCompetition(id_competition).then(function(pTeams){
                    teams = pTeams;
                    return teams;
                }, function(){

                    teams = [];
                    return $q.reject(teams);

                }).then(function(pTeams){
                    var end = offset+pageSize;
                    console.log('pTeams from server');
                    end = end >= pTeams.length? pTeams.length : end;
                    return pTeams.slice(offset, end);

                });


            }













            function init(){
                remoteTeams = [];
                teams = [];
                favTeams = [];
                loadTeams();
                loadFavoriteTeams();
            }

        return {

            isTeamFavoriteById : isTeamFavoriteById,

            isTeamFavorite : isTeamFavorite,

            addFavoriteTeam : addFavoriteTeam,

            removeFavoriteTeam : removeFavoriteTeam,

            setFavoriteTeams : setFavoriteTeams,

            getFavoriteTeams: function() {
                return favTeams;
            },

            /**
             * @ngdoc function
             * @name core.Services.TeamsManager#getTeams
             * @description Gets Teams List from Local Storage
             * @methodOf core.Services.TeamsManager
             */
            getTeams: getTeams,
            getTeamsIdCompetition: getTeamsIdCompetition,
            /**
             * @ngdoc function
             * @name core.Services.TeamsManager#init
             * @description Initializes the Service
             * @methodOf core.Services.TeamsManager
             */
            init : init

        };
    }]);
