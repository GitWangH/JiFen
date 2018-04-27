angular.module('huatek.controllers').controller('TendersBranchApproveFlowController', function ($scope, $rootScope, $http, editService, httpService, treeGridService, cacheService) {
	$scope.busiCode=$scope.passParams.busiCode;
	$scope.taskId=$scope.passParams.taskId;
	$scope.busiId=$scope.passParams.busiId;
	$scope.taskKey=$scope.passParams.taskKey;
	$scope.taskName=$scope.passParams.taskName;
	$scope.processInstanceId=$scope.passParams.processInstanceId;
	$scope.onlyView=$scope.passParams.onlyView;
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'分部分项概况'));
    if(!$scope.onlyView){
    	columnWrapArray.push(new multipleColumn(2,'审批信息'));
 	}
    $scope.columnWrapArray = columnWrapArray;
    
    /**创建表格*/
    var columnsArr = [];
    columnsArr.push(new treeColumn('编号','tendersBranchCode','20%'));
    columnsArr.push(new treeColumn('名称','tendersBranchName','20%'));
    columnsArr.push(new treeColumn('桩号类型','stakeNoType','10%',true,new otherConfig('select','dic.pile_no_type')));
    columnsArr.push(new treeColumn('起始桩号','startStakeNo','15%'));
    columnsArr.push(new treeColumn('结束桩号','endStakeNo','15%'));
    columnsArr.push(new treeColumn('合同图号','contractFigureNo','10%'));
    columnsArr.push(new treeColumn('程高','gradeHigh','10%'));

    var queryTopLevelUrl = "/api/tendersBranch/queryTopLevel";/*查询顶级节点URL*/
	var queryChildNodesUrl = "/api/tendersBranch/queryChildNodes/";/*根据父级节点查询子级节点URL*/
    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, null, false);
    

    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, {loadChildField:"uuid",loadChildHttpMethed:HTTP_METHED.GET});
    
    /**
     * 选择分部分项触发动态加载明细数据
     */
    treeGridService.setNodeCheckedFn($scope, function(selectedRow){
    	var selectDetails = selectedRow.busiContractTendersBranchDetailDtoList;
    	if (cnex4_isNotEmpty_str(selectDetails)) {
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
    
    
    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");
    var tendersId = new queryParam('标段ID', 'busiFwOrg.id', 'string', '=');
    tendersId.value = $scope.busiId;
    queryPage.addParam(tendersId);
    
    treeGridService.setQueryPage($scope, queryPage);
    
    
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
 	if(!$scope.onlyView){
 		var result=new FormElement(2,'审核','result','boolean',1,'128',"radio","resultChange");
 		result.items=[{code:"true",name:'同意'},{code:"false",name:"驳回"}];
 		/*result.value="true";*/
 		formFieldArray.push(result);
 		var resultMessage=new FormElement(2,'审核意见','resultMessage','string',1,'1000',"textarea");
 		/*resultMessage.value='同意';*/
 		formFieldArray.push(resultMessage);
 		formFieldArray.push(new FormElement(2,'意见模版','resultMessageTemplate','string',0,'512',"select"));
 	}
 	
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    $scope.huatekTree.loadData(queryPage);
    
    /**
     * 分部分项明细数据(子表)
     * @type {Object}
     */
    $scope.tableGrid_right = {
        useExternalPagination: true,
        columnDefs: [
            { name: '清单编号', field: 'contractDetailCode', width: '15%', enableColumnMenu: false },
            { name: '清单名称', field: 'contractDetailName', width: '20%', enableColumnMenu: false },
            { name: '单位', field: 'countUnitName', width: '10%', enableColumnMenu: false,decode:{field:"countUnit",dataKey:"dic.count_unit"} },
            { name: '可分解数量', field: 'dissolubleQuantity', width: '10%', enableColumnMenu: false },
            { name: '设计单价(元)', field: 'designUnitPrice', width: '10%', enableColumnMenu: false },
            { name: '设计数量', field: 'designQuantity', width: '10%', enableColumnMenu: false },
            { name: '设计金额(元)', field: 'designTotalPrice', width: '10%', enableColumnMenu: false },
            { name: '累计计量', field: 'cumulativeQuantity', width: '10%', enableColumnMenu: false, visible: $rootScope.orgType == 1  }
        ]
    };
    
    $scope.tableGrid_right.onRegisterApi = function(gridApi) {
        $scope.tableGrid_right = gridApi;
    };
    
    /**
     * 流程提交
     **/   
    $scope.submit = function(){
    	editService.submitData($scope, WORKFLOW_COMMON_SUBMIT_URL);
    } 
    
});