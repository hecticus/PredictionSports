'use strict';

/**
 * @ngdoc service
 * @name core.Services.Notification
 * @description Notification Factory
 */
angular
    .module('core')
    .factory('Notification',['$rootScope', '$state','$translate','$timeout',
        function($rootScope, $state, $translate, $timeout) {

            var strings = {};

            function getTranslationsNetError(){
                $translate(['ALERT.NETWORK_ERROR.TITLE',
                            'ALERT.NETWORK_ERROR.SUBTITLE',
                            'ALERT.NETWORK_ERROR.MSG'])
                .then(function(translation){
                    strings['NETWORK_ERROR_TITLE'] = translation['ALERT.NETWORK_ERROR.TITLE'];
                    strings['NETWORK_ERROR_SUBTITLE'] = translation['ALERT.NETWORK_ERROR.SUBTITLE'];
                    strings['NETWORK_ERROR_MSG'] = translation['ALERT.NETWORK_ERROR.MSG'];
                });
            };


            function getTranslationsLocked(){
                $translate(['ALERT.LOCKED_SECTION.TITLE',
                            'ALERT.LOCKED_SECTION.MSG',
                            'ALERT.LOCKED_SECTION.CONFIRM',
                            'ALERT.LOCKED_SECTION.CANCEL'])
                .then(function(translation){
                    strings['LOCKED_SECTION_TITLE'] = translation['ALERT.LOCKED_SECTION.TITLE'];
                    strings['LOCKED_SECTION_MSG'] = translation['ALERT.LOCKED_SECTION.MSG'];
                    strings['LOCKED_SECTION_OK'] = translation['ALERT.LOCKED_SECTION.CONFIRM'];
                    strings['LOCKED_SECTION_CANCEL'] = translation['ALERT.LOCKED_SECTION.CANCEL'];
                });
            }

            function getTranslationsFacebook(){
                $translate(['ALERT.FACEBOOK.TITLE',
                            'ALERT.FACEBOOK.MSG',
                            'ALERT.FACEBOOK.CONFIRM',
                            'ALERT.FACEBOOK.CANCEL'])
                .then(function(translation){
                    strings['FACEBOOK_SECTION_TITLE'] = translation['ALERT.FACEBOOK.TITLE'];
                    strings['FACEBOOK_SECTION_MSG'] = translation['ALERT.FACEBOOK.MSG'];
                    strings['FACEBOOK_SECTION_OK'] = translation['ALERT.FACEBOOK.CONFIRM'];
                    strings['FACEBOOK_SECTION_CANCEL'] = translation['ALERT.FACEBOOK.CANCEL'];
                });
            }

            getTranslationsFacebook();
            getTranslationsNetError();
            getTranslationsLocked();

            function showNotificationDialog(data, confirmCallback, cancelCallback){
                var hasNotificationPlugin = !!navigator.notification;
                if (hasNotificationPlugin) {
                    navigator.notification.confirm(data.message
                        , function(btnIndex){
                            switch (btnIndex) {
                                case 1:
                                    typeof confirmCallback === 'function' && confirmCallback();
                                    break;
                                case 2:
                                    typeof cancelCallback === 'function' && cancelCallback();
                                    break;
                                default:
                            }
                        }
                        , data.title, [data.confirm, data.cancel]);
                } else {
                    var confirmFallback = confirm(data.message);
                    if (confirmFallback === true) {
                        typeof confirmCallback === 'function' && confirmCallback();
                    } else {
                        typeof cancelCallback === 'function' && cancelCallback();
                    }
                }
            }

            //TODO i18n-alizar
            function showLockedSectionDialog() {
                getTranslationsLocked();
                showNotificationDialog(
                    {
                        title : strings['LOCKED_SECTION_TITLE'],
                        message :  strings['LOCKED_SECTION_MSG'],
                        confirm: strings['LOCKED_SECTION_OK'],
                        cancel: strings['LOCKED_SECTION_CANCEL']
                    }, registerUserCallback
                );

                function registerUserCallback(){
                    $state.go('remind');
                }

            }


            //TODO i18n-alizar
            function showQuestionFacebookDialog() {
                getTranslationsFacebook();

                  $translate(['ALERT.FACEBOOK.TITLE',
                              'ALERT.FACEBOOK.MSG',
                              'ALERT.FACEBOOK.CONFIRM',
                              'ALERT.FACEBOOK.CANCEL'])
                  .then(function(translation){
                      showNotificationDialog(
                            {
                                title : translation['ALERT.FACEBOOK.TITLE'],
                                message :  translation['ALERT.FACEBOOK.MSG'],
                                confirm: translation['ALERT.FACEBOOK.CONFIRM'],
                                cancel: translation['ALERT.FACEBOOK.CANCEL']
                            }, settingsUserCallback
                        );
                  });



                function settingsUserCallback(){
                    $state.go('settings');
                }
            }


            function showLockedSectionNotEligible(){
                $translate(['ALERT.LOCKED_SECTION_USER_NOT_ELIGIBLE.TITLE',
                            'ALERT.LOCKED_SECTION_USER_NOT_ELIGIBLE.MSG',
                            'ALERT.LOCKED_SECTION_USER_NOT_ELIGIBLE.SUBTITLE'])
                .then(function(translation){
                    strings['LOCKED_SECTION_USER_NOT_ELIGIBLE_TITLE'] = translation['ALERT.LOCKED_SECTION_USER_NOT_ELIGIBLE.TITLE'];
                    strings['LOCKED_SECTION_USER_NOT_ELIGIBLE_MSG'] = translation['ALERT.LOCKED_SECTION_USER_NOT_ELIGIBLE.MSG'];
                    strings['LOCKED_SECTION_USER_NOT_ELIGIBLE_SUBTITLE'] = translation['ALERT.LOCKED_SECTION_USER_NOT_ELIGIBLE.SUBTITLE'];

                    showInfoAlert({
                        title : strings['LOCKED_SECTION_USER_NOT_ELIGIBLE_TITLE'],
                        subtitle :  strings['LOCKED_SECTION_USER_NOT_ELIGIBLE_SUBTITLE'],
                        message : strings['LOCKED_SECTION_USER_NOT_ELIGIBLE_MSG'],
                        type: 'warning'
                    });
                });
            }

            function showInfoAlert(displayInfo){
                var icon = '';
                displayInfo.icon = '';
                switch(displayInfo.type){
                    case 'success':
                        icon = 'mdi-navigation-check text-success';
                        displayInfo.html = '<p class="text-success">' + displayInfo.subtitle + '</p>';
                        break;
                    case 'error':
                        icon = 'mdi-alert-error text-danger';
                        displayInfo.html = '<p class="text-danger">' + displayInfo.subtitle + '</p>';
                        break;
                    case 'warning':
                    default:
                        icon = 'mdi-alert-warning text-warning';
                        displayInfo.html = '<p class="text-warning">' + displayInfo.subtitle + '</p>';

                }

                displayInfo.icon = icon;
                displayInfo.html += '<p class="text-muted">' + displayInfo.message + '</p>';

                $rootScope.displayInfo = displayInfo;

                $('#info-modal').modal({
                    backdrop: true,
                    keyboard: false,
                    show: false})
                    .modal('show');
            }
            
            function showFBShareError(){
                $translate(['ALERT.FB_SHARE.TITLE',
                            'ALERT.FB_SHARE.SUBTITLE',
                            'ALERT.FB_SHARE.MSG'])
                .then(function(translation){
                    strings['FB_SHARE_TITLE'] = translation['ALERT.FB_SHARE.TITLE'];
                    strings['FB_SHARE_SUBTITLE'] = translation['ALERT.FB_SHARE.SUBTITLE'];
                    strings['FB_SHARE_MSG'] = translation['ALERT.FB_SHARE.MSG'];
                    
                    showInfoAlert({
                        title : strings['FB_SHARE_TITLE'],
                        subtitle :  strings['FB_SHARE_SUBTITLE'],
                        message : strings['FB_SHARE_MSG'],
                        type: 'error'
                    });
                });
                
                /*var displayInfo = {
                        title: 'FacebookManager. Not connected...',
                        subtitle: 'FacebookManager. Not connected...',
                        message: 'FacebookManager. Not connected...',
                        type: 'success'
                };
                
                displayInfo.icon = 'mdi-navigation-check text-success';
                displayInfo.html = '<p class="text-success">' + displayInfo.subtitle + '</p>';
                displayInfo.html += '<p class="text-muted">' + displayInfo.message + '</p>';
                $rootScope.displayInfo = displayInfo;
                
                $timeout( function() {
                    $('#info-modal').modal({
                        backdrop: true,
                        keyboard: false,
                        show: false})
                    .modal('show'); }, 500);*/
            }

            function showNetworkErrorAlert(){
                getTranslationsNetError();
                showInfoAlert({
                        title: strings['NETWORK_ERROR_TITLE'],
                        subtitle: strings['NETWORK_ERROR_SUBTITLE'],
                        message: strings['NETWORK_ERROR_MSG'],
                    type: 'error'
                });
            }

            return {

                /**
                 * @ngdoc function
                 * @name core.Services.Notification#method1
                 * @methodOf core.Services.Notification
                 * @return {boolean} Returns a boolean value
                 */
                showNotificationDialog: showNotificationDialog,

                /**
                 * @ngdoc function
                 * @name core.Services.Notification#showLockedSectionDialog
                 * @methodOf core.Services.Notification
                 * @return {boolean} Returns a boolean value
                 */
                showLockedSectionDialog: showLockedSectionDialog,
                showQuestionFacebookDialog: showQuestionFacebookDialog,
                /**
                 * @ngdoc function
                 * @name core.Services.Notification#showInfoAlert
                 * @description Function that displays an information dialog
                 * @param {object} displayInfo Information to display. must have keys:
                 * @param {string} displayInfo.title The name of the event.
                 * @param {string} displayInfo.subtitle The subtitle of the event.
                 * @param {string} displayInfo.message The message of the event.
                 * @param {string} displayInfo.type The type of information it is ('warning'/'error').
                 * @methodOf core.Services.Notification
                 */
                showInfoAlert : showInfoAlert,

                showNetworkErrorAlert : showNetworkErrorAlert,

                showLockedSectionNotEligible : showLockedSectionNotEligible,
                
                showFBShareError: showFBShareError
            };



        }
    ]);
