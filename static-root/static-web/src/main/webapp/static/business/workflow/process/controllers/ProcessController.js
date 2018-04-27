'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('processProgressController', function($scope, $location, $modal, $http, $rootScope, listService, excelService) {
    /***
     * 定义显示的表格.
     */

    $scope.canSetPerson = $scope.passParams.canSetPerson,
        $scope.processInstanceId = $scope.passParams.processInstanceId,
        $scope.tableGrid = {
            useExternalPagination: false,
            minRowsToShow: 4,
            enableFullRowSelection : true,
            enableSelectAll : false,
            multiSelect: false,
            columnDefs: [
                { name: '审批环节', field: 'name', width: '15%', enableColumnMenu: false },
                { name: '审批人(指定审批人)', field: 'assigneeName', width: '15%', enableColumnMenu: false, cellTemplate: returnCell },
                { name: '开始时间', field: 'startTime', width: '20%', enableColumnMenu: false },
                { name: '办结时间', field: 'endTime', width: '20%', enableColumnMenu: false },
                { name: '审批结果', field: 'result', width: '10%', enableColumnMenu: false },
                { name: '审批意见', field: 'resultMessage', width: '20%', enableColumnMenu: false, cellTemplate: '<div class="ui-grid-cell-contents"  ng-if="row.entity[\'resultMessage\']!=undefined" title="{{COL_FIELD}}" onmouseover="this.style.color=\'red\'" onmouseout="this.style.color=\'#333\'">{{COL_FIELD}}</div><div ng-if="row.entity[\'resultMessage\']==undefined" title="{{COL_FIELD}}" onmouseover="this.style.color=\'red\'" onmouseout="this.style.color=\'#333\'">{{COL_FIELD}}</div>' },
                /*{ name: '意见详情', width: '10%', enableColumnMenu: false,cellTemplate:'<a  ng-click="grid.appScope.showResultMessage(row.entity.resultMessage)">{{row.entity.resultMessage?"详情":""}}</a>'}*/
            ]

        };

    listService.init($scope, $http);

    function returnCell() {
        if ($scope.canSetPerson) {
            return '<a ng-if="!(row.entity.endTime!=undefined||!row.entity.canSetPerson)" ng-click="grid.appScope.setPerson(row.entity.id)">{{row.entity.assigneeName?row.entity.assigneeName:"指定审批人"}}</a>' +
                '<div class="ui-grid-cell-contents" ng-if="row.entity.endTime!=undefined||!row.entity.canSetPerson" >{{row.entity.assigneeName}}</div>';
        } else {
            return '<div class="ui-grid-cell-contents">{{row.entity.assigneeName}}</div>';
        }

    }
    $scope.setPerson = function(taskId) {
        var scope = $rootScope.$new();
        var pop = {
            title: "指定审批人",
            passParams: taskId,
            onclosedFun: load,
            controller: 'WorkflowSetPersonController',
            templateView: "static/business/workflow/task/templates/delegate.html",

        };
        listService.popPanel($scope, pop);
    }
    $scope.showResultMessage = function(message) {
        var pop = {
            title: "审批意见",
            passParams: message,
            controller: 'ResultMessageDetailController',
            templateView: 'static/business/workflow/process/templates/resultMessage.html',

        };
        listService.popPanel($scope, pop);
    }

    function load() {
        $http.get('api_workflow/myjob/dueList/' + $scope.processInstanceId)
            .success(function(response) {
                $scope.tableGrid.data = response;

            });
    }
    load();


    $scope.imgSrc = 'api_workflow/myjob/procPic/' + $scope.processInstanceId + "?t=" + new Date().getTime();

});
angular.module('huatek.controllers').controller('WorkflowSetPersonController', function($scope, $location, $http, editService, $rootScope) {
    $scope.taskId = $scope.passParams;
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '登录账号', field: 'userName', width: '20%', enableColumnMenu: false },
            { name: '姓名', field: 'name', width: '20%', enableColumnMenu: false },
            { name: '所属组织', field: 'orgName', width: '20%', enableColumnMenu: false },
            { name: '邮箱', field: 'email', width: '20%', enableColumnMenu: false },
            { name: '电话', field: 'phone', width: '20%', enableColumnMenu: false }
        ]

    };

    editService.init($scope, $http);


    /*定义查询页*/
    var queryPage = new QueryPage(1, 10, "level1 asc,level2 asc,level3 asc,level4 asc", "false");

    queryPage.addParam(new queryParam('所属组织', 'orgName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('登录账号', 'userName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('姓名', 'name', 'string', 'alllike'));

    /*定义搜索框*/

    $rootScope.searchCount = queryPage.queryParamList.length;
    $scope.queryFieldList = queryPage.queryParamList;
    for (var i = 0; i < $scope.queryFieldList.length; i++) {
        $scope.queryFieldList[i].isShowSelect = true;

    }
    $scope.resetSearch = function() {
        for (var i = 0; i < queryPage.queryParamList.length; i++) {
            queryPage.queryParamList[i].value = null;
            if (queryPage.queryParamList[i].type == 'dateTime') {
                queryPage.queryParamList[i].minValue = null;
                queryPage.queryParamList[i].maxValue = null;
            }
        }
    };
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
        listService.setGridHeight();
    };

    var load = function() {
        var postQueryPage = copyQueryPage(queryPage);
        $scope.promise = $http.post('api_workflow/workflowAuthority/queryPerson', postQueryPage)
            .success(function(response) {
                if (response.totalRows == undefined || response.totalRows == 0) {
                    $scope.tableGrid.data = [];
                } else {
                    $scope.tableGrid.data = response.content;
                }
                $scope.tableGrid.totalItems = response.totalRows;

            });
    }
    $scope.load = load;
    load();

    $scope.search = function() {
        load();
    };

    /***
     * 定义更新操作.
     */
    $scope.save = function() {
        var rows = $scope.gridApi.selection.getSelectedRows();
        if (rows.length == 0) {
            submitTips("请至少勾选一条数据！", 'warning');
            return;
        }


        $http.post('api_workflow/myjob/setPerson/' + $scope.taskId + "/" + rows[0].id).success(function(response) {
            $scope.back();
        });
    }

});
angular.module('huatek.controllers').controller('ResultMessageDetailController', function($scope) {

    $scope.context = $scope.passParams;

});