/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiWinnersAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService, listService, cacheService) {
    $scope.kaijiangwanchengDeis = true;
    if($scope.passParams.winnerStatus == "2"){
        $scope.lookModel = true;
    }
    //定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    var editId = $scope.passParams.productId;
    //定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
    $scope.id = new FormElement(1, '', 'id', 'string', 1, '30');
    $scope.id.value = $scope.passParams.productId;
    formFieldArray.push($scope.id);
    $scope.winnerStatus = new FormElement(1, '', 'winnerStatus', 'string', 1, '30');
    $scope.winnerStatus.value = $scope.passParams.winnerStatus;
    //console.log($scope.winnerStatus.value);
    $scope.winnerStatus.value = $scope.passParams.winnerStatus;
    formFieldArray.push($scope.winnerStatus);
    $scope.productName = new FormElement(1, '商品名称', 'productName', 'string', 1, '30');
    $scope.productName.value = $scope.passParams.productName;
    formFieldArray.push($scope.productName);
    $scope.cashconunt = new FormElement(1, '兑奖人数', 'cashconunt', 'string', 1, '30');
    formFieldArray.push($scope.cashconunt);
    $scope.opencount = new FormElement(1, '开奖总人数', 'winnerCounts', 'string', 1, '30');
    $scope.opencount.value = $scope.passParams.winnerCounts == null ? 0 : $scope.passParams.winnerCounts;
    formFieldArray.push($scope.opencount);
    $scope.Manual = new FormElement(1, '手动开奖', 'manualCounts', 'string', 1, '30');
    $scope.Manual.value = $scope.passParams.manualCounts == null ? 0 : $scope.passParams.manualCounts;
    formFieldArray.push($scope.Manual);
    $scope.selectMember = new FormElement(1, '选择', "selectMember", 'string', 1, '30', 'select-autocomplete');
    formFieldArray.push($scope.selectMember);
    $scope.btnxuanze = new FormElement(1, '加入中奖', null, 'string', 1, '30');
    //手动开奖
    $scope.btnxuanze.event = function() {
          var btnxuanze = tocheckValue();
         // console.log(btnxuanze)
          console.log("===========btnxuanze==========="+btnxuanze);
          if(!btnxuanze){
            console.log("===========!btnxuanze==========="+btnxuanze);
              submitTips('开奖人数过多', 'warning');
              return;
          }
        if ($scope.selectMember.value == null) {
            submitTips('请选择需要手动加入的会员！', 'warning');
           }else {
                        var isexsits = false;
                       // console.log($scope.tableGrid.data);
                        if ($scope.tableGrid.data && $scope.tableGrid.data.length > 0) {
                            for (var i = 0; i < $scope.tableGrid.data.length; i++) {
                                if ($scope.tableGrid.data[i].memberId == $scope.selectMember.value) {
                                    isexsits = true;
                                    break;
                                }
                            }
                        }
                        if (!isexsits) {
                            httpService.post($scope, "/api/phiWinners/Manual/" + editId + "/" + $scope.selectMember.value).success(function(res) {
                                res.userType_1 = cacheService.getPropertyName("dic.user_Type", res.userType);
                                res.winnersType_1 = cacheService.getPropertyName("dic.winners_type", res.winnersType);
                                $scope.tableGrid.data.push(res);
                                $scope.Manual.value += 1;
                                $scope.changeFinishBtnStatus();
                                submitTips('添加成功！', 'success');
                            });
                        } else {
                            submitTips('该用户已经在中奖列表中存在！', 'warning');
                        }
                }
    }
    formFieldArray.push($scope.btnxuanze);
    $scope.random = new FormElement(1, '随机开奖', 'randomCounts', 'string', 1, '30');
    //随机开奖
    $scope.random.value = $scope.passParams.randomCounts == null ? 0 : $scope.passParams.randomCounts;
    $scope.random.event = function() {
       var  checkend= tocheckValue();
         //console.log("checkend"+checkend);
          console.log("===========checkend==========="+checkend);
         if(!checkend){
              console.log("===========!checkend==========="+!checkend);
              submitTips('开奖人数过多', 'warning');
              return;
          }
        if ($scope.random.value < 1) {
            submitTips('请输入随机会员人数！', 'warning');
        } else {
                    var postData = [];
                    if ($scope.tableGrid.data && $scope.tableGrid.data.length > 0) {
                                for (var i = 0; i < $scope.tableGrid.data.length; i++) {
                                    if ($scope.tableGrid.data[i].winnersType != "1") {
                                        postData.push($scope.tableGrid.data[i]);
                                    }
                                }
                    }
                   // console.log(postData);
                    httpService.post($scope, "/api/phiWinners/Random/" + editId + "/" + $scope.random.value, postData).success(function(res) {
                                var resultData = [];
                                if ($scope.tableGrid.data && $scope.tableGrid.data.length > 0) {
                                    for (var i = 0; i < $scope.tableGrid.data.length; i++) {
                                        if ($scope.tableGrid.data[i].winnersType != "1") {
                                            resultData.push(cloneObj($scope.tableGrid.data[i]));
                                        }
                                    }
                                }
                                console.log(res.length);
                                if(res.length == 0){
                                       $scope.random.value = res.length;
                                       submitTips('兑奖用户已经添加完', 'warning'); 
                                }else{
                                            for (var i = 0; i < res.length; i++) {
                                            res[i].userType_1 = cacheService.getPropertyName("dic.user_Type", res[i].userType);
                                            res[i].winnersType_1 = cacheService.getPropertyName("dic.winners_type", res[i].winnersType);
                                            resultData.push(res[i]);
                                        }
                                        $scope.tableGrid.data = resultData;
                        
                                        $scope.changeFinishBtnStatus();
                                        submitTips('添加成功！', 'success'); 
                                   }
                                
                    }).error(function() {

                        });
        }
    }
    formFieldArray.push($scope.random);
    $scope.fake = new FormElement(1, '虚拟会员', 'fakeCounts', 'string', 1, '30');
    $scope.fake.value = $scope.passParams.fakeCounts == null ? 0 : $scope.passParams.fakeCounts;

    //虚拟用户；
    $scope.fake.event = function() {
         var fake =  tocheckValue();
         //console.log("===========fake==========="+fake);
          if(!fake){
            //console.log("===========!fake==========="+!fake);
              submitTips('开奖人数过多', 'warning');
              return;
          }
        if ($scope.fake.value < 1) {
            submitTips('请输入虚拟会员人数！', 'warning');
        } else {
            httpService.post($scope, "/api/phiWinners/fake/" + editId + "/" + $scope.fake.value).success(function(res) {
                        var resultData = [];
                          //console.log("++++++res.length+++++++"+res.length)
                          //    console.log("*****$scope.fake.value**********"+parseInt($scope.fake.value))
                        if ($scope.tableGrid.data && $scope.tableGrid.data.length > 0) {
                            for (var i = 0; i < $scope.tableGrid.data.length; i++) {
                                if ($scope.tableGrid.data[i].winnersType != "2") {
                                    resultData.push(cloneObj($scope.tableGrid.data[i]));
                                }
                            }                     
                            if(parseInt(res.length)<parseInt($scope.fake.value)){
                                 //console.log("++++++res.length+++++++"+res.length)
                              // console.log("*****$scope.fake.value**********"+parseInt($scope.fake.value))
                              // submitTips('虚拟人数不够','warning');
                              var res = parseInt(res.length);
                             submitTips('虚拟人数只有'+res+'人','warning');
                                return;
                            }else{
                                  for (var i = 0; i < res.length; i++) {
                                    console.log("1111111111111111111111111111111111111")
                                    res[i].userType_1 = cacheService.getPropertyName("dic.user_Type", res[i].userType);
                                    res[i].winnersType_1 = cacheService.getPropertyName("dic.winners_type", res[i].winnersType);
                                    resultData.push(res[i]);
                                }
                                $scope.tableGrid.data = resultData;
                                $scope.changeFinishBtnStatus();
                                submitTips('添加成功！', 'success');
                            }
                        }else if(parseInt(res.length)<parseInt($scope.fake.value)) {
                             var res = parseInt(res.length);
                             submitTips('虚拟人数只有'+res+'人','warning');
                             return;
                        }else{
                              for (var i = 0; i < res.length; i++) {
                            console.log("222222222222222222222222222222222")
                                    res[i].userType_1 = cacheService.getPropertyName("dic.user_Type", res[i].userType);
                                    res[i].winnersType_1 = cacheService.getPropertyName("dic.winners_type", res[i].winnersType);
                                    resultData.push(res[i]);
                                }
                                $scope.tableGrid.data = resultData;
                                $scope.changeFinishBtnStatus();
                                
                                submitTips('添加成功！', 'success');
                       
                        }
                                            
                         
                        
                               
                    });
                       
         
        }
    }
    formFieldArray.push($scope.fake);
    $scope.kaijiangwancheng = new FormElement(1, '开奖完成', 'kaijiangwancheng', 'string', 1, '30');
    $scope.kaijiangwancheng.readonly = true;
    $scope.kaijiangwancheng.event = function() {   
        var data = editService.getPostData($scope);
        data.phiWinnersListDtoList = $scope.tableGrid.data;
        data.winnerStatus = "2";//已开奖
        $scope.lookModel = true;
        console.log(data);
        httpService.post($scope, "/api/phiWinners/updateWinnerStatus/" + editId, data).success(function(res) {
            console.log(res);
            // load();
        });
    }
    
    var tocheckValue =function(){
        var ss = false;
        /*检查是否可以开奖*/
       var Manual = $scope.Manual.value;
       console.log("手动"+Manual);
       var randomValue = $scope.random.value;
       console.log("随机"+randomValue);
       var  fakeValue  = $scope.fake.value;
       console.log("虚拟"+fakeValue);
       console.log(fakeValue);
       //总开奖人数
       var count = $scope.opencount.value;
       console.log("开奖总人数"+count);
       var real = Number(Manual) + Number(randomValue) + Number(fakeValue);
       console.log("real"+real);
       if(parseInt(real) >parseInt(count)){
           submitTips('开奖人数过多', 'warning');
           console.log("===========ss==========="+ss);
           return ss ;
       }else{
           ss = true;
           console.log("===========ss==========="+ss);
           return ss;
       }

    }
    formFieldArray.push($scope.kaijiangwancheng);
    $scope.chongzhi = new FormElement(1, '重置', null, 'string', 1, '30');
    $scope.chongzhi.event = function() {
        $scope.Manual.value = 0
        $scope.random.value = 0 
        $scope.fake.value = 0    
        $scope.opencount.value = 0      
        $scope.tableGrid.data = [];
        submitTips('重置成功！', 'success');
    }
    formFieldArray.push($scope.chongzhi);
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
    editService.setFormFields($scope, formFieldArray);
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        multiSelect: true,
        columnDefs: [
            { name: '用户类型', field: 'userType_1', width: '20%', enableColumnMenu: false, decode: { field: "userType", dataKey: "dic.user_Type" } },
            { name: '中奖用户昵称', field: 'userName', width: '20%', enableColumnMenu: false },
            { name: '用户手机号', field: 'mobile', width: '20%', enableColumnMenu: false, },
            { name: '中奖方式', field: 'winnersType_1', width: '20%', enableColumnMenu: false, decode: { field: "winnersType", dataKey: "dic.winners_type" } },
            { name: '操作', field: 'operation', width: '15%', enableColumnMenu: false, cellTemplate: '<a ng-if="!grid.appScope.lookModel"><span style="cursor:pointer" ng-click="grid.appScope.deleteData(row)">删除</span></a>' }
        ]
    };

    $scope.deleteData = function(row) {
        //console.log(row);
        console.log("del111");
        console.log(row.entity);
        // console.log($scope.tableGrid.data.indexOf(row.entity));
        if ($scope.tableGrid.data.length > 0) {
            for (var i = 0; i < $scope.tableGrid.data.length; i++) {
                var delMember = $scope.tableGrid.data[i];
                if (delMember.$$hashKey == row.entity.$$hashKey) {
                     console.log("del222");
                     console.log(delMember);
                    $scope.tableGrid.data.remove(delMember);
                    break;
                }
            }
        }
       // console.log($scope.tableGrid.data)
        $scope.changeFinishBtnStatus();
       // $scope.opencount.value -= 1;
        var winnersType = row.entity.winnersType
        // console.log(winnersType);
        if(winnersType == 1){
            $scope.random.value -= 1;
        }else if(winnersType == 0){
            $scope.Manual.value -= 1;
        }else{
            $scope.fake.value -= 1;
        }        
        if($scope.opencount.value <0){
           $scope.opencount.value =0;          
        submitTips('删除成功！', 'success');
      }
    }
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/phiWinners/query/' + editId, $scope.tableGrid);
        //找到所有的中奖订单的数量
        httpService.post($scope, 'api/phiWinners/queryAllOrder/' + editId).success(function(res) {
            $scope.cashconunt.value = res;
        });
    }

    load();

    editService.initFieldItems($scope, $scope.selectMember, 'api/phiWinners/queryMember/' + editId);

    $scope.changeFinishBtnStatus = function(){
        if($scope.opencount.value > 0){
            if($scope.opencount.value == $scope.tableGrid.data.length){
                $scope.kaijiangwancheng.readonly = false;
            }
        }
    }
});