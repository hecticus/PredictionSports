'use strict';

/**
 * @ngdoc service
 * @name core.Services.SocialAppsManager
 * @description SocialAppsManager Factory
 */
angular
    .module('core')
    .factory('SocialAppsManager', ['$rootScope', '$window', '$fb','$twt', 'CordovaDevice', 'Utilities', 'FacebookManager','Notification',
        function($rootScope, $window, $fb, $twt, CordovaDevice, Utilities, FacebookManager, Notification) {

            init();

            //noinspection UnnecessaryLocalVariableJS
            var service = {

                /**
                 * @ngdoc function
                 * @name core.Services.SocialAppsManager#share
                 * @description Share post depending on platform
                 * @param {object} info Content to Share
                 * @param {string} subject Shared content Title
                 * @param {string} message Shared content Message
                 * @param {string} image Shared content Picture URL
                 * @param {string} link Shared content URL
                 * @methodOf core.Services.SocialAppsManager
                 */
                share : share,
                fbPost: fbPost,
                showShareModal: showShareModal

            };

            function share(info){

                /*if(CordovaDevice.isWebPlatform()){
                    showShareModal(info);
                } else {
                    //console.log("MAjor version:",Utilities.getMajorVersion(CordovaDevice.getOsVersion()));
                    //console.log("Share info:",info);

                    if( CordovaDevice.isAndroidPlatform()){
                        delete info.image;
                    }

                    if ($window.plugins && $window.plugins.socialsharing) {
                        $window.plugins.socialsharing.share(info.message, info.subject, info.image, info.link);
                    } else {
                        console.log('$window.plugins.socialsharing Plugin not available. Are you directly on a browser?');
                    }
                }*/
            }

            function fbShare(info) {
                var post = {};
                if(!info){ info = $rootScope.share;}
                if(info.subject) { post.name = info.subject; }
                if(info.message) { post.description = info.message; }
                if(info.subject) { post.caption = info.subject; }
                if(info.link) { post.link = info.link; }
                if(info.image) { post.picture = info.image; }

                $fb.feed(post);
            }
            
            function fbPost(link, caption, picture) {              
                FacebookManager.post(link, caption, picture);
            }

            function twitterShare(info) {
                var tweet = {};
                if(!info){ info = $rootScope.share;}
                if(info.subject && info.message){ tweet.text = info.subject + ' - ' + info.message; }
                if(info.link) { tweet.url = info.link; }
                if(info.hashtags) { tweet.hashtags = info.hashtags; }

                $twt.intent('tweet', tweet);
            }

            function showShareModal(info){
                $rootScope.share = info;
                angular.element('#share-modal').modal({
                    backdrop: true,
                    keyboard: false,
                    show: false
                }).modal('show');
            }

            function init(){
                $rootScope.share = {};
                $rootScope.fbShare = fbShare;
                $rootScope.twitterShare = twitterShare;
            }

            return service;
        }
    ]);
