'use strict';
   
angular.module('huatek.controllers').controller('FwBusinessMapController', function ($scope, $location, $http, listService) {
	/***
	 * 定义显示的表格. 
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 50, 100, 200],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		      { name: '业务模块名称', field: 'name',width: '25%',enableColumnMenu: false },
		      { name: '菜单名称', field: 'fwSourceId_',width: '25%',enableColumnMenu: false },
		      { name: '实体名称', field: 'entityId_',width: '25%',enableColumnMenu: false }
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
       
    var queryPage = new QueryPage(1,10,"id desc","false");
       
    queryPage.addParam(new queryParam('业务模块名称','name','string','like'));
       
    listService.setQueryPage($scope,queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/fwBusinessMap/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/fwBusinessMap/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/fwBusinessMap/delete','deleteData'));
        btnArray.push(new pageButton('应用配置','appConfig','/fwApplyScope/appConfig','appConfig'));
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
        if(operation=='addData'){
        	listService.addData($scope,new popup('新增模块','/fwBusinessMap/add',null,null,null,load));
        }else if(operation=='deleteData'){
        	listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api/fwBusinessMap/delete');
        }else if(operation=='editData'){
        	
        	listService.editData($scope,$scope.gridApi,new popup('编辑模块','/fwBusinessMap/edit',null,null,null,load));
        }else if(operation=='appConfig'){
        	listService.editData($scope, $scope.gridApi,new popup('应用配置','/fwApplyScope/appConfig'));
        }
    }
	   
    var load = function(){
    	listService.loadData($scope,'api/fwBusinessMap/query', $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
	/***
     * 应用配置.
     */
   
	
});


   
angular.module('huatek.controllers').controller('FwBusinessMapAddController', function ($scope, $location, $http, $routeParams, editService) {
	    
	 var addDataUrl = 'api/fwBusinessMap/add';
	 var editDataUrl = 'api/fwBusinessMap/edit';
	 var homeUrl = '/fwBusinessMap/home';
	 
	
    /*定义块*/
	var columnWrapArray = [];
	columnWrapArray.push(new multipleColumn(1,'基本信息'));
	
    /*columnWrapArray.push(new multipleColumn(2,'补充信息'));*/
	/***
	* 定义
	*/
	$scope.columnWrapArray = columnWrapArray;
	
    
    /*定义用户录入数据FormElement(title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'业务模块名称','name','string',1,'50'));
    var fwSourceIdEl=new FormElement(1,'菜单名称','fwSourceId','int',1,'30','select-autocomplete');
    fwSourceIdEl.showName='title';
    fwSourceIdEl.returnValue='id';
    formFieldArray.push(fwSourceIdEl); 
    formFieldArray.push(new FormElement(1,'数据实体','entityId','int',1,'30','select')); 
    
       
	editService.init($scope,  $http);
       
    editService.setFormFields($scope,formFieldArray); 
       
    /**
     * 获取字典数据
     */
    var fieldMap = editService.getFieldMap($scope);
    fieldMap.get("name").min = 5;
    /***
     * 初始化表单选择数据，包括字典数据，其他可选项数据.
     */
    editService.initFieldItems($scope,fieldMap.get("entityId"),"api/fwSourceEntity/public/select")
    editService.initFieldItems($scope,editService.getFieldMap($scope).get("fwSourceId"),"api/menu/public/loadAllMenu");

    
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