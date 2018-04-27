'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiPlusAllRightController', function($scope, $location, $http, $rootScope, listService, httpService, shareData) {

    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        multiSelect: true,
        columnDefs: [
            { name: '序号', field: 'id', width: '8%', enableColumnMenu: false },
            { name: '权限名称', field: 'rightName', width: '12%', enableColumnMenu: false },
            { name: '权限说明', field: 'remark', width: '18%', enableColumnMenu: false },
            { name: '奖励', field: 'award', width: '12%', enableColumnMenu: false },
            { name: '是否开启', field: 'isValidate1', width: '12%', enableColumnMenu: false,decode: { field: "isValidate", dataKey: "dic.isValidate"}},
            { name: '操作人员', field: 'operationperson', width: '10%', enableColumnMenu: false },
            { name: '末次操作时间', field: 'lastoperationtime', width: '15%', enableColumnMenu: false },
            { name: '操作', field: 'operate', width: '12%', enableColumnMenu: false, cellTemplate: '<a><span style="cursor:pointer;" ng-click="grid.appScope.edit(row.entity.id,row.entity.tasktype)">权限详情&nbsp;&nbsp;&nbsp</span><span ng-if="row.entity.isValidate==\'off\'" ng-click="grid.appScope.on(row.entity.id,row.entity.isValidate)">任务开启</span><span ng-if="row.entity.isValidate==\'on\'" ng-click="grid.appScope.off(row.entity.id,row.entity.isValidate)">任务关闭</span></a></div>' }
        ]
    };

    $scope.edit = function(id, tasktype) {
        var scope = $rootScope.$new();
        //var forPayPoints =  new popup("消费返积分 ",'',{'id':id},'','','','PhiPlusAllRightAddController',"/static/phicom/plusmember/template_add.html");
        //listService.popPanel(scope, forPayPoints);
        var forPayPoints = {
            title: "消费返积分特权",
            passParams: id,
            onclosedFun: load,
            controller: 'PhiPlusAllRightAddController',
            templateView: "/static/phicom/plusmember/template_add.html",
        };
        var firstExclusive = {
            title: "plus会员首次开通 ",
            passParams: id,
            onclosedFun: load,
            controller: 'PhiPlusAllRightAddController',
            templateView: "/static/phicom/plusmember/template_edit.html",
        };
        var everyMothExclusive = {
            title: "plus会员每月专享 ",
            passParams: id,
            onclosedFun: load,
            controller: 'PhiPlusAllRightAddController',
            templateView: "/static/phicom/plusmember/template_home_edit.html",
        };
        switch (tasktype) {
            case "forPayPoints":
                listService.popPanel($scope, forPayPoints);
                break;
            case "firstExclusive":
                listService.popPanel($scope, firstExclusive);
                break;
            case "everyMothExclusive":
                listService.popPanel($scope, everyMothExclusive);
                break;
            default:
                break;
        }
    }


    $scope.on = function(id, isValidate) {
        httpService.post($scope, 'api/phiPlusAllRight/switch/' + id+ '/' + isValidate, data).success(function() {
        });
        load();
     }
    
    $scope.off = function(id, isValidate) {
        httpService.post($scope, 'api/phiPlusAllRight/switch/' + id+ '/' + isValidate, data).success(function() {

        });
        load();
    }


    //定义查询页
    var queryPage = new QueryPage(1, 10, "id desc", "false");


    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);
    /***
     * 注册gridApi的选择器.
     */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
    };


    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
    }

    /***
     * 初始化加载数据.
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/phiPlusAllRight/query', $scope.tableGrid, null, null, function(response) {
            console.log(response);
        });
    }
    load();

    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };

});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiPlusAllRightAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService, listService) {
    /***
     * 定义编辑跳转的路径.
     */
    var addDataUrl = 'api/phiPlusAllRight/add';
    var editDataUrl = 'api/phiPlusAllRight/edit';
    var homeUrl = '/phiPlusAllRight/home';
    $scope.editId = $scope.passParams;
    //定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    var formFieldArray = [];
    $scope.rightName = new FormElement(1, '权限名称', 'rightName', 'string', 1, '30');
    formFieldArray.push($scope.rightName);
    $scope.tasktype = new FormElement(1, '权限类型', 'tasktype', 'string', 1, '30');
    formFieldArray.push($scope.tasktype);
    $scope.tasktype.isShow = false;
    $scope.scoreOrMutiply = new FormElement(1, '权限设置', 'scoreOrMutiply', 'string', 0, '30');
    $scope.scoreOrMutiply.items = [{ "name": "翻倍设置", "code": 0 }, { "name": "额外增加", "code": 1 }];
    $scope.scoreOrMutiply.event = function(val) {
        console.log(val);
        if (val == 0) {
            $scope.mutiplyRead == true;
            $scope.scorevalue1.value = null;
        } else if (val == 1) {
            $scope.scoreRead == true;
            $scope.scorevalue2.value = null;
        }

    }
    formFieldArray.push($scope.scoreOrMutiply);

    $scope.scorevalue2 = new FormElement(1, '翻倍设置', 'scorevalue2', 'number', 0, '30');
    $scope.scorevalue2.minValue = 0;
    $scope.scorevalue2.decimals = 0;
    formFieldArray.push($scope.scorevalue2);
    $scope.scorevalue1 = new FormElement(1, '额外增加', 'scorevalue1', 'number', 0, '30');
    $scope.scorevalue1.minValue = 0;
    // $scope.oneTimeScore.maxValue = 10;
    $scope.scorevalue1.decimals = 0;
    formFieldArray.push($scope.scorevalue1);
    
    $scope.startTip = new FormElement(1, '发放时机', 'startTip', 'string', 0, '30');
    formFieldArray.push($scope.startTip);

    $scope.award = new FormElement(1, '礼包配置', 'award', 'string', 0, '30');
    formFieldArray.push($scope.award);

    $scope.isValidate = new FormElement(1, '是否开启', 'isValidate', 'string', 1, '30');
    $scope.isValidate.items = [{ "name": "是", "code": "on" }, { "name": "否", "code": "off" }];
    formFieldArray.push($scope.isValidate);
    $scope.remark = new FormElement(1, '权限说明', 'remark', 'string', 1, '128', 'textarea');
    formFieldArray.push($scope.remark);

    $scope.chongzhi = new FormElement(1, '重置', null, 'string', 1, '30');
    formFieldArray.push($scope.chongzhi);

    //设置全局变量
    //	    $scope.formFieldArrayList = formFieldArray;
    $rootScope.formFieldArray = formFieldArray;
    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
     */
    editService.init($scope, $http);

    editService.setFormFields($scope, formFieldArray);


    var cpnsWayTemplate = function() {
        return "<div >"+
        "<select ng-model='row.entity.cpnsWayId'>" +
            "<option ng-model='row.entity.cpnsWayId' ng-repeat='item in row.entity.cpnsWayItems' value ='{{item.cpnsWayId}}'>{{item.cpnsWayId}}</option>" +
            "</select>"+
            "</div>";
    }
    $scope.tableGridCoup = {
        columnDefs: [
            //{ name: '编号', field: 'detailId', width: '10%', enableColumnMenu: false, cellTemplate:"<input ng-model='row.entity.detailId' ></input>"},
            //{ name: '优惠券面值', field: 'cpnsMoney', width: '30%', enableColumnMenu: false,cellTemplate:"<input ng-model='row.entity.cpnsMoney' ng-blur='grid.appScope.editCpnsMoney(row)' ></input>" },
            { name: '优惠券名称', field: 'cpnsName', width: '20%', enableColumnMenu: false,cellTemplate:"<input ng-model='row.entity.cpnsName' ng-blur='grid.appScope.editCpnsName(row)' ></input>" },
            {
                name: '优惠券方案id',
                field: 'cpnsWayId',
                width: '20%',
                enableColumnMenu: false,
                cellTemplate: cpnsWayTemplate()
            },
            { name: '优惠券张数', field: 'cpnsQuantity', width: '30%', enableColumnMenu: false, type: 'number'},
            { name: '操作', field: 'delete', width: '30%', enableColumnMenu: false, cellTemplate: '<a><span style="cursor:pointer;" ng-click="grid.appScope.deletedata(row)">删除</span></a></div>' }
        ]
    };
    $scope.tableGridCoup.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        //gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {   var field = colDef.field; });
        /*调整grid底部高度*/
        listService.setGridHeight();
    };
    $scope.editCpnsName =function(row){
    	httpService.get($scope, 'api/phiCoupons/select/' + row.entity.cpnsName).success(function(response) {
                row.entity.cpnsWayItems = response;
    	});
  }
  /*$scope.editCpnsMoney = function(row){
  	 httpService.get($scope, 'api/phiCoupons/choose/' + row.entity.cpnsMoney).success(function(response) {
                row.entity.cpnsWayItems = response;
            });
  }*/
    $scope.deletedata = function(row){
    	console.log(row);
    	console.log($scope.detailId);
    	//listService.deleteData($scope, $scope.tableGridCoup,  $scope.tableGridCoup.onRegisterApi, 'api/phiPlusAllRight/delete/');
    	httpService.get($scope, 'api/phiPlusAllRight/delete/' +row.entity.detailId).success(function(response) {
    		$scope.tableGridCoup.data.remove(row.entity);
    	 });
    	
    }
    $scope.chongzhi.clickEvent = function() {
        var taskType = $scope.tasktype.value;
       // $scope.isValidate.value = null;
        $scope.remark.value;
        httpService.get($scope, editDataUrl + "/" + $scope.editId).success(function(response) {
			$scope.isValidate.value = response.isValidate;
	   		$scope.remark.value = response.remark;
        if (taskType == "forPayPoints") {
        	$scope.scoreOrMutiply.value = response.scoreOrMutiply ;
            $scope.scorevalue1.value = response.scorevalue1;
            $scope.scorevalue2.value = response.scorevalue2;
        } else {
            $scope.award.value = response.award;
            if(response.plusRightGiftBagDetailsList && response.plusRightGiftBagDetailsList.length > 0){
            	for (var i = 0 ; i < response.plusRightGiftBagDetailsList.length; i ++ ) {
            		response.plusRightGiftBagDetailsList[i].cpnsWayItems = [{"cpnsWayId":response.plusRightGiftBagDetailsList[i].cpnsWayId}];
            		}
            	}
           	$scope.tableGridCoup.data = response.plusRightGiftBagDetailsList;
            $scope.startTip.value = response.startTip;
            }  
       });
    }



    /**
     *加载编辑数据
     */
    if ($scope.editId) {
        httpService.get($scope, editDataUrl + "/" + $scope.editId).success(function(res) {
       
            $scope.tasktype.value = res.tasktype;
            var tasktype = $scope.tasktype.value;
            $scope.rightName.value = res.rightName;
            $scope.isValidate.value = res.isValidate;
            $scope.remark.value = res.remark;
            $scope.award.value = res.award;
            if(tasktype == "forPayPoints"){
            	$scope.scoreOrMutiply.value = res.scoreOrMutiply;
            	$scope.scorevalue2.value = res.scorevalue2;
            	$scope.scorevalue1.value = res.scorevalue1;
            }else{
            	if(res.plusRightGiftBagDetailsList && res.plusRightGiftBagDetailsList.length > 0){
            	for (var i = 0 ; i < res.plusRightGiftBagDetailsList.length; i ++ ) {
            		res.plusRightGiftBagDetailsList[i].cpnsWayItems = [{"cpnsWayId":res.plusRightGiftBagDetailsList[i].cpnsWayId}];
            		console.log(res.plusRightGiftBagDetailsList[i].cpnsWayId);
            		}
            	}
           		$scope.tableGridCoup.data = res.plusRightGiftBagDetailsList;
            	$scope.startTip.value = res.startTip;
            }
            
        });
        // listService.loadData($scope, editDataUrl + "/" + $scope.editId, $scope.tableGridCoup);
    }



    /**
     *修改
     */
    $scope.update = function() {

        var data = editService.getPostData($scope);
        data.plusRightGiftBagDetailsList = $scope.tableGridCoup.data;
        
/*        var award = $scope.award.value;
        data.plusRightGiftBagDetailsList = $scope.tableGridCoup.data;
        var monery  = 0;
        var quantity = 0;
        var monerConfig =  monery * quantity;
        var acountMonery = 0;
        if(data.plusRightGiftBagDetailsList && data.plusRightGiftBagDetailsList.length > 0){
            	for (var i = 0 ; i < data.plusRightGiftBagDetailsList.length; i ++ ) {
            		monery = data.plusRightGiftBagDetailsList[i].cpnsMoney;
            		quantity = data.plusRightGiftBagDetailsList[i].cpnsQuantity;
            		 monerConfig = monery * quantity;
            		 acountMonery += monerConfig; 
            		console.log(acountMonery)
            	}
            	if(award < acountMonery){
						submitTips('警告!礼包优惠券总面值不能大于礼包配置金额！', 'warning');
                    	return;
            		}
         }*/
        
        console.log(data);
        console.log( data.plusRightGiftBagDetailsList);
        var flag = true;
        //scoreOrMutiply : 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分） 
	    var scoreorMutiply =  $scope.scoreOrMutiply.value;
	    if(null != scoreorMutiply ){
    	  if(scoreorMutiply == '0'){
	    	   //scoreValue2 :倍数（翻倍时）
	    	   var mutiplyNumber = $scope.scorevalue2.value;
	    	   if(mutiplyNumber == null || mutiplyNumber== undefined){
	               submitTips('数据输入错误:积分翻倍数量不能为空！', 'error');
	               flag = false;
	               return;
	    	   }else{
	    		   flag = true;
	    	   }
	       }else if(scoreorMutiply == '1'){
	    	   //scoreValue1 :积分值1(额外积分数值)
	    	   var scoreValue1 =$scope.scorevalue1.value;
	    	   if(scoreValue1 == null || scoreValue1== undefined){
	               submitTips('数据输入错误:额外积分数值不能为空！', 'error');
	               flag = false;
	               return;
	    	   }else{
	    		   flag = true;
	    	   }
	       }
	    }
        if(null != data.plusRightGiftBagDetailsList && data.plusRightGiftBagDetailsList.length >0 ){
            for(var i = 0; i< data.plusRightGiftBagDetailsList.length; i++){
                var cpnsNumber = data.plusRightGiftBagDetailsList[i].cpnsQuantity;
                var cpnsName = data.plusRightGiftBagDetailsList[i].cpnsName;
                if(cpnsName == null || cpnsName == '' || cpnsName== undefined){
                    submitTips('数据输入错误:优惠券名称不能为空！', 'error');
                    flag = false;
                    break;
                }else {
                	  flag = true;
                }
                if(cpnsNumber == null || cpnsNumber== undefined){
                    submitTips('数据输入错误:优惠券数量不能为空！', 'error');
                    flag = false;
                    break;
                }else {
                	  flag = true;
                }
            }
        }
        console.log(flag)
        if(flag){
            httpService.post($scope, 'api/phiPlusAllRight/edit/' + $scope.editId, data).success(function() {
            });
          /*back();*/
            $scope["jsPanel"].close();
        }
        // 	editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    }
    /**
     * 定义保存操作.
     */
    $scope.save = function() {
    }

	 $scope.addRow = function() {
    	if(!$scope.tableGridCoup.data){
    		$scope.tableGridCoup.data = [];
    	}
       $scope.tableGridCoup.data.push({"giftbagId":$scope.editId});
    }

});