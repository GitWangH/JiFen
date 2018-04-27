'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('PhiPlusPagelayoutController', function ($scope, $location, $http,$rootScope, listService,httpService,shareData) {

    /***
	 * 定义显示的表格.
	 */
    $scope.tableGrid = {
            paginationPageSizes: [10, 25, 50, 100],
            paginationPageSize: 10,
            useExternalPagination: true,
            multiSelect: true,
		    columnDefs: [
							{ name: '编号', field: 'code',width: '10%', enableColumnMenu: false},
							{ name: '配置位置', field: 'configureaddr',width: '10%', enableColumnMenu: false},
							{ name: '客户端', field: 'client',width: '10%', enableColumnMenu: false},
							{ name: '规则', field: 'rule',width: '10%', enableColumnMenu: false},
							{ name: '操作人员', field: 'operationperson',width: '10%', enableColumnMenu: false},
							{ name: '末次操作时间', field: 'endoperationtime',width: '10%', enableColumnMenu: false},
							{ name: '操作', field: 'operate',width: '10%', enableColumnMenu: false,cellTemplate:'<a><span ng-click="grid.appScope.editOpen(row.entity)">编辑</a>'}
                           // { name: '测试', field: 'test',width: '10%', enableColumnMenu: false,cellTemplate:'<a><span ng-click="grid.appScope.testCode(row.entity.code)">测试</a>'}
						]
	  };
    
    //定义查询页
    var queryPage = new QueryPage(1,10,"id desc","false");  
    
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);  

   /* $scope.testCode = function(code){
        httpService.get($scope,'api/phiPlusPagelayout/queryPhiAdPosition/'+code).success(function(){

        });
    }*/
    
    $scope.editOpen = function(entity){
    	var id = entity.id;
    	if(id != undefined && id != ''){
    		if(entity.id == '1'){
    			var scope = $rootScope.$new();
    			var pop = {
    					title: "移动端权益汇总",
    					passParams: entity,
    					onclosedFun: load,
    					controller: 'phiPlusGatherContrller',
    					templateView: "/static/phicom/plusPageLayout/template_plus_page.html"
    					//width:1000
    			};
    			listService.popPanel($scope, pop);
    		}else if(entity.id == '2'){
    			var scope = $rootScope.$new();
    			var pop = {
	    			title: "移动端广告位",
					passParams: entity,
					onclosedFun: load,
					controller: 'phiAdPositionController',
					templateView: "/static/phicom/plusPageLayout/template_plus_page_edit.html"
    			};
    			listService.popPanel($scope, pop);
    		}else if(entity.id == '3'){
    			var scope = $rootScope.$new();
    	        var pop = {
    	            title: "移动端_PLUS会员专享的编辑页面",
    	            passParams: entity,
    	            onclosedFun: load,
    	            controller: 'phiPlusExclusiveController',
    	            templateView: "/static/phicom/plusPageLayout/template_plus_exclusive.html"

    	        };
    	        listService.popPanel($scope, pop);
    		}else if (entity.id == '4'){
    			var scope = $rootScope.$new();
    		        var pop = {
    		            title: "移动端_权益说明",
    		            passParams: entity,
    		            onclosedFun: load,
    		            controller: 'phiRightExplainController',
    		            templateView: "/static/phicom/plusPageLayout/template_right_explain.html"
    	
    		        };
    		        listService.popPanel($scope, pop);
    		}else if (entity.id == '5'){
                var scope = $rootScope.$new();
                    var pop = {
                        title: "PC端_权益汇总",
                        passParams: entity,
                        onclosedFun: load,
                        controller: 'phiPlusGatherContrller',
                        templateView: "/static/phicom/plusPageLayout/template_plus_page.html"
                    };
                    listService.popPanel($scope, pop);
            }else if(entity.id == '6'){
                var scope = $rootScope.$new();
                var pop = {
                    title: "PC端_广告位",
                    passParams: entity,
                    onclosedFun: load,
                    controller: 'phiAdPositionController',
                    templateView: "/static/phicom/plusPageLayout/template_plus_page_edit.html"
                };
                listService.popPanel($scope, pop);
            }else if (entity.id == '7'){
                var scope = $rootScope.$new();
                    var pop = {
                        title: "PC端_权益说明",
                        passParams: entity,
                        onclosedFun: load,
                        controller: 'phiRightExplainController',
                        templateView: "/static/phicom/plusPageLayout/template_right_explain.html"
        
                    };
                    listService.popPanel($scope, pop);
            }else if(entity.id == '8'){
                var scope = $rootScope.$new();
                var pop = {
                    title: "PC端_PLUS会员专享的编辑页面",
                    passParams: entity,
                    onclosedFun: load,
                    controller: 'phiPlusExclusiveController',
                    templateView: "/static/phicom/plusPageLayout/template_plus_exclusive.html"

                };
                listService.popPanel($scope, pop);
            }
    		
       }
    }
    /***
	 * 注册gridApi的选择器.
	 */
	$scope.tableGrid.onRegisterApi = function(gridApi){
	      $scope.gridApi = gridApi;
	     gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	    		queryPage.page = newPage;
	    		queryPage.pageSize = pageSize;
	    		load();
	          });
	  	}; 

    
	/***
	 * 初始化加载数据.
	 */
    var load = $scope.load = function(){
    	listService.loadData($scope, 'api/phiPlusPagelayout/query', $scope.tableGrid);
    }
    load();
    
})