var module = angular.module('huatek.repeat', []);
module.directive('repeatFinish',function(){
    return {
        link: function(scope,element,attr){
            if(scope.$last == true){
                console.log('ng-repeat执行完毕')
                scope.$emit('repeat:finished');
            }
        }
    }
});