'use strict';
   
angular.module('huatek.controllers').controller('ExceptionController', function ($scope, $location, $http, listService) {
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
		      { name: '账户', field: 'acctName',width: '9%',enableColumnMenu: false },
		      { name: '操作人', field: 'userName',width: '9%',enableColumnMenu: false },
		      { name: '错误时间', field: 'createTime', width: '10%',enableColumnMenu: false},
		      { name: '菜单', field: 'sourceName', width: '5%',enableColumnMenu: false},
		      { name: '错误代码', field: 'ecptCode', width: '10%',enableColumnMenu: false},
		      { name: '错误内容', field: 'ecptMessage', width: '55%',enableColumnMenu: false},
		      
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
	 var btnArray = [];
     btnArray.push(new customButton('错误详情','detail',true,'showDetail'));
     
       
listService.setButtonList($scope,null,btnArray);
    var queryPage = new QueryPage(1,10,"id desc","false");
    var groupId=new queryParam('所属集团','fwAccount.fwOrg.level1,fwAccount.fwOrg.level2','string','=');
    groupId.componentType='groupSelect';
    queryPage.addParam(groupId);
    queryPage.addParam(new queryParam('操作人','fwAccount.acctName','string','like'));
    queryPage.addParam(new queryParam('操作时间','createTime','dateTime','>'));
    queryPage.addParam(new queryParam('———','createTime','dateTime','<'));
       
    listService.setQueryPage($scope,queryPage);
    $scope.execute = function(operation, param){
    	if(operation=='showDetail'){
    		if(listService.selectOne($scope.gridApi)){
    			var pop={
    		    		title:'异常明细',
    		    		controller:'ExceptionDetailController',
    		    		passParams:$scope.gridApi.selection.getSelectedRows()[0].ecptStack,
    		    		templateView:'static/business/system/exception/templates/resultMessage.html',
    		    		width:900,
    		    		height:350
    		    	}
    		}
    		listService.popPanel($scope,pop);
    	}
    }
	   
    var load = function(){
    	listService.loadData($scope,'api/exception/home', $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});
angular.module('huatek.controllers').controller('ExceptionDetailController', function ($scope) {
	
    $scope.context=$scope.passParams;

 });