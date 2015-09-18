'use strict';

/**
 * @ngdoc service
 * @name core.Services.FacebookManager
 * @description FacebookManager Factory
 */
angular
    .module('core')
    .factory('FacebookManager',['$localStorage', '$http', 'Client','Notification','$rootScope','ezfb', 'CordovaDevice',
        function($localStorage, $http, Client, Notification, $rootScope, ezfb, CordovaDevice) {
            var INTERVAL_FRIENDS_LOADER_TIMER = 30000;
            var FILE_KEY_FB_USER_ID = "APPDATAFBUSERID";
            var FILE_KEY_FB_FRIENDS = "APPDATAFBFRIENDS";
            var fbUserId = '';
            var intervalFriendsLoaderId = '';
            var localStorage = $localStorage;
            var plugin = ezfb;
            var pluginType = 'ezfb';
            
            var saveUserId = function (_fbUserId) {
                try{
                    fbUserId = _fbUserId;
                    localStorage[FILE_KEY_FB_USER_ID] = fbUserId;
                    return true;
                }catch(err){
                    return false;
                }
            };

            var loadUserId = function () {
                fbUserId = localStorage[FILE_KEY_FB_USER_ID];
            };

            var deleteUserId = function () {
                fbUserId = null;
                delete localStorage[FILE_KEY_FB_USER_ID];
            };

            var getLocalFriends = function () {
                return JSON.parse(localStorage[FILE_KEY_FB_FRIENDS]);
            };

            var saveFriends = function (friends) {
                if(!!friends){
                    delete localStorage[FILE_KEY_FB_FRIENDS];
                    localStorage[FILE_KEY_FB_FRIENDS] = JSON.stringify(friends);
                    Client.setFriends(friends);
                    return true;
                } else {
                    console.log('FacebookManager. saveFriends. error. Invalid friends');
                    return false;
                }
            };

            var getMoreFriends = function(url, callback){
                $http.get(url, {timeout: 60000}).success(function(result, status) {
                    var more = result.paging.hasOwnProperty('next');
                    var friends = result.data;
                    if(friends.length > 0){
                        console.log('FacebookManager. getMoreFriends. Got more Friends. Persisting.');
                        friends = getLocalFriends().concat(friends);
                        saveFriends(friends);
                        if(more){
                            console.log('FacebookManager. getMoreFriends. Got still more Friends. Retrieving.');
                            getMoreFriends(callback);
                        } else {
                            console.log('FacebookManager. getMoreFriends. No more friends. Executing Callback');
                            typeof callback == 'function' && callback(friends);
                        }
                    } else {
                        console.log('FacebookManager. getMoreFriends. No more friends. Executing Callback');
                        typeof callback == 'function' && callback(getLocalFriends());
                    }
                });
            };
            
            var postToFeed = function(link, caption, picture){
               var options = {};
                options.method = "share";
                options.href = link;
                options.link = link;
                options.caption = caption;
                options.picture = picture;
                options.display = "page";
                
                console.log("postToFeed options:",options);
                //$('#screen-block').hide();
                
                if( pluginType === 'facebookConnect'){
                    plugin.showDialog(options, 
                        function(response){
                            console.log("FacebookManager. Success posting to FB");
                        }, 
                        function(response){
                            console.log("FacebookManager. Failure posting to FB:" + response);
                        }
                    );
                } else {
                    plugin.ui(options, function (res){
                        console.log("FacebookManager, ezfb. posting to FB response:", res);
                    });
                }             
            };

            return {

                getUserId : function(){
                    return fbUserId;
                },

                getIntervalFriendsLoader : function(){
                    return intervalFriendsLoaderId;
                },

                setIntervalFriendsLoader : function(){
                    var that = this;
                    intervalFriendsLoaderId = setInterval(
                        that.getStatus(), INTERVAL_FRIENDS_LOADER_TIMER
                    );
                },

                clearIntervalFriendsLoader : function(){
                    try {
                        clearInterval(intervalFriendsLoaderId);
                    } catch(e){}
                },

                /**
                 * @ngdoc function
                 * @name core.Services.FacebookManager#login
                 * @methodOf core.Services.FacebookManager
                 * @return {boolean} Returns a boolean value
                 */
                login : function (callback) {
                    var that = this;
                    
                    if(pluginType === 'facebookConnect'){
                        console.log('facebookConnect login');
                        plugin.login(
                            ["email", "user_friends", "public_profile", "user_friends"]
                            , function (response) {
                                console.log("FB login response:",JSON.stringify(response, undefined, 1));
                                processResponse(response);
                                callback(response);
                            }
                            , function (response) {
                                console.log("FacebookManager. login. Error: "
                                    + JSON.stringify(response, undefined, 1));
                                callback(response);
                            }
                        );
                    } else {
                        console.log('ezfb login');
                        plugin.login(function (response){
                            console.log('FB login response:', response);
                            processResponse(response);
                            callback(response);
                            
                        },  {scope: 'email,user_friends,public_profile'});
                    }
                    
                    function processResponse(response){
                        if(response.authResponse){                                
                            var authResponse = response.authResponse;
                            fbUserId = authResponse.userID;
                            saveUserId(fbUserId);
                            that.setIntervalFriendsLoader();
                            that.getFriends();                                
                        }
                    };
                },

                login2 : function (response) {
                    var that = this;
                    var authResponse = response.authResponse;
                    fbUserId = authResponse.userID;
                    saveUserId(fbUserId);
                    that.setIntervalFriendsLoader();
                    that.getFriends();
                },

                logout: function (successCallback, errorCallback) {
                    plugin.logout(successCallback, errorCallback);
                },


                /**
                 * @ngdoc function
                 * @name core.Services.FacebookManager#login
                 * @description Posible status values:
                 * - connected: The person is logged into Facebook, and has logged into your app.
                 * - not_authorized: The person is logged into Facebook, but has not logged into your app.
                 * - unknown: The person is not logged into Facebook, so
                 * you don't know if they've logged into your app.
                 * @methodOf core.Services.FacebookManager
                 * @return {boolean} Returns a boolean value
                */
                getStatus : function (callback){
                    console.log("FacebookManager. getStatus.");
                    plugin.getLoginStatus(
                        function (result) {
                            console.log("getLoginStatus:", result);
                            typeof callback == 'function' && callback(result);
                        },
                        function (error) {
                            deleteUserId();
                            typeof callback == 'function' && callback(null);
                        });
                },

                getFriends : function (callback) {
                    if(pluginType === 'facebookConnect'){
                        plugin.api('/me/friends?fields=picture,name'
                            , ["public_profile", "user_friends"],
                            function (result) {
                                processData(result);
                            },
                            function (error) {
                                console.log("FacebookManager. getFriends. Error: "
                                    + JSON.stringify(error, undefined, 1));
                                typeof callback == 'function' && callback(null);
                            }
                        );
                    } else {
                         plugin.api('/me/friends?fields=picture,name',processData);
                    }
                    
                    function processData(result){
                        console.log('FacebookManager.getFriends. result: ', result);
                        console.log(' Result.error:',result.error);
                        if(angular.isDefined(result.error)){
                            console.log(" FacebookManager. getFriends. Error");
                            typeof callback == 'function' && callback(null);
                            return;
                        }
                        var friends = result.data;
                        console.log(friends);
                        saveFriends(friends);
                        var more = result.paging.hasOwnProperty('next');
                        console.log('more: ' + more);
                        if(more == true){
                            console.log('more. true');
                            getMoreFriends(result.paging.next, callback);
                        } else if (more == false){
                            console.log('getFriends: more. false. calling callback: ' + (typeof callback == 'function'));
                            typeof callback == 'function' && callback(friends);
                        }
                    };
                },

                getLocalFriends : getLocalFriends,
                
                post: function(link, caption, picture) {                     
                    $('#screen-block').show();
                    this.getStatus(function(response){                        
                        if(response.status !== 'undefined' && response.status == 'connected'){
                            console.log("post getStatus response:", response);
                            postToFeed(link, caption, picture);
                        } else {                            
                            Notification.showQuestionFacebookDialog();                      
                            console.log("FacebookManager. Not connected...");
                        }
                        $('#screen-block').hide();                        
                    });              
                },
                
                init: function(){
                    console.log("Init FacebookManager...");
                    if(CordovaDevice.isWebPlatform()){
                        pluginType = 'ezfb';
                        plugin = ezfb;
                    } else {
                        pluginType = 'facebookConnect';
                        plugin = facebookConnectPlugin;
                    }              
                    console.log("Facebook Manager plugin type:", pluginType);
                    console.log("Facebook Manager plugin:", plugin);
                }

            }
        }
    ]);
