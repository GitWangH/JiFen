angular.module('huatek.controllers').controller('ChangeManageAddController', function ($rootScope, $scope, $http, editService, httpService, listService, cacheService) {
	var addDataUrl = 'api/changeManage/add';
	var editDataUrl = 'api/changeManage/edit';
	var homeUrl = '/changeManage/home';
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'合同变更基本信息'));
    columnWrapArray.push(new multipleColumn(2,'合同变更附件'));
    columnWrapArray.push(new multipleColumn(3,'合同变更明细'));
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
    formFieldArray.push(new FormElement(1,'变更等级','changeLevel','string','require','30','select','','','','dic.change_level'));
    if($scope.editId){
    	formFieldArray.push(new FormElement(1,'变更日期','changeData','string','require','30','date'));
    }else{
    	formFieldArray.push(new FormElement(1,'变更日期','changeData','string','require','30','date','','',getDate(getNowFormatDate())));
    }
    formFieldArray.push(new FormElement(1,'变更类型','changeType','string','require','30','select','','','','dic.change_type'));
    formFieldArray.push(new FormElement(1,'变更项目','changeProjectName','string','','50'));
    formFieldArray.push(new FormElement(1,'变更令号','changeOrderNo','string','','100'));
    formFieldArray.push(new FormElement(1,'合同图纸','contractDrawings','string','','100'));
    
    formFieldArray.push(new FormElement(1,'变更后图纸','contractChangedDrawings','string','','100'));
    formFieldArray.push(new FormElement(1,'桩号','stakeNo','string','','100'));
    formFieldArray.push(new FormElement(1,'变更延长工期','delaySchedule','string','','100'));
    formFieldArray.push(new FormElement(1,'变更批复状态','replyChangeStatus','string','require','30','select','','','','dic.reply_change_status'));
    
    var changeMoney = new FormElement(1,'变更金额','changeMoney','number','require');
    changeMoney.number_MaxValue = 99999999999999.9999;
    changeMoney.value = 0;
    formFieldArray.push(changeMoney);
    
    formFieldArray.push(new FormElement(1,'变更内容','changeContent','string','','255','textarea'));
    formFieldArray.push(new FormElement(1,'变更原因','changeReason','string','','255','textarea'));
    
    var changeFileUUID = new FormElement(2,'变更文件','changeFile','string','','36','fileMultiple');
    changeFileUUID.systemHeader = "/root/changeManage";
 	formFieldArray.push(changeFileUUID);
 	
 	var meetingSummaryFileUUID = new FormElement(2,'会议纪要','meetingSummaryFile','string','','36','fileMultiple');
 	meetingSummaryFileUUID.systemHeader = "/root/changeManage";
 	formFieldArray.push(meetingSummaryFileUUID);
    
 	/**
 	 * 变更清单列表
 	 */
 	$scope.tableGrid = {
 	        paginationPageSizes: [10, 25, 50, 100],
 	        paginationPageSize: 10,
 	        useExternalPagination: true,
 	        enableFullRowSelection: false,
 	        enableSelectAll: false,
 	        multiSelect: false,
 	        columnDefs: [
 	            { name: '单位工程', field: 'orgName', width: '6%', enableColumnMenu: false },
 	            { name: '分部工程', field: 'orgName', width: '6%', enableColumnMenu: false },
 	            { name: '分项工程', field: 'orgName', width: '6%', enableColumnMenu: false /*, cellTemplate : '<a ng-click="grid.appScope.viewContractInfo(row.entity)">{{row.entity.contractCode}}</a>'*/ },
 	            { name: '段落和部位', field: 'orgName', width: '7%', enableColumnMenu: false, decode: { field: "technicalLevel", dataKey: "dic.highway_grade" } },
 	            { name: '清单编号', field: 'contractDetailCode', width: '6%', enableColumnMenu: false, cellFilter: "priceFilter" },
 	            { name: '清单名称', field: 'contractDetailName', width: '6%', enableColumnMenu: false, decode: { field: "approvalStatus", dataKey: "dic.flow_status" } },
 	            { name: '单位', field: 'countUnit', width: '5%', enableColumnMenu: false, decode: { field: "approvalStatus", dataKey: "dic.flow_status" }, cellTemplate: listService.getLinkCellTmplate("viewProcess", "approvalStatusName") /*,cellTemplate:returnApprovalStatusColor()*/ },
 	            { name: '变更类型', field: 'changeType', width: '6%', enableColumnMenu: false,editableCellTemplate: 'ui-grid/dropdownEditor',  editDropdownValueLabel: 'value', editDropdownOptionsArray: [] },
 	            { name: '变更前单价(元)', field: 'changeBeforeUnitPrice', width: '8%', enableColumnMenu: false },
 	            { name: '变更前数量', field: 'changeBeforeQuantity', width: '8%', enableColumnMenu: false },
	            { name: '变更前金额(元)', field: 'changeBeforeTotalPrice', width: '8', enableColumnMenu: false },
	            { name: '变更单价(元)', field: 'changeUnitPrice', width: '8%', enableColumnMenu: false },
	            { name: '变更数量', field: 'changeQuantity', width: '6%', enableColumnMenu: false },
 	            { name: '变更后单价(元)', field: 'changeAfterUnitPrice', width: '8%', enableColumnMenu: false },
 	            { name: '变更后数量', field: 'changeAfterQuantity', width: '6%', enableColumnMenu: false },
 	            { name: '变更后金额(元)', field: 'changeAfterTotalPrice', width: '*', enableColumnMenu: false }
 	        ]
 	    };
 	    /**
 	     * 初始化编辑界面. 比如显示编辑界面的模块名称.
 	     */
 	    listService.init($scope, $http, $scope.tableGrid);
 	    
 	/**
	 * 注册gridApi的选择器.
	 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        /*编辑单元格是将该数据更改为已编辑状态*/
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
        	if(typeof rowEntity.id === 'undefined'){
        		rowEntity.operation = 0;
        	} else {
        		rowEntity.operation = 1;
        	}
        });
    };
    
    editService.setFormFields($scope, formFieldArray); 
    
    /**
	 * 获取 changeType 变更类型 选项
	 */
	var getChangeTypeArr = function(){
		var changeType = cacheService.getData("dic.change_type");
		var changeTypeArr = [];
		if(changeType.length != 0){
			for(var i = 0; i < changeType.length; i++){
				var obj = {};
				obj.id = changeType[i].code;
				obj.value = changeType[i].name;
				changeTypeArr.push(obj);
			}
		}
		var tableGridDataMap = getMap($scope.tableGrid.columnDefs, "field");
//		console.log(tableGridDataMap);
		tableGridDataMap.get("changeType").editDropdownOptionsArray = changeTypeArr;
	}
	
	
    /**
     * 模糊检索：标段名称(机构名称)
     */
    $scope.getTendersInfo = function (orgIdVal) {
    	var orgId = cnex4_isNotEmpty_str(orgIdVal)? orgIdVal:tendersName.value;
    	/*$scope.promise = httpService.post($scope, "api/contractRestfulApi/getTendersInfoByOrgId/" + orgId)
								    .success(function (response) {
									   editService.setFieldValue($scope, "contractCode", response.contractCode);合同编号
								    })
								    .error(function(){
									   submitTips('根据机构编码获取标段信息出错(施工)','error');
								    });*/
    }

    /**数据加载*/
    var load = function(){
        if($scope.editId){
            listService.loadData($scope, loadUrl + $scope.editId , $scope.tableGrid, true);
        }
        getChangeTypeArr();
    }
    
    load();
    
    /**增加一行数据*/
    $scope.addRow = function(){
    	/*var data = {};
    	$scope.tableGrid.data.push(data);*/
    	var orgIdVal = "";
    	if($rootScope.orgType == 7){/*机构类型为标段*/
        	orgIdVal = $rootScope.orgId;
        }else{
        	orgIdVal = tendersName.value;
        }
    	console.log("orgIdVal:"+orgIdVal);
    	if(cnex4_isNotEmpty_str(orgIdVal)){
    		listService.addData($scope, new popup("选择挂接的合同清单", "/changeManage/addDetail", { seletedOrgId: orgIdVal, huatekTree: $scope.huatekTree }, "1000", "500", function() {
                //$scope.huatekTree.nodeCheckedFn(currentRow);
            }));
    	} else {
            submitTips('请选择标段名称再操作添加!', 'warning');
        }
    }
    
    /** 删除数据 */
    $scope.deleteRow = function(){
    	alert("删除一行数据");
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