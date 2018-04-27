'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiMemberGradeController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
			  paginationPageSizes: [10, 25, 50, 100],
	            paginationPageSize: 10,
	            useExternalPagination: true,
	            multiSelect: true,
		    columnDefs: [
		       { name: '会员等级id', field: 'id',width: '15%', enableColumnMenu: false},
		       { name: '等级名称', field: 'memberGrade',width: '16%', enableColumnMenu: false,decode: { field: "memberGrade", dataKey: "dic.member_grade" }},
		       //{ name: '创建时间', field: 'createTime',width: '10%', enableColumnMenu: false},
		       //{ name: '描述', field: 'remark',width: '10%', enableColumnMenu: false},
		       { name: 'Icon', field: 'image',width: '20%', enableColumnMenu: false},
		       { name: '积分下限', field: 'scoreMin',width: '16%', enableColumnMenu: false},
		       { name:'满足条件',field: 'conditionsMeet',width: '16%', enableColumnMenu: false},
		       { name: '积分上限', field: 'scoreMax',width: '16%', enableColumnMenu: false},
		       //{ name: '是否有效(1,有效、0，无效)', field: 'validState',width: '10%', enableColumnMenu: false},
		      // { name: '是否有效', field: 'validState',width: '10%', enableColumnMenu: false,decode: { field: "validState", dataKey: "dic.grade_validState" }},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id asc","false");
    
/*    //定义搜索框
   // queryPage.addParam(new queryParam('会员等级的code，全局唯一','memberGradeCode','string','like'));
    var memberGrade = new queryParam('会员等级','memberGrade','string','=',null,"dic.member_grade");
    memberGrade.componentType = 'select';
    queryPage.addParam(memberGrade);
    //queryPage.addParam(new queryParam('会员等级','memberGrade','string','like'));
    //queryPage.addParam(new queryParam('是否有效(1,有效、0，无效)','validState','string','like'));
    var validState = new queryParam('是否有效','validState','string','=',null,"dic.grade_validState");
    validState.componentType = 'select';
    queryPage.addParam(validState);*/
    
    $rootScope.searchCount = queryPage.queryParamList.length;
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
        btnArray.push(new pageButton('新增','add','/phiMemberGrade/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/phiMemberGrade/edit','editData'));
        //btnArray.push(new pageButton('删除', 'delete', '/phiMemberGrade/delete', 'deleteData'));
        listService.setButtonList($scope,btnArray);
   
    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation == 'addData'){
    		 listService.addData($scope, new popup("新增", '/phiMemberGrade/add', $scope.treeParam));
    	} else if(operation == 'editData'){
    		listService.editData($scope, $scope.gridApi, new popup("编辑", '/phiMemberGrade/edit'));
    	}/*else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/phiMemberGrade/delete');
        } */
    }
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope,'api/phiMemberGrade/query', $scope.tableGrid);
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
angular.module('huatek.controllers').controller('PhiMemberGradeAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope,httpService) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/phiMemberGrade/add';
	 var editDataUrl = 'api/phiMemberGrade/edit';
	 var homeUrl = '/phiMemberGrade/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  //formFieldArray.push(new FormElement(1,'会员等级id','memberGradeId','string',1,'30'));
    	/*$scope.random=Math.ceil(Math.random()*10);
    	  var gradeCode = new FormElement(1,'会员等级的code，全局唯一','memberGradeCode','string',1,'30');*/
    	  
		 formFieldArray.push(new FormElement(1,'会员等级的code，全局唯一','memberGradeCode','string',1,'30'));
		 formFieldArray.push(new FormElement(1,'会员等级','memberGrade','string',1,'30','select', null, null, null, 'dic.member_grade'));
		  var image = new FormElement(1,'图标','image','string','','36','fileMultiple');
		  image.systemHeader = "/root/dd";
		  formFieldArray.push(image);
		  //formFieldArray.push(new FormElement(1,'描述','remark','string',1,'128','textarea'));
		  formFieldArray.push(new FormElement(1,'积分下限','scoreMin','number',1,'30'));
		  formFieldArray.push(new FormElement(1,'积分上限','scoreMax','number',1,'30'));
		  
		  //formFieldArray.push(new FormElement(1,'是否有效(1,有效、0，无效)','validState','string',1,'30','select', null, null, null, 'dic.grade_validState'));
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
    	editService.loadData($scope, editDataUrl, $scope.editId);
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
    	editService.saveData($scope, addDataUrl, homeUrl);
    }
});

