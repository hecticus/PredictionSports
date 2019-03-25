'use strict';

describe('Controller: ScorersCtrl', function() {

    //Load the ui.router module
    beforeEach(module('ui.router'));
    //Load the module
    beforeEach(module('core'));

    var ScorersCtrl,
        scope;

    beforeEach(inject(function($controller, $rootScope) {
        scope = $rootScope.$new();
        ScorersCtrl = $controller('ScorersCtrl', {
        $scope: scope
        });
    }));

    it('should ...', function() {

    });
});
