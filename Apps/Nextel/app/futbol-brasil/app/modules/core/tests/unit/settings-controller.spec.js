'use strict';

describe('Controller: SettingsController', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var SettingsController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        SettingsController = $controller('SettingsController', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
