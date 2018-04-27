/**
 * 仰拱厚度检测Controller 
 * BusiQualityInvertedArchThicknessCheckController
 */
'use strict';
angular.module('huatek.controllers').controller('BusiQualityInvertedArchThicknessCheckController', function ($rootScope, $scope, $location, $http, listService) {
	
	$scope.tableGrid = {
	        paginationPageSizes: [10, 25, 50, 100],
	        paginationPageSize: 10,
	        useExternalPagination: true,
	        enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
	        columnDefs: [
				{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate("viewData","orgName")},
				{ name: '隧道名称', field: 'tunnelName',width: '10%', enableColumnMenu: false},
	            { name: '分部分项', field: 'tendersBranchName', width: '10%', enableColumnMenu: false},
				{ name: '测点桩号', field: 'meterPointStakeNo',width: '10%', enableColumnMenu: false},
				{ name: '设计厚度(cm)', field: 'designThickness',width: '10%', enableColumnMenu: false},
				{ name: '实测厚度(cm)', field: 'actualMeasureThickness',width: '10%', enableColumnMenu: false},
				{ name: '检测人员', field: 'checkUserName',width: '7%', enableColumnMenu: false},
				{ name: '检测开始时间', field: 'startCheckDate',width: '8%', enableColumnMenu: false, cellFilter: "dateFilter" },
				{ name: '检测结束时间', field: 'endCheckDate',width: '8%', enableColumnMenu: false, cellFilter: "dateFilter" },
				{ name: '是否合格', field: 'qualifiedStatus',width: '8%', enableColumnMenu: false,decode:{field:'qualifiedStatus',dataKey:'dic.inspection_status'}},
				{ name: '修改日志', field: 'modifyTime',width: '8%', enableColumnMenu: false,cellTemplate:listService.getLinkCellTmplate('queryModifyLog','modifyTime','日志')},
	        ]
	    };
		/**
		 * 初始化编辑界面. 比如显示编辑界面的模块名称.
		 */
		listService.init($scope, $http);
		// 定义查询页
		var queryPage = new QueryPage(1, 10, "id desc", "false");
		// 定义搜索框
		/*queryParam (title, field, type, logic, value, dropDataUrl,isShow, event,display)*/
		var tendersName = new queryParam('标段名称', 'org.id', 'string', '=');
	    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
	    queryPage.addParam(tendersName);
	    queryPage.addParam(new queryParam('测点桩号','meterPointStakeNo','string','like'));
	    queryPage.addParam(new queryParam('隧道名称','tunnelName','string','like'));
	    queryPage.addParam(new queryParam('检测日期','startCheckDate','date','>='));
	    queryPage.addParam(new queryParam('------','endCheckDate','date','<'));
	    var qualifiedStatus = new queryParam('是否合格','qualifiedStatus','string','=','','dic.inspection_status');
	    qualifiedStatus.componentType = 'select';
	    queryPage.addParam(qualifiedStatus);
	    $rootScope.searchCount = queryPage.queryParamList.length;
	    /**
		 * 设置查询输入按钮.
		 */
	    listService.setQueryPage($scope, queryPage);
	    
	    /**
		 * 注册gridApi的选择器.
		 */
	    $scope.tableGrid.onRegisterApi = function(gridApi) {
	        $scope.gridApi = gridApi;
	        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
	            queryPage.page = newPage;
	            queryPage.pageSize = pageSize;
	            load();
	        });
	        /*调整grid底部高度*/
	        listService.setGridHeight();
	    };

	    // 定义功能按钮
		var btnArray = [];
	    btnArray.push(new pageButton('新增','add','/invertedArchThicknessCheck/add','addData'));
	    btnArray.push(new pageButton('修改','edit','/invertedArchThicknessCheck/edit','editData'));
	    /*btnArray.push(new pageButton('查看', 'view', '/invertedArchThicknessCheck/view', 'viewData'));*/
		/**
		 * 设置界面的功能按钮.
		 */
	    listService.setButtonList($scope, btnArray);
	    
	    /*判断是否是系统管理员，系统管理员不可操作业务数据*/
	    var isAdminFn = function(){
	    	if($rootScope.tenantId){
	    		return true;
	    	}else{
	    		submitTips('系统管理员不能操作业务数据！','warning');
	    		return false;
	    	}
	    }
	    
	    /**
		 * 桥接按钮事件.
		 */
	    $scope.execute = function(operation, param){
	    	var selectData = $scope.gridApi.selection.getSelectedRows();
	        if(operation == 'addData'){
	        	if(isAdminFn()){
	        		listService.addData($scope, new popup("新增","/invertedArchThicknessCheck/add", null, "1200", "800"));
	        	}
	        }else if(operation == 'editData'){
	        	if(isAdminFn()){
	        		listService.editData($scope, $scope.gridApi, new popup("修改","/invertedArchThicknessCheck/edit", null, "1200", "800"));
	        	}
	        }else if(operation == 'viewData'){
	        	listService.editData($scope, $scope.gridApi, new popup("查看","/invertedArchThicknessCheck/view", null, "1200", "800"),param);
	        }else if(operation == 'queryModifyLog'){
	        	if(param.isEdited == 'N'){
	        		submitTips('警告：该条数据未进行修改操作, 不可查看日志。', 'warning');
            		return;
	        	}
	        	var logShowFieldArr = [
					{ name: '标段名称', field: 'orgName',width: '10%', enableColumnMenu: false},
					{ name: '隧道名称', field: 'tunnelName',width: '10%', enableColumnMenu: false},
		            { name: '分部分项', field: 'tendersBranchName', width: '10%', enableColumnMenu: false},
					{ name: '测点桩号', field: 'meterPointStakeNo',width: '10%', enableColumnMenu: false},
					{ name: '设计厚度(cm)', field: 'designThickness',width: '10%', enableColumnMenu: false},
					{ name: '实测厚度(cm)', field: 'actualMeasureThickness',width: '10%', enableColumnMenu: false},
					{ name: '检测人员', field: 'checkUserName',width: '9%', enableColumnMenu: false},
					{ name: '检测开始时间', field: 'startCheckDate',width: '8%', enableColumnMenu: false, cellFilter: "dateFilter" },
					{ name: '检测结束时间', field: 'endCheckDate',width: '8%', enableColumnMenu: false, cellFilter: "dateFilter" },
					{ name: '是否合格', field: 'qualifiedStatus',width: '8%', enableColumnMenu: false,decode:{field:'qualifiedStatus',dataKey:'dic.inspection_status'}},
					{ name: '修改日志', field: 'modifyTime',width: '15%', enableColumnMenu: false},
		        ];
	        	var queryLogUrl = '/api/invertedArchThicknessCheck/queryModifyLog/'+param.id;
	        	listService.popPanel($scope, 
	            		new popup("修改日志查看",null,{'fieldArr':logShowFieldArr,'queryUrl':queryLogUrl},"1300", "450",null,
	            				'BusiQualityPrimaryModifyLogController','static/business/quality/templates/third_party_modify_log.html'));
	        }
	    }
	    /**
		 * 初始化加载数据.
		 */
	    var load = $scope.load = function() {
	        listService.loadData($scope, '/api/invertedArchThicknessCheck/query', $scope.tableGrid);
	    }

	    load();

	    $scope.search = function() {
	        load();
	    };
	    
	   /* $scope.viewInfo = function(entity){
	    	listService.editData($scope, $scope.gridApi, new popup("查看","/rawMaterialInspection/view", null, "1200", "800"));
	    }*/
}).filter("dateFilter", function() {/*日期数据过滤器*/
    var dateFilterFunction = function(dateVal) {
    	if(cnex4_isNotEmpty_str(dateVal)){
    		return getDate(dateVal);
        } else {
        	return dateVal;
        }
    };
    return dateFilterFunction;
});


angular.module('huatek.controllers').controller('BusiQualityInvertedArchThicknessCheckAddController', function ($scope, $http, editService, httpService,$rootScope,listService) {
	var addDataUrl = 'api/invertedArchThicknessCheck/add';
	var editDataUrl = 'api/invertedArchThicknessCheck/edit';
	var homeUrl = '/invertedArchThicknessCheck/home';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'新增'));
    $scope.columnWrapArray = columnWrapArray;
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
    var orgId = new FormElement(1,'标段名称','orgId','string',1,'50','tenderSelect',null,null);
    if(!$scope.editId){
    	orgId.value = $rootScope.orgId;
    }
    formFieldArray.push(orgId);
    formFieldArray.push(new FormElement(1,'隧道名称','tunnelName','string',1,'30'));
    var tendersBranchId = new FormElement(1,'分部分项','tendersBranchId','string','require','50','');
    tendersBranchId.isShow =false;
    formFieldArray.push(tendersBranchId);
    var tendersBranch = new FormElement(1,'分部分项名称','tendersBranchName','string','require','500','longInput');
    tendersBranch.readonly = true;
    formFieldArray.push(tendersBranch);
    formFieldArray.push(new FormElement(1,'检测开始时间','startCheckDate','string',1,'30','date',null,null,getDate(getNowFormatDate())));
    formFieldArray.push(new FormElement(1,'检测结束时间','endCheckDate','string',1,'30','date',null,null,getDate(getNowFormatDate())));
    formFieldArray.push(new FormElement(1,'测点桩号','meterPointStakeNo','string',0,'30'));
    formFieldArray.push(new FormElement(1,'衬砌类型','liningType','string',0,'30'));
    formFieldArray.push(new FormElement(1,'设计厚度(cm)','designThickness','number',0,'30'));
    formFieldArray.push(new FormElement(1,'实测厚度(cm)','actualMeasureThickness','number',0,'30'));
    formFieldArray.push(new FormElement(1,'检测人员','checkUserName','string',1,'30',null,null,null,$rootScope.userName));
    var qualifiedStatus = new FormElement(1,'是否合格','qualifiedStatus','string',0,'30','radio',null,null,null,'dic.inspection_status');
    qualifiedStatus.value = 0;
    formFieldArray.push(qualifiedStatus);
    var contractFileUUID = new FormElement(1,'附件','attachmentFile','string',0,'200','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/thirdPartyInspection/invertedArch";
 	formFieldArray.push(contractFileUUID);
    
   //console.log(formFieldArray);
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
 
    /**
     * 加载项目分部分项
     */
    tendersBranch.clickEvent = function(){
    	listService.popPanel($scope, 
        		new popup("项目分部分项", "/busiQualityConsInspection/createQuantitiesBill",
        				{ 'orgId': orgId, 'tendersBranchId': tendersBranchId, 'tendersBranch': tendersBranch,huatekTree: $scope.huatekTree },
        				"900", "600",null,'BusiQualitySelectTendersBranchController','static/business/quality/templates/template_list_edit_treeGrid.html','modal'));
    }
    
 

    /**
     * 加载编辑数据
     */
    if($scope.editId){
    	editService.loadData($scope, editDataUrl, $scope.editId);
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


/*查看明细*/
angular.module('huatek.controllers').controller('BusiQualityInvertedArchThicknessCheckDetailController', function ($scope, $http, editService, httpService,$rootScope) {
	var editDataUrl = 'api/invertedArchThicknessCheck/edit';
	var homeUrl = '/invertedArchThicknessCheck/home';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'明细查看'));
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
    var orgName = new FormElement(1,'标段名称','orgName','string','require','50','',null,null,null,null,'readonly');
    /*editService.initFieldItems($scope, orgId,'api/org/getFwOrgByType/'+$rootScope.orgType);*//**初始化表单选择数据，包括字典数据，其他可选项数据.**//*
    orgId.value = $rootScope.orgId;*/
    formFieldArray.push(orgName);
    formFieldArray.push(new FormElement(1,'隧道名称','tunnelName','string',0,'30',null,null,null,null,null,'readonly'));
    var tendersBranchId = new FormElement(1,'分部分项','tendersBranchId','string','require','50','');
    tendersBranchId.isShow =false;
    formFieldArray.push(tendersBranchId);
    var tendersBranch = new FormElement(1,'分部分项名称','tendersBranchName','string','require','500','longInput');
    tendersBranch.readonly = true;
    formFieldArray.push(tendersBranch);
    formFieldArray.push(new FormElement(1,'检测开始时间','startCheckDate','string',0,'30','date',null,null,getDate(getNowFormatDate()),null,'readonly'));
    formFieldArray.push(new FormElement(1,'检测结束时间','endCheckDate','string',0,'30','date',null,null,getDate(getNowFormatDate()),null,'readonly'));
    formFieldArray.push(new FormElement(1,'测点桩号','meterPointStakeNo','string',0,'30',null,null,null,null,null,'readonly'));
    formFieldArray.push(new FormElement(1,'衬砌类型','liningType','string',0,'30',null,null,null,null,null,'readonly'));
    formFieldArray.push(new FormElement(1,'设计厚度(cm)','designThickness','number',0,'30',null,null,null,null,null,'readonly'));
    formFieldArray.push(new FormElement(1,'实测厚度(cm)','actualMeasureThickness','number',0,'30',null,null,null,null,null,'readonly'));
    formFieldArray.push(new FormElement(1,'检测人员','checkUserName','string',0,'30',null,null,null,null,null,'readonly'));
    formFieldArray.push(new FormElement(1,'是否合格','qualifiedStatus1','string',0,'30','radio',null,null,null,'dic.inspection_status','readonly'));
    formFieldArray.push(new FormElement(1,'附件','attachmentFile','string',0,'100','fileMultiple',null,null,null,null,'readonly'));
    
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    

    /**
     * 加载编辑数据
     */
    if($scope.editId){
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }
   
	
    /**
     * 修改
     **/   
    $scope.update = function(){
    	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    } 
   
    
});