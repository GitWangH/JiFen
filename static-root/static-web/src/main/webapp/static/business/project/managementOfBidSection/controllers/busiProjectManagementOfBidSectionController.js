/**
 * 工程标段管理
 */
'use strict';
   
angular.module('huatek.controllers').controller('busiProjectManagementOfBidSectionController', function ($scope, $location, $http,$rootScope,editService, listService,shareData) {
	/**queryAction*/
	var queryUrl = "/api/busiProjectManagementOfBidSection/query";
	/**创建表格*/
	$scope.tableGrid = {
		paginationPageSizes: [10, 25, 50,100],
	    paginationPageSize: 10,
	    useExternalPagination: true,
	    enableFullRowSelection : true,
	    enableSelectAll : false,
	    multiSelect: false,
	    lookDetailConfig:{menuKey:'/managementOfBidSection/edit',name:'工程标段查看',paramFieldsArr:['name','code','count','initialPileNumber','endPileNumber','createTime']},
	    columnDefs: [
           { name: '标段名称', field: 'name',width: '10%', enableColumnMenu: false},
           { name: '标段编号', field: 'code',width: '20%', enableColumnMenu: false},
           { name: '单位工程数量', field: 'count',width: '10%', enableColumnMenu: false},
           { name: '起始桩号', field: 'initialPileNumber',width: '20%', enableColumnMenu: false},
           { name: '结束桩号', field: 'endPileNumber',width: '20%', enableColumnMenu: false},
           { name: '创建时间', field: 'createTime',width: '20%', enableColumnMenu: false}
	    ]
	};
	/**初始化表格*/
	listService.init($scope, $http, $scope.tableGrid);
	
	/** 查询条件 */
	var queryPage = new QueryPage(1, 10, "a.id asc", "false");
    var tendersName = new queryParam('标段名称', 'b.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
	tendersName.event = function(orgId){
		load();
    }
    
    queryPage.addParam(tendersName);
    var keyWord = new queryParam('标段编号', 'b.orgCode', 'string', 'like')
    queryPage.addParam(keyWord);
	/** 查询条件初始化 */
	listService.setQueryPage($scope,queryPage);
	
    /**
	 * 注册gridApi的选择器.
	 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        listService.setGridHeight();
    };
    
    
    /* 定义功能按钮*/
	var btnArray = [];
	btnArray.push(new pageButton('修改', 'edit', '/managementOfBidSection/edit', 'edit'));
	/*btnArray.push(new pageButton('查看', 'check', '/managementOfBidSection/check', 'check'));*/

	/**
	 * 设置界面的功能按钮.
	 */
    listService.setButtonList($scope, btnArray);
    
    /**
	 * 桥接按钮事件.
	 */
    $scope.execute = function(operation, param){
        if(operation=='edit'){
        	if(listService.selectOne($scope.gridApi)){
        		/*获取当前页面选中行数据*/
        		var row = $scope.gridApi.selection.getSelectedRows()[0];
        		var obj = {}; 
        		obj.code = row.code; 
        		obj.name = row.name;
        		obj.orgId = row.orgId;
        		obj.id = row.id;
        		obj.initialPileNumber = row.initialPileNumber;
        		obj.endPileNumber = row.endPileNumber;
        		obj.load = load;
        		listService.addData($scope, new popup("新增","/managementOfBidSection/edit", obj, "1300", "600"));
        	}
        } else if (operation=='check'){
        	alert('查看');
        } 
    }
    
    var load = function(){
    	listService.loadData($scope, queryUrl, $scope.tableGrid);
    }

    load();
    
    $scope.search = function() {
        load();
    };

});