'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('FormWorkflowVariablesController', function ($scope, $location, $http,$rootScope, $routeParams,listService) {
	$scope.formId = $routeParams.formId;
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: false,
		    columnDefs: [
		       { name: '变量名称', field: 'name',width: '30%', enableColumnMenu: false},
		       { name: '代码', field: 'code',width: '30%', enableColumnMenu: false},
		       { name: '表单字段', field: 'formFieldConfigName',width: '30%', enableColumnMenu: false},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    
    /*定义搜索框*/
    
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
	     
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
        btnArray.push(new pageButton('添加','add','/formWorkflowVariables/add','addData'));
        btnArray.push(new pageButton('删除','delete','/formWorkflowVariables/delete','deleteData'));
        btnArray.push(new pageButton('返回','back','/formConfig/home','goBack'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
	        if(operation=='addData'){
        		listService.addData('/formWorkflowVariables/add/'+$scope.formId);
	        }
	       	else if(operation=='deleteData'){
        		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_workflow/formWorkflowVariables/delete');
      		}else if(operation=='goBack'){
	       		$location.path("/formConfig/home");
      		}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	$http.post('api_workflow/formWorkflowVariables/query/'+$scope.formId)
		   .success(function (response){
			   if(response){
				   $scope.tableGrid.data=response;
			   }else{
				   $scope.tableGrid.data=[];
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

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('HuatekApp').controller('FormWorkflowVariablesAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 $scope.editId = $routeParams.id;
	 $scope.formId = $routeParams.formId;
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api_workflow/formWorkflowVariables/add/'+$scope.formId;
	 var editDataUrl = 'api_workflow/formWorkflowVariables/edit/'+$scope.formId;
	 var homeUrl = '/formWorkflowVariables/home/'+$scope.formId ;
	 var codeUrl='api_workflow/formWorkflowVariables/code/'+$scope.formId;
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'变量名称','name','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'代码','code','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'表单属性','formFieldConfigId','string',1,'30',"select"));
    
    
    /*设置全局变量*/
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
    editService.initParams(codeUrl); 
    /***
     * 定义获取需要编辑的内容.
     */
    
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