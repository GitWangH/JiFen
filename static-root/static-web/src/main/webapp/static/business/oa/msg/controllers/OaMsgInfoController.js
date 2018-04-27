'use strict';
   
angular.module('HuatekApp').controller('OaMsgInfoController',['$scope', '$location','$modal', '$http','$rootScope', 'listService', function ($scope, $location,$modal, $http,$rootScope, listService) {
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
		    columnDefs: [
		       { name: '消息类别', field: 'typeName',width: '10%', enableColumnMenu: false},
		       { name: '创建时间', field: 'createTime',width: '20%', enableColumnMenu: false},
		       { name: '发送人', field: 'sendUserName',width: '10%', enableColumnMenu: false},
		       { name: '消息标题', field: 'msgTitle',width: '50%', enableColumnMenu: false},
		       { name: '状态', field: 'msgStatusName',width: '10%', enableColumnMenu: false}
		    ]
		    
	  };
	
	   
	listService.init($scope);
    var queryPage = new QueryPage(1,10,"status desc,oaMsgInfo.createTime desc","false");
    
    queryPage.addParam(new queryParam('时间 从','oaMsgInfo.createTime','date','>='));
    queryPage.addParam(new queryParam('至','oaMsgInfo.createTime','date','<='));
    
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
    var detail=new pageButton('查看','detail','/oaMsgInfo/detail','detailData');
    btnArray.push(detail);
/*    var handle=new pageButton('处理','handle','/oaMsgInfo/handle','handleData');
    btnArray.push(handle);*/
    var readAll=new pageButton('全部已读','readAll','/oaMsgInfo/readAll','readAllData');
    btnArray.push(readAll);
    
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
    	
    	 if(operation=='detailData'){
    		 var rows=$scope.gridApi.selection.getSelectedRows();
    	    	if(rows.length!=1){
    	    		submitTips('请至少勾选一条数据！','warning');
    	    		return;
    	    	}
    	     $http.get("api_oa/oaMsgInfo/read/"+rows[0].id).success(function(response){
    	    	 load();
    	     });
    		
    		 if(listService.selectOne($scope.gridApi)){
     			var pop={
     		    		title:rows[0].msgTitle,
     		    		controller:'MessageDetailController',
     		    		passParams:rows[0].msgContent,
     		    		templateView:'static/business/system/exception/templates/resultMessage.html',
     		    		width:900,
     		    		height:350
     		    	}
     		}
     		listService.popPanel($scope,pop);
    	 }else if(operation=='replyData'){
    		 
    	 }else if(operation=='handleData'){
    		 var rows=$scope.gridApi.selection.getSelectedRows();
 	    	if(rows.length!=1){
 	    		submitTips('请至少勾选一条数据！','warning');
 	    		return;
 	    	}
 	    	var path=rows[0].linkUrl;
 	    	if(path==null||path.trim()==""||path.trim()=="null"){
 	    		submitTips('此消息无需处理！','info');
 	    	}else{
 	    		$location.path(path);
 	    	}
 	    		
    	 }else if(operation=='readAllData'){
    		 $http.get("api_oa/oaMsgInfo/readAll").success(function(response){
    			 load();
    		 });
    	 }
    }
	   
    var load = function(){
    	listService.loadData($scope,'api_oa/oaMsgInfo/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
}]);

   
angular.module('HuatekApp').controller('OaMsgInfoSendController',['$scope', '$location', '$http', '$routeParams', 'editService','$rootScope', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = 'api_oa/oaMsgInfo/add';
	 var editDataUrl = 'api_oa/oaMsgInfo/edit';
	 var homeUrl = '/oaMsgInfo/home';
	
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'主键','id','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'接收人','receive','string',1,'1024'));
		  formFieldArray.push(new FormElement(1,'标题','msgTitle','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'内容','msgContent','string',1,'30'));
    
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
}]);
angular.module('huatek.controllers').controller('MessageDetailController', function ($scope) {
	
    $scope.context=$scope.passParams;

 });

