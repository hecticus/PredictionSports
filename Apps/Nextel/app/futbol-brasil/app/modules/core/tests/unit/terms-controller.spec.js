'use strict';

describe('Controller: TermsController', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var TermsController,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        TermsController = $controller('TermsController', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
