'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiScoreRuleConfigController', function($scope, $location, $http, $rootScope, listService, httpService) {

    var optionCellTemplate = function() {
        return '<a>' +
            '<span style="cursor:pointer" ng-if="(row.entity.productStatus == 1 && row.entity.isShop == 0)||row.entity.productStatus == 2||row.entity.isShop == 2" ng-click="grid.appScope.editData(row)">编辑&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>' +
            /*'<span style="cursor:pointer" ng-if="(row.entity.productStatus == 0 && row.entity.isShop == 0)|| row.entity.isShop ==1"">禁止修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>' +*/
            '<span style="cursor:pointer" ng-if="row.entity.productClass == 1 && row.entity.winnerStatus == 1" ng-click="grid.appScope.toWinner(row)">去开奖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>' +
            '<span style="cursor:pointer" ng-if="row.entity.productClass == 1 && row.entity.winnerStatus == 2" ng-click="grid.appScope.toWinner(row)">已开奖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>' +
            '<span style="cursor:pointer" ng-if="row.entity.productClass == 0 " ng-click="grid.appScope.couponsDetail(row.entity.coupWayId)">券码详细</span>' +
            '</a>';
    }

    /***
     * 定义显示的表格
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        multiSelect: true,
        columnDefs: [
            { name: '操作', field: 'operation', width: '20%', enableColumnMenu: false, cellTemplate: optionCellTemplate() },
            { name: '商品名称', field: 'productName', width: '10%', enableColumnMenu: false },
            { name: '商品编号', field: 'productCode', width: '10%', enableColumnMenu: false },
            { name: '商品类型', field: 'productClass_', width: '10%', enableColumnMenu: false, decode: { field: "productClass", dataKey: "dic.product_Type" } },
            //{ name: '开奖状态', field: 'winner_Status',width: '10%', enableColumnMenu: false,decode: { field: "winnerStatus", dataKey: "dic.winner_status" }},
            { name: '市场价', field: 'marketPrice', width: '10%', enableColumnMenu: false },
            { name: '兑换积分', field: 'score', width: '10%', enableColumnMenu: false },
            { name: '加价', field: 'money', width: '10%', enableColumnMenu: false },
            { name: '库存', field: 'productStock', width: '10%', enableColumnMenu: false },
            { name: '商品状态', field: 'productStatusa', width: '10%', enableColumnMenu: false, decode: { field: "productStatus", dataKey: "dic.product_status" } },
            { name: '是否上架', field: 'isShops', width: '10%', enableColumnMenu: false, decode: { field: "isShop", dataKey: "dic.isShop" } },
            { name: '上架时间', field: 'upTime', width: '15%', enableColumnMenu: false },
            { name: '下架时间', field: 'downTime', width: '15%', enableColumnMenu: false }

        ]
    };

    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     */
    listService.init($scope);
    //定义查询页

    var queryPage = new QueryPage(1, 10, "id desc", "false");




    //定义搜索框
    queryPage.addParam(new queryParam('商品名称', 'productName', 'string', 'like'));
    queryPage.addParam(new queryParam('商品编号', 'productCode', 'string', 'like'));
    var productType = new queryParam('商品类型', 'productClass', 'string', '=', null, "dic.product_Type");
    productType.componentType = 'select';
    queryPage.addParam(productType);
    var ProductStatus = new queryParam('商品状态', 'productStatus', 'string', '=', null, "dic.product_status");
    ProductStatus.componentType = 'select';
    queryPage.addParam(ProductStatus);
    //    var orderBy = new queryParam('排序','orderBy','string','=',null); 
    //    orderBy.componentType = 'select';
    //    orderBy.items = [{_returnFeild:"id desc",_showField:"id"},{_returnFeild:"productName desc",_showField:"productName"}]
    //    orderBy.event = function(value){
    //      console.log(value);
    //      queryPage.orderBy = value;
    //    }
    //    queryPage.addParam(orderBy);
    /* var productTypeNameList = new queryParam('商品分类','phiProductType','string','=');
      productTypeNameList.componentType = 'select';
      queryPage.addParam(productTypeNameList);*/
    /*listService.initQueryItems($scope,productTypeNameList, 'api/phiProduct/queryType');*/


    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);

    /***

     * 注册gridApi的选择器.
     */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        listService.setGridHeight();
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
    };

    /*
     * 注册gridApi的选择器.
     */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        listService.setGridHeight();
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
    };


    //定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/phiProduct/add', 'addData'));
    btnArray.push(new pageButton('生成订单', 'addorder', '/phiOrder/add', 'addOrderData'));
    btnArray.push(new pageButton('商品类型', 'addorder', '/phiProduct/queryByType', 'addOrderData'));
    //btnArray.push(new pageButton('修改', 'edit', '/phiProduct/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/phiProduct/delete', 'deleteData'));
    btnArray.push(new pageButton('上架', 'goShop', '/phiProduct/up', 'toShopData'));
    btnArray.push(new pageButton('下架', 'downShop', '/phiProduct/down', 'downShopData'));

    /***
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup("新增", '/phiProduct/add', $scope.treeParam, 1100));
        } else if (operation == 'editData') {
            listService.editData($scope, $scope.gridApi, new popup("修改", '/phiProduct/edit'));
        } else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/phiProduct/delete');
        } else if (operation == 'toShopData') {
            this.active($scope.gridApi, '/phiProduct/up', '1');
        } else if (operation == 'downShopData') {
            this.active($scope.gridApi, '/phiProduct/down', '2');
        } else if (operation == 'addOrderData') {
            listService.editData($scope, $scope.gridApi, new popup("生成订单", '/phiOrder/add'));
        }


    }
    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/phiProduct/query', $scope.tableGrid);
    }
    load();
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };
    $scope.editData = function(gridApi) {
        //console.log(11222)
        listService.editData($scope, gridApi, new popup("编辑", '/phiProduct/edit'));
    }

    $scope.active = function(gridApi, toUrl, val) {
        /*获取当前选择的数据.*/
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length == 0) {
            submitTips('请选择一条数据', 'warning');
            return;
        }
        var obj = selectData[0];
        if ("1" == val) {
            if (obj.isShop == "已上架") {
                submitTips('警告：该商品已上架，不能操作。', 'warning');
                return;
            }
        } else if ("2" == val) {
            if (obj.isShop == "已下架") {
                submitTips('警告：该商品已下架，不能操作。', 'warning');
                return;
            }
        }
        //批量上架商品
        bootbox.confirm('确定要提交所选的数据吗?', function(result) {
            if (result) {
                angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                    if (data.id == null || data.id == undefined) {
                        return;
                    }
                    var actionUrl = "api/phiProduct/productIsShop/" + data.id + "/" + val;
                    httpService.post($scope, actionUrl).success(function() {
                        var postQueryPage = copyQueryPage(_scope.queryPage);
                        if (_scope.notNeedQueryPage) {
                            postQueryPage.orderBy = '';
                            postQueryPage.queryParamList = [];
                        }
                        load();
                    });

                });
            }
        });

    }

    $scope.toWinner = function(row) {
        var params = new Object();
        params.productName = row.entity.productName;
        params.productId = row.entity.id;
        params.winnerStatus = row.entity.winnerStatus;
        params.winnerCounts = row.entity.winnerCounts;
        params.manualCounts = row.entity.manualCounts;
        params.randomCounts = row.entity.randomCounts;
        params.fakeCounts = row.entity.fakeCounts;
        listService.popPanel($scope, new popup("开奖", 'phi/toWinner', params, 923));
    }
    $scope.editData = function(row) {
        listService.popPanel($scope, new popup("编辑", 'phiProduct/edit', { "id": row.entity.id }));
    }
    $scope.couponsDetail = function(id) {
        listService.popPanel($scope, new popup("券码详情", 'phiProduct/couponsDetail', { "id": id }));
    }
});

