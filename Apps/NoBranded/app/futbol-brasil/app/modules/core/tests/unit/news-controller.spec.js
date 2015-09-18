'use strict';

describe('Controller: NewsCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var NewsCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        NewsCtrl = $controller('NewsCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
