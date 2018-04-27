/*文本框*/
var reportText = function(title,name,isRquire){
	this.title = title;
	this.name = name;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportText';
};

/*数字框*/
var reportNumber = function(title,name,isRquire,minValue,maxValue,decimals){
	this.title = title;
	this.name = name;
	this.minValue = minValue || -999999999;
	this.maxValue = maxValue || 999999999;
	this.decimals = decimals || 2;
	/*this.value = null;*/
	this.isRquire = isRquire;
	this.type = 'reportNumber';
};

/*带操作符的数字框*/
var reportOperatorNumber = function(title,name,isRquire,minValue,maxValue,decimals){
	this.title = title;
	this.name = name;
	this.minValue = minValue || -999999999;
	this.maxValue = maxValue || 999999999;
	this.decimals = decimals || 2;
	/*this.value = null;*/
	this.isRquire = isRquire;
	this.operator = "=";
	this.operatorItems = [{name:"等于",id:"="},{name:"大于",id:">"},{name:"小于",id:"<"},{name:"大于等于",id:">="},{name:"小于等于",id:"<="}];
	this.type = 'reportOperatorNumber';
}

/*单选*/
var reportSelect = function(title,name,items,isRquire){
	this.title = title;
	this.name = name;
	this.items = items;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportSelect';
};

/*动态单选*/
var reportDynamicSelect = function(title,name,isRquire){
	this.title = title;
	this.name = name;
	this.items = null;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportDynamicSelect';
};

/*报表切换单选*/
var reportChangeSelect = function(title,name,items,isRquire){
	this.title = title;
	this.name = name;
	this.items = items;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportChangeSelect';
};

/*多选*/
var reportMultipleSelect = function(title,name,items,isRquire){
	this.title = title;
	this.name = name;
	this.items = items;
	this.params = null;
	this.isRquire = isRquire;
	this.type = 'reportMultipleSelect';
};

/*日期时间*/
var reportDateTime = function(title,name,defaultTime,isRquire,minValue){
	this.title = title;
	this.name = name;
	this.minValue = minValue;
	this.maxValue = defaultTime || '00:00:00';
	this.isRquire = isRquire;
	this.type = 'reportDateTime';
};

/*核算加独立(过权限)*/
var reportCompany = function(title,name,office,isRquire){
	this.title = title;
	this.name = name;
	this.office = office;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportCompany';
};

/*办事处(过权限)*/
var reportOffice = function(title,name,isRquire){
	this.title = title;
	this.name = name;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportOffice';
};

/*核算加独立(不过权限)*/
var reportCompanyForNoAuthority = function(title,name,office,isRquire){
	this.title = title;
	this.name = name;
	this.office = office;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportCompanyForNoAuthority';
};

/*办事处(不过权限)*/
var reportOfficeForNoAuthority = function(title,name,isRquire){
	this.title = title;
	this.name = name;
	this.value = null;
	this.isRquire = isRquire;
	this.type = 'reportOfficeForNoAuthority';
};