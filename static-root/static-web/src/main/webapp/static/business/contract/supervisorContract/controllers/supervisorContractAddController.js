angular.module('huatek.controllers').controller('SupervisorContractAddController', function ($rootScope, $scope, $http, httpService, editService) {
	var addDataUrl = 'api/supervisorContract/add';
	var editDataUrl = 'api/supervisorContract/edit';
	var homeUrl = '/supervisorContract/home';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'合同概况'));
    columnWrapArray.push(new multipleColumn(2,'合同描述及附件'));
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    if($rootScope.orgType == 6){/*机构类型为监理*/
    	var orgId = new FormElement(1,'标段ID','orgId','string','','50');
    	orgId.value = $rootScope.orgId;
    	orgId.isShow = false;
    	formFieldArray.push(orgId);
    	var companyName = new FormElement(1,'单位名称','orgName','string','require','50','','','','','','readonly');
    	companyName.value = $rootScope.orgName;
    	formFieldArray.push(companyName);/*系统带出*/
    }else{
    	var companyName = new FormElement(1,'单位名称','orgId','string','require','50','select','getOrgInfo');
        formFieldArray.push(companyName);
    }
    formFieldArray.push(new FormElement(1,'合同名称','contractName','string','require','100'));
    formFieldArray.push(new FormElement(1,'合同编号','contractCode','string','','50','','','','','','readonly'));
    if($scope.editId){
    	formFieldArray.push(new FormElement(1,'签订日期','signatureData','string','require','30','date'));
    }else{
    	formFieldArray.push(new FormElement(1,'签订日期','signatureData','string','require','30','date','','',getDate(getNowFormatDate())));
    }
    var contractTotalPrice = new FormElement(1,'合同金额(元)','contractTotalPrice','number');
    contractTotalPrice.number_MaxValue = 99999999999999.9999;
    formFieldArray.push(contractTotalPrice);
    var provisionalMoney = new FormElement(2,'暂定金(元)','provisionalMoney','number');
    provisionalMoney.number_MaxValue = 99999999999999.9999;
    formFieldArray.push(provisionalMoney);
    
    formFieldArray.push(new FormElement(2,'合同描述','contractDescription','string','','255','textarea'));
    var contractFileUUID = new FormElement(2,'附件','contractFile','string','','36','fileMultiple');
    contractFileUUID.systemHeader = "/root/contract/supervisorContract";
 	formFieldArray.push(contractFileUUID);
    
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    /**
     * 模糊检索：标段名称(机构名称)
     */
    $scope.getOrgInfo = function (orgIdVal) {
    	var orgId = cnex4_isNotEmpty_str(orgIdVal)? orgIdVal:companyName.value;
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
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }
	
	/**
     * 根据机构类型加载其它信息
     */
    if($rootScope.orgType == 6){/*机构类型为标段*/
    	$scope.getOrgInfo($rootScope.orgId);
    }else{
    	/*动态加载机构数据*/
    	editService.initFieldItems($scope, companyName,'api/org/getFwOrgByType/6');/**初始化表单选择数据，包括字典数据，其他可选项数据.**/
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