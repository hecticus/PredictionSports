'use strict';

/**
 * @ngdoc service
 * @name core.Services.CordovaApp
 * @description CordovaApp Factory
 */
angular
    .module('core')
    .factory('CordovaApp',['$rootScope', '$state', '$window', '$timeout', '$translate',
        'CordovaDevice', 'WebManager', 'ClientManager', 'PushManager', 'FacebookManager',
        'Settings', 'Competitions', 'App', 'Update', 'Upstream', 'hAnalytics', 'i18n', 'News',
        function($rootScope, $state, $window, $timeout, $translate, CordovaDevice, WebManager, ClientManager,
                 PushManager, FacebookManager, Settings, Competitions, App, Update, Upstream, hAnalytics, i18n, News) {

            var currentSection = '';
            var prevSection = '';
            var utilitySections = ['login','settings', 'terms', 'remind', 'language-selection', 'team-selection'];
            var settingsSubSections = ['language-selection', 'team-selection'];
            var blockedSections = ['match', 'standings', 'scorers', 'mtm', 'friends'];
            var settingsSections = ['settings', 'terms', 'tutorial','remind', 'language-selection', 'team-selection'];
            var onSettingsSection = false;

            var strings = {};

            //noinspection UnnecessaryLocalVariableJS
            var service = {

                /**
                 * @ngdoc function
                 * @name core.Services.CordovaApp#init
                 * @methodOf core.Services.CordovaApp
                 */
                init : init,

                setCurrentSection : function(sect){
                    currentSection = sect;
                },
                getCurrentSection : function(){
                    return currentSection;
                },

                setPreviousSection : function(sect){
                    prevSection = sect;
                },
                getPreviousSection : function(){
                    return prevSection;
                },

                errorStartApp : errorStartApp,

                getVersion: getVersion,

                isBlockedSection : isBlockedSection,

                isSettingsSubSection : isSettingsSubSection,

                isOnUtilitySection : isOnUtilitySection,
                isOnUtility : isOnUtility,
                requiresAuthSection : requiresAuthSection,

                setIsOnSettingsSection: setIsOnSettingsSection,

                onBackButtonPressed: onBackButtonPressed,

                showNotificationDialog: showNotificationDialog
            };

            function getVersion(){
                if(!!$window.wizUtils){
                    $window.wizUtils.getBundleVersion(function(result){
                        App.setBundleVersion(result);
                    });
                    $window.wizUtils.getBundleIdentifier(function(id){
                        App.setBundleId(id);
                    });
                }
            }

            function exitApp(){
                FacebookManager.clearIntervalFriendsLoader();
                Upstream.appCloseEvent();

                //Legacy
                if (!!navigator.app) {
                    navigator.app.exitApp();
                } else if (!!navigator.device) {
                    navigator.device.exitApp();
                }
            }

            function onBackButtonPressed(){
                var hasPreviousSubsection = angular.element('.page.back.left:last').hasClass('left');

                if ($('#wrapperM').hasClass('rightShort')) {
                     $rootScope.hideMenu();
                } else if(currentSection == 'login'){
                    exitApp();
                } else if(isOnUtilitySection()){


                    if(isSettingsSubSection(currentSection)){
                        $state.go($state.current.data.prev);
                        onSettingsSection = false;
                    } else {

                        if ($rootScope.previousState === undefined) {
                          $state.go('prediction');
                        } else {
                          $state.go($rootScope.previousState);
                        }


                    }

                } else if(hasPreviousSubsection){
                    angular.element('.page.back.left:last').attr('class', ' page transition right');
                    $rootScope.isPageContentLeft = false;

                    if (!angular.element('.page.back.left:last').hasClass('left'))
                        angular.element('#menuIcon').attr('class', 'icon mdi-navigation-menu');


                } else {
                      $translate(['APP.EXIT_APP_TITLE', 'APP.EXIT_APP_MSG', 'OK', 'CANCEL'])
                      .then(function(translation){

                          strings['EXIT_APP_TITLE'] = translation['APP.EXIT_APP_TITLE'];
                          strings['EXIT_APP_MSG'] = translation['APP.EXIT_APP_MSG'];
                          strings['OK'] = translation['OK'];
                          strings['CANCEL'] = translation['CANCEL'];

                          showNotificationDialog({
                              title: strings.EXIT_APP_TITLE,
                              message: strings.EXIT_APP_MSG,
                              confirm: strings.OK,
                              cancel: strings.CANCEL
                          },
                          function(){
                              exitApp();
                          },
                          function(){
                              console.log('Cancelled by User');
                          });


                      });
                }
            }

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

            function isOnUtilitySection(){
                return utilitySections.some(function(utilitySection){
                    return utilitySection === currentSection;
                });
            }

            function isOnUtility(section){
                return settingsSections.some(function(utilitySection){
                    return utilitySection === section;
                });
            }

            function isSettingsSubSection(section){
                return settingsSubSections.some(function(settingsSubSection){
                    return settingsSubSection === currentSection;
                });
            }

            function requiresAuthSection(section){
                return !(section === 'login' || section === 'remind' || section === 'terms' || section === 'tutorial');
            }

            function isBlockedSection(section){
                return blockedSections.some(function (blockedSection) {
                    return blockedSection === section;
                });
            }

            function setIsOnSettingsSection(val){
                onSettingsSection = val;
            }

            function bindEvents() {
                document.addEventListener('deviceready', onDeviceReady, false);

                if(CordovaDevice.isWebPlatform()){

                    initAllAppData();
                }

                document.addEventListener('touchmove', function (e) {
                    e.preventDefault();
                }, false);
            }

            function onDeviceReady() {
                receivedEvent('deviceready');
                initAllAppData();
            }

            function receivedEvent(id){
                if (id === 'deviceready') {
                    document.addEventListener('backbutton', function(e){
                        console.log('backbutton event');
                        onBackButtonPressed();
                    }, false);
                }
            }

            function startApp(data){
                console.log("startApp. Starting App: Client Active: " + data.is_active
                    + ". Client Status: " + data.status);
            }

            function startAppOffline(){
                console.log("startAppOffline. App Offline");
            }

            function errorStartApp(){
                console.log("errorStartApp. Error. Couldn't Start Application");
            }

            function getTranslations(){
                $translate(['APP.EXIT_APP_TITLE', 'APP.EXIT_APP_MSG', 'OK', 'CANCEL'])
                    .then(function(translation){
                        strings['EXIT_APP_TITLE'] = translation['APP.EXIT_APP_TITLE'];
                        strings['EXIT_APP_MSG'] = translation['APP.EXIT_APP_MSG'];
                        strings['OK'] = translation['OK'];
                        strings['CANCEL'] = translation['CANCEL'];
                    });
            }

            function initAllAppData() {

                getVersion();
                hAnalytics.init();


                if(CordovaDevice.phonegapIsOnline()){

                    if(!!$window.StatusBar){
                        if(CordovaDevice.isAndroidPlatform()){
                            console.log('initAllAppData. platform: Android');
                            $window.StatusBar.hide();
                        } else if(CordovaDevice.isIosPlatform()){
                            console.log('initAllAppData. platform: iOS');
                            $window.StatusBar.hide();
                        } else {
                            console.log('initAllAppData. platform: ' + CordovaDevice.getPlatform());
                        }
                    }

                    PushManager.init();
                    Settings.init();
                    Competitions.init();
                    getTranslations();
                    FacebookManager.init();



                    if(!CordovaDevice.isWebPlatform()){
                        Update.checkUpdate();
                    };

                      /*WebManager.loadServerConfigs().then(function(resolve){

                        var data = resolve.data.response;
                        App.setCompanyName(data.company_name);
                        App.setBuildVersion(data.build_version);
                        App.setServerVersion(data.server_version);
                        App.setUpdateInfo(data.version);

                        i18n.init(data.default_language);
                        News.setMaxNews(data.max_news);
                        Settings.init();
                        Competitions.init();
                        getTranslations();
                        Upstream.setUp(data).then(Upstream.appLaunchEvent);


                        if(!CordovaDevice.isWebPlatform()){
                            Update.checkUpdate();
                        }

                     }, function(reject){
                        console.log(reject)
                     });*/

                    /*WebManager.loadServerConfigs().then(
                        function(data){

                            //console.warn(JSON.stringify(data));

                            data = data.data.response;
                            App.setCompanyName(data.company_name);
                            App.setBuildVersion(data.build_version);
                            App.setServerVersion(data.server_version);
                            App.setUpdateInfo(data.version);

                            i18n.init(data.default_language);
                            News.setMaxNews(data.max_news);
                            Settings.init();
                            Competitions.init();
                            getTranslations();
                            Upstream.setUp(data).then(Upstream.appLaunchEvent);


                            if(!CordovaDevice.isWebPlatform()){
                                Update.checkUpdate();
                            }

                        }, function(){
                            console.log("loadServerConfigs errorCallback. Error retrieving serverConfigs");
                        }
                    );*/

                    ClientManager.init().then(startApp, errorStartApp);

                }else{
                    startAppOffline();
                }



            }

            function init() {
                bindEvents();
            }

            return service;
        }
    ]);
