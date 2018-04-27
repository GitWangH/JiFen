'use strict';
   
angular.module('HuatekApp').controller('CmdReportController', function ($scope, $location, $http,$rootScope, listService,$modal,shareData) {
	var codeUrl='api_cmd/cmdReportController/queryCode'
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '报表名称', field: 'reportName',width: '20%', enableColumnMenu: false},
		       { name: '报表编号', field: 'reportCode',width: '20%', enableColumnMenu: false},
		       { name: '报表所属系统', field: 'system',width: '20%', enableColumnMenu: false},
		       { name: '报表路径', field: 'reportPath',width: '20%', enableColumnMenu: false},
		       { name: '显示行数', field: 'pageSize',width: '20%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
        
    var start=new queryParam('报表名称','reportName','string','like');
    queryPage.addParam(start);
    var type=new queryParam('报表编号','reportCode','string','like');
    queryPage.addParam(type);
    queryPage.addParam(new queryParam('所属系统','system','string','in'));
    
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
        btnArray.push(new pageButton('新增报表','addReport','/cmdReportController/add','addReportData'));
        btnArray.push(new pageButton('编辑报表','editReport','/cmdReportController/edit','editReportData'));
        btnArray.push(new pageButton('删除报表','deleteReport','/cmdReportController/delete','deleteData'));
        btnArray.push(new pageButton('属性维护','addReportConfig','/cmdReportController/addReportConfig','addReportConfigData'));
          
   listService.setButtonList(btnArray);

      
   $scope.execute = function(operation, param){
   	if(operation=='addReportData'){
   		
      /*新增*/
   		listService.addData('/cmdReportController/add');
     }
   	if(operation=='editReportData'){
   		
      /*修改*/
   		listService.editData($scope.gridApi,'/cmdReportController/edit');
   	}
   	if(operation=='deleteData'){
   		
      /*删除*/
   		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_cmd/cmdReportController/delete');
   	}
   	if(operation=='addReportConfigData'){
   		
      /*属性维护*/
        shareData.addReportConfigData=$scope.gridApi.selection.getSelectedRows()[0].id;
        $location.path("/cmdReportFieldConfigController/home/"+shareData.addReportConfigData);

/* 		listService.editData($scope.gridApi, '/cmdReportFieldConfigController/home');*/
   	}
   }	
	   
    var load = function(){
    	listService.loadData(URL_PATH.CMD_HEADER +'/cmdReportController/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});


   
angular.module('HuatekApp').controller('CmdReportAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope,excelService) {
	 var codeUrl=URL_PATH.CMD_HEADER+'/cmdReportController/formCode'
	   
	 var addDataUrl = URL_PATH.CMD_HEADER+'/cmdReportController/add';
	 var editDataUrl = URL_PATH.CMD_HEADER+'/cmdReportController/edit';
	 var homeUrl = '/cmdReportController/home';
	 
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
  /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    
    /*FormElement1(title, name, type, require, max, model, isEdit, isShow, event, min, defaultValue, dropDataUrl)*/
    var formFieldArray = [];
	  formFieldArray.push(new FormElement(1,'所属系统','system','string',1,'30','select'));
	  formFieldArray.push(new FormElement(1,'报表名称','reportName','string',1,'100'));
	
	  var reportCode=new FormElement(1,'报表编号','reportCode','string',1,'500');
	  formFieldArray.push(reportCode);
	  var pathElement=new FormElement(1,'报表设计','reportPath','string',1,'512');
	  formFieldArray.push(pathElement);
	  formFieldArray.push(new FormElement(1,'显示行数','pageSize','string',1,'100'));
     
     /*设置全局变量*/
     $rootScope.formFieldArray = formFieldArray;
       
	editService.init($scope, $location, $http);
	editService.initParams(codeUrl);
       
    editService.setFormFields(formFieldArray); 
    
       
    $scope.editId = $routeParams.id;
    
    if($scope.editId){

/*       reportCode.readonly=true;*/

/*     	formFieldArray.push(reportCode);*/
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

/*   $scope.upload = function(){*/

/*   	excelService.up({},false,function(path,name){*/

/*   		pathElement.value=path;*/

/*   	});*/

/*   }*/

/*   $scope.download = function(){*/

/*   	excelService.download(pathElement.value)*/

/*   }*/
});

