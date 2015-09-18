'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.NewsCtrl
 * @description NewsCtrl
 * @requires ng.$scope
 */
angular
    .module('core')
    .controller('NewsCtrl', ['$http','$rootScope','$scope','$state', '$stateParams', '$localStorage', '$window', '$translate', 'Domain'
        ,'Moment', 'iScroll', 'SocialAppsManager', 'News', 'CordovaDevice', 'Notification',
        function($http, $rootScope, $scope, $state, $stateParams, $localStorage, $window, $translate, Domain, Moment,
                 iScroll, SocialAppsManager, News, CordovaDevice, Notification) {


            //Indicador de primera y ultima posicion en cache
            var _news = {
                first : 0,
                last : 0
            };

            var strings = {};

            var listScroll = null;
            var detailScroll = null;
            $rootScope.isPageContentLeft = false;
            $rootScope.$storage.news = false;
            $scope.hasNews = true;
            $scope.news = [];
            //$rootScope.share = share;
            $scope.fromNow = fromNow;
            $scope.showContentNews = showContentNews;
            $scope.contentNewsImg = '';


            /*---------------- Scope Functions ----------------*/
            $scope.share = function(_news) {
              /*SocialAppsManager.share({
                  'message' : _news.summary
                  ,'subject' : _news.title
                  ,'link' : 'http://timfutebol.hecticus.com/#!/news/' + _news.idNews
                  ,'image' : 'data:image/png;base64,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'
              });*/
              
              //console.log("NewsCtrl. Fbpost!!");
              var img = "http://c2c966812fafaea61977-4a4dad4d6fcba0b5d30b97c30da42673.r19.cf1.rackcdn.com/logo-nextelfutebol-200x200.png";
              SocialAppsManager.fbPost('http://nextelfutebol.hecticus.com/#!/news/' + _news.idNews, _news.title, img);
            }

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
                        $rootScope.transitionPageBack('#wrapper2', 'left');
                        $rootScope.isPageContentLeft = angular.element('#wrapper2').hasClass('left');
                        detailScroll.scrollTo(0, 0, 0);
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

            function getNewsPreviousToId(newsId){
                if ($http.pendingRequests.length == 0 && !$rootScope.loading) {
                    $scope.$emit('load');
                    return $http.get(Domain.news.up(newsId))
                        .then(function (data) {
                            data = data.data;
                            if (data.response.news.length >= 1) {
                                _news.first = data.response.news[0].idNews;
                                angular.forEach(data.response.news, function(_item) {
                                    var matches = $scope.news.filter(function(elem){
                                        return elem.idNews === _item.idNews;
                                    });
                                    if(matches.length == 0) {
                                        $scope.news.unshift(_item);
                                    }
                                });
                            }
                            $scope.$emit('unload');
                        }, function () {
                            Notification.showNetworkErrorAlert();
                            console.log('getNewsPreviousToId. Network Error.');
                            $scope.$emit('unload');
                        });
                }
            }

            function getNewsAfterId(newsId){
                if ($http.pendingRequests.length == 0 && !$rootScope.loading) {
                    $scope.$emit('load');
                    return $http.get(Domain.news.down(newsId))
                        .then(function (data) {
                            data = data.data;
                            if (data.response.news.length >= 1) {
                                _news.last = data.response.news[data.response.news.length-1].idNews;
                                angular.forEach(data.response.news, function(_item) {
                                    var matches = $scope.news.filter(function(elem){
                                        return elem.idNews === _item.idNews;
                                    });
                                    if(matches.length == 0) {
                                        $scope.news.push(_item);
                                    }
                                });
                            }
                            $scope.$emit('unload');
                        }, function () {
                            Notification.showNetworkErrorAlert();
                            console.log('getNewsAfterId. Network Error.');
                            $scope.$emit('unload');
                        });
                }
            }

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

            function setUpIScroll() {
                listScroll = iScroll.vertical('wrapper');
                listScroll.on('scroll', function () {
                    if (this.y >= 50) {
                        getNewsPreviousToId(_news.first);
                    }

                    if (this.y <= this.maxScrollY) {
                        getNewsAfterId(_news.last);
                    }
                });
                detailScroll = iScroll.vertical('wrapper2');

                $scope.$on('$destroy', function() {
                    listScroll.destroy();
                    listScroll = null;

                    detailScroll.destroy();
                    detailScroll = null;
                });
            }

            function init(){
               $scope.$emit('load');
               getTranslations();
                getNews().then(function(){
                    if($stateParams.newsId){
                        $scope.showContentNews(getNewsById($stateParams.newsId));
                    }
                });
                setUpIScroll();
            } init();

            $scope.$on('onRepeatLast', function(scope, element, attrs) {
              $scope.$emit('unload');
            });

        }
    ]);
