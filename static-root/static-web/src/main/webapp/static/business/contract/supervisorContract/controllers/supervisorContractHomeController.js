'use strict';
/** [合同管理] - [项目合同] -[监理合同管理] **/
angular.module('huatek.controllers').controller('SupervisorContractHomeController', function ($rootScope, $scope, $location, $http, listService, httpService) {
	
	$scope.tableGrid = {
	        paginationPageSizes: [10, 25, 50, 100],
	        paginationPageSize: 10,
	        useExternalPagination: true,
	        enableFullRowSelection : true,/*选择当前行*/
	        enableSelectAll : false,/*选择所有*/
	        multiSelect: false,/*多行操作*/
	        lookDetailConfig:{menuKey:'/supervisorContract/edit',name:'监理合同查看'},
	        columnDefs: [
	            { name: '单位名称', field: 'orgName', width: '10%', enableColumnMenu: false },
	            { name: '合同名称', field: 'contractName', width: '12%', enableColumnMenu: false },
	            { name: '合同编号', field: 'contractCode', width: '10%', enableColumnMenu: false },
	            { name: '签订日期', field: 'signatureData', width: '10%', enableColumnMenu: false, cellFilter: "dateFilter"  },
	            { name: '合同金额', field: 'contractTotalPrice', width: '10%', enableColumnMenu: false, cellFilter: "priceFilter"  },
	            { name: '暂定金', field: 'provisionalMoney', width: '10%', enableColumnMenu: false, cellFilter: "priceFilter"  },
	            { name: '审批状态', field: 'approvalStatusName', width: '8%', enableColumnMenu: false, decode: { field: "approvalStatus", dataKey: "dic.flow_status" }, cellTemplate:listService.getLinkCellTmplate("viewProcess","approvalStatusName") },
	            /*{ name: '流程编号', field: 'flowInstanceId', width: '5%', enableColumnMenu: false },*/
	            { name: '创建人', field: 'createrName', width: '8%', enableColumnMenu: false },
	            { name: '创建时间', field: 'createTime', width: '*', enableColumnMenu: false }
	        ]
	    };
		/**
		 * 初始化编辑界面. 比如显示编辑界面的模块名称.
		 */
		listService.init($scope, $http, $scope.tableGrid);
		// 定义查询页
		var queryPage = new QueryPage(1, 10, "id desc", "false");
		// 定义搜索框
	    var companyName = new queryParam('单位名称', 'busiFwOrg.id', 'string', '=');
	    companyName.componentType='select';
	    queryPage.addParam(companyName);
	    /*动态加载监理机构数据*/
	    listService.initQueryItems($scope, companyName, 'api/org/getFwOrgByType/6');
	    
	    queryPage.addParam(new queryParam('合同编号', 'contractCode', 'string', 'like'));
	    queryPage.addParam(new queryParam('签订日期', 'signatureData', 'date', '>='));
	    queryPage.addParam(new queryParam('--', 'signatureData', 'date', '<='));

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
		btnArray.push(new pageButton('新增', 'add', '/supervisorContract/add', 'addData'));
		btnArray.push(new pageButton('编辑', 'edit', '/supervisorContract/edit', 'editData'));
		btnArray.push(new pageButton('删除', 'delete', '/supervisorContract/delete','deleteData'));
		btnArray.push(new pageButton('申请', 'apply', '/supervisorContract/apply', 'applyFlow'));
		/*btnArray.push(new pageButton('查看流程', 'viewProcess', '/supervisorContract/viewProcess', 'viewProcess'));*/
		/*btnArray.push(new pageButton('查看详情', 'viewDetails', '/supervisorContract/viewDetails', 'viewDetails'));*/
		/**
		 * 设置界面的功能按钮.
		 */
	    listService.setButtonList($scope, btnArray);
	    
	    /**
		 * 桥接按钮事件.
		 */
	    $scope.execute = function(operation, param){
	    	if(operation == 'addData'){
	        	listService.addData($scope, new popup("新增监理合同","/supervisorContract/add", null, "1200", "800"));
	        } else if(operation =='editData'){
	        	if(isAllowOperation('该监理合同正在审批中，不可修改!','该监理合同已审批，不可修改!')){
	        		listService.editData($scope, $scope.gridApi, new popup("修改监理合同","/supervisorContract/edit", null, "1200", "800"));
    			}
	        } else if(operation == 'deleteData'){
	        	if(isAllowOperation('该监理合同正在审批中，不可删除!','该监理合同已审批，不可删除!')){
    				listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/supervisorContract/delete');
    			}
	        } else if(operation == "applyFlow"){/*申请启动流程*/
    			if(isAllowOperation('该监理合同正在审批中，不可重复操作申请!','该监理合同已审批，不可再次操作申请!')){
    				bootbox.confirm('确定申请吗?', function(isConfirm) {
        				if(isConfirm){
        					var selectedData = listService.getSelectData($scope.gridApi);
        					$scope.promise = httpService.post($scope, "api/supervisorContract/apply/" + selectedData.id)
        										  .success(function (response) {
        											  load();
        										  })
        										  .error(function(){
        											  submitTips('流程启动失败!','error');
        										  });
        				}
        			});
    			}
    		}else if(operation == "viewProcess"){/*查看流程进度*/
    			/*listService.showProcessProgress($scope, "flowInstanceId", param);*/
    			listService.showProcessProgressInColumn($scope, "flowInstanceId",param);/*行内触发*/
    		}
	    }
	    
	    /**
	     * 是否可以操作，消息提示
	     */
	    var isAllowOperation = function(tipMsg1,tipMsg2){
	    	var selectedData = listService.getSelectData($scope.gridApi);
	    	console.log("选择操作的数据：");
	    	console.log(selectedData);
	    	if(selectedData != undefined){
	    		if(selectedData.approvalStatus == "flow_inapproval"){
					submitTips(tipMsg1,'warning');
					return false;
				}else if(selectedData.approvalStatus == "flow_passed"){
					submitTips(tipMsg2,'warning');
					return false;
				}else{
					return true;
				}
	    	}
	    }
	    
	    /**
		 * 初始化加载数据.
		 */
	    var load = $scope.load = function() {
	        listService.loadData($scope, '/api/supervisorContract/query', $scope.tableGrid);
	    }

	    load();

	    $scope.search = function() {
	        load();
	    };
	    
	    /**
	     * 查看合同详情
	     */
	    $scope.viewContractInfo = function(entity){
	    	console.log(entity);
	    	listService.editData($scope, $scope.gridApi, new popup("查看监理合同","/supervisorContract/viewDetails", null, "1200", "800"));
	    }

	}).filter("priceFilter", function() {/*价格数据过滤器*/
	    var priceFilterFunction = function(priceVal) {
	        if (priceVal == 0) {
	            return "0.0000";
	        } else {
	            return priceVal;
	        }
	    };
	    return priceFilterFunction;
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