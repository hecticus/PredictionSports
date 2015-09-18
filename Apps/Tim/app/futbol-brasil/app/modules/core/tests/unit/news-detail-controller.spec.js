'use strict';

describe('Controller: NewsDetailCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var NewsDetailCtrlController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        NewsDetailCtrlController = $controller('NewsDetailCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
