'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiProductController', function($scope, $location, $http, $rootScope, listService, httpService) {

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
    queryPage.addParam(new queryParam('商品名称', 'productName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('商品编号', 'productCode', 'string', 'alllike'));
    var productType = new queryParam('商品类型', 'productClass', 'string', '=', null, "dic.product_Type");
    productType.componentType = 'select';
    queryPage.addParam(productType);
    var ProductStatus = new queryParam('商品状态', 'productStatus', 'string', '=', null, "dic.product_status");
    ProductStatus.componentType = 'select';
    queryPage.addParam(ProductStatus);
    queryPage.addParam(new queryParam('加购价', 'money', 'long', '!=',null));

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
    btnArray.push(new pageButton('商品类型', 'addorder', '/phiProduct/queryByType', 'addOrderData'));
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
            var data = $scope.gridApi.selection.getSelectedRows();
           /* console.log($scope.gridApi);
              console.log(data[0].productStatus);*/
            if(data[0].productStatus =="0"){
                submitTips('待审核的商品不可删除', 'warning');
            }else{
             listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/phiProduct/delete');    
            }
            
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
        listService.loadData($scope, 'api/phiProduct/queryList', $scope.tableGrid);
    }
    load();
    $rootScope.phiProductScope = $scope;

    var loadAuditProduct = function() {
       // console.log($rootScope.auditProductScope);
        console.log("")
        listService.loadData($rootScope.auditProductScope, 'api/phiProduct/queryAuditStatus', $rootScope.auditProductScope.tableGrid);
    }
    if($rootScope.auditProductScope !=undefined){
           loadAuditProduct();    
        }
    
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };
    //$rootScope.proLoad = load();
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
                        /*var postQueryPage = copyQueryPage(QueryPage);
                        if (_scope.notNeedQueryPage) {
                            postQueryPage.orderBy = '';
                            postQueryPage.queryParamList = [];
                        }     */                
                        load();
                    });
                    load();
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

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiProductAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService,listService) {
    /***
     * 定义编辑跳转的路径.
     */
    var addDataUrl = 'api/phiProduct/add';
    var editDataUrl = 'api/phiProduct/edit';
    var homeUrl = '/phiProduct/home';

    //定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    //定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
    var productImageHead = new FormElement(1, '商品图片', 'productImageHead', 'string', 1, '36', 'fileAndPreview');
    productImageHead.systemHeader = "/root/dd";
    formFieldArray.push(productImageHead);
    formFieldArray.push(new FormElement(1, '商品名称', 'productName', 'string', 1, '30'));
    var productType = new FormElement(1, '商品类型', 'productClass', 'string', 1, '30', 'select', null, null, null, 'dic.product_Type');
    productType.event = function(value) {
        // console.log(value);
        if (value == 0) {
            idList.isShow = true;
            thirdIdList.isShow = false;
            validTime.isShow = true;
            logistCost.isShow = false;
            winnerTime.isShow = false;
            editService.initFieldItems($scope, idList, 'api/phiProduct/queryCouponsId');
        }
        if (value == 1) {
            thirdIdList.isShow = false;
            idList.isShow = false;
            validTime.isShow = false;
            logistCost.isShow = false;
            winnerTime.isShow = true;
        }
        if (value == 2) {
            thirdIdList.isShow = false;
            idList.isShow = false;
            validTime.isShow = false;
            logistCost.isShow = true;
            winnerTime.isShow = false;
        }
        if (value == 3) {
            thirdIdList.isShow = true;
            idList.isShow = false;
            validTime.isShow = true;
            logistCost.isShow = false;
            winnerTime.isShow = false;
            editService.initFieldItems($scope, thirdIdList, 'api/phiProduct/queryThirdPartyId');
        }

    }
    formFieldArray.push(productType);
    formFieldArray.push(new FormElement(1, '加价', 'money', 'number', 0, '30'));
    formFieldArray.push(new FormElement(1, '积分', 'score', 'number', 1, '30'));
    formFieldArray.push(new FormElement(1, '兑换上限', 'exchangSuperLimit', 'number', 0, '30'));
    formFieldArray.push(new FormElement(1, '库存', 'productStock', 'number', 1, '30'));
    var winnerTime = new FormElement(1, '开奖时间', 'winnerTime', 'string', 1, '30', 'date');
    formFieldArray.push(winnerTime);
    /*$scope.winnerStatus = new FormElement(1, '', 'winnerStatus', 'string', 1, '30');
    $scope.winnerStatus.value = 0;
    formFieldArray.push($scope.winnerStatus);*/
    formFieldArray.push(new FormElement(1, '市场价格', 'marketPrice', 'number', 1, '30'));
    formFieldArray.push(new FormElement(1, '会员条件', 'needGrade', 'number', 1, '30', 'select', null, null, null, 'dic.member_grade'));
    var productTypeNameList = new FormElement(1, '商品分类', 'phiProductType', 'String', 1, '30', 'select', 'changpage(val)');
    formFieldArray.push(productTypeNameList);
    var idList = new FormElement(1, '券码id', 'coupWayId', 'number', 1, '30', 'select');
    formFieldArray.push(idList);
    var thirdIdList = new FormElement(1, '第三方id', 'thirdId', 'number', 1, '30', 'select');
    formFieldArray.push(thirdIdList);
    var validTime = new FormElement(1, '有效期', 'validTime', 'string', 1, '30');
    validTime.readonly = true;
    formFieldArray.push(validTime);
    var logistCost = new FormElement(1, '物流费用', 'logisticsCost', 'number', 1, '30');
    formFieldArray.push(logistCost);
    var isRecommend = new FormElement(1, '是否推荐', 'isRecommend', 'String', 0, '30', 'radio');
    isRecommend.items = [{ "name": "是", "code": "true" }, { "name": "否", "code": "false" }];
    isRecommend.value = "false";
    formFieldArray.push(isRecommend);
    var upTime = new FormElement(1, '上架时间', 'upTime', 'string', 0, '30', 'date')
    formFieldArray.push(upTime);
    var downTime = new FormElement(1, '下架时间', 'downTime', 'string', 0, '30', 'date')
    formFieldArray.push(downTime);
    //上架时间必须小于下架时间
   $scope.compareTime = function(upTime,downTime){   
    var upTimevalue = new Date(upTime.value).getTime();
    var downTimevalue = new Date(downTime.value).getTime();
        if(upTime != null && downTime != null){
          var upTimevalue = new Date(upTime.value).getTime();
          var downTimevalue = new Date(downTime.value).getTime();
        }
        if(upTimevalue > downTimevalue){
          return false;
        }else{
          return true;
        }
    }

    formFieldArray.push(new FormElement(1, '描述', 'remark', 'string', 1, '128', 'textarea'));
    var productImagePC = $scope.productImagePC = new FormElement(1, 'PC端商品图片', 'productImagePc', 'string', '0', '36', 'fileMultiple');
    productImagePC.systemHeader = "/root/dd";
    formFieldArray.push(productImagePC);
    var productImageApp = $scope.productImageApp = new FormElement(1, 'APP端商品图片', 'productImageApp', 'string', '0', '36', 'fileMultiple');
    productImageApp.systemHeader = "/root/dd";
    formFieldArray.push(productImageApp);
    // formFieldArray.push(productImage);
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;
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
    editService.initFieldItems($scope, productTypeNameList, 'api/phiProduct/queryType');

    //editService.initFieldItems($scope, idList, 'api/phiProduct/queryCouponsId');
    /***
     * 定义获取需要编辑的内容.
     */
    /* 
    /***
     * 定义获取需要编辑的内容.
     */
    
    if (undefined == $scope.editId) {
        if ($scope.passParams != undefined) {
            $scope.editId = $scope.passParams.id;
            editService.loadData($scope, editDataUrl, $scope.editId, null, function(res) {
                //console.log(res);
                var value = res.productClass;
               // console.log(value);
                if (value == 0) {
                    idList.isShow = true;
                    thirdIdList.isShow = false;
                    validTime.isShow = true;
                    logistCost.isShow = false;
                    winnerTime.isShow = false;
                    editService.initFieldItems($scope, idList, 'api/phiProduct/queryCouponsId');
                }
                if (value == 1) {
                    thirdIdList.isShow = false;
                    idList.isShow = false;
                    validTime.isShow = false;
                    logistCost.isShow = false;
                    winnerTime.isShow = true;
                }
                if (value == 2) {
                    thirdIdList.isShow = false;
                    idList.isShow = false;
                    validTime.isShow = false;
                    logistCost.isShow = true;
                    winnerTime.isShow = false;
                }
                if (value == 3) {
                    thirdIdList.isShow = true;
                    idList.isShow = false;
                    validTime.isShow = true;
                    logistCost.isShow = false;
                    winnerTime.isShow = false;
                    editService.initFieldItems($scope, thirdIdList, 'api/phiProduct/queryThirdPartyId');
                }
            });
        } else {
            $scope.editId = -1;
        }
    }

    /***
     * 定义更新操作.
     */
    $scope.update = function() {
      var aMap = getMap(formFieldArray, 'name');
      var filesCount = aMap.get('productImageHead').filesCount;
       //console.log("=========="+filesCount+"+++++++++")
      if(filesCount==0){
        submitTips('商品主图不能为空','warning');
        return;
      }else if(parseInt(filesCount) > parseInt(5)){
        submitTips('商品图片数量过多','warning');
        return;
      }

        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId,null,function(){
            /*修改刷新审核列表*/
             if($rootScope.auditProductScope !=undefined){
            console.log("—————————修改商品刷新审核列表———————————");
            listService.loadData($rootScope.auditProductScope, 'api/phiProduct/queryAuditStatus', $rootScope.auditProductScope.tableGrid);
             console.log("—————————修改商品刷新审核列表———success————————");
          }
        });
    }
    $rootScope.phiProductScope = $scope;
    /**
     * 定义保存操作.
     */
    $scope.save = function() {  
     var resule = $scope.compareTime(upTime,downTime);
      if(resule == false){
         submitTips('上架时间必须小于下架时间','warning');
         return;
      }
      //debugger;
      var aMap = getMap(formFieldArray, 'name');
      var filesCount = aMap.get('productImageHead').filesCount;
      //console.log("=========="+filesCount+"+++++++++")
      if(filesCount==0){
        submitTips('商品主图不能为空','warning');
        return;
      }else if(parseInt(filesCount) > parseInt(5)){
        submitTips('商品主图数量过多','warning');
        return;
      }
     editService.saveData($scope, addDataUrl, homeUrl,function(){
        if($rootScope.auditProductScope !=undefined){
            console.log("—————————新增商品刷新审核列表———————————");
          listService.loadData($rootScope.auditProductScope, 'api/phiProduct/queryAuditStatus', $rootScope.auditProductScope.tableGrid);
             console.log("—————————新增商品刷新审核列表———success————————");
        }
     })
    }



    idList.event = function(val) {
        var couponsId = val;
        var actionUrl = "api/phiProduct/queryValidDate/" + couponsId;
        httpService.get($scope, actionUrl).success(function(param) {
            validTime.value = eval(param).name;
        });

    }
    thirdIdList.event = function(val) {
        var thirdIdList = val;
        var actionUrl = "api/phiProduct/queryThirdPartyValidDate/" + thirdIdList;
        httpService.get($scope, actionUrl).success(function(param) {
            validTime.value = eval(param).name;
        });
    }





});






