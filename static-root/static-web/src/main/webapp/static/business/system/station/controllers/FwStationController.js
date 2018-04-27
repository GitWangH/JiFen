'use strict';

angular.module('huatek.controllers').controller('FwStationController', function($scope, $location, $http, $rootScope, listService, shareData, $modal, httpService) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        columnDefs: [
            { name: '编码', field: 'code', width: '10%', enableColumnMenu: false },
            { name: '名称', field: 'name', width: '20%', enableColumnMenu: false },
            { name: '所属组织', field: 'orgName', width: '20%', enableColumnMenu: false },
           /* { name: '所属部门', field: 'departmentName', width: '20%', enableColumnMenu: false },*/
            { name: '备注', field: 'remark', width: '*', enableColumnMenu: false },
        ]

    };


    listService.init($scope);

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
        /*如果选择为机构则为机构参数赋值, 如果为部门则为部门赋值*/
        if (treeNode.id.indexOf('_') >= 0) {
            departmentId.value = treeNode.id_;
            orgIdParam.value = '';
            $scope.treeParam.dept = treeNode.id_;
            $scope.treeParam.org = undefined;
        } else {
            orgIdParam.value = treeNode.id;
            departmentId.value = "";
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
        $.fn.zTree.init($("#stationTree"), setting, $scope.orgList);
    }

    var queryPage = new QueryPage(1, 10, "id desc", "false");


    var name = new queryParam('岗位名称', 'name', 'string', 'like');
    name.dropDataUrl = 'api/fwDepartment/getParamStation/1';
    name.showField = "deptName";
    name.returnField = "deptName"
    queryPage.addParam(name);
    /*var code=new queryParam('岗位编码','code','string','like');
    code.dropDataUrl='api/fwStation/getParamDepartment/2';
    code.showField="deptCode";
    code.returnField="deptCode"
    queryPage.addParam(code);*/
    var orgIdParam = new queryParam('机构ID', 'fwOrg.id', 'string', '=');
    orgIdParam.isShow = false;
    queryPage.addParam(orgIdParam);
    var departmentId = new queryParam('部门ID', 'fwDepartment.id', 'string', '=');
    departmentId.isShow = false;
    queryPage.addParam(departmentId);
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
    btnArray.push(new pageButton('新增', 'add', '/fwStation/add', 'addData'));
    btnArray.push(new pageButton('修改', 'edit', '/fwStation/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/fwStation/delete', 'deleteData'));
    btnArray.push(new pageButton('分配人员', 'assignAcct', '/fwStation/assignAcct', 'assignAcct'));

    /*btnArray.push(new pageButton('查看详情','queryMsg','/fwDepartment/queryMsg','queryMsgData'));*/

    listService.setButtonList($scope, btnArray);


    $scope.execute = function(operation, param) {
        if (operation == 'addData') {

            listService.addData($scope, new popup("新增岗位", '/fwStation/add', $scope.treeParam));
        } else if (operation == 'editData') {
            listService.editData($scope, $scope.gridApi, new popup("修改岗位", '/fwStation/edit'));
        } else if (operation == 'deleteData') {

            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/fwStation/delete');

            /*this.operate($scope.gridApi, 'api/fwStation/delete',1);*/
        } else if (operation == 'queryMsgData') {

            /*listService.deleteData($scope.tableGrid, $scope.gridApi, 'api/fwStation/delete');*/
            this.operate($scope.gridApi, 'api/fwStation/queryMsg', 2);
        } else if (operation == 'assignAcct') {

            listService.editData($scope, $scope.gridApi, new popup("分配人员", '/fwStation/assignAcct'));
        }
    }
    $scope.operate = function(gridApi, toUrl, type) {
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length == 0) {
            submitTips('请在列表中选择一条数据。', 'warning');
            return false;
        }
        if (selectData.length > 1) {
            submitTips('警告：不能选择多条数据。', 'warning');
            return false;
        }
        var btnObj = {
            title: '岗位详细信息',
            controller: 'FwStationDetailController',
            template: 'public/template_modal_add.html' + '?t=' + huatek.version,
            content: '岗位详细'
        };
        var infoModal = listService.showModalForNoMenu(btnObj, $modal);

        /*显示modal窗口*/
        infoModal.$promise.then(infoModal.show);
        shareData.modal = infoModal;
        shareData.id = selectData[0].id;
    }
    $scope.showDetailMsg = function(entity) {
        var btnObj = {
            title: '岗位详细信息',
            controller: 'FwStationDetailController',
            template: 'public/template_modal_add.html' + '?t=' + huatek.version,
            content: '岗位详细'
        };
        var infoModal = listService.showModalForNoMenu(btnObj, $modal);

        /*显示modal窗口*/
        infoModal.$promise.then(infoModal.show);
        shareData.modal = infoModal;
        shareData.id = entity.id;
    }

    var load = $scope.load = function() {
        listService.loadData($scope, 'api/fwStation/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
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

});


angular.module('huatek.controllers').controller('FwStationAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {

    var addDataUrl = 'api/fwStation/add';
    var editDataUrl = 'api/fwStation/edit';
    var homeUrl = '/fwStation/home';
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
    }

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '岗位名称', 'name', 'string', 1, '100'));
    formFieldArray.push(new FormElement(1, '岗位编码', 'code', 'string', 1, '30'));
    var orgId = new FormElement(1, '所属组织Id', 'orgId', 'string', 1, '30');
    orgId.isShow = false;
    formFieldArray.push(orgId);

    /*var orgId=new FormElement(1,'所属组织','orgId','string',0,'30','querySelect');
		  orgId.showField = 'name';
		  orgId.returnField = 'code';
      orgId.displayValue = orgName.value;
		  formFieldArray.push(orgId);*/
    var orgName = new FormElement(1, '所属组织名称', 'orgName', 'string', 1, '30', 'autocomplete', null, null, null, 'api/org/getTheParentName');
    orgId.showField = 'name';
    orgId.returnField = 'name';
    formFieldArray.push(orgName);

    var departmentName = new FormElement(1, '所属部门名称', 'departmentName', 'string', 0, '30');
    departmentName.isShow = false;
    formFieldArray.push(departmentName);

    var departmentId = new FormElement(1, '所属部门', 'departmentId', 'string', 0, '30', 'querySelect');
    departmentId.showField = 'name';
    departmentId.returnField = 'code';
    departmentId.isShow = false;
    departmentId.displayValue = departmentName.value;
    formFieldArray.push(departmentId);

    formFieldArray.push(new FormElement(2, '备注', 'remark', 'string', 0, '400', 'textarea'));
    var tenantId = new FormElement(1, 'tenantId', 'tenantId', 'string', 0, '30');
    tenantId.isShow = false;
    formFieldArray.push(tenantId);

    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;


    editService.init($scope, $http);


    editService.setFormFields($scope, formFieldArray);

    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId, function() {
            departmentId.displayValue = departmentName.value;
            fieldMap.get("departmentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + orgId.value;
        });
    } else {
        $scope.editId = -1;
    }


    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    }

    $scope.save = function() {
        editService.saveData($scope, addDataUrl, homeUrl);
    }


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
                    /*orgId.displayValue = data.orgName;*/
                    orgName.value = data.orgName;
                    /*机构readonly*/
                    orgId.readonly = true;
                    orgName._choosed = true;
                    fieldMap.get("departmentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + data.orgId;
                }
                /*部门*/
                if (cnex4_isNotEmpty_str(data.deptId)) {
                    departmentId.value = data.deptId;
                    departmentId.displayValue = data.deptName;
                    departmentName.value = data.deptName;
                    /*部门readonly*/
                    departmentId.readonly = true;
                }
            });
        }
    }

    /**
     * 模糊匹配时间监听
     */
    $scope.$on('autocomplete:selected', function(event, data) {
        /*此时模糊检索组件的事件被响应*/
        if (data._componentsName == 'orgName') {
            orgId.value = data.value;
            fieldMap.get("departmentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + data.value;
        }
    });

    /*当选择机构树之后进行新增, 默认回填选择机构或部门, 并设置为readonly*/
    $scope.loadSelectTreeData($scope.passParams);

    var fieldMap = editService.getFieldMap($scope);
    /*组织模糊匹配*/
    fieldMap.get("orgId").dropDataUrl = '/api/org/public/selectAuto';
    /*部门模糊匹配*/
    fieldMap.get("departmentId").dropDataUrl = '/api/fwDepartment/getDepartmentLikeName/' + $rootScope.orgId;
});

/****
 * 详情控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('FwStationDetailController', function($scope, $location, $http, $routeParams, editService, $rootScope, shareData, httpService) {

    var editDataUrl = 'api/fwStation/edit';
    var homeUrl = '/fwStation/home';


    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    columnWrapArray.push(new multipleColumn(2, '补充信息'));
    $scope.columnWrapArray = columnWrapArray;


    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '岗位名称', 'deptName', 'string', 1, '100'));
    formFieldArray.push(new FormElement(1, '岗位编码', 'deptCode', 'string', 1, '30'));
    formFieldArray.push(new FormElement(1, '组织', 'oegId', 'string', 0, '30'));
    formFieldArray.push(new FormElement(2, '备注', 'remark', 'string', 0, '400', 'textarea'));


    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;


    editService.init($scope, $http);


    editService.setFormFields($scope, formFieldArray);

    /***
     * 定义获取需要编辑的内容.
     */
    var formFieldReadOnly = new Array("name", "code", "oegId", "remark");
    setReadOnly(formFieldReadOnly, editService.getFieldMap());
    $scope.closeId = 0;
    /*设置关闭按钮*/
    editService.loadData($scope, editDataUrl, shareData.id);
    /**
     * 返回
     */
    $scope.back = function() {
        $scope.jsPanel.close();
    }
});


angular.module('huatek.controllers').controller('FwStationAssignAcctController', function($scope, $location, $http, $routeParams, $rootScope, httpService, listService) {

    listService.init($scope, $http);
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    var name = new queryParam('姓名', 'userName', 'string', 'like');
    queryPage.addParam(name);
    $rootScope.searchCount = queryPage.queryParamList.length;
    listService.setQueryPage($scope, queryPage);

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        columnDefs: [
            { name: '帐号', field: 'acctName', width: '10%', enableColumnMenu: false },
            { name: '姓名', field: 'userName', width: '20%', enableColumnMenu: false },
            { name: '任职机构', field: 'orgName', width: '20%', enableColumnMenu: false },
           /* { name: '任职部门', field: 'deptName', width: '20%', enableColumnMenu: false },*/
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
        listService.setGridHeight();
    };

    /*加载人员列表, 岗位已有人员数据*/
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/fwAccount/getNoRoleAcct/' + $scope.editId, $scope.tableGrid, null, null, function() {
            /*记载岗位关联用户*/
            httpService.get($scope, 'api/fwStation/getFwAccountByStation/' + $scope.editId).success(function(response) {
                $scope.accts = response;
            });

        });
    }
    load();

    /**
     * 岗位添加人员
     */
    $scope.addAcctToStation = function() {
        var selectData = $scope.gridApi.selection.getSelectedRows();
        var ids = [];
       /* $.each(selectData, function(index, value, array) {
        	
            ids.push(value.id);
        });*/
        for(var k=0;k<selectData.length;k++){
        	  var row=selectData[k];
              var hasIn=false;
              for(var z=0;z<$scope.accts.length;z++){
            	  if($scope.accts[z].id == row.id){
            		  submitTips('人员已经存在请确认后添加。', 'warning');
            		  hasIn = true;
            		  break;
            	  }
              }
              if(!hasIn){
            	  ids.push(row.id); 
              }
        }
        
        var delUrl = "/api/fwStation/addAcctToStation/" + ids + "/" + $scope.editId;
        if (selectData.length <= 0) {
            submitTips('请在列表中至少选择一条数据。', 'warning');
            return false;
        }
        httpService.get($scope, delUrl).success(function() {
            load();
        });
    }

    /*移除ids*/
    var delIds = [];
    /**
     * 移除人员
     */
    $scope.rmAcctToStation = function() {
        var selectData = $("input:checkbox[name='acctBox']:checked");
        /*清空ids数组上次记录数据*/
        delIds = [];
        $.each(selectData, function(index, value, array) {
            delIds.push(value.value);
        });
        var delUrl = "/api/fwStation/delAcctFromStation/" + delIds + "/" + $scope.editId;
        if (delIds.length <= 0) {
            submitTips('请在列表中至少选择一条数据。', 'warning');
            return false;
        }
        httpService.get($scope, delUrl).success(function() {
            load();
        });
    }

    /**
     * 全选
     */
    $scope.selectAll = function() {
        /*清空ids数组上次记录数据*/
        delIds = [];
        /*获取checkbox*/
        var selectData = $("input:checkbox[name='acctBox']");
        $.each(selectData, function(index, value, array) {
            /*如果选中则取消选中*/
            if (!value.checked) {
                value.checked = true;
            } else {
                value.checked = false;
            }
        });
    }

    /*上方列表查询*/
    $scope.searchAcct = function() {
        load();
    }

});