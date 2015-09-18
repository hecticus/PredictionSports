'use strict';

/**
 * @ngdoc service
 * @name core.Services.i18n
 * @description i18n Factory
 */
angular
    .module('core')
    .factory('i18n',['$q', '$localStorage', '$http', 'Domain',
        function($q, $localStorage, $http, Domain) {
            var FILE_KEY_LANGUAGES = 'APPLANGUAGES';
            var FILE_KEY_LANGUAGE_DEFAULT = 'APPLANGUAGEDEFAULT';
            var availableLanguages = [];
            var defaultLanguage = null;

            //noinspection UnnecessaryLocalVariableJS
            var service = {

                /**
                 * @ngdoc function
                 * @name core.Services.i18n#init
                 * @description Initializes i18n Service
                 * @param {object} lang default language to be set
                 * @methodOf core.Services.i18n
                 */
                init: init,

                /**
                 * @ngdoc function
                 * @name core.Services.i18n#getAvailableLanguages
                 * @methodOf core.Services.i18n
                 * @return {Array} Returns available in-app Languages
                 */
                getAvailableLanguages: getAvailableLanguages,

                /**
                 * @ngdoc function
                 * @name core.Services.i18n#setDefaultLanguage
                 * @description Sets the app's default language
                 * @param {object} lang default language to be set
                 * @methodOf core.Services.i18n
                 */
                setDefaultLanguage : setDefaultLanguage,

                /**
                 * @ngdoc function
                 * @name core.Services.i18n#getDefaultLanguage
                 * @methodOf core.Services.i18n
                 * @return {object} Returns default app language
                 */
                getDefaultLanguage : getDefaultLanguage
            }

            function persistLanguages(languages){
                if(languages) {
                    availableLanguages = languages;
                }
                $localStorage[FILE_KEY_LANGUAGES] = JSON.stringify(availableLanguages);
            }

            function loadLanguages(){
                availableLanguages = JSON.parse($localStorage[FILE_KEY_LANGUAGES]);
                return availableLanguages;
            }

            function getAvailableLanguages() {
                if(!availableLanguages){ loadLanguages(); }
                return availableLanguages;
            }

            function getAvailableLanguagesFromServer(){
                $http.get(Domain.languages()).then(
                    function(response){
                        response = response.data;
                        if(response.error) {
                            return $q.reject(response.data);
                        } else {
                            response = response.response;
                            persistLanguages(response.languages);
                            return availableLanguages;
                        }
                    },
                    function(response){
                        console.log('i18n. setAvailableLanguages. promise error');
                        response.data.languages = availableLanguages;
                        return $q.reject(response.data);
                    }
                );
            }

            function getDefaultLanguage(){
                if(!defaultLanguage && $localStorage[FILE_KEY_LANGUAGE_DEFAULT]){
                    defaultLanguage = JSON.parse($localStorage[FILE_KEY_LANGUAGE_DEFAULT]);
                }
                return defaultLanguage;
            }

            function setDefaultLanguage(lang){
                if(lang !== defaultLanguage) {
                    defaultLanguage = lang;
                    $localStorage[FILE_KEY_LANGUAGE_DEFAULT] = JSON.stringify(defaultLanguage);
                }
            }

            function init(lang){
                Domain.setProvisionalLanguage(lang);
                setDefaultLanguage(lang);
                getAvailableLanguagesFromServer();
            }

            return service;
        }
    ]);
