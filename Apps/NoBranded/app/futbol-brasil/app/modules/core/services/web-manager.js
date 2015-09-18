'use strict';

/**
 * @ngdoc service
 * @name core.Services.WebManager
 * @description WebManager Factory
 */
angular
    .module('core')
    .factory('WebManager',['$http', '$q', '$localStorage', 'CordovaDevice', 'TeamsManager', 'Domain', 'Client', 'App',
        function($http, $q, $localStorage, CordovaDevice, TeamsManager, Domain, Client, App) {

            /**
             * @ngdoc function
             * @name core.Services.WebManager#enableCerts
             * @description Enables Self Signed Certificates for HTTP Protocol
             * @methodOf core.Services.WebManager
             * @return {boolean} Returns certificate activation result
             */
            function enableCerts(all) {
                console.log('WebManager. all: ' + all);
                if(!!plugins.CordovaHttpPlugin) {
                    if (all) {
                        plugins.CordovaHttpPlugin.acceptAllCerts(true, function () {
                            return true;
                        }, function () {
                            return false;
                        });
                    } else {
                        plugins.CordovaHttpPlugin.enableSSLPinning(true, function () {
                            return true;
                        }, function () {
                            return false;
                        });
                    }
                } else {
                    console.log('plugins.CordovaHttpPlugin Object not available. Are you directly on a browser?');
                }
            }

            /**
             * @ngdoc function
             * @name core.Services.WebManager#getAppender
             * @description Appender function for Auth Token Generation
             * @param {string} index position of the required character
             * @methodOf core.Services.WebManager
             * @return {string} Returns character value for index
             */
            function getAppender(index) {
                switch (index) {
                    case '1': return '|';
                    case '2': return '@';
                    case '3': return '#';
                    case '4': return '$';
                    case '5': return '%';
                    case '6': return '&';
                    case '7': return '/';
                    case '8': return '(';
                    case '9': return ')';
                    case '0': return '=';
                    default: return '-';
                }
            }

            function getHeaders() {


                var companyName = App.getCompanyName();
                var buildVersion = App.getBuildVersion();
                var serverVersion = App.getServerVersion();
                var headers = { "Content-Type": "application/json; charset=utf-8" };
                var auth = "";
                try {

                    if ($localStorage['LOADING']) {
                      var jLoading = JSON.parse($localStorage['LOADING']);
                      companyName = jLoading.company_name;
                      buildVersion = jLoading.build_version;
                      serverVersion = jLoading.server_version;
                    }

                    auth = companyName + getAppender(buildVersion.charAt(0))
                        + buildVersion + getAppender(serverVersion.charAt(0))
                        + serverVersion;

                    headers['HECTICUS-X-AUTH-TOKEN'] = auth;
                } catch (e) {
                    console.log('Error setting HECTICUS-X-AUTH-TOKEN');
                }

                var clientSession = Client.getSession();
                if(clientSession){
                    headers['Authorization'] = 'Bearer ' + clientSession;
                }

                return headers;
            }

            function loadServerConfigs(){
                var deferred = $q.defer();
                var platform = CordovaDevice.getPlatform();
                var version = App.getBundleVersion();
                var width = CordovaDevice.getRealWidth();
                var height = CordovaDevice.getRealHeight();

                return $http.get(Domain.loading(width , height, version, platform))
                .success(function(data) {
                    $localStorage['LOADING'] = JSON.stringify(data.response);
                    deferred.resolve('request successful');
                }).error(function(){
                    deferred.reject('ERROR');
                });
                return deferred.promise;
            }

            function getFavoritesConfig(isFilterActive){
                var httpConfig = {params:{}};
                if(isFilterActive){
                    httpConfig.params.teams = TeamsManager.getFavoriteTeams().map(function(elem){
                        return elem.id_teams;
                    });
                    console.log('httpConfig.params.teams: ');
                    console.log(httpConfig.params.teams);
                }
                return httpConfig;
            }

            return {

                /**
                 * @ngdoc function
                 * @name core.Services.WebManager#getHeaders
                 * @description Generates Headers like HECTICUS-X-AUTH-TOKEN for Web Requests
                 * @methodOf core.Services.WebManager
                 * @return {object} Returns HTTP Headers
                 */
                getHeaders: getHeaders,

                /**
                 * @ngdoc function
                 * @name core.Services.WebManager#loadServerConfigs
                 * @description Gets App configuration from Server
                 * @methodOf core.Services.WebManager
                 */
                loadServerConfigs : loadServerConfigs,

                /**
                 * @ngdoc function
                 * @name core.Services.WebManager#getFavoritesConfig
                 * @description Generates params for HTTP Requests according to
                 * the Favorites Filter configuration
                 * @methodOf core.Services.WebManager
                 * @return {object} Returns HTTP Params for Favorites
                 */
                getFavoritesConfig : getFavoritesConfig
            }
    }
]);
