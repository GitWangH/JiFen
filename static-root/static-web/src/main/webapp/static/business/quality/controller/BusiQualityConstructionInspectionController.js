'use strict';
/**
 * 施工报检列表页
 */
angular.module('huatek.controllers').controller('BusiQualityConstructionInspectionController', function($rootScope, $scope, $http, listService, httpService) {
    var _prefix = 'api/constructionInspection/';

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        multiSelect: false,
        columnDefs: [
            { name: '标段名称', field: 'orgName', width: '14%', enableColumnMenu: false, cellTemplate: listService.getLinkCellTmplate("editViewData", "orgName") },
            /*{ name: '报检单名称', field: 'inspectionCompanyName', width: '15%', enableColumnMenu: false },*/
            { name: '分部分项', field: 'tendersBranchName', width: '25%', enableColumnMenu: false },
            { name: '检测段落', field: 'checkParagraph', width: '20%', enableColumnMenu: false },
            { name: '质量状态', field: 'qualityStatus_', width: '10%', enableColumnMenu: false, decode: { field: "qualityStatus", dataKey: "dic.construction_inspection_type" } },
            { name: '审批状态', field: 'approvalStatus_', width: '10%', enableColumnMenu: false, decode: { field: "approvalStatus", dataKey: "dic.construction_approval_status" }, cellTemplate: listService.getLinkCellTmplate("queryDetail", "approvalStatus_") },
            { name: '报检人', field: 'inspectionUserName', width: '10%', enableColumnMenu: false },
            { name: '报检日期', field: 'inspectionDate', width: '10%', enableColumnMenu: false },
        ]
    };
    /**
     * 初始化编辑界面. 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http);

    /** 定义查询页 */
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    var orgId = new queryParam('标段名称', 'org.id', 'string', '=');
    /*listService.initQueryItems($scope, orgId, '/api/org/getFwOrgByType/' + $rootScope.orgType);*/
    orgId.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
    /*orgId.mapFiled = "id";*/
    queryPage.addParam(orgId);
    /*var inspectionCompanyName = new queryParam('报检单名称', 'inspectionCompanyName', 'string', 'alllike');
    queryPage.addParam(inspectionCompanyName);*/
    var qualityStatus = new queryParam('质量状态', 'qualityStatus', 'string', '=', '', 'dic.construction_inspection_type');
    qualityStatus.componentType = 'select';
    queryPage.addParam(qualityStatus);
    var approvalStatus = new queryParam('审批状态', 'approvalStatus', 'string', '=', '', 'dic.construction_approval_status');
    approvalStatus.componentType = 'select';
    queryPage.addParam(approvalStatus);
    var inspectionDate = new queryParam('报检日期(始)', 'inspectionDate', 'date', '>=');
    queryPage.addParam(inspectionDate);
    var inspectionDate_ = new queryParam('报检日期(终)', 'inspectionDate', 'date', '<=');
    queryPage.addParam(inspectionDate_);

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
    btnArray.push(new pageButton('新增', 'add', '/busiQualityConsInspection/add', 'addData'));
    btnArray.push(new pageButton('修改', 'edit', '/busiQualityConsInspection/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/busiQualityConsInspection/delete', 'deleteData'));
    btnArray.push(new pageButton('申请', 'apply', '/busiQualityConsInspection/flow', 'applyFlow'));
    /*btnArray.push(new pageButton('查看', 'queryDetail', '/busiQualityConsInspection/queryDetail', 'queryDetail'));*/
    btnArray.push(new pageButton('查看流程', 'viewProcess', '/busiQualityConsInspection/flow', 'viewProcess'));

    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /*判断是否是系统管理员，系统管理员不可操作业务数据*/
    var isAdminFn = function() {
        if ($rootScope.tenantId) {
            return true;
        } else {
            submitTips('系统管理员不能操作业务数据！', 'warning');
            return false;
        }
    }

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {

        if (operation == 'addData') {
            if (isAdminFn()) {
                listService.addData($scope, new popup("新增", "/busiQualityConsInspection/add", null, "1200", "800"));
            }
        } else if (operation == 'editData') {
            if (isAdminFn()) {
                if (isAllowOperation('该报检正在审批中，不可修改!', '该报检已审批，不可修改!')) {
                    listService.editData($scope, $scope.gridApi, new popup("修改", "/busiQualityConsInspection/edit", null, "1200", "800"));
                }
            }
        } else if (operation == 'editViewData') {
            listService.editData($scope, $scope.gridApi, new popup("查看", "/busiQualityConsInspection/edit", true, "1200", "800"), param);
        } else if (operation == 'deleteData') {
            if (isAdminFn()) {
                if (isAllowOperation('该报检正在审批中，不可删除!', '该报检已审批，不可删除!')) {
                    listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, _prefix + 'delete');
                }
            }
        } else if (operation == "applyFlow") {
            if (isAdminFn()) {
                if (isAllowOperation('该报检正在审批中，不可重复操作申请!', '该报检已审批，不可再次操作申请!')) {
                    bootbox.confirm('确定申请吗?', function(isConfirm) {
                        if (isConfirm) {
                            var selectedData = listService.getSelectData($scope.gridApi);
                            $scope.promise = httpService.get($scope, _prefix + 'flow' + '/' + selectedData.id).success(function(response) {
                                load();
                            }).error(function() {
                                submitTips('流程启动失败!', 'error');
                            });
                        }
                    });
                }
            }
        } else if (operation == "viewProcess") {
            var selectedData = listService.getSelectData($scope.gridApi);
            if (selectedData.approvalStatus == '7' || selectedData.approvalStatus == '8') { /* 申请审批中 */
                listService.showProcessProgress($scope, "flowInstanceId");
            } else if (selectedData.approvalStatus == '4' || selectedData.approvalStatus == '5') { /* 整改中 */
                httpService.get($scope, '/api/busiQualityRectification/queryRectification/' + selectedData.inspectionCode).success(function(rectData) {
                    var pop = {
                        title: "流程进度",
                        passParams: {
                            canSetPerson: true,
                            processInstanceId: rectData.flowInstanceId
                        },
                        controller: 'processProgressController',
                        templateView: "static/business/workflow/process/templates/processProgress.html",

                    };
                    listService.popPanel($scope, pop);
                });
            }
        } else if (operation == "queryDetail") {
            /*var selectedData = listService.getSelectData($scope.gridApi);*/
            if (param.approvalStatus == '5' || param.approvalStatus == '4') {
                /*var paramMap = { 'originalType': 'construction_inspection', 'inspectionCode': param.inspectionCode };*/
                listService.editData($scope, $scope.gridApi, new popup("查看整改单", "/busiQualityConsInspection/flow", null, "1200", "800"), param);
            } else if (param.approvalStatus == '7' || param.approvalStatus == '8') {
                listService.editData($scope, $scope.gridApi, new popup("查看申请信息", "/busiQualityConsInspection/queryDetail", param, "1200", "800"), param);
            }
        }
    }

    /**
     * 是否可以操作，消息提示
     */
    var isAllowOperation = function(tipMsg1, tipMsg2) {
        var selectedData = listService.getSelectData($scope.gridApi);
        if (selectedData != undefined) {
            if (selectedData.approvalStatus == '1' || selectedData.approvalStatus == '4' || selectedData.approvalStatus == '7') {
                submitTips(tipMsg1, 'warning');
                return false;
            } else if (selectedData.approvalStatus == '2' || selectedData.approvalStatus == '5' || selectedData.approvalStatus == '8') {
                submitTips(tipMsg2, 'warning');
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, _prefix + 'query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

});
/**
 * 编辑页
 */
angular.module('huatek.controllers').controller('BusiQualityConstructionInspectionEditController', function($scope, $http, $rootScope, editService, httpService, listService) {
    var _prefix = 'api/constructionInspection/';
    var addDataUrl = _prefix + 'add';
    var editDataUrl = _prefix + 'edit';

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '报检信息'));
    columnWrapArray.push(new multipleColumn(2, '报检内容'));
    columnWrapArray.push(new multipleColumn(3, '附件上传'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    /*var orgId = new FormElement(1,'标段名称','orgId','string','require','50','select');
    editService.initFieldItems($scope, orgId,'api/org/getFwOrgByType/'+$rootScope.orgType);*/
    /**初始化表单选择数据，包括字典数据，其他可选项数据.**/
    /*
        orgId.value = $rootScope.orgId;*/
    var orgId = new FormElement(1, '标段名称', 'orgId', 'string', 'require', '50', FORM_COMPONENT.TENDERS_SLECT);
    orgId.lookValue = "orgName";
    if(!$scope.editId){
    	orgId.value = $rootScope.orgId + '';
    }
    formFieldArray.push(orgId);
    /*formFieldArray.push(new FormElement(1,'报检单名称','inspectionCompanyName','string','require','50'));*/
    formFieldArray.push(new FormElement(1,'开工日期','beginConstructionDate','string','require','30','date','','',getDate(getNowFormatDate())));/*系统带出*/
    formFieldArray.push(new FormElement(1,'报检日期','inspectionDate','string','require','30','date','','',getDate(getNowFormatDate())));
   /* var beginConstructionDate = new FormElement(1,'开工日期','beginConstructionDate','string','require','30','date');
    beginConstructionDate.value = getDate(getNowFormatDate());系统带出
    formFieldArray.push(beginConstructionDate);*/
    /*var tendersBranchId = new FormElement(1,'分部分项','tendersBranchId','string','require','50','select');
    tendersBranchId.items = [{_showField:'基础数据-100-01',_returnField:'93'}];*/
    var tendersBranchId = new FormElement(1, '分部分项', 'tendersBranchId', 'string', 'require', '50', '');
    tendersBranchId.isShow = false;
    formFieldArray.push(tendersBranchId);

    var isCompleted = new FormElement(1, '', 'isCompleted', 'string', 'require', '50', 'checkbox');
    isCompleted.content = '该部位已完成';
    formFieldArray.push(isCompleted);
    formFieldArray.push(new FormElement(1, '报检人员', 'inspectionUserName', 'string', 'require', '50', '', '', '', $rootScope.userName));
    var tendersBranch = new FormElement(1, '分部分项名称', 'tendersBranchName', 'string', 'require', '500', 'longInput');
    tendersBranch.readonly = true;
    formFieldArray.push(tendersBranch);
    var checkParagraph = new FormElement(1,'检测段落','checkParagraph','string',1,'50');
    formFieldArray.push(checkParagraph);/* 由分部分项带出桩号 */
    formFieldArray.push(new FormElement(2,'报检内容','inspectionContent','string','require','255','textarea'));
    var contractFileUUID = new FormElement(3,'附件','attachmentFile','string','','100','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/constructInspection";
    formFieldArray.push(contractFileUUID);

    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);

    $scope.isShow = true;
    if ($scope.passParams) {
        $scope.lookModel = true;
        $scope.isShow = false;
        var fieldMap = editService.getFieldMap($scope);
        for (var field in fieldMap.data) {
            fieldMap.get(field).readonly = true;

        }
    }

    /*
     * 是否完成change事件
     */
    /*isCompleted.event = function(val){
    	console.log(val)
    }*/

    /**
     * 加载项目分部分项
     */
    tendersBranch.clickEvent = function() {
        listService.popPanel($scope, 
        		new popup("项目分部分项", "/busiQualityConsInspection/createQuantitiesBill",
        				{ 'orgId': orgId, 'tendersBranchId': tendersBranchId, 'tendersBranch': tendersBranch, 'checkParagraph': checkParagraph, huatekTree: $scope.huatekTree },
        				"900", "600",null,'BusiQualitySelectTendersBranchController','static/business/quality/templates/template_list_edit_treeGrid.html','modal'));
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
        editService.updateData($scope, editDataUrl, null, $scope.editId);
    }

    /**
     * 保存
     **/
    $scope.save = function() {
        editService.saveData($scope, addDataUrl, null);
    }

});

/**
 * 施工报检申请流程查看
 */
angular.module('huatek.controllers').controller('BusiQualityConstructionInspectionApplyController', function($scope, $http, $rootScope, editService, httpService, cacheService) {
    var _prefix = 'api/constructionInspection/';
    var editDataUrl = _prefix + 'edit';
    var _detailPrefix = 'api/constructionInspectionPassRecord/';
    var queryDetailUrl = _detailPrefix + 'getConstrucationInspectionPassRecord';
    if($scope.passParams.busiCode){
    	$scope.busiCode = $scope.passParams.busiCode;
    	$scope.taskId = $scope.passParams.taskId;
    	$scope.busiId = $scope.passParams.busiId;
    	$scope.taskKey = $scope.passParams.taskKey;
    	$scope.taskName = $scope.passParams.taskName;
    	$scope.processInstanceId = $scope.passParams.processInstanceId;
    	$scope.onlyView = $scope.passParams.onlyView;
    	if($scope.busiId){
    		$scope.editId = $scope.busiId;
    	}
    }else {
    	$scope.editId = $scope.passParams.id;
    }


    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '报检信息'));
    columnWrapArray.push(new multipleColumn(2, '报检内容'));
    columnWrapArray.push(new multipleColumn(3, '附件上传'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var orgId = new FormElement(1, '标段名称', 'orgId', 'string', 'require', '50', FORM_COMPONENT.TENDERS_SLECT);
    orgId.lookValue = "orgName";
    $scope.lookModel = true;
    /*editService.initFieldItems($scope, orgId,'api/org/getFwOrgByType/'+$rootScope.orgType);
    orgId.value = $rootScope.orgId;*/
    formFieldArray.push(orgId);
    /*formFieldArray.push(new FormElement(1,'报检单名称','inspectionCompanyName','string','require','50'));*/
    formFieldArray.push(new FormElement(1,'开工日期','beginConstructionDate','string','require','30','date','','',getDate(getNowFormatDate())));/*系统带出*/
    formFieldArray.push(new FormElement(1,'报检日期','inspectionDate','string','require','30','date','','',getDate(getNowFormatDate())));
    var tendersBranchId = new FormElement(1,'分部分项','tendersBranchId','string','require','50','');
    tendersBranchId.isShow =false;
    formFieldArray.push(tendersBranchId);
    var tendersBranch = new FormElement(1, '分部分项名称', 'tendersBranchName', 'string', 'require', '500', 'longInput');
    formFieldArray.push(tendersBranch);
    formFieldArray.push(new FormElement(1,'检测段落','checkParagraph','string','require','200'));/* 由分部分项带出桩号 */
    var isCompleted = new FormElement(1, '', 'isCompleted', 'string', 'require', '50', 'checkbox');
    isCompleted.content = '该部位已完成';
    formFieldArray.push(isCompleted);
    formFieldArray.push(new FormElement(1,'报检人员','inspectionUserName','string','require','50','','','',$rootScope.userName));
    formFieldArray.push(new FormElement(2,'报检内容','inspectionContent','string','require','255','textarea'));
    var contractFileUUID = new FormElement(3,'附件','attachmentFile','string','','100','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/constructInspection";
    formFieldArray.push(contractFileUUID);
    var cid = new FormElement(4, '施工报检id', 'id', 'string', '', '100');
    cid.isShow = false;
    formFieldArray.push(cid);
    var rectCode = new FormElement(1,'分部分项','inspectionCode','string','0','50');
    rectCode.isShow = false;
    formFieldArray.push(rectCode);
    

    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);

    for (var i = 0; i < formFieldArray.length; i++) {
        formFieldArray[i].readonly = true;
    }

    var loadSuccessCallBack = function() {
        httpService.get($scope, queryDetailUrl + '/' + $scope.editId).success(function(response) {
            var type = cacheService.getData('dic.rectification_approve_type');
            for (var i in response) {
                for (var j in type) {
                    if (response[i].flowResult == type[j].code) {
                        response[i].flowResult = type[j].name;
                        break;
                    }
                }
            }
            $scope.constructionDetailList = response;
            
        });
    }

    /**
     * 加载编辑数据
     */
    var load = function() {
        editService.loadData($scope, editDataUrl, $scope.editId, loadSuccessCallBack);
    }

    load();


});

/**
 * 施工报检列表查看整改单相关页面
 */
angular.module('huatek.controllers').controller('BusiQualityConstructionInspectionFlowController', function($scope, $http, $rootScope, editService, httpService, cacheService) {
    var _prefix = 'api/constructionInspection/';
    var approveDataUrl = _prefix + 'conInspectApprove';
    var editDataUrl = _prefix + 'edit';
    $scope.onlyView = true;
    var rectificationCode = '';
    console.log($scope.passParams)
    if($scope.passParams){
    	rectificationCode = typeof $scope.passParams.code == undefined ? '' : $scope.passParams.code;
    }
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn1(1, '报检信息'));
    columnWrapArray.push(new multipleColumn1(2, '审批', !$scope.onlyView));
    var rectColumn = new multipleColumn1(3, '整改通知单');
    columnWrapArray.push(rectColumn);

    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var orgId1 = new FormElement(1, '标段名称', 'orgId1', 'string', 0, '50', FORM_COMPONENT.TENDERS_SLECT);
    orgId1.lookValue = 
    orgId1.readonly = true;
    formFieldArray.push(orgId1);
    /*formFieldArray.push(new FormElement(1,'报检单名称','inspectionCompanyName','string','','30'));*/
    formFieldArray.push(new FormElement(1,'开工日期','beginConstructionDate','string','','30','date'));
    formFieldArray.push(new FormElement(1,'报检日期','inspectionDate','string','','30','date'));
    var isCompleted = new FormElement(1, '', 'isCompleted', 'string', '', '50', 'checkbox');
    isCompleted.content = '该部位已完成';
    formFieldArray.push(isCompleted);
    var tendersBranchId = new FormElement(1,'分部分项','tendersBranchId','string','','50','');
    tendersBranchId.isShow =false;
    formFieldArray.push(tendersBranchId);
    var tendersBranch = new FormElement(1, '分部分项名称', 'tendersBranchName', 'string', 0, '500', 'longInput');
    formFieldArray.push(tendersBranch);
    formFieldArray.push(new FormElement(1,'检测段落','checkParagraph','string',0,'200'));
    formFieldArray.push(new FormElement(1,'报检人员','inspectionUserName','string',0,'50'));
    formFieldArray.push(new FormElement(1,'报检内容','inspectionContent','string',0,'255','textarea'));
    var contractFileUUID = new FormElement(1,'附件','attachmentFile','string','','100','fileMultiple');
 	formFieldArray.push(contractFileUUID);
	var result=new FormElement(2,'是否合格','result','boolean',1,'128',"radio","resultChange");
	result.items=[{code:"true",name:'合格'},{code:"false",name:"不合格"}];
	formFieldArray.push(result);
	var resultMessage=new FormElement(2,'备注','resultMessage','string',1,'1000',"textarea");
	formFieldArray.push(resultMessage);
	var passRecordFileId = new FormElement(2,'附件','passRecordFileId','string','','100','fileMultiple');
	passRecordFileId.systemHeader = "/root/quality/constructionApply";
 	formFieldArray.push(passRecordFileId);
 	var flowStep=new FormElement(2,'流程步骤','flowStep','string',0,'1000');
 	flowStep.value = $scope.taskKey;
 	flowStep.isShow = false;
	formFieldArray.push(flowStep);
	var flowStepName=new FormElement(2,'流程步骤名称','flowStepName','string',0,'1000');
	flowStepName.value = $scope.taskName;
	flowStepName.isShow = false;
	formFieldArray.push(flowStepName);


    var orgId = new FormElement(3, '被通知单位', 'orgId', 'string', '', '50', FORM_COMPONENT.TENDERS_SLECT);
    /*orgId.value = orgId;*/
    formFieldArray.push(orgId);
    var orgName = new FormElement(3, '被通知单位名称', 'orgName', 'string', 0, '30');
    orgName.isShow =false;
    formFieldArray.push(orgName);
    var checkNo = new FormElement(3, '检查编号', 'checkNo', 'string', 0, '30');
    formFieldArray.push(checkNo);
    var rectificationCheckTime = new FormElement(3, '检查时间', 'rectificationCheckTime', 'string', 0, '30', 'date', '', '', getDate(getNowFormatDate()));
    formFieldArray.push(rectificationCheckTime);
    var rectificationPersonLiable = new FormElement(3, '相关责任人', 'rectificationPersonLiable', 'string', 0, '30');
    formFieldArray.push(rectificationPersonLiable);
    var rectificationUrgency = new FormElement(3, '紧急程度', 'rectificationUrgency', 'string', 0, '30', 'select', '', '', '', 'dic.emergency_degree');
    formFieldArray.push(rectificationUrgency);
    var rectificationPeriod = new FormElement(3, '整改期限(天)', 'rectificationPeriod', 'number', 0, '30');
    formFieldArray.push(rectificationPeriod);
    var rectificationPerson = new FormElement(3, '检查人员', 'rectificationPerson', 'string', 0, '30', null, '', '', $rootScope.userName);
    formFieldArray.push(rectificationPerson);
    var rectificationCheckContent = new FormElement(3, '检查内容', 'rectificationCheckContent', 'string', 0, '500', 'textarea');
    formFieldArray.push(rectificationCheckContent);
    var rectificationExistingProblems = new FormElement(3, '存在问题', 'rectificationExistingProblems', 'string', 0, '500', 'textarea');
    formFieldArray.push(rectificationExistingProblems);
    var rectificationRequirements = new FormElement(3, '整改要求', 'rectificationRequirements', 'string', 0, '500', 'textarea');
    formFieldArray.push(rectificationRequirements);
    var rectificationPunishmentSuggestion = new FormElement(3, '处罚建议', 'rectificationPunishmentSuggestion', 'string', 0, '500', 'textarea');
    formFieldArray.push(rectificationPunishmentSuggestion);
    var contractFileUUID = new FormElement(3, '附件', 'fileId', 'string', 0, '200', 'fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/constructInspection";
    /*contractFileUUID.systemHeader = "/root/quality/rawMaterial/rawMaterial";*/
    formFieldArray.push(contractFileUUID);

    var inspectionCode = new FormElement(3, '整改编号', 'inspectionCode', 'string', 0, '30');
    inspectionCode.isShow = false;
    formFieldArray.push(inspectionCode);

    for (var i = 0; i < formFieldArray.length; i++) {
       formFieldArray[i].readonly = true;
    }

    $scope.isOK = false;
    /**
     * 是否合格 change 事件
     */
    $scope.resultChange = function() {
        if (result.value == 'true') { /* true */
            $scope.isOK = false;
            rectColumn.isShow = false;
            resultMessage.isShow = true;
            resultMessage.require = true;
            passRecordFileId.isShow = true;
        } else {
            rectColumn.isShow = true;
            $scope.isOK = true;
            orgId.readonly = true;
            for (var i = 0; i < formFieldArray.length; i++) {
                if (formFieldArray[i].column === 3) {
                    formFieldArray[i].isShow = true;
                    resultMessage.isShow = false;
                    resultMessage.require = false;
                    passRecordFileId.isShow = false;
                    /*施工报检选择不合格时, 检查内容/存在问题/整改要求/处罚建议应为非必填*/
                    if (formFieldArray[i].name != 'fileId' && formFieldArray[i].name != 'rectificationCheckContent' &&  
                        formFieldArray[i].name != 'rectificationExistingProblems' &&  formFieldArray[i].name != 'rectificationRequirements' && 
                         formFieldArray[i].name != 'rectificationPunishmentSuggestion') {
                        formFieldArray[i].require = true;
                    }
                }
            }
            inspectionCode.isShow = false;
        }
    }

    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);

    var loadSuccessCallback = function() {
        orgId1.lookValue = orgId.lookValue = orgName.value;
        if(inspectionCode.value){
        	myLoadData('/api/busiQualityRectification/queryRectification/'+inspectionCode.value);
        }
    }

    /**
     * 加载编辑数据
     */
    if ($scope.editId) {
    	if(!rectificationCode){
    		editService.loadData($scope, editDataUrl, $scope.editId, loadSuccessCallback);
    	}
    }

    /**
     * 流程提交
     **/
    $scope.submit = function() {
        editService.submitData($scope, approveDataUrl);
    }


    /**
     * 获取施工报检审批明细数据
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

      /**
       * 根据不同的整改类型加载数据
       *
       * @param      {<type>}  url     The url
       */
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

/**
 * 施工报详情页
 */
angular.module('huatek.controllers').controller('BusiQualityConstructionInspectionDetailController', function($scope, $http, $rootScope, editService, httpService, cacheService) {
    
      /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '报检信息'));
    columnWrapArray.push(new multipleColumn(2, '报检内容'));
    columnWrapArray.push(new multipleColumn(3, '附件上传'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var id = new FormElement(1, 'id', 'id', 'string', 'require', '50', FORM_COMPONENT.TENDERS_SLECT);
    id.isShow = false;
    formFieldArray.push(id);
    var orgId = new FormElement(1, '标段名称', 'orgId', 'string', 'require', '50', FORM_COMPONENT.TENDERS_SLECT);
    orgId.lookValue = "orgName";
    if(!$scope.editId){
        orgId.value = $rootScope.orgId + '';
    }
    formFieldArray.push(orgId);
    formFieldArray.push(new FormElement(1,'开工日期','beginConstructionDate','string','require','30','date','','',getDate(getNowFormatDate())));/*系统带出*/
    formFieldArray.push(new FormElement(1,'报检日期','inspectionDate','string','require','30','date','','',getDate(getNowFormatDate())));
    var tendersBranchId = new FormElement(1, '分部分项', 'tendersBranchId', 'string', 'require', '50', '');
    tendersBranchId.isShow = false;
    formFieldArray.push(tendersBranchId);
    var isCompleted = new FormElement(1, '', 'isCompleted', 'string', 'require', '50', 'checkbox');
    isCompleted.content = '该部位已完成';
    formFieldArray.push(isCompleted);
    formFieldArray.push(new FormElement(1, '报检人员', 'inspectionUserName', 'string', 'require', '50', '', '', '', $rootScope.userName));
    var tendersBranch = new FormElement(1, '分部分项名称', 'tendersBranchName', 'string', 'require', '500', 'longInput');
    tendersBranch.readonly = true;
    formFieldArray.push(tendersBranch);
    var checkParagraph = new FormElement(1,'检测段落','checkParagraph','string',1,'50');
    formFieldArray.push(checkParagraph);/* 由分部分项带出桩号 */
    formFieldArray.push(new FormElement(2,'报检内容','inspectionContent','string','require','255','textarea'));
    var contractFileUUID = new FormElement(3,'附件','attachmentFile','string','','100','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/constructInspection";
    formFieldArray.push(contractFileUUID);

    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);
        
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
          httpService.get($scope,'/api/busiRectificationDetail/findDetailRecordByRectId/'+id.value).success(function(data){
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
      editService.loadData($scope, '/api/constructionInspection/edit', $scope.editId,queryRectificationDetail);
});


/**
 * 施工报检整改下发
 */
angular.module('huatek.controllers').controller('BusiQualityConsInsFlowController', function ($scope, $location, $http, editService, cacheService,$rootScope,httpService) {
	var sourceType = '';
	if($scope.passParams){
		sourceType = typeof $scope.passParams.type == undefined ? '' : $scope.passParams.type;
	}
	
	if(!sourceType){
		$scope.busiCode=$scope.passParams.busiCode;
		$scope.taskId=$scope.passParams.taskId;
		$scope.busiId=$scope.passParams.busiId;
		$scope.taskKey=$scope.passParams.taskKey;
		$scope.taskName=$scope.passParams.taskName;
		$scope.processInstanceId=$scope.passParams.processInstanceId;
		$scope.onlyView=$scope.passParams.onlyView;
		$scope.editId=$scope.busiId;
	}else{
		$scope.onlyView = true;
	}
	
    var _prefix = '/api/constructionInspection/';
    var editDataUrl = _prefix+'edit';
    var submitDataUrl = _prefix+"add";

    var tableGridContent = [];
    $scope.tableGrid = {};
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'报检信息'));
    columnWrapArray.push(new multipleColumn1(3, '整改通知单', true));
    if(!$scope.onlyView){
        columnWrapArray.push(new multipleColumn(4,$scope.taskName));
    }
    if(!$scope.onlyView){
    	columnWrapArray.push(new multipleColumn1(2, '审批', true));
    }
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    /*var orgId = new FormElement(1,'标段名称','orgId','string','require','50','select');
    editService.initFieldItems($scope, orgId,'api/org/getFwOrgByType/'+$rootScope.orgType);*/
    /**初始化表单选择数据，包括字典数据，其他可选项数据.**/
    /*
        orgId.value = $rootScope.orgId;*/
    /*var id = new FormElement(1, 'id', 'id', 'string', 'require', '50', FORM_COMPONENT.TENDERS_SLECT);
    id.isShow = false;
    formFieldArray.push(id);*/
    var orgId = new FormElement(1, '标段名称', 'orgId', 'string', 'require', '50', FORM_COMPONENT.TENDERS_SLECT);
    orgId.lookValue = 'orgName';
    if(!$scope.editId){
        orgId.value = $rootScope.orgId + '';
    }
    formFieldArray.push(orgId);
    formFieldArray.push(new FormElement(1,'开工日期','beginConstructionDate','string','require','30','date','','',getDate(getNowFormatDate())));/*系统带出*/
    formFieldArray.push(new FormElement(1,'报检日期','inspectionDate','string','require','30','date','','',getDate(getNowFormatDate())));
    var tendersBranchId = new FormElement(1, '分部分项', 'tendersBranchId', 'string', 'require', '50', '');
    tendersBranchId.isShow = false;
    formFieldArray.push(tendersBranchId);

    var isCompleted = new FormElement(1, '', 'isCompleted', 'string', 'require', '50', 'checkbox');
    isCompleted.content = '该部位已完成';
    formFieldArray.push(isCompleted);
    formFieldArray.push(new FormElement(1, '报检人员', 'inspectionUserName', 'string', 'require', '50', '', '', '', $rootScope.userName));
    var tendersBranch = new FormElement(1, '分部分项名称', 'tendersBranchName', 'string', 'require', '500', 'longInput');
    tendersBranch.readonly = true;
    formFieldArray.push(tendersBranch); 
    var checkParagraph = new FormElement(1,'检测段落','checkParagraph','string',1,'50');
    formFieldArray.push(checkParagraph);/* 由分部分项带出桩号 */
    formFieldArray.push(new FormElement(1,'报检内容','inspectionContent','string','require','255','textarea'));
    var contractFileUUID = new FormElement(1,'附件','attachmentFile','string','','100','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/constructInspection";
    formFieldArray.push(contractFileUUID);
    var rectificationId = new FormElement(1,'整改单id','id','string',0,'50');
    rectificationId.value = '';
    rectificationId.isShow = false;
    formFieldArray.push(rectificationId);


    var orgId1 = new FormElement(3, '被通知单位', 'orgId1', 'string', '', '50', FORM_COMPONENT.TENDERS_SLECT);
   /* orgId1.isShow = false;*/
    /*orgId1.lookValue = "orgName";*/
    orgId1.value = orgId.value;
    formFieldArray.push(orgId1);
    var checkNo = new FormElement(3, '检查编号', 'checkNo', 'string', 0, '30');
    checkNo.isShow = false;
    formFieldArray.push(checkNo);
    var rectificationCheckTime = new FormElement(3, '检查时间', 'rectificationCheckTime', 'string', 0, '30', 'date', '', '', getDate(getNowFormatDate()));
    /*rectificationCheckTime.isShow = false;*/
    formFieldArray.push(rectificationCheckTime);
    var rectificationPersonLiable = new FormElement(3, '相关责任人', 'rectificationPersonLiable', 'string', 0, '30');
    /*rectificationPersonLiable.isShow = false;*/
    formFieldArray.push(rectificationPersonLiable);
    var rectificationUrgency = new FormElement(3, '紧急程度', 'rectificationUrgency', 'string', 0, '30', 'select', '', '', '', 'dic.emergency_degree');
    /*rectificationUrgency.isShow = false;*/
    formFieldArray.push(rectificationUrgency);
    var rectificationPeriod = new FormElement(3, '整改期限(天)', 'rectificationPeriod', 'number', 0, '30');
    /*rectificationPeriod.isShow = false;*/
    formFieldArray.push(rectificationPeriod);
    var rectificationPerson = new FormElement(3, '检查人员', 'rectificationPerson', 'string', 0, '30', null, '', '', $rootScope.userName);
    /*rectificationPerson.isShow = false;*/
    formFieldArray.push(rectificationPerson);
    var rectificationCheckContent = new FormElement(3, '检查内容', 'rectificationCheckContent', 'string', 0, '500', 'textarea');
    /*rectificationCheckContent.isShow = false;*/
    formFieldArray.push(rectificationCheckContent);
    var rectificationExistingProblems = new FormElement(3, '存在问题', 'rectificationExistingProblems', 'string', 0, '500', 'textarea');
    /*rectificationExistingProblems.isShow = false;*/
    formFieldArray.push(rectificationExistingProblems);
    var rectificationRequirements = new FormElement(3, '整改要求', 'rectificationRequirements', 'string', 0, '500', 'textarea');
    /*rectificationRequirements.isShow = false;*/
    formFieldArray.push(rectificationRequirements);
    var rectificationPunishmentSuggestion = new FormElement(3, '处罚建议', 'rectificationPunishmentSuggestion', 'string', 0, '500', 'textarea');
    /*rectificationPunishmentSuggestion.isShow = false;*/
    formFieldArray.push(rectificationPunishmentSuggestion);
    var contractFileUUID = new FormElement(3, '附件', 'fileId', 'string', 0, '200', 'fileMultiple');
    /*contractFileUUID.isShow = false;*/
    contractFileUUID.systemHeader = "/root/quality/constructInspection";
    /*contractFileUUID.systemHeader = "/root/quality/rawMaterial/rawMaterial";*/
    formFieldArray.push(contractFileUUID);

    var inspectionCode = new FormElement(3, '整改编号', 'rectificationCode', 'string', 0, '30');
    inspectionCode.isShow = false;
    formFieldArray.push(inspectionCode);

    if(!$scope.onlyView){
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
    }
    
    var rectificationDescription=new FormElement(2,'处理描述','rectificationDescription','string',1,'1000',"textarea");
    formFieldArray.push(rectificationDescription);
    /*formFieldArray.push(new FormElement(2,'附件','detailFileId','string',0,'500','fileMultiple'));*/
    var contractFileUUID = new FormElement(2,'附件','detailFileId','string',0,'500','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/rectificationFlow";
    formFieldArray.push(contractFileUUID);

    editService.init($scope);

    for(var i=0;i<formFieldArray.length;i++){
        if(formFieldArray[i].column!=2){
            formFieldArray[i].readonly = true;
        }
    }
    editService.setFormFields($scope, formFieldArray);
    
    /*标段整改默认通过*/
    if(!$scope.onlyView){
    	if($scope.taskKey=='task1' || $scope.taskKey=='task4' || $scope.taskName=='标段整改'){
    		detailFlowResult.value = "true";
    		detailFlowResult.isShow = false;
    	}
    }
     
    /*显示整改单*/
    $scope.isOK = true;

    /* 通过editId查询整改单对象 */
    var load = function() {
        /*根据整改单ID获取整改单*/
         httpService.get($scope,'/api/busiQualityRectification/edit/'+$scope.editId).success(function(data){
             /*根据整改单编号获取施工报检信息*/
             editService.loadData($scope, '/api/constructionInspection/getConInspectionByInsCodeData', data.rectificationCode, function(){
                myLoadData('/api/busiQualityRectification/queryRectification/'+data.rectificationCode);
                orgId1.value = orgId.value;
                orgId1.lookValue = orgId.lookValue;
                orgId1.readonly = true;
                orgId.readonly = true;
             });
         });
    }
    
    load();
    
    /**
     * 流程提交
     **/   
    $scope.submit = function(){
        editService.saveData($scope, 'api/busiRectificationDetail/add/'+$scope.taskId+'/'+rectificationId.value);
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
      var myLoadData = function( url){
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

