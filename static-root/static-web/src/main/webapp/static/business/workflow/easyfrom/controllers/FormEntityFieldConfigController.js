'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('FormEntityFieldConfigController', function ($scope, $location,$routeParams, $http,$rootScope, listService) {
	$scope.entityId = $routeParams.entityId;
	var codeUrl="api_workflow/formEntityFieldConfig/code";
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: false,
		    groups:["typeName"],
		    columnDefs: [
		       { name: '类型', field: 'typeName',width: '10%', enableColumnMenu: false},
		       { name: '代码', field: 'code',width: '20%', enableColumnMenu: false},
		       { name: '长度', field: 'length',width: '10%', enableColumnMenu: false},
		       { name: '精度', field: 'precision',width: '10%', enableColumnMenu: false},
		       { name: '是否可空', field: 'nullableName',width: '10%', enableColumnMenu: false},
		       { name: '是否主键', field: 'primaryName',width: '10%', enableColumnMenu: false},
		       { name: '备注', field: 'comment',width: '30%', enableColumnMenu: false}
		    ],
		    
		    
	  };
	 
	$http.get("api_workflow/formEntityConfig/query/"+$scope.entityId).success(function(response){
		$scope.entity=response;
	});
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    
    /*定义搜索框*/
    
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
	     
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
        btnArray.push(new pageButton('添加','add','/formEntityFieldConfig/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/formEntityFieldConfig/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/formEntityFieldConfig/delete','deleteData'));
        btnArray.push(new pageButton('返回','back','/formEntityConfig/home','goBack'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    		if(operation=='goBack'){
    			$location.path("/formEntityConfig/home");
    			return;
    		}
    		if($scope.entity&&$scope.entity.isSystem){
    			bootbox.alert('警告：该表是系统表，不能维护');
    			return;
    		}
    		if(operation=='addData'){
        		listService.addData('/formEntityFieldConfig/add/'+$scope.entityId);
	        }
      	  	else if(operation=='editData'){
        		listService.editData($scope.gridApi, '/formEntityFieldConfig/edit/'+$scope.entityId);
       	  	}
	       	else if(operation=='deleteData'){
        		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_workflow/formEntityFieldConfig/delete/'+$scope.entityId);
      		}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	$http.post('api_workflow/formEntityFieldConfig/query/'+$scope.entityId)
		   .success(function (response){
			   if(response){
				   $scope.tableGrid.data=response;
			   }else{
				   $scope.tableGrid.data=[];
			   }
		   });
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
angular.module('HuatekApp').controller('FormEntityFieldConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	$scope.entityId=$routeParams.entityId;
	 $scope.editId = $routeParams.id;
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api_workflow/formEntityFieldConfig/add/'+$scope.entityId;
	 var editDataUrl = 'api_workflow/formEntityFieldConfig/edit/'+$scope.entityId;
	 var homeUrl = '/formEntityFieldConfig/home/'+$scope.entityId;
	 var codeUrl = 'api_workflow/formEntityFieldConfig/code';
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'代码','code','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'类型','type','string',1,'30',"select"));
		  formFieldArray.push(new FormElement(1,'长度','length','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'精度','precision','string',1,'30'));
		  var formElement=new FormElement(1,'是否可空','nullable','boolean',1,'30',"radio");
		  formElement.items=[{code:"true",name:'是'},{code:"false",name:'否'}]
		  formFieldArray.push(formElement);
		  var formElement=new FormElement(1,'是否主键','primary','boolean',1,'30',"radio");
		  formElement.items=[{code:"true",name:'是'},{code:"false",name:'否'}]
		  formFieldArray.push(formElement);
		  formFieldArray.push(new FormElement(1,'备注','comment','string',1,'30'));
    
    
    /*设置全局变量*/
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
    editService.initParams(codeUrl);
    /***
     * 定义获取需要编辑的内容.
     */
   
    
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