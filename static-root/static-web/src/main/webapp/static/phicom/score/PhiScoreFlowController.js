'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiScoreFlowController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	/***
	 * 定义显示的表格.
	 */
    $scope.tableGrid = {
            paginationPageSizes: [10, 25, 50, 100],
            paginationPageSize: 10,
            useExternalPagination: true,
            multiSelect: true,
		    columnDefs: [
		       { name: '序号', field: 'Num',width: '10%', enableColumnMenu: false},
		       { name: '任务项', field: 'scoreTaskConfig.taskName',width: '10%', enableColumnMenu: false},
		       { name: '任务前台名称', field: 'scoreTaskConfig.showName',width: '10%', enableColumnMenu: false},
		       { name: '任务说明', field: 'scoreTaskConfig.taskRemark',width: '10%', enableColumnMenu: false},
		       { name: '任务时间', field: 'score',width: '10%', enableColumnMenu: false},
		       { name: '是否开启', field: 'scoreTaskConfig.taskSwitch',width: '10%', enableColumnMenu: false},
		       { name: '操作人员', field: 'productStock',width: '10%', enableColumnMenu: false},
		       { name: '末次操作时间', field: 'productStatus',width: '10%', enableColumnMenu: false,decode: { field: "productStatus", dataKey: "dic.product_status" }},
		       { name: '操作', field: 'isShop',width: '10%', enableColumnMenu: false,decode: { field: "isShop", dataKey: "dic.isShop" }}
		      /* { name: '创建时间', field: 'createTime',width: '10%', enableColumnMenu: false},*/
		      
		    ]
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
    listService.init($scope);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    //定义搜索框
    queryPage.addParam(new queryParam('商品名称','productName','string','like'));
    queryPage.addParam(new queryParam('商品编号','productCode','string','like'));
     var productType = new queryParam('商品类型','productClass','string','=',null,"dic.product_class"); 
    productType.componentType = 'select';
    queryPage.addParam(productType);
    var ProductStatus = new queryParam('商品状态','productStatus','string','=',null,"dic.product_status"); 
    ProductStatus.componentType = 'select';
    queryPage.addParam(ProductStatus);
    
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);
    
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
    btnArray.push(new pageButton('生成订单', 'addorder',  '/phiOrder/add', 'addOrderData'));
    btnArray.push(new pageButton('新增', 'add',  '/phiProduct/add', 'addData'));
    btnArray.push(new pageButton('修改', 'edit', '/phiProduct/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/phiProduct/delete', 'deleteData'));
    btnArray.push(new pageButton('上架', 'goShop', '/phiProduct/up', 'toShopData'));
    btnArray.push(new pageButton('下架', 'downShop', '/phiProduct/down', 'downShopData'));
   
    /***
     * 设置界面的功能按钮.
     */ 
    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if (operation == 'addData') {
            listService.addData($scope, new popup("新增", '/phiProduct/add', $scope.treeParam));
        } else if (operation == 'editData') {
                listService.editData($scope, $scope.gridApi, new popup("修改", '/phiProduct/edit'));
        } else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/phiProduct/delete');
        } else if (operation == 'toShopData'){
        	this.active($scope.gridApi, '/phiProduct/up', '1');
        } else if (operation == 'downShopData'){
        	this.active($scope.gridApi, '/phiProduct/down', '2');
        } else if (operation == 'applyData'){
        	this.certification($scope.gridApi,'/phiProduct/apply')
        }else if(operation == 'addOrderData'){
        	listService.editData($scope, $scope.gridApi, new popup("生成订单", '/phiOrder/add'));
        }
    	
    }
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope, 'api/phiProduct/query', $scope.tableGrid);
    }
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};

	
	 $scope.certification = function(gridApi,toUrl) {
		 if (gridApi.selection.getSelectedRows().length < 1) {
	            submitTips('请至少勾选一条数据！', 'warning');
	            return false;
	        }
	        bootbox.confirm('确定要提交所选的数据吗?', function(result) {
	            if (result) {
	                angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
	                    if (data.id == null || data.id == undefined) {
	                        return;
	                    }
	                 var actionUrl = "api/phiProduct/submitProduct/" + data.id;
	                    httpService.post($scope, actionUrl).success(function() {
	                        var postQueryPage = copyQueryPage(_scope.queryPage);
	                        if (_scope.notNeedQueryPage) {
	                            postQueryPage.orderBy = '';
	                            postQueryPage.queryParamList = [];
	                        }	                       
	                        _scope.load();
	                    });
	                });
	            }
	        });		 		 
		 
	 }
	 $scope.active = function(gridApi, toUrl, val) {
	        /*获取当前选择的数据.*/
	        var selectData = gridApi.selection.getSelectedRows();
	        if (selectData.length == 0) {
	            submitTips('请选择一条数据', 'warning');
	            return;
	        }
	        var obj = selectData[0];
	        if ("1" == val) {
	            if (obj.isShop == "已上架") {
	                submitTips('警告：该商品已上架，不能操作。', 'warning');
	                return;
	            }
	        } else if ("2" == val) {
	            if (obj.isShop == "已下架") {
	                submitTips('警告：该商品已下架，不能操作。', 'warning');
	                return;
	            }
	        }
	        //批量上架商品
	        bootbox.confirm('确定要提交所选的数据吗?', function(result) {
	            if (result) {
	                angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
	                    if (data.id == null || data.id == undefined) {
	                        return;
	                    }
	                    var actionUrl = "api/phiProduct/productIsShop/" + data.id + "/" + val;
	                    httpService.post($scope, actionUrl).success(function() {
	                        var postQueryPage = copyQueryPage(_scope.queryPage);
	                        if (_scope.notNeedQueryPage) {
	                            postQueryPage.orderBy = '';
	                            postQueryPage.queryParamList = [];
	                        }	                       
	                        _scope.load();
	                    });
    	                    
	                });
	            }
	        });		 		 
	        
	        
	    }
	
	
	
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiProductAddController', function ($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiProduct/add';
	 var editDataUrl = 'api/phiProduct/edit';
	 var homeUrl = '/phiProduct/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'商品名称','productName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'商品类型','productType','string',1,'30','select',null, null, null, 'dic.product_class'));
		  formFieldArray.push(new FormElement(1,'加价','money','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'积分','score','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'兑换上限','exchangSuperLimit','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'库存','productStock','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'市场价格','marketPrice','number',1,'30'));
		  var productTypeNameList = new FormElement(1,'商品分类','productTypeNameList','String',1,'30','select');
		  /*productTypeNameList._showField = 'name';
		  productTypeNameList._returnField = 'code';*/
		  formFieldArray.push(productTypeNameList);
		  formFieldArray.push(new FormElement(1,'描述','remark','string',1,'128','textarea'));
		  var productImage = new FormElement(1,'商品图片','productImage','string','','36','fileMultiple');
		  productImage.systemHeader = "/root/dd";
		  formFieldArray.push(productImage);
    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope);
    
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
	
    editService.setFormFields($scope, formFieldArray);  
     editService.initFieldItems($scope,productTypeNameList, 'api/phiProduct/queryType');
    /***
     * 定义获取需要编辑的内容.
     */  
    if($scope.editId){
    	editService.loadData($scope,editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	editService.updateData($scope,editDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 定义保存操作.
     */
    $scope.save = function(){
    	editService.saveData($scope,addDataUrl, homeUrl);
    }
    
   
});

/****
 * 生成订单表
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiAddOrderController', function ($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiOrder/add';
	 var editDataUrl = 'api/phiProduct/edit';
	 var homeUrl = '/phiOrder/home';
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'商品信息'));
    columnWrapArray.push(new multipleColumn(2,'收货地址'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
      formFieldArray.push(new FormElement(1,'商品名称','productName','string',1,'30',null,null,null,null,null,'readonly'));
	  //formFieldArray.push(new FormElement(1,'商品类型','productType','string',1,'30','select',null, null, null, 'dic.product_type'));
	  formFieldArray.push(new FormElement(1,'加价','money','number',1,'30',null,null,null,null,null,'readonly'));
	  formFieldArray.push(new FormElement(1,'积分','score','number',1,'30',null,null,null,null,null,'readonly'));
	  //formFieldArray.push(new FormElement(1,'兑换上限','exchangSuperLimit','number',1,'30',null,null,null,null,null,'readonly'));
	 // formFieldArray.push(new FormElement(1,'库存','productStock','number',1,'30'));
	  formFieldArray.push(new FormElement(1,'市场价格','marketPrice','number',1,'30'));
	  formFieldArray.push(new FormElement(1,'描述','remark','string',1,'128','textarea'));
	  var productImage = new FormElement(1,'商品图片','productImage','string','','36','fileMultiple');
	  productImage.systemHeader = "/root/dd";
	  formFieldArray.push(productImage);
	  formFieldArray.push(new FormElement(1,'兑换数量','productCount','number',0,'30'));
	  formFieldArray.push(new FormElement(2,'收获人','receiverName','string',1,'30'));
	  formFieldArray.push(new FormElement(2,'联系电话','tel','string',1,'30'));
	  formFieldArray.push(new FormElement(2,'详细地址','addressDetail','string',1,'30'));
		  
		  
    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope);
    
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields($scope, formFieldArray);    
    /***
     * 定义获取需要编辑的内容.
     */  
    if($scope.editId){
    	editService.loadData($scope,editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
    /***
     * 定义更新操作.
     */
    $scope.update = function(){
    	editService.updateData($scope,addDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 定义保存操作.
     */
//    $scope.save = function(){
//    	editService.saveData($scope,addDataUrl, homeUrl);
//    }
    
   
});
