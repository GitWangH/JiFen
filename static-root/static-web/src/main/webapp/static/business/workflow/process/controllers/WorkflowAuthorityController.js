'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowAuthorityController', function ($scope, $location, $routeParams,$http,$rootScope, $modal,listService) {
	$scope.workflowBlueId=$routeParams.workflowBlueId;
	var codeUrl="api_workflow/workflowAuthority/code";
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
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    var typeQuery=new queryParam('授权类型','type','string','=');
    typeQuery.componentType="select";
    queryPage.addParam(typeQuery);
    queryPage.addParam(new queryParam('授权对象','objectName','string','alllike'));
    
    /*定义搜索框*/
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage(queryPage);
    listService.initQueryParams(codeUrl);
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
    btnArray.push(new pageButton('添加人员','addPerson','/workflowAuthority/addPerson','addPerson'));
	btnArray.push(new pageButton('添加角色','addGroup','/workflowAuthority/addGroup','addGroup'));
	btnArray.push(new pageButton('删除','delete','/workflowAuthority/delete','deleteData'));
	btnArray.push(new pageButton('返回','goBack','/workflowBlue/home','goBack'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='addPerson'){
    		var scope = $rootScope.$new();
            scope.workflowBlueId=$scope.workflowBlueId;
            scope.popCallBack=function(){
        		load();
        	}
			var modal= $modal({
    		     show: false,
    		     backdrop: 'static',
    		      controller: "WorkflowAuthorityAddPersonController",
    		     template: TemplatePrefix+"workflow/process/listSelect.html"+'?t='+huatek.version,
    		     scope:scope
    		 });
			modal.$promise.then(modal.show)
    	}else if(operation=='addGroup'){
    		var scope = $rootScope.$new();
            scope.workflowBlueId=$scope.workflowBlueId;
            scope.popCallBack=function(){
        		load();
        	}
			var modal= $modal({
    		     show: false,
    		     backdrop: 'static',
    		      controller: "WorkflowAuthorityAddGroupController",
    		     template: TemplatePrefix+"workflow/process/listSelect.html"+'?t='+huatek.version,
    		     scope:scope
    		 });
			modal.$promise.then(modal.show)
    	}else if(operation=='deleteData'){
    		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_workflow/workflowAuthority/delete');
    	}else if(operation=='goBack'){
    		$location.path("/workflowBlue/home");
    	}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData('api_workflow/workflowAuthority/query/'+$scope.workflowBlueId, $scope.tableGrid);
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
angular.module('huatek.controllers').controller('WorkflowAuthorityAddPersonController', function ($scope, $location, $http, editService,$rootScope) {
	
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
		    columnDefs: [
		       { name: '总公司', field: 'level1Name',width: '20%', enableColumnMenu: false, grouping: {groupPriority: 0}},
		       { name: '下级组织', field: 'level2Name',width: '20%', enableColumnMenu: false, grouping: {groupPriority: 1}},
		       { name: '用户名', field: 'userName',width: '20%', enableColumnMenu: false},
		       { name: '姓名', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '邮箱', field: 'email',width: '20%', enableColumnMenu: false},
		       { name: '电话', field: 'phone',width: '20%', enableColumnMenu: false}
		    ]
		    
	  };
	
	
	
    
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
	
	
	 var addPersonUrl = 'api_workflow/workflowAuthority/addPerson/'+$scope.workflowBlueId;
	 var homeUrl = '/workflowAuthority/home/'+$scope.workflowBlueId;


	
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
    		$scope.$hide();
    		if($scope.popCallBack){
    			$scope.popCallBack();
    		}
    	});
    } 
   
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('WorkflowAuthorityAddGroupController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
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
	
	
	 var addPersonUrl = 'api_workflow/workflowAuthority/addGroup/'+$scope.workflowBlueId;


	
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
    		$scope.$hide();
    		if($scope.popCallBack){
    			$scope.popCallBack();
    		}
    	});
    } 
   
});
