angular.module('huatek.controllers').controller('TendersContractDetailApproveFlowController', function ($scope, $http, editService, httpService, treeGridService) {
	$scope.busiCode=$scope.passParams.busiCode;
	$scope.taskId=$scope.passParams.taskId;
	$scope.busiId=$scope.passParams.busiId;
	$scope.taskKey=$scope.passParams.taskKey;
	$scope.taskName=$scope.passParams.taskName;
	$scope.processInstanceId=$scope.passParams.processInstanceId;
	$scope.onlyView=$scope.passParams.onlyView;
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'合同清单概况'));
    if(!$scope.onlyView){
    	columnWrapArray.push(new multipleColumn(2,'审批信息'));
 	}
    $scope.columnWrapArray = columnWrapArray;
    
    /**创建表格*/
    var columnsArr = [];
    columnsArr.push(new treeColumn('清单编号', 'contractDetailCode', '20%'));
    columnsArr.push(new treeColumn('清单名称', 'contractDetailName', '20%'));
    columnsArr.push(new treeColumn('单位', 'countUnit', '10%',true,new otherConfig('select','dic.count_unit')));
    columnsArr.push(new treeColumn('合同单价(元)', 'unitPrice', '15%'));
    columnsArr.push(new treeColumn('合同数量', 'quantity', '10%'));
    columnsArr.push(new treeColumn('合同总额', 'totalPrice', '15%'));

    var queryTopLevelUrl = "/api/tendersContractDetail/queryTopLevel"; /*查询顶级节点URL*/
    var queryChildNodesUrl = "/api/tendersContractDetail/queryChildNodes/"; /*根据父级节点查询子级节点URL*/
    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, null, false);
    

    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, {loadChildField:"uuid",loadChildHttpMethed:HTTP_METHED.GET});
    
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
     * 流程提交
     **/   
    $scope.submit = function(){
    	editService.submitData($scope, WORKFLOW_COMMON_SUBMIT_URL);
    } 
    
});