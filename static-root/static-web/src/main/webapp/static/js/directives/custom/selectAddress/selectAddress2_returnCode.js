(function() {
  var app;
  app = angular.module('selectAddress', []);
  app.directive('selectAddress', function($http, $q, $compile) {
    var cityURL, addressNameURL, templateURL;
    addressNameURL = URL_PATH.CMD_HEADER + '/public/cmd/getAddress/';
    templateURL =  'static/js/directives/custom/selectAddress/template/address_template.html';
    cityURL =  URL_PATH.CMD_HEADER + '/public/cmd/getAddresses';
    return {
      require: '?^addressCode',
      restrict: 'A',
      scope: {
        p: '=',
        a: '=',
        c: '=',
        addressCode: '=',
        ngModel: '='
      },
      link: function(scope, element, attrs) {
    	  
    	  /*监听addressCode值改变*/
    	  scope.$watch('addressCode', function(newV) {
    		  if(!scope.ngModel && newV){
    			  $http.get(addressNameURL+newV).success(function(data) {
    				  scope.ngModel = data.provinceName + " " + data.cityName + " " + data.districtName;
    				  scope.p = data.provinceName;
    				  scope.c = data.cityName;
    				  scope.a = data.districtName;
    			  });
    		  }
           });
    	  
    	  /*初始化模板*/
          $http.get(templateURL).success(function(template) {
              var $template;
              $template = $compile(template)(scope);
              $('body').append($template);
              popup.element = $($template[2]);
              popup.init();
          });
          
          
        var popup = {
          element: null,
          backdrop: null,
          show: function() {
            this.element.addClass('active');
          },
          hide: function() {
            this.element.removeClass('active');
            return false;
          },
          resize: function() {
            if (!this.element) {
              return;
            }
            this.element.css({
              'margin-left': -this.element.width() / 2
            });
            return false;
          },
          focus: function() {
            $('[ng-model="d"]').focus();
            return false;
          },
          init: function() {
            element.on('click keydown', function(event) {
        	  if(undefined == scope.provinces){
        		  getAddresses();
        	  }
        	  popup.show();
              event.stopPropagation();
              return false;
            });
            
            jQuery.fn.isChildAndSelfOf = function(b){
            	return (this.closest(b).length > 0);
        	};
            	
            $('body').on('click',function(event){
            	if(!$(event.target).isChildAndSelfOf (popup.element)){
            		popup.hide();
            	};
            	event.stopPropagation();
            });
            return setTimeout((function(_this) {
              return function() {
                _this.element.show();
                return _this.resize();
              };
            })(this), 500);
          }
        };
       
        scope.aSet = {
	        p: function(p,pCode) {
	          scope.p = p;
	          scope.pCode = pCode;
	          scope.c = null;
	          scope.a = null;
	        },
	        c: function(c,cCode) {
	          scope.c = c;
	          scope.cCode = cCode;
	          scope.a = null;
	        },
	        a: function(a,aCode) {
	          scope.a = a;
	          scope.aCode = aCode
	          popup.focus();
	        }
	      };
        
        /*获取地址数据*/
        function getAddresses(){
        	$http.get(cityURL).success(function(data) {
        		scope.provinces = data;
        		
                scope.clear = function() {
                  scope.p = null;
                  scope.c = null;
                  scope.a = null;
                  scope.addressCode = null;
                  scope.ngModel = null;
                };
                
                scope.submit = function() {
              		scope.ngModel = '';
              		scope.addressCode = '';
                    if (scope.p && scope.pCode) {
                      scope.ngModel += scope.p;
                      scope.addressCode += scope.pCode;
                    }else{
                    	submitTips('请选择省！','warning');
                    	return;
                    }
                    if (scope.c && scope.cCode) {
                      scope.ngModel += " " + scope.c;
                      scope.addressCode += " " + scope.cCode;
                    }else{
                    	submitTips('请选择市！','warning');
                    	return;
                    }
                    if (scope.a && scope.aCode) {
                      scope.ngModel += " " + scope.a;
                      scope.addressCode += " " + scope.aCode;
                    }else{
                    	submitTips('请选择区/县！','warning');
                    	return;
                    }
                   popup.hide();
                };
                
                scope.$watch('p', function(newV) {
                  var v, _i, _len, _results;
                  if (newV) {
                    _results = [];
                    for (_i = 0, _len = data.length; _i < _len; _i++) {
                      v = data[_i];
                      if (v.p === newV) {
                        _results.push(scope.cities = v.c);
                      }
                    }
                    return _results;
                  } else {
                    return scope.cities = [];
                  }
                });
                
                scope.$watch('c', function(newV) {
                  var v, _i, _len, _ref, _results;
                  if (newV) {
                    _ref = scope.cities;
                    _results = [];
                    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                      v = _ref[_i];
                      if (v.n === newV) {
                        _results.push(scope.dists = v.a);
                      }
                    }
                    return _results;
                  } else {
                    return scope.dists = [];
                  }
                });
                
                
        	});
        };
      }
    };
  });

}).call(this);

