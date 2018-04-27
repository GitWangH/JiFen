/**
 * 项目基本信息
 */
'use strict';
   
angular.module('huatek.controllers').controller('busiRemoteMonitorController', function ($scope, $location, $http,$rootScope,editService, listService,shareData,httpService) {
	/**queryAction*/
	var queryUrl = "/api/remoteMonitor/query";
	/**创建表格*/
	$scope.tableGrid = {
		paginationPageSizes: [10, 25, 50,100],
	    paginationPageSize: 10,
	    useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
	    columnDefs: [
           { name: '组织名称', field: 'tendersName',width: '10%', enableColumnMenu: false},
           { name: '监控类型', field: 'monitorTypeVal',width: '10%', enableColumnMenu: false, decode: { field: "monitorType", dataKey: "dic.monitor_type" }},
           { name: '用户名', field: 'acctName',width: '10%', enableColumnMenu: false},
           { name: '厂商名称', field: 'firmCompanyVal',width: '10%', enableColumnMenu: false, decode:{field:"firmCompany",dataKey:"dic.manufacturer"}},
           { name: '标题', field: 'title',width: '10%', enableColumnMenu: false},
           { name: '远程IP地址', field: 'remoteAddress',width: '25%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewRemoteMonitor(row.entity)">{{row.entity.remoteAddress}}</a>' },
           { name: '手机远程IP地址', field: 'remoteAddressPhone',width: '25%', enableColumnMenu: false, cellTemplate : '<a ng-click="grid.appScope.viewRemoteMonitorPhone(row.entity)">{{row.entity.remoteAddressPhone}}</a>' },
           
	    ]
	};
	/**初始化表格*/
	listService.init($scope, $location, $http);
	
	/** 查询条件 */
	var queryPage = new QueryPage(1, 10, "id desc", "false");
    var tendersName = new queryParam('组织名称', 'tenders.id', 'string', '=');
    // tendersName.componentType  = SEARCH_COMPONENT.TENDERS_SLECT;
    tendersName.componentType = SEARCH_COMPONENT.SELECT_TREE_SINGLE;
    queryPage.addParam(tendersName);
    var monitorType = new queryParam('监控类型', 'monitorType', 'string', '=');
    monitorType.dropDataUrl = 'dic.monitor_type';
    monitorType.componentType  = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(monitorType);
    var firmCompany=new queryParam('厂商名称','firmCompany','string','=');
    firmCompany.dropDataUrl = 'dic.manufacturer';
    firmCompany.componentType  = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(firmCompany);
	/** 查询条件初始化 */
	listService.setQueryPage($scope,queryPage);
	
    /**
	 * 注册gridApi的选择器.
	 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
        /*调整grid底部高度*/
        listService.setGridHeight();
    };
    
    
    // 定义功能按钮
	var btnArray = [];
	btnArray.push(new pageButton('新增', 'add', '/remoteMonitor/add', 'addData'));
	btnArray.push(new pageButton('修改', 'edit', '/remoteMonitor/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/remoteMonitor/delete', 'deleteData'));

	/**
	 * 设置界面的功能按钮.
	 */
    listService.setButtonList($scope, btnArray);
    
    var load = $scope.load = function(){
    	listService.loadData($scope, queryUrl, $scope.tableGrid);
    }
    
    /**
	 * 桥接按钮事件.
	 */
    $scope.execute = function(operation, param){
        if(operation=='addData'){
            listService.addData($scope, new popup("新增", "/remoteMonitor/add"));
        } else if (operation=='editData'){
            listService.editData($scope, $scope.gridApi,new popup("编辑","/remoteMonitor/edit"));
        } else if (operation=='deleteData'){
        	listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/remoteMonitor/delete');
        } 
    }
    
    load();
    
    $scope.search = function() {
        load();
    };

    /**
     * 打开远程监控地址
     *
     * @param      {<type>}  entity  The entity
     */
    $scope.viewRemoteMonitor = function(entity){
        window.open(entity.remoteAddress);
    }

    /**
     * 手机访问IP地址
     *
     * @param      {<type>}  entity  The entity
     */
    $scope.viewRemoteMonitorPhone = function(entity){
        window.open(entity.remoteAddress);
    }

});

/**
 * 远程监控新增Controller
 */
angular.module('huatek.controllers').controller('busiRemoteMonitorAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope, httpService, listService) {
        
     var addDataUrl = 'api/remoteMonitor/add';
     var editDataUrl = 'api/remoteMonitor/edit';
     /*定义块*/
     var columnWrapArray = [];
     columnWrapArray.push(new multipleColumn(1,'基本信息'));
     columnWrapArray.push(new multipleColumn(2,'加密信息'));
     $scope.columnWrapArray = columnWrapArray;
        
        /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'组织名称','tendersId','string','1','30', FORM_COMPONENT.SELECT_TREE_SINGLE));
    formFieldArray.push(new FormElement(1,'监控类型','monitorType','string','1','30','select',null,null,null,'dic.monitor_type'));
    formFieldArray.push(new FormElement(1,'远程IP地址','remoteAddress','string','1','255'));
    formFieldArray.push(new FormElement(1,'手机访问IP地址','remoteAddressPhone','string','0','255'));
    formFieldArray.push(new FormElement(1,'厂商名称','firmCompany','string','1','30','select',null,null,null,'dic.manufacturer'));
    formFieldArray.push(new FormElement(1,'标题','title','string','0','255'));
    formFieldArray.push(new FormElement(2,'用户名','acctName','string','0','30'));
    formFieldArray.push(new FormElement(2,'密码','acctPass','string','0','100','password'));
    formFieldArray.push(new FormElement(2,'MD5码','md5','string','0','100'));
    formFieldArray.push(new FormElement(2,'BASE64加密','base64','string','0','100'));
    formFieldArray.push(new FormElement(2,'3DES加密','threeDes','string','0','100'));
    formFieldArray.push(new FormElement(2,'描述','remark','string','0','100', 'textarea'));
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;
       
    editService.init($scope);
    
    editService.setFormFields($scope, formFieldArray); 


    if($scope.editId){
        editService.loadData($scope, editDataUrl, $scope.editId);
    }else{
        $scope.editId = -1;
    }
        
    /**
     * 更新
     */
    $scope.update = function(){
        editService.updateData($scope, editDataUrl,null, $scope.editId);
    } 
    
    /**
     * 新增
     */
    $scope.save = function(){
        editService.saveData($scope, addDataUrl);
    }
    
});