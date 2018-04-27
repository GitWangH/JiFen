/**
 * 在隔离的scope中共享数据
 */
angular.module('huatek.services').factory('shareData',function(){
	var shareObject = {};
	return shareObject;
});
