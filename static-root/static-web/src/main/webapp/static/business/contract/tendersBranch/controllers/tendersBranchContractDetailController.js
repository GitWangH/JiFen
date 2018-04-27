'use strict';
/**
 * 标段分部分项 挂接合同清单
 */
angular.module('huatek.controllers').controller('TendersBranchContractDetailController', function($scope, $location, $http, listService, treeGridService, httpService) {
	
	/*var queryTopLevelUrl = "/api/tendersContractDetail/queryTopLevel";*/
	var queryTopLevelUrl = "/api/tendersContractDetailCheck/queryTopLevel"/*查询顶级节点URL*/
	var queryChildNodesUrl = "/api/tendersContractDetail/queryChildNodes/";/*根据父级节点查询子级节点URL*/
	var saveDataUrl = "/api/tendersBranch/addSelectedDetailData";/*保存修改数据RUL*/
	
	var columnsArr = [];
	/*name, field, width, editable, otherConfig,max*/
	columnsArr.push(new treeColumn('清单编号','contractDetailCode','15%',false,'','50'));
	columnsArr.push(new treeColumn('清单名称','contractDetailName','15%',false,'','50'));
	columnsArr.push(new treeColumn('变更令号','','15%',false,'','50'));
	columnsArr.push(new treeColumn('单位','countUnit','10%',false,new otherConfig('select','dic.count_unit'),'50'));
	columnsArr.push(new treeColumn('数量','reviewQuantity','10%',false,'','50'));
	columnsArr.push(new treeColumn('单价','reviewUnitPrice','10%',false,'','50'));
	columnsArr.push(new treeColumn('可使用量','workabilityQuantity','10%',false,'','50'));
	columnsArr.push(new treeColumn('已使用量','usedQuantity','10%',false,'','50'));
	/**
	 *    { name: '清单编号', field: 'contractDetailCode', width: '15%' },
	      { name: '清单名称', field: 'contractDetailName', width: '15%' },
	      { name: '变更令号', field: '', width: '15%' },
	      { name: '单位', field: 'countUnit', width: '10%' },
	      { name: '数量', field: 'quantity', width: '10%' },
	      { name: '单价', field: 'unitPrice', width: '10%' },
	      { name: '可使用量', field: 'workabilityQuantity', width: '10%' },
	      { name: '已使用量', field: 'usedQuantity', width: '10%' }
	 * */
	/**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl, false);
	
    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    var checkRowMap = null;
    if($scope.passParams.row.busiContractTendersBranchDetailDtoList && $scope.passParams.row.busiContractTendersBranchDetailDtoList.length > 0){
        checkRowMap = getMap($scope.passParams.row.busiContractTendersBranchDetailDtoList,'contractDetailId');
    }
    treeGridService.setOtherConfig($scope, {loadChildField:"uuid",loadChildHttpMethed:HTTP_METHED.GET,checkRowMap:checkRowMap,checkRowKey:"id"});
    
    listService.init($scope, $http);
    /** 设置查询条件**/
    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");
    var queryOrgId = new queryParam('[默认必传参数]挂接数据所属机构Id', 'busiFwOrg.id', 'string', '=');
    queryOrgId.value = $scope.passParams.queryOrgId;
    queryOrgId.isShow = false;/*此条件隐藏*/
    queryPage.addParam(queryOrgId);
    
    queryPage.addParam(new queryParam('清单编号', 'contractDetailCode', 'string', 'alllike'));
    listService.setQueryPage($scope, queryPage);

    var btnArray = [];
    btnArray.push(new customButton('保存', 'save', true, 'saveContractDetail'));
    listService.setButtonList($scope, null, btnArray);

     
    /*封装业务对象*/
    var packageData = function(rows,tendersBranchId) {
        var packageObjArr = [];
        for (var i = 0; i < rows.length; i++) {
            var obj = {
                contractDetailCode: rows[i].contractDetailCode,/**/
                contractDetailName: rows[i].contractDetailName,
                countUnit: rows[i].countUnit,
                workabilityQuantity: rows[i].workabilityQuantity,/*原始可分解数量*/
                dissolubleQuantity: rows[i].workabilityQuantity,
                designUnitPrice: rows[i].reviewUnitPrice,
                designQuantity: '',
                designTotalPrice: '',
                cumulativeQuantity: '',
                tendersBranchId:tendersBranchId,
            	contractDetailId:rows[i].id,
            };
            packageObjArr.push(obj);
        }
        return packageObjArr;
    }
    /**
     * 桥接路由
     */
    $scope.execute = function(operation, param) {
        if (operation == 'saveContractDetail') {
        	var checkedNodesRows = $scope.huatekTree.getAllCheckedLeafNode();
            if (checkedNodesRows.length < 1) {
                submitTips('警告：请至少选择一条数据。', 'warning');
                return false;
            } else {
            	var currentRow = $scope.passParams.row;/*操作的当前行数据*/
            	var parentTree = $scope.passParams.huatekTree;
        		if (cnex4_isNotEmpty_str(currentRow.busiContractTendersBranchDetailDtoList)) {
        			/*非第一次挂接，则将本次挂接数据添加到子集合中*/
        			for(var i=checkedNodesRows.length-1;i>=0;i--){/**/
            			for(var j=0;j<currentRow.busiContractTendersBranchDetailDtoList.length;j++){
            				if(currentRow.busiContractTendersBranchDetailDtoList[j].contractDetailId==checkedNodesRows[i].id){
            					checkedNodesRows.splice(i,1);
            					break;
            				}
            			}
            		}
        			currentRow.busiContractTendersBranchDetailDtoList = currentRow.busiContractTendersBranchDetailDtoList.concat(packageData(checkedNodesRows,currentRow.id));
        		}else{
        			/*第一次挂接，则将本次挂接数据添加到子集合中*/
        			currentRow.busiContractTendersBranchDetailDtoList = packageData(checkedNodesRows,currentRow.id);
        		}
            	parentTree.addToEditMap(currentRow, "update");
            }
            $scope.back();
        }
    }
    
    /***
     * 工程量清单数据.
     * 详见：com.hisense.frame.page.DataPage<T>
     */
    var load = function() {
    	if (cnex4_isNotEmpty_str($scope.passParams.queryOrgId)) {
            httpService.get($scope, "api/tendersContractDetail/getTendersFlowInfo/" + $scope.passParams.queryOrgId)
		               .success(function(response) {
		                	if(response.checkApprovalStatus == 'flow_passed'){/*合同清单复核通过的数据才可以挂接 - 2017-11-21 毛工和王剑确认*/
		                		$scope.huatekTree.loadData($scope.queryPage);
		                	}
		               }).error(function() {
		                    console('获取流程审批信息失败!');
		               });
        }
    }

    load();
    
    $scope.search = function() {
        load();
    };
});