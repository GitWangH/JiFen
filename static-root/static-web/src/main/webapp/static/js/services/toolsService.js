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

