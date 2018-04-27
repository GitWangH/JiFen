'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('ApplyListController', function ($scope, $location, $http,$rootScope,$modal, listService,excelService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '流程名称', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '所属部门', field: 'departmentyName',width: '20%', enableColumnMenu: false,decode:{field:'department',dataKey:"dic.dept"}},
		       { name: '流程标识', field: 'processDefineKey',width: '20%', enableColumnMenu: false}
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
 
    queryPage.addParam(new queryParam('名称','name','string','alllike'));
    var department=new queryParam('所属部门','department','string','=');
    department.componentType="select";
    department.dropDataUrl="dic.dept";
    queryPage.addParam(department);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage(queryPage);
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
        btnArray.push(new pageButton('撰写请求','start','/myApply/start','startData'));
        btnArray.push(new pageButton('查看流程图','chart','/myApply/chart','showChart'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
	        if(operation=='startData'){
	        	if($scope.gridApi.selection.getSelectedRows().length != 1){
	    			submitTips('请勾选一条数据！','warning');
	    			return false;
	    		}
	        	var title=$scope.gridApi.selection.getSelectedRows()[0].name+"——填单";
	        	var processDefineKey=$scope.gridApi.selection.getSelectedRows()[0].processDefineKey;
	        	var formType=$scope.gridApi.selection.getSelectedRows()[0].formType;
	        	var formImpl=$scope.gridApi.selection.getSelectedRows()[0].formImpl;
	        	if(!formType){
	        		submitTips("该流程没有设置发起表单！",'warning');
	    			return false;
	        	}
	        	if(formType==='url'){
        			var url=formImpl;
	        		$http.get( "api/menu/querySourceByUrl?url="+url).success(function (source) {
	        			var scope = $rootScope.$new();
	        			sceop.processDefineKey=processDefineKey;
		                scope.popCallBack=function(){
		                	
		                	/*bootbox.alert("启动成功！");*/
		            	};
	        			var modal= $modal({
		        		     title: title, 
		        		     backdrop: 'static',
		        		     show: false,
		        		     controller: source.controller,
		        		     template: TemplatePrefix+source.view,
		        		     scope:scope
		        		 });
	        			modal.$promise.then(modal.show);
		            	
		            	
		                 
	        		});
        		}else{
        			var busiCode=formImpl;
        			var scope = $rootScope.$new();
        			scope.busiCode=busiCode;
        			scope.processDefineKey=processDefineKey;
	                scope.popCallBack=function(){
	                	
	                	/*bootbox.alert("启动成功！");*/
	            	};
        			var modal= $modal({
	        		     title: title, 
	        		     show: false,
	        		      controller: "ApplyEasyFormController",
	        		     template: TemplatePrefix+"workflow/apply/template_easyForm.html"+'?t='+huatek.version,
	        		     scope:scope
	        		 });
        			modal.$promise.then(modal.show); 
        		}
	        	
	        }else if(operation=='showChart'){
	        	if($scope.gridApi.selection.getSelectedRows().length != 1){
	    			submitTips("请勾选一条数据！",'warning');
	    			return false;
	    		}
	        	var title=$scope.gridApi.selection.getSelectedRows()[0].name;
	        	var processDefineKey=$scope.gridApi.selection.getSelectedRows()[0].processDefineKey;
	        	var scope = $rootScope.$new();
	        	
	        	var modal= $modal({
	       		     title: title, 
	       		     show: false,
	       		     template: TemplatePrefix+"workflow/easyfrom/processChart.html"+'?t='+huatek.version,
	       		     scope:scope
       		 	});
	        	modal.$promise.then(modal.show).then(function(){
	        		scope.imgSrc="api_workflow/workflowBlue/chart/"+processDefineKey+"?t="+(new Date().getTime());
	        	}); 
	        }

    };

	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData('api_workflow/myApply/list', $scope.tableGrid);
    };
    $scope.load=load;
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});

/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('ApplyHisController', function ($scope, $location, $http,$rootScope, listService,excelService,$modal) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		    
		    /*   { name: '任务描述', field: 'description',width: '40%', enableColumnMenu: false},*/
		    	{ name: '流程编号', field: 'id',width: '20%', enableColumnMenu: false},
		    	{ name: '标题', field: 'title',width: '20%', enableColumnMenu: false},
		       { name: '流程名称', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '提交时间', field: 'startTime',width: '15%', enableColumnMenu: false},
		       { name: '结束时间', field: 'endTime',width: '15%', enableColumnMenu: false},
		       { name: '审批结果', field: 'result',width: '10%', enableColumnMenu: false},
		       { name: '审批意见', field: 'resultMessage',width: '40%', enableColumnMenu: false}
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    queryPage.addParam(new queryParam('流程编号','flowId','string'));
    queryPage.addParam(new queryParam('标题','title','string','alllike'));
    queryPage.addParam(new queryParam('发起时间','startTime','date','>='));
    queryPage.addParam(new queryParam('——','startTime','date','<='));
    var sameDuty=new queryParam('本岗位所有','sameDuty','boolean','=',false);
    sameDuty.componentType="checkbox";
    queryPage.addParam(sameDuty);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage(queryPage);
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
    	btnArray.push(new pageButton('查看','detail','/myApply/start','detailData'));
    	btnArray.push(new pageButton('撤回','withdrow','/myApply/start','withdrowData'));
    	btnArray.push(new pageButton('打印','print','/myApply/start','printData'));
    	btnArray.push(new pageButton('编辑重发','detail','/myApply/start','editData'));
        btnArray.push(new pageButton('流程进度','progress','/myApply/start','progressData'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='withdrowData'){
    		if($scope.gridApi.selection.getSelectedRows().length != 1){
    			submitTips('请勾选一条数据！','warning');
    			return false;
    		}
    		$http.post("api_workflow/myApply/withdraw/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(){
    			load();
    		});
    	}else if(operation=='detailData'){
        	if($scope.gridApi.selection.getSelectedRows().length != 1){
    			submitTips('请勾选一条数据！','warning');
    			return false;
    		}
        	var title=$scope.gridApi.selection.getSelectedRows()[0].name+"";
        	var processDefineKey=$scope.gridApi.selection.getSelectedRows()[0].processDefineKey;
        	var busiId=$scope.gridApi.selection.getSelectedRows()[0].busiId;
        	var formType=$scope.gridApi.selection.getSelectedRows()[0].formType;
        	var formImpl=$scope.gridApi.selection.getSelectedRows()[0].formImpl;
        	var flowId=$scope.gridApi.selection.getSelectedRows()[0].id;
        	if(!formType){
        		submitTips("该流程没有设置全局表单！",'warning');
    			return false;
        	}
        	if(formType==='url'){
    			var url=formImpl;
        		$http.get( "api/menu/querySourceByUrl?url="+url).success(function (source) {
        			var scope = $rootScope.$new();
        			scope.onlyView=true;
        			scope.busiId=busiId;
        			scope.processDefineKey=processDefineKey;
        			scope.processInstanceId=flowId;
	                scope.popCallBack=function(){
	                	
	                	/*bootbox.alert("启动成功！");*/
	            	};
        			var modal= $modal({
	        		     title: title, 
	        		     backdrop: 'static',
	        		     show: false,
	        		     controller: source.controller,
	        		     template: TemplatePrefix+source.view,
	        		     scope:scope
	        		 });
        			modal.$promise.then(modal.show);
        		});
    		}else{
    			var busiCode=formImpl;
    			var scope = $rootScope.$new();
    			scope.busiCode=busiCode;
    			scope.busiId=busiId;
    			scope.processDefineKey=processDefineKey;
    			scope.processInstanceId=flowId;
    			scope.onlyView=true;
                scope.popCallBack=function(){
                	
                	/*bootbox.alert("启动成功！");*/
            	};
    			var modal= $modal({
        		     title: title, 
        		     show: false,
        		      controller: "EasyFormWorkflowViewController",
        		     template: TemplatePrefix+"workflow/easyformTemplate/template_workflow_view.html"+'?t='+huatek.version,
        		     scope:scope
        		 });
    			modal.$promise.then(modal.show); 
    		}
        }else if(operation=='printData'){
        	if($scope.gridApi.selection.getSelectedRows().length != 1){
    			submitTips('请勾选一条数据！','warning');
    			return false;
    		}
        	var title=$scope.gridApi.selection.getSelectedRows()[0].name+"";
        	var processDefineKey=$scope.gridApi.selection.getSelectedRows()[0].processDefineKey;
        	var busiId=$scope.gridApi.selection.getSelectedRows()[0].busiId;
        	var formType=$scope.gridApi.selection.getSelectedRows()[0].formType;
        	var formImpl=$scope.gridApi.selection.getSelectedRows()[0].formImpl;
        	var formPrint=$scope.gridApi.selection.getSelectedRows()[0].formPrint;
        	var flowId=$scope.gridApi.selection.getSelectedRows()[0].id;
        	if(!formType){
        		submitTips("该流程没有设置表单！",'warning');
    			return false;
        	}
        	if(formType==='url'){
        		if(!formPrint){
        			submitTips("该流程没有设置打印表单！",'warning');
        			return false;
        		}
    			var url=formPrint;
        		$http.get( "api/menu/querySourceByUrl?url="+url).success(function (source) {
        			var scope = $rootScope.$new();
        			scope.onlyView=true;
        			scope.busiId=busiId;
        			scope.processDefineKey=processDefineKey;
        			scope.processInstanceId=flowId;
	                scope.popCallBack=function(){
	                	
	                	/*bootbox.alert("启动成功！");*/
	            	};
        			var modal= $modal({
	        		     title: title, 
	        		     backdrop: 'static',
	        		     show: false,
	        		     controller: source.controller,
	        		     template: TemplatePrefix+source.view,
	        		     scope:scope
	        		 });
        			modal.$promise.then(modal.show);
        		});
    		}else{
    			var busiCode=formImpl;
        		var businessKey=processDefineKey;
        		var busiId=busiId;
        		var scope = $rootScope.$new();
    			scope.busiCode=busiCode;
                scope.busiId=busiId;
               // scope.taskKey=taskKey;
                scope.processInstanceId=flowId;
                scope.onlyView=true;
    			var modal= $modal({
        		     title: title, 
        		     show: false,
        		     controller: "EasyFormWorkflowPrintController",
        		     template: TemplatePrefix+"workflow/easyformTemplate/template_workflow_print.html"+'?t='+huatek.version,
        		     scope:scope
        		 });
    			modal.$promise.then(modal.show)
    		}
        }else if(operation=='editData'){
        	if($scope.gridApi.selection.getSelectedRows().length != 1){
    			submitTips('请勾选一条数据！','warning');
    			return false;
    		};
        	var title=$scope.gridApi.selection.getSelectedRows()[0].name+"";
        	var busiId=$scope.gridApi.selection.getSelectedRows()[0].busiId;
        	var processDefineKey=$scope.gridApi.selection.getSelectedRows()[0].processDefineKey;
        	var formType=$scope.gridApi.selection.getSelectedRows()[0].formType;
        	var formImpl=$scope.gridApi.selection.getSelectedRows()[0].formImpl;
        	var result=$scope.gridApi.selection.getSelectedRows()[0].result;
        	if(!result){
        		submitTips('流程未结束,不能编辑！','warning');
        		return;
        	}
        	if(!formType){
        		submitTips("该流程没有设置全局表单！",'warning');
    			return false;
        	}
        	if(formType==='url'){
    			var url=formImpl,busiCode=null;
    			scope.busiId=busiId;
        		if(url.indexOf("/easyForm")===0){
        			busiCode=url.split("/")[2];
        		}
        		$http.get( "api/menu/querySourceByUrl?url="+url).success(function (source) {
        			var scope = $rootScope.$new();
        			scope.busiCode=busiCode;
        			sceop.processDefineKey=processDefineKey;
	                scope.popCallBack=function(){
	                	
	                	/*bootbox.alert("启动成功！");*/
	            	};
        			var modal= $modal({
	        		     title: title, 
	        		     backdrop: 'static',
	        		     show: false,
	        		     controller: source.controller,
	        		     template: TemplatePrefix+source.view,
	        		     scope:scope
	        		 });
        			modal.$promise.then(modal.show);
	            	
	            	
	                 
        		});
    		}else{
    			var busiCode=formImpl,scope = $rootScope.$new();
    			scope.busiCode=busiCode;
    			scope.busiId=busiId;
    			scope.processDefineKey=processDefineKey;
                scope.popCallBack=function(){
                	
                	/*bootbox.alert("启动成功！");*/
            	};
    			var modal= $modal({
        		     title: title, 
        		     show: false,
        		      controller: "ApplyEasyFormController",
        		     template: TemplatePrefix+"workflow/apply/template_easyForm.html"+'?t='+huatek.version,
        		     scope:scope
        		 });
    			modal.$promise.then(modal.show); 
    		}
        }else if(operation=='progressData'){
        	listService.showProcessProgress("id");
        }
    };

	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData('api_workflow/myApply/hisList', $scope.tableGrid);
    };
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});

angular.module('HuatekApp').controller('ApplyEasyFormController', function ($scope, $location, $http, $routeParams,$rootScope,$q,cacheService,$timeout) {
	var editService=new EditServiceFunction($http, $q,$rootScope,cacheService,$timeout);
	var loadFormConfigUrl = 'api_workflow/form/loadForm/'+$scope.busiCode;
	var loadFormDataUrl = 'api_workflow/form/loadData/'+$scope.busiCode;
	var loadFormDefaultDataUrl = 'api_workflow/form/loadDefaultData/'+$scope.processDefineKey+"/"+$scope.busiCode;
	var startFlowUrl = 'api_workflow/form/startFlow/'+$scope.processDefineKey+"/"+$scope.busiCode;
	var initParamsUrl='api_workflow/form/initParams';
	$http.get(loadFormConfigUrl).success(function(formConfig){
		var groups=formConfig.formFieldGroupList;
		var fields=formConfig.formFieldConfigList;
		 var columnWrapArray = [];
		angular.forEach(groups,function(group,index){
			columnWrapArray.push(new multipleColumn(group.id,group.name));
		});
		$scope.columnWrapArray = columnWrapArray;
		var dicCategoryArr=[];
		var dicFieldArr=[];
		var formFieldArray = [];
		angular.forEach(fields,function(field,index){
			var formField=new FormElement(field.formFieldGroupId,field.name,field.code,field.type,field.require?1:0,field.length,field.model);
			if(field.defaultValue){
				formField.value=field.defaultValue;
			}
			
			if(field.dropType){
				if(field.dropType==='url'){
					formField.dropDataUrl=field.dropImpl;
				}else if(field.dropType==='dic'&&field.dropImpl){
					
					/*2016年9月2日修改使用缓存*/

/*					dicCategoryArr.push(field.dropImpl);*/

/*					dicFieldArr.push(field.code);*/
					formField.dropDataUrl="dic."+field.dropImpl;
				}else if(field.dropType==='map'&&field.dropImpl){
					try{
						var items=eval(field.dropImpl);
						resolveShowFieldAndReturnField(formField,items);
						formField.items=items;
					}catch(e){
						
					}
				}
			}
			formField.readonly=field.readonly;
			formField.isShow=field.visiable;
			formFieldArray.push(formField);
		});
		 $rootScope.formFieldArray = formFieldArray;
		 editService.init($scope, $location, $http);
		 editService.setFormFields(formFieldArray);
		 if(dicCategoryArr.length>0){
			 $scope.promise = $http.get(initParamsUrl+"/"+dicCategoryArr.join(",")+"/"+dicFieldArr.join(",")).success(function (params){
				 var _fieldMap=editService.getFieldMap();
				 for(var i=0;i<params.length;i++){
		    			var formField = _fieldMap.get(params[i].fieldName);
		    			if(formField){
							resolveShowFieldAndReturnField(formField,params[i].params);
							formField.items = params[i].params;
		          	  	}
				 	}
				 $scope.load();
		    	});
		 }else{
			 $scope.load();
		 }
		 
		 
		 
	});
	$scope.load=function(){
		if($scope.busiId>0){
		   	editService.loadData(loadFormDataUrl, $scope.busiId);
		 }else{
			 editService.loadData(loadFormDefaultDataUrl, -1);
		 }
	};
   /***
    * 定义更新操作.
    */
   $scope.startFlow = function(){
	   if(!editService.isAllowPost()){
			return;
		}
		var data = editService.getPostData();    
   	 	$scope.promise = $http.post(startFlowUrl, data).success(function (response) {
	   	 	if(response.type=='success'){
		 			if($scope.popCallBack){
		 				$scope.popCallBack();
		 			}
		 			$scope.$hide();
			}else {
				console.log(response);
			}
        });
   };
});

angular.module('HuatekApp').controller('EasyFormApplyLinkController', function ($scope, $location, $http, $routeParams,$modal,$rootScope,$q,cacheService,$timeout) {
	var editService=new EditServiceFunction($http, $q,$rootScope,cacheService,$timeout);
	$scope.showLink=function(formField){
		if(formField&&formField.value){
			var items=formField.value.split("|");
			var title=items[0];
        	var processDefineKey=items[1];
        	var busiId=items[2];
        	var formType=items[3];
        	var formImpl=items[4];
        	var flowId=items[5];
        	if(!formType){
        		submitTips("该流程没有设置全局表单！",'warning');
    			return false;
        	}
        	if(formType==='url'){
    			var url=formImpl;
        		$http.get( "api/menu/querySourceByUrl?url="+url).success(function (source) {
        			var scope = $rootScope.$new();
        			scope.onlyView=true;
        			scope.busiId=busiId;
        			scope.processDefineKey=processDefineKey;
        			scope.processInstanceId=flowId;
	                scope.popCallBack=function(){
	                	
	                	/*bootbox.alert("启动成功！");*/
	            	};
        			var modal= $modal({
	        		     title: title, 
	        		     backdrop: 'static',
	        		     show: false,
	        		     controller: source.controller,
	        		     template: TemplatePrefix+source.view,
	        		     scope:scope
	        		 });
        			modal.$promise.then(modal.show);
        		});
    		}else{
    			var busiCode=formImpl;
    			var scope = $rootScope.$new();
    			scope.busiCode=busiCode;
    			scope.busiId=busiId;
    			scope.processDefineKey=processDefineKey;
    			scope.processInstanceId=flowId;
    			scope.onlyView=true;
                scope.popCallBack=function(){
                	
                	/*bootbox.alert("启动成功！");*/
            	};
    			var modal= $modal({
        		     title: title, 
        		     show: false,
        		      controller: "EasyFormWorkflowViewController",
        		     template: TemplatePrefix+"workflow/easyformTemplate/template_workflow_view.html"+'?t='+huatek.version,
        		     scope:scope
        		 });
    			modal.$promise.then(modal.show); 
    		}
		}
	};
	
	$scope.setLink=function(formField){
		var scope = $rootScope.$new();
        scope.popCallBack=function(row){
        	if(row){
        		var title=row.title+"";
            	var processDefineKey=row.processDefineKey;
            	var busiId=row.busiId;
            	var formType=row.formType;
            	var formImpl=row.formImpl;
            	var flowId=row.id;
            	formField.value=title+"|"+processDefineKey+"|"+busiId+"|"+formType+"|"+formImpl+"|"+flowId;
        	}
        	
    	};
		var modal= $modal({
		     show: false,
		     backdrop: 'static',
		      controller: "ApplySelectController",
		     template: TemplatePrefix+"workflow/process/listSelect.html"+'?t='+huatek.version,
		     scope:scope
		 });
		modal.$promise.then(modal.show);
	};

});

angular.module('HuatekApp').controller('ApplySelectController', function ($scope, $location, $http,$rootScope, listService,excelService,$modal) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    multiSelect:false,
		    columnDefs: [
		    	{ name: '流程编号', field: 'id',width: '20%', enableColumnMenu: false},
		    	{ name: '标题', field: 'title',width: '20%', enableColumnMenu: false},
		       { name: '流程名称', field: 'name',width: '20%', enableColumnMenu: false},
		       { name: '提交时间', field: 'startTime',width: '15%', enableColumnMenu: false},
		       { name: '结束时间', field: 'endTime',width: '15%', enableColumnMenu: false},
		       { name: '审批结果', field: 'result',width: '10%', enableColumnMenu: false},
		       { name: '审批意见', field: 'resultMessage',width: '40%', enableColumnMenu: false}
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    queryPage.addParam(new queryParam('流程编号','flowId','string','='));
    queryPage.addParam(new queryParam('标题','title','string','alllike'));
    queryPage.addParam(new queryParam('发起时间','startTime','date','>='));
    queryPage.addParam(new queryParam('——','startTime','date','<='));
    var sameDuty=new queryParam('本岗位所有','sameDuty','boolean','=',false);
    sameDuty.componentType="checkbox";
    queryPage.addParam(sameDuty);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage(queryPage);
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
    	btnArray.push(new pageButton('查看','detail','/myApply/start','detailData'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='detailData'){
        	if($scope.gridApi.selection.getSelectedRows().length != 1){
    			submitTips('请勾选一条数据！','warning');
    			return false;
    		}
        	var title=$scope.gridApi.selection.getSelectedRows()[0].name+"";
        	var processDefineKey=$scope.gridApi.selection.getSelectedRows()[0].processDefineKey;
        	var busiId=$scope.gridApi.selection.getSelectedRows()[0].busiId;
        	var formType=$scope.gridApi.selection.getSelectedRows()[0].formType;
        	var formImpl=$scope.gridApi.selection.getSelectedRows()[0].formImpl;
        	var flowId=$scope.gridApi.selection.getSelectedRows()[0].id;
        	if(!formType){
        		submitTips("该流程没有设置全局表单！",'warning');
    			return false;
        	}
        	if(formType==='url'){
    			var url=formImpl;
        		$http.get( "api/menu/querySourceByUrl?url="+url).success(function (source) {
        			var scope = $rootScope.$new();
        			scope.onlyView=true;
        			scope.busiId=busiId;
        			scope.processDefineKey=processDefineKey;
        			scope.processInstanceId=flowId;
	                scope.popCallBack=function(){
	                	
	                	/*bootbox.alert("启动成功！");*/
	            	};
        			var modal= $modal({
	        		     title: title, 
	        		     backdrop: 'static',
	        		     show: false,
	        		     controller: source.controller,
	        		     template: TemplatePrefix+source.view,
	        		     scope:scope
	        		 });
        			modal.$promise.then(modal.show);
        		});
    		}else{
    			var busiCode=formImpl;
    			var scope = $rootScope.$new();
    			scope.busiCode=busiCode;
    			scope.busiId=busiId;
    			scope.processDefineKey=processDefineKey;
    			scope.processInstanceId=flowId;
    			scope.onlyView=true;
                scope.popCallBack=function(){
                	/*bootbox.alert("启动成功！");*/
            	};
    			var modal= $modal({
        		     title: title, 
        		     show: false,
        		      controller: "EasyFormWorkflowViewController",
        		     template: TemplatePrefix+"workflow/easyformTemplate/template_workflow_view.html"+'?t='+huatek.version,
        		     scope:scope
        		 });
    			modal.$promise.then(modal.show); 
    		}
        }
    };

	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData('api_workflow/myApply/hisSelectList', $scope.tableGrid);
    };
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	$scope.save = function(){
    	var rows=$scope.gridApi.selection.getSelectedRows();
    	if(rows.length===0){
    		submitTips('请至少选择一条数据','warning');
    		return;
    	}
    	if($scope.popCallBack){
    		$scope.popCallBack(rows[0]);
    	}
    	$scope.$hide();
    };
	
});

