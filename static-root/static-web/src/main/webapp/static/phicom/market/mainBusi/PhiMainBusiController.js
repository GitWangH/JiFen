'use strict';
/**
 * 
 */
angular.module('huatek.controllers').controller('PhiMainBusiController', function ($scope, $http, editService, httpService,$rootScope) {
	 var addDataUrl = 'api/phiMarket/add';
	 var homeUrl = '/phiOrder/home';
	 $scope.editId = $scope.passParams;
	//定义块
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'广告位1'));
	    columnWrapArray.push(new multipleColumn(2,'广告位2'));
   $scope.columnWrapArray = columnWrapArray;
  //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)

   
         var formFieldArray = [];
         var adAddress = new FormElement(1,'位置','adAddress','string',0,'30','',null,null,null,null,'readonly');
         formFieldArray.push(adAddress);
         
         var adTitle = new FormElement(1,'广告标题','adTitle','string',0,'30');
         formFieldArray.push(adTitle);
         var adSubheading = new FormElement(1,'广告副标题','adSubheading','string',0,'30');
         formFieldArray.push(adSubheading);
         var phoUuidName = new FormElement(1,'图片上传','phoUuidName','string',0,'30','fileMultiple');
         phoUuidName.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName);
         
         var phoLink = new FormElement(1,'跳转链接','phoLink','string',0,'100');
         formFieldArray.push(phoLink);
         
         /*var phoOrder = new FormElement(1,'显示顺序','phoOrder','string',0,'30');
         formFieldArray.push(phoOrder);*/
         
         var adTitle1 = new FormElement(2,'广告标题','adTitle1','string',0,'30');
         formFieldArray.push(adTitle1);
         var adSubheading1 = new FormElement(2,'广告副标题','adSubheading1','string',0,'30');
         formFieldArray.push(adSubheading1);
         var phoUuidName1 = new FormElement(2,'图片上传','phoUuidName1','string',0,'30','fileMultiple');
         phoUuidName1.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName1);
         
         var phoLink1 = new FormElement(2,'跳转链接','phoLink1','string',0,'100');
         formFieldArray.push(phoLink1);
        /* var phoOrder1 = new FormElement(2,'显示顺序','phoOrder1','string',0,'30')
         formFieldArray.push(phoOrder1);*/
         
         var adCode = new FormElement(1,'ADCODE','adCode','string',0,'30');
         adCode.isShow = false;
         formFieldArray.push(adCode);
   //只读
		  /*$rootScope.formFieldArray = formFieldArray;
		  for(var i=0;i<formFieldArray.length;i++){
			   formFieldArray[i].readonly=true;
		  }		*/
		  
    editService.init($scope, $http);
    editService.setFormFields($scope, formFieldArray);  
    /**
      *加载编辑数据
     */
    if($scope.editId){
    	var passParams = $scope.passParams;
    	console.log($scope.passParams);
    	adAddress.value = $scope.passParams[0];
    	adCode.value = $scope.passParams[1];
    	console.log(phoUuidName);
    	$http.get('api/phiPhoInfo/getPhoInfo/'+adCode.value).success(function(data){
    		if(data != undefined && data.phoUuidName !=undefined){
    			adTitle.value = data.adTitle;
    			adTitle1.value = data.adTitle1;
        		adSubheading.value = data.adSubheading;
        		adSubheading1.value = data.adSubheading1;
        		phoUuidName.value = data.phoUuidName;
        		phoUuidName1.value = data.phoUuidName1;
        		phoLink.value = data.phoLink;
        		phoLink1.value = data.phoLink1;
        		/*phoOrder.value = data.phoOrder;
        		phoOrder1.value = data.phoOrder1;
        		console.log(data);*/
        		
        		addDataUrl = 'api/phiMarket/add';
    		}
    	});
    	
    }
    /**
      *保存
     */   
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl, homeUrl);
    	/*load();*/
    } 
    
    $scope.reset = function(){
    	console.log('$scope.reset');
    	$rootScope.formFieldArray = formFieldArray;
    	console.log(formFieldArray.length);
		  for(var i=0;i<formFieldArray.length;i++){
			  console.log(formFieldArray[i]);
			  if(formFieldArray[i].model == 'fileMultiple'){
				  console.log(formFieldArray[i].value);
			  }
			   formFieldArray[i].value='';
			   adAddress.value = $scope.passParams[0];
		  }
    }
});