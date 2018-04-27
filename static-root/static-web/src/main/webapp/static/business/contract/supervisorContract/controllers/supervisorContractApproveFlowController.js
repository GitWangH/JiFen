angular.module('huatek.controllers').controller('SupervisorContractApproveFlowController', function ($scope, $http, editService, httpService) {
	$scope.busiCode=$scope.passParams.busiCode;
	$scope.taskId=$scope.passParams.taskId;
	$scope.busiId=$scope.passParams.busiId;
	$scope.taskKey=$scope.passParams.taskKey;
	$scope.taskName=$scope.passParams.taskName;
	$scope.processInstanceId=$scope.passParams.processInstanceId;
	$scope.onlyView=$scope.passParams.onlyView;
	$scope.editId=$scope.busiId;
	var editDataUrl = 'api/supervisorContract/edit';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'合同概况'));
    columnWrapArray.push(new multipleColumn(2,'合同描述及附件'));
    if(!$scope.onlyView){
    	columnWrapArray.push(new multipleColumn(3,'审批信息'));
 	}
    
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var companyName = new FormElement(1,'单位名称','orgId','string','','50','select');
    formFieldArray.push(companyName);
    formFieldArray.push(new FormElement(1,'合同名称','contractName','string'));
    formFieldArray.push(new FormElement(1,'合同编号','contractCode','string'));
    formFieldArray.push(new FormElement(1,'签订日期','signatureData','string'));
    formFieldArray.push(new FormElement(1,'合同金额(元)','contractTotalPrice','number'));
    formFieldArray.push(new FormElement(2,'暂定金(元)','provisionalMoney','number'));
    
    formFieldArray.push(new FormElement(2,'合同描述','contractDescription','string','','255','textarea','','','','','readonly'));
    var contractFileUUID = new FormElement(2,'附件','contractFile','string','','36','fileSingle');
    contractFileUUID.readonly = true;
    contractFileUUID.systemHeader = SYSTEM_HEADER.BUSI_HEADER;
 	formFieldArray.push(contractFileUUID);
 	
 	/**
 	 * 循环设置只读
 	 */
 	for(var formField in formFieldArray){
 		formFieldArray[formField].readonly = true;
 	}
 	
 	if(!$scope.onlyView){
 		var result=new FormElement(3,'审核','result','boolean',1,'128',"radio","resultChange");
 		result.items=[{code:"true",name:'同意'},{code:"false",name:"驳回"}];
 		/*result.value="true";*/
 		formFieldArray.push(result);
 		var resultMessage=new FormElement(3,'审核意见','resultMessage','string',1,'1000',"textarea");
 		/*resultMessage.value='同意';*/
 		formFieldArray.push(resultMessage);
 		formFieldArray.push(new FormElement(3,'意见模版','resultMessageTemplate','string',0,'512',"select"));
 	}
 	
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    /**
     * 模糊检索：标段名称(机构名称)
     */
    $scope.getTendersInfo = function () {
    	var orgId = companyName.value;
    	$scope.promise = httpService.post($scope, "api/contractRestfulApi/getTendersInfoByOrgId/" + orgId)
							  .success(function (response) {
								  editService.setFieldValue($scope, "contractCode", response.contractCode);/*合同编号*/
								})
							  .error(function(){
								  submitTips('根据机构编码获取标段信息出错(监理)','error');
							  });
    }
    
    /**
     * 加载编辑数据
     */
    if($scope.editId){
    	/*动态加载机构数据*/
    	editService.initFieldItems($scope, companyName, 'api/org/getFwOrgByType/6');/**初始化表单选择数据，包括字典数据，其他可选项数据.**/
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }
    
    
    /**
     * 流程提交
     **/   
    $scope.submit = function(){
    	editService.submitData($scope, WORKFLOW_COMMON_SUBMIT_URL);
    } 
    
});