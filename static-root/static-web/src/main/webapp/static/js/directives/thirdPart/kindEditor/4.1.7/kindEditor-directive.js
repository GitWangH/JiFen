

(function () {
    'use strict';

    var kindeditor = angular.module('kindeditor', ['oc.lazyLoad']);

    kindeditor.directive('kindEditor', ['$ocLazyLoad',function ($ocLazyLoad) {
    	return{
    		restrict: 'AE',
    		require: '?ngModel',
    		link: function (scope, element, attrs, ctrl) {
    				var _initContent, editor;
    				var fexUE = {
    						initEditor: function () {
    							editor = KindEditor.create(element[0],
    									{
    								width: '100%',
    								height: '400px',
    								resizeType: 1,
    								allowUpload : true, 
    								allowFileManager : true,
    								uploadJson:"api_file/upload.do?actionKindMethod=process",
    								extraFileUploadParams:{ businessId: getUUID() ,systemHeader:'busi'},
    								afterChange: function () {
    									ctrl.$setViewValue(this.html());
    								},
    							   items : [ 'undo', 'redo','preview','wordpaste','|', 'justifyleft', 'justifycenter',
    										'justifyright', 'justifyfull', 'insertorderedlist',
    										'insertunorderedlist', 'indent', 'outdent', '|',
    										'clearhtml', 'quickformat', 'selectall', 'link', 'unlink','|',
    										'formatblock', 'fontname', 'fontsize', '|', 'forecolor',
    										'hilitecolor', 'bold', 'italic', 'underline',
    										'strikethrough', 'lineheight', 'removeformat', '|',
    										'image',  'table', 'hr', 
    										'pagebreak' ],
    									});
    						},
    						setContent: function (content) {
    							if (editor) {
    								editor.html(content);
    							}
    						}
    				}
    				if (!ctrl) {
    					return;
    				}
    				_initContent = ctrl.$viewValue;
    				ctrl.$render = function () {
    					_initContent = ctrl.$isEmpty(ctrl.$viewValue) ? '' : ctrl.$viewValue;
    					fexUE.setContent(_initContent);
    				};
    				fexUE.initEditor();
    		}
    	};
    }]);

})();





