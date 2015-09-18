'use strict';

describe('Controller: PointsCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var PointsCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        PointsCtrl = $controller('PointsCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
