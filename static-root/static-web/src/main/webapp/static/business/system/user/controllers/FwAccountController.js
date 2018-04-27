'use strict';

angular.module('huatek.controllers').controller('FwAccountController', function($scope, $location, $http, $rootScope, listService, httpService) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [

            /*{ name: '职员id', field: 'id',width: '10%', enableColumnMenu: false},*/
            { name: '帐号', field: 'acctName', width: '10%', enableColumnMenu: false },
            { name: '姓名', field: 'userName', width: '10%', enableColumnMenu: false },
            { name: '任职机构', field: 'orgShortName', width: '10%', enableColumnMenu: false },
           /* { name: '任职部门', field: 'deptName', width: '10%', enableColumnMenu: false },*/
            { name: '邮箱', field: 'email', width: '10%', enableColumnMenu: false },
            { name: '电话', field: 'phone', width: '10%', enableColumnMenu: false },
            { name: '状态', field: 'status', width: '10%', enableColumnMenu: false, decode: { field: "status", dataKey: "dic.acct_status" } },
            { name: '是否管理员', field: 'isManager', width: '10%', enableColumnMenu: false, cellFilter: "showFilter" },

        ]

    };

    listService.init($scope, $http);
    $scope.treeParam = new Object();
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
        $.fn.zTree.init($("#acctTree"), setting, $scope.orgList);
    }

    var queryPage = new QueryPage(1, 10, "id desc", "false");


    var queryName = new queryParam('账户名', 'acctName', 'string', 'like');
    queryName.keepValue = false;
    queryName.dropDataUrl = URL_PATH.MDM_HEADER + '/fwAccountExt/getParamsUser/1';
    /*getParamsDuty*/

    /*queryName.useCase = 'query';*/
    queryPage.addParam(queryName);

    var queryAcc = new queryParam('姓名', 'userName', 'string', 'like')
    queryAcc.dropDataUrl = URL_PATH.MDM_HEADER + '/fwAccountExt/getParamsUser/2';
    queryAcc.useCase = 'query';
    queryPage.addParam(queryAcc);

    var status = new queryParam('状态', 'status', 'string', '=');
    status.items = [{ "_showField": "激活", "_returnField": "A" }, { "_showField": "禁用", "_returnField": "D" }];
    status.componentType = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(status);

    $rootScope.searchCount = queryPage.queryParamList.length;

    /*机构/部门*/
    var orgIdParam = new queryParam('机构', 'fwOrg.id', 'string', '=')
    orgIdParam.isShow = false;
    queryPage.addParam(orgIdParam);
   /*  var orgParentIdParam=new queryParam('机构','fwOrg.parent.id','string','=')
    orgParentIdParam.isShow = false;
    queryPage.addParam(orgParentIdParam);*/
    /*var departmentId = new queryParam('部门', 'fwDepartment.id', 'string', '=')
    departmentId.isShow = false;
    queryPage.addParam(departmentId);*/
    /*var departmentParentId=new queryParam('部门','fwDepartment.parent.id','string','=')
    departmentParentId.isShow = false;
    queryPage.addParam(departmentParentId);*/
    /*var tenantId=new queryParam('tenantId','tenantId','string','=')
    tenantId.isShow = false;
    tenantId.value = $rootScope.tenantId;
    queryPage.addParam(tenantId);*/

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
    btnArray.push(new pageButton('新增', 'add', '/fwAccount/add', 'addData'));
    btnArray.push(new pageButton('修改', 'edit', '/fwAccount/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/fwAccount/delete', 'deleteData'));
    btnArray.push(new pageButton('激活', 'active', '/fwAccount/active', 'activeData'));
    btnArray.push(new pageButton('禁用', 'disable', '/fwAccount/disable', 'disableData'));
    btnArray.push(new pageButton('密码重置', 'resetPass', '/fwAccount/resetPass', 'resetPassData'));
    btnArray.push(new pageButton('角色授权', 'roles', '/fwAccount/assign', 'rolesData'));

    listService.setButtonList($scope, btnArray);



    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup("新增账户", '/fwAccount/add', $scope.treeParam));
        } else if (operation == 'editData') {
            var selectData = $scope.gridApi.selection.getSelectedRows();
            if(listService.selectOne($scope.gridApi)){
                if(selectData[0].id == $rootScope.acctId){
                    submitTips('不能对本账户进行编辑操作。', 'warning');
                    return;
                }
                listService.editData($scope, $scope.gridApi, new popup("修改账户", '/fwAccount/edit'));
            }
        } else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/fwAccount/delete');
        } else if (operation == 'rolesData') {
            this.assignRight($scope.gridApi, new popup("分配角色", '/fwAccount/assign'));
        } else if (operation == 'activeData') {
            this.active($scope.gridApi, '/fwAccount/active', 'A');
        } else if (operation == 'disableData') {
            this.active($scope.gridApi, '/fwAccount/disable', 'D');
        } else if (operation == 'resetPassData') {
            if (listService.selectOne($scope.gridApi)) {
                var selectData = $scope.gridApi.selection.getSelectedRows();
                if(selectData[0].id == $rootScope.acctId){
                    submitTips('不能对本账户进行重置密码操作。', 'warning');
                    return;
                }
                var userInfo = { "acctId": selectData[0].id };
                listService.addData($scope, new popup("重置密码", '/fwAccount/resetPass', userInfo, '1196px', '166px'));
            }
        }

    }

    var load = $scope.load = function() {
        listService.loadData($scope, 'api/fwAccount/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        /*tenantId.value = $rootScope.tenantId;*/
        load();
    };

    $scope.clearTree = function(){
        if($scope.treeClickId){
            $("#" + $scope.treeClickId).find("li").removeClass("ztree-active");
            $("#" + $scope.treeChildClickId).find("a").removeClass("curSelectedNode");
        }
    }



    /***
     * 分派权限.
     */
    $scope.assignRight = function(gridApi, toUrl) {

            /*获取当前选择的数据.*/
            var selectData = gridApi.selection.getSelectedRows();
            if (selectData.length > 1) {
                submitTips('警告：不能选择多条数据用于分配角色。', 'warning');
                return;
            }
            if (selectData.length == 0) {
                submitTips('请在列表中选择一条用于分配角色的数据。', 'warning');
                return;
            }
            /*当前用户不能为自己进行角色授权*/
            if(selectData[0].id == $rootScope.acctId){
                submitTips('不能对本账户进行角色分配。', 'warning');
                return;
            }
            listService.editData($scope, gridApi, toUrl);
        }
        /**
         * 激活禁用
         * @param      {<type>}  gridApi  The grid api
         * @param      {<type>}  toUrl    To url
         * @param      {string}  val      The value
         */
    $scope.active = function(gridApi, toUrl, val) {

            /*获取当前选择的数据.*/
            var selectData = gridApi.selection.getSelectedRows();
            if (selectData.length > 1) {
                submitTips('警告：不能选择多条数据用于激活或者禁用。', 'warning');
                return;
            }
            if (selectData.length == 0) {
                submitTips('请在列表中选择一条用于激活或者禁用的数据。', 'warning');
                return;
            }
            var selectData = $scope.gridApi.selection.getSelectedRows();
            if(selectData[0].id == $rootScope.acctId){
                submitTips('不能对本账户进行激活或者禁用操作。', 'warning');
                return;
            }

            var obj = selectData[0];

            if ('A' == val) {
                if (obj.status == '激活') {
                    submitTips('警告：该数据已经激活，不能操作。', 'warning');
                    return;
                }
            } else if ('D' == val) {
                if (obj.status == '禁用') {
                    submitTips('警告：该数据已经禁用，不能操作。', 'warning');
                    return;
                }
            }
            var actionUrl = "api/fwAccount/active/" + obj.id + "/" + val;
            httpService.post($scope, actionUrl).success(function(response) {
                load();
            });
        }
        /**
         * 更新密码
         */
    $scope.resetPassData = function(gridApi, toUrl, val) {

        var selectData = gridApi.selection.getSelectedRows();
        var obj = selectData[0];
        if (selectData.length > 1) {
            submitTips('警告：不能选择多条数据。', 'warning');
            return;
        }
        if (selectData.length == 0) {
            submitTips('请在列表中选择一条数据。', 'warning');
            return;
        }
        layer.open({
            id: "analyse",
            type: 2,
            skin: 'layui-layer-rim',
            /* 加上边框*/
            area: ['420px', '240px'],
            /*宽高*/
            content: '/static/business/system/user/templates/editPassword.html' + '?t=' + huatek.version,
            btn: ['保存', '返回'],
            yes: function(index, layero) {
                var body = layer.getChildFrame('body', index);
                var pass = body.find('#newPass').val();
                var pass2 = body.find('#newPass2').val();
                var actionUrl = "api/fwAccount/updateFwAccountPass/" + obj.id;
                $http.post(actionUrl, { newPass: pass, submitPass: pass2 }).success(function(response) {
                    load();
                    layer.close(index);

                });
            },
            cancel: function(index, layero) {

            },
            success: function(layero, index) {

            }
        });
    }




}).filter("showFilter", function() { /*日期数据过滤器*/
       var dateFilterFunction = function(dateVal) {
           if (cnex4_isNotEmpty_str(dateVal) && dateVal == true) {
                return "是";
  
            }
            return "否";
        };
     return dateFilterFunction;
});

/**
 * 账户保存Controller
 */
angular.module('huatek.controllers').controller('FwAccountAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {

    var addDataUrl = 'api/fwAccount/add';
    var editDataUrl = 'api/fwAccount/edit';
    var getTreeParamUrl = '/api/org/public/getTreeParamUrl';
    /*初始化editService*/
    editService.init($scope);
    /* $scope.orgChange=function(val){
        	for(var i=0;i<orgName.items.length;i++){
        		if(val==orgName.items[i].name){
        			orgId.value=orgName.items[i].code;
        			$scope.initDeptId(orgId.value);
        			break;
        		}
        	}
        	
        }*/
    /**
     * 根据机构机构下部门数据
     * @param      {string}  val     The value
     */
    $scope.initDeptId = function(val) {
        /*加载机构下部门数据*/
        var url = '/api/fwDepartment/getDepartmentByOrgId/' + val;
        httpService.get($scope, url).success(function(response) {
            $.each(response, function(index, value, array) {
                deptId.items.push({ "_returnField": value.id + '', "_showField": value.deptName });
            });
        }); 
        /*editService.initFieldItems($scope, deptId, '/api/fwDepartment/getDepartmentByOrgId/'+val);*/
    }

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    var formFieldArray = [];
    var acctName = new FormElement(1, '账户', 'acctName', 'string', 1, '30');
    formFieldArray.push(acctName);
    var password = new FormElement(1, '密码', 'acctPwd', 'string', 1, '30', 'password');
    formFieldArray.push(password);
    formFieldArray.push(new FormElement(1, '姓名', 'userName', 'string', 1, '30'));
    var sex = new FormElement(1, '性别', 'sex', 'string', 0, '30', 'select', null, null, null, 'dic.sex');
    /*sex.items = [{"_showField":"男", "_returnField":"1"}, {"_showField":"女", "_returnField":"2"}];*/
    formFieldArray.push(sex);
    formFieldArray.push(new FormElement(1, '电话', 'phone', 'telephoneOrPhone', 0, '30'));
    formFieldArray.push(new FormElement(1, '邮箱', 'email', 'email', 0, '30'));
    formFieldArray.push(new FormElement(1, '身份证号码', 'identityCardNo', 'string', 0, '30'));

    var orgName = new FormElement(1, '任职机构', 'orgName', 'string', 1, '30', 'autocomplete', $scope.orgChange, null, null, 'api/org/getTheParentName');
    orgName.returnField = 'name';
    orgName.showField = 'name';
    formFieldArray.push(orgName);
    var orgId = new FormElement(1, '任职机构', 'orgId', 'string', 1, '30');
    orgId.isShow = false;
    formFieldArray.push(orgId);

    var deptId = new FormElement(1, '任职部门', 'deptId', 'int', 0, '30', 'select');
    deptId.showField = "deptName";
    deptId.returnField = "id";
    formFieldArray.push(deptId);
    deptId.isShow = false;
    var isManager = new FormElement(1, '是否管理员', 'isManager', 'boolean', 1, '30', 'checkbox');
    formFieldArray.push(isManager);
    $rootScope.formFieldArray = formFieldArray;


    editService.setFormFields($scope, formFieldArray);
    /*获取遍历内容*/
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId, null, function(response) {
            $scope.initDeptId(response.orgId);
            password.isShow = false;
            deptId.value = Number(deptId.value);
            acctName.readonly = true;
            acctName.require = 0;
            orgName._choosed = true;
        });
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
     * 保存
     */
    $scope.save = function() {
            editService.saveData($scope, addDataUrl);
        }
        /**
         * 模糊监听
         */
    $scope.$on('autocomplete:selected', function(event, data) {
        /*此时模糊检索组件的事件被响应*/
        if (data._componentsName == 'orgName') {
            deptId.items = [];
            deptId.value = "";
            orgId.value = data.value;
            /*加载机构下部门数据*/
            $scope.initDeptId(data.value);
            /*var url = '/api/fwDepartment/getDepartmentByOrgId/'+data.value;
            httpService.get($scope, url).success(function(response){
              $.each(response, function(index, value, array) {
                  console.log(value);
                  deptId.items.push({"_returnField":value.id, "_showField":value.deptName});
              });

            });*/
        }
    });

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
                    orgName._choosed = true;
                    /*机构readonly*/
                    $scope.initDeptId(data.orgId);
                }
                /*部门*/
                if (cnex4_isNotEmpty_str(data.deptId)) {
                    deptId.value = data.deptId + "";
                    /*departmentId.displayValue = data.deptName;
                    departmentName.value = data.deptName;*/
                    /*部门readonly*/
                }
            });
        }
    }

    /*当选择机构树之后进行新增, 默认回填选择机构或部门, 并设置为readonly*/
    $scope.loadSelectTreeData($scope.passParams);

});

/**
 * 用户分配角色
 */
angular.module('huatek.controllers').controller('UserAssginController', function($scope, $location, $http, $routeParams, httpService) {

    var roleMap = new Map();
    /*加载角色树*/
    $scope.roleTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        httpService.get($scope, 'api/fwAcctRole/assign/loadAllRoleWithAcct/' + $scope.editId).success(function(response) {
            for (var i = 0; i < response.data.length; i++) {
                var item = response.data[i];
                roleMap.put(item.rolecode, item);
            }
            $http.get("api/fwrole/public/loadAllRole").success(function(response) {
                $scope.roleTree = response.data;
                for (var i = 0; i < $scope.roleTree.length; i++) {
                    $scope.roleTree[i].nocheck = false;
                    $scope.roleTree[i].parentId = 0;
                    $scope.roleTree[i].checked = roleMap.get($scope.roleTree[i].rolecode) != null;
                    /*如果为角色组则不可勾选*/
                    if($scope.roleTree[i].child != 'Y'){
                        $scope.roleTree[i].disabled = true;
                    }
                }
                $scope.initRoleTree();
            });
        });

    });
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        load();
    }
    $scope.initRoleTree = function() {
        var setting = {
            check: {
                enable: true,
                chkStyle: "checkbox"
            },
            data: {
                key: {
                    name: "name"
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: 0
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#acctRoleTree"), setting, $scope.roleTree).expandAll(true);
        /*角色组不能勾选*/
        var zTree = $.fn.zTree.getZTreeObj("acctRoleTree");
        var node = zTree.getNodes();
        var nodes = zTree.transformToArray(node);
        var dataArr = [];
        for (var i = 0; i < nodes.length; i++) {
           if ($(nodes[i]).attr('disabled')) {
             zTree.setChkDisabled(nodes[i], true);
            }
        }
    }


    /**
     * 保存角色
     */
    $scope.checkNodeInfo = function() {
        var zTree = $.fn.zTree.getZTreeObj("acctRoleTree");
        var nodes = zTree.getCheckedNodes(true);
        var dataStr = '';
        for (var i = 0; i < nodes.length; i++) {
            if (!nodes[i].isParent) {
                var flag = $(nodes[i]).attr('id_');
                if (undefined != flag && null != flag && "" != flag) {
                    dataStr = flag + "_" + dataStr;
                }

            }
        }
        if (dataStr == '' || dataStr == null) {
            dataStr = 'noData';
        }
        var actionUrl = "api/fwAccount/doSaveAssign/" + $scope.editId + "/" + dataStr;
        $http.post(actionUrl).success(function(response) {
            $scope['jsPanel'].close();
        });
    }

});
angular.module('huatek.controllers').controller('EditPasswordController', function($scope, $location, $http, $routeParams, editService, httpService) {

    $scope.title = '修改密码';

    var editDataUrl = '/changePassword';
    var homeUrl = '/api/content';


    /*定义用户录入数据FormElement(title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '原密码', 'acctOldPsw', 'string', 1, '30', 'password'));
    formFieldArray.push(new FormElement(1, '新密码', 'acctNewPsw', 'string', 1, '30', 'password'));
    formFieldArray.push(new FormElement(1, '再次输入新密码', 'acctNewPsw', 'string', 1, '30', 'password'));


    editService.init($scope, $http);


    editService.setFormFields($scope, formFieldArray);

    /***
     * 定义更新操作.
     */

    /*  $scope.update = function(){*/

    /*  	editService.saveData(editDataUrl, homeUrl);*/

    /*  } */

    $scope.confirm = function() {
        if (!editService.isAllowPost()) {
            return;
        }
        var data = editService.getPostData();
        $scope.promise = $http.post(editDataUrl, data).success(function(response) {
            if (response.type == 'success') {
                if (homeUrl != null && homeUrl != '') {
                    shareData.editPassWordModal.hide();
                }
            } else {
                submitTips(response.text, 'warning');
            }
        });
    };

});

/**
 * 重置密码Controller
 */
angular.module('huatek.controllers').controller('FwAccountResetPassController', function($scope, $rootScope, $http, editService, httpService) {

    $scope.title = '重置密码';
    var editDataUrl = '/api/fwAccount/updateFwAccountPass';
    var homeUrl = '/api/fwAccount/query';

    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '重置密码'));
    $scope.columnWrapArray = columnWrapArray;
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '新密码', 'newPass', 'string', 1, '30', 'password'));
    formFieldArray.push(new FormElement(1, '再次输入新密码', 'submitPass', 'string', 1, '30', 'password'));

    editService.init($scope, $http);

    $rootScope.formFieldArray = formFieldArray;
    editService.setFormFields($scope, formFieldArray);

    /**
     * 提交保存
     */
    $scope.confirm = function() {
        /*if(!editService.isAllowPost($scope)){
            return;
        }
        var data = editService.getPostData($scope);    
        httpService.post($scope, editDataUrl+"/"+$scope.passParams.acctId, data);*/
        editService.updateData($scope, editDataUrl, homeUrl, $scope.passParams.acctId);
    };

});