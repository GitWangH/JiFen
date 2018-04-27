'use strict';

angular.module('huatek.controllers').controller('RoleController', function($scope, $location, $http, listService, $rootScope, httpService) {
    /***
     * 定义显示的表格. 
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        multiSelect: false,
        columnDefs: [
            { name: '角色名称', field: 'name', width: '20%', enableColumnMenu: false },
            { name: '角色编码', field: 'rolecode', width: '20%', enableColumnMenu: false },
            /*    { name: '是否合作网点', field: 'isCooperate',width: '10%', enableColumnMenu: false,cellFilter:"isCooperateFilter"},*/
            /*		      { name: '所属部门', field: 'fwDeptName',width: '30%', enableColumnMenu: false},*/
            { name: '描述', field: 'comments', width: '30%', enableColumnMenu: false },
            { name: '角色类型', field: 'type', width: '30%', enableColumnMenu: false },
            { name: '组织类型', field: 'orgTypeVal', width: '30%', enableColumnMenu: false, decode: { field: 'orgType', dataKey: 'dic.org_type' } }
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
        listService.setGridHeight("role");
    };

    listService.init($scope, $http, $scope.tableGrid);

    $scope.roleList = [];
    $scope.roleTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        httpService.get($scope, "api/fwrole/getRoleList").success(function(response) {
            {
                $scope.roleList = response;
                for (var i = 0; i < $scope.roleList.length; i++) {
                    $scope.roleList[i].open = true;
                }
                $scope.initRoleTree();
            }
        });
    });
    /*定义变量传递组织树所选参数*/
    $scope.treeParam = undefined;
    $scope.treeTenantId = undefined;
    /*记录已选中节点*/
    $scope.treeClickId = undefined;
    $scope.treeChildClickId = undefined;
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        $scope.treeParam = treeNode.id;
        groupId.value = treeNode.id;
        $scope.treeTenantId = treeNode.tenantId;
        $scope.treeClickId = treeId;
        $scope.treeChildClickId = treeNode.tId;
        load();
    }

    $scope.initRoleTree = function() {
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
        $.fn.zTree.init($("#roleTree"), setting, $scope.roleList);
    }


    var queryPage = new QueryPage(1, 10, "id asc", "false");

    queryPage.addParam(new queryParam('角色名称', 'name', 'string', 'alllike'));
    queryPage.addParam(new queryParam('角色编码', 'rolecode', 'string', 'alllike'));
    queryPage.addParam(new queryParam('描述', 'comments', 'string', 'alllike'));
    var groupId = new queryParam('角色组', 'fwRoleGroup.id', 'string', '=');
    groupId.isShow = false;
    queryPage.addParam(groupId);
    /*    var isCooperate=new queryParam('是否合作网点','isCooperate','string');
     isCooperate.componentType = "select";
     isCooperate.items=[{_returnField:"0", _showField:"否"},{_returnField:"1",_showField:"是"}];
     queryPage.addParam(isCooperate);*/


    listService.setQueryPage($scope, queryPage);


    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/fwrole/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/fwrole/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/fwrole/delete', 'deleteData'));
    btnArray.push(new pageButton('分配人员', 'assignAcct', '/fwrole/assignAcct', 'assginAcctData'));
    btnArray.push(new pageButton('分配资源', 'assignSource', '/fwrole/assignSource', 'assginSourceData'));
    listService.setButtonList($scope, btnArray);
    /*角色组树上面按钮*/
    $scope.btnArrayList_ = [];
    $scope.btnArrayList_.push(new pageButton('新增角色组', 'addGroup', '/roleGroup/add', 'addGroupData'));
    $scope.btnArrayList_.push(new pageButton('编辑角色组', 'editGroup', '/roleGroup/edit', 'editGroupData'));
    $scope.btnArrayList_.push(new pageButton('删除', 'deleteGroup', '/roleGroup/delete', 'deleteGroupData'));



    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            if ($scope.treeParam) {
                listService.addData($scope, new popup("新增角色", '/fwrole/add', $scope.treeParam));
            } else {
                /*未选择左侧角色组则默认为系统角色组*/
                /*listService.addData($scope, new popup("新增角色",'/fwrole/add', "1"));*/
                submitTips('警告：请在角色组树中选择一条数据。', 'warning');
                return;
            }

        } else if (operation == 'deleteData') {

            /*listService.deleteData($scope.tableGrid,$scope.gridApi, URL_PATH.MDM_HEADER+'/mdmPosition/deleteRole');*/
            this.deleteData($scope.tableGrid, $scope.gridApi, 'api/fwrole/delete');
        } else if (operation == 'editData') {

            /*listService.editData($scope.gridApi, '/fwrole/edit');*/
            this.editData($scope.gridApi, new popup("编辑角色", '/fwrole/edit'));
        } else if (operation == 'assginAcctData') {
            /*分配人员*/
            this.assignRight($scope.gridApi, new popup("分配人员", '/fwrole/assignAcct'), "1");
        } else if (operation == 'assginSourceData') {
            /*分配资源*/
            var selectData = $scope.gridApi.selection.getSelectedRows();
            if (selectData.length == 0) {
                submitTips('警告：请在列表中选择一条数据。', 'warning');
                return;
            }
            if (selectData.length > 1) {
                submitTips('警告：只能选择一条数据。', 'warning');
                return;
            }
            this.assignRight($scope.gridApi, new popup("分配资源", '/fwrole/assignSource', selectData[0].rolecode), "2");
        } else if (operation == 'addGroupData') {
            if ($scope.treeParam) {
                 var param = { "id": $scope.treeParam, "type": "add" };
                listService.addData($scope, new popup("新增角色组", '/roleGroup/add', param));
            } else {
                /* listService.addData($scope, new popup("新增角色组",'/roleGroup/add', "1"));*/
                submitTips('警告：请在角色组树中选择一条作为上级角色组。', 'warning');
                return;
            }

        } else if (operation == 'editGroupData') {
            if ($rootScope.tenantId && !$scope.treeTenantId) {
                submitTips('警告：不可进行编辑操作(系统角色组)。', 'warning');
                return;
            }
            if ($scope.treeParam) {
                var param = { "id": $scope.treeParam, "type": "edit" };

                listService.addData($scope, new popup("修改角色组", '/roleGroup/edit', param));
            } else {
                submitTips('警告：请在角色组树中选择一条修改的数据。', 'warning');
                return;
            }
        } else if (operation == 'deleteGroupData') {
            if ($rootScope.tenantId && !$scope.treeTenantId) {
                submitTips('警告：不可进行删除操作(系统角色组)。', 'warning');
                return;
            }
            if ($scope.treeParam) {
                /*如果该组包含子组或角色无法删除*/
                bootbox.confirm('确定要删除该角色组吗?', function(result) {
                    if (result) {
                        $http.delete("/api/fwRoleGroup/delete/" + $scope.treeParam).success(function() {
                            httpService.get($scope, "api/fwrole/getRoleList").success(function(response) {
                                {
                                    $scope.roleList = response;
                                    for (var i = 0; i < $scope.roleList.length; i++) {
                                        $scope.roleList[i].open = true;
                                    }
                                    $scope.initRoleTree();
                                    $scope.treeParam = undefined;
                                }
                            });
                        });
                    }
                });
            } else {
                submitTips('警告：请在角色组树中选择一条删除的数据。', 'warning');
                return;
            }
        }
    }

    var load = $scope.load = function() {
        listService.loadData($scope, 'api/fwrole/query', $scope.tableGrid);
    }

    /***
     * 分派权限.
     */
    $scope.assignRight = function(gridApi, popup, type) {

        /*获取当前选择的数据.*/
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length == 0) {
            submitTips('警告：请在列表中选择一条数据。', 'warning');
            return;
        }
        if (selectData.length > 1) {
            submitTips('警告：只能选择一条数据。', 'warning');
            return;
        }
        if ($rootScope.tenantId && (null == selectData[0].tenantId || selectData[0].tenantId == "") && type == "1") {
            submitTips('警告：角色不可分配人员(系统角色)', 'warning');
            return;
        }
        if ($rootScope.tenantId && (null == selectData[0].tenantId || selectData[0].tenantId == "") && type == "2") {
            submitTips('警告：角色不可分配资源(系统角色)', 'warning');
            return;
        }
        /*$location.path(toUrl + "/" + selectData[0].id);*/
        listService.editData($scope, gridApi, popup);

    }
    $scope.editData = function(gridApi, toUrl) {

        /*获取当前选择的数据.*/
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length == 0) {
            submitTips('警告：请在列表中选择一条编辑的数据。', 'warning');
            return;
        }
        if (selectData.length > 1) {
            submitTips('警告：只能选择一条编辑的数据。', 'warning');
            return;
        }
        if ($rootScope.tenantId && (null == selectData[0].tenantId || selectData[0].tenantId == "")) {
            submitTips('警告：角色不可编辑(系统角色)', 'warning');
            return;
        }
        listService.editData($scope, gridApi, toUrl);
    }
    $scope.deleteData = function(tableGrid, gridApi, toUrl) {
        if (gridApi.selection.getSelectedRows().length < 1) {

            /*			bootbox.alert("请至少勾选一条数据！");*/
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
                    if ($rootScope.tenantId && (null == data.tenantId || data.tenantId == "")) {
                        submitTips('警告：角色不可删除(系统角色)', 'warning');
                        isDel = false;
                        return;
                    }
                    ids.push(data.id);
                });
                if (isDel) {
                    $http.delete(toUrl + "/" + ids).success(function() {
                        load();
                    });
                }


            }
        });

    }

    load();

    $scope.search = function() {
        load();
    };

    $scope.clearTree = function() {
        if ($scope.treeClickId) {
            $("#" + $scope.treeClickId).find("li").removeClass("ztree-active");
            $("#" + $scope.treeChildClickId).find("a").removeClass("curSelectedNode");
        }
    }

});

/**
 * 角色新增
 */
angular.module('huatek.controllers').controller('RoleAddController', function($scope, $location, $http, $routeParams, editService, $rootScope) {

    var addDataUrl = 'api/fwrole/add';
    var editDataUrl = 'api/fwrole/edit';
    var homeUrl = '/fwrole/home';

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    columnWrapArray.push(new multipleColumn(2, '补充信息'));
    /***
     * 定义
     */
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '角色名称', 'name', 'string', 1, '30'));
    var roleCode = new FormElement(1, '角色编码', 'rolecode', 'string', 1, '50');
    formFieldArray.push(roleCode);
    var orgType = new FormElement(1, '组织类型', 'orgType', 'string', 1, '30', 'select', null, null, null, 'dic.org_type');
    formFieldArray.push(orgType);
    var groupId = new FormElement(1, 'groupId', 'groupId', 'string', 1, '50');
    if (!$scope.editId) {
        groupId.value = $scope.passParams;
    }
    groupId.isShow = false;
    formFieldArray.push(groupId);
    /*    var isCooperate=new FormElement(1,'是否合作网点','isCooperate','string',1,'30','select')
     isCooperate.items = [{_returnField:"0", _showField:"否"},{_returnField:"1",_showField:"是"}];
     isCooperate.value="0";
     formFieldArray.push(isCooperate);*/
    formFieldArray.push(new FormElement(2, '角色描述', 'comments', 'string', -1, '200', 'textarea'));

    editService.init($scope, $http);


    editService.setFormFields($scope, formFieldArray);


    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);
        roleCode.readonly = true;
    } else {
        $scope.editId = -1;
    }


    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId, null, function(response){

        });
    }

    $scope.save = function() {
        editService.saveData($scope, addDataUrl, homeUrl);
    }

   
     /**
      * 页面初始化之后执行
      */
     $scope.$watch('$viewContentLoaded', function() {
         /*
          限制组织类型为当前登陆人所在组织类型及下级
         */
        var type = Number($rootScope.orgType);
        var orgTypeItems = orgType.items;
        var newOrgTypeItems = [];
        angular.forEach(orgTypeItems, function(dicData, index) {
            orgType.value = "";
            if (Number(dicData.code) >= type) {
                newOrgTypeItems.push(dicData);
            }
        });
        orgType.items = newOrgTypeItems;
    });

});


/**
 * 角色分配资源
 */
angular.module('huatek.controllers').controller('RoleAssginSourceController', function($scope, $location, $http, $routeParams, $rootScope, httpService) {

    var sourceMap = new Map();
    /*加载菜单树*/
    $scope.menuTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        httpService.get($scope, 'api/fwrole/assign/loadAllMenuWithRole/' + $scope.passParams).success(function(response) {
            for (var i = 0; i < response.data.length; i++) {
                sourceMap.put(response.data[i].id, response.data[i]);
            }
            /*超级管理员可以看到所有菜单*/
            var url = "api/menu/public/loadAllUserSource";
            if (undefined == $rootScope.tenantId || null == $rootScope.tenantId || "" == $rootScope.tenantId) {
                url = "api/menu/public/loadAll";
            }
            httpService.get($scope, url).success(function(response) {
                $scope.menuTree = response;
                var hasRoot = false;
                for (var i = 0; i < $scope.menuTree.length; i++) {
                    $scope.menuTree[i].nocheck = false;
                    /*  复选框回显参照按钮权限的isShow*/
                    $scope.menuTree[i].checked = sourceMap.get($scope.menuTree[i].id) != null;
                    if ($scope.menuTree[i].title == '菜单根目录') {
                        hasRoot = true;
                    }
                }
                if (!hasRoot) {
                    $scope.menuTree.push({ id: 0, title: "菜单根目录" });
                }
                $scope.initOrgTree();
            });
        });

    });
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        load();
    }
    $scope.initOrgTree = function() {
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox",
                chkboxType: { "Y": "ps", "N": "s" }
                /*nocheckInherit: true */
            },
            data: {

                key: {
                    name: "title",
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
        var tree = $.fn.zTree.init($("#roleMenuTree"), setting, $scope.menuTree).expandAll(true);
    }

    /**
     * 保存功能权限
     */
    $scope.checkNodeInfo = function() {
        var zTree = $.fn.zTree.getZTreeObj("roleMenuTree");
        var nodes = zTree.getCheckedNodes(true);
        var dataArr = [];
        for (var i = 0; i < nodes.length; i++) {
            if ($(nodes[i]).attr('id') * 1 > 0) {
                dataArr.push($(nodes[i]).attr('id'));
            }
        }
        var actionUrl = "api/fwrole/doSaveAssign/" + $scope.editId;
        httpService.post($scope, actionUrl, dataArr).success(function() {
            $scope['jsPanel'].close();
        });
    }

    /**
     * 权限套用
     */
    $scope.addApply = function() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = zTree.getCheckedNodes(true);
        var dataArr = [];
        for (var i = 0; i < nodes.length; i++) {
            if ($(nodes[i]).attr('id') * 1 > 0) {
                dataArr.push($(nodes[i]).attr('id'));
            }
        }
        var rolecode = $rootScope.rolecode
        var rolename = $rootScope.name;
        var actionUrl = "api/fwrole/doApplyAssign/" + $scope.editId + "/" + rolecode + "/" + rolename;
        $scope.promise = $http.post(actionUrl, dataArr).success(function(response) {
            $location.path("/fwrole/home");
        });
    }

    /**
     * 关闭
     */
    $scope.back = function() {
        $scope['jsPanel'].close();
    }
});

/**
 * 角色组新增Controller
 */
angular.module('huatek.controllers').controller('FwRoleGroupAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {

    var addDataUrl = 'api/fwRoleGroup/add';
    var editDataUrl = 'api/fwRoleGroup/edit';
    var homeUrl = '/fwrole/home';
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '名称', 'name', 'string', '1', '50', '', -1));
    var parentId = new FormElement(1, 'parentId', 'parentId', 'string', '1', '50', '', -1);
    parentId.isShow = false;
    if ($scope.passParams.type  && $scope.passParams.type == 'add') {
        parentId.value = $scope.passParams.id;
    }

    formFieldArray.push(parentId);
    var id = new FormElement(1, 'id', 'id', 'string', '1', '50', '', -1);
    id.isShow = false;
    if ($scope.passParams.type && $scope.passParams.type == 'edit') {
        id.value = $scope.passParams.id;
    }

    formFieldArray.push(id);
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    editService.init($scope);

    editService.setFormFields($scope, formFieldArray);

    if ($scope.passParams.type && $scope.passParams.type == 'edit') {
        editService.loadData($scope, editDataUrl, $scope.passParams.id);
    }

    /**
     * 更新
     */
    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.passParams.id, null, function() {
            httpService.get($scope, "api/fwrole/getRoleList").success(function(response) {
                {
                    $scope.roleList = response;
                    for (var i = 0; i < $scope.roleList.length; i++) {
                        $scope.roleList[i].open = true;
                    }
                    $scope.initRoleTree(function(){
                        /*新增角色组之后, 选中已添加角色组*/
                         var zTree = $.fn.zTree.getZTreeObj("roleTree");
                         var node = zTree.getNodes();
                         var nodes = zTree.transformToArray(node);
                         var dataArr = [];
                         for (var i = 0; i < nodes.length; i++) {
                            if ($(nodes[i]).attr('id') == $scope.passParams.id) {
                               $("#roleTree").find("li").removeClass("ztree-active");
                               $("#" + nodes[i].tId).addClass("ztree-active");
                            }
                         }
                    });
                }
            });
        });
    }

    /**
     * 新增
     */
    $scope.save = function() {
        editService.saveData($scope, addDataUrl, homeUrl, null, function(data) {
            httpService.get($scope, "api/fwrole/getRoleList").success(function(response) {
                {
                    $scope.roleList = response;
                    for (var i = 0; i < $scope.roleList.length; i++) {
                        $scope.roleList[i].open = true;
                    }
                    $scope.initRoleTree(function(){
                        /*新增角色组之后, 选中已添加角色组*/
                         var zTree = $.fn.zTree.getZTreeObj("roleTree");
                         var node = zTree.getNodes();
                         var nodes = zTree.transformToArray(node);
                         for (var i = 0; i < nodes.length; i++) {
                            if ($(nodes[i]).attr('id') == data.id) {
                               $("#roleTree").find("li").removeClass("ztree-active");
                               $("#" + nodes[i].tId).addClass("ztree-active");
                            }
                         }
                    });
                }
            });

        });
    }

    $scope.roleList = [];
    $scope.roleTree = null;
   /* $scope.$watch('$viewContentLoaded', function() {
        httpService.get($scope, "api/fwrole/getRoleList").success(function(response) {
            {
                $scope.roleList = response;
                for (var i = 0; i < $scope.roleList.length; i++) {
                    $scope.roleList[i].open = true;
                }
                $scope.initRoleTree();
            }
        });
    });*/

    $scope.initRoleTree = function(callback) {
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
        $.fn.zTree.init($("#roleTree"), setting, $scope.roleList);
        if (callback != null && typeof(callback) == "function") {
           callback();
        } 
    }

});

/**
 * 角色分配人员
 */
angular.module('huatek.controllers').controller('RoleAssginAcctController', function($scope, $location, $http, $routeParams, $rootScope, httpService, listService) {

    listService.init($scope, $http);
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    var acctName = new queryParam('账号', 'acctName', 'string', 'like');
    queryPage.addParam(acctName);
    var tenantId = new queryParam('tenantId', 'tenantId', 'string', '=');
    tenantId.isShow = false;
    tenantId.value = $rootScope.tenantId;
    queryPage.addParam(tenantId);
    $rootScope.searchCount = queryPage.queryParamList.length;
    listService.setQueryPage($scope, queryPage);

    /*未分配人员列表*/
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        columnDefs: [
            { name: '帐号', field: 'acctName', width: '10%', enableColumnMenu: false },
            { name: '姓名', field: 'userName', width: '10%', enableColumnMenu: false },
            { name: '任职机构', field: 'orgName', width: '20%', enableColumnMenu: false },
            { name: '任职部门', field: 'deptName', width: '20%', enableColumnMenu: false },
            { name: '邮箱', field: 'email', width: '20%', enableColumnMenu: false },
            { name: '电话', field: 'phone', width: '20%', enableColumnMenu: false },
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
        // listService.setGridHeight();
    };

    /*已分配人员列表*/

    var queryPage1 = new QueryPage(1, 10, "id desc", "false");
    var roleId = new queryParam('角色ID', 'fwRole.id', 'string', '=');
    roleId.value = $scope.editId;
    queryPage1.addParam(roleId);

    $scope.tableGrid1 = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        columnDefs: [
            { name: '帐号', field: 'acctName', width: '10%', enableColumnMenu: false },
            { name: '姓名', field: 'userName', width: '10%', enableColumnMenu: false },
            { name: '任职机构', field: 'orgName', width: '20%', enableColumnMenu: false },
            { name: '任职部门', field: 'deptName', width: '20%', enableColumnMenu: false },
            { name: '邮箱', field: 'email', width: '20%', enableColumnMenu: false },
            { name: '电话', field: 'phone', width: '20%', enableColumnMenu: false },
        ]

    };

    $scope.tableGrid1.onRegisterApi = function(gridApi) {
        $scope.gridApi1 = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage1.page = newPage;
            queryPage1.pageSize = pageSize;
            loadExist();
        });
    };
    /*上方列表查询*/
    $scope.searchAcct = function() {
        tenantId.value = $rootScope.tenantId;
        load();
    }

    /*加载人员列表, 角色已有人员数据*/
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/fwAccount/query', $scope.tableGrid);
    }
    load();

    /**
     * 加载已分配人员列表
     *
     * @type       {<type>}
     */
    var loadExist = $scope.loadExist = function() {
        httpService.post($scope, 'api/fwAcctRole/queryRoleAcct', queryPage1).success(function(response) {
            if (response.totalRows == undefined || response.totalRows == 0) {
                $scope.tableGrid1.data = [];
            } else {
                $scope.tableGrid1.data = response.content;
            }

            $scope.tableGrid1.totalItems = response.totalRows;
            if (response.page) {
                $scope.tableGrid1.paginationCurrentPage = response.page;
            } else {
                $scope.tableGrid1.paginationCurrentPage = 1;
            }
        });
    }
    loadExist();
    /**
     * 角色添加人员
     */
    $scope.addAcctToRole = function() {
        var selectData = $scope.gridApi.selection.getSelectedRows();
        var ids = [];
        $.each(selectData, function(index, value, array) {
            ids.push(value.id);
        });
        var addUrl = "/api/fwrole/addAcctToRole/" + ids + "/" + $scope.editId;
        if (selectData.length <= 0) {
            submitTips('请在上方列表中至少选择一条数据。', 'warning');
            return false;
        }
        httpService.get($scope, addUrl).success(function() {
            load();
            loadExist();
        });
    }

    /**
     * 移除人员
     */
    $scope.rmAcctFromRole = function() {
        var selectData = $scope.gridApi1.selection.getSelectedRows();
        var ids = [];
        $.each(selectData, function(index, value, array) {
            ids.push(value.acctId);
        });
        var delUrl = "/api/fwrole/rmAcctFromRole/" + ids + "/" + $scope.editId;
        if (selectData.length <= 0) {
            submitTips('请在下方列表中至少选择一条数据。', 'warning');
            return false;
        }
        httpService.get($scope, delUrl).success(function() {
            load();
            loadExist();
        }).error(function(error) {
            console.log("error" + error);
        });
    }

});