/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiGameUserController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '参与时间', field: 'createtime',width: '10%', enableColumnMenu: false},
		       { name: '游戏类型', field: 'gameType',width: '10%', enableColumnMenu: false},
		       { name: '会员名', field: 'userName',width: '10%', enableColumnMenu: false},
		       { name: '会员电话', field: 'tel',width: '10%', enableColumnMenu: false},
		       { name: '会员等级', field: 'memberGrade',width: '10%', enableColumnMenu: false},
		       { name: '游戏消耗积分', field: 'score',width: '10%', enableColumnMenu: false},
		       { name: '游戏获取积分', field: 'getscore',width: '10%', enableColumnMenu: false}
		       
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    //定义搜索框
    queryPage.addParam(new queryParam('游戏类型','gameType','string','like'));
    queryPage.addParam(new queryParam('会员名','userName','string','like'));
    queryPage.addParam(new queryParam('会员等级','memberGrade','string','like'));
    queryPage.addParam(new queryParam('游戏参与时间','createtime','date','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage(queryPage);
    
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
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    }
    
    /***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope,'api/phiGameUser/query'+$scope.passParams, $scope.tableGrid);
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});

