(function (ng) {
    var app = ng.module('tree.directives', []);
    app.directive('nodeTree', function () {
        return {
            template: '<node ng-repeat="node in tree"></node>',
            replace: true,
            restrict: 'E',
            scope: {
                tree: '=children'
            }
        };
    });
    app.directive('node', function ($compile) {
        return {
            restrict: 'E',
            replace: true,
            templateUrl: 'static/directives/cnex4-data-picker/template/node.html', 
            link: function (scope, element) {
                if (scope.node && scope.node.children && scope.node.children.length > 0) {
                    scope.node.childrenVisibility = true;
                    var childNode = $compile('<ul class="tree" ng-if="!node.childrenVisibility"><node-tree children="node.children"></node-tree></ul>')(scope);
                    element.append(childNode);
                } else {
                    scope.node.childrenVisibility = false;
                }
            },
            controller: ["$scope", "TreeService",function ($scope,TreeService) {
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
    });
})(angular);