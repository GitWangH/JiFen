'use strict';
   
angular.module('HuatekApp').controller('PrintFieldController', function ($scope, $location, $http,$routeParams,$rootScope, listService) {
	var parentId = $routeParams.id;
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '字段类型', field: 'fieldType',width: '10%', enableColumnMenu: false},
		       { name: '字段英文名称', field: 'fieldEnName',width: '10%', enableColumnMenu: false},
		       { name: '字段中文名称', field: 'fieldZhName',width: '10%', enableColumnMenu: false},
		       { name: '字体名称', field: 'fieldFontName',width: '10%', enableColumnMenu: false},
		       { name: '字体大小', field: 'fieldFontSize',width: '10%', enableColumnMenu: false},
		       { name: '字体粗体', field: 'fieldFontBold',width: '10%', enableColumnMenu: false},
		       { name: '打印票据', field: 'receiptName',width: '10%', enableColumnMenu: false},
		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false},
		       { name: '操作人', field: 'operatorName',width: '10%', enableColumnMenu: false},
		       { name: '操作时间', field: 'operateTime',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"field_id desc","false");
    
       
    queryPage.addParam(new queryParam('字段英文名称','fieldEnName','string','like'));
    queryPage.addParam(new queryParam('字段中文名称','fieldZhName','string','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage(queryPage);
    
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
	    btnArray.push(new pageButton('新增','add','/printField/add','addData'));
	    btnArray.push(new pageButton('编辑','edit','/printField/edit','editData'));
	    btnArray.push(new pageButton('删除','delete','/printField/delete','deleteData'));
	    btnArray.push(new pageButton('返回','back','/printReceipt/home','printReceiptList'));
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
    	if(operation=='addData'){
        	listService.addData('/printField/add/'+parentId);
        }else if(operation=='deleteData'){
        	listService.deleteData($scope.tableGrid, $scope.gridApi, URL_PATH.CMD_HEADER+'/printField/delete');
        }else if(operation=='editData'){
        	listService.editData($scope.gridApi, '/printField/edit/'+parentId);
        }else if(operation=='printReceiptList'){
        	$location.path('/printReceipt/home');
        }
    }
	   
    var load = function(){
    	listService.loadData(URL_PATH.CMD_HEADER+'/printField/query/'+parentId, $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('HuatekApp').controller('PrintFieldAddController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	var parentId = $routeParams.parentId;
	    
	 var addDataUrl = URL_PATH.CMD_HEADER+'/printField/add';
	 var editDataUrl = URL_PATH.CMD_HEADER+'/printField/edit';
	 var homeUrl = '/printField/home/'+parentId;
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
          var fieldTypeEmt = new FormElement(1,'字段类型','fieldType','string',1,'10', 'select');
          fieldTypeEmt.items = [{_returnField:'1',_showField:'文本'},{_returnField:'2',_showField:'条形码'}];
    	  formFieldArray.push(fieldTypeEmt);
		  formFieldArray.push(new FormElement(1,'字段英文名称','fieldEnName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'字段中文名称','fieldZhName','string',1,'30'));
		  var fieldFontNameEmt = new FormElement(1,'字体名称','fieldFontName','string',0,'20','select');
		  fieldFontNameEmt.items = [{_returnField:'微软雅黑',_showField:'微软雅黑'},{_returnField:'宋体',_showField:'宋体'}];
		  fieldFontNameEmt.value="";
		  formFieldArray.push(fieldFontNameEmt);
		  formFieldArray.push(new FormElement(1,'字体大小','fieldFontSize','number',0,'2'));
		  var fieldFontBoldEmt = new FormElement(1,'字体粗体','fieldFontBold','string',0,'20','select');
		  fieldFontBoldEmt.items = [{_returnField:'1',_showField:'粗体'},{_returnField:'0',_showField:'非粗体'}];
		  formFieldArray.push(fieldFontBoldEmt);
		  formFieldArray.push(new FormElement(1,'备注','remark','string',0,'30','textarea'));
    
    
    /*设置全局变量*/
    $rootScope.formFieldArray = formFieldArray;

       
	editService.init($scope, $location, $http);
    
       
    editService.setFormFields(formFieldArray); 
    
       
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    	
       
    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    } 
       
    $scope.save = function(){
    	editService.saveData(addDataUrl+"/"+parentId, homeUrl);
    }
});

   
