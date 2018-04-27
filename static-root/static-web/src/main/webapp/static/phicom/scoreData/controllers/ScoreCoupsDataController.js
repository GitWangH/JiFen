'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('ScoreCoupsDataController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	
	var initTaskUrl='api/scoreData/scoreCoupsConfig';
	/***
	 * 定义显示的表格.
	 */
    $scope.tableGrid = {
            paginationPageSizes: [10, 25, 50, 100],
            paginationPageSize: 10,
            useExternalPagination: true,
            multiSelect: true,
		    columnDefs: [
		       { name: '卷类型', field: 'typeName',width: '8%', enableColumnMenu: false},
		       { name: '卷ID', field: 'cpnsId',width: '8%', enableColumnMenu: false},
		       { name: '卷名称', field: 'cpnsName',width: '10%', enableColumnMenu: false},
		       { name: '类型', field: 'cnpsType',width: '8%', enableColumnMenu: false},
		       { name: '兑换会员数', field: 'coupMemCount',width: '10%', enableColumnMenu: false},
		       { name: '总张数', field: 'totalCount',width: '10%', enableColumnMenu: false},
		       { name: '兑换张数', field: 'coupCount',width: '10%', enableColumnMenu: false},
		       { name: '兑换比例', field: 'coupThan',width: '10%', enableColumnMenu: false},
		       { name: '使用张数', field: 'useCount',width: '10%', enableColumnMenu: false},
		       { name: '兑换使用比例', field: 'useThan',width: '10%', enableColumnMenu: false},
		       { name: '操作', field: '',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewCpns","","使用详情")}
		    ]
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
    listService.init($scope);
    //定义查询页
    var queryPage = new QueryPage(1,10,"cpnsId asc","false");
    
    //定义搜索框
    
    queryPage.addParam(new queryParam('卷id ','cpnsId','string','like'));
    queryPage.addParam(new queryParam('卷名称','cpnsName','string','like'));
    var typeCode = new queryParam('卷类型','typeCode','string','='); 
    typeCode.componentType ='select';
    typeCode.showField='propertyName';
    typeCode.returnField='propertyValue';
    queryPage.addParam(typeCode);
    
    listService.initQueryItems($scope,typeCode,initTaskUrl);
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);
    
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
	 $scope.execute = function(operation, param){
	    	var selectData = $scope.gridApi.selection.getSelectedRows();
	    	if(operation == 'viewCpns'){
	    		if(param.typeCode=="-1"){
	    			var map = {'id' : param.cpnsId,'name' : param.cpnsName};
		    		listService.editData($scope, $scope.gridApi, new popup("查看券码","/scoreCoupsDataDetail/viewCoupons", map, "1200", "800"),param);
	    		}else{
	    			var map = {'id' : param.cpnsId,'name' : param.cpnsName};
		    		listService.editData($scope, $scope.gridApi, new popup("查看券码","/scoreCoupsDataThirdDetail/viewCoupons", map, "1200", "800"),param);
	    		}
    			
	    	}
	    }
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope, 'api/scoreData/queryScoreCoups', $scope.tableGrid);
    }
    
    load();

	$scope.search = function() {
		load();
	};
	
})
/****
 * 明细Controller
 */
angular.module('huatek.controllers').controller('ScoreCoupsDataDetailController', function ($scope,$http,$rootScope,listService,httpService,cacheService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '兑换时间', field: 'startTime',width: '15%', enableColumnMenu: false},      
		       { name: '券码', field: 'coupCode',width: '15%', enableColumnMenu: false},
		       { name: '券有效期', field: 'endTime',width: '15%', enableColumnMenu: false},
		       { name: '是否兑换', field: 'exchangeStatus1',width: '7%', enableColumnMenu: false,decode:{field:'exchangeStatus',dataKey:'dic.exchange_status'}},		     
		       { name: '绑定会员', field: 'memberName',width: '15%', enableColumnMenu: false},
		       { name: '劵状态', field: 'coupStatus1',width: '7%', enableColumnMenu: false,decode:{field:'coupStatus',dataKey:'dic.coupons_status'}},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope,$http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    queryPage.addParam(new queryParam('券码','coupCode','string','like'));
    queryPage.addParam(new queryParam('绑定会员','memberId.tel','string','like'));
    var exchangeStatus = new queryParam('兑换状态','exchangeStatus','string','=','','dic.exchange_status');
    exchangeStatus.componentType = 'select';
    queryPage.addParam(exchangeStatus);
    var exchangeStatus = new queryParam('使用状态','coupStatus','string','=','','dic.coupons_status');
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
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	
    	listService.loadData($scope,'api/scoreData/queryScoreCoups/'+$scope.passParams.id, $scope.tableGrid);
    	$scope.cpnsName = $scope.passParams.name;
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});
angular.module('huatek.controllers').controller('ScoreCoupsDataThirdDetailController', function ($scope,$http,$rootScope,listService,httpService,cacheService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '兑换时间', field: 'startTime',width: '15%', enableColumnMenu: false},      
		       { name: '券码', field: 'coupCode',width: '15%', enableColumnMenu: false},
		       { name: '券有效期', field: 'endTime',width: '15%', enableColumnMenu: false},
		       { name: '是否兑换', field: 'exchangeStatus1',width: '7%', enableColumnMenu: false,decode:{field:'exchangeStatus',dataKey:'dic.exchange_status'}},		     
		       { name: '绑定会员', field: 'memberName',width: '15%', enableColumnMenu: false},
		       { name: '劵状态', field: 'coupStatus1',width: '7%', enableColumnMenu: false,decode:{field:'coupStatus',dataKey:'dic.coupons_status'}},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope,$http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    queryPage.addParam(new queryParam('券码','coupCode','string','like'));
    queryPage.addParam(new queryParam('绑定会员','phiMember.tel','string','like'));
    var exchangeStatus = new queryParam('兑换状态','exchangeStatus','string','=','','dic.exchange_status');
    exchangeStatus.componentType = 'select';
    queryPage.addParam(exchangeStatus);
    var exchangeStatus = new queryParam('使用状态','coupStatus','string','=','','dic.coupons_status');
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
    	
    	listService.loadData($scope,'api/scoreData/queryScoreCoupsThird/'+$scope.passParams.id, $scope.tableGrid);
    	$scope.cpnsName = $scope.passParams.name;
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});
