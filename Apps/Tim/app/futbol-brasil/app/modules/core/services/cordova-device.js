'use strict';

/**
 * @ngdoc service
 * @name core.Services.CordovaDevice
 * @description CordovaDevice Factory
 */
angular
    .module('core')
    .factory('CordovaDevice',['$window',
        function($window) {
            var realWidth = 0;
            var realHeight = 0;
            var touchType = 'click';

            function setRealWidth(val){
                try{
                    if(val != null && val != "" && !isNaN(val)){
                        realWidth = parseInt(val);
                    }
                }catch(e){
                    console.log("Bad width: " + e);
                }
            }

            function getRealWidth(){
                if(realWidth != 0){
                    return realWidth;
                }else{
                    return $window.innerWidth;
                }
            }

            function setRealHeight(val){
                try{
                    if(val != null && val != "" && !isNaN(val)){
                        realHeight = parseInt(val);
                    }
                }catch(e){
                    console.log("Bad height: " + e);
                }
            }

            function getRealHeight(){
                if(realHeight != 0){
                    return realHeight;
                }else{
                    return $window.innerHeight;
                }
            }

            function getPlatform(){
                if(typeof device !== "undefined"){
                    return device.platform;
                } else {
                    return "Web";
                }
            }

            function isAndroidPlatform(){
                if(typeof device !== "undefined"){
                    return device.platform === "Android";
                } else {
                    return false;
                }
            }

            function isIosPlatform(){
                if(typeof device !== "undefined"){
                    return device.platform === "iOS";
                } else {
                    return false;
                }
            }

            function isWebPlatform(){
                return getPlatform() === 'Web';
            }

            function getUpstreamChannel(){
                if(isAndroidPlatform()){
                    return 'Android';
                } else if(isIosPlatform()){
                    return 'IOS';
                } else {
                    return 'Web';
                }
            }

            function getDeviceId(){
                if(isAndroidPlatform()){
                    return 1;
                } else if(isIosPlatform()){
                    return 2;
                } else {
                    return 3;
                }
            }

            function setTouchType(){
                if(isIosPlatform()){
                    touchType = "touchend";
                }else if(isAndroidPlatform){
                    touchType = "click";
                }else{
                    touchType = "click";
                }
            }

            function phonegapIsOnline(){
                if(navigator.connection){
                    var networkState = navigator.connection.type;
                    if(isWebPlatform()){
                        return !(networkState == 'none' || networkState == 'unknown');
                    } else {
                        return !(networkState == Connection.NONE || networkState == Connection.UNKNOWN);
                    }
                } else {
                    return true;
                }
            }

            return {
                touchType : touchType,

                getPlatform: getPlatform,

                /**
                 * @ngdoc function
                 * @name core.Services.CordovaDevice#isAndroidPlatform
                 * @methodOf core.Services.CordovaDevice
                 * @return {boolean} Returns true if running on Android Platform
                 */
                isAndroidPlatform: isAndroidPlatform,

                /**
                 * @ngdoc function
                 * @name core.Services.CordovaDevice#isAndroidPlatform
                 * @methodOf core.Services.CordovaDevice
                 * @return {boolean} Returns true if running on iOS Platform
                 */
                isIosPlatform: isIosPlatform,

                /**
                 * @ngdoc function
                 * @name core.Services.CordovaDevice#isWebPlatform
                 * @methodOf core.Services.CordovaDevice
                 * @return {boolean} Returns true if running on Web Platform
                 */
                isWebPlatform: isWebPlatform,

                /**
                 * @ngdoc function
                 * @name core.Services.CordovaDevice#getUpstreamChannel
                 * @methodOf core.Services.CordovaDevice
                 * @return {String} Returns UpstreamChannel for Platform
                 */
                getUpstreamChannel: getUpstreamChannel,

                /**
                 * @ngdoc function
                 * @name core.Services.CordovaDevice#getDeviceId
                 * @methodOf core.Services.CordovaDevice
                 * @return {Integer} Returns Device Id for Platform
                 */
                getDeviceId: getDeviceId,

                setTouchType: setTouchType,

                phonegapIsOnline: phonegapIsOnline,

                setRealWidth: setRealWidth,

                getRealWidth: getRealWidth,

                setRealHeight: setRealHeight,

                getRealHeight: getRealHeight
            };
        }
    ]);
