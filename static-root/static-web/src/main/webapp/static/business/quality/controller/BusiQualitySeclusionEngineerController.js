'use strict';
/**
 * 隐蔽工程页
 */
angular.module('huatek.controllers').controller('BusiQualitySeclusionEngineerController', function($scope, $location, httpService, listService, treeGridService,$http,$compile) {

    var queryTopLevelUrl = "/api/tendersBranch/queryTopLevel"; /*查询顶级节点URL*/
    var queryChildNodesUrl = "/api/tendersBranch/queryChildNodes/"; /*根据父级节点查询子级节点URL*/
    var saveDataUrl = "/api/tendersBranch/saveData"; /*保存修改数据RUL*/
    var queryFileUrl = "/api/busiQualitySeclusionEngineer/getSeclEngByTendersId";

    /** 左侧TreeGrid**/
    var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'tendersBranchCode', '40%'));
    columnsArr.push(new treeColumn('名称', 'tendersBranchName', '60%'));

    /**
     * 初始化表格
     */
  /*  function(_scope, columnsArr, topLevelUrl, childUrl, saveUrl, editable, isSingleCheckModel,setRowColorFn)*/
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl, false,true);

    /**
     * 设置请求参数和请求类型(非必须，默认会使用id以post方式请求)
     */
    treeGridService.setOtherConfig($scope, {loadChildField:"uuid",loadChildHttpMethed:HTTP_METHED.GET});
    
    /**
     * 选择分部分项触发
     */
    treeGridService.setNodeCheckedFn($scope, function(selectedRow) {
        if (cnex4_isNotEmpty_str(selectedRow.id)) {
            httpService.get($scope, queryFileUrl + '/' + selectedRow.id, null).success(function(data) {
            	console.log(data)
                $scope.tableGrid_right.data = data;
            });
        }


    });

    /** 查询条件 **/
    var queryPage = new QueryPage(1, 10, "orderNumber asc", "false");

    var tendersName = new queryParam('标段名称', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.TENDERS_SLECT;
    queryPage.addParam(tendersName);
    queryPage.addParam(new queryParam('分部分项名称', 'tendersBranchName', 'string', 'alllike'));
    listService.setQueryPage($scope, queryPage);

    /**
     * 加载数据
     */
    var load = $scope.load = function() {
    	if(tendersName.value){
    		httpService.get($scope, "api/tendersBranch/getTendersFlowInfo/" + tendersName.value)
    		.success(function(response) {
    			console.log("分部分项审批状态："+response.approvalStatus);
    			if(response.approvalStatus == 'flow_passed'){
    				$scope.huatekTree.loadData($scope.queryPage);
    			}
    		});
    	}
    }

    load();

    /**
     * 查询
     */
    $scope.search = function() {
        load();
    };

    /**
     * 分部分项上传文件(子表)
     */
    $scope.tableGrid_right = {
        useExternalPagination: true,
        columnDefs: [
            { name: '编号', field: 'tendersBranchCode', width: '25%', enableColumnMenu: false,cellTemplate: '<a style="color:black;">{{grid.rows.indexOf(row)+1}}</a>' },
            { name: '名称', field: 'fileName', width: '25%', enableColumnMenu: false, cellTemplate: '<a ng-click="grid.appScope.viewInfo(row.entity)">{{row.entity.fileName}}</a>' },
            { name: '上传人', field: 'modifierName', width: '25%', enableColumnMenu: false },
            { name: '上传时间', field: 'modifyTime', width: '25%', enableColumnMenu: false },
        ]
    };
    $scope.tableGrid_right.onRegisterApi = function(gridApi) {
        $scope.tableGrid_right = gridApi;
        /*gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
            if (!rowEntity.isNewRow) {
                rowEntity.isEdited = true;
            }
        });*/
        listService.setGridHeight();
    };

    /**
     * 查看附件
     */
    $scope.viewInfo = function(entity,row) {
    	httpService.get($scope,URL_PATH.FILE_HEADER + '/getFiles.do?actionMethod=getFiles',{ businessIds: entity.fileId }).success(function(response){
    		if(response && response.data && response.data.length>0){
    			$scope.showPreview(URL_PATH.FILE_HEADER +"/view.do?id=" + response.data[0].id, response.data[0].fileType);
    		}
    	})
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
    
});