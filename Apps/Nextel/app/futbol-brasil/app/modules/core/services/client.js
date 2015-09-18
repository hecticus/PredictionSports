'use strict';

/**
 * @ngdoc service
 * @name core.Services.Client
 * @description Client Factory
 */
angular
    .module('core')
    .factory('Client', ['$localStorage', '$translate', '$q',
        function($localStorage, $translate, $q) {
            var FILE_KEY_STOREDVERSION = "APPSTOREDVERSION";
            var FILE_KEY_CLIENT = "APPDATACLIENT";
            var FILE_KEY_CLIENT_PUSH_ALERTS = "APPDATACLIENTPUSHALERTS";
            var FILE_KEY_CLIENT_DATASAFE = "APPDATACLIENTDATASAFE";

            var localStorage = $localStorage;
            var hasToUpdateRegId = false;
            var clientPushAlerts = [];
            var clientDataSafe = false;
            var client = {};
            var currentVersion = 0;
            //noinspection UnnecessaryLocalVariableJS
            var service = {

                /**
                 * @ngdoc function
                 * @name core.Services.Client#init
                 * @methodOf core.Services.Client
                 * @return {boolean} Returns a boolean value
                 */
                init : init,

                updateClient : updateClient,

                /**
                 * @ngdoc function
                 * core.Services.Client
                 * @description Logs the user out of the Application
                 * @methodOf core.Services.ClientManager
                 */
                logout : logout,

                setGuest: setGuest,
                setNoGuest : setNoGuest,
                isGuest : isGuest,

                /**
                 * @ngdoc function
                 * @name core.Services.ClientManager#isActiveClient
                 * @description
                 * {
                 *  -1 : Unsuscribed by Upstream,
                 *   0 : Unsuscribed by User,
                 *   1 : Active and Paid,
                 *   2 : Trial period, awaiting confirmation
                 * }
                 * @methodOf core.Services.ClientManager
                 * @return {boolean} Returns a boolean value
                 */
                isActiveClient : isActiveClient,
                
                markClientAsNotOK : markClientAsNotOK,

                getClientId : function(){
                    return client.id_client;
                },
                setNickname : function(nickname){
                    client.nickname = nickname;
                },
                getNickname : function(){
                    return client.nickname;
                },
                getSession : function(){
                    return client.session;
                },
                getUpstreamAuthToken: function(){
                    return client.auth_token;
                },
                getPassword : function(){
                    return client.password;
                },

                setPassword : setPassword,

                getClientPushAlerts : function(){
                    return clientPushAlerts;
                },

                isClientOk : function(){
                    return clientDataSafe;
                },

                getMsisdn: function(){
                    return client.msisdn;
                },

                setMsisdn : setMsisdn,

                getRegId : function(){
                    return client.regId;
                },

                setRegId : setRegId,

                getHasToUpdateRegId : function (){
                    return hasToUpdateRegId;
                },

                setHasFavorites : setHasFavorites,

                getHasFavorites : getHasFavorites,

                enableFavoritesFilter: enableFavoritesFilter,

                isFavoritesFilterActive : isFavoritesFilterActive,

                setLanguage : setLanguage,

                getLanguage : getLanguage,

                getFriends : getFriends,

                setFriends : setFriends,

                getFriendsIds : getFriendsIds,
                
                setActiveStatus : setActiveStatus
            };

            function init() {
                var deferred = $q.defer();
                loadClient();
                deferred.resolve(true);
                return deferred.promise;
            }

            function updateClient(data, password) {                
                var deferred = $q.defer();
                if(!data){
                    deferred.reject();
                }
                client.id_client = data.id_client;
                client.user_id = data.user_id;
                client.login = data.login;
                client.session = data.session;
                client.auth_token = data.auth_token;
                client.nickname = data.nickname;
                
                if(data.status){
                    setActiveStatus(data.status);
                }                

                if(data.language){
                    setLanguage(data.language);
                }
                setPassword(password);
                saveClient();
                deferred.resolve(client);
                return deferred.promise;
            }

            function logout(){
                $localStorage.$reset();
                console.log("Client data:",client);
                client = {};
            }

            function saveStoredVersion() {
                try{
                    localStorage[FILE_KEY_STOREDVERSION] = currentVersion;
                    return true;
                }catch(err){
                    return false;
                }
            }

            function loadStoredVersion() {
                return localStorage[FILE_KEY_STOREDVERSION];
            }

            function checkStoredData(){
                var storedVersion = loadStoredVersion();
                if(!!storedVersion || currentVersion > storedVersion){
                    eraseAllConfigs();
                }
                saveStoredVersion();
            }

            function eraseAllConfigs(){
                delete localStorage[FILE_KEY_CLIENT];
                delete localStorage[FILE_KEY_CLIENT_DATASAFE];
            }

            function setPassword(password){
                if(!!password){
                    client.password = password;
                    saveClient();
                    markClientAsOk();
                }
            }

            function isActiveClient(){
                //return !!(status > 0 && status != 2);
                return client.active && client.active === true;
            }
            
            function setActiveStatus(status) {
                client.active = !!(status > 0 && status != 2);
                //client.active = false;
                saveClient();
                markClientAsOk();
                return client.active;
            }

            function setNoGuest(){
                client.guest = false;
            }

            function setGuest(){
                client.guest = true;
                saveClient();
                markClientAsOk();
            }

            function isGuest(){
                return client.guest && client.guest === true;
            }

            function loadClient(){
                checkStoredData();
                clientDataSafe = (localStorage[FILE_KEY_CLIENT_DATASAFE] === 'true');
                if(clientDataSafe){
                    var clientString = localStorage[FILE_KEY_CLIENT];
                    if(!!clientString && clientString != ''){
                        client = JSON.parse(clientString);
                    }
                }else{
                    eraseAllConfigs();
                }
            }

            function saveClient() {
                localStorage[FILE_KEY_CLIENT] = JSON.stringify(client);
            }

            function markClientAsOk() {
                clientDataSafe = true;
                localStorage[FILE_KEY_CLIENT_DATASAFE] = 'true';
                return clientDataSafe;
            }
            
            function markClientAsNotOK() {
                clientDataSafe = false;
                localStorage[FILE_KEY_CLIENT_DATASAFE] = 'false';
                return clientDataSafe;
            }

            function setMsisdn(msisdn, successCallback, errorCallback) {
                try{
                    msisdn = (''+msisdn).replace(/^\s+|\s+$/g, "");
                    if(isNaN(msisdn) && (msisdn.length < 8 || msisdn.length > 11)){
                        typeof errorCallback == "function" && errorCallback();
                    }

                    for(var i = 0; i < msisdn.length; ++i){
                        parseInt(msisdn[i],10);
                    }
                    client.msisdn = msisdn;
                    saveClient();

                    typeof successCallback == "function" && successCallback();
                } catch(err){
                    console.log("setMsisdn. Error. Invalid MSISDN: " + err);
                    typeof errorCallback == "function" && errorCallback();
                }
            }

            function setRegId(id) {
                hasToUpdateRegId = true;
                client.regId = id;
                saveClient();
            }

            function setHasFavorites(value){
                client.hasFavorites = value;
                saveClient();
            }

            function getHasFavorites(){
                if(!client.hasFavorites){
                    loadClient();
                }
                return client.hasFavorites === true;
            }

            function enableFavoritesFilter(value){
                client.isFavoritesFilterActive = value;
                saveClient();
            }

            function isFavoritesFilterActive(){
                if(!client.isFavoritesFilterActive){
                    loadClient();
                }
                return client.isFavoritesFilterActive;
            }

            function setLanguage(language, callback){
                if(language){
                    $translate.use(language.short_name.toLowerCase());
                    client.language = language;
                    saveClient();
                    typeof callback === 'function' && callback();
                }
            }

            function getLanguage(){
                if(!client.language){
                    loadClient();
                }
                return client.language;
            }

            function getFriends(){
                if(!client.friends){
                    loadClient();
                }
                return client.friends;
            }

            function setFriends(pFriends){
                client.friends = pFriends;
                saveClient();
            }

            function getFriendsIds(){
                var idFriends = [];
                if(!client.friends){
                    loadClient();
                }

                if(client.friends){
                    idFriends = client.friends.map(function(friend){
                        return friend.id;
                    });
                }

                return idFriends;
            }

            return service;
        }
    ]);
