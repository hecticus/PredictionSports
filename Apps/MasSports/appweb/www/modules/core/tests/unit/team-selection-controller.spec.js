'use strict';

describe('Controller: TeamSelectionController', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var TeamSelectionController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        TeamSelectionController = $controller('TeamSelectionController', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
