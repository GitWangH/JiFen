'use strict';
   
angular.module('huatek.controllers').controller('baseController', function ($scope, $http, listService,$rootScope,$compile) {

    /*在页面渲染后加载机构树*/
    $scope.$watch('$viewContentLoaded', function() {  
        var setting = {
            data: {simpleData: {enable: true}}
        };
        $http.get("static/json/treeOrg.json").success(function(res){
            $.fn.zTree.init($("#treeDemo"), setting, res);
        });
        
    }); 
    /***
	 * 定义显示的表格. 
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
            paginationCurrentPage:1,/*当前页码*/
		    columnDefs: [
		      { name: '角色名称', field: 'name',width: "25%",enableColumnMenu: false },
		      { name: '角色编码', field: 'rolecode',width: "25%",enableColumnMenu: false },
		      { name: '是否合作网点', field: 'isCooperate',width: "25%", enableColumnMenu: false,cellFilter:"isCooperateFilter"},
/*		      { name: '所属部门', field: 'fwDeptName',width: '30%', enableColumnMenu: false},*/
		      { name: '描述', field: 'comments',width: "25%", enableColumnMenu: false}
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
    /*初始化listService*/
	listService.init($scope, $http);
    /*定义查询对象*/
    var queryPage = new QueryPage(1,10,"id asc","false");
    queryPage.addParam(new queryParam('角色名称','name','string','like'));
    queryPage.addParam(new queryParam('角色编码','rolecode','string','like'));
    queryPage.addParam(new queryParam('描述','comments','string','like'));
    var isCooperate=new queryParam('是否合作网点','isCooperate','string');
    isCooperate.componentType = "select";
    isCooperate.items=[{_returnField:"0", _showField:"否"},{_returnField:"1",_showField:"是"}];
    queryPage.addParam(isCooperate);
    /*将查询对象设置到listService中*/
    listService.setQueryPage(queryPage);
    /*定义页面操作按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('新增','add','/fwrole/add','addData'));
    btnArray.push(new pageButton('编辑','edit','/fwrole/edit','editData'));
    btnArray.push(new pageButton('删除','delete','/fwrole/delete','deleteData'));
    btnArray.push(new pageButton('机构树','orgRole','/fwrole/orgRole','orgRoleData')); 
    btnArray.push(new pageButton('分配权限','assign','/fwrole/assign','assginData'));
    btnArray.push(new pageButton('APP分配权限','appAssign','/fwrole/appAssign','appAssginData')); 
    /*将操作按钮设置到listService*/
    listService.setButtonList(btnArray);
    /*定义操作按钮的响应事件*/
    $scope.execute = function(operation, param){
        if(operation=='addData'){
            listService.addData(new popup("新增角色","/fwrole/add"));
        }else if(operation=='deleteData'){
            /*listService.deleteData($scope.tableGrid,$scope.gridApi, URL_PATH.MDM_HEADER+'/mdmPosition/deleteRole');*/
        	this.deleteData($scope.tableGrid,$scope.gridApi, URL_PATH.MDM_HEADER+'/mdmPosition/deleteRole');
        }else if(operation=='editData'){
            listService.editData($scope.gridApi,new popup("编辑角色","/fwrole/edit"));
        }else if(operation=='assginData'){
        	this.assignRight($scope.gridApi, '/fwrole/assign');
        }else if(operation=='newassign'){
        	this.newassign($scope.gridApi, '/fwrole/newassign');
        }else if(operation=='appAssginData'){
        	this.newassign($scope.gridApi, '/fwrole/appAssign');
        }else if(operation=='appAssginData'){
        	this.newassign($scope.gridApi, '/fwrole/appAssign');
        }else if(operation=='orgRoleData'){
        	//this.newassign($scope.gridApi, '/fwrole/orgRoleData');
        	$location.path("/fwrole/orgRole");
        }
    } 
    /*加载页面列表数据方法*/
    var load = function(){
    	listService.loadData('api/fwrole/query', $scope.tableGrid); 
    };
    /*加载列表数据*/
    load();
    /*页面查询响应方法*/
    $scope.search = function() {
        load();
    };
    
    /***
     * 分派权限.
     */
    $scope.assignRight = function(gridApi, toUrl){
        /*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length==0){
    		submitTips('警告：请在列表中选择一条用于分配权限的数据。','warning');
    		return;
    	}
    	if(selectData.length>1){
    		submitTips('警告：只能选择一条数据用于分配权限。','warning');
    		return;
    	}
    	$location.path(toUrl + "/" + selectData[0].id);
    }
    /***
     * 分派权限.
     */
    $scope.appAssignRight = function(gridApi, toUrl){
    	
        /*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length==0){
    		submitTips('警告：请在列表中选择一条用于分配权限的数据。','warning');
    		return;
    	}
    	if(selectData.length>1){
    		submitTips('警告：只能选择一条数据用于分配权限。','warning');
    		return;
    	}
    	$location.path(toUrl + "/" + selectData[0].id);
    }
   
    $scope.editData = function(gridApi, toUrl){
        /*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length==0){
    		submitTips('警告：请在列表中选择一条编辑的数据。','warning');
    		return;
    	}
    	if(selectData.length>1){
    		submitTips('警告：只能选择一条编辑的数据。','warning');
    		return;
    	}
    	if(selectData[0].rolecode=="R_CJWDGLY"){
    		submitTips('警告：角色不可编辑(特殊角色)','warning');
    		return;
    	}
    	listService.editData(gridApi, toUrl);
    }

    $scope.deleteData = function(tableGrid,gridApi, toUrl){
    	if(gridApi.selection.getSelectedRows().length < 1){

/*			bootbox.alert("请至少勾选一条数据！");*/
			submitTips('请至少勾选一条数据！','warning');
			return false;
		}
		bootbox.confirm('确定要删除所选的数据吗?', function(result) {
	         if(result){
	        	 angular.forEach(gridApi.selection.getSelectedRows(), function (data, index) {
	        		 if(data.id == null || data.id == undefined){
	        			 return;
	        		 }
	        		 if(data.rolecode=="R_CJWDGLY"){
	        			  submitTips('警告：角色不可删除(特殊角色)','warning');
	        	    	  return;
	        	    	}
	        		 $http.delete( toUrl+"/"+ data.id).success(function () {
	        				load();
	 	            });
	        	});
		     }
	       });
    	
        /*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData[0].rolecode=="R_CJWDGLY"){
    		bootbox.alert('警告：角色不可删除(特殊角色)');
    		return;
    	}
    	
    }
    $scope.newassign = function(gridApi, toUrl){
    	
        /*获取当前选择的数据.*/
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length>1){
    		bootbox.alert('警告：不能选择多条数据用于分配权限。');
    		return;
    	}
    	if(selectData.length==0){
    		bootbox.alert('请在列表中选择一条用于分配权限的数据。');
    		return;
    	}
    	$location.path(toUrl + "/" + selectData[0].id);
    }
    
    
	
}).filter("isCooperateFilter", function() {
    var filterfun = function(val) {
    	if(val=="0"){
    		return "否";
    	}else if(val=="1"){
    		return "是";
    	}else{
    		return "";
    	}
    };
    return filterfun;
});
   
angular.module('huatek.controllers').controller('baseAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	    
	 var addDataUrl = 'api/fwrole/add';
	 var editDataUrl = 'api/fwrole/edit';
	 var homeUrl = '/fwrole/home';
	
    /*定义块*/
	var columnWrapArray = [];
	columnWrapArray.push(new multipleColumn(1,'基本信息'));
	columnWrapArray.push(new multipleColumn(2,'补充信息'));
	/***
	 * 定义
	 */
	$scope.columnWrapArray = columnWrapArray;
    
    /*定义用户录入数据FormElement(title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'角色名称','name','string',1,'30'));
    var roleCode =new FormElement(1,'角色编码','rolecode','string',1,'50');
    roleCode.value='系统自动生成';
    roleCode.readonly='readonly';
    formFieldArray.push(roleCode);
    var isCooperate=new FormElement(1,'是否合作网点','isCooperate','string',1,'30','select')
    isCooperate.items = [{_returnField:"0", _showField:"否"},{_returnField:"1",_showField:"是"}];
    isCooperate.value="0";
	formFieldArray.push(isCooperate);
    formFieldArray.push(new FormElement(2,'角色描述','comments','string',-1,'200','textarea'));
       
	editService.init($scope, $location, $http);
    
       
    editService.setFormFields(formFieldArray); 
    
       
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    	editService.getFieldMap().get("rolecode").readonly=true;
    }else{
    	$scope.editId = -1;
    }
    	
       
    $scope.back = function(){
       $scope.jsPanel.close();
    }

    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData(addDataUrl, homeUrl,null,function(){
            $scope.jsPanel.close();
        });
    }
    
});