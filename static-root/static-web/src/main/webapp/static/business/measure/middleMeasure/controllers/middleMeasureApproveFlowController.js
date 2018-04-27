angular.module('huatek.controllers').controller('MiddleMeasureApproveFlowController', function ($scope, $http, editService, httpService) {
	$scope.busiCode=$scope.passParams.busiCode;
	$scope.taskId=$scope.passParams.taskId;
	$scope.busiId=$scope.passParams.busiId;
	$scope.taskKey=$scope.passParams.taskKey;
	$scope.taskName=$scope.passParams.taskName;
	$scope.processInstanceId=$scope.passParams.processInstanceId;
	$scope.onlyView=$scope.passParams.onlyView;
	$scope.editId=$scope.busiId;
	var editDataUrl = 'api/tendersContract/edit';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'合同概况'));
    columnWrapArray.push(new multipleColumn(2,'合同其他条款'));
    columnWrapArray.push(new multipleColumn(3,'合同描述及附件'));
    if(!$scope.onlyView){
    	columnWrapArray.push(new multipleColumn(4,'审批信息'));
 	}
    
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var tendersName = new FormElement(1,'标段名称','orgId','string','','50','select','getTendersInfo');
    formFieldArray.push(tendersName);
    formFieldArray.push(new FormElement(1,'合同编号','contractCode','string'));/*系统带出*/
    formFieldArray.push(new FormElement(1,'合同名称(中)','contractCnName','string'));
    formFieldArray.push(new FormElement(1,'起始桩号','startStakeNo','string'));
    formFieldArray.push(new FormElement(1,'结束桩号','endStakeNo','string'));
    formFieldArray.push(new FormElement(1,'合同名称(英)','contractEnName','string'));
    formFieldArray.push(new FormElement(1,'技术等级','technicalLevel','string',1,'30','select','','','','dic.highway_grade'));
    formFieldArray.push(new FormElement(1,'全长(KM)','overallLength','number'));
    formFieldArray.push(new FormElement(1,'开工日期','beginDate','string'));
    formFieldArray.push(new FormElement(1,'计划完工日期','planCompleteData','string'));
    formFieldArray.push(new FormElement(1,'建设单位','constructionCompanyName','string'));
    formFieldArray.push(new FormElement(1,'监理单位','supervisionCompanyName','string'));
    formFieldArray.push(new FormElement(1,'施工单位','buildCompanyName','string'));
    
    formFieldArray.push(new FormElement(2,'动员预付款总额(元)','mobilizeAdvanceAmount','number'));
    formFieldArray.push(new FormElement(2,'月扣回动员预付款比例(%)','deductedMobilizeAdvanceRatio','number'));
    formFieldArray.push(new FormElement(2,'动员预付款起扣金额(元)','mobilizeAdvanceDeductedMoney','number'));
    formFieldArray.push(new FormElement(2,'动员预付款全额扣回限额(元)','mobilizeAdvanceAmountDeductedLimit','number'));
    formFieldArray.push(new FormElement(2,'暂扣保留金额比例(%)','detainRetentionMoneyRatio','number'));
    formFieldArray.push(new FormElement(2,'保留金起扣金额(元)','retentionDeductedMoney','number'));
    formFieldArray.push(new FormElement(2,'累计暂扣保留金限额(元)','cumulativeDetainRetentionMoneyLimit','number'));
    formFieldArray.push(new FormElement(2,'材料扣回款比例(%)','materialDeductMoneyRatio','number'));
    formFieldArray.push(new FormElement(2,'材料预付款比例(%)','materialAdvanceMoneyRatio','number'));
    formFieldArray.push(new FormElement(2,'材料预付款限额(元)','materialAdvanceMoneyLimit','number'));
    formFieldArray.push(new FormElement(2,'迟付款日利息(‰)','latePaymentDailyInterest','number'));
    formFieldArray.push(new FormElement(2,'迟付款利息限额(元)','latePaymentInterestMoneyLimit','number'));
    formFieldArray.push(new FormElement(2,'违约金限额(元)','liquidatedDamagesLimit','number'));
    formFieldArray.push(new FormElement(2,'索赔金额限额(元)','claimMoneyLimit','number'));
    formFieldArray.push(new FormElement(2,'清单金额(元)','detailedListMoney','number'));
    formFieldArray.push(new FormElement(2,'计日工金额(元)','dayworkMoney','number'));
    formFieldArray.push(new FormElement(2,'暂定金金额(元)','provisionalMoney','number'));
    formFieldArray.push(new FormElement(2,'扣农民工工资保证金(元)','deductionMigrantWorkersSalaryDeposit','number'));
    formFieldArray.push(new FormElement(2,'增值税比例(%)','incrementDutyRatio','number'));
    formFieldArray.push(new FormElement(2,'合同总价(元)','contractTotalPrice','number'));
    formFieldArray.push(new FormElement(2,'复核总价(元)','reviewTotalPrice','number'));
    
    formFieldArray.push(new FormElement(3,'合同描述','contractDescription','string','','255','textarea','','','','','readonly'));
    var contractFileUUID = new FormElement(3,'附件','contractFile','string','','36','fileSingle');
    contractFileUUID.systemHeader = SYSTEM_HEADER.BUSI_HEADER;
    contractFileUUID.readonly = true;
 	formFieldArray.push(contractFileUUID);
 	/**
 	 * 循环设置只读
 	 */
 	for(var formField in formFieldArray){
 		formFieldArray[formField].readonly = true;
 	}
 	if(!$scope.onlyView){
 		var result=new FormElement(4,'审核','result','boolean',1,'128',"radio","resultChange");
 		result.items=[{code:"true",name:'同意'},{code:"false",name:"驳回"}];
 		/*result.value="true";*/
 		formFieldArray.push(result);
 		var resultMessage=new FormElement(4,'审核意见','resultMessage','string',1,'1000',"textarea");
 		/*resultMessage.value='同意';*/
 		formFieldArray.push(resultMessage);
 		formFieldArray.push(new FormElement(4,'意见模版','resultMessageTemplate','string',0,'512',"select"));
 	}
 	
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    
    /**
     * 模糊检索：标段名称(机构名称)
     */
    $scope.getTendersInfo = function () {
    	var orgId = tendersName.value;
    	$scope.promise = httpService.post($scope, "api/contractRestfulApi/getTendersInfoByOrgId/" + orgId)
								  .success(function (response) {
									  editService.setFieldValue($scope, "contractCode", response.contractCode);/*合同编号*/
									  editService.setFieldValue($scope, "startStakeNo", response.startStakeNo);/*起始桩号*/
									  editService.setFieldValue($scope, "endStakeNo", response.endStakeNo);/*结束桩号*/
									  editService.setFieldValue($scope, "constructionCompanyCode", response.constructionCompanyCode);/*建设单位编码*/
									  editService.setFieldValue($scope, "constructionCompanyName", response.constructionCompanyName);/*建设单位名称*/
									  editService.setFieldValue($scope, "supervisionCompanyCode", response.supervisionCompanyCode);/*监理单位编码*/
									  editService.setFieldValue($scope, "supervisionCompanyName", response.supervisionCompanyName);/*监理单位名称*/
									  editService.setFieldValue($scope, "buildCompanyCode", response.buildCompanyCode);/*施工单位编码*/
									  editService.setFieldValue($scope, "buildCompanyName", response.buildCompanyName);/*施工单位名称*/
								  })
								  .error(function(){
									  submitTips('根据机构编码获取标段信息出错(施工)','error');
								  });
    }
    
    /**
     * 加载编辑数据
     */
    if($scope.editId){
    	/*动态加载机构数据*/
    	editService.initFieldItems($scope, tendersName,'api/org/getFwOrgByType/7');/**初始化表单选择数据，包括字典数据，其他可选项数据.**/
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }
    
    /**
     * 流程提交
     **/   
    $scope.submit = function(){
    	editService.submitData($scope, WORKFLOW_COMMON_SUBMIT_URL);
    } 
    
});