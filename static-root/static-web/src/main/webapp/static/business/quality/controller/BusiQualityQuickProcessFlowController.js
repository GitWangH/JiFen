/**
 * 快捷处理审批流程页
 * @author rocky_wei
 */
angular.module('huatek.controllers').controller('BusiQualityQuickProcessFlowController', function ($rootScope, $scope, $http, listService, editService,httpService) {
	$scope.busiCode=$scope.passParams.busiCode;
	$scope.taskId=$scope.passParams.taskId;
	$scope.busiId=$scope.passParams.busiId;
	$scope.taskKey=$scope.passParams.taskKey;
	$scope.taskName=$scope.passParams.taskName;
	$scope.processInstanceId=$scope.passParams.processInstanceId;
	$scope.onlyView=$scope.passParams.onlyView;
	$scope.editId=$scope.busiId;

	var _prefix = '/api/busiQualityQuickProcess/';
	var editDataUrl = _prefix+'edit';

	var tableGridContent = [];
	$scope.tableGrid = {};
	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'快捷处理'));
    if(!$scope.onlyView){
    	columnWrapArray.push(new multipleColumn(2,'审批信息'));
 	}
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'说明','quickExplain','string',1,'500','textarea'));
    formFieldArray.push(new FormElement(1,'处理人员','quickUserName','string',1,'30'));
    formFieldArray.push(new FormElement(1,'处理时间','quickProcessingTime','string',1,'30'));
    formFieldArray.push(new FormElement(1,'原因','quickReason','string',1,'100','select',null,null,null,'dic.quick_process_reason'));
    /*formFieldArray.push(new FormElement(1,'附件','enclosure','string',0,'100','fileMultiple'));*/
    var contractFileUUID = new FormElement(1,'附件','enclosure','string',0,'100','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/quickProcessingFlow";
 	formFieldArray.push(contractFileUUID);
    var dataSource = new FormElement(1,'快速处理类型','dataSource','string',0,'100');
    dataSource.value = '';
    dataSource.isShow = false;
    formFieldArray.push(dataSource);
    var quickProcessCode = new FormElement(1,'快捷处理编号','quickProcessCode','string',0,'50');
    quickProcessCode.value = '';
    quickProcessCode.isShow = false;
    formFieldArray.push(quickProcessCode);
    
    var result=new FormElement(2,'审核','result','boolean',1,'128',"radio","resultChange");
	result.items=[{code:"true",name:'同意'},{code:"false",name:"驳回"}];
	formFieldArray.push(result);
	var resultMessage=new FormElement(2,'审核意见','resultMessage','string',1,'1000',"textarea");
	formFieldArray.push(resultMessage);
	formFieldArray.push(new FormElement(2,'意见模版','resultMessageTemplate','string',0,'512',"select"));
	
	for(var i=0;i<formFieldArray.length;i++){
		if(formFieldArray[i].column!=2){
			formFieldArray[i].readonly = true;
		}
	}
    
    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope);
	
	/*var addConfig = function(tableGrid){
		tableGrid.enableFullRowSelection = true;
		tableGrid.enableSelectAll = true;
		tableGrid.multiSelect = false;
	}*/
  
   /***
    * 设置编辑界面输入的字段.
    * 被设置必须在服务初始化之后执行.
    */
    editService.setFormFields($scope, formFieldArray); 
    
    var loadSuccessCallBack = function(){
    	/* 判断快捷处理类型，根据类型去查对应的表 */
    	switch(dataSource.value){ 
	    	case 'raw_material_inspection': /*原材料*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
					{ name: '样品编号', field: 'sampleCode',width: '10%', enableColumnMenu: false},
					{ name: '样品名称', field: 'sampleName',width: '10%', enableColumnMenu: false},
					{ name: '检测编号', field: 'checkCode',width: '10%', enableColumnMenu: false},
					{ name: '检测时间', field: 'checkDate',width: '10%', enableColumnMenu: false},
					{ name: '检测量', field: 'checkQuantity',width: '8%', enableColumnMenu: false},
					{ name: '机器编号', field: 'machineNo',width: '10%', enableColumnMenu: false},
					{ name: '检测状态', field: 'reportConclusion',width: '8%', enableColumnMenu: false,decode:{field:'reportConclusion',dataKey:'dic.inspection_status'}},
					{ name: '检测报告', field: 'reportAddress',width: '7%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '上传时间', field: 'createTime',width: '8%', enableColumnMenu: false},
					{ name: '处理状态', field: 'disposeState_',width: '8%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
		        ]
	    		
	    		httpService.get($scope,'/api/busiQualityRawMaterialInspection/getRawMaterialByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    	    });
	    		break;
	    	case 'water_stable_mixing_station': /*水稳拌合站*/
	    		
	    		$scope.tableGrid.columnDefs = tableGridContent =  [
					{ name: '配方名称', field: 'recipeName',width: '11%', enableColumnMenu: false},
					{ name: '配方号', field: 'recipeCode',width: '11%', enableColumnMenu: false},
					{ name: '采集时间', field: 'gatherDate',width: '11%', enableColumnMenu: false},
					{ name: '超标等级', field: 'overproofGrade',width: '11%', enableColumnMenu: false,decode:{parent:'busiQualityWaterStableMixingStationExceed',field:'overproofGrade',dataKey:'dic.excessive_grade'}},
					{ name: '超标原因', field: 'busiQualityWaterStableMixingStationExceed.overproofReason',width: '11%', enableColumnMenu: false},
					{ name: '检测报告', field: 'busiQualityWaterStableMixingStationExceed.reportAddress',width: '11%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '上传时间', field: 'busiQualityWaterStableMixingStationExceed.createTime',width: '11%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isExcessive',width: '11%', enableColumnMenu: false,decode:{field:'isExcessive',dataKey:'dic.is_excessive'}},
					{ name: '处理状态', field: 'disposeState',width: '11%', enableColumnMenu: false,decode:{parent:'busiQualityWaterStableMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type_new'}},
   		        ]
	    		httpService.get($scope,'/api/busiQualityWaterStableMixingStation/getWaterStableMixingStationByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
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
					{ name: '检测报告', field: 'reportAddress',width: '9%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '上传时间', field: 'createTime',width: '10%', enableColumnMenu: false},
					{ name: '检测状态', field: 'dataType',width: '10%', enableColumnMenu: false,decode:{field:'dataType',dataKey:'dic.is_qualified'}},
					{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
  		        ]
	    		
	    		httpService.get($scope,'/api/busiQualitySpreaderRollerSpreader/getSpreaderRollerSpreaderByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
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
					{ name: '检测报告', field: 'busiQualityCementMixingStationExceed.reportAddress',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '描述', field: 'busiQualityCementMixingStationExceed.desContent',width: '10%', enableColumnMenu: false},
					{ name: '上传时间', field: 'busiQualityCementMixingStationExceed.createTime',width: '9%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isExcessive',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'isExcessive',dataKey:'dic.is_excessive'}},
					{ name: '处理状态', field: 'disposeState',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityCementMixingStationExceed',field:'disposeState',dataKey:'dic.deal_type_new'}},
  		        ]
	    		httpService.get($scope,'/api/busiQualityCementMixingStation/getCementMixingStationByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    		});
	    		break;
	    	case 'asphalt_mixing_station': /*沥青拌合站*/
	    		$scope.tableGrid.columnDefs = tableGridContent = [
					{ name: '施工地点', field: 'constructionPosition',width: '12%', enableColumnMenu: false},
					{ name: '工程名称', field: 'engineeringName',width: '12%', enableColumnMenu: false},
					{ name: '配方号', field: 'formula',width: '12%', enableColumnMenu: false},
					{ name: '超标等级', field: 'overproofGrade',width: '8%', enableColumnMenu: false,decode:{parent:'busiQualityAsphaltMixingPlantExceed',field:'overproofGrade',dataKey:'dic.excessive_grade'}},
					{ name: '超标原因', field: 'busiQualityAsphaltMixingPlantExceed.overproofReason',width: '11%', enableColumnMenu: false},
					{ name: '检测报告', field: 'busiQualityAsphaltMixingPlantExceed.reportAddress',width: '11%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '上传时间', field: 'busiQualityAsphaltMixingPlantExceed.createTime',width: '11%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isExcessive',width: '11%', enableColumnMenu: false,decode:{field:'isExcessive',dataKey:'dic.is_excessive'}},
					{ name: '处理状态', field: 'disposeState',width: '11%', enableColumnMenu: false,decode:{parent:'busiQualityAsphaltMixingPlantExceed',field:'disposeState',dataKey:'dic.deal_type_new'}},
  		        ]
	    		
	    		httpService.get($scope,'/api/busiQualityAsphaltMixingPlant/getAsphaltMixingPlantByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
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
					{ name: '检测报告', field: 'descUrl',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '描述', field: 'unqualifiedDescription',width: '10%', enableColumnMenu: false},
					{ name: '上传时间', field: 'createTime',width: '9%', enableColumnMenu: false},
					{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
  		        ]
	    		
	    		httpService.get($scope,'/api/busiQualityTestInspection/getUniversalPressMachineByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
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
					{ name: '检测报告', field: 'descriptionUrl',width: '10%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '上传时间', field: 'createTime',width: '10%', enableColumnMenu: false},
					{ name: '处理状态', field: 'disposeState1',width: '10%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type_new'}},
  		        ]
	    		httpService.get($scope,'/api/busiQualityMortarInspection/getMortarByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
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
					{ name: '检测报告', field: 'reportAddress',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewReport","","查看报告")},
					{ name: '上传时间', field: 'createTime',width: '8%', enableColumnMenu: false},
					{ name: '检测状态', field: 'isQualified',width: '8%', enableColumnMenu: false,decode:{field:'isQualified',dataKey:'dic.is_qualified'}},
					{ name: '处理状态', field: 'disposeStatus1',width: '8%', enableColumnMenu: false,decode:{field:'disposeStatus',dataKey:'dic.deal_type_new'}},
  		        ]
	    		
	    		httpService.get($scope,'/api/busiQualityPrestressedTensionMain/getPrestressedTensionMainByReCode/'+quickProcessCode.value).success(function(response){
	    			listService.decodeTable($scope, response, $scope.tableGrid);
	    			$scope.tableGrid.data = response;
	    		});
	    		break;
    	}
    	/*addConfig($scope.tableGrid);*/
    }
    
    /* 通过editId查询快捷处理对象 */
    var load = function() {
    	editService.loadData($scope, editDataUrl,$scope.editId,loadSuccessCallBack);
        
    }
    
    load();
    
    /**
     * 流程提交
     **/   
    $scope.submit = function(){
    	editService.submitData($scope, WORKFLOW_COMMON_SUBMIT_URL);
    } 
    
});