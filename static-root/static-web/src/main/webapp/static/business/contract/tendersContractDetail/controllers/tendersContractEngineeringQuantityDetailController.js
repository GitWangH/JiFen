'use strict';
/**
 * 合同清单 生成工程量清单
 */
angular.module('huatek.controllers').controller('TendersContractEngineeringQuantityDetailController', function($rootScope, $scope, $location, $http, listService, httpService, treeGridService) {
	
	var queryTopLevelUrl = "/api/busiBaseEngineeringQuantityList/queryTopLevel";/*查询顶级节点URL*/
	var queryChildNodesUrl = "/api/busiBaseEngineeringQuantityList/queryChildNodes/";/*根据父级节点查询子级节点URL*/
	var saveDataUrl = "/api/tendersContractDetail/addSelectedData";/*保存修改数据RUL*/
	
	var columnsArr = [
	                  { name: '清单编号', field: 'number', width: '30%' },
	                  { name: '清单名称', field: 'name', width: '30%' },
	                  { name: '金额(元)', field: 'unit', width: '30%' }
	              ];
	/**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl, false);

    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");
    var groupId = new queryParam('[默认必传参数]当前登陆用户所属集团Id', 'busiFwOrg.id', 'string', '=');
    groupId.value = $rootScope.groupId;
    groupId.isShow = false;/*此条件隐藏*/
    queryPage.addParam(groupId);
    
    queryPage.addParam(new queryParam('清单编号', 'number', 'string', 'alllike'));
    listService.setQueryPage($scope, queryPage);
    treeGridService.setQueryPage($scope, queryPage);
    
    var btnArray = [];
    btnArray.push(new customButton('保存', 'save', true, 'saveQuantitiesDetail'));
    listService.setButtonList($scope, null, btnArray);
    
    /*封装业务对象*/
    var packageData = function(rows) {
        var packageObjArr = [];
        for (var i = 0; i < rows.length; i++) {
        	console.log(rows[i]);
            var obj = {
            	contractDetailCode: rows[i].number,
                contractDetailName: rows[i].name,
                countUnit: rows[i].unit,
                orderNumber: rows[i].orderNumber,
                detaileType: 'create_beq',
                groupLevel: rows[i].groupLevel,
                parentId: rows[i].parentId,
                /**注意：这里冒号前面的uuid需为全小写，后台全大写，否则封装不了 2017-11-09 Edit By Mickey**/
                uuid:rows[i].uuid,
                isLeaf:rows[i].isLeaf
            };
            packageObjArr.push(obj);
        }
        return packageObjArr;
    }

    $scope.execute = function(operation, param) {
        if (operation == 'saveQuantitiesDetail') {
        	var checkedNodesRows = $scope.huatekTree.getAllCheckedNode();
        	console.log("选择的数据...");
        	console.log(checkedNodesRows);
            if (checkedNodesRows.length < 1) {
                submitTips('警告：请至少选择一条数据。', 'warning');
                return false;
            } else {
            	$scope.promise = httpService.post($scope, '/api/tendersContractDetail/addSelectedData/'+$scope.passParams, packageData(checkedNodesRows))
										    .success(function (response) {
										    	$scope.back();/*关闭弹框，返回父级页面*/
										    	$scope.$parent.load();/*重新加载父级页面数据*/
										    })
										    .error(function(){
										    	submitTips('保存出错(合同清单查询工程量清单)','error');
										    });
            }
        }
    }
    /***
     * 工程量清单数据.
     * 详见：com.hisense.frame.page.DataPage<T>
     */
    var load = function() {
    	$scope.huatekTree.loadData($scope.queryPage);
    }

    load();
    
    $scope.search = function() {
        load();
    };
});