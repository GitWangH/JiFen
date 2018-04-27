'use strict';
   
angular.module('huatek.controllers').controller('CmdImportController', function ($scope, $location, $http,$rootScope, listService,$modal) {
	var codeUrl='api_cmd/cmdImportConfig/queryCode';
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '业务类型', field: 'busiType',width: '10%', enableColumnMenu: false},
		       { name: '业务名称', field: 'busiName',width: '10%', enableColumnMenu: false},
		       { name: '导入人', field: 'operatorName',width: '10%', enableColumnMenu: false},
		       { name: '导入时间', field: 'operationTime',width: '20%', enableColumnMenu: false},
		       { name: '状态', field: 'status1',width: '10%', enableColumnMenu: false,decode:{field:"status",dataKey:"dic.excel_import_status"}},
		       { name: '入库时间', field: 'indbTime',width: '10%', enableColumnMenu: false},
		       { name: '导入参数', field: 'param',width: '10%', enableColumnMenu: false},
		       { name: '导入文件', field: 'excelPath',width: '20%', enableColumnMenu: false},
		      
		    ]
		    
	  };
	
	   
	listService.init($scope,  $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
        
    var start=new queryParam('导入时间','operationTime','date','>=');
    start.componentType='dateTimeSection';
    queryPage.addParam(start);
    var end=new queryParam('到','operationTime','date','<=');
    end.componentType='dateTimeSection';
    queryPage.addParam(end);
    var type=new queryParam('业务类型','busiType','string','=');
    type.componentType='select';
    queryPage.addParam(type);
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
    
       
    var btnArray = [new pageButton('导入日志','log','/cmdImport/home','log')];
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
    	 if(operation=='log'){
    		 if(listService.selectOne($scope.gridApi)){
     			var pop={
     		    		title:'导入日志',
     		    		controller:'ExcelImportLogDetailController',
     		    		passParams:$scope.gridApi.selection.getSelectedRows()[0].log,
     		    		templateView:'static/business/cmd/excelImport/templates/template_showLog.html',
     		    		width:900,
     		    		height:350
     		    	}
     		}
     		listService.popPanel($scope,pop);
    	 }
    }
	   
    var load = function(){
    	listService.loadData($scope,'api_cmd/cmdImport/query', $scope.tableGrid);
    }
    $scope.load=load;
    load();
	   
	$scope.search = function() {
		load();
	};
	
});
angular.module('huatek.controllers').controller('ExcelImportLogDetailController', function ($scope) {
	
    $scope.context=$scope.passParams;

 });
