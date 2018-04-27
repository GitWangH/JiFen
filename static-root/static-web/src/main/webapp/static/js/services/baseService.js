'use strict';

/*查询条件保存对象*/
var queryValues={};

angular.module('huatek.services', []).factory('ShopArr', function ($resource) {
    return $resource('static/directives/oi-select/src/shopArr.json', {}, {
            query: {method: 'GET', cache: true, isArray: true}
        }
    );
})





