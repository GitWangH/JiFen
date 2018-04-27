angular.module('huatek.controllers').controller('SupervisorContractDetailHomeController_bak', function($scope, httpService, listService) {

	var queryUrl = "/api/supervisorContractDetail/query";
    var saveUrl = "/api/supervisorContractDetail/saveData"
    	
    /**创建表格*/
    $scope.treeGrid = {
        useExternalPagination: true,
        columnDefs: [
            { name: '清单编号', field: 'contractDetailCode', width: '40%', enableColumnMenu: false, cellTemplate: listService.getTreeCellTemplate("contractDetailCode")},
            { name: '清单名称', field: 'contractDetailName', width: '40%', enableColumnMenu: false }
        ]
    };
    
    /**
     * 注册gridApi的选择器.
     */
    $scope.treeGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        /*单选事件*/
        gridApi.selection.on.rowSelectionChanged($scope, function(row) {
            if (row.isSelected) {
                if (gridApi.selection.getSelectedRows().length > 1) {
                    /*如果选中行数大于1，则先清除所有选中，然后选中被点击的行*/
                    gridApi.selection.clearSelectedRows();
                    gridApi.selection.selectRow(row.entity);
                }
            } else {
                // gridApi.selection.selectRow(row.entity);
            }
        });
        /*编辑单元格是将该数据更改为已编辑状态*/
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
            listService.editCell(rowEntity);
        });
    };
    /**初始化表格*/
    listService.init($scope);

    /** 查询条件 */
    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");
    
    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType='select';
    queryPage.addParam(tendersName);
    /*动态加载监理机构数据*/
    listService.initQueryItems($scope, tendersName, 'api/org/getFwOrgByType/5');
    
    /** 查询条件初始化 */
    listService.setQueryPage($scope, queryPage);


    // 定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('新增同级节点', 'add', '/supervisorContractDetail/addBrotherNode', 'addBrotherNode'));
    btnArray.push(new pageButton('新增子节点', 'addChild', '/supervisorContractDetail/addChildNode', 'addChildNode'));
    btnArray.push(new pageButton('删除节点', 'delete', '/supervisorContractDetail/deleteNode', 'deleteNode'));
    btnArray.push(new pageButton('上移', 'moveUp', '/supervisorContractDetail/moveUp', 'moveUp'));
    btnArray.push(new pageButton('下移', 'moveDown', '/supervisorContractDetail/moveDown', 'moveDown'));
    btnArray.push(new pageButton('保存', 'save', '/supervisorContractDetail/saveData', 'saveData'));

    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'addBrotherNode') {
            listService.addSameLevelTreeNode($scope.treeGrid, $scope.gridApi);
        } else if (operation == 'addChildNode') {
            listService.addChildTreeNode($scope.treeGrid, $scope.gridApi);
        } else if (operation == 'deleteNode') {
            listService.deleteTreeNode($scope.treeGrid, $scope.gridApi);
        } else if (operation == 'moveUp') {
            listService.moveTreeNode($scope.treeGrid, $scope.gridApi,TREE_NODE_UP);
        } else if (operation == 'moveDown') {
            listService.moveTreeNode($scope.treeGrid, $scope.gridApi,TREE_NODE_DOWN);
        } else if (operation == 'saveData') {
        	$scope.promise = httpService.post($scope, saveUrl, listService.getAllEditRows($scope.treeGrid))
									    .success(function (response) {
									    	load();
									    })
									    .error(function(){
									    	submitTips('保存出错(安全清单)','error');
									    });
        }
    }

    /**
     * 加载列表数据
     */
    var load = function() {
        listService.loadData($scope, queryUrl, $scope.treeGrid, false, function(res) {
            console.log(res.content);
            if (res.content && res.content.length > 0) {
                for (var i = 0; i < res.content.length; i++) {
                    res.content[i].$$treeLevel = res.content[i].groupLevel - 1;
                }
            }
        });
    }

    load();

    /**
     * 查询
     */
    $scope.search = function() {
        load();
    };
});