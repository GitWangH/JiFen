'use strict';
   
angular.module('huatek.controllers').controller('CmdMachineController', function ($scope, $location, $http, listService,$modal,$rootScope, httpService, $localStorage) {
  
  
  /***
   * 定义显示的表格. 
   */
  $scope.tableGrid = {
        paginationPageSizes: [10, 50, 100,1000],
        paginationPageSize: 10,
        useExternalPagination: true,
        // enableFullRowSelection : true,
        enableSelectAll : false,
        lookDetailConfig:{menuKey:'/cmdMachine/edit',name:'查看', paramFieldsArr:true},
        columnDefs: [
          { name: '机器编号', field: 'machineNum',width: '10%',enableColumnMenu: false },
          { name: '机器名称 ', field: 'macName',width: '10%', enableColumnMenu: false},
          { name: '机器类型 ', field: 'machineTypeVal',width: '10%', enableColumnMenu: false, decode:{field:"machineType",dataKey:"dic.test_inspection_type"}},
          { name: '机器所在机构', field: 'orgName',width: '10%', enableColumnMenu: false},
          { name: '安装位置', field: 'installLocation', width: '10%',enableColumnMenu: false},
          { name: '厂商名称', field: 'firmNameVal', width: '10%',enableColumnMenu: false, decode:{field:"firmName",dataKey:"dic.manufacturer"}},
          { name: '所在分部分项', field: 'subItemName', width: '20%',enableColumnMenu: false},
          { name: 'APPKEY', field: 'appKey', width: '20%',enableColumnMenu: false},
        ],
        enableGridMenu: true,
        exporterMenuPdf: false,
        exporterCsvFilename: '设备维护.csv',
        exporterOlderExcelCompatibility: true,
         exporterAllDataFn: function() {
                return listService.exportAllData($scope, $scope.tableGrid)
                .then(function() {
                  $scope.tableGrid.useExternalPagination = false;
                  $scope.tableGrid.useExternalSorting = false;
                });
            },

    };
     
  $scope.tableGrid.onRegisterApi = function(gridApi){
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          queryPage.page = newPage;
          queryPage.pageSize = pageSize;
          load();
        });
        /*调整grid底部高度*/    
        listService.setGridHeight();
  }; 
   listService.init($scope, $http, $scope.tableGrid);
  
    var queryPage = new QueryPage(1,10,"id desc","false");
    var machineNum = new queryParam('机器编号','machineNum','string','like');
    queryPage.addParam(machineNum);
    var machineName=new queryParam('机器名称','macName','string','like');
    queryPage.addParam(machineName);
    var orgId=new queryParam('机器所在组织','org.id','string','=');
    orgId.componentType = SEARCH_COMPONENT.SELECT_TREE_SINGLE;
    queryPage.addParam(orgId);
    var APPKEY=new queryParam('APPKEY','appKey','string','like');
    queryPage.addParam(APPKEY);
    var firmName=new queryParam('厂商名称','firmName','string','=');
    firmName.dropDataUrl = 'dic.manufacturer';
    firmName.componentType  = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(firmName);
    var tenantId=new queryParam('tenantId','tenantId','string','=');
    tenantId.value = $rootScope.tenantId;
    tenantId.isShow = false;
    queryPage.addParam(tenantId);
     /*var tenantIdParam=new queryParam('租户ID','tenantId','string','=');
    tenantIdParam.isShow=false;
    tenantIdParam.value = $rootScope.tenantId;
    queryPage.addParam(tenantIdParam);*/
       
    listService.setQueryPage($scope, queryPage);
    /*设置厂商默认选择第一个*/
    $scope.firmNameDefVal = "";
    var cache=$localStorage["dic.manufacturer"];
    var sourceList = cache.data;
    for(var i = 0;i< sourceList.length;i++){
      firmName.value = sourceList[0].code;
      $scope.firmNameDefVal = sourceList[0].code;
      break;
    }
    
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/cmdMachine/add','addData'));
        btnArray.push(new pageButton('编辑','edit','/cmdMachine/edit','editData'));
        btnArray.push(new pageButton('删除','active','/cmdMachine/delete','deleteData'));
          
   listService.setButtonList($scope, btnArray);

       
    $scope.execute = function(operation, param){
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if(operation=='addData'){
          listService.addData($scope, new popup("新增设备", "/cmdMachine/add", null, 890, 500));
        }else if(operation=='editData'){
          listService.editData($scope, $scope.gridApi,new popup("编辑设备","/cmdMachine/edit", null, 890, 500));
        }else if(operation=='deleteData'){
          listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/cmdMachine/delete');
        }
        /*else if(operation=='viewData'){
          listService.editData($scope, $scope.gridApi, new popup("查看","/cmdMachine/edit", null, "890", "500"),param);
        }*/
    }
    
    var load = $scope.load = function(){
      listService.loadData($scope, 'api/cmdMachine/query', $scope.tableGrid); 
    }
    
     load();
     
    $scope.search = function() {
       tenantId.value = $rootScope.tenantId;
       load();
    }

    /*查询清空时, 不清除厂商名称*/
    $scope.resetSearch = function(){
      machineNum.value = null;
      machineName.value = null;
      orgId.value = null;
      APPKEY.value = null;
    }
});

/**
 * 组织新增Controller
 */
angular.module('huatek.controllers').controller('CmdMachineAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope, httpService, listService) {
  console.log($scope.passParams);
      
   var addDataUrl = 'api/cmdMachine/add';
   var editDataUrl = 'api/cmdMachine/edit';
     /*定义块*/
     var columnWrapArray = [];
     columnWrapArray.push(new multipleColumn(1,'基本信息'));
     $scope.columnWrapArray = columnWrapArray;
      
        /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'机器编号','machineNum','string','1','30'));
    formFieldArray.push(new FormElement(1,'机器名称','macName','string','1','30'));
    formFieldArray.push(new FormElement(1,'机器类型','machineType','text','1','30','select',null,null,null,'dic.test_inspection_type'));
   /* var orgName = new FormElement(1,'机器所在机构','orgName','string','1','30','autocomplete',null,null,null,'api/org/getTheParentName');
    orgName.returnField='name';
    orgName.showField='name';
    formFieldArray.push(orgName);*/
   /* var orgName = new FormElement(1,'orgName','orgName','string','1','30');
    orgName.isShow = false;
    formFieldArray.push(orgName);*/
    var orgId=new FormElement(1,'机器所在机构','orgId','string',1,'30', SEARCH_COMPONENT.SELECT_TREE_SINGLE);
    orgId.lookValue = "orgName";
    orgId.selectCallBack = function(item){
      if(item){
        orgId.value = item.id;
      }
    }

    /*orgId.isShow=false;*/
    formFieldArray.push(orgId);
    formFieldArray.push(new FormElement(1,'安装位置','installLocation','string','1','30'));
    formFieldArray.push(new FormElement(1,'厂商名称','firmName','string','1','30','select',null,null,null,'dic.manufacturer'));
    var subItem = new FormElement(1,'所在分部分项','subItem','string','0','30');
    subItem.isShow = false;
    formFieldArray.push(subItem);
    var subItemName = new FormElement(1,'所在分部分项','subItemName','string','0','225');
    subItemName.clickEvent  = function(){
      if($scope.passParams){
        return;
      }
      /*先选择机器所在机构*/
      /*if(cnex4_isNotEmpty_str(orgId.value) && cnex4_isNotEmpty_str(orgName.value)){*/
      if(cnex4_isNotEmpty_str(orgId.value)){
    	  listService.popPanel($scope, 
          		new popup("项目分部分项", "/busiQualityConsInspection/createQuantitiesBill",
          				{ 'orgId': orgId, 'tendersBranchId': subItem, 'tendersBranch': subItemName, huatekTree: $scope.huatekTree },
          				"900", "600",null,'BusiQualitySelectTendersBranchController','static/business/quality/templates/template_list_edit_treeGrid.html','modal'));
      }else {
        submitTips('警告：请先选择机器所在机构。','warning');
        return;
      }
    }
    formFieldArray.push(subItemName);
    
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
      editService.updateData($scope, editDataUrl, null, $scope.editId);
    } 
    
    /**
     * 新增
     */
    $scope.save = function(){
      editService.saveData($scope, addDataUrl);
    }
    
    /**
     * 模糊监听
     */
    $scope.$on('autocomplete:selected',function(event,data){
        /*此时模糊检索组件的事件被响应*/
        orgId.value = null;
        //  清空已选分部分项
        subItem.value = null;
        if(data._componentsName == 'orgName'){
            orgId.value = data.value;
        }
     })
    
});

