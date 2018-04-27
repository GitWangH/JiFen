'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到;
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiProductTypeController', function($scope, $location, $http, $rootScope, listService, httpService) {
    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        columnDefs: [
            { name: '类型名称', field: 'typeName', width: '17%', enableColumnMenu: false },
            { name: '类型图片', field: 'typeIcon', width: '17%', enableColumnMenu: false },
            { name: '显示顺序', field: 'showOrder', width: '17%', enableColumnMenu: false },
            { name: '操作人员', field: 'handleMan', width: '17%', enableColumnMenu: false },
            { name: '操作时间', field: 'lastTime', width: '17%', enableColumnMenu: false },
            { name: '是否推荐', field: 'isRecomment', width: '17%', enableColumnMenu: false, cellTemplate: listService.getLinkCellTmplate("change", "isRecomment"), decode: { field: "isRecomment", dataKey: "dic.isRecommended" } }
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
    queryPage.addParam(new queryParam('类型名称', 'typeName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('操作人员', 'handleMan', 'string', 'alllike'));
    //queryPage.addParam(new queryParam('是否推荐','isRecomment','string','like'));
    var isRecomment = new queryParam('商品类型', 'isRecomment', 'string', '=', null, "dic.isRecommended");
    isRecomment.componentType = 'select';
    queryPage.addParam(isRecomment);
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
    btnArray.push(new pageButton('新增分类', 'add', '/phiProductType/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/phiProductType/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/phiProductType/delete', 'deleteData'));
    /***
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);
    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup("新增", '/phiProductType/add', $scope.treeParam));
        } else if (operation == 'editData') {
            listService.editData($scope, $scope.gridApi, new popup("修改", '/phiProductType/edit'));
        } else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/phiProductType/delete');
        }
    }
    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
         listService.loadData($scope, 'api/phiProductType/query', $scope.tableGrid);
        // listService.loadData($scope, 'api/phiProduct/detial/'+152+'/'+5, $scope.tableGrid);
       // httpService.post($scope,'api/phiProduct/query',$scope.tableGrid);
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
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiProductTypeAddController', function($scope, $location, $http, $routeParams, $rootScope, editService, httpService) {
    /***
     * 定义编辑跳转的路径.
     */
    var addDataUrl = 'api/phiProductType/add';
    var editDataUrl = 'api/phiProductType/edit';
    var homeUrl = '/phiProduct/typehome';
    //定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    //定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '类型名称', 'typeName', 'string', 1, '30'));
    //formFieldArray.push(new FormElement(1,'类型图片','typeIcon','string',1,'30'));
    formFieldArray.push(new FormElement(1, '显示顺序', 'showOrder', 'String', 1, '30'));
    formFieldArray.push(new FormElement(1, '操作人员', 'handleMan', 'string', 1, '30'));
    formFieldArray.push(new FormElement(1, '操作时间', 'lastTime', 'String', 1, '30', 'date'));
    formFieldArray.push(new FormElement(1, '是否推荐', 'isRecomment', 'string', 1, '30', 'select', null, null, null, 'dic.isRecommended'));
    var productTypeImage = new FormElement(1, '商品图片', 'typeIcon', 'string', '', '36', 'fileMultiple');
    productTypeImage.systemHeader = "/root/type";
    formFieldArray.push(productTypeImage);
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;
    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
     */
    editService.init($scope, $location, $http);

    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields($scope, formFieldArray);

    /***
     * 定义获取需要编辑的内容.
     */
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);
    } else {
        $scope.editId = -1;
    }

    /***
     * 定义更新操作.
     */
    $scope.update = function() {
        editService.updateData($scope,editDataUrl, homeUrl, $scope.editId);
    }
    /**
     * 定义保存操作.
     */
    $scope.save = function() {
        editService.saveData($scope, addDataUrl, homeUrl);
    }
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */