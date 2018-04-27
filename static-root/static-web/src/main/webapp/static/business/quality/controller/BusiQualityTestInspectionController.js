/**
 * 试验检测Controller 
 * BusiQualityTestInspectionController
 */
'use strict';
angular.module('huatek.controllers').controller('BusiQualityTestInspectionController', function ($rootScope, $scope, $location, $http, listService,httpService) {
	
	   $scope.tableGrid = {
	        paginationPageSizes: [10, 25, 50, 100],
	        paginationPageSize: 10,
	        useExternalPagination: true,
	        enableFullRowSelection : true,
	        enableSelectAll : false,
	        columnDefs: [
				{ name: '检测单位', field: 'orgName',width: '10%', enableColumnMenu: false},
				{ name: '样品编号', field: 'sampleNumber',width: '10%', enableColumnMenu: false},
				{ name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
				{ name: '试验类型', field: 'testType',width: '8%', enableColumnMenu: false,decode:{field:'testType',dataKey:'dic.test_inspection_type'}},
				{ name: '试验日期', field: 'testTime',width: '8%', enableColumnMenu: false},
				{ name: '检测状态', field: 'status1',width: '8%', enableColumnMenu: false,decode:{field:'status',dataKey:'dic.is_qualified'}},
				{ name: '试验人', field: 'testPersion',width: '8%', enableColumnMenu: false},
				{ name: '检测报告', field: 'descUrl',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
				{ name: '描述', field: 'unqualifiedDescription',width: '10%', enableColumnMenu: false},
				{ name: '上传时间', field: 'createTime',width: '9%', enableColumnMenu: false},
				{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'},cellTemplate:listService.getLinkCellTmplate("viewData","disposeState1")},
	        ]
	    };
		/**
		 * 初始化编辑界面. 比如显示编辑界面的模块名称.
		 */
		listService.init($scope, $http);
		// 定义查询页
		var queryPage = new QueryPage(1, 10, "id desc", "false");
		// 定义搜索框
		/*queryParam (title, field, type, logic, value, dropDataUrl,isShow, event,display)*/
		var tendersName = new queryParam('检测单位', 'org.id', 'string', '=');
		tendersName.componentType = SEARCH_COMPONENT.SELECT_TREE_SINGLE;;
		queryPage.addParam(tendersName);
	    queryPage.addParam(new queryParam('样品名称','sampleName','string','like'));
	    var status = new queryParam('检测状态','status','string','=','1','dic.is_qualified');
	    status.componentType = 'select';
	    queryPage.addParam(status);
	    var testType = new queryParam('试验类型','testType','string','=','','dic.test_inspection_type');
	    testType.componentType = 'select';
	    queryPage.addParam(testType);
	    queryPage.addParam(new queryParam('试验日期','testTime','date','>='));
	    queryPage.addParam(new queryParam('---','testTime','date','<'));
	    var disposeState = new queryParam('处理状态','disposeState','string','=',null,'dic.deal_type_new');
	    disposeState.componentType = 'select';
	    queryPage.addParam(disposeState);
	    $rootScope.searchCount = queryPage.queryParamList.length;
	    /**
		 * 设置查询输入按钮.
		 */
	    listService.setQueryPage($scope, queryPage);
	    
	    /**
		 * 注册gridApi的选择器.
		 */
	    $scope.tableGrid.onRegisterApi = function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
	            queryPage.page = newPage;
	            queryPage.pageSize = pageSize;
	            load();
	        });
	        /*调整grid底部高度*/
	        listService.setGridHeight();
	    };

	    // 定义功能按钮
		var btnArray = [];
		/*btnArray.push(new pageButton('查看', 'view', '/busiQualityQuickProcess/detail', 'viewData'));*/
	    btnArray.push(new pageButton('快捷处理','quickProcess','/busiQualityTestInspection/quickProcess','quickProcessData'));
	    btnArray.push(new pageButton('整改下发','rectification','/busiQualityTestInspection/rectification','rectificationData'));
	    btnArray.push(new pageButton('查看审批流','process','/busiQualityTestInspection/process','processData'));
		/**
		 * 设置界面的功能按钮.
		 */
	    listService.setButtonList($scope, btnArray);
	    
	    /*判断是否是系统管理员，系统管理员不可操作业务数据*/
	    var isAdminFn = function(){
	    	if($rootScope.tenantId){
	    		return true;
	    	}else{
	    		submitTips('系统管理员不能操作业务数据！','warning');
	    		return false;
	    	}
	    }
	    
	    /**
		 * 桥接按钮事件.
		 */
	    $scope.execute = function(operation, param){
	    	var selectData = $scope.gridApi.selection.getSelectedRows();
	        if(operation == 'viewData'){
	        	if(param.disposeState=='5' || param.disposeState=='4'){
	        		listService.editData($scope,$scope.gridApi, new popup("查看","/busiQualityTestInspection/view", 'test_inspection', "1200", "800"),param);
	        	}else if(param.disposeState=='1'||param.disposeState=='2'||param.disposeState=='6'){
	        		listService.editData($scope,$scope.gridApi, new popup("查看","/busiQualityQuickProcess/detail", 'test_inspection', "1200", "800"),param);
	        	}else{
	        		submitTips('该数据目前不支持查看','warning');
	        	}
	        }else if(operation == 'quickProcessData'){
	        	if(isAdminFn()){
	        		if(selectData.length==0){
	        			submitTips('请至少勾选一条数据！','warning');
	        			return;
	        		}
	        		var ids = [];
	        		var _orgId = '';
	        		for(var i=0;i<selectData.length;i++){

	        			if (selectData[i].disposeState!='0' && selectData[i].disposeState!='6' || selectData[i].status!='1') {
	 					submitTips('该条数据已提交整改或为合格数据，不能下发整改！', 'warning');
	 					ids = [];
	 					break;
	 				}
	        			   ids.push(selectData[i].id);
	        		   
	        			if(!_orgId){
	        				_orgId = selectData[i].orgId;
	        				continue;
	        			}
	        			if(_orgId != selectData[i].orgId){
	        				submitTips('多条数据时请选择相同标段进行处理','warning');
	        				return;
	        			}
	        		}
	        		if(ids.length>0){
	        			var map = {'id':ids,'orgId':selectData[0].orgId};
	        			listService.addData($scope,new popup("快捷处理","/busiQualityTestInspection/quickProcess", map, "900", "600"));
	        		}
	        	}
	        
	        }else if(operation == 'rectificationData'){
	        	if(isAdminFn()){
	        		if(selectData.length==0){
	        			submitTips('请至少勾选一条数据！','warning');
	        			return;
	        		}
	        		
	        		var ids = [];
	        		for(var i=0;i<selectData.length;i++){
	        			if (selectData[i].disposeState!='0' || selectData[i].status!='1') {
	        				submitTips('该条数据已提交整改或为合格数据，不能下发整改！', 'warning');
	        				ids = [];
	        				break;
	        			}
	        			
	        			ids.push(selectData[i].id);
	        		}
	        		if(ids.length>0){
	        			var map = {'id':ids,'factOrgId':selectData[0].factOrgId,'factOrgName':selectData[0].factOrgName};
	        			listService.addData($scope,new popup("整改下发","/busiQualityTestInspection/rectification", map, "1200", "800"));
	        		}
	        	}
	        }else if(operation == 'processData'){
	        	var selectedData = listService.getSelectData($scope.gridApi);
	        	/*if(selectData.length==0){
	       			submitTips('请至少勾选一条数据！','warning');
	       			return;
        		}*/
	        	if(selectedData){
	        		if(selectedData.disposeState=='0' || selectedData.disposeState=='3'){
	        			submitTips('该条数据还未处理或未整改！','warning');
	        			return;
	        		}
	        		if(selectedData.disposeState=='2' || selectedData.disposeState=='1'|| selectedData.disposeState=='6'){/*处理流程*/
	        			httpService.get($scope,'/api/busiQualityQuickProcess/queryRectification/'+selectedData.inspectionCode).success(function(quickProData){
	        				if(quickProData.flowInstanceId){
	        					var pop= {
	        							title: "流程进度",
	        							passParams:{
	        								canSetPerson:true,
	        								processInstanceId: quickProData.flowInstanceId
	        							},
	        							controller:  'processProgressController',
	        							templateView: "static/business/workflow/process/templates/processProgress.html",
	        							
	        					};
	        					listService.popPanel($scope, pop)
	        				}else{
	        					submitTips('项目办或监理已进行快捷处理','warning');
	        				}
	        			});
	        		}else if(selectedData.disposeState=='4' || selectedData.disposeState=='5'){/*整改流程*/
	        			httpService.get($scope,'/api/busiQualityRectification/queryRectification/'+selectedData.inspectionCode).success(function(rectData){
	        				var pop= {
	        						title: "流程进度",
	        						passParams:{
	        							canSetPerson:true,
	        							processInstanceId: rectData.flowInstanceId
	        						},
	        						controller:  'processProgressController',
	        						templateView: "static/business/workflow/process/templates/processProgress.html",
	        						
	        				};
	        				listService.popPanel($scope, pop)
	        			});
	        		}
	        	}
	        }else if(operation == 'viewReport'){
	        	window.open(param['descUrl'],"检测报告");
	    	}
	    }
	    /**
		 * 初始化加载数据.
		 */
	    var load = $scope.load = function() {
	        listService.loadData($scope, '/api/busiQualityTestInspection/query', $scope.tableGrid,null,function(res){
	        	if(res.content && res.content.length > 0){
	        		for(var i = 0 ; i < res.content.length ; i ++){
	        			if(res.content[i].status == '0'){
	        				res.content[i].disposeState = "";
	        			}
	        		}
	        	}
	        });
	    }

	    load();

	    $scope.search = function() {
	        load();
	    };
	    
	   /* $scope.viewInfo = function(entity){
	    	listService.editData($scope, $scope.gridApi, new popup("查看","/rawMaterialInspection/view", null, "1200", "800"));
	    }*/
});

/**
 * 查询整改明细
 */
angular.module('huatek.controllers').controller('BusiQualityTestInspectionDetailController', function ($scope,$rootScope, $location, $http,listService, editService, cacheService,httpService,$q) {
	var originType = $scope.passParams;
	var typeField = '';
	/* 判断接口来源类型 */
	if(originType=='test_inspection'){
		typeField = [
			{ name: '检测单位', field: 'orgName',width: '10%', enableColumnMenu: false},
			{ name: '样品编号', field: 'sampleNumber',width: '10%', enableColumnMenu: false},
			{ name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
			{ name: '试验类型', field: 'testType',width: '10%', enableColumnMenu: false,decode:{field:'testType',dataKey:'dic.test_inspection_type'} },
			{ name: '试验日期', field: 'testTime',width: '10%', enableColumnMenu: false},
			{ name: '检测状态', field: 'status1',width: '10%', enableColumnMenu: false,decode:{field:'status',dataKey:'dic.is_qualified'}},
			{ name: '试验人', field: 'testPersion',width: '10%', enableColumnMenu: false},
			{ name: '检测报告', field: 'descUrl',width: '10%', enableColumnMenu: false},
			{ name: '描述', field: 'unqualifiedDescription',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'createTime',width: '10%', enableColumnMenu: false},
			{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
		];
	}
	/*else if(){
		
	}*/
	
	/**
	 * 整改信息
	 */
	$scope.tableGrid = {
			useExternalPagination: false,
			columnDefs: typeField
	};
	 
	/**
	 * 初始化编辑界面. 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
	
    
    /**
	 * 注册gridApi的选择器.
	 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
    };
	
	 	//定义块
	  var columnWrapArray = [];
	  columnWrapArray.push(new multipleColumn(1,'整改指令'));
	  $scope.columnWrapArray = columnWrapArray;
	  
		//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
	  var formFieldArray = [];
	  formFieldArray.push(new FormElement(1,'被通知单位','orgName','string',1,'20'));
	  formFieldArray.push(new FormElement(1,'检查编号','checkNo','string',1,'50'));
	  formFieldArray.push(new FormElement(1,'整改期限','rectificationPeriod','number',1,'30'));
	  formFieldArray.push(new FormElement(1,'检查人员','rectificationPerson','string',1,'30'));
	  formFieldArray.push(new FormElement(1,'检查时间','rectificationCheckTime','string',1,'30'));
	  formFieldArray.push(new FormElement(1,'相关责任人','rectificationPersonLiable','string',1,'30'));
	  formFieldArray.push(new FormElement(1,'紧急程度','rectificationUrgency','string',1,'30','select','','','','dic.emergency_degree'));
	  formFieldArray.push(new FormElement(1,'检查内容','rectificationCheckContent','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'存在问题','rectificationExistingProblems','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'整改要求','rectificationRequirements','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'处罚建议','rectificationPunishmentSuggestion','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'附件','fileId','string',0,'30','fileMultiple'));
			 
	  //设置全局变量
	  $rootScope.formFieldArray = formFieldArray;
	  for(var i=0;i<formFieldArray.length;i++){
		   formFieldArray[i].readonly=true;
	  }
	
	  /**
	   * 初始化编辑界面.
	   * 比如显示编辑界面的模块名称.
	   * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	   */
	  editService.init($scope, $http);
	  editService.setFormFields($scope, formFieldArray); 
	  
	  /* 判断接口来源类型 */
	  var rectificationCode = '';
	  $scope.rectificationDetailList = [];
	  if(originType=='test_inspection'){
		  httpService.get($scope,'/api/busiQualityTestInspection/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  if(undefined != response){
				  $scope.tableGrid.data = [response];
			  }
			  rectificationCode = response.inspectionCode;
			  myLoadData('/api/busiQualityRectification/queryRectification',rectificationCode);
			  
		  })
	  }
	  /*else if(){}*/
      
      
	  /* 获取整改单明细信息 */
	  var queryRectificationDetail = function(id){
		  httpService.get($scope,'/api/busiRectificationDetail/findDetailRecordByRectId/'+id).success(function(data){
			  var type = cacheService.getData('dic.rectification_approve_type');
			  /*var index = 1;*/
			  for(var i in data){
				  for(var j in type){
					  if(data[i].detailFlowResult == type[j].code){
						  data[i].detailFlowResult = type[j].name;
						  break;
					  }
				  }
				  /*if(data[i].detailType=='0'){ 反馈 
					  if(!(data[i].index)){
						  data[i].index = index;
						  continue;
					  }
				  }
				  data[i].index = index;
				  index++;*/
			  }
			  $scope.rectificationDetailList = data;
		  });
	  }
		  
	  var _scope = $scope;
	  var myLoadData = function(url,code){
		  httpService.get($scope,url + "/" +code ).success(function(response) {
            for (var p in response) {
                var formField = _scope.fieldMap.get(p);
                /*如果是显示值,则把值取出来*/
                if ((!formField || formField.isShow == false) && p.charAt(p.length - 1) == '_') {
                    var fieldName = p.substring(0, p.length - 1);
                    formField = _scope.fieldMap.get(fieldName);
                    formField.displayValue = response[p];
                    continue;
                }
                if (formField) {
                    var endField = _scope.fieldMap.get(formField.endName);
                    /*如果是日期区间，需要把值拆分到时间区间的两个绑定值上*/
                    if ((formField.model == 'date-section' || formField.model == 'dateSection' || formField.model == 'time-section' || formField.model == 'timeSection') && response[p] && endField) {
                        formField.minValue = response[p];
                        formField.maxValue = response[endField.name];
                    } else if ((formField.model == 'dateTime-section' || formField.model == 'dateTimeSection') && response[p] && endField) {
                        formField.minValue = getDate(response[p]);
                        formField.maxValue = getDate(response[endField.name]);
                        formField.minTimeValue = getTime(response[p]);
                        formField.maxTimeValue = getTime(response[endField.name]);
                    } else if ((formField.model == 'dateTime' || formField.model == 'date-time') && response[p]) {
                        formField.minValue = getDate(response[p]);
                        formField.maxValue = getTime(response[p]);
                    } else if (formField.model == 'checkbox' || formField.type == 'boolean') {
                        if (typeof response[p] == 'string') {
                            if (response[p] == 'true') {
                                formField.value = true;
                            } else {
                                formField.value = false;
                            }
                        } else {
                            formField.value = response[p];
                        }
                    } else if (formField.model == 'selectMultiple') {
                        if (response[p] != null && response[p] != "") {
                            formField.params = response[p].split(",");
                        }
                    } else if (formField.model == 'checkboxAndOthers') {
                        formField.value = response[p];
                        if (cnex4_isEmpty_str(response[p])) {
                            formField.minValue = true;
                        } else {
                            formField.minValue = false;
                        }
                    } else if (formField.model == 'autocomplete') {
                        formField._choosed = true;
                        formField.value = response[p];
                    } else {
                        formField.value = response[p] + '';
                    }

                }
            }
            queryRectificationDetail(response.id);
	    });
	  }
});

angular.module('huatek.controllers').controller('BusiQualityTestInspectionRectificationController', function ($scope, $location, $http, editService, cacheService,$rootScope) {
	var addDataUrl = '/api/busiQualityTestInspection/rectificationAdd';
	var homeUrl = "/busiQualityTestInspection/home";
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'整改通知单'));
    $scope.columnWrapArray = columnWrapArray;
	 
    var formFieldArray = [];
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    /*系统带出*/
    var orgName = new FormElement(1,'被通知单位','orgName','string',1,'30','','','','','','readonly');
    orgName.value = $scope.passParams.factOrgName;
    formFieldArray.push(orgName);
    var orgId = new FormElement(1,'被通知单位','orgId','string',1,'30');
    orgId.isShow = false;
    orgId.value = $scope.passParams.factOrgId;
    formFieldArray.push(orgId);
    formFieldArray.push(new FormElement(1,'检查编号','checkNo','string',1,'30'));
    formFieldArray.push(new FormElement(1,'检查时间','rectificationCheckTime','string',1,'30','date','','',getDate(getNowFormatDate())));
    formFieldArray.push(new FormElement(1,'相关责任人','rectificationPersonLiable','string',1,'30'));
    formFieldArray.push(new FormElement(1,'紧急程度','rectificationUrgency','string',1,'30','select','','','','dic.emergency_degree'));
    formFieldArray.push(new FormElement(1,'整改期限(天)','rectificationPeriod','number',1,'30'));
    formFieldArray.push(new FormElement(1,'检查人员','rectificationPerson','string',1,'30',null,'','',$rootScope.userName));
    formFieldArray.push(new FormElement(1,'检查内容','rectificationCheckContent','string',0,'200','textarea'));
    formFieldArray.push(new FormElement(1,'存在问题','rectificationExistingProblems','string',0,'200','textarea'));
    formFieldArray.push(new FormElement(1,'整改要求','rectificationRequirements','string',0,'200','textarea'));
    formFieldArray.push(new FormElement(1,'处罚建议','rectificationPunishmentSuggestion','string',0,'200','textarea'));
    var contractFileUUID = new FormElement(1,'附件','fileId','string',0,'200','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/rawMaterial/testInspection";
 	formFieldArray.push(contractFileUUID);
    
    $scope.formFieldArrayList = formFieldArray;
    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $http);
  
	/***
	 * 设置编辑界面输入的字段.
	 * 被设置必须在服务初始化之后执行.
	 */
	editService.setFormFields($scope,formFieldArray); 
    
    /**
     * 保存
     **/    
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl+"/"+$scope.passParams.id, homeUrl);
    }
    
});

/**
 * 试验检测快捷处理
 */
angular.module('huatek.controllers').controller('BusiQualityTestInspectionQuickProcessController', function ($rootScope, $scope, $http, listService, editService,httpService) {
	console.log($scope.passParams)
	var addDataUrl = '/api/busiQualityTestInspection/quickProcessAdd';
	//var editDataUrl = 'api/busiQualityRawMaterialInspection/edit';
	var homeUrl = "/busiQualityTestInspection/home";
	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'快捷处理'));
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
   
    formFieldArray.push(new FormElement(1,'说明','quickExplain','string',1,'500','textarea'));
    formFieldArray.push(new FormElement(1,'处理人员','quickUserName','string',1,'30',null,null,null,$scope.userName));
    formFieldArray.push(new FormElement(1,'处理时间','quickProcessingTime','string',1,'30','date',null,null,getDate(getNowFormatDate())));
    formFieldArray.push(new FormElement(1,'原因','quickReason','string',1,'100','select',null,null,null,'dic.quick_process_reason'));
    var contractFileUUID = new FormElement(1,'附件','enclosure','string',0,'100','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/rawMaterial/testInspection";
 	formFieldArray.push(contractFileUUID);
   
    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope);
  
   /***
    * 设置编辑界面输入的字段.
    * 被设置必须在服务初始化之后执行.
    */
    editService.setFormFields($scope, formFieldArray); 
    
    /**
	 * 初始化加载数据.
	 */
   /* httpService.get($scope,'/api/busiQualityTestInspection/queryList'+"/"+$scope.passParams.id).success(function(response){
    	$scope.tableGrid.data = response;
    });*/
         
    
    /*load();*/
    

    /**
     * 保存
     **/    
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl+"/"+$scope.passParams.id+'/'+$scope.passParams.orgId, homeUrl);
    }
    
});
