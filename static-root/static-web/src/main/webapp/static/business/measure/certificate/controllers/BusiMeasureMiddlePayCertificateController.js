'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
as.controller('BusiMeasureMiddlePayCertificateController', function ($scope, $location, $http,$rootScope, listService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '标段', field: 'orgName',width: '10%', enableColumnMenu: false},
		       { name: '计量周期', field: 'measurePeriodName',width: '10%', enableColumnMenu: false},
		       { name: '流程编号', field: 'flowId',width: '10%', enableColumnMenu: false},
		       { name: '审批结果', field: 'flowResult',width: '10%', enableColumnMenu: false},
		       { name: '创建人姓名', field: 'createrName',width: '10%', enableColumnMenu: false},
		       { name: '创建时间', field: 'createTime',width: '10%', enableColumnMenu: false},
		      
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
    queryPage.addParam(new queryParam('标段','orgId','string','like'));
    queryPage.addParam(new queryParam('计量周期','measurePeriodId','string','like'));
    
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
    btnArray.push(new pageButton('生成支付证书','add','/busiMeasureMiddlePayCertificate/add','addData'));
    btnArray.push(new pageButton('删除','delete','/busiMeasureMiddlePayCertificate/delete','deleteData'));
    btnArray.push(new pageButton('申请','report','/busiMeasureMiddlePayCertificate/report','reportData'));
    btnArray.push(new pageButton('查看审批流','showProcess','/busiMeasureMiddlePayCertificate/showProcess','showProcess'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
    		listService.addData($scope,new popup('新增支付证书','/busiMeasureMiddlePayCertificate/add',orgIdParam.value,null,null,load));
    	}else if(operation=='deleteData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api/busiMeasureMiddlePayCertificate/delete');
    	}else if(operation=='reportData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api/busiMeasureMiddlePayCertificate/delete');
    	}else if(operation=='showProcess'){
    		listService.showProcessProgress($scope);
    	}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api/busiMeasureMiddlePayCertificate/query', $scope.tableGrid);
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
as.controller('BusiMeasureMiddlePayCertificateAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/busiMeasureMiddlePayCertificate/add';
	 var editDataUrl = 'api/busiMeasureMiddlePayCertificate/edit';
	 var homeUrl = '/busiMeasureMiddlePayCertificate/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'主键','id','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'标段','orgId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'计量周期','measurePeriodId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'流程编号','flowId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'审批结果','flowResult','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'审批意见','flowMessage','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'创建人id','creater','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'创建人姓名','createrName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'创建时间','createTime','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'修改人id','modifer','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'修改人姓名','modifierName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'修改时间','modifyTime','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'租户id','tenantId','string',1,'30'));
    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $location, $http);
    
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields(formFieldArray); 
    
    /***
     * 定义获取需要编辑的内容.
     */
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 定义保存操作.
     */
    $scope.save = function(){
    	editService.saveData(addDataUrl, homeUrl);
    }
});
as.controller('BusiMeasureMiddlePayCertificateDetailController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/busiMeasureMiddlePayCertificate/add';
	 var editDataUrl = 'api/busiMeasureMiddlePayCertificate/edit';
	 var homeUrl = '/busiMeasureMiddlePayCertificate/home';
	
  	//定义块
   var columnWrapArray = [];
   columnWrapArray.push(new multipleColumn(1,'基本信息'));
   $scope.columnWrapArray = columnWrapArray;
   
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
   var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'主键','id','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'标段','orgId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'计量周期','measurePeriodId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'流程编号','flowId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'审批结果','flowResult','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'审批意见','flowMessage','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'创建人id','creater','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'创建人姓名','createrName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'创建时间','createTime','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'修改人id','modifer','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'修改人姓名','modifierName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'修改时间','modifyTime','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'租户id','tenantId','string',1,'30'));
   
   //设置全局变量
   $rootScope.formFieldArray = formFieldArray;

   /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $location, $http);
   
   /***
    * 设置编辑界面输入的字段.
    * 被设置必须在服务初始化之后执行.
    */
   editService.setFormFields(formFieldArray); 
   
   /***
    * 定义获取需要编辑的内容.
    */
   $scope.editId = $routeParams.id;
   
   if($scope.editId){
   	editService.loadData(editDataUrl, $scope.editId);
   }else{
   	$scope.editId = -1;
   }
   	
   /***
    * 定义更新操作.
    */
   $scope.update = function(){
   	editService.updateData(editDataUrl, homeUrl, $scope.editId);
   } 
   /**
    * 定义保存操作.
    */
   $scope.save = function(){
   	editService.saveData(addDataUrl, homeUrl);
   }
});
as.controller('BusiMeasureMiddlePayCertificateFlowController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/busiMeasureMiddlePayCertificate/add';
	 var editDataUrl = 'api/busiMeasureMiddlePayCertificate/edit';
	 var homeUrl = '/busiMeasureMiddlePayCertificate/home';
	
 	//定义块
  var columnWrapArray = [];
  columnWrapArray.push(new multipleColumn(1,'基本信息'));
  $scope.columnWrapArray = columnWrapArray;
  
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
  var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'标段','orgId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'计量周期','measurePeriodId','string',1,'30'));
  
  //设置全局变量
  $rootScope.formFieldArray = formFieldArray;

  /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $location, $http);
  
  /***
   * 设置编辑界面输入的字段.
   * 被设置必须在服务初始化之后执行.
   */
  editService.setFormFields(formFieldArray); 
  
  /***
   * 定义获取需要编辑的内容.
   */
  $scope.editId = $routeParams.id;
  
  if($scope.editId){
  	editService.loadData(editDataUrl, $scope.editId);
  }else{
  	$scope.editId = -1;
  }
  	
  /***
   * 定义更新操作.
   */
  $scope.update = function(){
  	editService.updateData(editDataUrl, homeUrl, $scope.editId);
  } 
  /**
   * 定义保存操作.
   */
  $scope.save = function(){
  	editService.saveData(addDataUrl, homeUrl);
  }
});



