'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiScoreTaskConfigController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	/***
	 * 定义显示的表格.
	 */
    $scope.tableGrid = {
            paginationPageSizes: [10, 25, 50, 100],
            paginationPageSize: 10,
            useExternalPagination: true,
            multiSelect: true,
		    columnDefs: [
		       { name: '序号', field: 'code',width: '10%', enableColumnMenu: false},
		       { name: '任务项', field: 'taskName',width: '10%', enableColumnMenu: false},
		       { name: '任务前台名称', field: 'showName',width: '10%', enableColumnMenu: false},
		       { name: '任务说明', field: 'taskRemark',width: '10%', enableColumnMenu: false},
		       { name: '任务时间', field: 'time',width: '10%', enableColumnMenu: false},
		       { name: '是否开启', field: 'MytaskSwitch',width: '10%', enableColumnMenu: false,decode: { field: "taskSwitch", dataKey: "dic.task_switch" }},
		       { name: '操作人员', field: 'operationPerson',width: '10%', enableColumnMenu: false},
		       { name: '末次操作时间', field: 'lastOperationTime',width: '10%', enableColumnMenu: false},
 	           /*{ name: '操作', field: 'operate',width: '20%', enableColumnMenu: false,cellTemplate:'<a style="cursor:pointer"><span ng-click="grid.appScope.edit(row.entity.id,row.entity.taskType)">编辑&nbsp;&nbsp;&nbsp;</span><span ng-if="row.entity.taskSwitch==\'off\'" ng-click="grid.appScope.on(row.entity.id,row.entity.taskSwitch)">任务开启</span><span ng-if="row.entity.taskSwitch==\'on\'" ng-click="grid.appScope.off(row.entity.id,row.entity.taskSwitch)">任务关闭</span></a>'}*/
		       { name: '操作', field: '',width: '20%', enableColumnMenu: false,cellTemplate:'<a style="cursor:pointer"><span ng-click="grid.appScope.edit(row.entity.id,row.entity.taskType)">编辑&nbsp;&nbsp;&nbsp;</span><span ng-if="row.entity.taskSwitch==\'off\'" ng-click="grid.appScope.changeState(row.entity.id,row.entity.taskSwitch)">任务开启</span><span ng-if="row.entity.taskSwitch==\'on\'" ng-click="grid.appScope.changeState(row.entity.id,row.entity.taskSwitch)">任务关闭</span></a>'}
		    ]
	  };
    /*$scope.on = function(id, Switch) {
    	var taskSwitch = "on"
        httpService.post($scope, 'api/phiScoreTaskConfig/switch/' + id+ '/' + taskSwitch, data).success(function() {
        	load();
        });
    }
    
    $scope.off = function(id, Switch) {
    	var taskSwitch = "off"
        httpService.post($scope, 'api/phiScoreTaskConfig/switch/' + id+ '/' + taskSwitch, data).success(function() {
        	load();
        });
    }*/
    
    $scope.changeState = function(id, taskSwitch) {
    	 if(taskSwitch == 'on'){
			   var taskSwitch = 'off';
		   }else{
			   var taskSwitch = 'on';
		   }
        httpService.post($scope, 'api/phiScoreTaskConfig/switch/' + id+ '/' + taskSwitch).success(function() {
        	load();
        });
    }
    
    $scope.edit = function(id,taskType) {
        var scope = $rootScope.$new();
        var map = {'id' : id,'type' : taskType};
        var forPayPoints = {
            title: "消费 ",
            passParams: map,
            onclosedFun: load,
            controller: 'PhiScoreTaskAddController',
            templateView: "/static/phicom/score/template_add.html",
//            /static/phicom/score/inner_template_add.html
        };
        var forCheckinPoints = {
                title: "签到 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/sign.html",
            };

        var forAppraisePoints = {
                title: " 评论",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/comments.html",
            };
        var forMInfoPoints = {
                title: "个人资料 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/forMInfoPoints.html",
            };
        var forAuthPoints = {
                title: "实名认证 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/forAuthPoints.html",
            };
        var forBindPoints = {
                title: "账号绑定 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/forBindPoints.html",
            };
        var forum = {
                title: "论坛活动 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/forum.html",
            };
        var forSharePoints = {
                title: "商城分享 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/forSharePoints.html",
            };
        var forInviteePoints = {
                title: "邀请注册 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/forInviteePoints.html",
            };
        var register = {
                title: "注册得积分 ",
                passParams: map,
                onclosedFun: load,
                controller: 'PhiScoreTaskAddController',
                templateView: "/static/phicom/score/register.html",
            };
        switch(taskType) {
        case "forPayPoints" :
        	listService.popPanel($scope, forPayPoints);
        	break;
        case "forCheckinPoints" :
        	listService.popPanel($scope, forCheckinPoints);
        	break;
        case "forAppraisePoints" :
        	listService.popPanel($scope, forAppraisePoints);
        	break;
        case "forMInfoPoints" :
        	listService.popPanel($scope, forMInfoPoints);
        	break;
        case "forAuthPoints" :
        	listService.popPanel($scope, forAuthPoints);
        	break;
        case "forBindPoints" :
        	listService.popPanel($scope, forBindPoints);
        	break;	
        case "forum" :
        	listService.popPanel($scope, forum);
        	break;
        case "forSharePoints" :
        	listService.popPanel($scope, forSharePoints);
        	break;
        case "forInviteePoints" :
        	listService.popPanel($scope, forInviteePoints);
        	break;
        case "register" :
        	listService.popPanel($scope, register);
        	break;	
        default:
        	break;
        	
        }
    }
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
    listService.init($scope);
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
	      /*调整grid底部高度*/
	        listService.setGridHeight();
	  	}; 
    

	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
   
    	listService.loadData($scope, 'api/phiScoreTaskConfig/backquery', $scope.tableGrid);
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
angular.module('huatek.controllers').controller('PhiScoreTaskAddController', function ($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiScoreTaskConfig/add';
	 var editDataUrl = 'api/phiScoreTaskConfig/edit';
	 var homeUrl = '/phiScoreTaskConfig/home';
	 $scope.editId = $scope.passParams.id;
	 $scope.type = $scope.passParams.type;
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    var formFieldArray = [];
    $scope.thirdAccount = [];
    //存放数组
    formFieldArray.push($scope.thirdAccount);
    
    $scope.taskName = new FormElement(1,'任务项','taskName','string',1,'30');
    formFieldArray.push($scope.taskName);
    var taskType = new FormElement(1,'任务类型','taskType','string',0,'30');
    formFieldArray.push(taskType);
    $scope.id = new FormElement(1,'任务项','id','string',0,'30');
    formFieldArray.push($scope.id);
    $scope.showName = new FormElement(1,'任务前台名称','showName','string',1,'30');
    formFieldArray.push($scope.showName);
    $scope.taskTimeType = new FormElement(1,'时间类型','taskTimeType','string',0,'30');
    formFieldArray.push($scope.taskTimeType);
    $scope.isRead=false;
    $scope.ruleTimeType = new FormElement(1,'任务时间','ruleTimeType','Date',0,'30');
    $scope.ruleTimeType.items = [{"name":"永久", "code":"1"},{"name":"设置时间段", "code":"2"}];
    formFieldArray.push($scope.ruleTimeType);
    $scope.ruleTimeType.event = function(val){
    	console.log(val);
    	$scope.scoreConfigRule[0].startTime = null;
    	$scope.scoreConfigRule[0].endTime = null;
    	$scope.taskTimeType = 1;//1表示永久
    	 
    	 if(val ==1){
    		 $scope.isRead=true; 
    	 }
    }
    $scope.fromtime = new FormElement(1,'设置时间','fromtime','String',0,'30');
    formFieldArray.push($scope.fromtime);
    $scope.fromtime.event = function(val){
    	 if(val ==1){
    		 $scope.isRead=true; 
    		 $scope.scoreConfigRule[0].startTime = null;
    	     $scope.scoreConfigRule[0].endTime = null;
    	     $scope.taskTimeType = 1;//1表示永久
    	 }else{
    		 $scope.isRead=false; 
    		 $scope.taskTimeType = 2;//1表示永久
    	 }
    }
    
    $scope.startTime = new FormElement(1, '开始时间', 'phiScoreRule.startTime', 'string', null, '100','date');
    $scope.startTime.value = getDate(getNowFormatDate());
    formFieldArray.push($scope.startTime);
    $scope.endTime = new FormElement(1, '结束时间', 'phiScoreRule.endTime', 'string', null, '100','date');
    $scope.endTime.value = getDate(getNowFormatDate());
    formFieldArray.push($scope.endTime);
    $scope.taskSwitch = new FormElement(1,'是否开启','taskSwitch','string',1,'30');
    $scope.taskSwitch.items = [{"name":"是", "code":"on"}, {"name":"否", "code":"off"}];
    formFieldArray.push($scope.taskSwitch);
    $scope.taskRemark = new FormElement(1,'任务说明','taskRemark','string',1,'30');
    formFieldArray.push($scope.taskRemark);
    $scope.src = new FormElement(1,'app链接地址','src','string',1,'30');
    formFieldArray.push($scope.src);
    $scope.pcsrc = new FormElement(1,'pc链接地址','pcSrc','string',1,'30');
    formFieldArray.push($scope.pcsrc); 
    switch($scope.type) {
    case "forPayPoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forPayPoints";
	     formFieldArray.push($scope.image);
    	break;
    case "forCheckinPoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forCheckinPoints";
	     formFieldArray.push($scope.image);
    	break;
    case "forAppraisePoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forAppraisePoints";
	     formFieldArray.push($scope.image);
    	break;
    case "forMInfoPoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forMInfoPoints";
	     formFieldArray.push($scope.image);
    	break;
    case "forAuthPoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forAuthPoints";
	     formFieldArray.push($scope.image);
    	break;
    case "forBindPoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forBindPoints";
	     formFieldArray.push($scope.image);
    	break;	
    case "forum" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forum";
	     formFieldArray.push($scope.image);
    	break;
    case "forSharePoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forSharePoints";
	     formFieldArray.push($scope.image);
    	break;
    case "forInviteePoints" :
    	 $scope.image = new FormElement(1, '图片', 'image', 'string', '', '36', 'fileMultiple');
	     $scope.image.systemHeader = "/forInviteePoints";
	     formFieldArray.push($scope.image);
    	break;	
    default:
    	break;
    }
    $scope.extensing = new FormElement(1, '配置类型', 'phiScoreRule.extensing', 'string', null, '100','date');
//    $scope.weibo = new FormElement(1,'weibo','weibo','string',0,'30');
//    formFieldArray.push($scope.weibo);
//    $scope.wechat = new FormElement(1,'wechat','wechat','string',0,'30');
//    formFieldArray.push($scope.wechat);
//    $scope.qq = new FormElement(1,'wechat','qq','string',0,'30');
//    formFieldArray.push($scope.qq);
    
//    $scope.manySelect = new FormElement(1,'manySelect','','string',0,'30');
//    $scope.manySelect.event = function(val){
//    	console.log(val);
//    }
    
    
  //设置全局变量
//    $scope.formFieldArrayList = formFieldArray;
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
   		console.log("================");
   		console.log(response);
   		console.log("================");
   		$scope.taskType = response.taskType;
   		$scope.scoreConfigRule = response.scoreConfigRule;
//   		$scope.scoreConfigRuleList = response.scoreConfigRuleList;
   		console.log(response.thirdAccount);
   		if(!response.thirdAccount){
   			$scope.thirdAccount = [];
   		}else {
   			$scope.thirdAccount = response.thirdAccount;
   		}
   		console.log(response.taskType);
   		if( $scope.taskType == "forMInfoPoints"){
   		 $scope.thirdAccoutCheckItem = [
      	    {
      	    	id: 'icon',
      	    	name: '头像'
      	    },
      	    {
      	    	id: 'nickname',
      	    	name: '昵称'
      	    },
      	    {
      	    	id: 'gender',
      	    	name: '性别'
      	    },
      	    {
      	    	id: 'birthday',
      	    	name: '生日'
      	    }
      	 ]
   	 }else {
   		 $scope.thirdAccoutCheckItem = [
      	    {
      	    	id: 'wechat',
      	    	name: '微信'
      	    },
      	    {
      	    	id: 'qq',
      	    	name: 'QQ'
      	    },
      	    {
      	    	id: 'weibo',
      	    	name: '微博'
      	    }
      	 ]
   	 }
   		if($scope.taskType == "forAuthPoints"){
   			$scope.thirdAccount = ['identity'];
   		}
   		
   	});
   }
   
   /**
    *修改
   */   
  $scope.update = function(){
	  var data = editService.getPostData($scope);
	  data.scoreConfigRule = $scope.scoreConfigRule;
	  console.log(data.scoreConfigRule);
	  data.thirdAccount = $scope.thirdAccount;
	  for(var i= 0;i< data.scoreConfigRule.length;i++){
		  if(data.scoreConfigRule[i].startTime!=null||data.scoreConfigRule[i].endTime){
			  var start = dateFormat(new Date(data.scoreConfigRule[i].startTime),"yyyy-MM-dd");
				 var end =  dateFormat(new Date(data.scoreConfigRule[i].endTime),"yyyy-MM-dd");
				 data.scoreConfigRule[i].startTime=start;
				 data.scoreConfigRule[i].endTime = end;
		  }

	  }
	  console.log(data.thirdAccount)
	  console.log(data.scoreConfigRule);
	  httpService.post($scope,'api/phiScoreTaskConfig/edit/'+ $scope.editId , data).success(function(){
		  //	关闭弹框
		  $scope["jsPanel"].close();
	  });
// 	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
  } 
  
	
    //定义积分配制
//    $scope.scoreConfig=0;
   
  	var updateSelected = function(action,id,name){ 
  		 $scope.thirdAccount;
	    if(action == 'add' && $scope.thirdAccount.indexOf(id) == -1){ 
	      $scope.thirdAccount.push(id); 
	    } 
	    if(action == 'remove' && $scope.thirdAccount.indexOf(id)!=-1){ 
	      var idx = $scope.thirdAccount.indexOf(id); 
	      $scope.thirdAccount.splice(idx,1); 
	    } 
	  } 
	  
	  $scope.updateSelection = function($event, id){ 
	    var checkbox = $event.target; 
	    var action = (checkbox.checked?'add':'remove'); 
	    updateSelected(action,id,checkbox.name); 
	  } 
	  
	  $scope.isSelected = function(id){ 
	    return $scope.thirdAccount.indexOf(id)>=0; 
	  } 

    	
    /**
     * 定义保存操作.
     */
    $scope.save = function(){
    	console.log($scope.thirdAccount);
//    	console.log($scope.scoreConfigRule);
//    	console.log($scope.taskTimeType.value);
//    	console.log($scope.startTime);
//    	console.log($scope.config);
    	
//    	editService.saveData($scope,addDataUrl, homeUrl);
    }
    
//    $scope.taskFun = function(val){
//    	if(val == 0){
//    		
//    	}
//    }
    
   
});
   
