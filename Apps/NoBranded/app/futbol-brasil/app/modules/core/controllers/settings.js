'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.SettingsController
 * @description SettingsController
 * @requires ng.$scope
*/
angular
    .module('core')
    .controller('SettingsController', [
        '$scope', '$rootScope', '$state', '$timeout', '$translate', '$stateParams', 'ClientManager'
        , 'TeamsManager', 'FacebookManager', 'Settings', 'iScroll', 'i18n', 'Client', 'Notification',
        function($scope, $rootScope, $state, $timeout, $translate, $stateParams, ClientManager, TeamsManager
            , FacebookManager, Settings, iScroll, i18n, Client, Notification) {

            var strings = {};
            var scroll = null;
            var removeEventCallback = null;



            $rootScope.onMain = onMain;
            $scope.fbObject = {
                fbStatus: null,
                fbButtonMsg: '',
                class : 'btn-info'
            };

            $scope.lang = {};
            $scope.nickname = '';

            $scope.favoriteTeams = [{isEmpty : true}, {isEmpty : true}, {isEmpty : true}];

            $scope.toggles = {
                bets: true,
                news: true,
                mtm: true
            };

            $scope.isEditing = false;

            $scope.saveNickname = function(){
              $scope.isEditing = true;
            };

            $scope.saveNickname = function(){
                if(!$scope.isEditing){
                    $scope.isEditing = true;
                } else if ($scope.nickname){
                    $scope.isEditing = false;
                    console.log('Saving nickName: ' + $scope.nickname);
                    ClientManager.createOrUpdateClient({ 'nickname' : $scope.nickname });
                } else {
                    console.log('Please input a valid nickName');
                }
            };

            $scope.teamSelected = function(team){
                if($scope.isEmptySlot(team)){
                    $scope.$emit('load');
                    $state.go('team-selection',{newClient:$stateParams.newClient});
                }
            };

            $scope.removeTeam = function(team){
                if(team){
                    TeamsManager.removeFavoriteTeam(team, function(){ $state.reload(); });
                    $scope.favoriteTeams[$scope.favoriteTeams.indexOf(team)] = {isEmpty: true};
                }
            };

            $scope.getTeamName = function(team){
                if(team && typeof team.name != 'undefined'){
                    return team.name !== '' ? team.name : $scope.strings.NOT_AVAILABLE;
                }else{
                    return $scope.strings.ADD_TEAM;
                }
            };

            $scope.getTeamClasses = function(team, index){
                var cssClass = $scope.isEmptySlot(team)? 'mdi-content-add ': 'mdi-action-grade ';
                if($scope.isEmptySlot(team)){
                    cssClass += 'btn-primary ';
                }else if(index / 2 == 0){
                    cssClass += 'btn-success ';
                } else {
                    cssClass += 'btn-info ';
                }
                return cssClass;
            };

            $scope.isEmptySlot = function(team){
                return !!team.isEmpty;
            };

            $scope.toggleBets = function(){
                $scope.toggles.bets = !$scope.toggles.bets;
                Settings.toggleBetsPush($scope.toggles.bets);
            };

            $scope.toggleNews = function(){
                $scope.toggles.news = !$scope.toggles.news;
                Settings.toggleNewsPush($scope.toggles.news);
            };

            $scope.toggleMtm = function(){
                $scope.toggles.mtm = !$scope.toggles.mtm;
                Settings.toggleMtmPush($scope.toggles.mtm);
            };


            $scope.onFbButtonClick = function(){

                if(Client.isGuest() || !Client.isActiveClient()){
                    Notification.showLockedSectionDialog();
                } else {

                    $scope.fbObject.class = 'btn-warning';
                    $scope.fbObject.fbButtonMsg = strings['SET_LOADING'];

                    if($scope.fbObject.fbStatus === 'connected'){
                       FacebookManager.logout(function(){
                          $scope.fbObject.class = 'btn-info';
                          getStatus();
                       });
                    } else if($scope.fbObject.fbStatus !== 'connected') {
                      FacebookManager.login(function(response){
                          console.log("FB login:", response);
                          if (response.status === 'connected') {
                              console.log("Setting FB status");
                              ClientManager.createOrUpdateClient({'msisdn' : Client.getMsisdn()}, false);
                              $scope.fbObject.class = 'btn-success';
                            };
                            getStatus();
                      });
                      
                    }
                }
            };


            $scope.setFbButtonMsg = function(){
                $translate(['SETTINGS.FACEBOOK.CONNECT', 'SETTINGS.FACEBOOK.CONNECTED'])
                    .then(function(translations){
                        if($scope.fbObject.fbStatus === 'connected'){
                            $scope.fbObject.fbButtonMsg = translations['SETTINGS.FACEBOOK.CONNECTED'];
                            $scope.fbObject.class = 'btn-success';
                        } else{
                            $scope.fbObject.fbButtonMsg = translations['SETTINGS.FACEBOOK.CONNECT'];
                            $scope.fbObject.class = 'btn-info';
                        }
                    }
                );

            };

            $scope.selectLanguage = function(){
                $scope.$emit('load');
                $state.go('language-selection',{newClient:$stateParams.newClient});
            };

            function getFavoriteTeams(){
                TeamsManager.getTeams();
                var teams = TeamsManager.getFavoriteTeams();
                console.log(JSON.stringify(teams));
                $rootScope.hasFavorites = false;
                for(var i = 0; i < 3; i++){
                    if(teams[i]) {
                        $scope.favoriteTeams[i] = teams[i];
                        $scope.favoriteTeams[i].isEmpty = false;
                        $rootScope.hasFavorites = true;
                    } else {
                        $scope.favoriteTeams[i] = {};
                        $scope.favoriteTeams[i].isEmpty = true;
                    }
                }
            }

            function loadSettings(){
                Settings.init();
                $scope.toggles.bets = Settings.isBetsPushActive();
                $scope.toggles.news = Settings.isNewsPushActive();
                $scope.toggles.mtm = Settings.isMtmPushActive();
            }

            function getStatus(){
                //if(!!window.facebookConnectPlugin) {
                    FacebookManager.getStatus(function (result) {
                        if (result) {
                            $scope.fbObject.fbStatus = result.status;
                            $scope.setFbButtonMsg();
                        }
                    });
                /*} else {
                    $scope.setFbButtonMsg();
                    //$timeout(function(){
                        //$scope.fbObject.fbStatus = 'connected';
                        //$scope.setFbButtonMsg();
                    //}, 1000);
                }*/
            }

            function getClientLanguage(){
                $scope.lang = Client.getLanguage();
                if(!$scope.lang){
                    $scope.lang = i18n.getDefaultLanguage();
                }

                $scope.lang.short_name = $scope.lang.short_name.toUpperCase();

                $translate(['LANGUAGE.' + $scope.lang.short_name, 'SETTINGS.ADD_TEAM', 'NOT_AVAILABLE'])
                .then(function(translations){
                    $scope.lang.translation = translations['LANGUAGE.' + $scope.lang.short_name];
                    $scope.strings.ADD_TEAM = translations['SETTINGS.ADD_TEAM'];
                    $scope.strings.NOT_AVAILABLE = translations['NOT_AVAILABLE'];
                });
            }

            function setUpIScroll() {
                scroll = iScroll.verticalForm('wrapper');

                $scope.$on('$destroy', function() {
                    scroll.destroy();
                    scroll = null;
                });
            }

            function getTranslationsSetUserName(){
              $translate(['ALERT.SET_USERNAME.TITLE',
                          'ALERT.SET_USERNAME.SUBTITLE',
                          'ALERT.SET_USERNAME.MSG', 'LOADING'])
              .then(function(translation){
                  strings['SET_USERNAME_TITLE'] = translation['ALERT.SET_USERNAME.TITLE'];
                  strings['SET_USERNAME_SUBTITLE'] = translation['ALERT.SET_USERNAME.SUBTITLE'];
                  strings['SET_USERNAME_MSG'] = translation['ALERT.SET_USERNAME.MSG'];
                  strings['SET_LOADING'] = translation['LOADING'];
              });
            }


            function onMain(){
                 if (Client.getNickname() == undefined) {
                    Notification.showInfoAlert({
                        title: strings['SET_USERNAME_TITLE'],
                        subtitle: strings['SET_USERNAME_SUBTITLE'],
                        message: strings['SET_USERNAME_MSG'],
                        type: 'success'
                    });
                 } else {
                    $rootScope.$storage.settings = true;
                    if($stateParams.newClient){
                      $state.go('tutorial');
                    } else {
                      $state.go('prediction');
                    }
                 };
            }


            function init(){
                setUpIScroll();
                getFavoriteTeams();
                loadSettings();
                getStatus();
                getClientLanguage();
                getTranslationsSetUserName();

                removeEventCallback = $rootScope.$on('$translateChangeSuccess', function () {
                    getClientLanguage();
                    getTranslationsSetUserName();
                });
                $scope.nickname = Client.getNickname();
                $scope.$emit('unload');

                $scope.$on('$destroy', function() {
                    typeof removeEventCallback === 'function' && removeEventCallback();
                });

                 if ($scope.nickname == undefined) {
                    $scope.isEditing = true;
                 };

            } init();

        }
]);
