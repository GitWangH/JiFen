'use strict';

angular.module('huatek.controllers').controller('CmdExportConfigController', function($scope, $location, $http, $rootScope, listService, shareData, $modal, excelService) {
    var codeUrl = URL_PATH.CMD_HEADER + '/cmdExportConfig/formCode';

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '编码', field: 'code', width: '10%', enableColumnMenu: false },
            { name: '名称', field: 'name', width: '10%', enableColumnMenu: false },
            { name: '起始行', field: 'row', width: '10%', enableColumnMenu: false },
            { name: '序号列', field: 'numCol', width: '10%', enableColumnMenu: false },
            { name: '导出文件名', field: 'reportName', width: '10%', enableColumnMenu: false },
            { name: '所属子系统', field: 'systemType', width: '8%', enableColumnMenu: false },
            { name: '关系hql语句', field: 'hqlContent', width: '15%', enableColumnMenu: false },
            { name: 'hql语句条件', field: 'hqlConditions', width: '10%', enableColumnMenu: false },
            { name: '业务类型', field: 'exportType', width: '10%', enableColumnMenu: false },
            { name: '处理类', field: 'conversionService', width: '10%', enableColumnMenu: false },
            { name: '模版名称', field: 'templateName', width: '10%', enableColumnMenu: false },
            { name: '备注', field: 'remarks', width: '10%', enableColumnMenu: false },
        ]

    };


    listService.init($scope);

    var queryPage = new QueryPage(1, 10, "id desc", "false");

    var busiTypeQuery = new queryParam('所属子系统', 'systemType', 'string');
    busiTypeQuery.componentType = 'select';

    queryPage.addParam(busiTypeQuery);
    queryPage.addParam(new queryParam('编码', 'code', 'string', 'like'));
    queryPage.addParam(new queryParam('名称', 'name', 'string', 'like'));

    $rootScope.searchCount = queryPage.queryParamList.length;

    listService.setQueryPage($scope, queryPage);
    listService.initQueryParams($scope, codeUrl);

    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
            listService.setGridHeight();
        });
    };


    var btnArray = [];
    btnArray.push(new pageButton('新增', 'add', '/cmdExportConfig/add', 'addData'));
    btnArray.push(new pageButton('编辑', 'edit', '/cmdExportConfig/edit', 'editData'));
    btnArray.push(new pageButton('删除', 'delete', '/cmdExportConfig/delete', 'deleteData'));
    btnArray.push(new pageButton('关联表维护', 'editTableProperty', '/cmdExportTableConfig/home', 'editTablePropertyData'));
    btnArray.push(new pageButton('导出字段维护', 'editFieldProperty', '/cmdExportFieldConfig/home', 'editFieldPropertyData'));
    btnArray.push(new pageButton('上传模版', 'upload', '/cmdExportConfig/upload', 'upload'));
    btnArray.push(new pageButton('导出示例', 'excelImp', '/cmdExportConfig/home', 'excelImp'));


    listService.setButtonList($scope, btnArray);



    $scope.execute = function(operation, param) {
        if (operation == 'addData') {
            listService.addData($scope, new popup('新增导出配置', '/cmdExportConfig/add', null, null, null, load));
        } else if (operation == 'editData') {
            listService.editData($scope, $scope.gridApi, new popup('编辑导出配置', '/cmdExportConfig/edit', null, null, null, load));
        } else if (operation == 'deleteData') {
            listService.deleteData($scope, $scope.tableGrid, $scope.gridApi, URL_PATH.CMD_HEADER + '/cmdExportConfig/delete');
        } else if (operation == 'editTablePropertyData') {

            listService.editData($scope, $scope.gridApi, new popup('编辑导出配置', '/cmdExportTableConfig/home'));
        } else if (operation == 'editFieldPropertyData') {

            listService.editData($scope, $scope.gridApi, new popup('编辑导出配置', '/cmdExportFieldConfig/home'));
        } else if (operation == 'excelImp') {
            excelService.exp("E160802134219057", queryPage);
        } else if (operation == 'upload') {
            var selectData = listService.returnSectData($scope.gridApi);
            if (selectData.length > 1) {
                bootbox.alert('警告：不能选择多条数据上传模版。');
                return;
            }
            if (selectData.length == 0) {
                bootbox.alert('请在列表中选择一条用于上传。');
                return;
            }
            excelService.up('exportTemplate', { busiCode: selectData[0].code }, false, function(path, name) {
                load();


            });

        }

    }

    $scope.load=load;


    var load = function() {
        listService.loadData($scope, URL_PATH.CMD_HEADER + '/cmdExportConfig/query', $scope.tableGrid);
    }
    load();

    $scope.search = function() {
        load();
    };

});


angular.module('huatek.controllers').controller('CmdExportConfigAddController', function($scope, $location, $http, $routeParams, editService, $rootScope) {

    var addDataUrl = URL_PATH.CMD_HEADER + '/cmdExportConfig/add';
    var editDataUrl = URL_PATH.CMD_HEADER + '/cmdExportConfig/edit';
    var homeUrl = '/cmdExportConfig/home';
    var codeUrl = URL_PATH.CMD_HEADER + '/cmdExportConfig/formCode';


    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;


    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '所属子系统', 'systemType', 'string', 1, '30', 'select'));
    /*formFieldArray.push(new FormElement(1,'编码','code','string',1,'30'));*/
    formFieldArray.push(new FormElement(1, '名称', 'name', 'string', 1, '30'));
    formFieldArray.push(new FormElement(1, '导出文件名', 'reportName', 'string', 1, '40'));
    formFieldArray.push(new FormElement(1, '起始行', 'row', 'number', 1, '30'));
    formFieldArray.push(new FormElement(1, '序号列(不填或-1无序号)', 'numCol', 'number', 1, '30'));
    formFieldArray.push(new FormElement(1, '关系hql语句', 'hqlContent', 'string', 1, '500', 'textarea'));
    formFieldArray.push(new FormElement(1, 'hql语句条件', 'hqlConditions', 'string', 0, '250', 'textarea'));
    formFieldArray.push(new FormElement(1, '业务类型', 'exportType', 'string', 1, '100', 'select'));
    formFieldArray[formFieldArray.length - 1].items = [{ _returnField: "export_model_type", _showField: "默认" },
        { _returnField: "export_service_type", _showField: "自定义类" }
    ];
    formFieldArray[formFieldArray.length - 1].value = "export_model_type";
    formFieldArray.push(new FormElement(1, '处理类', 'conversionService', 'string', 0, '100'));
    formFieldArray.push(new FormElement(1, '备注', 'remarks', 'string', 0, '100', 'textarea'));

    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;


    editService.init($scope, $location, $http);
    editService.initParams($scope, codeUrl);

    editService.setFormFields($scope, formFieldArray);



    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);
    } else {
        $scope.editId = -1;
    }


    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    }

    $scope.save = function() {
        editService.saveData($scope, addDataUrl, homeUrl);
    }
});
angular.module('huatek.controllers').controller('CmdExportLogController', function($scope, $location, $http, $rootScope, listService, $modal) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '业务类型', field: 'busiCode', width: '10%', enableColumnMenu: false },
            { name: '业务名称', field: 'busiName', width: '10%', enableColumnMenu: false },
            { name: '导出人', field: 'operatorName', width: '10%', enableColumnMenu: false },
            { name: '导出时间', field: 'operationTime', width: '20%', enableColumnMenu: false },
            { name: '文件名', field: 'filePath', width: '20%', enableColumnMenu: false },
            { name: '导出参数', field: 'param', width: '20%', enableColumnMenu: false },

        ]

    };


    listService.init($scope, $location, $http);

    var queryPage = new QueryPage(1, 10, "id desc", "false");


    var start = new queryParam('导出时间', 'operationTime', 'date', '>=');
    start.componentType = 'dateTimeSection';
    queryPage.addParam(start);
    var end = new queryParam('到', 'operationTime', 'date', '<=');
    end.componentType = 'dateTimeSection';
    queryPage.addParam(end);
    var type = new queryParam('业务类型', 'busiCode', 'string', 'alllike');
    queryPage.addParam(type);
    queryPage.addParam(new queryParam('业务名称', 'busiName', 'string', 'alllike'));

    $rootScope.searchCount = queryPage.queryParamList.length;

    listService.setQueryPage($scope, queryPage);

    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
            listService.setGridHeight();
        });
    };




    var load = function() {
        listService.loadData($scope, 'api_cmd/cmdExportLog/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

});