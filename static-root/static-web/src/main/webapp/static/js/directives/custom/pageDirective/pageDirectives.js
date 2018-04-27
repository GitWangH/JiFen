
angular.module('huatek.directives')
.directive('search',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/search.html',
        restrict:'E',
        replace:true
    }
}).directive('reportSearchOne',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/reportSearchOne.html',
        restrict:'E',
        replace:true
    }
}).directive('reportSearchTwo',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/reportSearchTwo.html',
        restrict:'E',
        replace:true
    }
}).directive('reportSearchThree',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/reportSearchThree.html',
        restrict:'E',
        replace:true
    }
}).directive('btn',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/btn.html',
        restrict:'E',
        replace:true
    }
}).directive('page',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/page.html',
        restrict:'E',
        replace:true
    }
}).directive('alert',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/alert.html',
        restrict:'E',
        replace:true
    }
}).directive('footer',function(){
    return {
        templateUrl: 'static/js/directives/custom/pageDirective/footer.html',
        restrict:'E',
        replace:true
    }
}).directive("huatekUploadify", function() {
    return {
        require: '?ngModel',
        restrict: 'A',
        priority: 1000,
        link: function ($scope, element, attrs, ngModel) {
        	console.log(attrs);
            var opts = angular.extend({}, $scope.$eval(attrs.nlUploadify));
            var optsEx =angular.extend({}, $scope.$eval(attrs.huatekUploadify));/*获取扩展参数*/
            var businessId=(optsEx.businessId!=null&&optsEx.businessId!=undefined)?optsEx.businessId:"";/*业务id*/
            var busiType=(optsEx.busiType!=null&&optsEx.busiType!=undefined)?optsEx.busiType:"";/*业务类型id*/
            var param={
                    'fileObjName': opts.fileObjName || 'upfile',
                    'auto': opts.auto!=undefined?opts.auto:true,
                    'swf': opts.swf || 'static/css/theme-default/uploadify.swf',
                    'uploader': opts.uploader || 'upload.do?actionMethod=process',/*图片上传方法*/
                    'buttonText': opts.buttonText || '上传文件',
                    'width': opts.width || 80,
                    'height': opts.height || 25,
                    'formData':{businessId:optsEx.businessId,busiType:busiType},
                    'onUploadSuccess': function (file, d, response) {
                    	if (ngModel) {
                    		var result=eval("[" + d + "]")[0];
                    		var visitUrl=result.visitUrl;/*获取访问地址*/
                    		var viewDivId=optsEx.viewDivId;/*获取显示图片的div*/
                    		var extFileName=result.extFileName;/*获取扩展名*/
                    		var fileId=result.fileId;/*返回文件的id  */
                    		if('true'==result.ifSuccess){
                          		if(visitUrl!=null&&visitUrl!=undefined&&viewDivId!=null&&viewDivId!=undefined&&("img"==extFileName||"png"==extFileName) )
                        		{
                        			var img="<span style='margin:2px;float:left'><img src='"+visitUrl+"'/></span>";
                        			var html=$("#"+viewDivId).html();
                        			$("#"+viewDivId).html(img+html);/*添加图片*/
                        		}
                    		}

                    		
  
                        }
                    }};
            element.uploadify(param);
        }
    };
}).directive('fileModel', ['$parse', function ($parse) {

    return {

        restrict: 'A',

        link: function(scope, element, attrs) {

            var model = $parse(attrs.fileModel);

            var modelSetter = model.assign;

            

            element.bind('change', function(){

                scope.$apply(function(){

                    modelSetter(scope, element[0].files[0]);

                });

            });

        }

    };

}]);
