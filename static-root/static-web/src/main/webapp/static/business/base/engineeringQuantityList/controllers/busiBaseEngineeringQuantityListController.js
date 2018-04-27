/**
 * 工程量清单Controller 
 * busiBaseEngineeringQuantityListController
 */
'use strict';

angular.module('huatek.controllers').controller('busiBaseEngineeringQuantityListController', function($scope, httpService, listService, treeGridService) {
    /*查询顶级节点URL*/
    var queryTopLevelUrl = '/api/busiBaseEngineeringQuantityList/queryTopLevel';
    /*根据父级节点查询子级节点URL*/
    var queryChildNodesUrl = '/api/busiBaseEngineeringQuantityList/queryChildNodes/';
    /*保存修改数据RUL*/
    var saveDataUrl = '/api/busiBaseEngineeringQuantityList/saveData'

    var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'number', '15%'));
    columnsArr.push(new treeColumn('名称', 'name', '25%'));
    columnsArr.push(new treeColumn('单位', 'unit', '30%', true, new otherConfig('select', 'dic.count_unit')));
    columnsArr.push(new treeColumn('描述', 'remark', '30%'));


    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl);

    /** 查询条件 */
    var queryPage = new QueryPage(1, 1000, "orderNumber asc", "false");

    var tendersName = new queryParam('项目工程量清单所属', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.GROUPS_SELECT;
    queryPage.addParam(tendersName);
    /*tendersName.componentType='select';
    listService.initQueryItems($scope,tendersName,'api/org/getParamDtoListByGroupLevel/2');动态加载机构数据*/
    tendersName.event = function(orgId) {
        load();
    }

    var code = new queryParam('编号', 'code', 'string', 'like');
    queryPage.addParam(code);
    var name = new queryParam('名称', 'name', 'string', 'like');
    queryPage.addParam(name);
    /** 查询条件初始化 */
    listService.setQueryPage($scope, queryPage);
    treeGridService.setQueryPage($scope, queryPage);
    /* 定义功能按钮*/    
    var btnArray = [];
    btnArray.push(new pageButton('保存', 'save', '/busiBaseEngineeringQuantityList/save', 'save'));
    /* 设置界面的功能按钮.*/
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接路由
     */
    $scope.execute = function(operation, param) {
        if (operation == 'save') {
            var orgId = tendersName.value;
            if (cnex4_isNotEmpty_str(orgId)) {
                $scope.huatekTree.saveAll(saveDataUrl + "/" + orgId);
            } else {
                submitTips('请选择项目工程量清单所属', 'warning');
                return;
            }
        }
    }

    /*加载数据*/
    var load = $scope.load = function() {
        $scope.huatekTree.loadData($scope.queryPage);
    }

    load();

    /**
     * 查询
     */
    $scope.search = function() {
        load();
    };
});