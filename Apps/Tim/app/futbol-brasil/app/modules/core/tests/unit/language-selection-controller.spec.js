'use strict';

describe('Controller: LanguageSelectionController', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var LanguageSelectionController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        LanguageSelectionController = $controller('LanguageSelectionController', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
