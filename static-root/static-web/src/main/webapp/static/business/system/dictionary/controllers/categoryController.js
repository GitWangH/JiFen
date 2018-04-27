'use strict';
/**
 * 数据字典
 */
angular.module('huatek.controllers').controller('categoryController', function($scope, $location, $http, listService, $rootScope) {

    var checkedCategory = null;
    /**
     * 字典类型
     * @type {Object}
     */
    $scope.tableGrid_category = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection : true,
        enableSelectAll : false,
        multiSelect: false,
        columnDefs: [
            { name: '字典类型', field: 'kindName', width: '25%', enableColumnMenu: false },
            { name: '类型名称', field: 'categoryName', width: '25%', enableColumnMenu: false },
            { name: '系统级别', field: 'isSystem', width: '25%', enableColumnMenu: false, cellTemplate: "<div class='ui-grid-cell-contents' style='text-align:center' ng-if='!row.entity.tenandId'>是</div><div class='ui-grid-cell-contents' style='text-align:center' ng-if='row.entity.tenandId'>否</div>" },
            { name: '创建时间', field: 'createTime', width: '25%', enableColumnMenu: false }
        ]
    };
    $scope.tableGrid_category.onRegisterApi = function(gridApi) {
        $scope.gridApi_category = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
        /*调整grid底部高度*/
        listService.setGridHeight();
        /*单选事件*/
        gridApi.selection.on.rowSelectionChanged($scope, function(row) {
            checkedCategory = row;
            if (row.isSelected) {
                if (gridApi.selection.getSelectedRows().length > 1) {
                    /*如果选中行数大于1，则先清除所有选中，然后选中被点击的行*/
                    gridApi.selection.clearSelectedRows();
                    gridApi.selection.selectRow(row.entity);
                }
            } else {
                gridApi.selection.selectRow(row.entity);
            }
            loadProperties(row.entity.id);
        });
    };


    /**
     * 字典项
     * @type {Object}
     */
    $scope.tableGrid_property = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        columnDefs: [
            { displayName: '字典编号', field: 'propertyValue', width: '50%', enableColumnMenu: false },
            { displayName: '字典名称', field: 'propertyName', width: '50%', enableColumnMenu: false }
        ]
    };
    $scope.tableGrid_property.onRegisterApi = function(gridApi) {
        $scope.gridApi_property = gridApi;
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
            if (!rowEntity.isNewRow) {
                rowEntity.isEdited = true;
            }
        });
        /*调整grid底部高度*/
        listService.setGridHeight();
    };

    listService.init($scope);

    var queryPage = new QueryPage(1, 10, "id desc", "false");

    queryPage.addParam(new queryParam('字典类型', 'kindName', 'string', 'alllike'));
    queryPage.addParam(new queryParam('类型名称', 'categoryName', 'string', 'alllike'));

    listService.setQueryPage($scope, queryPage);


    var btnArray = [];
    btnArray.push(new pageButton('字典新增', 'add', '/base/addProperty', 'addCategory'));
    btnArray.push(new pageButton('字典编辑', 'edit', '/base/editProperty', 'editCategory'));
    btnArray.push(new pageButton('字典删除', 'delete', '/base/deleteCategory', 'deleteCategory'));

    btnArray.push(new pageButton('字典项新增', 'add', '/base/addProperty', 'addPropertyrow'));
    btnArray.push(new pageButton('字典项删除', 'delete', '/base/addProperty', 'deleteProperty'));
    btnArray.push(new pageButton('字典项保存', 'save', '/base/addProperty', 'saveProperty'));

    listService.setButtonList($scope, btnArray);



    $scope.execute = function(operation, param) {
        if (operation == 'addCategory') {
            listService.addData($scope, new popup("字典新增", '/base/addCategory'));
        } else if (operation == 'editCategory') {
            if (!checkedCategory.entity.tenantId && $rootScope.tenantId) {
                submitTips('警告：系统字典不能编辑。', 'warning');
                return;
            }
            listService.editData($scope, $scope.gridApi_category, new popup("字典编辑", "/base/editCategory"));
        } else if (operation == 'deleteCategory') {
            listService.deleteData($scope, $scope.tableGrid_category, $scope.gridApi_category, 'api_cmd/category/delete');
        } else if (operation == 'addPropertyrow') {
            if (checkedCategory && checkedCategory.isSelected == true) {
                $scope.tableGrid_property.data.push({ "fwCategoryId": checkedCategory.entity.id, "isNewRow": true });
            } else {
                submitTips('警告：请先选择一条数据。', 'warning');
                return;
            }
        } else if (operation == 'deleteProperty') {
            listService.DeleteRows($scope, $scope.tableGrid_property, $scope.gridApi_property);
        } else if (operation == 'saveProperty') {

            if (!checkedCategory.entity.tenantId && $rootScope.tenantId) {
                submitTips('警告：系统字典不能编辑。', 'warning');
                return;
            }
            /*listService.saveRows($scope, "api_cmd/property/save", $scope.tableGrid_property);*/
            /* 保存之前判断是否有重复编码*/
            var propertyKeyMap = new Map();
            var propertyValMap = new Map();
            var canSave = true;
            angular.forEach($scope.tableGrid_property.data, function(data, index) {
                /*编码是否存在*/
                if (propertyKeyMap.get(data.propertyValue)) {
                    canSave = false;
                    submitTips('警告：字典编号【' + data.propertyValue + '】不能重复。', 'warning');
                    return;
                }
                /*名称是否存在*/
                if (propertyValMap.get(data.propertyName)) {
                    canSave = false;
                    submitTips('警告：字典名称【' + data.propertyName + '】不能重复。', 'warning');
                    return;
                }
                propertyKeyMap.put(data.propertyValue, data.propertyValue);
                propertyValMap.put(data.propertyName, data.propertyName);
            });
            if (canSave) {
                listService.saveRows($scope, "api_cmd/property/save", $scope.tableGrid_property, null, function() {
                    loadProperties(checkedCategory.entity.id);
                });
            }
        }
    }

    /***
     * 查询后台数据返回来的就是一个翻页对象.
     * 详见：com.hisense.frame.page.DataPage<T>
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api_cmd/category/query', $scope.tableGrid_category);
    };

    var loadProperties = function(categoryId) {
        listService.loadData($scope, 'api_cmd/property/query/' + categoryId, $scope.tableGrid_property);
    };

    load();



    $scope.search = function() {
        load();
    };

});


/*新增*/
angular.module('huatek.controllers').controller('categoryModifyController', function($scope, $location, $http, editService, $routeParams, $rootScope) {

    editService.init($scope, $location, $http);


    var addDataUrl = 'api_cmd/category/add';
    var editDataUrl = 'api_cmd/category/edit';
    var homeUrl = '/base/CategoryList'

    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column,title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    formFieldArray.push(new FormElement(1, '字典类型', 'kindName', 'string', 1, '50'));
    formFieldArray.push(new FormElement(1, '字典类型名称', 'categoryName', 'string', -1, '50'));

    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    editService.setFormFields($scope, formFieldArray);

    /***
     * 获取需要编辑的内容.
     */
    // $scope.editId = $routeParams.id;
    if ($scope.editId) {
        editService.loadData($scope, editDataUrl, $scope.editId);
    } else {
        $scope.editId = -1;
    }

    /***
     * 定义更新操作.
     */
    $scope.update = function() {
        editService.updateData($scope, editDataUrl, homeUrl, $scope.editId);
    }

    /**
     * 定义保存操作.
     */
    $scope.save = function() {
        editService.saveData($scope, addDataUrl, homeUrl);
    }
});