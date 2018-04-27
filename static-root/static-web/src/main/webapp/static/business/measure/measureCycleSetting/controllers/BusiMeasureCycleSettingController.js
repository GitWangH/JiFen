'use strict';

/**
 * 计量设置Cont
 */
angular.module('huatek.controllers').controller('BusiMeasureCycleSettingController', function ($scope, $location, $http, listService,$modal,$rootScope, httpService, $localStorage) {
  
  /***
   * 定义显示的表格. 
   */
  $scope.tableGrid = {
        paginationPageSizes: [10, 50, 100,1000],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        columnDefs: [
          { name: '组织名称', field: 'orgName',width: '20%',enableColumnMenu: false },
          { name: '计量类型', field: 'measureTypeVal',width: '20%', enableColumnMenu: false, decode:{field:"measureType",dataKey:"dic.measure_type"}},
          { name: '创建时间',  field: 'createTime',width: '20%', enableColumnMenu: false},
          { name: '备注',  field: 'remarks',width: '*', enableColumnMenu: false},
        ],

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
    var orgId=new queryParam('标段名称','org.id','string','in');
    orgId.componentType = SEARCH_COMPONENT.TENDERS_MULTIPLE_SLECT;
    queryPage.addParam(orgId);
    var measureType=new queryParam('计量类型','measureType','string','in');
    measureType.dropDataUrl = 'dic.measure_type';
    measureType.componentType = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(measureType);
    var tenantId=new queryParam('tenantId','tenantId','string','=');
    tenantId.value = $rootScope.tenantId;
    tenantId.isShow = false;
    queryPage.addParam(tenantId);
       
    listService.setQueryPage($scope, queryPage);
   
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/measureCycleSet/add','addData'));
        btnArray.push(new pageButton('填写','set','/measureCycleSetDetail/home','setData'));
        btnArray.push(new pageButton('编辑','edit','/measureCycleSet/edit','editData'));
        btnArray.push(new pageButton('删除','delete','/measureCycleSet/delete','deleteData'));
          
   listService.setButtonList($scope, btnArray);

    $scope.execute = function(operation, param){
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if(operation=='addData'){
          /*新增*/
          listService.addData($scope, new popup("新增", "/measureCycleSet/add", null, 890, 500));
        }else if(operation=='setData'){
          /*填写, 如果选择计量类型为中间计量, 则需要带出项目所填周期*/
          if (listService.selectOne($scope.gridApi)) {
              $scope.setCycleDetail($scope, $scope.gridApi, new popup("填写", "/measureCycleSetDetail/home", null, 890, 500));
              /*listService.addData($scope, new popup("填写", "/measureCycleSetDetail/home", null, 890, 500));*/
          }
        }else if(operation=='editData'){
          /*编辑*/
          listService.editData($scope, $scope.gridApi, new popup("编辑", "/measureCycleSet/edit", null, 890, 500));
          
        }else if(operation=='deleteData'){
          /*删除, 删除时校验周期是否已经在计量中使用*/
          if (listService.selectOne($scope.gridApi)) {
              listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/measureCycleSetting/delete');
          }
        }
    }

    /**
     * 填写计量周期
     *
     * @param      {<type>}  _scope   The scope
     * @param      {<type>}  gridApi  The grid api
     * @param      {<type>}  popup    The popup
     */
    $scope.setCycleDetail = function(_scope, gridApi, popup){
        /*获取选中数据*/
        var selData = gridApi.selection.getSelectedRows()[0];
        /*判断选中是否为中间计量, 则查询项目开工日期并自动带出对应周期*/
        var params = new Object();
        params.id = selData.id;
        if(selData.measureType == 'middle_measure'){
            params.measureType = "middleMeasure";
        }else{
          /*默认带出一行数据*/
            params.measureType = "othersMeasure";
        }
        popup.passParams = params;
        listService.addData($scope, popup);
    }
    
    var load = $scope.load = function(){
      listService.loadData($scope, 'api/measureCycleSetting/query', $scope.tableGrid); 
    }
    
     load();
     
    $scope.search = function() {
       tenantId.value = $rootScope.tenantId;
       load();
    }

});

/**
 * 计量设置新增
 */
angular.module('huatek.controllers').controller('BusiMeasureCycleSettingAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope, httpService, listService) {
  console.log($scope.passParams);
      
   var addDataUrl = 'api/measureCycleSetting/add';
   var editDataUrl = 'api/measureCycleSetting/edit';
     /*定义块*/
     var columnWrapArray = [];
     columnWrapArray.push(new multipleColumn(1,'基本信息'));
     $scope.columnWrapArray = columnWrapArray;
      
        /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1,'标段名称','orgId','string','1','30', FORM_COMPONENT.SELECT_TREE_SINGLE));
    formFieldArray.push(new FormElement(1,'计量类型','measureType','string','1','30','select',null,null,null,'dic.measure_type'));
    formFieldArray.push(new FormElement(1,'备注','remarks','string','0','255', 'textarea'));
   
    
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
    
});
