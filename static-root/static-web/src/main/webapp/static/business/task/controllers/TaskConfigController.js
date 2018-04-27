'use strict';
   
angular.module('huatek.controllers').controller('TaskConfigController', function ($scope, $location, $http,$rootScope, httpService,listService) {
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '名称', field: 'name',width: '22%', enableColumnMenu: false},
		       { name: '类型', field: 'typeName',width: '10%', enableColumnMenu: false},
		       { name: '所属系统', field: 'systemTypeName',width: '10%', enableColumnMenu: false},
		       { name: '状态', field: 'statusName',width: '10%', enableColumnMenu: false},
		       { name: '时间表达式', field: 'cron',width: '10%', enableColumnMenu: false},
		       { name: '任务开始时间', field: 'startTime',width: '10%', enableColumnMenu: false},
		       { name: '任务结束时间', field: 'endTime',width: '10%', enableColumnMenu: false},
		       { name: '重试次数', field: 'retryCount',width: '10%', enableColumnMenu: false},
		       { name: '重试间隔秒数', field: 'retryIntervalSecond',width: '10%', enableColumnMenu: false},
		       { name: '上次执行时间', field: 'lastExcuteTime',width: '10%', enableColumnMenu: false},
		       { name: '下次执行时间', field: 'nextExcuteTime',width: '10%', enableColumnMenu: false},
		       { name: '任务实现服务', field: 'taskClass',width: '10%', enableColumnMenu: false},
		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false},
		       { name: '参数', field: 'params',width: '10%', enableColumnMenu: false}
		    ]
		    
	  };
	
	   
	listService.init($scope, );
       
    var queryPage = new QueryPage(1,10,"nextExcuteTime asc,id desc","false");
    
       
    queryPage.addParam(new queryParam('名称','name','string','alllike'));
    var type =new queryParam('类型','type','string','=');
    type.componentType='select';
    queryPage.addParam(type);
    var status=new queryParam('状态','status','string','=');
    status.componentType='select';
    queryPage.addParam(status);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage($scope,queryPage);
    listService.initQueryParams($scope,"api_task/taskConfig/code");
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
    btnArray.push(new pageButton('添加','add','/taskConfig/add','addData'));
    btnArray.push(new pageButton('编辑','edit','/taskConfig/edit','editData'));
    btnArray.push(new pageButton('运行记录','log','/taskHistory/home','logData'));
    btnArray.push(new pageButton('暂停','stop','/taskConfig/edit','stopData'));
    btnArray.push(new pageButton('恢复','resume','/taskConfig/edit','resumeData'));
    btnArray.push(new pageButton('删除','edit','/taskConfig/delete','deleteData'));
    btnArray.push(new pageButton('手动执行','execute','/taskConfig/edit','executeTask'));
          
   listService.setButtonList($scope,btnArray);

       
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
    		listService.addData($scope,new popup('新增任务','/taskConfig/add',null,null,null,load));
        }
  	  	else if(operation=='editData'){
    		listService.editData($scope,$scope.gridApi, new popup('编辑任务','/taskConfig/edit',null,null,null,load));
   	  	}
  	  	else if(operation=='logData'){
  	  		listService.editData($scope,$scope.gridApi, new popup('运行历史','/taskHistory/home'));
 	  	}
       	else if(operation=='deleteData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api_task/taskConfig/delete');
  		}else if(operation=='executeTask'){
  			if($scope.gridApi.selection.getSelectedRows().length==1){
  				$http.get("api_task/taskConfig/executeImmediate/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(){
  					load();
  				});
  			}else{
  				bootbox.alert("请至少勾选一条数据！");
  			}
    		
  		}
       	else if(operation=='stopData'){
    		if($scope.gridApi.selection.getSelectedRows().length < 1){
				bootbox.alert("请至少勾选一条数据！");
				return false;
			}
	    	if($scope.gridApi.selection.getSelectedRows().length > 1){
				bootbox.alert("请勾选一条数据！");
				return false;
			}
	    	var status=$scope.gridApi.selection.getSelectedRows()[0].status;
	    	if(status=='stop'||status=='end'){
	    		bootbox.alert("不能对暂定和完成的任务操作！");
	    		return;
	    	}
    		$scope.promise=httpService.post($scope,"api_task/taskConfig/stop/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(response){
    			load();
    		});
        }
  	  	else if(operation=='resumeData'){
	  	  	if($scope.gridApi.selection.getSelectedRows().length < 1){
				bootbox.alert("请至少勾选一条数据！");
				return false;
			}
	    	if($scope.gridApi.selection.getSelectedRows().length > 1){
				bootbox.alert("请勾选一条数据！");
				return false;
			}
	    	var status=$scope.gridApi.selection.getSelectedRows()[0].status;
	    	if(status!='stop'){
	    		bootbox.alert("请选择暂停的任务！");
	    		return;
	    	}
			$scope.promise=httpService.post($scope,"api_task/taskConfig/resume/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(response){
				load();
			});
   	  	}
    }
	   
    var load = function(){
    	listService.loadData($scope,'api_task/taskConfig/query', $scope.tableGrid);
    }
    $scope.load=load;
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('huatek.controllers').controller('TaskConfigAddController', function ($scope, $location, $http,  editService,$rootScope) {
	    
	 var addDataUrl = 'api_task/taskConfig/add';
	 var editDataUrl = 'api_task/taskConfig/edit';
	 var homeUrl = '/taskConfig/home';
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'名称','name','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'时间表达式','cron','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'任务开始时间','startTime','string',1,'30',"dateTime"));
		  formFieldArray.push(new FormElement(1,'任务结束时间','endTime','string',1,'30',"dateTime"));
		  formFieldArray.push(new FormElement(1,'重试次数','retryCount','number',1,'1'));
		  formFieldArray.push(new FormElement(1,'重试间隔秒数','retryIntervalSecond','number',1,'5'));
		  formFieldArray.push(new FormElement(1,'所属系统','systemType','string',1,'30',"select"));
		  formFieldArray.push(new FormElement(1,'任务实现服务','taskClass','string',1,'128'));
		  formFieldArray.push(new FormElement(1,'参数','params','string',0,'30'));
		  formFieldArray.push(new FormElement(1,'备注','remark','string',0,'30'));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope, $http);
    
       
    editService.setFormFields($scope,formFieldArray); 
    editService.initParams($scope,"api_task/taskConfig/code")
       
    
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

   
