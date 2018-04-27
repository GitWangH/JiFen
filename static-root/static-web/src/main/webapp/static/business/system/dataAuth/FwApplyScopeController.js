'use strict';
   
angular.module('huatek.controllers').controller('FwApplyScopeController', function ($scope, $location, $http, listService, $routeParams) {
	
    
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
		      { name: '目标类', field: 'targetClass',width: '30%',enableColumnMenu: false },
		      { name: '目标字段', field: 'targetField',width: '30%',enableColumnMenu: false },
		      { name: '业务模块名称', field: 'businessMapName',width: '30%',enableColumnMenu: false }
		    ]
	  };
	   
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	      listService.setGridHeight();
	  	}; 
	   
	listService.init($scope, $http);
       
    var queryPage = new QueryPage(1,10,"targetClass,targetField","false");
       
    queryPage.addParam(new queryParam('业务模块名称','businessMapName','string','like'));
    queryPage.addParam(new queryParam('目标类','targetClass','string','like'));
       
    listService.setQueryPage($scope,queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/fwApplyScope/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/fwApplyScope/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/fwApplyScope/delete','deleteData'));
          
   listService.setButtonList($scope,btnArray);
   $scope.parentId=$scope.editId;
   $scope.editId=null;
       
    $scope.execute = function(operation, param){
        if(operation=='addData'){
        	listService.addData($scope,new popup('新增类','/fwApplyScope/add',$scope.parentId,null,null,load));
        }else if(operation=='deleteData'){  
        	listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api/fwApplyScope/delete');
        }else if(operation=='editData'){
        	listService.editData($scope,$scope.gridApi, new popup('编辑类','/fwApplyScope/edit',$scope.parentId,null,null,load));
        }
    }
	
       
    var load = function(){
    	listService.loadData($scope,'api/fwApplyScope/query/'+ $scope.parentId, $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});
   
angular.module('huatek.controllers').controller('FwApplyScopeAddController', function ($scope, $location, $http, $routeParams, editService) {
	
	 /**
	  * 上级页面的业务模块ID
	  */
	 $scope.parentId = $scope.passParams;
	
	    
	 var addDataUrl = 'api/fwApplyScope/add/' + $scope.parentId;
	 var editDataUrl = 'api/fwApplyScope/edit';
	 var homeUrl = '/fwApplyScope/appConfig/' + $scope.parentId;
	
	/*定义块*/
	var columnWrapArray = [];
	columnWrapArray.push(new multipleColumn(1,'基本信息'));
	/***
	 * 定义
	 */
	$scope.columnWrapArray = columnWrapArray;
    
    /*定义用户录入数据FormElement(title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'目标类','targetClass','string',1,'50'));
    formFieldArray.push(new FormElement(1,'目标字段','targetField','string',1,'50'));
    
       
	editService.init($scope,  $http);
       
    editService.setFormFields($scope,formFieldArray); 
    
    if($scope.editId){
    	editService.loadData($scope,editDataUrl, $scope.editId);
    }
       
    $scope.update = function(){
    	editService.updateData($scope,editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData($scope,addDataUrl, homeUrl);
    }
});