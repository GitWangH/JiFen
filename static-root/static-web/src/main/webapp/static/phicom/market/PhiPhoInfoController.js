'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
as.controller('PhiPhoInfoController', function ($scope, $location, $http,$rootScope, listService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '图片id', field: 'phoId',width: '10%', enableColumnMenu: false},
		       { name: '广告位code', field: 'adCode',width: '10%', enableColumnMenu: false},
		       { name: '图片url', field: 'phoUrl',width: '10%', enableColumnMenu: false},
		       { name: '图片位置', field: 'phoAddr',width: '10%', enableColumnMenu: false},
		       { name: '图片链接', field: 'phoLink',width: '10%', enableColumnMenu: false},
		       { name: '图片开始时间', field: 'phoStart',width: '10%', enableColumnMenu: false},
		       { name: '图片结束时间', field: 'phoEnd',width: '10%', enableColumnMenu: false},
		       { name: '图片排序标识', field: 'phoOrder',width: '10%', enableColumnMenu: false},
		       { name: '图片大小', field: 'phoSize',width: '10%', enableColumnMenu: false},
		       { name: '预留1', field: 'plan1',width: '10%', enableColumnMenu: false},
		       { name: '预留2', field: 'plan2',width: '10%', enableColumnMenu: false},
		       { name: '图片uuid名称', field: 'phoUuidName',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    //定义搜索框
    queryPage.addParam(new queryParam('图片id','phoId','string','like'));
    queryPage.addParam(new queryParam('广告位code','adCode','string','like'));
    queryPage.addParam(new queryParam('图片url','phoUrl','string','like'));
    queryPage.addParam(new queryParam('图片位置','phoAddr','string','like'));
    queryPage.addParam(new queryParam('图片链接','phoLink','string','like'));
    queryPage.addParam(new queryParam('图片开始时间','phoStart','string','like'));
    queryPage.addParam(new queryParam('图片结束时间','phoEnd','string','like'));
    queryPage.addParam(new queryParam('图片排序标识','phoOrder','string','like'));
    queryPage.addParam(new queryParam('图片大小','phoSize','string','like'));
    queryPage.addParam(new queryParam('预留1','plan1','string','like'));
    queryPage.addParam(new queryParam('预留2','plan2','string','like'));
    queryPage.addParam(new queryParam('图片uuid名称','phoUuidName','string','like'));
    
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
    var btnArray = [];
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api/phiPhoInfo/query', $scope.tableGrid);
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
as.controller('PhiPhoInfoAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiPhoInfo/add';
	 var editDataUrl = 'api/phiPhoInfo/edit';
	 var homeUrl = '/phiPhoInfo/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'图片id','phoId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'广告位code','adCode','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'图片url','phoUrl','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'图片位置','phoAddr','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'图片链接','phoLink','string',1,'100'));
		  formFieldArray.push(new FormElement(1,'图片开始时间','phoStart','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'图片结束时间','phoEnd','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'图片排序标识','phoOrder','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'图片大小','phoSize','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'预留1','plan1','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'预留2','plan2','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'图片uuid名称','phoUuidName','string',1,'30'));
    
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
