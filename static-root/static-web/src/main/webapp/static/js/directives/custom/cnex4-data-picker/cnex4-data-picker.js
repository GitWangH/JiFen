'use strict';
var cnex4_dataPicker_url = URL_PATH.PUBLIC_HEADER+'/getOrg';
angular.module('huatek.cnex4.dataPicker', ['ngAnimate', 'mgcrea.ngStrap'])
	.config(function($asideProvider) {
		  angular.extend($asideProvider.defaults, {
		    container: 'body',
		    html: true
		  });
	})
	.controller("TreeController", ["$http","$scope",'$rootScope', function($http,$scope,$rootScope) {
		if($scope.treeType == undefined || $scope.treeType == null){
			$scope.treeType = 'type5UnAuth';
		}
		
		/**
		 * 加载数据
		 */
		if(undefined == $scope.tree || $scope.tree.length < 1){
			/*switch($scope.treeType){
				case 'type3':
					$scope.tree = $rootScope.orgType3List;
					break;
				case 'type4':
					$scope.tree = $rootScope.orgType4List;
					break;
				case 'type5':
					$scope.tree = $rootScope.reportUserCompanyList;
					break;
				case 'type3UnAuth':
					$scope.tree = $rootScope.orgType3ListForNoAuthority;
					break;
				case 'type4UnAuth':
					$scope.tree = $rootScope.orgType4ListForNoAuthority;
					break;
				case 'type5UnAuth':
					$scope.tree = $rootScope.reportCompanyList;
					break;
				default :
					$scope.tree = $rootScope.reportCompanyList;
					break;
			}*/
			$http.get("/api/org/getCompany").success(function(response){	
				$scope.tree = response;
				/*如果为编辑页面,则需要选中回填数据*/
				if(undefined != $scope.checkItemNames && "" != $scope.checkItemNames){
					/*递归迭代$scope.tree*/
					var checkChildren = function(c) {
						if($scope.checkItemNames.indexOf(c.name) > -1){
							c.checked = true;
						}
						angular.forEach(c.children, function(c) {
							if($scope.checkItemNames.indexOf(c.name) > -1){
								c.checked = true;
							}
							checkChildren(c);
						});
					}
					checkChildren($scope.tree[0]);
				}
			});
		}

		$scope.showCheckList = function() {
			/*选中节点的对象集合*/
			$scope.checkItemList = [];
			/*选中节点的名称集合*/
			$scope.checkItemNameList = [];
			/*选中节点的id集合*/
			$scope.checkItemIdList = [];
			function checkChildren(c) {
				angular.forEach(c.children, function(c) {
					if(c.checked){
						$scope.checkItemList.push({"id":c.id,"name":c.name,"code":c.code,"type":c.type,"pid":c.pid});
						$scope.checkItemNameList.push(c.name);
						$scope.checkItemIdList.push(c.id);
					}
					checkChildren(c);
				});
			}
			checkChildren($scope.tree[0]);
			$scope.$emit('cnex4-tree-selected:org',{'_componentsName':$scope._componentsName,'checkItemList':$scope.checkItemList,'checkItemNameList':$scope.checkItemNameList,'checkItemIdList':$scope.checkItemIdList,'tree':$scope.tree});
		}
	}])
	.controller('orgDataPickerController',['$scope', '$http','$sce','$timeout',function($scope, $http, $sce,$timeout){
		$scope.aside = {title: $sce.trustAsHtml('机构选择器（核算加独立）'), content: $sce.trustAsHtml('Hello Aside<br />This is a multiline message!')};
		$scope.checkItemNameList = "";
		$scope.checkItemList = [];
		$scope.tree = [];
		$scope.$on('cnex4-tree-selected:org',function(event,data){
			$scope.ngModel = data.checkItemNameList.toString();
			$scope.checkItemNames = data.checkItemNameList.toString();
			$scope.checkItemList = data.checkItemList;
			$scope.tree = data.tree;
	    });
   }])
   .directive('nodeTree', function () {
        return {
            template: '<node ng-repeat="node in tree"></node>',
            replace: true,
            restrict: 'E',
            scope: {
                tree: '=children'
            }
        };
    })
    .directive('node', function ($compile) {
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'static/js/custom/directives/cnex4-data-picker/template/node.html', 
            link: function (scope, element) {
                if (scope.node && scope.node.children && scope.node.children.length > 0) {
                    scope.node.childrenVisibility = true;
                    var childNode = $compile('<ul class="tree" ng-if="!node.childrenVisibility"><node-tree children="node.children"></node-tree></ul>')(scope);
                    element.append(childNode);
                } else {
                    scope.node.childrenVisibility = false;
                }
            },
            controller: ["$scope", function ($scope) {
                $scope.toggleVisibility = function (node) {
                    if (node.children) {
                        node.childrenVisibility = !node.childrenVisibility;
                    }
                };
                $scope.checkNode = function (node) {
            		 node.checked = !node.checked;
                     function checkChildren(c) {
                         angular.forEach(c.children, function (c) {
                             c.checked = node.checked;
                             checkChildren(c);
                         });
                     }
                     checkChildren(node);
                };
            }]
        };
    })
   .directive('cnex4OrgTreePicker',function () {
	    return { 
	    	require: '?ngModel',
	    	restrict: 'E',
	    	scope: {ngModel: '='},
	    	controller: 'orgDataPickerController',
	    	templateUrl:'static/js/custom/directives/cnex4-data-picker/template/template_org_data_picker.html',
	        replace: true,
	        link:function(scope, el, attrs,ngModel){
	        	scope.treeType = attrs.tree;
	        	scope._componentsName = attrs.name;
	        	ngModel.$render = function() {
	        		if("" != ngModel.$viewValue && undefined != ngModel.$viewValue){
	        			scope.checkItemNames = ngModel.$viewValue;
	        		}
	            };
	        }
	    };
	});
