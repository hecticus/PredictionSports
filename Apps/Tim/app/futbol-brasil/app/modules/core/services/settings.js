'use strict';

/**
 * @ngdoc service
 * @name core.Services.Settings
 * @description Settings Factory
 */
angular
    .module('core')
    .factory('Settings',[ '$localStorage','$http', 'Client', 'Domain',
        function($localStorage, $http, Client, Domain) {
            var KEY_SETTINGS = 'SETTINGS';
            var KEY_BETS_PUSH = 'BETS_PUSH';
            var KEY_NEWS_PUSH = 'NEWS_PUSH';
            var KEY_MTM_PUSH = 'MTM_PUSH';
            var settings = {};

            var loadPersistedSettings = function(){
                if($localStorage[KEY_SETTINGS]){
                    settings = JSON.parse($localStorage[KEY_SETTINGS]);
                } else {
                    settings = {};
                    settings[KEY_BETS_PUSH] = true;
                    settings[KEY_NEWS_PUSH] = true;
                    settings[KEY_MTM_PUSH] = true;
                }
            };

            var persistSettings = function(){
                $localStorage[KEY_SETTINGS] = JSON.stringify(settings);
            };

            var saveSettingsToServer = function (){
                var jsonData = {
                    "receive_bets": settings[KEY_BETS_PUSH],
                    "receive_news": settings[KEY_NEWS_PUSH],
                    "receive_min": settings[KEY_MTM_PUSH]
                };
                $http.post(Domain.client.update(Client.getClientId()), jsonData, {timeout : 60000})
                    .success(function(data, status) {
                        var errorCode = data.error;
                        var response = data.response;
                        if(errorCode == 0 && response != null){
                            //console.log('saveSettingsToServer.success: ');
                            //console.log(response);
                            //console.log("Configuracion guardada con éxito en el servidor. ");
                            persistSettings();
                        }else{
                            //console.log("Error guardando nuevos favoritos");
                        }
                    })
                    .error (function(data, status) {
                    //console.log("error save favorite");
                });
            };

            var loadSettings = function (pushAlerts){
                try{
                    //console.log("loadSettings");
                    loadPersistedSettings();

                    pushAlerts.forEach( function(item) {
                        switch(item.push_alert.name){
                            case "news":
                                settings[KEY_NEWS_PUSH] = item.status;
                                break;
                            case "bets":
                                settings[KEY_BETS_PUSH] = item.status;
                                break;
                        }
                    });

                    persistSettings();
                } catch(e){
                   //console.log("Error seteando pushAlerts settings");
                }
            };

            return {

                /**
                 * @ngdoc function
                 * @name core.Services.Settings#init
                 * @methodOf core.Services.Settings
                 */
                init: function() {
                    loadPersistedSettings();
                },

                isBetsPushActive : function(){
                    return settings[KEY_BETS_PUSH];
                },

                isNewsPushActive : function(){
                    return settings[KEY_NEWS_PUSH];
                },

                isMtmPushActive : function(){
                    return settings[KEY_MTM_PUSH];
                },

                toggleBetsPush : function(state){
                    //console.log('toggleBetsPush: ' + state);
                    settings[KEY_BETS_PUSH] = state;
                    saveSettingsToServer();
                },

                toggleNewsPush : function(state){
                    //console.log('toggleNewsPush: ' + state);
                    settings[KEY_NEWS_PUSH] = state;
                    saveSettingsToServer();
                },

                toggleMtmPush : function(state){
                    //console.log('toggleMtmPush: ' + state);
                    settings[KEY_MTM_PUSH] = state;
                    saveSettingsToServer();
                },

                setMtmStatus : function(state){
                    settings[KEY_MTM_PUSH] = state;
                    persistSettings();
                },

                loadSettings: loadSettings
            };
        }
    ]);
