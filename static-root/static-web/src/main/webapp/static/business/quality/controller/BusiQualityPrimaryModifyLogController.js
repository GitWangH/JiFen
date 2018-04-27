/**
 * 第三方检测修改日志页
 * @author rocky_wei
 */
angular.module('huatek.controllers').controller('BusiQualityPrimaryModifyLogController', function ($rootScope, $scope, $http, listService,httpService) {

	var fieldArr = $scope.passParams.fieldArr;
	var queryUrl = $scope.passParams.queryUrl;

	var tableGridContent = [];
	$scope.tableGrid = {
			useExternalPagination: false,
			columnDefs:fieldArr
	};
    
    /* 通过editId查询整改单对象 */
    var load = function() {
    	listService.loadData($scope,queryUrl,$scope.tableGrid);
    }
    
    load();
}).filter("dateFilter", function() {/*日期数据过滤器*/
    var dateFilterFunction = function(dateVal) {
    	if(cnex4_isNotEmpty_str(dateVal)){
    		return getDate(dateVal);
        } else {
        	return dateVal;
        }
    };
    return dateFilterFunction;
});