'use strict';

angular.module('huatek.controllers').controller('ChangeManageAddBranchDetailController', function($scope, $rootScope, $location, httpService, $http, listService, treeGridService, cacheService,excelService) {
	var queryTopLevelUrl = "/api/tendersBranch/queryTopLevel";/*查询顶级节点URL*/
	var queryChildNodesUrl = "/api/tendersBranch/queryChildNodes/";/*根据父级节点查询子级节点URL*/
    
    /**
     * 加载分部分项标段审批信息
     */
    var getTendersFlowInfo = function(orgId, initSuccessCallback){ 
    	httpService.get($scope, "api/tendersBranch/getTendersFlowInfo/" + orgId)
			        .success(function(response) {
			            $scope.approvalStatusName = response.approvalStatusName;
			            $scope.approvalStatus = response.approvalStatus;
			            if (angular.isFunction(initSuccessCallback)) {
			                initSuccessCallback();
			            }
			        }).error(function() {
			            submitTips('获取流程审批信息失败!', 'error');
			        });
    }
    
	/** 左侧TreeGrid**/
	var columnsArr = [];
    columnsArr.push(new treeColumn('编号','tendersBranchCode','50%','','','50'));
    columnsArr.push(new treeColumn('名称','tendersBranchName','50%','','','50'));

    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl);

    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, { loadChildField: "uuid", loadChildHttpMethed: HTTP_METHED.GET,showModifyBtn:false,showUpBtn:false,showDownBtn:false });

    /**
     * 选择分部分项触发动态加载明细数据
     */
    treeGridService.setNodeCheckedFn($scope, function(selectedRow){
    	$scope.currentSelectedRow = selectedRow;
    	console.log(selectedRow);
    	if (cnex4_isNotEmpty_str(selectedRow.busiContractTendersBranchDetailDtoList)) {
            var selectDetails = selectedRow.busiContractTendersBranchDetailDtoList;
            if( selectDetails && selectDetails.length > 0){
                for(var i = 0 ; i < selectDetails.length ; i ++){
                    selectDetails[i].countUnitName = cacheService.getPropertyName('dic.count_unit',selectDetails[i].countUnit);
                }
            }
    		$scope.tableGrid_right.data = selectedRow.busiContractTendersBranchDetailDtoList;
    	}else{
    		$scope.tableGrid_right.data = [];
    	}
    });

    /**
     * 删除之前校验
     */
    treeGridService.setBeforeDeleteCallBackFn($scope, function(selectedRow) {
        if (cnex4_isNotEmpty_str(selectedRow.busiContractTendersBranchDetailDtoList) && selectedRow.busiContractTendersBranchDetailDtoList.length > 0) {
            submitTips('请先删除挂接的合同清单再操作删除!', 'warning');
            return false;
        } else {
            return true;
        }
    });

    /** 用于校验的数据字典 - 桩号类型 kind_name : pile_no_type*/
    var pileNoTypeNameArr = getPropertyNameList(cacheService.getData("dic.pile_no_type"));

    /**
     * TreeGrid添加数据校验
     * @param  {[type]} $scope      controller的作用域
     * @param  {[type]} addRow      当前添加行对象
     */
    treeGridService.setSaveCheckDataFn($scope, function(addRow) {
        var startStakeNoIsOk = false;
        var endStakeNoIsOk = false;
        if (cnex4_isNotEmpty_str(addRow.startStakeNo)) {
            addRow.startStakeNo = toCDB(addRow.startStakeNo).toUpperCase(); /*全角转半角*/
            startStakeNoIsOk = checkPileNumber(addRow.startStakeNo, "起始桩号", columnsArr, 'stakeNoType', 3, pileNoTypeNameArr, null);
        } else {
            startStakeNoIsOk = true; /*起始桩号不做非空校验*/
        }

        if (cnex4_isNotEmpty_str(addRow.endStakeNo)) {
            addRow.endStakeNo = toCDB(addRow.endStakeNo).toUpperCase(); /*全角转半角*/
            endStakeNoIsOk = checkPileNumber(addRow.endStakeNo, "结束桩号", columnsArr, 'endStakeNo', 3, pileNoTypeNameArr, null);
        } else {
            submitTips('结束桩号不能为空!', 'warning');
        }
        if (startStakeNoIsOk && endStakeNoIsOk) {
            return true;
        } else {
            return false;
        }
    });

    /** 查询条件 **/
    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");
    var queryOrgId = new queryParam('[默认必传参数]挂接数据所属机构Id', 'busiFwOrg.id', 'string', '=');
    queryOrgId.value = $scope.passParams.seletedOrgId;
    queryOrgId.isShow = false;/*此条件隐藏*/
    queryPage.addParam(queryOrgId);
    listService.setQueryPage($scope, queryPage);

    /** 注册按钮**/
    var btnArray = [];
    btnArray.push(new customButton('添加', 'add', true, 'addcontractDetail'));
    btnArray.push(new customButton('保存', 'save', true, 'saveTendersBranchDetail'));
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
	     if (operation == 'saveData') { /*保存*/
	    	 alert("保存...");
	     } else if(operation == "addcontractDetail"){
	    	 if (cnex4_isNotEmpty_str($scope.currentSelectedRow)){ 
	    		 listService.addData($scope, new popup("挂接合同清单", "/changeManage/hookDetail", { queryOrgId: $scope.passParams.seletedOrgId, row: $scope.currentSelectedRow, huatekTree: $scope.huatekTree }, "1000", "500", function() {
	                 $scope.huatekTree.nodeCheckedFn($scope.currentSelectedRow);
	             }));
	     	 }else{
                submitTips('请先选择分部分项后操作添加!', 'warning');
	     	 }	
	     }
    }

    var isApproval = function(approvalStatus) {
        if (approvalStatus == "flow_inapproval" || approvalStatus == "flow_passed") {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 删除已挂接数据
     */
    $scope.deleteHasHookDetail = function(deleteRow){
    	deleteRow.opration='delete';
    	$scope.huatekTree.addToEditMap($scope.currentSelectedRow, "update");
    	$scope.currentSelectedRow.busiContractTendersBranchDetailDtoList.remove(deleteRow);
    	/*deleteRow.designQuantity = 0;*/
        
    	
    }

    /**
     * 分部分项明细数据(子表)
     * @type {Object}
     */
    $scope.tableGrid_right = {
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        multiSelect: false,
        columnDefs: [
            { name: '清单编号', field: 'contractDetailCode', width: '25%', enableColumnMenu: false, enableCellEdit:false },
            { name: '清单名称', field: 'contractDetailName', width: '25%', enableColumnMenu: false, enableCellEdit:false },
            { name: '设计单价(元)', field: 'designUnitPrice', width: '20%', enableColumnMenu: false, enableCellEdit:false },
            { name: '设计数量', field: 'designQuantity', width: '20%', enableColumnMenu: false,type: 'number'},
            { name: '操作', field: '', width: '10%', enableColumnMenu: false, enableCellEdit:false, cellTemplate : '<a ng-if="{{row.entity.sourceType == 1}}" ng-click="grid.appScope.deleteHasHookDetail(row.entity)">删除</a>'},
        ]
    };
    
    /**
     * 初始化TableGrid
     */
    $scope.initTableGrid = function() {
        /**
         * 注册tableGrid
         */
        $scope.tableGrid_right.onRegisterApi = function(gridApi) {
            $scope.tableGrid_right = gridApi;
             gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
            	 /*console.log("newValue："+newValue+"        oldValue："+oldValue);*/
            	 /*11-23新改的版*/
            	 if(colDef.field == 'designQuantity'){
            		 if(rowEntity.designQuantity > rowEntity.workabilityQuantity){
            			 submitTips('设计数量应不能大于可分解量', 'warning');
            			 rowEntity.designQuantity = 0;
            		 }else{
            			 rowEntity.dissolubleQuantity = rowEntity.workabilityQuantity - rowEntity.designQuantity;
            		 }
            	 }
            	 /*单元格值发生变化时触发*/
            	 var designQuantity = cnex4_isNotEmpty_str(rowEntity.designQuantity)?rowEntity.designQuantity:0;
            	/* if(parseInt(designQuantity) > parseInt(rowEntity.dissolubleQuantity )){
            		 submitTips('设计数量应不能大于可分解量', 'warning');
            		 rowEntity.designQuantity = rowEntity.dissolubleQuantity;
            	 }*/
            	 rowEntity.designTotalPrice = (parseFloat(designQuantity) * parseFloat(rowEntity.designUnitPrice)).toFixed(4);
                 if (!rowEntity.isNewRow) {
                     rowEntity.isEdited = true;
                     $scope.huatekTree.addToEditMap($scope.currentSelectedRow, "update");
                 }
             });
        };
    }
    /**
     * 加载数据
     */
    var load = $scope.load = function() {
    	/*var parentTree = $scope.passParams.huatekTree;
    	var seletedOrgId = $scope.passParams.seletedOrgId;*/
        if (cnex4_isNotEmpty_str(queryOrgId.value)) {
        	getTendersFlowInfo(queryOrgId.value,function(){
        		$scope.initTableGrid();
        	});
        	$scope.huatekTree.loadData($scope.queryPage);
        } else {
            submitTips('请选择标段名称再查询!', 'warning');
        }
        if ($scope.tableGrid_right != undefined) {
            $scope.tableGrid_right.data = [];
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