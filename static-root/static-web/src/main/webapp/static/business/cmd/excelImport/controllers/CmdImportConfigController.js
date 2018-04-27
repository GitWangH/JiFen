'use strict';
   
angular.module('huatek.controllers').controller('CmdImportConfigController', function ($scope, $location, $http,$rootScope, listService,excelService) {
	var codeUrl='api_cmd/cmdImportConfig/queryCode'
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [

/*		       { name: '主键', field: 'id',width: '10%', enableColumnMenu: false},*/
		       { name: '业务类型', field: 'busiType',width: '10%', enableColumnMenu: false},
		       { name: '业务名称', field: 'busiName',width: '20%', enableColumnMenu: false},
		       { name: '业务编码', field: 'busiCode',width: '10%', enableColumnMenu: false},
		       { name: '验证服务', field: 'validateService',width: '20%', enableColumnMenu: false},
		       { name: '转换服务', field: 'transformService',width: '20%', enableColumnMenu: false},
		       { name: '持久化方式', field: '_persisType',width: '10%', enableColumnMenu: false,decode:{field:"persisType",dataKey:"dic.excel_import_persist_type"}},
		       { name: '持久化实现', field: 'persisImpl',width: '20%', enableColumnMenu: false},
		       { name: '导入模版', field: 'templateFileName',width: '20%', enableColumnMenu: false},
		       { name: 'sheet页', field: 'sheet',width: '10%', enableColumnMenu: false},
		       { name: '开始行', field: 'startRow',width: '10%', enableColumnMenu: false},
		       { name: '开始列', field: 'startCol',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope,  $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    var busiTypeQuery=new queryParam('业务类型','busiType','string');
    busiTypeQuery.componentType='select';
       
    queryPage.addParam(busiTypeQuery);
    queryPage.addParam(new queryParam('业务名称','busiName','string','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage($scope,queryPage);
    listService.initQueryParams($scope,codeUrl)
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	      listService.setGridHeight();
	  	}; 
    
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/cmdImportConfig/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/cmdImportConfig/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/cmdImportConfig/delete','deleteData'));
        btnArray.push(new pageButton('上传模版','upload','/cmdImportConfig/upload','upload'));
        btnArray.push(new pageButton('下载模版','download','/cmdImportConfig/home','download'));
        btnArray.push(new pageButton('属性维护','editProperty','/cmdImportConfig/home','editPropertyData'));
        btnArray.push(new pageButton('导入示例','excelImp','/cmdImportConfig/home','excelImp'));
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
	        if(operation=='addData'){
	        	console.log(11);
        		listService.addData($scope,new popup('新增导入配置','/cmdImportConfig/add',null,null,null,load));
	        }
      	  	else if(operation=='editData'){
        		listService.editData($scope,$scope.gridApi, new popup('编辑导入配置','/cmdImportConfig/edit',null,null,null,load));
       	  	}
	       	else if(operation=='deleteData'){
        		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api_cmd/cmdImportConfig/delete');
      		}
	       	else if(operation=='editPropertyData'){
	       		listService.editData($scope,$scope.gridApi, new popup('导入属性配置','/cmdImportFieldConfig/home'));
	       		
      		}
	       	else if(operation=='excelImp'){
	       		excelService.imp({busiCode:'cmd000000002'})
	       		
      		}
	       	else if(operation=='download'){
	       		var selectData = listService.returnSectData($scope.gridApi);
	        	if(selectData.length>1){
	        		bootbox.alert('警告：不能选择多条数据下载模版。');
	        		return;
	        	}
	        	if(selectData.length==0){
	        		bootbox.alert('请在列表中选择一条用于下载模版。');
	        		return;
	        	}
	       		excelService.down(selectData[0].busiCode,selectData[0].busiName);
	       		
      		}
	       	else if(operation=='upload'){
	       		var selectData = listService.returnSectData($scope.gridApi);
	        	if(selectData.length>1){
	        		bootbox.alert('警告：不能选择多条数据上传模版。');
	        		return;
	        	}
	        	if(selectData.length==0){
	        		bootbox.alert('请在列表中选择一条用于上传。');
	        		return;
	        	}
	        	excelService.up('importTemplate',{busiCode:selectData[0].busiCode},false,function(path,name){
	        		

	        	
	        		    	});
	       		
      		}

    }

	   
    var load = function(){
    	listService.loadData($scope,'api_cmd/cmdImportConfig/query', $scope.tableGrid);
    }
    $scope.load=load;
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('huatek.controllers').controller('CmdImportConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope,excelService) {
	    
	 var addDataUrl = 'api_cmd/cmdImportConfig/add';
	 var editDataUrl = 'api_cmd/cmdImportConfig/edit';
	 var homeUrl = '/cmdImportConfig/home';
	 var codeUrl='api_cmd/cmdImportConfig/formCode'
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  
		  /*formFieldArray.push(new FormElement(1,'主键','id','string',1,'30'));*/
		  formFieldArray.push(new FormElement(1,'业务类型','busiType','string',1,'30','select'));
		  formFieldArray.push(new FormElement(1,'业务名称','busiName','string',1,'30'));
		  var busiCode=new FormElement(1,'业务编码','busiCode','string',0,'30');
		  busiCode.readonly=true;
		  formFieldArray.push(busiCode);
		  formFieldArray.push(new FormElement(1,'Sheet页','sheet','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'开始行','startRow','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'开始列','startCol','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'验证服务','validateService','string',0,'100'));
		  formFieldArray.push(new FormElement(1,'转换服务','transformService','string',0,'100'));
		  formFieldArray.push(new FormElement(1,'持久化方式','persisType','string',1,'100',"select"));
		  formFieldArray.push(new FormElement(1,'持久化实现','persisImpl','string',1,'255'));
	var pathElement=new FormElement(1,'模版路径','excelPath','string',0,'512');
		  formFieldArray.push(pathElement);
		
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope,  $http);
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

   
