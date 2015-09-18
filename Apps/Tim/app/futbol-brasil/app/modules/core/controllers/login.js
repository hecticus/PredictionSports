'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.LoginCtrl
 * @description LoginCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('LoginCtrl', ['$rootScope', '$scope', '$localStorage', '$state', '$stateParams', '$translate', 'ClientManager', 'iScroll','Client',
        'Upstream', 'Notification', 'CordovaDevice', 'PushManager', 'flash',
        function($rootScope, $scope, $localStorage, $state, $stateParams, $translate, ClientManager, iScroll, Client, Upstream, Notification, CordovaDevice, PushManager, flash) {

            var scroll = null;
            var strings = {};
            $scope.flash = flash;

            $scope.msisdn = '';
            $scope.password = '';

            //$scope.isWeb = false;

            function getTranslations(){

              $translate(['ALERT.SET_USERNAME.TITLE',
                          'ALERT.SET_USERNAME.SUBTITLE',
                          'ALERT.SET_USERNAME.MSG',
                          'ALERT.GET_CREDENTIALS.TITLE',
                          'ALERT.GET_CREDENTIALS.SUBTITLE',
                          'ALERT.GET_CREDENTIALS.MSG',
                          'ALERT.GET_LOGIN.TITLE',
                          'ALERT.GET_LOGIN.SUBTITLE',
                          'ALERT.GET_LOGIN.MSG',
                          'ALERT.SET_MSISDN.TITLE',
                          'ALERT.SET_MSISDN.SUBTITLE',
                          'ALERT.SET_MSISDN.MSG',
                          'ALERT.SET_PASSWORD.TITLE',
                          'ALERT.SET_PASSWORD.SUBTITLE',
                          'ALERT.SET_PASSWORD.MSG',
                          'ALERT.LOGIN_ALREADY_SUBSCRIBED.TITLE',
                          'ALERT.LOGIN_ALREADY_SUBSCRIBED.SUBTITLE',
                          'ALERT.LOGIN_ALREADY_SUBSCRIBED.MSG',
                          'ALERT.LOGIN_USER_NOT_IDENTIFIED.TITLE',
                          'ALERT.LOGIN_USER_NOT_IDENTIFIED.SUBTITLE',
                          'ALERT.LOGIN_USER_NOT_IDENTIFIED.MSG',
                          'ALERT.LOGIN_USER_NOT_SUBSCRIBED.TITLE',
                          'ALERT.LOGIN_USER_NOT_SUBSCRIBED.SUBTITLE',
                          'ALERT.LOGIN_USER_NOT_SUBSCRIBED.MSG',
                          'ALERT.LOGIN_INVALID_MSISDN.TITLE',
                          'ALERT.LOGIN_INVALID_MSISDN.SUBTITLE',
                          'ALERT.LOGIN_INVALID_MSISDN.MSG',
                          'ALERT.LOGIN_INVALID_PASSWORD.TITLE',
                          'ALERT.LOGIN_INVALID_PASSWORD.SUBTITLE',
                          'ALERT.LOGIN_INVALID_PASSWORD.MSG',
                          'ALERT.LOGIN_GENERIC_ERROR.TITLE',
                          'ALERT.LOGIN_GENERIC_ERROR.SUBTITLE',
                          'ALERT.LOGIN_GENERIC_ERROR.MSG',
                          'LOGIN.REMIND.SUCCESS'])
              .then(function(translation){

                  strings['SET_USERNAME_TITLE'] = translation['ALERT.SET_USERNAME.TITLE'];
                  strings['SET_USERNAME_SUBTITLE'] = translation['ALERT.SET_USERNAME.SUBTITLE'];
                  strings['SET_USERNAME_MSG'] = translation['ALERT.SET_USERNAME.MSG'];

                  strings['GET_CREDENTIALS_TITLE'] = translation['ALERT.GET_CREDENTIALS.TITLE'];
                  strings['GET_CREDENTIALS_SUBTITLE'] = translation['ALERT.GET_CREDENTIALS.SUBTITLE'];
                  strings['GET_CREDENTIALS_MSG'] = translation['ALERT.GET_CREDENTIALS.MSG'];

                  strings['GET_LOGIN_TITLE'] = translation['ALERT.GET_LOGIN.TITLE'];
                  strings['GET_LOGIN_SUBTITLE'] = translation['ALERT.GET_LOGIN.SUBTITLE'];
                  strings['GET_LOGIN_MSG'] = translation['ALERT.GET_LOGIN.MSG'];

                  strings['SET_MSISDN_TITLE'] = translation['ALERT.SET_MSISDN.TITLE'];
                  strings['SET_MSISDN_SUBTITLE'] = translation['ALERT.SET_MSISDN.SUBTITLE'];
                  strings['SET_MSISDN_MSG'] = translation['ALERT.SET_MSISDN.MSG'];

                  strings['SET_PASSWORD_TITLE'] = translation['ALERT.SET_PASSWORD.TITLE'];
                  strings['SET_PASSWORD_SUBTITLE'] = translation['ALERT.SET_PASSWORD.SUBTITLE'];
                  strings['SET_PASSWORD_MSG'] = translation['ALERT.SET_PASSWORD.MSG'];

                  strings['LOGIN_ALREADY_SUBSCRIBED_TITLE'] = translation['ALERT.LOGIN_ALREADY_SUBSCRIBED.TITLE'];
                  strings['LOGIN_ALREADY_SUBSCRIBED_SUBTITLE'] = translation['ALERT.LOGIN_ALREADY_SUBSCRIBED.SUBTITLE'];
                  strings['LOGIN_ALREADY_SUBSCRIBED_MSG'] = translation['ALERT.LOGIN_ALREADY_SUBSCRIBED.MSG'];

                  strings['LOGIN_USER_NOT_IDENTIFIED_TITLE'] = translation['ALERT.LOGIN_USER_NOT_IDENTIFIED.TITLE'];
                  strings['LOGIN_USER_NOT_IDENTIFIED_SUBTITLE'] = translation['ALERT.LOGIN_USER_NOT_IDENTIFIED.SUBTITLE'];
                  strings['LOGIN_USER_NOT_IDENTIFIED_MSG'] = translation['ALERT.LOGIN_USER_NOT_IDENTIFIED.MSG'];

                  strings['LOGIN_USER_NOT_SUBSCRIBED_TITLE'] = translation['ALERT.LOGIN_USER_NOT_SUBSCRIBED.TITLE'];
                  strings['LOGIN_USER_NOT_SUBSCRIBED_SUBTITLE'] = translation['ALERT.LOGIN_USER_NOT_SUBSCRIBED.SUBTITLE'];
                  strings['LOGIN_USER_NOT_SUBSCRIBED_MSG'] = translation['ALERT.LOGIN_USER_NOT_SUBSCRIBED.MSG'];

                  strings['LOGIN_INVALID_MSISDN_TITLE'] = translation['ALERT.LOGIN_INVALID_MSISDN.TITLE'];
                  strings['LOGIN_INVALID_MSISDN_SUBTITLE'] = translation['ALERT.LOGIN_INVALID_MSISDN.SUBTITLE'];
                  strings['LOGIN_INVALID_MSISDN_MSG'] = translation['ALERT.LOGIN_INVALID_MSISDN.MSG'];

                  strings['LOGIN_INVALID_PASSWORD_TITLE'] = translation['ALERT.LOGIN_INVALID_PASSWORD.TITLE'];
                  strings['LOGIN_INVALID_PASSWORD_SUBTITLE'] = translation['ALERT.LOGIN_INVALID_PASSWORD.SUBTITLE'];
                  strings['LOGIN_INVALID_PASSWORD_MSG'] = translation['ALERT.LOGIN_INVALID_PASSWORD.MSG'];

                  strings['LOGIN_GENERIC_ERROR_TITLE'] = translation['ALERT.LOGIN_GENERIC_ERROR.TITLE'];
                  strings['LOGIN_GENERIC_ERROR_SUBTITLE'] = translation['ALERT.LOGIN_GENERIC_ERROR.SUBTITLE'];
                  strings['LOGIN_GENERIC_ERROR_MSG'] = translation['ALERT.LOGIN_GENERIC_ERROR.MSG'];
                  strings['LOGIN_REMIND_SUCCESS'] = translation['LOGIN.REMIND.SUCCESS'];

              });



            };


            function remindSuccess(){
                flash.setMessage(strings['LOGIN_REMIND_SUCCESS']);
                $state.go('login', {'msisdn': $scope.msisdn});
            }

            function remindError(){
                //TODO i18n-alizar

                Notification.showInfoAlert({
                    title: strings['GET_CREDENTIALS_TITLE'],
                    subtitle: strings['GET_CREDENTIALS_SUBTITLE'],
                    message: strings['GET_CREDENTIALS_MSG'],
                    type: 'error'
                });

                $scope.$emit('unload');
            }


            function loginSuccess(isNewClient){
                //console.log('onLoginSuccess. Login Success.');
                Upstream.loginEvent();
                if(isNewClient){
                    //TODO i18n-alizar
                    Notification.showInfoAlert({
                        title: strings['SET_USERNAME_TITLE'],
                        subtitle: strings['SET_USERNAME_SUBTITLE'],
                        message: strings['SET_USERNAME_MSG'],
                        type: 'success'
                    });
                    //console.log('new client. going to settings');
                    $state.go('settings',{newClient:true});
                } else {
                    //console.log('existing client. going to news');
                    $state.go('tutorial');
                    //$state.go('prediction');
                }
            }

            function loginError(errorData){
                if(errorData && errorData.upstream_code){
                   //console.log("errorcode:",errorData);
                    var errorKey = getUPSResponseCodeString(errorData.upstream_code);
                    Notification.showInfoAlert({
                        title:  strings[errorKey+'_TITLE'],
                        subtitle: strings[errorKey+'_SUBTITLE'],
                        message: strings[errorKey+'_MSG'],
                        type: 'error'
                    });
                } else {
                    Notification.showNetworkErrorAlert();
                }

                Client.markClientAsNotOK();

                $scope.$emit('unload');
            }

            $scope.sendMsisdn = function(){
                $scope.$emit('load');
                if($scope.msisdn){
                    //console.log('sendMsisdn. msisdn: ' + $scope.msisdn);
                    Upstream.clickedSubscriptionPromptEvent();
                    Client.setMsisdn($scope.msisdn,
                        function(){
                            ClientManager.createOrUpdateClient(
                                {'msisdn' : $scope.msisdn}
                                , true, remindSuccess, remindError);
                        },
                        function(){
                            //$scope.$emit('unload');
                            console.log('Error saving MSISDN');
                        }
                    );
                } else {
                    Notification.showInfoAlert({
                        title: strings['SET_MSISDN_TITLE'],
                        subtitle: strings['SET_MSISDN_SUBTITLE'],
                        message: strings['SET_MSISDN_MSG'],
                        type: 'warning'
                    });
                    $scope.$emit('unload');
                }
                //$scope.$emit('unload');
            };

            $scope.doMsisdnLogin = function(){
                $scope.$emit('load');
                if($scope.password && $scope.msisdn){
                    ClientManager.createOrUpdateClient(
                        {
                            'msisdn' : $scope.msisdn,
                            'password' : $scope.password
                        }
                        , true, loginSuccess, loginError);
                    Client.setNoGuest();
                } else {
                    if(!$scope.msisdn){
                        Notification.showInfoAlert({
                            title: strings['SET_MSISDN_TITLE'],
                            subtitle: strings['SET_MSISDN_SUBTITLE'],
                            message: strings['SET_MSISDN_MSG'],
                            type: 'warning'
                         });
                    } else {
                        Notification.showInfoAlert({
                            title: strings['SET_PASSWORD_TITLE'],
                            subtitle: strings['SET_PASSWORD_SUBTITLE'],
                            message: strings['SET_PASSWORD_MSG'],
                            type: 'warning'
                        });
                    }
                    $scope.$emit('unload');
                }
            };

            $scope.enterAsGuest = function(){
                $scope.$emit('load');
                ClientManager.createOrUpdateClient({}, true, loginSuccess, loginError);
                Client.setGuest();
                $scope.$emit('unload');
            };

            function setUpIScroll(){
                scroll = iScroll.vertical('wrapper');
                $scope.$on('$destroy', function() {
                    scroll.destroy();
                    scroll = null;
                });
            }

            function init(){
                getTranslations();
                $scope.$emit('unload');
                setUpIScroll();
                if($state.current.name === 'remind'){
                    console.log('Remind Upstream call');
                    Upstream.viewSubscriptionPromptEvent();
                }
                if($stateParams.msisdn){
                    $scope.msisdn = $stateParams.msisdn;
                }

                 if(!Client.getRegId()){
                    console.log("Se Reinicia el PushManager");
                    PushManager.init();
                 }

            } init();

            function getUPSResponseCodeString(code){
                switch(code){
                    case 1:
                        //User already subscribed
                        return 'LOGIN_ALREADY_SUBSCRIBED';
                    case 2:
                        //User cannot be identified
                        return 'LOGIN_USER_NOT_IDENTIFIED';
                    case 3:
                        //User not subscribed
                        return 'LOGIN_USER_NOT_SUBSCRIBED';
                    case 5:
                        //Invalid MSISDN
                        return 'LOGIN_INVALID_MSISDN';
                    case 401:
                        //Invalid password
                        return 'LOGIN_INVALID_PASSWORD';
                    default:
                        //Generic Error
                        return 'LOGIN_GENERIC_ERROR';
                }
            }
        }
    ]).factory('flash', function($rootScope) {
        var queue = [];
        var currentMessage = '';

        $rootScope.$on('$stateChangeSuccess', function() {
          currentMessage = queue.shift() || '';
        });

        return {
          setMessage: function(message) {
            queue.push(message);
          },
          getMessage: function() {
            return currentMessage;
          }
        };
    });
