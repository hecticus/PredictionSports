'use strict';

/**
 * @ngdoc directive
 * @name core.Directives.myScroll
 * @description myScroll directive
 */
angular
    .module('core')
    .directive('myScroll', [
        function() {
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
                  if (scope.$last){
                    var _scroll = new IScroll('#wrapper',{click:true, preventDefault:true});
                    _scroll.on('beforeScrollStart', function () {
                      this.refresh();
                    });
                  }
                }
            };
        }
    ]);
