angular.module('huatek.controllers').controller('TendersContractDetailHomeController', function($rootScope, $scope, $http, httpService, listService, treeGridService, excelService) {

    var queryTopLevelUrl = "/api/tendersContractDetail/queryTopLevel"; /*查询顶级节点URL*/
    var queryChildNodesUrl = "/api/tendersContractDetail/queryChildNodes/"; /*根据父级节点查询子级节点URL*/
    var saveDataUrl = "/api/tendersContractDetail/saveData"; /*保存修改数据RUL*/
    /** 审批状态 */
    var checkApprovalStatus = false;
    
    var getTendersFlowInfo = function(){ 
		/** 合同清单复核管理中的审批状态为 flow_passed 已通过，时 合同清单管理中的 “查看分部分项” 按钮才能使用。此方法为 “获取 合同清单复核管理中的 审批状态” 使用*/
		httpService.get($scope, "api/tendersContractDetailCheck/getTendersFlowInfo/" + tendersName.value)
		.success(function(response) {
			if(response.checkApprovalStatus == 'flow_passed'){
				checkApprovalStatus = true
			}
		})
		.error(function() {
		    submitTips('获取“合同清单复核管理”流程审批信息失败!', 'error');
		});
    }
    
    
    /**创建表格*/
    var columnsArr = [];
    columnsArr.push(new treeColumn('清单编号', 'contractDetailCode', '20%','','','50'));
    columnsArr.push(new treeColumn('清单名称', 'contractDetailName', '20%','','','50'));
    columnsArr.push(new treeColumn('单位', 'countUnit', '10%',true,new otherConfig('select','dic.count_unit')));
    columnsArr.push(new treeColumn('合同单价(元)', 'unitPrice', '15%',true,new numberConfig(),'50'));
    columnsArr.push(new treeColumn('合同数量', 'quantity', '10%','',new numberConfig(),'50'));
    columnsArr.push(new treeColumn('合同总额', 'totalPrice', '15%','',new numberConfig(),'50'));
    columnsArr.push(new treeColumn('查看对应分部分项', '', '10%', true, new otherConfig('button', null, function(currentRow) {
        listService.addData($scope, new popup("查看对应分部分项", "/tendersContractDetail/viewRelationBranch", { row: currentRow }, "1000", "600"));
    },function(currentRow){
        if(currentRow.isLeaf == 1 && checkApprovalStatus){
            return true
        }
        return false;
    })));

    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl);

    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, {loadChildField:"uuid",loadChildHttpMethed:HTTP_METHED.GET});
    
    listService.init($scope, $http); /**初始化部分公共方法**/

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
    treeGridService.setBeforeEditCallBackFn($scope,function(operation, row, parentRow) {
    	var uneditMap = new Map();/*不可编辑的列*/
    	uneditMap.put("countUnit","countUnit");
        uneditMap.put("unitPrice","unitPrice");
        uneditMap.put("quantity","quantity");
        uneditMap.put("totalPrice","totalPrice");

        if(operation == ROW_OPERATION.ADD_TOP){
            if(row.groupLevel == 1){
            	row.uneditMap = uneditMap;
            }
        } else if(operation == ROW_OPERATION.MODIFY){
        	if(row.isLeaf == 0){/*非叶子节点部分列不可编辑*/
        		row.uneditMap = uneditMap;
            } else{
            	row.uneditMap = new Map();/*不可编辑的列*/
            	row.uneditMap.put("totalPrice","totalPrice");
            }
        } else if(operation == ROW_OPERATION.ADD_CHILD){
        	row.uneditMap = new Map();/*不可编辑的列*/
        	row.uneditMap.put("totalPrice","totalPrice");
        }
        
        if(cnex4_isNotEmpty_str(parentRow)){
        	if(parentRow.groupLevel > 1){
            	row.contractDetailCode = parentRow.contractDetailCode + "-";
            }
        }
        row.uneditMap.put("orderNumber","orderNumber");
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
    	if(cnex4_isNotEmpty_str(addRow.quantity) && cnex4_isNotEmpty_str(addRow.unitPrice)){
    		addRow.totalPrice = (parseInt(addRow.quantity) * parseFloat(addRow.unitPrice)).toFixed(4);/*自动计算合同金额*/
    	}else{
    		addRow.totalPrice = 0.0000
    	}
    	return true;
    });

    /** 定义功能按钮 **/
    var btnArray = [];
    
    
    /**定义功能按钮**/
    var btnArray = [];
    btnArray.push(new pageButton('生成工程量清单', 'create', '/tendersContractDetail/createQuantitiesBill', 'createQuantitiesBill'));
    btnArray.push(new pageButton('下载模板', 'download', '/tendersContractDetail/downloadTemplate', 'downloadTemplate'));
    btnArray.push(new pageButton('导入', 'import', '/tendersContractDetail/importData', 'importData'));
    btnArray.push(new pageButton('申请', 'apply', '/tendersContractDetail/apply', 'apply'));
    btnArray.push(new pageButton('查看流程', 'tendersContractDetail', '/tendersContractDetail/viewProcess', 'viewProcess'));
    btnArray.push(new pageButton('保存', 'save', '/tendersContractDetail/saveData', 'saveData'));
    listService.setButtonList($scope, btnArray);

    /**
     * 桥接路由
     */
    $scope.execute = function(operation, param) {
        if (operation == 'createQuantitiesBill') { /*生成分部分项*/
        	if(isAllowOperation('该合同清单正在审批中，不可生成工程量清单!','该合同清单已审批，不可生成工程量清单!')){
        		if($rootScope.orgType == 1){
        			submitTips('系统管理员不可操作此功能!', 'warning');
                    return;
        		}else{
        			var orgId = tendersName.value;
                    if (cnex4_isNotEmpty_str(orgId)) {
                        listService.addData($scope, new popup("生成工程量清单", "/tendersContractDetail/createQuantitiesBill", orgId, "1200", "800"));
                    } else {
                        submitTips('请选择标段名称', 'warning');
                        return;
                    }
        		}
        	}
        } else if (operation == 'saveData') { /*保存*/
        	if(isAllowOperation('该合同清单正在审批中，不可修改!','该合同清单已审批，不可修改!')){
        		var orgId = tendersName.value;
                if (cnex4_isNotEmpty_str(orgId)) {
                    $scope.huatekTree.saveAll(saveDataUrl + "/" + orgId);
                } else {
                    submitTips('请选择标段名称', 'warning');
                    return;
                }
        	}
        } else if (operation == 'downloadTemplate') { /*下载模板*/
        	excelService.down("mpms000000003","工程量清单(合同清单)_模板");
        } else if (operation == 'importData') { /*导入*/
        	if(isAllowOperation('该合同清单正在审批中，不可导入!','该合同清单已审批，不可导入!')){
        		var orgId = tendersName.value;
                if (cnex4_isNotEmpty_str(orgId)) {
                	excelService.imp({orgId:orgId,busiCode:"mpms000000003"},function(){
                		load();/*导入成功，重新加载列表数据*/
                	});
                }else {
                    submitTips('请选择标段名称', 'warning');
                    return;
                }
        	}
        } else if (operation == 'apply') { /*申请*/
        	if(isAllowOperation('该合同清单正在审批中，不可重复操作申请!','该合同清单已审批，不可再次操作申请!')){
        		var orgId = tendersName.value;
                if (cnex4_isNotEmpty_str(orgId)) {
                    bootbox.confirm('确定申请吗?', function(isConfirm) {
                        if (isConfirm) {
                            $scope.promise = httpService.post($scope, "api/tendersContractDetail/apply/" + orgId)
                                .success(function(response) {
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
        	if (cnex4_isNotEmpty_str($scope.detailFlowInstanceId)){
        		var pop = {
                        title: "流程进度",
                        passParams: {
                            canSetPerson: true,
                            processInstanceId: $scope.detailFlowInstanceId,
                        },
                        controller: 'processProgressController',
                        templateView: "static/business/workflow/process/templates/processProgress.html",

                    };
                    listService.popPanel($scope, pop)
        	}else{
        		submitTips('该标段还未启动流程!', 'warning');
                return;
        	}
        }
    }

    /**
     * 是否可以操作，消息提示
     */
    var isAllowOperation = function(tipMsg1, tipMsg2) {
        if ($scope.detailApprovalStatus == "flow_inapproval") {
            submitTips(tipMsg1, 'warning');
            return false;
        } else if($scope.detailApprovalStatus == "flow_passed") {
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
            $scope.promise = httpService.get($scope, "api/tendersContractDetail/getTendersFlowInfo/" + orgId)
                .success(function(response) {
                	getTendersFlowInfo();
                    load();
                    $scope.approvalStatusName = response.approvalStatusName;
                    $scope.detailApprovalStatus = response.detailApprovalStatus;
                    $scope.detailFlowInstanceId = response.detailFlowInstanceId;
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
            httpService.get($scope, "api/tendersContractDetail/getTendersFlowInfo/" + tendersName.value)
                .success(function(response) {
                    $scope.approvalStatusName = response.approvalStatusName;
                    $scope.detailApprovalStatus = response.detailApprovalStatus;
                    $scope.detailFlowInstanceId = response.detailFlowInstanceId;
                    $scope.huatekTree.editable = !cnex4_isNotEmpty_str($scope.detailApprovalStatus);
                    /**如果 已驳回 允许编辑*/
                    if($scope.detailApprovalStatus == 'flow_rebut'){
                    	$scope.huatekTree.editable = true;
                    }
                    
//                    if($scope.detailApprovalStatus == '')
                }).error(function() {
                    submitTips('获取流程审批信息失败!', 'error');
                });
        }else{
        	submitTips('请选择标段名称再查询!', 'warning');
        }
    }
    
    getTendersFlowInfo();

    load();

    /**
     * 查询
     */
    $scope.search = function() {
    	getTendersFlowInfo();
        load();
    };
});