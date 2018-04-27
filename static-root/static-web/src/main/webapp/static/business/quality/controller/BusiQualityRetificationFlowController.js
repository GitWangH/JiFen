/**
 * 整改单审批流程页
 * @author rocky_wei
 */
angular.module('huatek.controllers').controller('BusiQualityRetificationFlowController', function ($rootScope, $scope, $http, listService, editService,httpService, cacheService) {
	$scope.busiCode=$scope.passParams.busiCode;
	$scope.taskId=$scope.passParams.taskId;
	$scope.busiId=$scope.passParams.busiId;
	$scope.taskKey=$scope.passParams.taskKey;
	$scope.taskName=$scope.passParams.taskName;
	$scope.processInstanceId=$scope.passParams.processInstanceId;
	$scope.onlyView=$scope.passParams.onlyView;
	$scope.editId=$scope.busiId;

	var _prefix = '/api/busiRectificationDetail/';
	var editDataUrl = _prefix+'edit';
	var submitDataUrl = _prefix+"add";

	var tableGridContent = [];
	$scope.tableGrid = {};
    /*定义块*/
    var columnWrapArray = [];
    /*var rectDetailType = ''; 整改明细类型 */
    columnWrapArray.push(new multipleColumn(1,'整改单信息'));
    if(!$scope.onlyView){
    	/*if($rootScope.orgType=='4'){项目办下发
    	}*/
    	/*else{监理下发
    		if($scope.taskKey=='task4'){
    			columnWrapArray.push(new multipleColumn(2,'标段反馈'));
    		}else if($scope.taskKey=='task5'){
    			columnWrapArray.push(new multipleColumn(2,$scope.taskName));
    		}
    	}*/
    	columnWrapArray.push(new multipleColumn(2,$scope.taskName));
 	}
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'被通知单位','orgName','string',1,'500'));
    formFieldArray.push(new FormElement(1,'检查编号','checkNo','string',1,'30'));
    formFieldArray.push(new FormElement(1,'整改期限','rectificationPeriod','number',1,'30'));
    formFieldArray.push(new FormElement(1,'检查人员','rectificationPerson','string',1,'30'));
    formFieldArray.push(new FormElement(1,'相关责任人','rectificationPersonLiable','string',1,'30'));
    formFieldArray.push(new FormElement(1,'紧急程度','rectificationUrgency','string',0,'30','select','','','','dic.emergency_degree'));
    formFieldArray.push(new FormElement(1,'检查内容','rectificationCheckContent','string',0,'500','textarea'));
    formFieldArray.push(new FormElement(1,'存在问题','rectificationExistingProblems','string',0,'500','textarea'));
    formFieldArray.push(new FormElement(1,'整改要求','rectificationRequirements','string',0,'500','textarea'));
    formFieldArray.push(new FormElement(1,'处罚建议','rectificationPunishmentSuggestion','string',0,'500','textarea'));
    formFieldArray.push(new FormElement(1,'附件','fileId','string',0,'500','fileMultiple'));
    var rectificationId = new FormElement(1,'整改单id','id','string',0,'50');
    rectificationId.value = '';
    rectificationId.isShow = false;
    formFieldArray.push(rectificationId);
    var orgId = new FormElement(1,'机构id','orgId','string',0,'50');
    orgId.value = '';
    orgId.isShow = false;
    formFieldArray.push(orgId);
    var dataSource = new FormElement(1,'整改类型','dataSource','string',0,'50');/*判断列表查对应的表*/
    dataSource.value = '';
    dataSource.isShow = false;
    formFieldArray.push(dataSource);
    var rectificationCode = new FormElement(1,'整改编号','rectificationCode','string',0,'50');
    rectificationCode.value = '';
    rectificationCode.isShow = false;
    formFieldArray.push(rectificationCode);
    /*var detailType = new FormElement(1,'整改明细类型','detailType','string',0,'50');
    detailType.value = rectDetailType;
    detailType.isShow = false;
    formFieldArray.push(detailType);*/
    
    var flowStep = new FormElement(1,'流程步骤','flowStep','string',0,'50');
    flowStep.value = $scope.taskKey;
    flowStep.isShow = false;
    formFieldArray.push(flowStep);
    var flowStepName = new FormElement(1,'流程步骤名称','flowStepName','string',0,'50');
    flowStepName.value = $scope.taskName;
    flowStepName.isShow = false;
    formFieldArray.push(flowStepName);
    
    var detailFlowResult=new FormElement(2,'处理类型','detailFlowResult','boolean',1,'128',"radio","resultChange");
    detailFlowResult.items=[{code:"true",name:'通过'},{code:"false",name:"不通过"}];
	formFieldArray.push(detailFlowResult);
    
	var rectificationDescription=new FormElement(2,'处理描述','rectificationDescription','string',1,'1000',"textarea");
	formFieldArray.push(rectificationDescription);
	/*formFieldArray.push(new FormElement(2,'附件','detailFileId','string',0,'500','fileMultiple'));*/
	var contractFileUUID = new FormElement(2,'附件','detailFileId','string',0,'500','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/rectificationFlow";
 	formFieldArray.push(contractFileUUID);
	
	for(var i=0;i<formFieldArray.length;i++){
		if(formFieldArray[i].column!=2){
			formFieldArray[i].readonly = true;
		}
	}
    
/*	$scope.resultChange = function(){
		console.log(detailFlowResult.value);
	}*/
	
	/*标段整改默认通过*/
	if($scope.taskKey=='task1' || $scope.taskKey=='task4' || $scope.taskName=='标段整改'){
		detailFlowResult.value = "true";
		detailFlowResult.isShow = false;
	}
	
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
    
    var loadSuccessCallBack = function(){
    	/* 判断快捷处理类型，根据类型去查对应的表 */
    	switch(dataSource.value){ 
	    	case 'raw_material_inspection': /*原材料*/
	    		/* 填充表格列 */
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
					{ name: '样品编号', field: 'sampleCode',width: '10%', enableColumnMenu: false},
					{ name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
					{ name: '检测编号', field: 'checkCode',width: '10%', enableColumnMenu: false},
					{ name: '检测时间', field: 'checkDate',width: '10%', enableColumnMenu: false},
					{ name: '检测量', field: 'checkQuantity',width: '8%', enableColumnMenu: false},
					{ name: '机器编号', field: 'machineNo',width: '10%', enableColumnMenu: false},
					{ name: '检测状态', field: 'reportConclusion',width: '8%', enableColumnMenu: false,decode:{field:'reportConclusion',dataKey:'dic.inspection_status'}},
					{ name: '检测报告', field: 'reportAddress',width: '7%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.reportAddress)">查看报告</a>'},
					{ name: '上传时间', field: 'disposeTime',width: '8%', enableColumnMenu: false},
					{ name: '处理状态', field: 'disposeState_',width: '8%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ];
	    		
	    		httpService.get($scope,'/api/busiQualityRawMaterialInspection/getRawMaterialByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'water_stable_mixing_station': /*水稳拌合站*/
	    		$scope.tableGrid.columnDefs = tableGridContent =  [
					{ name: '配方名称', field: 'recipeName',width: '11%', enableColumnMenu: false},
					{ name: '配方号', field: 'recipeCode',width: '11%', enableColumnMenu: false},
					{ name: '采集时间', field: 'gatherDate',width: '11%', enableColumnMenu: false},
					{ name: '超标等级', field: 'overproofGrade',width: '11%', enableColumnMenu: false,decode:{parent:'busiQualityWaterStableMixingStationExceed',field:'overproofGrade',dataKey:'dic.excessive_grade'}},
					{ name: '超标原因', field: 'busiQualityWaterStableMixingStationExceed.overproofReason',width: '11%', enableColumnMenu: false},
					{ name: '检测地址', field: 'busiQualityWaterStableMixingStationExceed.reportAddress',width: '11%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.busiQualityWaterStableMixingStationExceed.reportAddress)">查看报告</a>'},
					{ name: '上传时间', field: 'busiQualityWaterStableMixingStationExceed.disposeDate',width: '11%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isExcessive',width: '11%', enableColumnMenu: false,decode:{field:'isExcessive',dataKey:'dic.is_excessive'}},
					{ name: '处理状态', field: 'disposeState',width: '11%', enableColumnMenu: false,decode:{parent:'busiQualityWaterStableMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ]
	    		httpService.get($scope,'/api/busiQualityWaterStableMixingStation/getWaterStableMixingStationByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'spreader_roller':/*铺摊碾压*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
					{ name: '作业层面', field: 'operationSurface',width: '10%', enableColumnMenu: false},
					{ name: '采集时间', field: 'gatherDate',width: '10%', enableColumnMenu: false},
					{ name: '作业类型', field: 'type',width: '10%', enableColumnMenu: false,decode:{field:'type',dataKey:'dic.job_type'}},
					{ name: '超标等级', field: 'overproofGrade',width: '10%', enableColumnMenu: false,decode:{field:'overproofGrade',dataKey:'dic.excessive_grade'}},
					{ name: '超标原因', field: 'overproofReason',width: '10%', enableColumnMenu: false},
					{ name: '检测地址', field: 'reportAddress',width: '9%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.reportAddress)">查看报告</a>'},
					{ name: '上传时间', field: 'modifyTime',width: '10%', enableColumnMenu: false},
					{ name: '检测状态', field: 'dataType',width: '10%', enableColumnMenu: false,decode:{field:'dataType',dataKey:'dic.is_qualified'}},
					{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ]
	    		httpService.get($scope,'/api/busiQualitySpreaderRollerSpreader/getSpreaderRollerSpreaderByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'cement_mixing_station_inspection': /*水泥拌合站*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '施工地点', field: 'jobLocation',width: '10%', enableColumnMenu: false},
					{ name: '工程名称', field: 'engineeringName',width: '10%', enableColumnMenu: false},
					{ name: '强度等级', field: 'strengthGrade',width: '8%', enableColumnMenu: false},
					{ name: '配方号', field: 'recipeNumber',width: '10%', enableColumnMenu: false},
					{ name: '超标等级', field: 'excessiveGrade',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'excessiveGrade',dataKey:'dic.excessive_grade'}},
					{ name: '超标原因', field: 'busiQualityCementMixingStationExceed.excessiveReason',width: '10%', enableColumnMenu: false},
					{ name: '检测地址', field: 'busiQualityCementMixingStationExceed.reportAddress',width: '8%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.busiQualityCementMixingStationExceed.reportAddress)">查看报告</a>'},
					{ name: '描述', field: 'busiQualityCementMixingStationExceed.desContent',width: '10%', enableColumnMenu: false},
					{ name: '上传时间', field: 'busiQualityCementMixingStationExceed.disposeTime',width: '9%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isExcessive',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'isExcessive',dataKey:'dic.is_excessive'}},
					{ name: '处理状态', field: 'disposeState',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ]
	    		httpService.get($scope,'/api/busiQualityCementMixingStation/getCementMixingStationByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'asphalt_mixing_station': /*沥青拌合站*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '施工地点', field: 'constructionPosition',width: '12%', enableColumnMenu: false},
					{ name: '工程名称', field: 'engineeringName',width: '12%', enableColumnMenu: false},
					{ name: '配方号', field: 'formula',width: '12%', enableColumnMenu: false},
					{ name: '超标等级', field: 'overproofGrade',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityAsphaltMixingPlantExceed',field:'overproofGrade',dataKey:'dic.excessive_grade'}},
					{ name: '超标原因', field: 'busiQualityAsphaltMixingPlantExceed.overproofReason',width: '11%', enableColumnMenu: false},
					{ name: '检测地址', field: 'busiQualityAsphaltMixingPlantExceed.reportAddress',width: '11%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.busiQualityAsphaltMixingPlantExceed.reportAddress)">查看报告</a>'},
					{ name: '上传时间', field: 'busiQualityAsphaltMixingPlantExceed.disposeDate',width: '11%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isExcessive',width: '11%', enableColumnMenu: false,decode:{field:'isExcessive',dataKey:'dic.is_excessive'}},
					{ name: '处理状态', field: 'disposeState',width: '11%', enableColumnMenu: false,decode:{parent:'busiQualityAsphaltMixingPlantExceed',field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ]
	    		httpService.get($scope,'/api/busiQualityAsphaltMixingPlant/getAsphaltMixingPlantByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			 myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'test_inspection': /*试验检测*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '检测单位', field: 'orgName',width: '10%', enableColumnMenu: false},
					{ name: '样品编号', field: 'sampleNumber',width: '10%', enableColumnMenu: false},
					{ name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
					{ name: '试验类型', field: 'testType',width: '8%', enableColumnMenu: false,decode:{field:'testType',dataKey:'dic.test_inspection_type'}},
					{ name: '试验日期', field: 'testTime',width: '8%', enableColumnMenu: false},
					{ name: '检测状态', field: 'status1',width: '8%', enableColumnMenu: false,decode:{field:'status',dataKey:'dic.is_qualified'}},
					{ name: '试验人', field: 'testPersion',width: '8%', enableColumnMenu: false},
					{ name: '检测报告', field: 'descUrl',width: '8%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.descUrl)">查看报告</a>'},
					{ name: '描述', field: 'unqualifiedDescription',width: '10%', enableColumnMenu: false},
					{ name: '上传时间', field: 'modifyTime',width: '9%', enableColumnMenu: false},
					{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ]
	    		httpService.get($scope,'/api/busiQualityTestInspection/getUniversalPressMachineByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'mortar_inspection': /*砂浆检测*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
					{ name: '样品编号', field: 'sampleCode',width: '10%', enableColumnMenu: false},
					{ name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
					{ name: '试验类型', field: 'testType',width: '9%', enableColumnMenu: false},
					{ name: '试验日期', field: 'testDate',width: '10%', enableColumnMenu: false},
					{ name: '试验人', field: 'testUser',width: '10%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isQualified',width: '10%', enableColumnMenu: false,decode:{field:'isQualified',dataKey:'dic.is_qualified'}},
					{ name: '检测报告', field: 'descriptionUrl',width: '10%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.descriptionUrl)">查看报告</a>'},
					{ name: '上传时间', field: 'disposeTime',width: '10%', enableColumnMenu: false},
					{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ]
	    		httpService.get($scope,'/api/busiQualityMortarInspection/getMortarByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'prestressed_tension_main_inspection': /*预应力张拉、压浆检测*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '标段名称', field: 'orgName',width: '9%', enableColumnMenu: false},
					{ name: '工程名称', field: 'engineeringName',width: '9%', enableColumnMenu: false},
					{ name: '桥梁名称', field: 'bridgeName',width: '9%', enableColumnMenu: false},
					{ name: '分级张拉', field: 'gradedTension',width: '8%', enableColumnMenu: false,decode:{field:'gradedTension',dataKey:'dic.graded_tension'}},
					{ name: '记录时间', field: 'recordTime',width: '8%', enableColumnMenu: false},
					{ name: '张拉力状态', field: 'tensionState',width: '8%', enableColumnMenu: false,decode:{field:'tensionState',dataKey:'dic.tension_state'}},
					{ name: '伸长量状态', field: 'elongationState',width: '8%', enableColumnMenu: false,decode:{field:'elongationState',dataKey:'dic.tension_state'}},
					{ name: '应力状态', field: 'stressState',width: '8%', enableColumnMenu: false,decode:{field:'stressState',dataKey:'dic.tension_state'}},
					{ name: '检测地址', field: 'reportAddress',width: '8%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewDescription(row.entity.reportAddress)">查看报告</a>'},
					{ name: '上传时间', field: 'disposeTime',width: '8%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isQualified',width: '8%', enableColumnMenu: false,decode:{field:'isQualified',dataKey:'dic.is_qualified'}},
					{ name: '处理状态', field: 'disposeStatus1',width: '8%', enableColumnMenu: false,decode:{field:'disposeStatus',dataKey:'dic.deal_type_new'}},
		        ]
	    		httpService.get($scope,'/api/busiQualityPrestressedTensionMain/getPrestressedTensionMainByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'construction_inspection': /*施工报检*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
		            { name: '标段名称', field: 'orgName', width: '15%', enableColumnMenu: false },
		            /*{ name: '报检单名称', field: 'inspectionCompanyName', width: '15%', enableColumnMenu: false },*/
		            { name: '分部分项', field: 'tendersBranch', width: '15%', enableColumnMenu: false},
		            { name: '检测段落', field: 'checkParagraph', width: '15%', enableColumnMenu: false},
		            { name: '质量状态', field: 'qualityStatus_', width: '10%', enableColumnMenu: false, decode: { field: "qualityStatus", dataKey: "dic.inspection_status" } },
		            { name: '审批状态', field: 'approvalStatus_', width: '15%', enableColumnMenu: false,decode:{field: "approvalStatus", dataKey: "dic.deal_type" }},
		            { name: '报检人', field: 'inspectionUserName', width: '15%', enableColumnMenu: false},
		            { name: '报检日期', field: 'inspectionDate', width: '15%', enableColumnMenu: false},
		        ]
	    		httpService.get($scope,'/api/constructionInspection/getConInspectionByInsCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
	    	case 'routing_inspection': /*质量巡检*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
		             { name: '检测编号', field: 'checkNumber', width: '10%', enableColumnMenu: false },
		             { name: '巡检类型', field: 'patrolType', width: '10%', enableColumnMenu: false,decode:{field:'patrolType',dataKey:'dic.patrol_type'}},
		             { name: '被通报单位', field: 'orgName', width: '10%', enableColumnMenu: false },
		             { name: '检测时间', field: 'checkTime', width: '10%', enableColumnMenu: false },
		             { name: '检测人', field: 'checkPerson', width: '10%', enableColumnMenu: false },
		             { name: '相关责任人', field: 'personLiable', width: '10%', enableColumnMenu: false },
		             { name: '紧急程度', field: 'urgency', width: '10%', enableColumnMenu: false ,decode:{field:'urgency',dataKey:'dic.emergency_degree'}},
		             { name: '整改期限（天）', field: 'rectificationPeriod', width: '10%', enableColumnMenu: false },
		             { name: '是否合格', field: 'checkResults', width: '10%', enableColumnMenu: false,decode:{field:'checkResults',dataKey:'dic.inspection_status'} },
		             { name: '下发时间', field: 'disposeDate', width: '10%', enableColumnMenu: false },
		             { name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
		         ]
	    		httpService.get($scope,'/api/busiQualityRoutingInspection/getRoutingInspectionByReCode/'+rectificationCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    			myLoadData('/api/busiQualityRectification/queryRectification/'+rectificationCode.value);
	    	    });
	    		break;
    	}
    }
    
    /* 通过editId查询整改单对象 */
    var load = function() {
    	editService.loadData($scope, '/api/busiQualityRectification/edit',$scope.editId,loadSuccessCallBack);
    }
    
    load();
    
    /**
     * 流程提交
     **/   
    $scope.submit = function(){
    	editService.saveData($scope, submitDataUrl+'/'+$scope.taskId+'/'+rectificationId.value);
    } 

    /**
     * 查看检测报告
     *
     * @param      {<type>}  entity  The entity
     */
    $scope.viewDescription = function(url){
    	window.open(url);
    }

    /**
     * 获取整改单明细时候数据
     *
     * @param      {string}  id      The identifier
     */
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
			  }
			  $scope.rectificationDetailList = data;
		  });
	  }

	  var _scope = $scope;
	  /**
	   * 根据不同的整改类型加载数据
	   *
	   * @param      {<type>}  url     The url
	   */
	  var myLoadData = function(url){
		  httpService.get($scope,url).success(function(response) {
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