/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('huatek.controllers').controller('PhiGameConfigAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService, listService) {
    /***
     * 定义编辑跳转的路径.
     */
    var addDataUrl = 'api/phiGame/add';
    var editDataUrl = 'api/phiGame/edit';
    var homeUrl = '/phiGame/home';
    $scope.editId = $scope.passParams;

    //定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '游戏配置'));
    $scope.columnWrapArray = columnWrapArray;

    //定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
    $scope.gameType = new FormElement(1, '游戏类型', 'gameType', 'string', 1, '30');
    formFieldArray.push($scope.gameType);
    $scope.gameName = new FormElement(1, '前台显示游戏名称', 'gameName', 'string', 1, '8');
    formFieldArray.push($scope.gameName);
    //单次消耗积分
    $scope.oneTimeScore = new FormElement(1, '积分配置', 'oneTimeScore', 'number', 1, '30');
    formFieldArray.push($scope.oneTimeScore);
    $scope.freeTimesDay = new FormElement(1, '单日免费次数', 'freeTimesDay', 'number', 1, '30');
    formFieldArray.push($scope.freeTimesDay);
    $scope.drawMax = new FormElement(1, '抽奖次数上限', 'drawMax', 'number', 1, '30');
    formFieldArray.push($scope.drawMax);
    $scope.taskSwitch = new FormElement(1, '是否开启', 'taskSwitch', 'string', 1, '30');
    $scope.taskSwitch.items = [{ "name": "是", "code": "on" }, { "name": "否", "code": "off" }];
    formFieldArray.push($scope.taskSwitch);
    $scope.remark = new FormElement(1, '权限说明', 'remark', 'string', 1, '128', 'textarea');
    formFieldArray.push($scope.remark);

    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;
    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
     */
    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);

    $scope.tableGridConfigCoup = {
        columnDefs: [
            //{ name: '编号', field: 'detailId', width: '10%', enableColumnMenu: false, cellTemplate:"<input ng-model='row.entity.detailId' ></input>"},
            { name: '位置', field: 'location', width: '20%', enableColumnMenu: false, cellTemplate: "<input ng-disabled='row.entity.location' ng-model='row.entity.location' ></input>" },
            { name: '积分', field: 'score', width: '30%', enableColumnMenu: false, cellTemplate: "<input ng-model='row.entity.score' ></input>" },
            { name: '客户端显示文字', field: 'clientShow', width: '30%', enableColumnMenu: false, cellTemplate: "<input ng-model='row.entity.clientShow' ></input>" },
            { name: '奖项比例（%）', field: 'prizeRate', width: '20%', enableColumnMenu: false, cellTemplate: "<input type='number' ng-model='row.entity.prizeRate'></input>" }
            //{ name: '操作', field: 'delete', width: '30%', enableColumnMenu: false, cellTemplate: '<a><span style="cursor:pointer;" ng-click="grid.appScope.deletedata(row)">删除</span></a></div>' }
        ]
    };
    $scope.tableGridConfigCoup.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        $scope.isdisabled = true;
        //gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {   var field = colDef.field; });
        /*调整grid底部高度*/
        listService.setGridHeight();
    };

    /**
     *加载编辑数据
     */
    if ($scope.editId) {
        httpService.get($scope, editDataUrl + "/" + $scope.editId).success(function(res) {
            $scope.gameType.value = res.gameType;
            $scope.gameName.value = res.gameName;
            $scope.oneTimeScore.value = res.oneTimeScore;
            $scope.freeTimesDay.value = res.freeTimesDay;
            $scope.drawMax.value = res.drawMax;
            $scope.taskSwitch.value = res.taskSwitch;
            $scope.remark.value = res.remark;
            $scope.tableGridConfigCoup.data = res.phiGameConfigs;
        });
    }

        
    /***
     * 定义更新操作.
     */
    $scope.update = function() {
   
        var data = editService.getPostData($scope);
        console.log(data);

        data.phiGameConfigs = $scope.tableGridConfigCoup.data;

        console.log(data.phiGameConfigs);
        httpService.post($scope, 'api/phiGame/edit/' + $scope.editId, data,data.phiGameConfigs).success(function() {
            $scope["jsPanel"].close();

        });
    }
    /**
     * 定义保存操作.
     */
    $scope.save = function() {
        //  editService.saveData(addDataUrl, homeUrl);
    }

});