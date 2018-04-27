(function() {
  var app;
  app = angular.module('selectAddress', []);
  app.directive('selectAddress', ['$http', '$q', '$compile',function($http, $q, $compile) {
    var cityURL, delay, templateURL;
    delay = $q.defer();
    templateURL =  'static/js/directives/custom/selectAddress/template/address_template.html';
    cityURL =  URL_PATH.CMD_HEADER + '/public/cmd/getAddresses';
    return {
      restrict: 'A',
      scope: {
        p: '=',
        a: '=',
        c: '=',
        d: '=',
        ngModel: '='
      },
      link: function(scope, element, attrs) {
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
        	  if(undefined == scope.p && undefined == scope.provinces){
        		  getAddresses();
        	  }
        	  popup.show();
              event.stopPropagation();
              return false;
            });
            this.element.on('click', function(event) {
              return event.stopPropagation();
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
	        p: function(p) {
	          scope.p = p;
	          scope.c = null;
	          scope.a = null;
	        },
	        c: function(c) {
	          scope.c = c;
	          scope.a = null;
	        },
	        a: function(a) {
	          scope.a = a;
	          popup.focus();
	        }
	      };
        
        /*获取地址数据*/
        function getAddresses(){
        	$http.get(cityURL).success(function(data) {
        		scope.provinces = data;
        		
        		if(scope.ngModel){
           		 	var ngModelArr = scope.ngModel.split(" ");
          		    if (!scope.p && ngModelArr[0]) {
                      scope.p = ngModelArr[0];
                    }
                    if (!scope.c && ngModelArr[1]) {
                      scope.c = ngModelArr[1];
                    }
                    if (!scope.a && ngModelArr[2]) {
                  	  scope.a = ngModelArr[2];
                    }
        		}
                
                scope.clear = function() {
                  scope.p = null;
                  scope.c = null;
                  scope.a = null;
                  scope.ngModel = null;
                };
                scope.submit = function() {
                	
              		scope.ngModel = '';
                    if (scope.p) {
                      scope.ngModel += scope.p;
                    }
                    if (scope.c) {
                      scope.ngModel += " " + scope.c;
                    }
                    if (scope.a) {
                      scope.ngModel += " " + scope.a;
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
                	  if(_ref){
                          for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                            v = _ref[_i];
                            if (v.n === newV) {
                              _results.push(scope.dists = v.a);
                            }
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
  }]);

}).call(this);

