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