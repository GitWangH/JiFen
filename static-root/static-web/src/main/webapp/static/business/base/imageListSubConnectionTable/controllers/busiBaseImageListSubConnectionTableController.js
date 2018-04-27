/** 
 * 形象清单与分部分项挂接
 * 左边 项目形象清单
 * 右边 项目形象清单与项目分部分项
 * */
angular.module('huatek.controllers').controller('busiBaseImageListSubConnectionTableController', function ($scope, $location, $http,$rootScope,editService, listService, shareData, httpService, treeGridService) {
	/**actionUrl start*/
	/*查询顶级节点URL*/
	var queryTopLevelUrl = '/api/busiBaseImageList/queryTopLevel';
	/*根据父级节点查询子级节点URL*/
	var queryChildNodesUrl = '/api/busiBaseImageList/queryChildNodes/';
	/*右侧数据查询*/
	var rightGridQueryUrl = "/api/busiBaseImageListSubConnectionTable/query/";
	/*删除右侧表格选中的数据*/
	var deleteRightDataUrl = "/api/busiBaseImageListSubConnectionTable/delete";
    /*形象清单id 左侧选择的id*/
    var imageId = 0;
	/**actionUrl end*/
		
    /**创建表格 项目形象清单列表 - 页面左边的表格 */
	var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'number', '25%'));
    columnsArr.push(new treeColumn('名称','name','25%'));
    columnsArr.push(new treeColumn('挂接分部分项','','50%',true,new otherConfig('button',null,function(row){
        var obj = {};
    	obj.id = row.id;
    	obj.number = row.number;
    	obj.name = row.name;
    	obj.loadRightTableGrid = loadRightTableGrid;
    	obj.loadLeftTreeGrid = loadLeftTreeGrid;
    	loadRightTableGrid(row.id);
    	imageId = row.id;
        listService.addData($scope,new popup('项目形象清单挂接分部分项','/busiBaseConnectionSubEngineering/home',obj,'1200','800'));
    })));
    
    /** 初始化左边树形表格 */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl,null,false,true);
    
    /** 左侧treeGrid queryPage*/
    var treeGridQueryPage = new QueryPage(1, 1000, "orderNumber asc", "false");
    treeGridService.setQueryPage($scope, treeGridQueryPage);
    var orgId = new queryParam('orgId', 'busiFwOrg.id', 'string', '=', $rootScope.orgId);
    treeGridQueryPage.addParam(orgId);
    
    /** 查询左侧树形数据 */
    var loadLeftTreeGrid  = function(){
    	$scope.huatekTree.loadData(treeGridQueryPage);
    }
    
    /**	查询右侧TableGrid数据*/ 
    var loadRightTableGrid= function(id) {
    	if(typeof id != 'undefined'){
    		listService.loadData($scope, rightGridQueryUrl + id, $scope.tableGrid, true);
    	}
    };
    
    /**
     * 选中树节点的回调
     */
    treeGridService.setNodeCheckedFn($scope, function(selectedRow){
    	imageId = selectedRow.id;
/*    	var rows = $scope.huatekTree.rows;
    	for(var i = 0; i < rows.length; i++){
    		if(rows[i].id != imageId)
    			rows[i].checked = false;

    	}*/
    	if(typeof selectedRow.id != 'undefined'){
    		loadRightTableGrid(selectedRow.id);
    	}
    })
    
    
    /**
     * 项目分部分项与项目工程量清单挂接 右边的表
     */
    $scope.tableGrid = {
	    enableFullRowSelection : true,
	    enableSelectAll : true,
	    multiSelect: true,
        columnDefs: [
            { name: '形象清单编号', field: 'imageNumber', width: '24%', enableColumnMenu: false },
            { name: '形象清单名称', field: 'imageName', width: '25%', enableColumnMenu: false },
            { name: '分部分项编号', field: 'subEngineeringNumber', width: '24%', enableColumnMenu: false },
            { name: '分部分项名称', field: 'subEngineeringName', width: '25%', enableColumnMenu: false }
        ]
    };
    /** 注册页面右侧的选择器 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi_detial = gridApi;
    };
    
/*    $scope.hang = function(row){
    	shareData.id = row.id;
    	shareData.number = row.number;
    	shareData.name = row.name;
    	shareData.loadRightTableGrid = loadRightTableGrid;
    	shareData.loadLeftTreeGrid = loadLeftTreeGrid;
        listService.popPanel($scope,new popup('项目形象清单挂接分部分项','/busiBaseConnectionSubEngineering/home',null,900));
    }*/
    
    /**表格初始化*/
    listService.init($scope);
    
    /**查询对象 此处使用orderNumber 作为排序条件*/
    var queryPage = new QueryPage(1, 1000, "orderNumber asc", "false");
    listService.setQueryPage($scope, queryPage);
    
    /* 定义功能按钮*/
    var btnArray = [];
    /*btnArray.push(new pageButton('挂接分部分项', 'hitch', '/busiBaseImageListSubConnectionTable/hitch', 'hitch'));*/
    btnArray.push(new pageButton('删除', 'delete', '/busiBaseImageListSubConnectionTable/delete', 'delete'));
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
        			loadRightTableGrid(imageId);
        		});
        	}
        } 
    }
    /*加载左侧页面显示  $scope.isRefreshSubPage 是否更新左侧页面*/
    if($scope.isRefreshImagePage == true || typeof $scope.isRefreshImagePage == 'undefined'){
    	loadLeftTreeGrid();
    } else {
    	$scope.isRefreshImagePage = true;
    }
    /*加载右侧页面显示*/
    /*loadRightTableGrid();*/

});
