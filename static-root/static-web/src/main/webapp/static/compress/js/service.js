'use strict';

/*查询条件保存对象*/
var queryValues={};

angular.module('huatek.services', []).factory('ShopArr', function ($resource) {
    return $resource('static/directives/oi-select/src/shopArr.json', {}, {
            query: {method: 'GET', cache: true, isArray: true}
        }
    );
})






/*缓存服务*/
angular.module('huatek.services').service("cacheService",['$cacheFactory','$http','$localStorage',function($cacheFactory,$http,$localStorage){
	this.cacheLongTime=24*60*60*1000;
	this.cachePrefix=["dic","region","config"];
	this.bindFieldData=function(field,key){
		if(key==undefined){
			key=field.dropDataUrl;
		}
		if(key==undefined||key==null||key==""){
			return;
		}
		key+="";
		var prefix=null;
		for(var item in this.cachePrefix) {
			if(key.indexOf(this.cachePrefix[item]+".")==0){
				prefix=this.cachePrefix[item];
				break;
			}
		}
		if(prefix==null){
			return;
		}
		
		if(prefix=="dic"){
			this._getDicData(key,field);
			
		}else if(prefix=="region"){
			this._getRegionData(key,field);
		}
	};
	this.getData=function(key){
		var prefix=null;
		for(var item in this.cachePrefix) {
			if(key.indexOf(this.cachePrefix[item]+".")==0){
				prefix=this.cachePrefix[item];
				break;
			}
		}
		if(prefix==null){
			return null;
		}
		if(prefix=="dic"){
			var dic=this._getDicData(key);
			return dic;
		}else if(prefix=="region"){
			var region=this._getRegionData(key);
			return region;
		}else if(prefix=="config"){
			var value=this._getConfigData(key);
			return value;
		}
	};
	this._getDicData=function(key,field){
		var cache=$cacheFactory.get("dic");
		if(!cache){
			cache=$cacheFactory("dic");
		}
		var dic=cache.get(key);
		if(dic){
			if(field){
				resolveShowFieldAndReturnField(field,dic);
				field.items=dic;
			}else{
				return dic;
			}
			
		}else{
			if($localStorage[key]&&$localStorage[key].expire>new Date().getTime()){
				dic=$localStorage[key].data;
				cache.put(key,dic);
				if(field){
					resolveShowFieldAndReturnField(field,dic);
					field.items=dic;
				}else{
					return dic;
				}
			}else{
				var dicKindName=key.substring(4)
				$.ajax({
	                url : "api_cmd/property/getDictionaryCache/"+dicKindName,
	                cache : false,
	                async : field?true:false,
	                type : "GET",
	                dataType : 'json',
	                success : function (response){
	                	dic=response;
	                	if(field){
	    					resolveShowFieldAndReturnField(field,dic);
	    					field.items=dic;
	    				}
						cache.put(key,dic);
						var expireDate = new Date();
						expireDate.setTime(expireDate.getTime() + this.cacheLongTime);
						$localStorage[key]={'data':dic,'expire': expireDate.getTime()};
	                }
	            });
				return dic;
			}
		}
	};
	this.getPropertyName = function(key,value){
		var name = "";
		var items = this.getData(key);
		if(items && items.length > 0){
			for (var i = 0; i < items.length; i++) {
				if(items[i].code == value){
					name = items[i].name;
					break;
				}
			}
		}
		return name;
	};
	this._getRegionData=function(key,field){
		var cache=$cacheFactory.get("region");
		if(!cache){
			cache=$cacheFactory("region");
		}
		var region=cache.get(key);
		if(region){
			if(field){
				resolveShowFieldAndReturnField(field,region);
				field.items=region;
			}else{
				return region;
			}
		}else{
			if($localStorage[key]&&$localStorage[key].expire>new Date().getTime()){
				region=$localStorage[key].data;
				cache.put(key,region);
				if(field){
					resolveShowFieldAndReturnField(field,region);
					field.items=region;
				}else{
					return region;
				}
			}else{
				var regionCode=key.substring(6)
				$.ajax({
	                url : "api_cmd/base/baseRegion/getRegionCache/"+regionCode,
	                cache : false,
	                async : field?true:false,
	                type : "GET",
	                dataType : 'json',
	                success : function (response){
	                	region=response;
	                	if(field){
	        				resolveShowFieldAndReturnField(field,region);
	        				field.items=region;
	        			}
						cache.put(key,region);
						var expireDate = new Date();
						expireDate.setTime(expireDate.getTime() + this.cacheLongTime);
						$localStorage[key]={'data':region,'expire': expireDate.getTime()};
	                }
	            });
				return region;
			}
		}
	};
	this._getConfigData=function(key){
		var cache=$cacheFactory.get("config");
		if(!cache){
			cache=$cacheFactory("config");
		}
		var config=cache.get(key);
		if(config){
			return config
			
		}else{
			
				var configKey=key.substring(7)
				
				$.ajax({
	                url : "api/config/queryConfig/"+configKey,
	                cache : false,
	                async : false,
	                type : "GET",
	                dataType : 'json',
	                success : function (response){
	                	config=response.value;
	                	
						cache.put(key,config);
	                }
	            });
				return config;
			
		}
	};
}]);
angular.module('huatek.services').service('base64', function() {
    var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    this.encode = function(input) {
        var output = "",
            chr1, chr2, chr3 = "",
            enc1, enc2, enc3, enc4 = "",
            i = 0;
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                keyStr.charAt(enc1) +
                keyStr.charAt(enc2) +
                keyStr.charAt(enc3) +
                keyStr.charAt(enc4);
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        }
        return output;
    };
    this.decode = function(input) {
        var output = "",
            chr1, chr2, chr3 = "",
            enc1, enc2, enc3, enc4 = "",
            i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = keyStr.indexOf(input.charAt(i++));
            enc2 = keyStr.indexOf(input.charAt(i++));
            enc3 = keyStr.indexOf(input.charAt(i++));
            enc4 = keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
            chr1 = chr2 = chr3 = "";
            enc1 = enc2 = enc3 = enc4 = "";
        }
    };
});
'use strict';
/**
 * [用于处理页面列表的服务类]
 * @param  {[object]} $rootScope   [根作用域]
 * @param  {[object]} cacheService [缓存服务类]
 * @param  {[object]} $timeout     [延时等待处理对象]
 * @author lis 2017-10-22
 */
var ListServiceFunction = function($rootScope, cacheService, $timeout, $compile, $controller, $http, httpService, $ocLazyLoad) {


    /**
     * [初始化listService对象]
     * @param  {[object]} scope     [注入listService的controller的作用域对象]
     * @param  {[object]} http      [angularjs封装的ajax对象]
     * @param  {[object]} rootScope [当前应用的根作用域]
     * @return {[undefined]}           [无]
     * @author lis 2017-10-22
     */
    this.init = function(_scope, http, tableGrid, gridApi, exportUrl) {
        /*给当前scope定义清空查询条件的方法*/
        _scope.resetSearch = function() {
            for (var i = 0; i < _scope.queryPage.queryParamList.length; i++) {
                var key = _scope.menuId + "-" + _scope.queryPage.queryParamList[i].name;
                delete queryValues[key];
                if (_scope.queryPage.queryParamList[i].isReset == undefined || _scope.queryPage.queryParamList[i].isReset) {
                    _scope.queryPage.queryParamList[i].value = null;
                }
                _scope.queryPage.queryParamList[i].queryParam = null;
                if (_scope.queryPage.queryParamList[i].type == 'dateTime') {
                    delete queryValues[key+"_m"];
                    delete queryValues[key+"_x"];
                    _scope.queryPage.queryParamList[i].minValue = null;
                    _scope.queryPage.queryParamList[i].maxValue = null;
                }
                /* if (_scope.queryPage.queryParamList[i].type == 'number') {
                     console.log(_scope.queryPage.queryParamList[i]);
                 }*/
                if (_scope.queryPage.queryParamList[i].componentType == 'tenderMultipleSelect') {
                    _scope.queryPage.queryParamList[i].params = [];
                }
                if (_scope.queryPage.queryParamList[i].componentType == 'selectTreeMutiple') {
                    _scope.queryPage.queryParamList[i].params = [];
                }
            }
            delete queryValues[_scope.menuId + "_page"];
            delete queryValues[_scope.menuId + "_pageSize"];
        };
        /*展示高级查询条件*/
        _scope.showMoreSearch = function() {
            for (var i = 0; i < _scope.queryFieldList.length; i++) {
                _scope.queryFieldList[i].isShowSelect = true;
            }
            _scope.showMoreBtn = false;
            _scope.hideMoreBtn = true;
        };
        /*关闭高级查询条件*/
        _scope.hideMoreSearch = function() {
            for (var i = 0; i < _scope.queryFieldList.length; i++) {
                if (i > 5) {
                    _scope.queryFieldList[i].isShowSelect = false;
                } else {
                    _scope.queryFieldList[i].isShowSelect = true;
                }
            }
            _scope.hideMoreBtn = false;
            _scope.showMoreBtn = true;
        };
        var tableGrid = tableGrid || _scope.tableGrid;
        /*给所有列表加导出和字段过滤功能*/
        if (tableGrid) {
        	if(tableGrid.enableGridMenu != false){
        		tableGrid.enableGridMenu = true;
                tableGrid.exporterMenuPdf = false;
                tableGrid.exporterMenuCsv = false;
                tableGrid.enableFullRowSelection = false;
                tableGrid.gridMenuCustomItems = [
                     /*积分商城 导出所有到excel 不需要
                      {
                        title: "导出所有到excel",
                        action: function($event) {
                            exportAllToExcel(_scope, tableGrid, exportUrl);
                        },
                        icon: "glyphicon glyphicon-circle-arrow-down"
                    },*/
                    {
                        title: "导出当前页到excel",
                        action: function($event) {
                            exportCurrentToExcel(tableGrid);
                        },
                        icon: "glyphicon glyphicon-circle-arrow-down"
                    },
                    {
                        title: "导出选中到excel",
                        action: function($event) {
                            var gridApi = gridApi || _scope.gridApi;
                            exportCurrentToExcel(tableGrid, gridApi);
                        },
                        icon: "glyphicon glyphicon-circle-arrow-down"
                    }
                ]
                if (tableGrid.multiSelect) {
                    tableGrid.enableSelectAll = true;
                } else {
                    tableGrid.enableSelectAll = false;
                }
                tableGrid.rowTemplate = '<div ng-click="grid.appScope.$rowClick(row)" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.uid" class="ui-grid-cell" ng-class="col.colIndex()" ui-grid-cell></div>';
                _scope.$rowClick = function(row, gridApi) {
                    if (row.isSelected) {
                        row.isSelected = false;
                    } else {
                        if (tableGrid.multiSelect) {
                            row.isSelected = true;
                        } else {
                            if (row.grid.api.selection.getSelectedRows().length > 0) {
                                row.grid.api.selection.clearSelectedRows();
                            }
                            row.isSelected = true;
                        }
                    }
                }
        	} 
        }

        /*返回按钮*/
        _scope.back = function() {
            this.jsPanel.close();
        }
        /*设置点击后按钮禁止*/
        _scope.waitForDbCommint = function(btn) {
            /*if (btn.isDisabled != undefined) {
                btn.isDisabled = true;
                $timeout(function() {
                    btn.isDisabled = false;
                }, $rootScope.disabled_wait_time);
            }*/
        };
        if (tableGrid && tableGrid.lookDetailConfig && tableGrid.lookDetailConfig.menuKey) {
            tableGrid.columnDefs.insert(0, new lookDetailBtn());
            _scope.$lookDetail = function(row) {
                var passParams = new Object();
                if (row && tableGrid.lookDetailConfig.paramFieldsArr && tableGrid.lookDetailConfig.paramFieldsArr.length > 0) {
                    for (var i = 0; i < tableGrid.lookDetailConfig.paramFieldsArr.length; i++) {
                        if (row.entity[tableGrid.lookDetailConfig.paramFieldsArr[i]]) {
                            passParams[tableGrid.lookDetailConfig.paramFieldsArr[i]] = row.entity[tableGrid.lookDetailConfig.paramFieldsArr[i]];
                        }
                    }
                }
                lookDetail(_scope, row, new popup(tableGrid.lookDetailConfig.name || '查看', tableGrid.lookDetailConfig.menuKey, passParams));
            }
        }

        _scope.tab = { "panels": [] };
        _scope.deleteDataArr = [];
    };
    /**
     * [下载模版]
     * @param  {[string]} form [模版文件的路径]
     * @return {[undefined]}      [无]
     * @author lis 2017-10-22
     */
    this.downloadTemplate = function(form) {
        var actionUrl = fileServerPath + form;
        window.location.href = actionUrl;
    };

    var exportAllToExcel = this.exportAllToExcel = function(_scope, tableGrid, exportUrl) {
        var exportUrl = exportUrl || _scope.loadURL;
        if (exportUrl) {
            var postQueryPage = copyQueryPage(_scope.queryPage);
            //如果数据条数大于2000条则只能导出2000条
            var totalItems = tableGrid.totalItems;
             console.log(totalItems);
             if(totalItems >2000){
                postQueryPage.pageSize = 2000;                 
             }else{
                postQueryPage.pageSize = totalItems;    
             }
             console.log(postQueryPage.pageSize);
            //postQueryPage.pageSize = tableGrid.totalItems;    
            return $http.post(exportUrl, postQueryPage)
                .success(function(data) {
                	console.log("query for export");
                	console.log(data);
                	if(angular.isArray(data)){
                		exportToExcel(tableGrid, data);
                	}else{
                		exportToExcel(tableGrid, data.content);
                	}
                    
                });
        } else {
            submitTips('警告：未定义导出的地址。', 'warning');
        }

    }
    var exportCurrentToExcel = this.exportCurrentToExcel = function(tableGrid) {
        var data = tableGrid.data;
        exportToExcel(tableGrid, data);
    };
    var exportChoosedToExcel = this.exportChoosedToExcel = function(tableGrid, gridApi) {
        if (gridApi) {
            var data = gridApi.selection.getSelectedRows();
            exportToExcel(tableGrid, data);
        } else {
            submitTips('警告：无法获取选中数据。', 'warning');
        }
    };
    var exportToExcel = this.exportToExcel = function(tableGrid, data) {
        var fileName = "download.xls";
        if (tableGrid.exportExcelName) {
            fileName = tableGrid.exportExcelName;
        }
        console.log("--------------------------------");
        console.log(data);
        if (data && data.length > 0) {
            if (data && data.length > 0) {
                var arr = [];
                angular.forEach(data, function(item) {
                    var exportObj = {}
                    if (tableGrid.columnDefs && tableGrid.columnDefs.length > 0) {
                        for (var i = 0; i < tableGrid.columnDefs.length; i++) {
                            if (tableGrid.columnDefs[i].visible || tableGrid.columnDefs[i].visible == undefined) {
                                if(tableGrid.columnDefs[i].decode){
                                    exportObj[tableGrid.columnDefs[i].name] = cacheService.getPropertyName(tableGrid.columnDefs[i].decode.dataKey,item[tableGrid.columnDefs[i].decode.field]);
                                }else{
                                    if(tableGrid.columnDefs[i].field.indexOf(".") > -1){
                                        var fieldArr = tableGrid.columnDefs[i].field.split('.');
                                        if(fieldArr != null && fieldArr.length >= 2){
                                            var prent = fieldArr[0];
                                            var child = fieldArr[1];
                                            exportObj[tableGrid.columnDefs[i].name] = item[prent][child];
                                        }else{
                                            exportObj[tableGrid.columnDefs[i].name] = item[tableGrid.columnDefs[i].field];
                                        }
                                    }else{
                                        exportObj[tableGrid.columnDefs[i].name] = item[tableGrid.columnDefs[i].field];
                                    }
                                }
                            }
                        }
                    }
                    arr.push(exportObj)
                })
                console.log("---------------------------")
                console.log(arr)
                if (arr.length < 1) {
                    ToasterTool.error('暂无数据，导出失败！');
                } else {
                    alasql.promise('SELECT * INTO XLSX("' + fileName + '",{headers:true}) FROM ?', [arr])
                        .then(function(data) {
                            if (data == 1) {
                                $timeout(function() {
                                    submitTips('成功提示：导出成功！', 'success');
                                })
                            }
                        })
                }
            }
        } else {
            submitTips('警告：无可导出数据。', 'warning');
        }
    };
    /**
     * [导出数据]
     * @param  {[string]} impl  [导出数据的后台实现类请求路径]
     * @param  {[object]} param [导出时的参数]
     * @return {[undefined]}       [无]
     * @author lis 2017-10-22
     */
    this.exportData = function(_scope, impl, param) {
        _scope.actionUrl = exportServerPath + impl;
        for (_scope.num = 0; _scope.num < _scope.queryFieldList.length; _scope.num++) {
            if (_scope.queryFieldList[_scope.num].value != null && _scope.queryFieldList[_scope.num].value != '') {
                _scope.actionUrl += '&' + _scope.queryFieldList[_scope.num].field + '=' + _scope.queryFieldList[_scope.num].value;
            }
        }
        if (param) {
            _scope.actionUrl += param
        }
        /*window.location.href = _scope.actionUrl;*/
    };
    /**
     * [将出入的queryPage对象封装成当前全局变量_scope.queryPage]
     * @param  {[object]} queryPage [查询对象]
     * @author lis 2017-10-22
     */
    this.setQueryPage = function(_scope, queryPage) {
        _scope.queryPage = queryPage;
        if (queryValues[_scope.menuId + "_page"]) {
            _scope.queryPage.page = queryValues[_scope.menuId + "_page"];
        }
        if (queryValues[_scope.menuId + "_pageSize"]) {
            _scope.queryPage.pageSize = queryValues[_scope.menuId + "_pageSize"];
        }
        _scope.queryFieldList = [];
        for (var m, i = 0; i < queryPage.queryParamList.length; i++) {
            if (queryPage.queryParamList[i].isShow) {
                _scope.queryFieldList.push(queryPage.queryParamList[i]);
            }
        }
        _scope.queryPageMap = getMap(queryPage.queryParamList, "field");
        for (var index in _scope.queryFieldList) {
            var field = _scope.queryFieldList[index];
            cacheService.bindFieldData(field);
        }
        for (var i = 0; i < _scope.queryPage.queryParamList.length; i++) {
            if (!_scope.queryPage.queryParamList[i].keepValue) {
                continue;
            }
            var queryValue;
            var key = _scope.menuId + "-" + _scope.queryPage.queryParamList[i].name + i;
            if (_scope.queryPage.queryParamList[i].type == 'dateTime') {
                var minValue = queryValues[key + "_m"];
                var maxValue = queryValues[key + "_x"];
                if (minValue != null && minValue != 'null' &&
                    minValue != '' && minValue != undefined && minValue != 'undefined') {
                    _scope.queryPage.queryParamList[i].minValue = minValue;
                }
                if (maxValue != null && maxValue != 'null' &&
                    maxValue != '' && maxValue != undefined && maxValue != 'undefined') {
                    _scope.queryPage.queryParamList[i].maxValue = maxValue;
                }
            } else if (_scope.queryPage.queryParamList[i].componentType == SEARCH_COMPONENT.TENDERS_SLECT) {
                if ($rootScope.userTenders && $rootScope.userTenders.length > 0) {
                    _scope.queryPage.queryParamList[i].value = $rootScope.userTenders[0].code;
                }
            } else if (_scope.queryPage.queryParamList[i].componentType == SEARCH_COMPONENT.GROUPS_SELECT) {
                if ($rootScope.userCompList && $rootScope.userCompList.length > 0) {
                    _scope.queryPage.queryParamList[i].value = $rootScope.userCompList[0].code;
                }
            } else {
                queryValue = queryValues[key];
                if (queryValue && queryValue != 'undefined' && queryValue != 'null') {
                    if (_scope.queryPage.queryParamList[i].logic == "in") {
                        /*解决页面查询条件复选框值回显问题*/
                        _scope.queryPage.queryParamList[i].params = queryValue.toString().split(",");
                    } else {
                        if (queryValue == 'true' || queryValue == 'false') {
                            _scope.queryPage.queryParamList[i].value = Boolean(queryValue);
                        } else {
                            _scope.queryPage.queryParamList[i].value = queryValue;
                        }
                    }
                }
            }
        } /* 判断queryFieldList的总数量，默认显示前三条 by wing 2016/9/5 */
        for (var i = 0; i < _scope.queryFieldList.length; i++) {
            _scope.queryFieldList[i].isShowSelect;
            if (i > 5) {
                _scope.queryFieldList[i].isShowSelect = false;
                _scope.showMoreBtn = true;
            } else {
                _scope.queryFieldList[i].isShowSelect = true;
                _scope.showMoreBtn = false;
            }
        }
    };
    /**
     * [返回查询对象的map对象]
     * @return {[map]} [包含查询对象的map，key为查询对象的field]
     * @author lis 2017-10-22
     */
    this.getQueryFieldMap = function(_scope) {
        return _scope.queryPageMap;
    };
    /**
     * [页面缓存查询条件]
     * @return {[undefined]} [无]
     * @author lis 2017-10-22
     */
    this.storeQueryParam = function(_scope) {
        /*for (var i = 0; i < _scope.queryPage.queryParamList.length; i++) {
            var values = _scope.queryPage.queryParamList[i].values;
            var value = _scope.queryPage.queryParamList[i].value;
            if (_scope.queryPage.queryParamList[i].logic == 'in') {
                values = _scope.queryPage.queryParamList[i].params
            }
            var key = _scope.menuId + "-" + _scope.queryPage.queryParamList[i].name + i;
            if (values && values.length > 0) {
                queryValues[key] = values;
            } else if (_scope.queryPage.queryParamList[i].type == 'dateTime') {
                queryValues[key + "_m"] = _scope.queryPage.queryParamList[i].minValue;
                queryValues[key + "_x"] = _scope.queryPage.queryParamList[i].maxValue;
            } else {
                queryValues[key] = value;
            }
        }
        queryValues[_scope.menuId + "_page"] = _scope.queryPage.page;
        queryValues[_scope.menuId + "_pageSize"] = _scope.queryPage.pageSize;*/

    }
    /**
     * [将系统按钮和自定义按钮设置到当前scope中]
     * @param  {[array]} _btnArray          [页面定义与菜单映射的按钮对象数组]
     * @param  {[array]} _customButtonArray [页面定义无菜单映射的自定义按钮对象数组]
     * @author lis 2017-10-22
     */
    this.setButtonList = function(_scope, _btnArray, _customButtonArray) {
        if (_btnArray && !_customButtonArray) {
            _scope.btnArrayList = _btnArray;
        } else if (_btnArray && _customButtonArray) {
            _scope.btnArrayList = _btnArray.concat(_customButtonArray);
        } else if (!_btnArray && _customButtonArray) {
            _scope.btnArrayList = _customButtonArray;
        }
    };
    /**
     * [初始化数据字典]
     * @param  {[string]} toUrl               [获取数据字典的后台请求路径]
     * @param  {[function]} initSuccessCallback [回调，请求完成并且响应的数据字典数据处理完毕后被调用]
     * @return {[undefined]}                     [无]
     * @author lis 2017-10-21
     */
    this.initQueryParams = function(_scope, toUrl, initSuccessCallback) {
        _scope.promise = httpService.get(_scope, toUrl).success(function(params) {
            for (var i = 0; i < params.length; i++) {
                var queryParam = _scope.queryPageMap.get(params[i].fieldName);
                if (queryParam) {
                    resolveShowFieldAndReturnField(queryParam, params[i].params);
                    queryParam.items = params[i].params;
                    if (queryParam.defaultValue != undefined && queryParam.defaultValue.length > 0) {
                        if (queryParam.logic && '=' == queryParam.logic) {
                            queryParam.value = queryParam.value || queryParam.defaultValue[0];
                        } else if (queryParam.logic && 'in' == queryParam.logic) {
                            queryParam.params = queryParam.params || queryParam.defaultValue;
                        }

                    }
                }
            }
            if (angular.isFunction(initSuccessCallback)) {
                initSuccessCallback();
            }
        });
    };
    /***
     * 加载api数据.
     */
    this.initQueryItems = function(_scope, queryField, dataUrl) {
        _scope.promise = httpService.get(_scope, dataUrl).success(function(params) {
            resolveShowFieldAndReturnField(queryField, params);
            queryField.items = params;
        });
    }
    /**
     * [新增数据方法]
     * @param  {[string]} menuKey [新增功能的菜单唯一标示]
     * @author lis 2017-10-21
     */
    this.addData = function(_scope, popup) {
        var source = actionMap.get(popup.menuKey);
        if (!source) {
            console.log("该menuKey对应的菜单不存在！");
        }
        var isModalOpen = false;
        /*判断是否已经打开该modal页面*/
        if (_scope.tab && _scope.tab.panels.length > 0) {
            var panels = _scope.tab.panels;
            for (var i = 0; i < panels.length; i++) {
                if (panels[i].option.id == source.url.replace(/\//g, '-')) {
                    panels[i].normalize();
                    isModalOpen = true;
                    break;
                }
            }
        }
        if (!isModalOpen) {
            var jsPanelKey = uuid(36);
            if ($('#' + jsPanelKey)) {
                jsPanelKey = uuid(36);
            }
            if (controllerMap.get(source.controller)) {
                $ocLazyLoad.load(controllerMap.get(source.controller) + "?version=" + huatek.version).then(function() {
                    controllerProvider.register(jsPanelKey, function($scope, $controller) {
                        /*如果有额外参数，则传递过去*/
                        if (popup.passParams) {
                            $scope.passParams = popup.passParams;
                        }
                        $controller(source.controller, { $scope: $scope });
                    });
                    var modal = "<div id='" + jsPanelKey + "' ng-controller='" + jsPanelKey + "'>";
                    $http.get((source.view ? source.view : TEMPLATE_NOT_FOUND_PATH) + "?version=" + huatek.version).success(function(res) {
                        modal += res
                        modal += "</div>";
                        var jsPanel = $.jsPanel({
                            id: urlToString(source.url),
                            selector: '.content',
                            headerTitle: popup.title,
                            contentSize: { width: source.width || popup.width || 800, height: source.height || popup.height || 500 },
                            theme: 'dark',
                            position: 'center',
                            content: $compile(modal)(_scope),
                            paneltype: popup.paneltype ? popup.paneltype : false,
                            draggable: {
                                containment: "parent"
                            },
                            onclosed: function() {
                                if (_scope.tab && _scope.tab.panels) {
                                    var panels = _scope.tab.panels;
                                    if (panels.length > 0) {
                                        var arrIdex = 0;
                                        for (var i = 0; i < panels.length; i++) {
                                            if (panels[i] === _scope['jsPanel']) {
                                                arrIdex = i;
                                                break;
                                            }
                                        }
                                        panels.splice(arrIdex, 1);
                                    }
                                }
                                if (popup.onclosedFun) {
                                    popup.onclosedFun();
                                } else if (_scope.load) {
                                    _scope.load();
                                }
                            }
                        });
                        _scope.tab.panels.push(jsPanel);
                        var controllerScope = $('div[ng-controller="' + jsPanelKey + '"]').scope();
                        controllerScope.jsPanel = jsPanel;
                        /*将编辑数据的ID传递到controller的scope中*/
                        /*如果有额外参数，则传递过去*/
                        if (popup.passParams) {
                            controllerScope.passParams = popup.passParams;
                        }
                    }).error(function() {
                        console.log("加载模板失败,模版路径为：" + source.view);
                    });
                })
            } else {
                console.log("该controller未配置对应的文件路径！")
            }
        } else {
            console.log("该窗口未关闭");
        }
    };
    /**
     * [编辑数据]
     * @param  {[object]} gridApi [列表事件对象]
     * @param  {[string]} menuKey   [编辑功能的菜单唯一标示]
     * @param  {object} 选中行对象
     * @return {[null]}         [null]
     * @author lis 2017-10-21
     */
    this.editData = function(_scope, gridApi, popup, selectRow) {
        var selectRow = selectRow;
        if (!selectRow) {
            if (!selectOne(gridApi)) {
                return;
            } else {
                selectRow = gridApi.selection.getSelectedRows()[0];
            }
        }

        /*获取当前选择的数据.*/
        var source = actionMap.get(popup.menuKey);
        if (!source) {
            console.log("该menuKey对应的菜单不存在！");
            return;
        }
        var isModalOpen = false;
        /*判断是否已经打开该modal页面*/
        if (_scope.tab.panels.length > 0) {
            var panels = _scope.tab.panels;
            for (var i = 0; i < panels.length; i++) {
                if (panels[i].option.id == source.url.replace(/\//g, '-')) {
                    panels[i].normalize();
                    isModalOpen = true;
                    break;
                }
            }
        }
        if (!isModalOpen) {
            var jsPanelKey = uuid(36);
            if ($('#' + jsPanelKey)) {
                jsPanelKey = uuid(36);
            }
            if (controllerMap.get(source.controller)) {
                $ocLazyLoad.load(controllerMap.get(source.controller) + "?version=" + huatek.version).then(function() {
                    controllerProvider.register(jsPanelKey, function($scope, $controller) {
                        /*如果有额外参数，则传递过去*/
                        if (popup.passParams) {
                            $scope.passParams = popup.passParams;
                        }
                        $scope.editId = selectRow.id;
                        $controller(source.controller, { $scope: $scope });
                    });
                    var modal = "<div id='" + jsPanelKey + "' ng-controller='" + jsPanelKey + "'>";
                    $http.get((source.view ? source.view : TEMPLATE_NOT_FOUND_PATH) + "?version=" + huatek.version).success(function(res) {
                        modal += res
                        modal += "</div>";
                        var jsPanel = $.jsPanel({
                            id: urlToString(source.url),
                            selector: '.content',
                            headerTitle: popup.title,
                            contentSize: { width: source.width || popup.width || 800, height: source.height || popup.height || 500 },
                            theme: 'dark',
                            position: 'center',
                            content: $compile(modal)(_scope),
                            paneltype: popup.paneltype ? popup.paneltype : false,
                            draggable: {
                                containment: "parent"
                            },
                            onclosed: function() {
                                if (_scope.tab && _scope.tab.panels) {
                                    var panels = _scope.tab.panels;
                                    if (panels.length > 0) {
                                        var arrIdex = 0;
                                        for (var i = 0; i < panels.length; i++) {
                                            if (panels[i] === _scope['jsPanel']) {
                                                arrIdex = i;
                                                break;
                                            }
                                        }
                                        panels.splice(arrIdex, 1);
                                    }
                                }
                                if (popup.onclosedFun) {
                                    popup.onclosedFun();
                                } else if (_scope.load) {
                                    _scope.load();
                                }
                            }
                        });
                        _scope.tab.panels.push(jsPanel);
                        var controllerScope = $('div[ng-controller="' + jsPanelKey + '"]').scope();
                        controllerScope.jsPanel = jsPanel;
                        /*将编辑数据的ID传递到controller的scope中*/
                        /*如果有额外参数，则传递过去*/
                        if (popup.passParams) {
                            controllerScope.passParams = popup.passParams;
                        }
                    }).error(function() {
                        console.log("加载模板失败,模版路径为：" + source.view);
                    });
                })
            } else {
                console.log("该controller未配置对应的文件路径！")
            }
        } else {
            console.log("该窗口未关闭");
        }
    };

    /**
     * [查看数据]
     * @param  {[object]} gridApi [列表事件对象]
     * @param  {[string]} menuKey   [编辑功能的菜单唯一标示]
     * @return {[null]}         [null]
     * @author lis 2017-10-21
     */
    var lookDetail = this.lookDetail = function(_scope, row, popup) {
        /*获取当前选择的数据.*/
        var source = actionMap.get(popup.menuKey);
        if (!source) {
            console.log("该menuKey对应的菜单不存在！");
            return;
        }
        var isModalOpen = false;
        /*判断是否已经打开该modal页面*/
        if (_scope.tab.panels.length > 0) {
            var panels = _scope.tab.panels;
            for (var i = 0; i < panels.length; i++) {
                if (panels[i].option.id == source.url.replace(/\//g, '-')) {
                    panels[i].normalize();
                    isModalOpen = true;
                    break;
                }
            }
        }
        if (!isModalOpen) {
            var jsPanelKey = uuid(36);
            if ($('#' + jsPanelKey)) {
                jsPanelKey = uuid(36);
            }
            if (controllerMap.get(source.controller)) {
                $ocLazyLoad.load(controllerMap.get(source.controller) + "?version=" + huatek.version).then(function() {
                    controllerProvider.register(jsPanelKey, function($scope, $controller) {
                        /*如果有额外参数，则传递过去*/
                        if (popup.passParams) {
                            $scope.passParams = popup.passParams;
                        }
                        $scope.editId = row.entity.id;
                        $scope.lookModel = true;
                        $controller(source.controller, { $scope: $scope });
                    });
                    var modal = "<div id='" + jsPanelKey + "' ng-controller='" + jsPanelKey + "'>";
                    $http.get((source.view ? source.view : TEMPLATE_NOT_FOUND_PATH) + "?version=" + huatek.version).success(function(res) {
                        modal += res
                        modal += "</div>";
                        var jsPanel = $.jsPanel({
                            id: urlToString(source.url),
                            selector: '.content',
                            headerTitle: popup.title,
                            contentSize: { width: source.width || popup.width || 800, height: source.height || popup.height || 500 },
                            theme: 'dark',
                            position: 'center',
                            content: $compile(modal)(_scope),
                            paneltype: popup.paneltype ? popup.paneltype : false,
                            draggable: {
                                containment: "parent"
                            },
                            onclosed: function() {
                                if (_scope.tab && _scope.tab.panels) {
                                    var panels = _scope.tab.panels;
                                    if (panels.length > 0) {
                                        var arrIdex = 0;
                                        for (var i = 0; i < panels.length; i++) {
                                            if (panels[i] === _scope['jsPanel']) {
                                                arrIdex = i;
                                                break;
                                            }
                                        }
                                        panels.splice(arrIdex, 1);
                                    }
                                }
                                if (popup.onclosedFun) {
                                    popup.onclosedFun();
                                } else if (_scope.load) {
                                    _scope.load();
                                }
                            }
                        });
                        _scope.tab.panels.push(jsPanel);
                        var controllerScope = $('div[ng-controller="' + jsPanelKey + '"]').scope();
                        controllerScope.jsPanel = jsPanel;
                        /*将编辑数据的ID传递到controller的scope中*/
                        /*如果有额外参数，则传递过去*/
                        if (popup.passParams) {
                            controllerScope.passParams = popup.passParams;
                        }
                    }).error(function() {
                        console.log("加载模板失败,模版路径为：" + source.view);
                    });
                })
            } else {
                console.log("该controller未配置对应的文件路径！")
            }
        } else {
            console.log("该窗口未关闭");
        }
    };
    /**
     * 弹出窗口
     * @param  {[type]} _scope 当前controller作用域
     * @param  {[type]} popup  弹框对象
     * @return {[type]}        [description]
     * @author lis 2017-10-29
     */
    this.popPanel = function(_scope, popup) {
        var source = popup.menuKey ? actionMap.get(popup.menuKey) : null;
        var jsPanelKey = uuid(36);
        if ($('#' + jsPanelKey)) {
            jsPanelKey = uuid(36);
        }
        var controller = source ? source.controller : popup.controller;
        if (controllerMap.get(controller)) {
            $ocLazyLoad.load(controllerMap.get(controller) + "?version=" + huatek.version).then(function() {
                controllerProvider.register(jsPanelKey, function($scope, $controller) {
                    if (popup.passParams) {
                        $scope.passParams = popup.passParams;
                    }
                    if (controller) {
                        $controller(controller, { $scope: $scope });
                    }

                });
                /* var jsPanelKey = source.controller;*/
                var modal = "<div id='" + jsPanelKey + "' ng-controller='" + jsPanelKey + "'>";
                $http.get((source ? source.view : popup.templateView) + "?version=" + huatek.version).success(function(res) {
                    if (!source) {
                        source = {};
                    }
                    modal += res
                    modal += "</div>";
                    var jsPanel = $.jsPanel({
                        id: jsPanelKey,
                        selector: '.content',
                        headerTitle: popup.title,
                        contentSize: { width: source.width || popup.width || 800, height: source.height || popup.height || 500 },
                        theme: 'dark',
                        position: 'center',
                        content: $compile(modal)(_scope),
                        paneltype: popup.paneltype ? popup.paneltype : false,
                        draggable: {
                            containment: "parent"
                        },
                        onclosed: function() {
                            if (_scope.tab && _scope.tab.panels) {
                                var panels = _scope.tab.panels;
                                if (panels.length > 0) {
                                    var arrIdex = 0;
                                    for (var i = 0; i < panels.length; i++) {
                                        if (panels[i] === _scope['jsPanel']) {
                                            arrIdex = i;
                                            break;
                                        }
                                    }
                                    panels.splice(arrIdex, 1);
                                }
                            }
                            if (popup.onclosedFun) {
                                popup.onclosedFun();
                            } else if (_scope.load) {
                                _scope.load();
                            }
                        }
                    });
                    if (_scope.tab && _scope.tab.panels) {
                        _scope.tab.panels.push(jsPanel);
                    }

                    var controllerScope = $('div[ng-controller="' + jsPanelKey + '"]').scope();
                    controllerScope.jsPanel = jsPanel;
                }).error(function() {
                    console.log("加载模板失败,模版路径为：" + source.view);
                });
            })
        } else {
            controllerProvider.register(jsPanelKey, function($scope, $controller) {
                if (popup.passParams) {
                    $scope.passParams = popup.passParams;
                }
                if (controller) {
                    $controller(controller, { $scope: $scope });
                }

            });
            /* var jsPanelKey = source.controller;*/
            var modal = "<div id='" + jsPanelKey + "' ng-controller='" + jsPanelKey + "'>";
            $http.get(source ? source.view : popup.templateView).success(function(res) {
                if (!source) {
                    source = {};
                }
                modal += res
                modal += "</div>";
                var jsPanel = $.jsPanel({
                    id: jsPanelKey,
                    selector: '.content',
                    headerTitle: popup.title,
                    contentSize: { width: source.width || popup.width || 800, height: source.height || popup.height || 500 },
                    theme: 'dark',
                    position: 'center',
                    content: $compile(modal)(_scope),
                    paneltype: popup.paneltype ? popup.paneltype : false,
                    draggable: {
                        containment: "parent"
                    },
                    onclosed: function() {
                        if (_scope.tab && _scope.tab.panels) {
                            var panels = _scope.tab.panels;
                            if (panels.length > 0) {
                                var arrIdex = 0;
                                for (var i = 0; i < panels.length; i++) {
                                    if (panels[i] === _scope['jsPanel']) {
                                        arrIdex = i;
                                        break;
                                    }
                                }
                                panels.splice(arrIdex, 1);
                            }
                        }
                        if (popup.onclosedFun) {
                            popup.onclosedFun();
                        } else if (_scope.load) {
                            _scope.load();
                        }
                    }
                });
                if (_scope.tab && _scope.tab.panels) {
                    _scope.tab.panels.push(jsPanel);
                }

                var controllerScope = $('div[ng-controller="' + jsPanelKey + '"]').scope();
                controllerScope.jsPanel = jsPanel;
            }).error(function() {
                console.log("加载模板失败,模版路径为：" + source.view);
            });
        }
    }
    this.showProcessProgress = function(_scope, fieldName, canSetPerson) {
        var selectData = this.returnSectData(_scope.gridApi);
        if (selectData.length > 1) {
            submitTips('警告：不能选择多条数据用于编辑。', 'warning');
            return;
        }
        if (selectData.length == 0) {
            submitTips('请至少勾选一条数据！', 'warning');
            return;
        }
        if (!fieldName) {
            fieldName = "flowId";
        }

        if (!selectData[0][fieldName]) {
            submitTips('该记录还没有启动流程。', 'warning');
            return;
        }
        if (canSetPerson == undefined) {
            canSetPerson = true;
        }
        var pop = {
            title: "流程进度",
            passParams: {
                canSetPerson: canSetPerson,
                processInstanceId: selectData[0][fieldName],
            },
            controller: 'processProgressController',
            templateView: "static/business/workflow/process/templates/processProgress.html",

        };
        this.popPanel(_scope, pop)
    };

    this.showProcessProgressInColumn = function(_scope, fieldName, row, canSetPerson) {
        if (!fieldName) {
            fieldName = "flowId";
        }
        if (!row[fieldName]) {
            submitTips('该记录还没有启动流程。', 'warning');
            return;
        }
        if (canSetPerson == undefined) {
            canSetPerson = true;
        }
        var pop = {
            title: "流程进度",
            passParams: {
                canSetPerson: canSetPerson,
                processInstanceId: row[fieldName],
            },
            controller: 'processProgressController',
            templateView: "static/business/workflow/process/templates/processProgress.html",
        };
        this.popPanel(_scope, pop)
    };
    /**
     * [验证列表选中数据是否为一条]
     * @param  {[object]} gridApi [列表事件对象]
     * @return {[boolean]}         [如果选择一条则返回true，否则都为false]
     * @author lis 2017-10-22
     */
    var selectOne = this.selectOne = function(gridApi) {
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length == 0) {
            submitTips('警告：请选择一条数据进行操作。', 'warning');
            return false;
        }
        if (selectData.length > 1) {
            submitTips('警告：只能选择一条数据进行操作。', 'warning');
            return false;
        }
        return true;

    };
    /**
     * [验证列表选中数据是否为一条]
     * @param  {[object]} gridApi [列表事件对象]
     * @return {[boolean]}         [如果选择一条则返回true，否则都为false]
     * @author lis 2017-10-22
     */
    var selectMany = this.selectMany = function(gridApi) {
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length == 0) {
            submitTips('警告：请选择至少选择一条数据进行操作。', 'warning');
            return false;
        }
        return true;

    };
    /**
     * treeGrid新增同级节点
     * @param  {[type]} treeGrid  列表树对象
     * @param  {[type]} gridApi   列表树api对象
     * @author lis 2017-10-29
     */
    this.addSameLevelTreeNode = function(treeGrid, gridApi) {
        if (this.selectOne(gridApi)) {
            var checkedCategory = gridApi.selection.getSelectedRows()[0];
            var index = -1;
            var newNode = {
                "isEdited": true,
                "$$treeLevel": checkedCategory.$$treeLevel,
                "groupLevel": checkedCategory.groupLevel,
                "parentId": checkedCategory.parentId
            };
            for (var i = checkedCategory.orderNumber; i < treeGrid.data.length; i++) {
                if (treeGrid.data[i].$$treeLevel <= newNode.$$treeLevel) {
                    index = i;
                    break;
                }
            }
            newNode.orderNumber = index == -1 ? treeGrid.data.length + 1 : index + 1;
            if (index == -1) {
                treeGrid.data.push(newNode);
            } else {
                treeGrid.data.insert(index, newNode);
                for (var i = index - 1; i < treeGrid.data.length; i++) {
                    treeGrid.data[i].orderNumber = i + 1;
                    treeGrid.data[i].isEdited = true;
                }
            }
        }
    };
    /**
     * treeGrid新增子节点
     * @param  {[type]} treeGrid  列表树对象
     * @param  {[type]} gridApi   列表树api对象
     * @author lis 2017-10-29
     */
    this.addChildTreeNode = function(treeGrid, gridApi) {
        if (this.selectOne(gridApi)) {
            var checkedCategory = gridApi.selection.getSelectedRows()[0];
            var index = -1;
            var newNode = {
                "isEdited": true,
                "$$treeLevel": checkedCategory.$$treeLevel + 1,
                "groupLevel": checkedCategory.groupLevel + 1,
                "parentId": checkedCategory.id
            };
            for (var i = checkedCategory.orderNumber; i < treeGrid.data.length; i++) {
                if (treeGrid.data[i].$$treeLevel <= checkedCategory.$$treeLevel) {
                    index = i;
                    break;
                }
            }
            newNode.orderNumber = index == -1 ? treeGrid.data.length + 1 : index + 1;
            if (index == -1) {
                treeGrid.data.push(newNode);
            } else {
                treeGrid.data.insert(index, newNode);
                for (var i = index - 1; i < treeGrid.data.length; i++) {
                    treeGrid.data[i].orderNumber = i + 1;
                    treeGrid.data[i].isEdited = true;
                }
            }
        }
    };
    /**
     * 删除树节点
     * @param  {[type]} treeGrid  列表树对象
     * @param  {[type]} gridApi   列表树api对象
     * @return {[type]}          [description]
     * @author lis 2017-10-29
     */
    this.deleteTreeNode = function(treeGrid, gridApi) {
        if (this.selectOne(gridApi)) {
            var checkedCategory = gridApi.selection.getSelectedRows()[0];
            for (var i = 0; i < treeGrid.data.length; i++) {
                if (treeGrid.data[i].$$hashKey == checkedCategory.$$hashKey) {
                    if (treeGrid.data.length > 1 && i < treeGrid.data.length - 1) {
                        if (treeGrid.data[i].$$treeLevel < treeGrid.data[i + 1].$$treeLevel) {
                            submitTips('警告：当前节点有子节点，不能删除。', 'warning');
                            return;
                        } else {
                            treeGrid.data.splice(i, 1);
                        }
                    } else {
                        treeGrid.data.splice(i, 1);
                    }
                    break;
                }
            }
            for (var k = checkedCategory.orderNumber - 1; k < treeGrid.data.length; k++) {
                treeGrid.data[k].isEdited = true;
                treeGrid.data[k].orderNumber = k + 1;
            }
        }
    }
    /**
     * treeGrid节点选中方法，父节点被选中时所有的子节点都将被选中，父节点取消选中时所有的子节点都将被取消选中
     * @param  {[type]} row      当前选中行对象
     * @param  {[type]} treeGrid 列表树对象
     * @param  {[type]} gridApi  列表树api对象
     * @return {[type]}          [description]
     * @author lis 2017-10-29
     */
    this.selectGridTreeNode = function(row, treeGrid, gridApi) {
        var seletedOrNot = row.isSelected == true ? "seleted" : "unseleted";
        selectNode(row, gridApi, seletedOrNot);
    };
    /**
     * 递归选中子节点方法
     * @param  {[type]} row          当前选中行对象
     * @param  {[type]} gridApi      列表树api对象
     * @param  {[type]} seletedOrNot 选中或取消选中
     * @return {[type]}              [description]
     * @author lis 2017-10-29
     */
    var selectNode = function(row, gridApi, seletedOrNot) {
        var childrenNodes = gridApi.treeBase.getRowChildren(row);
        if (childrenNodes && childrenNodes.length > 0) {
            for (var i = 0; i < childrenNodes.length; i++) {
                if (seletedOrNot === "seleted") {
                    gridApi.selection.selectRow(childrenNodes[i].entity);
                } else {
                    gridApi.selection.unSelectRow(childrenNodes[i].entity);
                }
                selectNode(childrenNodes[i], gridApi, seletedOrNot);
            }
        }
    }
    /**
     * 获取treeGrid被选中的叶子结点
     * @param  {[type]} gridApi 列表树api对象
     * @return {[type]}         叶子节点对象数组
     * @author lis 2017-10-29
     */
    this.onlyGetSeletedLeafNodes = function(gridApi) {
        var leafNodes = [];
        var seletedRows = gridApi.selection.getSelectedGridRows();
        if (seletedRows && seletedRows.length > 0) {
            for (var i = 0; i < seletedRows.length; i++) {
                if (seletedRows[i].treeNode.children.length == 0) {
                    leafNodes.push(seletedRows[i].entity);
                }
            }
        }
        return leafNodes;
    }
    /**
     * 编辑表格的时候给数据的isEdited的属性设置为true
     * @param  {[type]} rowEntity 行数据对象
     * @return {[type]}           [description]
     * @author lis 2017-10-30
     */
    this.editCell = function(rowEntity) {
        rowEntity.isEdited = true;
    }
    /**
     * treeGrid列模版
     * @param  {[type]} field 列字段名称
     * @return {[type]}         [description]
     * @author lis 2017-10-29
     */
    this.getTreeCellTemplate = function(field) {
        return "<div style='text-align:left;'>" +
            "<span ng-if='row.entity.$$treeLevel==1'>&nbsp;&nbsp;&nbsp;</span>" +
            "<span ng-if='row.entity.$$treeLevel==2'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>" +
            "<span ng-if='row.entity.$$treeLevel==3'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>" +
            "<span ng-if='row.entity.$$treeLevel==4'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>" +
            "{{row.entity." + field + "}}" +
            "</div>";
    }
    /**
     * 树状节点的上移和下移
     * @param  {[type]} treeGrid  列表对象
     * @param  {[type]} gridApi   列表api对象
     * @param  {[type]} moveModel 上移或者下移标示
     * @return {[type]}           [description]
     * @author lis 2017-10-31
     */
    this.moveTreeNode = function(treeGrid, gridApi, moveModel) {
        if (this.selectOne(gridApi)) {
            var gridRow = gridApi.selection.getSelectedRows()[0];
            var rowIndex = gridRow.orderNumber - 1,
                beforeRowIndex = -1,
                afterRowBeginIndex = -1,
                afterRowEndIndex = -1;
            var beginOrder = 0;
            for (var i = rowIndex - 1; i >= 0; i--) {
                if (treeGrid.data[i].$$treeLevel == gridRow.$$treeLevel) {
                    beforeRowIndex = i;
                    break;
                }
                if (treeGrid.data[i].$$treeLevel < gridRow.$$treeLevel) {
                    break;
                }
            }
            for (var j = rowIndex + 1; j < treeGrid.data.length; j++) {
                if (treeGrid.data[j].$$treeLevel == gridRow.$$treeLevel) {
                    if (afterRowBeginIndex == -1) {
                        afterRowBeginIndex = j;
                        continue;
                    }
                }
                if (treeGrid.data[j].$$treeLevel <= gridRow.$$treeLevel) {
                    if (afterRowBeginIndex != -1) {
                        afterRowEndIndex = j;
                    } else {
                        afterRowBeginIndex = j;
                        afterRowEndIndex = j;
                    }
                    break;
                }
            }
            if (beforeRowIndex == -1 && moveModel == TREE_NODE_UP) {
                submitTips('警告：当前节点不能上移。', 'warning');
                return;
            } else if (afterRowBeginIndex == -1 && moveModel == TREE_NODE_DOWN) {
                submitTips('警告：当前节点不能下移。', 'warning');
                return;
            }

            if (moveModel == TREE_NODE_UP) {
                var firstArr = treeGrid.data.slice(0, beforeRowIndex),
                    secondArr = treeGrid.data.slice(beforeRowIndex, rowIndex),
                    thirdArr = afterRowBeginIndex == -1 ? treeGrid.data.slice(rowIndex) : treeGrid.data.slice(rowIndex, afterRowBeginIndex),
                    fourthArr = afterRowBeginIndex == -1 ? [] : treeGrid.data.slice(afterRowBeginIndex);
                beginOrder = beforeRowIndex + 1;
                var chageOrderArr = thirdArr.concat(secondArr);
                for (var k = 0; k < chageOrderArr.length; k++) {
                    chageOrderArr[k].orderNumber = beginOrder + k;
                    chageOrderArr[k].isEdited = true;
                }
                treeGrid.data = firstArr.concat(chageOrderArr, fourthArr);
            } else {
                var firstArr = treeGrid.data.slice(0, rowIndex),
                    secondArr = treeGrid.data.slice(rowIndex, afterRowBeginIndex),
                    thirdArr = afterRowEndIndex == -1 ? treeGrid.data.slice(afterRowBeginIndex) : treeGrid.data.slice(afterRowBeginIndex, afterRowEndIndex),
                    fourthArr = afterRowEndIndex == -1 ? [] : treeGrid.data.slice(afterRowEndIndex);
                beginOrder = rowIndex + 1;
                var chageOrderArr = thirdArr.concat(secondArr);
                for (var k = 0; k < chageOrderArr.length; k++) {
                    chageOrderArr[k].orderNumber = beginOrder + k;
                    chageOrderArr[k].isEdited = true;
                }
                treeGrid.data = firstArr.concat(chageOrderArr, fourthArr);
            }
        }
    }
    /**
     * 返回所有编辑过的行
     * @param  {[type]} gridTable [description]
     * @return {[type]}           [description]
     * @author lis 2017-10-31
     */
    this.getAllEditRows = function(gridTable) {
        if (gridTable.data.length > 0) {
            var editedArr = [];
            for (var i = 0; i < gridTable.data.length; i++) {
                if (gridTable.data[i].isEdited == true) {
                    editedArr.push(gridTable.data[i]);
                }
            }
            return editedArr;
        }
    }
    /**
     * [数据删除方法]
     * @param  {[object]} gridTable [页面列表对象]
     * @param  {[object]} gridApi   [列表对象事件监听对象]
     * @param  {[string]} toUrl     [删除数据的后台请求路径]
     * @return {[undefined]}           [无]
     * @author lis 2017-10-21
     */
    this.deleteData = function(_scope, gridTable, gridApi, toUrl) {
        if (gridApi.selection.getSelectedRows().length < 1) {
            /*          bootbox.alert("请至少勾选一条数据！");*/
            submitTips('请至少勾选一条数据！', 'warning');
            return false;
        }
        bootbox.confirm('确定要删除所选的数据吗?', function(result) {
            if (result) {
                angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                    if (data.id == null || data.id == undefined) {
                        return;
                    }
                    httpService.delete(_scope, toUrl + "/" + data.id).success(function() {
                        var postQueryPage = copyQueryPage(_scope.queryPage);
                        if (_scope.notNeedQueryPage) {
                            postQueryPage.orderBy = '';
                            postQueryPage.queryParamList = [];
                        }
                        /*_scope.promise = $http.post(_scope.loadURL, postQueryPage).success(function(response) {
                            if (response.totalRows == undefined || response.totalRows == 0) {
                                gridTable.data = [];
                            } else {
                                gridTable.data = response.content;
                            }
                            gridTable.totalItems = response.totalRows;
                        });*/
                        _scope.load();
                    });
                });
            }
        });
    };

    this.DeleteRows = function(_scope, gridTable, gridApi, toUrl) {
        if (gridApi.selection.getSelectedRows().length < 1) {
            submitTips('请至少勾选一条数据！', 'warning');
            return false;
        }
        bootbox.confirm('确定要删除所选的数据吗?', function(result) {
            if (result) {
                if (!toUrl) {
                    angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                        if (!data.isNewRow) {
                            _scope.deleteDataArr.push(data);
                        }
                        gridTable.data.splice(gridTable.data.lastIndexOf(data), 1);
                        _scope.isDataChanged = true;
                    });
                } else {
                    angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                        /*当前行移除*/
                        gridTable.data.splice(gridTable.data.lastIndexOf(data), 1);
                        if (data.id != null && data.id != '') {
                            httpService.delete(toUrl + "/" + data.id).success(function() {});
                        }
                    });
                }
                _scope.$apply();
            }
        });
    }


    /**
     * 保存表格数据(只提交新增、修改和删除的数据)
     * 注意：需要在controller中定义单元格编辑后的动作，将行数据中的isEdited设置为true, 否则会获取不到进行过编辑的数据
     * 可参考如下代码： $scope.tableGrid.onRegisterApi = function(gridApi){
     * $scope.gridApi = gridApi;
     * gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef,
     * newValue, oldValue){ if(!rowEntity.isNewRow) { rowEntity.isEdited = true; }
     * });
     * 
     */
    this.saveRows = function(_scope, toUrl, gridTable, forceUrl, saveSuccessCallBack) {
        var data = gridTable.data;
        var newDataArr = [],
            editDataArr = [],
            postData = [];
        angular.forEach(data, function(row, index) {
            if (row.isNewRow) {
                newDataArr.push(row);
            } else if (row.isEdited) {
                editDataArr.push(row);
            }
        });
        postData.push(newDataArr);
        /*新增行数据*/
        postData.push(editDataArr);
        /*修改的行数据*/
        postData.push(_scope.deleteDataArr);
        /*删除的行数据*/
        _scope.promise = $http.post(toUrl, postData).success(function(data) {
            if (angular.isFunction(saveSuccessCallBack)) {
                saveSuccessCallBack();
            }
            /** 增加检查提示向导框 start 20160119 add by caojun1@hisense.com */
            if (data.needConfirm && !data.checkResult) {
                bootbox.confirm("" + data.mesg, function(result) {
                    if (result) {
                        $http.post(forceUrl, postData).success(function(data) {

                        });
                    }
                });
            }
            /** 增加检查提示向导框 end */
            _scope.deleteDataArr = [];
            /*成功后清除数据新增和编辑标记，初始化删除记录数组*/
            angular.forEach(newDataArr, function(rowData) {
                rowData.isNewRow = false;
            });
            angular.forEach(editDataArr, function(rowData) {
                rowData.isEdited = false;
            });
            _scope.deleteDataArr = [];
            _scope.isDataChanged = false;
            _scope.$eval(_scope.functions);
        });
    };
    /**
     * 返回选中的数据行对象数组
     * @param  {[object]} gridApi [列表事件对象]
     * @return {[array]}         [被选中数据行对象数组]
     * @author lis 2017-10-21
     */
    this.returnSectData = function(gridApi) {
        return gridApi.selection.getSelectedRows();
    }
    /**
     * 根据给定url加载列表数据
     * @param  {[string]} url                 [请求地址]
     * @param  {[object]} gridTable           [页面列表对象，请求到的响应数据将会赋值给它]
     * @param  {[boolean]} notNeedQueryPage    [是否需要查询条件表示，为true表示不需要查询条件]
     * @param  {[function]} loadSuccessCallBack [回调，数据将会在成功返回并赋值给gridTable后执行]
     * @return {[undefined]}           [无]
     * @author lis 2017-10-21
     */
    this.loadData = function(_scope, url, gridTable, notNeedQueryPage, beforInitCallBack, loadSuccessCallBack) {
        _scope.loadURL = url;
        _scope.notNeedQueryPage = notNeedQueryPage;
        var postQueryPage = copyQueryPage(_scope.queryPage);
        if (notNeedQueryPage) {
            postQueryPage.orderBy = '';
            postQueryPage.queryParamList = [];
        }
        for (var i = 0; i < postQueryPage.queryParamList.length; i++) {
            var param = postQueryPage.queryParamList[i];
            if (param.value != undefined && param.value != null && (param.logic == 'like' || param.logic == 'alllike')) {
                if (param.value.indexOf("%") > -1) {
                    submitTips('警告!查询条件不能包含特殊字符', 'warning');
                    return;
                }
            }
        }
        /*var pattern = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]")*/
        /*        for (var i = 0; i < postQueryPage.queryParamList.length; i++) {
                    var param = postQueryPage.queryParamList[i];
                    if (param.value != undefined && param.value != null && (param.logic == 'like' || param.logic == 'alllike')) {
                        if (param.value.indexOf("%") > -1) {
                            submitTips('警告!查询条件不能包含特殊字符', 'warning');
                            return;
                        }
                    }
                }*/
        this.storeQueryParam(_scope);
        var _self = this;
        _scope.promise = httpService.post(_scope, url, postQueryPage)
            .success(function(response) {

                if (angular.isFunction(beforInitCallBack)) {
                    beforInitCallBack(response);
                }
                if (response.totalRows == undefined || response.totalRows == 0) {
                    gridTable.data = [];
                } else {
                    var data = response.content;
                    _self.decodeTable(_scope, data, gridTable);
                    gridTable.data = response.content;
                }
                gridTable.totalItems = response.totalRows;
                if (response.page) {
                    gridTable.paginationCurrentPage = response.page;
                } else {
                    gridTable.paginationCurrentPage = 1;
                }

                if (angular.isFunction(loadSuccessCallBack)) {
                    loadSuccessCallBack(response);
                }

            });
    };
    /**
     * 如果当前列表只选择了一条数据，则返回这表数据对象
     * @param  {[object]} gridApi [列表事件对象]
     * @return {[object]}         [被选中的唯一一条数据列表对象]
     * @author lis 2017-10-21
     */
    this.getSelectData = function(gridApi) {
        var selectData = gridApi.selection.getSelectedRows();
        if (selectData.length > 1) {
            submitTips('警告：不能选择多条数据。', 'warning');
            return;
        }
        if (selectData.length == 0) {
            submitTips('请在列表中选择要操作的数据。', 'warning');
            return;
        }
        return selectData[0];
    }
    /**
     * 附件下载
     * @param  {[string]} filesIds [文件的业务关联ID]
     * @return {[undefined]}          [无]
     * @author lis 2017-10-21
     */
    this.downLoadFiles = function(filesIds) {
        $http({
            method: 'POST',
            url: URL_PATH.FILE_HEADER + '/getFiles.do?actionMethod=getFiles',
            data: { businessIds: filesIds },
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            transformRequest: function(obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function(data, status, headers, config) {
            if (data.data && data.data.length > 0) {
                angular.forEach(data.data, function(image) {
                    httpService.get(_scope, URL_PATH.IMAGE_HEADER + image.viewUrl).success(function(response) {
                        $('#cnex4DownLoadFiles').attr({ download: image.name, href: URL_PATH.IMAGE_HEADER + image.viewUrl });
                        $('#cnex4DownLoad').trigger('click');
                    }).error(function(error) {
                        submitTips('附件已经丢失，请联系管理员！', 'warning');
                    });
                });
            } else {
                submitTips('尚无附件，请先上传后下载！', 'warning');
            }
        });
    }
    /**
     * [列表导出所有数据方法]
     * @param  {[object]} tableGrid [页面列表对象]
     * @return {[object]}           [返回permise对象]
     * @author lis 2017-10-21
     */
    this.exportAllData = function(_scope, tableGrid) {
        var postQueryPage = copyQueryPage(_scope.queryPage);
        postQueryPage.pageSize = tableGrid.totalItems;
        this.storeQueryParam();
        return httpService.post(_scope, _scope.loadURL, postQueryPage)
            .success(function(data) {
                tableGrid.data = data.content;
            });
    };
    /**
     * [对列表中的数据进行转码，目前未使用]
     * @param  {[object]} data      [需要转码的数据对象]
     * @param  {[object]} gridTable [数据列表对象]
     * @param  {[object]} _scope    [当前controller的作用域对象]
     * @return {[undefined]}           [无]
     * @author lis 2017-10-21
     */
    this.decodeTable = function(_scope, data, gridTable) {
        var columnDefs = gridTable.columnDefs;
        for (var columnIndex in columnDefs) {
            var columnDef = columnDefs[columnIndex];
            if (columnDef.decode) {
                var decodeField = columnDef.field;
                var parent = columnDef.decode.parent;
                var dataField = columnDef.decode.field;
                var dataKey = columnDef.decode.dataKey;
                var isMutil = columnDef.decode.mutil;
                if (!dataField || !dataKey) {
                    continue;
                }
                var attrCode = columnDef.decode.code ? columnDef.decode.code : 'code';
                var attrName = columnDef.decode.name ? columnDef.decode.name : 'name';
                var decodeData;
                if (dataKey.indexOf("local.") > -1) {
                    if (_scope.localData) {
                        decodeData = _scope.localData[dataKey.substring(6)];
                    }
                } else {
                    decodeData = cacheService.getData(dataKey);
                }
                if (decodeData) {
                    for (var i in data) {
                        var dataItem = data[i];
                        var code = "";
                        if (parent !== undefined) {
                            var objParent = dataItem[parent];
                            if (objParent) {
                                code = objParent[dataField];
                            }
                        } else {
                            code = dataItem[dataField];
                        }
                        if (code != undefined) {
                            if (isMutil) {
                                var labels = '';
                                var codes = code.split(",");
                                for (var z in codes) {
                                    for (var y in decodeData) {
                                        var decodeItem = decodeData[y];
                                        if (codes[z] == decodeItem[attrCode]) {
                                            if (labels != '') {
                                                labels += "," + decodeItem[attrName];
                                            } else {
                                                labels = decodeItem[attrName];
                                            }
                                            break;
                                        }
                                    }
                                }
                                dataItem[decodeField] = labels;
                            } else {
                                for (var y in decodeData) {
                                    var decodeItem = decodeData[y];
                                    if (code == decodeItem[attrCode]) {
                                        /* if (parent) {
                                             var parentObj = dataItem[parent];
                                             parentObj[dataField] = decodeItem[attrName];
                                         } else {*/
                                        dataItem[decodeField] = decodeItem[attrName];
                                        /*  }*/
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };
    /**
     * 列表操作
     * @param  scope的excute中定义的操作类型
     * @param  {[type]} filed     当前列字段名称
     * @return {[type]}           [description]
     * @author lis 2017-11-20
     */
    this.getLinkCellTmplate = function(operation, filed, displayValue) {
        if (displayValue) {
            return "<a style='text-align:center'>" +
                "<span style='color:#F09500;cursor:pointer;' ng-click=grid.appScope.execute('" + operation + "',row.entity)>" +
                "{{'" + displayValue + "'}}" +
                "</span>" +
                "</a>";
        } else {
            return "<a style='text-align:center'>" +
                "<span style='color:#F09500;cursor:pointer;' ng-click=grid.appScope.execute('" + operation + "',row.entity)>" +
                "{{row.entity['" + filed + "']}}" +
                "</span>" +
                "</a>";
        }
    }

    this.setGridHeight = function(para) {
        /* 最外层高度*/
        var contHeight = $(".content.n-cont").height();
        /* 顶部tab框的高度*/
        var tabsHeight = 0;
        $(".bs-example #headerDiv").each(function() {
            if ($(this).height() > 0) {
                tabsHeight = $(this).height();
            }
        });
        /* 搜索框的高度*/
        var searchHeight = 0;
        /* 搜索框行数*/
        var searchRow = Math.ceil($rootScope.searchCount / 3);
        if (searchRow <= 1) {
            searchHeight = 70;
        } else {
            searchHeight = 110;
        }
        /*表格顶部功能按钮高度*/
        var btnsHeight = 0;
        $(".results .row").each(function() {
            if ($(this).height() > 0) {
                btnsHeight = $(this).height();
            }
        });
        /* 表格高度*/
        $(".grid").height(contHeight - (tabsHeight + btnsHeight + searchHeight + 20));
        $(".ztree").css({
            "min-height": contHeight - (tabsHeight + btnsHeight + searchHeight + 20) + "px",
            "max-height": contHeight - (tabsHeight + btnsHeight + searchHeight + 20) + "px"
        })
    };

};

angular.module('huatek.services').service('listService', ListServiceFunction);
var EditServiceFunction = function($http, $q, $rootScope, cacheService, $timeout,httpService) {
    var _functions;
    this.init = function(_scope) {
    	var _self=this;
        _scope.checkData = function(i){return _self.checkData(_scope,i)};
        /*返回按钮*/
        _scope.back = function() {
            _scope['jsPanel'].close();
        }
        /*设置点击后按钮禁止*/
        _scope.waitForDbCommint = function(){
			_scope.btnDisabledStatus = true;
			$timeout(function() {
				_scope.btnDisabledStatus = false;
	        },$rootScope.disabled_wait_time);
		};
    };

    this.setFormFields = function(_scope, formFieldArray) {
        _scope.formFieldArrayList = formFieldArray;
        _scope.fieldMap = getMap(formFieldArray, "name");
        for (var index in _scope.formFieldArrayList) {
            var field = _scope.formFieldArrayList[index];
            cacheService.bindFieldData(field);
        }
    };

    this.getFieldMap = function(_scope) {
        return _scope.fieldMap;
    }

    /**
     * 显示图片
     */
    this.viewPicture = function(_scope) {
        var formFieldArray = _scope.formFieldArrayList;
        for (var i = 0; i < formFieldArray.length; i++) {
            var formField = formFieldArray[i];
            if (formField.model == 'file') {
                var fileId = formField.businessId;
                /*文件id*/
                var viewDivId = formField.viewDivId;
                /*显示的div*/
                if (fileId && viewDivId) {
                    var actionUrl = 'getFileUrl.do?actionMethod=getUrl';
                    _scope.promise = httpService.post(_scope,actionUrl + "&fileId=" + fileId).success(function(result) {
                        /*获取文件路径并且预览*/
                        var data = result.data;
                        if (data != null && data != undefined && viewDivId != null && viewDivId != undefined) {
                            for (var i = 0; i < data.length; i++) {
                                var result = data[i];
                                var visitUrl = result.viewUrl;
                                var extFileName = result.extFileName;
                                if (visitUrl != null && visitUrl != undefined && ("img" == extFileName || "png" == extFileName || "jpg" == extFileName || "gif" == extFileName)) {
                                    var img = "<span style='margin:2px;float:left'><img src='" + visitUrl + "'/></span>";
                                    var html = $("#" + viewDivId).html();
                                    $("#" + viewDivId).html(img + html);
                                }
                            }
                        }
                    });
                }
            }
        }
    };

    /**
     * beforeInitCallback 在数据加载成功之后，渲染页面之前执行
     * afterInitCallback 在数据加载成功之后，渲染页面之后执行
     */
    this.loadData = function(_scope,url,id,afterInitCallback,beforeInitCallback) {
        _scope.promise = httpService.get(_scope,url + "/" + id).success(function(response) {
            $q.when(true).then(function(result) {
                if (angular.isFunction(beforeInitCallback)) {
                    beforeInitCallback(response);
                }
                return result;
            }).then(function(result) {
                for (var p in response) {
                    var formField = _scope.fieldMap.get(p);
                    /*如果是显示值,则把值取出来*/
                    if ((!formField || formField.isShow == false) && p.charAt(p.length - 1) == '_') {
                        var fieldName = p.substring(0, p.length - 1);
                        formField = _scope.fieldMap.get(fieldName);
                        formField.displayValue = response[p];
                        continue;
                    }
                    if (formField) {
                        var endField = _scope.fieldMap.get(formField.endName);
                        /*如果是日期区间，需要把值拆分到时间区间的两个绑定值上*/
                        if ((formField.model == 'date-section' || formField.model == 'dateSection' || formField.model == 'time-section' || formField.model == 'timeSection') && response[p] && endField) {
                            formField.minValue = response[p];
                            formField.maxValue = response[endField.name];
                        } else if ((formField.model == 'dateTime-section' || formField.model == 'dateTimeSection') && response[p] && endField) {
                            formField.minValue = getDate(response[p]);
                            formField.maxValue = getDate(response[endField.name]);
                            formField.minTimeValue = getTime(response[p]);
                            formField.maxTimeValue = getTime(response[endField.name]);
                        } else if ((formField.model == 'dateTime' || formField.model == 'date-time') && response[p]) {
                            formField.minValue = getDate(response[p]);
                            formField.maxValue = getTime(response[p]);
                        }else if (formField.model == 'date' && response[p]) {
                            formField.minValue = getDate(response[p]);
                            formField.value = getDate(response[p]);
                        } else if (formField.model == 'checkbox' || formField.type == 'boolean') {
                            if (typeof response[p] == 'string') {
                                if (response[p] == 'true') {
                                    formField.value = true;
                                } else {
                                    formField.value = false;
                                }
                            } else {
                                formField.value = response[p];
                            }
                        } else if (formField.model == 'selectMultiple') {
                            if (response[p] != null && response[p] != "") {
                                formField.params = response[p].split(",");
                            }
                        } else if (formField.model == 'checkboxAndOthers') {
                            formField.value = response[p];
                            if (cnex4_isEmpty_str(response[p])) {
                                formField.minValue = true;
                            } else {
                                formField.minValue = false;
                            }
                        } else if (formField.model == 'autocomplete') {
                            formField._choosed = true;
                            formField.value = response[p];
                        } else if(formField.model == FORM_COMPONENT.SELECT_TREE_SINGLE || formField.model == FORM_COMPONENT.TENDERS_SLECT){
                        	if(formField.lookValue && response[formField.lookValue]){
                        		formField.lookValue = response[formField.lookValue];
                        	}
                        	formField.value = response[p] + '';
                        }else {
                            formField.value = response[p] + '';
                        }

                    }
                }
                return result;
            }).then(function(result) {
                if (angular.isFunction(afterInitCallback)) {
                    afterInitCallback(response);
                }
            });
        });
    };

    this.getPostData = function(_scope) {
        var data = {};
        for (var i = 0; i < _scope.formFieldArrayList.length; i++) {
            var _field = _scope.formFieldArrayList[i];
            if (_field.model == 'dateTime' || _field.model == 'date-time') {
                data[_field.name] = _field.minValue + " " + _field.maxValue;
            } else if (_field.model == 'dateSection' || _field.model == 'date-section' || _field.model == 'timeSection' || _field.model == 'time-section') {
                data[_field.name] = _field.minValue;
                data[_field.endName] = _field.maxValue;
            } else if (_field.model == 'dateTimeSection' || _field.model == 'dateTime-section') {
                data[_field.name] = _field.minValue + " " + _field.minTimeValue;
                data[_field.endName] = _field.maxValue + " " + _field.maxTimeValue;
            } else if (_field.model == 'notHandle' || _field.model == 'not-handle') {
                /** model等于notHandle或者not-handle的字段不需要赋值，会在处理相关字段的时候自动赋值 */
            } else if (_field.model == 'selectMultiple') {
                if (_field.params != null && _field.params.length > 0) {
                    data[_field.name] = _field.params.toString();
                } else {
                    data[_field.name] = null;
                }
            } else if (_field.model == 'select-autocomplete') {
                if (_field.value) {
                    if (typeof _field.value == 'string') {
                        data[_field.name] = _field.value;
                    } else {
                        data[_field.name] = _field.value['_returnField'];
                    }
                }
            } else if (_field.model == 'selectOrAdd') {
                if (_field.value) {
                    data[_field.name] = _field.value.toString();
                }
            } else {
                data[_field.name] = _field.value;
            }

        }
        return data;
    };

    this.isAllowPost = function(_scope) {
        var formFieldArray = _scope.formFieldArrayList;
        var allowPost = true;
        /*未取到表单字段集合*/
        if (!formFieldArray || formFieldArray.length < 1) {
            return true;
        }
        /*遍历当前集合，校验所有字段*/
        for (var i = 0; i < formFieldArray.length; i++) {
            this.checkData(_scope,i);
        }
        var errorMsgArray = [];
        /*遍历当前集合，判断时候所有字段校验都通过*/
        angular.forEach(formFieldArray, function(data, index, array) {
            if (data.errorMsg != null || data.minErrorMsg != null || data.maxErrorMsg != null || data.minTimeErrorMsg != null || data.maxTimeErrorMsg != null) {
                console.log(data.title + "，data.errorMsg=" + data.errorMsg + ";data.minErrorMsg=" + data.minErrorMsg + "data.maxErrorMsg=" + data.maxErrorMsg + ";data.minTimeErrorMsg=" + data.minTimeErrorMsg + ";data.maxTimeErrorMsg=" + data.maxTimeErrorMsg);
                var errorMessage = '';
                if (null != data.errorMsg) {
                    errorMessage += data.errorMsg;
                }
                if (null != data.minErrorMsg) {
                    errorMessage += data.minErrorMsg;
                } else {
                    if (null != data.maxErrorMsg) {
                        errorMessage += data.maxErrorMsg;
                    }
                }

                if (null != data.minTimeErrorMsg) {
                    errorMessage += data.minTimeErrorMsg;
                } else {
                    if (null != data.maxTimeErrorMsg) {
                        errorMessage += data.maxTimeErrorMsg;
                    }
                }
                var errorItem = new errorMsgItem(data.title, errorMessage);
                errorMsgArray.push(errorItem);
                allowPost = false;
                return;
            }
        });
        if (!allowPost) {
            submitTips('数据输入错误:' + errorMsgArray[0].title + errorMsgArray[0].msg, 'error');
            return false;
        }
        return true;
    };

    this.updateData = function(_scope,toUrl, homeUrl, id, checkDataCallback, saveDataCallback) {
        if (!this.isAllowPost(_scope)) {
            return;
        } else {
            if (checkDataCallback != null && typeof(checkDataCallback) == "function") {
                if (!checkDataCallback()) {
                    submitTips('数据输入错误，请修正后再提交!\t', 'error');
                    return;
                }
            }
        }
        var data = this.getPostData(_scope);
        var actionUrl = toUrl + "/" + id;
        _scope.promise = httpService.post(_scope,actionUrl, data).success(function(response) {
            if (response.type == 'success') {
                if (saveDataCallback != null && typeof(saveDataCallback) == "function") {
                    saveDataCallback(response);
                }
                _scope.back();
            } else {
                submitTips(response.text, 'error');
            }
        });
    };

    this.submitData = function(_scope,toUrl) {
        if (!this.isAllowPost(_scope)) {
            return;
        }
        var data = this.getPostData(_scope);
        var actionUrl = toUrl + "/" + _scope.taskId + "/" + _scope.busiId;
        _scope.promise = httpService.post(_scope,actionUrl, data).success(function(response) {
            if (response.type == 'success') {
                
                _scope.back();
            } else {
                submitTips(response.text, 'error');
            }
        });
    };

    this.updateDataTable = function(_scope,toUrl, homeUrl, id, gridTable) {
        if (!this.isAllowPost(_scope)) {
            return;
        }
        var data = this.getPostData(_scope);
        var table = gridTable.data;
        var actionUrl = toUrl + "/" + id;
        _scope.promise = $http.post(actionUrl, data, table).success(function() {
            _scope.back();
        });
    };


    /**
     * 提供回调方法，在数据验证成功执行特定操作，比如关闭弹出框
     */
    this.saveData = function(_scope,toUrl, checkDataCallback, saveDataCallback) {
        if (!this.isAllowPost(_scope)) {
            return;
        } else {
            if (checkDataCallback != null && typeof(checkDataCallback) == "function") {
                if (!checkDataCallback()) {
                    submitTips('数据输入错误，请修正后再提交!\t', 'error');
                    return;
                }
            }
        }
        var data = this.getPostData(_scope);
        _scope.promise = httpService.post(_scope,toUrl, data).success(function(response) {
            if (response.type == 'success') {
                if (saveDataCallback != null && typeof(saveDataCallback) == "function") {
                    saveDataCallback(response);
                }
                _scope.back();
            } else {
                submitTips(response.text, 'error');
            }
        });
    };


    this.saveOrderData = function(_scope,toUrl, homeUrl, checkDataCallback, saveDataCallback) {
        if (!this.isAllowPost(_scope)) {
            return;
        } else {
            if (checkDataCallback != null && typeof(checkDataCallback) == "function") {
                if (!checkDataCallback()) {
                    bootbox.alert("数据输入错误，请修正后再提交!\t");
                    return;
                }
            }
        }
        var data = this.getPostData(_scope);
        _scope.promise = httpService.post(_scope,toUrl, data).success(function(response) {
            if (response.type == 'success') {
                if (saveDataCallback != null && typeof(saveDataCallback) == "function") {
                    saveDataCallback();
                }
                _scope.back();
            } else {
                submitTips(response.text, 'error');
            }
        });
    };



    /*带弹框的保存*/

    this.addData = function(toUrl, homeUrl) {
        if (!this.isAllowPost(_scope)) {
            return;
        }
        var data = this.getPostData(_scope);
        _scope.promise = httpService.post(_scope,toUrl, data).success(function(response) {
            if (response.type == 'success') {
                submitTips(response.text, 'success');
            } else {
                submitTips(response.text, 'error');
            }

        });
    };

    this.flowStart = function(_scope,toUrl, homeUrl, id) {
        if (!this.isAllowPost(_scope)) {
            return;
        }
        var data = this.getPostData(_scope);
        var actionUrl = toUrl + "/" + id;
        _scope.promise = httpService.post(_scope,actionUrl, data).success(function() {});
    };

    /**
     * 获取字典的js服务（需要在init、setFormFields服务被调用之后，再调用）
     * 
     * @param toUrl
     *            提供字典查询的服务URL
     */
    this.initParams = function(_scope,toUrl, initSuccessCallback) {
        _scope.promise = httpService.get(_scope,toUrl).success(function(params) {
            for (var i = 0; i < params.length; i++) {
                var formField = _scope.fieldMap.get(params[i].fieldName);
                if (formField) {
                    /*此处数据量如果比较大可能会有异步的bug*/
                    resolveShowFieldAndReturnField(formField, params[i].params);
                    formField.items = params[i].params;
                }
            };
        }).then(function(rest) {
            if (angular.isFunction(initSuccessCallback)) {
                initSuccessCallback();
            }
        });
    };

    /***
     * 加载api数据.
     */
    this.initFieldItems = function(_scope,formField, dataUrl) {
        _scope.promise = httpService.get(_scope,dataUrl).success(function(params) {
        	resolveShowFieldAndReturnField(formField,params);
            formField.items = params;
        });
    }

    /**给页面字段赋值*/
    this.setFieldValue = function(_scope, fieldName, fieldValue) {
        if (_scope.fieldMap.get(fieldName)) {
        	_scope.fieldMap.get(fieldName).value = fieldValue;
        }else{
            console.log("无此对象");
        }
    }

    /**
     * 数据校验方法
     * 
     * @param index
     *            单独字段校验时字段的序号
     */
    this.checkData = function(_scope,index, checkObj) {
        var formField = checkObj || _scope.formFieldArrayList[index];
        /*初始化错误提示及样式*/
        formField.css = null;
        formField.minCss = null;
        formField.maxCss = null;
        formField.minTimeCss = null;
        formField.maxTimeCss = null;
        formField.errorMsg = null;
        formField.minErrorMsg = null;
        formField.maxErrorMsg = null;
        formField.minTimeErrorMsg = null;
        formField.maxTimeErrorMsg = null;
        var return_falg = false;

        /*checkbox,notHandle和不显示字段不做任何处理*/
        if (formField.model == 'notHandle' || !formField.isShow || formField.model == 'checkbox' /*|| formField.readonly == 'readonly' || formField.readonly == true*/ ) {
            return;
        }
        /*必填字段校验，且不为隐藏字或者非处理字段*/
        if (formField.require == '1' || formField.require == 'true' || formField.require == 'require') {
            /*值部位空的时候*/
            /*特殊组件需要按类型判断(暂时缺比较时间区间值的校验)*/
            if (formField.model == 'dateTime' || formField.model == 'date-time' || formField.model == 'dateSection' || formField.model == 'date-section' || formField.model == 'timeSection' || formField.model == 'time-section') {
                if (!formField.minValue) {
                    formField.minErrorMsg = "不能为空";
                    formField.minCss = "validation-failure";
                    return_falg = true;
                }
                if (!formField.maxValue) {
                    formField.maxErrorMsg = "不能为空";
                    formField.maxCss = "validation-failure";
                    return_falg = true;
                }
                if (return_falg) {
                    return;
                }
            } else if (formField.model == 'dateTimeSection' || formField.model == 'dateTime-section') {
                if (!formField.minValue) {
                    formField.minErrorMsg = "不能为空";
                    formField.minCss = "validation-failure";
                    return_falg = true;
                }
                if (!formField.maxValue) {
                    formField.maxErrorMsg = "不能为空";
                    formField.maxCss = "validation-failure";
                    return_falg = true;
                }
                if (!formField.minTimeValue) {
                    formField.minTimeErrorMsg = "不能为空";
                    formField.minTimeCss = "validation-failure";
                    return_falg = true;
                }
                if (!formField.maxTimeValue) {
                    formField.maxTimeErrorMsg = "不能为空";
                    formField.maxTimeCss = "validation-failure";
                    return_falg = true;
                }
                if (return_falg) {
                    return;
                }
            } else if (formField.model == "selectMultiple") {
                if (undefined == formField.params || null == formField.params || formField.params.length < 1) {
                    formField.errorMsg = '值不能为空';
                    formField.css = "validation-failure";
                    return;
                }
            } else if (formField.model == "one-in-two") {
                if (!cnex4_isEmpty_str(formField.value) && !cnex4_isEmpty_str(formField.endField.value)) {
                    formField.errorMsg = '二选一必填，请至少保证其中的一项有值！';
                    formField.css = "validation-failure";
                    return;
                }
            } else if (formField.model != 'select' && formField.model != 'select-autocomplete' && formField.model != "selectMultiple" && formField.model != "file" && formField.model != "fileMultiple" && formField.model != "fileSingle") {
               
                if (typeof formField.value != "number" && (formField.value == null || formField.value == "" || formField.value == undefined)) {
                    formField.errorMsg = '值不能为空';
                    formField.css = "validation-failure";
                    return;
                }
                if (formField.min >= 0 && formField.max >= 0 && (formField.value.length > formField.max || formField.value.length < formField.min)) {
                    formField.errorMsg = '长度应在' + formField.min + '和' + formField.max + '之间';
                    formField.css = "validation-failure";
                    return;
                }
                if (formField.min > 0 && formField.value.length < formField.min) {
                    formField.errorMsg = '长度不能小于' + formField.min;
                    formField.css = "validation-failure";
                    return;
                }
            } else if (formField.model == "fileMultiple") {
                /*多附件上传*/
                var filesCount = formField.filesCount;
                if (undefined == filesCount || null == filesCount || 0 == filesCount) {
                    formField.errorMsg = '上传附件不能为空';
                    formField.css = "validation-failure";
                    return;
                }
                var maxFilesCount = formField.maxFilesCount;
                if (filesCount > maxFilesCount) {
                    formField.errorMsg = '上传附件数量不能大于' + maxFilesCount;
                    formField.css = "validation-failure";
                    return;
                }
                var minFilesCount = formField.minFilesCount;
                if (filesCount < minFilesCount) {
                    formField.errorMsg = '上传附件数量不能小于' + minFilesCount;
                    formField.css = "validation-failure";
                    return;
                }
            } else if (formField.model == "fileSingle") {
                /*单附件上传*/
                var filesCount = formField.filesCount;
                if (undefined == filesCount || null == filesCount || filesCount < 1) {
                    formField.errorMsg = '上传附件不能为空';
                    formField.css = "validation-failure";
                    return;
                } else if (filesCount > 1) {
                    formField.errorMsg = '附件数量过多，最多只能提交一个附件！';
                    formField.css = "validation-failure";
                    return;
                }
            } else if (formField.model == "imageScan") {
                /*单附件上传*/
                var filesCount = formField.filesCount;
                if (undefined == filesCount || null == filesCount || filesCount < 1) {
                    formField.errorMsg = '上传图片不能为空';
                    formField.css = "validation-failure";
                    return;
                }
            } else {
                if (formField.value == null || formField.value == "" || formField.value == undefined) {
                    formField.errorMsg = '值不能为空';
                    formField.css = "validation-failure";
                    return;
                }
            }
        }
        if (formField.value != null && formField.value != '') {
            if (formField.model != 'select' && formField.model != 'select-autocomplete' && formField.model != "selectMultiple" && formField.model != "file" && formField.model != "fileMultiple" && formField.model != "fileSingle") {
                if (formField.value == null || formField.value == "" || formField.value == undefined) {
                    formField.errorMsg = '值不能为空';
                    formField.css = "validation-failure";
                    return;
                }
                if (formField.min >= 0 && formField.max >= 0 && (formField.value.length > formField.max || formField.value.length < formField.min)) {
                    formField.errorMsg = '长度应在' + formField.min + '和' + formField.max + '之间';
                    formField.css = "validation-failure";
                    return;
                }
                if (formField.min > 0 && formField.value.length < formField.min) {
                    formField.errorMsg = '长度不能小于' + formField.min;
                    formField.css = "validation-failure";
                    return;
                }
            }
            if (formField.type == 'int' && formField.model != 'select' && formField.model != 'select-autocomplete' && formField.model != "selectMultiple" && formField.model != "file" && formField.model != "fileMultiple" && formField.model != "fileSingle") {
                var reg = /^-?\d+$/;
                if (!reg.test(formField.value)) {
                    formField.errorMsg = '输入的不是整数';
                    formField.css = "validation-failure";
                    return;
                }
                if (formField.number_MinValue != "") {
                    var value = formField.value;
                    var number_MinValue = formField.number_MinValue;
                    if (value <= number_MinValue) {
                        formField.errorMsg = '输入的值不能小于等于' + number_MinValue;
                        formField.css = "validation-failure";
                        return;
                    }
                }
                if (formField.number_MaxValue != "") {
                    var value = formField.value;
                    var number_MaxValue = formField.number_MaxValue;
                    if (value > number_MaxValue) {
                        formField.errorMsg = '输入的值不能大于' + number_MaxValue;
                        formField.css = "validation-failure";
                        return;
                    }
                }
            }
            if (formField.type == 'number' && formField.model != 'select' && formField.model != 'select-autocomplete' && formField.model != "selectMultiple" && formField.model != "file" && formField.model != "fileMultiple" && formField.model != "fileSingle") {
                /*	 		   var reg = /^-?\\d+(.[0-9]{1,3})?$/;*/
                var reg = /^(-?\d+)(\.\d+)?$/;
                if (!reg.test(formField.value)) {
                    formField.errorMsg = '输入的不是数字';
                    formField.css = "validation-failure";
                    return;
                }
                if (undefined != formField.scale && (formField.value).toString().indexOf('.') > -1 && (formField.value).toString().split('.')[1].length > formField.scale * 1) {
                    formField.errorMsg = '请保留' + formField.scale + '位小数！';
                    formField.css = "validation-failure";
                    return;
                }
                if (formField.number_MinValue != "") {
                    var value = formField.value;
                    var number_MinValue = formField.number_MinValue;
                    if (parseFloat(value) <= parseFloat(number_MinValue)) {
                        formField.errorMsg = '输入的值不能小于等于' + number_MinValue;
                        formField.css = "validation-failure";
                        return;
                    }
                }
                if (formField.number_MaxValue != "") {
                    var value = formField.value;
                    var number_MaxValue = formField.number_MaxValue;
                    if (parseFloat(value) > parseFloat(number_MaxValue)) {
                        formField.errorMsg = '输入的值不能大于' + number_MaxValue;
                        formField.css = "validation-failure";
                        return;
                    }
                }
                if (formField.number_MinValueNoEqual != "") {
                    var value = formField.value;
                    var number_MinValueNoEqual = formField.number_MinValueNoEqual;
                    if (parseFloat(value) < parseFloat(number_MinValueNoEqual)) {
                        formField.errorMsg = '输入的值不能小于' + number_MinValueNoEqual;
                        formField.css = "validation-failure";
                        return;
                    }
                }
                if (formField.number_MaxValueEqual != "") {
                    var value = formField.value;
                    var number_MaxValueEqual = formField.number_MaxValueEqual;
                    if (parseFloat(value) >= parseFloat(number_MaxValueEqual)) {
                        formField.errorMsg = '输入的值不能大于等于' + number_MaxValueEqual;
                        formField.css = "validation-failure";
                        return;
                    }
                }
            }
            if (formField.type == 'email') {
                var reg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
                if (!reg.test(formField.value)) {
                    formField.errorMsg = '非法的邮箱格式';
                    formField.css = "validation-failure";
                }
                return;
            }
            if (formField.type == 'phone') {
                var reg = /^(\+86)?(1[0-9]{10})$/;
                if (!reg.test(formField.value)) {
                    formField.errorMsg = '非法的手机号码格式';
                    formField.css = "validation-failure";
                }
                return;
            }
            if (formField.type == 'telephone') {
                var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
                if (!reg.test(formField.value)) {
                    formField.errorMsg = '非法的电话号码格式,电话格式:区号+号码';
                    formField.css = "validation-failure";
                }
                return;
            }
            /*既满足手机号又能满足电话号*/
            if (formField.type == 'telephoneOrPhone') {
                var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
                var reg2 = /^(\+86)?(1[0-9]{10})$/;
                if (!reg.test(formField.value) && !reg2.test(formField.value)) {
                    formField.errorMsg = '手机或固话,固话格式:区号-号码';
                    formField.css = "validation-failure";
                }
                return;
            }
            if (formField.type == 'personalCard') {
                var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                if (!reg.test(formField.value)) {
                    formField.errorMsg = '非法的身份证号码输入';
                    formField.css = "validation-failure";
                }
                return;
            }

            /*输入正整数,不包括 0*/
            if (formField.type == 'numberInt') {
                var reg = /^\+?[1-9][0-9]*$/
                if (!reg.test(formField.value)) {
                    formField.errorMsg = '请输入非零的正整数';
                    /*formField.maxTimeCss = "validation-failure";*/
                    formField.css = "validation-failure";
                }
                return;
            }
            if (formField.type == 'password') {
                /**密码校验-长度6到20位，必须包含字母和数字**/
            }
            if (formField.model != 'select' && formField.model != 'select-autocomplete' && formField.model != "selectMultiple" && formField.model != "fileMultiple" && formField.model != "fileSingle") {
                if (formField.max > 0 && formField.value.length > formField.max) {
                    formField.errorMsg = '长度不能大于' + formField.max;
                    formField.css = "validation-failure";
                    return;
                }
            }

        }

    };


    /*文件下载，参数为文件的UUID*/
    this.downLoadFiles = function(filesIds) {
        $http({
            method: 'POST',
            url: URL_PATH.FILE_HEADER + '/getFiles.do?actionMethod=getFiles',
            data: { businessIds: filesIds },
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            transformRequest: function(obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function(data, status, headers, config) {
            if (data.data && data.data.length > 0) {
                angular.forEach(data.data, function(image) {
                    httpService.get(_scope,URL_PATH.IMAGE_HEADER + image.viewUrl).success(function(response) {
                        $('#cnex4DownLoadFiles').attr({ download: image.name, href: URL_PATH.IMAGE_HEADER + image.viewUrl });
                        $('#cnex4DownLoad').trigger('click');
                    }).error(function(error) {
                        submitTips('附件已经丢失，请联系管理员！', 'warning');
                    });
                });
            } else {
                submitTips('尚无附件，请先上传后下载！', 'warning');
            }
        });
    }
};
angular.module('huatek.services').service('editService', EditServiceFunction);

/**
 * [用于分装所有http请求]
 */
angular.module('huatek.services').service('httpService',function($http){
	
	
	this.get=function(_scope,url,data){
		var obj={
	            method:'get',
	            url:url,
	        };
		if(data){
			obj.params=data;
		}
		var menuId=_scope.menuId;
		var parentScope=_scope.$parent;
		while(parentScope&&!menuId){
			menuId=parentScope.menuId;
			parentScope=parentScope.$parent;

		}
		if(menuId){
			obj.headers= {'menuId':menuId}  ;
		}

		return $http(obj);
	};
	this.post=function(_scope,url,data){
		var obj={
	            method:'post',
	            url:url,
	        };
		if(data){
			obj.data=data;
		}
		var menuId=_scope.menuId;
		var parentScope=_scope.$parent;
		while(parentScope&&!menuId){
			menuId=parentScope.menuId;
			parentScope=parentScope.$parent;
		}
		if(menuId){
			obj.headers= {'menuId':menuId}  ;
		}
		return $http(obj);
	};
	this.getOutOfScope=function(menuId,url,data){
		var obj={
	            method:'get',
	            url:url,
	        };
		if(data){
			obj.params=data;
		}
		if(menuId){
			obj.headers= {'menuId':menuId}  ;
		}
		return $http(obj);
	};
	this.postOutOfScope=function(menuId,url,data){
		var obj={
	            method:'post',
	            url:url,
	        };
		if(data){
			obj.data=data;
		}
		if(menuId){
			obj.headers= {'menuId':menuId}  ;
		}
		return $http(obj);
	};
	
	this.delete=function(_scope,url,data){
		var obj={
	            method:'delete',
	            url:url,
	        };
		if(data){
			obj.data=data;
		}
		var menuId=_scope.menuId;
		var parentScope=_scope.$parent;
		while(parentScope&&!menuId){
			menuId=parentScope.menuId;
			parentScope=parentScope.$parent;
		}
		if(menuId){
			obj.headers= {'menuId':menuId}  ;
		}
		return $http(obj);
	}
});


/*批量新增*/

angular.module('huatek.services')
    .service('batchEditService', function(cacheServic,$http) {
        this.init = function(_scope) {
            _scope.deleteDataArr = [];
            /*用来暂存删除的行数据*/
            _scope.isDataChanged = false;
            /*返回按钮*/

            _scope.back = function() {
                var panels = _scope.tab.panels
                if (panels.length > 0) {
                    var arrIdex = 0;
                    for (var i = 0; i < panels.length; i++) {
                        if (panels[i] === this) {
                            arrIdex = i;
                            break;
                        }
                    }
                    _scope.tab.panels.splice(arrIdex, 1);
                }
                _scope['jsPanel'].close();
            }
            _scope.resetSearch = function() {
                for (var i = 0; i < _scope.queryPage.queryParamList.length; i++) {
                    _scope.queryPage.queryParamList[i].value = null;
                }
            };
        };
        this.setQueryPage = function(_scope,queryPage) {
            _scope.queryPage = queryPage;
            _scope.queryFieldList = _scope.queryPage.queryParamList;
            _scope.queryPageMap = getMap(_scope.queryPage.queryParamList, 'field');
            for (var index in _scope.queryFieldList) {
                var field = _scope.queryFieldList[index];
                cacheService.bindFieldData(field);
            }
        };

        /**
         * 动态控制“查询条件UI”和“消息体”
         * 
         * @auth caojun1@hisense.com 20160312
         */
        this.setQueryPage2 = function(_scope,queryPage) {
            /*消息体内容*/
            _scope.queryPage = queryPage;
            var newQps = [];
            var qps = _scope.queryPage.queryParamList;
            for (var i = 0; i < qps.length; i++) {
                if (qps[i]["display"] == false) {
                    continue;
                } else
                    newQps.push(qps[i]);
            }
            /*查询条件UI*/
            _scope.queryFieldList = newQps;
            _scope.queryPageMap = getMap(_scope.queryPage.queryParamList, 'field');
        };

        this.setButtonList = function(_scope,_btnArray) {
            _scope.btnArrayList = _btnArray;
        };

        this.setFunctions = function(_scope,functions) {
            _scope.functions = functions;
        }

        this.getDeleteDataArray = function() {
            return _scope.deleteDataArr;
        }
        /***************************************************************************
         * 导入模版下载
         */
        this.downloadTemplate = function(_scope,form) {
            var actionUrl = fileServerPath + form;
            window.location.href = actionUrl;
        };

        this.exportData = function(_scope,impl, param) {
            _scope.actionUrl = exportServerPath + impl;
            for (_scope.num = 0; _scope.num < _scope.queryFieldList.length; _scope.num++) {
                if (_scope.queryFieldList[_scope.num].value != null && _scope.queryFieldList[_scope.num].value != '') {
                    _scope.actionUrl += '&' + _scope.queryFieldList[_scope.num].field + '=' + _scope.queryFieldList[_scope.num].value;
                }
            }
            if (param) {
                _scope.actionUrl += param
            }
            window.location.href = _scope.actionUrl;
        };

        /**
         * 加载表格数据
         */
        this.loadData = function(_scope,url, gridTable) {
            _scope.promise = $http.get(url).success(function(response) {
                gridTable.data = response;
            });
        }

        /**
         * 加载表格数据
         */
        this.loadDataByQueryPage = function(url, gridTable) {
            for (var i = 0; i < _scope.queryPage.queryParamList.length; i++) {
                var param = _scope.queryPage.queryParamList[i];
                if (param.value != undefined && param.value != null && (param.logic == 'like' || param.logic == 'alllike')) {
                    if (param.value.indexOf("%") > -1) {
                        submitTips('警告!查询条件不能包含特殊字符', 'warning');
                        return;
                    }
                }
            }
            _scope.promise = $http.post(url, _scope.queryPage).success(function(response) {
                gridTable.data = response;
            });
        }

        /**
         * 加载表格分页数据
         */
        this.loadDataDataPage = function(_scope,url, gridTable) {
            for (var i = 0; i < _scope.queryPage.queryParamList.length; i++) {
                var param = _scope.queryPage.queryParamList[i];
                if (param.value != undefined && param.value != null && (param.logic == 'like' || param.logic == 'alllike')) {
                    if (param.value.indexOf("%") > -1) {
                        submitTips('警告!查询条件不能包含特殊字符', 'warning');
                        return;
                    }
                }
            }
            _scope.deleteDataArr = [];
            _scope.promise = $http.post(url, _scope.queryPage).success(function(response) {
                if (response.totalRows == 0) {
                    gridTable.data = [];
                } else {
                    gridTable.data = response.content;
                }
                gridTable.totalItems = response.totalRows;
            });
        }

        /**
         * 添加行
         * 
         * @param gridTable
         *            gridTable
         * @param isAddToTop
         *            是否添加到表头
         */
        this.addRow = function(_scope,gridTable, isAddToTop) {
            var newRow = { isNewRow: true };
            if (isAddToTop) {
                gridTable.data.splice(0, 0, newRow);
            } else {
                gridTable.data.push(newRow);
            }
            _scope.isDataChanged = true;
        }
        /**
         * 删除行
         */
        this.delRow = function(_scope,gridTable, gridApi, toUrl) {
            if (gridApi.selection.getSelectedRows().length < 1) {
                submitTips('请至少勾选一条数据！', 'warning');
                return false;
            }
            bootbox.confirm('确定要删除所选的数据吗?', function(result) {
                if (result) {
                    if (!toUrl) {
                        angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                            if (!data.isNewRow) {
                                _scope.deleteDataArr.push(data);
                            }
                            gridTable.data.splice(gridTable.data.lastIndexOf(data), 1);
                            _scope.isDataChanged = true;
                        });
                    } else {
                        angular.forEach(gridApi.selection.getSelectedRows(), function(data, index) {
                            /*当前行移除*/
                            gridTable.data.splice(gridTable.data.lastIndexOf(data), 1);
                            if (data.id != null && data.id != '') {
                                $http.delete(toUrl + "/" + data.id).success(function() {});
                            }
                        });
                    }
                    _scope.$apply();
                }
            });
        }
        /**
         * 更新表格数据
         */
        this.updateData = function(_scope,toUrl, homeUrl, id, gridTable) {
            var data = gridTable.data;
            var actionUrl = toUrl + "/" + id;
            _scope.promise = $http.post(actionUrl, data).success(function() {

            });
        };

        /**
         * 保存表格数据(只提交新增、修改和删除的数据)
         * 注意：需要在controller中定义单元格编辑后的动作，将行数据中的isEdited设置为true, 否则会获取不到进行过编辑的数据
         * 可参考如下代码： $scope.tableGrid.onRegisterApi = function(gridApi){
         * $scope.gridApi = gridApi;
         * gridApi.edit.on.afterCellEdit($scope,function(rowEntity, colDef,
         * newValue, oldValue){ if(!rowEntity.isNewRow) { rowEntity.isEdited = true; }
         * });
         * 
         */
        this.saveData = function(_scope,toUrl, homeUrl, gridTable, forceUrl) {
            var data = gridTable.data;
            var newDataArr = [],
                editDataArr = [],
                postData = [];
            angular.forEach(data, function(row, index) {
                if (row.isNewRow) {
                    newDataArr.push(row);
                } else if (row.isEdited) {
                    editDataArr.push(row);
                }
            });
            postData.push(newDataArr);
            /*新增行数据*/
            postData.push(editDataArr);
            /*修改的行数据*/
            postData.push(_scope.deleteDataArr);
            /*删除的行数据*/
            postData.push(_scope.queryPage);
            _scope.promise = $http.post(toUrl, postData).success(function(data) {
                /** 增加检查提示向导框 start 20160119 add by caojun1@hisense.com */
                if (data.needConfirm && !data.checkResult) {
                    bootbox.confirm("" + data.mesg, function(result) {
                        if (result) {
                            $http.post(forceUrl, postData).success(function(data) {

                            });
                        }
                    });
                }
                /** 增加检查提示向导框 end */
                _scope.deleteDataArr = [];
                if (homeUrl != "") {
                    _scope.isDataChanged = false;
                    $http.post(homeUrl, _scope.queryPage).success(function(response) {
                        if (response.totalRows == 0) {
                            gridTable.data = [];
                        } else {
                            gridTable.data = response.content;
                        }
                        gridTable.totalItems = response.totalRows;
                    });
                } else {
                    /*成功后清除数据新增和编辑标记，初始化删除记录数组*/
                    angular.forEach(newDataArr, function(rowData) {
                        rowData.isNewRow = false;
                    });
                    angular.forEach(editDataArr, function(rowData) {
                        rowData.isEdited = false;
                    });
                    _scope.deleteDataArr = [];
                    _scope.isDataChanged = false;
                }
                _scope.$eval(_scope.functions);
            });
        };

        /**
         * 保存表格数据（数据全部提交）
         */
        this.saveAllData = function(_scope,toUrl, homeUrl, gridTable) {
            var data = gridTable.data;
            _scope.promise = $http.post(toUrl, data).success(function() {

            });
        }

        this.editData = function(_scope,gridApi, toUrl) {
            /*获取当前选择的数据.*/
            var selectData = gridApi.selection.getSelectedRows();
            if (selectData.length > 1) {
                submitTips('警告：不能选择多条数据用于编辑。', 'warning');
                return;
            }
            if (selectData.length == 0) {
                submitTips('请至少勾选一条数据！', 'warning');
                return;
            }
        }

        /**
         * 获取字典的js服务（需要在表格定义之后调用）
         * 
         * @param toUrl
         *            提供字典查询的服务URL
         */
        this.initParams = function(_scope,toUrl) {
            _scope.colDefMap = getMap(_scope.tableGrid.columnDefs, "name");
            $http.get(toUrl).success(function(params) {
                for (var i = 0; i < params.length; i++) {
                    var colDef = _scope.colDefMap.get(params[i].fieldName);
                    if (colDef) {
                        colDef.editDropdownIdLabel = "code";
                        colDef.editDropdownValueLabel = "name";
                        colDef.editDropdownOptionsArray = params[i].params;
                    }
                }
            });
        };

        /**
         * 获取字典的js服务（需要在init、setFormFields服务被调用之后，再调用）
         * 
         * @param toUrl
         *            提供字典查询的服务URL
         */
        this.initQueryParams = function(_scope,toUrl) {
            $http.get(toUrl).success(function(params) {
                for (var i = 0; i < params.length; i++) {
                    var queryParam = _scope.queryPageMap.get(params[i].fieldName);
                    if (queryParam) {
                        resolveShowFieldAndReturnField(queryParam, params[i].params);
                        queryParam.items = params[i].params;
                        if (!queryParam.value) {
                            queryParam.value = params[i].defaultValue;
                        }
                    }
                }
            });
        };
    });
/*excel服务*/
angular.module('huatek.services').service("excelService",function($rootScope,shareData,listService,$window,$http){
	var _scope = $rootScope;
	
	this.FILE_SERVICE="api_file/excel.do";
	this.up=function(type,params,isMutil,callback){
		
		shareData.params=params||{};
		shareData.params["actionMethod"] = type;
		shareData.url=this.FILE_SERVICE;
		shareData.isMutil=isMutil;
		var pop= {
   		     title: "excel上传",
   		     
   		  onclosedFun:callback,
   		     templateView: "static/business/cmd/excelImport/templates/template_excelUpload.html",
   		     
   		 };
		listService.popPanel(_scope,pop);
	};
	this.down=function(busiCode,filename){
		var url=this.FILE_SERVICE+"?actionMethod=download&busiCode="+busiCode+"&filename="+filename+"&t="+(new Date().getTime());
		window.location.href=url;
	};
	this.imp=function(params,popCallBack,url){
	
		shareData.params=params||{};
		shareData.params["actionMethod"]='import';
		shareData.url=url||this.FILE_SERVICE;
		var pop= {
   		     title: "excel导入",
   		     
   		  onclosedFun:popCallBack,
   		     templateView: "static/business/cmd/excelImport/templates/template_excelImport.html",
   		     
   		 };
		listService.popPanel(_scope,pop);
	};


	this.exp = function(busiCode,pageData,userParams){
		var url= this.FILE_SERVICE+"?actionMethod=export";
		var parmaDatas = {"busiCode":busiCode};
		if(pageData&&pageData.queryParamList){
			var queryParamList = pageData.queryParamList;
			for(var i=0;i<queryParamList.length;i++){
				var queryParam = queryParamList[i];
				var name = queryParam.name;
				var value = queryParam.value;
				var params = queryParam.params;
				
				/*针对日期组件名字相同问题处理（把第二次出现的名字后面加'_1'）*/
				for(var key in parmaDatas){
					if(name==key){
						name = name + '_1';
					}
				}
				
				if(undefined!=value&&value!=""){
					parmaDatas[name] = value;
				}else if(undefined!=params&&params!=""){
					parmaDatas[name] = params;
				}
			}
		}
		
		if(userParams){
			for (var key in userParams){
				parmaDatas[key] = userParams[key];
			}
		}
		
		_scope.promise=$http({
            method: 'POST',
            url: url,
            
            /*async: false,*/
            headers: {'Content-Type': undefined },
            transformRequest: function (data) {
                var formData = new FormData();
                formData.append("queryData", angular.toJson(data.parmaDatas));
                return formData;
            },
            data: { parmaDatas: parmaDatas}
        })
        .success(function (data, status, headers, config) {
        	var dowurl = "api_file/excel.do?actionMethod=dowExp&busiCode="+busiCode+"&filePath="+data.filepath;
        	
        	/*submitTips('导出数据成功','success');*/
            window.location.href = dowurl;
        })
        .error(function (data, status, headers, config) {
        	
        	/*submitTips('导出数据失败','error');*/
        	/*如果用户多次点击会后面请求无法正常导出，所以去掉错误提示框*/
        	console.log("导出数据失败！");
        });
		
	};
	this.del=function(busiCode,callback){
		$http.get(this.FILE_SERVICE+"?actionMethod=delete&busiCode="+busiCode).success(function(data){
			if(callback){
				callback(data);
			}
			
		}) ;
	}

});



angular.module('huatek.services').service('i18n', function () {
    var self = this;
    this.setLanguage = function (language) {
        $.i18n.properties({
            name: 'messages',
            path: 'i18n/',
            mode: 'map',
            language: language,
            callback: function () {
                self.language = language;
            }
        });
    };
    this.setLanguage('zh_CN');
});

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
/**
 * 在隔离的scope中共享数据
 */
angular.module('huatek.services').factory('shareData',function(){
	var shareObject = {};
	return shareObject;
});

angular.module('huatek.services').factory('alertService', function($rootScope) {
    var alertService = {};
 
    $rootScope.alerts = [];
 
    alertService.add = function(type, msg) {
    	$rootScope.alerts.push({'type': type, 'msg': msg, 'close': function(){alertService.closeAlert(this); }});
    };
 
    alertService.closeAlert = function(alert) {
    	alertService.closeAlertIdx($rootScope.alerts.indexOf(alert));
    };
 
    alertService.closeAlertIdx = function(index) {
    	$rootScope.alerts.splice(index, 1);
    };
 
    return alertService;
});


/**
 * treeGrid的个性服务定义（对应模版template_treeGrid_withTab.html）
 * 
 * @author lis
 */
angular.module('huatek.services').service('treeGridService', function($http, cacheService, httpService, $sce) {

    var initColumns = function(columnsArr) {
        var arr = [];
        if (columnsArr && columnsArr.length > 0) {
            for (var i = 0; i < columnsArr.length; i++) {
                if (columnsArr[i].type == "treeColumn") {
                    if (columnsArr[i].otherConfig && columnsArr[i].otherConfig.dicName) {
                        columnsArr[i].otherConfig.items = cacheService.getData(columnsArr[i].otherConfig.dicName);
                    }
                    arr.push(columnsArr[i])
                }
            }
        }
        return arr;
    }

    var initUnEditFieldMap = function(columnsArr){
        var map = new Map();
        if (columnsArr && columnsArr.length > 0) {
            for (var i = 0; i < columnsArr.length; i++) {
                if (!columnsArr[i].editable) {
                    map.put(columnsArr[i].field,columnsArr[i])
                }
            }
        }
        return map;
    }
    var getMenuId = function(scope) {
        var menuId = scope.menuId;
        var parentScope = scope.$parent;
        while (parentScope && !menuId) {
            menuId = parentScope.menuId;
            parentScope = parentScope.$parent;
        }
        return menuId;
    }
    /**
     * 选中行时触发动态加载明细数据
     */
    this.setNodeCheckedFn = function(_scope, nodeCheckedFn) {
        if (nodeCheckedFn && angular.isFunction(nodeCheckedFn)) {
            _scope.huatekTree.nodeCheckedFn = nodeCheckedFn;
        }
    }
    /**
     * 点对号，保存当前行时触发
     */
    this.setSaveCheckDataFn = function(_scope, saveCheckDataFn) {
        if (saveCheckDataFn && angular.isFunction(saveCheckDataFn)) {
            _scope.huatekTree.saveCheckDataFn = saveCheckDataFn;
        }
    }
    /**
     * 修改当前行之前触发
     */
    this.setBeforeEditCallBackFn = function(_scope, beforeEditCallBackFn) {
        if (beforeEditCallBackFn && angular.isFunction(beforeEditCallBackFn)) {
            _scope.huatekTree.beforeEditCallBackFn = beforeEditCallBackFn;
        }
    }
    /**
     * 删除当前行之前触发
     */
    this.setBeforeDeleteCallBackFn = function(_scope, beforeDeleteCallBackFn) {
        if (beforeDeleteCallBackFn && angular.isFunction(beforeDeleteCallBackFn)) {
            _scope.huatekTree.beforeDeleteCallBackFn = beforeDeleteCallBackFn;
        }
    }
    /**
     * [setOtherConfig description]
     * @param  {[type]} _scope      [description]
     * @param  otherConfig 
     *         行编辑按钮配置
     *         showTopAdd 是否显示顶级节点新增按钮
     *         showModifyBtn 是否显示修改按钮，编辑模式默认显示，非编辑模式默认不显示
     *         showDeleteBtn 是否显示删除按钮，编辑模式默认显示，非编辑模式默认不显示
     *         showAddBtn 是否显示添加按钮，编辑模式默认显示，非编辑模式默认不显示
     *         showUpBtn 是否显示上移按钮，编辑模式默认显示，非编辑模式默认不显示
     *         showDownBtn 是否显示下移按钮，编辑模式默认显示，非编辑模式默认不显示
     *         数据请求配置
     *         loadChildField 根据配置字段请求子节点数据，默认使用id，可指定行对象的任意字段，例如"uuid"
     *         loadChildHttpMethed 请求子节点数据的请求方式，默认 get,提供两种方式 HTTP_METHED.GET 和 HTTP_METHED.POST
     * @author lis 2017-12-07
     */
    this.setOtherConfig = function(_scope, otherConfig) {
        if (otherConfig) {
            _scope.huatekTree.otherConfig = otherConfig;
        }
    }
    this.setQueryPage = function(_scope, queryPage) {
        _scope.huatekTree.queryPage = queryPage;
        if (queryValues[_scope.huatekTree.menuId + "_page"]) {
            _scope.huatekTree.queryPage.page = queryValues[_scope.huatekTree.menuId + "_page"];
        }
        if (queryValues[_scope.huatekTree.menuId + "_pageSize"]) {
            _scope.huatekTree.queryPage.pageSize = queryValues[_scope.huatekTree.menuId + "_pageSize"];
        }
        _scope.huatekTree.queryFieldList = [];
        for (var m, i = 0; i < queryPage.queryParamList.length; i++) {
            if (queryPage.queryParamList[i].isShow) {
                _scope.huatekTree.queryFieldList.push(queryPage.queryParamList[i]);
            }
        }
        _scope.huatekTree.queryPageMap = getMap(queryPage.queryParamList, "field");
        for (var index in _scope.huatekTree.queryFieldList) {
            var field = _scope.huatekTree.queryFieldList[index];
            cacheService.bindFieldData(field);
        }
        for (var i = 0; i < _scope.huatekTree.queryPage.queryParamList.length; i++) {
            if (!_scope.huatekTree.queryPage.queryParamList[i].keepValue) {
                continue;
            }
            var queryValue;
            var key = _scope.huatekTree.menuId + "-" + _scope.huatekTree.queryPage.queryParamList[i].name + i;
            if (_scope.huatekTree.queryPage.queryParamList[i].type == 'dateTime') {
                var minValue = queryValues[key + "_m"];
                var maxValue = queryValues[key + "_x"];
                if (minValue != null && minValue != 'null' &&
                    minValue != '' && minValue != undefined && minValue != 'undefined') {
                    _scope.huatekTree.queryPage.queryParamList[i].minValue = minValue;
                }
                if (maxValue != null && maxValue != 'null' &&
                    maxValue != '' && maxValue != undefined && maxValue != 'undefined') {
                    _scope.huatekTree.queryPage.queryParamList[i].maxValue = maxValue;
                }
            } else {
                queryValue = queryValues[key];
                if (queryValue && queryValue != 'undefined' && queryValue != 'null') {
                    if (_scope.huatekTree.queryPage.queryParamList[i].logic == "in") {
                        /*解决页面查询条件复选框值回显问题*/
                        _scope.huatekTree.queryPage.queryParamList[i].params = queryValue.toString().split(",");
                    } else {
                        if (queryValue == 'true' || queryValue == 'false') {
                            _scope.huatekTree.queryPage.queryParamList[i].value = Boolean(queryValue);
                        } else {
                            _scope.huatekTree.queryPage.queryParamList[i].value = queryValue;
                        }
                    }
                }
            }
        }
        for (var i = 0; i < _scope.huatekTree.queryFieldList.length; i++) {
            _scope.huatekTree.queryFieldList[i].isShowSelect;
            if (i > 5) {
                _scope.huatekTree.queryFieldList[i].isShowSelect = false;
                _scope.huatekTree.showMoreBtn = true;
            } else {
                _scope.huatekTree.queryFieldList[i].isShowSelect = true;
                _scope.huatekTree.showMoreBtn = false;
            }
        }
    };

    var getSingleSelectModel = function(editable, isSingleCheckModel) {
        var editable = editable == undefined ? true : editable;
        var isSingleCheckModel = isSingleCheckModel == undefined ? false : isSingleCheckModel;
        if (editable) {
            return true;
        } else {
            if (isSingleCheckModel) {
                return true;
            } else {
                return false;
            }
        }
    };

    var createHeader = function(columnsArr) {
        var header = "<th width='35px'>" +
            "<input ng-model='huatekTree.all.checked' type='checkbox' ng-click='huatekTree.allChecked()'>" +
            "<label for='' class='icon-check'></label>" +
            "</th>";
        if (columnsArr && columnsArr.length > 0) {
            for (var i = 0; i < columnsArr.length; i++) {
                if (columnsArr[i].type == "treeColumn") {
                    header += "<th width=" + columnsArr[i].width + " title=" + columnsArr[i].name + ">";
                    header += columnsArr[i].name
                    header += "</th>";
                } else {
                    header += "<th colspan=" + columnsArr[i].colspan + " width='" + colspanWidth(i, columnsArr[i].colspan, columnsArr) + "' style='text-align: center;padding:0px;'>";
                    /*   header += "<table width='100%'>";
                       header += "<thead>";
                       header += "<th colspan=" + columnsArr[i].colspan + " style='text-align: center;'>";
                       header += columnsArr[i].name;
                       header += "</th>";
                       header += "</thead>";
                       header += "<tbody>";
                       header += "<tr>";*/
                    header += createInnerTable(columnsArr, i, columnsArr        [i].colspan);
                    /*header += "</tr>";
                    header += "</tbody>";
                    header += "</table>";*/
                    header += "</th>";
                    i = i + columnsArr[i].colspan;
                }
            }
        }
        header += "<th width='200px' ng-show='huatekTree.editable'>操作</th>";
        console.log(header);
        return header;
    }

    var createInnerTable = function(arr, index, count) {
        var header = "<table width='100%' >" +
            "<thead>" +
            "<th colspan='" + count + "' style='text-align:center;font-weight: inherit;border-bottom:1px solid #fff;'>" + arr[index].name + "</th>" +
            "</thead>" +
            "<tbody>" +
            "<tr class='ntabletd'>"
        for (var i = index + 1; i <= index + count; i++) {
            if (arr[i].type == "treeColumn") {
                header += "<td style='border:1px solid #fff;'>" + arr[i].name + "</td>";
            } else {
                createInnerTable(arr, i, arr[i].colspan);
                i = i + arr[i].colspan;
            }
        }
        header += "</tr>" +
            "</tbody>" +
            "</table>";
        return header;
    }

    var colspanWidth = function(index, colspan, arr) {
        var width = 0;
        if (arr && arr.length > 0) {
            for (var i = index; i < index + colspan; i++) {
                if (arr[i] && arr[i].type == "treeColumn") {
                    width += arr[i] * 1;
                }
            }
        }
        return width;
    }

    /**
     * 初始化树对象
     * @param  {[type]} _scope      controller的作用域
     * @param  {[type]} columnsArr  字段对象数组
     * @param  {[type]} topLevelUrl 顶级节点请求路径
     * @param  {[type]} childUrl    子节点请求路径
     * @param  {[type]} saveUrl     保存路径
     * @param  {[type]} editable    列表树是否可编辑
     * @return {[type]}             [description]
     * @author lis 2017-11-08
     */
    this.init = function(_scope, columnsArr, topLevelUrl, childUrl, saveUrl, editable, isSingleCheckModel, rowRenderFn) {
        _scope.resetSearch = function() {
            for (var i = 0; i < _scope.queryPage.queryParamList.length; i++) {
                var key = _scope.menuId + "-" + _scope.queryPage.queryParamList[i].name;
                delete queryValues[key];
                _scope.queryPage.queryParamList[i].value = null;
                _scope.queryPage.queryParamList[i].queryParam = null;
                if (_scope.queryPage.queryParamList[i].type == 'dateTime') {
                    delete queryValues[key+"_m"];
                    delete queryValues[key+"_x"];
                    _scope.queryPage.queryParamList[i].minValue = null;
                    _scope.queryPage.queryParamList[i].maxValue = null;
                }
                if (_scope.queryPage.queryParamList[i].type == 'number') {
                    console.log(_scope.queryPage.queryParamList[i]);
                }
            }
            delete queryValues[_scope.menuId + "_page"];
            delete queryValues[_scope.menuId + "_pageSize"];
        };
        _scope.huatekTree = {
            menuId: getMenuId(_scope),
            topLevelUrl: topLevelUrl,
            childUrl: childUrl,
            saveUrl: saveUrl,
            columns: initColumns(columnsArr || []),
            all: { launch: false, checked: false, topLevelCount: 0, topLevelOrder: 0 },
            modify: { btnShow: false, panelShow: false, offTop: 0, rowIndex: 0, row: {} },
            addtop: { isShow: false, row: {} },
            child: { isShow: false, orderNumber: 1, offTop: 0, rowIndex: -1, row: {}, parentIndex: -1 },
            beforeCheckedRow: null,
            rows: [],
            editable: editable == undefined ? true : editable,
            editMap: new Map(),
            updateIdMap: new Map(),
            nodeCheckedFn: null,
            /**节点选中回调*/
            saveCheckDataFn: null,
            /**节点保存回调*/
            beforeEditCallBackFn: null,
            beforeDeleteCallBackFn: null,
            /**打开编辑回调，用于解决复杂情况节点是否可编辑等配置*/
            otherConfig: new Object(),
            /*是否为单选模式 可编辑模式下默认是单选模式不可配置，非可编辑模式下可通过此参数控制是否单选模式*/
            isSingleCheckModel: getSingleSelectModel(editable, isSingleCheckModel),
            rowRenderFn: rowRenderFn,
            header: createHeader(columnsArr),
            uneditFieldMap:initUnEditFieldMap(columnsArr),
            /**{loadChildField:加载子节点时的url参数,loadChildHttpMethed:加载子节点的请求方式}**/
            lookChild: function(index, row, callBack) {
                /*展开或者收缩单个节点*/
                if (!row.launch) {
                    if (row.looking == true) {
                        for (var i = index + 1; i < this.rows.length; i++) {
                            if (this.rows[i].groupLevel <= row.groupLevel) {
                                break;
                            } else {
                                if (this.rows[i].groupLevel == row.groupLevel + 1) {
                                    this.rows[i].isShow = true;
                                }
                            }
                        }
                    } else {
                        this.loadChild(this, index, callBack);
                        row.looking = true;
                    }
                    row.launch = true;
                } else {
                    for (var i = index + 1; i < this.rows.length; i++) {
                        if (this.rows[i].groupLevel <= row.groupLevel) {
                            break;
                        } else {
                            this.rows[i].isShow = false;
                            if (this.rows[i].isLeaf == 0) {
                                this.rows[i].launch = false;
                            }
                        }
                    }
                    row.launch = false;
                }
            },
            allLaunch: function() {
                /*展开所有节点*/
                if (this.rows.length > 0) {
                    if (this.all.launch) {
                        this.all.launch = false;
                        for (var i = 0; i < this.rows.length; i++) {
                            if (this.rows[i].groupLevel <= 1) {
                                this.rows[i].launch = false;
                            } else {
                                this.rows[i].launch = false;
                                this.rows[i].isShow = false;
                            }
                        }
                    } else {
                        this.all.launch = true;
                        for (var i = 0; i < this.rows.length; i++) {
                            if (this.rows[i].groupLevel <= 1) {
                                this.rows[i].launch = true;
                            } else {
                                if (this.rows[i].looking) {
                                    this.rows[i].launch = false;
                                }
                                this.rows[i].isShow = true;
                            }
                        }
                    }
                }
            },
            allChecked: function() {
                /*选中所有节点*/
                if (this.rows.length > 0) {
                    if (this.all.checked) {
                        this.all.checked = false;
                        for (var i = 0; i < this.rows.length; i++) {
                            this.rows[i].checked = false;
                        }
                    } else {
                        this.all.checked = true;
                        for (var i = 0; i < this.rows.length; i++) {
                            this.rows[i].checked = true;
                        }
                    }
                }
            },
            checkedNode: function(row, index) {
                if (this.isSingleCheckModel) {
                    if (this.beforeCheckedRow) {
                        this.beforeCheckedRow.checked = false;
                    }
                }
                this.beforeCheckedRow = row;
                row.checked = true;
                this.childChecked(index, row);
            },
            rowClick: function(index, row) {
                /**行点击的时候选中或者不选中行*/
                /*if (this.isSingleCheckModel) {
                    if (this.beforeCheckedRow) {
                        this.beforeCheckedRow.checked = false;
                        this.beforeCheckedRow = row;
                    }
                }*/
                if (row.checked) {
                    row.checked = false;
                } else {
                    row.checked = true;
                }
                if (this.isSingleCheckModel) {
                    if (this.beforeCheckedRow) {
                        this.beforeCheckedRow.checked = false;
                        this.beforeCheckedRow = row;
                    } else {
                        this.beforeCheckedRow = row;
                    }
                } else {
                    this.beforeCheckedRow = row;
                }
                if (this.nodeCheckedFn && angular.isFunction(this.nodeCheckedFn)) {
                    this.nodeCheckedFn(row);
                }
                this.childChecked(index, row);
            },
            childChecked: function(index, row) {
                /*选中或者不选中子节点*/
                if (!this.editable && !this.isSingleCheckModel) {
                    if (row.checked) {
                        if (index + 1 != this.rows.length) {
                            for (var i = index + 1; i < this.rows.length; i++) {
                                if (this.rows[i].groupLevel <= row.groupLevel) {
                                    break;
                                } else {
                                    this.rows[i].checked = true;
                                }
                            }
                        }
                        /*this.beforeCheckedRow = row;*/
                        /*if (this.nodeCheckedFn && angular.isFunction(this.nodeCheckedFn)) {
                            this.nodeCheckedFn(row);
                        }*/
                    } else {
                        if (index + 1 != this.rows.length) {
                            for (var i = index + 1; i < this.rows.length; i++) {
                                if (this.rows[i].groupLevel <= row.groupLevel) {
                                    break;
                                } else {
                                    this.rows[i].checked = false;
                                }
                            }
                        }
                    }
                }
                /*else {
                    if (row.checked) {
                        if (this.beforeCheckedRow && this.beforeCheckedRow != row) {
                            this.beforeCheckedRow.checked = false;
                        }
                        this.beforeCheckedRow = row;
                        if (this.nodeCheckedFn && angular.isFunction(this.nodeCheckedFn)) {
                            this.nodeCheckedFn(row);
                        }
                    }
                }*/
            },
            addTopNode: function() {
                /*添加顶级节点*/
                this.addtop.row = {
                    "uuid": uuid(36),
                    "parentId": 0,
                    "groupLevel": 1,
                    "isLeaf": 1,
                    "launch": false,
                    "isShow": true,
                    "orderNumber": this.all.topLevelOrder + 1
                }
                this.addtop.row.uneditMap = cloneObj(this.uneditFieldMap);
                if (this.beforeEditCallBackFn && angular.isFunction(this.beforeEditCallBackFn)) {
                    this.beforeEditCallBackFn(ROW_OPERATION.ADD_TOP, this.addtop.row);
                    if (this.addtop.row.uneditMap == undefined) {
                        this.addtop.row.uneditMap = new Map();
                    }
                }
                this.addtop.isShow = true;
            },
            saveTopNode: function() {
                /*保存顶级节点*/
                if (this.saveCheckDataFn && angular.isFunction(this.saveCheckDataFn)) {
                    if (!this.saveCheckDataFn(this.addtop.row)) {
                        return;
                    }
                }
                this.addtop.isShow = false;
                var saveRow = cloneObj(this.addtop.row);
                this.checkedNode(saveRow);
                this.rows.push(saveRow);
                this.addToEditMap(this.addtop.row, "add");
                this.all.topLevelOrder = this.addtop.row.orderNumber;
                this.addtop.row = {};
            },
            cacelTopNode: function() {
                /*取消保存顶级节点*/
                this.addtop.isShow = false;
            },
            editNode: function($event, index, row) {
                /*编辑当前节点*/
                this.modify.rowIndex = index;
                this.modify.row = cloneObj(row);
                this.modify.row.uneditMap = cloneObj(this.uneditFieldMap);
                if (this.beforeEditCallBackFn && angular.isFunction(this.beforeEditCallBackFn)) {
                    this.beforeEditCallBackFn(ROW_OPERATION.MODIFY, this.modify.row);
                    if (this.modify.row.uneditMap == undefined) {
                        this.modify.row.uneditMap = new Map();
                    }
                }
                this.modify.panelShow = true;
                var thisOffTop = "";
                $(".table-top").each(function() {
                    if ($(this).height() > 0) {
                        thisOffTop = $(this).offset().top;
                    }
                })
                this.modify.offTop = $($event.target).parents("td").offset().top - thisOffTop;
            },
            saveNode: function() {
                /*保存当前节点*/
                var parent = null;
                if (this.modify.row.parentId != 0) {
                    for (var i = this.modify.rowIndex - 1; i >= 0; i--) {
                        if (this.rows[i].uuid == this.modify.row.parentId) {
                            parent = this.rows[i];
                            break;
                        }
                    }
                }
                if (this.saveCheckDataFn && angular.isFunction(this.saveCheckDataFn)) {
                    if (!this.saveCheckDataFn(this.modify.row, parent)) {
                        return;
                    }
                }
                this.modify.panelShow = false;
                this.modify.offTop = 0;
                this.rows[this.modify.rowIndex] = cloneObj(this.modify.row);
                this.checkedNode(this.rows[this.modify.rowIndex]);
                this.addToEditMap(cloneObj(this.modify.row), "update");
                this.modify.rowIndex = -1;
                this.modify.row = {};
            },
            cancelNode: function() {
                /*取消保存当前节点*/
                this.modify.panelShow = false;
                this.modify.offTop = 0;
                this.modify.row = {};
            },
            deleteNode: function(index, row) {
                if (this.beforeDeleteCallBackFn && angular.isFunction(this.beforeDeleteCallBackFn)) {
                    if (!this.beforeDeleteCallBackFn(row)) {
                        return;
                    }
                }
                /*删除当前节点*/
                if (row.isLeaf == 1) {
                    /*先判断当前节点是否有父节点，如果有，则判断当前父节点的子节点个数，如果只有当前一个子节点，则将父节点的isLeaf设置成true*/
                    if (row.parentId != "0") {
                        if (this.rows.length == index + 1) {
                            if (index != 0) {
                                if (this.rows[index - 1].parentId != row.parentId && index != 0) {
                                    this.rows[index - 1].isLeaf = 1;
                                    /*将节点放到编辑map中*/
                                    this.addToEditMap(this.rows[index - 1], "update");
                                }
                            }
                        } else {
                            if (this.rows[index - 1].parentId != row.parentId && this.rows[index + 1].parentId != row.parentId) {
                                this.rows[index - 1].isLeaf = 1;
                                this.addToEditMap(this.rows[index - 1], "update");
                            }
                        }
                    }
                    /*将节点放到编辑map中*/
                    this.addToEditMap(row, "delete");
                    /*删除当前节点*/
                    this.rows.splice(index, 1);
                } else {
                    submitTips('警告：当前节点有子节点，不能删除。', 'warning');
                    return;
                }
            },
            moveNode: function(index, row, moveModel) {
                this.checkedNode(row);
                /*节点的上移和下移*/
                var rowIndex = index,
                    beforeRowIndex = -1,
                    afterSameLevelIndex = -1,
                    afterRowBeginIndex = -1,
                    afterRowEndIndex = -1;
                for (var i = index - 1; i >= 0; i--) {
                    if (this.rows[i].groupLevel == row.groupLevel) {
                        beforeRowIndex = i;
                        break;
                    }
                    if (this.rows[i].groupLevel < row.groupLevel) {
                        break;
                    }
                }
                for (var j = index + 1; j < this.rows.length; j++) {
                    if (this.rows[j].groupLevel == row.groupLevel && row.parentId == this.rows[j].parentId) {
                        afterSameLevelIndex = j;
                    }
                    if (this.rows[j].groupLevel == row.groupLevel) {
                        if (afterRowBeginIndex == -1) {
                            afterRowBeginIndex = j;
                            continue;
                        }
                    }
                    if (this.rows[j].groupLevel <= row.groupLevel) {
                        if (afterRowBeginIndex != -1) {
                            afterRowEndIndex = j;
                        } else {
                            afterRowBeginIndex = j;
                            afterRowEndIndex = j;
                        }
                        break;
                    }
                }
                if (beforeRowIndex == -1 && moveModel == TREE_NODE_UP) {
                    submitTips('警告：当前节点不能上移。', 'warning');
                    return;
                } else if (afterSameLevelIndex == -1 && moveModel == TREE_NODE_DOWN) {
                    submitTips('警告：当前节点不能下移。', 'warning');
                    return;
                }
                if (moveModel == TREE_NODE_UP) {
                    /*更新节点的orderNumber*/
                    var beforeRow = this.rows[beforeRowIndex];
                    var beforeOrder = beforeRow.orderNumber;
                    beforeRow.orderNumber = row.orderNumber;
                    row.orderNumber = beforeOrder;
                    this.addToEditMap(beforeRow, "update");
                    this.addToEditMap(row, "update");
                    /*重新构造树的rows数组*/
                    var firstArr = this.rows.slice(0, beforeRowIndex),
                        secondArr = this.rows.slice(beforeRowIndex, rowIndex),
                        thirdArr = afterRowBeginIndex == -1 ? this.rows.slice(rowIndex) : this.rows.slice(rowIndex, afterRowBeginIndex),
                        fourthArr = afterRowBeginIndex == -1 ? [] : this.rows.slice(afterRowBeginIndex);
                    this.rows = firstArr.concat(thirdArr, secondArr, fourthArr);
                } else {
                    /*更新节点的orderNumber*/
                    var afterRow = this.rows[afterRowBeginIndex];
                    var afterOrder = afterRow.orderNumber;
                    afterRow.orderNumber = row.orderNumber;
                    row.orderNumber = afterOrder;
                    this.addToEditMap(afterRow, "update");
                    this.addToEditMap(row, "update");
                    /*重新构造树的rows数组*/
                    var firstArr = this.rows.slice(0, rowIndex),
                        secondArr = this.rows.slice(rowIndex, afterRowBeginIndex),
                        thirdArr = afterRowEndIndex == -1 ? this.rows.slice(afterRowBeginIndex) : this.rows.slice(afterRowBeginIndex, afterRowEndIndex),
                        fourthArr = afterRowEndIndex == -1 ? [] : this.rows.slice(afterRowEndIndex);
                    this.rows = firstArr.concat(thirdArr, secondArr, fourthArr);
                }
            },
            addChild: function($event, index, row) {
                /*给当前节点添加子节点*/
                var thisOffTop = "";
                $(".table-top").each(function() {
                    if ($(this).height() > 0) {
                        thisOffTop = $(this).offset().top;
                    }
                });
                this.child.offTop = $($event.target).parents("td").offset().top - thisOffTop + $($event.target).parents("td").height();
                if (row.isLeaf == 0 && row.launch == false) {
                    this.lookChild(index, row, this.createChildNode);
                } else {
                    this.createChildNode(this, index, row);
                }

            },
            createChildNode: function(treeGrid, index) {
                treeGrid.child.rowIndex = index;
                if (index + 1 != treeGrid.rows.length) {
                    var j = 0;
                    for (var i = index + 1; i < treeGrid.rows.length; i++) {
                        if (treeGrid.rows[i].groupLevel == treeGrid.rows[index].groupLevel + 1) {
                            treeGrid.child.orderNumber = treeGrid.rows[i].orderNumber + 1;
                        }
                        if (treeGrid.rows[i].groupLevel <= treeGrid.rows[index].groupLevel) {
                            break;
                        }
                        j += 1;
                    }
                    treeGrid.child.rowIndex = index + j + 1;
                } else {
                    treeGrid.child.rowIndex += 1;
                }
                treeGrid.child.row = {
                    "uuid": uuid(36),
                    "parentId": treeGrid.rows[index].uuid,
                    "groupLevel": treeGrid.rows[index].groupLevel + 1,
                    "isLeaf": 1,
                    "launch": false,
                    "isShow": true,
                    "orderNumber": treeGrid.child.orderNumber
                };
                treeGrid.child.parentIndex = index;
                treeGrid.child.row.uneditMap = cloneObj(treeGrid.uneditFieldMap);
                if (this.beforeEditCallBackFn && angular.isFunction(this.beforeEditCallBackFn)) {
                    this.beforeEditCallBackFn(ROW_OPERATION.ADD_CHILD, treeGrid.child.row, treeGrid.rows[index]);
                    if (treeGrid.child.row.uneditMap == undefined) {
                        treeGrid.child.row.uneditMap = new Map();
                    }
                }
                treeGrid.child.isShow = true;
            },
            saveChild: function() {
                /*保存当前添加的子节点*/
                var parent = this.rows[this.child.parentIndex];
                if (this.saveCheckDataFn && angular.isFunction(this.saveCheckDataFn)) {
                    if (!this.saveCheckDataFn(this.child.row, parent)) {
                        return;
                    }
                }
                if (parent.isLeaf == 1) {
                    parent.isLeaf = 0;
                    parent.launch = true;
                    parent.looking = true;
                    this.addToEditMap(parent, "update");
                }
                this.addToEditMap(this.child.row, "add");
                var saveRow = cloneObj(this.child.row);
                this.checkedNode(saveRow);
                this.rows.insert(this.child.rowIndex, saveRow);
                this.cancelChild();
            },
            cancelChild: function() {
                /*取消保存当前添加的子节点*/
                this.child.isShow = false;
                this.child.rowIndex = -1;
                this.child.orderNumber = -1;
                this.child.parentIndex = -1;
                this.child.row = {};
            },
            addToEditMap: function(row, operation) {
                /*将操作过的节点放放到map中*/
                var obj = cloneObj(row);
                if (row.id) {
                    if (operation == "add") {
                        obj.operation = "update";
                    } else if (operation == "update") {
                        obj.operation = "update";
                    } else if (operation == "delete") {
                        obj.operation = "delete"
                    }
                } else {
                    if (operation == "add") {
                        obj.operation = "add";
                    } else if (operation == "update") {
                        obj.operation = "add";
                    } else if (operation == "delete") {
                        obj.operation = "noIdDelete";
                    }
                }
                if (operation == "add") {
                    this.updateIdMap.put(row);
                }
                if (obj.operation == "noIdDelete") {
                    if (this.editMap.get(obj.uuid)) {
                        this.editMap.remove(obj.uuid);
                    }
                } else {
                    if (this.editMap.get(obj.uuid)) {
                        this.editMap.remove(obj.uuid);
                        this.editMap.put(obj.uuid, obj);
                    } else {
                        this.editMap.put(obj.uuid, obj);
                    }
                }
            },
            getTreeShape: function(index, groupLevel) {
                /*节点树状样式*/
                var styleStr = "";
                if (index == 0 || index == '0') {
                    switch (groupLevel) {
                        case 1:
                            styleStr = "";
                            break;
                        case 2:
                            styleStr = "margin-left:20px";
                            break;
                        case 3:
                            styleStr = "margin-left:40px";
                            break;
                        case 4:
                            styleStr = "margin-left:60px";
                            break;
                        default:
                            styleStr = "";
                            break;
                    }
                } else {
                    styleStr = "";
                }
                return styleStr;
            },
            loadData: function(queryPage) {
                var huatekTree = this;
                httpService.postOutOfScope(this.menuId, this.topLevelUrl, copyQueryPage(queryPage)).success(function(res) {
                    var loopFlag = false;
                    if (res && huatekTree.otherConfig && huatekTree.otherConfig.checkRowMap && huatekTree.otherConfig.checkRowKey) {
                        loopFlag = true;
                    }
                    for (var i = 0; i < res.length; i++) {
                        if (huatekTree.rowRenderFn && angular.isFunction(huatekTree.rowRenderFn)) {
                            huatekTree.rowRenderFn(res[i]);
                        }
                        if (loopFlag && huatekTree.otherConfig.checkRowMap.get(res[i][huatekTree.otherConfig.checkRowKey])) {
                            res[i].checked = true;
                        }
                        if (res[i].groupLevel == 1 && res[i].orderNumber > huatekTree.all.topLevelOrder) {
                            huatekTree.all.topLevelOrder = res[i].orderNumber;
                        }
                    }
                    huatekTree.rows = res;
                    huatekTree.all.topLevelCount = res.length;
                    huatekTree.editMap = new Map();
                }).error(function() {
                    submitTips('查询数据出错', 'error');
                });
            },
            loadChild: function(huatekTree, index, callBack) {
                var passParam = this.rows[index].id;
                if (this.otherConfig.loadChildField) {
                    passParam = this.rows[index][this.otherConfig.loadChildField]
                }
                if (this.otherConfig.loadChildHttpMethed == HTTP_METHED.GET) {
                    httpService.getOutOfScope(this.menuId, this.childUrl + passParam).success(function(res) {
                        if ((res && huatekTree.otherConfig && huatekTree.otherConfig.checkRowMap && huatekTree.otherConfig.checkRowKey) || (huatekTree.rowRenderFn && angular.isFunction(huatekTree.rowRenderFn))) {
                            for (var i = 0; i < res.length; i++) {
                                if (huatekTree.rowRenderFn && angular.isFunction(huatekTree.rowRenderFn)) {
                                    huatekTree.rowRenderFn(res[i]);
                                }
                                if (huatekTree.otherConfig.checkRowMap && huatekTree.otherConfig.checkRowMap.get(res[i][huatekTree.otherConfig.checkRowKey])) {
                                    res[i].checked = true;
                                }
                            }
                        }
                        if (huatekTree.rows.length == index + 1) {
                            huatekTree.rows = huatekTree.rows.concat(res);
                        } else {
                            var first = [],
                                second = [],
                                third = [];
                            first = huatekTree.rows.slice(0, index + 1);
                            second = res;
                            third = huatekTree.rows.slice(index + 1);
                            huatekTree.rows = first.concat(second.concat(third));
                        }
                        if (angular.isFunction(callBack)) {
                            callBack(huatekTree, index);
                        }
                    })
                } else {
                    httpService.postOutOfScope(this.menuId, this.childUrl + passParam, this.queryPage).success(function(res) {
                        if ((res && huatekTree.otherConfig && huatekTree.otherConfig.checkRowMap && huatekTree.otherConfig.checkRowKey) || (huatekTree.rowRenderFn && angular.isFunction(huatekTree.rowRenderFn))) {
                            for (var i = 0; i < res.length; i++) {
                                if (huatekTree.rowRenderFn && angular.isFunction(huatekTree.rowRenderFn)) {
                                    huatekTree.rowRenderFn(res[i]);
                                }
                                if (huatekTree.otherConfig.checkRowMap && huatekTree.otherConfig.checkRowMap.get(res[i][huatekTree.otherConfig.checkRowKey])) {
                                    res[i].checked = true;
                                }
                            }
                        }
                        if (huatekTree.rows.length == index + 1) {
                            huatekTree.rows = huatekTree.rows.concat(res);
                        } else {
                            var first = [],
                                second = [],
                                third = [];
                            first = huatekTree.rows.slice(0, index + 1);
                            second = res;
                            third = huatekTree.rows.slice(index + 1);
                            huatekTree.rows = first.concat(second.concat(third));
                        }
                        if (angular.isFunction(callBack)) {
                            callBack(huatekTree, index);
                        }
                    })
                }

            },
            saveAll: function(saveDataUrl) {
                var huatekTree = this;
                var dataMap = huatekTree.editMap;
                var updateMap = huatekTree.updateIdMap;
                var submitArr = dataMap.toArray();
                var postSaveUrl = huatekTree.saveUrl;
                if (cnex4_isNotEmpty_str(saveDataUrl)) {
                    postSaveUrl = saveDataUrl;
                }
                if (submitArr && submitArr.length > 0) {
                    httpService.postOutOfScope(this.menuId, postSaveUrl, this.editMap.toArray()).success(function(res) {
                        if (res && res.length > 0) {
                            for (var i = 0; i < res.length; i++) {
                                if (updateMap.get(res[i].uuid)) {
                                    updateMap.get(res[i].uuid).id = res[i].id;
                                }
                            }
                        }
                        huatekTree.editMap = new Map();
                        huatekTree.updateIdMap = new Map();
                        submitTips('保存成功！', 'success');
                    });
                } else {
                    submitTips('警告：未做更改！', 'warning');
                }
            },
            getAllCheckedNode: function() {
                var checkedArr = [];
                for (var i = 0; i < this.rows.length; i++) {
                    if (this.rows[i].checked) {
                        checkedArr.push(this.rows[i]);
                    }
                }
                return checkedArr;
            },
            getAllCheckedLeafNode: function() {
                var checkedArr = [];
                for (var i = 0; i < this.rows.length; i++) {
                    if (this.rows[i].checked && this.rows[i].isLeaf) {
                        checkedArr.push(this.rows[i]);
                    }
                }
                return checkedArr;
            },
            getItemName: function(value, otherConfig) {
                var itemName = value;
                if (value && otherConfig && otherConfig.items && otherConfig.items.length > 0) {
                    for (var i = 0; i < otherConfig.items.length; i++) {
                        if (otherConfig.items[i].code == value) {
                            itemName = otherConfig.items[i].name;
                            break;
                        }
                    }
                }
                return itemName;
            }
        }
    };
});