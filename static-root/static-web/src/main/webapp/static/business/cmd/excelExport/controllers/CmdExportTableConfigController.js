'use strict';
   
angular.module('huatek.controllers').controller('CmdExportTableConfigController', function ($scope, $location,$routeParams, $http,$rootScope, listService) {
	var configId= $scope.editId;
	$scope.editId=null;
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '表名', field: 'tableName',width: '20%', enableColumnMenu: false},
		       { name: '表中文名', field: 'tableDesc',width: '20%', enableColumnMenu: false},
		       { name: '表别名', field: 'tableAliasName',width: '20%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope, $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
       
    queryPage.addParam(new queryParam('表名','tableName','string','like'));
    queryPage.addParam(new queryParam('表别名','tableAliasName','string','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage($scope,queryPage);
    
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
    btnArray.push(new pageButton('新增','add','/cmdExportTableConfig/add','addData'));
	btnArray.push(new pageButton('编辑','edit','/cmdExportTableConfig/edit','editData'));
	btnArray.push(new pageButton('删除','delete','/cmdExportTableConfig/delete','deleteData'));
          
   listService.setButtonList($scope,btnArray);

      
   $scope.execute = function(operation, param){
	    if(operation=='addData'){
       		listService.addData($scope,new popup('新增导出关联表','/cmdExportTableConfig/add/',configId,null,null,load));
	    }else if(operation=='editData'){
       		listService.editData($scope,$scope.gridApi, new popup('编辑导出关联表','/cmdExportTableConfig/edit/',configId,null,null,load));
      	}else if(operation=='deleteData'){
       		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, URL_PATH.CMD_HEADER+'/cmdExportTableConfig/delete');
     	}
   }
	   
    var load = function(){
    	listService.loadData($scope,'api_cmd/cmdExportTableConfig/query/'+configId, $scope.tableGrid);
    }
    $scope.load=load;
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('huatek.controllers').controller('CmdExportTableConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	var configId= $scope.passParams; 
	   
	 var addDataUrl = URL_PATH.CMD_HEADER+'/cmdExportTableConfig/add/'+configId;
	 var editDataUrl = URL_PATH.CMD_HEADER+'/cmdExportTableConfig/edit/'+configId;
	 var homeUrl = '/cmdExportTableConfig/home/'+configId;
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'表名','tableName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'表别名','tableAliasName','string',1,'15'));
		  formFieldArray.push(new FormElement(1,'表中文名','tableDesc','string',1,'30'));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope);
    
       
    editService.setFormFields($scope,formFieldArray); 

    if($scope.editId){
    	editService.loadData($scope,editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
       
    $scope.update = function(){
    	editService.updateData($scope,editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData($scope,addDataUrl, homeUrl);
    }
});

   
