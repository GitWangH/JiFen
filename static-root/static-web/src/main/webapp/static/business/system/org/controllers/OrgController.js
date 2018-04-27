'use strict';

angular.module('huatek.controllers').controller('OrgController', function($scope, $location, $http, listService, $modal, $rootScope, httpService) {
    /***
     * 定义显示的表格. 
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true, 
        enableSelectAll : false,
        multiSelect: true,  //设置是否能够进行多选
        columnDefs: [
            { name: '机构名称', field: 'name', width: '10%', enableColumnMenu: false },
            { name: '机构编号', field: 'orgCode', width: '10%', enableColumnMenu: false },
            { name: '机构简称', field: 'shortName', width: '10%', enableColumnMenu: false },
           /* { name: '机构级别', field: 'orgLevel', width: '10%', enableColumnMenu: false },*/
            { name: '上级机构', field: 'parentId_', width: '10%', enableColumnMenu: false },
            { name: '机构类别', field: 'orgTypeName', width: '10%', enableColumnMenu: false, decode: { field: 'orgType', dataKey: 'dic.org_type' } },
            { name: '生成时间', field: 'createTime', width: '10%', enableColumnMenu: false },
            { name: '更新时间', field: 'updateTime', width: '15%', enableColumnMenu: false },
            { name: '状态', field: 'orgStatus', width: '5%', enableColumnMenu: false, cellFilter: "orgStatusFilter" },
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
    $scope.orgList = [];
    $scope.orgTree = null;
    $scope.loadOrgTree = function() {
        httpService.get($scope, "api/org/getAllOrg").success(function(response) {
            {
                $scope.orgList = response;
                for (var i = 0; i < $scope.orgList.length; i++) {
                    /*if($scope.orgList[i].orgType == '2'){
                         $scope.orgList[i].shortName = $scope.orgList[i].name;
                    }*/
                    if(!$scope.orgList[i].shortName){
                        $scope.orgList[i].shortName = $scope.orgList[i].name;
                    }
                    $scope.orgList[i].open = true;
                }
                $scope.initOrgTree(); //加载orgTree
            }
        });
    }
    $scope.$watch('$viewContentLoaded', function() {
        $scope.loadOrgTree();
    });
    /*定义变量传递机构树所选参数*/
    $scope.treeParam = new Object();
    /*记录已选中节点*/
    $scope.treeClickId = undefined;
    $scope.treeChildClickId = undefined;
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        parentIdParam.value = treeNode.id;
        $scope.treeParam.org = treeNode.id;
        $scope.orgStatusVal = treeNode.orgStatus;
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
                    pIdKey: "parentId",
                    rootPId: null
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#orgTree"), setting, $scope.orgList);
    }


    listService.init($scope, $http);

    var queryPage = new QueryPage(1, 10, "id desc", "false");

    queryPage.addParam(new queryParam('机构名称', 'name', 'string', 'like'));
    var parentIdParam = new queryParam('上级机构', 'parent.id', 'string', '=');
    parentIdParam.isShow = false;
    queryPage.addParam(parentIdParam);
    var orgStatusParam = new queryParam('状态', 'orgStatus', 'string');
    orgStatusParam.componentType = 'select';
    orgStatusParam.items = [{ "_showField": "激活", "_returnField": "2" }, { "_showField": "禁用", "_returnField": "1" }];
    queryPage.addParam(orgStatusParam);

    listService.setQueryPage($scope, queryPage);
    

    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/org/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/org/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/org/delete', 'deleteData'));
    btnArray.push(new pageButton('激活', 'active', '/org/active', 'activeOrg'));
    btnArray.push(new pageButton('禁用', 'disable', '/org/disable', 'disableOrg'));

    listService.setButtonList($scope, btnArray);


    $scope.execute = function(operation, param) {
            var selectData = $scope.gridApi.selection.getSelectedRows();
            if (operation == 'addData') {

                /*如果选择上级机构, 判断上级机构是否禁用, 如果禁用则不进行新增操作*/
                if (undefined != $scope.orgStatusVal && null != $scope.orgStatusVal && "" != $scope.orgStatusVal && "1" == $scope.orgStatusVal) {
                    bootbox.alert('警告：所选上级机构已禁用, 不能添加下级机构。');
                    return;
                }
                /*获取当前树展开状态*/
                var statusMap = $scope.getTreeStatus();
                $scope.treeParam.statusMap = statusMap;
                listService.addData($scope, new popup("新增机构", "/org/add", $scope.treeParam, null, null, function() {
                   /* $scope.loadOrgTree();
                    load();*/
                    load();
                }));
            } else if (operation == 'activeOrg') {

                this.active($scope, $scope.gridApi, '/org/active', 'A');
            } else if (operation == 'disableOrg') {

                this.active($scope, $scope.gridApi, '/org/disable', 'D');
            } else if (operation == 'editData') {

                if (listService.selectOne($scope.gridApi) && selectData[0].orgStatus == '1') {
                    bootbox.alert('警告：该数据已经禁用，不能操作。');
                    return;
                }
                /*获取当前树展开状态*/
                var statusMap = $scope.getTreeStatus();
                var obj = new Object();
                obj.statusMap = statusMap;
                listService.editData($scope, $scope.gridApi, new popup("编辑机构", "/org/edit", obj, null, null, function() {
                   /* $scope.loadOrgTree();*/
                    load();
                }));
            } else if (operation == 'deleteData') {

                /*删除, 机构下有人员, 部门, 岗位, 下级机构则不能进行删除*/
                if (listService.selectOne($scope.gridApi)) {
                    listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/org/delete');
                }
            }
        }
        /***
         * 激活或禁用
         */
    $scope.active = function(scope, gridApi, toUrl, val) {

        /*获取当前选择的数据.*/
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length > 1) {
            bootbox.alert('警告：不能选择多条数据用于激活或者禁用。');
            return;
        }
        if (selectData.length == 0) {
            bootbox.alert('请在列表中选择一条用于激活或者禁用的数据。');
            return;
        }

          var obj = selectData[0];//只能禁用一个
        //var obj = selectData;//支持多个禁用

        if ('A' == val) {
            if (obj.orgStatus == '2') {
                bootbox.alert('警告：该数据已经激活，不能操作。');
                return;
            }
        } else if ('D' == val) {
            if (obj.orgStatus == '1') {
                bootbox.alert('警告：该数据已经禁用，不能操作。');
                return;
            }
        }
        var actionUrl = "api/org/active/" + obj.id + "/" + val;
        httpService.post(scope, actionUrl).success(function(response) {
            load();
            /*重新加载机构树*/
            httpService.get($scope, "api/org/getAllOrg").success(function(response) {
                {
                    $scope.orgList = response;
                    for (var i = 0; i < $scope.orgList.length; i++) {
                        $scope.orgList[i].open = true;
                    }
                    $scope.initOrgTree();
                }
            });
        });
    }
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/org/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
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
        var zTree = $.fn.zTree.getZTreeObj("orgTree");
        var node = zTree.getNodes();
        var nodes = zTree.transformToArray(node);
        angular.forEach(nodes, function(data, index) {
            statusMap.put(data.id, data.open);
        });
        return statusMap;
    }

}).filter("orgStatusFilter", function() { /*机构状态过滤器*/
    var filterFunction = function(val) {
        if (val == 2) {
            return '激活';
        } else if (val == 1) {
            return '禁用';
        }
    };
    return filterFunction;
});

/**
 * 机构新增Controller
 */
angular.module('huatek.controllers').controller('OrgAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService, listService) {

    var addDataUrl = 'api/org/add';
    var editDataUrl = 'api/org/edit';
    var homeUrl = '/org/home';
    var getTreeParamUrl = '/api/org/public/getTreeParamUrl';
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '机构名称', 'name', 'string', '1', '30', '', -1));
    formFieldArray.push(new FormElement(1, '机构编号', 'orgCode', 'string', '1', '30', '', -1));
    formFieldArray.push(new FormElement(1, '机构简称', 'shortName', 'string', '1', '30', '', -1));
    var companyName=new FormElement(1, '单位名称', 'companyName', 'string', '0', '30', '', -1);
    companyName.isShow = false;
    formFieldArray.push(companyName);
    var parentId = new FormElement(1, '上级机构', 'parentId', 'int', '1', '30');
    parentId.isShow = false;
    formFieldArray.push(parentId);
    var parentName = new FormElement(1, '上级机构', 'parentName', 'string', '1', '30', 'autocomplete', null, null, null, 'api/org/public/selectAuto');
    parentName.showField = "name";
    parentName.returnField = "name";
    formFieldArray.push(parentName);
    var orgType = new FormElement(1, '机构类别', 'orgType', 'string', '1', '30', 'select', null, null, null, 'dic.org_type');
    formFieldArray.push(orgType);
    var tenantId = new FormElement(1, '租户ID', 'tenantId', 'string', '0', '30', 'string');
    tenantId.isShow = false;
    formFieldArray.push(tenantId);
    var orgStatus = new FormElement(1, '机构状态', 'orgStatus', 'string', '0', '30');
    orgStatus.isShow = false;
    orgStatus.value = '2';
    formFieldArray.push(orgStatus);
    var remark = new FormElement(1, '备注', 'remark', 'string', '0', '120', 'textarea');
    formFieldArray.push(remark);

    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    editService.init($scope);



    editService.setFormFields($scope, formFieldArray);

    /**
     * 获取字典数据
     */
    var fieldMap = editService.getFieldMap($scope);
    /*fieldMap.get("parentId").dropDataUrl = '/api/org/public/select';*/

    if ($scope.editId) {
        /*设置上级机构/机构编码不可编辑*/
        fieldMap.get("parentId").readonly = true;
        fieldMap.get("parentName").readonly = true;
        fieldMap.get("orgType").readonly = true;
        parentName._choosed = true;
        /*fieldMap.get("orgCode").readonly = true;*/
        editService.loadData($scope, editDataUrl, $scope.editId);
    } else {
        $scope.editId = -1;
    }

    /**
     * 更新
     */
    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId, null, function() {
            $scope.loadOrgTree(function(){
                /*新增机构之后, 选中已添加机构*/
                 var zTree = $.fn.zTree.getZTreeObj("orgTree");
                 var node = zTree.getNodes();
                 var nodes = zTree.transformToArray(node);
                 var dataArr = [];
                 for (var i = 0; i < nodes.length; i++) {
                    if ($(nodes[i]).attr('id') == $scope.editId) {
                       $("#orgTree").find("li").removeClass("ztree-active");
                       $("#" + nodes[i].tId).addClass("ztree-active");
                    }
                 }
            });
        });
    }

    /**
     * 新增
     */
    $scope.save = function() {
            editService.saveData($scope, addDataUrl, homeUrl, null, function(response){
                if(response.type == 'success'){
                    $scope.loadOrgTree(function(){
                        /*新增机构之后, 选中已添加机构*/
                         var zTree = $.fn.zTree.getZTreeObj("orgTree");
                         var node = zTree.getNodes();
                         var nodes = zTree.transformToArray(node);
                         var dataArr = [];
                         for (var i = 0; i < nodes.length; i++) {
                            if ($(nodes[i]).attr('id') == response.id) {
                               $("#orgTree").find("li").removeClass("ztree-active");
                               $("#" + nodes[i].tId).addClass("ztree-active");
                            }
                         }
                    });
                    /*重新加载*/
                    if(response.orgType == '3'){
                         httpService.get($scope, "/api/org/getProData").success(function(data) {
                            $rootScope.proDatas = data;
                            if (data.length > 0) {
                                $rootScope.proDatasShow = true;
                                if(!$rootScope.currProName){
                                    $rootScope.currProName = data[0].name;
                                }
                            } else {
                                $rootScope.proDatasShow = false;
                            }
                        });
                    }
                }
                 
            });
    }
    var fieldMap = editService.getFieldMap($scope);
    /**
     * 根据所选参数加载机构或部门数据
     */
    $scope.loadSelectTreeData = function(treeParam) {

        if (undefined != treeParam) {
            var jsonParam = new Object();
            if (undefined != treeParam.org) {
                jsonParam.orgId = treeParam.org;
            }

            httpService.post($scope, getTreeParamUrl, jsonParam).success(function(data) {

                /*如果选择为机构则机构进行回填*/
                /*机构*/
                if (cnex4_isNotEmpty_str(data.orgId)) {
                    /*机构模糊匹配*/
                    fieldMap.get("parentId").value = data.orgId;
                    fieldMap.get("parentId").readonly = true;
                    fieldMap.get("parentName").value = data.orgName;
                    fieldMap.get("parentName").readonly = true;
                    fieldMap.get("parentName").dropDataUrl = null;
                    parentName._choosed = true;
                    /*
                      清空上次选中机构类别, 限制机构类别为当前选中机构下级
                     */
                    var type = Number(data.type) + 1;
                    var orgTypeItems = $scope.orgTypeItemsOriginal;
                    var newOrgTypeItems = [];
                    angular.forEach(orgTypeItems, function(dicData, index) {
                        orgType.value = "";
                        if (Number(dicData.code) == type) {
                            newOrgTypeItems.push(dicData);
                            orgType.items = newOrgTypeItems;
                            return;
                        }
                    });
                }

            });
        }
    }

    /**
     * 模糊匹配时间监听
     */

    $scope.$watch('$viewContentLoaded', function() {
        $scope.orgTypeItemsOriginal = orgType.items;
        /*当选择机构树之后进行新增, 默认回填选择机构*/
        $scope.loadSelectTreeData($scope.passParams);
    });

    $scope.$on('autocomplete:selected', function(event, data) {
        /*此时模糊检索组件的事件被响应*/
        if (data._componentsName == 'parentName') {
            parentId.value = data.code;
            /*
              清空上次选中机构类别, 限制机构类别为当前选中机构下级
             */
            var type = Number(data.type) + 1;
            var orgTypeItems = $scope.orgTypeItemsOriginal;
            var newOrgTypeItems = [];
            angular.forEach(orgTypeItems, function(dicData, index) {
                orgType.value = "";
                if (Number(dicData.code) == type) {
                    newOrgTypeItems.push(dicData);
                    orgType.items = newOrgTypeItems;
                    return;
                }
            });
        }
    });

    $scope.orgList = [];
    $scope.orgTree = null;
    $scope.loadOrgTree = function(callBackFun){
        httpService.get($scope, "api/org/getAllOrg").success(function(response) {
            {
                $scope.orgList = response;
                for (var i = 0; i < $scope.orgList.length; i++) {
                    /*if($scope.orgList[i].orgType == '2'){
                         $scope.orgList[i].shortName = $scope.orgList[i].name;
                    }*/
                    if(!$scope.orgList[i].shortName){
                        $scope.orgList[i].shortName = $scope.orgList[i].name;
                    }
                    $scope.orgList[i].open = $scope.passParams.statusMap.get($scope.orgList[i].id);
                }
                $scope.initOrgTree(callBackFun);
                 
            }
        });
    }

    $scope.initOrgTree = function(callBackFun) {
        var setting = {
            data: {
                key: {
                    name: "shortName"
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentId",
                    rootPId: null
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#orgTree"), setting, $scope.orgList);
        if (callBackFun != null && typeof(callBackFun) == "function") {
           callBackFun();
        }   
    }

});