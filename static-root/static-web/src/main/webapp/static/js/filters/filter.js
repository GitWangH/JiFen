'use strict';
var app = angular.module('huatek.filters', []).filter('range', function() {
    return function(input, total) {
        total = parseInt(total);
        for (var i = 0; i < total; i++)
            input.push(i);
        return input;
    };
}).filter("resover", function() {
    return function(input, dataMap) {
        return dataMap.get(input);
    }
}).filter("priceFilter", function() { /*价格数据过滤器-暂时没用到此过滤器 2017-10-27*/
    var filterFunction = function(priceVal) {
        if (priceVal == 0 || !priceVal) {
            return "0.0000";
        } else {
            /*var decimalFormat = new DecimalFormat();
            decimalFormat.applyPattern("00.00");*/
            return roundMethod(priceVal, 4);
        }
    };
    return filterFunction;
});