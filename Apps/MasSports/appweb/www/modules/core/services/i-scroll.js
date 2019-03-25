'use strict';

/**
 * @ngdoc service
 * @name core.Services.iScroll
 * @description iScroll Factory
 */
angular
    .module('core')
    .factory('iScroll',['$window',
        function($window) {
            return {

                /**
                 * @ngdoc function
                 * @name core.Services.iScroll#horizontal
                 * @description Sets up a new horizontal iScroll instance
                 * on the DOM element with the specified ID
                 * @param {string} _wrapper DOM Element Wrapper ID
                 * @methodOf core.Services.iScroll
                 */
                horizontal : function (_wrapper) {
                    delete $window[_wrapper] ;
                    $window[_wrapper] = new IScroll('#' + _wrapper, {
                        scrollX: true,
                        scrollY: false,
                        mouseWheel: true,
                        momentum: false,
                        snap: true,
                        snapSpeed: 700,
                        probeType: 3,
                        bounce: false,
                        click: true,
                        preventDefault:true
                    });
                    $window[_wrapper].on('beforeScrollStart', function () {
                        this.refresh();
                    });
                    return $window[_wrapper];
                },

                /**
                 * @ngdoc function
                 * @name core.Services.iScroll#vertical
                 * @description Sets up a new vertical iScroll instance
                 * on the DOM element with the specified ID
                 * @param {string} _wrapper DOM Element Wrapper ID
                 * @methodOf core.Services.iScroll
                 */
                vertical : function (_wrapper) {
                    delete $window[_wrapper] ;
                    $window[_wrapper] = new IScroll('#' + _wrapper, {
                        scrollX: false,
                        scrollY: true,
                        mouseWheel: false,
                        momentum: true,
                        snap: false,
                        snapSpeed: 10,
                        probeType: 3,
                        bounce: true,
                        click: false,
                        preventDefault:false
                    });
                    $window[_wrapper].on('beforeScrollStart', function () {
                        this.refresh();
                    });
                    return $window[_wrapper];

                },

                /**
                 * @ngdoc function
                 * @name core.Services.iScroll#verticalForm
                 * @description Sets up a new vertical iScroll
                 * instance, that propagates click events to it's children,
                 * on the DOM element with the specified ID
                 * @param {string} _wrapper DOM Element Wrapper ID
                 * @methodOf core.Services.iScroll
                 */
                verticalForm : function (_wrapper) {
                    delete $window[_wrapper] ;
                    $window[_wrapper] = new IScroll('#' + _wrapper, {
                        click : false,
                        preventDefault : true,
                        bounce : true,
                        probeType: 2,
                        preventDefaultException: { tagName:/.*/ }
                    });
                    $window[_wrapper].on('beforeScrollStart', function () {
                        this.refresh();
                    });
                    return $window[_wrapper];
                }
            };
        }
    ]);
