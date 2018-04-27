/**
 * 整改单Controller 
 * busiBaseEngineeringQuantityListController
 */
'use strict';
   
angular.module('huatek.controllers').controller('BusiQualityRectificationController', function ($scope, $location, $http,$rootScope,editService, listService,shareData, $localStorage,cacheService) {
	/**创建表格*/
	$scope.tableGrid = {
		paginationPageSizes: [10, 25, 50,100],
	    paginationPageSize: 10,
	    useExternalPagination: true,
	    enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
	    columnDefs: [
           { name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
           { name: '检查编号', field: 'checkNo',width: '15%', enableColumnMenu: false},
          /* { name: '名称', field: 'sampleName',width: '14%', enableColumnMenu: false},*/
           { name: '类型', field: 'dataSource_',width: '10%', enableColumnMenu: false,decode:{field:'dataSource',dataKey:'dic.original_type'}},
           { name: '检测时间', field: 'rectificationCheckTime',width: '10%', enableColumnMenu: false},
           { name: '下发人', field: 'applyUserName',width: '10%', enableColumnMenu: false},
           { name: '下发时间', field: 'applyTime',width: '10%', enableColumnMenu: false},
          /* { name: '数据来源', field: 'tpId',width: '10%', enableColumnMenu: false},*/
           { name: '紧急程度', field: 'rectificationUrgency_',width: '10%', enableColumnMenu: false,decode:{field:'rectificationUrgency',dataKey:'dic.emergency_degree'}},
           { name: '处理状态', field: 'approvalStatus_',width: '10%', enableColumnMenu: false,decode:{field:'approvalStatus',dataKey:'dic.deal_type'},cellTemplate:listService.getLinkCellTmplate("queryData","approvalStatus_")}
	    ]
	};
	/**初始化表格*/
	listService.init($scope, $location, $http);

	/**
     * 页面加载完成,过滤处理状态数据字典
     */
    $scope.$watch('$viewContentLoaded', function() { 
//        var cache=$localStorage["dic.deal_type"];
//        cacheService.getData('dic.deal_type');
        var sourceList = cacheService.getData('dic.deal_type');
        angular.forEach(sourceList, function (data, index) {
            if(data.code >= 4){
                approvalStatus.items.push({ "_showField": data.name, "_returnField": data.code });
            }
        });
    });

	
	/** 查询条件 */
	var queryPage = new QueryPage(1, 10, "id desc", "false");
    var orgId = new queryParam('标段名称', 'org.id', 'string', '='); /* 机构下拉树 */
    orgId.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
 /*  accountType.dropDataUrl = "dic.staff_type";*/
    queryPage.addParam(orgId);
    var inspectionCode = new queryParam('检查编号', 'checkNo', 'string', 'like');
    queryPage.addParam(inspectionCode);
    var approvalStatus = new queryParam('处理状态', 'approvalStatus', 'string', '=',null);
    approvalStatus.componentType = 'select';
    queryPage.addParam(approvalStatus);
    var originalType = new queryParam('类型', 'dataSource', 'string', '=',null,'dic.original_type');
    originalType.value = 'raw_material_inspection';
    originalType.componentType = 'select';
    queryPage.addParam(originalType);
    var applyTime = new queryParam('下发时间(开始)', 'applyTime', 'date', '>=');
    queryPage.addParam(applyTime);
    var applyTime = new queryParam('下发时间(结束)', 'applyTime', 'date', '<=');
    queryPage.addParam(applyTime);
    var rectificationUrgency = new queryParam('紧急程度', 'rectificationUrgency', 'string', '=',null,'dic.emergency_degree');
    rectificationUrgency.componentType = 'select';
    queryPage.addParam(rectificationUrgency);

    
	/** 查询条件初始化 */
	listService.setQueryPage($scope,queryPage);
	
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
	/*btnArray.push(new pageButton('查看', 'query', '/busiQualityRectification/query', 'queryData'));*/
	/**
	 * 设置界面的功能按钮.
	 */
    listService.setButtonList($scope, btnArray);
    
    /**
	 * 桥接按钮事件.
	 */
    var inspectionCode = '';
    $scope.execute = function(operation, param){
    	var selectRow = $scope.gridApi.selection.getSelectedRows();
        if(operation=='queryData'){
			var paramMap = {'originalType':originalType.value,'inspectionCode':param.inspectionCode};
			if(originalType.value=='construction_inspection'){/*施工报检*/
				var paramMap = {'type':'rectification'};
				listService.editData($scope, $scope.gridApi, new popup("查看整改单", "/busiQualityConsInspection/consFlow", paramMap, "1200", "800"), param);
			}else if(originalType.value=='routing_inspection'){/*质量巡检*/
				var paramMap = {'type':'rectification'};
				listService.editData($scope,$scope.gridApi, new popup("查看整改单","/busiQualityRoutingInspection/view", paramMap, "1200", "800"),param);
			}else{/*其他模块*/
				var paramMap = {'originalType':originalType.value,'inspectionCode':param.rectificationCode};
				listService.editData($scope, $scope.gridApi, new popup("查看整改单","/busiQualityRectification/query", paramMap, "1200", "800"),param);
			}
        } 
    }

    var load = function(){
    	if(!originalType.value){
    		submitTips('请选择类型','warning');
    		return;
    	}
    	listService.loadData($scope,'api/busiQualityRectification/query', $scope.tableGrid);
    	/*listService.loadData($scope,'api/busiCommonRectification/query', $scope.tableGrid);*/
	}

	load();
	/*
	 * 查询需要调用的函数.
	 */
	$scope.search = function() {
		load();
	};
    
});

/**
 * 查询整改明细
 */
angular.module('huatek.controllers').controller('BusiQualityRectificationDetailController', function ($scope,$rootScope, $location, $http,listService, editService, cacheService,httpService,$q) {
	var originType = $scope.passParams.originalType;
	var inspectionCode = $scope.passParams.inspectionCode;
	var typeField = '';
	/* 判断接口来源类型 */
	if(originType=='raw_material_inspection'){/*原材料检测*/
		typeField = [
             { name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
             { name: '样品编号', field: 'sampleCode',width: '10%', enableColumnMenu: false},
             { name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
             { name: '检测编号', field: 'checkCode',width: '10%', enableColumnMenu: false},
             { name: '检测时间', field: 'checkDate',width: '10%', enableColumnMenu: false},
             { name: '检测量', field: 'checkQuantity',width: '10%', enableColumnMenu: false},
             { name: '机器编号', field: 'machineNo',width: '10%', enableColumnMenu: false},
             { name: '数据来源', field: 'tpId',width: '10%', enableColumnMenu: false},
             { name: '检测报告', field: 'reportAddress',width: '10%', enableColumnMenu: false },
             { name: '上传时间', field: 'disposeTime',width: '10%', enableColumnMenu: false},
             /*{ name: '处理状态', field: 'disposeState_',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type'}},*/
         ];
	}else if(originType=='water_stable_mixing_station'){/*水稳拌合站*/
		typeField = [
			{ name: '配方名称', field: 'recipeName',width: '10%', enableColumnMenu: false},
			{ name: '配方号', field: 'recipeCode',width: '15%', enableColumnMenu: false},
			{ name: '采集时间', field: 'gatherDate',width: '10%', enableColumnMenu: false},
			{ name: '超标等级', field: 'overproofGrade',width: '10%', enableColumnMenu: false,decode:{parent:'busiQualityWaterStableMixingStationExceed',field:'excessiveGrade',dataKey:'dic.excessive_grade'}},
			{ name: '超标原因', field: 'busiQualityWaterStableMixingStationExceed.overproofReason',width: '15%', enableColumnMenu: false},
			{ name: '检测报告', field: 'busiQualityWaterStableMixingStationExceed.reportAddress',width: '15%', enableColumnMenu: false},
			{ name: '上传时间', field: 'busiQualityWaterStableMixingStationExceed.disposeDate',width: '15%', enableColumnMenu: false},
/*			{ name: '处理状态', field: 'disposeState',width: '10%', enableColumnMenu: false,decode:{parent:'busiQualityWaterStableMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type'}},
*/		]
	}else if(originType=='spreader_roller'){/*铺摊碾压*/
		typeField = [
			{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
			{ name: '作业层面', field: 'operationSurface',width: '10%', enableColumnMenu: false},
			{ name: '采集时间', field: 'gatherDate',width: '10%', enableColumnMenu: false},
			{ name: '作业类型', field: 'type',width: '15%', enableColumnMenu: false,decode:{field:'type',dataKey:'dic.job_type'}},
			{ name: '超标等级', field: 'overproofGrade',width: '10%', enableColumnMenu: false,decode:{field:'overproofGrade',dataKey:'dic.excessive_grade'}},
			{ name: '超标原因', field: 'overproofReason',width: '15%', enableColumnMenu: false},
			{ name: '检测报告', field: 'reportAddress',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'modifyTime',width: '10%', enableColumnMenu: false},
			/*{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type'}},*/
        ]
	}else if(originType=='cement_mixing_station_inspection'){/*水泥拌合站*/
		typeField = [
			{ name: '施工地点', field: 'jobLocation',width: '10%', enableColumnMenu: false},
			{ name: '工程名称', field: 'engineeringName',width: '10%', enableColumnMenu: false},
			{ name: '强度等级', field: 'strengthGrade',width: '10%', enableColumnMenu: false},
			{ name: '配方号', field: 'recipeNumber',width: '10%', enableColumnMenu: false},
			{ name: '超标等级', field: 'excessiveGrade',width: '10%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'excessiveGrade',dataKey:'dic.excessive_grade'}},
			{ name: '超标原因', field: 'busiQualityCementMixingStationExceed.excessiveReason',width: '10%', enableColumnMenu: false},
			{ name: '检测报告', field: 'busiQualityCementMixingStationExceed.reportAddress',width: '10%', enableColumnMenu: false},
			{ name: '描述', field: 'busiQualityCementMixingStationExceed.desContent',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'busiQualityCementMixingStationExceed.disposeTime',width: '10%', enableColumnMenu: false},
			/*{ name: '处理状态', field: 'disposeState',width: '10%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type'}},*/
     ]
	}else if(originType=='asphalt_mixing_station'){/*沥青拌合站*/
		typeField = [
			{ name: '施工地点', field: 'constructionPosition',width: '15%', enableColumnMenu: false},
			{ name: '工程名称', field: 'engineeringName',width: '15%', enableColumnMenu: false},
			{ name: '配方号', field: 'formula',width: '15%', enableColumnMenu: false},
			{ name: '超标等级', field: 'overproofGrade',width: '10%', enableColumnMenu: false,decode:{parent:'busiQualityAsphaltMixingPlantExceed',field:'overproofGrade',dataKey:'dic.excessive_grade'}},
			{ name: '超标原因', field: 'busiQualityAsphaltMixingPlantExceed.overproofReason',width: '15%', enableColumnMenu: false},
			{ name: '检测报告', field: 'busiQualityAsphaltMixingPlantExceed.reportAddress',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'busiQualityAsphaltMixingPlantExceed.disposeDate',width: '10%', enableColumnMenu: false},
			/*{ name: '处理状态', field: 'disposeState',width: '10%', enableColumnMenu: false,decode:{parent:'busiQualityAsphaltMixingPlantExceed',field:'disposeState',dataKey:'dic.deal_type'}},*/
     ]
	}else if(originType=='test_inspection'){/*试验检测*/
		typeField = [
			{ name: '检测单位', field: 'orgName',width: '10%', enableColumnMenu: false},
			{ name: '样品编号', field: 'sampleNumber',width: '10%', enableColumnMenu: false},
			{ name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
			{ name: '试验类型', field: 'type_',width: '10%', enableColumnMenu: false,decode:{field:'type',dataKey:'dic.test_inspection_type'} },
			{ name: '试验日期', field: 'testTime',width: '10%', enableColumnMenu: false},
			{ name: '试验人', field: 'verifier',width: '10%', enableColumnMenu: false},
			{ name: '检测报告', field: 'descUrl',width: '10%', enableColumnMenu: false},
			{ name: '描述', field: 'unqualifiedDescription',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'modifyTime',width: '10%', enableColumnMenu: false},
			/*{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type'}},*/
     ]
	}else if(originType=='mortar_inspection'){/*砂浆检测*/
		typeField = [
			{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
			{ name: '样品编号', field: 'sampleCode',width: '15%', enableColumnMenu: false},
			{ name: '样品名称', field: 'sampleName',width: '15%', enableColumnMenu: false},
			{ name: '试验类型', field: 'testType',width: '10%', enableColumnMenu: false},
			{ name: '试验日期', field: 'testDate',width: '10%', enableColumnMenu: false},
			{ name: '试验人', field: 'testUser',width: '10%', enableColumnMenu: false},
			{ name: '检测报告', field: 'descriptionUrl',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'disposeTime',width: '10%', enableColumnMenu: false},
			/*{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type'}},*/
     ]
	}else if(originType=='prestressed_tension_main_inspection'){/*预应力张拉、压浆检测*/
		typeField = [
			{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
			{ name: '工程名称', field: 'engineeringName',width: '10%', enableColumnMenu: false},
			{ name: '桥梁名称', field: 'bridgeName',width: '10%', enableColumnMenu: false},
			{ name: '分级张拉', field: 'gradedTension',width: '10%', enableColumnMenu: false,decode:{field:'gradedTension',dataKey:'dic.graded_tension'}},
			{ name: '记录时间', field: 'recordTime',width: '10%', enableColumnMenu: false},
			{ name: '张拉力状态', field: 'tensionState',width: '10%', enableColumnMenu: false,decode:{field:'gradedTension',dataKey:'dic.tension_state'}},
			{ name: '伸长量状态', field: 'elongationState',width: '10%', enableColumnMenu: false,decode:{field:'gradedTension',dataKey:'dic.tension_state'}},
			{ name: '应力状态', field: 'stressState',width: '10%', enableColumnMenu: false,decode:{field:'gradedTension',dataKey:'dic.tension_state'}},
			{ name: '检测报告', field: 'reportAddress',width: '10%', enableColumnMenu: false},
			{ name: '上传时间', field: 'disposeTime',width: '10%', enableColumnMenu: false},
			/*{ name: '处理状态', field: 'disposeStatus1',width: '10%', enableColumnMenu: false,decode:{field:'disposeStatus',dataKey:'dic.deal_type'}},*/
        ]
	}else if(originType=='construction_inspection'){/*施工报检*/
		typeField = [
			{ name: '标段名称', field: 'orgName', width: '15%', enableColumnMenu: false },
			/*{ name: '报检单名称', field: 'inspectionCompanyName', width: '15%', enableColumnMenu: false },*/
			{ name: '分部分项', field: 'tendersBranchName', width: '15%', enableColumnMenu: false},
			{ name: '检测段落', field: 'checkParagraph', width: '15%', enableColumnMenu: false},
			{ name: '质量状态', field: 'qualityStatus_', width: '10%', enableColumnMenu: false, decode: { field: "qualityStatus", dataKey: "dic.construction_inspection_type" } },
	        /*{ name: '审批状态', field: 'approvalStatus_', width: '15%', enableColumnMenu: false,decode:{field: "approvalStatus", dataKey: "dic.construction_approval_status" }},*/
			{ name: '报检人', field: 'inspectionUserName', width: '15%', enableColumnMenu: false},
			{ name: '报检日期', field: 'inspectionDate', width: '15%', enableColumnMenu: false},
		]
	}else if(originType=='routing_inspection'){/*质量巡检*/
		typeField = [
		     { name: '检测编号', field: 'checkNumber', width: '10%', enableColumnMenu: false },
		     { name: '巡检类型', field: 'patrolType', width: '10%', enableColumnMenu: false,decode:{field:'patrolType',dataKey:'dic.patrol_type'}},
		     { name: '被通报单位', field: 'orgName', width: '10%', enableColumnMenu: false },
		     { name: '检测时间', field: 'checkTime', width: '10%', enableColumnMenu: false },
		     { name: '检测人', field: 'checkPerson', width: '10%', enableColumnMenu: false },
		     { name: '相关责任人', field: 'personLiable', width: '10%', enableColumnMenu: false },
		     { name: '紧急程度', field: 'urgency', width: '10%', enableColumnMenu: false ,decode:{field:'urgency',dataKey:'dic.emergency_degree'}},
		     { name: '整改期限（天）', field: 'rectificationPeriod', width: '10%', enableColumnMenu: false },
		     { name: '下发时间', field: 'disposeDate', width: '10%', enableColumnMenu: false },
		     /*{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type'}},*/
		 ]
	}
	
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
	  formFieldArray.push(new FormElement(1,'标段编号','orgName','string',1,'20'));
	  formFieldArray.push(new FormElement(1,'检查编号','checkNo','string',1,'50'));
	  formFieldArray.push(new FormElement(1,'整改期限','rectificationPeriod','number',1,'30'));
	  formFieldArray.push(new FormElement(1,'检查人员','rectificationPerson','string',1,'30'));
	  formFieldArray.push(new FormElement(1,'相关负责人','rectificationPersonLiable','string',1,'30'));
	  formFieldArray.push(new FormElement(1,'紧急程度','rectificationUrgency','string',1,'30','select','','','','dic.emergency_degree'));
	  formFieldArray.push(new FormElement(1,'检查内容','rectificationCheckContent','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'存在问题','rectificationExistingProblems','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'整改要求','rectificationRequirements','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'处罚建议','rectificationPunishmentSuggestion','string',0,'30','textarea'));
	  formFieldArray.push(new FormElement(1,'附件','fileId','string',0,'500','fileMultiple'));
			 
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
	  
	  $scope.rectificationDetailList = [];
	  /* 判断接口来源类型 */
	  if(originType=='raw_material_inspection'){/* 原材料检测 */
		  httpService.get($scope,'/api/busiQualityRawMaterialInspection/getRawMaterialByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='water_stable_mixing_station'){/*水稳拌合站*/
		  httpService.get($scope,'/api/busiQualityWaterStableMixingStation/getWaterStableMixingStationByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='spreader_roller'){/*铺摊碾压*/
		  httpService.get($scope,'/api/busiQualitySpreaderRollerSpreader/getSpreaderRollerSpreaderByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='cement_mixing_station_inspection'){/*水泥拌合站*/
		  httpService.get($scope,'/api/busiQualityCementMixingStation/getCementMixingStationByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='asphalt_mixing_station'){/*沥青拌合站*/
		  httpService.get($scope,'/api/busiQualityAsphaltMixingPlant/getAsphaltMixingPlantByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='test_inspection'){/*试验检测*/
		  httpService.get($scope,'/api/busiQualityTestInspection/getUniversalPressMachineByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='mortar_inspection'){/*砂浆检测*/
		  httpService.get($scope,'/api/busiQualityMortarInspection/getMortarByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='prestressed_tension_main_inspection'){/*预应力张拉、压浆检测*/
		  httpService.get($scope,'/api/busiQualityPrestressedTensionMain/getPrestressedTensionMainByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='construction_inspection'){/*施工报检*/
		  httpService.get($scope,'/api/constructionInspection/getConInspectionByInsCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }else if(originType=='routing_inspection'){/*质量巡检*/
		  httpService.get($scope,'/api/busiQualityRoutingInspection/getRoutingInspectionByReCode/'+inspectionCode).success(function(response){
			  listService.decodeTable($scope, response, $scope.tableGrid);
			  $scope.tableGrid.data = response;
			  myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode);
		  });
	  }
      
      
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
