'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('BusiMeasureMiddlePayCertificateTemplateController', function ($scope, $location, $http,$rootScope, listService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '汇总归属', field: 'type1',width: '15%', enableColumnMenu: false,decode:{field:"type",dataKey:"dic.certificate_item_type"}},
		       { name: '支付项key', field: 'itemKey',width: '20%', enableColumnMenu: false},
		       { name: '支付项名称', field: 'itemName',width: '20%', enableColumnMenu: false},
		       { name: '计算方向', field: 'sign1',width: '10%', enableColumnMenu: false,decode:{field:"sign",dataKey:"dic.ertificate_item_sign"}},
		       { name: '备注', field: 'remarks', enableColumnMenu: false} 
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
    var orgIdParam=new queryParam('项目','orgId','string','=');
    queryPage.addParam(orgIdParam);
    orgIdParam.componentType="select";
    orgIdParam.returnField='id';
    orgIdParam.showField='shortName';
    resolveShowFieldAndReturnField(orgIdParam,$rootScope.proDatas);
    orgIdParam.items=$rootScope.proDatas;
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
    btnArray.push(new pageButton('新增','add','/busiMeasureMiddlePayCertificateTemplate/add','addData'));
    btnArray.push(new pageButton('编辑','edit','/busiMeasureMiddlePayCertificateTemplate/edit','editData'));
    btnArray.push(new pageButton('删除','delete','/busiMeasureMiddlePayCertificateTemplate/delete','deleteData'));
    btnArray.push(new pageButton('预览模板','preview','/busiMeasureMiddlePayCertificateTemplate/preview','previewData'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
    		if(!orgIdParam.value){
        		submitTips('请选择项目', 'warning');
        		return;
        	}
    		listService.addData($scope,new popup('新增数据项','/busiMeasureMiddlePayCertificateTemplate/add',orgIdParam.value,null,null,load));
        }
  	  	else if(operation=='editData'){
	  	  	if(!orgIdParam.value){
	    		submitTips('请选择项目', 'warning');
	    		return;
	    	}
    		listService.editData($scope,$scope.gridApi, new popup('编辑数据项','/busiMeasureMiddlePayCertificateTemplate/edit',orgIdParam.value,null,null,load));
   	  	}
       	else if(operation=='deleteData'){
    		listService.deleteData($scope,$scope.tableGrid, $scope.gridApi, 'api/busiMeasureMiddlePayCertificateTemplate/delete');
  		}
       	else if(operation=='previewData'){
    		listService.addData($scope, new popup('模版预览','/busiMeasureMiddlePayCertificateTemplate/preview',orgIdParam.value));
  		}
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	if(!orgIdParam.value){
    		submitTips('请选择项目', 'warning');
    		return;
    	}
    	listService.loadData($scope,'api/busiMeasureMiddlePayCertificateTemplate/query', $scope.tableGrid);
    }
    
   
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
angular.module('huatek.controllers').controller('BusiMeasureMiddlePayCertificateTemplateAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/busiMeasureMiddlePayCertificateTemplate/add';
	 var editDataUrl = 'api/busiMeasureMiddlePayCertificateTemplate/edit';
	 var homeUrl = '/busiMeasureMiddlePayCertificateTemplate/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    $scope.items=[{code:'code1',name:"暂列金额"}];
    $scope.nameChange=function(val){
    	for(var k in $scope.items){
    		if($scope.items[k].name==val){
    			itemKeyEl.value=$scope.items[k].code;
    			return;
    		}
    	}
    	itemKeyEl.value=null;
    }
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray =[];
          var orgIdEl=new FormElement(1,'项目','orgId','string',1,'30',"select");
          orgIdEl.isShow=false;
		  formFieldArray.push(orgIdEl);
		  if(!$scope.editId){
			  orgIdEl.value=$scope.passParams;
		  }
		  formFieldArray.push(new FormElement(1,'汇总类型','type','string',1,'30','select',null,null,null,"dic.certificate_item_type"));
		  var itemNameEl=new FormElement(1,'支付项名称','itemName','string',1,'30','selectOrAdd',$scope.nameChange);
		  formFieldArray.push(itemNameEl);
		  resolveShowFieldAndReturnField(itemNameEl,$scope.items);
		  itemNameEl.items=$scope.items;
		  var itemKeyEl=new FormElement(1,'支付项key','itemKey','string',0,'30');
		  itemKeyEl.readonly=true;
		  formFieldArray.push(itemKeyEl);
		  formFieldArray.push(new FormElement(1,'计算方向','sign','string',1,'30','select',null,null,null,"dic.ertificate_item_sign"));
		  formFieldArray.push(new FormElement(1,'备注','remarks','string',0,'30'));

    
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
    editService.setFormFields($scope,formFieldArray); 
    
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

angular.module('huatek.controllers').controller('BusiMeasureMiddlePayCertificateTemplatePreviewController', function ($scope, cacheService,$location, httpService, $routeParams, editService,$rootScope) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	editService.init($scope);
	$scope.templateItems=[];
	httpService.get($scope,"api/busiMeasureMiddlePayCertificateTemplate/queryAll/"+$scope.passParams).success(function(response){
		var i=1;
		var types=cacheService.getData("dic.certificate_item_type");
		for(var k=0;k<types.length ;k++){
			for(var j =0 ;j< response.length;j++){
				if(response[j].type==types[k].code){
					$scope.templateItems.push({orderNo:i++,name:response[j].itemName});
				}
			}
			
			switch(k){
			case 0:
				$scope.templateItems.push({orderNo:i++,name:"小计"});
				break;
			case 1:
				$scope.templateItems.push({orderNo:i++,name:"合计"});
				break;
			case 2:
				$scope.templateItems.push({orderNo:i++,name:"支付"});
				break;
			case 3:
				$scope.templateItems.push({orderNo:i++,name:"实际支付"});
			}
			
		}
		
	});
	
  	//定义块
  
   
	
   

});
