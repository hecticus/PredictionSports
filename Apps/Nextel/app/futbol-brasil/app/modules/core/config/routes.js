'use strict';

/**
 * @ngdoc object
 * @name core.config
 * @requires ng.$stateProvider
 * @requires ng.$urlRouterProvider
 * @description Defines the routes and other config within the core module
 */
angular
    .module('core')
    .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/prediction');

        /**
         * @ngdoc event
         * @name core.config.route
         * @eventOf core.config
         * @description
         *
         * Define routes and the associated paths
         *
         * - When the path is `'/'`, route to home
         * */
        /**
       * @ngdoc event
       * @name core.config.route
       * @eventOf core.config
       * @description
       *
       * Define routes and the associated paths
       *
       * - When the state is `'dashboard'`, route to dashboard
       *
      */
      $stateProvider
            .state('dashboard', {
                url: '/dashboard',
                templateUrl: 'modules/core/views/dashboard.html',
                controller: 'DashboardController',
                data:{
                    prev: 'prediction',
                    next: 'prediction',
                    contentClass: 'content-dashboard',
                    section: 'dashboard',
                    state: 'dashboard'
                }
            }).
            state('login', {
                url: '/login',
                templateUrl:'modules/core/views/login.html',
                controller:'LoginCtrl as login',
                data:{
                    prev: 'login',
                    next: 'login',
                    contentClass: 'content-login',
                    section: 'login',
                    state: 'login'
                }
            })
            .state('remind', {
                url: '/remind',
                templateUrl:'modules/core/views/remind.html',
                controller:'LoginCtrl as remind',
                data:{
                    prev: 'login',
                    next: 'login',
                    contentClass: 'content-login',
                    section: 'remind',
                    state: 'remind'
                }
            })
            .state('settings', {
                url: '/settings/{newClient}',
                templateUrl:'modules/core/views/settings.html',
                controller:'SettingsController',
                data:{
                    prev: 'prediction',
                    next: 'prediction',
                    contentClass: 'content-settings',
                    section: 'settings',
                    state: 'settings'
                }
            })
            .state('team-selection', {
                url: '/team-selection/{newClient}',
                templateUrl:'modules/core/views/team-selection.html',
                controller:'TeamSelectionController',
                data:{
                    prev: 'settings',
                    next: 'news',
                    contentClass: 'content-team-selection',
                    section: 'team-selection',
                    state: 'team-selection'
                }
            })
            .state('language-selection', {
                url: '/language-selection/{newClient}',
                templateUrl:'modules/core/views/language-selection.html',
                controller:'LanguageSelectionController',
                data:{
                    prev: 'settings',
                    next: 'news',
                    contentClass: 'content-language-selection',
                    section: 'language-selection',
                    state: 'language-selection'
                }
            })
            .state('match', {
                url: '/match',
                controller:'MatchCtrl as _this',
                templateUrl:'modules/core/views/match.html',
                data:{
                    prev: 'mtm',
                    next: 'standings',
                    contentClass: 'content-match',
                    section: 'notistats',
                    state: 'match'
                }
            })
            .state('standings', {
                url: '/standings',
                controller:'StandingsCtrl as _this',
                templateUrl:'modules/core/views/standings.html',
                data:{
                    prev: 'match',
                    next: 'news',
                    contentClass: 'content-standings',
                    section: 'notistats',
                    state: 'standings'
                }
            })
            .state('news', {
                url: '/news/{newsId:int}',
                params : {
                  newsId : {value: null, squash: true}
                },
                controller:'NewsCtrl  as _this',
                templateUrl:'modules/core/views/news.html',
                data:{
                    prev: 'standings',
                    next: 'scorers',
                    contentClass: 'content-news',
                    section: 'notistats',
                    state: 'news'
                }

            })
            .state('scorers', {
                url: '/scorers',
                controller:'ScorersCtrl  as _this',
                templateUrl:'modules/core/views/scorers.html',
                data:{
                    prev: 'news',
                    next: 'mtm',
                    contentClass: 'content-scorers',
                    section: 'notistats',
                    state: 'scorers'
                }
            })
            .state('mtm', {
                url: '/mtm/{matchId:int}',
                params : {
                  matchId : {value: null, squash: true}
                },
                controller:'MtmCtrl',
                templateUrl:'modules/core/views/mtm.html',
                data:{
                    prev: 'scorers',
                    next: 'match',
                    contentClass: 'content-mtm',
                    section: 'notistats',
                    state: 'mtm'
                }
            })
            .state('prediction', {
                url: '/prediction',
                controller:'PredictionCtrl  as _this',
                templateUrl:'modules/core/views/prediction.html',
                data:{
                    prev: 'points',
                    next: 'leaderboard',
                    contentClass: 'content-prediction',
                    section: 'bets',
                    state: 'prediction'
                }
            })
            .state('leaderboard', {
                url: '/leaderboard',
                controller:'LeaderboardCtrl  as _this',
                templateUrl:'modules/core/views/leaderboard.html',
                data:{
                    prev: 'prediction',
                    next: 'friends',
                    contentClass: 'content-leaderboard',
                    section: 'bets',
                    state: 'leaderboard'
                }
            })
            .state('friends', {
                url: '/friends',
                controller:'LeaderboardCtrl  as _this',
                templateUrl:'modules/core/views/leaderboard.html',
                data:{
                    prev: 'leaderboard',
                    next: 'points',
                    contentClass: 'content-friends',
                    section: 'bets',
                    state: 'friends'
                }
            })
            .state('points', {
                url: '/points',
                controller:'PointsCtrl  as _this',
                templateUrl:'modules/core/views/points.html',
                data:{
                    prev: 'friends',
                    next: 'prediction',
                    contentClass: 'content-points',
                    section: 'bets',
                    state: 'points'
                }
            })
            .state('terms', {
                url: '/terms',
                controller:'TermsController as terms',
                templateUrl:'modules/core/views/terms.html',
                data:{
                    prev: 'login',
                    next: 'login',
                    contentClass: 'content-terms',
                    section: 'terms',
                    state: 'terms'
                }
            })

            .state('how', {
              url: '/how',
              controller:'HowDoesItWorkController as how',
              templateUrl:'modules/core/views/how-does-it-work.html',
              data:{
                  prev: 'settings',
                  next: 'prediction',
                  contentClass: 'content-how',
                  section: 'how',
                  state: 'how'
              }
            })

            .state('tutorial', {
                url: '/tutorial',
                controller:'TutorialController as tutorial',
                templateUrl:'modules/core/views/tutorial.html',
                data:{
                    prev: 'login',
                    next: 'login',
                    contentClass: 'content-tutorial',
                    section: 'tutorial',
                    state: 'tutorial'
                }
            });
    }
    ]);
