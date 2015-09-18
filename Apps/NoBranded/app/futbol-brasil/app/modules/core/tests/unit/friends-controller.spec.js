'use strict';

describe('Controller: FriendsCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var FriendsCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        FriendsCtrl = $controller('FriendsCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
