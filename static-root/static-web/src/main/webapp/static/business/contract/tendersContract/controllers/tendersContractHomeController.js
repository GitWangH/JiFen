'use strict';
/** [合同管理] - [项目合同] -[施工合同管理] **/
angular.module('huatek.controllers').controller('TendersContractHomeController', function($rootScope, $scope, $http, listService, httpService) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: false,
        enableSelectAll: false,
        multiSelect: false,
        lookDetailConfig: { menuKey: '/tendersContract/edit', name: '施工合同查看' },
        columnDefs: [
            { name: '标段名称', field: 'orgName', width: '12%', enableColumnMenu: false },
            { name: '合同名称', field: 'contractCnName', width: '15%', enableColumnMenu: false },
            { name: '合同编号', field: 'contractCode', width: '12%', enableColumnMenu: false /*, cellTemplate : '<a ng-click="grid.appScope.viewContractInfo(row.entity)">{{row.entity.contractCode}}</a>'*/ },
            { name: '技术等级', field: 'technicalLevelName', width: '8%', enableColumnMenu: false, decode: { field: "technicalLevel", dataKey: "dic.highway_grade" } },
            { name: '合同金额', field: 'contractTotalPrice', width: '10%', enableColumnMenu: false, cellFilter: "priceFilter" },
            { name: '审批状态', field: 'approvalStatusName', width: '8%', enableColumnMenu: false, decode: { field: "approvalStatus", dataKey: "dic.flow_status" }, cellTemplate: listService.getLinkCellTmplate("viewProcess", "approvalStatusName") /*,cellTemplate:returnApprovalStatusColor()*/ },
            /* { name: '流程编号', field: 'flowInstanceId', width: '8%', enableColumnMenu: false },*/
            { name: '创建人', field: 'createrName', width: '8%', enableColumnMenu: false },
            { name: '创建时间', field: 'createTime', width: '*', enableColumnMenu: false }
        ]
    };
    /**
     * 初始化编辑界面. 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http, $scope.tableGrid);

    /**
     * 转换审批状态颜色(暂未使用)
     */
    function returnApprovalStatusColor() {
        return '<div class="ui-grid-cell-contents ui-grid-line-color-red"  ng-if="row.entity[\'approvalStatus\'] == \'flow_approved\'">{{COL_FIELD}}</div>';
    }
    // 定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    // 定义搜索框

    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', 'in');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_MULTIPLE_SLECT;
    queryPage.addParam(tendersName);
    /*动态加载机构数据(废弃)*/
    /*listService.initQueryItems($scope, tendersName, 'api/org/getFwOrgByType/7');*/

    queryPage.addParam(new queryParam('合同编号', 'contractCode', 'string', 'like'));
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
    btnArray.push(new pageButton('新增', 'add', '/tendersContract/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/tendersContract/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/tendersContract/delete', 'deleteData'));
    btnArray.push(new pageButton('申请', 'apply', '/tendersContract/apply', 'applyFlow'));
    /*btnArray.push(new pageButton('查看流程', 'viewProcess', '/tendersContract/viewProcess', 'viewProcess'));*/
    /*btnArray.push(new pageButton('查看详情', 'viewDetails', '/tendersContract/viewDetails', 'viewDetails'));*/
    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup("新增施工合同", "/tendersContract/add", null, "1200", "800"));
        } else if (operation == 'editData') {
            if (isAllowOperation('该施工合同正在审批中，不可修改!', '该施工合同已审批，不可修改!')) {
                listService.editData($scope, $scope.gridApi, new popup("修改施工合同", "/tendersContract/edit", null, "1200", "800"));
            }
        } else if (operation == 'deleteData') {
            if (isAllowOperation('该施工合同正在审批中，不可删除!', '该施工合同已审批，不可删除!')) {
                listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/tendersContract/delete');
            }
        } else if (operation == "applyFlow") {
            if (isAllowOperation('该施工合同正在审批中，不可重复操作申请!', '该施工合同已审批，不可再次操作申请!')) {
                bootbox.confirm('确定申请吗?', function(isConfirm) {
                    if (isConfirm) {
                        var selectedData = listService.getSelectData($scope.gridApi);
                        $scope.promise = httpService.post($scope, "api/tendersContract/apply/" + selectedData.id)
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
        listService.loadData($scope, '/api/tendersContract/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

    /**
     * 查看合同详情
     */
    $scope.viewContractInfo = function(entity) {
        listService.editData($scope, $scope.gridApi, new popup("查看施工合同", "/tendersContract/viewDetails", null, "1200", "800"));
    }

});