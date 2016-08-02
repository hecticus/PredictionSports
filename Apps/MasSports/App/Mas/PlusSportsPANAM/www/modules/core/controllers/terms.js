'use strict';

/**
 * @ngdoc object
 * @name core.Controllers.TermsController
 * @description TermsController
 * @requires ng.$scope
*/
angular
    .module('core')
    .controller('TermsController', [
        '$scope', '$translate', 'iScroll',
        function($scope, $translate, iScroll) {

          $scope.message = '';


          function getTranslations(){
            $translate(['TERMS.MESSAGE'])
            .then(function(translation){
                $scope.message  = translation['TERMS.MESSAGE'];
            });
          };

          function init(){
            $scope.$emit('load');
            getTranslations();
            $scope.scroll = iScroll.vertical('wrapper');
            $scope.$on('$destroy', function() {
                $scope.scroll.destroy();
                $scope.scroll = null;
            });
            $scope.$emit('unload');
          } init();

        }
]);
