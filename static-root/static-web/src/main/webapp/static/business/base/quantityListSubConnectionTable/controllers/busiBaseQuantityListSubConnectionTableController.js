/**
 * 分部分项与工程量清单挂接
 * 左边 分部分项
 * 右边 分部分项与工程量清单
 */
angular.module('huatek.controllers').controller('busiBaseQuantityListSubConnectionTableController', function($scope, $location, $http, $rootScope, editService, listService, shareData, httpService,treeGridService) {
	/**actionUrl start */
	/*查询顶级节点URL*/
	var queryTopLevelUrl = '/api/busiBaseSubEngineering/queryTopLevel';
	/*根据父级节点查询子级节点URL*/
	var queryChildNodesUrl = '/api/busiBaseSubEngineering/queryChildNodes/';
	/*分部分项与工程量清单挂接 右侧列表显示数据*/
	var busiBaseQuantityListSubConnectionTableQueryUrl = "/api/busiBaseQuantityListSubConnectionTable/query/";
	/*删除右侧表格选中的数据*/
	var deleteRightDataUrl = "/api/busiBaseQuantityListSubConnectionTable/delete";
	/*分部分项id*/
	var subEngineeringId = 0;
	/**actionUrl end */

    /**创建表格 分部分项 - 页面左边的表格 */
	var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'number', '25%'));
    columnsArr.push(new treeColumn('名称','name','25%'));
    columnsArr.push(new treeColumn('挂接工程量清单','','50%',true,new otherConfig('button',null,function(row){
        var obj = {};
    	obj.id = row.id;
    	obj.number = row.number;
    	obj.name = row.name;
    	obj.loadRightTableGrid = loadRightTableGrid;
    	obj.loadLeftTreeGrid = loadLeftTreeGrid;
    	loadRightTableGrid(row.id);
    	subEngineeringId = row.id;
        listService.addData($scope,new popup('项目分部分项挂接工程量清单','/busiBaseConnectionQuantityList/home',obj,'1200','800'));
    })));
    
    /** 初始化左边树形表格 */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl,null,false,true);
    
    /** 左侧treeGrid queryPage*/
    var treeGridQueryPage = new QueryPage(1, 1000, "orderNumber asc", "false");
    treeGridService.setQueryPage($scope, treeGridQueryPage);
    var orgId = new queryParam('orgId', 'busiFwOrg.id', 'string', '=', $rootScope.orgId);
    treeGridQueryPage.addParam(orgId);
    
    /** 查询左侧树形数据 */
    var loadLeftTreeGrid = function(){
    	$scope.huatekTree.loadData(treeGridQueryPage);
    }
    
    /**	查询右侧TableGrid数据*/ 
    var loadRightTableGrid= function(subId) {
    	if(subId != 0){
    	    listService.loadData($scope, busiBaseQuantityListSubConnectionTableQueryUrl + subId, $scope.tableGrid, true);	
    	}
    };
    
    /**
     * 选中树节点的回调
     */
    treeGridService.setNodeCheckedFn($scope, function(selectedRow){
    	subEngineeringId = selectedRow.id; 
/*    	var rows = $scope.huatekTree.rows;
    	for(var i = 0; i < rows.length; i++){
    		if(rows[i].id != subEngineeringId)
    			rows[i].checked = false;
    	}*/
    	if(typeof selectedRow.id != 'undefined'){
    		loadRightTableGrid(selectedRow.id);
    	}
    })
    
    /**
     *  项目分部分项挂接工程量清单 跳转 工程量清单挂接页面
     */
/*    $scope.hang = function(row){
    	var obj = {};
    	obj.id = row.id;
    	obj.number = row.number;
    	obj.name = row.name;
    	obj.loadRightTableGrid = loadRightTableGrid;
    	obj.loadLeftTreeGrid = loadLeftTreeGrid;
        listService.addData($scope,new popup('项目分部分项挂接工程量清单','/busiBaseConnectionQuantityList/home',obj,'1200','800'));
    }*/
    /**
     * 项目分部分项与项目工程量清单挂接 右边的表格
     */
    $scope.tableGrid = {
	    enableFullRowSelection : true,
	    enableSelectAll : true,
	    multiSelect: true,
        columnDefs: [
            { name: '分部分项编号', field: 'subEngineeringNumber', width: '24%', enableColumnMenu: false },
            { name: '分部分项名称', field: 'subEngineeringName', width: '25%', enableColumnMenu: false },
            { name: '工程量清单编号', field: 'engineeringQuantityNumber', width: '24%', enableColumnMenu: false },
            { name: '工程量清单名称', field: 'engineeringQuantityName', width: '25%', enableColumnMenu: false }
        ]
    };
    
    /** 注册页面右侧的选择器 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi_detial = gridApi;
    };
    
    /** 表格初始化 */
    listService.init($scope);

    /**查询对象 此处使用orderNumber 作为排序条件*/
    var queryPage = new QueryPage(1, 1000, "orderNumber asc", "false");
    listService.setQueryPage($scope, queryPage);
    

    /** 定义功能按钮*/
    var btnArray = [];
    /*btnArray.push(new pageButton('挂接工程量清单', 'hitch', '/BusiBaseQuantityListSubConnectionTable/hitch', 'hitch'));*/
    btnArray.push(new pageButton('删除', 'delete', '/BusiBaseQuantityListSubConnectionTable/delete', 'delete'));
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'hitch') { /*挂接*/
/*        	if(listService.selectOne($scope.gridApi)){
    			$scope.hang($scope.gridApi.selection.getSelectedRows()[0]);
    		}*/
        } else if (operation == 'delete') { /*删除*/
        	if(listService.selectMany($scope.gridApi_detial)){
        		var selectRows = $scope.gridApi_detial.selection.getSelectedRows();
        		var selectedList = [];
        		for(var i = 0; i < selectRows.length; i++){
        			selectedList.push(selectRows[i].id);
        		}
        		httpService.post($scope, deleteRightDataUrl, selectedList).success(function(text){
        			/*保存成功后刷新右侧列表数据*/
        			loadRightTableGrid(subEngineeringId);
        		});
        	}
        } 
    }
    /*刷新左侧列表数据*/
    loadLeftTreeGrid();
    if($scope.isRefreshSubPage == true || typeof $scope.isRefreshSubPage == 'undefined'){
    	loadLeftTreeGrid();
    } else {
    	$scope.isRefreshSubPage = true;
    }
    /*刷新右侧列表数据*/
    /*loadRightTableGrid();*/
});