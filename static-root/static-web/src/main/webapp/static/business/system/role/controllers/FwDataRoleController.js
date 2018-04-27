'use strict';
   
angular.module('huatek.controllers').controller('FwDataRoleController', function ($scope, $location, $http, listService) {
	/***
	 * 定义显示的表格. 
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		      { name: '角色名称', field: 'name',width: '20%',enableColumnMenu: false }
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
       
    var queryPage = new QueryPage(1,10,"id asc","false");
       
    queryPage.addParam(new queryParam('数据角色名称','name','string','like'));
       
    listService.setQueryPage(queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/fwDataRole/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/fwDataRole/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/fwDataRole/delete','deleteData'));
        btnArray.push(new pageButton('分配数据权限','doAssginDataRight','/fwDataRole/doAssginDataRight','doAssginDataRight'));
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
        if(operation=='addData'){
        	listService.addData('/fwDataRole/add');
        }else if(operation=='deleteData'){
        	listService.deleteData($scope.tableGrid, $scope.gridApi, 'api/fwDataRole/delete');
        }else if(operation=='editData'){
        	listService.editData($scope.gridApi, '/fwDataRole/edit');
        }else if(operation=='doAssginDataRight'){
        	this.doAssginDataRight($scope.gridApi, '/fwDataRole/doAssginDataRight');
        }
    }
	   
    var load = function(){
    	listService.loadData('api/fwDataRole/query', $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
	/***
     * 分配数据权限操作.
     */
    $scope.doAssginDataRight = function(gridApi, toUrl){
    	
    	/*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length>1){
    		bootbox.alert('警告：不能选择多条数据。');
    		return;
    	}
    	if(selectData.length==0){
    		bootbox.alert('请在列表中选择一条数据。');
    		return;
    	}
    	$location.path(toUrl + "/" + selectData[0].id);
    }
	
});
   
angular.module('huatek.controllers').controller('FwDataRoleAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = 'api/fwDataRole/add';
	 var editDataUrl = 'api/fwDataRole/edit';
	 var homeUrl = '/fwDataRole/home';
	
		
		/*定义块*/
	    var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'基本信息'));
	    $scope.columnWrapArray = columnWrapArray;
	    
	    /*定义用户录入数据FormElement(column,title, name, type, require, max, model, event, min)*/
	    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'角色名称','name','string',1,'50'));
    
	
	/*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;
    
       
	editService.init($scope, $location, $http);
       
    editService.setFormFields(formFieldArray); 
       
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
});

angular.module('huatek.controllers').controller('FwDataRoleAssginController', function ($scope, $location, $http, listService, $routeParams) {
	
	   
    $scope.editId = $routeParams.id;
	
	/***
	 * 定义显示的表格. 
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		      { name: '过滤名称', field: 'name',width: '10%', enableColumnMenu: false},
		      { name: '菜单名称', field: 'fwSourceId_',width: '10%', enableColumnMenu: false},
		      { name: '实体名称', field: 'entityId_',width: '10%',enableColumnMenu: false },
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
       
    var queryPage = new QueryPage(1,10,"","false");
       
    queryPage.addParam(new queryParam('过滤名称','name','string','like'));
    queryPage.addParam(new queryParam('实体名称','entityName','string','like'));
       
    listService.setQueryPage(queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('数据权限实体配置','doAssginDataRightEntity','/fwDataRole/doAssginDataRightEntity','doAssginDataRightEntity'));
        btnArray.push(new pageButton('返回','doAssginDataRightBack','/fwDataRole/doAssginDataRightBack','doAssginDataRightBack'));
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
        if(operation=='doAssginDataRightEntity'){
        	this.doAssginDataRightEntity($scope.gridApi, '/fwDataRole/doAssginDataRightEntity');
        }else if(operation=='doAssginDataRightBack'){
        	this.doAssginDataRightBack("/fwDataRole/home");
        }
    }
	   
    var load = function(){
    	listService.loadData('api/fwDataRole/querySource/'+ $scope.editId, $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
	/***
     * 分派权限.
     */
    $scope.doAssginDataRightEntity = function(gridApi, toUrl){
    	
    	/*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length>1){
    		bootbox.alert('警告：不能选择多条数据用于分配权限。');
    		return;
    	}
    	if(selectData.length==0){
    		bootbox.alert('请在列表中选择一条用于分配权限的数据。');
    		return;
    	}
    	$location.path(toUrl + "/" + selectData[0].id+"/"+$scope.editId);
    }
    
    /***
     * 分派权限.
     */
    $scope.doAssginDataRightBack = function(toUrl){
    	$location.path(toUrl);
    }
	
});


   
angular.module('huatek.controllers').controller('FwDataRoleAssginTreeController', function ($scope, $location, $http, listService, $routeParams) {
	   
    $scope.editId = $routeParams.id;
    $scope.parentId = $routeParams.parentId;
	
	/***
	 * 定义显示的表格. 
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		      { name: '数据信息', field: 'propertyName',width: '10%', enableColumnMenu: false},
		      { name: '绑定' ,field: 'id', cellTemplate: '/static/views/system/role/button.html'+'?t='+huatek.version, width: '80',enableColumnResizing: false,enableSorting: false,enableColumnMenu: false },
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
       
    var queryPage = new QueryPage(1,10,"","false");
       
    queryPage.addParam(new queryParam('输出实体','name','string','like'));
       
    listService.setQueryPage(queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = []; 
    btnArray.push(new pageButton('返回','backDoAssginDataRightEntity','/fwDataRole/backDoAssginDataRightEntity','backDoAssginDataRightEntity'));
    
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
    	if(operation=='backDoAssginDataRightEntity'){
        	this.backDoAssginDataRightEntity();
        }
    }
	   
    var load = function(){
    	listService.loadData('api/fwDataRole/querySourceObject/'+ $scope.editId+"/"+$scope.parentId, $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
	 $scope.backDoAssginDataRightEntity = function(){
		 $location.path("/fwDataRole/doAssginDataRight/" + $scope.parentId);
	 } 
	 
	 $scope.doAssignRole = function(row){
		 var checkedOfAll=$("#assgin_"+row.entity.id).prop("checked");
		 var actionUrl = "api/fwDataRole/assignDataInfo/" + row.entity.id+"/"+$scope.editId+"/"+$scope.parentId+"/"+checkedOfAll+"/"+row.entity.propertyValue;
		 $http.post(actionUrl).success(function (response) {
			 
			 /*load();*/
			 row.entity.ifChecked = true;
		 });
	 }
    
});