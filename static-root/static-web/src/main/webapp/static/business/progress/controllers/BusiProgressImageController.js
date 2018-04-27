/**
 * 标段形象清单划分Controller 
 */
'use strict';

angular.module('huatek.controllers').controller('BusiProgressImageController', function($scope, httpService, listService, treeGridService) {
    /*查询顶级节点URL*/
    var queryTopLevelUrl = '/api/busiProgressImage/queryTopLevel';
    /*根据父级节点查询子级节点URL*/
    var queryChildNodesUrl = '/api/busiProgressImage/queryChildNodes/';
    /*保存修改数据RUL*/
    var saveDataUrl = '/api/busiProgressImage/saveData';
    /*新增形象清单的数据RUL*/
    var createDataUrl = '/api/busiProgressImage/createData'
    /*被选中的一行数据*/
    var selectRow = null;
    /*记录是否被选中状态*/
    var isSelected = false;

    /**创建表格*/
    var columnsArr = [];
    columnsArr.push(new treeColumn('编号', 'code', '10%'));
    columnsArr.push(new treeColumn('名称', 'name', '20%'));
    columnsArr.push(new treeColumn('单位', 'unit', '20%', true, new otherConfig('select', 'dic.unit_base')));
    columnsArr.push(new treeColumn('是否是重点工程', 'keyProject', '20%', true, new otherConfig('select', 'dic.is_keyProject')));
    columnsArr.push(new treeColumn('备注', 'remark', '30%'));

    /**
     * 初始化表格
     */
    treeGridService.init($scope, columnsArr, queryTopLevelUrl, queryChildNodesUrl, saveDataUrl);

    /** 查询条件 */
    var queryPage = new QueryPage(1, 1000, "orderNumber asc", "false");
    treeGridService.setQueryPage($scope, queryPage);

    var tendersName = new queryParam('形象清单所属标段', 'busiFwOrg.id', 'string', '=');
    tendersName.componentType = SEARCH_COMPONENT.GROUPS_SELECT;
    queryPage.addParam(tendersName);
    tendersName.event = function(orgId) {
        load();
    }
    var code = new queryParam('编号', 'code', 'string', 'like');
    queryPage.addParam(code);
    var name = new queryParam('名称', 'name', 'string', 'like');
    queryPage.addParam(name);
    /** 查询条件初始化 */
    listService.setQueryPage($scope, queryPage);

    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('新增', 'create', '/busiProgressImage/create', 'create'));
    btnArray.push(new pageButton('保存', 'save', '/busiProgressImage/save', 'save'));
    /* 设置界面的功能按钮.*/
    listService.setButtonList($scope, btnArray);

    /**
     * 选中树节点的回调
     */
    treeGridService.setNodeCheckedFn($scope, function(selectedRow){
    	if(isSelected == true && selectRow.id == selectedRow.id){
    		isSelected = false;
    		selectRow = null;
    	} else {
    		selectRow = selectedRow;
    		isSelected = true;
    	}
    });
        
    /**
     * 桥接路由
     */
    $scope.execute = function(operation, param) {
        if (operation == 'save') {
            var orgId = tendersName.value;
            if (cnex4_isNotEmpty_str(orgId)) {
                $scope.huatekTree.saveAll(saveDataUrl + "/" + orgId);
            } else {
                submitTips('请选择项目工程量清单所属', 'warning');
                return;
            }
        }else if(operation == 'create'){
        	/*var orgId = tendersName.value;
        	if(cnex4_isNotEmpty_str(orgId)){
        		httpService.post($scope, createDataUrl + "/" + orgId, null).success(function(){
        			刷新页面
        			load();
        		}).error(function(){
        			 submitTips('生成失败。', 'warning');
                     return;
        		});
        	} else {
        		 submitTips('请选择项目工程量清单所属', 'warning');
                 return;
        	}*/
        	
        	if(selectRow != null && (selectRow != undefined || selectRow != null)){
        		/*已选中数据新增*/
        		alert("已选中数据新增");
        	} else {
        		/*未选中数据 新增*/
        		alert("未选中数据 新增");
        	}
        }
    }

    /*加载数据*/
    var load = $scope.load = function() {
        $scope.huatekTree.loadData($scope.queryPage);
    }

    load();

    /**
     * 查询
     */
    $scope.search = function() {
        load();
    };
    
});