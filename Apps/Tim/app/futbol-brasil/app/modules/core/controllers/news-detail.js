'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.NewsDetailCtrl
 * @description NewsDetailCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('NewsDetailCtrl', ['$http','$rootScope','$scope','$state', '$stateParams', '$localStorage', '$window', '$translate', 'Domain'
        ,'Moment', 'iScroll', 'SocialAppsManager', 'News', 'CordovaDevice', 'Notification',
        function($http, $rootScope, $scope, $state, $stateParams, $localStorage, $window, $translate, Domain, Moment,
                 iScroll, SocialAppsManager, News, CordovaDevice, Notification) {


            //Indicador de primera y ultima posicion en cache
            var _news = {
                first : 0,
                last : 0
            };

            var strings = {};


            $rootScope.isPageContentLeft = false;
            $rootScope.$storage.news = false;
            $scope.hasNews = true;
            $scope.news = [];
            //$rootScope.share = share;
            $scope.fromNow = fromNow;
            $scope.showContentNews = showContentNews;

            $scope.contentNewsImg = '';




            function fromNow(_date) {
                return Moment.date(_date).fromNow();
            }

            function getTranslations(){
                $translate(['ALERT.NEWS_LIMIT.TITLE', 'ALERT.NEWS_LIMIT.MSG', 'ALERT.NEWS_LIMIT.CONFIRM', 'ALERT.NEWS_LIMIT.CANCEL'])
                    .then(function(translation){
                        strings['NEWS_LIMIT_TITLE'] = translation['ALERT.NEWS_LIMIT.TITLE'];
                        strings['NEWS_LIMIT_MSG'] = translation['ALERT.NEWS_LIMIT.MSG'];
                        strings['NEWS_LIMIT_CONFIRM'] = translation['ALERT.NEWS_LIMIT.CONFIRM'];
                        strings['NEWS_LIMIT_CANCEL'] = translation['ALERT.NEWS_LIMIT.CANCEL'];
                    });
            }




            function showContentNews(_news) {


                $scope.contentNewsImg = null;

                if(_news) {
                    if ((!$scope.isGuest() && $scope.isActiveClient()) ||
                        (($scope.isGuest() || !$scope.isActiveClient()) && News.canViewNews(_news))) {
                        $scope.contentNews = $scope.news[$scope.news.indexOf(_news)];
                        $scope.contentNews.body = $scope.contentNews.body.replace(/\n/g, '<br/><br/>');
                        $scope.contentNewsImg = null;
                        if (_news.resources != undefined) $scope.contentNewsImg = _news.resources.mid[0];

                    } else {
                        Notification.showNotificationDialog(
                            {
                                title: strings.NEWS_LIMIT_TITLE,
                                message: strings.NEWS_LIMIT_MSG,
                                confirm: strings.NEWS_LIMIT_CONFIRM,
                                cancel: strings.NEWS_LIMIT_CANCEL
                            }
                        );
                        console.log('Daily News Limit Exceeded');
                    }
                } else {
                    console.log('Not a valid news object');
                }
            }

            /*---------------- Internal Functions ----------------*/



            function getNews() {
                if ($rootScope.$storage.news) {
                    $rootScope.error = !$rootScope.$storage.hasOwnProperty('news');
                    $scope.news = JSON.parse($rootScope.$storage.news);
                    _news.first = $scope.news[0].idNews;
                    _news.last  = $scope.news[$scope.news.length-1].idNews;
                }
                return $http.get(Domain.news.index()).then(
                    function (data) {
                        data = data.data;
                        if(data.response.total > 0){
                            $scope.hasNews = true;
                            $scope.news = data.response.news;
                            _news.first = $scope.news[0].idNews;
                            _news.last  = $scope.news[$scope.news.length-1].idNews;
                            $rootScope.$storage.news = JSON.stringify($scope.news);
                        } else {
                            $scope.hasNews = false;
                            console.log('No News Available.');
                        }
                        $scope.$emit('unload');
                    }, function () {
                        $scope.hasNews = false;
                        Notification.showNetworkErrorAlert();
                        console.log('getNews. Network Error.');
                        $scope.$emit('unload');
                    });
            }

            function getNewsById(id){
                return $scope.news.filter(function(news){
                    return news.idNews === id;
                })[0];
            }


            function init(){

               $scope.$emit('load');
               getTranslations();
                getNews().then(function(){
                    if($stateParams.newsId){
                        $scope.showContentNews(getNewsById($stateParams.newsId));
                    }
                });



            } init();

            $scope.$on('onRepeatLast', function(scope, element, attrs) {
              $scope.$emit('unload');
            });

        }
    ]);
