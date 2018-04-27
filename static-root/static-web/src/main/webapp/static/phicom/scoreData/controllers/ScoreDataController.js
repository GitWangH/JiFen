'use strict';
/***
 * 拷贝被控制器时，务必修改在本文档中的另一个控制器的名字，否则会影响到
 * 原来控制器的修改，切记.
 */
angular.module('huatek.controllers').controller('ScoreDataController', function ($scope, $location, $http,$rootScope, listService,httpService) {
	
	var initTaskUrl='api/scoreData/scoreTaskConfig';
	/***
	 * 定义显示的表格.
	 */
    $scope.tableGrid = {
            paginationPageSizes: [10, 25, 50, 100],
            paginationPageSize: 10,
            useExternalPagination: true,
            multiSelect: true,
		    columnDefs: [
		       { name: '时间', field: 'createTime',width: '10%', enableColumnMenu: false},
		       { name: '会员账号', field: 'memberName',width: '10%', enableColumnMenu: false},
		       { name: '类型', field: 'scoreType',width: '10%', enableColumnMenu: false,decode: { field: "scoreType", dataKey: "dic.scoreFlow_scoreType" }},
		       { name: '任务项', field: 'taskName',width: '10%', enableColumnMenu: false},
		       { name: '积分值', field: 'score',width: '10%', enableColumnMenu: false},
		       { name: '说明', field: 'remark',width: '10%', enableColumnMenu: false}
		    ]
	  };
	
	/**
	 * 初始化编辑界面.
	 * 比如显示编辑界面的模块名称.
	 */
    listService.init($scope);
    //定义查询页
    var queryPage = new QueryPage(1,10,"createTime desc","false");
    
    //定义搜索框
    
    queryPage.addParam(new queryParam('时间 ','createTime','date','>='));
    queryPage.addParam(new queryParam('会员账号','memberName','string','like'));
     var productType = new queryParam('类型','scoreType','string','=',null,"dic.scoreFlow_scoreType"); 
    productType.componentType = 'select';
    queryPage.addParam(productType);
    var task = new queryParam('任务项','taskId','string','='); 
    task.componentType ='select';
    task.showField='taskName';
    task.returnField='id';
    queryPage.addParam(task);
    
    listService.initQueryItems($scope,task,initTaskUrl);
    /***
     * 设置查询输入按钮.
     */
    listService.setQueryPage($scope, queryPage);
    
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
    	listService.loadData($scope, 'api/scoreData/query', $scope.tableGrid);
    }
    
    load();

	$scope.search = function() {
		load();
	};
	
})
.controller('ScoreDataChartController', function($scope, httpService) {
	var myDate = new Date();
	$scope.year=myDate.getFullYear();
	$scope.gain="0";
	$scope.consume="0";
	$scope.than="0";
	$scope.loadChartType=function(type){
		if(type=="1"){
			$scope.year=myDate.getFullYear();
		}else{
			$scope.year=myDate.getFullYear()-1;
		}
		$scope.loadChart(type);
	}
	$scope.loadChart=function(type){
		httpService.get($scope, '/api/scoreData/scoreData/'+type).success(function(res) {
			if(res){
				$scope.gain=comdify(res.gainTotal);
				$scope.consume=comdify(res.consumeTotal);
				if(res.consumeTotal=="0"){
					$scope.than="0";
				}else{
					$scope.than=((Number(res.consumeTotal)/Number(res.gainTotal))*100).toFixed(2);
				}
				
				var gainChart = echarts.init(document.getElementById('gainChart'));
				var gainOption = {
				    title : {
				        text: '积分获取行为分布',
				        subtext: '',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: '',
				        left: '',
				        data: typeof(res.gainLegendData)=="undefined"?['']:res.gainLegendData
				    },
				    series : [
				        {
				            name: '获得来源',
				            type: 'pie',
				            radius : '70%',
				            center: ['50%', '60%'],
				            data:typeof(res.gainSeriesData)=="undefined"?new Array():res.gainSeriesData,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};

				gainChart.setOption(gainOption);
				var consumeChart = echarts.init(document.getElementById('consumeChart'));
				var consumeOption = {
				    title : {
				        text: '积分消耗行为分布',
				        subtext: '',
				        x:'center'
				    },
				    tooltip : {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: '',
				        left: '',
				        data: typeof(res.consumeLegendData)=="undefined"?['']:res.consumeLegendData
				    },
				    series : [
				        {
				            name: '消耗来源',
				            type: 'pie',
				            radius : '70%',
				            center: ['50%', '60%'],
				            data:typeof(res.consumeSeriesData)=="undefined"?new Array():res.consumeSeriesData,
				            itemStyle: {
				                emphasis: {
				                    shadowBlur: 10,
				                    shadowOffsetX: 0,
				                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				                }
				            }
				        }
				    ]
				};
				consumeChart.setOption(consumeOption);
			}
	    });
	}
	$scope.loadChart("1");
	function comdify(n){
		if(n){
		　　var re=/\d{1,3}(?=(\d{3})+$)/g;
		　　var n1=n.replace(/^(\d+)((\.\d+)?)$/,function(s,s1,s2){return s1.replace(re,"$&,")+s2;});
		　　return n1;
		}
    }
})
