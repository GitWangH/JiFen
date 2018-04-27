'use strict';
/**
 * 
 */
angular.module('huatek.controllers').controller('phiPCMainGamesController', function ($scope, $http, editService, httpService,$rootScope) {
	 var addDataUrl = 'api/phiMarket/add';
	 var homeUrl = '/phiOrder/home';
	 $scope.editId = $scope.passParams;
	//定义块
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'移动端首页_Banner区编辑'));
   $scope.columnWrapArray = columnWrapArray;
  //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)

   
         var formFieldArray = [];
         var adAddress = new FormElement(1,'位置','adAddress','string',0,'30',null,null,null,null,null,'readonly');
         formFieldArray.push(adAddress);
         
         /*var adTitle = new FormElement(1,'广告标题','adTitle','string',0,'30');
         formFieldArray.push(adTitle);*/
         var phoUuidName1 = new FormElement(1,'大转盘','phoUuidName1','string',0,'30','fileMultiple');
         phoUuidName1.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName1);
         var phoUuidName2 = new FormElement(1,'九宫格','phoUuidName2','string',0,'30','fileMultiple');
         phoUuidName2.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName2);
         var phoUuidName3 = new FormElement(1,'积分夺宝','phoUuidName3','string',0,'30','fileMultiple');
         phoUuidName3.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName3);
         var adCode = new FormElement(1,'ADCODE','adCode','string',0,'30');
         adCode.isShow = false;
         formFieldArray.push(adCode);
         
         
   //只读
		/*  $rootScope.formFieldArray = formFieldArray;
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
    	$http.get('api/phiPhoInfo/getPhoInfo/'+adCode.value).success(function(data){
    		console.log("===========");
    		console.log(data);
    		if(data != undefined ){
	    		/*adTitle.value = data.adTitle;*/
	    		/*phoUuidName.vlaue = data.phoUuidName;*/
	    		phoUuidName1.value = data.phoUuidName1;
	    		phoUuidName2.value = data.phoUuidName2;
	    		phoUuidName3.value = data.phoUuidName3;
	    		addDataUrl = 'api/phiMarket/add';
    		}
    	});
    }
    
    /**
      *保存
     */   
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl, homeUrl);
    } 
    
    
    /**
     * 重置
     */
    $scope.reset = function(){
    	$rootScope.formFieldArray = formFieldArray;
    	console.log(formFieldArray.length);
		  for(var i=0;i<formFieldArray.length;i++){
			  console.log(formFieldArray[i]);
			  if(formFieldArray[i].model == 'fileMultiple'){
				  formFieldArray[i].value="0"
				  $scope.queue=new Array();
			  }
			   formFieldArray[i].value='';
			   adAddress.value = $scope.passParams[0];
		  }
    }
});

angular.module('huatek.controllers').controller('phiExchangeController', function ($scope, $http, editService, httpService,$rootScope) {
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
         var adAddress = new FormElement(1,'位置','adAddress','string',0,'30',null,null,null,null,null,'readonly');
         formFieldArray.push(adAddress);
         var adTitle = new FormElement(1,'广告标题','adTitle','string',0,'30');
         formFieldArray.push(adTitle);
         /*var adSubheading = new FormElement(1,'广告副标题','adSubheading','string',0,'30');
         formFieldArray.push(adSubheading);*/
         
         var phoUuidName = new FormElement(1,'图片上传','phoUuidName','string',0,'30','fileMultiple');
         phoUuidName.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName);
         var phoLink = new FormElement(1,'跳转链接','phoLink','string',0,'100');
         formFieldArray.push(phoLink);
        /* var phoOrder = new FormElement(1,'显示顺序','phoOrder','string',0,'30');
         formFieldArray.push(phoOrder);*/
         
         
         var adTitle1 = new FormElement(2,'广告标题','adTitle1','string',0,'30');
         formFieldArray.push(adTitle1);
         /*var adSubheading1 = new FormElement(2,'广告副标题','adSubheading1','string',0,'30');
         formFieldArray.push(adSubheading1);*/
         
         var phoUuidName1 = new FormElement(2,'图片上传','phoUuidName1','string',0,'30','fileMultiple');
         phoUuidName1.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName1);
         var phoLink1 = new FormElement(2,'跳转链接','phoLink1','string',0,'100');
         formFieldArray.push(phoLink1);
        /* var phoOrder1 = new FormElement(2,'显示顺序','phoOrder1','string',0,'30');
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
    	console.log($scope.passParams[1]);
    	
    	adAddress.value = $scope.passParams[0];
    	adCode.value = $scope.passParams[1];
    	
    	$http.get('api/phiPhoInfo/getPhoInfo/'+adCode.value).success(function(data){
    		if(data != null && data != undefined && data != ''){
    			console.log("-------------------------");
    			console.log(data);
    			console.log("-------------------------");
    			adTitle.value = data.adTitle;
    			adTitle1.value = data.adTitle1;
    			/*adSubheading.value = data.adSubheading;*/
    			phoLink.value = data.phoLink;
    			phoLink1.value = data.phoLink1;
    			phoUuidName.value = data.phoUuidName;
    			phoUuidName1.value = data.phoUuidName1;
    			/*addDataUrl = 'api/phiMarket/add';*/
    		}
    		console.log(data);
    	});
    }
    /**
      *保存
     */   
    $scope.save = function(){
    	editService.saveData($scope, addDataUrl, homeUrl);
    } 
    
    $scope.reset = function(){
    	console.log('$scope.reset');
    	$rootScope.formFieldArray = formFieldArray;
    	console.log(formFieldArray.length);
		  for(var i=0;i<formFieldArray.length;i++){
			  console.log(formFieldArray[i]);
			   formFieldArray[i].value='';
			   adAddress.value = $scope.passParams[0];
		  }
    }
});














