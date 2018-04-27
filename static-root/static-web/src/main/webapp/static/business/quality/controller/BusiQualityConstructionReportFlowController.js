'use strict';
/**
 * 施工报检申请流程审批
 */
angular.module('huatek.controllers').controller('BusiQualityConstructionReportFlowController', function($scope, $http, $rootScope, editService, httpService, cacheService) {
    $scope.busiCode = $scope.passParams.busiCode;
    $scope.taskId = $scope.passParams.taskId;
    $scope.busiId = $scope.passParams.busiId;
    $scope.taskKey = $scope.passParams.taskKey;
    $scope.taskName = $scope.passParams.taskName;
    $scope.processInstanceId = $scope.passParams.processInstanceId;
    $scope.onlyView = $scope.passParams.onlyView;
    $scope.editId = $scope.busiId;

    var _prefix = 'api/constructionInspection/';
    var approveDataUrl = _prefix + 'conInspectApprove';
    var editDataUrl = _prefix + 'edit';

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn1(1, '报检信息'));
    columnWrapArray.push(new multipleColumn1(2, '审批', !$scope.onlyView));
    var rectColumn = new multipleColumn1(3, '整改通知单', false);
    columnWrapArray.push(rectColumn);

    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var orgId1 = new FormElement(1, '标段名称', 'orgId1', 'string', 'require', '50', FORM_COMPONENT.TENDERS_SLECT);
    orgId1.readonly = true;
    /*orgId.isShow = false;*/
    formFieldArray.push(orgId1);
    var orgName = new FormElement(1, '标段名称', 'orgName', 'string', '', '50');
    orgName.isShow = false;
    formFieldArray.push(orgName);
    formFieldArray.push(new FormElement(1,'开工日期','beginConstructionDate','string','','30','date'));
    formFieldArray.push(new FormElement(1,'报检日期','inspectionDate','string','','30','date'));
    var isCompleted = new FormElement(1, '', 'isCompleted', 'string', '', '50', 'checkbox');
    isCompleted.content = '该部位已完成';
    formFieldArray.push(isCompleted);
    var tendersBranchId = new FormElement(1,'分部分项','tendersBranchId','string','','50','');
    tendersBranchId.isShow =false;
    formFieldArray.push(tendersBranchId);
    var tendersBranch = new FormElement(1, '分部分项名称', 'tendersBranchName', 'string', '', '500', 'longInput');
    formFieldArray.push(tendersBranch);
    formFieldArray.push(new FormElement(1,'检测段落','checkParagraph','string',1,'200'));
    formFieldArray.push(new FormElement(1,'报检人员','inspectionUserName','string','','50'));
    formFieldArray.push(new FormElement(1,'报检内容','inspectionContent','string','','255','textarea'));
    var contractFileUUID = new FormElement(1,'附件','attachmentFile','string','','100','fileMultiple');
 	formFieldArray.push(contractFileUUID);
	var result=new FormElement(2,'是否合格','result','boolean',1,'128',"radio","resultChange");
	result.items=[{code:"true",name:'合格'},{code:"false",name:"不合格"}];
	formFieldArray.push(result);
	var resultMessage=new FormElement(2,'备注','resultMessage','string',1,'1000',"textarea");
	formFieldArray.push(resultMessage);
	var passRecordFileId = new FormElement(2,'附件','passRecordFileId','string','','100','fileMultiple');
	passRecordFileId.systemHeader = "/root/quality/constructionApply";
 	formFieldArray.push(passRecordFileId);
 	var flowStep=new FormElement(2,'流程步骤','flowStep','string',0,'1000');
 	flowStep.value = $scope.taskKey;
 	flowStep.isShow = false;
	formFieldArray.push(flowStep);
	var flowStepName=new FormElement(2,'流程步骤名称','flowStepName','string',0,'1000');
	flowStepName.value = $scope.taskName;
	flowStepName.isShow = false;
	formFieldArray.push(flowStepName);


    var orgId = new FormElement(3, '被通知单位', 'orgId', 'string', '', '50', FORM_COMPONENT.TENDERS_SLECT);
    orgId.lookValue = 'orgName';
    orgId.isShow = false;
    formFieldArray.push(orgId);
    var checkNo = new FormElement(3, '检查编号', 'checkNo', 'string', 0, '30');
    checkNo.isShow = false;
    formFieldArray.push(checkNo);
    var rectificationCheckTime = new FormElement(3, '检查时间', 'rectificationCheckTime', 'string', 0, '30', 'date', '', '', getDate(getNowFormatDate()));
    rectificationCheckTime.isShow = false;
    formFieldArray.push(rectificationCheckTime);
    var rectificationPersonLiable = new FormElement(3, '相关责任人', 'rectificationPersonLiable', 'string', 0, '30');
    rectificationPersonLiable.isShow = false;
    formFieldArray.push(rectificationPersonLiable);
    var rectificationUrgency = new FormElement(3, '紧急程度', 'rectificationUrgency', 'string', 0, '30', 'select', '', '', '', 'dic.emergency_degree');
    rectificationUrgency.isShow = false;
    formFieldArray.push(rectificationUrgency);
    var rectificationPeriod = new FormElement(3, '整改期限(天)', 'rectificationPeriod', 'number', 0, '30');
    rectificationPeriod.isShow = false;
    formFieldArray.push(rectificationPeriod);
    var rectificationPerson = new FormElement(3, '检查人员', 'rectificationPerson', 'string', 0, '30', null, '', '', $rootScope.userName);
    rectificationPerson.isShow = false;
    formFieldArray.push(rectificationPerson);
    var rectificationCheckContent = new FormElement(3, '检查内容', 'rectificationCheckContent', 'string', 0, '500', 'textarea');
    rectificationCheckContent.isShow = false;
    formFieldArray.push(rectificationCheckContent);
    var rectificationExistingProblems = new FormElement(3, '存在问题', 'rectificationExistingProblems', 'string', 0, '500', 'textarea');
    rectificationExistingProblems.isShow = false;
    formFieldArray.push(rectificationExistingProblems);
    var rectificationRequirements = new FormElement(3, '整改要求', 'rectificationRequirements', 'string', 0, '500', 'textarea');
    rectificationRequirements.isShow = false;
    formFieldArray.push(rectificationRequirements);
    var rectificationPunishmentSuggestion = new FormElement(3, '处罚建议', 'rectificationPunishmentSuggestion', 'string', 0, '500', 'textarea');
    rectificationPunishmentSuggestion.isShow = false;
    formFieldArray.push(rectificationPunishmentSuggestion);
    var contractFileUUID = new FormElement(3, '附件', 'fileId', 'string', 0, '200', 'fileMultiple');
    contractFileUUID.isShow = false;
    contractFileUUID.systemHeader = "/root/quality/constructInspection";
    /*contractFileUUID.systemHeader = "/root/quality/rawMaterial/rawMaterial";*/
    formFieldArray.push(contractFileUUID);

    var inspectionCode = new FormElement(3, '整改编号', 'inspectionCode', 'string', 0, '30');
    inspectionCode.isShow = false;
    formFieldArray.push(inspectionCode);

    for (var i = 0; i < formFieldArray.length; i++) {
        if (formFieldArray[i].column === 1) {
            formFieldArray[i].readonly = true;
        }
    }

    $scope.isOK = false;
    /**
     * 是否合格 change 事件
     */
    $scope.resultChange = function() {
        if (result.value == 'true') { /* true */
            $scope.isOK = false;
            rectColumn.isShow = false;
            resultMessage.isShow = true;
            resultMessage.require = true;
            passRecordFileId.isShow = true;
            /*施工报检选择不合格时, 检查内容/存在问题/整改要求/处罚建议应为必填*/
            /*rectificationCheckContent.require = 1;
            rectificationExistingProblems.require = 1;
            rectificationRequirements.require = 1;
            rectificationPunishmentSuggestion.require = 1;*/
            for (var i = 0; i < formFieldArray.length; i++) {
                if (formFieldArray[i].column === 3) {
                    formFieldArray[i].isShow = false;
                    formFieldArray[i].require = false;
                }
            }
        } else {
            rectColumn.isShow = true;
            $scope.isOK = true;
            orgId.readonly = true;
            for (var i = 0; i < formFieldArray.length; i++) {
                if (formFieldArray[i].column === 3) {
                    formFieldArray[i].isShow = true;
                    resultMessage.isShow = false;
                    resultMessage.require = false;
                    passRecordFileId.isShow = false;
                    /*施工报检选择不合格时, 检查内容/存在问题/整改要求/处罚建议应为非必填*/
                    if (formFieldArray[i].name != 'fileId' && formFieldArray[i].name != 'rectificationCheckContent' &&  
                        formFieldArray[i].name != 'rectificationExistingProblems' &&  formFieldArray[i].name != 'rectificationRequirements' && 
                         formFieldArray[i].name != 'rectificationPunishmentSuggestion') {
                        formFieldArray[i].require = true;
                    }
                }
            }
            inspectionCode.isShow = false;
        }
    }

    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);
    
    var loadSuccessBack = function(){
    	orgId1.value = orgId.value;
    	orgId1.lookValue = orgName.value;
    }

    /**
     * 加载编辑数据
     */
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId,loadSuccessBack);
    }

    /**
     * 流程提交
     **/
    $scope.submit = function() {
        editService.submitData($scope, approveDataUrl);
    }

});