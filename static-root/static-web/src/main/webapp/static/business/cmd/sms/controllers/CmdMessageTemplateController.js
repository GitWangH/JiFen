'use strict';
   
angular.module('HuatekApp').controller('CmdMessageTemplateController', function ($scope, $location, $http,$rootScope, listService) {
	var getMsgtypeUrl= URL_PATH.CMD_HEADER+'/cmdMessageTemplate/getMsgtype';
	var getMsgStatusUrl= URL_PATH.CMD_HEADER+'/cmdMessageTemplate/getMsgstatus';
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '模版编码', field: 'code',width: '10%', enableColumnMenu: false},
		       { name: '短信接口系统ID', field: 'outId',width: '10%', enableColumnMenu: false},
		       { name: '模版内容', field: 'msgContent',width: '25%', enableColumnMenu: false},
		       { name: '短信模块', field: 'msgType',width: '10%', enableColumnMenu: false},
		       { name: '描述', field: 'description',width: '10%', enableColumnMenu: false},
		       { name: '状态', field: 'status',width: '10%', enableColumnMenu: false},
		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false},
		       { name: '审核失败原因', field: 'reason',width: '10%', enableColumnMenu: false},
		       { name: '创建人', field: 'creater',width: '10%', enableColumnMenu: false},
		       { name: '创建时间', field: 'createDate',width: '10%', enableColumnMenu: false},
		       { name: '修改人', field: 'modifier',width: '10%', enableColumnMenu: false},
		       { name: '修改时间', field: 'modifierDate',width: '10%', enableColumnMenu: false}
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
       
    var msgStatus = new queryParam('状态','status','string');
    msgStatus.componentType='select';
    queryPage.addParam(msgStatus);
    
    
    var msgType=new queryParam('短信模块','msgType','string','=');
    msgType.componentType='select';
    msgType.dropDataUrl="dic.msg_type";
    queryPage.addParam(msgType);
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage(queryPage);
    
    /*listService.initQueryParams(getMsgtypeUrl);*/
    listService.initQueryParams(getMsgStatusUrl);
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
    btnArray.push(new pageButton('新增','add','/cmdMessageTemplate/add','addData'));
	btnArray.push(new pageButton('编辑','edit','/cmdMessageTemplate/edit','editData'));
	btnArray.push(new pageButton('删除','delete','/cmdMessageTemplate/delete','deleteData'));
	btnArray.push(new pageButton('同步状态','updateStutas','/cmdMessageTemplate/home','updateStutasData'));
	btnArray.push(new pageButton('同步模版','updatemsg','/cmdMessageTemplate/home','updateAllmsg'));
	btnArray.push(new pageButton('发送短信测试','sendMessage','/cmdMessageTemplate/home','sendMessageTest'));
	
          
   listService.setButtonList(btnArray);
   
   /**
    * 同步状态方法
    * **/
   $scope.updateStatus = function(toUrl){
	 
	 /*调用启用action*/
	   $http.get(toUrl).success(function(response) {
				load();
	   }).error(
		function(data) {
			bootbox.alert("操作失败！");
		});
	};
   
       
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
        	listService.addData('/cmdMessageTemplate/add');
        }
 	  	else if(operation=='editData'){
 	  		listService.editData($scope.gridApi, '/cmdMessageTemplate/edit');
  	  	}
       	else if(operation=='deleteData'){
       		alert("该功能已禁用！");
       		
       		/*listService.deleteData($scope.tableGrid, $scope.gridApi, URL_PATH.CMD_HEADER+'/cmdMessageTemplate/delete');*/
 		}
       	else if(operation=='updateStutasData'){
       		this.updateStatus(URL_PATH.CMD_HEADER+'/cmdMessageTemplate/updateStatus');
 		}
       	else if(operation=='updateAllmsg'){
       		this.updateStatus(URL_PATH.CMD_HEADER+'/cmdMessageTemplate/updateAllmsg');
 		}else if(operation=='sendMessageTest'){
 			alert("该功能已禁用！");
 			
 			/*this.updateStatus(URL_PATH.CMD_HEADER+'/cmdMessageTemplate/sendMessageTest');*/
 		}
    }
	   
    var load = function(){
    	console.log($scope);
    	listService.loadData(URL_PATH.CMD_HEADER + '/cmdMessageTemplate/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('HuatekApp').controller('CmdMessageTemplateAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = URL_PATH.CMD_HEADER+'/cmdMessageTemplate/add';
	 var editDataUrl = URL_PATH.CMD_HEADER+'/cmdMessageTemplate/edit';
	 var homeUrl = '/cmdMessageTemplate/home';
	 var getMsgtypeUrl= URL_PATH.CMD_HEADER+'/cmdMessageTemplate/getMsgtype';
	 var getMsgDescriptionUrl= URL_PATH.CMD_HEADER+'/cmdMessageTemplate/getMsgDescription';
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    
    /*FormElement1(title, name, type, require, max, model, isEdit, isShow, event, min, defaultValue, dropDataUrl)*/
    var formFieldArray = [];
      
      /*隐藏该列*/
      var outId = new FormElement(1,'短信接口系统ID','outId','string',1,'64');
      outId.isShow = false;
      formFieldArray.push(outId);
      var msgType=new FormElement(1,'短信模块','msgType','string',1,'20','select');
      msgType.dropDataUrl="dic.msg_type";
	  formFieldArray.push(msgType);
	  formFieldArray.push(new FormElement(1,'描述','description','string',1,'100','select'));
	  formFieldArray.push(new FormElement(1,'模版内容','msgContent','string',1,'500','textarea'));
	  formFieldArray.push(new FormElement(1,'备注','remark','string',0,'100','textarea'));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope, $location, $http);
	
	/*editService.initParams(getMsgtypeUrl);*/
	editService.initParams(getMsgDescriptionUrl);
       
    editService.setFormFields(formFieldArray); 
    
       
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
       
    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData(addDataUrl, homeUrl);
    }
});

   
