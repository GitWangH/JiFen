'use strict';

angular.module('huatek.controllers').controller('FileManagerController', function($scope, $location, $http, listService, $modal, $rootScope, httpService, $compile) {

    listService.init($scope, $http, $rootScope);
    /***
     * 定义显示的表格. 
     */
    $scope.tableGrid = {
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFullRowSelection: true,
        enableSelectAll: false,
        columnDefs: [
            { name: '文件名', field: 'fileName', width: '20%', enableColumnMenu: false },
            { name: '大小(KB) ', field: 'fileSize', width: '10%', enableColumnMenu: false },
            { name: '类型', field: 'suffixName', width: '10%', enableColumnMenu: false },
            { name: '目录', field: 'filePathCh', width: '10%', enableColumnMenu: false },
            { name: '上传人', field: 'uploadUser', width: '10%', enableColumnMenu: false },
            { name: '上传时间', field: 'uploadTime', width: '*', enableColumnMenu: false },
        ]
    };

    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
        });
        /*调整grid底部高度*/
        listService.setGridHeight();
    };
    $scope.catalogList = [];
    $scope.catalogTree = null;
    /*菜单传递参数*/
    var param = $scope.passParams;
    if (!param) {
        param = "/root";
    }

    $scope.$watch('$viewContentLoaded', function() {
        $scope.treeId = uuid();
        /*加载目录树时, 根据菜单参数获取*/
        httpService.get($scope, "api/cmdFileCatalog/getAllUserCatalog", { path: param }).success(function(response) {
            {
                $scope.catalogList = response;
                for (var i = 0; i < $scope.catalogList.length; i++) {
                    $scope.catalogList[i].open = true;
                }
                $scope.initcatalogTree();
            }
        });
    });
    /*定义变量传递目录树所选参数*/
    $scope.treeParam = new Object();

    $scope.onTreeClick = function(event, treeId, treeNode) {
        /* parentIdParam.value=treeNode.id;*/
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        $scope.treeParam.tenantId = treeNode.tenantId;
        $scope.treeParam.path = treeNode.path;
        pathParam.value = treeNode.path;
        load();
    }
    $scope.initcatalogTree = function() {
        var setting = {
            data: {
                key: {
                    name: "name"
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#" + $scope.treeId), setting, $scope.catalogList);
        /*$.fn.zTree.init($("#catalogFileTree"), setting, $scope.catalogList);*/
    }

    var queryPage = new QueryPage(1, 10, "id desc", "false");

    queryPage.addParam(new queryParam('目录', 'filePathCh', 'string', 'like'));
    /*var parentIdParam=new queryParam('上级目录','catalogId','string','=');
    parentIdParam.isShow=false;
    queryPage.addParam(parentIdParam);*/
    var orgIdParam = new queryParam('orgId', 'orgId', 'string', '=');
    orgIdParam.isShow = false;
    queryPage.addParam(orgIdParam);
    var orgParam = new queryParam('机构', 'orgName', 'string');
    orgParam.componentType = 'autocomplete';
    orgParam.dropDataUrl = "api/org/public/selectAuto";
    orgParam.returnField = "name";
    orgParam.showField = "name";
    queryPage.addParam(orgParam);
    var tenantIdParam = new queryParam('租户ID', 'tenantId', 'string', '=');
    tenantIdParam.isShow = false;
    tenantIdParam.value = $rootScope.tenantId;
    queryPage.addParam(tenantIdParam);
    var pathParam = new queryParam('filePath', 'filePath', 'string', 'like');
    pathParam.value = param;
    pathParam.isShow = false;
    queryPage.addParam(pathParam);

    listService.setQueryPage($scope, queryPage);

    var btnArray = [];
    btnArray.push(new pageButton('上传', 'add', '/fileManager/upload' + param, 'uploadData'));
    btnArray.push(new pageButton('删除', 'delete', '/fileManager/delete' + param, 'deleteData'));
    btnArray.push(new pageButton('预览', 'view', '/fileManager/view' + param, 'viewData'));
    btnArray.push(new pageButton('下载', 'download', '/fileManager/download' + param, 'downloadData'));

    listService.setButtonList($scope, btnArray);


    $scope.execute = function(operation, param) {
        var selectData = $scope.gridApi.selection.getSelectedRows();
        if (operation == 'uploadData') {
            /* 系统目录内不能进行上传操作。*/
            if ((selectData.length < 1) && (undefined == $scope.treeParam.path || null == $scope.treeParam.path || "" == $scope.treeParam.path)) {
                submitTips('警告：请选择文件上传目录上传操作。', 'warning');
                return;
            }

            if (selectData.length < 1 && $rootScope.tenantId && (undefined == $scope.treeParam.tenantId || null == $scope.treeParam.tenantId || "" == $scope.treeParam.tenantId)) {
                submitTips('警告：系统目录内不能进行上传操作。', 'warning');
                return;
            }
            if (selectData.length > 1) {
                submitTips('警告：只能选择一条数据进行上传操作。', 'warning');
                return;
            }
            if (selectData.length == 1) {
                $scope.treeParam.id = selectData[0].id;
            }
            if (!$scope.passParams) {
                $scope.passParams = "/root";
            }
            var sourceUrl = "/fileManager/upload" + $scope.passParams;
            listService.addData($scope, new popup("上传", sourceUrl, $scope.treeParam, null, null, function() {
                load();
            }));
        } else if (operation == 'viewData') {
            /*预览*/
            if (selectData.length < 1) {
                submitTips('警告：请至少选择一条数据进行预览操作。', 'warning');
                return;
            }
            if (selectData.length > 1) {
                submitTips('警告：只能选择一条数据进行预览操作。', 'warning');
                return;
            }
            $scope.showPreview("api_file/view.do?id=" + selectData[0].id, selectData[0].suffixName);
        } else if (operation == 'deleteData') {
            /*系统目录下文件不能删除。*/
            this.deleteData($scope.tableGrid, $scope.gridApi, 'api/cmdFileCatalog/delete');
        } else if (operation == 'downloadData') {
            /*下载: 如果选择多条先进行压缩再下载, 一条直接下载*/
            if (selectData.length < 1) {
                submitTips('警告：请至少选择一条数据进行下载操作。', 'warning');
                return;
            }
            var ids = "";
            angular.forEach(selectData, function(data, index) {
                ids += data.id + "|";
            });
            window.open("api_file/download.do?ids=" + ids);

        }
    }

    /**
     * [删除]
     *
     * @param      {<type>}   tableGrid  [The table grid]
     * @param      {<type>}   gridApi    The grid api
     * @param      {string}   toUrl      [请求URL]
     * @return     {boolean}  
     */
    $scope.deleteData = function(tableGrid, gridApi, toUrl) {
        if (gridApi.selection.getSelectedRows().length < 1) {
            submitTips('请至少勾选一条数据！', 'warning');
            return false;
        }
        if (gridApi.selection.getSelectedRows().length > 1) {
            submitTips('至多一条数据进行删除！', 'warning');
            return false;
        }

        bootbox.confirm('确定要删除所选的数据吗?', function(result) {
            if (result) {
                var isDel = true;
                var ids = [];
                angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                    if (data.id == null || data.id == undefined) {
                        isDel = false;
                        return;
                    }
                    if ($rootScope.tenantId && (null == data.tenantId || data.tenantId == "")) {
                        submitTips('警告：文件不可删除(系统文件)', 'warning');
                        isDel = false;
                        return;
                    }
                    ids.push(data.id);
                });
                if (isDel) {
                    $http.delete(toUrl + "/" + ids).success(function() {
                        load();
                    });
                }
            }
        });

    }

    /**
     * 图片预览
     *
     * @param      {<type>}  viewUrl  预览地址
     * @param      {<type>}  fileType    文件类型
     */
    $scope.showPreview = function(viewUrl, fileType) {
        if (!(isImage(fileType) || isOffice(fileType)||isVideo(fileType))) {
            return;
        }
        var jsPanelKey = uuid(10);
        controllerProvider.register(jsPanelKey, function($scope, $controller) {
            $scope.viewUrl = viewUrl;
            $scope.fileType = fileType;
            $scope.rootElementId = jsPanelKey;
            $controller("FileOnlineReaderController", { $scope: $scope });
        });
        var modal = "<div  ng-controller='" + jsPanelKey + "'>";
        $http.get('static/js/directives/custom/cnex4FileUpload/template/file_online_read.html').success(function(res) {
            modal += res
            modal += "</div>";
            var jsPanel = $.jsPanel({
                id: jsPanelKey,
                selector: '.content',
                headerTitle: "文件在线浏览",
                contentSize: { width: 800, height: 500 },
                theme: 'dark',
                position: 'center',
                content: $compile(modal)($scope),
                draggable: {
                    containment: "parent"
                }
            });

        });
    }

    /**
     * [加载数据]
     *
     * @type       {<type>}
     */
    var load = $scope.load = function() {
        listService.loadData($scope, 'api/cmdFileManager/query', $scope.tableGrid);
    }

    load();

    /**
     * [查询]
     */
    $scope.search = function() {
        tenantIdParam.value = $rootScope.tenantId;
        load();
    }

    /**
     * [模糊查询监听]
     */
    $scope.$on('autocomplete:selected', function(event, data) {
        /*清空上次选择数据*/
        orgIdParam.value = "";
        /*此时模糊检索组件的事件被响应*/
        if (data._componentsName == 'orgName') {
            orgIdParam.value = data.code;
        }
    })
});

/**
 * 上传
 */
angular.module('huatek.controllers').controller('FileManagerUploadController', function($scope, $location, $http, $routeParams, editService, $rootScope, httpService) {
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1, '基本信息'));
    $scope.columnWrapArray = columnWrapArray;

    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var uploadForm = new FormElement(1, '上传', 'businessId', 'string', '1', '30', 'fileMultiple');
    uploadForm.systemHeader = $scope.passParams.path;
    formFieldArray.push(uploadForm);

    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

    editService.init($scope);

    editService.setFormFields($scope, formFieldArray);
    if ($scope.passParams.id) {
        var url = "api/cmdFileManager/edit";
        editService.loadData($scope, url, $scope.passParams.id);
    }

    /*var load = $scope.load = function(){
      listService.loadData($scope, 'api/cmdFileManager/query', $scope.tableGrid, null, function(response){
        uploadForm.value = response.businessId;
      }); 
    }*/

    /* $scope.back = function(){
       $scope.jsPanel.close();
       load();
     }*/
});