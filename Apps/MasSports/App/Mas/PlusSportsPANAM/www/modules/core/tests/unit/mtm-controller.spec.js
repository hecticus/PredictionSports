'use strict';

describe('Controller: MtmCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var MtmCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        MtmCtrl = $controller('MtmCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
