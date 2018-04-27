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