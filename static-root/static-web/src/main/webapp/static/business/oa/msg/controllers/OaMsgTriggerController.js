'use strict';
   
angular.module('HuatekApp').controller('OaMsgTriggerController',['$scope', '$location', '$http','$rootScope', 'listService', function ($scope, $location, $http,$rootScope, listService) {
	var codeUrl='api_oa/oaMsgTrigger/code';
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '名称', field: 'name',width: '10%', enableColumnMenu: false},
		       { name: '编码', field: 'code',width: '10%', enableColumnMenu: false},
		       { name: '所属系统', field: 'systemTypeName',width: '10%', enableColumnMenu: false},
		       { name: '链接', field: 'linkUrl',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    queryPage.addParam(new queryParam('编码','code','string','like'));
    var systemType=new queryParam('所属系统','systemType','string','=');
    systemType.componentType='select';
    queryPage.addParam(systemType);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage(queryPage);
    listService.initQueryParams(codeUrl)
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
    var btnArray = [];
    btnArray.push(new pageButton('新增','add','/oaMsgTrigger/add','addData'));
    btnArray.push(new pageButton('修改','edit','/oaMsgTrigger/edit','editData'));
    btnArray.push(new pageButton('接收范围','range','/oaMsgRange/home','rangeData'));
    btnArray.push(new pageButton('删除','delete','/oaMsgTrigger/delete','deleteData'));
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
    	if(operation=="addData"){
    		listService.addData('/oaMsgTrigger/add');
    	}else if(operation=="editData"){
    		listService.editData($scope.gridApi,'/oaMsgTrigger/edit');
    	}else if(operation=="rangeData"){
    		listService.editData($scope.gridApi,'/oaMsgRange/home');
    	}else if(operation=="deleteData"){
    		listService.deleteData($scope.tableGrid, $scope.gridApi, URL_PATH.OA_HEADER+'/oaMsgTrigger/delete');
    	}
    }
	   
    var load = function(){
    	listService.loadData('api_oa/oaMsgTrigger/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
}]);

   
angular.module('HuatekApp').controller('OaMsgTriggerAddController',['$scope', '$location', '$http', '$routeParams', 'editService','$rootScope', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = 'api_oa/oaMsgTrigger/add';
	 var editDataUrl = 'api_oa/oaMsgTrigger/edit';
	 var homeUrl = '/oaMsgTrigger/home';
	var codeUrl='api_oa/oaMsgTrigger/code';
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'名称','name','string',1,'30'));
		  var code=new FormElement(1,'编码','code','string',0,'30');
		  code.readonly= true;
		  formFieldArray.push(code);
		  formFieldArray.push(new FormElement(1,'所属系统','systemType','string',1,'30',"select"));
		  formFieldArray.push(new FormElement(1,'链接','linkUrl','string',1,'128'));
    
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope, $location, $http);
    
       
    editService.setFormFields(formFieldArray); 
    editService.initParams(codeUrl);
       
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
       
    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData(addDataUrl, homeUrl);
    }
}]);

   
