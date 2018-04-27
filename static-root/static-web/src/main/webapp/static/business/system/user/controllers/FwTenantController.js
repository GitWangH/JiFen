'use strict';
/*******************************************************************************
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('FwTenantController', function($scope, $location, $http,$modal,
		$rootScope, listService,httpService) {
	/***************************************************************************
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		paginationPageSizes : [ 10, 25, 50, 100 ],
		paginationPageSize : 10,
		useExternalPagination : true,
		enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
		columnDefs : [ 
		 {
			name : '代码',
			field : 'code',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '会员全称',
			field : 'name',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '会员简称',
			field : 'shortName',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '详细地址',
			field : 'addr',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '注册时间',
			field : 'createTime',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '到期时间',
			field : 'endTime',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '启用状态',
			field : 'status1',
			width : '10%',
			enableColumnMenu : false,decode:{field:"status",dataKey:"dic.yes_or_no"}
		}, {
			name : '联系人',
			field : 'contactPerson',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '联系人电话',
			field : 'contactTel',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '联系人邮箱',
			field : 'contactMail',
			width : '10%',
			enableColumnMenu : false
		}, {
			name : '联系人手机',
			field : 'contactPhone',
			width : '10%',
			enableColumnMenu : false
		} ]

	};

	/**
	 * 初始化编辑界面. 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
	// 定义查询页
	var queryPage = new QueryPage(1, 10, "id desc", "false");

	// 定义搜索框
	var name = new queryParam('会员全称', 'name', 'string', 'like');
	queryPage.addParam(name);
	var endTime = new queryParam('到期时间', 'endTime', 'dateTime', '<=');
	queryPage.addParam(endTime);
	var statusParam=new queryParam('状态', 'status', 'string',
			'=',null,'dic.yes_or_no');
	queryPage.addParam(statusParam);
	statusParam.componentType="select";

	$rootScope.searchCount = queryPage.queryParamList.length;
	/***************************************************************************
	 * 设置查询输入按钮.
	 */
	listService.setQueryPage($scope,queryPage);

	/***************************************************************************
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi) {
		$scope.gridApi = gridApi;
		gridApi.pagination.on.paginationChanged($scope, function(newPage,
				pageSize) {
			queryPage.page = newPage;
			queryPage.pageSize = pageSize;
			load();
		});
	};

	// 定义功能按钮
	var btnArray = [];
	btnArray.push(new pageButton('新增', 'add', '/fwTenant/add', 'addData'));
	btnArray.push(new pageButton('编辑', 'edit', '/fwTenant/edit', 'editData'));
	btnArray.push(new pageButton('到期时间', 'changeDate', '/fwTenant/changeDate','changeDate'));
	btnArray.push(new pageButton('启用', 'active', '/fwTenant/active','activeData'));
	btnArray.push(new pageButton('禁用', 'disable', '/fwTenant/disable','disableData'));
	btnArray.push(new pageButton('初始化数据', 'init', '/fwTenant/initData','initData'));
	/***************************************************************************
	 * 设置界面的功能按钮.
	 */
	listService.setButtonList($scope,btnArray);

	/***************************************************************************
	 * 桥接按钮事件.
	 */
	$scope.execute = function(operation, param) {
		if (operation == 'addData') {
			listService.addData($scope, new popup("会员新增","/fwTenant/add",null,null,null,function(){load()}));
		} else if (operation == 'editData') {
			listService.editData($scope,$scope.gridApi,new popup("会员编辑","/fwTenant/edit",null,null,null,function(){load()}) );
		}else if (operation == 'changeDate') {
			listService.editData($scope,$scope.gridApi,new popup("会员到期时间","/fwTenant/changeDate",null,500,350,function(){load()}) );
			

		}else if(operation == 'activeData'){
			if($scope.gridApi.selection.getSelectedRows().length !=1){
    			submitTips("请勾选一条数据！",'warning');
    			return false;
    		}
			if($scope.gridApi.selection.getSelectedRows()[0].status=='1'){
				submitTips("该会员已经启用，请选择禁用的会员！",'warning');
    			return false;
			}
			$scope.promse=httpService.get($scope,"api/fwTenant/changeStatus/1/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(){load();});
		}else if(operation == 'disableData'){
			if($scope.gridApi.selection.getSelectedRows().length !=1){
    			submitTips("请勾选一条数据！",'warning');
    			return false;
    		}
			if($scope.gridApi.selection.getSelectedRows()[0].status=='0'){
				submitTips("该会员已经禁用，请选择启用的会员！",'warning');
    			return false;
			}
			$scope.promse=httpService.get($scope,"api/fwTenant/changeStatus/0/"+$scope.gridApi.selection.getSelectedRows()[0].id).success(function(){load();});
		}else if(operation == 'initData'){
			if($scope.gridApi.selection.getSelectedRows().length !=1){
    			submitTips("请勾选一条数据！",'warning');
    			return false;
    		}
			if($scope.gridApi.selection.getSelectedRows()[0].status=='0'){
				submitTips("该会员已经禁用，请选择启用的会员！",'warning');
    			return false;
			}
			$scope.promise=httpService.get($scope,"api/fwTenant/initData/"+$scope.gridApi.selection.getSelectedRows()[0].id);
		}
	}
	/***************************************************************************
	 * 初始化加载数据.
	 */
	var load = function() {
		listService.loadData($scope,'api/fwTenant/query', $scope.tableGrid);
	}

	load();
	/*
	 * 查询需要调用的函数.
	 */
	$scope.search = function() {
		load();
	};

	$scope.resetSearch = function(){
		name.value = null;
		statusParam.value = null;
		endTime.minValue = null;
		endTime.maxValue = null;
	}

});

/*******************************************************************************
 * 编辑控制器. 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('FwTenantAddController', function($scope, $http, editService, $rootScope,httpService) {
	/***************************************************************************
	 * 定义编辑跳转的路径.
	 */
	var addDataUrl = 'api/fwTenant/add';
	var editDataUrl = 'api/fwTenant/edit';
	var homeUrl = '/fwTenant/home';

	// 定义块
	var columnWrapArray = [];
	columnWrapArray.push(new multipleColumn(1, '基本信息'));
	$scope.columnWrapArray = columnWrapArray;

	// 定义用户录入数据FormElement(column, title, name, type, require, max, model,
	// event, min)
	var formFieldArray = [];
	formFieldArray.push(new FormElement(1, '代码', 'code', 'string', 1, '30'));
	formFieldArray.push(new FormElement(1, '会员全称', 'name', 'string', 1, '30'));
	formFieldArray.push(new FormElement(1, '会员简称', 'shortName', 'string', 1, '30'));
	formFieldArray.push(new FormElement(1, '详细地址', 'addr', 'string', 0, '50'));
	var endTimeFiled = new FormElement(1, '到期时间', 'endTime', 'string', 1, '30','dateTime');
	endTimeFiled.minValue = dateFormat(new Date(), 'yyyy-MM-dd');
	endTimeFiled.maxValue = dateFormat(new Date(), 'hh:mm:ss');
	formFieldArray.push(endTimeFiled);
	formFieldArray.push(new FormElement(1, '启用状态', 'status', 'string',
			1, '30','select',null,null,'1','dic.yes_or_no'));
	formFieldArray.push(new FormElement(1, '联系人', 'contactPerson', 'string', 0,
			'30'));
	formFieldArray.push(new FormElement(1, '联系人电话', 'contactTel', 'telephoneOrPhone', 0,
			'15'));
	formFieldArray.push(new FormElement(1, '联系人邮箱', 'contactMail', 'email', 0,
			'30'));
	formFieldArray.push(new FormElement(1, '联系人手机', 'contactPhone', 'phone',
			0, '15'));

	// 设置全局变量
	$rootScope.formFieldArray = formFieldArray;

	/**
	 * 初始化编辑界面. 比如显示编辑界面的模块名称. 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $http);

	/***************************************************************************
	 * 设置编辑界面输入的字段. 被设置必须在服务初始化之后执行.
	 */
	editService.setFormFields($scope,formFieldArray);

	/***************************************************************************
	 * 定义获取需要编辑的内容.
	 */

	if ($scope.editId) {
		editService.loadData($scope,editDataUrl, $scope.editId);
	} else {
		$scope.editId = -1;
	}

	/***************************************************************************
	 * 定义更新操作.
	 */
	$scope.update = function() {
		editService.updateData($scope,editDataUrl, homeUrl, $scope.editId,null,$scope.back);
	}
	/**
	 * 定义保存操作.
	 */
	$scope.save = function() {
		editService.saveData($scope,addDataUrl, homeUrl,null,$scope.back);
	}
	
});
angular.module('huatek.controllers').controller('ChangeDateController', function($scope, $location, $http,
		$routeParams, editService, $rootScope) {
	/***************************************************************************
	 * 定义编辑跳转的路径.
	 */
	var editDataUrl = 'api/fwTenant/changeDate';
	var getDataUrl = 'api/fwTenant/edit';

	// 定义块
	var columnWrapArray = [];
	columnWrapArray.push(new multipleColumn(1, '基本信息'));
	$scope.columnWrapArray = columnWrapArray;

	// 定义用户录入数据FormElement(column, title, name, type, require, max, model,
	// event, min)
	var formFieldArray = [];
	var endTimeFiled = new FormElement(1, '到期时间', 'endTime', 'string', 1, '30','dateTime');
	// endTimeFiled.minValue = dateFormat(new Date(), 'yyyy-MM-dd');
	// endTimeFiled.maxValue = dateFormat(new Date(), 'hh:mm:ss');
	formFieldArray.push(endTimeFiled);

	// 设置全局变量
	$rootScope.formFieldArray = formFieldArray;

	/**
	 * 初始化编辑界面. 比如显示编辑界面的模块名称. 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $http);

	/***************************************************************************
	 * 设置编辑界面输入的字段. 被设置必须在服务初始化之后执行.
	 */
	editService.setFormFields($scope,formFieldArray);

	/***************************************************************************
	 * 定义获取需要编辑的内容.
	 */
	
	if($scope.editId){
		editService.loadData($scope, getDataUrl, $scope.editId);
	}
	$scope.update = function() {
		editService.updateData($scope, editDataUrl, "", $scope.editId,null,$scope.back);
	}
	
});
/*******************************************************************************
 * 编辑控制器. 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
