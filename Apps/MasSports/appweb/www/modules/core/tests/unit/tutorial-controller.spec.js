'use strict';

describe('Controller: TutorialController', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var TutorialController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        TutorialController = $controller('TutorialController', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
