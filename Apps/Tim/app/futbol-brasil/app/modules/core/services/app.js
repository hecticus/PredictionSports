'use strict';

/**
 * @ngdoc service
 * @name core.Services.App
 * @description App Factory
 */
angular
    .module('core')
    .factory('App',
        function() {
            var companyName = '';
            var buildVersion = '';
            var serverVersion = '';
            var bundleVersion = '0.0.2';
            var bundleId = '';
            var updateInfo = {};

            return {

                /**
                 * @ngdoc function
                 * @name core.Services.App#getCompanyName
                 * @description companyName getter
                 * @methodOf core.Services.App
                 * @return {string} companyName
                 */
                getCompanyName: function(){
                    return companyName;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#getCompanyName
                 * @description companyName getter
                 * @methodOf core.Services.App
                 * @return {string} companyName
                 */
                setCompanyName: function(name){
                    companyName = name;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#getBuildVersion
                 * @description buildVersion getter
                 * @methodOf core.Services.App
                 * @return {string} buildVersion
                 */
                getBuildVersion: function(){
                    return buildVersion;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#setBuildVersion
                 * @description buildVersion setter
                 * @methodOf core.Services.App
                 */
                setBuildVersion: function(version){
                    buildVersion = version;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#getServerVersion
                 * @description serverVersion getter
                 * @methodOf core.Services.App
                 * @return {string} serverVersion
                 */
                getServerVersion: function(){
                    return serverVersion;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#setServerVersion
                 * @description serverVersion setter
                 * @methodOf core.Services.App
                 */
                setServerVersion: function(version){
                    serverVersion = version;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#getBundleVersion
                 * @description bundleVersion getter
                 * @methodOf core.Services.App
                 * @return {string} bundleVersion
                 */
                getBundleVersion: function(){
                    return bundleVersion;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#setBundleVersion
                 * @description bundleVersion setter
                 * @methodOf core.Services.App
                 */
                setBundleVersion: function(version){
                    bundleVersion = version;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#getBundleId
                 * @description bundleId getter
                 * @methodOf core.Services.App
                 * @return {string} bundleId
                 */
                getBundleId: function(){
                    return bundleId;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#setBundleId
                 * @description bundleId setter
                 * @methodOf core.Services.App
                 */
                setBundleId: function(id){
                    bundleId = id;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#getUpdateInfo
                 * @description updateInfo getter
                 * @methodOf core.Services.App
                 * @return {object} updateInfo
                 */
                getUpdateInfo: function(){
                    return updateInfo;
                },

                /**
                 * @ngdoc function
                 * @name core.Services.App#setUpdateInfo
                 * @description aupdateInfo setter
                 * @methodOf core.Services.App
                 */
                setUpdateInfo: function(info){
                    updateInfo = info;
                }
            };
    });
