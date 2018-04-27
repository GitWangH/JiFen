/**
 * 项目基本信息
 */
'use strict';
   
angular.module('huatek.controllers').controller('busiProjectBaseInfoController', function ($scope, $location, $http,$rootScope,editService, listService,shareData,httpService) {
	/**queryAction*/
	var queryUrl = "/api/busiProjectBaseInfo/query";
	/**创建表格*/
	$scope.tableGrid = {
		paginationPageSizes: [10, 25, 50,100],
	    paginationPageSize: 10,
	    useExternalPagination: true,
	    enableFullRowSelection : true,
	    enableSelectAll : false,
	    multiSelect: false,
	    lookDetailConfig:{menuKey:'/baseInfo/add',name:'查看',paramFieldsArr:['buildCompany']},
	    columnDefs: [
           { name: '项目全称', field: 'projectFullName',width: '20%', enableColumnMenu: false},
           { name: '项目简称', field: 'projectAbbreviation',width: '10%', enableColumnMenu: false},
           { name: '建设单位', field: 'buildCompany',width: '20%', enableColumnMenu: false},
           { name: '项目状态', field: 'projectStatus',width: '10%', enableColumnMenu: false, decode: { field: "projectStatus", dataKey: "dic.project_category" }},
           { name: '项目概算（万元）', field: 'projectBudgetEstimate',width: '10%', enableColumnMenu: false},
           { name: '开工日期', field: 'commencementDate',width: '10%', enableColumnMenu: false},
           { name: '竣工日期', field: 'completionDate',width: '10%', enableColumnMenu: false},
           { name: '创建时间', field: 'createTime',width: '10%', enableColumnMenu: false}
	    ]
	};
	/**初始化表格*/
	listService.init($scope, $http, $scope.tableGrid);
	
	/** 查询条件 */
	var queryPage = new QueryPage(1, 10, "a.id asc", "false");
    var projectFullName = new queryParam('项目全称', 'a.projectFullName', 'string', 'like');
    queryPage.addParam(projectFullName);
    var buildCompany = new queryParam('项目状态', 'a.projectStatus', 'string', 'like','','dic.project_category');
    buildCompany.componentType  = SEARCH_COMPONENT.SELECT;
    queryPage.addParam(buildCompany);
	/** 查询条件初始化 */
	listService.setQueryPage($scope,queryPage);
	
    /**
	 * 注册gridApi的选择器.
	 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
            queryPage.page = newPage;
            queryPage.pageSize = pageSize;
            load();
            listService.setGridHeight();
        });
    };
    
    
    /*定义功能按钮/*/
	var btnArray = [];
	btnArray.push(new pageButton('修改', 'edit', '/baseInfo/add', 'edit'));
	/*btnArray.push(new pageButton('查看', 'check', '/baseInfo/check', 'check'));*/

	/**
	 * 设置界面的功能按钮.
	 */
    listService.setButtonList($scope, btnArray);
    
    var load = function(){
    	listService.loadData($scope, queryUrl, $scope.tableGrid,null ,function(response){
    		var content = response.content;
    		if(typeof content != 'undefined'){
        		for(var i = 0; i < content.length; i++){
        			content[i].completionDate = getDate(content[i].completionDate);
        			content[i].commencementDate = getDate(content[i].commencementDate);
        		}
    		}
    	});
    }
    
    /**
	 * 桥接按钮事件.
	 */
    $scope.execute = function(operation, param){
        if(operation=='edit'){
            if(listService.selectOne($scope.gridApi)) {
            	/*listService.addData($scope, new popup("新增","/managementOfBidSection/edit", obj, "1300", "600"));*/
            	var data = {};
            	data.jsdw = $scope.tableGrid.data[0].buildCompany;
            	data.orgIdForShow = $scope.tableGrid.data[0].orgIdForShow;
            	data.load = load;
            	if($scope.gridApi.selection.getSelectedRows()[0].id){
            		$scope.editId = $scope.gridApi.selection.getSelectedRows()[0].id;
            		listService.addData($scope, new popup("工程项目","/baseInfo/edit", data , "1300", "600"));
            	} else {
            		/*新增*/
            		listService.addData($scope, new popup("工程项目","/baseInfo/add", data , "1300", "600"));
            	}
            }
        } else if (operation=='check'){
        	/*alert("查看");
        	httpService.get($scope, "/api/commonInterface/getTendersBranchByKeywordAndOrgId/工/102064",null);
        	console.log( $scope.gridApi.selection.getSelectedRows());
        	//console.log($rootScope);
        	var f = {
        	    ukey : "034-fjkld-djlfdeqeet",
    	    	entrustSampleBillNo : '2016-ZJ-0245',
    	    	testDate: '2016-11-30 08:12:00',
    	    	reportAddress : 'http://gs.linrain.com/dfxtn/getdfxrpt.aspx?p=54&id=95827',
    	    	reportConclusion:'合格',
    	    	reportDate:'2016-11-30 08:10:00',
    	    	reportName:'水泥砼抗压试验11111111113',
    	    	reportCode:'2016-TKY-0713',
    	    	reportResult:'不合格描述不合格描述50000565656565678798798',
    	    	entrustSampleBillDate:'2016-11-30 08:07:05',
    	    	measureUnit:'千克',
    	    	sampleName:'水泥砼抗压试块',
    	    	sampleCode:'YP-2016-TKY-0608',
    	    	batch:'1',
    	    	quantity:'1',
    	    	supplier:'祁连山',
    	    	checkDate:'2016-11-30 08:09:00',
    	    	checkCode:'1-1B	',
    	    	checkQuantity:'1',
    	    	checkUserName:'张san',
    	    	checkResult:'合格',
    	    	sampleUnit:'块',
    	    	checkType:'1',
    	    	machineNo:'1#-01',
    	    	factOrgId:'102001',
    	    	operateType:'1'
        	}
        	var d = {
                	'app_key' : '91bb90b3-2bbb-42ca-b724-1d74d7801b32',
                	'method' : 'rawMaterialInspection',
                	'timestamp' : '2016-11-30 08:09:00',
                	'data' : JSON.stringify(f),
                	'sign' : "1"
        	}

        	console.log(d);
        	console.log(JSON.stringify(f));
        	httpService.post($scope, "/api/public/external/quality",d);
        	alert('查看');*/
        } 
    }
    
    
    load();
    
    $scope.search = function() {
        load();
    };

});