'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiRegionsController', function ($scope, $location, $http,$rootScope, listService,httpService,shareData) {

    /***
	 * 定义显示的表格.
	 */
    $scope.tableGrid = {
            paginationPageSizes: [10, 25, 50, 100],
            paginationPageSize: 10,
            useExternalPagination: true,
            multiSelect: true,
		    columnDefs: [
							{ name: '序号', field: 'id',width: '10%', enableColumnMenu: false},
							{ name: '权限名称', field: 'rightName',width: '10%', enableColumnMenu: false},
							{ name: '权限说明', field: 'remark',width: '10%', enableColumnMenu: false},
							{ name: '奖励', field: 'award',width: '10%', enableColumnMenu: false},
							{ name: '是否开启', field: 'isValidate',width: '10%', enableColumnMenu: false},
							{ name: '操作人员', field: 'operationperson',width: '10%', enableColumnMenu: false},
							{ name: '末次操作时间', field: 'lastoperationtime',width: '10%', enableColumnMenu: false},
							{ name: '操作', field: 'operate',width: '10%', enableColumnMenu: false,cellTemplate:'<a><span ng-click="grid.appScope.edit(row.entity.id)">权限详情&nbsp;</span><span ng-click="grid.appScope.show(row.entity.id)">任务关闭</span></a>'}
						]
	  };
	
    $scope.edit = function(id) {
        var scope = $rootScope.$new();
        var pop = {
            title: "消费返积分 ",
            passParams: id,
            onclosedFun: load,
            controller: 'PhiPlusAllRightAddController',
            templateView: "/static/phicom/plusmember/template_add.html",
//            /static/phicom/score/inner_template_add.html
        };
        listService.popPanel($scope, pop);
    }
    
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");  

    
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
    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
       /* if(operation == 'viewData'){
        	console.log($scope.tableGrid.data);
        	listService.editData($scope, $scope.gridApi, new popup("查看",'/phiOrder/view', null, "1200", "800"),param);
        }*/
    }
    
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope, 'api/phiPlusAllRight/query', $scope.tableGrid);
    }
    load();
    
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};   
	
	
	/****
	 * 编辑控制器.
	 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
	 */
	angular.module('huatek.controllers').controller('PhiRegionsAddController', function ($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {
		/***
		  * 定义编辑跳转的路径.
		  */
		 var addDataUrl = 'api/phiRegions/add';
//		 var editDataUrl = 'api/phiPlusAllRight/edit';
		 var homeUrl = '/phiPlusAllRight/home';
		 $scope.editId = $scope.passParams;
	   	//定义块
		 var columnWrapArray = [];
		    columnWrapArray.push(new multipleColumn(1,'基本信息'));
		    $scope.columnWrapArray = columnWrapArray;
		    var formFieldArray = [];
//		    $scope.rightName = new FormElement(1,'权限名称','rightName','string',1,'30');
//		    formFieldArray.push($scope.rightName);
//		    
//		    $scope.scoreOrMutiply = new FormElement(1,'权限设置','scoreOrMutiply','String',0,'30');
//		    formFieldArray.push($scope.scoreOrMutiply);
//		    $scope.scoreOrMutiply.event = function(val){
//		    	console.log(val);
//		    }
//		    $scope.scoreValue1Quantity = new FormElement(1,'翻倍设置','scoreValue1Quantity','number',0,'30');
//		    formFieldArray.push($scope.scoreValue1Quantity);
//		    $scope.scoreValue1 = new FormElement(1,'额外增加','scoreValue1','String',0,'30');
//		    formFieldArray.push($scope.scoreValue1);
//		    
//		    
//		    $scope.isValidate = new FormElement(1,'是否开启','isValidate','string',1,'30');
//		    $scope.isValidate.items = [{"name":"是", "code":"on"}, {"name":"否", "code":"off"}];
//		    formFieldArray.push($scope.isValidate);
//		    $scope.remark = new FormElement(1,'权限说明','remark','string',1,'30');
//		    formFieldArray.push($scope.remark);
		  //设置全局变量
//		    $scope.formFieldArrayList = formFieldArray;
		    $rootScope.formFieldArray = formFieldArray;
	    /**
		 * 初始化编辑界面.
		 * 比如显示编辑界面的模块名称.
		 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
		 */
		editService.init($scope, $http);
		/***
		 * 设置编辑界面输入的字段.
		 * 被设置必须在服务初始化之后执行.
		 */
		editService.setFormFields($scope,formFieldArray); 
		/**
	     *加载编辑数据
	    */
	   if($scope.editId){
	   	console.log($scope.editId);
	   	editService.loadData($scope, editDataUrl, $scope.editId, function(response){
	   		console.log(response);
	   		$scope.scoreConfigRule = response.scoreConfigRule;
	   		
	   	});
	   }
	   
	   /**
	    *修改
	   */  
	  $scope.update = function(){
		  var data = editService.getPostData($scope);
		  data.scoreConfigRuleList = $scope.scoreConfigRule;
		  console.log($scope.scoreConfigRule);
		  console.log(data);
		  httpService.post($scope,'api/phiScoreTaskConfig/edit/'+ $scope.editId , data).success(function(){
			  
		  });
//	 	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
	  } 
	  
		
	    //定义积分配制
//	    $scope.scoreConfig=0;
	   


	    	
	    /***
	     * 定义更新操作.
	     */
//	    $scope.update = function(){
//	    	editService.updateData($scope,editDataUrl, homeUrl, $scope.editId);
//	    } 
	   /**
	     * 定义保存操作.
	     */
	    $scope.save = function(){
	    	console.log($scope.scoreConfigRule);
	    	console.log($scope.taskTimeType.value);
	    	console.log($scope.startTime);
//	    	console.log($scope.config);
	    	
//	    	editService.saveData($scope,addDataUrl, homeUrl);
	    }
	    
//	    $scope.taskFun = function(val){
//	    	if(val == 0){
//	    		
//	    	}
//	    }
	    
	   
	});
	
});



