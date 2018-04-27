'use strict';

angular.module('huatek.controllers').controller('CatalogController', function($scope, $location, $http, listService, $modal, $rootScope, httpService) {

    listService.init($scope, $http, $rootScope);
    /***
     * 定义显示的表格. 
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '编码', field: 'code', width: '20%', enableColumnMenu: false },
            { name: '名称 ', field: 'name', width: '20%', enableColumnMenu: false },
            { name: '路径 ', field: 'path', width: '20%', enableColumnMenu: false },
            { name: '是否系统目录', field: 'tenantId', width: '10%', enableColumnMenu: false, cellFilter: "showFilter" },
            { name: '备注', field: 'remark', width: '*', enableColumnMenu: false },
        ]
    };

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

    $scope.catalogList = [];
    $scope.catalogTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        /*加载目录树时, 根据菜单参数获取*/
        httpService.get($scope, "api/cmdFileCatalog/getAllUserCatalog", { path: '/root' }).success(function(response) {
            {
                $scope.catalogList = response;
                for (var i = 0; i < $scope.catalogList.length; i++) {
                    $scope.catalogList[i].open = true;
                }
                $scope.initcatalogTree();
            }
        });
    });
    /*定义变量传递目录树所选参数*/
    $scope.treeParam = new Object();
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        parentIdParam.value = treeNode.id;
        $scope.treeParam.parent = treeNode.id;
        load();
    }
    $scope.initcatalogTree = function() {
        var setting = {
            data: {
                key: {
                    name: "name"
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#catalogTree"), setting, $scope.catalogList);
    }

    var queryPage = new QueryPage(1, 10, "id desc", "false");

    queryPage.addParam(new queryParam('目录名称', 'name', 'string', 'like'));
    var parentIdParam = new queryParam('上级目录', 'parent.id', 'string', '=');
    parentIdParam.isShow = false;
    queryPage.addParam(parentIdParam);
    /*var tenantIdParam=new queryParam('租户ID','tenantId','string','=');
    tenantIdParam.isShow=false;
    tenantIdParam.value = $rootScope.tenantId;
    queryPage.addParam(tenantIdParam);*/

    listService.setQueryPage($scope, queryPage);


    /*console.log(actionMap);*/

    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/catalog/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/catalog/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'active', '/catalog/delete', 'deleteData'));
    btnArray.push(new pageButton('目录权限', 'disable', '/catalog/assign', 'assignSource'));

    listService.setButtonList($scope, btnArray);


    $scope.execute = function(operation, param) {
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if (operation == 'addData') {
            if (undefined == $scope.treeParam.parent || null == $scope.treeParam.parent || "" == $scope.treeParam.parent) {
                $scope.treeParam.parent = 1;
            }
            listService.addData($scope, new popup("新增目录", "/catalog/add", $scope.treeParam.parent));
        } else if (operation == 'editData') {
            /*系统目录不能修改*/
            if ($rootScope.tenantId && (undefined == selectData[0].tenantId || null == selectData[0].tenantId || "" == selectData[0].tenantId)) {
                submitTips('警告：系统目录不能修改。', 'warning');
                return;
            }
            listService.editData($scope, $scope.gridApi, new popup("编辑目录", "/catalog/edit"));
        } else if (operation == 'deleteData') {
            /*if($rootScope.tenantId && (undefined == selectData[0].tenantId || null == selectData[0].tenantId || "" == selectData[0].tenantId)){
                submitTips('警告：系统目录不能删除。','warning');
                 return;
            }*/
            this.deleteData($scope.tableGrid, $scope.gridApi, 'api/cmdFileCatalog/delete');
        } else if (operation == 'assignSource') {
            listService.editData($scope, $scope.gridApi, new popup("目录权限", "/catalog/assign"));
        }
    }

    $scope.deleteData = function(tableGrid, gridApi, toUrl) {
        if (gridApi.selection.getSelectedRows().length < 1) {

            /*      bootbox.alert("请至少勾选一条数据！");*/
            submitTips('请至少勾选一条数据！', 'warning');
            return false;
        }
        bootbox.confirm('确定要删除所选的数据吗?', function(result) {
            if (result) {
                var isDel = true;
                var ids = [];
                angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                    if (data.id == null || data.id == undefined) {
                        isDel = false;
                        return;
                    }
                    if ($rootScope.tenantId && (undefined == data.tenantId || null == data.tenantId || data.tenantId == "")) {
                        submitTips('警告：所选目录不可删除(包含系统目录)', 'warning');
                        isDel = false;
                        return;
                    }
                    ids.push(data.id);
                });
                if (isDel) {
                    $http.delete(toUrl + "/" + ids).success(function() {
                        load();
                        /*重新加载目录树*/
                        httpService.get($scope, "api/cmdFileCatalog/getAllUserCatalog", { path: '/root' }).success(function(response) {
                            {
                                $scope.catalogList = response;
                                for (var i = 0; i < $scope.catalogList.length; i++) {
                                    $scope.catalogList[i].open = true;
                                }
                                $scope.initcatalogTree();
                            }
                        });
                    });
                }


            }
        });

    }

    var load = $scope.load = function() {
        listService.loadData($scope, 'api/cmdFileCatalog/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    }
}).filter("showFilter", function() { /*价格数据过滤器*/
    var filterFunction = function(val) {
        if (undefined == val || null == val || "" == val) {
            return "是";
        } else {
            return "否";
        }
    };
    return filterFunction;
});

/**
 * 组织新增Controller
 */
angular.module('huatek.controllers').controller('CatalogAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService, listService) {

    var addDataUrl = 'api/cmdFileCatalog/add';
    var editDataUrl = 'api/cmdFileCatalog/edit';
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '编码', 'code', 'string', '1', '30'));
    formFieldArray.push(new FormElement(1, '名称', 'name', 'string', '1', '30'));
    formFieldArray.push(new FormElement(1, '备注', 'remark', 'text', '0', '30', 'textarea'));
    var parentId = new FormElement(1, 'parentId', 'parentId', 'string', '1', '120');
    parentId.value = $scope.passParams;
    parentId.isShow = false;
    formFieldArray.push(parentId);

    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    editService.init($scope);

    editService.setFormFields($scope, formFieldArray);
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);
    } else {
        $scope.editId = -1;
    }

    /**
     * 更新
     */
    $scope.update = function() {
        editService.updateData($scope, editDataUrl, null, $scope.editId);
    }

    /**
     * 新增
     */
    $scope.save = function() {
            editService.saveData($scope, addDataUrl, null, null, function() {
                /*load();*/
                httpService.get($scope, "api/cmdFileCatalog/getAllUserCatalog", { path: '/root' }).success(function(response) {
                    {
                        $scope.catalogList = response;
                        for (var i = 0; i < $scope.catalogList.length; i++) {
                            $scope.catalogList[i].open = true;
                        }
                        $scope.initcatalogTree();
                    }
                });

            });
        }
        /*加载机构树*/
    $scope.initcatalogTree = function() {
        var setting = {
            data: {
                key: {
                    name: "name"
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#catalogTree"), setting, $scope.catalogList);
    }

});

/**
 * 目录权限
 */
angular.module('huatek.controllers').controller('CatalogAssignController', function($scope, $location, $http, $routeParams, $rootScope, httpService) {

    var catalogMap = new Map();
    /*加载菜单树*/
    $scope.catalogTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        httpService.get($scope, 'api/cmdFileCatalog/assign/loadAllOrgWithCatalog/' + $scope.editId).success(function(response) {
            for (var i = 0; i < response.data.length; i++) {
                catalogMap.put(response.data[i].orgId, response.data[i]);
            }
            /*超级管理员可以看到所有菜单*/
            var url = "api/org/public/getAllUserOrg";
            /*if(undefined == $rootScope.tenantId || null == $rootScope.tenantId || "" == $rootScope.tenantId){
                url = "api/menu/public/loadAll";
            }*/
            httpService.get($scope, url).success(function(response) {
                $scope.catalogTree = response;
                for (var i = 0; i < $scope.catalogTree.length; i++) {
                    $scope.catalogTree[i].nocheck = false;
                    /*   复选框回显参照按钮权限的isShow*/
                    $scope.catalogTree[i].checked = catalogMap.get($scope.catalogTree[i].id) == null;;
                }
                $scope.initOrgTree();
            });
        });

    });
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        /*parentIdParam.value = treeNode.id;*/
        load();
    }
    $scope.initOrgTree = function() {
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox"
                    /*nocheckInherit: true */
            },
            data: {

                key: {
                    name: "name",
                    url: 'url1'
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentId",
                    rootPId: 0
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#catalogBlackListTree"), setting, $scope.catalogTree);
    }

    /**
     * 保存权限
     */
    $scope.checkNodeInfo = function() {
        var zTree = $.fn.zTree.getZTreeObj("catalogBlackListTree");
        var nodes = zTree.getCheckedNodes(false);
        var dataArr = [];
        for (var i = 0; i < nodes.length; i++) {
            if ($(nodes[i]).attr('id') * 1 > 0) {
                dataArr.push($(nodes[i]).attr('id'));
            }
        }
        var actionUrl = "api/cmdFileCatalog/doSaveAssign/" + $scope.editId;
        httpService.post($scope, actionUrl, dataArr).success(function() {
            $scope['jsPanel'].close();
        });
    }

    /**
     * 关闭
     */
    $scope.back = function() {
        $scope['jsPanel'].close();
    }
});