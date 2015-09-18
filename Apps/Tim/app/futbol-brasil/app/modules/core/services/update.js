'use strict';

/**
 * @ngdoc service
 * @name core.Services.Update
 * @description Update Factory
 */
angular
    .module('core')
    .factory('Update',['$rootScope', 'App',
        function($rootScope, App) {

            function checkUpdate(){
                var updateInfo = App.getUpdateInfo();
                updateInfo.current_version = App.getBundleVersion();
                updateInfo.bundle_id = App.getBundleId();
                console.log('updateInfo.update: ');
                console.log(updateInfo.update);
                if(updateInfo.update === 1){
                    showUpdateModal(updateInfo);
                }
            }

            function showUpdateModal(updateInfo){
                updateInfo.title = 'Update Info';
                updateInfo.html = '<p class="text-success">New Update</p><p>- ';
                if(updateInfo.mandatory === 1){ updateInfo.html += 'Mandatory '; }
                updateInfo.html += 'Version ' + updateInfo.new_version + ' is now Available</p>';

                $rootScope.updateInfo = updateInfo;

                $('#update-modal').modal({
                    backdrop: !!updateInfo.mandatory? 'static' : true,
                    keyboard: false,
                    show: false})
                    .modal('show');
            }

            return {

                /**
                 * @ngdoc function
                 * @name core.Services.Update#checkUpdate
                 * @description Verifies for a new App version and display message dialog if
                 * a new update is available
                 * @methodOf core.Services.Update
                 */
                checkUpdate : checkUpdate
            };
        }
    ]);
