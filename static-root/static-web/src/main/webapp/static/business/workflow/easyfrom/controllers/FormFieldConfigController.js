'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('FormFieldConfigController', function ($scope, $location, $routeParams,$http,$rootScope, listService) {
	$scope.formId = $routeParams.formId;
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 100,
		    useExternalPagination: false,
		    columnDefs: [
		       { name: '名称', field: 'name',width: '10%', enableColumnMenu: false},
		       { name: '代码', field: 'code',width: '10%', enableColumnMenu: false},
		       { name: '数据类型', field: 'typeName',width: '10%', enableColumnMenu: false},
		       { name: '长度', field: 'length',width: '10%', enableColumnMenu: false},
		       { name: '必输项', field: 'require',width: '10%', enableColumnMenu: false},
		       { name: '只读', field: 'readonly',width: '10%', enableColumnMenu: false},
		       { name: '控件组', field: 'formFieldGroupName',width: '10%', enableColumnMenu: false},
		       { name: '控件类型', field: 'modelName',width: '10%', enableColumnMenu: false},
		       { name: '下拉类型', field: 'dropTypeName',width: '10%', enableColumnMenu: false},
		       { name: '下拉实现', field: 'dropImpl',width: '10%', enableColumnMenu: false},
		       { name: '排序号', field: 'orderNo',width: '10%', enableColumnMenu: false}
		    ]
		    
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $location, $http);

    
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	     
	  	}; 
    
    
    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('添加','add','/formFieldConfig/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/formFieldConfig/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/formFieldConfig/delete','deleteData'));
        btnArray.push(new pageButton('返回','back','/formConfig/home','goBack'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList(btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	 	if(operation=='addData'){
    	 		listService.addData('/formFieldConfig/add/'+$scope.formId);
    	  	}else if(operation=='editData'){
        		listService.editData($scope.gridApi, '/formFieldConfig/edit/'+$scope.formId);
       	  	}
	       	else if(operation=='deleteData'){
        		listService.deleteData($scope.tableGrid, $scope.gridApi, 'api_workflow/formFieldConfig/delete');
      		}
	       	else if(operation=='goBack'){
	       		$location.path("/formConfig/home");
      		}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	$http.post('api_workflow/formFieldConfig/query/'+$scope.formId)
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
angular.module('HuatekApp').controller('FormFieldConfigAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
    $scope.editId = $routeParams.id;
    $scope.formId = $routeParams.formId;
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api_workflow/formFieldConfig/add/'+$scope.formId;
	 var editDataUrl = 'api_workflow/formFieldConfig/edit/'+$scope.formId;
	 var homeUrl = '/formFieldConfig/home/'+$scope.formId;
	 var codeUrl='api_workflow/formFieldConfig/code/'+$scope.formId;
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'从实体中选择'));
    columnWrapArray.push(new multipleColumn(2,'控件属性'));
    columnWrapArray.push(new multipleColumn(3,'控件值'));
    columnWrapArray.push(new multipleColumn(4,'排序'));
    $scope.columnWrapArray = columnWrapArray;
    $http.get("api_workflow/formEntityFieldConfig/loadByForm/"+$scope.formId).success(function(response){
    	$scope.entityFields=response;
    });
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
	     formFieldArray.push(new FormElement(1,'实体字段','entityField','string',0,'128','select','entityFieldChange'));
		  formFieldArray.push(new FormElement(2,'名称','name','string',1,'30'));
		  formFieldArray.push(new FormElement(2,'代码','code','string',1,'30'));
		  formFieldArray.push(new FormElement(2,'数据类型','type','string',1,'30','select'));
		  formFieldArray.push(new FormElement(2,'最大长度','length','number',1,'30'));
		  var require=new FormElement(2,'必输项','require','boolean',0,'30','checkbox')
		  require.value=true;
		  formFieldArray.push(require);
		  var readonly=new FormElement(2,'只读','readonly','boolean',0,'30','checkbox');
		  readonly.value=false;
		  formFieldArray.push(readonly);
		  var visiable=new FormElement(2,'是否显示','visiable','boolean',0,'30','checkbox');
		  visiable.value=true;
		  formFieldArray.push(visiable);
		  formFieldArray.push(new FormElement(2,'控件组','formFieldGroupId','string',1,'30','select'));
		  formFieldArray.push(new FormElement(2,'控件类型','model','string',1,'30','select'));
		  
		  formFieldArray.push(new FormElement(2,'下拉类型','dropType','string',0,'30','select','dropTypeChange'));
		  formFieldArray.push(new FormElement(2,'下拉实现','dropImpl','string',0,'128'));
		  formFieldArray.push(new FormElement(2,'下拉实现','dropImplUrl','string',0,'128','select'));
		  var dropImplDic=new FormElement(2,'下拉实现','dropImplDic','string',0,'128','select-autocomplete');
		  $http.get("api_cmd/category/listLikeName").success(function(response){
		    	resolveShowFieldAndReturnField(dropImplDic,response);
		    	dropImplDic.items=response;
		  });
		  
		  /*dropImplDic.dropDataUrl="api_cmd/category/listLikeName";*/
		  formFieldArray.push(dropImplDic);
		  formFieldArray.push(new FormElement(3,'默认值','defaultValue','string',0,'30'));
		  formFieldArray.push(new FormElement(3,'计算表达式','evalValue','string',0,'255','textarea'));
		  formFieldArray.push(new FormElement(3,'函数变量选择','functionSelect','string',0,'255','select',"functionSelectChange"));
		  formFieldArray.push(new FormElement(4,'排序号','orderNo','number',0,'255'));
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope, $location, $http);
	var dropType;
	$scope.dropTypeChange=function(type){
		dropType=type;
		if(type==='url'){
			editService.getFieldMap().get("dropImpl").isShow=false;
			editService.getFieldMap().get("dropImplUrl").isShow=true;
			editService.getFieldMap().get("dropImplDic").isShow=false;
		}else if(type==='dic'){
			editService.getFieldMap().get("dropImpl").isShow=false;
			editService.getFieldMap().get("dropImplUrl").isShow=false;
			editService.getFieldMap().get("dropImplDic").isShow=true;
		}else{
			editService.getFieldMap().get("dropImpl").isShow=true;
			editService.getFieldMap().get("dropImplUrl").isShow=false;
			editService.getFieldMap().get("dropImplDic").isShow=false;
		}
	}
	$scope.entityFieldChange=function(entityCode){
		var code=entityCode.split("|")[1];
		var entityCode=entityCode.split("|")[0];
		for(var i=0;i<$scope.entityFields.length;i++){
			var field=$scope.entityFields[i];
			if(entityCode===field.code){
				editService.getFieldMap().get("name").value=field.comment;
				editService.getFieldMap().get("code").value=code;
				editService.getFieldMap().get("type").value=(field.type==='bigint_'||field.type==='int_'||field.type==='double_')?'number':"string";
				editService.getFieldMap().get("length").value=field.length;
				editService.getFieldMap().get("require").value=!field.nullable;
				break;
			}
		}
	}
	$scope.functionSelectChange=function(value){
		var formField=editService.getFieldMap().get("evalValue");
		formField.value=(formField.value?formField.value:"")+" "+value ;
	}
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields(formFieldArray); 
    editService.initParams(codeUrl);
    editService.getFieldMap().get("dropImplUrl").isShow=false;
	editService.getFieldMap().get("dropImplDic").isShow=false;
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
    	if(dropType&&dropType==="url"){
    		editService.getFieldMap().get("dropImpl").value=editService.getFieldMap().get("dropImplUrl").value;
    	}else if(dropType&&dropType==="dic"){
    		editService.getFieldMap().get("dropImpl").value=editService.getFieldMap().get("dropImplDic").value;
    	}
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
    /**
     * 定义保存操作.
     */
    $scope.save = function(){
    	if(dropType&&dropType==="url"){
    		editService.getFieldMap().get("dropImpl").value=editService.getFieldMap().get("dropImplUrl").value;
    	}else if(dropType&&dropType==="dic"){
    		editService.getFieldMap().get("dropImpl").value=editService.getFieldMap().get("dropImplDic").value;
    	}
    	editService.saveData(addDataUrl, homeUrl);
    }
});

angular.module('HuatekApp').controller('FormFieldGroupController', function ($scope, $location, $http, $routeParams, batchEditService) {

	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	batchEditService.init($scope, $location, $http);
	$scope.formId = $routeParams.formId;
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: false,
		    columnDefs: [
		      { displayName: '编号', field: 'no',width: '49%', enableColumnMenu: false},
		      { displayName: '名称', field: 'name', width: '48%',enableColumnMenu: false}
		    ]
	  };
	
	/***
     * 注册gridapi-cmd的选择器.
     */
     $scope.tableGrid.onRegisterApi = function(gridApi){
 	   $scope.gridApi = gridApi;
 	  gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
 			if(!rowEntity.isNewRow) {
 				rowEntity.isEdited = true;
 			}
 		});
     }; 
     
     
    
    /*定义功能按钮*/
    var btnArray = [];
        btnArray.push(new pageButton('新增行','add','/formFieldConfig/saveGroup','addrow'));
        btnArray.push(new pageButton('删除行','delete','/formFieldConfig/saveGroup','deleterow'));
        btnArray.push(new pageButton('保存','save','/formFieldConfig/saveGroup','addProperty'));
        btnArray.push(new pageButton('返回','back','/formConfig/home','goBack'));
    /***
     * 设置界面的功能按钮.
     */    
     batchEditService.setButtonList(btnArray);
    
    
    /***
     * 桥接按钮事件.
     */
    
    $scope.execute = function(operation, param){
        if(operation=='addrow'){
        	batchEditService.addRow($scope.tableGrid, false);
        	$scope.tableGrid.data[0].fwCategoryId = $scope.id;
        }else if(operation=='deleterow'){
        	batchEditService.delRow($scope.tableGrid, $scope.gridApi);
        }else if(operation=='addProperty'){
        	batchEditService.saveData("api_workflow/formFieldConfig/saveGroup/"+$scope.formId, "/formConfig/home", $scope.tableGrid);
        }else if(operation=='goBack'){
        	$location.path('/formConfig/home');
        }
    }
    
    /***
     * 查询后台数据返回来的就是一个翻页对象.
     * 详见：com.huatek.frame.page.DataPage<T>
     */
	var load = function () {
			if($scope.formId!=null){
				batchEditService.loadData('api_workflow/formFieldConfig/loadGroup/'+$scope.formId, $scope.tableGrid); 
			}
	};
	/***
	 * 初始化加载数据.
	 */
	load(); 
});