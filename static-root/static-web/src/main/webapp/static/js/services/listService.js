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
                            exportChoosedToExcel(tableGrid, gridApi);
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