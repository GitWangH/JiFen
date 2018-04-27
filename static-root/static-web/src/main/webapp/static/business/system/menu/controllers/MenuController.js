'use strict';

angular.module('huatek.controllers').controller('MenuController', function($scope, $location, $http, $compile, listService) {
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
        /*
         * 菜单添加的文本框
         */
        columnDefs: [
            { name: '上级菜单', field: 'parentName', width: '25%', enableColumnMenu: false },
            { name: '名称', field: 'title', width: '25%', enableColumnMenu: false },
            { name: '级别', field: 'level', width: '10%', enableColumnMenu: false },
            { name: '是否菜单', field: 'isMenuStr', width: '10%', enableColumnMenu: false },
            { name: '菜单展示', field: 'isShowStr', width: '10%', enableColumnMenu: false },
            { name: '样式', field: 'cssClass', width: '10%', enableColumnMenu: false },
            { name: '图标', field: 'icon', width: '10%', enableColumnMenu: false },
            { name: '菜单标识', field: 'url', width: '15%', enableColumnMenu: false },
            { name: '模版路径', field: 'view', width: '20%', enableColumnMenu: false },
            { name: '控制器', field: 'controller', width: '20%', enableColumnMenu: false },
            { name: '菜单参数', field: 'params', width: '20%', enableColumnMenu: false },
            { name: '弹出宽度', field: 'width', width: '20%', enableColumnMenu: false },
            { name: '弹出高度', field: 'height', width: '20%', enableColumnMenu: false }

        ],
        enableGridMenu: true, //是否显示grid 菜单
        exportExcelName: '菜单管理.xls'
            /*exporterAllDataFn: function() {
                
            }*/
    };

    $scope.menuTree = null;  //定义一个menuTree 初始值为null
    
    $scope.$watch('$viewContentLoaded', function() {
        $http.get("api/menu/public/loadAllMenu").success(function(response) {
        	
            $scope.menuTree = response; //把json对象赋值给menuTree
            $scope.menuTree.push({id:0,title:"菜单根目录"}) //给原有的json数据手动push数据
            //delete $scope.menuTree[18];
            console.log($scope.menuTree);
            $scope.initOrgTree();  //初始化OrgTree
        });
    });
    /*$scope.menuTree = null
    $scope.oldMenuTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        $http.get("api/menu/public/loadAllMenu").success(function(response) {
        	
        	$scope.oldMenuTree = response; //把json对象赋值给menuTree
        	//delete $scope.oldMenuTree[18];
        	
        	for(var a in $scope.oldMenuTree){
        		alert($scope.oldMenuTree[a].title);
        		$scope.menuTree.push($scope.oldMenuTree[a]);
        	}
        	
        	console.log($scope.menuTree);
            $scope.menuTree.push({id:0,title:"菜单根目录"}) //给原有的json数据手动push数据
            $scope.initOrgTree();  //初始化OrgTree
        });
    });*/
    /*
     * treeId  ? 怎么获取的？
     * treeNode ？ 怎么获取的？
     */
    $scope.onTreeClick = function(event, treeId, treeNode) {
    	//find() 方法返回被选元素的后代元素。给li的所有后代去除样式
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        /*
         * parentIdParam这个对象是不是在serviceList中封装的？ serviceList是干嘛的？如何传递的
         * 
         */
        parentIdParam.value = treeNode.id;
        load();
    }
    
    /*
     *初始化OrgTree，这里有点不懂，为什么要去掉用这个方法 去构造这个json呢？
     */
    $scope.initOrgTree = function() {
        var setting = {
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
        /*
         * $.fn.zTree.init($("#tree"), setting, nodes);
         * 第一个参数是<ul/>id选择的jquery对象,第二个参数是配置的setting,第三个参数是获取到的数据.
         * $.fn.zTree.init($("#menuTree"), setting, $scope.menuTree)
         * $("#menuTree") 这个是template_home_with_tree.html中的一个ul的id。
         * setting是一个拼接的json
         * $scope.menuTree是访问后台获得的一个json
         * expandall是xTree提供的一个方法，展开/折叠 节点（参数是布尔值） true是默认打开Tree
         */
        $.fn.zTree.init($("#menuTree"), setting, $scope.menuTree).expandAll(true);
    }
    
    /**
     * 一个删除操作就copy这个 
     */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;

        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
        listService.setGridHeight();
    };

    
    //初始化listservice，listservice是干什么用的？？？
    listService.init($scope, $http,$scope.tableGrid,$scope.gridApi);
    var queryPage = new QueryPage(1, 10, "id desc", "false");

    queryPage.addParam(new queryParam('名称', 'sourceName', 'string', 'like'));
    queryPage.addParam(new queryParam('访问路径', 'sourceUrl', 'string', 'like'));
    var parentIdParam = new queryParam('上级组织', 'parent.id', 'string', '=');
    parentIdParam.isShow = false; //
    parentIdParam.value = 0;
    queryPage.addParam(parentIdParam);

    listService.setQueryPage($scope, queryPage);

    /**封装按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/menu/add', 'addData'));  //如果在fw_source表中没有第三个参数，那么这个菜单就会自动屏蔽。
    btnArray.push(new pageButton('编辑', 'edit', '/menu/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/menu/delete', 'deleteData'));

    listService.setButtonList($scope, btnArray);


    /*
     * 点击新增 编辑 删除 就会触发execute方法？
     */
    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup("菜单新增Test", "/menu/add", parentIdParam.value, null, null, load)); //这里的/menu/add 才是真正会访问controller的所对应的/menu/add
        } else if (operation == 'deleteData') {
        	//($scope, $scope.tableGrid, $scope.gridApi, 'api/menu/delete')  域对象、页面信息、选中的信息、action的url
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/menu/delete');
        } else if (operation == 'editData') {
            listService.editData($scope, $scope.gridApi, new popup("菜单编辑", "/menu/edit", parentIdParam.value, null, null, load));
        }
    }

    var load = function() {
        if (!parentIdParam.value) {
            parentIdParam.value = 0;
        }

        listService.loadData($scope, 'api/menu/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

});

angular.module('huatek.controllers').controller('MenuAddController', function($scope, $location, $http, $routeParams, listService, editService, $rootScope) {

    var addDataUrl = 'api/menu/add';
    var editDataUrl = 'api/menu/edit';
    var homeUrl = '/menu/home';

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    columnWrapArray.push(new multipleColumn(2, '样式'));
    columnWrapArray.push(new multipleColumn(3, '资源配置'));
    columnWrapArray.push(new multipleColumn(4, '弹出框'));
    $scope.columnWrapArray = columnWrapArray;


    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];


    /*formFieldArray.push(new FormElement(1,'id','id','string',1,'30'));*/
    formFieldArray.push(new FormElement(1, '上级菜单', 'parentId', 'int', '-1', '-1', 'select-autocomplete'));
    formFieldArray.push(new FormElement(1, '名称', 'title', 'string', 'require', '30'));
    formFieldArray.push(new FormElement(1, '是否菜单', 'isMenu', 'int', 'require', '2', 'select'));
    formFieldArray.push(new FormElement(1, '菜单展示', 'isShow', 'int', 'require', '2', 'select'));
    formFieldArray.push(new FormElement(1, '展示顺序', 'displayOrder', 'int', -1, '2', '', '', '', 0));
    formFieldArray.push(new FormElement(2, '样式', 'cssClass', 'string', '', '30'));
    formFieldArray.push(new FormElement(2, '图标', 'icon', 'string', '', '100', 'icon'));
    formFieldArray.push(new FormElement(3, '菜单标识', 'url', 'string', 'require', '60'));
    formFieldArray.push(new FormElement(3, '菜单参数', 'params', 'string', -1, '60'));
    formFieldArray.push(new FormElement(3, '控制器', 'controller', 'string', '-1', '128'));
    formFieldArray.push(new FormElement(3, '模版路径', 'view', 'string', '-1', '128'));
    formFieldArray.push(new FormElement(4, '弹出框宽度', 'width', 'int', '-1', '4'));
    formFieldArray.push(new FormElement(4, '弹出框高度', 'height', 'int', '-1', '4'));



    editService.init($scope, $http);


    editService.setFormFields($scope, formFieldArray);

    // $scope.editId = $routeParams.id;

    /***
     * 初始化表单选择数据，包括字典数据，其他可选项数据.
     */

    /***
     * 初始化表单数据.
     */
    var fieldMap = editService.getFieldMap($scope);
    fieldMap.get("isMenu").value = "1";
    fieldMap.get("isShow").value = "1";
    var isShowItems = [];
    isShowItems.push(new selectItem("显示", "1"));
    isShowItems.push(new selectItem("不显示", "-1"));
    fieldMap.get("isShow").items = isShowItems;
    resolveShowFieldAndReturnField(fieldMap.get("isShow"), isShowItems);
    var isMenuItems = [];
    isMenuItems.push(new selectItem("是", "1"));
    isMenuItems.push(new selectItem("否", "-1"));
    fieldMap.get("isMenu").items = isMenuItems;
    resolveShowFieldAndReturnField(fieldMap.get("isMenu"), isMenuItems);
    /***
     * 定义联动事件
     */
    editService.getFieldMap($scope).get("parentId").value = $scope.passParams;
    $.get("api/menu/public/loadAllMenu", function(data) {
        editService.getFieldMap($scope).get("parentId").showField = 'title';
        editService.getFieldMap($scope).get("parentId").returnField = 'id';

        resolveShowFieldAndReturnField(editService.getFieldMap($scope).get("parentId"), data);
        editService.getFieldMap($scope).get("parentId").items = data;
    });


    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);

    }

    $scope.selectIcon = function() {
        var pop = {
            title: "图标选择",
            passParams: function(icon) {
                editService.getFieldMap($scope).get("icon").value = icon;
            },
            controller: "MenuIconSelectController",
            height: 350,
            paneltype: 'modal',
            templateView: "static/business/system/menu/templates/menu_icon.html",
        };
        listService.popPanel($scope, pop);
    };


    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    }

    $scope.save = function() {
        editService.saveData($scope, addDataUrl, homeUrl);
        /*submitTips('保存成功！', 'success');*/

    }

});
angular.module('huatek.controllers').controller('MenuIconSelectController', function($scope, $location, $http, $routeParams, editService, $rootScope) {

    editService.init($scope);
    $scope.seticon = function(icon) {
        $scope.icon = icon;
    }
    $scope.confirm = function() {
        if ($scope.passParams) {
            $scope.passParams($scope.icon);
            $scope.back();
        }
    }

});