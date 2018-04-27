'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('HuatekApp').controller('CmdInterfaceReceiveMessageController', function ($scope, $location, $http,$rootScope, listService) {
	/***
	 * 定义显示的表格.
	 */
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    enableFullRowSelection : true,
	        enableSelectAll : false,
	        multiSelect: false,
		    columnDefs: [
		       { name: '接口类型', field: 'code',width: '10%', enableColumnMenu: false,decode:{field:"code",dataKey:"dic.external_interface_type"}},
		       { name: '成功与否', field: 'result1',width: '5%', enableColumnMenu: false,decode:{field:"result",dataKey:"dic.yes_or_no"}},
		       { name: '处理消息', field: 'msg',width: '10%', enableColumnMenu: false},
		       { name: '业务时间', field: 'busiTime',width: '10%', enableColumnMenu: false},
		       { name: '处理时间', field: 'createTime',width: '10%', enableColumnMenu: false},
		       { name: '请求数据', field: 'requestBody',width: '30%', enableColumnMenu: false},
		       { name: '响应数据', field: 'responseBody',width: '25%', enableColumnMenu: false},
		    ]
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");
    
    //定义搜索框
    var codeParam=new queryParam('接口类型','code','string','=',null,"dic.external_interface_type");
    codeParam.componentType='select';
    queryPage.addParam(codeParam);
    queryPage.addParam(new queryParam('业务时间-开始','busiTime','dateTime','>='));
    queryPage.addParam(new queryParam('业务时间-结束','busiTime','dateTime','<'));
    $rootScope.searchCount = queryPage.queryParamList.length;
    
    var resultParam=new queryParam('成功与否','result','string','=',null,"dic.yes_or_no");
    resultParam.componentType='select';
    queryPage.addParam(resultParam);
    
    queryPage.addParam(new queryParam('请求数据','requestBody','string','alllike'));
    queryPage.addParam(new queryParam('响应数据','responseBody','string','alllike'));
    
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope,queryPage);
    
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	      listService.setGridHeight();
	  	}; 
    
    //定义功能按钮
    var btnArray = [];
    btnArray.push(new pageButton('接口明细','detail','/cmdInterfaceReceiveMessage/detail','detailData'));
    /***
     * 设置界面的功能按钮.
     */    
   listService.setButtonList($scope,btnArray);

    /***
     * 桥接按钮事件.
     */
    $scope.execute = function(operation, param){
    	listService.editData($scope, $scope.gridApi,new popup("接口明细","/cmdInterfaceReceiveMessage/detail"));
    }
	/***
	 * 初始化加载数据.
	 */
    var load = function(){
    	listService.loadData($scope,'api/cmdInterfaceReceiveMessage/query', $scope.tableGrid);
    }
    
    load();
	/*
	 *查询需要调用的函数. 
	 */
	$scope.search = function() {
		load();
	};
	
});

/****
 * 编辑控制器.
 * 拷贝之后注意把该名字修改掉，否则会影响到原来程序的修改，切记！
 */
angular.module('HuatekApp').controller('CmdInterfaceReceiveMessageDetailController', function ($scope, $location, $http, $routeParams, editService,$rootScope,$sce) {
	 /***
	  * 定义编辑跳转的路径.
	  */
	 var addDataUrl = 'api/cmdInterfaceReceiveMessage/add';
	 var editDataUrl = 'api/cmdInterfaceReceiveMessage/edit';
	 var homeUrl = '/cmdInterfaceReceiveMessage/home';
	
   	//定义块
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	//定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'请求数据','requestBody','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'响应数据','responseBody','string',1,'30'));
    
    //设置全局变量
    $rootScope.formFieldArray = formFieldArray;

    /**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 * 以下部分如果没有特殊改动可以不做修改,直接拷贝即可.
	 */
	editService.init($scope);
    
    /***
     * 设置编辑界面输入的字段.
     * 被设置必须在服务初始化之后执行.
     */
    editService.setFormFields($scope,formFieldArray); 
    
    /***
     * 定义获取需要编辑的内容.
     */
    
    if($scope.editId){
    	editService.loadData($scope,editDataUrl, $scope.editId,function(){
    		for(var i=0;i<formFieldArray.length;i++){
    			Process(formFieldArray[i]);
    		}
    	});
    }else{
    	$scope.editId = -1;
    }
    $scope.SINGLE_TAB = "  ";
    $scope.QuoteKeys = true;
 
    function IsArray(obj) {
    	return obj && typeof obj === 'object' && typeof obj.length === 'number'
    			&& !(obj.propertyIsEnumerable('length'));
    }

    function Process(field) {
    	var json=field.value;
    	json=json.replace("\\n","");
    	SetTab()
    	var html = "";
    	try {
    		if (json == "")
    			json = "\"\"";
    		var obj = eval("[" + json + "]");
    		html = ProcessObject(obj[0], 0, false, false, false);
    		field.value=$sce.trustAsHtml("<PRE class='CodeContainer'>" + html + "</PRE>");
    	} catch (e) {
    		
    		field.value="";
    	}
    }
    $scope._dateObj = new Date();
    $scope._regexpObj = new RegExp();
    function ProcessObject(obj, indent, addComma, isArray, isPropertyContent) {
    	var html = "";
    	var comma = (addComma) ? "<span class='Comma'>,</span> " : "";
    	var type = typeof obj;
    	if (IsArray(obj)) {
    		if (obj.length == 0) {
    			html += GetRow(indent, "<span class='ArrayBrace'>[ ]</span>"
    					+ comma, isPropertyContent);
    		} else {
    			html += GetRow(indent, "<span class='ArrayBrace'>[</span>", isPropertyContent);
    			for (var i = 0; i < obj.length; i++) {
    				html += ProcessObject(obj[i], indent + 1, i < (obj.length - 1),
    						true, false);
    			}
    			html += GetRow(indent, "<span class='ArrayBrace'>]</span>" + comma);
    		}
    	} else if (type == 'object') {
    		if (obj == null) {
    			html += FormatLiteral("null", "", comma, indent, isArray, "Null");
    		} else if (obj.constructor == $scope._dateObj.constructor) {
    			html += FormatLiteral("new Date(" + obj.getTime() + ") /*"
    					+ obj.toLocaleString() + "*/", "", comma, indent, isArray,
    					"Date");
    		} else if (obj.constructor == $scope._regexpObj.constructor) {
    			html += FormatLiteral("new RegExp(" + obj + ")", "", comma, indent,
    					isArray, "RegExp");
    		} else {
    			var numProps = 0;
    			for ( var prop in obj)
    				numProps++;
    			if (numProps == 0) {
    				html += GetRow(indent, "<span class='ObjectBrace'>{ }</span>"
    						+ comma, isPropertyContent);
    			} else {
    				html += GetRow(indent, "<span class='ObjectBrace'>{</span>", isPropertyContent);
    				var j = 0;
    				for ( var prop in obj) {
    					var quote = $scope.QuoteKeys ? "\"" : "";
    					html += GetRow(indent + 1, "<span class='PropertyName'>"
    							+ quote
    							+ prop
    							+ quote
    							+ "</span>: "
    							+ ProcessObject(obj[prop], indent + 1,
    									++j < numProps, false, true));
    				}
    				html += GetRow(indent, "<span class='ObjectBrace'>}</span>" + comma);
    			}
    		}
    	} else if (type == 'number') {
    		html += FormatLiteral(obj, "", comma, indent, isArray, "Number");
    	} else if (type == 'boolean') {
    		html += FormatLiteral(obj, "", comma, indent, isArray, "Boolean");
    	} else if (type == 'function') {
    		if (obj.constructor == $scope._regexpObj.constructor) {
    			html += FormatLiteral("new RegExp(" + obj + ")", "", comma, indent,
    					isArray, "RegExp");
    		} else {
    			obj = FormatFunction(indent, obj);
    			html += FormatLiteral(obj, "", comma, indent, isArray, "Function");
    		}
    	} else if (type == 'undefined') {
    		html += FormatLiteral("undefined", "", comma, indent, isArray, "Null");
    	} else {
    		html += FormatLiteral(obj.toString().split("\\").join("\\\\")
    				.split('"').join('\\"'), "\"", comma, indent, isArray, "String");
    	}
    	return html;
    }

    function FormatLiteral(literal, quote, comma, indent, isArray, style) {
    	if (typeof literal == 'string')
    		literal = literal.split("<").join("&lt;").split(">").join("&gt;");
    	var str = "<span class='" + style + "'>" + quote + literal + quote + comma
    			+ "</span>";
    	if (isArray)
    		str = GetRow(indent, str);
    	return str;
    }

    function FormatFunction(indent, obj) {
    	var tabs = "";
    	for (var i = 0; i < indent; i++)
    		tabs += $scope.TAB;
    	var funcStrArray = obj.toString().split("\n");
    	var str = "";
    	for (var i = 0; i < funcStrArray.length; i++) {
    		str += ((i == 0) ? "" : tabs) + funcStrArray[i] + "\n";
    	}
    	return str;
    }

    function GetRow(indent, data, isPropertyContent) {
    	var tabs = "";
    	for (var i = 0; i < indent && !isPropertyContent; i++)
    		tabs += $scope.TAB;
    	if (data != null && data.length > 0 && data.charAt(data.length - 1) != "\n")
    		data = data + "\n";
    	return tabs + data;
    }


    /*缩进量*/
    function SetTab() {
    	$scope.TAB = MultiplyString(2, $scope.SINGLE_TAB);
    }

    function MultiplyString(num, str) {
    	var sb = [];
    	for (var i = 0; i < num; i++) {
    		sb.push(str);
    	}
    	return sb.join("");
    }
    
});

