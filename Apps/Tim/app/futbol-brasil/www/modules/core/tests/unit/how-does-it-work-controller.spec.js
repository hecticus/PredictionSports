'use strict';

describe('Controller: HowDoesItWorkController', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var HowDoesItWorkController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        HowDoesItWorkController = $controller('HowDoesItWorkController', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
