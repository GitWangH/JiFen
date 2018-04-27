/**
 * 快捷处理Controller 
 * busiBaseEngineeringQuantityListController
 */
'use strict';
angular.module('huatek.controllers').controller('BusiQualityQuickProcessDetailController', function ($scope,$rootScope, $location, $http,listService, editService, cacheService,httpService,$q) {
	var originType = $scope.passParams;
	var typeField = '';
	/*console.log(originType)*/
	/* 判断接口来源类型 */
	if(originType=='raw_material_inspection'){/*原材料检测*/
		typeField = [
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
         ];
	}else if(originType=='water_stable_mixing_station'){/*水稳拌合站*/
		typeField = [
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
	}else if(originType=='spreader_roller'){/*铺摊碾压*/
		typeField = [
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
	}else if(originType=='cement_mixing_station_inspection'){/*水泥拌合站*/
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
     ]
	}else if(originType=='asphalt_mixing_station'){/*沥青拌合站*/
		typeField = [
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
	}else if(originType=='test_inspection'){/*试验检测*/
		typeField = [
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
	}else if(originType=='mortar_inspection'){/*砂浆检测*/
		typeField = [
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
	}else if(originType=='prestressed_tension_main_inspection'){/*预应力张拉、压浆检测*/
		typeField = [
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
	  columnWrapArray.push(new multipleColumn(1,'快捷处理'));
	  $scope.columnWrapArray = columnWrapArray;
	  
		//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
	  var formFieldArray = [];
	  	formFieldArray.push(new FormElement(1,'说明','quickExplain','string',1,'500','textarea'));
	    formFieldArray.push(new FormElement(1,'处理人员','quickUserName','string',1,'30',null,null,null));
	    /*formFieldArray.push(new FormElement(1,'处理时间','quickProcessingTime','string',0,'30','date'));*/
	    formFieldArray.push(new FormElement(1,'处理时间','quickProcessingTime','string',1,'30'));
	    formFieldArray.push(new FormElement(1,'原因','quickReason','string',1,'100','select',null,null,null,'dic.quick_process_reason'));
	    formFieldArray.push(new FormElement(1,'附件','enclosure','string',0,'100','fileMultiple'));
			 
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
	  if(originType=='raw_material_inspection'){/* 原材料检测 */
		  httpService.get($scope,'/api/busiQualityRawMaterialInspection/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
	  }else if(originType=='water_stable_mixing_station'){/*水稳拌合站*/
		  httpService.get($scope,'/api/busiQualityWaterStableMixingStation/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
	  }else if(originType=='spreader_roller'){/*铺摊碾压*/
		  httpService.get($scope,'/api/busiQualitySpreaderRollerSpreader/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
	  }else if(originType=='cement_mixing_station_inspection'){/*水泥拌合站*/
		  httpService.get($scope,'/api/busiQualityCementMixingStation/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
	  }else if(originType=='asphalt_mixing_station'){/*沥青拌合站*/
		  httpService.get($scope,'/api/busiQualityAsphaltMixingPlant/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
	  }else if(originType=='test_inspection'){/*试验检测*/
		  httpService.get($scope,'/api/busiQualityTestInspection/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
	  }else if(originType=='mortar_inspection'){/*砂浆检测*/
		  httpService.get($scope,'/api/busiQualityMortarInspection/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
	  }else if(originType=='prestressed_tension_main_inspection'){/*预应力张拉、压浆检测*/
		  httpService.get($scope,'/api/busiQualityPrestressedTensionMain/edit/'+$scope.editId).success(function(response){
			  listService.decodeTable($scope, [response], $scope.tableGrid);
			  $scope.tableGrid.data = [response];
			  myLoadData('/api/busiQualityQuickProcess/queryRectification/'+response.inspectionCode);
		  })
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
	    });
	  }
});
