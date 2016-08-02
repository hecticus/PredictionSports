'use strict';

/**
 * @ngdoc directive
 * @name core.Directives.loading
 * @description loading directive
 */
angular
    .module('core')
    .directive('loading', [
        function() {
            return {
                // name: '',
                // priority: 1,
                // terminal: true,
                // scope: {}, // {} = isolate, true = child, false/undefined = no change
                // controller: function($scope, $element, $attrs, $transclude) {},
                // require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
                restrict: 'E', // E = Element, A = Attribute, C = Class, M = Comment
                // template: '',
                templateUrl: 'modules/core/views/templates/loading.html',
                replace: true,
                // transclude: true,
                // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
                link: function (scope, element, attr, controller) {
                      scope.$watch('loading', function (val) {
                          if (val)
                              $(element).show();
                          else
                              $(element).hide();
                      });
                }
            };
        }
    ]);
