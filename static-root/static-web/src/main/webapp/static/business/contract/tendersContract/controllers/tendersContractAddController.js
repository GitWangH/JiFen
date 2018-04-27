angular.module('huatek.controllers').controller('TendersContractAddController', function ($rootScope, $scope, $http, editService, httpService) {
	var addDataUrl = 'api/tendersContract/add';
	var editDataUrl = 'api/tendersContract/edit';
	var homeUrl = '/tendersContract/home';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'合同概况'));
    columnWrapArray.push(new multipleColumn(2,'合同其他条款'));
    columnWrapArray.push(new multipleColumn(3,'合同描述及附件'));
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    if($rootScope.orgType == 7){/*机构类型为标段*/
    	var orgId = new FormElement(1,'标段ID','orgId','string','','50');
    	orgId.value = $rootScope.orgId;
    	orgId.isShow = false;
    	formFieldArray.push(orgId);
    	var tendersName = new FormElement(1,'标段名称','orgName','string','require','50','','','','','','readonly');
    	tendersName.value = $rootScope.orgName;
    	formFieldArray.push(tendersName);/*系统带出*/
    }else{
    	var tendersName = new FormElement(1,'标段名称','orgId','string','require','50','select','getTendersInfo');
    	formFieldArray.push(tendersName);
    }
    formFieldArray.push(new FormElement(1,'合同编号','contractCode','string','','50','','','','','','readonly'));/*系统带出*/
    formFieldArray.push(new FormElement(1,'合同名称(中)','contractCnName','string','require','100'));
    formFieldArray.push(new FormElement(1,'起始桩号','startStakeNo','string','','100','','','','','','readonly'));
    formFieldArray.push(new FormElement(1,'结束桩号','endStakeNo','string','','100','','','','','','readonly'));
    formFieldArray.push(new FormElement(1,'合同名称(英)','contractEnName','string','','100'));
    formFieldArray.push(new FormElement(1,'技术等级','technicalLevel','string','require','30','select','','','','dic.highway_grade'));
    formFieldArray.push(new FormElement(1,'全长(KM)','overallLength','number','','18'));
    if($scope.editId){
    	formFieldArray.push(new FormElement(1,'开工日期','beginDate','string','require','30','date'));
    	formFieldArray.push(new FormElement(1,'计划完工日期','planCompleteData','string','require','30','date'));
    }else{
    	formFieldArray.push(new FormElement(1,'开工日期','beginDate','string','require','30','date','','',getDate(getNowFormatDate())));
	    formFieldArray.push(new FormElement(1,'计划完工日期','planCompleteData','string','require','30','date','','',getDate(getNowFormatDate())));
    }
    formFieldArray.push(new FormElement(1,'建设单位','constructionCompanyName','string','','100','','','','','','readonly'));
    formFieldArray.push(new FormElement(1,'监理单位','supervisionCompanyName','string','','100','','','','','','readonly'));
    formFieldArray.push(new FormElement(1,'施工单位','buildCompanyName','string','','100','','','','','','readonly'));
    
    var constructionCompanyCode = new FormElement(1,'建设单位编码','constructionCompanyCode','string','','100');
    constructionCompanyCode.isShow = false;
    formFieldArray.push(constructionCompanyCode);
    var supervisionCompanyCode = new FormElement(1,'监理单位编码','supervisionCompanyCode','string','','100');
    supervisionCompanyCode.isShow = false;
    formFieldArray.push(supervisionCompanyCode);
    var buildCompanyCode = new FormElement(1,'施工单位编码','buildCompanyCode','string','','100');
    buildCompanyCode.isShow = false;
    formFieldArray.push(buildCompanyCode);
    
    var mobilizeAdvanceAmount = new FormElement(2,'动员预付款总额(元)','mobilizeAdvanceAmount','number','require');
    mobilizeAdvanceAmount.number_MaxValue = 99999999999999.9999;
    mobilizeAdvanceAmount.value = 0;
    formFieldArray.push(mobilizeAdvanceAmount);
    var deductedMobilizeAdvanceRatio = new FormElement(2,'月扣回动员预付款比例(%)','deductedMobilizeAdvanceRatio','number','require');
    deductedMobilizeAdvanceRatio.number_MaxValue = 100;
    deductedMobilizeAdvanceRatio.value = 0;
    formFieldArray.push(deductedMobilizeAdvanceRatio);
    var mobilizeAdvanceDeductedMoney = new FormElement(2,'动员预付款起扣金额(元)','mobilizeAdvanceDeductedMoney','number','require');
    mobilizeAdvanceDeductedMoney.number_MaxValue = 99999999999999.9999;
    mobilizeAdvanceDeductedMoney.value = 0;
    formFieldArray.push(mobilizeAdvanceDeductedMoney);
    var mobilizeAdvanceAmountDeductedLimit = new FormElement(2,'动员预付款全额扣回限额(元)','mobilizeAdvanceAmountDeductedLimit','number','require');
    mobilizeAdvanceAmountDeductedLimit.number_MaxValue = 99999999999999.9999;
    mobilizeAdvanceAmountDeductedLimit.value = 0;
    formFieldArray.push(mobilizeAdvanceAmountDeductedLimit);
    var detainRetentionMoneyRatio = new FormElement(2,'暂扣保留金额比例(%)','detainRetentionMoneyRatio','number','require');
    detainRetentionMoneyRatio.number_MaxValue = 100;
    detainRetentionMoneyRatio.value = 0;
    formFieldArray.push(detainRetentionMoneyRatio);
    var retentionDeductedMoney = new FormElement(2,'保留金起扣金额(元)','retentionDeductedMoney','number','require');
    retentionDeductedMoney.number_MaxValue = 99999999999999.9999;
    retentionDeductedMoney.value = 0;
    formFieldArray.push(retentionDeductedMoney);
    var cumulativeDetainRetentionMoneyLimit = new FormElement(2,'累计暂扣保留金限额(元)','cumulativeDetainRetentionMoneyLimit','number','require');
    cumulativeDetainRetentionMoneyLimit.number_MaxValue = 99999999999999.9999;
    cumulativeDetainRetentionMoneyLimit.value = 0;
    formFieldArray.push(cumulativeDetainRetentionMoneyLimit);
    var materialDeductMoneyRatio = new FormElement(2,'材料扣回款比例(%)','materialDeductMoneyRatio','number','require');
    materialDeductMoneyRatio.number_MaxValue = 100;
    materialDeductMoneyRatio.value = 0;
    formFieldArray.push(materialDeductMoneyRatio);
    var materialAdvanceMoneyRatio = new FormElement(2,'材料预付款比例(%)','materialAdvanceMoneyRatio','number','require');
    materialAdvanceMoneyRatio.number_MaxValue = 100;
    materialAdvanceMoneyRatio.value = 0;
    formFieldArray.push(materialAdvanceMoneyRatio);
    var materialAdvanceMoneyLimit = new FormElement(2,'材料预付款限额(元)','materialAdvanceMoneyLimit','number','require');
    materialAdvanceMoneyLimit.number_MaxValue = 99999999999999.9999;
    materialAdvanceMoneyLimit.value = 0;
    formFieldArray.push(materialAdvanceMoneyLimit);
    var latePaymentDailyInterest = new FormElement(2,'迟付款日利息(‰)','latePaymentDailyInterest','number','require');
    latePaymentDailyInterest.number_MaxValue = 1000;
    latePaymentDailyInterest.value = 0;
    formFieldArray.push(latePaymentDailyInterest);
    var latePaymentInterestMoneyLimit = new FormElement(2,'迟付款利息限额(元)','latePaymentInterestMoneyLimit','number','require');
    latePaymentInterestMoneyLimit.number_MaxValue = 99999999999999.9999;
    latePaymentInterestMoneyLimit.value = 0;
    formFieldArray.push(latePaymentInterestMoneyLimit);
    var liquidatedDamagesLimit = new FormElement(2,'违约金限额(元)','liquidatedDamagesLimit','number','require');
    liquidatedDamagesLimit.number_MaxValue = 99999999999999.9999;
    liquidatedDamagesLimit.value = 0;
    formFieldArray.push(liquidatedDamagesLimit);
    var claimMoneyLimit = new FormElement(2,'索赔金额限额(元)','claimMoneyLimit','number','require');
    claimMoneyLimit.number_MaxValue = 99999999999999.9999;
    claimMoneyLimit.value = 0;
    formFieldArray.push(claimMoneyLimit);
    var detailedListMoney = new FormElement(2,'清单金额(元)','detailedListMoney','number','require');
    detailedListMoney.number_MaxValue = 99999999999999.9999;
    detailedListMoney.value = 0;
    formFieldArray.push(detailedListMoney);
    var dayworkMoney = new FormElement(2,'计日工金额(元)','dayworkMoney','number','require');
    dayworkMoney.number_MaxValue = 99999999999999.9999;
    dayworkMoney.value = 0;
    formFieldArray.push(dayworkMoney);
    var provisionalMoney = new FormElement(2,'暂定金金额(元)','provisionalMoney','number','require');
    provisionalMoney.number_MaxValue = 99999999999999.9999;
    provisionalMoney.value = 0;
    formFieldArray.push(provisionalMoney);
    var deductionMigrantWorkersSalaryDeposit = new FormElement(2,'扣农民工工资保证金(元)','deductionMigrantWorkersSalaryDeposit','number','require');
    deductionMigrantWorkersSalaryDeposit.number_MaxValue = 99999999999999.9999;
    deductionMigrantWorkersSalaryDeposit.value = 0;
    formFieldArray.push(deductionMigrantWorkersSalaryDeposit);
    var incrementDutyRatio = new FormElement(2,'增值税比例(%)','incrementDutyRatio','number','require');
    incrementDutyRatio.number_MaxValue = 100;
    incrementDutyRatio.value = 0;
    formFieldArray.push(incrementDutyRatio);
    var contractTotalPrice = new FormElement(2,'合同总价(元)','contractTotalPrice','number','require');
    contractTotalPrice.number_MaxValue = 99999999999999.9999;
    contractTotalPrice.value = 0;
    formFieldArray.push(contractTotalPrice);
    var reviewTotalPrice = new FormElement(2,'复核总价(元)','reviewTotalPrice','number','require');
    reviewTotalPrice.number_MaxValue = 99999999999999.9999;
    reviewTotalPrice.value = 0;
    formFieldArray.push(reviewTotalPrice);
    
    formFieldArray.push(new FormElement(3,'合同描述','contractDescription','string','','255','textarea'));
    var contractFileUUID = new FormElement(3,'附件','contractFile','string','','36','fileMultiple');
    contractFileUUID.systemHeader = "/root/contract/tendersContract";
 	formFieldArray.push(contractFileUUID);
    
   /*日期开始 至 结束组件
    var startValidityDate = new FormElement(2,'有效期从','startValidityDate','date-section','require','30','date-section');
    startValidityDate.endTitle = "至";
    startValidityDate.endName = "endValidityDate"
    formFieldArray.push(startValidityDate);
    var endValidityDate = new FormElement(2,'','endValidityDate','string',0,'','notHandle');
    endValidityDate.isShow = false;
    formFieldArray.push(endValidityDate);
   */
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    /**
     * 模糊检索：标段名称(机构名称)
     */
    $scope.getTendersInfo = function (orgIdVal) {
    	var orgId = cnex4_isNotEmpty_str(orgIdVal)? orgIdVal:tendersName.value;
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
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }
    
    /**
     * 根据机构类型加载其它信息
     */
    if($rootScope.orgType == 7){/*机构类型为标段*/
    	$scope.getTendersInfo($rootScope.orgId);
    }else{
    	/*动态加载机构数据*/
    	editService.initFieldItems($scope, tendersName,'api/org/getFwOrgByType/7');/**初始化表单选择数据，包括字典数据，其他可选项数据.**/
    }
    
    /**
     * 修改
     **/   
    $scope.update = function(){
    	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 保存
     **/    
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl, homeUrl);
    }
    
});