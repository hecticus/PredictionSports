'use strict';

/**
 * @ngdoc directive
 * @name core.Directives.onLastRepeat
 * @description onLastRepeat directive
 */
angular
    .module('core')
    .directive('onLastRepeat', ['$timeout',
        function($timeout) {
            return {
                // name: '',
                // priority: 1,
                // terminal: true,
                // scope: {}, // {} = isolate, true = child, false/undefined = no change
                // controller: function($scope, $element, $attrs, $transclude) {},
                // require: 'ngModel', // Array = multiple requires, ? = optional, ^ = check parent elements
                // restrict: 'A', // E = Element, A = Attribute, C = Class, M = Comment
                // template: '',
                // templateUrl: '',
                // replace: true,
                // transclude: true,
                // compile: function(tElement, tAttrs, function transclude(function(scope, cloneLinkingFn){ return function linking(scope, elm, attrs){}})),
                link: function(scope, element, attrs) {
                  if (scope.$last) $timeout(function(){
                      scope.$emit('onRepeatLast', element, attrs);
                  }, 1);

                  if (scope.$first) $timeout(function(){
                      scope.$emit('onRepeatFirst', element, attrs);
                  }, 1);
                }
            };
        }
    ]);
