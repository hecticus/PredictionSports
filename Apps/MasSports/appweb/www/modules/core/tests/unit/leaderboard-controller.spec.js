'use strict';

describe('Controller: LeaderboardCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var LeaderboardCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        LeaderboardCtrl = $controller('LeaderboardCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
