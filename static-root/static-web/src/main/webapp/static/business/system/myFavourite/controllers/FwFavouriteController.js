'use strict';
   
/**
 * 添加常用功能Cont
 */
angular.module('huatek.controllers').controller('FwFavouriteController', function ($scope, $location, $http, $routeParams,$rootScope, httpService, cacheService) {
	
    var sourceMap = new Map();
    /*加载菜单树*/
    $scope.menuTree = null;
    $scope.$watch('$viewContentLoaded', function() {
        httpService.get($scope, 'api/fwFavourite/getUserFavourite').success(function(response){
            for(var i=0;i<response.length;i++){
                sourceMap.put(response[i].sourceId, response[i]);
            }
            /*超级管理员可以看到所有菜单*/
            var url = "api/menu/public/loadAllUserMenu";
            if(undefined == $rootScope.tenantId || null == $rootScope.tenantId || "" == $rootScope.tenantId){
                url = "api/menu/public/loadAllMenu";
            }
            httpService.get($scope, url).success(function(response) {
            	for(var i=response.length-1;i>=0;i--){
            		if(response[i].isShow!=1){
            			response.splice(i,1);
            		}
            	}
               $scope.menuTree = response;
               $rootScope.totalFavourite = response.length;
               for(var i =0; i< $scope.menuTree.length;i++){
                    $scope.menuTree[i].nocheck  = false;
                    $scope.menuTree[i].checked  = sourceMap.get($scope.menuTree[i].id) != null;;
               }
               $scope.initOrgTree();
            });
        });
        
    });
    $scope.onTreeClick = function(event, treeId, treeNode) {
        $("#" + treeId).find("li").removeClass("ztree-active");
        $("#" + treeNode.tId).addClass("ztree-active");
        /*parentIdParam.value = treeNode.id;*/
        load();
    }
    $scope.initOrgTree = function() {
        var setting = {
            check: {
                    enable: true,
                    chkStyle: "checkbox",
                    chkboxType: { "Y" : "ps", "N" : "ps" }
                    /*nocheckInherit: true */
            },
            data: {
                
                key: {
                    name: "title",
                    url:'url1'
                },
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentId",
                    rootPId: 0
                }
            },
            callback: {
                onClick: $scope.onTreeClick
            }
        }
        $.fn.zTree.init($("#favouriteMenuTree"), setting, $scope.menuTree).expandAll(true);
    }

    /**
     * 保存功能权限
     */
    $scope.checkNodeInfo = function(){
        var zTree = $.fn.zTree.getZTreeObj("favouriteMenuTree");
        var nodes = zTree.getCheckedNodes(true);
        var dataArr = [];
        var maxCount = cacheService.getData("config.quick_menu_max_count");
        for(var i=0;i<nodes.length;i++){
            /*只选择符节点*/
            if($(nodes[i]).attr('id')*1 > 0 && !nodes[i].isParent){
                dataArr.push($(nodes[i]).attr('id'));
            }
        }
        if(nodes.length  > maxCount ){
            submitTips('警告：最多只能添加'+maxCount+'条常用功能!','warning');
            return;
        }
        
        var actionUrl = "api/fwFavourite/doSaveAssign";
        httpService.post($scope, actionUrl, dataArr).success(function(){
            /*重新加载常用功能*/
            $http.get("/api/fwFavourite/getUserFavourite").success(function(data) {
                 $rootScope.myFavourites = data;
                  $scope.jsPanel.close();
            });
        });
    }

    $scope.back = function(){
        $scope.jsPanel.close();
    }
    

});