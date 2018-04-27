
angular.module('HuatekApp').controller('FwOnlineController', function ($scope, $location, $http,$rootScope,$modal, listService) {
	   
	$scope.tableGrid = {
		    paginationPageSizes: [100, 500, 1000, 2000,5000],
		    paginationPageSize: 100,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '登录账户', field: 'acctName',width: '15%', enableColumnMenu: false},
		       { name: '用户姓名', field: 'userName',width: '15%', enableColumnMenu: false},
		       { name: '登录时间', field: 'loginTime',width: '20%', enableColumnMenu: false},
		       { name: '所属机构', field: 'orgName',width: '15%', enableColumnMenu: false},
		       { name: '登录IP', field: 'loginIp',width: '15%', enableColumnMenu: false},
		       { name: '终端环境', field: 'userAgent',width: '20%', enableColumnMenu: false}
		       
		       
		      
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,100,"id desc","false");
    
    var groupId=new queryParam('所属集团','groupId','string','=');
    groupId.componentType='groupSelect';
    queryPage.addParam(groupId);
    queryPage.addParam(new queryParam('登录人姓名','userName','string','like'));
    
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
    var notice=new pageButton('发送通知','notice','','noticeBtn');
    notice.show=true;
    btnArray.push(notice);
    var model=new pageButton('切换模式','model','','modelBtn');
    model.show=($rootScope.acctName=='admin');
    btnArray.push(model);
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
    	 if(operation=='noticeBtn'){
    		 var scope = $rootScope.$new();
    	        scope.title="系统维护通知";
    	        scope.content="10分钟后系统开始维护，大概需要30分钟时间,请及时做好数据保存，退出系统，给您造成不变请谅解";
    	        scope.popCallBack=function(){
    	    	}
    			var modal= $modal({
    			     show: false,
    			     backdrop: 'static',
    			      controller: "OaMsgInfoSendController",
    			     template: TemplatePrefix+"oa/msg/sendMsg.html"+'?t='+huatek.version,
    			     scope:scope
    			 });
    			modal.$promise.then(modal.show);
    	}
    	 if(operation=='modelBtn'){
    		 $http.get('api_task/session/changeMode/'+(!$scope.model)).success(function(){
    			 load();
    		 });
    	}
    	
    }
	   
    var load = function(){

		
		var postQueryPage = copyQueryPage(queryPage);
		
	
		$scope.promise = $http.post('api_task/session/queryOnlineCount', postQueryPage)
			   .success(function (response) {
				   $scope.onlineCount=response.onlineCount;
				   $scope.onlineUser=response.onlineUser;
				   $scope.onlineUser=response.onlineUser;
				   $scope.ipCount=response.ipCount;
				   $scope.model=response.model;
				   
				   var datapage=response.datapage;
				   if (datapage.totalRows == undefined || datapage.totalRows == 0) {
					   $scope.tableGrid.data = [];
				   } else{
					   var data=datapage.content;
					   listService.decodeTable(data,$scope.tableGrid,$scope);
					  $scope.tableGrid.data = datapage.content;
				   }
				   $scope.tableGrid.totalItems = datapage.totalRows;
				   
				   
			});
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});
