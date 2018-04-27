'use strict';
   
angular.module('HuatekApp').controller('CmdReportFieldConfigController', function ($scope, $location,$http,$rootScope,$routeParams, listService,shareData) {
	
	
	/*将当前controller的scope放到共享对象*/
	
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '参数名称', field: 'title',width: '10%', enableColumnMenu: false},
		       { name: '参数代码', field: 'name',width: '10%', enableColumnMenu: false},
		       { name: '数据字典名称', field: 'items',width: '10%', enableColumnMenu: false},
		       { name: '组件类型', field: 'type',width: '10%', enableColumnMenu: false},
		       { name: '级联办事处组件的名称', field: 'office',width: '10%', enableColumnMenu: false},
		       { name: '字段最大值', field: 'maxValue',width: '10%', enableColumnMenu: false},
		       { name: '字段最小值', field: 'minValue',width: '10%', enableColumnMenu: false},
		       { name: '字段小数位', field: 'decimals',width: '10%', enableColumnMenu: false},
		       { name: '查询条件顺序', field: 'cloum',width: '10%', enableColumnMenu: false},
		       { name: '时间默认值', field: 'timeChoose',width: '10%', enableColumnMenu: false},
		       { name: '是否必填', field: 'isMsut',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,20,"id desc","false");
    
       
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage(queryPage);
    
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/cmdReportFieldConfig/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/cmdReportFieldConfig/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/cmdReportFieldConfig/delete','deleteData'));
        btnArray.push(new pageButton('返回','back','/cmdReportFieldConfig/back','goBack'));
          
   listService.setButtonList(btnArray);
   var configId= shareData.addReportConfigData;
       
    $scope.execute = function(operation, param){
	        if(operation=='addData'){
        		listService.addData('/cmdReportFieldConfig/add/'+configId);
	        }else if(operation=='editData'){
        		listService.editData($scope.gridApi, '/cmdReportFieldConfig/edit/'+configId);
       	  	}else if(operation=='deleteData'){
        		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_cmd/cmdReportFieldConfig/delete');
      		}else if(operation=='goBack'){
      			$location.path("/cmdReportController/home");
      		}
    }
	   
    var load = function(){
    	listService.loadData('api_cmd/cmdReportFieldConfig/query/' + configId, $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('HuatekApp').controller('CmdReportFieldAddConfigController', function ($scope, $location, $http, $routeParams, editService,$rootScope,shareData) {
	    
	 var configId= shareData.addReportConfigData;
	 var addDataUrl = 'api_cmd/cmdReportFieldConfig/add/'+configId;
	 var editDataUrl = 'api_cmd/cmdReportFieldConfig/edit/'+configId;
	 var homeUrl = '/cmdReportFieldConfigController/home/'+configId;
	 var codeUrl='api_cmd/cmdReportFieldConfig/formCode'
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'参数名称','title','string',0,'100'));
		  formFieldArray.push(new FormElement(1,'参数代码','name','string',0,'100'));
		  formFieldArray.push(new FormElement(1,'数据字典名称','items','string',0,'100'));
		  formFieldArray.push(new FormElement(1,'组件类型','type','string',0,'255','select'));
		  formFieldArray.push(new FormElement(1,'级联办事处组件的名称','office','string',0,'100'));
		  formFieldArray.push(new FormElement(1,'字段最大值','maxValue','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'字段最小值','minValue','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'字段小数位','decimals','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'字段顺序','cloum','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'时间默认值','timeChoose','string',0,'30','select'));
		  formFieldArray.push(new FormElement(1,'是否必填','isMsut','string',0,'30','select'));
    
    /*设置全局变量*/
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
});

   
