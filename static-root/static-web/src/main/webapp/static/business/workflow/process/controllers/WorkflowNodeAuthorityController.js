'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowNodeAuthorityController', function ($scope, $location, $routeParams,$http,$modal,$rootScope, listService) {
	$scope.workflowBlueId = $scope.passParams;
	$scope.workflowNodeId = $scope.editId;
	var codeUrl="api_workflow/workflowNodeAuthority/code"
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
		    columnDefs: [
		         { name: '授权类型', field: 'typeName',width: '30%', enableColumnMenu: false},
		         { name: '授权对象', field: 'objectName',width: '30%', enableColumnMenu: false},
		         { name: '人员范围', field: 'orgTypeName',width: '30%', enableColumnMenu: false},   
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
    
    /*定义查询页*/
	 var queryPage = new QueryPage(1,10,"id desc","false");
	    var typeQuery=new queryParam('授权类型','type','string','=');
	    typeQuery.componentType="select";
	    queryPage.addParam(typeQuery);
	    queryPage.addParam(new queryParam('授权对象','objectName','string','alllike'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope,queryPage);
    listService.initQueryParams($scope,codeUrl);
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
    
    
    /*定义功能按钮*/
    var btnArray = [];
    	btnArray.push(new pageButton('添加人员','addPerson','/workflowNodeAuthority/addPerson','addPerson'));
    	btnArray.push(new pageButton('添加角色','addGroup','/workflowNodeAuthority/addGroup','addGroup'));
    	btnArray.push(new pageButton('人员范围','personScope','/workflowNodeAuthority/addGroup','personScope'));
    	btnArray.push(new pageButton('删除','delete','/workflowNodeAuthority/delete','deleteData'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='addPerson'){
    		
			var pop= {
    		     title: "添加人员",
    		     passParams:$scope.workflowNodeId,
    		     onclosedFun:load,
    		     paneltype: 'modal',
    		      controller: "WorkflowNodeAuthorityAddPersonController",
    		     templateView: 'static/business/workflow/process/templates/listSelect.html',
    		     
    		 };
			listService.popPanel($scope,pop);
    	}else if(operation=='addGroup'){
    			
			var pop= {
   		     title: "添加角色",
   		     passParams:$scope.workflowNodeId,
   		     onclosedFun:load,
   		  paneltype: 'modal',
   		      controller: "WorkflowNodeAuthorityAddGroupController",
   		     templateView: 'static/business/workflow/process/templates/listSelect.html',
   		     
   		 };
			listService.popPanel($scope,pop);
    	}else if(operation=='personScope'){
    		var selectData =$scope.gridApi.selection.getSelectedRows();
        	if(selectData.length>1){
        		bootbox.alert('警告：不能选择多条数据操作。');
        		return;
        	}
        	if(selectData.length==0){
        		bootbox.alert('请在列表中选择一条数据操作。');
        		return;
        	}
			var pop= {
		   		     title: "角色范围",
		   		     passParams:selectData[0].id,
		   		     onclosedFun:load,
		   		  paneltype: 'modal',
		   		      controller: "WorkflowNodeAuthorityPersonScopeController",
		   		     templateView: 'static/business/workflow/process/templates/formSelect.html',
		   		     
		   		 };
			listService.popPanel($scope,pop);
    	}else if(operation=='deleteData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api_workflow/workflowNodeAuthority/delete');
    	}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api_workflow/workflowNodeAuthority/query/'+$scope.workflowNodeId, $scope.tableGrid);
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
angular.module('huatek.controllers').controller('WorkflowNodeAuthorityAddPersonController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.workflowNodeId=$scope.passParams
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
		    columnDefs: [
		       { name: '用户名', field: 'userName',width: '20%', enableColumnMenu: false},
		       { name: '姓名', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '所属组织', field: 'orgName',width: '20%', enableColumnMenu: false},
		       { name: '邮箱', field: 'email',width: '20%', enableColumnMenu: false},
		       { name: '电话', field: 'phone',width: '20%', enableColumnMenu: false}
		    ]
		    
	  };
	
	editService.init($scope)
	$scope.btnArrayList=[];
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"level1 asc,level2 asc,level3 asc,level4 asc","false");
   
    queryPage.addParam(new queryParam('所属组织','orgName','string','alllike'));
    queryPage.addParam(new queryParam('用户名','userName','string','alllike'));
    queryPage.addParam(new queryParam('姓名','name','string','alllike'));
    
    /*定义搜索框*/
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    $scope.queryFieldList = queryPage.queryParamList;
    for(var i=0;i<$scope.queryFieldList.length;i++){
    	$scope.queryFieldList[i].isShowSelect=true;

	}
    $scope.resetSearch = function() {
		for (var i = 0; i < queryPage.queryParamList.length; i++) {
			queryPage.queryParamList[i].value = null;
			if(queryPage.queryParamList[i].type == 'dateTime'){
				queryPage.queryParamList[i].minValue = null;
				queryPage.queryParamList[i].maxValue = null;
			}
		}
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
	  	}; 
    
	var load = function(){
		var postQueryPage = copyQueryPage(queryPage);
		$scope.promise = $http.post('api_workflow/workflowAuthority/queryPerson', postQueryPage)
		   .success(function (response) {
			   if (response.totalRows == undefined || response.totalRows == 0) {
				   $scope.tableGrid.data = [];
			   } else{
				   $scope.tableGrid.data = response.content;
			   }
			   $scope.tableGrid.totalItems = response.totalRows;
			   
		});
	}
	    
	load();
		
	$scope.search = function() {
			load();
	};
	
	
	 var addPersonUrl = 'api_workflow/workflowNodeAuthority/addPerson/'+$scope.workflowNodeId;

    /***
     * 定义更新操作.
     */
    $scope.save = function(){
    	var rows=$scope.gridApi.selection.getSelectedRows();
    	if(rows.length==0){
    		submitTips("请至少勾选一条数据！",'warning');
    		return;
    	}
    	
    	
    	$http.post(addPersonUrl,rows).success(function(response){
    		$scope.back();
    	});
    } 
   
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('WorkflowNodeAuthorityAddGroupController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.workflowNodeId=$scope.passParams
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 100,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
		    columnDefs: [
		       { name: '角色编码', field: 'code',width: '20%', enableColumnMenu: false},
		       { name: '角色名称', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '角色描述', field: 'comments',width: '60%', enableColumnMenu: false},
		    ]
		    
	  };
	
	editService.init($scope)
	$scope.btnArrayList=[];
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,100,"id desc ","false");
   
    var codeParam = new queryParam('角色编码','code','string','like');
    queryPage.addParam(codeParam);
    var nameParam = new queryParam('角色名称','name','string','like');
    queryPage.addParam(nameParam);
    
    /*定义搜索框*/
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    $scope.queryFieldList = queryPage.queryParamList;
    for(var i=0;i<$scope.queryFieldList.length;i++){
    	$scope.queryFieldList[i].isShowSelect=true;

	}
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
    
	var load = function(){
		var postQueryPage = copyQueryPage(queryPage);
		$scope.promise = $http.post('api_workflow/workflowAuthority/queryGroup', postQueryPage)
		   .success(function (response) {
			   if (response.totalRows == undefined || response.totalRows == 0) {
				   $scope.tableGrid.data = [];
			   } else{
				   $scope.tableGrid.data = response.content;
			   }
			   $scope.tableGrid.totalItems = response.totalRows;
			   
		});
	}
	    
	load();
		
	$scope.search = function() {
		load();
	};

	$scope.resetSearch = function() {
		codeParam.value = null;
		nameParam.value = null;
	};
	
	
	 var addPersonUrl = 'api_workflow/workflowNodeAuthority/addGroup/'+$scope.workflowNodeId;

	
	
    /***
     * 定义更新操作.
     */
    $scope.save = function(){
    	var rows=$scope.gridApi.selection.getSelectedRows();
    	if(rows.length==0){
    		submitTips("请至少勾选一条数据！",'warning');
    		return;
    	}
    	
    	
    	$http.post(addPersonUrl,rows).success(function(response){
    		$scope.back();
    	});
    } 
});
angular.module('huatek.controllers').controller('WorkflowNodeAuthorityPersonScopeController', function ($scope, $location, $http, $routeParams, cacheService,editService,$rootScope) {
	$scope.workflowNodeAuthorityId=$scope.passParams 
	/***
	  * 定义编辑跳转的路径.
	  */
	 var saveUrl = 'api_workflow/workflowNodeAuthority/saveScope/'+$scope.workflowNodeAuthorityId;
	 var codeUrl="api_workflow/workflowNodeAuthority/code";
	
  	
  	/*定义块*/
   var columnWrapArray = [];
   columnWrapArray.push(new multipleColumn(1,'选择组织类型'));
   $scope.columnWrapArray = columnWrapArray;
   
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
   var orgType=new FormElement(1,'组织类型','orgType','string',1,'30',"select");
   var formFieldArray = [];
		  formFieldArray.push(orgType);
		  var items=angular.copy(cacheService.getData("dic.org_type"));
		  items.push({code:'-1',name:'本部门'});
		  resolveShowFieldAndReturnField(orgType,items);
		  orgType.items=items;
   
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
    * 定义更新操作.
    */
   $scope.save = function(){
	   if(!editService.isAllowPost($scope)){
			return;
	   }
	   var value=editService.getFieldMap($scope).get("orgType").value;
	   var typeName=""
	   for(var i=0;i<items.length;i++){
		   if(value==items[i].code){
			   typeName=items[i].name;
			   break;
		   }
	   }
   		$http.post(saveUrl,{code:value,name:typeName}).success(function(response){
   			$scope.back();
   		});
   } 
  
});
