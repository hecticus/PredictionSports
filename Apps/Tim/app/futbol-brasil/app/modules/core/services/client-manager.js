'use strict';

/**
 * @ngdoc service
 * @name core.Services.ClientManager
 * @description ClientManager Factory
 */
angular
    .module('core')
    .factory('ClientManager',['$http', '$translate', '$q', '$localStorage',  'CordovaDevice', 'WebManager', 'FacebookManager',
        'TeamsManager', 'Client', 'Domain', 'i18n', 'Settings', 'App',
        function($http, $translate, $q, $localStorage, CordovaDevice, WebManager, FacebookManager,
                 TeamsManager, Client, Domain, i18n, Settings, App) {

            var arrMsisdnTestClient = ['40766666611','40766666612', '40766666613',
            '40766666614', '40766666615', '40766666616',
            '40766666617', '40766666618', '40766666619',
            '40766666620'];

            var strPasswordTestClient ='1234';


            //noinspection UnnecessaryLocalVariableJS
            var service = {

                /**
                 * @ngdoc function
                 * @name core.Services.ClientManager#init
                 * @methodOf core.Services.ClientManager
                 */
                init : init,

                /**
                 * @ngdoc function
                 * @name core.Services.ClientManager#createOrUpdateClient
                 * @description Creates or Updates a Client on the server, if password is null
                 * then it only upates the client
                 * @methodOf core.Services.ClientManager
                 */
                createOrUpdateClient : createOrUpdateClient,

                /**
                 * @ngdoc function
                 * @name core.Services.ClientManager#getClientStatus
                 * @description Retrieves Client info from the server
                 * @methodOf core.Services.ClientManager
                 */
                getClientStatus : getClientStatus,

                /**
                 * @ngdoc function
                 * @name core.Services.ClientManager#updateRegistrationId
                 * @description Triggers a Client info update on the server if the remote RegId differs from
                 * local RegId
                 * @methodOf core.Services.ClientManager
                 */
                updateRegistrationId : updateRegistrationId,
            };

            function init(){
                var deferred = $q.defer();
                Client.init().then(function(){
                    if(Client.getClientId()){
                        TeamsManager.init();
                        getClientStatus()
                            .then(function(data){
                                console.log('init.resolve, data:',data);
                                deferred.resolve(data);
                            }, function(){
                                console.log('init.reject');
                                deferred.reject();
                            });
                    }else{
                        //Cliente en periodo de pruebas
                        console.log('init.resolve trial');
                        deferred.resolve(false, 2);
                    }
                });

                return deferred.promise;
            }

            function createOrUpdateClient(client, subscribe, successCallback, errorCallback){

                var devices = [];
                var device = {};
                var isNewClient = true;
                var lang = getLanguage();

                var jData = {
                    country : 3,
                    language: lang? lang.id_language : 405,
                    device_id : CordovaDevice.getDeviceId(),
                    upstreamChannel : CordovaDevice.getUpstreamChannel()
                };

                var facebook_id = FacebookManager.getUserId();
                if(facebook_id){
                    jData.facebook_id = facebook_id;
                }

                if(Client.getRegId()){
                    device.device_id = jData.device_id;
                    device.registration_id = Client.getRegId();
                    devices.push(device);
                }else{
                    //console.log('createOrUpdateClient. no regId.');
                }


                if(client.msisdn){
                    jData.login = client.msisdn;
                }

                if(client.password){
                    jData.password = client.password;
                    Client.setPassword(client.password);
                }

                if(client.nickname){
                    Client.setNickname(client.nickname);
                    jData.nickname = client.nickname;
                }

                var url = '';

                if(Client.getClientId()){
                    url = Domain.client.update();
                    jData.add_devices = devices;
                } else {
                    url = Domain.client.create();
                    if (jData.device_id == 3) delete jData.device_id;
                    else jData.devices = devices;
                    if(subscribe){ jData.subscribe = true; }
                }

                //console.log('jData -> ' + JSON.stringify(jData));

                $http({
                    url : url,
                    method: 'POST',
                    data: jData,
                    timeout : 60000
                })
                    .then(function(data) {
                        isNewClient = (data.status === 201);
                        data = data.data;

                        var errorCode = data.error;
                        var response = data.response;
                        if(errorCode == 0 && response != null){
                            //console.log("data:",response);
                            setClientPushAlerts(response.push_alerts_info);
                            TeamsManager.setFavoriteTeams(response.push_alerts_teams);
                            if(Client.updateClient(response)){
                                typeof successCallback == "function" && successCallback(isNewClient);
                            }else{
                                typeof errorCallback == "function" && errorCallback();
                            }
                        }else{
                            //console.log("Error guardando cliente: " + data.description);
                            typeof errorCallback == "function" && errorCallback();
                        }
                    }, function(data) {

                        //console.log("createClient. Error creating client. status: " + data.status);
                        ////console.log(data.data);

                         if ((arrMsisdnTestClient.indexOf(client.msisdn) >= 0)
                             && strPasswordTestClient === client.password) {
                             createOrUpdateClient({}, true, successCallback, errorCallback);
                         } else {
                            typeof errorCallback == "function" && errorCallback(data.data);
                         }

                    });
            }


            function getClientStatus(){
                //console.log("getClientStatus...");
                var deferred = $q.defer();
                var upstreamChannel = CordovaDevice.getUpstreamChannel();
                var clientId = Client.getClientId();

                if(!clientId){
                    return $q.reject();
                }

                $http.get(Domain.client.get(clientId, upstreamChannel), {cache: false, timeout : 60000})
                .success(function(data, status) {
                    var errorCode = data.error;
                    var response = data.response;
                    if(response && errorCode === 0){
                        //setClientPushAlerts(response.push_alerts_info);
                        TeamsManager.setFavoriteTeams(response.push_alerts_teams);
                        Client.updateClient(response, null)
                        .then(
                            function(){
                                //console.log("getClientStatus data:",data);
                                var clientData = {
                                    'is_active': Client.setActiveStatus(response.status),
                                    'status' : response.status
                                };
                                setLanguage(response.language);
                                deferred.resolve(clientData);
                            }, function(){
                                deferred.reject();
                            }
                        );
                    }else{
                        var description = data.description ? data.description:'No Error Description';
                        //console.log("Error. obteniendo status de cliente: " + description);
                        deferred.reject();
                    }
                })
                .error(function(data, status) {
                    //console.log("Error. Get status client " + data);
                    deferred.reject();
                });

                return deferred.promise;
            }

            function updateRegistrationId(id){
                Client.setRegId(id);
                if(Client.getClientId() && Client.getHasToUpdateRegId()){
                    createOrUpdateClient({'msisdn' : Client.getMsisdn()}, false);
                }
            }

            function setLanguage(lang){
                if(!lang){
                    lang = i18n.getDefaultLanguage();
                }

                if(lang){
                    $translate.use(lang.short_name.toLowerCase());
                }
            }

            function getLanguage(){
                var lang = Client.getLanguage();
                if(!lang){
                    lang = i18n.getDefaultLanguage();
                }
                return lang;
            }

            function setClientPushAlerts(pushAlerts){
                Settings.loadSettings(pushAlerts);
            }

            return service;
        }
    ]);
