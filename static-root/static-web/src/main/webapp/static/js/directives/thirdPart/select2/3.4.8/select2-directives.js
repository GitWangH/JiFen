/**
 * select2封装
 * @param {scope} ng-model 选中的ID
 * @param {scope} select2-model 选中的详细内容
 * @param {scope} config 自定义配置
 * @param {String} [query] 内置的配置 (怎么也还得默认一个config)
 * @example
 * <input select2 ng-model="a" select2-model="b" config="config" type="text" placeholder="占位符" />
 * <input select2 ng-model="a" select2-model="b" config="default" query="member" type="text" placeholder="占位符" />
 * <select select2 ng-model="b" class="form-control"></select>
 */
angular.module('huatek.select2', []).directive('select2', function (select2Query) {
    return {
        restrict: 'A',
        scope: {
            config: '=',
            ngModel: '=',
            select2Model: '=',
            select2Items: '='
        },
        link: function (scope, element, attrs) {
        	
            var tagName = element[0].tagName,
                config = {
                    allowClear: true,
                    multiple: !!attrs.multiple,
                    placeholder: attrs.placeholder || ' '   
                };
            	  
            var dataMap = new Map();
            if(tagName === 'SELECT') {
                var $element = $(element);
                delete config.multiple;
                $element
                    .prepend('<option value=""></option>')
                    .val('')
                    .select2(config);
                scope.$watch('ngModel', function (newVal) {
                    setTimeout(function () {
                        $element.find('[value^="?"]').remove();    
                        $element.select2('val', newVal);
                    },1000);	
                }, true);
                
                $element.on('click', function () {
                	if(dataMap.size() < 1){
                		dataMap = getMap(scope.select2Items,'_returnField');
                	}
                	if(null != $element.select2('data')){
                		var data = dataMap.get($element.select2('data').id);
                    	console.log(dataMap);
                    	data._componentsName = attrs.name;
                    	scope.$emit('select2:selected',data);
                	}
                });
                
                return false;
            }

            if(tagName === 'INPUT') {
                var $element = $(element);
                if(attrs.query) {
                    scope.config = select2Query[attrs.query]();
                }

                scope.$watch('config', function () {
                    angular.extend(config, scope.config);
                    $element.select2('destroy').select2(config);
                }, true);

                $element.on('change', function () {
                    scope.$apply(function () {
                        scope.select2Model = $element.select2('data');
                    });
                });

                scope.$watch('select2Model', function (newVal) {
                    $element.select2('data', newVal);
                }, true);

                scope.$watch('ngModel', function (newVal) {
                    if(config.ajax || config.multiple) { return false }
                    $element.select2('val', newVal);
                }, true);
            }
        }
    }
}).factory('select2Query', function ($timeout) {
    return {
        testAJAX: function () {
    	var config = {
                minimumInputLength: 1,
                ajax: {
                    url: "http://localhost:8888/static/directives/select2/testJson.json",
                    dataType: 'jsonp',
                    data: function (term) {
                        return {
                            q: term,
                            page_limit: 10,
                            apikey: "ju6z9mjyajq2djue3gbvv26t"
                        };
                    },
                    results: function (data, page) {
                        return {results: data.movies};
                    }
                },
                formatResult: function (data) {
                    return data.title;
                },
                formatSelection: function (data) {
                    return data.title;
                }
            };

            return config;
        }
    }
});

/*as.controller('appCtrl', function ($scope, $timeout) {
    $scope.config1 = {
        data: [],
        placeholder: '尚无数据'
    };

    $timeout(function () {
        $scope.config1.data = [{id:1,text:'bug'},{id:2,text:'duplicate'},{id:3,text:'invalid'},{id:4,text:'wontfix'}]
        $scope.config1.placeholder = '加载完毕'
    }, 1000);


    $scope.config2 = [
        {id: 6, text: '来自ng-repeat'},
        {id: 7, text: '来自ng-repeat'},
        {id: 8, text: '来自ng-repeat'}
    ];

    $scope.config3 = {
        data: [{id:1,text:'bug'},{id:2,text:'duplicate'},{id:3,text:'invalid'},{id:4,text:'wontfix'}]
    };

});*/

