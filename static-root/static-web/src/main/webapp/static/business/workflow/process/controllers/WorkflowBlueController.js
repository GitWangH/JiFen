'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowBlueController', function ($scope, $location, $http,$rootScope, listService) {
	var codeUrl="api_workflow/workflowBlue/code";
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '流程分类', field: 'departmentName',width: '20%', enableColumnMenu: false,decode:{field:'department',dataKey:'dic.flow_class'}},
		       { name: '流程名称', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '标识key', field: 'processDefineKey',width: '20%', enableColumnMenu: false},
		       { name: '流程类型', field: 'typeName',width: '10%', enableColumnMenu: false},
		       { name: '表单类型', field: 'formTypeName',width: '10%', enableColumnMenu: false},
		       { name: '查看表单', field: 'formImpl',width: '20%', enableColumnMenu: false},
		       { name: '打印表单', field: 'formPrint',width: '20%', enableColumnMenu: false}
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    
    /*定义搜索框*/
    queryPage.addParam(new queryParam('流程名称','name','string','alllike'));
    queryPage.addParam(new queryParam('标识key','processDefineKey','string','alllike'));
    var workflowType=new queryParam('流程类型','type','string','=');
    workflowType.componentType="select";
    queryPage.addParam(workflowType);
    var department=new queryParam('流程分类','department','string','=')
    department.componentType="select";
    department.dropDataUrl="dic.flow_class";
    queryPage.addParam(department);
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
	      listService.setGridHeight();
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('添加','add','/workflowBlue/add','addData'));
    btnArray.push(new pageButton('编辑','edit','/workflowBlue/edit','editData'));
    btnArray.push(new pageButton('流程节点','node','/workflowNode/home','nodeData'));
    /**
    btnArray.push(new pageButton('流程发起授权','authory','/workflowAuthority/home','authoryData'));
    */
    btnArray.push(new pageButton('设置表单','form','/workflowBlue/form','formData'));
    btnArray.push(new pageButton('删除','delete','/workflowBlue/delete','deleteData'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
    		listService.addData($scope,new popup('新增流程方案','/workflowBlue/add'));
        }
  	  	else if(operation=='editData'){
    		listService.editData($scope,$scope.gridApi, new popup('编辑流程方案','/workflowBlue/edit'));
   	  	}
       	else if(operation=='deleteData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api_workflow/workflowBlue/delete');
  		}
    	else if(operation=='nodeData'){
    		listService.editData($scope,$scope.gridApi,new popup('流程节点管理', '/workflowNode/home',null,null,600));
  		}else if(operation=='authoryData'){
  			listService.editData($scope,$scope.gridApi, new popup('流程发起授权','/workflowAuthority/home'));
  		}else if(operation=='formData'){
  			listService.editData($scope,$scope.gridApi, new popup('流程发起表单设置','/workflowBlue/form'));
  		}
    }
    
    listService.initQueryParams($scope,codeUrl);
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api_workflow/workflowBlue/query', $scope.tableGrid);
    }
    $scope.load=load;
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
angular.module('huatek.controllers').controller('WorkflowBlueAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api_workflow/workflowBlue/add';
	 var editDataUrl = 'api_workflow/workflowBlue/edit';
	 var homeUrl = '/workflowBlue/home';
	 var codeUrl="api_workflow/workflowBlue/code";
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
  /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    		var department=new FormElement(1,'流程分类','department','string',0,'128','select');
    		department.dropDataUrl='dic.flow_class';
    		formFieldArray.push(department);
		  formFieldArray.push(new FormElement(1,'流程名称','name','string',1,'128'));
		  formFieldArray.push(new FormElement(1,'标识key','processDefineKey','string',1,'128'));
		  formFieldArray.push(new FormElement(1,'流程类型','type','string',1,'30','select'));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

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
    editService.initParams($scope,codeUrl);
    /***
     * 定义获取需要编辑的内容.
     */
    
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
angular.module('huatek.controllers').controller('WorkflowBlueFormController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var editUrl = 'api_workflow/workflowBlue/edit';
	 var codeUrl = 'api_workflow/workflowNode/code';
	 var homeUrl = '/workflowBlue/home';
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
  /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'表单类型','formType','string',1,'30',"select",'typeChange'));
		  formFieldArray.push(new FormElement(1,'查看表单','formImpl','string',1,'128'));
		  formFieldArray.push(new FormElement(1,'打印表单','formPrint','string',0,'128'));
		  var formCode=new FormElement(1,'表单选择','formCode','string',1,'128',"select-autocomplete");
		  formCode.isShow=false;
		  formFieldArray.push(formCode);
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

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
    editService.initParams($scope,codeUrl);
    /***
     * 定义获取需要编辑的内容.
     */
    $scope.typeChange=function(formType){
    	if(formType&&formType=="code"){
    		editService.getFieldMap($scope).get("formCode").isShow=true;
			editService.getFieldMap($scope).get("formImpl").isShow=false;
			editService.getFieldMap($scope).get("formPrint").isShow=false;
    	}else{
    		editService.getFieldMap($scope).get("formCode").isShow=false;
			editService.getFieldMap($scope).get("formImpl").isShow=true;
			editService.getFieldMap($scope).get("formPrint").isShow=true;
    	}
    };
    if($scope.editId){
    	editService.loadData($scope,editUrl, $scope.editId);
    	$scope.promise.then(function(){
    		var formType=editService.getFieldMap($scope).get("formType").value;
    		if(formType&&formType=="code"){
    			editService.getFieldMap($scope).get("formCode").value=editService.getFieldMap($scope).get("formImpl").value;
    			editService.getFieldMap($scope).get("formCode").isShow=true;
    			editService.getFieldMap($scope).get("formImpl").isShow=false;
    			editService.getFieldMap($scope).get("formPrint").isShow=false;
    			editService.getFieldMap($scope).get("formImpl").value=null;
    			editService.getFieldMap($scope).get("formPrint").value=null;
    			
        	}
    		
    	});
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	var formType=editService.getFieldMap($scope).get("formType").value;
		if(formType&&formType=="code"){
			editService.getFieldMap($scope).get("formImpl").value=editService.getFieldMap($scope).get("formCode").value;
    	}
    	editService.updateData($scope,editUrl, homeUrl, $scope.editId);
    } 
    
});
