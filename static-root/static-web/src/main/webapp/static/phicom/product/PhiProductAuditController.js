'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiProductAuditController', function($scope, $location, $http, $rootScope, listService, httpService) {
    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        multiSelect: true,
        columnDefs: [
            { name: '商品名称', field: 'productName', width: '10%', enableColumnMenu: false },
            { name: '商品编号', field: 'productCode', width: '10%', enableColumnMenu: false },
            { name: '商品类型', field: 'productType11', width: '10%', enableColumnMenu: false, decode: { field: "productClass", dataKey: "dic.product_Type" } },
            { name: '市场价', field: 'marketPrice', width: '10%', enableColumnMenu: false },
            { name: '兑换积分', field: 'score', width: '10%', enableColumnMenu: false },
            { name: '加价', field: 'money', width: '10%', enableColumnMenu: false },
            { name: '库存', field: 'productStock', width: '10%', enableColumnMenu: false },
            { name: '商品状态', field: 'productStatus', width: '10%', enableColumnMenu: false, decode: { field: "productStatus", dataKey: "dic.product_status" } },
            { name: '是否上架', field: 'isShop', width: '10%', enableColumnMenu: false, decode: { field: "isShop", dataKey: "dic.isShop" } },
            { name: '上架时间', field: 'upTime', width: '10%', enableColumnMenu: false },
            { name: '下架时间', field: 'downTime', width: '10%', enableColumnMenu: false },

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
    queryPage.addParam(new queryParam('商品名称', 'productName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('商品编号', 'productCode', 'string', 'alllike'));
    var productType = new queryParam('商品类型', 'productType', 'string', '=', null, "dic.product_type");
    productType.componentType = 'select';
    queryPage.addParam(productType);
    var ProductStatus = new queryParam('商品状态', 'productStatus', 'string', '=', null, "dic.product_status");
    ProductStatus.componentType = 'select';
    queryPage.addParam(ProductStatus);
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
    btnArray.push(new pageButton('审核通过', 'audit', '/phiProduct/Audited', 'auditData'));
    btnArray.push(new pageButton('审核驳回', 'reject', '/phiProduct/reject', 'rejectData'));
    btnArray.push(new pageButton('商品详情', 'checkDetail', '/phiProduct/checkDetail', 'DetailData'));
    /***
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'auditData') {
            this.active($scope.gridApi, '/phiProduct/Audited', '1');
             load();           
        } else if (operation == 'rejectData') {
            this.active($scope.gridApi, '/phiProduct/rejectData', '2');
        } else if (operation == 'DetailData') {
            listService.editData($scope, $scope.gridApi, new popup("查看", '/phiProduct/checkDetail'));
        }
    }

    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/phiProduct/queryAuditStatus', $scope.tableGrid);
    }
    load();
    $rootScope.auditProductScope = $scope;

    var loadPhiProduct = $scope.load  = function() {
        console.log('+++++++++审核后刷新商品列表++++++++++')
       // console.log($rootScope.phiProductScope);
        listService.loadData($rootScope.phiProductScope, 'api/phiProduct/queryList', $rootScope.phiProductScope.tableGrid);
        console.log('==============审核后刷新商品列表===success=======')
    }
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };
    $scope.active = function(gridApi, toUrl, val) {
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if (selectData.length == 0) {
            submitTips('请至少勾选一条数据！', 'warning');
            return;
        }
        var ids = [];
        var val = val;
        for (var i = 0; i < selectData.length; i++) {
            if (selectData[i].productStatus == "1") {
                submitTips('该条数据已审批', 'warning');
                ids = [];
                break;
            }
            ids.push(selectData[i].id);
        }
        if (ids.length > 0) {
            $scope.promise = httpService.post($scope, "api/phiProduct/productStatus/" + val + "/" + ids).success(function(response) {
                load();
                /*重新加载商品管理数据*/
                loadPhiProduct()
                // console.log($rootScope.proLoad)
                // $rootScope.proLoad();
            })
        }

    }

});


/*查看明细*/
angular.module('huatek.controllers').controller('PhiProductDetialController', function($scope, $http, editService, httpService, $rootScope,cacheService) {
    var DetailDataUrl = 'api/phiProduct/edit';
    var homeUrl = '/phiProduct/Audithome';

    //定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '商品详情'));
    $scope.columnWrapArray = columnWrapArray;

    //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)

    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '商品名称', 'productName', 'string', 1, '30', null, null, null, null, null, 'readonly'));
      var productType = new FormElement(1, '商品类型', 'productClass', 'string', 1, '30', null, null, null, null, 'dic.product_Type', 'readonly')
    formFieldArray.push(productType);
    formFieldArray.push(new FormElement(1, '加价', 'money', 'number', 1, '30', null, null, null, null, null, 'readonly'));
    formFieldArray.push(new FormElement(1, '积分', 'score', 'number', 1, '30', null, null, null, null, null, 'readonly'));
    formFieldArray.push(new FormElement(1, '兑换上限', 'exchangSuperLimit', 'number', 1, '30', null, null, null, null, null, 'readonly'));
    formFieldArray.push(new FormElement(1, '库存', 'productStock', 'number', 1, '30', null, null, null, null, null, 'readonly'));
    formFieldArray.push(new FormElement(1, '市场价格', 'marketPrice', 'number', 1, '30', null, null, null, null, null, 'readonly'));
    formFieldArray.push(new FormElement(1, '描述', 'remark', 'string', 1, '128', 'textarea', null, null, null, null, 'readonly'));
    var productImage = new FormElement(1, '商品图片', 'productImageHead', 'string', '', '36', 'fileMultiple', null, null, null, null, 'readonly')
    productImage.systemHeader = "/root/dd";
    formFieldArray.push(productImage);
    //只读
    /*  $rootScope.formFieldArray = formFieldArray;
      for(var i=0;i<formFieldArray.length;i++){
    	   formFieldArray[i].readonly=true;
      }		*/

    editService.init($scope, $http);
    console.log(formFieldArray);
    editService.setFormFields($scope, formFieldArray);
    /**
     *加载编辑数据
     */
    if ($scope.editId) {
        console.log($scope.editId);
        editService.loadData($scope, DetailDataUrl, $scope.editId, function(response){
               var cache=cacheService.getData("dic.product_Type");
               console.log(cache);
                angular.forEach(cache, function (data, index) {
                    console.log(response);
                    if(data.code == response.productClass){
                        productType.value = data.name;
                        return;
                    }
                });

        });
    }
    /* */
    /**
     *修改
     */
    /*   
        $scope.update = function(){
        	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
        } */
    $scope.audit = function() {
        var actionUrl = "api/phiProduct/productStatus/1/" + $scope.editId;
        httpService.post($scope, actionUrl).success(function() {
            var postQueryPage = copyQueryPage(_scope.queryPage);
            if (_scope.notNeedQueryPage) {
                postQueryPage.orderBy = '';
                postQueryPage.queryParamList = [];
            }
            _scope.load();
        });

    }
    $scope.reject = function() {
        var actionUrl = "api/phiProduct/productStatus/2/" + $scope.editId;
        httpService.post($scope, actionUrl).success(function() {
            var postQueryPage = copyQueryPage(_scope.queryPage);
            if (_scope.notNeedQueryPage) {
                postQueryPage.orderBy = '';
                postQueryPage.queryParamList = [];
            }
            _scope.load();
        });

    }



});