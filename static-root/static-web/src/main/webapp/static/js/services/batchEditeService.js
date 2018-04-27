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