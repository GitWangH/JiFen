angular.module('huatek.controllers').controller('BusiQualityRawMaterialQuickProcessController', function ($rootScope, $scope, $http, listService, editService,httpService) {
	console.log($scope.passParams)
	var addDataUrl = '/api/busiQualityRawMaterialInspection/quickProcessAdd';
	//var editDataUrl = 'api/busiQualityRawMaterialInspection/edit';
	var homeUrl = "/busiQualityRawMaterialInspection/home";
	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'快捷处理'));
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)*/
    var formFieldArray = [];
   
    formFieldArray.push(new FormElement(1,'处理人员','quickUserName','string',1,'30',null,null,null,$scope.userName));
    formFieldArray.push(new FormElement(1,'处理时间','quickProcessingTime','string',1,'30','date',null,null,getDate(getNowFormatDate())));
    formFieldArray.push(new FormElement(1,'原因','quickReason','string',1,'100','select',null,null,null,'dic.quick_process_reason'));
    formFieldArray.push(new FormElement(1,'说明','quickExplain','string',1,'500','textarea'));    
    var contractFileUUID = new FormElement(1,'附件','enclosure','string',0,'100','fileMultiple');
    contractFileUUID.systemHeader = "/root/quality/rawMaterial/rawMaterial";
 	formFieldArray.push(contractFileUUID);
   
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
    
    /**
     * 加载编辑数据
     */
    /*if($scope.editId){
    	editService.loadData($scope, editDataUrl, $scope.editId);
    }*/
   
    
    /**
	 * 初始化加载数据.
	 */
    /*var load = function() {
        listService.loadData($scope, '/api/busiQualityRawMaterialInspection/queryList/'+$scope.passParams.id, $scope.tableGrid);
    }
    
    if(undefined != $scope.passParams && undefined !=  $scope.passParams.id && $scope.passParams.id.length >0){
    	load();
    }*/
//    httpService.get($scope,'/api/busiQualityRawMaterialInspection/queryList'+"/"+$scope.passParams.id).success(function(response){
//    	$scope.tableGrid.data = response;
//    });
         
    
    /*load();*/
    

    /**
     * 保存
     **/    
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl+"/"+$scope.passParams.id+'/'+$scope.passParams.orgId, homeUrl);
    }
    
});