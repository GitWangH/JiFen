/*缓存服务*/
angular.module('huatek.services').service("cacheService",['$cacheFactory','$http','$localStorage',function($cacheFactory,$http,$localStorage){
	this.cacheLongTime=24*60*60*1000;
	this.cachePrefix=["dic","region","config"];
	this.bindFieldData=function(field,key){
		if(key==undefined){
			key=field.dropDataUrl;
		}
		if(key==undefined||key==null||key==""){
			return;
		}
		key+="";
		var prefix=null;
		for(var item in this.cachePrefix) {
			if(key.indexOf(this.cachePrefix[item]+".")==0){
				prefix=this.cachePrefix[item];
				break;
			}
		}
		if(prefix==null){
			return;
		}
		
		if(prefix=="dic"){
			this._getDicData(key,field);
			
		}else if(prefix=="region"){
			this._getRegionData(key,field);
		}
	};
	this.getData=function(key){
		var prefix=null;
		for(var item in this.cachePrefix) {
			if(key.indexOf(this.cachePrefix[item]+".")==0){
				prefix=this.cachePrefix[item];
				break;
			}
		}
		if(prefix==null){
			return null;
		}
		if(prefix=="dic"){
			var dic=this._getDicData(key);
			return dic;
		}else if(prefix=="region"){
			var region=this._getRegionData(key);
			return region;
		}else if(prefix=="config"){
			var value=this._getConfigData(key);
			return value;
		}
	};
	this._getDicData=function(key,field){
		var cache=$cacheFactory.get("dic");
		if(!cache){
			cache=$cacheFactory("dic");
		}
		var dic=cache.get(key);
		if(dic){
			if(field){
				resolveShowFieldAndReturnField(field,dic);
				field.items=dic;
			}else{
				return dic;
			}
			
		}else{
			if($localStorage[key]&&$localStorage[key].expire>new Date().getTime()){
				dic=$localStorage[key].data;
				cache.put(key,dic);
				if(field){
					resolveShowFieldAndReturnField(field,dic);
					field.items=dic;
				}else{
					return dic;
				}
			}else{
				var dicKindName=key.substring(4)
				$.ajax({
	                url : "api_cmd/property/getDictionaryCache/"+dicKindName,
	                cache : false,
	                async : field?true:false,
	                type : "GET",
	                dataType : 'json',
	                success : function (response){
	                	dic=response;
	                	if(field){
	    					resolveShowFieldAndReturnField(field,dic);
	    					field.items=dic;
	    				}
						cache.put(key,dic);
						var expireDate = new Date();
						expireDate.setTime(expireDate.getTime() + this.cacheLongTime);
						$localStorage[key]={'data':dic,'expire': expireDate.getTime()};
	                }
	            });
				return dic;
			}
		}
	};
	this.getPropertyName = function(key,value){
		var name = "";
		var items = this.getData(key);
		if(items && items.length > 0){
			for (var i = 0; i < items.length; i++) {
				if(items[i].code == value){
					name = items[i].name;
					break;
				}
			}
		}
		return name;
	};
	this._getRegionData=function(key,field){
		var cache=$cacheFactory.get("region");
		if(!cache){
			cache=$cacheFactory("region");
		}
		var region=cache.get(key);
		if(region){
			if(field){
				resolveShowFieldAndReturnField(field,region);
				field.items=region;
			}else{
				return region;
			}
		}else{
			if($localStorage[key]&&$localStorage[key].expire>new Date().getTime()){
				region=$localStorage[key].data;
				cache.put(key,region);
				if(field){
					resolveShowFieldAndReturnField(field,region);
					field.items=region;
				}else{
					return region;
				}
			}else{
				var regionCode=key.substring(6)
				$.ajax({
	                url : "api_cmd/base/baseRegion/getRegionCache/"+regionCode,
	                cache : false,
	                async : field?true:false,
	                type : "GET",
	                dataType : 'json',
	                success : function (response){
	                	region=response;
	                	if(field){
	        				resolveShowFieldAndReturnField(field,region);
	        				field.items=region;
	        			}
						cache.put(key,region);
						var expireDate = new Date();
						expireDate.setTime(expireDate.getTime() + this.cacheLongTime);
						$localStorage[key]={'data':region,'expire': expireDate.getTime()};
	                }
	            });
				return region;
			}
		}
	};
	this._getConfigData=function(key){
		var cache=$cacheFactory.get("config");
		if(!cache){
			cache=$cacheFactory("config");
		}
		var config=cache.get(key);
		if(config){
			return config
			
		}else{
			
				var configKey=key.substring(7)
				
				$.ajax({
	                url : "api/config/queryConfig/"+configKey,
	                cache : false,
	                async : false,
	                type : "GET",
	                dataType : 'json',
	                success : function (response){
	                	config=response.value;
	                	
						cache.put(key,config);
	                }
	            });
				return config;
			
		}
	};
}]);