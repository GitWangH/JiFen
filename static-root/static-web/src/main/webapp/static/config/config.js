/**配置报表服务器的路径**/
var reportServerList = ['121.41.98.142:8080', '121.41.98.142:8180', '121.41.98.142:8280', '121.41.98.142:8380'];
var reportServiceURL = 'http://' + reportServerList[Math.round(Math.random() * 3)] + '/cnex4ReportPreview/frameset?__report=';

var TemplatePrefix = 'static';
var DirectiveTemplatePrefix = '';
var WORKFLOW_COMMON_SUBMIT_URL = 'api_workflow/myjob/commonApprove';
var reportPath = 'reportFiles';
var mapFajian = "";
var mapshoujian = "";
var lngInfo = "";
var latInfo = "";
/*记录车牌号用于寻车*/
var cartruckNo = "";
var previousLoc = "/";
var currentReportCode = null;
var URL_PATH = { BUSI_HEADER: 'api_busi', REPORT_HEADER: 'api_report', OA_HEADER: 'api_oa', CMD_HEADER: 'api_cmd', WH_HEADER: 'api_wh', FIM_HEADER: 'api_fim', MDM_HEADER: 'api_mdm', FILE_HEADER: "api_file", IMAGE_HEADER: "api_image", PUBLIC_HEADER: "api/public" };
var SYSTEM_HEADER = { BUSI_HEADER: 'busi', OA_HEADER: 'oa', CMD_HEADER: 'cmd', WH_HEADER: 'wh', FIM_HEADER: 'fim', MDM_HEADER: 'mdm' };
var templateBasePath = "";

/*global variable*/
var huatek_global = {
    "css_path": "static/css/",
    "images_path": "static/images/",
    "business_path": "static/business/",
    "template_path": "static/templates/"
};
/*屏幕的宽度和高度*/
var client = { "clientWidth": document.body.clientWidth, "clientHeight": document.body.clientHeight };


var actionMap = new Map();


var controllerProvider = null;

var TEMPLATE_NOT_FOUND_PATH = "static/templates/template_not_found.html";

var TREE_NODE_UP = "up",
    TREE_NODE_DOWN = "dn";

var TENDERS_BY_USER_URL = "api/org/getFwOrgByType/7";

var SEARCH_COMPONENT = {
    SELECT: "select",
    SELECTNEW: "selectNew",
    SELECT_AUTOCOMPLAE: "select-autocomplete",
    CHECKBOX: "checkbox",
    DATE: "date",
    TIME: "time",
    DATETIME: "dateTime",
    DATEMONTH: "dateMonth",
    AUTOCOMPLETE: "autocomplete",
    TENDERS_SLECT: "tenderSelect",
    GROUPS_SELECT: "groupSelect",
    TENDERS_MULTIPLE_SLECT: "tenderMultipleSelect",
    SELECT_TREE_SINGLE: "selectTreeSingle"
};
var FORM_COMPONENT = {
    SELECT: "select",
    SELECTNEW: "selectNew",
    SELECT_AUTOCOMPLAE: "select-autocomplete",
    CHECKBOX: "checkbox",
    DATE: "date",
    TIME: "time",
    DATETIME: "dateTime",
    DATEMONTH: "dateMonth",
    AUTOCOMPLETE: "autocomplete",
    TENDERS_SLECT: "tenderSelect",
    GROUPS_SELECT: "groupSelect",
    TENDERS_MULTIPLE_SLECT: "tenderMultipleSelect",
    SELECT_TREE_SINGLE: "selectTreeSingle",
    SELECT_TREE_MULTIPLE: "selectTreeMutiple"
};
var ROW_OPERATION = { ADD_TOP: "addTop", MODIFY: "modify", ADD_CHILD: "addChild" };

var HTTP_METHED = { GET: "get", POST: "post" };

var cnex4_limit_quantity = 10;

var CUSTOMER_BTN = {LOOK_DETAIL:"lookDetail"}

var CUSTOMER_BTN = { LOOK_DETAIL: "lookDetail" };
var controllerMap = new Map();
controllerMap.put("FwDepartmentController","static/business/system/dept/controllers/FwDepartmentController.js");
controllerMap.put("FwDepartmentAddController","static/business/system/dept/controllers/FwDepartmentController.js");
controllerMap.put("FwDepartmentDetailController","static/business/system/dept/controllers/FwDepartmentController.js");
controllerMap.put("OrgController","static/business/system/org/controllers/OrgController.js");
controllerMap.put("OrgAddController","static/business/system/org/controllers/OrgController.js");
controllerMap.put("FwApplyScopeController","static/business/system/role/controllers/FwApplyScopeController.js");
controllerMap.put("FwApplyScopeAddController","static/business/system/role/controllers/FwApplyScopeController.js");
controllerMap.put("FwBusinessMapController","static/business/system/role/controllers/FwBusinessMapController.js");
controllerMap.put("FwBusinessMapAddController","static/business/system/role/controllers/FwBusinessMapController.js");
controllerMap.put("FwDataRoleController","static/business/system/role/controllers/FwDataRoleController.js");
controllerMap.put("FwDataRoleAssginController","static/business/system/role/controllers/FwDataRoleController.js");
controllerMap.put("FwDataRoleAssginTreeController","static/business/system/role/controllers/FwDataRoleController.js");
controllerMap.put("FwSourceEntityController","static/business/system/role/controllers/FwSourceEntityController.js");
controllerMap.put("FwSourceEntityAddController","static/business/system/role/controllers/FwSourceEntityController.js");
controllerMap.put("RoleController","static/business/system/role/controllers/RoleController.js");
controllerMap.put("RoleAddController","static/business/system/role/controllers/RoleController.js");
controllerMap.put("RoleAssginSourceController","static/business/system/role/controllers/RoleController.js");
controllerMap.put("FwRoleGroupAddController","static/business/system/role/controllers/RoleController.js");
controllerMap.put("RoleAssginAcctController","static/business/system/role/controllers/RoleController.js");
controllerMap.put("FwAccountController","static/business/system/user/controllers/FwAccountController.js");
controllerMap.put("FwAccountAddController","static/business/system/user/controllers/FwAccountController.js");
controllerMap.put("UserAssginController","static/business/system/user/controllers/FwAccountController.js");
controllerMap.put("EditPasswordController","static/business/system/user/controllers/FwAccountController.js");
controllerMap.put("FwAccountResetPassController","static/business/system/user/controllers/FwAccountController.js");
controllerMap.put("FwTenantController","static/business/system/user/controllers/FwTenantController.js");
controllerMap.put("FwTenantAddController","static/business/system/user/controllers/FwTenantController.js");
controllerMap.put("ChangeDateController","static/business/system/user/controllers/FwTenantController.js");
controllerMap.put("FwStationController","static/business/system/station/controllers/FwStationController.js");
controllerMap.put("FwStationAddController","static/business/system/station/controllers/FwStationController.js");
controllerMap.put("FwStationDetailController","static/business/system/station/controllers/FwStationController.js");
controllerMap.put("FwStationAssignAcctController","static/business/system/station/controllers/FwStationController.js");
controllerMap.put("categoryController","static/business/system/dictionary/controllers/categoryController.js");
controllerMap.put("categoryModifyController","static/business/system/dictionary/controllers/categoryController.js");
controllerMap.put("ConfigController","static/business/system/config/controllers/ConfigController.js");
controllerMap.put("ConfigEditController","static/business/system/config/controllers/ConfigController.js");
controllerMap.put("ExceptionController","static/business/system/exception/controllers/ExceptionController.js");
controllerMap.put("ExceptionDetailController","static/business/system/exception/controllers/ExceptionController.js");
controllerMap.put("FimActionLogController","static/business/system/actionLog/controllers/FwActionLogController.js");
controllerMap.put("FwOnlineController","static/business/system/online/controllers/FwOnlineController.js");
controllerMap.put("FwApplyScopeController","static/business/system/dataAuth/FwApplyScopeController.js");
controllerMap.put("FwApplyScopeAddController","static/business/system/dataAuth/FwApplyScopeController.js");
controllerMap.put("FwBusinessMapController","static/business/system/dataAuth/FwBusinessMapController.js");
controllerMap.put("FwBusinessMapAddController","static/business/system/dataAuth/FwBusinessMapController.js");
controllerMap.put("FwSourceEntityController","static/business/system/dataAuth/FwSourceEntityController.js");
controllerMap.put("FwSourceEntityAddController","static/business/system/dataAuth/FwSourceEntityController.js");
controllerMap.put("TaskConfigController","static/business/task/controllers/TaskConfigController.js");
controllerMap.put("TaskConfigAddController","static/business/task/controllers/TaskConfigController.js");
controllerMap.put("TaskEngineController","static/business/task/controllers/TaskEngineController.js");
controllerMap.put("TaskEngineAddController","static/business/task/controllers/TaskEngineController.js");
controllerMap.put("TaskHistoryController","static/business/task/controllers/TaskHistoryController.js");
controllerMap.put("TaskExceptionLogController","static/business/task/controllers/TaskHistoryController.js");
controllerMap.put("CmdExportConfigController","static/business/cmd/excelExport/controllers/CmdExportConfigController.js");
controllerMap.put("CmdExportConfigAddController","static/business/cmd/excelExport/controllers/CmdExportConfigController.js");
controllerMap.put("CmdExportLogController","static/business/cmd/excelExport/controllers/CmdExportConfigController.js");
controllerMap.put("CmdExportFieldConfigController","static/business/cmd/excelExport/controllers/CmdExportFieldConfigController.js");
controllerMap.put("CmdExportFieldConfigAddController","static/business/cmd/excelExport/controllers/CmdExportFieldConfigController.js");
controllerMap.put("CmdExportTableConfigController","static/business/cmd/excelExport/controllers/CmdExportTableConfigController.js");
controllerMap.put("CmdExportTableConfigAddController","static/business/cmd/excelExport/controllers/CmdExportTableConfigController.js");
controllerMap.put("CmdImportConfigController","static/business/cmd/excelImport/controllers/CmdImportConfigController.js");
controllerMap.put("CmdImportConfigAddController","static/business/cmd/excelImport/controllers/CmdImportConfigController.js");
controllerMap.put("CmdImportController","static/business/cmd/excelImport/controllers/CmdImportController.js");
controllerMap.put("ExcelImportLogDetailController","static/business/cmd/excelImport/controllers/CmdImportController.js");
controllerMap.put("CmdImportFieldConfigController","static/business/cmd/excelImport/controllers/CmdImportFieldConfigController.js");
controllerMap.put("CmdImportFieldConfigAddController","static/business/cmd/excelImport/controllers/CmdImportFieldConfigController.js");
controllerMap.put("ExcelUploadController","static/business/cmd/excelImport/controllers/ExcelUploadController.js");
controllerMap.put("ExcelImportController","static/business/cmd/excelImport/controllers/ExcelUploadController.js");
controllerMap.put("CmdInterfaceReceiveMessageController","static/business/cmd/external/controllers/CmdInterfaceReceiveMessageController.js");
controllerMap.put("CmdInterfaceReceiveMessageDetailController","static/business/cmd/external/controllers/CmdInterfaceReceiveMessageController.js");
controllerMap.put("ApplyListController","static/business/workflow/apply/controllers/ApplyController.js");
controllerMap.put("ApplyHisController","static/business/workflow/apply/controllers/ApplyController.js");
controllerMap.put("ApplyEasyFormController","static/business/workflow/apply/controllers/ApplyController.js");
controllerMap.put("EasyFormApplyLinkController","static/business/workflow/apply/controllers/ApplyController.js");
controllerMap.put("ApplySelectController","static/business/workflow/apply/controllers/ApplyController.js");
controllerMap.put("WorkflowClaimController","static/business/workflow/task/controllers/TaskController.js");
controllerMap.put("WorkflowToDoController","static/business/workflow/task/controllers/TaskController.js");
controllerMap.put("WorkflowDoneController","static/business/workflow/task/controllers/TaskController.js");
controllerMap.put("WorkflowDelegateController","static/business/workflow/task/controllers/TaskController.js");
controllerMap.put("WorkflowBatchApproveController","static/business/workflow/task/controllers/TaskController.js");
controllerMap.put("EasyFormWorkflowAuditingController","static/business/workflow/easyformTemplate/controllers/EasyFormController.js");
controllerMap.put("EasyFormWorkflowShareController","static/business/workflow/easyformTemplate/controllers/EasyFormController.js");
controllerMap.put("EasyFormWorkflowViewController","static/business/workflow/easyformTemplate/controllers/EasyFormController.js");
controllerMap.put("EasyFormPreviewController","static/business/workflow/easyformTemplate/controllers/EasyFormController.js");
controllerMap.put("EasyFormWorkflowPrintController","static/business/workflow/easyformTemplate/controllers/EasyFormController.js");
controllerMap.put("ApproveMessageTemplateController","static/business/workflow/easyformTemplate/controllers/EasyFormController.js");
controllerMap.put("FormConfigController","static/business/workflow/easyfrom/controllers/FormConfigController.js");
controllerMap.put("FormConfigAddController","static/business/workflow/easyfrom/controllers/FormConfigController.js");
controllerMap.put("FormConfigMenuController","static/business/workflow/easyfrom/controllers/FormConfigController.js");
controllerMap.put("FormEntityConfigController","static/business/workflow/easyfrom/controllers/FormEntityConfigController.js");
controllerMap.put("FormEntityConfigAddController","static/business/workflow/easyfrom/controllers/FormEntityConfigController.js");
controllerMap.put("FormEntityFieldConfigController","static/business/workflow/easyfrom/controllers/FormEntityFieldConfigController.js");
controllerMap.put("FormEntityFieldConfigAddController","static/business/workflow/easyfrom/controllers/FormEntityFieldConfigController.js");
controllerMap.put("FormFieldConfigController","static/business/workflow/easyfrom/controllers/FormFieldConfigController.js");
controllerMap.put("FormFieldConfigAddController","static/business/workflow/easyfrom/controllers/FormFieldConfigController.js");
controllerMap.put("FormFieldGroupController","static/business/workflow/easyfrom/controllers/FormFieldConfigController.js");
controllerMap.put("FormWorkflowVariablesController","static/business/workflow/easyfrom/controllers/FormWorkflowVariablesController.js");
controllerMap.put("FormWorkflowVariablesAddController","static/business/workflow/easyfrom/controllers/FormWorkflowVariablesController.js");
controllerMap.put("processProgressController","static/business/workflow/process/controllers/ProcessController.js");
controllerMap.put("WorkflowSetPersonController","static/business/workflow/process/controllers/ProcessController.js");
controllerMap.put("ResultMessageDetailController","static/business/workflow/process/controllers/ProcessController.js");
controllerMap.put("WorkflowAuthorityController","static/business/workflow/process/controllers/WorkflowAuthorityController.js");
controllerMap.put("WorkflowAuthorityAddPersonController","static/business/workflow/process/controllers/WorkflowAuthorityController.js");
controllerMap.put("WorkflowAuthorityAddGroupController","static/business/workflow/process/controllers/WorkflowAuthorityController.js");
controllerMap.put("WorkflowBlueController","static/business/workflow/process/controllers/WorkflowBlueController.js");
controllerMap.put("WorkflowBlueAddController","static/business/workflow/process/controllers/WorkflowBlueController.js");
controllerMap.put("WorkflowBlueFormController","static/business/workflow/process/controllers/WorkflowBlueController.js");
controllerMap.put("WorkflowBlueController","static/business/workflow/process/controllers/WorkflowBlueController.js");
controllerMap.put("WorkflowBlueAddController","static/business/workflow/process/controllers/WorkflowBlueController.js");
controllerMap.put("WorkflowBlueFormController","static/business/workflow/process/controllers/WorkflowBlueController.js");
controllerMap.put("WorkflowModelController","static/business/workflow/process/controllers/WorkflowModelController.js");
controllerMap.put("WorkflowModelAddController","static/business/workflow/process/controllers/WorkflowModelController.js");
controllerMap.put("WorkflowModelFileController","static/business/workflow/process/controllers/WorkflowModelController.js");
controllerMap.put("WorkflowModelDesignController","static/business/workflow/process/controllers/WorkflowModelController.js");
controllerMap.put("WorkflowNodeAuthorityController","static/business/workflow/process/controllers/WorkflowNodeAuthorityController.js");
controllerMap.put("WorkflowNodeAuthorityAddPersonController","static/business/workflow/process/controllers/WorkflowNodeAuthorityController.js");
controllerMap.put("WorkflowNodeAuthorityAddGroupController","static/business/workflow/process/controllers/WorkflowNodeAuthorityController.js");
controllerMap.put("WorkflowNodeAuthorityPersonScopeController","static/business/workflow/process/controllers/WorkflowNodeAuthorityController.js");
controllerMap.put("WorkflowNodeController","static/business/workflow/process/controllers/WorkflowNodeController.js");
controllerMap.put("WorkflowNodeDueController","static/business/workflow/process/controllers/WorkflowNodeController.js");
controllerMap.put("WorkflowNodeCanSetPersonController","static/business/workflow/process/controllers/WorkflowNodeController.js");
controllerMap.put("WorkflowNodeFormController","static/business/workflow/process/controllers/WorkflowNodeController.js");
controllerMap.put("CatalogController","static/business/cmd/material/catalog/controllers/CatalogController.js");
controllerMap.put("CatalogAddController","static/business/cmd/material/catalog/controllers/CatalogController.js");
controllerMap.put("CatalogAssignController","static/business/cmd/material/catalog/controllers/CatalogController.js");
controllerMap.put("FileManagerController","static/business/cmd/material/fileManager/controllers/FileManagerController.js");
controllerMap.put("FileManagerUploadController","static/business/cmd/material/fileManager/controllers/FileManagerController.js");
controllerMap.put("CmdMachineController","static/business/cmd/machine/controllers/CmdMachineController.js");
controllerMap.put("CmdMachineAddController","static/business/cmd/machine/controllers/CmdMachineController.js");
controllerMap.put("TendersContractHomeController","static/business/contract/tendersContract/controllers/tendersContractHomeController.js");
controllerMap.put("TendersContractAddController","static/business/contract/tendersContract/controllers/tendersContractAddController.js");
controllerMap.put("TendersContractApproveFlowController","static/business/contract/tendersContract/controllers/tendersContractApproveFlowController.js");
controllerMap.put("TendersContractDetailHomeController","static/business/contract/tendersContractDetail/controllers/tendersContractDetailHomeController.js");
controllerMap.put("TendersContractEngineeringQuantityDetailController","static/business/contract/tendersContractDetail/controllers/tendersContractEngineeringQuantityDetailController.js");
controllerMap.put("TendersContractDetailApproveFlowController","static/business/contract/tendersContractDetail/controllers/tendersContractDetailApproveFlowController.js");
controllerMap.put("TendersContractViewRelationBranchController","static/business/contract/tendersContractDetail/controllers/tendersContractViewRelationBranchController.js");
controllerMap.put("TendersContractDetailCheckHomeController","static/business/contract/tendersContractDetailCheck/controllers/tendersContractDetailCheckHomeController.js");
controllerMap.put("TendersContractDetailCheckApproveFlowController","static/business/contract/tendersContractDetailCheck/controllers/tendersContractDetailCheckApproveFlowController.js");
controllerMap.put("SupervisorContractHomeController","static/business/contract/supervisorContract/controllers/supervisorContractHomeController.js");
controllerMap.put("SupervisorContractAddController","static/business/contract/supervisorContract/controllers/supervisorContractAddController.js");
controllerMap.put("SupervisorContractApproveFlowController","static/business/contract/supervisorContract/controllers/supervisorContractApproveFlowController.js");
controllerMap.put("SupervisorContractDetailHomeController","static/business/contract/supervisorContractDetail/controllers/supervisorContractDetailHomeController.js");
controllerMap.put("OtherContractHomeController","static/business/contract/otherContract/controllers/otherContractHomeController.js");
controllerMap.put("OtherContractAddController","static/business/contract/otherContract/controllers/otherContractAddController.js");
controllerMap.put("OtherContractDetailHomeController","static/business/contract/otherContractDetail/controllers/otherContractDetailHomeController.js");
controllerMap.put("TendersBranchHomeController","static/business/contract/tendersBranch/controllers/tendersBranchHomeController.js");
controllerMap.put("TendersBranchApproveFlowController","static/business/contract/tendersBranch/controllers/tendersBranchApproveFlowController.js");
controllerMap.put("TendersBranchContractDetailController","static/business/contract/tendersBranch/controllers/tendersBranchContractDetailController.js");
controllerMap.put("FwFavouriteController","static/business/system/myFavourite/controllers/FwFavouriteController.js");
controllerMap.put("ChangePasswordController","static/business/system/user/controllers/ChangePasswordController.js");
controllerMap.put("BusiQualityRectificationController","static/business/quality/controller/BusiQualityRectificationController.js");
controllerMap.put("BusiQualityRectificationDetailController","static/business/quality/controller/BusiQualityRectificationController.js");
controllerMap.put("BusiQualityRawMaterialInspectionController","static/business/quality/controller/BusiQualityRawMaterialInspectionController.js");
controllerMap.put("BusiQualityRawMaterialInspectionDetailController","static/business/quality/controller/BusiQualityRawMaterialInspectionController.js");
controllerMap.put("BusiQualityRawMaterialRectificationController","static/business/quality/controller/BusiQualityRawMaterialInspectionController.js");
controllerMap.put("BusiQualityRawMaterialQuickProcessController","static/business/quality/controller/BusiQualityRawMaterialQuickProcessController.js");
controllerMap.put("BusiQualityTestInspectionController","static/business/quality/controller/BusiQualityTestInspectionController.js");
controllerMap.put("BusiQualityTestInspectionDetailController","static/business/quality/controller/BusiQualityTestInspectionController.js");
controllerMap.put("BusiQualityTestInspectionRectificationController","static/business/quality/controller/BusiQualityTestInspectionController.js");
controllerMap.put("BusiQualityTestInspectionQuickProcessController","static/business/quality/controller/BusiQualityTestInspectionController.js");
controllerMap.put("BusiQualityCementMixingStationInspectionController","static/business/quality/controller/BusiQualityCementMixingStationInspectionController.js");
controllerMap.put("BusiQualityCementMixingStationDetailController","static/business/quality/controller/BusiQualityCementMixingStationInspectionController.js");
controllerMap.put("BusiQualityCementMixingStationRectificationController","static/business/quality/controller/BusiQualityCementMixingStationInspectionController.js");
controllerMap.put("BusiQualityCementMixingStationQuickProcessController","static/business/quality/controller/BusiQualityCementMixingStationInspectionController.js");
controllerMap.put("BusiQualityWaterStableMixingStationController","static/business/quality/controller/BusiQualityWaterStableMixingStationInspectionController.js");
controllerMap.put("BusiQualityWaterStableMixingStationDetailController","static/business/quality/controller/BusiQualityWaterStableMixingStationInspectionController.js");
controllerMap.put("BusiQualityWaterStableMixingStationRectificationController","static/business/quality/controller/BusiQualityWaterStableMixingStationInspectionController.js");
controllerMap.put("BusiQualityWaterStableMixingStationQuickProcessController","static/business/quality/controller/BusiQualityWaterStableMixingStationInspectionController.js");
controllerMap.put("BusiQualityAsphaltMixingPlantController","static/business/quality/controller/BusiQualityAsphaltMixingPlantInspectionController.js");
controllerMap.put("BusiQualityAsphaltMixingPlantDetailController","static/business/quality/controller/BusiQualityAsphaltMixingPlantInspectionController.js");
controllerMap.put("BusiQualityAsphaltMixingPlantRectificationController","static/business/quality/controller/BusiQualityAsphaltMixingPlantInspectionController.js");
controllerMap.put("BusiQualityAsphaltMixingPlantQuickProcessController","static/business/quality/controller/BusiQualityAsphaltMixingPlantInspectionController.js");
controllerMap.put("BusiQualitySpreaderRollerSpreaderController","static/business/quality/controller/BusiQualitySpreaderRollerSpreaderController.js");
controllerMap.put("BusiQualitySpreaderRollerSpreaderDetailController","static/business/quality/controller/BusiQualitySpreaderRollerSpreaderController.js");
controllerMap.put("BusiQualitySpreaderRollerSpreaderRectificationController","static/business/quality/controller/BusiQualitySpreaderRollerSpreaderController.js");
controllerMap.put("BusiQualitySpreaderRollerSpreaderQuickProcessController","static/business/quality/controller/BusiQualitySpreaderRollerSpreaderController.js");
controllerMap.put("BusiQualityMortarInspectionController","static/business/quality/controller/BusiQualityMortarInspectionController.js");
controllerMap.put("BusiQualityMortarInspectionDetailController","static/business/quality/controller/BusiQualityMortarInspectionController.js");
controllerMap.put("BusiQualityMortarInspectionRectificationController","static/business/quality/controller/BusiQualityMortarInspectionController.js");
controllerMap.put("BusiQualityMortarInspectionQuickProcessController","static/business/quality/controller/BusiQualityMortarInspectionController.js");
controllerMap.put("BusiQualityPrestressedTensionMainController","static/business/quality/controller/BusiQualityPrestressedTensionMainController.js");
controllerMap.put("BusiQualityPrestressedTensionMainDetailController","static/business/quality/controller/BusiQualityPrestressedTensionMainController.js");
controllerMap.put("BusiQualityPrestressedTensionMainRectificationController","static/business/quality/controller/BusiQualityPrestressedTensionMainController.js");
controllerMap.put("BusiQualityPrestressedTensionMainQuickProcessController","static/business/quality/controller/BusiQualityPrestressedTensionMainController.js");
controllerMap.put("BusiQualityPrimarySupportArchSpacingCheckController","static/business/quality/controller/BusiQualityPrimarySupportArchSpacingCheckController.js");
controllerMap.put("BusiQualityPrimarySupportArchSpacingCheckAddController","static/business/quality/controller/BusiQualityPrimarySupportArchSpacingCheckController.js");
controllerMap.put("BusiQualityPrimarySupportArchSpacingCheckDetailController","static/business/quality/controller/BusiQualityPrimarySupportArchSpacingCheckController.js");
controllerMap.put("BusiQualityMonthReportMgrController","static/business/quality/controller/BusiQualityMonthReportMgrController.js");
controllerMap.put("BusiQualityMonthReportEditController","static/business/quality/controller/BusiQualityMonthReportMgrController.js");
controllerMap.put("BusiQualityMonthReportFlowController","static/business/quality/controller/BusiQualityMonthReportMgrController.js");
controllerMap.put("BusiQualityRetificationFlowController","static/business/quality/controller/BusiQualityRetificationFlowController.js");
controllerMap.put("BusiQualityQuickProcessFlowController","static/business/quality/controller/BusiQualityQuickProcessFlowController.js");
controllerMap.put("BusiQualityRoutingInspectionController","static/business/quality/controller/BusiQualityRoutingInspectionController.js");
controllerMap.put("BusiQualityRoutingInspectionAddController","static/business/quality/controller/BusiQualityRoutingInspectionController.js");
controllerMap.put("BusiQualityRoutingInspectionDetailController","static/business/quality/controller/BusiQualityRoutingInspectionController.js");
controllerMap.put("BusiQualityRoutingInspectionRectificationController","static/business/quality/controller/BusiQualityRoutingInspectionController.js");
controllerMap.put("BusiQualityPrimarySupportThicknessCheckController","static/business/quality/controller/BusiQualityPrimarySupportThicknessCheckController.js");
controllerMap.put("BusiQualityPrimarySupportThicknessCheckAddController","static/business/quality/controller/BusiQualityPrimarySupportThicknessCheckController.js");
controllerMap.put("BusiQualityPrimarySupportThicknessCheckDetailController","static/business/quality/controller/BusiQualityPrimarySupportThicknessCheckController.js");
controllerMap.put("BusiQualityPrimarySupportClearanceSectionCheckController","static/business/quality/controller/BusiQualityPrimarySupportClearanceSectionCheckController.js");
controllerMap.put("BusiQualityPrimarySupportClearanceSectionCheckAddController","static/business/quality/controller/BusiQualityPrimarySupportClearanceSectionCheckController.js");
controllerMap.put("BusiQualityPrimarySupportClearanceSectionCheckDetailController","static/business/quality/controller/BusiQualityPrimarySupportClearanceSectionCheckController.js");
controllerMap.put("BusiQualityPrimarySupportConcreteThicknessCheckController","static/business/quality/controller/BusiQualityPrimarySupportConcreteThicknessCheckController.js");
controllerMap.put("BusiQualityPrimarySupportConcreteThicknessCheckAddController","static/business/quality/controller/BusiQualityPrimarySupportConcreteThicknessCheckController.js");
controllerMap.put("BusiQualityPrimarySupportConcreteThicknessCheckDetailController","static/business/quality/controller/BusiQualityPrimarySupportConcreteThicknessCheckController.js");
controllerMap.put("BusiQualitySecondLiningThicknessCheckController","static/business/quality/controller/BusiQualitySecondLiningThicknessCheckController.js");
controllerMap.put("BusiQualitySecondLiningThicknessCheckAddController","static/business/quality/controller/BusiQualitySecondLiningThicknessCheckController.js");
controllerMap.put("BusiQualitySecondLiningThicknessCheckDetailController","static/business/quality/controller/BusiQualitySecondLiningThicknessCheckController.js");
controllerMap.put("BusiQualitySecondLiningClearanceSectionSizeCheckController","static/business/quality/controller/BusiQualitySecondLiningClearanceSectionSizeCheckController.js");
controllerMap.put("BusiQualitySecondLiningClearanceSectionSizeCheckAddController","static/business/quality/controller/BusiQualitySecondLiningClearanceSectionSizeCheckController.js");
controllerMap.put("BusiQualitySecondLiningClearanceSectionSizeCheckDetailController","static/business/quality/controller/BusiQualitySecondLiningClearanceSectionSizeCheckController.js");
controllerMap.put("BusiQualityInvertedArchThicknessCheckController","static/business/quality/controller/BusiQualityInvertedArchThicknessCheckController.js");
controllerMap.put("BusiQualityInvertedArchThicknessCheckAddController","static/business/quality/controller/BusiQualityInvertedArchThicknessCheckController.js");
controllerMap.put("BusiQualityInvertedArchThicknessCheckDetailController","static/business/quality/controller/BusiQualityInvertedArchThicknessCheckController.js");
controllerMap.put("BusiQualitySeclusionEngineerController","static/business/quality/controller/BusiQualitySeclusionEngineerController.js");
controllerMap.put("BusiQualityQuickProcessDetailController","static/business/quality/controller/BusiQualityQuickProcessDetailController.js");
controllerMap.put("BusiQualityConstructionInspectionController","static/business/quality/controller/BusiQualityConstructionInspectionController.js");
controllerMap.put("BusiQualityConstructionInspectionEditController","static/business/quality/controller/BusiQualityConstructionInspectionController.js");
controllerMap.put("BusiQualityConstructionInspectionApplyController","static/business/quality/controller/BusiQualityConstructionInspectionController.js");
controllerMap.put("BusiQualityConstructionInspectionFlowController","static/business/quality/controller/BusiQualityConstructionInspectionController.js");
controllerMap.put("BusiQualitySelectTendersBranchController","static/business/quality/controller/BusiQualitySelectTendersBranchController.js");
controllerMap.put("busiBaseEngineeringQuantityListController","static/business/base/engineeringQuantityList/controllers/busiBaseEngineeringQuantityListController.js");
controllerMap.put("busiBaseSubEngineeringController","static/business/base/subEngineering/controllers/busiBaseSubEngineeringController.js");
controllerMap.put("busiBaseImageListController","static/business/base/imageList/controllers/busiBaseImageListController.js");
controllerMap.put("busiBaseImageListSubConnectionTableController","static/business/base/imageListSubConnectionTable/controllers/busiBaseImageListSubConnectionTableController.js");
controllerMap.put("busiBaseConnectionSubEngineeringController","static/business/base/imageListSubConnectionTable/controllers/busiBaseConnectionSubEngineeringController.js");
controllerMap.put("busiBaseQuantityListSubConnectionTableController","static/business/base/quantityListSubConnectionTable/controllers/busiBaseQuantityListSubConnectionTableController.js");
controllerMap.put("busiBaseConnectionQuantityListController","static/business/base/quantityListSubConnectionTable/controllers/busiBaseConnectionQuantityListController.js");
controllerMap.put("busiProjectManagementOfBidSectionController","static/business/project/managementOfBidSection/controllers/busiProjectManagementOfBidSectionController.js");
controllerMap.put("busiProjectManagementOfBidSectionEditController","static/business/project/managementOfBidSection/controllers/busiProjectManagementOfBidSectionEditController.js");
controllerMap.put("busiProjectBaseInfoController","static/business/project/baseInfo/controllers/busiProjectBaseInfoController.js");
controllerMap.put("busiProjectBaseInfoEditController","static/business/project/baseInfo/controllers/busiProjectBaseInfoEditController.js");
controllerMap.put("busiRemoteMonitorController","static/business/project/remoteMonitor/controllers/busiRemoteMonitorController.js");
controllerMap.put("busiRemoteMonitorAddController","static/business/project/remoteMonitor/controllers/busiRemoteMonitorController.js");
controllerMap.put("busiRemoteMonitorSourceController","static/business/project/remoteMonitor/controllers/busiRemoteMonitorSourceController.js");
controllerMap.put("treeGridDemoController","static/business/demo/treeGrid/controllers/treeGridController.js");
controllerMap.put("MenuController","static/business/system/menu/controllers/MenuController.js");
controllerMap.put("MenuAddController","static/business/system/menu/controllers/MenuController.js");
controllerMap.put("MenuIconSelectController","static/business/system/menu/controllers/MenuController.js");
controllerMap.put("OaMsgInfoController","static/business/oa/msg/controllers/OaMsgInfoController.js");
controllerMap.put("MessageDetailController","static/business/oa/msg/controllers/OaMsgInfoController.js");
controllerMap.put("BusiQualityPrimaryModifyLogController","static/business/quality/controller/BusiQualityPrimaryModifyLogController.js");
controllerMap.put("BusiQualityConstructionInspectionDetailController","static/business/quality/controller/BusiQualityConstructionInspectionController.js");
controllerMap.put("BusiQualityPrimarySupportArchSpacingCheckAddController","static/business/quality/controller/BusiQualityPrimarySupportArchSpacingCheckController.js");
controllerMap.put("BusiQualityConsInsFlowController","static/business/quality/controller/BusiQualityConstructionInspectionController.js");
controllerMap.put("ChangeManageController","static/business/changeManage/controllers/changeManageController.js");
controllerMap.put("ChangeManageAddController","static/business/changeManage/controllers/changeManageAddController.js");
controllerMap.put("ChangeManageApproveFlowController","static/business/changeManage/controllers/changeManageApproveFlowController.js");
controllerMap.put("ChangeManageAddBranchDetailController","static/business/changeManage/controllers/changeManageAddBranchDetailController.js");
controllerMap.put("ChangeManageAddContractDetailController","static/business/changeManage/controllers/changeManageAddContractDetailController.js");
controllerMap.put("MiddleMeasureHomeController","static/business/measure/middleMeasure/controllers/middleMeasureHomeController.js");
controllerMap.put("MiddleMeasureAddController","static/business/measure/middleMeasure/controllers/middleMeasureAddController.js");
controllerMap.put("MiddleMeasureApproveFlowController","static/business/measure/middleMeasure/controllers/middleMeasureApproveFlowController.js");
controllerMap.put("BusiProgressImageController","static/business/progress/controllers/BusiProgressImageController.js");
controllerMap.put("BusiProgressImageAddController","static/business/progress/controllers/BusiProgressImageAddController.js");
controllerMap.put("busiProgressImageTotalInputController","static/business/progress/controllers/busiProgressImageTotalInputController.js");
controllerMap.put("BusiMeasureBasicDataConfigController","static/business/measure/measureBasicConfig/controllers/BusiMeasureBasicDataConfigController.js");
controllerMap.put("BusiMeasureCycleSettingController","static/business/measure/measureCycleSetting/controllers/BusiMeasureCycleSettingController.js");
controllerMap.put("BusiMeasureCycleSettingAddController","static/business/measure/measureCycleSetting/controllers/BusiMeasureCycleSettingController.js");
controllerMap.put("BusiMeasureCycleSettingDetailController","static/business/measure/measureCycleSetting/controllers/BusiMeasureCycleSettingDetailController.js");
controllerMap.put("busiProgressImageListSubConnectionTableController","static/business/progress/controllers/busiProgressImageListSubConnectionTableController.js");
controllerMap.put("busiProgressConnectionSubEngineeringController","static/business/progress/controllers/busiProgressConnectionSubEngineeringController.js");
controllerMap.put("BusiQualityConstructionReportFlowController","static/business/quality/controller/BusiQualityConstructionReportFlowController.js");
controllerMap.put("BusiMeasureMiddlePayCertificateTemplateController","static/business/measure/certificate/controllers/BusiMeasureMiddlePayCertificateTemplateController.js");	
controllerMap.put("BusiMeasureMiddlePayCertificateTemplateAddController","static/business/measure/certificate/controllers/BusiMeasureMiddlePayCertificateTemplateController.js");	
controllerMap.put("BusiMeasureMiddlePayCertificateTemplatePreviewController","static/business/measure/certificate/controllers/BusiMeasureMiddlePayCertificateTemplateController.js");	
controllerMap.put("busiProgressImagePlanOverallController","static/business/progress/controllers/busiProgressImagePlanOverallController.js");
controllerMap.put("busiProgressImageYearInputAddController","static/business/progress/controllers/busiProgressImageYearInputAddController.js");
controllerMap.put("busiProgressImageYearInputHomeController","static/business/progress/controllers/busiProgressImageYearInputHomeController.js");
controllerMap.put("BusiMeasureMiddlePayCertificateController","static/business/measure/certificate/controllers/BusiMeasureMiddlePayCertificateController.js");
controllerMap.put("BusiMeasureMiddlePayCertificateAddController","static/business/measure/certificate/controllers/BusiMeasureMiddlePayCertificateController.js");
controllerMap.put("BusiMeasureMiddlePayCertificateDetailController","static/business/measure/certificate/controllers/BusiMeasureMiddlePayCertificateController.js");
controllerMap.put("BusiMeasureMiddlePayCertificateFlowController","static/business/measure/certificate/controllers/BusiMeasureMiddlePayCertificateController.js");
/*广告位*/
controllerMap.put("PhiAdPositionController","static/phicom/market/PhiAdPositionController.js");
controllerMap.put("PhiMainBannerEditController","static/phicom/market/mainBanner/phiMainBannerController.js");
controllerMap.put("PhiMainBusiController","static/phicom/market/mainBusi/PhiMainBusiController.js");
controllerMap.put("phiMainGamesController","static/phicom/market/mainGame/phiMainGamesController.js");
controllerMap.put("phiMainHotRecommendController","static/phicom/market/mainGame/phiMainGamesController.js");
controllerMap.put("phiPCMainGamesController","static/phicom/market/PCTerminal/phiPCMainGamesController.js");
controllerMap.put("phiExchangeController","static/phicom/market/PCTerminal/phiPCMainGamesController.js");
controllerMap.put("phiMainListConfigureController","static/phicom/market/mainGame/phiMainGamesController.js");
/*plus会员页面维护*/
controllerMap.put("PhiPlusPagelayoutController","static/phicom/plusPageLayout/PhiPlusPagelayoutController.js");
/*权益汇总页面维护*/
controllerMap.put("phiPlusGatherContrller","static/phicom/plusPageLayout/phiPlusGatherContrller.js");
/*广告位页面维护*/
controllerMap.put("phiAdPositionController","static/phicom/plusPageLayout/phiAdPositionController.js");
/*plus会员专享页面维护*/
controllerMap.put("phiPlusExclusiveController","static/phicom/plusPageLayout/phiPlusExclusiveController.js");
/*权益说明页面维护*/
controllerMap.put("phiRightExplainController","static/phicom/plusPageLayout/phiRightExplainController.js");


/* 会员信息管理*/
controllerMap.put("PhiMemberController","static/phicom/member/PhiMemberController.js");
controllerMap.put("PhiMemberAddController","static/phicom/member/PhiMemberController.js");

/* 会员等级信息管理*/
controllerMap.put("PhiMemberGradeController","static/phicom/member/PhiMemberGradeController.js");
controllerMap.put("PhiMemberGradeAddController","static/phicom/member/PhiMemberGradeController.js");
/* 会员详细信息管理*/
controllerMap.put("PhiMemberDetailController","static/phicom/member/PhiMemberDetailController.js");
controllerMap.put("PhiMemberDetailAddController","static/phicom/member/PhiMemberDetailController.js");
/* 会员收货地址信息管理*/
controllerMap.put("PhiMemberAddressController","static/phicom/member/PhiMemberAddressController.js");
controllerMap.put("PhiMemberAddressAddController","static/phicom/member/PhiMemberAddressController.js");

/*会员特权管理*/
controllerMap.put("PhiMemberPrivilegeController","static/phicom/member/PhiMemberPrivilegeController.js");
controllerMap.put("PhiMemberPrivilegeDetailController","static/phicom/member/PhiMemberPrivilegeController.js");
controllerMap.put("PhiMemberPrivilegeBirthDetailController","static/phicom/member/PhiMemberPrivilegeController.js");

/* 商品管理*/
controllerMap.put("PhiProductController","static/phicom/product/PhiProductController.js");
controllerMap.put("PhiProductAddController","static/phicom/product/PhiProductController.js");
controllerMap.put("PhiProductAuditController","static/phicom/product/PhiProductAuditController.js");
controllerMap.put("PhiProductDetialController","static/phicom/product/PhiProductAuditController.js");
controllerMap.put("PhiProductTypeController","static/phicom/product/PhiProductCategoryController.js");
controllerMap.put("PhiProductTypeAddController","static/phicom/product/PhiProductCategoryController.js");

/*生成订单*/
controllerMap.put("PhiAddOrderController","static/phicom/product/PhiProductController.js");



/* 商品的兑换规则管理*/
controllerMap.put("PhiProductExchangeRulesController","static/phicom/exchangeRules/PhiProductExchangeRulesController.js");
controllerMap.put("PhiProductExchangeRulesAddController","static/phicom/exchangeRules/PhiProductExchangeRulesController.js");
/*开奖*/
controllerMap.put("PhiWinnersAddController","static/phicom/winner/phiwinnerController.js");
controllerMap.put("PhiAllMemberController","static/phicom/winner/phiwinnerController.js");
/*订单管理*/
controllerMap.put("PhiOrderController","static/phicom/order/PhiOrderController.js");
controllerMap.put("PhiOrderAddController","static/phicom/order/PhiOrderController.js");
controllerMap.put("PhiOrderDetialController","static/phicom/order/PhiOrderController.js");
controllerMap.put("logisticController","static/phicom/order/PhiOrderController.js");
controllerMap.put("WinListController","static/phicom/order/PhiOrderController.js");



/*地区测试*/
controllerMap.put("PhiRegionsAddController","static/phicom/order/PhiOrderController.js");


/*优惠券管理*/
controllerMap.put("PhiCouponsController","static/phicom/coupons/controllers/PhiCouponsController.js");
controllerMap.put("PhiCouponsDetailController","static/phicom/coupons/controllers/PhiCouponsController.js");
controllerMap.put("PhiRepairCouponsController","static/phicom/coupons/controllers/PhiRepairCouponsController.js");

/*第三方券管理*/
controllerMap.put("PhiThirdPartyCouponsController","static/phicom/coupons/controllers/PhiThirdPartyCouponsController.js");
controllerMap.put("PhiThirdPartyCouponsDetailController","static/phicom/coupons/controllers/PhiThirdPartyCouponsController.js");

/*积分规则*/
controllerMap.put("PhiScoreTaskConfigController","static/phicom/score/PhiScoreTaskConfigController.js");
controllerMap.put("PhiScoreTaskAddController","static/phicom/score/PhiScoreTaskConfigController.js");
controllerMap.put("PhiScoreRuleConfigController","static/phicom/score/PhiScoreRuleConfigController.js");
/*数据管理*/
controllerMap.put("ScoreDataController","static/phicom/scoreData/controllers/ScoreDataController.js");
controllerMap.put("ScoreDataChartController","static/phicom/scoreData/controllers/ScoreDataController.js");
controllerMap.put("ScoreCoupsDataController","static/phicom/scoreData/controllers/ScoreCoupsDataController.js");
controllerMap.put("ScoreCoupsDataDetailController","static/phicom/scoreData/controllers/ScoreCoupsDataController.js");
controllerMap.put("ScoreCoupsDataThirdDetailController","static/phicom/scoreData/controllers/ScoreCoupsDataController.js");

/*菜单图标*/
var iconFont = ["menu-icon iconfont icon-icon_", "menu-icon iconfont icon-icon_1",
    "menu-icon iconfont icon-icon_2", "menu-icon iconfont icon-icon_3", "menu-icon iconfont icon-icon_4",
    "menu-icon iconfont icon-icon_5", "menu-icon iconfont icon-icon_6", "menu-icon iconfont icon-icon_7",
    "menu-icon iconfont icon-icon_8", "menu-icon iconfont icon-icon_9", "menu-icon iconfont icon-icon_10",
    "menu-icon iconfont icon-icon_11", "menu-icon iconfont icon-icon_12", "menu-icon iconfont icon-icon_13",
    "menu-icon iconfont icon-icon_14", "menu-icon iconfont icon-icon_15", "menu-icon iconfont icon-icon_16",
    "menu-icon iconfont icon-icon_17", "menu-icon iconfont icon-icon_18", "menu-icon iconfont icon-icon_19",
    "menu-icon iconfont icon-icon_20", "menu-icon iconfont icon-icon_21",
];

/*plus会员权益*/
controllerMap.put("PhiPlusAllRightController","static/phicom/plusmember/PhiPlusAllRightController.js");
/*购买plus会员*/
controllerMap.put("PhiPlusMemberOrderController","static/phicom/plusmember/PhiPlusMemberOrderController.js");

/*plus会员消费返积分*/
controllerMap.put("PhiPlusAllRightAddController","static/phicom/plusmember/PhiPlusAllRightController.js");

/* 虚拟用户信息管理*/
controllerMap.put("PhiVirtualUserController","static/phicom/member/PhiVirtualUserController.js");
controllerMap.put("PhiVirtualUserAddController","static/phicom/member/PhiVirtualUserController.js");

/*积分游戏列表*/
controllerMap.put("PhiGameController","static/phicom/game/PhiGameController.js");
controllerMap.put("PhiGameUserController","static/phicom/game/PhiGameController.js");
/*游戏积分配置*/
controllerMap.put("PhiGameConfigAddController","static/phicom/game/PhiGameConfigController.js");

