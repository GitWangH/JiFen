/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiGameController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		               { name: '序号', field: 'code',width: '10%', enableColumnMenu: false},
		  		       { name: '游戏类型', field: 'gameType',width: '10%', enableColumnMenu: false},
		  		       { name: '前台显示游戏名称', field: 'gameName',width: '10%', enableColumnMenu: false},
		  		       { name: '游戏说明', field: 'remark',width: '10%', enableColumnMenu: false},
		  		       { name: '单次消耗积分', field: 'oneTimeScore',width: '10%', enableColumnMenu: false},
		  		       { name: '单日免费次数', field: 'freeTimesDay',width: '10%', enableColumnMenu: false},
		  		       { name: '是否开启', field: 'MytaskSwitch',width: '10%', enableColumnMenu: false,decode: { field: "taskSwitch", dataKey: "dic.task_switch" }},
//		  		       { name: '是否开启', field: 'taskSwitch',width: '10%', enableColumnMenu: false},
		  		       { name: '操作人', field: 'operationPerson',width: '10%', enableColumnMenu: false},
		  		       { name: '末次操作时间', field: 'lastOperationTime',width: '10%', enableColumnMenu: false},
		  		       { name: '操作', field: 'operate',width: '10%', enableColumnMenu: false,cellTemplate:'<a style="cursor:pointer"><span ng-click="grid.appScope.edit(row.entity.id,row.entity.type)">编辑&nbsp;&nbsp;&nbsp;</span><span ng-if="row.entity.taskSwitch==\'off\'" ng-click="grid.appScope.changeState(row.entity.id,row.entity.taskSwitch)">任务开启</span><span ng-if="row.entity.taskSwitch==\'on\'" ng-click="grid.appScope.changeState(row.entity.id,row.entity.taskSwitch)">任务关闭</span><span ng-click="grid.appScope.GameUserList(row.entity.id)">&nbsp;&nbsp;&nbsp;参与用户</span></a>'}
		  		    ]
		    
	  };
	$scope.GameUserList = function(id) {
        var scope = $rootScope.$new();
        var pop = {
            title: "参与用户列表",
            passParams: id,
            onclosedFun: load,
            controller: 'PhiGameUserController',
            templateView: "/static/phicom/game/gameUsr_home.html",
            height:"820px",
            width:"1024px"

        };
        listService.popPanel($scope, pop);
    }
	 $scope.changeState = function(id, taskSwitch) {
    	 if(taskSwitch == 'on'){
			   var taskSwitch = 'off';
		   }else{
			   var taskSwitch = 'on';
		   }
        httpService.post($scope, 'api/phiGame/switch/' + id+ '/' + taskSwitch).success(function() {
        	load();
        });
    }
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
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
    
    //定义功能按钮
    var btnArray = [];
    listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if (operation == 'addData') {
            listService.addData($scope, new popup("新增", '/phiVirtualUser/add', $scope.treeParam));
        } else if (operation == 'editData') {
                listService.editData($scope, $scope.gridApi, new popup("修改", '/phiVirtualUser/edit'));
        } else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/phiVirtualUser/delete');
        } 
    }
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope,'api/phiGame/query', $scope.tableGrid);
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	/**@Fields type : 类型(bigWheel大转盘  ninePlace九宫格)*/
	$scope.edit = function(id,type) {
	    var scope = $rootScope.$new();
	    //var sudoku =  new popup("消费返积分 ",'',{'id':id},'','','','PhiPlusAllRightAddController',"/static/phicom/plusmember/template_add.html");
	    //listService.popPanel(scope, forPayPoints);
	    var ninePlace = {
	        title: "积分游戏配置-九宫格",
	        passParams: id,
	        onclosedFun: load,
	        controller: 'PhiGameConfigAddController',
	        templateView: "/static/phicom/game/template_game_config.html",
	    };
	    var bigWheel = {
		        title: "积分游戏配置-大转盘",
		        passParams: id,
		        onclosedFun: load,
		        controller: 'PhiGameConfigAddController',
		        templateView: "/static/phicom/game/template_game_config.html",
		    };
        switch (type) {
	        case "ninePlace":
	        	listService.popPanel(scope, ninePlace);
	            break;
	        case "bigWheel":
	            listService.popPanel($scope, bigWheel);
	            break;
	        default:
	            break;
        }    
	}
	
});

/****
 * 编辑控制器.参与用户列表
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
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
		       { name: '参与时间', field: 'createTime',width: '15%', enableColumnMenu: false},
		       { name: '游戏类型', field: 'gameType',width: '10%', enableColumnMenu: false},
		       { name: '会员名', field: 'usrName',width: '15%', enableColumnMenu: false},
		       { name: '会员电话', field: 'tel',width: '15%', enableColumnMenu: false},
		       { name: '会员等级', field: 'memberGrade',width: '15%', enableColumnMenu: false},
		       { name: '游戏消耗积分', field: 'score',width: '15%', enableColumnMenu: false},
		       { name: '游戏获取积分', field: 'getscore',width: '15%', enableColumnMenu: false}
		       
		    ]
		    
	  };
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
	      /*调整grid底部高度*/
	        listService.setGridHeight();
	  	}; 
	listService.init($scope,$http, $scope.tableGrid, $scope.gridApi, 'api/phiGameUsr/query/'+$scope.passParams);
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    //定义搜索框
    queryPage.addParam(new queryParam('游戏类型','gameType','string','alllike'));
    queryPage.addParam(new queryParam('会员名称','usrName','string','alllike'));
    queryPage.addParam(new queryParam('会员等级','memberGrade','string','alllike'));
    queryPage.addParam(new queryParam('游戏参与开始时间','createTime','date','>='));
    queryPage.addParam(new queryParam('游戏参与结束时间','createTime','date','<='));
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage); 
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
    	listService.loadData($scope,'api/phiGameUsr/query/'+$scope.passParams, $scope.tableGrid);
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});


