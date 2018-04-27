'use strict';
/** [变更管理]**/
angular.module('huatek.controllers').controller('ChangeManageController', function($rootScope, $scope, $http, listService, httpService) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: false,
        enableSelectAll: false,
        multiSelect: false,
        /*lookDetailConfig: { menuKey: '/changeManage/edit', name: '变更合同查看' },*/
        columnDefs: [
            { name: '标段名称', field: 'orgName', width: '15%', enableColumnMenu: false },
            { name: '变更令号', field: 'changeOrderNo', width: '10%', enableColumnMenu: false },
            { name: '变更项目', field: 'changeProjectName', width: '10%', enableColumnMenu: false /*, cellTemplate : '<a ng-click="grid.appScope.viewContractInfo(row.entity)">{{row.entity.contractCode}}</a>'*/ },
            { name: '变更金额', field: 'changeMoney', width: '10%', enableColumnMenu: false, decode: { field: "technicalLevel", dataKey: "dic.highway_grade" }, cellFilter: "priceFilter" },
            { name: '变更日期', field: 'changeData', width: '10%', enableColumnMenu: false },
            { name: '是否批复', field: 'replyChangeStatusName', width: '10%', enableColumnMenu: false, decode: { field: "replyChangeStatus", dataKey: "dic.reply_change_status" } },
            { name: '审批状态', field: 'approvalStatusName', width: '10%', enableColumnMenu: false, decode: { field: "approvalStatus", dataKey: "dic.flow_status" }, cellTemplate: listService.getLinkCellTmplate("viewProcess", "approvalStatusName") /*,cellTemplate:returnApprovalStatusColor()*/ },
            { name: '创建人', field: 'createrName', width: '10%', enableColumnMenu: false },
            { name: '创建时间', field: 'createTime', width: '*', enableColumnMenu: false }
        ]
    };
    /**
     * 初始化编辑界面. 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http, $scope.tableGrid);

    // 定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    // 定义搜索框

    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', 'in');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_MULTIPLE_SLECT;
    queryPage.addParam(tendersName);
    queryPage.addParam(new queryParam('变更令号', 'changeOrderNo', 'string', 'alllike'));
    var approvalStatus = new queryParam('审批状态', 'approvalStatus', 'string', '=', '', 'dic.flow_status');
    approvalStatus.componentType = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(approvalStatus);
    queryPage.addParam(new queryParam('变更日期', 'changeData', 'date', '>='));
    queryPage.addParam(new queryParam('--', 'changeData', 'date', '<='));
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

        $scope.gridApi.selection.on.rowSelectionChanged($scope, function(row, event) {
            /*isSelected*/
            console.log(row);
            if (row) {
               console.log(123)
            }
        });
    };

    // 定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/changeManage/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/changeManage/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/changeManage/delete', 'deleteData'));
    btnArray.push(new pageButton('申请', 'apply', '/changeManage/apply', 'applyFlow'));
    /*btnArray.push(new pageButton('查看流程', 'viewProcess', '/changeManage/viewProcess', 'viewProcess'));*/
    /*btnArray.push(new pageButton('查看详情', 'viewDetails', '/changeManage/viewDetails', 'viewDetails'));*/
    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup("新增变更合同", "/changeManage/add", null, "1200", "800"));
        } else if (operation == 'editData') {
            if (isAllowOperation('该变更合同正在审批中，不可修改!', '该变更合同已审批，不可修改!')) {
                listService.editData($scope, $scope.gridApi, new popup("修改变更合同", "/changeManage/edit", null, "1200", "800"));
            }
        } else if (operation == 'deleteData') {
            if (isAllowOperation('该变更合同正在审批中，不可删除!', '该变更合同已审批，不可删除!')) {
                listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/changeManage/delete');
            }
        } else if (operation == "applyFlow") {
            if (isAllowOperation('该变更合同正在审批中，不可重复操作申请!', '该变更合同已审批，不可再次操作申请!')) {
                bootbox.confirm('确定申请吗?', function(isConfirm) {
                    if (isConfirm) {
                        var selectedData = listService.getSelectData($scope.gridApi);
                        $scope.promise = httpService.post($scope, "api/changeManage/apply/" + selectedData.id)
                            .success(function(response) {
                                load();
                            })
                            .error(function() {
                                submitTips('流程启动失败!', 'error');
                            });
                    }
                });
            }
        } else if (operation == "viewProcess") {
            /*列表上面按钮触发*/
            /*listService.showProcessProgress($scope, "flowInstanceId");*/
            listService.showProcessProgressInColumn($scope, "flowInstanceId", param); /*行内触发*/
        }
    }

    /**
     * 是否可以操作，消息提示
     */
    var isAllowOperation = function(tipMsg1, tipMsg2) {
        var selectedData = listService.getSelectData($scope.gridApi);
        /*console.log(selectedData);*/
        if (selectedData != undefined) {
            if (selectedData.approvalStatus == "flow_inapproval") {
                submitTips(tipMsg1, 'warning');
                return false;
            } else if (selectedData.approvalStatus == "flow_passed") {
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
        listService.loadData($scope, '/api/changeManage/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

});