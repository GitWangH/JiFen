/**
 * 工程标段管理 - 修改
 */
angular.module('huatek.controllers').controller('busiProjectManagementOfBidSectionEditController', function ($scope, $location, $http, $routeParams, editService, cacheService,listService,shareData,httpService,cacheService) {
	var saveUrl = "/api/busiProjectManagementOfBidSection/add";
	var loadUrl = "/api/busiProjectManagementOfBidSection/getDetailInfoByParentId/";
	/** 工程标段管理主页面传输数据 */
	var params = $scope.passParams;
	/** 用户操作的数据  删除*/
	var mData = [];
	/** 用于校验的数据字典 - 桩号类型 kind_name : pile_no_type*/
	var pileNoType;
	/** 校验数据是否可以删除*/
	var checkUrl = '/api/busiProjectManagementOfBidSection/checkIsCanDelete';
	
    /*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'工程标段管理'));
    $scope.columnWrapArray = columnWrapArray;
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var name = new FormElement(1,'标段名称','name','string','require','50');
    name.readonly = 'readonly';
    formFieldArray.push(name);/*系统带出*/
    var code = new FormElement(1,'标段编号','code','string','require','100');
    code.readonly = 'readonly';
    formFieldArray.push(code);
    formFieldArray.push(new FormElement(1,'起始桩号','initialPileNumber','string','require','100','',function(data){
    	initialPileNumberEvent(typeof data == 'undefined' ? '' : data);
    }));
    formFieldArray.push(new FormElement(1,'结束桩号','endPileNumber','string','require','100','',function(data){
    	endPileNumberEvent(typeof data == 'undefined' ? '' : data);
    }));
    var id = new FormElement(1,'id','id','string','require','50');
    id.isShow = false;
    formFieldArray.push(id);
    formFieldArray[0].value = params.name;
    formFieldArray[1].value = params.code;
    formFieldArray[2].value = params.initialPileNumber;
    formFieldArray[3].value = params.endPileNumber;
    formFieldArray[4].value = params.id;
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
	$scope.tableGrid = {
	    enableFullRowSelection : true,
	    enableSelectAll : false,
	    multiSelect: true,
	    columnDefs: [
           { name: '编号', field: 'code',width: '10%', enableColumnMenu: false},
           { name: '单位工程', field: 'nameOfUnitProject',width: '10%', enableColumnMenu: false},
           { name: '单位工程类型', field: 'typeOfUnitProject',width: '10%', enableColumnMenu: false, cellFilter: 'unitFilter',
        	   editableCellTemplate: 'ui-grid/dropdownEditor',  editDropdownValueLabel: 'value', editDropdownOptionsArray: []},
           { name: '起始桩号', field: 'initialPileNumber',width: '18%', enableColumnMenu: false},
           { name: '结束桩号', field: 'endPileNumber',width: '18%', enableColumnMenu: false},
           { name: '经度', field: 'longitude',width: '10%', enableColumnMenu: false},
           { name: '纬度', field: 'latitude',width: '10%', enableColumnMenu: false},
           { name: '长度（KM）', field: 'longKm',width: '8%', enableColumnMenu: false}
	    ]
	};
	
    /**初始化表格*/
	listService.init($scope, $location, $http);
    
    /**
	 * 注册gridApi的选择器.
	 */
    $scope.tableGrid.onRegisterApi = function(gridApi) {
        $scope.gridApi = gridApi;
        /*编辑单元格是将该数据更改为已编辑状态*/
        gridApi.edit.on.afterCellEdit($scope, function(rowEntity, colDef, newValue, oldValue) {
        	if(typeof rowEntity.id === 'undefined'){
        		rowEntity.operation = 0;
        	} else {
        		rowEntity.operation = 1;
        	}
        	/*起始桩号校验*/
        	if(typeof rowEntity.initialPileNumber != 'undefined'){
        		initialPileNumberTable(rowEntity.initialPileNumber, rowEntity);
        	}
        	/*结束桩号校验*/
        	if(typeof rowEntity.endPileNumber != 'undefined'){
        		endPileNumberTable(rowEntity.endPileNumber, rowEntity);
        	}
        });
    };
	

    
    /**数据加载*/
    var load = function(){
        if(typeof params.id != 'undefined' || $scope.editId){
        	var id = params.id || $scope.editId;
            listService.loadData($scope, loadUrl + id , $scope.tableGrid, true);
        }
    }

    
    /*定义功能按钮*/
    var btnArray = [];
    btnArray.push(new pageButton('保存', 'save', '/busiBaseEngineeringQuantityList/save', 'save'));
    /**
     * 设置界面的功能按钮.
     */
    listService.setButtonList($scope, btnArray);
	
    $scope.execute = function(operation, param){
    }
    
    /** 获取grid中修改的数据 包括增加 修改 删除 */
    var getModifyedData = function(){
    	var gridData = $scope.tableGrid.data;
    	var data = [];
    	for(var i = 0; i < gridData.length; i++){
    		if(typeof gridData[i].operation != 'undefined'){
    			data.push(gridData[i]);
    		}
    	}
    	if(mData.length != 0){
    		/*去除mData数组中id为undefined的元素*/
    		for(var i = 0; i < mData.length; i++){
    			if(mData[i].id){
    				data.push(mData[i]);
    			}
    		}
    		/*data = data.concat(mData);*/
    	}
    	return data;
    }
    
    /**
     * 构造传输的数据
     */
    var buildPostData = function(formData){
    	var data = {};
    	var busiProjectManagementOfBidSectionDetailDtoList = getModifyedData();/*[{"id":"","nameOfUnitProject":"名称99", "typeOfUnitProject":"11","operation":"0"},{"id":"","nameOfUnitProject":"名称4", "typeOfUnitProject":"22","initialPileNumber":"START_001","endPileNumber":"END_100","longitude":"125.88","latitude":"188.99","longKm":"180","operation":"0"}];*/
    	data.id = params.id;
    	data.initialPileNumber = formData.initialPileNumber;
    	data.endPileNumber = formData.endPileNumber;
    	data.orgId = params.orgId;
    	data.busiProjectManagementOfBidSectionDetailDtoList = busiProjectManagementOfBidSectionDetailDtoList;
    	return data;
    }
    
    
    /**更新 & 保存*/
    $scope.update = function(){
    	var data = buildPostData(editService.getPostData($scope));
    	var dtoList = data.busiProjectManagementOfBidSectionDetailDtoList;
		if(dtoList.length != 0){
    		for(var i = 0; i < dtoList.length; i++){
    			if(typeof dtoList[i].code == 'undefined' || dtoList[i].code == ''){
    				submitTips('编号不能为空。','error');
    				return false;
    			} 
    			if(typeof dtoList[i].nameOfUnitProject == 'undefined' || dtoList[i].nameOfUnitProject == ''){
    				submitTips('单位工程不能为空。','error');
    				return false;
    			} 
    			if(typeof dtoList[i].typeOfUnitProject == 'undefined' || dtoList[i].typeOfUnitProject == ''){
    				submitTips('单位工程类型不能为空。','error');
    				return false;
    			}
    		}
		}

    	var map = getMap(formFieldArray, 'name');
    	if(map.get('initialPileNumber').value == undefined || map.get('initialPileNumber').value == ''){
    		submitTips('起始桩号不能为空。','error');
    		return;
    	}
    	if(map.get('endPileNumber').value == undefined || map.get('endPileNumber').value == ''){
    		submitTips('结束桩号不能为空。','error');
    		return;
    	}
    	httpService.post($scope, saveUrl, data).success(function (response) {
    		$scope.back();
    		/*刷新页面数据*/
    		params.load();
	    }).error(function(){
	    	submitTips('保存出错，请重试。','error');
	    });
    }
    
    load();
    
    /**增加一行数据*/
    $scope.addRow = function(){
    	var data = {};
    	$scope.tableGrid.data.push(data);
    }

    /** 删除数据 */
    $scope.deleteRow = function(){
    	if(listService.selectMany($scope.gridApi)){
    	    var selectData = $scope.gridApi.selection.getSelectedRows();
    	    var selectDateList = [];
    	    for(var i = 0; i < selectData.length; i++){
    	    	selectDateList.push(selectData[i].id);
    	    }
    	    /**校验是否可以删除选中的数据 -- BusiProjectManagementOfBidSectionAction.checkIsCanDeleteByDetailIdList*/
        	httpService.post($scope, checkUrl, selectDateList).success(function (response) {
        		/*如果 response.length != 0 表示数据可以删除*/
        		if(response != 1){
        			submitTips(response ,'error');
        		} else { /*如果 response.length == 0 表示数据可以删除 将数据放入 mData数组中，在用户保存时执行 isDelete = 1 的数据修改*/
            	    for(var i = 0; i < selectData.length; i++){
            	    	mData.push($scope.tableGrid.data.splice($scope.tableGrid.data.indexOf(selectData[i]), 1)[0]);
            	    }
            	    for(var i = 0; i < mData.length; i++){
            	    	mData[i].operation = 2;
            	    	mData[i].isDelete = 1;
            	    }
        		}
    	    });
    	}
    }
    
    /* 起始桩号from 数据校验 */
    var initialPileNumberEvent = function(data){
    	$scope.formFieldArrayList[2].value = toCDB(data).toUpperCase();/*全角转半角*/
    	checkPileNumber(data,"起始桩号", $scope.formFieldArrayList, 2, 0, pileNoType);
    }
    /* 结束桩号from 数据校验 */
    var endPileNumberEvent = function(data){
    	$scope.formFieldArrayList[3].value = toCDB(data).toUpperCase();/*全角转半角*/
    	checkPileNumber(data,"结束桩号", $scope.formFieldArrayList, 3, 0, pileNoType);
    }
    /* 起始桩号表格 数据校验*/
    var initialPileNumberTable = function(data, obj){
    	obj['initialPileNumber'] = toCDB(data).toUpperCase();/*全角转半角*/
    	checkPileNumber(data,"起始桩号", $scope.formFieldArrayList, 'initialPileNumber', 1, pileNoType, obj);
    }
    /* 结束桩号表格 数据校验 */
    var endPileNumberTable = function(data, obj){
    	obj['endPileNumber'] = toCDB(data).toUpperCase();/*全角转半角*/
    	checkPileNumber(data,"结束桩号", $scope.formFieldArrayList, 'endPileNumber', 1, pileNoType, obj);
    }
    /**获取数据字典的property key*/
    var getPropertyList = function(list){
    	var result = [];
    	for(var i = 0; i < list.length; i++){
    		result.push(list[i].name);
    	}
    	return result;
    }
    /** 用于校验的数据字典 - 桩号类型 kind_name : pile_no_type*/
	pileNoType = getPropertyList(cacheService.getData("dic.pile_no_type"));
	
	/**
	 * 获取 typeOfUnitProject1 单位工程类型 选项
	 */
	var getTypeOfUnitProject = function(){
		var typeOfUnitProject = cacheService.getData("dic.unit_project_type");
		var newTypeOfUnitProject = [];
		if(typeOfUnitProject.length != 0){
			for(var i = 0; i < typeOfUnitProject.length; i++){
				var obj = {};
				obj.id = typeOfUnitProject[i].code;
				obj.value = typeOfUnitProject[i].name;
				newTypeOfUnitProject.push(obj);
			}
		}
		var tableGridDataMap = getMap($scope.tableGrid.columnDefs, "field");
		tableGridDataMap.get("typeOfUnitProject").editDropdownOptionsArray = newTypeOfUnitProject;
	}
	getTypeOfUnitProject();
	
	/**
	 * 如果用户点击查看按钮，将查看页面的表单和列表设置为只读，添加和删除按钮在模板里做了ng-if 设置。
	 */
	if($scope.lookModel){
		var map = getMap(formFieldArray, 'name');
		/*表单只读*/
		for(var i = 0; i < map.keys.length; i++){
			map.get(map.keys[i]).readonly = 'readonly';
		}
		/*tableGrid只读*/
		var columnDefs = $scope.tableGrid.columnDefs;
		for(var i = 0; i < columnDefs.length; i++){
			columnDefs[i].enableCellEdit = false;
		}
	}
 }).filter('unitFilter', function(cacheService) {
	 		/*修改页面 ‘单位工程类型’ 的过滤器*/
			var unitProperty = cacheService.getData("dic.unit_project_type");
			var obj = {};
			for (var i = 0; i < unitProperty.length; i++) {
				obj[unitProperty[i].code] = unitProperty[i].name;
			}
			return function(input) {
				if (!input) {
					return '';
				} else {
					return obj[input];
				}
			};
		})
