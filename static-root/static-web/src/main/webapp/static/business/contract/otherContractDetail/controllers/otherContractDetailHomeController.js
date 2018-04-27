angular.module('huatek.controllers').controller('OtherContractDetailHomeController', function($scope, httpService, listService, treeGridService) {

    var queryTopLevelUrl = "/api/otherContractDetail/queryTopLevel"; /*查询顶级节点URL*/
    var queryChildNodesUrl = "/api/otherContractDetail/queryChildNodes/"; /*根据父级节点查询子级节点URL*/
    var saveDataUrl = "/api/otherContractDetail/saveData"; /*保存修改数据RUL*/

    var columnsArr = [];
    columnsArr.push(new treeColumn('清单编号', 'contractDetailCode', '40%','','','50'));
    columnsArr.push(new treeColumn('清单名称', 'contractDetailName', '40%','','','50'));
    columnsArr.push(new treeColumn('金额(元)', 'detailMoney', '20%','',new numberConfig(),'15'));
    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl);

    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, { loadChildField: "uuid", loadChildHttpMethed: HTTP_METHED.GET });

    /** 查询条件 */
    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");

    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
    queryPage.addParam(tendersName);

    tendersName.event = function(orgId) {
        load();
    }
    //	queryPage.addParam(new queryParam('计量内容', 'meteringType', 'string', 'in','','dic.metering_type'));
    /** 查询条件初始化 */
    listService.setQueryPage($scope, queryPage);

    // 定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('保存', 'save', '/otherContractDetail/saveData', 'saveData'));
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接路由
     */
    $scope.execute = function(operation, param) {
        if (operation == 'saveData') {
            var orgId = tendersName.value;
            if (cnex4_isNotEmpty_str(orgId)) {
                $scope.huatekTree.saveAll(saveDataUrl + "/" + orgId);
            } else {
                submitTips('请选择标段名称', 'warning');
                return;
            }
        }
    }

    /**
     * 加载数据
     */
    var load = $scope.load = function() {
        if (cnex4_isNotEmpty_str(tendersName.value)) {
            $scope.huatekTree.loadData($scope.queryPage);
        } else {
            submitTips('请选择标段名称再查询!', 'warning');
        }
    }

    load();

    /**
     * 查询
     */
    $scope.search = function() {
        load();
    };
});