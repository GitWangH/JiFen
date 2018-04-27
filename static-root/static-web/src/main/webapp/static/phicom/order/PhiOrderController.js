'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiOrderController', function ($scope, $location, $http,$rootScope, listService,httpService,shareData, excelService) {
    
    /***
	 * 定义显示的表格.
	 */
    $scope.tableGrid = {
            paginationPageSizes: [10, 25, 50, 100],
            paginationPageSize: 10,
            useExternalPagination: true,
            multiSelect: true,
		    columnDefs: [
//		       { name: '序号', field: 'code',width: '3%', enableColumnMenu: false},	        
//		       { name: '下单时间', field: 'createTime',width: '10%', enableColumnMenu: false},
//		       { name: '会员名称', field: 'userName',width: '10%', enableColumnMenu: false},
//		       { name: '订单编号', field: 'orderCode',width: '10%', enableColumnMenu: false},
//		       { name: '商品类型', field: 'productClassName',width: '10%', enableColumnMenu: false,decode: { field: "productClass", dataKey: "dic.product_Type" }},
//		       { name: '商品名称', field: 'productName',width: '10%', enableColumnMenu: false},
//		       { name: '市场价格(元)', field: 'marketPrice',width: '10%', enableColumnMenu: false},
//		       { name: '兑换积分', field: 'score',width: '10%', enableColumnMenu: false},
//		       { name: '加价(元)', field: 'money',width: '10%', enableColumnMenu: false},
//		       { name: '订单状态', field: 'mystatus',width: '10%', enableColumnMenu: false,decode: { field: "status", dataKey: "dic.status" }},
//		       { name: '是否支付', field: 'isclose',width: '5%', enableColumnMenu: false,decode: { field: "isclose", dataKey: "dic.isclose" }},
//		       { name: '是否配送', field: 'isdistribut',width: '5%', enableColumnMenu: false,decode: { field: "isdistribut", dataKey: "dic.isdistribut" }},
//		       { name: '支付方式', field: 'payType',width: '10%', enableColumnMenu: false,decode: { field: "payType", dataKey: "dic.pay_type" }},
//		       { name: '会员账号', field: 'phiMember.tel',width: '0%',visible:false, enableColumnMenu: false},
//		       { name: '是否为plus', field: 'isplusMember',width: '0%',visible:false, enableColumnMenu: false,decode: { field: "isPlus", dataKey: "dic.is_plusMember"}},
 		       { name: '序号', field: 'code',width: '3%', enableColumnMenu: false},	        
		       { name: '会员账号', field: 'phiMember.tel',width: '8%', enableColumnMenu: false},
		       { name: '是否为plus', field: 'isplusMember',width: '5%', enableColumnMenu: false,decode: { field: "isPlus", dataKey: "dic.is_plusMember"}},
		       { name: '订单编号', field: 'orderCode',width: '10%', enableColumnMenu: false},
		       { name: '商品名称', field: 'productName',width: '10%', enableColumnMenu: false},
		       { name: '兑换积分', field: 'score',width: '10%', enableColumnMenu: false},
		       { name: '下单时间', field: 'createTime',width: '10%', enableColumnMenu: false},
		       { name: '省', field: 'phiOrderInfo.province',width: '10%', enableColumnMenu: false},
		       { name: '市', field: 'phiOrderInfo.city',width: '10%', enableColumnMenu: false},
		       { name: '区', field: 'phiOrderInfo.area',width: '10%', enableColumnMenu: false},
		       { name: '详细地址', field: 'phiOrderInfo.addressDetail',width: '10%', enableColumnMenu: false},
		       { name: '联系人手机', field: 'phiOrderInfo.tel',width: '10%', enableColumnMenu: false},
		       { name: '快递单号', field: 'nu',width: '10%', enableColumnMenu: false},
		       { name: '操作', field: 'opreate',width: '10%', enableColumnMenu: false,cellTemplate:'  <a style="cursor:pointer"><span ng-click="grid.appScope.showDetail(row.entity.id)" >查看</span>&nbsp;<span ng-if="row.entity.productClass==2 && row.entity.phiLogistic!=null" ng-click="grid.appScope.logisticDetail(row.entity.phiLogistic.id)" >物流详情</span> <span ng-if="row.entity.productClass==1 && row.entity.phiOrderInfo.winnerStatus==1"  ng-click="grid.appScope.WinList(row.entity.productId)">中奖名单 </span> <span ng-if="row.entity.productClass==0" ng-click="grid.appScope.CouponDetail(row.entity.phiOrderInfo.id)">券码详情</span></a>'}
//		       { name: '生成地区', field: 'addregin',width: '5%', enableColumnMenu: false,cellTemplate:'  <a><span ng-click="grid.appScope.addRegin(row.entity.id)" >地区</span></a>'},

		       ]
	  };
	//地区测试
    $scope.addRegin = function(id) {
        var scope = $rootScope.$new();
        var pop = {
            title: "地区测试",
            passParams: id,
            onclosedFun: load,
            controller: 'PhiRegionsAddController',
            templateView: "/static/templates/form/template_add.html",

        };
        listService.popPanel($scope, pop);
    }
    
    
    
    $scope.showDetail = function(id) {
        var scope = $rootScope.$new();
        var pop = {
            title: "订单详情",
            passParams: id,
            onclosedFun: load,
            controller: 'PhiOrderDetialController',
            templateView: "/static/phicom/order/template_details.html",
            height:"350px"

            	
        };
        listService.popPanel($scope, pop);
    }
    
    $scope.logisticDetail = function(id) {
        var scope = $rootScope.$new();
        var pop = {
            title: "物流详情",
            passParams: id,
            onclosedFun: load,
            controller: 'logisticController',
            templateView: "/static/phicom/order/LogisticDetial.html",

        };
        listService.popPanel($scope, pop);
    }
    
    $scope.WinList = function(id) {
        var scope = $rootScope.$new();
        var pop = {
            title: "中奖名单",
            passParams: id,
            onclosedFun: load,
            controller: 'WinListController',
            templateView: "/static/phicom/order/template_home.html",

        };
        listService.popPanel($scope, pop);
    }
    
    $scope.CouponDetail = function(id) {
        var scope = $rootScope.$new();
        var pop = {
            title: "券码详情",
            passParams: id,
            onclosedFun: load,
            controller: 'CouposDetialController',
            templateView: "/static/phicom/order/template_details.html",
            height:"350px"
        };
        listService.popPanel($scope, pop);
    }
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		$scope.gridApi.selection.clearSelectedRows() 
	    		load();
	          });
	      /*调整grid底部高度*/
	        listService.setGridHeight();
	  	}; 
    
    listService.init($scope,$http, $scope.tableGrid, $scope.gridApi, 'api/phiOrder/Allquery');
  
    //定义查询页
    var queryPage = new QueryPage(1,10,"createTime desc","false");
    queryPage.addParam(new queryParam('会员账号','phiMember.tel','string','alllike'));
    var memberId = new queryParam('会员id','phiMember.id','string');
    memberId.isShow = false;
    if($scope.passParams && $scope.passParams.memberId){
        memberId.value = $scope.passParams.memberId;
    }
    queryPage.addParam(memberId);
    var isplusMember=new queryParam('是否为plus','phiMember.isplusMember','string','=',null,"dic.is_plusMember");
    isplusMember.componentType = 'select';
    queryPage.addParam(isplusMember);
    queryPage.addParam(new queryParam('订单编号','orderCode','string','alllike'));
    queryPage.addParam(new queryParam('商品名称','phiOrderInfo.productName','string','alllike'));
    var orderTime = new queryParam('下单时间','createTime','date','>=');
    var time = new queryParam('结束时间','createTime','date','<=');
    time.isShow = false;
	queryPage.addParam(time);
    queryPage.addParam(orderTime);
//  memberId.isShow = false;
//  if($scope.passParams && $scope.passParams.memberId){
//      memberId.value = $scope.passParams.memberId;
//  }
    queryPage.addParam(new queryParam('快递单号','phiLogistic.nu','string','alllike'));
    queryPage.addParam(new queryParam('兑换积分(最小)','phiOrderInfo.score','long','>='));
    queryPage.addParam(new queryParam('兑换积分(最大)','phiOrderInfo.score','long','<='));
    queryPage.addParam(new queryParam('省','phiOrderInfo.province','string','alllike'));
    queryPage.addParam(new queryParam('市','phiOrderInfo.city','string','alllike'));
    queryPage.addParam(new queryParam('区','phiOrderInfo.area','string','alllike'));
    queryPage.addParam(new queryParam('详细地址','phiOrderInfo.addressDetail','string','alllike'));
    queryPage.addParam(new queryParam('联系人手机号','phiOrderInfo.tel','string','alllike'));
    
    
    
//    queryPage.addParam(new queryParam('会员名称','phiMember.userName','string','alllike'));
//    var memberId = new queryParam('会员id','phiMember.id','string');
//    memberId.isShow = false;
//    if($scope.passParams && $scope.passParams.memberId){
//        memberId.value = $scope.passParams.memberId;
//    }
//    queryPage.addParam(memberId);
//    queryPage.addParam(new queryParam('订单编号','orderCode','string','alllike'));
//    queryPage.addParam(new queryParam('商品名称','phiOrderInfo.productName','string','alllike'));
//    var status=new queryParam('商品类型','productClass','string','=',null,"dic.product_Type");
//    status.componentType = 'select';
//    queryPage.addParam(status);
//    var status=new queryParam('订单状态','status','string','=',null,"dic.status");
//    status.componentType = 'select';
//    queryPage.addParam(status);
    
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage); 
   
    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('导出','export','/phiOrder/export','exportData'));
    /***
     * 设置界面的功能按钮.
     */    
    listService.setButtonList($scope, btnArray);
   
    //定义功能按钮
   /* var btnArray = [];
    btnArray.push(new pageButton('审核通过', 'audit', '/phiOrder/Audited', 'auditData'));
    btnArray.push(new pageButton('审核驳回', 'reject', '/phiOrder/reject', 'rejectData'));
    btnArray.push(new pageButton('商品详情', 'checkDetail', '/phiOrder/checkDetail', 'DetailData'));
    *//***
     * 设置界面的功能按钮.
     *//* 
    listService.setButtonList($scope, btnArray);*/

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
        if(operation == 'viewData'){
//        	console.log($scope.tableGrid.data);
        	listService.editData($scope, $scope.gridApi, new popup("查看",'/phiOrder/view', null, "1200", "800"),param);
        }else if(operation=='exportData'){ /** 导出 **/
//    		excelService.init($scope);
    		excelService.exp("E180413135154990",queryPage);
        }
    }
    
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
   	listService.loadData($scope, 'api/phiOrder/Allquery', $scope.tableGrid);
    }
    load();
    
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		if($scope.queryPage!=null){
			if($scope.queryPage.queryParamList[5].value!=null){
				$scope.queryPage.queryParamList[4].value = $scope.queryPage.queryParamList[5].value;
			}
		}
		// $scope.gridApi.selection.clearSelectedRows() 
		load();
	};   
	
	/*
	 * 查看订单详情
	 * */
//	$scope.showDetail = function(id){
//		 var scope = $rootScope.$new();
//		 scope.id = id;
//		 //scope.type = 4; /*1:劳务-搬迁 2:管具-单井 3:劳务-零星 4:管具-零星 */
//		var modal= $modal({
//		     title: '订单明细', 
//		     backdrop: 'static',
//		     show: false,
//		      controller: "PhiOrderDetialController",
//		     template: TemplatePrefix+"public/template_modal_home.html",
//		     scope:scope
//		 });
//		modal.$promise.then(modal.show);
//	}
});



/*查看明细*/
angular.module('huatek.controllers').controller('PhiOrderDetialController', function ($scope, $http, editService, httpService,$rootScope,cacheService) {
	 var editDataUrl = 'api/phiOrder/edit';
	 var homeUrl = '/phiOrder/home';
	 $scope.editId = $scope.passParams;
	//定义块
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'订单基本信息'));
	    columnWrapArray.push(new multipleColumn(2,'订单商品基本信息'));
      $scope.columnWrapArray = columnWrapArray;
  //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)
  
      var formFieldArray = [];
      formFieldArray.push(new FormElement(1,'下单时间','createTime','string',0,'30','',null,null,null,null,'readonly'));
      formFieldArray.push(new FormElement(1,'会员账号','userName','string',0,'30','',null,null,null,null,'readonly'));
      formFieldArray.push(new FormElement(1,'订单编号','orderCode','string',0,'30',null,null,null,null,null,'readonly'));
      var status = new FormElement(1,'订单状态','status','string',0,'30',null,null,null,null,'dic.status','readonly');
      formFieldArray.push(status);
      var myscore = new FormElement(1,'兑换积分','score','string',0,'30',null,null,null,null,null,'readonly');
      formFieldArray.push(myscore);
      var myMoney = new FormElement(1,'加购金额(元)','money','string',0,'30',null,null,null,null,null,'readonly');
      formFieldArray.push(myMoney);

      	
      formFieldArray.push(new FormElement(2,'商品编号','productCode','string',0,'30',null,null,null,null,null,'readonly'));
      formFieldArray.push(new FormElement(2,'商品名称','productName','string',0,'30',null,null,null,null,null,'readonly'));
      var productClassEle = new FormElement(2,'商品类型','productClass','string',0,'30',null,null,null,null,'dic.product_type','readonly');
      formFieldArray.push(productClassEle);
      formFieldArray.push(new FormElement(2,'商品分类','productType','string',0,'30',null,null,null,null,null,'readonly'));

      formFieldArray.push(new FormElement(2,'市场价格(元)','marketPrice','string',0,'30',null,null,null,null,null,'readonly'));
      formFieldArray.push(new FormElement(2,'兑换积分','score','string',0,'30',null,null,null,null,null,'readonly'));
      formFieldArray.push(new FormElement(2,'加购金额(元)','money','string',0,'30',null,null,null,null,null,'readonly'));
      formFieldArray.push(new FormElement(2,'兑换数量','productCount','string',0,'30',null,null,null,null,null,'readonly'));
   //只读
		/*  $rootScope.formFieldArray = formFieldArray;
		  for(var i=0;i<formFieldArray.length;i++){
			   formFieldArray[i].readonly=true;
		  }		*/
		  
    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);  
    /**
      *加载编辑数据
     */
    if($scope.editId){
    	console.log($scope.editId);
    	editService.loadData($scope, editDataUrl, $scope.editId,function(response){
    		myscore.value = response.score;
    		myMoney.value = response.money;
    		 var cache=cacheService.getData("dic.status");
    		 var myclass = cacheService.getData("dic.product_type");
        	 console.log(cache);
             angular.forEach(cache, function (data, index) {
            	 console.log(response)
	             if(data.code == response.status){
	            	 status.value = data.name;
	             }
	         });
             angular.forEach(myclass, function (data, index) {
            	 console.log(response)
	             if(data.code == response.productClass){
	            	 productClassEle.value = data.name;
	                 return;
	             }
	         });  
             
    	});
    }
    /**
      *修改
     */   
    $scope.update = function(){
    	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    } 
   
    
});


/*查看物流信息*/
angular.module('huatek.controllers').controller('logisticController', function ($scope, $http, editService, httpService) {
		var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'基本信息'));
	    columnWrapArray.push(new multipleColumn(2,'物流详情'));
	    $scope.columnWrapArray = columnWrapArray;
	    var formFieldArray = [];
	    var comname = new FormElement(1,'物流公司','comname','string',0,'30');
	    comname.readonly = true;
	    formFieldArray.push(comname);
	    var nu = new FormElement(1,'物流单号','nu','string',0,'30');
	    nu.readonly = true;
        formFieldArray.push(nu);
        editService.init($scope, $http);
        editService.setFormFields($scope, formFieldArray); 
        $scope.tableGrid = {
	        columnDefs: [
	             { name: '时间', field: 'time',width: '20%', enableColumnMenu: false},
				 { name: '详细信息', field: 'context',width: '*', enableColumnMenu: false}
	        ]
	    };
	    /***
	     * 初始化加载数据.
	     */
	    var load = $scope.load = function() {
	        httpService.get($scope, 'api/phiLogistic/detailForAdmin/'+$scope.passParams).success(function(res){
	        	comname.value = res.comname;
	        	nu.value = res.nu;
	        	if(res.mylogisticdto){
	        		$scope.tableGrid.data = res.mylogisticdto;
	        	}
	        });
	        //找到所有的中奖订单的数量
	    }
	    load();
    
});

/*查看券码详情*/
angular.module('huatek.controllers').controller('CouposDetialController', function ($scope, $http, editService, httpService,$rootScope, cacheService) {
	 $scope.editId = $scope.passParams;
	 var editDataUrl = 'api/phiOrder/couponsDetial';
	 var homeUrl = '/phiOrder/home';
	 
	//定义块
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'券码详情'));
	    /*columnWrapArray.push(new multipleColumn(2,'券码'));*/
	    var formFieldArray = [];
	    $scope.columnWrapArray = columnWrapArray;
	    var productClass = new FormElement(1,'商品类型','productClass','string',0,'30');
	    productClass.isShow = false;
	    formFieldArray.push(productClass);
  //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)
	    var consType = new FormElement(1,'券类型','coupType','string',0,'30');
	    formFieldArray.push(consType);
        
         formFieldArray.push(new FormElement(1,'优惠券名称','productName','string',0,'30'));
         formFieldArray.push(new FormElement(1,'券码','coupCode','string',0,'30'));
   //只读
		 $rootScope.formFieldArray = formFieldArray;
		  for(var i=0;i<formFieldArray.length;i++){
			   formFieldArray[i].readonly=true;
		  }		
		  
    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);  
    /**
      *加载编辑数据
     */
    if($scope.editId){
    	console.log($scope.editId);
    	editService.loadData($scope, editDataUrl, $scope.editId, function(response){
    		console.log(productClass.value);
	    	if("0" != productClass.value){
	    		var cache=cacheService.getData("dic.coupons_third_party_type");
	            angular.forEach(cache, function (data, index) {
	                if(data.code == response.consType){
	                	consType.value = data.name;
	                    return;
	                }
	            });
	   	    }
    	});
    	
    }
    /**
      *修改
     */   
    $scope.update = function(){
    	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    } 
   
    
});

/*查看中奖名单*/
angular.module('huatek.controllers').controller('WinListController', function ($scope, $location, $http,$rootScope, listService,httpService,shareData) {
	 
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        multiSelect: true,
        columnDefs: [
             { name: '名称', field: 'userName',width: '50%', enableColumnMenu: false},
			 { name: '电话号码', field: 'mobile',width: '50%', enableColumnMenu: false}
        ]
    };
    var queryPage = new QueryPage(1,10,"id desc","false");
    listService.setQueryPage($scope, queryPage); 
    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/phiOrder/winnerListForBack/'+$scope.passParams, $scope.tableGrid);
        //找到所有的中奖订单的数量
    }
    load();
	
    
});


/****
 * 测试地区
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiRegionsAddController', function ($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	$scope.editId = $scope.passParams;
	 var addDataUrl = 'api/phiRegions/add';
	 var editDataUrl = 'api/phiRegions/edit';
	 var homeUrl = '/phiRegions/home';
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'商品信息'));
    columnWrapArray.push(new multipleColumn(2,'收货地址'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
//      formFieldArray.push(new FormElement(1,'商品名称','productName','string',1,'30',null,null,null,null,null,'readonly'));
//	  //formFieldArray.push(new FormElement(1,'商品类型','productType','string',1,'30','select',null, null, null, 'dic.product_type'));
//	  formFieldArray.push(new FormElement(1,'加价','money','number',1,'30',null,null,null,null,null,'readonly'));
//	  formFieldArray.push(new FormElement(1,'积分','score','number',1,'30',null,null,null,null,null,'readonly'));
//	  //formFieldArray.push(new FormElement(1,'兑换上限','exchangSuperLimit','number',1,'30',null,null,null,null,null,'readonly'));
//	 // formFieldArray.push(new FormElement(1,'库存','productStock','number',1,'30'));
//	  formFieldArray.push(new FormElement(1,'市场价格','marketPrice','number',1,'30'));
//	  formFieldArray.push(new FormElement(1,'描述','remark','string',1,'128','textarea'));
//	  var productImage = new FormElement(1,'商品图片','productImage','string','','36','fileMultiple');
//	  productImage.systemHeader = "/root/dd";
//	  formFieldArray.push(productImage);
//	  formFieldArray.push(new FormElement(1,'兑换数量','productCount','number',0,'30'));
//	  formFieldArray.push(new FormElement(2,'收获人','receiverName','string',1,'30'));
//	  formFieldArray.push(new FormElement(2,'联系电话','tel','string',1,'30'));
//	  formFieldArray.push(new FormElement(2,'详细地址','addressDetail','string',1,'30'));
		  
		  
    
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
    /*if($scope.editId){
    	editService.loadData($scope,addDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }*/
    	
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
