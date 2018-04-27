'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiPlusMemberOrderController', function ($scope, $location, $http,$rootScope, listService,httpService,shareData, excelService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.inFocus = true
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    enableGridMenu : true,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '会员用户名', field: 'telNumber',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '订单号', field: 'orderNo',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '商户订单号', field: 'transactionId',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '购买时间', field: 'payTime',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '货品明细', field: 'plusCode',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '总数', field: 'count',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '平台', field: 'platFormName',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '订单支付状态', field: 'isPayName',width: '10%', enableColumnMenu: false,enableCellEdit:false,decode: { field: "isPay", dataKey: "dic.is_pay"}},
		       { name: '支付方式', field: 'payTypeName',width: '10%', enableColumnMenu: false,enableCellEdit:false,decode: { field: "payType", dataKey: "dic.pay_type" }},
		       { name: '支付优惠金额', field: 'countMoney',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '实际支付金额', field: 'payMoney',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '实际到账金额', field: 'realMoney',width: '10%', enableColumnMenu: false,enableCellEdit:false},
		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false,isShow:true,cellTemplate:"<input ng-model='row.entity.remark' class='new-input' ng-blur='grid.appScope.saveRemark(row)' type='text'></input>"}

//	           { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false,enableCellEdit:true,isShow:true,cellTemplate:"<input ng-model='row.entity.remark' class='new-input' ng-class='{input-focus:inFocus}' ng-focus= 'grid.appScope.saveRemark1(row)' ng-blur='grid.appScope.saveRemark(row)' type='text'></input>"}
//		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false,enableCellEdit:true,cellEditableCondition:},
		       ]
	  };
	
	/**
	 * 编辑保存行
	 */
	$scope.saveRemark1 = function (row) {
		$scope.inFocus = true
	}
	$scope.saveRemark = function(row) {
		console.log("id:"+(row.entity.id != undefined) +"     value:"+row.entity.id);
		console.log("remark:"+cnex4_isNotBlank_str(row.entity.remark));
		console.log((row.entity.id != undefined)  && cnex4_isNotBlank_str(row.entity.remark));
		if((row.entity.id != undefined) && 
				cnex4_isNotBlank_str(row.entity.remark)){
			httpService.post($scope, 'api/phiPlusOrder/saveRemark/' + row.entity.id,{"remark":row.entity.remark})
		}
//          row.entity.cpnsWayItems = response;
//		editService.updateData($scope, 'api/phiPlusOrder/edit', '/phiPlusOrder/home',id);
//		httpService.get($scope, 'api/phiCoupons/select/' + row.entity.cpnsName).success(function(response) {
//            row.entity.cpnsWayItems = response;
	}
	
	
	
	
	
	/***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	      /*调整grid底部高度*/
	        listService.setGridHeight();
	  	}; 
  	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope,$http, $scope.tableGrid, $scope.gridApi, 'api/phiPlusOrder/query');
//	listService.init($scope, $location, $http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"createTime desc","false");
    
    //定义搜索框
    queryPage.addParam(new queryParam('会员用户','telNumber','string','alllike'));
    queryPage.addParam(new queryParam('订单号','orderNo','string','alllike'));
    queryPage.addParam(new queryParam('商家订单号','transactionId','string','alllike'));
    var platform=new queryParam('平台','platForm','string','=',null,"dic.platform");
    platform.componentType = 'select';
    queryPage.addParam(platform);
    queryPage.addParam(new queryParam('购买时间-开始','payTime','dateTime','>='));
    queryPage.addParam(new queryParam('购买时间-结束','payTime','dateTime','<='));
    var paytYpe=new queryParam('支付方式','payType','string','=',null,"dic.pay_type");
    paytYpe.componentType = 'select';
    queryPage.addParam(paytYpe);
    queryPage.addParam(new queryParam('实际支付金额','realMoney','double','='));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope,queryPage);
    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('导出','export','/phiPlusOrder/export','exportData'));
    /***
     * 设置界面的功能按钮.
     */    
    listService.setButtonList($scope, btnArray);
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	  if(operation=='exportData'){ /** 导出 **/
//     		excelService.init($scope);
     		excelService.exp("E180417162845385",queryPage);
         }
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api/phiPlusOrder/query', $scope.tableGrid);
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiPlusMemberOrderAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiPlusOrder/add';
	 var editDataUrl = 'api/phiPlusOrder/edit';
	 var homeUrl = '/phiPlusOrder/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'主键','id','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'商品订单号','orderNo','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'时间','createTime','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'微信支付订单号','transactionId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'是否支付 1 已支付 0 未支付','isPay','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'支付金额','payMoney','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'支付时间','payTime','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'会员id','memberId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'plus会员等级code','plusCode','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'支付方式 1 微信 2 支付宝','paytYpe','string',1,'30'));
    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $location, $http);
    
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields($scope,formFieldArray); 
    
    /***
     * 定义获取需要编辑的内容.
     */
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 定义保存操作.
     */
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl, homeUrl);
    }
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
