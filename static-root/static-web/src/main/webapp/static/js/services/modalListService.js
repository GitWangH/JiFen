
angular.module('huatek.services').service('modalListService', function($rootScope,$modal,cacheService) {
	var _scope;
	var _location;
	var _http;
	var _queryPageMap;
	var loadURL;
	var notNeedQueryPage;
	var _menuId;
	
	/*var searchDate = [];*/
	this.init = function(scope, location, http, modal,rootScope) {
		_scope = scope;
		_location = location;
		_http = http;
		var url = _location.path();
		var position = url.indexOf("/",1);
		position = url.indexOf("/", position+1);
		if(position>0){
			url = url.substring(0, position);
		}
		var sourceModule = actionMap.get(url);
		_menuId = sourceModule.id;
		if(sourceModule){
			var parentModule = menuMap.get(String(sourceModule.parentid))||{label:''};
			
			/*定义模块名称*/

			_scope.moduleName = parentModule.label;
			_scope.subModuleName = sourceModule.label;
		}
		_scope.resetSearch = function() {
			for (var i = 0; i < _queryPage.queryParamList.length; i++) {
				_queryPage.queryParamList[i].value = null;
				if(_queryPage.queryParamList[i].type == 'dateTime'){
					_queryPage.queryParamList[i].minValue = null;
					_queryPage.queryParamList[i].maxValue = null;
				}
				if(_queryPage.queryParamList[i].type == 'number'){
					console.log(_queryPage.queryParamList[i]);
				}
				
				
			}
		};
        /*点击更多查询按钮显示所有的input数据  by wing 2016/9/5 */
		_scope.showMoreSearch = function(){
			for(var i=0;i< _scope.queryFieldList.length;i++){
				_scope.queryFieldList[i].isShowSelect = true;
			}
			_scope.showMoreBtn = false;
			_scope.hideMoreBtn = true;
		};
		_scope.hideMoreSearch = function(){
			for(var i=0;i< _scope.queryFieldList.length;i++){
				if(i>5){
					_scope.queryFieldList[i].isShowSelect = false;
				}else{
					_scope.queryFieldList[i].isShowSelect = true;
				}
			}
			_scope.hideMoreBtn = false;
			_scope.showMoreBtn = true;
		};


	};




	/***************************************************************************
	 * 导入模版下载
	 */
	
	this.downloadTemplate = function (form){
    	var actionUrl = fileServerPath+form;
    	window.location.href=actionUrl;
    };
    
    this.exportData = function (impl, param){
    	_scope.actionUrl = exportServerPath+impl;
    	for(_scope.num=0; _scope.num<_scope.queryFieldList.length; _scope.num++){
    		if(_scope.queryFieldList[_scope.num].value!=null && _scope.queryFieldList[_scope.num].value!=''){
    			_scope.actionUrl += '&'+_scope.queryFieldList[_scope.num].field+'='+_scope.queryFieldList[_scope.num].value;
    		}
    	}
    	if(param){
    		_scope.actionUrl += param
    	}
    	window.location.href=_scope.actionUrl;
    };
    
	var _queryPage;
	this.setQueryPage = function(queryPage) {
		_queryPage = queryPage;
		_scope.queryFieldList = [];
		for(var m ,i= 0;i<queryPage.queryParamList.length;i++){
			if(queryPage.queryParamList[i].isShow){
				_scope.queryFieldList.push(queryPage.queryParamList[i]);
			}
		}
		_queryPageMap = getMap(queryPage.queryParamList, "field");
		for(var index in _scope.queryFieldList){
			var field=_scope.queryFieldList[index];
			cacheService.bindFieldData(field);
		}
		
		
		for(var i=0;i<_queryPage.queryParamList.length;i++){
			if(!_queryPage.queryParamList[i].keepValue){
				continue;
			}
			var queryValue;
			var key = _menuId + "-" + _queryPage.queryParamList[i].name+i;
			if(_queryPage.queryParamList[i].type == 'dateTime'){
				var minValue=queryValues[key+"_m"];
				var maxValue=queryValues[key+"_x"];
				/*
				if(window.localStorage){
					minValue =localStorage.getItem(key+"_m");
					maxValue = localStorage.getItem(key+"_x");
				}else{
					minValue = Cookie.read(key+"_m");
					maxValue = Cookie.read(key+"_x");
				}*/
				if(minValue != null && minValue != 'null' 
					&& minValue != '' && minValue != undefined && minValue != 'undefined'){
					_queryPage.queryParamList[i].minValue = minValue;
					
					
				}
				if(maxValue != null && maxValue != 'null' 
					&& maxValue != '' && maxValue != undefined && maxValue != 'undefined'){
					_queryPage.queryParamList[i].maxValue =	maxValue;
					
					
				}
			}else {
				queryValue=queryValues[key];
				/* 
				if(window.localStorage){
					queryValue = localStorage.getItem(key);
				}else{
					queryValue = Cookie.read(key);
				}*/
				if(queryValue && queryValue != 'undefined' && queryValue != 'null'){
					if(_queryPage.queryParamList[i].logic == "in"){

						
						/*解决页面查询条件复选框值回显问题*/
						_queryPage.queryParamList[i].params =queryValue.toString().split(",");
					} else {
						if(queryValue == 'true' || queryValue == 'false'){
							_queryPage.queryParamList[i].value = Boolean(queryValue);
						}else{
								_queryPage.queryParamList[i].value = queryValue;
							
							
						}
					}
				}
			}
		}          /* 判断queryFieldList的总数量，默认显示前三条 by wing 2016/9/5 */
		for(var i=0;i<_scope.queryFieldList.length;i++){
			_scope.queryFieldList[i].isShowSelect;
			if(i>5){
				_scope.queryFieldList[i].isShowSelect = false;
				_scope.showMoreBtn = true;
			}else{
				_scope.queryFieldList[i].isShowSelect = true;
				_scope.showMoreBtn = false;
			}
		}
	};
	/**
	 * 保存查詢條件
	 */
	this.storeQueryParam = function(){
		for(var i=0;i<_queryPage.queryParamList.length;i++){
			
			/*存储，IE6~IE7 cookie 其他浏览器HTML5本地存储*/
			var values = _queryPage.queryParamList[i].values;
			var value = _queryPage.queryParamList[i].value;
			if(_queryPage.queryParamList[i].logic == 'in'){
				values = _queryPage.queryParamList[i].params
			}
			var key = _menuId + "-" + _queryPage.queryParamList[i].name+i;
			if( values && values.length > 0){
				queryValues[key]=values;
	    	}else if(_queryPage.queryParamList[i].type == 'dateTime'){
	    		queryValues[key+"_m"]= _queryPage.queryParamList[i].minValue;  
	    		/*进行本地存储*/
	    		queryValues[key+"_x"]= _queryPage.queryParamList[i].maxValue;  
	    		/*进行本地存储*/
	    	}else{
	    		queryValues[key]= value;  
	    		/*进行本地存储*/
	    	}
			/* 改成查询条件在 当前浏览器窗口生命周期内保存
		    if(window.localStorage) {
		    	if( values && values.length > 0){
		    		localStorage.setItem(key, values);
		    	}else if(_queryPage.queryParamList[i].type == 'dateTime'){
		    		localStorage.setItem(key+"_m", _queryPage.queryParamList[i].minValue);  
		    		
		    		localStorage.setItem(key+"_x", _queryPage.queryParamList[i].maxValue);  
		    		
		    	}else{
		    		localStorage.setItem(key, value);  
		    		
		    	}
		    }
		    else {
		    	if(values && values.length > 0){
		    		Cookie.write(key, values); 
		    	}else if(_queryPage.queryParamList[i].type == 'dateTime'){
		    		Cookie.write(key+"_m", _queryPage.queryParamList[i].minValue); 
		    		Cookie.write(key+"_x", _queryPage.queryParamList[i].maxValue); 
		    	}else{
		    		Cookie.write(key, value);   
		    		
		    	}
		    }*/
		}
	}
	this.getFieldMap = function() {
		return _queryPageMap;
	};
	
	this.setButtonList = function(_btnArray,_customButtonArray) {
		_scope.btnArrayList = _btnArray;
		_scope.customButtonArray = _customButtonArray;
	};
	
	this.deleteData = function(gridTable, gridApi, toUrl){
		if(gridApi.selection.getSelectedRows().length < 1){

/*			bootbox.alert("请至少勾选一条数据！");*/
			submitTips('请至少勾选一条数据！','warning');
			return false;
		}
		bootbox.confirm('确定要删除所选的数据吗?', function(result) {
	         if(result){
	        	 angular.forEach(gridApi.selection.getSelectedRows(), function (data, index) {
	        		 if(data.id == null || data.id == undefined){
	        			 return;
	        		 }
	        		 _http.delete( toUrl+"/"+ data.id).success(function () {
	        			 var postQueryPage = copyQueryPage(_queryPage);
	        				if(notNeedQueryPage){
	        					postQueryPage.orderBy = '';
	        					postQueryPage.queryParamList = [];
	        				}
	        				_scope.promise = _http.post(loadURL, postQueryPage).success(function (response) {
        						   if (response.totalRows == undefined || response.totalRows == 0) {
        							   _scope.tableGrid.data = [];
        						   } else{
        							   _scope.tableGrid.data = response.content;
        						   }
        						   _scope.tableGrid.totalItems = response.totalRows;
        					});
	 	            });
	        	});
		     }
	         _scope.$apply();
	       });
	};
	/***************************************************************************
	 * 查询后台数据返回来的就是
	 * @param notNeedQueryPage 值为true的时候去掉查询条件(在列表页面弹出模态窗口的时候，如果模态窗口是列表，loadData的时候会自动拼上父列表的查询条件，所以需要在loadData的时候把notNeedQueryPage设置为true)
	 */
	this.loadData = function(url, gridTable,notNeedQueryPage,loadSuccessCallBack){
		loadURL = url;
		notNeedQueryPage = notNeedQueryPage;
		var postQueryPage = copyQueryPage(_queryPage);
		if(notNeedQueryPage){
			postQueryPage.orderBy = '';
			postQueryPage.queryParamList = [];
		}
		for(var i=0;i<postQueryPage.queryParamList.length;i++){
			var param=postQueryPage.queryParamList[i];
			if(param.value!=undefined &&param.value!=null&&(param.logic=='like'||param.logic=='alllike')){
				if(param.value.indexOf("%")>-1){
					submitTips('警告!查询条件不能包含特殊字符','warning');
					return;
				}
			}
		}
		this.storeQueryParam();
		var _self=this;
		_scope.promise = _http.post(url, postQueryPage)
			   .success(function (response) {
				   if (response.totalRows == undefined || response.totalRows == 0) {
					   gridTable.data = [];
				   } else{
					   var data=response.content;
					  _self.decodeTable(data,gridTable,_scope);
					   gridTable.data = response.content;
				   }
				   gridTable.totalItems = response.totalRows;
				   if(angular.isFunction(loadSuccessCallBack)){
					   loadSuccessCallBack();
				   }
				   
			});
	}
	/***************************************************************************
	 * 对表格中的数据集进行转码，
	 */
	this.decodeTable=function(data,gridTable,_scope){
		var columnDefs=gridTable.columnDefs;
		for(var columnIndex in columnDefs){
			var columnDef=columnDefs[columnIndex];
			if(columnDef.decode){
				var decodeField=columnDef.field;
				var dataField=columnDef.decode.field;
				var dataKey=columnDef.decode.dataKey;
				if(!dataField||!dataKey){
					continue;
				}
				var attrCode=columnDef.decode.code?columnDef.decode.code:'code';
				var attrName=columnDef.decode.name?columnDef.decode.name:'name';
				var decodeData;
				if(dataKey.indexOf("local.")>0){
					if(_scope.localData){
						decodeData=_scope.localData[dataKey.substring(5)];
					}
				}else{
					decodeData=cacheService.getData(dataKey);
				}
				if(decodeData){
					for(var i in data){
						var dataItem=data[i];
						var code=dataItem[dataField];
						for(var y in decodeData){
							var decodeItem=decodeData[y];
							if(code===decodeItem[attrCode]){
								dataItem[decodeField]=decodeItem[attrName];
								break;
							}
						}
					}
				}
			}
		}
	};
	/**
	 * 获取字典的js服务（需要在init、setFormFields服务被调用之后，再调用）
	 * 
	 * @param toUrl
	 *            提供字典查询的服务URL
	 */
	this.initQueryParams = function (toUrl){
		_scope.promise = _http.get(toUrl).success(function (params){
    		for(var i=0;i<params.length;i++){
    			var queryParam = _queryPageMap.get(params[i].fieldName);
          	  	if(queryParam){
          	  		resolveShowFieldAndReturnField(queryParam,params[i].params);
          	  		queryParam.items = params[i].params;


/*         	  		if(!queryParam.value){*/

/*         	  			queryParam.value = params[i].defaultValue;*/

/*         	  		}	*/
          	  		
          	  		/*设置默认值*/

              	  	if(queryParam.defaultValue != undefined && queryParam.defaultValue.length > 0){
              	  		if(queryParam.logic && '=' == queryParam.logic){
              	  			queryParam.value = queryParam.value || queryParam.defaultValue[0];
              	  		}else if(queryParam.logic && 'in' == queryParam.logic){
              	  			queryParam.params = queryParam.params || queryParam.defaultValue;
              	  		}
              	  		
              	  	}
          	  	}
          	  	
    		}
    	});
	};
	
	
	/**
	 * 添加数据
	 */
    this.addData = function (url){
    	_location.path(url);
    }
    
    /**
	 * 弹出模态窗口(此类按钮走权限过滤，需要配置菜单)
	 * 
	 * @param url
	 */
    this.showModal = function(url,$modal,title){
    	var btn = actionMap.get(url);
    	return $modal({
		         title: title, 
		         content: 'My Content', 
		         show: false,
		         backdrop:'static',
		         keyboard:false,
		         controller: btn.controller,
		         template: TemplatePrefix+btn.view
		       });
    }
    this.showProcessProgress = function(fieldName){
    	var selectData = this.returnSectData(_scope.gridApi);
    	if(selectData.length>1){


/*   		bootbox.alert('警告：不能选择多条数据用于编辑。');*/

    		submitTips('警告：不能选择多条数据用于编辑。','warning');
    		return;
    	}
    	if(selectData.length==0){


/*   		bootbox.alert('请在列表中选择一条用于编辑的数据。');*/

    		submitTips('请至少勾选一条数据！','warning');
    		return;
    	}
    	var scope = $rootScope.$new();
    	if(!fieldName){
    		fieldName="flowId";
    	}
    	if(!selectData[0][fieldName]){


/*   		bootbox.alert('该记录还没有启动流程');*/

    		submitTips('该记录还没有启动流程。','warning');
    		return;
    	}
    	
    	scope.processInstanceId=selectData[0][fieldName];
    	var modal=$modal({
    			backdrop: 'static',
		         title: '流程图', 
		         show: false,
		         controller: 'processProgressController',
		         template: TemplatePrefix+'workflow/process/processProgress.html',
		         scope:scope
		       });
    	modal.$promise.then(modal.show);
    }
    
    /**
	 * 弹出模态窗口(此类按钮不走权限过滤，不需要配置菜单，通常在表单中直接弹出)
	 * 
	 * @param btnObj
	 */
    this.showModalForNoMenu = function(btnObj,$modal){
    	return $modal({
		         title: btnObj.title, 
		         content: btnObj.content, 
		         show: false,
		         backdrop:'static',
		         keyboard:false,
		         controller: btnObj.controller,
		         template: TemplatePrefix+btnObj.template
		       });
    }
    /***************************************************************************
	 * 编辑用户.
	 */
    this.editData = function(gridApi, toUrl){

    	
    	/*获取当前选择的数据.*/

    	var selectData = this.returnSectData(gridApi);
    	if(selectData.length>1){


/*   		bootbox.alert('警告：不能选择多条数据操作。');*/

    		submitTips('警告：不能选择多条数据操作。','warning');
    		return;
    	}
    	if(selectData.length==0){


/*   		bootbox.alert('请在列表中选择一条数据操作。');*/

    		submitTips('请在列表中选择一条数据操作。','warning');
    		return;
    	}
    	_location.path(toUrl + "/" + selectData[0].id);
    }
    
    
    /*打印获取背景图片方法*/
    var getFilesForLodop = function(lodop,businessId,response) {
		var cnex4_getFiles_url = "/"+URL_PATH.FILE_HEADER+'/getFiles.do?actionMethod=getFiles';
		
		$.ajax({
				url: cnex4_getFiles_url+"&businessIds="+businessId,
				method: 'GET',
				async:false,
				/*同步*/
				
				/*cache:false,*/
				success:function(data){
					data = JSON.parse(data);
					var imageUrl = data.data[0].viewUrl;
		        	lodop.ADD_PRINT_SETUP_BKIMG("<img src='"+"/api_image"+imageUrl+"'>");
		        	
		        	
		        	/*设置图片参数*/
					var bkimgLeft = response.bkimgLeft;
					var bkimgtop = response.bkimgtop;
					var bkimgWidth = response.bkimgWidth;
					var bkimgHeight = response.bkimgHeight;
					var bkimgInPreview = response.bkimgInPreview;
					var bkimgPrint = response.bkimgPrint;
					
					if(undefined!=bkimgLeft && bkimgLeft>0){
						lodop.SET_SHOW_MODE("BKIMG_LEFT",bkimgLeft);
					}
					if(undefined!=bkimgtop && bkimgtop>0){
						lodop.SET_SHOW_MODE("BKIMG_TOP",bkimgtop);
					}
					if(undefined!=bkimgWidth && bkimgWidth!=""){
						lodop.SET_SHOW_MODE("BKIMG_WIDTH",bkimgWidth);
					}
					if(undefined!=bkimgHeight && bkimgHeight!=""){
						lodop.SET_SHOW_MODE("BKIMG_HEIGHT",bkimgHeight);
					}
					if(undefined!=bkimgInPreview && bkimgInPreview=="1"){
						lodop.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
					}
					if(undefined!=bkimgPrint && bkimgPrint=="1"){
						lodop.SET_SHOW_MODE("BKIMG_PRINT",true);
					}
				}
		 });
    }
    /***************************************************************************
	 * 打印数据.
	 */
    this.printData = function(gridApi, toUrl){

    	
    	/*获取当前选择的数据.*/

    	var selectData = this.returnSectData(gridApi);
    	if(selectData.length==0){


/*   		bootbox.alert('请在列表中选择一条用于编辑的数据。');*/

    		submitTips('请在列表中选择一条用于编辑的数据。','warning');
    		return;
    	}
    	var ids = "";
    	angular.forEach(selectData, function (data, index) { 
    		ids = ids+data.id+",";
    	});
    	ids = ids.substring(0,ids.trim().length-1);
    	
    	/*_location.path(toUrl + "?ids=" + ids);*/
    	var LODOP;
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		/*此处需要判断LODOP是否为undefined，如果为undefined则说明未安装打印插件或者打印插件未正常运行*/
		if(LODOP == undefined){
			submitTips('未安装打印插件或者打印插件异常！','warning');
		}else{
	    	_http.get(toUrl + "?ids=" + ids).success(function (params){
	    		_scope.printContentArray = params;
	    		if(_scope.printContentArray.length>0)
	    		{
	    			var printReceipt = _scope.printContentArray[0][0];
	    			var paperHeight = printReceipt.paperHeight;
	    			var paperWidth = printReceipt.paperWidth;
	    			for(var i=0;i<_scope.printContentArray.length;i++)
	    			{
	    				LODOP.NEWPAGE();
	    				var printComponentDto = _scope.printContentArray[i];
	    				for(var j=0;j<printComponentDto.length;j++)
	    				{

	    					if(j==0){
	    					/*背景图片，只加载一次*/

	    				    	var businessId = printComponentDto[j].businessId;
	    				    	if(undefined!=businessId&&businessId!=""){
	    				    		getFilesForLodop(LODOP,businessId,printComponentDto[j]);
	    				    	}
	    				    }
	    					
	    					var fieldType = printComponentDto[j].fieldType;
	    					var fieldId = printComponentDto[j].fieldId;
	    					var fieldValue = printComponentDto[j].fieldValue;
	    					var fieldPosition = printComponentDto[j].fieldPosition;
	    					var fieldPositionArr = fieldPosition.split(",");
	    					var fieldFontName = printComponentDto[j].fieldFontName;
	    					var fieldFontSize = printComponentDto[j].fieldFontSize;
	    					var fieldFontBold = printComponentDto[j].fieldFontBold;

	    					if(fieldType=="1")	
	    					/*普通文本*/

	    					{
	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);	
	    					}else if(fieldType=="2")	
	    					/*条形码*/
	    					{
	    						LODOP.ADD_PRINT_BARCODE(fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],"128A",fieldValue);
	    					}else
	    					{
	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);
	    					}
	    					
	    					/*如果字体名称不为空，则设置字体名称*/
	    					if(fieldFontName.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "FontName", fieldFontName);
	    					}
	    					
	    					/*如果字体大小不为空，则设置字体大小*/
	    					if(fieldFontSize.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "FontSize", fieldFontSize);
	    					}
	    					
	    					/*如果字体粗体不为空，则设置字体粗体*/
	    					if(fieldFontBold.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "Bold", fieldFontBold);
	    					}
	    				}
	    			}
	    			
	    			/*alert(LODOP.GET_VALUE("ProgramCodes",0));*/
	    			
	    			/*LODOP.PRINT();*/
	    			/*直接打印*/
	    			LODOP.PRINTA();
	    			/*弹出打印机选择框*/
	    		}
	    	});
	    }
    }
    
    /***************************************************************************
	 * 打印预览.
	 */
    this.preViewData = function(gridApi, toUrl){

    	
    	/*获取当前选择的数据.*/

    	var selectData = this.returnSectData(gridApi);
    	if(selectData.length==0){


/*   		bootbox.alert('请在列表中选择一条用于编辑的数据。');*/

    		submitTips('请在列表中选择一条用于编辑的数据。','warning');
    		return;
    	}
    	var ids = "";
    	angular.forEach(selectData, function (data, index) { 
    		ids = ids+data.id+",";
    	});
    	ids = ids.substring(0,ids.trim().length-1);
    	
    	/*_location.path(toUrl + "?ids=" + ids);*/
    	var LODOP;
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		/*此处需要判断LODOP是否为undefined，如果为undefined则说明未安装打印插件或者打印插件未正常运行*/
		if(LODOP == undefined){
			submitTips('未安装打印插件或者打印插件异常！','warning');
		}else{
	    	_http.get(toUrl + "?ids=" + ids).success(function (params){
	    		_scope.printContentArray = params;
	    		if(_scope.printContentArray.length>0)
	    		{
	    			var printReceipt = _scope.printContentArray[0][0];
	    			var paperHeight = printReceipt.paperHeight;
	    			var paperWidth = printReceipt.paperWidth;
	    			for(var i=0;i<_scope.printContentArray.length;i++)
	    			{
	    				LODOP.NEWPAGE();
	    				var printComponentDto = _scope.printContentArray[i];
	    				for(var j=0;j<printComponentDto.length;j++)
	    				{

	    				    if(j==0){
	    				    /*背景图片，只加载一次*/

	    				    	var businessId = printComponentDto[j].businessId;
	    				    	if(undefined!=businessId&&businessId!=""){
	    				    		getFilesForLodop(LODOP,businessId,printComponentDto[j]);
	    				    	}
	    				    }
	    				    
	    					var fieldType = printComponentDto[j].fieldType;
	    					var fieldId = printComponentDto[j].fieldId;
	    					var fieldValue = printComponentDto[j].fieldValue;
	    					var fieldPosition = printComponentDto[j].fieldPosition;
	    					var fieldPositionArr = fieldPosition.split(",");
	    					var fieldFontName = printComponentDto[j].fieldFontName;
	    					var fieldFontSize = printComponentDto[j].fieldFontSize;
	    					var fieldFontBold = printComponentDto[j].fieldFontBold;

	    					if(fieldType=="1")	
	    					/*普通文本*/

	    					{
	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);	
	    					}else if(fieldType=="2")	
	    					/*条形码*/
	    					{
	    						LODOP.ADD_PRINT_BARCODE(fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],"128A",fieldValue);
	    					}else
	    					{
	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);
	    					}
	    					
	    					/*如果字体名称不为空，则设置字体名称*/
	    					if(fieldFontName.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "FontName", fieldFontName);
	    					}
	    					
	    					/*如果字体大小不为空，则设置字体大小*/
	    					if(fieldFontSize.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "FontSize", fieldFontSize);
	    					}
	    					
	    					/*如果字体粗体不为空，则设置字体粗体*/
	    					if(fieldFontBold.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "Bold", fieldFontBold);
	    					}
	    				}
	    			}
	    			
	    			/*alert(LODOP.GET_VALUE("ProgramCodes",0));*/
	    			LODOP.PREVIEW();
	    		}
	    	});
	    }
    }
    
    
    /***************************************************************************
	 * 打印数据（适用保存数据后，直接根据id进行打印，不用勾选表格，直接提供URL和id）.
	 */
    this.printDataNOGridApi = function(id, toUrl){
    	var LODOP;
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		/*此处需要判断LODOP是否为undefined，如果为undefined则说明未安装打印插件或者打印插件未正常运行*/
		if(LODOP == undefined){
			submitTips('未安装打印插件或者打印插件异常！','warning');
		}else{
			LODOP.SET_PRINT_PAGESIZE(1,"250mm","140mm","");
	    	_http.get(toUrl + "?ids=" + id).success(function (params){
	    		_scope.printContentArray = params;
	    		if(_scope.printContentArray.length>0)
	    		{
	    			var printReceipt = _scope.printContentArray[0][0];
	    			var paperHeight = printReceipt.paperHeight;
	    			var paperWidth = printReceipt.paperWidth;
	    			for(var i=0;i<_scope.printContentArray.length;i++)
	    			{
	    				LODOP.NEWPAGE();
	    				var printComponentDto = _scope.printContentArray[i];
	    				for(var j=0;j<printComponentDto.length;j++)
	    				{

	    					if(j==0){
	    					/*背景图片，只加载一次*/

	    				    	var businessId = printComponentDto[j].businessId;
	    				    	if(undefined!=businessId&&businessId!=""){
	    				    		getFilesForLodop(LODOP,businessId,printComponentDto[j]);
	    				    	}
	    				    }
	    					
	    					var fieldType = printComponentDto[j].fieldType;
	    					var fieldId = printComponentDto[j].fieldId;
	    					var fieldValue = printComponentDto[j].fieldValue;
	    					var fieldPosition = printComponentDto[j].fieldPosition;
	    					var fieldPositionArr = fieldPosition.split(",");
	    					var fieldFontName = printComponentDto[j].fieldFontName;
	    					var fieldFontSize = printComponentDto[j].fieldFontSize;
	    					var fieldFontBold = printComponentDto[j].fieldFontBold;

	    					if(fieldType=="1")	
	    					/*普通文本*/

	    					{
	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);	
	    					}else if(fieldType=="2")	
	    					/*条形码*/
	    					{
	    						LODOP.ADD_PRINT_BARCODE(fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],"128A",fieldValue);
	    					}else
	    					{
	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);
	    					}
	    					
	    					/*如果字体名称不为空，则设置字体名称*/
	    					if(fieldFontName.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "FontName", fieldFontName);
	    					}
	    					
	    					/*如果字体大小不为空，则设置字体大小*/
	    					if(fieldFontSize.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "FontSize", fieldFontSize);
	    					}
	    					
	    					/*如果字体粗体不为空，则设置字体粗体*/
	    					if(fieldFontBold.trim().length>0)
	    					{
	    						LODOP.SET_PRINT_STYLEA(j+1, "Bold", fieldFontBold);
	    					}
	    				}
	    			}
	    			
	    			/*alert(LODOP.GET_VALUE("ProgramCodes",0));*/
	    			
	    			/*LODOP.PRINT();*/
	    			/*直接打印*/
	    			LODOP.PRINTA();
	    			/*弹出打印机选择框*/
	    		}
	    	});
	    }
    }
    
    /***************************************************************************
	 * 打印全部标签（前台业务办理托运专用 Dean_yang）
	 */
    this.printDataLabelApi = function(id, toUrl){
    	var LODOP;
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		/*此处需要判断LODOP是否为undefined，如果为undefined则说明未安装打印插件或者打印插件未正常运行*/
		if(LODOP == undefined){
			submitTips('未安装打印插件或者打印插件异常！','warning');
		}else{
	    	_http.get(toUrl + "?ids=" + id).success(function (params){
	    		_scope.printContentArray = params;
	    		if(_scope.printContentArray.length>0)
	    		{
	    			var printReceipt = _scope.printContentArray[0][0];
	    			var paperHeight = printReceipt.paperHeight;
	    			var paperWidth = printReceipt.paperWidth;
	    			for(var i=0;i<_scope.printContentArray.length;i++)
	    			{
	    				var printComponentDto = _scope.printContentArray[i];
	    				var goodsMun = 0;
	    				for(var g=0;g<printComponentDto.length;g++){
	    					var fieldNameCode = printComponentDto[g].fieldNameCode;
	    					
	    					/*货物件数*/
	    					if(fieldNameCode=='goodsMun'){
	    						goodsMun = printComponentDto[g].fieldValue;
	    						break;
	    					}
	    				}
	    				
	    				
	    				/*循环货物件数*/
	    				if(goodsMun != null && goodsMun != undefined){
	    					for(var x = 0;x<goodsMun;x++){
	    						
	    						/*打印*/
	    						LODOP.NEWPAGE();
	    	    				for(var j=0;j<printComponentDto.length;j++)
	    	    				{

	    	    					if(j==0){
	    	    					/*背景图片，只加载一次*/

	    	    				    	var businessId = printComponentDto[j].businessId;
	    	    				    	if(undefined!=businessId&&businessId!=""){
	    	    				    		getFilesForLodop(LODOP,businessId,printComponentDto[j]);
	    	    				    	}
	    	    				    }
	    	    					
	    	    					var fieldType = printComponentDto[j].fieldType;
	    	    					var fieldId = printComponentDto[j].fieldId;
	    	    					var fieldValue = printComponentDto[j].fieldValue;
	    	    					var fieldPosition = printComponentDto[j].fieldPosition;
	    	    					var fieldPositionArr = fieldPosition.split(",");
	    	    					var fieldFontName = printComponentDto[j].fieldFontName;
	    	    					var fieldNameCode = printComponentDto[j].fieldNameCode;
	    	    					var fieldFontSize = printComponentDto[j].fieldFontSize;
	    	    					var fieldFontBold = printComponentDto[j].fieldFontBold;
	    	    					
	    	    					/*运单号-第几件货*/
	    	    					if(fieldNameCode=='waybill'){
	    	    						var num = x+1;
	    	    						fieldValue = fieldValue+"-"+num;
	    	    					}else if(fieldNameCode=='goodsMun'){
	    	    						break;
	    	    					}
	    	    					

	    	    					if(fieldType=="1")	
	    	    					/*普通文本*/

	    	    					{
	    	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);	
	    	    					}else if(fieldType=="2")	
	    	    					/*条形码*/
	    	    					{
	    	    						LODOP.ADD_PRINT_BARCODE(fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],"128A",fieldValue);
	    	    					}else
	    	    					{
	    	    						LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);
	    	    					}
	    	    					
	    	    					/*如果字体名称不为空，则设置字体名称*/
	    	    					if(fieldFontName.trim().length>0)
	    	    					{
	    	    						LODOP.SET_PRINT_STYLEA(j+1, "FontName", fieldFontName);
	    	    					}
	    	    					
	    	    					/*如果字体大小不为空，则设置字体大小*/
	    	    					if(fieldFontSize.trim().length>0)
	    	    					{
	    	    						LODOP.SET_PRINT_STYLEA(j+1, "FontSize", fieldFontSize);
	    	    					}
	    	    					
	    	    					/*如果字体粗体不为空，则设置字体粗体*/
	    	    					if(fieldFontBold.trim().length>0)
	    	    					{
	    	    						LODOP.SET_PRINT_STYLEA(j+1, "Bold", fieldFontBold);
	    	    					}
	    	    				}
	    					}
	    					
	    					/*LODOP.PRINT();*/
	    					/*直接打印*/
	    	    			LODOP.PRINTA();
	    	    			/*弹出打印机选择框*/
	    				}
	    				
	    			}
	    			
	    			/*alert(LODOP.GET_VALUE("ProgramCodes",0));*/
	    			
	    			/*LODOP.PRINT();*/
	    		}
	    	});
	    }
    }
    
    /***************************************************************************
	 * 打印部分标签（前台业务办理托运专用 Dean_yang）
	 * strArray(例：1-5或者1,2,3,4,5)
	 */
    this.printDataPartLabelApi = function(id, toUrl,strArray,afterInitCallback){
    	if(!strArray){
    		return ;
    	}
    	
    	var r1 = new RegExp("^\\d+(\\-\\d+)?$");
    	var r2 = new RegExp("^\\d+(\\,\\d+)?$");
    	var r3 = new RegExp("^[0-9]*$");  
    	
    	if(!r1.test(strArray) && !r2.test(strArray)){
    		if(!r3.test(strArray)){
    			submitTips('您输入的格式有误，请重新输入！','info');
        		return ;
    		}
    	}
    	
    	var arry = null;
    	var str = "";
    	
    	/*格式：1-5*/
    	if(r1.test(strArray)){
    		arry = strArray.split("-");
    		if(arry.length == 2 &&arry[0]<arry[1]){
    			for(var a=arry[0];a<=arry[1];a++){
    				str += a+",";
        		}
    			if(str != null){
    				str = str.substring(0,str.trim().length-1);
    			}
    		}else{
    			if(!r3.test(strArray)){
	    			submitTips('您输入的格式有误，请重新输入！','info');
	        		return ;
    			}
    		}
    		
    	}
    	
    	
    	/*格式：1,2,3,4,5*/
    	if(r2.test(strArray) || r3.test(strArray)){
    		str = strArray;
    	}
    	
    	var LODOP;
		LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
		/*此处需要判断LODOP是否为undefined，如果为undefined则说明未安装打印插件或者打印插件未正常运行*/
		if(LODOP == undefined){
			submitTips('未安装打印插件或者打印插件异常！','warning');
		}else{
	    	_http.get(toUrl + "?ids=" + id).success(function (params){
	    		_scope.printContentArray = params;
	    		if(_scope.printContentArray.length>0)
	    		{
	    			var printReceipt = _scope.printContentArray[0][0];
	    			var paperHeight = printReceipt.paperHeight;
	    			var paperWidth = printReceipt.paperWidth;
	    			for(var i=0;i<_scope.printContentArray.length;i++)
	    			{
	    				var printComponentDto = _scope.printContentArray[i];
	    				var goodsMun = 0;
	    				for(var g=0;g<printComponentDto.length;g++){
	    					var fieldNameCode = printComponentDto[g].fieldNameCode;
	    					
	    					/*货物件数*/
	    					if(fieldNameCode=='goodsMun'){
	    						goodsMun = printComponentDto[g].fieldValue;
	    						break;
	    					}
	    				}
	    				
	    				
	    				/*循环货物件数*/
	    				var strArr = str.split(",");
	    				if(goodsMun != null && goodsMun != undefined){
	    					var boolean = true; 
	    					for(var x = 0;x<strArr.length;x++){
	    						if(strArr[x]==null && strArr[x]!=0 || parseInt(strArr[x])>parseInt(goodsMun)){
	    							boolean = false;
	    							break;
	    						}else{
	    							boolean = true;
	    							
	    							/*打印*/
	    							LODOP.NEWPAGE();
	    							for(var j=0;j<printComponentDto.length;j++)
	    							{

	    								if(j==0){
	    								/*背景图片，只加载一次*/

	    									var businessId = printComponentDto[j].businessId;
	    									if(undefined!=businessId&&businessId!=""){
	    										getFilesForLodop(LODOP,businessId,printComponentDto[j]);
	    									}
	    								}
	    								
	    								var fieldType = printComponentDto[j].fieldType;
	    								var fieldId = printComponentDto[j].fieldId;
	    								var fieldValue = printComponentDto[j].fieldValue;
	    								var fieldPosition = printComponentDto[j].fieldPosition;
	    								var fieldPositionArr = fieldPosition.split(",");
	    								var fieldFontName = printComponentDto[j].fieldFontName;
	    								var fieldNameCode = printComponentDto[j].fieldNameCode;
	    								var fieldFontSize = printComponentDto[j].fieldFontSize;
	    								var fieldFontBold = printComponentDto[j].fieldFontBold;
	    								
	    								/*运单号-第几件货*/
	    								if(fieldNameCode=='waybill'){
	    									fieldValue = fieldValue+"-"+strArr[x];
	    								}else if(fieldNameCode=='goodsMun'){
	    									break;
	    								}
	    								

	    								if(fieldType=="1")	
	    								/*普通文本*/

	    								{
	    									LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);	
	    								}else if(fieldType=="2")	
	    								/*条形码*/
	    								{
	    									LODOP.ADD_PRINT_BARCODE(fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],"128A",fieldValue);
	    								}else
	    								{
	    									LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldValue);
	    								}
	    								
	    								/*如果字体名称不为空，则设置字体名称*/
	    								if(fieldFontName.trim().length>0)
	    								{
	    									LODOP.SET_PRINT_STYLEA(j+1, "FontName", fieldFontName);
	    								}
	    								
	    								/*如果字体大小不为空，则设置字体大小*/
	    								if(fieldFontSize.trim().length>0)
	    								{
	    									LODOP.SET_PRINT_STYLEA(j+1, "FontSize", fieldFontSize);
	    								}
	    								
	    								/*如果字体粗体不为空，则设置字体粗体*/
	    								if(fieldFontBold.trim().length>0)
	    								{
	    									LODOP.SET_PRINT_STYLEA(j+1, "Bold", fieldFontBold);
	    								}
	    							}
	    						}
	    					}
	    					
	    					/*LODOP.PRINT();*/
	    					/*直接打印*/
	    					LODOP.PRINTA();
	    					/*弹出打印机选择框*/
	    				}
	    				
	    			}
	    			
	    			/*alert(LODOP.GET_VALUE("ProgramCodes",0));*/
	    			
	    			/*LODOP.PRINT();*/
	    		}
	    	}).then(function(result){
		 		if (angular.isFunction(afterInitCallback)) {
					 afterInitCallback();
				 }
		 	});
	    }
    }
    
    /**
	 * 返回选中行对象数组
	 */
    this.returnSectData = function(gridApi){
    	return gridApi.selection.getSelectedRows();
    }
    
    this.comparisonDate=function(gridApi, toUrl){
    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length!=2){


/*   		bootbox.alert('警告：请选择两条数据进行对比!');*/

    		submitTips('警告：请选择两条数据进行对比!','warning');
    		return;
    	}
    	_location.path(toUrl + "/" + selectData[0].id+","+selectData[1].id);
    	
    }
    

	this.setTableFields = function(showFields) {
		_scope.tableFields = showFields;
	}
	
	
	 /***********************************************************************
		 * 获取当前选择数据
		 */
    this.getSelectData = function(gridApi){

    	
    	/*获取当前选择的数据.*/

    	var selectData = gridApi.selection.getSelectedRows();
    	if(selectData.length>1){


/*   		bootbox.alert('警告：不能选择多条数据。');*/

    		submitTips('警告：不能选择多条数据。','warning');
    		return;
    	}
    	if(selectData.length==0){


/*   		bootbox.alert('请在列表中至少选择要操作的数据。');*/

    		submitTips('请在列表中至少选择要操作的数据。','warning');
    		return;
    	}
    	return selectData[0];
    }
    
	
});	

/**
 * 同时只能选择一个数据用于编辑验证
 */
var tipsMessage = function(selectData){
	var flag = true;
	
	/*获取当前选择的数据.*/
	if(selectData.length>1){

/*		bootbox.alert('警告：不能选择多条数据操作。');*/
		submitTips('警告：不能选择多条数据操作。','warning');
		flag = false;
	}
    if(selectData.length==0){


/*   	bootbox.alert('请在列表中选择一条数据操作。');*/

    	submitTips('请至少勾选一条数据！','warning');
    	flag = false;
    }
    return flag;
};