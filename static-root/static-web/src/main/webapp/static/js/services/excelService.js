/*excel服务*/
angular.module('huatek.services').service("excelService",function($rootScope,shareData,listService,$window,$http){
	var _scope = $rootScope;
	
	this.FILE_SERVICE="api_file/excel.do";
	this.up=function(type,params,isMutil,callback){
		
		shareData.params=params||{};
		shareData.params["actionMethod"] = type;
		shareData.url=this.FILE_SERVICE;
		shareData.isMutil=isMutil;
		var pop= {
   		     title: "excel上传",
   		     
   		  onclosedFun:callback,
   		     templateView: "static/business/cmd/excelImport/templates/template_excelUpload.html",
   		     
   		 };
		listService.popPanel(_scope,pop);
	};
	this.down=function(busiCode,filename){
		var url=this.FILE_SERVICE+"?actionMethod=download&busiCode="+busiCode+"&filename="+filename+"&t="+(new Date().getTime());
		window.location.href=url;
	};
	this.imp=function(params,popCallBack,url){
	
		shareData.params=params||{};
		shareData.params["actionMethod"]='import';
		shareData.url=url||this.FILE_SERVICE;
		var pop= {
   		     title: "excel导入",
   		     
   		  onclosedFun:popCallBack,
   		     templateView: "static/business/cmd/excelImport/templates/template_excelImport.html",
   		     
   		 };
		listService.popPanel(_scope,pop);
	};


	this.exp = function(busiCode,pageData,userParams){
		var url= this.FILE_SERVICE+"?actionMethod=export";
		var parmaDatas = {"busiCode":busiCode};
		if(pageData&&pageData.queryParamList){
			var queryParamList = pageData.queryParamList;
			for(var i=0;i<queryParamList.length;i++){
				var queryParam = queryParamList[i];
				var name = queryParam.name;
				var value = queryParam.value;
				var params = queryParam.params;
				
				/*针对日期组件名字相同问题处理（把第二次出现的名字后面加'_1'）*/
				for(var key in parmaDatas){
					if(name==key){
						name = name + '_1';
					}
				}
				
				if(undefined!=value&&value!=""){
					parmaDatas[name] = value;
				}else if(undefined!=params&&params!=""){
					parmaDatas[name] = params;
				}
			}
		}
		
		if(userParams){
			for (var key in userParams){
				parmaDatas[key] = userParams[key];
			}
		}
		
		_scope.promise=$http({
            method: 'POST',
            url: url,
            
            /*async: false,*/
            headers: {'Content-Type': undefined },
            transformRequest: function (data) {
                var formData = new FormData();
                formData.append("queryData", angular.toJson(data.parmaDatas));
                return formData;
            },
            data: { parmaDatas: parmaDatas}
        })
        .success(function (data, status, headers, config) {
        	var dowurl = "api_file/excel.do?actionMethod=dowExp&busiCode="+busiCode+"&filePath="+data.filepath;
        	
        	/*submitTips('导出数据成功','success');*/
            window.location.href = dowurl;
        })
        .error(function (data, status, headers, config) {
        	
        	/*submitTips('导出数据失败','error');*/
        	/*如果用户多次点击会后面请求无法正常导出，所以去掉错误提示框*/
        	console.log("导出数据失败！");
        });
		
	};
	this.del=function(busiCode,callback){
		$http.get(this.FILE_SERVICE+"?actionMethod=delete&busiCode="+busiCode).success(function(data){
			if(callback){
				callback(data);
			}
			
		}) ;
	}

});

