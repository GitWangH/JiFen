/**
 * 质量巡检Controller 
 * BusiQualityRoutingInspectionController
 */
'use strict';
angular.module('huatek.controllers').controller('BusiQualityRoutingInspectionController', function($rootScope, $scope, $location, $http, listService, httpService) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '检测编号', field: 'checkNumber', width: '8%', enableColumnMenu: false },
            { name: '巡检类型', field: 'patrolType', width: '8%', enableColumnMenu: false,decode:{field:'patrolType',dataKey:'dic.patrol_type'}},
            { name: '被通报单位', field: 'orgName', width: '10%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("editViewData","orgName")},
            { name: '检测人', field: 'checkPerson', width: '8%', enableColumnMenu: false },
            { name: '相关责任人', field: 'personLiable', width: '10%', enableColumnMenu: false },
            { name: '紧急程度', field: 'urgency', width: '10%', enableColumnMenu: false ,decode:{field:'urgency',dataKey:'dic.emergency_degree'}},
            { name: '下发时间', field: 'disposeDate', width: '10%', enableColumnMenu: false },
            { name: '整改期限（天）', field: 'rectificationPeriod', width: '8%', enableColumnMenu: false },
            { name: '检测时间', field: 'checkTime', width: '10%', enableColumnMenu: false },
            { name: '是否合格', field: 'checkResults', width: '8%', enableColumnMenu: false,decode:{field:'checkResults',dataKey:'dic.inspection_status'} },
            { name: '处理状态', field: 'disposeState1',width: '9%', enableColumnMenu: false,decode:{field:'disposeState',dataKey:'dic.deal_type'},cellTemplate:listService.getLinkCellTmplate("viewData","disposeState1") },
        ]
    };

    // 查询条件 标段 巡检类型 检测编号 处理状态 检测时间
    // 按钮 新增 查看 删除 整改下发 打印整改通知单 打印整改报告单

    listService.init($scope, $http);
    // 定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    // 定义搜索框
    var tendersName = new queryParam('标段名称', 'org.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
    queryPage.addParam(tendersName);
    queryPage.addParam(new queryParam('检测编号', 'checkNumber', 'string', 'like'));
    var patrolType = new queryParam('巡检类型', 'patrolType', 'string', '=', null, 'dic.patrol_type');
    patrolType.componentType = 'select';
    queryPage.addParam(patrolType);
    var disposeState = new queryParam('处理状态','disposeState','string','=',null,'dic.deal_type');
    disposeState.componentType = 'select';
    queryPage.addParam(disposeState);
    var checkResults = new queryParam('是否合格','checkResults','string','=',null,'dic.inspection_status');
    checkResults.componentType = 'select';
    queryPage.addParam(checkResults);
    queryPage.addParam(new queryParam('检测时间(始)', 'checkTime', 'date', '>='));
    queryPage.addParam(new queryParam('检测时间(终)', 'checkTime', 'date', '<='));
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
    btnArray.push(new pageButton('新增', 'add', '/busiQualityRoutingInspection/add', 'addData'));
    btnArray.push(new pageButton('修改', 'edit', '/busiQualityRoutingInspection/edit', 'editData'));
    /*btnArray.push(new pageButton('查看', 'view', '/busiQualityRoutingInspection/view', 'viewData'));*/
    btnArray.push(new pageButton('查看审批流','process','/busiQualityRoutingInspection/process','processData'));
    btnArray.push(new pageButton('删除', 'delete', '/busiQualityRoutingInspection/delete', 'deleteData'));
    btnArray.push(new pageButton('整改下发', 'rectification', '/busiQualityRoutingInspection/rectification', 'rectificationData'));
    btnArray.push(new pageButton('打印整改通知单', 'printNotice', '/busiQualityRoutingInspection/printNotice', 'printNoticeData'));
    btnArray.push(new pageButton('打印整改报告单', 'printReport', '/busiQualityRoutingInspection/printReport', 'printReportData'));
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
     * 桥接按钮事件.listService.getQueryFieldMap($scope).get('orgId')
     */
    $scope.execute = function(operation, param) {
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if (operation == 'addData') {
        	if(isAdminFn()){
        		listService.addData($scope, new popup("巡检维护新增", "/busiQualityRoutingInspection/add", null,900,600));
        	}
        }else if(operation == 'editData'){
        	if(isAdminFn()){
        		if (selectData.length == 0) {
    				submitTips('请至少勾选一条数据！', 'warning');
    				return;
    			}
        		if(selectData[0].disposeState=='3'){
        			listService.editData($scope, $scope.gridApi, new popup("巡检维护修改","/busiQualityRoutingInspection/edit", null, "1200", "800"));
        		}else{
        			submitTips('整改中或已整改的数据不支持修改', 'warning');
        		}
        	}
        } else if(operation == 'editViewData'){
			listService.editData($scope, $scope.gridApi, new popup("巡检维护查看","/busiQualityRoutingInspection/edit", true, "1200", "800"), param);
        }else if (operation == 'deleteData') {
        	if(isAdminFn()){
        		if (selectData.length == 0) {
    				submitTips('请至少勾选一条数据！', 'warning');
    				return;
    			}
        		if (listService.selectOne($scope.gridApi)) {
        			if(selectData[0].disposeState=='5' || selectData[0].disposeState=='4'){
        				submitTips('整改中或者已整改的数据不能删除！', 'warning');
        				return;
        			}
        			listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, "api/busiQualityRoutingInspection/delete/");
        		}
        	}
        } else if(operation == 'viewData'){
        	if(param.disposeState=='5' || param.disposeState=='4'){
        		listService.editData($scope,$scope.gridApi, new popup("查看","/busiQualityRoutingInspection/view", 'routing_inspection', "1200", "800"),param);
        	}else{
        		submitTips('该数据目前不支持查看','warning');
        	}
        }
        else if (operation == 'rectificationData') {
        	if (selectData.length == 0) {
				submitTips('请至少勾选一条数据！', 'warning');
				return;
			}

			var ids = [];
			for (var i = 0; i < selectData.length; i++) {
				if (selectData[i].disposeState != '3' || selectData[i].checkResults != '不合格') {
					submitTips('该条数据已提交整改或为合格数据，不能下发整改！', 'warning');
					ids = [];
					break;
				}
				ids.push(selectData[i].id);
			}
		
			if(ids.length > 0){
				$scope.promise = httpService.post($scope,'/api/busiQualityRoutingInspection/rectificationAdd/'+ ids).success(function(response) {
					load();
				})
			}

        }else if(operation == 'processData'){
        	var selectedData = listService.getSelectData($scope.gridApi);
        	if(selectedData){
        		if(selectedData.disposeState=='0' || selectedData.disposeState=='3'){
        			submitTips('该条数据还未处理或未整改！','warning');
        			return;
        		}
        		if(selectedData.disposeState=='2' || selectedData.disposeState=='1'){/*处理流程*/
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
        } else if (operation == 'printNoticeData') {
        	alert("developing ... ...");
        } else if (operation == 'printReportData') {
        	alert("developing ... ...");
        }
    }
    /**
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, '/api/busiQualityRoutingInspection/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

});

angular.module('huatek.controllers').controller('BusiQualityRoutingInspectionAddController', function($rootScope, $scope, $location, $http, editService) {
    var _prefix = 'api/busiQualityRoutingInspection/';
    var addDataUrl = _prefix + 'add';
    var editDataUrl = _prefix + 'edit';
    var homeUrl = '/busiQualityRoutingInspection/home';

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var orgName = new FormElement(1, '被通报单位', 'orgName', 'string', '', '50');
    var orgId = new FormElement(1, '被通报单位', 'orgId', 'string', 'require', '50', 'tenderSelect');
    orgId.lookValue = 'orgName';
    if(!$scope.editId){
    	orgId.value = $rootScope.orgId + '';
    }
    formFieldArray.push(orgId);
    var patrolType = new FormElement(1, '巡检类型', 'patrolType', 'string', 'require', '30', 'select');
    patrolType.dropDataUrl = "dic.patrol_type";
    formFieldArray.push(patrolType); /*系统带出*/
    formFieldArray.push(new FormElement(1, '检查编号', 'checkNumber', 'string', 'require', '50'));
    var checkTime = new FormElement(1, '检查时间', 'checkTime', 'string', 'require', '100','date');
    checkTime.value = getDate(getNowFormatDate());
//    console.log(checkTime.value);
    formFieldArray.push(checkTime);
    var urgency = new FormElement(1, '紧急程度', 'urgency', 'string', '1', '255', 'select');
    urgency.dropDataUrl = "dic.emergency_degree";
    formFieldArray.push(urgency);
    formFieldArray.push(new FormElement(1, '整改期限（天）', 'rectificationPeriod', 'number', '1', '100'));
    formFieldArray.push(new FormElement(1, '相关责任人', 'personLiable', 'string', '1', '50'));
    var checkPerson = new FormElement(1, '检查人员', 'checkPerson', 'string', '1', '50');
    checkPerson.value = $rootScope.userName;
   /* checkPerson.readonly = true;*/
    formFieldArray.push(checkPerson);
    var checkResults = new FormElement(1, '是否合格','checkResults', 'string', 0, '30', 'radio');
    checkResults.items = [{code:1,name:'合格'},{code:0,name:'不合格'}];
    checkResults.value = 0;
    formFieldArray.push(checkResults);
    formFieldArray.push(new FormElement(1, '检查内容', 'checkContent', 'string', '', '50','textarea'));
    formFieldArray.push(new FormElement(1, '存在问题', 'question', 'string', '', '255', 'textarea'));
    formFieldArray.push(new FormElement(1, '整改要求', 'rectificationRequirements', 'string', '', '255', 'textarea'));
    formFieldArray.push(new FormElement(1, '违规处罚', 'violationPenalty', 'string', '', '255', 'textarea'));
    formFieldArray.push(new FormElement(1, '处罚建议', 'punishmentSuggestion', 'string', '', '255', 'textarea'));
    var contractFileUUID = new FormElement(1,'上传附件','enclosure','string','','255','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/routingInspection";
 	formFieldArray.push(contractFileUUID);
 	
    editService.setFormFields($scope, formFieldArray);

    editService.init($scope, $http);
    $scope.isShow = true;
    if($scope.passParams){
    	$scope.isShow = false;
    	$scope.lookModel = true;
    	 var fieldMap = editService.getFieldMap($scope);
    	 for (var field in fieldMap.data) {  
    		 fieldMap.get(field).readonly = true; 
    		 
		 } 
    }

    /**
     * 加载编辑数据
     */
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);
    }

    /**
     * 修改
     **/
    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    }

    /**
     * 保存
     **/
    $scope.save = function() {
        editService.saveData($scope, "api/busiQualityRoutingInspection/add",homeUrl);
    }
});


/**
 * 查询整改明细
 */
angular.module('huatek.controllers').controller('BusiQualityRoutingInspectionDetailController', function ($scope,$rootScope, $location, $http,listService, editService, cacheService,httpService,$q) {
	var originType = $scope.passParams;
	var sourceType = typeof $scope.passParams.type == undefined ? '' : $scope.passParams.type;
	/**
	 * 初始化编辑界面. 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
	
	 	//定义块
	  var columnWrapArray = [];
	  columnWrapArray.push(new multipleColumn(1,'整改指令'));
	  $scope.columnWrapArray = columnWrapArray;
	  
		//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
	  var formFieldArray = [];
	    var orgId = new FormElement(1, '被通报单位', 'orgId', 'string', 'require', '50', 'tenderSelect');
	    $scope.lookModel = true;
	    /*orgId.value = $rootScope.orgId + '';*/
	    formFieldArray.push(orgId);
	    var orgName = new FormElement(1, '被通报单位', 'orgName', 'string', 'require', '50');
	    orgName.isShow = false;
	    orgId.lookValue = 'orgName';
	    formFieldArray.push(orgName);
	    var patrolType = new FormElement(1, '巡检类型', 'patrolType', 'string', 'require', '30', 'select');
	    patrolType.dropDataUrl = "dic.patrol_type";
	    formFieldArray.push(patrolType); /*系统带出*/
	    formFieldArray.push(new FormElement(1, '检查编号', 'checkNumber', 'string', 'require', '50'));
	    var checkTime = new FormElement(1, '检查时间', 'checkTime', 'string', 'require', '100','date');
	    checkTime.value = getDate(getNowFormatDate());
//	    console.log(checkTime.value);
	    formFieldArray.push(checkTime);
	    var urgency = new FormElement(1, '紧急程度', 'urgency', 'string', '1', '255', 'select');
	    urgency.dropDataUrl = "dic.emergency_degree";
	    formFieldArray.push(urgency);
	    formFieldArray.push(new FormElement(1, '整改期限（天）', 'rectificationPeriod', 'number', '1', '100'));
	    formFieldArray.push(new FormElement(1, '相关责任人', 'personLiable', 'string', '1', '50'));
	    var checkPerson = new FormElement(1, '检查人员', 'checkPerson', 'string', '1', '50');
	    checkPerson.value = $rootScope.userName;
	   /* checkPerson.readonly = true;*/
	    formFieldArray.push(checkPerson);
	    var checkResults = new FormElement(1, '是否合格','checkResults', 'string', 0, '30', 'radio');
	    checkResults.dropDataUrl = "dic.inspection_status";
	    formFieldArray.push(checkResults);
	    formFieldArray.push(new FormElement(1, '检查内容', 'checkContent', 'string', '', '50','textarea'));
	    formFieldArray.push(new FormElement(1, '存在问题', 'question', 'string', '', '255', 'textarea'));
	    formFieldArray.push(new FormElement(1, '整改要求', 'rectificationRequirements', 'string', '', '255', 'textarea'));
	    formFieldArray.push(new FormElement(1, '违规处罚', 'violationPenalty', 'string', '', '255', 'textarea'));
	    formFieldArray.push(new FormElement(1, '处罚建议', 'punishmentSuggestion', 'string', '', '255', 'textarea'));
	    var contractFileUUID = new FormElement(1,'上传附件','enclosure','string','','255','fileMultiple');
	    contractFileUUID.systemHeader = "/root/quality/routingInspection";
	 	formFieldArray.push(contractFileUUID);
	 	var id = new FormElement(1, '编号', 'id', 'string', '', '50');
	 	id.isShow = false;
	 	formFieldArray.push(id);
	 	var inspectionId = new FormElement(1, '整改单id', 'inspectionId', 'string', '0', '50');
	 	inspectionId.isShow =false;
	 	formFieldArray.push(inspectionId);
	 	
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
	  
	  
	  /* 获取整改单明细信息 */
	  var queryRectificationDetail = function(){
		  orgId.lookValue = orgName.value;
		  httpService.get($scope,'/api/busiRectificationDetail/findDetailRecordByRectId/'+inspectionId.value).success(function(data){
			  var type = cacheService.getData('dic.rectification_approve_type');
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
	  
	  /* 判断接口来源类型 */
	  var rectificationCode = '';
	  $scope.rectificationDetailList = [];
	  if(originType=='routing_inspection' && !sourceType){
		  editService.loadData($scope, '/api/busiQualityRoutingInspection/edit', $scope.editId,queryRectificationDetail);
	  }else if(sourceType){
		  editService.loadData($scope, '/api/busiQualityRoutingInspection/getRoutingByRectificationId', $scope.editId,queryRectificationDetail)
	  }else{
		  return;
	  }
});

/**
 * 巡检整改下发
 */
angular.module('huatek.controllers').controller('BusiQualityRoutingInspectionRectificationController', function ($scope, $location, $http, editService, cacheService,$rootScope,httpService) {
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
    columnWrapArray.push(new multipleColumn(1,'巡检信息'));
    if(!$scope.onlyView){
    	columnWrapArray.push(new multipleColumn(2,$scope.taskName));
 	}
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
    var formFieldArray = [];
    var orgId = new FormElement(1, '被通报单位', 'orgId', 'string', 'require', '50', 'tenderSelect');
    orgId.readonly = true;
    formFieldArray.push(orgId);
    var orgName = new FormElement(1, '被通报单位', 'orgName', 'string', 'require', '50');
    orgName.isShow = false;
    orgId.lookValue = 'orgName';
    formFieldArray.push(orgName);
    var patrolType = new FormElement(1, '巡检类型', 'patrolType', 'string', 'require', '30', 'select');
    patrolType.dropDataUrl = "dic.patrol_type";
    formFieldArray.push(patrolType); /*系统带出*/
    formFieldArray.push(new FormElement(1, '检查编号', 'checkNumber', 'string', 'require', '50'));
    var checkTime = new FormElement(1, '检查时间', 'checkTime', 'string', 'require', '100','date');
    checkTime.value = getDate(getNowFormatDate());
//	    console.log(checkTime.value);
    formFieldArray.push(checkTime);
    var urgency = new FormElement(1, '紧急程度', 'urgency', 'string', '1', '255', 'select');
    urgency.dropDataUrl = "dic.emergency_degree";
    formFieldArray.push(urgency);
    formFieldArray.push(new FormElement(1, '整改期限（天）', 'rectificationPeriod', 'number', '1', '100'));
    formFieldArray.push(new FormElement(1, '相关责任人', 'personLiable', 'string', '1', '50'));
    var checkPerson = new FormElement(1, '检查人员', 'checkPerson', 'string', '1', '50');
    checkPerson.value = $rootScope.userName;
   /* checkPerson.readonly = true;*/
    formFieldArray.push(checkPerson);
    var checkResults = new FormElement(1, '是否合格','checkResults', 'string', 0, '30', 'radio', null,null, null, 'dic.inspection_status');
    checkResults.value = false;
    formFieldArray.push(checkResults);
    formFieldArray.push(new FormElement(1, '检查内容', 'checkContent', 'string', '', '50','textarea'));
    formFieldArray.push(new FormElement(1, '存在问题', 'question', 'string', '', '255', 'textarea'));
    formFieldArray.push(new FormElement(1, '整改要求', 'rectificationRequirements', 'string', '', '255', 'textarea'));
    formFieldArray.push(new FormElement(1, '违规处罚', 'violationPenalty', 'string', '', '255', 'textarea'));
    formFieldArray.push(new FormElement(1, '处罚建议', 'punishmentSuggestion', 'string', '', '255', 'textarea'));
    var contractFileUUID = new FormElement(1,'上传附件','enclosure','string','','255','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/routingInspection";
 	formFieldArray.push(contractFileUUID);
    var rectificationId = new FormElement(1,'整改单id','id','string',0,'50');
    rectificationId.value = '';
    rectificationId.isShow = false;
    formFieldArray.push(rectificationId);
    /*var orgId = new FormElement(1,'机构id','orgId','string',0,'50');
    orgId.value = '';
    orgId.isShow = false;
    formFieldArray.push(orgId);*/
    var rectificationCode = new FormElement(1,'整改编号','rectificationCode','string',0,'50');
    rectificationCode.value = '';
    rectificationCode.isShow = false;
    formFieldArray.push(rectificationCode);
    
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
	var contractFileUUID = new FormElement(2,'附件','detailFileId','string',0,'500','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/rectificationFlow";
 	formFieldArray.push(contractFileUUID);

    var inspectionCode = new FormElement(2, '整改编号', 'inspectionCode', 'string', 0, '30');
    inspectionCode.isShow = false;
    formFieldArray.push(inspectionCode);
	
	for(var i=0;i<formFieldArray.length;i++){
		if(formFieldArray[i].column!=2){
			formFieldArray[i].readonly = true;
		}
	}
    
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
    
    
    /* 通过editId查询整改单对象 */
    var load = function() {
    	 httpService.get($scope,'/api/busiQualityRoutingInspection/getRoutingByRectificationId/'+$scope.editId).success(function(data){
    		 editService.loadData($scope, '/api/busiQualityRoutingInspection/edit',data.id, function(){
                myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode.value);
             });
    	 });
    }
    
    load();
    
    /**
     * 流程提交
     **/   
    $scope.submit = function(){
    	editService.saveData($scope, submitDataUrl+'/'+$scope.taskId+'/'+rectificationId.value);
    } 

    /**
     * 获取巡检维护审批明细数据
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

