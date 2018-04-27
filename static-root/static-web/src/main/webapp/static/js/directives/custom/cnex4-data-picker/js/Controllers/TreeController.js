(function(ng) {
	var app = ng.module('tree', [ 'tree.service', 'tree.directives' ]);
	app.controller("TreeController", [ "TreeService","$scope", function(TreeService,$scope) {
		var tc = this;
		buildTree();
		function buildTree() {
			if(undefined == $scope.tree || $scope.tree.length < 1){
				TreeService.getTree().then(function(result) {
					tc.tree = result.data;
				}, function(result) {
					alert("Tree no available, Error: " + result);
				});
			}else{
				tc.tree = $scope.tree;
			}
			
		}
		this.showCheckList = function() {
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
			checkChildren(tc.tree[0]);
			$scope.$emit('cnex4-tree-selected:org',{'_componentsName':TreeService._componentsName,'checkItemList':$scope.checkItemList,'checkItemNameList':$scope.checkItemNameList,'checkItemIdList':$scope.checkItemIdList,'tree':tc.tree});
		}
	} ]);
})(angular);
