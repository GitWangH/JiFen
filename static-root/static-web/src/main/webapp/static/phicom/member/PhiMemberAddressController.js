'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiMemberAddressController', function ($scope, $location, $http,$rootScope, listService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '会员id', field: 'memberId',width: '10%', enableColumnMenu: false},
		       { name: '地址id', field: 'addressId',width: '10%', enableColumnMenu: false},
		       { name: '国家', field: 'country',width: '10%', enableColumnMenu: false},
		       { name: '省', field: 'province',width: '10%', enableColumnMenu: false},
		       { name: '市', field: 'city',width: '10%', enableColumnMenu: false},
		       { name: '区', field: 'area',width: '10%', enableColumnMenu: false},
		       { name: '街道', field: 'street',width: '10%', enableColumnMenu: false},
		       { name: '详细地址', field: 'addressDetail',width: '10%', enableColumnMenu: false},
		       { name: '电话', field: 'tel',width: '10%', enableColumnMenu: false},
		       { name: '联系人姓名', field: 'name',width: '10%', enableColumnMenu: false},
		       { name: '邮编', field: 'zipCode',width: '10%', enableColumnMenu: false},
		       { name: '是否默认(1，默认。0不默认)', field: 'defaultState',width: '10%', enableColumnMenu: false},
		       { name: '上门日期', field: 'day',width: '10%', enableColumnMenu: false},
		       { name: '上门时间', field: 'time',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    //定义搜索框
    queryPage.addParam(new queryParam('电话','tel','string','alllike'));
    queryPage.addParam(new queryParam('联系人姓名','name','string','alllike'));
    queryPage.addParam(new queryParam('邮编','zipCode','string','alllike'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage(queryPage);
    
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
    
    //定义功能按钮
    var btnArray = [];
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData('api/phiMemberAddress/query', $scope.tableGrid);
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
angular.module('huatek.controllers').controller('PhiMemberAddressAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiMemberAddress/add';
	 var editDataUrl = 'api/phiMemberAddress/edit';
	 var homeUrl = '/phiMemberAddress/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'会员id','memberId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'地址id','addressId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'国家','country','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'省','province','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'市','city','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'区','area','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'街道','street','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'详细地址','addressDetail','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'电话','tel','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'联系人姓名','name','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'邮编','zipCode','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'是否默认(1，默认。0不默认)','defaultState','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'上门日期','day','string',1,'30','date'));
		  formFieldArray.push(new FormElement(1,'上门时间','time','string',1,'30','datetime'));
    
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
    editService.setFormFields(formFieldArray); 
    
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
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 定义保存操作.
     */
    $scope.save = function(){
    	editService.saveData(addDataUrl, homeUrl);
    }
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
