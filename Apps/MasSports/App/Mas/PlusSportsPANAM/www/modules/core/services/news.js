'use strict';

/**
 * @ngdoc service
 * @name core.Services.News
 * @description News Factory
 */
angular
    .module('core')
    .factory('News',['$localStorage','Moment',
        function($localStorage, Moment) {
            var FILE_KEY_GUEST_MODE = 'APPDATAGUEST';

            var guestData = {
                news: {
                    count: 0,
                    history: [],
                    maxNews: 5,
                    timestamp: ''
                }
            };

            var loadGuestData = function(){
                if($localStorage[FILE_KEY_GUEST_MODE]){
                    guestData = JSON.parse($localStorage[FILE_KEY_GUEST_MODE]);
                } else {
                    saveGuestData();
                }
            };

            var saveGuestData = function(){
//                console.log('persisting Guest Data: ');
//                console.log(guestData);
                $localStorage[FILE_KEY_GUEST_MODE] = JSON.stringify(guestData);
            };

            var setMaxNews = function(max){
                var date = Moment.today('YYYYMMDD');
//                console.log('timeStamp: ' + guestData.news.timestamp + ' date: ' + date);
                if(guestData.news.timestamp !== date){
                    console.log('Resetting Guest News Count');
                    guestData.news.count = 0;
                    guestData.news.history = [];
                }

                guestData.news.maxNews = parseInt(max, 10);
                guestData.news.timestamp = date;
                saveGuestData();
            };

            var markVisitedNews = function(news){
                guestData.news.history.push(news.idNews);
                saveGuestData();
            };

            var canViewNews = function(news){
                var data = guestData.news;
                if(data.history.indexOf(news.idNews) < 0){
                    if(data.count < data.maxNews){
                        ++data.count;
                        markVisitedNews(news);
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            };

            var init = function(){
                loadGuestData();
            }();

            return {

                init: init,

                /**
                 * @ngdoc function
                 * @name core.Services.News#setMaxNews
                 * @description Sets the max number of News a Guest Client can open in a day
                 * @params
                 * @methodOf core.Services.News
                 */
                setMaxNews: setMaxNews,
                canViewNews: canViewNews
            };
        }
    ]);
