'use strict';
   
angular.module('huatek.controllers').controller('BusiMeasureBasicDataConfigController', function ($scope, $location, $http, listService,$modal,$rootScope, httpService, $localStorage) {
  
  var setUrl = "api/measureBsicDataConfig/set";
  /*标题*/
  var upperLimitRationTitle = "中间计量时，累计的计量金额不能超过合同金额*计量上限比例的值";
  var mobilizeAdvancePayRatioTitle = "中间计量时，付动员预付款的金额=动员预付款*动员预付款付款比例";
  var monthDeductedMobilizeAdvanceRatioTitle = "中间计量时，扣动员预付款的金额=已付动员预付款*月扣回动员预付款比例";
  var mobilizeAdvanceDeductedRatioTitle = "计量累计金额>合同金额*动员预付款起扣比例时，开始扣回动员预付款";
  var detainRetentionMoneyRatioTitle = "生成中期支付证书时，若累计暂扣保留金不到上限，则暂扣保留金=本次计量金额*暂扣保留金比例；若累计暂扣保留金到上限，则暂扣保留金为0";
  var cumulativeDetainRetentionMoneyLimitTitle = "累计暂扣保留金上限=合同金额*累计暂扣保留金限额（%）";
  
  /***
   * 定义显示的表格. 
   */
  $scope.tableGrid = {
        /*paginationPageSizes: [10, 50, 100,1000],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,*/
        columnDefs: [
          { name: '标段名称', field: 'orgName',width: '10%',enableColumnMenu: false },
          { name: '计量上限比例（%） ', headerTooltip: upperLimitRationTitle, field: 'upperLimitRatio',width: '10%', enableColumnMenu: false/*, cellFilter: "numFilter"*/},
          { name: '计量支付设置 ', field: 'measurePaySet',width: '10%', enableColumnMenu: false, editableCellTemplate: 'ui-grid/dropdownEditor', editDropdownValueLabel: 'measurePaySet', 
            editDropdownOptionsArray: [
            { id: 1, measurePaySet: '按合同清单' },
            { id: 2, measurePaySet: '按复核清单' },
            { id: 3, measurePaySet: '按最小数量' }
          ] ,cellFilter: "paySetFilter"},
          { name: '动员预付款付款比例（%）', headerTooltip: mobilizeAdvancePayRatioTitle, field: 'mobilizeAdvancePayRatio',width: '10%', enableColumnMenu: false},
          { name: '月扣回动员预付款比例（%）', headerTooltip: monthDeductedMobilizeAdvanceRatioTitle, field: 'monthDeductedMobilizeAdvanceRatio', width: '10%',enableColumnMenu: false},
          { name: '动员预付款起扣比例（%）', headerTooltip: mobilizeAdvanceDeductedRatioTitle, field: 'mobilizeAdvanceDeductedRatio', width: '10%',enableColumnMenu: false},
          { name: '暂扣保留金额比例（%）', headerTooltip: detainRetentionMoneyRatioTitle, field: 'detainRetentionMoneyRatio', width: '20%',enableColumnMenu: false},
          { name: '累计暂扣保留金限额（%）', headerTooltip: cumulativeDetainRetentionMoneyLimitTitle, field: 'cumulativeDetainRetentionMoneyLimit', width: '20%',enableColumnMenu: false},
        ],

    };
     
  $scope.tableGrid.onRegisterApi = function(gridApi){
        $scope.gridApi = gridApi;
        /*gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          queryPage.page = newPage;
          queryPage.pageSize = pageSize;
          load();
        });*/
        gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
          console.log(rowEntity);
            var num = parseFloat(formatValue(newValue));
            var field = colDef.field;
            if(cnex4_is_positive_number(num)){
              if(num < 0 || num > 100){
                submitTips('所填数据必须在0-100之间！', 'warning');
                rowEntity[field] = null;
              }
            }else {
               rowEntity[field] = null;
               submitTips(colDef.name + '格式输入错误！', 'warning');
               rowEntity[field] = null;
            }
            $scope.$apply();
          });
        load();
        /*调整grid底部高度*/    
        listService.setGridHeight();
  }; 
   listService.init($scope, $http, $scope.tableGrid);
  
    var queryPage = new QueryPage(1,10,"id desc","false");
    var orgId=new queryParam('标段名称','org.id','string','in');
    orgId.componentType = SEARCH_COMPONENT.TENDERS_MULTIPLE_SLECT;
    queryPage.addParam(orgId);
    var tenantId=new queryParam('tenantId','tenantId','string','=');
    tenantId.value = $rootScope.tenantId;
    tenantId.isShow = false;
    queryPage.addParam(tenantId);
       
    listService.setQueryPage($scope, queryPage);
   
   /*console.log(actionMap);*/
       
    var btnArray = [];
       /* btnArray.push(new customButton('全部设置','add',true,'setAll'));
        btnArray.push(new customButton('保存','edit',true,'addData'));*/
        btnArray.push(new pageButton('全部设置','add','/measureBsicDataConfig/set','setAll'));
        btnArray.push(new pageButton('保存','edit','/measureBsicDataConfig/add','addData'));
          
   listService.setButtonList($scope, btnArray);

    $scope.execute = function(operation, param){
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if(operation=='setAll'){
          /*选中一条数据进行设置, 将选中数据的值替换值当前列表中所有数据*/
          if(listService.selectOne($scope.gridApi)){
              console.log($scope.gridApi.selection.getSelectedRows());
              if(selectData[0].id){
                  console.log(selectData[0].id);
                   var tenders = $rootScope.userTenders;
                   var tenderStr = "";
                    angular.forEach(tenders, function(data, index) {
                        if(index == tenders.length - 1){
                          tenderStr += data.code;
                        } else {
                          tenderStr += data.code+",";
                        }
                    });

                  httpService.get($scope, setUrl+'/'+selectData[0].id+'/'+tenderStr).success(function(){
                    load();
                  });
              }else{
                   submitTips('所选数据未进行保存！', 'warning');
                   return;
              }

          }
        }else if(operation=='addData'){
            /*保存之前校验是否填写完整*/
            for(var i=0;i<$scope.tableGrid.data.length;i++){
                var obj = $scope.tableGrid.data[i];
                if(!obj.upperLimitRatio || !obj.measurePaySet || !obj.mobilizeAdvancePayRatio || !obj.monthDeductedMobilizeAdvanceRatio || !obj.mobilizeAdvanceDeductedRatio
                  || !obj.detainRetentionMoneyRatio || !obj.cumulativeDetainRetentionMoneyLimit){
                    submitTips('数据未填写完整不能进行保存操作！', 'warning');
                    return;
                }
            }
            /*保存*/
            listService.saveRows($scope, "api/measureBsicDataConfig/save", $scope.tableGrid, null, function() {
               load();
            });
        }
    }
    
    var load = $scope.load = function(){
      listService.loadData($scope, 'api/measureBsicDataConfig/query', $scope.tableGrid, null, null, function(response){
          /*基础数据查询时, 如果数据为空,则为第一次进入页面, 此时按当前登陆人取其下级标段构造数据*/
          var tableData = new Array();
          if(response.totalRows == 0){
            /*
             *获取当前用户所有标段数据,构造列表
             *如果选择标段则只根据当前选择标段进行数据构造,
             *否则以当前用户下所有标段
             */
            if(orgId.params && orgId.params.length > 0){
              /*遍历选中数据*/
               angular.forEach(orgId.params, function(data, index) {
                  httpService.get($scope, 'api/org/edit/'+data).success(function(response){
                    var dataJson = new Object();
                    dataJson.orgName = response.name;
                    dataJson.isNewRow = true;
                    dataJson.orgId = data;
                    dataJson.isEdit = 0;
                    tableData.push(dataJson);
                  })
               });
            }else {
              var tenders = $rootScope.userTenders;
              angular.forEach(tenders, function(data, index) {
                  var dataJson = new Object();
                  dataJson.orgName = data.remark;
                  dataJson.isNewRow = true;
                  dataJson.orgId = data.code;
                  dataJson.isEdit = 0;
                  tableData.push(dataJson);
              });
            }
             
          }else {
             /*如果有数据, 查询条件中不再列表数据中的重新构造*/
             var orgMap = new Map();
             var tenders = new Array();
             if(orgId.params &&  orgId.params.length > 0){
                tenders = orgId.params;
             }else {
                tenders = $rootScope.userTenders;
             }
             /*遍历已返回列表数据, 将列表数据表示为修改, isEdit = 1*/
             angular.forEach(response.content, function(data, index) {
                orgMap.put(data.orgId, data.orgId);
                data.isNewRow = true;
                data.isEdit = 1;
                tableData.push(data);
             });
             /*遍历标段数据, 如果标段数据不再列表中则重新构造*/
              angular.forEach(tenders, function(data, index) {
                  if(orgId.params &&  orgId.params.length > 0){
                    /*如果查询条件进行了选择, 构造查询条件中有但列表中没有的数据*/
                      if(!orgMap.get(data)){
                        httpService.get($scope, 'api/org/edit/'+data).success(function(response){
                          var dataJson = new Object();
                          dataJson.orgName = response.name;
                          dataJson.isNewRow = true;
                          dataJson.orgId = data;
                          dataJson.isEdit = 0;
                          tableData.push(dataJson);
                        })
                      }
                  }else{
                    if(!orgMap.get(data.code)){
                      var dataJson = new Object();
                      dataJson.orgName = data.remark;
                      dataJson.isNewRow = true;
                      dataJson.orgId = data.code;
                      dataJson.isEdit = 0;
                      tableData.push(dataJson);
                    }
                  }
                  
              });
          }
          $scope.tableGrid.data = tableData;
      }); 
    }
    
     load();
     
    $scope.search = function() {
       tenantId.value = $rootScope.tenantId;
       load();
    }

}).filter('paySetFilter', function() {
  var paySetHash = {
    1: '按合同清单',
    2: '按复核清单',
    3: '按最小数量'
  };

  return function(input) {
    if (!input){
      return '';
    } else {
      return paySetHash[input];
    }
  };
})/*.filter('numFilter', function(){
    return function(input){
        var num = parseFloat(formatValue(input));
        console.log(cnex4_is_positive_number(num));
        if(cnex4_is_positive_number(num)){
          if(num < 0 || num > 100){
            submitTips('所填数据必须在0-100之间！', 'warning');
            return "";
          }
          return input;
        }else {
           submitTips('数字格式错误！', 'warning');
           return "";
        }
    };
})*/;

