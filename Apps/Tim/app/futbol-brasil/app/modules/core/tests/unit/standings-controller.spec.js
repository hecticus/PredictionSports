'use strict';

describe('Controller: StandingsCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var StandingsCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        StandingsCtrl = $controller('StandingsCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
