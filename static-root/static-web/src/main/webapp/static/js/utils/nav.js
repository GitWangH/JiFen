/**
* build a Menu from JSON data.
* json data format:
*
*	var data = [
{
"title": "Chocolate Beverage",
"label":"sytem management",
"cssClass":"md md-computer",
"url":"#/home",
"isMenu":"1",
"level":"1",
"id": "1",
"parentid": "-1"
}, 
{..}];
*
*/

var menuMap = new Map();
var actionMap = new Map();
/**
 * 组装left菜单数据
 */
var buildLeftdata = function (data) {
    var source = [];
    var items = [];
    for (i = 0; i < data.length; i++) {
    	console.log(data[i]);
        var item = data[i];
        var label = item.label;
		var title = item.title;
		var url = item.url;
		var isMenu = item.isMenu;
		var isShow = item.isShow;
		var level = item.level;
		var cssClass ;
		if(item.cssClass!=null){
			cssClass = item.cssClass;
		}
		var parentid = item.parentid;
        var id = item.id;
        var path = item.path;
        actionMap.put(url, item);
		if(isMenu<0){
			continue;
		}
        if (parentid) {
            if(menuMap.get(parentid)){
           
            	var item = {id: id,  parentid: parentid, label: label, item: item, title: title, cssClass: cssClass, url: url, level:level, isMenu: isMenu, path:path, isShow: isShow};
                if(!menuMap.get(parentid).items){
                	menuMap.get(parentid).items = [];
                }
                menuMap.get(parentid).items[menuMap.get(parentid).items.length] = item;
                items[items.length-1] = item;
                menuMap.put(id, item);
            	
            }
        }
        else {
        
            items[items.length-1] = { id: id, parentid: parentid, label: label, item: item, title: title, cssClass: cssClass, url: url, level:level, isMenu: isMenu,  path:path, isShow: isShow};
            source[source.length] = items[items.length-1];
            menuMap.put(id, items[items.length-1]);
        	
        }
    }
    return source;
}

var buildLeftUL = function (parent, items) {
	 $.each(items, function () {
    	console.log(this);
	        if (this.label && this.isShow > 0) {
	            var li = $('<li></li>');
	        	var a = $('<a></a>');
				if(this.url){
					a.attr('href', '#'+this.url);
					if(this.level == 1){
						li.addClass('menu-item');
						a.attr('href', 'javascript:;');
						a.append(this.label);
					}else{
						a.append(this.label);
						/**
						 * 测试点击弹出tab页
						 * 
						 */
						a.attr('ng-click', "addTabForTest('"+this.label+"','"+this.controller+"','"+this.view+"')");
					}
				}
				if(this.item.path && this.item.path.indexOf('report_') > -1){
        			a.attr('onclick', 'setReportNameToCurrent("'+ this.item.path +'")');
        		}
				a.appendTo(li);
				li.appendTo(parent);
	            if (this.items && this.items.length > 0) {
	            	if(this.level == 1){
	            		li.addClass('has-child');
	            	}
            		
	            	if(this.level == 2){
	            		if(this.url){
	            			a.attr('href', '#'+this.url);
	            		}else{
	            			a.attr('href', 'javascript:;');
						}
	            		if(this.item.path && this.item.path.indexOf('report_') > -1){
	            			a.attr('onclick', 'setReportNameToCurrent("'+ this.item.path +'")');
	            		}
	            	}
	            	var ul = $('<ul class="menu-child" ></ul>');
            		buildLeftUL(ul, this.items);
 					ul.appendTo(li);
	            }
			}
	    });
}


/**
 * 组装菜单数据
 */
var builddata = function () {
	var menuMap = new Map();
	var actionMap = new Map();
    var source = [];
    var items = [];
    for (i = 0; i < data.length; i++) {
        var item = data[i];
        var label = item.label;
		var title = item.title;
		var url = item.url;
		var isMenu = item.isMenu;
		var isShow = item.isShow;
		var level = item.level;
		var cssClass ;
		if(item.cssClass!=null){
			cssClass = item.cssClass;
		}
		var parentid = item.parentid;
        var id = item.id;
        var path = item.path;
        actionMap.put(url, item);
		if(isMenu<0){
			continue;
		}
        if (parentid) {
        	var obj;
            var item = {id: id,  parentid: parentid, label: label, item: item, title: title, cssClass: cssClass, url: url, level:level, isMenu: isMenu, path:path, isShow: isShow};
            obj=menuMap.get(parentid);
            if(obj){
                if (undefined==obj.items) {
                	obj.items = [];
                }
                menuMap.get(parentid).items[menuMap.get(parentid).items.length] = item;
                items[items.length-1] = item;
                menuMap.put(id, item);
             	}
            }

        	
       
        else {
         	if(id!="undefined"&&id!=undefined){
            items[items.length-1] = { id: id, parentid: parentid, label: label, item: item, title: title, cssClass: cssClass, url: url, level:level, isMenu: isMenu,  path:path, isShow: isShow};
            source[source.length] = items[items.length-1];
            menuMap.put(id, items[items.length-1]);
         	}
        }
    }
    return source;
}
var source = builddata();

/**
 * 构造菜单UI
 */
var buildUL = function (parent, items) {
    $.each(items, function () {
    	console.log(this);
        if (this.label && this.isShow > 0) {
            var li = $('<li></li>');

			if(this.items && this.items.length > 0){
				li.addClass('gui-folder');
			}
			var a = $('<a></a>');
			
			if(this.path){
				a.attr('href', '#'+this.path);
			}else if(this.url){
				a.attr('href', '#'+this.url);
			}

			if(this.url){
				if(this.level == 1){
					a.append('<div class="gui-icon"><i class="'+this.cssClass+'"></i></div>');
				}
				a.append('<span class="title">'+this.label+'</span>');
				a.appendTo(li);
			}else {
				if(this.level == 1){
					li.append('<div class="gui-icon"><i class="'+this.cssClass+'"></i></div>');
				}
				li.append('<span class="title">'+this.label+'</span>');
			}
			li.appendTo(parent);
            if (this.items && this.items.length > 0) {
                var ul = $("<ul></ul>");
                buildUL(ul, this.items);
				ul.appendTo(li);
            }
		}
    });
}
var ul = $('#main-menu');

buildUL(ul, source);


