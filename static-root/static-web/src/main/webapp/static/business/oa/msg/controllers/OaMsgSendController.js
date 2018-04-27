'use strict';

angular.module('HuatekApp').controller('OaMsgInfoSendController',['$scope', '$location', '$http', '$routeParams','$modal', 'editService','$rootScope', function ($scope, $location, $http, $routeParams,$modal, editService,$rootScope) {
	
	    
	 var sendUrl = 'api_oa/oaMsgInfo/send';
	 $scope.tableGrid = {
			    paginationPageSizes: [10, 25, 50,100],
			    paginationPageSize: 10,
			    useExternalPagination: false,
			    columnDefs: [
			       { name: '工号', field: 'userId',width: '20%', enableColumnMenu: false},
			       { name: '姓名', field: 'name',width: '20%', enableColumnMenu: false},
			       { name: '邮箱', field: 'email',width: '20%', enableColumnMenu: false},
			       { name: '电话', field: 'phone',width: '40%', enableColumnMenu: false}
			    ]
			    
		  };
	
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
    var formFieldArray = [];
    var isAll=new FormElement(1,'系统全部人员','isAll','string',0,'30');
    	if($scope.isAllHiden){
    		isAll.isShow=false;
    	}
    	formFieldArray.push(isAll);
    	var title=new FormElement(1,'标题','title','string',1,'50');
    	title.value=$scope.title;
		formFieldArray.push(title);
		var content=new FormElement(1,'内容','content','string',1,'1024');
		content.value=$scope.content;
		formFieldArray.push(content);
		  
    
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope, $location, $http);
    
       
    editService.setFormFields(formFieldArray); 
    
    $scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	}; 
	$scope.addPerson=function(){
		var scope = $rootScope.$new();
       
        scope.popCallBack=function(rows){
        	if($scope.tableGrid.data){
        		for(var i=0;i<rows.length;i++){
        			var row=rows[i];
        			var hasIn=false;
        			for(var j=0;j<$scope.tableGrid.data.length;j++){
        				if($scope.tableGrid.data[j].id==row.id){
        					hasIn=true;
        					break;
        				}
        			}
        			if(!hasIn){
        				$scope.tableGrid.data.push(rows[i]);
        			}
        			
        		}
        	}else{
        		$scope.tableGrid.data=rows;
        	}
    	}
		var modal= $modal({
		     show: false,
		     backdrop: 'static',
		      controller: "OaMsgAddPersonController",
		     template: TemplatePrefix+"oa/msg/listSelect.html"+'?t='+huatek.version,
		     scope:scope
		 });
		modal.$promise.then(modal.show)
	}
	$scope.delPerson=function(){
		angular.forEach($scope.gridApi.selection.getSelectedRows(), function (data, index) {
			
			$scope.tableGrid.data.splice($scope.tableGrid.data.lastIndexOf(data), 1);
			
		});
	}
    	
       
   
       
    $scope.send = function(){
    	if($scope.tableGrid.data.length==0&&!isAll.value){
    		submitTips('请添加人员！','warning');
    		return;
    	}
    	if(editService.isAllowPost()){
    		var data={isAll:isAll.value,title:title.value,content:content.value,userIds:[]};
    		angular.forEach($scope.tableGrid.data, function (item, index) {
    			
    			data.userIds.push(item.id);
    			
    		});
    		data.userIds=data.userIds.join(",");
    		if(isAll.value==true){
    			bootbox.confirm('确定确认要发给系统所有用户吗?',function(){
            		$http.post(sendUrl,data).success(function(response){
            			$scope.$hide();
                	});
        		});
    		}else{
    			$http.post(sendUrl,data).success(function(response){
        			$scope.$hide();
            	});
    		}
    	}
    	
    }
}]);

angular.module('HuatekApp').controller('OaMsgAddPersonController',['$scope', '$location', '$http', 'editService','$rootScope', function ($scope, $location, $http, editService,$rootScope) {
	
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '总公司', field: 'level1Name',width: '20%', enableColumnMenu: false, grouping: {groupPriority: 0}},
		       { name: '下级组织', field: 'level2Name',width: '20%', enableColumnMenu: false, grouping: {groupPriority: 1}},
		       { name: '用户名', field: 'userName',width: '20%', enableColumnMenu: false},
		       { name: '姓名', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '邮箱', field: 'email',width: '20%', enableColumnMenu: false},
		       { name: '电话', field: 'phone',width: '20%', enableColumnMenu: false}
		    ]
		    
	  };
	
	
	
    var queryPage = new QueryPage(1,10,"level1 asc,level2 asc,level3 asc,level4 asc","false");
   
    queryPage.addParam(new queryParam('所属组织','orgName','string','alllike'));
    queryPage.addParam(new queryParam('用户名','userName','string','alllike'));
    queryPage.addParam(new queryParam('姓名','name','string','alllike'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    $scope.queryFieldList = queryPage.queryParamList;
    for(var i=0;i<$scope.queryFieldList.length;i++){
    	$scope.queryFieldList[i].isShowSelect=true;

	}
    $scope.resetSearch = function() {
		for (var i = 0; i < queryPage.queryParamList.length; i++) {
			queryPage.queryParamList[i].value = null;
			if(queryPage.queryParamList[i].type == 'dateTime'){
				queryPage.queryParamList[i].minValue = null;
				queryPage.queryParamList[i].maxValue = null;
			}
		}
	};
       
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
		$scope.promise = $http.post('api_workflow/workflowAuthority/queryPerson', postQueryPage)
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
	
       
    $scope.save = function(){
    	var rows=$scope.gridApi.selection.getSelectedRows();
    	if(rows.length==0){
    		submitTips("请至少勾选一条数据！",'warning');
    		return;
    	}
    	$scope.popCallBack(rows);
    	$scope.$hide();
    } 
    
   
}]);
