'use strict';
   
angular.module('huatek.controllers').controller('FwApplyScopeController', function ($scope, $location, $http, listService, $routeParams) {
	
	   
    $scope.editId = $routeParams.id;
    
	/***
	 * 定义显示的表格.      
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
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
	  	}; 
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"targetClass,targetField","false");
       
    queryPage.addParam(new queryParam('业务模块名称','businessMapName','string','like'));
    queryPage.addParam(new queryParam('目标类','targetClass','string','like'));
       
    listService.setQueryPage(queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/fwApplyScope/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/fwApplyScope/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/fwApplyScope/delete','deleteData'));
        btnArray.push(new pageButton('返回','cancel','/fwApplyScope/appConfig','cancel'));
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
        if(operation=='addData'){
        	listService.addData('/fwApplyScope/add/'+$scope.editId);
        }else if(operation=='deleteData'){
        	listService.deleteData($scope.tableGrid, $scope.gridApi, 'api/fwApplyScope/delete');
        }else if(operation=='editData'){
        	listService.editData($scope.gridApi, '/fwApplyScope/edit/'+$scope.editId);
        }else if(operation=='cancel'){
        	$location.path("/fwBusinessMap/home");
        }
    }
	
       
    var load = function(){
    	listService.loadData('api/fwApplyScope/query/'+ $scope.editId, $scope.tableGrid); 
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
	 $scope.parentId = $routeParams.parentId;
	
	    
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
    
       
	editService.init($scope, $location, $http);
       
    editService.setFormFields(formFieldArray); 
       
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }
       
    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData(addDataUrl, homeUrl);
    }
});