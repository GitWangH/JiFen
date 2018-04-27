'use strict';
/**
 * 
 */
angular.module('huatek.controllers').controller('phiMainGamesController', function ($scope, $http, editService, httpService,$rootScope) {
	 var addDataUrl = 'api/phiMarket/add';
	 var homeUrl = '/phiOrder/home';
	 $scope.editId = $scope.passParams;
	//定义块
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'移动端首页_Banner区编辑'));
   $scope.columnWrapArray = columnWrapArray;
  //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)

   
         var formFieldArray = [];
         var adAddress = new FormElement(1,'位置','adAddress','string',0,'30','',null,null,null,null,'readonly');
         formFieldArray.push(adAddress);
         
         var phoUuidName = new FormElement(1,'主广告','phoUuidName','string',0,'30','fileMultiple');
         phoUuidName.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName);
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
    		if(data != undefined && data.phoUuidName !=undefined){
    			/*adTitle.value = data.adTitle;*/
    			phoUuidName.value = data.phoUuidName;
    			phoUuidName1.value = data.phoUuidName1;
    			phoUuidName2.value = data.phoUuidName2;
    			phoUuidName3.value = data.phoUuidName3;
        		editDataUrl = 'api/phiMarket/add';
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

angular.module('huatek.controllers').controller('phiMainHotRecommendController', function ($scope, $http, editService, httpService,$rootScope) {
	 var addDataUrl = 'api/phiMarket/add';
	 var homeUrl = '/phiOrder/home';
	 $scope.editId = $scope.passParams;
	//定义块
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'移动端首页_Banner区编辑'));
   $scope.columnWrapArray = columnWrapArray;
  //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)

   
         var formFieldArray = [];
         var adAddress = new FormElement(1,'位置','adAddress','string',0,'30','',null,null,null,null,'readonly');
         formFieldArray.push(adAddress);
         
         var phoUuidName = new FormElement(1,'广告','phoUuidName','string',0,'30','fileMultiple');
         phoUuidName.systemHeader = "/root/adPosition/mainBanner";
         formFieldArray.push(phoUuidName);
         
         var phoLink = new FormElement(1,'跳转链接','phoLink','string',0,'100');
         formFieldArray.push(phoLink);
         
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
    		if(data != undefined && data.phoUuidName !=undefined){
    			phoUuidName.value = data.phoUuidName;
    			phoLink.value = data.phoLink;
        		addDataUrl = 'api/phiMarket/add';
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


angular.module('huatek.controllers').controller('phiMainListConfigureController', function ($scope, $http, editService, httpService,$rootScope) {
	 var addDataUrl = 'api/phiMarket/add';
	 var homeUrl = '/phiOrder/home';
	 $scope.editId = $scope.passParams;
	//定义块
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'移动端首页_Banner区编辑'));
	    columnWrapArray.push(new multipleColumn(2,'区间1'));
	    columnWrapArray.push(new multipleColumn(3,'区间2'));
	    columnWrapArray.push(new multipleColumn(4,'区间3'));
	 $scope.columnWrapArray = columnWrapArray;
	//  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)

 
       var formFieldArray = [];
       var adAddress = new FormElement(1,'位置','adAddress','string',0,'30',null,null,null,null,null,'readonly');
       formFieldArray.push(adAddress);
      /* var adTitle = new FormElement(1,'广告标题','adTitle','string',0,'30');
       formFieldArray.push(adTitle);*/
       
       var below = new FormElement(2,'以下（积分值）','below','number',0,'30');
       var choose1 = new FormElement(2,'选择（%）','choose1','number',0,'30');
       formFieldArray.push(below);
       formFieldArray.push(choose1);
       
       var section = new FormElement(3,'','section','number',0,'30')
       formFieldArray.push(section);
       var section1 = new FormElement(3,'-','section1','number',0,'30');
       formFieldArray.push(section1);
       var choose2 = new FormElement(3,'选择（%）','choose2','number',0,'30');
       formFieldArray.push(choose2);
       
       var over = new FormElement(4,'以上（积分值）','over','number',0,'30');
       var choose3 = new FormElement(4,'选择（%）','choose3','number',0,'30');
       formFieldArray.push(over);
       formFieldArray.push(choose3);
       
       
       var adCode = new FormElement(1,'ADCODE','adCode','string',0,'30');
       adCode.isShow = false;
       formFieldArray.push(adCode);
       
       
  editService.init($scope, $http);
  editService.setFormFields($scope, formFieldArray);  
  /**
    *加载编辑数据
   */
  if($scope.editId){
  	var passParams = $scope.passParams;
  	console.log($scope.passParams);
  	adAddress.value = $scope.passParams;
  	
  	adAddress.value = $scope.passParams[0];
	adCode.value = $scope.passParams[1];
  	
  	$http.get('api/phiPhoInfo/getPhoInfo/'+adCode.value).success(function(data){
  		console.log(data);
  		if(data != undefined && data.choose2 !=undefined && data.choose1 != undefined && data.choose3 != undefined){
  			/*adTitle.value = data.adTitle;*/
  			below.value = data.below;
  			choose1.value = data.choose1;
  			choose2.value = data.choose2;
  			choose3.value = data.choose3;
  			over.value = data.over;
  			section.value = data.section;
  			section1.value = data.section1;
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













