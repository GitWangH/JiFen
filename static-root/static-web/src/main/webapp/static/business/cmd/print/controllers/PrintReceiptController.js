'use strict';
   
angular.module('HuatekApp').controller('PrintReceiptController', function ($scope, $location, $http,$rootScope, listService) {
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '票据名称', field: 'receiptName',width: '10%', enableColumnMenu: false},
		       { name: '票据编码', field: 'receiptCode',width: '10%', enableColumnMenu: false},
		       { name: '票据内容查询sql语句', field: 'contentSql',width: '10%', enableColumnMenu: false},
		       { name: '子系统编码', field: 'systemType',width: '10%', enableColumnMenu: false},
		       { name: '打印纸张高度', field: 'paperHeight',width: '10%', enableColumnMenu: false},
		       { name: '打印纸张宽度', field: 'paperWidth',width: '10%', enableColumnMenu: false},
		       { name: '背景图片地址', field: 'receiptUrl',width: '10%', enableColumnMenu: false},
		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false},
		       { name: '操作人', field: 'operatorName',width: '10%', enableColumnMenu: false},
		       { name: '操作时间', field: 'operateTime',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"receipt_id desc","false");
    
       
    queryPage.addParam(new queryParam('票据名称','receiptName','string','like'));
    queryPage.addParam(new queryParam('票据编码','receiptCode','string','like'));
    
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
	    btnArray.push(new pageButton('新增','add','/printReceipt/add','addData'));
	    btnArray.push(new pageButton('编辑','edit','/printReceipt/edit','editData'));
	    btnArray.push(new pageButton('删除','delete','/printReceipt/delete','deleteData'));
	    btnArray.push(new pageButton('打印字段管理','fieldEdit','/printField/home','editField'));
	    btnArray.push(new pageButton('打印模版设计','design','/printFieldPosition/design','printTemplateDesign'));
	    btnArray.push(new pageButton('打印测试','preview','/printComponent/print','printPriview'));
	    btnArray.push(new pageButton('打印预览','preViewData','/printComponent/print','preViewData'));
	    
	    /*btnArray.push(new pageButton('打印导出','printSaveFile','/printComponent/print','printSaveFile'));*/
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
        	listService.addData('/printReceipt/add');
        }else if(operation=='deleteData'){
        	listService.deleteData($scope.tableGrid, $scope.gridApi, URL_PATH.CMD_HEADER+'/printReceipt/delete');
        }else if(operation=='editData'){
        	listService.editData($scope.gridApi, '/printReceipt/edit');
        }else if(operation=='editField'){
        	listService.editData($scope.gridApi,'/printField/home');
        }else if(operation=='printTemplateDesign'){
        	listService.editData($scope.gridApi,'/printFieldPosition/design');
        }else if(operation=='printPriview'){
        	listService.printData($scope.gridApi,URL_PATH.CMD_HEADER+'/printComponent/print/print_test01');
        }else if(operation=='preViewData'){
        	listService.preViewData($scope.gridApi,URL_PATH.CMD_HEADER+'/printComponent/print/print_test01');
        }else if(operation=='printSaveFile'){
        	listService.printSaveFile($scope.gridApi,URL_PATH.CMD_HEADER+'/printComponent/print/print_test01',"测试打印文件");
        }
    }
	   
    var load = function(){
    	listService.loadData(URL_PATH.CMD_HEADER+'/printReceipt/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('HuatekApp').controller('PrintReceiptAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = URL_PATH.CMD_HEADER+'/printReceipt/add';
	 var editDataUrl = URL_PATH.CMD_HEADER+'/printReceipt/edit';
	 var homeUrl = '/printReceipt/home';
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'票据名称','receiptName','string',1,'50'));
		  formFieldArray.push(new FormElement(1,'票据编码','receiptCode','string',1,'50'));
		  formFieldArray.push(new FormElement(1,'子系统编码','systemType','string',1,'100'));
		  formFieldArray.push(new FormElement(1,'纸张高度','paperHeight','string',0,'20'));
		  formFieldArray.push(new FormElement(1,'纸张宽度','paperWidth','string',0,'20'));
		  formFieldArray.push(new FormElement(1,'票据内容查询sql语句','contentSql','string',1,'4000','textarea'));
		  formFieldArray.push(new FormElement(1,'备注','remark','string',0,'100','textarea'));
		  var file = new FormElement(1,'背景图片地址','receiptUrl','string','','50');
		  file.systemHeader = SYSTEM_HEADER.CMD_HEADER;
		  file.model = 'fileSingle';
		  formFieldArray.push(file);
    
    
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

   
