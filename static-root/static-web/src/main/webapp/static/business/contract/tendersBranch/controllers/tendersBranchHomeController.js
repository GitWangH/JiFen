'use strict';
/**
 * 标段分部分项
 */

angular.module('huatek.controllers').controller('TendersBranchHomeController', function($scope, $rootScope, $location, httpService, $http, listService, treeGridService, cacheService,excelService) {
	
	var queryTopLevelUrl = "/api/tendersBranch/queryTopLevel";/*查询顶级节点URL*/
	var queryChildNodesUrl = "/api/tendersBranch/queryChildNodes/";/*根据父级节点查询子级节点URL*/
	var saveDataUrl = "/api/tendersBranch/saveData";/*保存修改数据RUL*/
    
    /**
     * 加载分部分项标段审批信息
     */
    var getTendersFlowInfo = function(orgId, initSuccessCallback){ 
    	httpService.get($scope, "api/tendersBranch/getTendersFlowInfo/" + tendersName.value)
			        .success(function(response) {
			            $scope.approvalStatusName = response.approvalStatusName;
			            $scope.approvalStatus = response.approvalStatus;
			            $scope.approvalStatusFlag = isApproval(response.approvalStatus);
			            $scope.flowInstanceId = response.flowInstanceId;
			            console.log("分部分项审批状态："+$scope.approvalStatus);
			            if(cnex4_isNotEmpty_str($scope.approvalStatus) && $scope.approvalStatus != 'flow_rebut'){
			            	$scope.huatekTree.editable = false;
			            }else{
			            	$scope.huatekTree.editable = true;
			            }
			            if (angular.isFunction(initSuccessCallback)) {
			                initSuccessCallback();
			            }
			        }).error(function() {
			            submitTips('获取流程审批信息失败!', 'error');
			        });
    	getDetailCheckTendersFlowInfo();
    }
    /**
     * 加载清单复核审批信息
     */
    var checkApprovalStatus = false;
    var getDetailCheckTendersFlowInfo = function(){ 
		/** 合同清单复核管理中的审批状态为 flow_passed 已通过，时 合同清单管理中的 “查看分部分项” 按钮才能使用。此方法为 “获取 合同清单复核管理中的 审批状态” 使用*/
		httpService.get($scope, "api/tendersContractDetailCheck/getTendersFlowInfo/" + tendersName.value)
			.success(function(response) {
				if(response.checkApprovalStatus == 'flow_passed'){
					checkApprovalStatus = true
				}
			})
			.error(function() {
			    submitTips('获取合同清单复核管理”流程审批信息失败!', 'error');
			});
		return checkApprovalStatus;
    }
    
	/** 左侧TreeGrid**/
	var columnsArr = [];
    columnsArr.push(new treeColumn('编号','tendersBranchCode','10%','','','50'));
    columnsArr.push(new treeColumn('名称','tendersBranchName','10%','','','50'));
    columnsArr.push(new treeColumn('桩号类型','stakeNoType','10%',true,new otherConfig('select','dic.pile_no_type')));
    columnsArr.push(new treeColumn('起始桩号','startStakeNo','10%','','','50'));
    columnsArr.push(new treeColumn('结束桩号','endStakeNo','10%','','','50'));
    columnsArr.push(new treeColumn('合同图号','contractFigureNo','10%','','','50'));
    columnsArr.push(new treeColumn('程高','gradeHigh','10%','','','50'));
//    columnsArr.push(new treeColumn('css','css','5%'));
    columnsArr.push(new treeColumn('校验信息','checkMessage','10%'));
    columnsArr.push(new treeColumn('核对基础库','checkBaseLibrary','10%',true,new otherConfig('button',null,function(currentRow){
        if(isAllowOperation('该分部分项正在审批中，不可操作!','该分部分项已审批，不可操作!')){
        	alert("核对基础库开发中...");
        	/*listService.addData($scope, new popup("核对基础库", "/tendersBranch/checkBaseLib", {row:currentRow,huatekTree:$scope.huatekTree}, "1200", "800", function(){
            	$scope.huatekTree.nodeCheckedFn(currentRow);
            }));*/
        }
    },function(){
    	return $scope.huatekTree.editable;
    })));
    columnsArr.push(new treeColumn('挂接', 'remark', '10%', true, new otherConfig('button', null, function(currentRow) {
        if (isAllowOperation('该分部分项正在审批中，不可挂接!', '该分部分项已审批，不可挂接!')) {
            if (cnex4_isNotEmpty_str(tendersName.value)) {
                console.log(currentRow)
                listService.addData($scope, new popup("挂接合同清单", "/tendersBranch/hookDetail", { queryOrgId: tendersName.value, row: currentRow, huatekTree: $scope.huatekTree }, "1000", "500", function() {
                    $scope.huatekTree.nodeCheckedFn(currentRow);
                }));
            } else {
                submitTips('请选择标段名称再操作挂接!', 'warning');
            }

        }
    },function(){
    	if($scope.huatekTree.editable){/*分部分项非审批通过，则判断清单复核是否为已审批通过*/
    		return checkApprovalStatus;
    	}else{
    		return false;
    	}
    })));

    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl,null,null,function(setRow){
        console.log(setRow)
    	if(cnex4_isNotEmpty_str(setRow.checkMessage)){
//    		console.log(setRow);
    		setRow.css = "background:#ff6633";
    	}
    });

    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, { loadChildField: "uuid", loadChildHttpMethed: HTTP_METHED.GET });

    /**
     * 选择分部分项触发动态加载明细数据
     */
    treeGridService.setNodeCheckedFn($scope, function(selectedRow){
    	$scope.currentSelectedRow = selectedRow;
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

    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
    queryPage.addParam(tendersName);

    tendersName.event = function(orgId){
    	getTendersFlowInfo();
		load();
    }

    queryPage.addParam(new queryParam('分部分项', 'tendersBranchName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('编号', 'tendersBranchCode', 'string', 'alllike'));
    var stakeNoType = new queryParam('桩号类型', 'stakeNoType', 'string', '=', '', 'dic.pile_no_type');
    stakeNoType.componentType = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(stakeNoType);
    var checkStatus = new queryParam('复核状态', 'checkStatus', 'string', '=', '', 'dic.check_status');
    checkStatus.componentType = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(checkStatus);
    /*var approvalStatus = new queryParam('审批状态', 'approvalStatus', 'string', '=', '', 'dic.flow_status');
    approvalStatus.componentType = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(approvalStatus);*/

    listService.setQueryPage($scope, queryPage);

    /** 注册按钮**/
    var btnArray = [];
    btnArray.push(new pageButton('生成分部分项', 'create', '/tendersBranch/createTendersBranch', 'createTendersBranch'));

    /**
    btnArray.push(new pageButton('挂接', 'hook', '/tendersBranch/hookDetail', 'hookDetail'));
    btnArray.push(new pageButton('新增同级节点', 'add', '/tendersBranch/addBrotherNode', 'addBrotherNode'));
    btnArray.push(new pageButton('新增子节点', 'addChild', '/tendersBranch/addChildNode', 'addChildNode'));
    btnArray.push(new pageButton('删除子节点', 'delete', '/tendersBranch/deleteNode', 'deleteNode'));
    **/
    btnArray.push(new pageButton('下载模板', 'downloadTemplate', '/tendersBranch/downloadTemplate', 'downloadTemplate'));
    btnArray.push(new pageButton('导入', 'import', '/tendersBranch/importData', 'importData'));
    btnArray.push(new pageButton('申请', 'apply', '/tendersBranch/apply', 'applyFlow'));
    btnArray.push(new pageButton('查看流程', 'viewProcess', '/tendersContract/viewProcess', 'viewProcess'));
    btnArray.push(new pageButton('保存', 'save', '/tendersBranch/saveData', 'saveData'));

    listService.setButtonList($scope, btnArray);

    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'createTendersBranch') { /*生成分部分项*/
            if (isAllowOperation('该分部分项正在审批中，不可操作!', '该分部分项已审批，不可操作!')) {
                var orgId = tendersName.value;
                if (cnex4_isNotEmpty_str(orgId)) {
                    bootbox.confirm('确定生成分部分项吗?', function(isConfirm) {
                        if (isConfirm) {
                            $scope.promise = httpService.post($scope, "api/tendersBranch/createTendersBranch/" + orgId)
                                .success(function(response) {
                                	getTendersFlowInfo();
                                    load();
                                })
                                .error(function() {
                                    submitTips('生成分部分项失败!', 'error');
                                });
                        }
                    });
                } else {
                    submitTips('请选择标段名称', 'warning');
                    return;
                }
            }
        } else if (operation == 'downloadTemplate') { /*下载模板*/
            excelService.down("mpms000000001", "分部分项清单挂接_模板");
        } else if (operation == 'importData') { /*导入*/
            if (isAllowOperation('该分部分项正在审批中，不可导入!', '该分部分项已审批，不可导入!')) {
                var orgId = tendersName.value;
                excelService.imp({}, function() {}, "api/tendersBranch/importData/" + orgId);
            }
        } else if (operation == 'applyFlow') { /*申请*/
            if (isAllowOperation('该分部分项正在审批中，不可重复操作申请!', '该分部分项已审批，不可再次操作申请!')) {
                var orgId = tendersName.value;
                if (cnex4_isNotEmpty_str(orgId)) {
                    bootbox.confirm('确定申请吗?', function(isConfirm) {
                        if (isConfirm) {
                            $scope.promise = httpService.post($scope, "api/tendersBranch/apply/" + orgId)
                                .success(function(response) {
                                	getTendersFlowInfo();
                                    load();
                                })
                                .error(function() {
                                    submitTips('流程启动失败!', 'error');
                                });
                        }
                    });
                } else {
                    submitTips('请选择标段名称', 'warning');
                    return;
                }
            }
        } else if (operation == 'viewProcess') { /*查看流程*/
            //          listService.showProcessProgress($scope, "detailFlowInstanceId");
            if (cnex4_isNotEmpty_str($scope.flowInstanceId)) {
                var pop = {
                    title: "流程进度",
                    passParams: {
                        canSetPerson: true,
                        processInstanceId: $scope.flowInstanceId,
                    },
                    controller: 'processProgressController',
                    templateView: "static/business/workflow/process/templates/processProgress.html",

                };
                listService.popPanel($scope, pop)
            } else {
                submitTips('该标段还未启动流程!', 'warning');
                return;
            }
        } else if (operation == 'saveData') { /*保存*/
            if (isAllowOperation('该分部分项正在审批中，不可重复操作申请!', '该分部分项已审批，不可再次操作申请!')) {
                var orgId = tendersName.value;
                if (cnex4_isNotEmpty_str(orgId)) {
                    $scope.huatekTree.saveAll(saveDataUrl + "/" + orgId);
                } else {
                    submitTips('请选择标段名称', 'warning');
                    return;
                }
            }
        }
    }

    /**
     * 是否可以操作，消息提示
     */
    var isAllowOperation = function(tipMsg1, tipMsg2) {
        if ($scope.approvalStatus == "flow_inapproval") {
            submitTips(tipMsg1, 'warning');
            return false;
        } else if ($scope.approvalStatus == "flow_passed") {
            submitTips(tipMsg2, 'warning');
            return false;
        } else {
            return true;
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
     * 初始化TableGrid
     */
    $scope.initTableGrid = function(approvalStatusFlag) {
        console.log("approvalStatusFlag:" + approvalStatusFlag);
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
                { name: '清单编号', field: 'contractDetailCode', width: '15%', enableColumnMenu: false, enableCellEdit:false },
                { name: '清单名称', field: 'contractDetailName', width: '18%', enableColumnMenu: false, enableCellEdit:false },
                { name: '单位', field: 'countUnitName', width: '10%', enableColumnMenu: false, enableCellEdit:false,decode:{field:"countUnit",dataKey:"dic.count_unit"}},
                { name: '可分解数量', field: 'workabilityQuantity', width: '10%', enableColumnMenu: false, enableCellEdit:false },
                /*{ name: '可分解数量', field: 'dissolubleQuantity', width: '10%', enableColumnMenu: false, enableCellEdit:false },*/
                { name: '设计单价(元)', field: 'designUnitPrice', width: '10%', enableColumnMenu: false, enableCellEdit:false },
                { name: '设计数量', field: 'designQuantity', width: '10%', enableColumnMenu: false,type: 'number', enableCellEdit:$scope.huatekTree.editable  },
                { name: '设计金额(元)', field: 'designTotalPrice', width: '10%', enableColumnMenu: false, enableCellEdit:false },
                { name: '累计计量', field: 'cumulativeQuantity', width: '10%', enableColumnMenu: false, enableCellEdit:false, visible: $rootScope.orgType == 1 },/*管理员可见：设置列[累计计量]是否可见*/
                { name: '操作', field: '', width: '5%', enableColumnMenu: false, enableCellEdit:false,visible: $scope.huatekTree.editable, cellTemplate : '<a ng-if="grid.appScope.approvalStatusFlag" ng-click="grid.appScope.deleteHasHookDetail(row.entity)">删除</a>'},
            ]
        };

        /**
         * 注册tableGrid
         */
        $scope.tableGrid_right.onRegisterApi = function(gridApi) {
            $scope.tableGrid_right = gridApi;
             gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
//            	 console.log("colDef：");
//            	 console.log(colDef);
//            	 console.log("newValue："+newValue+"        oldValue："+oldValue);
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
        if (cnex4_isNotEmpty_str(tendersName.value)) {
        	getTendersFlowInfo(tendersName.value,function(){
        		$scope.initTableGrid($scope.approvalStatusFlag);
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