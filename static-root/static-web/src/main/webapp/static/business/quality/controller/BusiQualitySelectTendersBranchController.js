'use strict';
/**
 * 施工报检选择项目分部分项
 */
angular.module('huatek.controllers').controller('BusiQualitySelectTendersBranchController', function($rootScope, $scope, $location, $http, listService, httpService, treeGridService) {
	var queryTopLevelUrl = "/api/tendersBranch/queryTopLevel";/*查询顶级节点URL*/
	var queryChildNodesUrl = "/api/tendersBranch/queryChildNodes/";/*根据父级节点查询子级节点URL*/
	var saveDataUrl = "/api/tendersBranch/saveData";/*保存修改数据RUL*/
	
	var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'tendersBranchCode', '20%','','','50'));
    columnsArr.push(new treeColumn('名称', 'tendersBranchName', '20%','','','50'));
    columnsArr.push(new treeColumn('桩号类型', 'stakeNoType', '20%',true,new otherConfig('select','dic.pile_no_type')));
    columnsArr.push(new treeColumn('起始桩号', 'startStakeNo', '20%','','','50'));
    columnsArr.push(new treeColumn('结束桩号', 'endStakeNo', '20%','','','50'));
	
	/**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl, false,true);

    var queryPage = new QueryPage(1, 10, "id asc", "false");
    var groupId = new queryParam('[默认必传参数]当前登陆用户所属集团Id', 'busiFwOrg.id', 'string', '=');
    groupId.isReset = false;
    groupId.value = $scope.passParams.orgId.value;
    groupId.isShow = false;/*此条件隐藏*/
    queryPage.addParam(groupId);
    
    queryPage.addParam(new queryParam('名称', 'tendersBranchName', 'string', 'alllike'));
    listService.setQueryPage($scope, queryPage);
    treeGridService.setQueryPage($scope, queryPage);
    
    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, {loadChildField:"uuid",loadChildHttpMethed:HTTP_METHED.GET});
    
    var btnArray = [];
    btnArray.push(new customButton('保存', 'save', true, 'saveQuantitiesDetail'));
    listService.setButtonList($scope, null, btnArray);
    listService.init($scope);
    
    $scope.execute = function(operation, param) {
        if (operation == 'saveQuantitiesDetail') {
        	var checkedNodesRows = $scope.huatekTree.getAllCheckedNode();
        	var tendersBranchIdObj = $scope.passParams.tendersBranchId;
        	var tendersBranchObj = $scope.passParams.tendersBranch;
        	var checkParagraphObj = $scope.passParams.checkParagraph;
            if (checkedNodesRows.length < 1) {
                submitTips('警告：请至少选择一条数据。', 'warning');
                return false;
            } else {
            	tendersBranchIdObj.value = checkedNodesRows[0].id;
            	var map = getMap($scope.huatekTree.rows,'uuid');
            	var parentNameArr = [];
            	tendersBranchObj.value = getParentForStr(checkedNodesRows[0],map,'tendersBranchName');
            	if(checkParagraphObj){
            		if(checkedNodesRows[0].startStakeNo ){
            			checkParagraphObj.value = checkedNodesRows[0].startStakeNo+'~'+checkedNodesRows[0].endStakeNo;
            		}else{
            			checkParagraphObj.value = checkedNodesRows[0].endStakeNo;
            		}
            	}
            	$scope.back();/*关闭弹框，返回父级页面*/
            }
        }
    }
    /***
     * 工程量清单数据.
     * 详见：com.hisense.frame.page.DataPage<T>
     */
    var load = function() {
    	if($scope.passParams.orgId.value){
    		httpService.get($scope, "api/tendersBranch/getTendersFlowInfo/" + $scope.passParams.orgId.value)
    		.success(function(response) {
    			if(response.approvalStatus == 'flow_passed'){
    				$scope.huatekTree.loadData($scope.queryPage);
    			}
    		});
    	}
    }

    load();
    
    $scope.search = function() {
        load();
    };
});