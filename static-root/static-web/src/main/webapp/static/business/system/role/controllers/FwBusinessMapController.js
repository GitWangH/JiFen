'use strict';
   
angular.module('huatek.controllers').controller('FwBusinessMapController', function ($scope, $location, $http, listService) {
	/***
	 * 定义显示的表格. 
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 50, 100, 200],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		      { name: '系统名称', field: 'fwSystemId_',width: '25%',enableColumnMenu: false },
		      { name: '业务模块名称', field: 'name',width: '25%',enableColumnMenu: false },
		      { name: '菜单名称', field: 'fwSourceId_',width: '25%',enableColumnMenu: false },
		      { name: '实体名称', field: 'entityId_',width: '25%',enableColumnMenu: false }
		    ]
	  };
	   
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
       
    queryPage.addParam(new queryParam('业务模块名称','name','string','like'));
       
    listService.setQueryPage(queryPage);
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/fwBusinessMap/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/fwBusinessMap/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/fwBusinessMap/delete','deleteData'));
        btnArray.push(new pageButton('应用配置','appConfig','/fwApplyScope/appConfig','appConfig'));
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
        if(operation=='addData'){
        	listService.addData('/fwBusinessMap/add');
        }else if(operation=='deleteData'){
        	listService.deleteData($scope.tableGrid, $scope.gridApi, 'api/fwBusinessMap/delete');
        }else if(operation=='editData'){
        	
            /*listService.editData($scope.gridApi, '/fwBusinessMap/edit');*/
        	
            /*listService.editData($scope.gridApi, '');*/
        	
            /*获取当前选择的数据.*/
        	var selectData = listService.returnSectData($scope.gridApi);
        	if(selectData.length>1){
        		bootbox.alert('警告：不能选择多条数据用于编辑。');
        		return;
        	}
        	if(selectData.length==0){
        		bootbox.alert('请在列表中选择一条用于编辑的数据。');
        		return;
        	}
        	$location.path("/fwBusinessMap/edit/" + selectData[0].id+"/"+selectData[0].fwSystemId);
        }else if(operation=='appConfig'){
        	this.appConfig($scope.gridApi, '/fwApplyScope/appConfig');
        }
    }
	   
    var load = function(){
    	listService.loadData('api/fwBusinessMap/query', $scope.tableGrid); 
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
	/***
     * 应用配置.
     */
    $scope.appConfig = function(gridApi, toUrl){
    	
        /*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length>1){
    		bootbox.alert('警告：不能选择多条数据用于应用配置。');
    		return;
    	}
    	if(selectData.length==0){
    		bootbox.alert('请在列表中选择一条用于应用配置的数据。');
    		return;
    	}
    	$location.path(toUrl + "/" + selectData[0].id);
    }
	
});


   
angular.module('huatek.controllers').controller('FwBusinessMapAddController', function ($scope, $location, $http, $routeParams, editService) {
	    
	 var addDataUrl = 'api/fwBusinessMap/add';
	 var editDataUrl = 'api/fwBusinessMap/edit';
	 var homeUrl = '/fwBusinessMap/home';
	 
	
    /*定义块*/
	var columnWrapArray = [];
	columnWrapArray.push(new multipleColumn(1,'基本信息'));
	
    /*columnWrapArray.push(new multipleColumn(2,'补充信息'));*/
	/***
	* 定义
	*/
	$scope.columnWrapArray = columnWrapArray;
	
    
    /*定义用户录入数据FormElement(title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'业务模块名称','name','string',1,'50'));
    formFieldArray.push(new FormElement(1,'系统名称','fwSystemId','int',1,'30','select')); 
    formFieldArray.push(new FormElement(1,'菜单名称','fwSourceId','int',1,'30','select')); 
    formFieldArray.push(new FormElement(1,'数据实体','entityId','int',1,'30','select')); 
    
       
	editService.init($scope, $location, $http);
       
    editService.setFormFields(formFieldArray); 
       
    $scope.editId = $routeParams.id;
    $scope.systemId = $routeParams.systemId;
    /**
     * 获取字典数据
     */
    var fieldMap = editService.getFieldMap();
    fieldMap.get("name").min = 5;
    fieldMap.get("fwSystemId").event="changeSystemMenu";
    /***
     * 初始化表单选择数据，包括字典数据，其他可选项数据.
     */
    editService.initFieldItems(fieldMap.get("fwSystemId"),"api/system/public/select");
    editService.initFieldItems(fieldMap.get("entityId"),"api/fwSourceEntity/public/select")
    if($scope.editId){
    	editService.initFieldItems(editService.getFieldMap().get("fwSourceId"),"api/menu/public/select/"+$scope.systemId);
    }
    
    /*fieldMap.get("fwSourceId").value=0;*/
    
    /*fieldMap.get("entityId").value=0;*/
    /***
     * 定义联动事件
     */
    $scope.changeSystemMenu = function(val){
    	$.get("api/menu/public/select/"+val,function(data){
    		editService.getFieldMap().get("fwSourceId").items=data;
    	});
    }
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }
       
    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData(addDataUrl, homeUrl);
    }
});