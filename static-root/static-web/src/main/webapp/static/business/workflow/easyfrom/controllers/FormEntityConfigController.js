'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('FormEntityConfigController', function ($scope, $location, $http,$rootScope, listService) {
	var codeUrl="api_workflow/formEntityConfig/code";
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '系统类型', field: 'systemName',width: '10%', enableColumnMenu: false},
		       { name: '名称', field: 'name',width: '30%', enableColumnMenu: false},
		       { name: '实体', field: 'entity',width: '40%', enableColumnMenu: false},
		       { name: '是否已创建', field: 'created',width: '10%', enableColumnMenu: false},
		       { name: '是否系统表', field: 'isSystem',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    var param=new queryParam('系统类型','systemCode','string','=');
    param.componentType='select';
    
    /*定义搜索框*/
    queryPage.addParam(param);
    queryPage.addParam(new queryParam('名称','name','string','like'));
    queryPage.addParam(new queryParam('实体','entity','string','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage(queryPage);
    listService.initQueryParams(codeUrl);
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
    
    
    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('添加','add','/formEntityConfig/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/formEntityConfig/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/formEntityConfig/delete','deleteData'));
        btnArray.push(new pageButton('实体字段','editField','/formEntityFieldConfig/home','editField'));
        btnArray.push(new pageButton('创建表','createTable','/formEntityFieldConfig/createTable','createTable'));
        
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	 	if(operation=='addData'){
    	 		listService.addData('/formEntityConfig/add');
    	  	}else if(operation=='editData'){
    	  		var selectData = listService.returnSectData($scope.gridApi);
      	    	if(selectData.length>1){
      	    		submitTips('警告：不能选择多条数据用于编辑。','warning');
      	    		return;
      	    	}
      	    	if(selectData.length==0){
      	    		submitTips('请在列表中选择一条用于编辑的数据。','warning');
      	    		return;
      	    	}
      	    	if(selectData[0].isSystem){
      	    		submitTips('该表是系统表，不能编辑','warning');
      	    		return;
      	    	}
        		listService.editData($scope.gridApi, '/formEntityConfig/edit');
       	  	}
	       	else if(operation=='deleteData'){
	       		var selectData = listService.returnSectData($scope.gridApi);
      	    	if(selectData.length==0){
      	    		submitTips('请在列表中选择一条用于删除的数据。','warning');
      	    		return;
      	    	}
      	    	for(var i=0;i<selectData.length;i++){
      	    		if(selectData[i].isSystem){
          	    		submitTips('不能删除系统表！','warning');
          	    		return;
          	    	}
      	    	}
      	    	
        		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_workflow/formEntityConfig/delete');
      		}
	       	else if(operation=='editField'){
	       		var selectData = listService.returnSectData($scope.gridApi);
      	    	if(selectData.length>1){
      	    		submitTips('警告：不能选择多条数据用于编辑。','warning');
      	    		return;
      	    	}
      	    	if(selectData.length==0){
      	    		submitTips('请在列表中选择一条用于编辑的数据。','warning');
      	    		return;
      	    	}

/*      	    	if(selectData[0].isSystem){*/

/*      	    		bootbox.alert('该表是系统表，不能编辑属性');*/

/*      	    		return;*/

/*      	    	}*/
        		listService.editData($scope.gridApi, '/formEntityFieldConfig/home');
      		}else if(operation=='createTable'){
      			var selectData = listService.returnSectData($scope.gridApi);
      	    	if(selectData.length>1){
      	    		submitTips('警告：不能选择多条数据用于创建表。','warning');
      	    		return;
      	    	}
      	    	if(selectData.length==0){
      	    		submitTips('请在列表中选择一条用于创建表的数据。','warning');
      	    		return;
      	    	}
      	    	if(selectData[0].create){
      	    		submitTips('该记录的表已创建，请选择其他数据,若修改了属性,请联系管理员进行修改!','warning');
      	    		return;
      	    	}
        		$scope.promise=$http.post("api_workflow/formEntityConfig/createTable/"+selectData[0].id).success(function(){
        			load();
        		});
      		}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData('api_workflow/formEntityConfig/query', $scope.tableGrid);
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
angular.module('HuatekApp').controller('FormEntityConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api_workflow/formEntityConfig/add';
	 var editDataUrl = 'api_workflow/formEntityConfig/edit';
	 var homeUrl = '/formEntityConfig/home';
	 var codeUrl="api_workflow/formEntityConfig/code";
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
  /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'系统类型','systemCode','string',1,'30','select'));
		  formFieldArray.push(new FormElement(1,'名称','name','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'实体','entity','string',1,'30'));
    
    
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