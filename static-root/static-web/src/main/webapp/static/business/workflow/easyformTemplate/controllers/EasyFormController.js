'use strict';

angular.module('huatek.controllers').controller('EasyFormWorkflowAuditingController', function ($scope, $location, $http,httpService, $routeParams,editService,$rootScope, $q,cacheService,$timeout) {
	
	 $scope.busiCode=$scope.passParams.busiCode;
	 $scope.taskId=$scope.passParams.taskId;
	 $scope.busiId=$scope.passParams.busiId;
	 $scope.taskKey=$scope.passParams.taskKey;
	 $scope.taskName=$scope.passParams.taskName;
	 $scope.processInstanceId=$scope.passParams.processInstanceId;
	 $scope.onlyView=$scope.passParams.onlyView;
	var loadFormConfigUrl = 'api_workflow/form/loadForm/'+$scope.busiCode;
	var loadFormDataUrl = 'api_workflow/form/loadData/'+$scope.busiCode;
	var saveDataUrl = 'api_workflow/form/saveData/'+$scope.busiCode;
	var initParamsUrl='api_workflow/form/initParams';
	var systemHeader=$scope.busiCode.substring(0,$scope.busiCode.indexOf('0'));
	httpService.get($scope,loadFormConfigUrl).success(function(formConfig){
		var groups=formConfig.formFieldGroupList;
		var fields=formConfig.formFieldConfigList;
		 var columnWrapArray = [];
		angular.forEach(groups,function(group,index){
			if(group.name==='审批信息'||group.name==='审核信息'){
				return;
			}
			columnWrapArray.push(new multipleColumn(group.id,group.name));
		});
		columnWrapArray.push(new multipleColumn(99,"审批信息"));
		$scope.columnWrapArray = columnWrapArray;
		var dicCategoryArr=[];
		var dicFieldArr=[];
		var formFieldArray = [];
		
		angular.forEach(fields,function(field,index){
			var formField=new FormElement(field.formFieldGroupId,field.name,field.code,field.type,field.require?1:0,field.length,field.model);
			if(field.model=='file'||field.model=='fileSingle'||field.model=='fileMultiple'){
				formField.systemHeader=systemHeader;
			}
			if(formField.code=='result'||formField.code=='resultMessage'){
				return;
			}
			if(field.defaultValue){
				formField.value=field.defaultValue;
			}
			
			if(field.dropType){
				if(field.dropType==='url'){
					formField.dropDataUrl=field.dropImpl;
				}else if(field.dropType==='dic'&&field.dropImpl){
					
					/*2016年9月2日修改使用缓存*/

/*					dicCategoryArr.push(field.dropImpl);*/

/*					dicFieldArr.push(field.code);*/
					formField.dropDataUrl="dic."+field.dropImpl;
				}else if(field.dropType==='map'&&field.dropImpl){
					try{
						var items=eval(field.dropImpl);
						resolveShowFieldAndReturnField(formField,items);
						formField.items=items;
					}catch(e){
						
					}
				}
			}
			
			formField.readonly=field.readonly;
			formField.isShow=field.visiable;
			formFieldArray.push(formField);
		});
		var result=new FormElement(99,'审核','result','boolean',1,'128',"radio","resultChange");
		result.items=[{code:"true",name:'同意'},{code:"false",name:"驳回"}];
		/*result.value="true";*/
		formFieldArray.push(result);
		var resultMessage=new FormElement(99,'审核意见','resultMessage','string',1,'1000',"textarea");
		/*resultMessage.value='同意';*/
		formFieldArray.push(resultMessage);
		formFieldArray.push(new FormElement(99,'意见模版','resultMessageTemplate','string',0,'512',"select"));
		 $rootScope.formFieldArray = formFieldArray;
		 editService.init($scope, $http);
		 editService.setFormFields($scope,formFieldArray);
		 if(dicCategoryArr.length>0){
			 $scope.promise = httpService.get($scope,initParamsUrl+"/"+dicCategoryArr.join(",")+"/"+dicFieldArr.join(",")).success(function (params){
				 var _fieldMap=editService.getFieldMap($scope);
				 for(var i=0;i<params.length;i++){
		    			var formField = _fieldMap.get(params[i].fieldName);
		    			if(formField){
							resolveShowFieldAndReturnField(formField,params[i].params);
							formField.items = params[i].params;
		          	  	}
				 	}
				 $scope.load();
		    	});
		 }else{
			 $scope.load();
		 }
		 
		 $scope.resultMessage=editService.getFieldMap($scope).get("resultMessage");
		$scope.resultMessageTemplate=editService.getFieldMap($scope).get("resultMessageTemplate"); 
		 
	});
	$scope.resultChange=function(value){
		/*editService.getFieldMap($scope).get("resultMessage").value=value=='true'?"同意":"驳回";*/
	}
	
	$scope.load=function(){
		if($scope.busiId>0){
		   	editService.loadData($scope,loadFormDataUrl, $scope.busiId);
		}
	}
   /***
    * 定义更新操作.
    */
   $scope.submit = function(){
   		editService.submitData($scope,saveDataUrl);
   }  
  
});

angular.module('huatek.controllers').controller('EasyFormWorkflowShareController', function ($scope, $location, $http,httpService, $routeParams,editService,$rootScope,$q,cacheService,$timeout) {
	 $scope.busiCode=$scope.passParams.busiCode;
	 $scope.taskId=$scope.passParams.taskId;
	 $scope.busiId=$scope.passParams.busiId;
	 $scope.taskKey=$scope.passParams.taskKey;
	 $scope.taskName=$scope.passParams.taskName;
	 $scope.processInstanceId=$scope.passParams.processInstanceId;
	 $scope.onlyView=$scope.passParams.onlyView;
	var loadFormConfigUrl = 'api_workflow/form/loadForm/'+$scope.busiCode;
	var loadFormDataUrl = 'api_workflow/form/loadData/'+$scope.busiCode;
	var saveDataUrl = 'api_workflow/form/saveData/'+$scope.busiCode;
	var initParamsUrl='api_workflow/form/initParams';
	var systemHeader=$scope.busiCode.substring(0,$scope.busiCode.indexOf('0'));
	httpService.get($scope,loadFormConfigUrl).success(function(formConfig){
		var groups=formConfig.formFieldGroupList;
		var fields=formConfig.formFieldConfigList;
		 var columnWrapArray = [];
		angular.forEach(groups,function(group,index){
			columnWrapArray.push(new multipleColumn(group.id,group.name));
		});
		$scope.columnWrapArray = columnWrapArray;
		var dicCategoryArr=[];
		var dicFieldArr=[];
		var formFieldArray = [];
		angular.forEach(fields,function(field,index){
			var formField=new FormElement(field.formFieldGroupId,field.name,field.code,field.type,field.require?1:0,field.length,field.model);
			if(field.model=='file'||field.model=='fileSingle'||field.model=='fileMultiple'){
				formField.systemHeader=systemHeader;
			}
			if(field.defaultValue){
				formField.value=field.defaultValue;
			}
			
			if(field.dropType){
				if(field.dropType==='url'){
					formField.dropDataUrl=field.dropImpl;
				}else if(field.dropType==='dic'&&field.dropImpl){
					
					/*2016年9月2日修改使用缓存*/

/*					dicCategoryArr.push(field.dropImpl);*/

/*					dicFieldArr.push(field.code);*/
					formField.dropDataUrl="dic."+field.dropImpl;
				}else if(field.dropType==='map'&&field.dropImpl){
					try{
						var items=eval(field.dropImpl);
						resolveShowFieldAndReturnField(formField,items);
						formField.items=items;
					}catch(e){
						
					}
				}
			}
			formField.readonly=field.readonly;
			formField.isShow=field.visiable;
			formFieldArray.push(formField);
		});
		 
		 /*$scope.formFieldArray = formFieldArray;*/
		 editService.init($scope, $http);
		 editService.setFormFields($scope,formFieldArray);
		 if(dicCategoryArr.length>0){
			 $scope.promise = httpService.get($scope,initParamsUrl+"/"+dicCategoryArr.join(",")+"/"+dicFieldArr.join(",")).success(function (params){
				 var _fieldMap=editService.getFieldMap($scope);
				 for(var i=0;i<params.length;i++){
		    			var formField = _fieldMap.get(params[i].fieldName);
		    			if(formField){
							resolveShowFieldAndReturnField(formField,params[i].params);
							formField.items = params[i].params;
		          	  	}
				 	}
				 $scope.load();
		    	});
		 }else{
			 $scope.load();
		 }
		 
		 
		 
	});
	$scope.load=function(){
		if($scope.busiId>0){
		   	editService.loadData($scope,loadFormDataUrl, $scope.busiId);
		 }
	}
   /***
    * 定义更新操作.
    */
   $scope.submit = function(){
   		editService.submitData($scope,saveDataUrl);
   }  
  
});

angular.module('huatek.controllers').controller('EasyFormWorkflowViewController', function ($scope, $location, $http, $routeParams,editService,$rootScope,$q,cacheService,$timeout) {
	 $scope.busiCode=$scope.passParams.busiCode;
	 $scope.taskId=$scope.passParams.taskId;
	 $scope.busiId=$scope.passParams.busiId;
	 $scope.taskKey=$scope.passParams.taskKey;
	 $scope.taskName=$scope.passParams.taskName;
	 $scope.processInstanceId=$scope.passParams.processInstanceId;
	 $scope.onlyView=$scope.passParams.onlyView;
	var loadFormConfigUrl = 'api_workflow/form/loadForm/'+$scope.busiCode;
	var loadFormDataUrl = 'api_workflow/form/loadData/'+$scope.busiCode;
	var saveDataUrl = 'api_workflow/form/saveData/'+$scope.busiCode;
	var initParamsUrl='api_workflow/form/initParams';
	var systemHeader=$scope.busiCode.substring(0,$scope.busiCode.indexOf('0'));
	httpService.get($scope,loadFormConfigUrl).success(function(formConfig){
		var groups=formConfig.formFieldGroupList;
		var fields=formConfig.formFieldConfigList;
		 var columnWrapArray = [];
		angular.forEach(groups,function(group,index){
			columnWrapArray.push(new multipleColumn(group.id,group.name));
		});
		$scope.columnWrapArray = columnWrapArray;
		var dicCategoryArr=[];
		var dicFieldArr=[];
		var formFieldArray = [];
		angular.forEach(fields,function(field,index){
			var formField=new FormElement(field.formFieldGroupId,field.name,field.code,field.type,field.require?1:0,field.length,field.model);
			if(field.model=='file'||field.model=='fileSingle'||field.model=='fileMultiple'){
				formField.systemHeader=systemHeader;
			}
			if(field.defaultValue){
				formField.value=field.defaultValue;
			}
			
			if(field.dropType){
				if(field.dropType==='url'){
					formField.dropDataUrl=field.dropImpl;
				}else if(field.dropType==='dic'&&field.dropImpl){
					
					/*2016年9月2日修改使用缓存*/

/*					dicCategoryArr.push(field.dropImpl);*/

/*					dicFieldArr.push(field.code);*/
					formField.dropDataUrl="dic."+field.dropImpl;
				}else if(field.dropType==='map'&&field.dropImpl){
					try{
						var items=eval(field.dropImpl);
						resolveShowFieldAndReturnField(formField,items);
						formField.items=items;
					}catch(e){
						
					}
				}
			}
			formField.readonly=true;
			formField.isShow=field.visiable;
			formFieldArray.push(formField);
		});
		 $rootScope.formFieldArray = formFieldArray;
		 editService.init($scope,  $http);
		 editService.setFormFields($scope,formFieldArray);
		 if(dicCategoryArr.length>0){
			 $scope.promise = httpService.get($scope,initParamsUrl+"/"+dicCategoryArr.join(",")+"/"+dicFieldArr.join(",")).success(function (params){
				 var _fieldMap=editService.getFieldMap($scope);
				 for(var i=0;i<params.length;i++){
		    			var formField = _fieldMap.get(params[i].fieldName);
		    			if(formField){
							resolveShowFieldAndReturnField(formField,params[i].params);
							formField.items = params[i].params;
		          	  	}
				 	}
				 $scope.load();
		    	});
		 }else{
			 $scope.load();
		 }
		 
		 
		 
	});
	$scope.load=function(){
		if($scope.busiId>0){
		   	editService.loadData($scope,loadFormDataUrl, $scope.busiId);
		 }
	}
   /***
    * 定义更新操作.
    */
   $scope.submit = function(){
   		editService.submitData($scope,saveDataUrl);
   }  
  
});


angular.module('huatek.controllers').controller('EasyFormPreviewController', function ($scope, $location, $http,httpService, editService,$routeParams,$rootScope,$q,cacheService,$timeout) {
	 $scope.busiCode=$scope.passParams.busiCode;
	 $scope.taskId=$scope.passParams.taskId;
	 $scope.busiId=$scope.passParams.busiId;
	 $scope.taskKey=$scope.passParams.taskKey;
	 $scope.taskName=$scope.passParams.taskName;
	 $scope.processInstanceId=$scope.passParams.processInstanceId;
	 $scope.onlyView=$scope.passParams.onlyView;
	var loadFormConfigUrl = 'api_workflow/form/loadForm/'+$scope.busiCode;
	var loadFormDataUrl = 'api_workflow/form/loadData/'+$scope.busiCode;
	var saveDataUrl = 'api_workflow/form/saveData/'+$scope.busiCode;
	var initParamsUrl='api_workflow/form/initParams';
	var systemHeader=$scope.busiCode.substring(0,$scope.busiCode.indexOf('0'));
	httpService.get($scope,loadFormConfigUrl).success(function(formConfig){
		var groups=formConfig.formFieldGroupList;
		var fields=formConfig.formFieldConfigList;
		 var columnWrapArray = [];
		angular.forEach(groups,function(group,index){
			columnWrapArray.push(new multipleColumn(group.id,group.name));
		});
		$scope.columnWrapArray = columnWrapArray;
		var dicCategoryArr=[];
		var dicFieldArr=[];
		var formFieldArray = [];
		angular.forEach(fields,function(field,index){
			var formField=new FormElement(field.formFieldGroupId,field.name,field.code,field.type,field.require?1:0,field.length,field.model);
			if(field.model=='file'||field.model=='fileSingle'||field.model=='fileMultiple'){
				formField.systemHeader=systemHeader;
			}
			if(field.defaultValue){
				formField.value=field.defaultValue;
			}
			
			if(field.dropType){
				if(field.dropType==='url'){
					formField.dropDataUrl=field.dropImpl;
				}else if(field.dropType==='dic'&&field.dropImpl){
					dicCategoryArr.push(field.dropImpl);
					dicFieldArr.push(field.code);
				}else if(field.dropType==='map'&&field.dropImpl){
					try{
						var items=eval(field.dropImpl);
						resolveShowFieldAndReturnField(formField,items);
						formField.items=items;
					}catch(e){
						
					}
				}
			}
			formField.readonly=field.readonly;
			formField.isShow=field.visiable;
			formFieldArray.push(formField);
		});
		 $rootScope.formFieldArray = formFieldArray;
		 editService.init($scope, $http);
		 editService.setFormFields($scope,formFieldArray);
		 if(dicCategoryArr.length>0){
			 $scope.promise = httpService.get($scope,initParamsUrl+"/"+dicCategoryArr.join(",")+"/"+dicFieldArr.join(",")).success(function (params){
				 var _fieldMap=editService.getFieldMap($scope);
				 for(var i=0;i<params.length;i++){
		    			var formField = _fieldMap.get(params[i].fieldName);
		    			if(formField){
							resolveShowFieldAndReturnField(formField,params[i].params);
							formField.items = params[i].params;
		          	  	}
		    		}
		    	});
		 }
		
	});

});
angular.module('huatek.controllers').controller('EasyFormWorkflowPrintController', function ($scope, $location, $http, $routeParams,editService,$rootScope, $q,cacheService,$timeout) {
	 $scope.busiCode=$scope.passParams.busiCode;
	 $scope.taskId=$scope.passParams.taskId;
	 $scope.busiId=$scope.passParams.busiId;
	 $scope.taskKey=$scope.passParams.taskKey;
	 $scope.taskName=$scope.passParams.taskName;
	 $scope.processInstanceId=$scope.passParams.processInstanceId;
	 $scope.onlyView=$scope.passParams.onlyView;
	var loadFormConfigUrl = 'api_workflow/form/loadForm/'+$scope.busiCode;
	var loadFormDataUrl = 'api_workflow/form/loadData/'+$scope.busiCode;
	var saveDataUrl = 'api_workflow/form/saveData/'+$scope.busiCode;
	var initParamsUrl='api_workflow/form/initParams';
	var systemHeader=$scope.busiCode.substring(0,$scope.busiCode.indexOf('0'));
	httpService.get($scope,loadFormDataUrl+"/"+$scope.busiId).success(function (formData){
		httpService.get($scope,loadFormConfigUrl).success(function(formConfig){
			var groups=formConfig.formFieldGroupList;
			var fields=formConfig.formFieldConfigList;
			 var columnWrapArray = [];
			angular.forEach(groups,function(group,index){
				if(group.name==='审批信息'||group.name==='审核信息'){
					return;
				}
				columnWrapArray.push(new multipleColumn(group.id,group.name));
			});
			
			for(var i=0;i<columnWrapArray.length;i++){
				var group=columnWrapArray[i];
				group.html="<table border='1' style='width:900px;' >";
				var cellCount=0;
				for(var j=0;j<fields.length;j++){
					var field=fields[j];
					if(field.model=='file'||field.model=='fileMultiple'||field.model=='fileSingle'){
						continue;
					}
					var value="";
					for(var key in formData){
						if(key==field.code){
							value=formData[key];
							break;
						}
					}
					if(field.visiable&&field.formFieldGroupId==group.id&&field.type!='file'){
						 if(field.dropType==='dic'&&field.dropImpl){
							var items=cacheService.getData("dic."+field.dropImpl);
							for(var item in items){
								if(items[item].code==value){
									value=items[item].name;
									break;
								}
							}
						}else if(field.dropType==='map'&&field.dropImpl){
							try{
								var items=eval(field.dropImpl);
								for(var item in items){
									if(items[item].code==value){
										value=items[item].name;
										break;
									}
								}
							}catch(e){
							}		
						}
						if(field.model=='applyLink'){
							value=(value&&value.split('|').length>0)?value.split('|')[0]:''
						}
						if(field.model=='longtext'){
							if(cellCount%2==1){
								group.html+="<td class='celltitle' style='padding-left:10px; font-weight:bold; height:50px; width:150px;'></td><td class='cellValue' style='padding-left:10px;'></td></tr>";
								cellCount++;
							}
							group.html+="<tr><td class='celltitle' style='padding-left:10px; font-weight:bold; height:50px; width:150px;'>"+field.name+"</td><td class='cellValue' style='padding-left:10px;' colspan='3'>"+value+"</td></tr>"
						}else if(field.model=='textarea'){
							if(cellCount%2==1){
								group.html+="<td class='celltitle' style='padding-left:10px; font-weight:bold; height:50px; width:150px;'></td><td class='cellValue' style='padding-left:10px;'></td></tr>";
								cellCount++;
							}
							group.html+="<tr><td class='celltitle' style='padding-left:10px; font-weight:bold; height:50px; width:150px;'>"+field.name+"</td><td class='cellTextarea' style='padding-left:10px;' colspan='3'>"+value+"</td></tr>"
						}else if(field.model=='editor'){
							if(cellCount%2==1){
								group.html+="<td class='celltitle' style='padding-left:10px; font-weight:bold; height:50px; width:150px;'></td><td class='cellValue' style='padding-left:10px;'></td></tr>";
								cellCount++;
							}
							group.html+="<tr><td class='celltitle' style='padding-left:10px; font-weight:bold; height:50px; width:150px;'>"+field.name+"</td><td class='cellEdtor' style='height:200px;' colspan='3'>"+value+"</td></tr>"
						}else if(cellCount%2==0){
							group.html+="<tr><td class='celltitle' style= 'font-weight:bold; height:50px; width:150px;padding-left:10px;'>"+field.name+"</td><td class='cellValue' style='padding-left:10px;'>"+value+"</td>";
							cellCount++;
						}else{
							group.html+="<td class='celltitle' style=' font-weight:bold; height:50px; width:150px;padding-left:10px;'>"+field.name+"</td><td class='cellValue' style='padding-left:10px;'>"+value+"</td></tr>";
							cellCount++;
						}
					}
				}
				group.html+="</table>";
			};
//			angular.forEach(fields,function(field,index){
//				var formField=new FormElement(field.formFieldGroupId,field.name,field.code,field.type,field.require?1:0,field.length,field.model);
//				if(field.model=='file'||field.model=='fileSingle'||field.model=='fileMultiple'){
//					formField.systemHeader=systemHeader;
//					formFieldArray.push(formField);
//				}
//			}); 
			
			
			
			httpService.get($scope,'api_workflow/myjob/dueList/'+$scope.processInstanceId)
			   .success(function (response) {
				   var group=new multipleColumn(99,"审批信息");
					group.html="<table border='1' style='width:900px;'>";
					group.html+="<tr><td class='cpTitle' style='font-weight:bold;text-align:center;' width='150px'>审批环节</td><td class='cpTitle' style='font-weight:bold;text-align:center;' width='150px'>审批人</td><td class='cpTitle' style='font-weight:bold;text-align:center;' width='150px' >审批结果</td><td class='cpTitle' style='font-weight:bold;text-align:center;' >审批意见</td></tr>";
				  for(var i=0;i<response.length;i++){
					  var item=response[i];
					  group.html+="<tr height='100px'><td>"+item.name+"</td><td>"+(item.assigneeName?item.assigneeName:'')+"</td><td>"+(item.result?item.result:'')+"</td><td>"+(item.resultMessage?item.resultMessage:'')+"</td></tr>";
				  }
				  group.html+="</table>";
				  columnWrapArray.push(group);
				   
			});
			$scope.columnWrapArray = columnWrapArray;
			
		});
	});

   $scope.print = function(){
	   
	   $("div#easyFormPrintArea").printArea();
   }  
  
});

angular.module('huatek.controllers').controller('ApproveMessageTemplateController', function ($scope, $location, $http, httpService,$routeParams,$rootScope,$q,cacheService,$timeout) {
	var resultMessage=$scope.$parent.resultMessage||$scope.$parent.fieldMap.get('resultMessage');
	var resultMessageTemplate=$scope.$parent.resultMessageTemplate||$scope.$parent.fieldMap.get('resultMessageTemplate');
	$scope.templateChange=function(value){
		resultMessage.value=value.split("|")[1];
	}
	$scope.removeTemplate=function(){
		if(resultMessageTemplate&&resultMessageTemplate.value){
			var templateId=resultMessageTemplate.value.split("|")[0];
			httpService.post($scope,"api_workflow/form/removeTemplate/"+templateId).success(function(){
				$scope.loadTemplate();
			});
		}else{
			bootbox.alert("请选择要删除的模版！");
		}
		
	}
	$scope.addTemplate=function(){
		if(!resultMessage.value||resultMessage.value.trim()==""){
			bootbox.alert("审核意见为空，不能保存为模版！");
			return;
		}
		var value=resultMessage.value.trim();
		var templateName = prompt("请输入模版名称:","新模版");
		if(!templateName||templateName.trim()==""){
			bootbox.alert("模版名称不能为空！");
			return;
		}
		httpService.post($scope,"api_workflow/form/addTemplate/"+$scope.$parent.busiCode,{name:templateName,content:value}).success(function(){
			$scope.loadTemplate();
		})
	}
	
	$scope.loadTemplate=function(){
		httpService.get($scope,"api_workflow/form/loadTemplate/"+$scope.$parent.busiCode).success(function(response){
			if(typeof resultMessageTemplate == 'object'){
				resolveShowFieldAndReturnField(resultMessageTemplate,response);
				resultMessageTemplate.items=response;
			}
		});
	}
	$scope.loadTemplate();
});


