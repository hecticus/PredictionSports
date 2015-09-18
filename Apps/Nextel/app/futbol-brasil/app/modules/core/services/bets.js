'use strict';

/**
 * @ngdoc service
 * @name core.Services.Bets
 * @description Bets Factory
 */
angular
    .module('core')
    .factory('Bets',['$rootScope', '$http', 'Domain',
        function($rootScope, $http, Domain) {
            return {

                /**
                 * @ngdoc function
                 * @name core.Services.Bets#get
                 * @description Gets Leagues with Bets info
                 * @methodOf core.Services.Bets
                 * @return {Array} Returns Leagues with user Bets
                 */
                get: function(competition,successCallback, errorCallback) {
                    $http.get(Domain.bets.get(competition))
                        .success(function (data) {
                            $rootScope.$storage.bet = JSON.stringify(data.response);
                            typeof successCallback === 'function' && successCallback(data);
                        }).error(function () {
                            typeof errorCallback === 'function' && errorCallback();
                        });
                },

                /**
                 * @ngdoc function
                 * @name core.Services.Bets#create
                 * @description Creates Bets for the current user
                 * @methodOf core.Services.Bets
                 * @return {object} Returns the Request's Promise
                 */
                create: function(bets, successCallback, errorCallback) {
                    $http.post(Domain.bets.create(), bets)
                    .success(function() {
                            typeof successCallback === 'function' && successCallback();
                    })
                    .error(function() {
                            typeof errorCallback === 'function' && errorCallback();
                    });
                }
            };
        }
    ]);
