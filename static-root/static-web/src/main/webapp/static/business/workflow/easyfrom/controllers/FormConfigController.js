'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('FormConfigController', function ($scope, $location, $http,$rootScope, listService,$modal) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
               { name: '名称', field: 'name',width: '10%', enableColumnMenu: false},
  		       { name: '业务编号', field: 'busiCode',width: '10%', enableColumnMenu: false},
  		       { name: '表单类型', field: 'formTypeName',width: '10%', enableColumnMenu: false},
  		       { name: '界面模版', field: 'template',width: '25%', enableColumnMenu: false},
  		       { name: '控制器', field: 'controller',width: '25%', enableColumnMenu: false},
  		       { name: '表单实体', field: 'formEntityConfigName',width: '10%', enableColumnMenu: false}
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);
    
    /*定义查询页*/
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    
    /*定义搜索框*/
    queryPage.addParam(new queryParam('表单名称','name','string','like'));
    queryPage.addParam(new queryParam('业务编码','busiCode','string','like'));
    
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
    
    
    /*定义功能按钮*/
    var btnArray = [];
        btnArray.push(new pageButton('添加','add','/formConfig/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/formConfig/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/formConfig/delete','deleteData'));
        btnArray.push(new pageButton('控件组配置','editFieldGroup','/formFieldConfig/group','editFieldGroupData'));
        btnArray.push(new pageButton('控件配置','editField','/formFieldConfig/home','editFieldData'));
        btnArray.push(new pageButton('流程变量配置','editVariable','/formWorkflowVariables/home','editVariableData'));
        
        /*btnArray.push(new pageButton('菜单配置','editMenu','/formConfig/menu','editMenuData'));*/
        btnArray.push(new pageButton('预览','preview','/formConfig/menu','preview'));
        btnArray.push(new pageButton('表单复制','copy','/formConfig/add','copyForm'));
        
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
        if(operation=='addData'){
    		listService.addData('/formConfig/add');
        }
  	  	else if(operation=='editData'){
    		listService.editData($scope.gridApi, '/formConfig/edit');
   	  	}
       	else if(operation=='deleteData'){
    		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_workflow/formConfig/delete');
  		}else if(operation=='editFieldData'){
    		listService.editData($scope.gridApi, '/formFieldConfig/home');
  		}else if(operation=='editVariableData'){
    		listService.editData($scope.gridApi, '/formWorkflowVariables/home');
  		}else if(operation=='editFieldGroupData'){
  			listService.editData($scope.gridApi, '/formFieldConfig/group');
  		}else if(operation=='editMenuData'){
  			listService.editData($scope.gridApi, '/formConfig/menu');
  		}else if(operation=='preview'){
  			var selectData = listService.returnSectData($scope.gridApi);
  	    	if(selectData.length>1){
  	    		bootbox.alert('警告：不能选择多条数据。');
  	    		return;
  	    	}
  	    	if(selectData.length==0){
  	    		bootbox.alert('请在列表中选择一条数据。');
  	    		return;
  	    	}
  	    	var scope = $rootScope.$new();
			scope.busiCode=selectData[0].busiCode;
            
			var modal= $modal({
    		     title: selectData[0].name, 
    		     show: false,
    		      controller: "EasyFormPreviewController",
    		     template: TemplatePrefix+"workflow/easyformTemplate/template_preview.html"+'?t='+huatek.version,
    		     scope:scope
    		 });
			modal.$promise.then(modal.show)
  		}else if(operation=='copyForm'){
  			var selectData = listService.returnSectData($scope.gridApi);
  			if(selectData.length>1){
  	    		bootbox.alert('警告：不能选择多条数据。');
  	    		return;
  	    	}
  	    	if(selectData.length==0){
  	    		bootbox.alert('请在列表中选择一条数据。');
  	    		return;
  	    	}
  	    	var formName = prompt("请输入表单名称:","新"+selectData[0].name);
  	    	$scope.promise=$http.post("api_workflow/formConfig/copy/"+selectData[0].id,{name:formName}).success(function(response){
  	    		$scope.search();
  	    	});
  		}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData('api_workflow/formConfig/query', $scope.tableGrid);
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
angular.module('HuatekApp').controller('FormConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api_workflow/formConfig/add';
	 var editDataUrl = 'api_workflow/formConfig/edit';
	 var homeUrl = '/formConfig/home';
	 var codeUrl='api_workflow/formConfig/code';
	
   	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
  /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'表单名称','name','string',1,'30'));
		  var busiCodeForm=new FormElement(1,'业务编码','busiCode','string',0,'30');
		  busiCodeForm.readonly=true;
		  formFieldArray.push(busiCodeForm);
		  formFieldArray.push(new FormElement(1,'表单类型','formTypeId','string',1,'30',"select","typeChange"));
		  formFieldArray.push(new FormElement(1,'界面模版','template','string',1,'128'));
		  formFieldArray.push(new FormElement(1,'控制器','controller','string',1,'128'));
		  formFieldArray.push(new FormElement(1,'表单实体','formEntityConfigId','string',0,'128',"select"));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;
    $rootScope.typeChange=function(value){
    	var items=editService.getFieldMap().get("formTypeId").items;
    	for(var i=0;i<items.length;i++){
    		if(items[i].code==value){
    			if(items[i].desc&&items[i].desc.length>3){
    				var arr=items[i].desc.split("|");
        			editService.getFieldMap().get("template").value=arr[0];
        			editService.getFieldMap().get("controller").value=arr[1];
    			}
    			return;
    		}
    	}
    }
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
angular.module('HuatekApp').controller('FormConfigMenuController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var loadMenu = 'api_workflow/formConfig/add';
	 var editDataUrl = 'api_workflow/formConfig/edit';
	 var homeUrl = '/formConfig/home';
	 var codeUrl='api_workflow/formConfig/code';
	
  	
    /*定义块*/
   var columnWrapArray = [];
   columnWrapArray.push(new multipleColumn(1,'基本信息'));
   $scope.columnWrapArray = columnWrapArray;
   
	
  /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
   var formFieldArray = [];
	  formFieldArray.push(new FormElement(1,'表单名称','name','string',1,'30'));
	  var busiCodeForm=new FormElement(1,'业务编码','busiCode','string',0,'30');
	  busiCodeForm.readonly=true;
	  formFieldArray.push(busiCodeForm);
	  formFieldArray.push(new FormElement(1,'表单类型','formTypeId','string',1,'30',"select","typeChange"));
	  formFieldArray.push(new FormElement(1,'界面模版','template','string',1,'128'));
	  formFieldArray.push(new FormElement(1,'控制器','controller','string',1,'128'));
	  formFieldArray.push(new FormElement(1,'表单实体','formEntityConfigId','string',0,'128',"select"));

   
   /*设置全局变量*/
   $rootScope.formFieldArray = formFieldArray;
   $rootScope.typeChange=function(value){
   	var items=editService.getFieldMap().get("formTypeId").items;
   	for(var i=0;i<items.length;i++){
   		if(items[i].code==value){
   			if(items[i].desc&&items[i].desc.length>3){
   				var arr=items[i].desc.split("|");
       			editService.getFieldMap().get("template").value=arr[0];
       			editService.getFieldMap().get("controller").value=arr[1];
   			}
   			return;
   		}
   	}
   }
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