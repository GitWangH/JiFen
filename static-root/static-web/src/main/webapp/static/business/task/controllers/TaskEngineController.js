'use strict';
   
angular.module('huatek.controllers').controller('TaskEngineController', function ($scope, $location, $http,$rootScope, listService) {
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '所属系统', field: 'systemTypeName',width: '10%', enableColumnMenu: false},
		       { name: '服务ip', field: 'ip',width: '30%', enableColumnMenu: false},
		       { name: '状态', field: 'statusName',width: '10%', enableColumnMenu: false},
		       { name: '服务位置', field: 'name',width: '60%', enableColumnMenu: false}
 
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
       
    var type =new queryParam('所属系统','systemType','string','=');
    type.componentType='select';
    queryPage.addParam(type);
    queryPage.addParam(new queryParam('服务ip','ip','string','like'));
   
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage($scope,queryPage);
    listService.initQueryParams($scope,"api_task/taskEngine/code");
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
        
    var startBtn=new pageButton('开启','start','/taskEngine/start','startData');
    startBtn.show=true;
    btnArray.push(startBtn);
    var stopBtn=new pageButton('停止','stop','/taskEngine/stop','stopData');
    stopBtn.show=true;
    btnArray.push(stopBtn);
    var deleteBtn=new pageButton('删除','delete','/taskEngine/delete','deleteData');
    deleteBtn.show=true;
    btnArray.push(deleteBtn);
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
    	if(operation=='startData'){
    		if($scope.gridApi.selection.getSelectedRows().length < 1){
				bootbox.alert("请至少勾选一条数据！");
				return false;
			}
	    	if($scope.gridApi.selection.getSelectedRows().length > 1){
				bootbox.alert("请勾选一条数据！");
				return false;
			}
    		$scope.promise=$http.post("api_task/taskEngine/start/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(response){
    			load();
    		});
        }
  	  	else if(operation=='stopData'){
	  	  	if($scope.gridApi.selection.getSelectedRows().length < 1){
				bootbox.alert("请至少勾选一条数据！");
				return false;
			}
	    	if($scope.gridApi.selection.getSelectedRows().length > 1){
				bootbox.alert("请勾选一条数据！");
				return false;
			}
			$scope.promise=$http.post("api_task/taskEngine/stop/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(response){
				load();
			});
   	  	}
       	else if(operation=='deleteData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api_task/taskEngine/delete');
  		}
    }
	   
    var load = function(){
    	listService.loadData($scope,'api_task/taskEngine/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('huatek.controllers').controller('TaskEngineAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = 'api_task/taskEngine/add';
	 var editDataUrl = 'api_task/taskEngine/edit';
	 var homeUrl = '/taskEngine/home';
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'主键','id','string',1,'30'));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope, $http);
    
       
    editService.setFormFields($scope,formFieldArray); 
    
       
    $scope.editId = $routeParams.id;
    
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

   
