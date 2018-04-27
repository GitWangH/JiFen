/**
 * 远程监控
 */
'use strict';
   
angular.module('huatek.controllers').controller('busiRemoteMonitorSourceController', function ($scope, httpService, $localStorage, $cacheFactory) {
	/**queryAction*/
	var queryUrl = "/api/remoteMonitor/getAllUserRemoteMonitor";
    
    /**
     * 页面加载完之后加载数据
     */
    $scope.$watch('$viewContentLoaded', function() { 
        var cache=$localStorage["dic.monitor_type"];
        var sourceList = cache.data;
        angular.forEach(sourceList, function (data, index) {
            if(data.code == $scope.passParams){
                $scope.subModuleName = data.name;
                return;
            }
        });
        /*设置延时加载*/
        httpService.get($scope, queryUrl+"/"+$scope.passParams).success(function(repsonse){
            $scope.remoteMonitorList = repsonse;
        });
    });
    
    /**
     * 打开远程监控地址
     *
     * @param      {<type>}  entity  The entity
     */
    $scope.viewRemoteMonitor = function(address){
        window.open(address);
    }

});
