'use strict';
   
angular.module('huatek.controllers').controller('CmdImportFieldConfigController', function ($scope, $location, $routeParams,$http,$rootScope, listService,shareData) {
	
	var configId= $scope.editId;
	$scope.editId=null;
	/*将当前controller的scope放到共享对象*/
	
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '字段名称', field: 'name',width: '10%', enableColumnMenu: false},
		       { name: '字段代码', field: 'code',width: '10%', enableColumnMenu: false},
		       { name: '类型', field: 'type',width: '10%', enableColumnMenu: false},
		       { name: '列号', field: 'col',width: '10%', enableColumnMenu: false},
		       { name: '格式或正则表达式', field: 'patten',width: '10%', enableColumnMenu: false},
		       { name: '是否可空', field: 'nullable',width: '10%', enableColumnMenu: false},
		       { name: '最大长度', field: 'length',width: '10%', enableColumnMenu: false},
		       { name: '最大值', field: 'max',width: '10%', enableColumnMenu: false},
		       { name: '最小值', field: 'min',width: '10%', enableColumnMenu: false},
		       { name: '验证方式', field: 'contentValidateType',width: '10%', enableColumnMenu: false},
		       { name: '验证实现', field: 'contentValidateImp',width: '10%', enableColumnMenu: false},
		       { name: '转换方式', field: 'contentTransformType',width: '10%', enableColumnMenu: false},
		       { name: '转换实现', field: 'contentTransformImp',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope);
       
    var queryPage = new QueryPage(1,20,"id desc","false");
    
       
    
       
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
        btnArray.push(new pageButton('新增','add','/cmdImportFieldConfig/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/cmdImportFieldConfig/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/cmdImportFieldConfig/delete','deleteData'));
          
   listService.setButtonList($scope,btnArray);
   
       
    $scope.execute = function(operation, param){
	        if(operation=='addData'){
        		listService.addData($scope,new popup('新增导入属性','/cmdImportFieldConfig/add',configId,null,null,load));
	        }else if(operation=='editData'){
        		listService.editData($scope,$scope.gridApi, new popup('编辑导入属性','/cmdImportFieldConfig/edit',configId,null,null,load));
       	  	}else if(operation=='deleteData'){
        		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api_cmd/cmdImportFieldConfig/delete');
      		}
    }
    
  
	   
    var load = function(){
    	listService.loadData($scope,'api_cmd/cmdImportFieldConfig/query/' + configId, $scope.tableGrid);
    }
    $scope.load=load;
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('huatek.controllers').controller('CmdImportFieldConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	var configId= $scope.passParams;
	 var addDataUrl = 'api_cmd/cmdImportFieldConfig/add/'+configId;
	 var editDataUrl = 'api_cmd/cmdImportFieldConfig/edit/'+configId;
	 var homeUrl = 'cmdImportFieldConfig/home/'+configId;
	 var codeUrl='api_cmd/cmdImportFieldConfig/formCode'
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'字段名称','name','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'字段代码','code','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'字段类型','type','string',1,'30',"select"));
		  formFieldArray.push(new FormElement(1,'列号','col','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'格式或正则表达式','patten','string',0,'255'));
		  formFieldArray.push(new FormElement(1,'为空(勾选为是)','nullable','boolean',0,'30','checkbox'));
		  formFieldArray.push(new FormElement(1,'最大长度','length','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'最大值','max','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'最小值','min','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'验证方式','contentValidateType','string',0,'30','select'));
		  formFieldArray.push(new FormElement(1,'验证实现','contentValidateImp','string',0,'255'));
		  formFieldArray.push(new FormElement(1,'转换方式','contentTransformType','string',0,'30','select'));
		  formFieldArray.push(new FormElement(1,'转换实现','contentTransformImp','string',0,'255'));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope);
    
       
    editService.setFormFields($scope,formFieldArray); 
    editService.initParams($scope,codeUrl);
       
    
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

   
