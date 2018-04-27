'use strict';

/**
 * 计量周期填写Controller
 */
angular.module('huatek.controllers').controller('BusiMeasureCycleSettingDetailController', function ($scope, $location, $http, listService,$modal,$rootScope, httpService, $localStorage) {
  

   var dateEditTemplate = function(){
       return '<div><form name="inputForm"><input  type="text" ng-model="MODEL_COL_FIELD" data-autoclose="true" ng-class="\'colt\' + col.uid" ui-grid-editor placement="bottom-right" bs-datepicker></form></div>'
      
/*<div><form name="inputForm"><input type="text" ng-class="\'colt\' + col.uid" ui-grid-editor ng-model="MODEL_COL_FIELD"></form></div>*/
    }
  /***
   * 定义显示的表格. 
   */
  $scope.tableGrid = {
        /*paginationPageSizes: [10, 50, 100,1000],
        paginationPageSize: 10,
        useExternalPagination: true,*/
        enableFullRowSelection : true,
        enableSelectAll : false,
        columnDefs: [
          { name: '序号', field: 'serialNumber',width: '5%',enableColumnMenu: false ,cellTemplate: '<a style="color:black;">{{grid.rows.indexOf(row)+1}}</a>'},
          { name: '期号', field: 'issueNumber',width: '10%', enableColumnMenu: false},
          /*{ name: '开始日期',  field: 'startDate',width: '20%', enableColumnMenu: false , cellFilter: 'date:"yyyy-MM-dd"', editableCellTemplate: dateEditTemplate()},
          { name: '结束日期',  field: 'endDate',width: '20%', enableColumnMenu: false, cellFilter: 'date:"yyyy-MM-dd"', editableCellTemplate: dateEditTemplate()},*/
          { name: '开始日期',  field: 'startDate',width: '20%', enableColumnMenu: false, type: 'date', cellFilter: 'date:"yyyy-MM-dd"'},
          { name: '结束日期',  field: 'endDate',width: '20%', enableColumnMenu: false, type: 'date', cellFilter: 'date:"yyyy-MM-dd"'},
          { name: '年份',  field: 'year',width: '20%', enableColumnMenu: false, enableCellEdit :false},
          { name: '月份',  field: 'month',width: '20%', enableColumnMenu: false, enableCellEdit :false},
        ],

    };
    
   
  $scope.tableGrid.onRegisterApi = function(gridApi){
        $scope.gridApi = gridApi;
        /*gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
          queryPage.page = newPage;
          queryPage.pageSize = pageSize;
        });*/
          /*load();*/
          gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef, newValue, oldValue){
            var rowIndex = $scope.tableGrid.data.indexOf(rowEntity);
            var num = parseFloat(formatValue(newValue));
            var field = colDef.field;
            if(field == 'startDate' ){
               var startDateVal = dateFormat(new Date(newValue), 'yyyy-MM-dd');
                /*开始日期不能小于上移周期结束时间*/
               if(rowIndex > 0){
                  /*获取上一级结束时间*/
                  var lastRow = $scope.tableGrid.data[rowIndex-1];
                  var laStartDate = new Date(lastRow['endDate']);
                  var newStartDate = new Date(newValue);
                  /*当前开始时间与上一级结束时间进行比较*/
                  if(laStartDate.getTime() >= newStartDate.getTime()){
                    submitTips('开始日期不能早于上一周期结束日期！', 'warning');
                    rowEntity[field] = null;
                  }else {
                    rowEntity[field] = startDateVal;
                  }
               }
               /*带出年份月份*/
               var year = startDateVal.split("-")[0];
               var month = startDateVal.split("-")[1];
               rowEntity['year'] = year;
               rowEntity['month'] = month;
            }else if(field == 'endDate'){
              if(rowEntity['startDate']){
                var endDateVal = dateFormat(new Date(newValue), 'yyyy-MM-dd');
                 /*结束日期不能小于开始日期*/
                 var startDate = new Date(rowEntity['startDate']);
                 var endDate = new Date(endDateVal);
                 console.log(startDate.getTime());
                 console.log(endDate.getTime());
                 if(startDate.getTime() > endDate.getTime()){
                    submitTips('结束日期不能早于开始日期！', 'warning');
                    rowEntity[field] = oldValue;
                 }else {
                    rowEntity[field] = endDateVal;
                 }
                
              }else {
                 submitTips('请先填写开始日期！', 'warning');
                 rowEntity[field] = null;
              }
            }
           
            $scope.$apply();
          });
        /*调整grid底部高度*/    
        listService.setGridHeight();
  }; 
   listService.init($scope, $http, $scope.tableGrid);
  
    var queryPage = new QueryPage(1,10,"id asc","false");
    var tenantId=new queryParam('tenantId','tenantId','string','=');
    tenantId.value = $rootScope.tenantId;
    tenantId.isShow = false;
    queryPage.addParam(tenantId);
    var settingId=new queryParam('settingId','busiMeasureCycleSetting.id','string','=');
    settingId.value = $scope.passParams.id;
    settingId.isShow = false;
    queryPage.addParam(settingId);
       
    listService.setQueryPage($scope, queryPage);
   
    var btnArray = [];
        btnArray.push(new pageButton('新增','add','/measureCycleSetDetail/add','addData'));
        btnArray.push(new pageButton('保存','save','/measureCycleSetDetail/save','saveData'));
        btnArray.push(new pageButton('删除','delete','/measureCycleSetDetail/delete','deleteData'));
          
   listService.setButtonList($scope, btnArray);

    $scope.execute = function(operation, param){
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if(operation=='addData'){
          /*新增行*/
            $scope.tableGrid.data.push({"isNewRow": true, "busiMeasureCycleSettingId": $scope.passParams.id, "isEdit": 0});
        }else if(operation=='saveData'){
          /*保存*/
          listService.saveRows($scope, "api/measureCycleSetting/save", $scope.tableGrid, null, function() {
               load();
          });

        }else if(operation=='deleteData'){
          /*删除, 删除时校验周期是否已经在计量中使用*/
          if (listService.selectOne($scope.gridApi)) {
              /*未保存周期直接删除*/
              if(selectData[0].id){
                  listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, 'api/measureCycleSetting/deleteCycleDetail');
              }else {
                /*直接删除*/
                $scope.tableGrid.data.remove(selectData[0]);
              }
          }
        }
    }

    var load = $scope.load = function(){
      listService.loadData($scope, 'api/measureCycleSetting/queryCycleSettingDetail', $scope.tableGrid, null, null, function(response){
        /*如果计量类型为中间计量当列表数据为0时通过项目开工日期添加周期*/
        if($scope.passParams && $scope.passParams.measureType == 'middleMeasure' && response.totalRows == 0){
          /*
           *根据项目开工日期, 竣工日期构造数据
           *获取项目开工日期和竣工日期接口还未完成, 暂时写死
           */
          var beginDate = '2017-05-03';
          var endDate = '2017-09-12';
          $scope.createMiddleMeasureDetail($scope.tableGrid, beginDate, endDate, 1);
         

        }else if($scope.passParams && $scope.passParams.measureType != 'middleMeasure' && response.totalRows == 0){
          //  构造一条数据
          $scope.tableGrid.data.push({"issueNumber": "第1期", "isNewRow": true , "busiMeasureCycleSettingId": $scope.passParams.id, "isEdit": 0});
        }
      }); 
    }

    /**
     * 根据项目开工日期和竣工日期构造中间计量明细数据
     *
     * @param      {<type>}  tableGrid  The table grid
     * @param      {<type>}  beginDate  The begin date
     * @param      {<type>}  beginDate  The begin date
     */
    $scope.createMiddleMeasureDetail = function(tableGrid, beginDate, endDate, i){
      /*如果开工日期和竣工日期在同一月之内, 则只构造一条数据*/
      /*获取下一月日期*/
      var tempEndDate = new Date(getNextMonth(beginDate));

      if(tempEndDate.getTime() >= new Date(endDate)){
         $scope.tableGrid.data.push({"issueNumber": "第"+i+"期", "isNewRow": true , "busiMeasureCycleSettingId": $scope.passParams.id, "isEdit": 0, "startDate":beginDate, "endDate":endDate, "year":beginDate.split("-")[0], "month":beginDate.split("-")[1]});
        return;
      }else{
         $scope.tableGrid.data.push({"issueNumber": "第"+i+"期", "isNewRow": true , "busiMeasureCycleSettingId": $scope.passParams.id, "isEdit": 0, "startDate":beginDate, "endDate":tempEndDate, "year":beginDate.split("-")[0], "month":beginDate.split("-")[1]});
         var newBeginDate = new Date(tempEndDate.getTime() + 24*60*60*1000); //后一天
         i++;
         var newBeginDateTemp = newBeginDate.getUTCFullYear()+'-'+(newBeginDate.getUTCMonth()*1+1)+"-"+newBeginDate.getUTCDate();
         /*开始时间加1*/
         $scope.createMiddleMeasureDetail(tableGrid, newBeginDateTemp, endDate, i);
      }

    }

    /**
     * 获取当前日期下一个月数据
     *
     * @param      {<type>}                         date    The date
     * @return     {(Date|Function|number|string)}  The next month.
     */
    function getNextMonth(date) {
            var arr = date.split('-');
            var year = arr[0]; //获取当前日期的年份
            var month = arr[1]; //获取当前日期的月份
            var day = arr[2]; //获取当前日期的日
            var days = new Date(year, month, 0);
            days = days.getDate(); //获取当前日期中的月的天数
            var year2 = year;
            var month2 = parseInt(month) + 1;
            if (month2 == 13) {
                year2 = parseInt(year2) + 1;
                month2 = 1;
            }
            var day2 = day;
            var days2 = new Date(year2, month2, 0);
            days2 = days2.getDate();
            if (day2 > days2) {
                day2 = days2;
            }
            if (month2 < 10) {
                month2 = '0' + month2;
            }
        
            var t2 = year2 + '-' + month2 + '-' + day2;
            return t2;
        }
    
    load();
     
    $scope.search = function() {
       tenantId.value = $rootScope.tenantId;
       load();
    }

});
