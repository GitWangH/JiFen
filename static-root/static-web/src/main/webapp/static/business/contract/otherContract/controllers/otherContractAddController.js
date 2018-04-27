angular.module('huatek.controllers').controller('OtherContractAddController', function ($rootScope, $scope, $http, $routeParams, editService) {
	var addDataUrl = 'api/otherContract/add';
	var editDataUrl = 'api/otherContract/edit';
	var homeUrl = '/otherContract/home';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'合同概况'));
    columnWrapArray.push(new multipleColumn(2,'合同描述及附件'));
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
    
    formFieldArray.push(new FormElement(1,'合同名称','contractName','string','require','100'));
    if($scope.editId){
    	formFieldArray.push(new FormElement(1,'签订日期','signatureData','string','require','30','date'));
    }else{
    	formFieldArray.push(new FormElement(1,'签订日期','signatureData','string','require','30','date','','',getDate(getNowFormatDate())));
    }
    formFieldArray.push(new FormElement(2,'合同描述','contractDescription','string','','255','textarea'));
    var contractFileUUID = new FormElement(2,'附件','contractFile','string','','36','fileMultiple');
    contractFileUUID.systemHeader = "/root/contract/otherContract";
 	formFieldArray.push(contractFileUUID);

 	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    /**
     * 加载编辑数据
     */
    if($scope.editId){
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }
    
    /**
     * 根据机构类型加载其它信息
     */
	if($rootScope.orgType != 7){/*机构类型为标段*/
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