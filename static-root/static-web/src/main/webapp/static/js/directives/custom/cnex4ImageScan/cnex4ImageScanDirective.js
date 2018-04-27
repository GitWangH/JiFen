'use strict';
customDirective.controller('cenx4ImageScanController',['$scope', '$http',function($scope, $http){
	$scope.upload=function(){
		$scope.scanControl=$scope.el.find("#scanControl")[0];
		 var hosturl = [ location.protocol, "//", location.host ].join("");
		 hosturl=hosturl+"/"+URL_PATH.FILE_HEADER+'/upload.do?actionMethod=process&systemHeader='+$scope.systemHeader+"&businessId="+$scope.ngModel;
		 $scope.scanControl.InitParam(hosturl,'扫描件');
		if($scope.scanControl){
			  var result= $scope.scanControl.Upload();
			  if(result.indexOf('ifSuccess\":\"true')>-1){
				  submitTips('上传成功！','info');
				 if( $scope.filesCount==null||$scope.filesCount==undefined){
					 $scope.filesCount=0;
				 }
				 $scope.filesCount=$scope.filesCount+1;
			  }
		}

	}
	$scope.isIE=function() { 
	      if (!!window.ActiveXObject || "ActiveXObject" in window)
	          return true;
	        else
	          return false;
	}
	$scope.$on('$destroy',function() {
		$scope.scanControl=null;
		$scope.el.empty();
	});
	
}]).directive('cenx4ImageScan',function(){
	return { 
    	require: '?ngModel',
    	restrict: 'E',
    	scope: {ngModel: "=",filesCount:"=",systemHeader:"="},
    	controller: 'cenx4ImageScanController',
    	templateUrl:  'static/js/custom/directives/cnex4ImageScan/template/scan.html',
        replace: true,
        link: function (scope, el, attrs,ngModel) {
        	var businessId = getUUID();
        	ngModel.$render = function() {
        		if("" == ngModel.$viewValue || undefined == ngModel.$viewValue ){
        			scope.ngModel = businessId;
        		}
            };
            scope.el=el;
        }
    };
});
