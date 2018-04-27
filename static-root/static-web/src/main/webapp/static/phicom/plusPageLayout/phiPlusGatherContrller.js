'use strict';
/**
 * 
 */
angular.module('huatek.controllers').controller('phiPlusGatherContrller', function($scope, $http, editService, listService, httpService, $rootScope) {
    /* var homeUrl = '/phiOrder/home';*/
    var homeUrl = '';
    $scope.editId = $scope.passParams.id;
    var editDataUrl = 'api/phiPlusPagelayout/add';

    $scope.title = $scope.passParams.title;

    $scope.configureaddr = $scope.passParams.configureaddr;
     $scope.uploadPath = '/root/dd';
    var load = function() {
        httpService.get($scope, "api/phiPlusPagelayout/edit/" + $scope.editId).success(function(res) {
            if (res != null) {
                $scope.phiPlusPagelaoutDetail = res;
            }
        })
    }


    $scope.save = function() {
        httpService.post($scope, editDataUrl, { id: $scope.editId, title: $scope.title, phiPlusPagelaoutDetail: $scope.phiPlusPagelaoutDetail }).success(function(res) {
            submitTips("保存成功！", "success");
        });
        this.jsPanel.close();
    }

    $scope.reset = function(id) {
        httpService.get($scope, "api/phiPlusPagelayout/reset/" + id).success(function() {
            load();
        })
    }

    $scope.back = function() {
        this.jsPanel.close();
    }
        
    load();

});
