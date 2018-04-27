angular.module('huatek.controllers').controller('MiddleMeasureAddController', function ($rootScope, $scope, $http, editService, httpService) {
	var addDataUrl = 'api/middleMeasure/add';
	var editDataUrl = 'api/middleMeasure/edit';
	var homeUrl = '/middleMeasure/home';
    
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
    
    
    
    formFieldArray.push(new FormElement(1,'计量期间','measureIssueNumber','string','require','100'));
    
    var isMeasureAdvance = new FormElement(1,'是否只计量动员预付款','isMeasureAdvance','string','require','30');
    isMeasureAdvance.value = [];
    formFieldArray.push(isMeasureAdvance);
    
    var createrName = new FormElement(1,'创建人','createrName','string','require','30','','','','','','readonly');
    createrName.value = $rootScope.userName;
    formFieldArray.push(createrName);
	formFieldArray.push(new FormElement(1,'创建时间','createTime','string','require','30','date','','',getDate(getNowFormatDate()),'readonly'));
    
    formFieldArray.push(new FormElement(3,'备注','remarks','string','','255','textarea'));
    var measureFileUUID = new FormElement(3,'附件','measureFile','string','','36','fileMultiple');
    measureFileUUID.systemHeader = "/root/measure/middleMeasure";
 	formFieldArray.push(measureFileUUID);
    
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