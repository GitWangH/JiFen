/***
 * 第三方券管理Controller
 */
angular.module('huatek.controllers').controller('PhiThirdPartyCouponsController', function ($scope,$http,$rootScope, listService,httpService,excelService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '劵id', field: 'cpnsId',width: '12%', enableColumnMenu: false},
		       { name: '劵来源（第三方公司名）', field: 'cpnsSource',width: '12%', enableColumnMenu: false},
		       { name: '劵名称', field: 'cpnsName',width: '15%', enableColumnMenu: false},
		       { name: '券类型', field: 'cpnsType1',width: '12%', enableColumnMenu: false,decode:{field:'cpnsType',dataKey:'dic.coupons_third_party_type'}},	
		       { name: '券数量', field: 'cpnsQuantity',width: '12%', enableColumnMenu: false},
		       { name: '券有效期', field: 'cpnsValid',width: '12%', enableColumnMenu: false},
		       { name: '券方案', field: 'cpnsWay',width: '16%', enableColumnMenu: false},
		       { name: '操作', field: '',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewCpns","","查看券码")}
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope,$http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    //定义搜索框
	/*queryParam (title, field, type, logic, value, dropDataUrl,isShow, event,display)*/
    queryPage.addParam(new queryParam('券id','cpnsId','string','alllike'));
    queryPage.addParam(new queryParam('券名称','cpnsName','string','alllike'));
    var cpnsType = new queryParam('券类型','cpnsType','string','=','','dic.coupons_third_party_type');
    cpnsType.componentType = 'select';
    queryPage.addParam(cpnsType);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope,queryPage);

    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
    //定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('批量导入','import','/phiThirdPartyCoupons/batchInput','importData'));
    listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	var selectData = $scope.gridApi.selection.getSelectedRows();
    	if(operation == 'viewCpns'){
    		var map = {'id' : param.cpnsId,'name' : param.cpnsName,'valid' : param.cpnsValid};
    		listService.editData($scope, $scope.gridApi, new popup("查看券码","/phiThirdPartyCoupons/viewCoupons", map, "1000", "700"),param);
    	}else if(operation == 'importData'){
    		excelService.imp({busiCode:'mpms000000001'}, function(){
    			load();
    		});
    	}
    	
    	
    }
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope,'api/phiThirdPartyCoupons/query', $scope.tableGrid);
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});

/****
 * 第三方优惠券明细Controller
 */
angular.module('huatek.controllers').controller('PhiThirdPartyCouponsDetailController', function ($scope,$http,$rootScope,listService,httpService,cacheService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '序号', field: 'id',width: '16%', enableColumnMenu: false},      
		       { name: '券id', field: 'cpnsId',width: '17%', enableColumnMenu: false},
		       { name: '券有效期', field: 'cpnsValid',width: '17%', enableColumnMenu: false},
		       { name: '是否兑换', field: 'exchangeStatus1',width: '16%', enableColumnMenu: false,decode:{field:'exchangeStatus',dataKey:'dic.exchange_status'}},		     
		       { name: '绑定会员', field: 'memberName',width: '17%', enableColumnMenu: false},
		       { name: '劵状态', field: 'coupStatus1',width: '16%', enableColumnMenu: false,decode:{field:'coupStatus',dataKey:'dic.coupons_status'}},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope,$http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    queryPage.addParam(new queryParam('券id','coupId','string','alllike'));
    queryPage.addParam(new queryParam('绑定会员','memberName','string','alllike'));
    var coupStatus = new queryParam('券状态','coupStatus','string','=','','dic.coupons_status');
    coupStatus.componentType = 'select';
    queryPage.addParam(coupStatus);
    var exchangeStatus = new queryParam('兑换状态','exchangeStatus','string','=','','dic.exchange_status');
    exchangeStatus.componentType = 'select';
    queryPage.addParam(exchangeStatus);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope,queryPage);

    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
    //定义功能按钮
    
    var btnArray = [];
 
    listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){	
    	
    }
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope,'api/phiThirdPartyCouponsDetail/getCouponsDetailByCouponsId/'+$scope.passParams.id, $scope.tableGrid, null, null, function(){
    		for(var i=0;i<$scope.tableGrid.data.length;i++){
    			$scope.tableGrid.data[i].cpnsId = $scope.passParams.id;
    			$scope.tableGrid.data[i].cpnsValid = $scope.passParams.valid;
    		}
    	});
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});
