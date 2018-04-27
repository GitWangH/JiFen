'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowClaimController', function($scope, $location, $http, httpService, $rootScope, listService, excelService) {
    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        multiSelect: false,
        columnDefs: [
            { name: '流程编号', field: 'processInstanceId', width: '10%', enableColumnMenu: false },
            { name: '标题', field: 'description', width: '30%', enableColumnMenu: false, cellTemplate: returnTitleColor() },
            /*{ name: '原结算单号', field: 'oldSettlementCode',width: '12%', enableColumnMenu: false},*/
            { name: '发起机构', field: 'applyOrgName', width: '10%', enableColumnMenu: false },
            { name: '发起人', field: 'applyUserName', width: '10%', enableColumnMenu: false },
            { name: '审批环节', field: 'name', width: '15%', enableColumnMenu: false },
            { name: '流程名称', field: 'processDefinitionName', width: '15%', enableColumnMenu: false },
            { name: '创建时间', field: 'createTime', width: '15%', enableColumnMenu: false },
            { name: '截至时间', field: 'dueDate', width: '15%', enableColumnMenu: false },

        ]

    };


    function returnTitleColor() {
        return '<div class="ui-grid-cell-contents"  title="{{COL_FIELD}}" onmouseover="this.style.color=\'red\'" onmouseout="this.style.color=\'#333\'">{{COL_FIELD}}</div>';
    }

    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http);

    /*定义查询页*/
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    queryPage.addParam(new queryParam('流程编号', 'processInstanceId', 'number'));
    queryPage.addParam(new queryParam('标题', 'title', 'string', 'alllike'));
    queryPage.addParam(new queryParam('流程名称', 'name', 'string', 'alllike'));
    queryPage.addParam(new queryParam('发起人', 'applyUserName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('创建时间', 'startTime', 'date', '>='));
    queryPage.addParam(new queryParam('——', 'startTime', 'date', '<='));
    $rootScope.searchCount = queryPage.queryParamList.length;
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


    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('签收', 'claim', '/myjob/claim', 'claimData'));
    btnArray.push(new pageButton('查看流程进度', 'progress', '/myjob/progress', 'progressData'));
    /***
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'claimData') {
            if ($scope.gridApi.selection.getSelectedRows().length < 1) {
                bootbox.alert("请至少勾选一条数据！");
                return false;
            }
            var ids = [];
            angular.forEach($scope.gridApi.selection.getSelectedRows(), function(data, index) {
                ids.push(data.id);

            });
            httpService.post($scope, "api_workflow/myjob/claim", ids).success(function() {
                angular.forEach($scope.gridApi.selection.getSelectedRows(), function(data, index) {
                    $scope.tableGrid.data.splice($scope.tableGrid.data.lastIndexOf(data), 1);

                });
            });
        } else if (operation == 'progressData') {
            listService.showProcessProgress($scope, "processInstanceId", false);
        }

    }

    /***
     * 初始化加载数据.
     */
    var load = function() {
        listService.loadData($scope, 'api_workflow/myjob/claimlist', $scope.tableGrid);
    }
    $scope.load = load;
    load();
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };

});

'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowToDoController', function($scope, $routeParams, $location, $http, httpService, $rootScope, listService, excelService, $modal) {
    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        multiSelect: false,
        columnDefs: [
            { name: '流程编号', field: 'processInstanceId', width: '10%', enableColumnMenu: false, cellTemplate: returnColor() },
            { name: '标题', field: 'description', width: '30%', enableColumnMenu: false, cellTemplate: returnTitleColor() },
            /*{ name: '原结算单号', field: 'oldSettlementCode',width: '12%', enableColumnMenu: false},*/
            { name: '发起机构', field: 'applyOrgName', width: '10%', enableColumnMenu: false, cellTemplate: returnColor() },
            { name: '发起人', field: 'applyUserName', width: '10%', enableColumnMenu: false, cellTemplate: returnColor() },
            { name: '剩余天数', field: 'restDay', width: '8%', enableColumnMenu: false, cellTemplate: returnColor() },
            { name: '审批环节', field: 'name', width: '15%', enableColumnMenu: false, cellTemplate: returnColor() },
            { name: '流程名称', field: 'processDefinitionName', width: '15%', enableColumnMenu: false, cellTemplate: returnColor() },
            { name: '创建时间', field: 'createTime', width: '15%', enableColumnMenu: false, cellTemplate: returnColor() },
            { name: '截至时间', field: 'dueDate', width: '15%', enableColumnMenu: false, cellTemplate: returnColor() },
        ]

    };

    function returnColor() {
        return '<div class="ui-grid-cell-contents ui-grid-line-color-red"  ng-if="row.entity[\'restDay\']!=undefined&&row.entity[\'restDay\']&lt;1">{{COL_FIELD}}</div><div class="ui-grid-cell-contents" ng-if="row.entity[\'restDay\']==undefined||row.entity[\'restDay\']&gt;0">{{COL_FIELD}}</div>';
    }

    function returnTitleColor() {
        return '<div class="ui-grid-cell-contents ui-grid-line-color-red"  ng-if="row.entity[\'restDay\']!=undefined&&row.entity[\'restDay\']&lt;1" title="{{COL_FIELD}}" onmouseover="this.style.color=\'red\'" onmouseout="this.style.color=\'#333\'">{{COL_FIELD}}</div><div class="ui-grid-cell-contents" ng-if="row.entity[\'restDay\']==undefined||row.entity[\'restDay\']&gt;0" title="{{COL_FIELD}}" onmouseover="this.style.color=\'red\'" onmouseout="this.style.color=\'#333\'">{{COL_FIELD}}</div>';
    }
    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http);

    /*定义查询页*/
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    var processInstanceId = new queryParam('流程编号', 'processInstanceId', 'number');
    queryPage.addParam(processInstanceId);
    queryPage.addParam(new queryParam('标题', 'title', 'string', 'alllike'));
    queryPage.addParam(new queryParam('流程名称', 'name', 'string', 'alllike'));
    queryPage.addParam(new queryParam('发起人', 'applyUserName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('创建时间', 'startTime', 'date', '>='));
    queryPage.addParam(new queryParam('——', 'startTime', 'date', '<='));


    $rootScope.searchCount = queryPage.queryParamList.length;
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);
    if ($routeParams.processId && $routeParams.processId != 0) {
        if ($routeParams.processId != 1) {
            processInstanceId.value = $routeParams.processId;
        } else {
            processInstanceId.value = "";
        }

    }
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


    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('办理', 'toDo', '/myjob/toDo', 'toDoData'));
    /* btnArray.push(new pageButton('取消签收','reject','/myjob/reject','rejectData'));*/
    btnArray.push(new pageButton('委派', 'delegateDo', '/myjob/delegate', 'delegateData'));
    /*btnArray.push(new pageButton('批量审批','batchApprove','/myjob/batchApprove','batchApproveData'));*/
    btnArray.push(new pageButton('打印', 'print', '/myjob/toDoprint', 'printData'));
    btnArray.push(new pageButton('查看流程进度', 'progress', '/myjob/toDoprogress', 'progressData'));
    /***
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'toDoData') {
            if ($scope.gridApi.selection.getSelectedRows().length < 1) {
                submitTips("请至少勾选一条数据！", 'warning');
                return false;
            }
            if ($scope.gridApi.selection.getSelectedRows().length > 1) {
                bootbox.alert("请勾选一条数据！");
                submitTips('请勾选一条数据！', 'warning');
                return false;
            }

            var id = $scope.gridApi.selection.getSelectedRows()[0].id;
            var taskName = $scope.gridApi.selection.getSelectedRows()[0].name;
            var processDefinitionKey = $scope.gridApi.selection.getSelectedRows()[0].processDefinitionKey;
            var taskKey = $scope.gridApi.selection.getSelectedRows()[0].taskDefinitionKey;
            var processInstanceId = $scope.gridApi.selection.getSelectedRows()[0].processInstanceId;
            httpService.post($scope, "api_workflow/myjob/toDo/" + id).success(function(response) {
                var formKey = response.formKey;
                var type = formKey.split(":")[0]
                var value = formKey.split(":")[1];
                if (type === 'url') {
                    var url = value;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];
                    httpService.get($scope, "api/menu/querySourceByUrl?url=" + url).success(function(source) {

                        var pop = {
                            title: "流程办理",
                            passParams: {
                                busiCode: processDefinitionKey,
                                taskId: id,
                                busiId: busiId,
                                taskKey: taskKey,
                                taskName: taskName,
                                processInstanceId: processInstanceId
                            },
                            onclosedFun: load,
                            controller: source.controller,
                            templateView: source.view,

                        };
                        listService.popPanel($scope, pop);


                    });
                } else {
                    var busiCode = value;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];
                    $http.get("api_workflow/form/querySource/" + busiCode).success(function(source) {
                        var pop = {
                            title: "流程办理",
                            passParams: {
                                busiCode: busiCode,
                                taskId: id,
                                busiId: busiId,
                                taskKey: taskKey,
                                taskName: taskName,
                                processInstanceId: processInstanceId
                            },
                            onclosedFun: load,
                            controller: source.controller,
                            templateView: source.view,

                        };
                        listService.popPanel($scope, pop);



                    });
                }

            });
        } else if (operation == 'progressData') {
            listService.showProcessProgress($scope, "processInstanceId", false);
        } else if (operation == 'rejectData') {
            if ($scope.gridApi.selection.getSelectedRows().length < 1) {
                submitTips('请至少勾选一条数据！', 'warning');
                return false;
            }
            var ids = [];
            for (var i = 0; i < $scope.gridApi.selection.getSelectedRows().length; i++) {
                ids.push($scope.gridApi.selection.getSelectedRows()[i].id);
            }
            $scope.promise = $http.post("api_workflow/myjob/reject", ids).success(function(response) {
                load();
            });
        } else if (operation == 'delegateData') {
            if ($scope.gridApi.selection.getSelectedRows().length < 1) {
                bootbox.alert("请至少勾选一条数据！");
                return false;
            }
            if ($scope.gridApi.selection.getSelectedRows().length > 1) {
                bootbox.alert("请勾选一条数据！");
                return false;
            }

            var pop = {
                title: "流程委派",
                passParams: {
                    taskId: $scope.gridApi.selection.getSelectedRows()[0].id,
                },
                onclosedFun: load,
                controller: "WorkflowDelegateController",
                templateView: "static/business/workflow/task/templates/delegate.html",

            };
            listService.popPanel($scope, pop);
        } else if (operation == 'batchApproveData') {
            if ($scope.gridApi.selection.getSelectedRows().length < 1) {
                submitTips('请至少勾选一条数据！', 'warning');
                return false;
            }
            var ids = [];
            for (var i = 0; i < $scope.gridApi.selection.getSelectedRows().length; i++) {
                ids.push($scope.gridApi.selection.getSelectedRows()[i].id);
            }
            var pop = {
                title: "批量审批",
                passParams: {
                    taskIds: ids,
                },
                onclosedFun: load,
                controller: 'WorkflowBatchApproveController',
                templateView: "static/business/workflow/task/templates/batchApprove.html",

            };
            listService.popPanel($scope, pop);
        } else if (operation == 'printData') {
            if ($scope.gridApi.selection.getSelectedRows().length != 1) {
                submitTips('请勾选一条数据！', 'warning');
                return false;
            }

            var id = $scope.gridApi.selection.getSelectedRows()[0].id;
            var taskName = $scope.gridApi.selection.getSelectedRows()[0].name;
            var processDefinitionKey = $scope.gridApi.selection.getSelectedRows()[0].processDefinitionKey;
            var taskKey = $scope.gridApi.selection.getSelectedRows()[0].taskKey;
            var processInstanceId = $scope.gridApi.selection.getSelectedRows()[0].processInstanceId;
            var description = $scope.gridApi.selection.getSelectedRows()[0].description;
            $http.post("api_workflow/myjob/toPrint/" + id).success(function(response) {
                var formType = response.formType;
                var formUrl = response.formUrl;
                var formPrint = response.formPrint; /*定制表单的打印页面*/
                if (!formType || formUrl == 'null' || formUrl == '' || (formType == 'url' && (!formPrint || formPrint == 'null' || formPrint == ''))) {
                    /*submitTips('该流程打印表单正在开发中，请联系管理员！','warning');*/
                    submitTips('该流程暂不支持直接打印，请选择其他进行打印！', 'warning');
                    return;
                }
                if (formType === 'url') {
                    var url = formUrl;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];
                    $http.get("api/menu/querySourceByUrl?url=" + formPrint).success(function(source) {


                        var pop = {
                            title: description,
                            passParams: {
                                busiCode: processDefinitionKey,
                                taskId: id,
                                busiId: busiId,
                                taskKey: taskKey,
                                taskName: taskName,
                                processInstanceId: processInstanceId
                            },
                            onclosedFun: load,
                            controller: source.controller,
                            templateView: source.view,

                        };
                        listService.popPanel($scope, pop);

                    });
                } else {
                    var busiCode = formUrl;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];

                    var pop = {
                        title: description,
                        passParams: {
                            busiCode: busiCode,
                            taskId: id,
                            busiId: busiId,
                            taskKey: taskKey,
                            taskName: taskName,
                            processInstanceId: processInstanceId
                        },
                        onclosedFun: load,
                        controller: 'EasyFormWorkflowPrintController',
                        templateView: "static/business/workflow/easyformTemplate/templates/template_workflow_print.html",

                    };
                    listService.popPanel($scope, pop);
                }

            });
        }

    }

    /***
     * 初始化加载数据.
     */
    var load = function() {
        listService.loadData($scope, 'api_workflow/myjob/toDolist', $scope.tableGrid);
        $rootScope.refreshToDoCount();
    }

    load();
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };

});

'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('WorkflowDoneController', function($scope, $location, $http, httpService, $rootScope, $modal, listService, excelService) {
    /***
     * 定义显示的表格.
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        multiSelect: false,
        columnDefs: [
            { name: '流程编号', field: 'processInstanceId', width: '10%', enableColumnMenu: false },
            { name: '标题', field: 'description', width: '30%', enableColumnMenu: false, cellTemplate: returnTitleColor() },
            /*{ name: '原结算单号', field: 'oldSettlementCode',width: '12%', enableColumnMenu: false},*/
            { name: '发起机构', field: 'applyOrgName', width: '10%', enableColumnMenu: false },
            { name: '发起人', field: 'applyUserName', width: '10%', enableColumnMenu: false },
            { name: '审批环节', field: 'name', width: '15%', enableColumnMenu: false },
            { name: '流程名称', field: 'processDefinitionName', width: '15%', enableColumnMenu: false },
            { name: '创建时间', field: 'createTime', width: '15%', enableColumnMenu: false },
            { name: '结束时间', field: 'endTime', width: '15%', enableColumnMenu: false },
        ]

    };

    function returnTitleColor() {
        return '<div class="ui-grid-cell-contents"  title="{{COL_FIELD}}" onmouseover="this.style.color=\'red\'" onmouseout="this.style.color=\'#333\'">{{COL_FIELD}}</div>';
    }

    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     */
    listService.init($scope, $http);

    /*定义查询页*/
    var queryPage = new QueryPage(1, 10, "id desc", "false");
    queryPage.addParam(new queryParam('流程编号', 'processInstanceId', 'number'));
    queryPage.addParam(new queryParam('标题', 'title', 'string', 'alllike'));
    queryPage.addParam(new queryParam('流程名称', 'name', 'string', 'alllike'));
    queryPage.addParam(new queryParam('发起人', 'applyUserName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('办理时间', 'startTime', 'date', '>='));
    queryPage.addParam(new queryParam('——', 'startTime', 'date', '<='));
  
    $rootScope.searchCount = queryPage.queryParamList.length;
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
        listService.setGridHeight();
    };


    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('查看', 'detail', '/myjob/detail', 'detailData'));
    btnArray.push(new pageButton('打印', 'print', '/myjob/donePrint', 'printData'));
    /*btnArray.push(new pageButton('撤回','progress','/myjob/withdraw','withdraw'));*/
    btnArray.push(new pageButton('查看流程进度', 'progress', '/myjob/doneProgress', 'progressData'));
    /*btnArray.push(new pageButton('通知相关人','notice','/myjob/notice','noticeData'));*/
    /***
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param) {
        if (operation == 'progressData') {
            listService.showProcessProgress($scope, "processInstanceId");
        } else if (operation == 'withdraw') {
            if ($scope.gridApi.selection.getSelectedRows().length != 1) {
                submitTips('请至少勾选一条数据！', 'warning');
                return false;
            }
            $http.post("api_workflow/myjob/widthdraw/" + $scope.gridApi.selection.getSelectedRows()[0].id).success(function() {
                $scope.search();
            });
        } else if (operation == 'noticeData') {
            if ($scope.gridApi.selection.getSelectedRows().length != 1) {
                submitTips('请至少勾选一条数据！', 'warning');
                return false;
            }
            $http.get("api_workflow/myjob/canNotic/" + $scope.gridApi.selection.getSelectedRows()[0].id).success(function(response) {
                if (response.isEnd) {
                    var pop = {
                        title: "流程编号【" + $scope.gridApi.selection.getSelectedRows()[0].processInstanceId + "】审批结束提醒",
                        passParams: {
                            content: "《" + $scope.gridApi.selection.getSelectedRows()[0].description + "》审批结束，结果为" + $scope.gridApi.selection.getSelectedRows()[0].result,
                        },
                        onclosedFun: load,
                        controller: 'OaMsgInfoSendController',
                        templateView: "static/business/oa/msg/templates/sendMsg.html",

                    };
                    listService.popPanel($scope, pop);
                } else {
                    submitTips('流程还未结束不能发送通知！', 'warning');
                }

            })

        } else if (operation == 'detailData') {
            if ($scope.gridApi.selection.getSelectedRows().length != 1) {
                submitTips('请勾选一条数据！', 'warning');
                return false;
            }

            var id = $scope.gridApi.selection.getSelectedRows()[0].id;
            var taskName = $scope.gridApi.selection.getSelectedRows()[0].name;
            var processDefinitionKey = $scope.gridApi.selection.getSelectedRows()[0].processDefinitionKey;
            var taskKey = $scope.gridApi.selection.getSelectedRows()[0].taskKey;
            var processInstanceId = $scope.gridApi.selection.getSelectedRows()[0].processInstanceId;
            $http.post("api_workflow/myjob/toDetail/" + id).success(function(response) {
                var formType = response.formType;
                var formUrl = response.formUrl;
                if (!formType || formUrl == 'null' || formUrl == '') {
                    submitTips('该流程查看表单正在开发中，请联系管理员！', 'warning');
                    return;
                }
                if (formType === 'url') {
                    var url = formUrl;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];
                    $http.get("api/menu/querySourceByUrl?url=" + formUrl).success(function(source) {

                        var pop = {
                            title: "流程明细数据查看",
                            passParams: {
                                busiCode: processDefinitionKey,
                                taskId: id,
                                busiId: busiId,
                                taskKey: taskKey,
                                taskName: taskName,
                                processInstanceId: processInstanceId,
                                onlyView: true
                            },
                            onclosedFun: load,
                            controller: source.controller,
                            templateView: source.view,

                        };
                        listService.popPanel($scope, pop);


                    });
                } else {
                    var busiCode = formUrl;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];
                    var pop = {
                        title: description,
                        passParams: {
                            busiCode: busiCode,
                            taskId: id,
                            busiId: busiId,
                            taskKey: taskKey,
                            taskName: taskName,
                            processInstanceId: processInstanceId,
                            onlyView: true
                        },
                        onclosedFun: load,
                        controller: 'EasyFormWorkflowViewController',
                        templateView: "static/business/workflow/easyformTemplate/templates/template_workflow_view.html",

                    };
                    listService.popPanel($scope, pop);
                }

            });
        } else if (operation == 'printData') {
            if ($scope.gridApi.selection.getSelectedRows().length != 1) {
                submitTips('请勾选一条数据！', 'warning');
                return false;
            }

            var id = $scope.gridApi.selection.getSelectedRows()[0].id;
            var taskName = $scope.gridApi.selection.getSelectedRows()[0].name;
            var processDefinitionKey = $scope.gridApi.selection.getSelectedRows()[0].processDefinitionKey;
            var taskKey = $scope.gridApi.selection.getSelectedRows()[0].taskKey;
            var processInstanceId = $scope.gridApi.selection.getSelectedRows()[0].processInstanceId;
            var description = $scope.gridApi.selection.getSelectedRows()[0].description;
            $http.post("api_workflow/myjob/toPrint/" + id).success(function(response) {
                var formType = response.formType;
                var formUrl = response.formUrl;
                var formPrint = response.formPrint; /*定制表单的打印页面*/
                if (!formType || formUrl == 'null' || formUrl == '' || (formType == 'url' && (!formPrint || formPrint == 'null' || formPrint == ''))) {
                    /*submitTips('该流程打印表单正在开发中，请联系管理员！','warning');*/
                    submitTips('该流程不支持直接打印，请在明细中进行打印！', 'warning');
                    return;
                }
                if (formType === 'url') {
                    var url = formUrl;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];
                    $http.get("api/menu/querySourceByUrl?url=" + formPrint).success(function(source) {


                        var pop = {
                            title: description,
                            passParams: {
                                busiCode: processDefinitionKey,
                                taskId: id,
                                busiId: busiId,
                                taskKey: taskKey,
                                taskName: taskName,
                                processInstanceId: processInstanceId
                            },
                            onclosedFun: load,
                            controller: source.controller,
                            templateView: source.view,

                        };
                        listService.popPanel($scope, pop);

                    });
                } else {
                    var busiCode = formUrl;
                    var businessKey = response.businessKey;
                    var busiId = businessKey.split("|")[1];

                    var pop = {
                        title: description,
                        passParams: {
                            busiCode: busiCode,
                            taskId: id,
                            busiId: busiId,
                            taskKey: taskKey,
                            taskName: taskName,
                            processInstanceId: processInstanceId
                        },
                        onclosedFun: load,
                        controller: 'EasyFormWorkflowPrintController',
                        templateView: "static/business/workflow/easyformTemplate/templates/template_workflow_print.html",

                    };
                    listService.popPanel($scope, pop);
                }

            });
        }


    }

    /***
     * 初始化加载数据.
     */
    var load = function() {
        listService.loadData($scope, 'api_workflow/myjob/doneList', $scope.tableGrid);
    }

    load();
    /*
     *查询需要调用的函数. 
     */
    $scope.search = function() {
        load();
    };

});
angular.module('huatek.controllers').controller('WorkflowDelegateController', function($scope, $location, $http, httpService, editService, $rootScope) {
    $scope.taskId = $scope.passParams.taskId;
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '用户名', field: 'userName', width: '20%', enableColumnMenu: false },
            { name: '所属组织', field: 'orgName', width: '20%', enableColumnMenu: false },
            { name: '姓名', field: 'name', width: '20%', enableColumnMenu: false },
            { name: '邮箱', field: 'email', width: '20%', enableColumnMenu: false },
            { name: '电话', field: 'phone', width: '20%', enableColumnMenu: false }
        ]

    };

    editService.init($scope, $http);


    /*定义查询页*/
    var queryPage = new QueryPage(1, 10, "level1 asc,level2 asc,level3 asc,level4 asc", "false");

    queryPage.addParam(new queryParam('所属组织', 'orgName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('用户名', 'userName', 'string', 'alllike'));
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


        httpService.post($scope, 'api_workflow/myjob/delegate/' + $scope.taskId + "/" + rows[0].id).success(function(response) {
            $scope.back();
        });
    }

});
angular.module('huatek.controllers').controller('WorkflowBatchApproveController', function($scope, $location, $http, httpService, editService, $rootScope) {

    /***
     * 定义编辑跳转的路径.
     */


    /*定义块*/
    var columnWrapArray = [];
    $scope.columnWrapArray = columnWrapArray;


    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var result = new FormElement(99, '审核', 'result', 'boolean', 1, '128', "radio", "resultChange");
    result.items = [{ code: "true", name: '同意' }, { code: "false", name: "驳回" }];
    var resultMessage = new FormElement(99, '审核意见', 'resultMessage', 'string', 1, '1000', "textarea");

    formFieldArray.push(result);
    formFieldArray.push(resultMessage);


    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    /**
     * 初始化编辑界面.
     * 比如显示编辑界面的模块名称.
     * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
     */
    editService.init($scope, $http);

    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields($scope, formFieldArray);

    /***
     * 定义更新操作.
     */
    $scope.approve = function() {
        if (editService.isAllowPost($scope)) {
            httpService.post($scope, "api_workflow/myjob/batchApprove", { taskIds: $scope.taskIds, result: result.value, resultMessage: resultMessage.value }).success(function(response) {
                $scope.back();
            })
        }
    }

});