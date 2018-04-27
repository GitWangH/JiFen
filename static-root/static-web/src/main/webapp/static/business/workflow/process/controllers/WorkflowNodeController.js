'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowNodeController', function ($scope, $location, $http,$rootScope, $routeParams,listService) {
	$scope.workflowBlueId = $scope.editId;
	/***
	 * 定义显示的表格.
	 */
	$scope.localData={};
	$scope.localData['canSetPersonMap']=[{code:true,name:'是'},{code:false,name:'否'}];
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 100,
		    useExternalPagination: false,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
		    columnDefs: [
		       { name: '节点标识', field: 'elementId',width: '15%', enableColumnMenu: false},
		       { name: '节点名称', field: 'elementName',width: '20%', enableColumnMenu: false},
		       { name: '办理时长(天)', field: 'dueDay',width: '10%', enableColumnMenu: false},
		       { name: '指定审批人', field: 'canSetPerson1',width: '10%', enableColumnMenu: false,decode:{field:'canSetPerson',dataKey:'local.canSetPersonMap'}},
		       { name: '表单类型', field: 'formTypeName',width: '20%', enableColumnMenu: false},
		       { name: '表单code', field: 'formImpl',width: '25%', enableColumnMenu: false},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
    
    /*定义查询页*/
  
    
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('刷新节点','refresh','/workflowNode/refresh','refreshData'));
    btnArray.push(new pageButton('设置时长','due','/workflowNode/due','dueData'));
    btnArray.push(new pageButton('可否指定审批人','canSetPerson','/workflowNode/canSetPerson','setPersonData'));
    btnArray.push(new pageButton('设置表单','form','/workflowNode/form','formData'));
    btnArray.push(new pageButton('节点审批授权','authority','/workflowNodeAuthority/home','authority'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='refreshData'){
    		$http.get('api_workflow/workflowNode/refresh/'+$scope.workflowBlueId).success(function(response){
    			$scope.search();
    		});;
        }else if(operation=='dueData'){
    		listService.editData($scope,$scope.gridApi, new popup('办理时长设置','/workflowNode/due',$scope.workflowBlueId,null,null,load,null,null,'modal'));
   	  	}else if(operation=='setPersonData'){
    		listService.editData($scope,$scope.gridApi, new popup('指定办理人设置','/workflowNode/canSetPerson',$scope.workflowBlueId,null,null,load,null,null,'modal'));
   	  	}
  	  	else if(operation=='formData'){
    		listService.editData($scope,$scope.gridApi, new popup('设置办理表单','/workflowNode/form',$scope.workflowBlueId,null,null,load,null,null,'modal'));
   	  	}else if(operation=='authority'){
   	  		listService.editData($scope,$scope.gridApi, new popup('节点审批授权','/workflowNodeAuthority/home',$scope.workflowBlueId,null,600,load,null,null,'modal'));
   	  	}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	$http.get('api_workflow/workflowNode/query/'+$scope.workflowBlueId).success(function(response){
    		listService.decodeTable($scope,response,$scope.tableGrid,$scope);
    		$scope.tableGrid.data = response;
    	});
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

angular.module('huatek.controllers').controller('WorkflowNodeDueController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.workflowBlueId = $scope.passParams;
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var editUrl = 'api_workflow/workflowNode/edit';
	 var homeUrl = '/workflowNode/home/'+$scope.workflowBlueId;
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'办理时长','dueDay','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'应用到全部','isAll','boolean',0,'30',"checkbox"));
    
    
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
    /***
     * 定义获取需要编辑的内容.
     */
   
    if($scope.editId){
    	editService.loadData($scope,editUrl, $scope.editId);
    	
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	editService.updateData($scope,editUrl, homeUrl, $scope.editId);
    } 
    
});
angular.module('huatek.controllers').controller('WorkflowNodeCanSetPersonController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.workflowBlueId = $scope.passParams;;
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var editUrl = 'api_workflow/workflowNode/edit';
	 var homeUrl = '/workflowNode/home/'+$scope.workflowBlueId;
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'是否可以指定审批人','canSetPerson','string',1,'30','checkbox'));
		  formFieldArray.push(new FormElement(1,'应用到全部','isAll','boolean',0,'30',"checkbox"));
    
    
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
    /***
     * 定义获取需要编辑的内容.
     */
   
    if($scope.editId){
    	editService.loadData($scope,editUrl, $scope.editId);
    	
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	editService.updateData($scope,editUrl, homeUrl, $scope.editId);
    } 
    
});
angular.module('huatek.controllers').controller('WorkflowNodeFormController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.workflowBlueId = $scope.passParams;
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var editUrl = 'api_workflow/workflowNode/edit';
	 var codeUrl = 'api_workflow/workflowNode/code';
	 var homeUrl = '/workflowNode/home/'+$scope.workflowBlueId;
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'表单类型','formType','string',1,'30',"select",'typeChange'));
		  formFieldArray.push(new FormElement(1,'表单url','formImpl','string',1,'128'));
		  var formCode=new FormElement(1,'表单选择','formCode','string',1,'30',"select-autocomplete");
		  formCode.isShow=false;
		  formFieldArray.push(formCode);
		  formFieldArray.push(new FormElement(1,'应用到全部','isAll','boolean',0,'30',"checkbox"));
    
    
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
    	}else{
    		editService.getFieldMap($scope).get("formCode").isShow=false;
			editService.getFieldMap($scope).get("formImpl").isShow=true;
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
    			editService.getFieldMap($scope).get("formImpl").value=null;
    			
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
