'use strict';

angular.module('huatek.controllers').controller('FwDepartmentController', function($scope, $location, $http, $rootScope, listService, shareData, $modal, httpService) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        columnDefs: [
            { name: '部门编码', field: 'deptCode', width: '20%', enableColumnMenu: false },
            { name: '部门名称', field: 'deptName', width: '20%', enableColumnMenu: false },
            { name: '所属组织', field: 'orgName', width: '20%', enableColumnMenu: false },
            { name: '上级部门', field: 'parentName', width: '20%', enableColumnMenu: false },
            { name: '备注', field: 'remark', width: '*', enableColumnMenu: false },

        ]

    };


    listService.init($scope, $http, $rootScope);

    $scope.orgList = [];
    $scope.orgTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        httpService.get($scope, "api/org/getOrgAndDepartment").success(function(response) {
            {
                $scope.orgList = response;
                for (var i = 0; i < $scope.orgList.length; i++) {
                    $scope.orgList[i].open = true;
                    if(!$scope.orgList[i].shortName){
                        $scope.orgList[i].shortName = $scope.orgList[i].name;
                    }
                }
                $scope.initOrgTree();
            }
        });
    });

    /*定义变量传递组织树所选参数*/
    $scope.treeParam = new Object();
     /*记录已选中节点*/
    $scope.treeClickId = undefined;
    $scope.treeChildClickId = undefined;
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        /*orgIdParam.value=treeNode.id;*/
        /*如果选择为机构则为机构参数赋值, 如果为部门则为部门赋值*/
        if (treeNode.id.indexOf('_') >= 0) {
            parentId.value = treeNode.name;
            orgIdParam.value = '';
            $scope.treeParam.dept = treeNode.id_;
            $scope.treeParam.org = undefined;
        } else {
            orgIdParam.value = treeNode.id;
            parentId.value = "";
            $scope.treeParam.org = treeNode.id;
            $scope.treeParam.dept = undefined;
        }
        $scope.treeClickId = treeId;
        $scope.treeChildClickId = treeNode.tId;
        load();
    }
    $scope.initOrgTree = function() {
        var setting = {
            data: {
                key: {
                    name: "shortName"
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
        $.fn.zTree.init($("#deptTree"), setting, $scope.orgList);
    }

    var queryPage = new QueryPage(1, 10, "id desc", "false");


    var name = new queryParam('部门名称', 'deptName', 'string', 'like');
    name.dropDataUrl = 'api/fwDepartment/getParamDepartmentNew/1';
    name.showField = "deptName";
    name.returnField = "deptName"
    queryPage.addParam(name);

    var parentIdParam = new queryParam('租户ID', 'parent.id', 'string', '=');
    parentIdParam.isShow = false;
    queryPage.addParam(parentIdParam);

    var parentId = new queryParam('上级部门', 'parent.deptName', 'string', '=');
    parentId.dropDataUrl = 'api/fwDepartment/getParamDepartmentNew/1';
    parentId.showField = "deptName";
    parentId.returnField = "deptName"
    queryPage.addParam(parentId);

    /* var code=new queryParam('部门编码','deptCode','string','like');
     code.dropDataUrl='api/fwDepartment/getParamDepartment/2';
     code.showField="deptCode";
     code.returnField="deptCode"
     queryPage.addParam(code);*/
    var orgIdParam = new queryParam('机构ID', 'fwOrg.id', 'string', '=');
    orgIdParam.isShow = false;
    queryPage.addParam(orgIdParam);
    var tenantIdParam = new queryParam('租户ID', 'tenantId', 'string', '=');
    tenantIdParam.isShow = false;
    tenantIdParam.value = $rootScope.tenantId;
    queryPage.addParam(tenantIdParam);


    $rootScope.searchCount = queryPage.queryParamList.length;

    listService.setQueryPage($scope, queryPage);


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


    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/fwDepartment/add', 'addData'));
    btnArray.push(new pageButton('修改', 'edit', '/fwDepartment/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/fwDepartment/delete', 'deleteData'));

    /*btnArray.push(new pageButton('查看详情','queryMsg','/fwDepartment/queryMsg','queryMsgData'));*/

    listService.setButtonList($scope, btnArray);


    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            /*获取当前树展开状态*/
            var statusMap = $scope.getTreeStatus();
            $scope.treeParam.statusMap = statusMap;
            listService.addData($scope, new popup("新增部门", "/fwDepartment/add", $scope.treeParam));
        } else if (operation == 'editData') {
            /*获取当前树展开状态*/
            var statusMap = $scope.getTreeStatus();
            var obj = new Object();
            obj.statusMap = statusMap;
            listService.editData($scope, $scope.gridApi, new popup("编辑部门", '/fwDepartment/edit', obj));
        } else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/fwDepartment/delete');
        }
    }

    var load = $scope.load = function() {
        listService.loadData($scope, 'api/fwDepartment/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        tenantIdParam.value = $rootScope.tenantId;
        if(!orgIdParam.value){
            orgIdParam.value = $rootScope.orgId;
        }
        load();
    };

    $scope.clearTree = function(){
        if($scope.treeClickId){
            $("#" + $scope.treeClickId).find("li").removeClass("ztree-active");
            $("#" + $scope.treeChildClickId).find("a").removeClass("curSelectedNode");
        }
    }

    /**
     * 获取当前树展开状态
     */
    $scope.getTreeStatus = function(){
        var statusMap = new Map();
        var zTree = $.fn.zTree.getZTreeObj("deptTree");
        var node = zTree.getNodes();
        var nodes = zTree.transformToArray(node);
        angular.forEach(nodes, function(data, index) {
            statusMap.put(data.id, data.open);
        });
        return statusMap;
    }

});


angular.module('huatek.controllers').controller('FwDepartmentAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService, listService) {

    var addDataUrl = 'api/fwDepartment/add';
    var editDataUrl = 'api/fwDepartment/edit';
    var homeUrl = '/fwDepartment/home';
    var getTreeParamUrl = '/api/org/public/getTreeParamUrl';

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    columnWrapArray.push(new multipleColumn(2, '补充信息'));
    $scope.columnWrapArray = columnWrapArray;
    $scope.getParentId = function(val) {
        if (!val) {
            return;
        }
        editService.initParams($scope, "api/fwDepartment/getDepartment/parentId/" + val);
    }

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '编码', 'deptCode', 'string', 1, '30'));
    formFieldArray.push(new FormElement(1, '名称', 'deptName', 'string', 1, '100'));

    var orgId = new FormElement(1, '所属组织ID', 'orgId', 'string', 1, '50');
    orgId.isShow = false;
    formFieldArray.push(orgId);

    var orgName = new FormElement(1, '所属组织', 'orgName', 'string', 1, '50', 'autocomplete', null, null, null, 'api/org/getTheParentName');
    orgName.showField = 'name';
    orgName.returnField = 'name';
    formFieldArray.push(orgName);

    var parentName = new FormElement(1, '所属部门名称', 'parentName', 'string', 0, '50');
    parentName.isShow = false;
    formFieldArray.push(parentName);

    var parentId = new FormElement(1, '上级部门', 'parentId', 'string', 0, '50', 'querySelect');
    parentId.showField = 'name';
    parentId.returnField = 'code';
    parentId.displayValue = parentName.value;
    formFieldArray.push(parentId);

    formFieldArray.push(new FormElement(2, '备注', 'remark', 'string', 0, '400'));


    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;


    editService.init($scope);


    editService.setFormFields($scope, formFieldArray);

    var fieldMap = editService.getFieldMap($scope);

    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId, function() {
            orgId.displayValue = orgName.value;
            parentId.displayValue = parentName.value;
            fieldMap.get("deptCode").readonly = true;
            fieldMap.get("parentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + orgId.value;
        });
    } else {
        $scope.editId = -1;
        /*如果新增时选择机构或部门, 组织和部门默认选择不能修改*/

    }

    /*load数据*/
    var load = function() {
        listService.loadData($scope, 'api/fwDepartment/query', $scope.tableGrid);
    }

    /**
     * 更新
     */
    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId, null, function() {
            $scope.loadOrgAndDeptTree(function(){
                /*新增部门之后, 选中已添加部门*/
                 var zTree = $.fn.zTree.getZTreeObj("deptTree");
                 var node = zTree.getNodes();
                 var nodes = zTree.transformToArray(node);
                 var dataArr = [];
                 for (var i = 0; i < nodes.length; i++) {
                    var id_ = $(nodes[i]).attr('id') + "";
                    if (id_.indexOf($scope.editId+"_") >= 0 ) {
                       $("#deptTree").find("li").removeClass("ztree-active");
                       $("#" + nodes[i].tId).addClass("ztree-active");
                    }
                 }
            });
        });
    }

    /**
     * 保存
     */
    $scope.save = function() {
        editService.saveData($scope, addDataUrl, null, null, function(response) {
            if(response.type == 'success'){
                $scope.loadOrgAndDeptTree(function(){
                    /*新增部门之后, 选中已添加部门*/
                     var zTree = $.fn.zTree.getZTreeObj("deptTree");
                     var node = zTree.getNodes();
                     var nodes = zTree.transformToArray(node);
                     var dataArr = [];
                     for (var i = 0; i < nodes.length; i++) {
                        var id_ = $(nodes[i]).attr('id') + "";
                        if (id_.indexOf(response.id+"_") >= 0) {
                           $("#deptTree").find("li").removeClass("ztree-active");
                           $("#" + nodes[i].tId).addClass("ztree-active");
                        }
                     }
                 });
            }
        });
    }

    /**
     * 加载组织部门树
     */
    $scope.orgList = [];
    $scope.loadOrgAndDeptTree = function(callbackFun){
        httpService.get($scope, "api/org/getOrgAndDepartment").success(function(response) {
            $scope.orgList = response;
            for (var i = 0; i < $scope.orgList.length; i++) {
                $scope.orgList[i].open = true;
                if(!$scope.orgList[i].shortName){
                    $scope.orgList[i].shortName = $scope.orgList[i].name;
                }
                if($scope.orgList[i].isDept == 'Y'){
                    $scope.orgList[i].open = $scope.passParams.statusMap.get($scope.orgList[i].id_+'_'+$scope.orgList[i].pId);
                }else {
                    $scope.orgList[i].open = $scope.passParams.statusMap.get($scope.orgList[i].id);
                }
            }
            $scope.initOrgTree(callbackFun);
       });
    }

    /*加载机构树*/
    $scope.initOrgTree = function(callbackFun) {
        var setting = {
            data: {
                key: {
                    name: "shortName"
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
        $.fn.zTree.init($("#deptTree"), setting, $scope.orgList);
        if (callbackFun != null && typeof(callbackFun) == "function") {
           callbackFun();
        }
    }

    editService.initParams($scope, "api/fwDepartment/getParam");

    /*组织模糊匹配*/
    fieldMap.get("orgId").dropDataUrl = '/api/org/public/select';
    /*部门模糊匹配*/
    fieldMap.get("parentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + $rootScope.orgId;

    /**
     * 根据所选参数加载机构或部门数据
     */
    $scope.loadSelectTreeData = function(treeParam) {

        if (undefined != treeParam) {
            var jsonParam = new Object();
            if (undefined != treeParam.org) {
                jsonParam.orgId = treeParam.org;
            }
            if (undefined != treeParam.dept) {
                jsonParam.id = treeParam.dept;
            }

            httpService.post($scope, getTreeParamUrl, jsonParam).success(function(data) {
                /*如果选择为机构则机构readonly,部门可填, 选择为部门则机构部门回填均为readonly*/
                /*机构*/
                if (cnex4_isNotEmpty_str(data.orgId)) {
                    orgId.value = data.orgId;
                    orgId.displayValue = data.orgName;
                    orgName.value = data.orgName;
                    /*机构readonly*/
                    orgId.readonly = true;
                    orgName._choosed = true;
                    fieldMap.get("parentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + data.orgId;
                }
                /*部门*/
                if (cnex4_isNotEmpty_str(data.deptId)) {
                    parentId.value = data.deptId;
                    parentId.displayValue = data.deptName;
                    parentName.value = data.deptName;
                    /*部门readonly*/
                    parentId.readonly = true;
                }
            });
        }
    }

    /*当选择机构树之后进行新增, 默认回填选择机构或部门, 并设置为readonly*/
    $scope.loadSelectTreeData($scope.passParams);

    /**
     * 模糊匹配时间监听
     */
    $scope.$on('autocomplete:selected', function(event, data) {
        /*此时模糊检索组件的事件被响应*/
        if (data._componentsName == 'orgName') {

            orgId.value = data.value;
            fieldMap.get("parentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + data.value;
        }
    });

});

/****
 * 详情控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('FwDepartmentDetailController', function($scope, $location, $http, $routeParams, editService, $rootScope, shareData) {

    var editDataUrl = 'api/fwDepartment/edit';
    var homeUrl = '/fwDepartment/home';


    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    columnWrapArray.push(new multipleColumn(2, '补充信息'));
    $scope.columnWrapArray = columnWrapArray;


    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '部门名称', 'deptName', 'string', 1, '100'));
    formFieldArray.push(new FormElement(1, '部门编码', 'deptCode', 'string', 1, '30'));
    formFieldArray.push(new FormElement(1, '上级部门', 'parentName', 'string', 0, '30'));
    formFieldArray.push(new FormElement(2, '备注', 'remark', 'string', 0, '400', 'textarea'));


    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;


    editService.init($scope, $location, $http);


    editService.setFormFields($scope, formFieldArray);

    /***
     * 定义获取需要编辑的内容.
     */
    var formFieldReadOnly = new Array("deptName", "deptCode", "parentName", "remark");
    setReadOnly(formFieldReadOnly, editService.getFieldMap());
    $scope.closeId = 0;
    /*设置关闭按钮*/
    editService.loadData($scope, editDataUrl, shareData.id);
});