'use strict';
/** [合同管理] - [项目合同] -[其它合同管理] **/
angular.module('huatek.controllers').controller('OtherContractHomeController', function($rootScope, $scope, $http, listService) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        /*选择当前行*/
        enableSelectAll: false,
        /*选择所有*/
        multiSelect: false,
        /*多行操作*/
        lookDetailConfig: { menuKey: '/otherContract/edit', name: '施工合同查看' },
        columnDefs: [
            { name: '标段名称', field: 'orgName', width: '20%', enableColumnMenu: false },
            { name: '合同名称', field: 'contractName', width: '20%', enableColumnMenu: false },
            { name: '签订日期', field: 'signatureData', width: '20%', enableColumnMenu: false, cellFilter: "dateFilter" },
            { name: '创建人', field: 'createrName', width: '18%', enableColumnMenu: false },
            { name: '创建时间', field: 'createTime', width: '15%', enableColumnMenu: false }
        ]
    };

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

    /**
     * 初始化编辑界面. 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http, $scope.tableGrid, $scope.gridApi);
    // 定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    // 定义搜索框

    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
    tendersName.value = $rootScope.orgId;
    queryPage.addParam(tendersName);

    queryPage.addParam(new queryParam('合同名称', 'contractName', 'string', 'like'));
    queryPage.addParam(new queryParam('签订日期', 'signatureData', 'date', '>='));
    queryPage.addParam(new queryParam('--', 'signatureData', 'date', '<='));

    $rootScope.searchCount = queryPage.queryParamList.length;
    /**
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);



    // 定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/otherContract/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/otherContract/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/otherContract/delete', 'deleteData'));
    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'addData') { /*新增*/
            listService.addData($scope, new popup("新增其它合同", "/otherContract/add", null, 800, 600));
        } else if (operation == 'editData') { /*编辑*/
            listService.editData($scope, $scope.gridApi, new popup("修改其它合同", "/otherContract/edit", null, 800, 600));
        } else if (operation == 'deleteData') { /*删除*/
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/otherContract/delete');
        }
    }
    /**
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, '/api/otherContract/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

    /**
     * 查看合同详情
     */
    $scope.viewContractInfo = function(entity) {
        listService.editData($scope, $scope.gridApi, new popup("查看其它合同", "/otherContract/viewDetails", null, 800, 600));
    }
}).filter("dateFilter", function() { /*日期数据过滤器*/
    var dateFilterFunction = function(dateVal) {
        if (cnex4_isNotEmpty_str(dateVal)) {
            return getDate(dateVal);
        } else {
            return dateVal;
        }
    };
    return dateFilterFunction;
});