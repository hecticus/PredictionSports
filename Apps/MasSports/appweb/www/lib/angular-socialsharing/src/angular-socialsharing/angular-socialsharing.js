// Create all modules and define dependencies to make sure they exist
// and are loaded in the correct order to satisfy dependency injection
// before all nested files are concatenated by Grunt

// Config
angular.module('socialsharing.config', [])
    .value('socialsharing.config', {
        debug: true
    });

// Modules
angular.module('socialsharing.services', []);
angular.module('socialsharing',
    [
        'socialsharing.config',
        'socialsharing.services'
    ]);
