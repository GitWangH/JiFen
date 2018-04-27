'use strict';
/***
 * 会员特权管理Controller
 */
angular.module('huatek.controllers').controller('PhiMemberPrivilegeController', function ($scope,$http,$rootScope, listService,httpService,cacheService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '序号', field: 'id',width: '6%', enableColumnMenu: false},
		       { name: '权限名称', field: 'rightName',width: '10%', enableColumnMenu: false},
		       { name: '等级名称', field: 'memberGradeName',width: '10%', enableColumnMenu: false},
		       { name: '权限说明', field: 'rightExplain',width: '22%', enableColumnMenu: false},
		       { name: '权限时间', field: 'rightDeadline',width: '12%', enableColumnMenu: false,decode:{field:'rightDeadline',dataKey:'dic.right_deadline'}},
		       { name: '是否开启', field: 'state1',width: '8%', enableColumnMenu: false,decode:{field:'state',dataKey:'dic.yes_or_no'}},
		       { name: '操作人员', field: 'modifierName',width: '10%', enableColumnMenu: false},
		       { name: '末次操作时间', field: 'modifyTime',width: '11%', enableColumnMenu: false},
		       { name: '操作', field: '',width: '10%', enableColumnMenu: false,cellTemplate:'<div style="color:#F58220;text-align:center"><span style="cursor:pointer;margin-right:10px;" ng-click="grid.appScope.showDetail(row.entity.id,row.entity.privilegeType)">权限详情</span><span style="cursor:pointer;" ng-if="row.entity.state == 0 " ng-click="grid.appScope.editState(row.entity.id,row.entity.state)">任务开启</span><span style="cursor:pointer;" ng-if="row.entity.state == 1" ng-click="grid.appScope.editState(row.entity.id,row.entity.state)">任务关闭</span></div>'}
		      ]
	  };
	
	/**
	 * 会员权限状态设置
	 */
	   $scope.editState = function(id, state) {
		   if(state == 1){
			   var state = 0;
		   }else{
			   var state = 1;
		   }
	        httpService.post($scope, 'api/phiMemberPrivilege/editState/' + id+ '/' + state).success(function() {
	        	load();
	        });
	    }
	   
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope,$http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id asc","false");
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope,queryPage);

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
 
    listService.setButtonList($scope,btnArray);

    $scope.showDetail = function(id,privilegeType) {
    	if(privilegeType == 1){
    		 var pop = {
    		            title: "权限详情",
    		            passParams: id,
    		            onclosedFun: load,
    		            controller: 'PhiMemberPrivilegeDetailController',
    		            templateView: "/static/phicom/member/template/phi_member_for_pay_points_privilege_detail.html",

    		        };
    		        listService.popPanel($scope, pop);
    	}else if(privilegeType == 2){
    		var pop = {
    				title: "权限详情",
    				passParams: id,
    				onclosedFun: load,
    				controller: 'PhiMemberPrivilegeBirthDetailController',
    				templateView: "/static/phicom/member/template/phi_member_birthday_privilege_detail.html",
    				
    		};
    		listService.popPanel($scope, pop);
    	}
        
    }
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){

    	listService.loadData($scope,'api/phiMemberPrivilege/query', $scope.tableGrid);
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
 * 会员消费返积分特权明细Controller
 */
angular.module('huatek.controllers').controller('PhiMemberPrivilegeDetailController', function ($scope, $http, editService, httpService,$rootScope,listService) {
	var addDataUrl = 'api/phiMemberPrivilege/add';
	var editDataUrl = 'api/phiMemberPrivilege/edit';
	var homeUrl = '/phiMemberPrivilege/home';
	$scope.editId = $scope.passParams;
    
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
    $scope.rightName = new FormElement(1, '权限名称', 'rightName', 'string', 1, '30');
    formFieldArray.push($scope.rightName);
    
    $scope.memberGradeName = new FormElement(1, '等级名称', 'memberGradeName', 'string', 1, '30');
    formFieldArray.push($scope.memberGradeName);
    
    $scope.payScoreMultiple = new FormElement(1, '消费返积分倍数', 'doubleSet', 'string', 0, '30');
    formFieldArray.push($scope.payScoreMultiple);
    
    $scope.privilegeType = new FormElement(1, '权限类型', 'privilegeType', 'string', 1, '30',null,null,null,'1');
    formFieldArray.push($scope.privilegeType);
    $scope.privilegeType.isShow = false;
    
    $scope.rightDeadline = new FormElement(1,'权限时间','rightDeadline','string',0,'30');
    $scope.rightDeadline.items = [{"name":"永久", "code":"0"},{"name":"设置时间段", "code":"1"}];
    formFieldArray.push($scope.rightDeadline);
    /*权限时间转换事件*/
    $scope.changeRadio = function(val){
 	   if(val == 0){
 	   		 $scope.startTime.readonly = true;
 	   		 $scope.endTime.readonly = true;
 	   	     $scope.rightDeadline.value = 0;//0表示永久
 	   	     $scope.startTime.value = "";
 	   	     $scope.endTime.value = "";
 	   	 }else{
 	   		 $scope.rightDeadline.value = 1;//1表示设置时间段
 	   		 $scope.startTime.readonly = false;
 	   		 $scope.endTime.readonly = false;
 	   	 }
    }
    
    $scope.startTime = new FormElement(1, '开始时间', 'startTime', 'string', 0, '30','date');
    formFieldArray.push($scope.startTime);
    $scope.endTime = new FormElement(1, '结束时间', 'endTime', 'string', 0, '30','date');
    formFieldArray.push($scope.endTime);
    
    $scope.state = new FormElement(1,'是否开启','state','string',1,'30');
    $scope.state.items = [{"name":"是", "code":"1"}, {"name":"否", "code":"0"}];
    formFieldArray.push($scope.state);
    $scope.rightExplain = new FormElement(1,'权限说明','rightExplain','string',1,'30');
    formFieldArray.push($scope.rightExplain);
    
    
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 

    /**
     * 加载编辑数据
     */
    if($scope.editId){
      	editService.loadData($scope, editDataUrl, $scope.editId,function(response){
      		/*console.log("================");
      		console.log(response);
      		console.log("================");*/
      	});
      }
	
    /**
     * 修改
     **/   
    $scope.update = function(){
    	 if($scope.startTime.value && $scope.startTime.value != null ||$scope.endTime.value && $scope.endTime.value != null ){
   		  $scope.startTime.value = dateFormat(new Date($scope.startTime.value), 'yyyy-MM-dd');
   		  $scope.endTime.value = dateFormat(new Date($scope.endTime.value), 'yyyy-MM-dd');
   	  }else{
   		  $scope.startTime.value = null;
   		  $scope.endTime.value = null;
   	  }
          	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    } 
});


/****
 * 会员生日特权明细Controller
 */
angular.module('huatek.controllers').controller('PhiMemberPrivilegeBirthDetailController', function ($scope, $http, editService, httpService,$rootScope,listService) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiMemberPrivilege/add';
	 var editDataUrl = 'api/phiMemberPrivilege/edit';
	 var homeUrl = '/phiMemberPrivilege/home';
	 $scope.editId = $scope.passParams;
	
  	//定义块
   var columnWrapArray = [];
   columnWrapArray.push(new multipleColumn(1,'基本信息'));
   $scope.columnWrapArray = columnWrapArray;
   var formFieldArray = [];
   /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
   $scope.rightName = new FormElement(1,'权限名称','rightName','string',1,'30');
   formFieldArray.push($scope.rightName);
   $scope.checkIn = new FormElement(1,'签到','checkIn','string',0,'30');
   formFieldArray.push($scope.checkIn);
   $scope.pay = new FormElement(1,'商城消费购物','pay','string',0,'30');
   formFieldArray.push($scope.pay);
   $scope.comment = new FormElement(1,'评论','comment','string',0,'30');
   formFieldArray.push($scope.comment);
   $scope.forum = new FormElement(1,'论坛','forum','string',0,'30');
   formFieldArray.push($scope.forum);
   $scope.privilegeType = new FormElement(1, '权限类型', 'privilegeType', 'string', 1, '30',null,null,null,'2');
   formFieldArray.push($scope.privilegeType);
   $scope.privilegeType.isShow = false;
   $scope.scoreOrMutiply = new FormElement(1, '权限设置', 'scoreOrMutiply', 'string', 0, '30');
   $scope.scoreOrMutiply.items = [{ "name": "翻倍设置", "code": "0" }, { "name": "额外增加", "code": "1" }];
   $scope.scoreOrMutiply.event = function(val) {
     /*  console.log(val);*/
       if (val == 0) {
           $scope.mutiplyRead == true;
           $scope.extraAdd.value = null;
       } else if (val == 1) {
           $scope.scoreRead == true;
           $scope.doubleSet.value = null;
       }
   }
   formFieldArray.push($scope.scoreOrMutiply);

   $scope.doubleSet = new FormElement(1, '翻倍设置', 'doubleSet', 'string', 0, '30');
   formFieldArray.push($scope.doubleSet);
   $scope.extraAdd = new FormElement(1, '额外增加', 'extraAdd', 'string', 0, '30');
   formFieldArray.push($scope.extraAdd);
   $scope.rightDeadline = new FormElement(1,'权限时间','rightDeadline','string',0,'30');
   $scope.rightDeadline.items = [{"name":"永久", "code":"0"},{"name":"设置时间段", "code":"1"}];
   formFieldArray.push($scope.rightDeadline);
   /*权限时间转换事件*/
   $scope.changeRadio = function(val){
	   if(val == 0){
	   		 $scope.startTime.readonly = true;
	   		 $scope.endTime.readonly = true;
	   	     $scope.rightDeadline.value = 0;//0表示永久
	   	     $scope.startTime.value = "";
	   	     $scope.endTime.value = "";
	   	 }else{
	   		 $scope.rightDeadline.value = 1;//1表示设置时间段
	   		 $scope.startTime.readonly = false;
	   		 $scope.endTime.readonly = false;
	   	 }
   }
   
   $scope.startTime = new FormElement(1, '开始时间', 'startTime', 'string', 0, '30','date');
   formFieldArray.push($scope.startTime);
   $scope.endTime = new FormElement(1, '结束时间', 'endTime', 'string', 0, '30','date');
   formFieldArray.push($scope.endTime);
   
   $scope.state = new FormElement(1,'是否开启','state','string',1,'30');
   $scope.state.items = [{"name":"是", "code":"1"}, {"name":"否", "code":"0"}];
   formFieldArray.push($scope.state);
   $scope.rightExplain = new FormElement(1,'权限说明','rightExplain','string',1,'30');
   formFieldArray.push($scope.rightExplain);
 
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
  	editService.loadData($scope, editDataUrl, $scope.editId,function(response){
  		/*console.log(response);*/
  		$scope.checkIn.value = response.checkIn;
  		$scope.pay.value = response.pay;
  		$scope.comment.value = response.comment;
  		$scope.forum.value = response.forum;
  	});
  }
  
  /**
   *修改
   */
  $scope.update = function() {
	  if($scope.startTime.value && $scope.startTime.value != null ||$scope.endTime.value && $scope.endTime.value != null ){
		  $scope.startTime.value = dateFormat(new Date($scope.startTime.value), 'yyyy-MM-dd');
		  $scope.endTime.value = dateFormat(new Date($scope.endTime.value), 'yyyy-MM-dd');
	  }else{
		  $scope.startTime.value = null;
		  $scope.endTime.value = null;
	  }
       	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
  }
});