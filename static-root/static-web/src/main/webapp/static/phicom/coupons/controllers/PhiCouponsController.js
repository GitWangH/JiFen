'use strict';
/***
 * 券码管理Controller
 */
angular.module('huatek.controllers').controller('PhiCouponsController', function($scope, $http, $rootScope, listService, httpService, cacheService) {
    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        columnDefs: [
            { name: '优惠劵方案id', field: 'cpnsWayId', width: '14%', enableColumnMenu: false },
            { name: '优惠劵名称', field: 'cpnsName', width: '14%', enableColumnMenu: false },
            { name: '优惠券类型', field: 'cpnsType1', width: '14%', enableColumnMenu: false, decode: { field: 'cpnsType', dataKey: 'dic.coupons_type' } },
            { name: '优惠券数量', field: 'cpnsQuantity', width: '14%', enableColumnMenu: false },
            { name: '优惠券有效期', field: 'cpnsValid', width: '14%', enableColumnMenu: false },
            { name: '优惠券方案', field: 'cponWay', width: '15%', enableColumnMenu: false },
            { name: '操作', field: '', width: '14%', enableColumnMenu: false, cellTemplate: listService.getLinkCellTmplate("viewCpns", "", "查看券码") }
        ]

    };

    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http);
    //定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");

    //定义搜索框
    /*queryParam (title, field, type, logic, value, dropDataUrl,isShow, event,display)*/
    queryPage.addParam(new queryParam('优惠券方案id', 'cpnsWayId', 'string', 'alllike'));
    queryPage.addParam(new queryParam('优惠券名称', 'cpnsName', 'string', 'alllike'));
    var cpnsType = new queryParam('优惠券类型', 'cpnsType', 'string', '=', '', 'dic.coupons_type');
    cpnsType.componentType = 'select';
    queryPage.addParam(cpnsType);

    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);

    /***
     * 注册gridApi的选择器.
     */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
        /*  调整grid底部高度
            listService.setGridHeight();*/
    };

    //定义功能按钮

    var btnArray = [];

    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if (operation == 'viewCpns') {
            var map = { 'id': param.cpnsWayId, 'name': param.cpnsName };
            listService.editData($scope, $scope.gridApi, new popup("查看券码", "/PhiCoupons/viewCoupons", map, "1200", "800"), param);
        }


    }
    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/phiCoupons/query', $scope.tableGrid);
    }

    load();
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };

});

/****
 * 优惠券明细Controller
 */
angular.module('huatek.controllers').controller('PhiCouponsDetailController', function($scope, $http, $rootScope, listService, httpService, cacheService) {
    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        columnDefs: [
            { name: '序号', field: 'cpnsId', width: '16%', enableColumnMenu: false },
            { name: '券码', field: 'coupCode', width: '17%', enableColumnMenu: false },
            { name: '券有效期', field: 'endTime', width: '17%', enableColumnMenu: false },
            { name: '是否兑换', field: 'exchangeStatus1', width: '16%', enableColumnMenu: false, decode: { field: 'exchangeStatus', dataKey: 'dic.exchange_status' } },
            { name: '绑定会员', field: 'memberName', width: '17%', enableColumnMenu: false },
            { name: '劵状态', field: 'coupStatus1', width: '16%', enableColumnMenu: false, decode: { field: 'coupStatus', dataKey: 'dic.coupons_status' } },
        ]

    };

    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http);
    //定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");

    queryPage.addParam(new queryParam('券码', 'coupCode', 'string', 'alllike'));
    queryPage.addParam(new queryParam('绑定会员', 'memberName', 'string', 'alllike'));
    var coupStatus = new queryParam('券状态', 'coupStatus', 'string', '=', '', 'dic.coupons_status');
    coupStatus.componentType = 'select';
    queryPage.addParam(coupStatus);
    var exchangeStatus = new queryParam('兑换状态', 'exchangeStatus', 'string', '=', '', 'dic.exchange_status');
    exchangeStatus.componentType = 'select';
    queryPage.addParam(exchangeStatus);

    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);

    /***
     * 注册gridApi的选择器.
     */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
    };

    //定义功能按钮

    var btnArray = [];

    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {

    }
    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        /*listService.loadData($scope,'api/phiCouponsDetail/query', $scope.tableGrid);*/
        /*httpService.post($scope, "api/phiCouponsDetail/getCouponsDetailByWayId/"+$scope.passParams.id).success(function(response) {
            listService.decodeTable($scope, response, $scope.tableGrid);
            $scope.tableGrid.data = response;
        });*/
        listService.loadData($scope, 'api/phiCouponsDetail/getCouponsDetailByWayId/' + $scope.passParams.id, $scope.tableGrid);
        $scope.cpnsName = $scope.passParams.name;
    }

    load();
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };

});