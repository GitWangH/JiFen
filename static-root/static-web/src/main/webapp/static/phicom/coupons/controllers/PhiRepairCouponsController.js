'use strict';
/***
 * 补优惠劵Controller
 */
angular.module('huatek.controllers').controller('PhiRepairCouponsController', function ($rootScope, $scope, $http, editService, httpService) {
	
	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'手动补优惠劵'));
    columnWrapArray.push(new multipleColumn(2,''));
    columnWrapArray.push(new multipleColumn(3,''));
    $scope.columnWrapArray = columnWrapArray;
	 
    /*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    var formFieldArray = [];
    var telNumber = new FormElement(1,'手机号码','telNumber','string','require','11');
    formFieldArray.push(telNumber);
    var coupWayId = new FormElement(1,'优惠劵方案ID','coupWayId','string','require','5');
    formFieldArray.push(coupWayId);
//    formFieldArray.push(new FormElement(1,'优惠劵方案ID','coupWayId','string','','2','textarea'));
    
	editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray); 
    
    /**
     * 模糊检索：标段名称(机构名称)
     */
    $scope.getTendersInfo = function (orgIdVal) {
    	var orgId = cnex4_isNotEmpty_str(orgIdVal)? orgIdVal:tendersName.value;
    	
    }
    /**
     * 保存
     **/    
    $scope.save = function(){
    	console.log("telNumber:"+telNumber.value+"   coupWayId:"+coupWayId.value)
    	if(!cnex4_isNotBlank_str(telNumber.value)){
    		 submitTips('手机号码不能为空!','error');
    		 return;
    	}else{
    		if(!cnex4_isNotBlank_str(coupWayId.value)){
       		 	submitTips('优惠劵方案ID不能为空!','error');
       		 	return;
    		}else{
    			$scope.promise = httpService.post($scope, 'api/phiCouponsDetail/repairCoupons/' + telNumber.value + "/" + coupWayId.value)
		    	  	  .success(function (response) {
		    	  		  submitTips(response,'success');
		    	  	  })
		    	  	  .error(function(response){
		    	  		  console.log(response)
		    	  		  submitTips(response,'error');
		    	  	  });
    		}
    	}
    }
    
});