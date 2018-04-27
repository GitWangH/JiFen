/**
 *	分部分项与工程量清单挂接 - 显示工程量清单 
 */
angular.module('huatek.controllers').controller('busiBaseConnectionQuantityListController', function($scope, $location, $http, $rootScope, editService, listService, shareData, httpService, treeGridService) {
	var params = $scope.passParams;
	$scope.titleNumber = '分部分项编号';
	$scope.titleName = '分部分项名称';
	$scope.number = params.number;
	$scope.name = params.name;
	/*查询顶级节点URL*/
	var queryTopLevelUrl = '/api/busiBaseEngineeringQuantityList/queryTopLevel';
	/*根据父级节点查询子级节点URL*/
	var queryChildNodesUrl = '/api/busiBaseEngineeringQuantityList/queryChildNodes/';
	/**saveAction*/
	var saveUrl = "/api/busiBaseQuantityListSubConnectionTable/save";
	
    /**创建表格 工程量清单 树形列表 - 不可编辑*/
	var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'number', '25%'));
    columnsArr.push(new treeColumn('名称','name','25%'));
    columnsArr.push(new treeColumn('单位','unit','25%'));
    columnsArr.push(new treeColumn('备注','remark','25%'));
    
    /** 初始化表格 */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl,null,false);
    /** 查询条件 */
    var treeGridQueryPage = new QueryPage(1, 1000, "orderNumber asc", "false");
    treeGridService.setQueryPage($scope, treeGridQueryPage);
    var orgId = new queryParam('orgId', 'busiFwOrg.id', 'string', '=', $rootScope.orgId);
    treeGridQueryPage.addParam(orgId);
    
    /** 定义功能按钮*/
    var btnArray = [];
    listService.setButtonList($scope, btnArray);
    
    /** 加载数据 */
    var load = $scope.load = function(){
    	$scope.huatekTree.loadData(treeGridQueryPage);
    }

    load();
    
    /** 保存*/
    $scope.save = function(){
    	/*var selectedRow = $scope.huatekTree.getAllCheckedLeafNode();*/
    	var selectedRow = $scope.huatekTree.getAllCheckedNode();
    	var data = {};
    	/*工程量清单id列表*/
    	var engineeringQuantityIdList = [];
    	/*分部分项id*/
    	data.subEngineeringId = params.id;
    	for(var i = 0; i < selectedRow.length; i++){
    		engineeringQuantityIdList.push(selectedRow[i].id);
    	}
    	data.engineeringQuantityIdList = engineeringQuantityIdList;
    	$scope.back();
    	$scope.promise = httpService.post($scope, saveUrl, data)
	    .success(function (response) {
	    	params.loadRightTableGrid(params.id);
	    	$scope.isRefreshSubPage = false;
	    	/*params.loadLeftTreeGrid();*/
	    })
	    .error(function(){
	    	submitTips('保存出错，请重试。','error');
	    });
    }
});