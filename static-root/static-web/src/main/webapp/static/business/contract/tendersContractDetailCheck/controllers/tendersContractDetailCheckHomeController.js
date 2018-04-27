angular.module('huatek.controllers').controller('TendersContractDetailCheckHomeController', function($scope, httpService, listService, treeGridService) {

    var queryTopLevelUrl = "/api/tendersContractDetailCheck/queryTopLevel"; /*查询顶级节点URL*/
    var queryChildNodesUrl = "/api/tendersContractDetailCheck/queryChildNodes/"; /*根据父级节点查询子级节点URL*/
    var saveDataUrl = "/api/tendersContractDetailCheck/saveData"; /*保存修改数据RUL*/
    /** 审批状态 */
    var detailApprovalStatus = '';
    /** 控制 查看对应分部分项 是否展示 当本页面 审批状态 为 flow_passed 已通过时，才可以点击 */
    var checkIsShow = false;
    /**创建表格*/
    var columnsArr = [];
    columnsArr.push(new treeColumn('清单编号','contractDetailCode','15%',false,'','50'));
    columnsArr.push(new treeColumn('清单名称','contractDetailName','15%',false,'','50' ));
    columnsArr.push(new treeColumn('单位','countUnit','5%',true,new otherConfig('select','dic.count_unit')));
    columnsArr.push(new treeColumn('合同单价(元)','unitPrice','10%',false,new numberConfig(),'50' ));
    columnsArr.push(new treeColumn('合同数量','quantity','5%',false,new numberConfig(),'50' ));
    columnsArr.push(new treeColumn('合同总额','totalPrice','10%',false,new numberConfig(),'50' ));
    columnsArr.push(new treeColumn('复核单价','reviewUnitPrice','10%','',new numberConfig(),'50'));
    columnsArr.push(new treeColumn('复核数量','reviewQuantity','10%','',new numberConfig(),'50'));
    columnsArr.push(new treeColumn('复核总额','reviewTotalPrice','10%','',new numberConfig(),'50'));
    columnsArr.push(new treeColumn('查看对应分部分项', '', '10%', true, new otherConfig('button', null, function(currentRow) {
        listService.addData($scope, new popup("查看对应分部分项", "/tendersContractDetail/viewRelationBranch", { row: currentRow }, "1000", "600"));
    },function(currentRow){
        if(currentRow.isLeaf == 1 && checkIsShow){
            return true
        }
        return false;
    })));

    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl);

    /** 查询条件 */
    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");

    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
    queryPage.addParam(tendersName);
    tendersName.event = function(orgId) {
            $scope.changeTenders(orgId);
        }
        /** 查询条件初始化 */
    listService.setQueryPage($scope, queryPage);
    treeGridService.setQueryPage($scope, queryPage);
    /**
     * 修改校验
     */
    treeGridService.setBeforeEditCallBackFn($scope,function(operation, row, parentRow) {
    	row.detailType = 'check_detail';
    	var uneditMap = new Map();//不可编辑的列
        uneditMap.put("unitPrice","unitPrice");
        uneditMap.put("quantity","quantity");
        uneditMap.put("totalPrice","totalPrice");
        uneditMap.put("reviewUnitPrice","reviewUnitPrice");
        uneditMap.put("reviewQuantity","reviewQuantity");
        uneditMap.put("reviewTotalPrice","reviewTotalPrice");
        if(operation == ROW_OPERATION.ADD_TOP){
            if(row.groupLevel == 1){
                row.uneditMap = uneditMap;
            }
        } else if(operation == ROW_OPERATION.ADD_CHILD){
        	uneditMap.remove("reviewUnitPrice");
        	uneditMap.remove("reviewQuantity");
        	row.uneditMap = uneditMap;
        } else if(operation == ROW_OPERATION.MODIFY){
        	if(row.isLeaf == 1){
        		uneditMap.remove("reviewUnitPrice");
            	uneditMap.remove("reviewQuantity");
            }
        	row.uneditMap = uneditMap;
        }
    	if(cnex4_isNotEmpty_str(parentRow)){
        	if(parentRow.groupLevel > 1){
            	row.contractDetailCode = parentRow.contractDetailCode + "-";
            }
        }
    });
    /**
     * 删除校验
     */
    treeGridService.setBeforeDeleteCallBackFn($scope,function(row) {
/*    	if(row.detailType == 'check_detail' || !cnex4_isNotEmpty_str(row.detailType)){
    		return true;
    	}*/
    	if(!row.totalPrice) {
    		return true;
    	} else {
    		submitTips('原清单数据不可删除!', 'warning');
            return false;
    	}
    });
    
    /**
     * TreeGrid添加数据校验
     * @param  {[type]} $scope      controller的作用域
     * @param  {[type]} addRow      当前添加行对象
     * @param  {[type]} addRow      当前添加行父节点对象
     */
    treeGridService.setSaveCheckDataFn($scope, function(addRow, parentRow){
    	/** 校验编号规则 **/
    	if(cnex4_isNotEmpty_str(parentRow)){
    		if(parentRow.groupLevel > 1){
    			if(addRow.contractDetailCode.indexOf(parentRow.contractDetailCode+'-') < 0 ){
    				submitTips('当前节点清单编号不符合规则，请检查!', 'warning');
                    return false;
				}
            }
    	}
    	if(cnex4_isNotEmpty_str(addRow.reviewQuantity) && cnex4_isNotEmpty_str(addRow.reviewUnitPrice)){
    		addRow.reviewTotalPrice = (parseInt(addRow.reviewQuantity) * parseFloat(addRow.reviewUnitPrice)).toFixed(4);/*自动计算合同金额*/
    	}else{
    		addRow.reviewTotalPrice = 0.0000
    	}
    	return true;
    });
    
    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, {loadChildField:"uuid",loadChildHttpMethed:HTTP_METHED.GET});
    // 定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('申请', 'apply', '/tendersContractDetailCheck/apply', 'apply'));
    btnArray.push(new pageButton('查看流程', 'tendersContractDetail', '/tendersContractDetailCheck/viewProcess', 'viewProcess'));
    btnArray.push(new pageButton('保存', 'save', '/tendersContractDetailCheck/saveData', 'saveData'));


    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /** 当合同清单管理中的审批状态为 flow_passed 已通过，时 合同清单复核管理中的 申请，查看流程，保存按钮才能使用。此方法为“获取 合同清单管理中的 审批状态” 使用*/
    var getTendersFlowInfo = function(orgId){
    	httpService.get($scope, "api/tendersContractDetail/getTendersFlowInfo/" + orgId)
        .success(function(response) {
        	detailApprovalStatus = response.detailApprovalStatus;
        })
        .error(function() {
            submitTips('获取“合同清单管理”流程审批信息失败!', 'error');
        });
    }
    /**
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
    	/** 当合同清单管理中的审批状态为 flow_passed 已通过，时 合同清单复核管理中的 申请，查看流程，保存按钮才能使用。*/
    	if(detailApprovalStatus != 'flow_passed'){
    		submitTips('合同清单管理审批未通过，不允许操作合同清单复核管理!', 'error');
    		return;
    	}
        if (operation == 'apply') { /*申请*/
        	if(isAllowOperation('该合同复核清单正在审批中，不可重复操作申请!','该合同复核清单已审批，不可再次操作申请!')){
        		var orgId = tendersName.value;
                if (cnex4_isNotEmpty_str(orgId)) {
                    bootbox.confirm('确定申请吗?', function(isConfirm) {
                        if (isConfirm) {
                            $scope.promise = httpService.post($scope, "api/tendersContractDetailCheck/apply/" + orgId)
                                .success(function(response) {
                                	getTendersFlowInfo(tendersName.value);
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
            //        	listService.showProcessProgress($scope, "detailFlowInstanceId");
        	if(cnex4_isNotEmpty_str($scope.checkFlowInstanceId)){
        		var pop = {
                        title: "流程进度",
                        passParams: {
                            canSetPerson: true,
                            processInstanceId: $scope.checkFlowInstanceId,
                        },
                        controller: 'processProgressController',
                        templateView: "static/business/workflow/process/templates/processProgress.html",

                    };
                    listService.popPanel($scope, pop)
        	}else{
        		submitTips('该标段还未启动流程!', 'warning');
                return;
        	}
        } else if (operation == 'saveData') { /*保存*/
        	if(isAllowOperation('该合同复核清单正在审批中，不可重复操作申请!','该合同复核清单已审批，不可再次操作申请!')){
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
        if ($scope.checkApprovalStatus == "flow_inapproval") {
            submitTips(tipMsg1, 'warning');
            return false;
        } else if ($scope.checkApprovalStatus == "flow_passed") {
            submitTips(tipMsg2, 'warning');
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * 切换标段信息
     */
    $scope.changeTenders = function(orgId) {
        if (cnex4_isNotEmpty_str(orgId)) {
            $scope.promise = httpService.get($scope, "api/tendersContractDetailCheck/getTendersFlowInfo/" + orgId)
                .success(function(response) {
                	console.log(43443);
                	console.log(response.checkApprovalStatus);
                	if(response.checkApprovalStatus == 'flow_passed'){
                		checkIsShow = true;
                	}
                	getTendersFlowInfo(tendersName.value);
                    load();
                    $scope.approvalStatusName = response.approvalStatusName;
                    $scope.checkApprovalStatus = response.checkApprovalStatus;
                    $scope.checkFlowInstanceId = response.checkFlowInstanceId;
                })
                .error(function() {
                    submitTips('获取流程审批信息失败!', 'error');
                });
        }
    }

    /**
     * 加载数据
     */
    var load = $scope.load = function() {
        if (cnex4_isNotEmpty_str(tendersName.value)) {
        	$scope.huatekTree.loadData($scope.queryPage);
            httpService.get($scope, "api/tendersContractDetailCheck/getTendersFlowInfo/" + tendersName.value)
                .success(function(response) {
                	if(response.checkApprovalStatus == 'flow_passed'){
                		checkIsShow = true;
                	}
                    $scope.approvalStatusName = response.approvalStatusName;
                    $scope.checkApprovalStatus = response.checkApprovalStatus;
                    $scope.checkFlowInstanceId = response.checkFlowInstanceId;
                    $scope.huatekTree.editable = !cnex4_isNotEmpty_str($scope.checkApprovalStatus);
                    /**如果 已驳回 允许编辑*/
                    if($scope.checkApprovalStatus == 'flow_rebut'){
                    	$scope.huatekTree.editable = true;
                    }
                }).error(function() {
                    submitTips('获取流程审批信息失败!', 'error');
                });
        } else{
        	submitTips('请选择标段名称再查询!', 'warning');
        }
    }
    
    /** 获取 合同清单管理中的 审批状态 */
    getTendersFlowInfo(tendersName.value);

    load();

    /**
     * 查询
     */
    $scope.search = function() {
    	getTendersFlowInfo(tendersName.value);
        load();
    };
});