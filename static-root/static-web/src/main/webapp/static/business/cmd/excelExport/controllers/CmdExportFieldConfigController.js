'use strict';
   
angular.module('huatek.controllers').controller('CmdExportFieldConfigController', function ($scope, $location, $http,$rootScope,$routeParams,listService) {
	var configId= $scope.editId;
	$scope.editId=null;
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '表别名', field: 'tableAliasName',width: '5%', enableColumnMenu: false},
		       { name: '字段名', field: 'fieldName',width: '20%', enableColumnMenu: false},
		       { name: '字段别名', field: 'fieldAliasName',width: '10%', enableColumnMenu: false},
		       { name: '字段中文名', field: 'fieldDesc',width: '20%', enableColumnMenu: false},
		       { name: '是否导出', field: 'isExport',width: '10%', enableColumnMenu: false},
		       { name: '排序', field: 'orderBy',width: '10%', enableColumnMenu: false},
		       { name: '条件名称', field: 'conditionsName',width: '15%', enableColumnMenu: false},
		       { name: '条件运算', field: 'conditionsOperation',width: '10%', enableColumnMenu: false},
		    ]
	  };
	
	   
	listService.init($scope);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
       
    queryPage.addParam(new queryParam('字段名','fieldName','string','like'));
    queryPage.addParam(new queryParam('字段中文名','fieldDesc','string','like'));
    
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
    btnArray.push(new pageButton('新增','add','/cmdExportFieldConfig/add','addData'));
	btnArray.push(new pageButton('编辑','edit','/cmdExportFieldConfig/edit','editData'));
	btnArray.push(new pageButton('删除','delete','/cmdExportFieldConfig/delete','deleteData'));
          
   listService.setButtonList($scope,btnArray);

       
   $scope.execute = function(operation, param){
	    if(operation=='addData'){
      		listService.addData($scope,new popup('新增导出属性','/cmdExportFieldConfig/add/',configId,null,null,load));
	    }else if(operation=='editData'){
      		listService.editData($scope,$scope.gridApi, new popup('编辑导出属性','/cmdExportFieldConfig/edit/',configId,null,null,load));
     	}else if(operation=='deleteData'){
      		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, URL_PATH.CMD_HEADER+'/cmdExportFieldConfig/delete');
    	}
  }
	   
    var load = function(){
    	listService.loadData($scope,URL_PATH.CMD_HEADER+'/cmdExportFieldConfig/query/'+configId, $scope.tableGrid);
    }
    $scope.load=load;
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('huatek.controllers').controller('CmdExportFieldConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	var configId= $scope.passParams;
	 var addDataUrl = URL_PATH.CMD_HEADER+'/cmdExportFieldConfig/add/'+configId;
	 var editDataUrl = URL_PATH.CMD_HEADER+'/cmdExportFieldConfig/edit/'+configId;
	 var homeUrl = '/cmdExportFieldConfig/home/'+configId;
	 var codeUrl= URL_PATH.CMD_HEADER+'/cmdExportFieldConfig/formCode/'+configId;
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column,title, name, type, require, max, model, event, min,defaultValue,dropDataUrl,readonly)*/
    var formFieldArray = [];
	  var tableConfigId = new FormElement(1,'导出关联表','tableConfigId','string',1,'30','select','changeTableConfig');
	
	/*tableConfigId.showField = 'name';*/
	
	/*tableConfigId.returnField = 'id';*/
	  formFieldArray.push(tableConfigId);
	  var tableAliasName = new FormElement(1,'表别名','tableAliasName','string',1,'30','','','','','','readonly');
	  formFieldArray.push(tableAliasName);
	  formFieldArray.push(new FormElement(1,'字段名','fieldName','string',1,'100'));
	  formFieldArray.push(new FormElement(1,'字段别名','fieldAliasName','string',0,'30'));
	  formFieldArray.push(new FormElement(1,'字段中文名','fieldDesc','string',1,'30'));
	  formFieldArray.push(new FormElement(1,'是否导出','isExport','string',1,'30','select'));
  	  formFieldArray[formFieldArray.length-1].items=[{_returnField:"1", _showField:"是"},{_returnField:"2", _showField:"否"}];
  	  formFieldArray[formFieldArray.length-1].value="1";
	  formFieldArray.push(new FormElement(1,'排序','orderBy','string',1,'30'));
	  formFieldArray.push(new FormElement(1,'条件名称','conditionsName','string','','30'));
	  formFieldArray.push(new FormElement(1,'条件运算','conditionsOperation','string','','30','select'));
	  formFieldArray[formFieldArray.length-1].items=[{_returnField:"1", _showField:"等于"},
	                                                 {_returnField:"2", _showField:"大于"},
	                                                 {_returnField:"3", _showField:"小于"},
	                                                 {_returnField:"6", _showField:"大于等于"},
	                                                 {_returnField:"7", _showField:"小于等于"},
	                                                 {_returnField:"4", _showField:"模糊"},
	                                                 {_returnField:"5", _showField:"in"}];
	  
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    
    $scope.changeTableConfig = function(value){
		  if(value == null){
			  tableAliasName.value = "";
		  }else{
			  var itemValues = tableConfigId.items;
			  if(itemValues != null){
				  for(var i=0;i<itemValues.length;i++){
					  if(itemValues[i].code == value){
						  tableAliasName.value = itemValues[i].desc;
					  }
					  
				  }
			  }
		  }
		  
	}
    
       
	editService.init($scope);
	editService.initParams($scope,codeUrl);
       
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

   
