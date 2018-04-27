'use strict';
   
angular.module('huatek.controllers').controller('FimActionLogController', function ($scope, $location, $http,$rootScope, $modal,listService) {
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '登录账号', field: 'acctName',width: '10%', enableColumnMenu: false},
		       { name: '用户名', field: 'userName',width: '10%', enableColumnMenu: false},
		       { name: '操作菜单', field: 'fwSource.title',width: '10%', enableColumnMenu: false},
		       { name: '操作内容', field: 'msg',width: '30%', enableColumnMenu: false},
		       { name: '操作时间', field: 'actionTime',width: '20%', enableColumnMenu: false},
		       { name: '客户端IP', field: 'clientIp',width: '15%', enableColumnMenu: false},
		       { name: '端口号', field: 'clientPort',width: '10%', enableColumnMenu: false},
		       { name: '浏览器', field: 'userAgent',width: '35%', enableColumnMenu: false}
		       
		       
		      
		    ]
		    
	  };
	
	   
	listService.init($scope, $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
   
    
    var groupId=new queryParam('所属集团','fwAccount.fwOrg.level1,fwAccount.fwOrg.level2','string','=');
    groupId.componentType='groupSelect';
    queryPage.addParam(groupId);
    queryPage.addParam(new queryParam('用户名','fwAccount.userName','string','like'));
    queryPage.addParam(new queryParam('操作时间','actionTime','dateTime','>='));
    queryPage.addParam(new queryParam('——','actionTime','dateTime','<='));
    queryPage.addParam(new queryParam('客户端IP','clientIp','string','like'));
    queryPage.addParam(new queryParam('端口号','clientPort','string','like'));
    
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage($scope,queryPage);
    
       
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
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
    }
	   
    var load = function(){
    	listService.loadData($scope,'api/fwActionLog/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});


