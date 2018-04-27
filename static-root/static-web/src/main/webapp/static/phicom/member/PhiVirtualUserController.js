'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiVirtualUserController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '虚拟用户编号', field: 'id',width: '30%', enableColumnMenu: false},
		       { name: '用户昵称', field: 'userName',width: '40%', enableColumnMenu: false},
		       { name: '手机号码', field: 'mobile',width: '30%', enableColumnMenu: false},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    
    //$rootScope.searchCount = queryPage.queryParamList.length;
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
    btnArray.push(new pageButton('新增','add','/phiVirtualUser/add','addData'));
    btnArray.push(new pageButton('编辑','edit','/phiVirtualUser/edit','editData'));
    btnArray.push(new pageButton('删除', 'delete', '/phiVirtualUser/delete', 'deleteData'));
    listService.setButtonList($scope,btnArray);
    /***
     * 设置界面的功能按钮.
     */    
  // listService.setButtonList($scope,btnArray);

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
    	listService.loadData($scope,'api/phiVirtualUser/query', $scope.tableGrid);
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
angular.module('huatek.controllers').controller('PhiVirtualUserAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope,httpService) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiVirtualUser/add';
	 var editDataUrl = 'api/phiVirtualUser/edit';
	 var homeUrl = '/phiVirtualUser/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		 // formFieldArray.push(new FormElement(1,'虚拟用户id','id','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'用户昵称','userName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'手机号码','mobile','phone',1,'30'));
    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope);
    
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields($scope,formFieldArray); 
    
    /***
     * 定义获取需要编辑的内容.
     */
   // $scope.editId = $routeParams.id;
    
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
    	//editService.checkData($scope,3,mobile);
    }
});


