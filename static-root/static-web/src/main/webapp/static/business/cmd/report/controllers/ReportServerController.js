'use strict';

angular.module('HuatekApp').controller('ReportServerController', function($scope, $location, $http, $rootScope, listService, $modal, shareData) {

    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        columnDefs: [
            { name: '服务器地址', field: 'serverAddress', width: '30%', enableColumnMenu: false },
            { name: '端口', field: 'point', width: '5%', enableColumnMenu: false },
            { name: '权重', field: 'weight', width: '5%', enableColumnMenu: false },
            { name: '状态', field: 'status', width: '5%', enableColumnMenu: false },
            { name: '说明', field: 'remark', width: '50%', enableColumnMenu: false }
        ]

    };


    listService.init($scope, $location, $http);
    
    
    var queryPage = new QueryPage(1,10,"id desc","false");
    queryPage.addParam(new queryParam('端口','point','string','like'));
    $rootScope.searchCount = queryPage.queryParamList.length;
    
    listService.setQueryPage(queryPage);

    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
    };


    var btnArray = [];
    btnArray.push(new pageButton('新增', 'addReport', '/reportServerController/add', 'addReportData'));
    btnArray.push(new pageButton('编辑', 'editReport', '/reportServerController/edit', 'editReportData'));
    btnArray.push(new pageButton('删除', 'deleteReport', '/reportServerController/delete', 'deleteData'));


    listService.setButtonList(btnArray);


    $scope.execute = function(operation, param) {
        if (operation == 'addReportData') {
            listService.addData('/reportServerController/add');
        }
        if (operation == 'editReportData') {
            listService.editData($scope.gridApi, '/reportServerController/edit');
        }
        if (operation == 'deleteData') {
            listService.deleteData($scope.tableGrid, $scope.gridApi, URL_PATH.CMD_HEADER+'/reportServer/delete');
        }
    }

    var load = function() {
        listService.loadData(URL_PATH.CMD_HEADER + '/reportServer/query', $scope.tableGrid);
    }

    load();

    $scope.search = function() {
        load();
    };

});



angular.module('HuatekApp').controller('ReportServerAddController', function($scope, $location, $http, $routeParams, editService, $rootScope, excelService) {
    var addDataUrl = URL_PATH.CMD_HEADER + '/reportServer/save';
    var editDataUrl = URL_PATH.CMD_HEADER + '/reportServer/edit';
    var updateDataUrl = URL_PATH.CMD_HEADER + '/reportServer/update';
    var homeUrl = '/reportServerController/home';



    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;


    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/

    /*FormElement1(title, name, type, require, max, model, isEdit, isShow, event, min, defaultValue, dropDataUrl)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '服务器地址', 'serverAddress', 'string', 1, '200'));
    var point = new FormElement(1, '端口', 'point', 'number', 1, '20');
    point.model = 'number';
    formFieldArray.push(point);
    var weight = new FormElement(1, '权重', 'weight', 'number', 0, '20');
    weight.model = 'number';
    formFieldArray.push(weight);
    formFieldArray.push(new FormElement(1, '说明', 'remark', 'string', 0, '200'));

    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    editService.init($scope, $location, $http);

    editService.setFormFields(formFieldArray);

    $scope.editId = $routeParams.id;

    if ($scope.editId) {
        editService.loadData(editDataUrl, $scope.editId);
    } else {
        $scope.editId = -1;
    }


    $scope.update = function() {
        editService.updateData(updateDataUrl, homeUrl, $scope.editId);
    }

    $scope.save = function() {
        editService.saveData(addDataUrl, homeUrl);
    }
});
