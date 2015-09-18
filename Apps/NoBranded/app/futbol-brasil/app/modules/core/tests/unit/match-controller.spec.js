'use strict';

describe('Controller: MatchCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var MatchCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        MatchCtrl = $controller('MatchCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
