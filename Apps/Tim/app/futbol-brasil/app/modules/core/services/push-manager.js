'use strict';

/**
 * @ngdoc service
 * @name core.Services.PushManager
 * @description PushManager Factory
 */
angular
    .module('core')
    .factory('PushManager', ['$state', '$window',  'ClientManager', 'CordovaDevice',
        function($state, $window,  ClientManager, CordovaDevice) {
            var pushNotification = {};

            function tokenHandler(result) {
                ClientManager.updateRegistrationId(result);
            }

            function successPushHandler(result) {
                console.log('<li>success:'+ result +'</li>');
                if(result != null && result != ""){
                    ClientManager.updateRegistrationId(result);
                }
            }

            function errorPushHandler(error) {
                console.log('<li>error:'+ error +'</li>');
            }

            // handle GCM notifications for Android
            function onNotificationGCM(e) {
                console.log('onNotificationGCM.');
                switch( e.event ){
                    case 'registered':
                        if ( e.regid.length > 0 )
                        {
                            var regId = e.regid;
                            console.log('<li>REGISTERED -> REGID:' + regId + "</li>");
                            console.log("PushManager. regID = " + regId);
                            console.log('onNotificationGCM event: ');
                            console.log(e);
                            ClientManager.updateRegistrationId(regId);
                        }
                        break;

                    case 'message':

                        if (e.foreground) {
                        } else {
                            // otherwise we were launched because the user touched a notification
                            if (e.coldstart){
                                //console.log('<li>--COLDSTART NOTIFICATION--' + '</li>');
                            }else{
                                //console.log('<li>--BACKGROUND NOTIFICATION--' + '</li>');
                            }
                        }

                        if (e.payload["extra_params"].is_news) {
                          $state.go('news',{newsId:e.payload["extra_params"].id_news});
                        } else if (e.payload["extra_params"].id_game_match) {
                          $state.go('mtm',{matchId:e.payload["extra_params"].id_game_match});
                        }

                        break;

                    case 'error':
                        console.log('<li>ERROR -> MSG:' + e.msg + '</li>');
                        break;

                    default:
                        console.log(
                            '<li>EVENT -> Unknown, ' +
                                'an event was received and we do not know what it is</li>'
                        );
                        break;
                }
            }

            // handle APNS notifications for iOS
            function onNotificationAPN(e) {
                /*if (e.badge) {
                 pushNotification.setApplicationIconBadgeNumber(successPushHandler, e.badge);
                 }*/
                console.log("LLEGO INFO PUSH");
                console.log(JSON.stringify(e));
                if(e["extra_params"] != null && e["extra_params"] != ""){
                    //TODO executePushInit no esta implementado
                    //executePushInit(e["extra_params"]);
                }
            }

            function init(){
                try {

                    if($window.plugins){
                        pushNotification = $window.plugins.pushNotification;
                    }
                    if(CordovaDevice.isAndroidPlatform()) {
                        console.log('PushManager. isAndroid');
                        pushNotification.register(
                            function(result){
                                successPushHandler(result);
                            }, function(result){
                                errorPushHandler(result);
                            },
                            {
                                "senderID":"961052813400",
                                "ecb":"angular.element('body').injector().get('PushManager')" +
                                    ".onNotificationGCM"
                            }
                        );
                    } else if(CordovaDevice.isIosPlatform()){
                        console.log('PushManager. isIOS');
                        pushNotification.register(
                            function(){
                                tokenHandler();
                            }, function(){
                                errorPushHandler();
                            },
                            {
                                "badge":"false",
                                "sound":"true",
                                "alert":"true",
                                "ecb":"angular.element('body').injector().get('PushManager')" +
                                    ".onNotificationAPN"
                            }
                        );
                    } else if(CordovaDevice.isWebPlatform()){
                        ClientManager.updateRegistrationId("APA91bGUo-" +
                            "_CbLa7jbiwHDkUZkUjGHBuAcVMnuGLl-" +
                            "afFqmw_O2Gukymxf6UPPR-R8-EguAq4F4xD2Ls8Om-" +
                            "8gCU4xkK_ht55x-5YroQdprfAUkn0xG-" +
                            "G4QLj7FM4YsZEs668YF3dgZrK-" +
                            "K6TgzWJXL9eM7y2LcXQHHueiGeQWXdtolAhOgh1oQ");
                    }
                } catch(err) {
                    console.log("PushManager. init. Error. " + err);
                }
            }

            return {

                /**
                 * @ngdoc function
                 * @name core.Services.PushManager#init
                 * @description Initializes Push Registration for the Device
                 * @methodOf core.Services.PushManager
                 */
                init : init,

                onNotificationGCM : onNotificationGCM,

                onNotificationAPN: onNotificationAPN
            };
        }
    ]);
