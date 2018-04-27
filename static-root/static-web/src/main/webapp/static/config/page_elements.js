/***
 * 查询条件对象.
 * @type={string, long, double, date, dateTime}
 * @logic={'=','>','<',">=","<=","like","in"}
 */
function queryParam(title, field, type, logic, value, dropDataUrl, isShow, event, display) {
    this.title = title;
    this.name = this.field = field;
    this.type = type;
    this.componentType = null; /*组件类型，默认不指定，如果需要使用单选的时候指定一下*/
    if (typeof(isvalid) == "undefined") {
        this.isvalid = true;
    } else {
        this.isvalid = isvalid;
    }
    if (!type) {
        this.type = 'string';
    }
    this.logic = logic;
    if (!logic || logic == undefined) {
        this.logic = '=';
    }
    this.value = value;
    this.items = [];
    this.setitem = function(itemsArray) {
        this.items = itemsArray;
    }
    this.dropDataUrl = dropDataUrl;
    if (typeof(isShow) == "undefined") {
        this.isShow = true;
    } else {
        this.isShow = isShow;
    }
    this.event = event;
    this.display = display;
    this.componentType = null;
    this.useCase = 'query';
    /*定义下拉的显示字段和返回字段*/
    this.showName = 'name';
    this.returnValue = 'code';
    this.returnField = null;
    this.showField = null;
    this.dateFormat = 'yyyy-MM-dd';
    this.defaultTimeValue = '00:00:00';
    this.ObjectType = 'queryParam';
    this.keepValue = true;
    this.mapField = "id";
    this.selectCallBack = function(item) {
        if (this.mapField && item[this.mapField]) {
            this.value = item[this.mapField];
        } else {
            this.value = item.id;
        }

    }
}

function param(field, type, logic, value, items) {
    this.field = field;
    this.type = type;
    this.logic = logic;
    this.value = value;
    if (null == items) {
        this.items = [];
    } else {
        this.items = items;
    }
}

function copyQueryPage(queryPage) {
    var newQueryPage = new QueryPage(queryPage.page, queryPage.pageSize, queryPage.orderBy, queryPage.isExport);
    if (queryPage.queryParamList && queryPage.queryParamList.length > 0) {
        var params = [];
        for (var i = 0; i < queryPage.queryParamList.length; i++) {
            params[i] = copyQueryParam(queryPage.queryParamList[i]);
        }
        newQueryPage.queryParamList = params;
    }
    return newQueryPage;
}

function copyQueryParam(queryParam) {
    /*处理日期时间类型*/
    if (queryParam.type == 'dateTime') {
        if (cnex4_isEmpty_str(queryParam.minValue) && queryParam.minValue.indexOf(':') < 0) {
            var timeValue = queryParam.maxValue || queryParam.defaultTimeValue;
            queryParam.value = queryParam.minValue + " " + timeValue;
        } else {
            queryParam.value = null;
        }
    }
/*    if (queryParam.componentType == SEARCH_COMPONENT.TENDERS_MULTIPLE_SLECT) {
        queryParam.items = queryParam.value;
        queryParam.value = null;
    }*/
    if (queryParam.logic == "notHandle") {
        queryParam.value = null;
    }
    /*处理带操作符的数字类型*/
    if (queryParam.type == 'number' && queryParam.componentType == 'operatorNumber') {
        if (queryParam.value !== null || queryParam.value !== '') {
            queryParam.logic = queryParam.operator;
        }
    }
    return new param(queryParam.field, queryParam.type, queryParam.logic, queryParam.value, queryParam.params);
}

/***
 * 页面按钮对象.
 * @title button 显示名字.
 * @name button名字
 * @show={true,false}  {显示,不显示}
 * @even click事件
 * @url 后台记录的访问路劲
 */
function pageButton(title, name, url, event, target) {
    var pageButton = new Object();
    pageButton.title = title;
    pageButton.name = name;
    pageButton.event = event;
    pageButton.show = actionMap.get(url) != null;
    pageButton.target = target;
    pageButton.isDisabled = false;
    return pageButton;
}


/***
 * 自定义按钮
 * @title button 显示名字.
 * @name button名字
 * @show={true,false}  {显示,不显示}
 * @even click事件
 * @url 后台记录的访问路劲
 * @dataAnimation 动画样式
 * @dataTemplateUrl 模板路径
 * @bsModal 类型
 */
function customButton(title, name, show, event, dataAnimation, dataTemplateUrl, bsModal, target) {
    var customButton = new Object();
    customButton.title = title;
    customButton.name = name;
    customButton.event = event;
    customButton.show = show;
    customButton.target = target;
    /*扩展modal button*/
    customButton.dataAnimation = dataAnimation;
    customButton.dataTemplateUrl = dataTemplateUrl;
    customButton.bsModal = bsModal;
    return customButton;
}


/**
 *  页面分页组件. 
 * @page button 显示名字.
 * @pageSize button名字
 * @show={1,-1}  {显示,不显示}
 * @even click事件
 * @url 后台记录的访问路劲
 */
function QueryPage(page, pageSize, orderBy, isExport) {
    var queryPage = new Object;
    queryPage.page = page;
    queryPage.pageSize = pageSize;
    queryPage.orderBy = orderBy;
    queryPage.export = isExport;
    queryPage.queryParamList = [];
    queryPage.addParam = function(param) {
        queryPage.queryParamList.push(param);
    }
    return queryPage;
}
/***
 * 表格字段类.
 * @title 字段描述
 * @name 字段名
 */
function TableField(title, name) {
    this.title = title;
    this.name = name;
}

/***
 * 
 * @param title 显示名称
 * @param name 字段名
 * @param type 字段类型
 * @param require 是否必填
 * @param max 最大长度
 * @param model 显示类型
 * @param event 事件
 * @param min 最小长度
 */
function FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly) {
    this.column = column;
    this.title = title;
    this.name = name;
    this.type = type;
    /**
     * 目前设置下面几种类型的数据:
     * email、phone、number
     */
    this.require = require;
    this.max = max;
    this.model = model;
    this.event = event;
    if (null == min) {
        this.min = '0';
    } else {
        this.min = min;
    }
    this.css = "";
    this.items = [];
    this.value = defaultValue;
    /***
     * 针对模糊查询数据回填显示困难的问题，
     * 提供一个显示展示数据的字段.
     * 该字段在服务器端以如下格式传递：
     * fieldName_=displayValue
     * 和真实值相差一个下划线.
     */
    this.displayValue;
    this.dropDataUrl = dropDataUrl;
    this.showText = "";
    this.errorMsg = "";
    this.readonly = readonly ? true : false;
    this.uploadFileDir = ""; /*如果为文件字段，指定上传的目录，按照模块划分*/
    this.businessId = ""; /*业务的id*/
    this.viewDivId = ""; /*图片上传后用于显示图片的div id*/
    this.busiType = ""; /*业务类型 id*/
    /*date 和  time 类型默认值*/
    this.dateFormat = "yyyy-MM-dd"; /*日期格式*/
    this.autoclose = "1"; /*是否自动关闭 boolean*/
    this.dateType = "string"; /*预期类型  date | number | unix | iso | string*/
    this.minDate = "01/01/1900"; /*最小时间*/
    this.maxDate = "31/12/2050"; /*最大时间*/
    this.placeholder = ""; /*占位符  起始时间和结束时间*/
    this.timeType = "string";
    this.timeFormat = "HH:mm:ss";
    this.minTime = "00:00:00"; /*最大时间*/
    this.maxTime = "23:59:59"; /*最小时间*/
    /*时间日期区间*/
    this.endTitle = ""; /*结束字段的名称*/
    this.minTimeValue = ""; /*日期时间区间的开始时间*/
    this.maxTimeValue = ""; /*日期时间区间的结束时间*/
    this.minCss = null;
    this.maxCss = null;
    this.minTimeCss = null;
    this.maxTimeCss = null;
    this.minErrorMsg = null;
    this.maxErrorMsg = null;
    this.minTimeErrorMsg = null;
    this.maxTimeErrorMsg = null;
    this.isShow = true; /*字段是否显示*/
    this.useCase = null;
    this.allowDecimal = true;
    /*下拉的默认配显示字段和返回字段*/
    this.showName = 'name';
    /*if(model == 'select'){*/
    this.returnValue = 'code';
    /*}else{
        this.returnValue = null;
    }*/

    this.returnField = null;
    this.showField = null;
    this.filesCount = 0;
    /*数字组件专用*/
    this.decimals = 4;
    this.maxValue = 99999999999999.9999;
    this.minValue = -99999999999999.9999;
    this.ObjectType = 'FormElement';
    this.mapField = "id";
    this.selectCallBack = function(item) {
        if (this.mapField && item[this.mapField]) {
            this.value = item[this.mapField];
        } else {
            this.value = item.id;
        }

    }
}

/*页面tab对象*/
var tabObject = function(title, controller, templateUrl, mapKey) {
    var tab = {};
    tab.title = title;
    tab.active = 'active';
    tab.panelShow = true;
    tab.controller = controller;
    tab.templateUrl = templateUrl;
    tab.mapKey = mapKey;
    tab.panels = [];
    return tab;
};
/**
 * [弹出框对象]
 * @param  {[string]} id          [ID]
 * @param  {[string]} title       [标题]
 * @param  {[object]} context     [内容]
 * @param  {[function]} onclosedFun [关闭弹出框后的回调]
 * @param  {[string]} width       [宽度]
 * @param  {[string]} height      [高度]
 * @return {[object]}             [弹出框对象]
 * @author lis 2017-10-22
 */
/**
 * [弹出框对象]
 * @param  {[string]} title       [标题]
 * @param  {[string]} menuKey     [菜单唯一标识]
 * @param  {[string or object]} passParams  [传递参数]
 * @param  {[string]} width       [弹出框宽度]
 * @param  {[string]} height      [弹出框高度]
 * @param  {[function]} onclosedFun [弹出框关闭事件]
 * @param  {[string]} controller [弹出框controller]
 *  @param  {[string]} templateView [弹出框templateView]
 * @return {[object]}             [弹出框对象]
 * @author lis 2017-10-22
 */
var popup = function(title, menuKey, passParams, width, height, onclosedFun, controller, templateView, paneltype) {
    var popupObj = {};
    popupObj.title = title;
    popupObj.menuKey = menuKey;
    popupObj.passParams = passParams;
    popupObj.onclosedFun = onclosedFun;
    popupObj.width = width;
    popupObj.height = height;
    popupObj.controller = controller;
    popupObj.templateView = templateView;
    popupObj.paneltype = paneltype;
    return popupObj;
};
/***
 * 
 * @param title 显示名称
 * @param name 字段名
 * @param type 字段类型
 * @param require 是否必填
 * @param max 最大长度
 * @param model 显示类型
 * @param isEdit 是否可编辑,true:不可编辑,false:可编辑
 * @param isShow 是否显示,true:显示,false:不显示
 * @param event 事件
 * @param min 最小长度
 */
function FormElement1(title, name, type, require, max, model, isEdit, isShow, event, min, defaultValue, dropDataUrl) {
    this.title = title;
    this.name = name;
    this.type = type;
    this.require = require;
    this.max = max;
    this.model = model;
    this.isEdit = isEdit;
    this.isShow = isShow;
    this.event = event;
    this.min = min;
    this.items = [];
    this.value = defaultValue;
    this.dropDataUrl = dropDataUrl;
    this.showText = "";
}

/**
 * @param wrapTitle 栏目名称
 */
function multipleColumn(id, wrapTitle) {
    this.id = id;
    this.wrapTitle = wrapTitle;
}

/**
 * @param isShow 该栏目是否显示
 */
function multipleColumn1(id, wrapTitle, isShow) {
    this.id = id;
    this.wrapTitle = wrapTitle;
    this.isShow = isShow;
}


function pageButton1(title, content, controller, template) {
    var pageButton1 = new Object();
    pageButton1.title = title;
    pageButton1.content = content;
    pageButton1.controller = controller;
    pageButton1.template = template;
    return pageButton1;

}
/***
 * 本对象用于存储form表单字段formField的items的元素值.
 * @param name 选择项显示名.
 * @param code 选择项的值.
 */
function selectItem(name, code) {
    this.name = name;
    this.code = code;
}

/**
 * 错误信息
 * @param title
 * @param msg
 */
function errorMsgItem(title, msg) {
    this.title = title;
    this.msg = msg;
}
/**
 * treeGrid 列对象
 * @param  {[type]} name        [description]
 * @param  {[type]} field       [description]
 * @param  {[type]} width       [description]
 * @param  {[type]} editable    [description]
 * @param  {[type]} otherConfig [description]
 * @return {[type]}             [description]
 * @author lis 2017-11-16
 */
var treeColumn = function(name, field, width, editable, otherConfig,max) {
    var column = new Object();
    column.name = name;
    column.field = field;
    column.width = width;
    column.max = max || 100;
    column.editable = editable == undefined ? true : editable;
    column.otherConfig = otherConfig;
    column.type = "treeColumn";
    return column;
}

var treeColumnSpan = function(name,colspan){
    var columnSpan = new Object();
    columnSpan.name = name;
    columnSpan.colspan = colspan;
    columnSpan.type = "treeColumnSpan";
    return columnSpan;
}
/**
 * treeGrid其他配置
 * @param  {[type]} tag     [description]
 * @param  {[type]} dicName [description]
 * @param  {[type]} eventFn [description]
 * @param  {[type]} showFn  [description]
 * @return {[type]}         [description]
 * @author lis 2017-11-16
 */
var otherConfig = function(tag, dicName, eventFn, showFn) {
    var other = new Object();
    other.tag = tag;
    other.dicName = dicName;
    other.items = [];
    other.eventFn = eventFn;
    other.showFn = showFn || function() {
        return true;
    }
    return other;
}
var selectConfig = function(dicName, eventFn, showFn) {
    var other = new Object();
    other.tag = 'select';
    other.dicName = dicName;
    other.items = [];
    other.eventFn = eventFn;
    other.showFn = showFn || function() {
        return true;
    }
    return other;
}

var numberConfig = function(minValue, maxValue, decimals) {
    var other = new Object();
    other.tag = 'number';
    other.minValue = minValue || -99999999999999.9999;
    other.maxValue = maxValue || 99999999999999.9999;
    other.decimals = decimals || 4;
    return other;
}

var lookDetailBtn = function(panelName, width) {
    return { name: panelName || '查看', field: '', width: width || '5%', enableColumnMenu: false, cellTemplate: "<a style='text-align:center'><span style='color:#F09500;cursor:pointer;' ng-click=grid.appScope.$lookDetail(row)>查看</span></a>" }
}