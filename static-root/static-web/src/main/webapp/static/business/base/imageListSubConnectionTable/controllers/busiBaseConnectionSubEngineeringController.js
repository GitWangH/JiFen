/**
 * 形象清单与分部分项挂接 -  显示分部分项
 */
angular.module('huatek.controllers').controller('busiBaseConnectionSubEngineeringController', function($scope, $location, $http, $rootScope, editService, listService, shareData, httpService, treeGridService) {
    var params = $scope.passParams;
    $scope.titleNumber = '形象清单编号';
    $scope.titleName = '形象清单名称';
    $scope.number = params.number;
    $scope.name = params.name;
    /**queryAction*/
    /*查询顶级节点URL*/
    var queryTopLevelUrl = '/api/busiBaseSubEngineering/queryTopLevel';
    /*根据父级节点查询子级节点URL*/
    var queryChildNodesUrl = '/api/busiBaseSubEngineering/queryChildNodes/';
    /**saveAction*/
    var saveUrl = "/api/busiBaseImageListSubConnectionTable/save";

    /**创建表格 工程量清单 树形列表 - 不可编辑*/
    var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'number', '50%'));
    columnsArr.push(new treeColumn('名称', 'name', '50%'));

    /** 初始化表格 */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, null, false);
    /** 查询条件 */
    var treeGridQueryPage = new QueryPage(1, 1000, "orderNumber asc", "false");
    treeGridService.setQueryPage($scope, treeGridQueryPage);
    var orgId = new queryParam('orgId', 'busiFwOrg.id', 'string', '=', $rootScope.orgId);
    treeGridQueryPage.addParam(orgId);
    /** 定义功能按钮*/
    var btnArray = [];
    listService.setButtonList($scope, btnArray);

    /** 加载数据 */
    var load = $scope.load = function() {
        $scope.huatekTree.loadData(treeGridQueryPage);
    }

    load();

    $scope.save = function() {
        /*var selectedRow = $scope.gridApi.selection.getSelectedRows();*/
        var selectedRow = $scope.huatekTree.getAllCheckedNode();
        var data = {};
        /*分部分项id列表*/
        var subEngineeringIdList = [];
        /*形象清单id*/
        data.imageId = params.id;
        for (var i = 0; i < selectedRow.length; i++) {
            subEngineeringIdList.push(selectedRow[i].id);
        }
        data.subEngineeringIdList = subEngineeringIdList;
        $scope.back();
        $scope.promise = httpService.post($scope, saveUrl, data)
            .success(function(response) {
                params.loadRightTableGrid(params.id);
                $scope.isRefreshImagePage = false;
                /*params.loadLeftTreeGrid();*/
            })
            .error(function() {
                submitTips('保存出错，请重试。', 'error');
            });
    }

});