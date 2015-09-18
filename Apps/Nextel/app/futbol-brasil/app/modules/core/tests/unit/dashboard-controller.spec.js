'use strict';

describe('Controller: DashboardController', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var DashboardController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        DashboardController = $controller('DashboardController', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
