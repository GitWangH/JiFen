'use strict';
   
angular.module('HuatekApp').controller('ChangePasswordController', function ($scope, $http, editService, httpService) {
  
  $scope.title = '修改密码';
     
   var editDataUrl = 'api/fwAccount/changePassword';
   var columnWrapArray = [];
    columnWrapArray.push(new multipleColumn(1,'基本信息'));
     columnWrapArray.push(new multipleColumn(2,'基本信息'));
      columnWrapArray.push(new multipleColumn(3,'基本信息'));
    $scope.columnWrapArray = columnWrapArray;
   /*定义用户录入数据FormElement(title, name, type, require, max, model, event, min)*/
   var formFieldArray = [];
   formFieldArray.push(new FormElement(1,'原密码','acctOldPsw','string',1,'30','password'));
   formFieldArray.push(new FormElement(2,'新密码','acctNewPsw','string',1,'30','password'));
   formFieldArray.push(new FormElement(3,'再次输入新密码','acctNewPsw','string',1,'30','password'));
   
      
   editService.init($scope);
   
      
   editService.setFormFields($scope, formFieldArray); 
   
   $scope.confirm = function(){
    if(!editService.isAllowPost($scope)){
      return;
    }
    var data = editService.getPostData($scope);    
    httpService.post($scope, editDataUrl, data).success(function (response) {
          $scope.jsPanel.close();
    });
  };

  $scope.back = function(){
     $scope.jsPanel.close();
  }
   
});