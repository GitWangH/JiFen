'use strict';
/**
 * 
 */
angular.module('huatek.controllers').controller('PhiMainBannerEditController', function ($scope, $http, editService,listService, httpService,$rootScope) {
	 var editDataUrl = 'api/phiMarket/add';
	/* var homeUrl = '/phiOrder/home';*/
	 var homeUrl = '';
	 $scope.editId = $scope.passParams;
	//定义块
	 
	 $scope.tableGrid = {
			   /* paginationPageSizes: [10, 25, 50,100],
			    paginationPageSize: 10,*/
			    useExternalPagination: true,
			    columnDefs: [
			       { name: '图片标题', field: 'plan1',width: '10%', enableColumnMenu: false},
			       { name: '图片', field: 'phoUuidName',width: '10%', enableColumnMenu: false},
			       { name: '链接', field: 'phoLink',width: '10%', enableColumnMenu: false},
			       { name: '显示时间', field: 'phoStart',width: '10%', enableColumnMenu: false},
			       { name: '结束时间', field: 'phoEnd',width: '10%', enableColumnMenu: false},
			       { name: '操作人', field: 'operator',width: '10%', enableColumnMenu: false},
			       { name: '最后操作时间', field: 'phoEndOpTime',width: '10%', enableColumnMenu: false},
			       { name: '显示顺序', field: 'phoOrder',width: '10%', enableColumnMenu: false},
			       { name: '操作', field: 'operate',width: '20%', enableColumnMenu: false,cellTemplate:'<a><span ng-click="grid.appScope.openEdit(row.entity)">编辑</span>&nbsp;&nbsp;&nbsp;&nbsp;<span ng-click="grid.appScope.deletes(row.entity)">删除</span></a>'}
			    ]
		  };
		
	 
	 listService.init($scope, $http);
	 /***
		 * 注册gridApi的选择器.gridApi.pagination
		 */
		$scope.tableGrid.onRegisterApi = function(gridApi){
		      $scope.gridApi = gridApi;
		      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
		    		queryPage.page = newPage;
		    		queryPage.pageSize = pageSize;
		    		load();
		          });
		  	}; 
		  	
		  	
		  	
		  	$scope.deletes = function(entity){
		  		var id = entity.id;
		  		$http.get('api/phiPhoInfo/delete/'+id).success(function(response){
		  			if (response.type == 'success') {
		            	load();
		            } else {
		                submitTips(response.text, 'error');
		            }
		  		});
		  	};
		  	
		  	$scope.openEdit = function (entity){
		  		var id = entity.id;
		  		console.log(id);
		  		adAddress.value = $scope.passParams[0];
		    	adCode.value = $scope.passParams[1];
		  		$http.get('api/phiPhoInfo/getPhoInfobyId/'+id).success(function(data){
		    		console.log(data);
		    		if(data != undefined){
		    			plan1.value = data.plan1;
			    		/*phoUuidName.vlaue = data.phoUuidName;*/
			    		phoLink.value = data.phoLink;
			    		console.log('aaaaaaaaaaaaaaaaaaaaaaaa');
			    		console.log(data.phoUuidName);
			    		console.log('aaaaaaaaaaaaaaaaaaaaaaaa');
			    		phoStart.value = data.phoStart;
			    		phoEnd.value = data.phoEnd;
			    		phoOrder.value = data.phoOrder;
			    		phoUuidName.value = data.phoUuidName;
			    		phoLink.value = data.phoLink;
			    		phoStart.value = data.phoStart;
			    		phoEnd.value = data.phoEnd;
			    		editDataUrl = 'api/phiMarket/update/'+id;
		    		}
		    		
		    	});
		  	}
		  	
		  	/***
			 * 初始化加载数据.
			 */
		    var load = function(){
		    	console.log($scope.tableGrid.totalItems);
		    	/*listService.loadData($scope,'api/phiPhoInfo/querybyAdCode/'+$scope.passParams[1], $scope.tableGrid);*/
		    	httpService.post($scope,'api/phiPhoInfo/querybyAdCode/'+$scope.passParams[1]).success(function (response){
		    		$scope.tableGrid.data = response;
		    	});
		    }
		    
		    load();
			
	 
	 var columnWrapArray = [];
	    columnWrapArray.push(new multipleColumn(1,'移动端首页_Banner区编辑'));
   $scope.columnWrapArray = columnWrapArray;
  //  定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min, defaultValue, dropDataUrl, readonly)

   
         var formFieldArray = [];
         var adAddress = new FormElement(1,'位置','adAddress','string',0,'30',null,null,null,null,null,'readonly');
         formFieldArray.push(adAddress);
         var plan1 = new FormElement(1,'广告标题','plan1','string',0,'30');
         formFieldArray.push(plan1);
         var phoUuidName = new FormElement(1,'图片上传','phoUuidName','string',0,'30','fileMultiple');
         phoUuidName.systemHeader = "/root/adPosition/mainBanner";
         phoUuidName.value=getUUID(36);
         formFieldArray.push(phoUuidName);
         var phoLink = new FormElement(1,'跳转链接','phoLink','string',0,'100');
         formFieldArray.push(phoLink);
         var phoStart = new FormElement(1,'显示时间','phoStart','string',0,'30','date');
         formFieldArray.push(phoStart);
         var phoEnd = new FormElement(1,'结束时间','phoEnd','string',0,'30','date');
         formFieldArray.push(phoEnd);
         var phoOrder = new FormElement(1,'显示顺序','phoOrder','string',0,'30');
         formFieldArray.push(phoOrder);
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
    	console.log($scope.passParams[0]);
    	console.log($scope.passParams[1]);
    	adAddress.value = $scope.passParams[0];
    	adCode.value = $scope.passParams[1];
    	
    	/*$http.get('api/phiPhoInfo/getPhoInfo/'+adCode.value).success(function(data){
    		console.log(data);
    		if(data != undefined && data.phoUuidName !=undefined){
    			console.log(data);
    			plan1.value = data.plan1;
	    		phoUuidName.vlaue = data.phoUuidName;
	    		phoLink.value = data.phoLink;
	    		phoStart.value = data.phoStart;
	    		phoEnd.value = data.phoEnd;
	    		phoOrder.value = data.phoOrder;
	    		phoUuidName.value = data.phoUuidName;
	    		phoLink.value = data.phoLink;
	    		phoStart.value = data.phoStart;
	    		phoEnd.value = data.phoEnd;
	    		editDataUrl = 'api/phiMarket/update';
    		}
    		
    	});*/
    }
    
    
   $scope.add = function(){
	   var count = 2;
	   	if(count <= 6){
		   columnWrapArray.push(new multipleColumn(count,'移动端首页_Banner区编辑'));
		   $scope.columnWrapArray = columnWrapArray;
		   var formFieldArrayCopy = [];
		   console.log(formFieldArray.length);
		   for(var i=1; i<formFieldArray.length;i++){
			   formFieldArray[i].column = count;
			   formFieldArray[i].name = formFieldArray[i].name + count;
			   formFieldArrayCopy[i -1] = formFieldArray[i];
		   }
		   for(var j=0;j>formFieldArrayCopy.length;j++){
			   formFieldArray.push(formFieldArrayCopy[i]);
		   }
		   
   		}
	   count ++;
   }
    
    /**
      *保存
     */   
    $scope.save = function(){
    	var length = $scope.tableGrid.data.length;
		if(length > 5  && editDataUrl=='api/phiMarket/add'){
			submitTips('最多添加6条数据', 'warning');
			return ;
		}
		if (!editService.isAllowPost($scope)) {
            return;
        }
		var data = editService.getPostData($scope);
		httpService.post($scope,editDataUrl, data).success(function(response) {
            if (response.type == 'success') {
            	load();
            } else {
                submitTips(response.text, 'error');
            }
        });
    	/*editService.saveData($scope, editDataUrl, homeUrl,null,function(){
    		load();
    	});*/
    	$scope.reset();
    	adAddress.value = $scope.passParams[0];
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
					  
				  phoUuidName.value=getUUID(36);
				  console.log(phoUuidName.value)
			  }else{
				  formFieldArray[i].value='';
			  }
			   adAddress.value = $scope.passParams[0];
			   adCode.value=$scope.passParams[1];
		  }
    }
});
















