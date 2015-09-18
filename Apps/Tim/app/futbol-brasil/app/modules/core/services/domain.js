'use strict';

/**
 * @ngdoc object
 * @name core.Constants.Domain
 * @description Define a constant
 */
angular
    .module('core')
    .factory('Domain', ['$localStorage', 'App','Client','CordovaDevice',
        function($localStorage, App, Client,CordovaDevice){

            var football_manager_url = 'http://footballmanager.hecticus.com/';
            var brazil_football_manager_url = 'http://brazil.footballmanager.hecticus.com/';
            var appId = '1';
            var apiVersion = 'v1';
            var provisionalLang = null;


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

            function getHeadersAuth() {

                var companyName = App.getCompanyName();
                var buildVersion = App.getBuildVersion();
                var serverVersion = App.getServerVersion();

                if ($localStorage['LOADING']) {
                  var jLoading = JSON.parse($localStorage['LOADING']);
                  companyName = jLoading.company_name;
                  buildVersion = jLoading.build_version;
                  serverVersion = jLoading.server_version;
                }


                var auth = companyName;
                auth += getAppender(buildVersion.charAt(0));
                auth += buildVersion;
                auth += getAppender(serverVersion.charAt(0));
                auth += serverVersion;

                return auth;
            }

           function getGMT(prefix){
              return prefix + 'timezoneName=' + moment().format('[GMT]ZZ').replace(/\s/g, '');
           }

            function getLang(){
                if(!Client.getLanguage() && provisionalLang){
                    return provisionalLang.id_language;
                } else if(Client.getLanguage()){
                    return Client.getLanguage().id_language;
                } else {
                    return 405;
                }
            }

            function getClientId(){
                return Client.getClientId();
            }

            function setProvisionalLanguage(lang){
                provisionalLang = lang;
            }

            function getSecurityToken(prefix){
                return prefix + 'upstreamChannel=' + CordovaDevice.getUpstreamChannel()
                + '&api_password=' + getHeadersAuth();
            }

            return {

                setProvisionalLanguage : setProvisionalLanguage,

                loading: function (width, height, appVersion, platform) {
                    //console.log('client->loading');
                    return brazil_football_manager_url + 'api/loading/'
                        + width + '/' + height + '/' + appVersion + '/' + platform;
                },

                upstream: function () {
                  var ClientId = getClientId();
                  if (ClientId) return brazil_football_manager_url + 'futbolbrasil/v2/client/' + getClientId() + '/upstream' + getSecurityToken('?');
                  return false;
                },

                languages:  function () {
                  return brazil_football_manager_url + 'futbolbrasil/' + apiVersion + '/languages'  + getSecurityToken('?');
                },

                client: {
                    create: function () {
                      //console.log('client->create');
                      return brazil_football_manager_url + 'futbolbrasil/' + apiVersion + '/clients/create' + getSecurityToken('?');
                    },
                    update: function () {
                        //console.log('client->update');
                        return brazil_football_manager_url + 'futbolbrasil/'
                            + apiVersion + '/clients/update/' + getClientId() + getSecurityToken('?');
                    },
                    get: function (clientId, upstreamChannel) {
                          //console.log('client->get');
                        return brazil_football_manager_url + 'futbolbrasil/'
                            + apiVersion + '/clients/get/'
                            + clientId + '/' + upstreamChannel + getSecurityToken('?');
                    }
                },

                competitions: function () {
                 return football_manager_url + 'footballapi/'
                        + apiVersion + '/competitions/list/'
                        + appId + '/'
                        + getLang()
                        + getSecurityToken('?');
                },

                competitionsPrediction:  function (clientId) {

                    //console.log('domain -> competitionsPrediction');

                    return brazil_football_manager_url + 'futbolbrasil/'
                    + apiVersion + '/clients/dashboard/' +  clientId + '/' + getLang()
                    +  getGMT('?') + getSecurityToken('&') ;


                },

                news: {
                    index: function () {
                        return football_manager_url + 'newsapi/'
                            + apiVersion + '/news/scroll/'
                            + appId + '/' + getLang()
                            +  getGMT('?')  + getSecurityToken('&');
                    },

                    up: function (_news) {
                        return football_manager_url + 'newsapi/'
                            + apiVersion + '/news/scroll/up/rest/'
                            + appId + '/' + getLang() + '/' + _news
                            +  getGMT('?') + getSecurityToken('&');
                    },

                    down: function (_news) {
                        return football_manager_url + 'newsapi/'
                            + apiVersion + '/news/scroll/down/rest/'
                            + appId + '/' + getLang() + '/' + _news
                            +  getGMT('?')  + getSecurityToken('&');
                    }
                },

                teams:{

                    index: football_manager_url + 'footballapi/'
                        + apiVersion + '/team/app/all/' + appId + getSecurityToken('?'),

                     competition: function (id) {
                          return football_manager_url + 'footballapi/'
                                 + apiVersion + '/team/competition/all/' + id + getSecurityToken('?');
                     }


                },

                standings: function () {

                    return football_manager_url + 'api/'
                        + apiVersion + '/rankings/get/'
                        + appId + getLang() +  getGMT('?')  + getSecurityToken('&');
                },

                phases: function (_competition) {
                    return football_manager_url + 'footballapi/'
                        + apiVersion + '/competitions/phases/'
                        + appId + '/' + _competition + '/' + getLang()
                        +  getGMT('?')  + getSecurityToken('&');
                },

                ranking: function (_competition, _phase) {
                    return football_manager_url + 'footballapi/'
                        + apiVersion + '/competitions/ranking/'
                        + appId + '/' + _competition + '/' + getLang() + '/' + _phase
                        + getGMT('?')  + getSecurityToken('&');
                },

                scorers: function (_competition) {
                    return football_manager_url + 'footballapi/'
                        + apiVersion + '/players/competition/scorers/'
                        + appId + '/'
                        + _competition
                        + '?pageSize=15'
                        + '&page=0'
                        + getGMT('&')
                        + getSecurityToken('&');
                },

                match: function (_date) {
                    return football_manager_url + 'footballapi/'
                        + apiVersion + '/matches/date/paged/' + appId + '/'
                        + getLang() + '/' + _date
                        + getGMT('?')
                        + getSecurityToken('&');
                },

                mtm: function (_competition, _match, _event) {
                    return football_manager_url + 'footballapi/'
                        + apiVersion + '/matches/mam/next/'
                        + appId + '/' + _competition + '/' + _match
                        + '/' + getLang() + '/' + _event
                        + getGMT('?')
                        + getSecurityToken('&');
                },

                bets: {
                    get: function (_competition) {
                        return brazil_football_manager_url + 'futbolbrasil/'
                            + apiVersion + '/clients/bets/get/'
                            + getClientId() + '/' + _competition
                            + getGMT('?')
                            + getSecurityToken('&');
                    },

                    getToday: function (_date) {
                      return brazil_football_manager_url + 'futbolbrasil/'
                          + apiVersion + '/clients/bets/get/date/'
                          + getClientId() + '/' + _date
                          + getGMT('?')
                          + getSecurityToken('&');
                    },

                    create : function() {
                        return brazil_football_manager_url + 'futbolbrasil/'
                            + 'v2' + '/client/' + getClientId() + '/bet' + getSecurityToken('?');
                    }
                },

                leaderboard:  {

                    total: function() {
                        return 'http://brazil.footballmanager.hecticus.com/futbolbrasil/v1/clients/leaderboard/total/' + getClientId()
                        + getSecurityToken('?');
                    },

                    phase: function (_competition, _phase) {
                        return brazil_football_manager_url+ 'futbolbrasil/'
                            + apiVersion + '/clients/leaderboard/get/'
                            + getClientId() + '/' + _competition + '/' + _phase
                            + getGMT('?')
                            + getSecurityToken('&');
                    },

                    competition: function (_competition) {
                        return brazil_football_manager_url + 'futbolbrasil/'
                            + apiVersion + '/clients/leaderboard/global/get/'
                            + getClientId() + '/' + _competition
                            + getGMT('?')
                            + getSecurityToken('&');
                    },

                    personal : {

                        competition: function() {
                            return brazil_football_manager_url
                                + 'futbolbrasil/'
                                + apiVersion + '/clients/leaderboard/personal/tournament/'
                                + getClientId()
                                + getGMT('?')
                                + getSecurityToken('&');
                        },
                        phase: {
                            index : function(){
                                return brazil_football_manager_url
                                    + 'futbolbrasil/'
                                    + apiVersion + '/clients/leaderboard/personal/phase/'
                                    + getClientId()
                                    + getGMT('?')
                                    + getSecurityToken('&');
                            },
                            latest: function(idCompetition, date){
                                return football_manager_url + 'footballapi/'
                                    + apiVersion + '/competitions/phases/latest/'
                                    + appId + '/' + idCompetition + '/' + date + '/' + getLang()
                                    + getGMT('?') + getSecurityToken('&');
                            }
                        }
                    }
                }

            };
        }
    ]);
