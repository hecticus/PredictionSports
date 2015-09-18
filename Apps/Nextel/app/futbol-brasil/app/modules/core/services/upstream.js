'use strict';

/**
 * @ngdoc service
 * @name core.Services.Upstream
 * @description Upstream Factory
 */
angular
    .module('core')
    .factory('Upstream',['$http', '$q', 'Client', 'Moment', 'CordovaDevice', 'App', 'Domain',
        function($http, $q, Client, Moment, CordovaDevice, App, Domain) {

            var AUTH_TOKEN_PREFIX = 'Basic ';
            var TIMESTAMP_FORMAT = 'DD/MM/YY HH:mm:ss.SSS[UTC]';
            var EVENT_SUFFIX = '/game/user/event';
            var PASSWORD_SUFFIX = '/game/user/password';

            var appKey = '';
            var appVersion = '';
            var guestPassword = '';
            var guestUser = '';
            var guestToken = '';
            var serviceId = '';
            var url = '';
            var eventUrl = '';
            var passwordUrl = '';
            var guestUserId = '';
            var headers = {};

            var events = {
                'app_launch' : 'APP_LAUNCH',
                'app_close' : 'APP_CLOSE',
                'login' : 'LOGIN',
                'viewed_subscription' : 'VIEW_SP',
                'clicked_subscription' : 'CLICK_SP'
            };

            var service = {

                /**
                 * @ngdoc function
                 * @name core.Services.Upstream#setUp
                 * @methodOf core.Services.Upstream
                 * @description Sets Up Upstream configuration from server. Example content:
                 * <ul>
                 *   <li>upstreamAppKey: "DEcxvzx98533fdsagdsfiou"</li>
                 *   <li>upstreamAppVersion: "gamingapi.v1"</li>
                 *   <li>upstreamGuestPassword: "guesth"</li>
                 *   <li>upstreamGuestUser: "guesth"</li>
                 *   <li>upstreamServiceID: "prototype-app -SubscriptionDefault"</li>
                 *   <li>upstreamURL: "http://brazil.footballmanager.hecticus.com/futbolbrasil/v1/clients/upstream"</li>
                 *   <li>upstreamUserID: "100"</li>
                 * </ul>
                 */
                setUp : setUp,

                /**
                 * @ngdoc function
                 * @name core.Services.Upstream#appLaunchEvent
                 * @description Reports App Launch Event to Upstream
                 * @methodOf core.Services.Upstream
                 */
                appLaunchEvent : appLaunchEvent,

                /**
                 * @ngdoc function
                 * @name core.Services.Upstream#appCloseEvent
                 * @description Reports App Close Event to Upstream
                 * @methodOf core.Services.Upstream
                 */
                appCloseEvent : appCloseEvent,

                /**
                 * @ngdoc function
                 * @name core.Services.Upstream#loginEvent
                 * @description Reports Login Event to Upstream
                 * @methodOf core.Services.Upstream
                 */
                loginEvent : loginEvent,

                /**
                 * @ngdoc function
                 * @name core.Services.Upstream#viewSubscriptionPromptEvent
                 * @description Reports Subscription Prompt Viewed Event to Upstream
                 * @methodOf core.Services.Upstream
                 */
                viewSubscriptionPromptEvent : viewSubscriptionPromptEvent,

                /**
                 * @ngdoc function
                 * @name core.Services.Upstream#clickedSubscriptionPromptEvent
                 * @description Reports Subscription Prompt Clicked Event to Upstream
                 * @methodOf core.Services.Upstream
                 */
                clickedSubscriptionPromptEvent : clickedSubscriptionPromptEvent
            };

            function setUp(config){
                var deferred = $q.defer();
                appKey = config.upstream_app_key;
                appVersion = config.upstream_app_version;

                guestPassword = config.upstream_guest_password;
                guestUser = config.upstream_guest_user;
                guestUserId = config.upstream_guest_user_id;
                guestToken = config.upstream_guest_auth_token;

                serviceId = config.upstream_service_id;
                url = config.upstream_url;
                eventUrl = url + EVENT_SUFFIX;
                passwordUrl = url + PASSWORD_SUFFIX;
                updateHeaders();
                deferred.resolve();
                return deferred.promise;
            }

            function getAppResponseCodeString(code){
                switch(code){
                    case 0:
                        return 'Success';
                    case 1:
                        return 'User already subscribed';
                    case 2:
                        return 'User cannot be identified';
                    case 3:
                        return 'User not subscribed';
                    case 4:
                        return 'Push_notification_id missing';
                    case 5:
                        return 'Invalid MSISDN';
                    case 6:
                        return 'Push_notification_id already exists';
                    case 7:
                        return 'Upstream’s Service no longer available';
                    default:
                        return 'Unhandled Application Response Code';
                }
            }

            function updateHeaders(){
                headers = {
                    'Accept': 'application/'+ appVersion + '+json',
                    'x-gameapi-app-key' : appKey
                };
            }

            function getBody(event, extras){
                var metadata = extras? extras : {};
                metadata.channel = CordovaDevice.getUpstreamChannel();
                metadata.app_version = App.getBundleVersion();
                /*var body = {
                    'event' : event,
                    'service_id' : serviceId,
                    'timestamp' : Moment.today(TIMESTAMP_FORMAT),
                    'device_id' : Client.getRegId(),
                    'push_notification_id' : Client.getRegId(),
                    'metadata' : metadata
                };*/

                var body = {
                    'event_type' : event,
                    'metadata' : metadata
                };

                if(Client.isGuest()){
                    body['user_id'] = guestUserId;
                } else{
                    body['user_id'] = Client.getClientId();
                }

                //console.log("BODY -> " + JSON.stringify(body));
                return body;
            }

            function sendEvent(event){

                var data = getBody(event);
                var token = Client.getUpstreamAuthToken();
                var clientId = 0;

                if(token){
                    headers['Authorization'] = AUTH_TOKEN_PREFIX + token;
                } else {
                    headers['Authorization'] = AUTH_TOKEN_PREFIX + guestToken;
                }
                var config = {
                    headers: headers
                };

                function success(data){
                    return getAppResponseCodeString(data.result);
                }

                function error(data){
                    //console.log('Error sending ' + events.app_launch + ' Event. Result: ' + getAppResponseCodeString(data.result));
                    return 'error';
                }

                //return $http.post(eventUrl, data, config).then(success, error)


                /*console.log('<upstream>');
                  console.log('config -> ' + JSON.stringify(config));
                  console.log('sendEvent -> ' + JSON.stringify(data));
                console.log('</upstream>');*/

                if (Domain.upstream()) {
                  return $http.post(Domain.upstream(), data, config).then(success, error);
                }

                return false;
            }

            function appLaunchEvent (){
                //console.log('Upstream. appLaunchEvent');
                return sendEvent(events.app_launch);
            }

            function appCloseEvent(){
                //console.log('Upstream. appCloseEvent');
                return sendEvent(events.app_close);
            }

            function loginEvent(){
                //console.log('Upstream. loginEvent');
                return sendEvent(events.login);
            }

            function viewSubscriptionPromptEvent(){
                //console.log('Upstream. viewSubscriptionPromptEvent');
                return sendEvent(events.viewed_subscription);
            }

            function clickedSubscriptionPromptEvent(){
                //console.log('Upstream. clickedSubscriptionPromptEvent');
                return sendEvent(events.clicked_subscription);
            }

            return service;
        }
    ]);
