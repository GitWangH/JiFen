'use strict';
   
angular.module('HuatekApp').controller('PrintFieldPositionController', function ($scope, $location, $http,$rootScope, listService) {
	   
	$scope.tableGrid = {
		    paginationPageSizes: [10, 25, 50,100],
		    paginationPageSize: 10,
		    useExternalPagination: true,
		    columnDefs: [
		       { name: '字段位置id', field: 'fieldPositionId',width: '10%', enableColumnMenu: false},
		       { name: '操作用户', field: 'operatorName',width: '10%', enableColumnMenu: false},
		       { name: '字段id', field: 'fieldId',width: '10%', enableColumnMenu: false},
		       { name: '字段位置', field: 'fieldPosition',width: '10%', enableColumnMenu: false},
		       { name: '字段状态', field: 'fieldStatus',width: '10%', enableColumnMenu: false},
		       { name: '备注', field: 'remark',width: '10%', enableColumnMenu: false},
		       { name: '操作时间', field: 'operateTime',width: '10%', enableColumnMenu: false},
		    ]
		    
	  };
	
	   
	listService.init($scope, $location, $http);
       
    var queryPage = new QueryPage(1,10,"id desc","false");
    
       
    queryPage.addParam(new queryParam('操作用户','operatorName','string','like'));
    queryPage.addParam(new queryParam('字段状态','fieldStatus','string','like'));
    
    $rootScope.searchCount = queryPage.queryParamList.length;
       
    listService.setQueryPage(queryPage);
    
       
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 
    
       
    var btnArray = [];
          
   listService.setButtonList(btnArray);

       
    $scope.execute = function(operation, param){
    }
	   
    var load = function(){
    	listService.loadData('api/printFieldPosition/query', $scope.tableGrid);
    }
    
    load();
	   
	$scope.search = function() {
		load();
	};
	
});

   
angular.module('HuatekApp').controller('PrintFieldPositionDesignController', function ($scope, $location, $http, $routeParams, editService,$rootScope) {
	var parentId = $routeParams.parentId;
	    
	 
	 /*var addDataUrl = 'api/printFieldPosition/add';*/
	 
	 /*var editDataUrl = 'api/printFieldPosition/edit';*/
	 
	 /*var homeUrl = '/printFieldPosition/home';*/
	var homeUrl = '/printReceipt/home';
	var getPrintFieldUrl = URL_PATH.CMD_HEADER+'/printFieldPosition/design/findPrintField';
	var designUrl = URL_PATH.CMD_HEADER+'/printFieldPosition/design';
	var designUrlAdmin = URL_PATH.CMD_HEADER+'/printFieldPosition/designAdmin';
	var getPrintReceiptUrl = URL_PATH.CMD_HEADER+"/printReceipt/getPrintReceipt";
	
   	
   	/*定义块*/
    var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
    
	
	/*定义用户录入数据FormElement(column, title, name, type, require, max, model, event, min)*/
    /*
    var formFieldArray = [];
		  formFieldArray.push(new FormElement(1,'字段位置id','fieldPositionId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'操作用户','operatorName','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'字段id','fieldId','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'字段位置','fieldPosition','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'字段状态','fieldStatus','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'备注','remark','string',1,'30'));
		  formFieldArray.push(new FormElement(1,'操作时间','operateTime','string',1,'30'));
    */
    
    /*设置全局变量*/
    
    /*$rootScope.formFieldArray = formFieldArray;*/

       
	editService.init($scope, $location, $http);
    
	$().ready(function(){
		/**
		 * 根据打印票据Id获取打印字段，生成页面选择checkBox
		 */
		jQuery.ajax({
			type:"get",
			url: getPrintFieldUrl+"/"+parentId,
			async:false,
			success:function(response){
				$scope.printFieldArray = response;
			}
		});
		
		/**
		 * 根据打印票据Id获取打印字段位置信息
		 */
		jQuery.ajax({
			type:"get",
			url: designUrl+"/"+parentId,
			async:false,
			success:function(response){
				$scope.printFieldPositionArray = response;
			}
		});
		
		setTimeout(function (){
			for(var i=0;i<$scope.printFieldPositionArray.length;i++)
			{
				var fieldId = $scope.printFieldPositionArray[i].fieldId;
				var fieldName = $scope.printFieldPositionArray[i].fieldName;
				var fieldPosition = $scope.printFieldPositionArray[i].fieldPosition;
				var fieldPositionArr = fieldPosition.split(",");
				LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldName);
				
				for(var j=0;j<$scope.printFieldArray.length;j++)
				{
					var id = $scope.printFieldArray[j].id;
					if(fieldId==id)
					{
						document.getElementById(fieldId).checked = "true";
					}else{
						document.getElementById(fieldId).checked = "false";
					}
				}
			}
			
			/**
			 * 根据打印票据Id打印设置信息
			 */
			jQuery.ajax({
				type:"get",
				url: getPrintReceiptUrl+"/"+parentId,
				async:false,
				success:function(response){
					var receiptUrl = response.receiptUrl;
					if(undefined!=receiptUrl && receiptUrl!=""){
						
						/*加载图片*/
						getFiles(receiptUrl,response);
						
					}
				}
			});
		}, 1);
	});
	
	var getFiles = function(businessId,response) {
		var cnex4_getFiles_url = "/"+URL_PATH.FILE_HEADER+'/getFiles.do?actionMethod=getFiles';
    	
        $http({
        	 method:'POST',  
        	   url:cnex4_getFiles_url,  
        	   data:{businessIds:businessId},  
        	   headers:{'Content-Type': 'application/x-www-form-urlencoded'},  
        	   transformRequest: function(obj) {
        	     var str = [];  
        	     for(var p in obj){  
        	       str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));  
        	     }  
        	     return str.join("&");  
        	   }
        })
        .success(function (data, status, headers, config) {
        	var imageUrl = data.data[0].viewUrl;
        	LODOP.ADD_PRINT_SETUP_BKIMG("<img src='"+"/api_image"+imageUrl+"'>");
        	
        	
        	/*设置图片参数*/
			var bkimgLeft = response.bkimgLeft;
			var bkimgtop = response.bkimgtop;
			var bkimgWidth = response.bkimgWidth;
			var bkimgHeight = response.bkimgHeight;
			var bkimgInPreview = response.bkimgInPreview;
			var bkimgPrint = response.bkimgPrint;
			
			/*console.log("---bkimgLeft--"+bkimgLeft+"--bkimgtop-"+bkimgtop+"--bkimgWidth-"+bkimgWidth+"--bkimgHeight-"+bkimgHeight+"--bkimgInPreview-"+bkimgInPreview+"--bkimgPrint-"+bkimgPrint);*/
			if(undefined!=bkimgLeft && bkimgLeft>0){
				LODOP.SET_SHOW_MODE("BKIMG_LEFT",bkimgLeft);
			}
			if(undefined!=bkimgtop && bkimgtop>0){
				LODOP.SET_SHOW_MODE("BKIMG_TOP",bkimgtop);
			}
			if(undefined!=bkimgWidth && bkimgWidth!=""){
				LODOP.SET_SHOW_MODE("BKIMG_WIDTH",bkimgWidth);
			}
			if(undefined!=bkimgHeight && bkimgHeight!=""){
				LODOP.SET_SHOW_MODE("BKIMG_HEIGHT",bkimgHeight);
			}
			if(undefined!=bkimgInPreview && bkimgInPreview=="1"){
				LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",true);
			}
			if(undefined!=bkimgPrint && bkimgPrint=="1"){
				LODOP.SET_SHOW_MODE("BKIMG_PRINT",true);
			}
        })
        .error(function (data, status, headers, config) {
        	console.log("load files failed!");
        });
    };
	
	
       
    
    /*editService.setFormFields(formFieldArray); */
    
       
    /*
    $scope.editId = $routeParams.id;
    
    if($scope.editId){
    	editService.loadData(editDataUrl, $scope.editId);
    }else{
    	$scope.editId = -1;
    }
    */	
       
    /*
    $scope.update = function(){
    	editService.updateData(editDataUrl, homeUrl, $scope.editId);
    }
    */ 
       
    $scope.save = function(){
    	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
    	
    	/*通过LODOP获取设计器上的打印字段及位置信息 */
    	var programCodes = LODOP.GET_VALUE("ProgramCodes",0);
    	console.log(programCodes);
    	/*
    	 * 分割字符串，去掉字符串中如下代码以及结尾的空格和分号
    	 * LODOP.PRINT_INIT("");LODOP.SET_PRINT_MODE("PROGRAM_CONTENT_BYVAR",true);
    	 */
    	/*var startIndex=programCodes.indexOf("ADD_PRINT_TEXTA");
    	programCodes = programCodes.substring(startIndex,programCodes.trim().length-1);*/
    	var dataArr = new Array();
    	var programCodeArr = programCodes.split(";");
    	console.log(JSON.stringify(programCodeArr));
    	
    	/*遍历打印字段*/
    	var k = 0;
    	$.each(programCodeArr, function(i,val){   
    		  var fieldPosition = val.trim();
    		  if(fieldPosition.indexOf("ADD_PRINT_TEXTA") > 0 )
    		  /*如果是文字*/
    		  {
    		      /**
    		       * 去掉LODOP.ADD_PRINT_TEXTA及括号()
    		       */
    		      fieldPosition = fieldPosition.substring(22,fieldPosition.trim().length-1);
    		      var fieldPositionArr = fieldPosition.split(",");
    		      
    		      /*构建打印字段*/
    		      var Id = fieldPositionArr[0].substring(1,fieldPositionArr[0].length-1);
    		      
    		      /*构建打印字段位置*/
    		      var position = fieldPositionArr[1]+","+fieldPositionArr[2]+","+fieldPositionArr[3]+","+fieldPositionArr[4];
    		      var dataTemp = {fieldId:Id,fieldPosition:position,fieldType:"text"};
    		      dataArr[k] = dataTemp;
    		      k++;
    		  }else if(fieldPosition.indexOf("SET_SHOW_MODE") > 0 ){
    		  /*图片位置*/
    			  /**
    		       * 去掉LODOP.SET_SHOW_MODE及括号()
    		       */
    		      fieldPosition = fieldPosition.substring(20,fieldPosition.trim().length-1);
    		      var fieldPositionArr = fieldPosition.split(",");
    		      
    		      /*图片参数属性*/
    		      var name = fieldPositionArr[0].substring(1,fieldPositionArr[0].length-1);
    		      
    		      /*图片参数值*/
    		      var value = fieldPositionArr[1];
    		      if(name=="BKIMG_LEFT"){
    		    	  dataArr[k] = {bkimgLeft:value,fieldType:"image"};
    		      }else if(name=="BKIMG_TOP"){
    		    	  dataArr[k] = {bkimgtop:value,fieldType:"image"};
    		      }else if(name=="BKIMG_WIDTH"){
    		    	  value = value.substring(1,value.length-1);
    		    	  dataArr[k] = {bkimgWidth:value,fieldType:"image"};
    		      }else if(name=="BKIMG_HEIGHT"){
    		    	  value = value.substring(1,value.length-1);
    		    	  dataArr[k] = {bkimgHeight:value,fieldType:"image"};
    		      }else if(name=="BKIMG_IN_PREVIEW"){
    		    	  if(value){
    		    		  dataArr[k] = {bkimgInPreview:"1",fieldType:"image"};
    		    	  }else{
    		    		  dataArr[k] = {bkimgInPreview:"2",fieldType:"image"};
    		    	  }
    		      }else if(name=="BKIMG_PRINT"){
    		    	  if(value){
    		    		  dataArr[k] = {bkimgPrint:"1",fieldType:"image"};
    		    	  }else{
    		    		  dataArr[k] = {bkimgPrint:"2",fieldType:"image"};
    		    	  }
    		      }
    			  
    		      k++;
    		  }else if(fieldPosition.indexOf("SET_PRINT_STYLEA") > 0 ){
    		  /*字体信息*/
    			  
    			  
    		  }
    	}); 
    	var data = JSON.stringify(dataArr);
    	console.log(data);
    	
    	/*将打印字段新增或更新到系统中*/
    	$scope.promise = $http.post(designUrl+"/"+parentId, data).success(function (response) {
    		if(response.type=='success'){
    			$location.path(homeUrl);
    		}else {
    			console.log(response);
    		}
         });
    }
    
    /**
     * 自定义复原按钮
     * */
    $scope.recovery = function(){
    	bootbox.confirm('复原后不能恢复，您确定要复原吗?', function(result) {
	         if(result){
	        	 /**
	     		 * 根据打印票据Id获取打印字段，生成页面选择checkBox
	     		 */
	     		jQuery.ajax({
	     			type:"get",
	     			url: getPrintFieldUrl+"/"+parentId,
	     			async:false,
	     			success:function(response){
	     				$scope.printFieldArray = response;
	     			}
	     		});
	     		
	     		/**
	     		 * 根据打印票据Id获取打印字段位置信息
	     		 */
	     		jQuery.ajax({
	     			type:"get",
	     			url: designUrlAdmin+"/"+parentId,
	     			async:false,
	     			success:function(response){
	     				$scope.printFieldPositionArray = response;
	     				
	     				/*
	     				for(var i=0;i<$scope.printFieldPositionArray.length;i++)
	     				{
	     					var fieldId = $scope.printFieldPositionArray[i].fieldId;
	     					var fidldName = $scope.printFieldPositionArray[i].fidldName;
	     					var fieldPosition = $scope.printFieldPositionArray[i].fieldPosition;
	     					var fieldPositionArr = fieldPosition.split(",");
	     					LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fidldName);
	     				}
	     				*/
	     			}
	     		});
	     		/**
	     		 * 这一段有问题，由于数据加载不出来，所以设置了等待加载
	     		 */
	     		setTimeout(function (){
	     			LODOP.SET_PRINT_STYLEA('All','Deleted',true);
	     			for(var i=0;i<$scope.printFieldPositionArray.length;i++)
	     			{
	     				var fieldId = $scope.printFieldPositionArray[i].fieldId;
	     				var fieldName = $scope.printFieldPositionArray[i].fieldName;
	     				var fieldPosition = $scope.printFieldPositionArray[i].fieldPosition;
	     				var fieldPositionArr = fieldPosition.split(",");
	     				LODOP.ADD_PRINT_TEXTA(fieldId,fieldPositionArr[0],fieldPositionArr[1],fieldPositionArr[2],fieldPositionArr[3],fieldName);
	     				
	     				for(var j=0;j<$scope.printFieldArray.length;j++)
	     				{
	     					var id = $scope.printFieldArray[j].id;
	     					if(fieldId==id)
	     					{
	     						document.getElementById(fieldId).checked = "true";
	     					}else{
	    						document.getElementById(fieldId).checked = "false";
	    					}
	     				}
	     			}
	     		}, 1);
		     }
	         $scope.$apply();
	       });
    }
});

   
