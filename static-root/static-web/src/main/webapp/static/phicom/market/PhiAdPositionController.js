'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiAdPositionController', function ($scope, $location, $http,$rootScope, listService) {
	
	var cellString = " <div class='ps-list'>"+
	"<img style='display: block;background-position: center;background-size: contain;background-repeat: no-repeat;width: 35px;height: 35px; margin: 0 auto' ng-src='api_file/view.do?id={{row.entity.phoUrl}}' ng-click='grid.appScope.getPreviewImages(row.entity)'>"+
	"</div>";
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '编号', field: 'adCode',width: '11%', enableColumnMenu: false},
		       { name: '配置位置', field: 'adAddress',width: '11%', enableColumnMenu: false},
		       { name: '客户端', field: 'client',width: '11%', enableColumnMenu: false},
		       { name: '规则', field: 'rule',width: '15%', enableColumnMenu: false},
		       { name: '广告图', field: 'phoUrl',width: '8%', enableColumnMenu: false,cellTemplate: cellString},
		       { name: '广告url', field: 'phoLink',width: '15%', enableColumnMenu: false},
		       { name: '操作人员', field: 'operatingPerson',width: '11%', enableColumnMenu: false},
		       { name: '末次操作时间', field: 'endoperatingtime',width: '11%', enableColumnMenu: false},
		       { name: '操作', field: 'operate',width: '6%', enableColumnMenu: false,cellTemplate:'<a><span ng-click="grid.appScope.openEdit(row.entity)">编辑</span></a>'}
		    ]
		    
	  };
	
	listService.init($scope);
	$scope.openEdit = function(entity){
		console.log(entity);
		if(entity.id == '1'){
			console.log('移动端首页banner位的编辑页面');
			console.log(entity.adAddress);
			console.log(entity.adCode);
			console.log(entity.client);
			var showEditAddress = entity.client+entity.adAddress;
			var array = [];
			array.push(showEditAddress);
			array.push(entity.adCode);
			var scope = $rootScope.$new();
	        var pop = {
	            title: "移动端首页banner位的编辑页面",
	            passParams: array,
	            onclosedFun: load,
	            controller: 'PhiMainBannerEditController',
	            templateView: "/static/phicom/market/mainBanner/template_main_banner.html",

	        };
	        listService.popPanel($scope, pop);
		}else if(entity.id == '2'){
			console.log('移动端首页的运营位的编辑页面');
			console.log(entity.adAddress);
			console.log(entity.client);
			var showEditAddress = entity.client+entity.adAddress;
			var array = [];
			array.push(showEditAddress);
			array.push(entity.adCode);
			var scope = $rootScope.$new();
		        var pop = {
		            title: "移动端首页的运营位的编辑页面",
		            passParams: array,
		            onclosedFun: load,
		            controller: 'PhiMainBusiController',
		            templateView: "/static/phicom/market/mainGame/template_games_banner.html",

		        };
		        listService.popPanel($scope, pop);
		}else if(entity.id == '3'){
			console.log('移动端_游戏');
			console.log(entity.adAddress);
			console.log(entity.client);
			var showEditAddress = entity.client+entity.adAddress;
			var array = [];
			array.push(showEditAddress);
			array.push(entity.adCode);
			var scope = $rootScope.$new();
		        var pop = {
		            title: "移动端_游戏",
		            passParams: array,
		            onclosedFun: load,
		            controller: 'phiMainGamesController',
		            templateView: "/static/phicom/market/mainGame/template_games_banner.html",
	
		        };
		        listService.popPanel($scope, pop);
		}else if (entity.id == '4'){
			/*console.log('移动端_热门推荐');
			console.log(entity.adAddress);
			console.log(entity.client);*/
			var showEditAddress = entity.client+entity.adAddress;
			var array = [];
			array.push(showEditAddress);
			array.push(entity.adCode);
			var scope = $rootScope.$new();
		        var pop = {
		            title: "移动端_热门推荐",
		            passParams: array,
		            onclosedFun: load,
		            controller: 'phiMainHotRecommendController',
		            templateView: "/static/phicom/market/mainGame/template_games_banner.html",
	
		        };
		        listService.popPanel($scope, pop);
			
		}else if (entity.id == '5'){
			console.log('移动端列表页筛选配置');
			var showEditAddress = entity.client+entity.adAddress;
			var array = [];
			array.push(showEditAddress);
			array.push(entity.adCode);
			var scope = $rootScope.$new();
		        var pop = {
		            title: "移动端_筛选推荐",
		            passParams: array,
		            onclosedFun: load,
		            controller: 'phiMainListConfigureController',
		            templateView: "/static/phicom/market/mainGame/template_games_banner.html",
	
		        };
		        listService.popPanel($scope, pop);
			
		}else if (entity.id == '6'){
			console.log('PC_游戏首页');
			var showEditAddress = entity.client+entity.adAddress;
			var array = [];
			array.push(showEditAddress);
			array.push(entity.adCode);
			var scope = $rootScope.$new();
		        var pop = {
		            title: "PC_游戏首页",
		            passParams: array,
		            onclosedFun: load,
		            controller: 'phiExchangeController',
		            templateView: "/static/phicom/market/mainGame/template_games_banner.html",
	
		        };
		        listService.popPanel($scope, pop);
			
		}else if (entity.id == '7'){
			console.log('PC_积分兑换运营位配置');
			var showEditAddress = entity.client+entity.adAddress;
			var array = [];
			array.push(showEditAddress);
			array.push(entity.adCode);
			var scope = $rootScope.$new();
		        var pop = {
		            title: "PC_积分兑换运营位配置",
		            passParams: array,
		            onclosedFun: load,
		            controller: 'phiPCMainGamesController',
		            templateView: "/static/phicom/market/mainGame/template_games_banner.html",
	
		        };
		        listService.popPanel($scope, pop);
			
		}
	}
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id asc","true");
    
    //定义搜索框
    queryPage.addParam(new queryParam('位置的客户端','client','string','like'));
    queryPage.addParam(new queryParam('位置规则','rule','string','like'));
    
    $scope.searchCount = queryPage.queryParamList.length;
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
    /*var btnArray = [];*/
    /***
     * 设置界面的功能按钮.
     */    
   /*listService.setButtonList($scope,btnArray);*/

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api/phiMarket/query', $scope.tableGrid);
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
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
as.controller('PhiAdPositionAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiAdPosition/add';
	 var editDataUrl = 'api/phiAdPosition/edit';
	 var homeUrl = '/phiAdPosition/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'标题','adTitle','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'副标题','adSubheading','string',1,'30'));
    
    //设置全局变量
    $scope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $http);
    
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields($scope,formFieldArray); 
    
    /***
     * 定义获取需要编辑的内容.
     */
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData($scope,editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	editService.updateData($scope,editDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 定义保存操作.
     */
    $scope.save = function(){
    	editService.saveData($scope,addDataUrl, homeUrl);
    }
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
