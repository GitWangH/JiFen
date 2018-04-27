'use strict';
   
angular.module('HuatekApp').controller('OaMsgRangeController', ['$scope', '$location','$routeParams', '$http','$rootScope', '$modal','listService',function ($scope, $location,$routeParams, $http,$rootScope, $modal,listService) {
	$scope.triggerId=$routeParams.triggerId;
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		         { name: '类型', field: 'authTypeName',width: '10%', enableColumnMenu: false},
		  		 { name: '范围对象', field: 'authObjectName',width: '10%', enableColumnMenu: false}
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    
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
    btnArray.push(new pageButton('添加角色','addRole','/oaMsgRange/addRole','addRoleData'));
    btnArray.push(new pageButton('添加组织','addOrg','/oaMsgRange/addOrg','addOrgData'));
    btnArray.push(new pageButton('删除','delete','/oaMsgRange/delete','deleteData'));
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
    	if(operation=="addRoleData"){
    		var scope = $rootScope.$new();
            scope.triggerId=$scope.triggerId;
            scope.popCallBack=function(){
        		load();
        	}
			var modal= $modal({
    		     show: false,
    		     backdrop: 'static',
    		      controller: "OaMsgRangeAddRoleController",
    		     template: TemplatePrefix+"oa/msg/listSelect.html"+'?t='+huatek.version,
    		     scope:scope
    		 });
			modal.$promise.then(modal.show)
    	}else if(operation=="addOrgData"){
        		var scope = $rootScope.$new();
                scope.triggerId=$scope.triggerId;
                scope.popCallBack=function(){
            		load();
            	}
    			var modal= $modal({
        		     show: false,
        		     backdrop: 'static',
        		      controller: "OaMsgRangeAddOrgController",
        		     template: TemplatePrefix+"oa/msg/listSelect.html"+'?t='+huatek.version,
        		     scope:scope
        		 });
    			modal.$promise.then(modal.show)
    	}else if(operation=="deleteData"){
    		listService.deleteData($scope.tableGrid, $scope.gridApi, URL_PATH.OA_HEADER+'/oaMsgRange/delete');
    	}
    }
	   
    var load = function(){
    	listService.loadData('api_oa/oaMsgRange/query/'+$scope.triggerId, $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
}]);


angular.module('HuatekApp').controller('OaMsgRangeAddRoleController', ['$scope', '$location','$http', '$routeParams', 'editService','$rootScope',function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 100,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '角色编码', field: 'ROLE_CODE',width: '20%', enableColumnMenu: false},
		       { name: '角色名称', field: 'NAME',width: '20%', enableColumnMenu: false},
		       { name: '角色描述', field: 'COMMENTS',width: '60%', enableColumnMenu: false},
		    ]
		    
	  };
	
	
	
    var queryPage = new QueryPage(1,100,"id desc ","false");
   
    queryPage.addParam(new queryParam('角色编码','code','string','like'));
    queryPage.addParam(new queryParam('角色名称','name','string','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    $scope.queryFieldList = queryPage.queryParamList;
    for(var i=0;i<$scope.queryFieldList.length;i++){
    	$scope.queryFieldList[i].isShowSelect=true;

	}
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
	var load = function(){
		var postQueryPage = copyQueryPage(queryPage);
		$scope.promise = $http.post('api_oa/oaMsgRange/queryRole', postQueryPage)
		   .success(function (response) {
			   if (response.totalRows == undefined || response.totalRows == 0) {
				   $scope.tableGrid.data = [];
			   } else{
				   $scope.tableGrid.data = response.content;
			   }
			   $scope.tableGrid.totalItems = response.totalRows;
			   
		});
	}
	    
	load();
		
	$scope.search = function() {
		load();
	};
	
	
	 var addPersonUrl = 'api_oa/oaMsgRange/addRole/'+$scope.triggerId;


	
       
    $scope.save = function(){
    	var rows=$scope.gridApi.selection.getSelectedRows();
    	if(rows.length==0){
    		submitTips("请至少勾选一条数据！",'warning');
    		return;
    	}
    	
    	
    	$http.post(addPersonUrl,rows).success(function(response){
    		$scope.$hide();
    		if($scope.popCallBack){
    			$scope.popCallBack();
    		}
    	});
    } 
}]);
angular.module('HuatekApp').controller('OaMsgRangeAddOrgController',['$scope', '$location', '$http', '$routeParams', 'editService','$rootScope', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '组织编码', field: 'ORG_CODE',width: '20%', enableColumnMenu: false},
		       { name: '组织名称', field: 'ORG_NAME',width: '20%', enableColumnMenu: false},
		    ]
		    
	  };
	
	
	
    var queryPage = new QueryPage(1,100,"id desc ","false");
   
    queryPage.addParam(new queryParam('组织编码','code','string','like'));
    queryPage.addParam(new queryParam('组织名称','name','string','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    $scope.queryFieldList = queryPage.queryParamList;
    for(var i=0;i<$scope.queryFieldList.length;i++){
    	$scope.queryFieldList[i].isShowSelect=true;

	}
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
	var load = function(){
		var postQueryPage = copyQueryPage(queryPage);
		$scope.promise = $http.post('api_oa/oaMsgRange/queryOrg', postQueryPage)
		   .success(function (response) {
			   if (response.totalRows == undefined || response.totalRows == 0) {
				   $scope.tableGrid.data = [];
			   } else{
				   $scope.tableGrid.data = response.content;
			   }
			   $scope.tableGrid.totalItems = response.totalRows;
			   
		});
	}
	    
	load();
		
	$scope.search = function() {
		load();
	};
	
	
	 var addPersonUrl = 'api_oa/oaMsgRange/addOrg/'+$scope.triggerId;


	
       
    $scope.save = function(){
    	var rows=$scope.gridApi.selection.getSelectedRows();
    	if(rows.length==0){
    		submitTips("请至少勾选一条数据！",'warning');
    		return;
    	}
    	
    	
    	$http.post(addPersonUrl,rows).success(function(response){
    		$scope.$hide();
    		if($scope.popCallBack){
    			$scope.popCallBack();
    		}
    	});
    } 
}]);