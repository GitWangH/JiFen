/**
 * 水泥拌合站检测Controller 
 * BusiQualityCementMixingStationInspectionController
 */
'use strict';
angular.module('huatek.controllers').controller('BusiQualityCementMixingStationInspectionController', function ($rootScope, $scope, $location, $http, listService,httpService) {
	
	$scope.tableGrid = {
	        paginationPageSizes: [10, 25, 50, 100],
	        paginationPageSize: 10,
	        useExternalPagination: true,
	        enableFullRowSelection : true,
	        enableSelectAll : false,
	        columnDefs: [
                { name: '标段名称', field: 'orgName',width: '9%', enableColumnMenu: false},
				{ name: '施工地点', field: 'jobLocation',width: '10%', enableColumnMenu: false},
				{ name: '工程名称', field: 'engineeringName',width: '10%', enableColumnMenu: false},
				{ name: '强度等级', field: 'strengthGrade',width: '8%', enableColumnMenu: false},
				{ name: '配方号', field: 'recipeNumber',width: '10%', enableColumnMenu: false},
				{ name: '超标等级', field: 'excessiveGrade',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'excessiveGrade',dataKey:'dic.excessive_grade'}},
				{ name: '超标原因', field: 'busiQualityCementMixingStationExceed.excessiveReason',width: '10%', enableColumnMenu: false},
				{ name: '检测报告', field: 'busiQualityCementMixingStationExceed.reportAddress',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
				{ name: '描述', field: 'busiQualityCementMixingStationExceed.desContent',width: '10%', enableColumnMenu: false},
				{ name: '上传时间', field: 'createTime',width: '9%', enableColumnMenu: false},
				{ name: '检测状态', field: 'isExcessive_',width: '8%', enableColumnMenu: false,decode:{field:'isExcessive',dataKey:'dic.is_excessive'}},
				{ name: '处理状态', field: 'disposeState',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type_new'},cellTemplate:listService.getLinkCellTmplate("viewData","disposeState")},
	        ]
	    };
		/**
		 * 初始化编辑界面. 比如显示编辑界面的模块名称.
		 */
		listService.init($scope, $http);
		// 定义查询页
		/*var queryPage = new QueryPage(1, 10, "busiQualityCementMixingStationExceed.disposeTime desc", "false");*/
		var queryPage = new QueryPage(1, 10, "id desc", "false");
		// 定义搜索框
		/*queryParam (title, field, type, logic, value, dropDataUrl,isShow, event,display)*/
	    var tendersName = new queryParam('标段名称', 'org.id', 'string', '=');
	    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
	    queryPage.addParam(tendersName);
	    queryPage.addParam(new queryParam('工程名称','engineeringName','string','like'));
	    var isExcessive = new queryParam('检测状态','isExcessive','string','=','1','dic.is_excessive');
	    isExcessive.componentType = 'select';
	    queryPage.addParam(isExcessive);
	    var excessiveGrade = new queryParam('超标等级','busiQualityCementMixingStationExceed.excessiveGrade','string','=',null,'dic.excessive_grade');
	    excessiveGrade.componentType = 'select';
	    queryPage.addParam(excessiveGrade);
	    var disposeState = new queryParam('处理状态','busiQualityCementMixingStationExceed.disposeState','string','=',null,'dic.deal_type_new');
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
		/*btnArray.push(new pageButton('查看', 'view', '/busiQualityCementMixingStation/view', 'viewData'));*/
	    btnArray.push(new pageButton('快捷处理','quickProcess','/busiQualityCementMixingStation/quickProcess','quickProcessData'));
	    btnArray.push(new pageButton('整改下发','rectification','/busiQualityCementMixingStation/rectification','rectificationData'));
	    btnArray.push(new pageButton('查看审批流','process','/busiQualityCementMixingStation/process','processData'));
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
	        	if(param.busiQualityCementMixingStationExceed.disposeState=='5' || param.busiQualityCementMixingStationExceed.disposeState=='4'){
	        		listService.editData($scope,$scope.gridApi, new popup("查看","/busiQualityCementMixingStation/view", 'cement_mixing_station_inspection', "1200", "800"),param);
	        	}else if(param.busiQualityCementMixingStationExceed.disposeState=='1'||param.busiQualityCementMixingStationExceed.disposeState=='2'||param.busiQualityCementMixingStationExceed.disposeState=='6'){
	        		listService.editData($scope,$scope.gridApi, new popup("查看","/busiQualityQuickProcess/detail", 'cement_mixing_station_inspection', "1200", "800"),param);
	        	}else{
	        		submitTips('该数据目前不支持查看','warning');
	        	}
	        }else if(operation == 'quickProcessData'){
	        	if(isAdminFn()){
	        		if(selectData.length==0){
	        			submitTips('请至少勾选一条数据！','warning');
	        			return;	
	        		}
	        		
	        		var scope = $rootScope.$new();
	        		var ids = [];
	        		var _orgId = '';
	        		var boo = false;
	        		for(var i=0;i<selectData.length;i++){
	        			if(selectData[i].isExcessive=='0'){
	        				submitTips('所选数据包含合格数据，请选择超标数据进行操作！','warning');
	        				return;
	        			}
	        			
	        			if(selectData[i].busiQualityCementMixingStationExceed && selectData[i].busiQualityCementMixingStationExceed.disposeState!='0' && selectData[i].busiQualityCementMixingStationExceed.disposeState!='6'){
	        				submitTips('该条数据已提交处理,请选择未处理的数据！','warning');
	        				return;
	        			}
	        			if(selectData[i].busiQualityCementMixingStationExceed){
	        				if(selectData[i].busiQualityCementMixingStationExceed.excessiveGrade=='2' && Number($rootScope.orgType)>6){
	        					boo = true;
	        					break;
	        				}
	        				if(selectData[i].busiQualityCementMixingStationExceed.excessiveGrade=='3' && Number($rootScope.orgType)>4){
	        					boo = true;
	        					break;
	        				}
	        			}else{
	        				submitTips('工程名称为:'+selectData[i].engineeringName+'的数据未同步完成！','warning');
	        				return;
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
	        		if(boo){
	        			submitTips('对不起，存在您不能操作快捷处理在该超标等级下的数据','warning');
	        			return;
	        		}else{
	        			var map = {'id':ids,'orgId':selectData[0].orgId};
	        			listService.addData($scope,new popup("快捷处理","/busiQualityCementMixingStation/quickProcess", map, "900", "600"));
	        		}
	        	}
	        }else if(operation == 'rectificationData'){
	        	if(isAdminFn()){
	        		if(selectData.length==0){
	        			submitTips('请至少勾选一条数据！','warning');
	        			return;
	        		}
	        		if(selectData[0].isExcessive=='0'){
	        			submitTips('该条数据属于合格数据，请选择超标数据进行操作！','warning');
	        			return;
	        		}
	        		if(selectData[0].busiQualityCementMixingStationExceed.disposeState!='0'){
	        			submitTips('该条数据已提交处理,请选择未处理的数据！','warning');
	        			return;
	        		}
	        		var scope = $rootScope.$new();
	        		var ids = [];
	        		for(var i=0;i<selectData.length;i++){
	        			ids.push(selectData[i].id);
	        		}
	        		var map = {'id':ids,'orgId':selectData[0].orgId,'orgName':selectData[0].orgName};
	        		listService.addData($scope,new popup("整改下发","/busiQualityCementMixingStation/rectification", map, "1200", "800"));
	        	}
	        }else if(operation == 'processData'){
	        	var selectedData = listService.getSelectData($scope.gridApi);
	        	/*if(selectData.length==0){
	       			submitTips('请至少勾选一条数据！','warning');
	       			return;
        		}*/
	        	if(selectedData){
	        		if(selectedData.busiQualityCementMixingStationExceed.disposeState=='0' || selectedData.busiQualityCementMixingStationExceed.disposeState=='3'){
	        			submitTips('该条数据还未处理或未整改！','warning');
	        			return;
	        		}
	        		if(selectedData.busiQualityCementMixingStationExceed.disposeState=='2' || selectedData.busiQualityCementMixingStationExceed.disposeState=='1'|| selectedData.busiQualityCementMixingStationExceed.disposeState=='6'){/*处理流程*/
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
	        		}else if(selectedData.busiQualityCementMixingStationExceed.disposeState=='4' || selectedData.busiQualityCementMixingStationExceed.disposeState=='5'){/*整改流程*/
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
	        	window.open(param['busiQualityCementMixingStationExceed']['reportAddress'],"检测报告");
	    	}
	    }
	    /**
		 * 初始化加载数据.
		 */
	    var load = $scope.load = function() {
	    	/*if(listService.getQueryFieldMap($scope).get("isExcessive").value=='0'){
	    		queryPage.orderBy="id desc";
	    	}else{
	    		queryPage.orderBy="busiQualityCementMixingStationExceed.disposeTime desc ";
	    		
	    	}*/
	        listService.loadData($scope, '/api/busiQualityCementMixingStation/query', $scope.tableGrid,null,function(res){
	        	if(res.content && res.content.length > 0){
	        		for(var i = 0 ; i < res.content.length ; i ++){
	        			if(res.content[i].isExcessive == '1' && res.content[i].busiQualityCementMixingStationExceed){
	        				res.content[i].createTime = res.content[i].busiQualityCementMixingStationExceed.createTime;
	        			}/*else{
	        				res.content[i].createTime = res.content[i].createTime;
	        			}*/
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
angular.module('huatek.controllers').controller('BusiQualityCementMixingStationDetailController', function ($scope,$rootScope, $location, $http,listService, editService, cacheService,httpService,$q) {
	var originType = $scope.passParams;
	var typeField = '';
	/* 判断接口来源类型 */
	if(originType=='cement_mixing_station_inspection'){
		typeField = [
			{ name: '施工地点', field: 'jobLocation',width: '10%', enableColumnMenu: false},
			{ name: '工程名称', field: 'engineeringName',width: '10%', enableColumnMenu: false},
			{ name: '强度等级', field: 'strengthGrade',width: '8%', enableColumnMenu: false},
			{ name: '配方号', field: 'recipeNumber',width: '10%', enableColumnMenu: false},
			{ name: '超标等级', field: 'excessiveGrade',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'excessiveGrade',dataKey:'dic.excessive_grade'}},
			{ name: '超标原因', field: 'busiQualityCementMixingStationExceed.excessiveReason',width: '10%', enableColumnMenu: false},
			{ name: '检测报告', field: 'busiQualityCementMixingStationExceed.reportAddress',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
			{ name: '描述', field: 'busiQualityCementMixingStationExceed.desContent',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'busiQualityCementMixingStationExceed.createTime',width: '9%', enableColumnMenu: false},
			{ name: '检测状态', field: 'isExcessive',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'isExcessive',dataKey:'dic.is_excessive'}},
			{ name: '处理状态', field: 'disposeState',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type_new'}},
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
	  if(originType=='cement_mixing_station_inspection'){
		  httpService.get($scope,'/api/busiQualityCementMixingStation/edit/'+$scope.editId).success(function(response){
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

angular.module('huatek.controllers').controller('BusiQualityCementMixingStationRectificationController', function ($scope, $location, $http, editService, cacheService,$rootScope) {
	var addDataUrl = '/api/busiQualityCementMixingStation/rectificationAdd';
	var homeUrl = "/busiQualityCementMixingStation/home";
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'整改通知单'));
    $scope.columnWrapArray = columnWrapArray;
	 
    var formFieldArray = [];
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    /*系统带出*/
    var orgName = new FormElement(1,'被通知单位','orgName','string',1,'30','','','','','','readonly');
    orgName.value = $scope.passParams.orgName;
    formFieldArray.push(orgName);
    var orgId = new FormElement(1,'被通知单位','orgId','string',1,'30');
    orgId.isShow = false;
    orgId.value = $scope.passParams.orgId;
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
    contractFileUUID.systemHeader = "/root/quality/semifinished/cementMixingStation";
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
 * 水泥拌合站检测快捷处理
 */
angular.module('huatek.controllers').controller('BusiQualityCementMixingStationQuickProcessController', function ($rootScope, $scope, $http, listService, editService,httpService) {
	//console.log($scope.passParams)
	var addDataUrl = '/api/busiQualityCementMixingStation/quickProcessAdd';
	//var editDataUrl = 'api/busiQualityRawMaterialInspection/edit';
	var homeUrl = "/busiQualityCementMixingStation/home";
	
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
    contractFileUUID.systemHeader = "/root/quality/semifinished/cementMixingStation";
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
   /* httpService.get($scope,'/api/busiQualityCementMixingStation/queryList'+"/"+$scope.passParams.id).success(function(response){
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


