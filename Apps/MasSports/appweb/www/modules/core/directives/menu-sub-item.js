'use strict';

/**
 * @ngdoc directive
 * @name core.Directives.menuSubItem
 * @description menuSubItem directive
 */
angular
    .module('core')
    .directive('menuSubItem', [
        function() {
            return {
                restrict: "E",
                templateUrl: 'modules/core/views/templates/menu-sub-item.html',
                transclude: true,
                scope: {
                    section: "@",
                    icon: "@"
                }
            };
        }
    ]);
