'use strict';
/**
 * 月报管理列表页
 */
angular.module('huatek.controllers').controller('BusiQualityMonthReportMgrController', function($rootScope, $scope, $http, listService, httpService) {
    var _prefix = 'api/busiQualityMonthRportManagement/';

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '标段名称', field: 'orgName', width: '15%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewData","orgName")},
            { name: '文件名称', field: 'monthlyReportName', width: '15%', enableColumnMenu: false },
            { name: '文件编号', field: 'monthlyReportCode', width: '15%', enableColumnMenu: false },
            { name: '月报类型', field: 'monthlyReportType_', width: '15%', enableColumnMenu: false, decode: { field: "monthlyReportType", dataKey: "dic.report_month_inspect" } },
            { name: '填报人', field: 'writeReportUserName', width: '15%', enableColumnMenu: false },
            { name: '填报日期', field: 'writeReportTime', width: '15%', enableColumnMenu: false },
            { name: '审批状态', field: 'approvalStatus_', width: '10%', enableColumnMenu: false, decode: { field: "approvalStatus", dataKey: "dic.flow_status" },
            cellTemplate:listService.getLinkCellTmplate("viewProcess","approvalStatus_") }
        ]
    };
    /**
     * 初始化编辑界面. 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http);


    /** 定义查询页 */
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    /*queryParam(title, field, type, logic, value, dropDataUrl, isShow, event, display)*/
    /** 定义搜索框 */
    var orgId = new queryParam('标段名称', 'org.id', 'string', '=');
    /*listService.initQueryItems($scope, orgId, '/api/org/getFwOrgByType/' + $rootScope.orgType);*/
    orgId.componentType = SEARCH_COMPONENT.SELECT_TREE_SINGLE;
    // orgId.mapFiled = "id";
    queryPage.addParam(orgId);
    var monthlyReportName = new queryParam('文件名称', 'monthlyReportName', 'string', 'like');
    queryPage.addParam(monthlyReportName);
    var monthlyReportType = new queryParam('月报类型', 'monthlyReportType', 'string', '=', '', 'dic.report_month_inspect');
    monthlyReportType.componentType = 'select';
    queryPage.addParam(monthlyReportType);
    queryPage.addParam(new queryParam('文件编号', 'monthlyReportCode', 'string', 'like'));
    var writeReportTime = new queryParam('填报日期(开始)', 'writeReportTime', 'date', '>=');
    queryPage.addParam(writeReportTime);
    var writeReportTime_ = new queryParam('填报日期(结束)', 'writeReportTime', 'date', '<=');
    queryPage.addParam(writeReportTime_);
  /*  var writeReportTime_ = new queryParam('填报日期(结束)', 'writeReportTime_', 'date', '<=');
    queryPage.addParam(writeReportTime_);*/
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

    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/busiQualityMonthRportMgr/add', 'addData'));
    btnArray.push(new pageButton('修改', 'edit', '/busiQualityMonthRportMgr/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/busiQualityMonthRportMgr/delete', 'deleteData'));
    btnArray.push(new pageButton('申请', 'apply', '/busiQualityMonthRportMgr/flow', 'applyFlow'));
//    btnArray.push(new pageButton('查看流程', 'viewProcess', '/busiQualityMonthRportMgr/flow', 'viewProcess'));

    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup("新增", "/busiQualityMonthRportMgr/add", null, "1000", "600"));
        } else if (operation == 'editData') {
            if (isAllowOperation('该月报正在审批中，不可修改!', '该月报已审批，不可修改!')) {
                listService.editData($scope, $scope.gridApi, new popup("修改", "/busiQualityMonthRportMgr/edit",null, "1000", "600"));
            }
        } else if (operation == 'deleteData') {
            if (isAllowOperation('该月报正在审批中，不可删除!', '该月报已审批，不可删除!')) {
                listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, _prefix + 'delete');
            }
        } else if (operation == "applyFlow") {
            if (isAllowOperation('该月报正在审批中，不可重复操作申请!', '该月报已审批，不可再次操作申请!')) {
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
        } else if (operation == "viewProcess") {
            // listService.showProcessProgress($scope, "flowInstanceId");
            listService.showProcessProgressInColumn($scope, "flowInstanceId",param);
        }else if(operation == "viewData"){
        	listService.editData($scope, $scope.gridApi, new popup("查看", "/busiQualityMonthRportMgr/edit", true, "1000", "600"),param);
        }
        /*else if(operation == CUSTOMER_BTN.LOOK_DETAIL){
            listService.lookDetail($scope, param, new popup("查看", "/busiQualityMonthRportMgr/edit", null, "800", "600"));
        }*/
    }

    /**
     * 是否可以操作，消息提示
     */
    var isAllowOperation = function(tipMsg1, tipMsg2) {
        var selectedData = listService.getSelectData($scope.gridApi);
        if (selectedData != undefined) {
            if (selectedData.approvalStatus == 'flow_inapproval') {
                submitTips(tipMsg1, 'warning');
                return false;
            } else if (selectedData.approvalStatus == 'flow_approved' || selectedData.approvalStatus == 'flow_passed') {
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
 * 月报编辑页
 */
angular.module('huatek.controllers').controller('BusiQualityMonthReportEditController', function($scope, $http, $rootScope, editService, httpService) {
    var _prefix = 'api/busiQualityMonthRportManagement/';
    var addDataUrl = _prefix + 'add';
    var editDataUrl = _prefix + 'edit';
    
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '月报信息'));
    columnWrapArray.push(new multipleColumn(2, '月报内容'));
    columnWrapArray.push(new multipleColumn(3, '附件上传'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var orgId = new FormElement(1, '标段名称', 'orgId', 'string', 'require', '50', FORM_COMPONENT.SELECT_TREE_SINGLE, null, null);
    if(!$scope.editId){
    	orgId.value = $rootScope.orgId+'';
    }
    orgId.items = cloneObj($rootScope.reportUserOrg);
    formFieldArray.push(orgId);
    var orgName = new FormElement(1, '标段名称', 'orgName', 'string', 'require', '50');
    orgName.isShow = false;
    formFieldArray.push(orgName);
    formFieldArray.push(new FormElement(1, '填报日期', 'writeReportTime', 'string', 'require', '30', 'date', '', '', getDate(getNowFormatDate()))); /*系统带出*/
    formFieldArray.push(new FormElement(1, '文件名称', 'monthlyReportName', 'string', 'require', '50'));
    formFieldArray.push(new FormElement(1, '文件编号', 'monthlyReportCode', 'string', 'require', '100'));
    var monthlyReportType = new FormElement(1, '月报类型', 'monthlyReportType', 'string', 'require', '50', 'select');
    monthlyReportType.dropDataUrl = 'dic.report_month_inspect';
    formFieldArray.push(monthlyReportType);
    formFieldArray.push(new FormElement(2, '正文', 'content', 'string', 'require', '255', 'textarea'));
    var contractFileUUID = new FormElement(3, '附件', 'attachmentFile', 'string', '', '100', 'fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/monthRport";
    formFieldArray.push(contractFileUUID);

    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);
    
    $scope.isShow = true;
    if($scope.passParams){
    	$scope.isShow = false;
    	 var fieldMap = editService.getFieldMap($scope);
    	 for (var field in fieldMap.data) {  
    		 fieldMap.get(field).readonly = true; 
		 } 
    }
    /**
     * 获取当前登录人所有的月报类型
     */
    /* if($rootScope.orgType=='7'){ 机构类型：标段
        monthlyReportType.dropDataUrl = 'dic.report_month_inspect';
     }else if($rootScope.orgType=='6'){ 机构类型： 监理
        monthlyReportType.dropDataUrl = 'dic.report_month_project';
     }*/

    var loadSuccessBack = function(){
    	orgId.lookValue = orgName.value;
    }
    
    /**
     * 加载编辑数据
     */
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId,loadSuccessBack);
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
 * 月报审批页
 */
angular.module('huatek.controllers').controller('BusiQualityMonthReportFlowController', function($scope, $http, $rootScope, editService, httpService) {
    $scope.busiCode = $scope.passParams.busiCode;
    $scope.taskId = $scope.passParams.taskId;
    $scope.busiId = $scope.passParams.busiId;
    $scope.taskKey = $scope.passParams.taskKey;
    $scope.taskName = $scope.passParams.taskName;
    $scope.processInstanceId = $scope.passParams.processInstanceId;
    $scope.onlyView = $scope.passParams.onlyView;
    $scope.editId = $scope.busiId;

    var _prefix = 'api/busiQualityMonthRportManagement/';
    var editDataUrl = _prefix + 'approve';

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '月报信息'));
    columnWrapArray.push(new multipleColumn(2, '月报内容'));
    columnWrapArray.push(new multipleColumn(3, '附件上传'));
    if (!$scope.onlyView) {
        columnWrapArray.push(new multipleColumn(4, '审批信息'));
    }
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var orgId = new FormElement(1, '标段名称', 'orgName', 'string', 'require', '50');
    formFieldArray.push(orgId);
    formFieldArray.push(new FormElement(1, '填报日期', 'writeReportTime', 'string', 'require', '30')); /*系统带出*/
    formFieldArray.push(new FormElement(1, '文件名称', 'monthlyReportName', 'string', 'require', '50'));
    formFieldArray.push(new FormElement(1, '文件编号', 'monthlyReportCode', 'string', 'require', '100'));
    var monthlyReportType = new FormElement(1, '月报类型', 'monthlyReportType', 'string', 'require', '50', 'select');
    monthlyReportType.dropDataUrl = 'dic.report_month_inspect';
    formFieldArray.push(monthlyReportType);
    formFieldArray.push(new FormElement(2, '正文', 'content', 'string', 'require', '255', 'textarea'));
    var attachmentFile = new FormElement(3, '附件', 'attachmentFile', 'string', '', '100', 'fileMultiple');
    attachmentFile.systemHeader = SYSTEM_HEADER.BUSI_HEADER;
    formFieldArray.push(attachmentFile);

    var result = new FormElement(4, '审核', 'result', 'boolean', 1, '128', "radio", "resultChange");
    result.items = [{ code: "true", name: '同意' }, { code: "false", name: "驳回" }];
    /*result.value="true";*/
    formFieldArray.push(result);
    var resultMessage = new FormElement(4, '审核意见', 'resultMessage', 'string', 1, '1000', "textarea");
    /*resultMessage.value='同意';*/
    formFieldArray.push(resultMessage);
    formFieldArray.push(new FormElement(4, '意见模版', 'resultMessageTemplate', 'string', 0, '512', "select"));

    for (var i = 0; i < formFieldArray.length; i++) {
        if (formFieldArray[i].name != 'result' && formFieldArray[i].name != 'resultMessage' && formFieldArray[i].name != 'resultMessageTemplate') {
            formFieldArray[i].readonly = true;
        }
    }

    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);

    /**
     * 获取当前登录人所有的月报类型
     */
    /* if($rootScope.orgType=='7'){ 机构类型：标段
        monthlyReportType.dropDataUrl = 'dic.report_month_inspect';
     }else if($rootScope.orgType=='6'){ 机构类型： 监理
        monthlyReportType.dropDataUrl = 'dic.report_month_project';
     }*/

    /**
     * 加载编辑数据
     */
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);
    }

    /**
     * 流程提交
     **/
    $scope.submit = function() {
        editService.submitData($scope, WORKFLOW_COMMON_SUBMIT_URL);
    }
});