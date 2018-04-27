'use strict';
   
angular.module('huatek.controllers').controller('TaskHistoryController', function ($scope, $location, $http,$rootScope,$routeParams,$modal, listService) {
	$scope.taskId = $scope.editId;
	$scope.editId=null;   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '执行开始时间', field: 'excuteStartTime',width: '20%', enableColumnMenu: false},
		       { name: '执行结束时间', field: 'excuteEndTime',width: '20%', enableColumnMenu: false},
		       { name: '执行结果', field: 'resultName',width: '10%', enableColumnMenu: false},
		       { name: '异常信息', field: 'exception',width: '10%', enableColumnMenu: false,cellTemplate:'<a  ng-click="grid.appScope.showException(row.entity.exception)">{{row.entity.exception&&row.entity.exception.length>0?"详情":""}}</a>'},
		       { name: '引擎IP', field: 'engineIp',width: '10%', enableColumnMenu: false},
		       { name: '引擎地址', field: 'engineName',width: '20%', enableColumnMenu: false},
		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	$scope.showException=function(exception){
			var pop= {
		   		     title: "异常信息",
		   		     passParams:exception,
		   		     controller:  'TaskExceptionLogController',
		   		     templateView: 'static/business/task/templates/resultMessage.html',
		   		     
		   		 };
			listService.popPanel($scope,pop);
	   }
	   
	listService.init($scope);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    var result=new queryParam('执行结果','result','string','=');
    result.componentType='select';
       
    queryPage.addParam(result);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage($scope,queryPage);
    listService.initQueryParams($scope,"api_task/taskHistory/code");
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
          
   listService.setButtonList($scope,btnArray);
   
       
    $scope.execute = function(operation, param){
    	
    }
	   
    var load = function(){
    	listService.loadData($scope,'api_task/taskHistory/query/'+$scope.taskId, $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

angular.module('huatek.controllers').controller('TaskExceptionLogController', function ($scope) {
	
    $scope.context=$scope.passParams;

 });
