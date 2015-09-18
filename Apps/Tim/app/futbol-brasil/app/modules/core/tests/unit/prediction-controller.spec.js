'use strict';

describe('Controller: PredictionCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var PredictionCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        PredictionCtrl = $controller('PredictionCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
