'use strict';
/**
 * 合同清单 查看对应分部分项
 */
angular.module('huatek.controllers').controller('TendersContractViewRelationBranchController', function($scope, $location, $http, listService, httpService, treeGridService) {
	
	var currentRow = $scope.passParams.row;/*操作的当前行数据*/
	
	var queryTopLevelUrl = "/api/tendersBranch/queryRelationBranchTreeList";/*查询分部分项URL*/
	
	var columnsArr = [];
    columnsArr.push(new treeColumn('编号','tendersBranchCode','15%'));
    columnsArr.push(new treeColumn('单位工程','tendersBranchName','10%'));
    columnsArr.push(new treeColumn('分部工程','tendersBranchName','10%'));
    columnsArr.push(new treeColumn('设计数量','designQuantity','10%'));
    columnsArr.push(new treeColumn('设计金额(元)','designTotalPrice','10%'));
    columnsArr.push(new treeColumn('桩号类型','stakeNoType','10%',true,new otherConfig('select','dic.pile_no_type')));
    columnsArr.push(new treeColumn('起始桩号','startStakeNo','10%'));
    columnsArr.push(new treeColumn('结束桩号','endStakeNo','10%'));
    columnsArr.push(new treeColumn('合同图号','contractFigureNo','10%'));
    columnsArr.push(new treeColumn('程高','gradeHigh','10%'));
	/**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl + "/"+currentRow.id, null, null, false);

    /*var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");
    
    queryPage.addParam(new queryParam('清单编号', 'number', 'string', 'alllike'));
    listService.setQueryPage($scope, queryPage);*/
    
    /***
     * 工程量清单数据.
     */
    var load = function() {
    	$scope.huatekTree.loadData($scope.queryPage);
    }

    load();
    
});