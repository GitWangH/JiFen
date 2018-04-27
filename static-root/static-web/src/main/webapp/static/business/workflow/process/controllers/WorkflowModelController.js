'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowModelController', function ($scope, $http,$rootScope,$modal, listService) {
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
		       { name: '流程名称', field: 'name',width: '40%', enableColumnMenu: false},
		       { name: '标识key', field: 'key',width: '20%', enableColumnMenu: false},
		       { name: '创建时间', field: 'createTime',width: '10%', enableColumnMenu: false},
		       { name: '最后修改时间', field: 'lastUpdateTime',width: '20%', enableColumnMenu: false},
		       { name: '版本', field: 'version',width: '10%', enableColumnMenu: false}
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
    queryPage.addParam(new queryParam('流程名称','name','string','like'));
    
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
    btnArray.push(new pageButton('添加','add','/workflowModel/add','addData'));
    btnArray.push(new pageButton('编辑','edit','/workflowModel/edit','editData'));
    btnArray.push(new pageButton('流程设计','dsign','/workflowModel/dsign','dsignData'));
    btnArray.push(new pageButton('流程部署','deploy','/workflowModel/deploy','deployData'));
    btnArray.push(new pageButton('下载','down','/workflowModel/dsign','downData'));
    btnArray.push(new pageButton('上传','up','/workflowModel/dsign','upData'));
    btnArray.push(new pageButton('删除','delete','/workflowModel/delete','deleteData'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
    		listService.addData($scope,new popup('新增流程','/workflowModel/add'));
        }
  	  	else if(operation=='editData'){
    		listService.editData($scope,$scope.gridApi, new popup('编辑流程','/workflowModel/edit'));
   	  	}else if(operation=='dsignData'){
	   	  	if($scope.gridApi.selection.getSelectedRows().length < 1){
				submitTips('请至少勾选一条数据！','warning');
				return false;
			}
	    	if($scope.gridApi.selection.getSelectedRows().length > 1){
				submitTips('请勾选一条数据！','warning');
				return false;
			}
	    	
	    	var pop={
	    		title:'流程设计',
	    		controller:'WorkflowModelDesignController',
	    		passParams:$scope.gridApi.selection.getSelectedRows()[0].id,
	    		templateView:'static/business/workflow/process/templates/processDsign.html'
	    	}
	    	listService.popPanel($scope,pop)
	    
	    	
   	  	}
       	else if(operation=='deleteData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api_workflow/workflowModel/delete');
  		}else if(operation=='deployData'){
  			var ids=[];
  			if($scope.gridApi.selection.getSelectedRows().length < 1){
				submitTips('请至少勾选一条数据！','warning');
				return false;
			}
	    	for(var i=0;i<$scope.gridApi.selection.getSelectedRows().length;i++){
	    		ids.push($scope.gridApi.selection.getSelectedRows()[i].id);
	    	}
  			$scope.promise=$http.post("api_workflow/workflowModel/deploy/"+ids.join("_")).success(function(response){
  				
          /*load();*/
  			});
  		}else if(operation=='downData'){
  			var ids=[];
  			if($scope.gridApi.selection.getSelectedRows().length < 1){
				submitTips('请至少勾选一条数据！','warning');
				return false;
			}
  			for(var i=0;i<$scope.gridApi.selection.getSelectedRows().length;i++){
	    		ids.push($scope.gridApi.selection.getSelectedRows()[i].id);
	    	}
	    	window.location.href="api_workflow/workflowModel/export/"+ids.join("_");
  		}else if(operation=='upData'){
  			if($scope.gridApi.selection.getSelectedRows().length < 1){
				submitTips('请至少勾选一条数据！','warning');
				return false;
			}
	    	if($scope.gridApi.selection.getSelectedRows().length > 1){
				submitTips('请勾选一条数据！','warning');
				return false;
			}
			var pop={
		    		title:'流程上传',
		    		controller:'WorkflowModelFileController',
		    		passParams:$scope.gridApi.selection.getSelectedRows()[0].id,
		    		templateView:'static/business/workflow/process/templates/fileupload.html',
		    		width:400,
		    		height:350
		    	}
		    	listService.popPanel($scope,pop)
  		}
    }
    
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api_workflow/workflowModel/query', $scope.tableGrid);
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
angular.module('huatek.controllers').controller('WorkflowModelAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api_workflow/workflowModel/add';
	 var editDataUrl = 'api_workflow/workflowModel/edit';
	 var homeUrl = '/workflowModel/home';
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
  /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'流程名称','name','string',1,'60'));
		  formFieldArray.push(new FormElement(1,'标识key','key','string',1,'128'));
    
    
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

angular.module('huatek.controllers').controller('WorkflowModelFileController', function ($scope, $location, $http,$document, $routeParams, editService,$rootScope) {
	editService.init($scope);
    $scope.submit=function(){
    	var fd = new FormData();
        var file = $scope.file;
        fd.append('file', file); 
        $scope.promise=$http({
              method:'POST',
              url:"api_workflow/workflowModel/import/"+$scope.modelId,
              data: fd,
              headers: {'Content-Type':undefined},
              transformRequest: angular.identity 
          }).success( function ( response )
          {
               $scope.back();
          }); 
    }
 });
angular.module('huatek.controllers').controller('WorkflowModelDesignController', function ($scope) {
	
    $scope.modelSrc="static/business/workflow/process-editor/modeler.html?modelId="+$scope.passParams;

 });


