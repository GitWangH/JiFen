/**
 * 项目基本信息 - 修改
 * busiProjectBaseInfoEditController
 */
'use strict';

angular.module('huatek.controllers').controller('busiProjectBaseInfoEditController', function ($scope, $location, $http, $routeParams, editService, cacheService,listService,shareData,httpService) {
	var addDataUrl = 'api/busiProjectBaseInfo/add';
	var editDataUrl = 'api/busiProjectBaseInfo/edit';
	var homeUrl = '/baseInfo/home';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'项目基本信息'));
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var projectFullName = new FormElement(1,'项目全称','projectFullName','string','require','100');
    formFieldArray.push(projectFullName);
    formFieldArray.push(new FormElement(1,'项目简称','projectAbbreviation','string','require','100'));
    formFieldArray.push(new FormElement(1,'项目编号','projectNumber','string','require','100'));
    formFieldArray.push(new FormElement(1,'立项人','projectSponsor','string','0','100'));
    formFieldArray.push(new FormElement(1,'项目状态','projectStatus','string','require','100','select','','','','dic.project_category'));
    var buildCompany = new FormElement(1,'建设单位','jsdw','string','100');
    buildCompany.value = $scope.passParams.jsdw || $scope.passParams['buildCompany'];
    buildCompany.readonly = true;
    formFieldArray.push(buildCompany);
    formFieldArray.push(new FormElement(1,'项目办地址','projectOfficeAddress','string','require','100'));
    formFieldArray.push(new FormElement(1,'立项日期','projectDate','string','require','30','date'));
    formFieldArray.push(new FormElement(1,'联系方式','contactInformation','string','0','100'));
    formFieldArray.push(new FormElement(1,'项目概算（万元）','projectBudgetEstimate','string','0','100'));
    formFieldArray.push(new FormElement(1,'竣工决算（万元）','finalAccountsOfCompletedProject','string','0','100'));
    formFieldArray.push(new FormElement(1,'开工日期','commencementDate','string','require','30','date'));
    formFieldArray.push(new FormElement(1,'竣工日期','completionDate','string','require','30','date'));
    formFieldArray.push(new FormElement(1,'项目描述','projectDescription','string','require','100','textarea'));
    formFieldArray.push(new FormElement(1,'备注','remark','string','require','100','textarea'));
    var orgIdForShow = new FormElement(1,'org','orgIdForShow','string','require','100');
    orgIdForShow.value = $scope.passParams.orgIdForShow;
    orgIdForShow.readonly = true;
    orgIdForShow.isShow = false;
    formFieldArray.push(orgIdForShow);

 	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    /**
     * 加载编辑数据
     */
    if($scope.editId){
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }
    /*动态加载机构数据*/
	/*editService.initFieldItems($scope, tendersName, 'api/org/getFwOrgByType/3');*/ 
    /**初始化表单选择数据，包括字典数据，其他可选项数据.**/
	
	
    /**
     * 修改
     **/   
    $scope.update = function(){
    	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId,null ,$scope.passParams.load);
    } 
    /**
     * 保存
     **/    
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl, homeUrl,'',$scope.passParams.load);
    }
});