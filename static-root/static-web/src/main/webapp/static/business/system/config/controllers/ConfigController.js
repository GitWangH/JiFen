'use strict';
   
angular.module('huatek.controllers').controller('ConfigController', function ($scope, $location, $http, $rootScope,listService) {
	/***
	 * 定义显示的表格. 
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection: true,
	        enableSelectAll: false,
	        multiSelect: false,
		    columnDefs: [
		      { name: '参数代码', field: 'code',width: '20%', enableColumnMenu: false},
		      { name: '参数名称', field: 'name',width: '20%',enableColumnMenu: false },
		      { name: '参数值', field: 'value',width: '20%', enableColumnMenu: false},
		      { name: '生效状态', field: '_status',width: '20%', enableColumnMenu: false,decode:{field:'status',dataKey:'dic.dic_whether_use'}},
		      { name: '系统级别', field: 'isSystem',width: '20%', enableColumnMenu: false,cellTemplate:"<a ng-if='!row.entity.tenandId'>是</a><a ng-if='row.entity.tenandId'>否</a>"},
		      { name: '描述', field: 'description',width: '20%', enableColumnMenu: false},
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
	   
	listService.init($scope,$http);
       
    var queryPage = new QueryPage(1,10,"id asc","false");
       
    queryPage.addParam(new queryParam('参数名称','name','string','like'));
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage($scope,queryPage);
    
   
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/config/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/config/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/config/delete','deleteData'));
        
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
        if(operation=='addData'){
        	listService.addData($scope,new popup('新增参数','/config/add',null,null,null,load));
        }else if(operation=='deleteData'){
        	listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api/config/delete');
        }else if(operation=='editData'){
        	listService.editData($scope,$scope.gridApi, new popup('修改参数','/config/edit',null,null,null,load));
        }
    }
	   
    var load = function(){
    	listService.loadData($scope,'api/config/query', $scope.tableGrid); 
    }
    
    
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});
   
angular.module('HuatekApp').controller('ConfigEditController', function ($rootScope,$scope, $location, $http, $routeParams, editService) {
	    
	 var addDataUrl = 'api/config/add';
	 var editDataUrl = 'api/config/edit';
	 var homeUrl = '/database/configList';
	
	 var columnWrapArray = [];
	 columnWrapArray.push(new multipleColumn(1,'基本信息'));
	 columnWrapArray.push(new multipleColumn(2,'描述'));
	 $scope.columnWrapArray = columnWrapArray;
	 
    /*FormElement(1,'劳务类型','labourType','string','require','30','select');*/
	 var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'参数编码','code','string','require','50'));
    formFieldArray.push(new FormElement(1,'参数名称','name','string','require','50'));
    formFieldArray.push(new FormElement(1,'参数值','value','string','require','500'));
    var status = new FormElement(1,'是否生效','status','string','require','10','select','','','','dic.dic_whether_use');
//    status.items = [{_showField:'生效',_returnField:'1'},{_showField:'失效',_returnField:'0'}];
    formFieldArray.push(status);
    formFieldArray.push(new FormElement(2,'参数描述','description','string','','1000','textarea'));
    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;
    
	editService.init($scope, $http);
    
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


