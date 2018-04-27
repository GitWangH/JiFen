'use strict';
   
angular.module('huatek.controllers').controller('FwSourceEntityController', function ($scope, $location, $http, listService) {
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
		      { name: '系统编码', field: 'systemCode',width: '10%',enableColumnMenu: false },          
		      { name: '实体名称', field: 'entityName',width: '10%',enableColumnMenu: false },
		      { name: '实体类', field: 'entityClass',width: '10%',enableColumnMenu: false },
		      { name: '关联字段', field: 'entityField',width: '10%',enableColumnMenu: false },
		      { name: '数据过滤条件', field: 'queryParam',width: '10%',enableColumnMenu: false },
		      { name: '显示值字段', field: 'outputKey',width: '10%',enableColumnMenu: false },
		      
              /*{ name: '显示标题字段', field: 'outputTitle',width: '10%',enableColumnMenu: false },*/
		      { name: '数据所属类名', field: 'outputClass',width: '10%',enableColumnMenu: false },
		      { name: '分配值字段', field: 'outputValue',width: '10%',enableColumnMenu: false },
		      { name: '是否必填', field: 'notNull_',width: '10%',enableColumnMenu: false }
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
       
    var queryPage = new QueryPage(1,10,"id asc","false");
       
    queryPage.addParam(new queryParam('实体名称','entityName','string','like'));
       
    listService.setQueryPage($scope,queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/fwSourceEntity/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/fwSourceEntity/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/fwSourceEntity/delete','deleteData'));
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
        if(operation=='addData'){
        	listService.addData($scope,new popup('新增权限实体','/fwSourceEntity/add',null,null,null,load));
        }else if(operation=='deleteData'){
        	listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api/fwSourceEntity/delete');
        }else if(operation=='editData'){
        	listService.editData($scope,$scope.gridApi, new popup('修改权限实体','/fwSourceEntity/edit',null,null,null,load));
        }
    }
	   
    var load = function(){
    	listService.loadData($scope,'api/fwSourceEntity/query', $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});
   
angular.module('huatek.controllers').controller('FwSourceEntityAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = 'api/fwSourceEntity/add';
	 var editDataUrl = 'api/fwSourceEntity/edit';
	 var homeUrl = '/fwSourceEntity/home';
	
    /*定义块*/
	var columnWrapArray = [];
	columnWrapArray.push(new multipleColumn(1,'基本信息'));
	$scope.columnWrapArray = columnWrapArray;
	
    /*定义用户录入数据FormElement(column,title, name, type, require, max, model, event, min)*/
	var formFieldArray=[];
    formFieldArray.push(new FormElement(1,'实体名称','entityName','string',1,'50'));
    formFieldArray.push(new FormElement(1,'实体类','entityClass','string',1,'50'));
    formFieldArray.push(new FormElement(1,'关联字段','entityField','string',1,'50'));
    formFieldArray.push(new FormElement(1,'数据过滤条件','queryParam','string',-1,'100'));
    formFieldArray.push(new FormElement(1,'显示值字段','outputKey','string',1,'50'));
    
    /*formFieldArray.push(new FormElement(1,'数据标题','outputTitle','string',1,'50'));*/
    formFieldArray.push(new FormElement(1,'数据所属类名','outputClass','string',1,'50'));
    formFieldArray.push(new FormElement(1,'分配值字段','outputValue','string',1,'50'));
    formFieldArray.push(new FormElement(1,'是否必填','notNull','int',1,'2','select'));
    formFieldArray.push(new FormElement(1,'系统编码','systemCode','string',1,'20'));
	
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;
       
	editService.init($scope, $http);
       
    editService.setFormFields($scope,formFieldArray); 
    /**
     * 获取字典数据
     */
    
    /*editService.initParams("api/fwSourceEntity/param");*/
    var fieldMap = editService.getFieldMap($scope);
    var notNullItems = [];
    notNullItems.push(new selectItem("是","1"));
    notNullItems.push(new selectItem("否","0"));
    fieldMap.get("notNull").items = notNullItems;
       
    
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