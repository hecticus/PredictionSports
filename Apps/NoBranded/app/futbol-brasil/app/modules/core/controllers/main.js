'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.MainCtrl
 * @description MainCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('MainCtrl', ['$rootScope', '$scope', '$state', '$localStorage', '$interval',
        '$timeout', '$window', '$translate', 'Client', 'CordovaApp','CordovaDevice',
        function($rootScope, $scope, $state, $localStorage, $interval, $timeout, $window, $translate,
               Client, CordovaApp, CordovaDevice) {

            $('body').flowtype({
                minimum : 320,
                maximum : 1200
            });
            //$('body').flowtype();
            $rootScope.refreshInterval = null;
            $rootScope.$storage = $localStorage;
            $rootScope.hasFavorites = false;
            $rootScope.isFavoritesFilterActive = isFavoritesFilterActive;
            $rootScope.showMenu = showMenu;
            $rootScope.hideMenu = hideMenu;
            $rootScope.onMenuButtonPressed = onMenuButtonPressed;
            $rootScope.showSection = showSection;
            $rootScope.executeAction = executeAction;
            $rootScope.transitionPage = transitionPage;
            $rootScope.transitionPageBack = transitionPageBack;
            $rootScope.nextPage = nextPage;
            $rootScope.prevPage = prevPage;
            $rootScope.clickPage = clickPage;
            $rootScope.isPageContentLeft = false;
            $rootScope.hideLoading = hideLoading;
            $rootScope.isBadAndroid = /Android /.test(window.navigator.appVersion) && !(/Chrome\/\d/.test(window.navigator.appVersion));
            $rootScope.isWebPlatform  = CordovaDevice.isWebPlatform;
            $rootScope.getDeviceId  = CordovaDevice.getDeviceId;

            $rootScope.getCompetitionShield = function (logo) {
              var ext = (($rootScope.isBadAndroid) ? '.png' : '.svg');
              return logo + ext;
            }

            $scope.toggles = {
                favorites: true
            };

            $scope.strings = {};
            $scope.updateInfo = {};
            $scope.displayInfo = {};
            $scope.getFavoritesClass = getFavoritesClass;
            $scope.toggleFavorites = toggleFavorites;
            $scope.isOnUtilitySection = CordovaApp.isOnUtilitySection;
            $scope.isOnUtility = CordovaApp.isOnUtility;
            $scope.getSection = getSection;
            $scope.isGuest = isGuest;
            $scope.isActiveClient = isActiveClient;
            $scope.getDrawerIcon = getDrawerIcon;
            $scope.goToStore = goToStore;


            init();

            ////////////// Root Scope //////////////////////////
            $rootScope.hideMenuIcon = hideMenuIcon;
            $rootScope.showMenuForward = showMenuForward;
            $rootScope.hasPreviousSubsection = hasPreviousSubsection;
            $rootScope.hideMenuFavorites = hideMenuFavorites;

            function hasPreviousSubsection(){
                return angular.element('.page.back.left:last').hasClass('left');
            }

            function hideMenuFavorites() {
              if ((getSection() === 'login')
                  || (getSection() === 'settings')
                  || (getSection() === 'remind')
                  || (getSection() === 'tutorial')
                  || (getSection() === 'language-selection')
                  || (getSection() === 'team-selection')
                  || (getSection() === 'dashboard')
                  || ($('.content-news #wrapper2').hasClass('left'))
                  || ($rootScope.hasFavorites === false)) {

                return true;
              } else {
                return false;
              }
            }

            function showMenuForward() {
              if ((getSection() === 'settings')
                && (!$rootScope.$storage.settings) ) {
                return true;
              } else {
                return false;
              }
            }

            function hideMenuIcon() {
              if (((getSection() === 'login') && !hasPreviousSubsection())
                  || (getSection() === 'tutorial')
                  || (getSection() === 'dashboard')
                  || ((getSection() === 'settings') &&
                      (!$rootScope.$storage.settings))) {
                return true;
              } else {
                return false;
              }
            }

            function isFavoritesFilterActive(){
                return $scope.toggles.favorites;
            }

            function showMenu() {
                if (!CordovaApp.isOnUtilitySection() && $('#wrapperM').hasClass('leftShort')) {
                    $window.addEventListener('touchmove', function(){
                        $window.removeEventListener('touchmove');
                    });
                    $rootScope.menuScroll.scrollTo(0,0,0);
                    $rootScope.transitionPage('#wrapperM', 'rightShort');
                } else {
                  $rootScope.menuScroll.scrollTo(0,0,0);
                  $rootScope.transitionPage('#wrapperM', 'rightShort');
                }
            }

            function hideMenu() {
                if ($('#wrapperM').hasClass('rightShort')) {
                    $rootScope.transitionPage('#wrapperM', 'leftShort');
                }
            }

            function onMenuButtonPressed(){

                $scope.$emit('load');
                $interval.cancel($rootScope.refreshInterval);
                $rootScope.refreshInterval = null;

                var menuWrapper = $('#wrapperM');
                var hasPreviousSubsection = angular.element('.page.back.left:last').hasClass('left');

                $interval.cancel($rootScope.refreshInterval);
                $rootScope.refreshInterval = undefined;


                //if(hasPreviousSubsection || CordovaApp.isOnUtilitySection()) {
                if(hasPreviousSubsection) {
                     angular.element('.page.back.left:last').attr('class', ' page transition right');
                     $rootScope.isPageContentLeft = false;
                     $scope.$emit('unload');
                } else if (CordovaApp.isOnUtilitySection()) {
                    CordovaApp.onBackButtonPressed();
                    $scope.$emit('unload');
                } else if (menuWrapper.hasClass('leftShort')) {
                    $scope.showMenu();
                } else if (menuWrapper.hasClass('rightShort')) {
                    $scope.hideMenu();
                    $scope.$emit('unload');
                } else {
                    $scope.showMenu();
                }

            }

            function showSection(_section) {
                if (_section == $state.current.name) {
                  if ($('#wrapperM').hasClass('rightShort')) {
                    $scope.$emit('unload');
                    $scope.hideMenu();
                  }
                } else {
                  $state.go(_section);
                }
            }

            function executeAction(action){
                switch(action){
                    case 'logout':
                        Client.logout();
                        $state.go('login');
                        break;
                    default:
                }
            }

            function transitionPage(_wrapper, _direction, _class) {
                if (!_class) _class = '';
                angular.element(_wrapper).attr('class', _class + ' page transition ' + _direction);
            }

            function transitionPageBack(_wrapper, _direction) {
                $rootScope.transitionPage(_wrapper, _direction, 'back')
            }

            function clickPage() {
              $scope.hideMenu();
              $scope.$emit('unload');
            }

            function nextPage() {
              $scope.hideMenu();
              $scope.$emit('unload');
            }

            function prevPage() {
              $scope.hideMenu();
              $scope.$emit('unload');
            }

            ////////////// Scope //////////////////////////

            function isGuest(){
                return Client.isGuest();
            }

            function isActiveClient(){
                return Client.isActiveClient();
            }

            function getFavoritesClass(){
                if($scope.toggles.favorites){
                    return 'mdi-action-favorite';
                } else {
                    return 'mdi-action-favorite-outline';
                }
            }

            function toggleFavorites(){
                $scope.toggles.favorites =! $scope.toggles.favorites;
                Client.enableFavoritesFilter($scope.toggles.favorites);
                $state.reload();
            }

            function getSection(){
                return CordovaApp.getCurrentSection();
            }

            function getDrawerIcon(){

                if(hasPreviousSubsection()
                    || CordovaApp.isOnUtilitySection()){
                    return 'icon mdi-navigation-arrow-back ';
                } else {
                    return 'icon mdi-navigation-menu';
                }
            }

            function goToStore(){
                if($window.cordova && $window.cordova.plugins && $window.cordova.plugins.market) {
                    $window.cordova.plugins.market.open($scope.updateInfo.download, {
                        success: function() {
                            console.log("Redirect to App Store Successful");
                        },
                        failure: function() {
                            console.log("Couldn't open App Store to update App");
                        }
                    });
                } else {
                    console.log('$window.cordova.plugins.market Object not available. Are you directly on a browser?');
                }
            }

            /**
             * Function that gets and updates the app's common usage Strings
             * to minimize the number of requests across modules and improve
             * performance
             */
            function getTranslations(){
                $translate('NOT_AVAILABLE').then(function(translation){
                    $scope.strings.NOT_AVAILABLE = translation;
                });
            }

            function init(){
                $scope.toggles.favorites = Client.isFavoritesFilterActive();
                $scope.$watch('Client.getHasFavorites()', function(){
                    $rootScope.hasFavorites = Client.getHasFavorites();
                });

                getTranslations();

                /*$rootScope.$on('$stateChangeStart',  function (event, toState, toParams, fromState, fromParams){
                  alert(CordovaDevice.phonegapIsOnline());
                });*/

                $rootScope.$on('$stateChangeSuccess', function(event, to, toParams, from, fromParams) {
                  if (from.data) {
                    if ($scope.isOnUtility(from.data.state) === false) {
                      $rootScope.previousState = from.data.state;
                    }
                  }
                });

                $rootScope.$on('$translateChangeSuccess', function () {
                    getTranslations();
                });

                $scope.$on('load', function(){
                  $scope.loading = true;
                  //$scope.error = false;
                });

                $scope.$on('unload', function(){
                  $rootScope.LOADING_TEXT = '';
                  $timeout(function(){
                      $scope.loading = false;
                  }, 200);
                });

                $scope.$on('error', function(){
                    $scope.error = true;
                    $scope.loading = false;
                 });

            }

            function hideLoading(){
                $timeout(function(){
                    $scope.loading = false;
                }, 200);
            }
        }
    ]);
