
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

