angular.module('huatek.controllers').controller('ExcelUploadController',function ($scope,$window,editService,shareData,$filter ,$http) {
            	var params=shareData.params;
            	var url=shareData.url;
            	var filePath=null;
            	var fileName=null;
            	var isMutil=shareData.isMutil||false;
                $scope.options = {
                    url: url,
                    autoUpload:false,
                	formData:params,
                	handleResponse:function(e, data){
                		var files = data.result && data.result.files;
                        if (files&&files[0]&&!files['error']) {
                            filePath=files[0]['filepath'];
                            fileName=files[0]['name'];
                        } 
                	}
                };
                editService.init($scope);
                $scope.commit = function(){
                	$scope["jsPanel"].close();
            	}
            }
);
angular.module('huatek.controllers').controller('ExcelImportController',function ($scope,$window,editService,shareData,$filter ,$http) {
	var params=shareData.params;
	var url=shareData.url;
    $scope.options = {
        url: url,
        autoUpload:false,
    	formData:params


    };
	editService.init($scope);
    $scope.commit = function(){
    	$scope["jsPanel"].close();
	}
});