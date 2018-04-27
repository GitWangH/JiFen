 angular.module('huatek.controllers').controller('ReportChartUnqualifiedStatisController', function($scope, $http, listService, httpService) {
	 
	/**
	 * 初始化编辑界面. 比如显示编辑界面的模块名称.
	 */
	listService.init($scope, $http);
	
	// 定义查询页
	var queryPage = new QueryPage(1, 10, "id desc", "false");
	// 定义搜索框
    var tendersName = new queryParam('机构名称', 'orgId', 'string', '=');
    tendersName.componentType  = SEARCH_COMPONENT.TENDERS_SLECT;
    queryPage.addParam(tendersName);
    
    queryPage.addParam(new queryParam('时间范围', 'timeRanges', 'string', '=','','dic.time_ranges'));
    /**
	 * 设置查询输入按钮.
	 */
    listService.setQueryPage($scope, queryPage);
 	
    /**
	 * 初始化加载数据.
	 */
    var load = function() {
    	$scope.promise = httpService.post($scope, "api/report/getReportData/unqualified", queryPage)
							        .success(function(response) {
							        	/*基于准备好的dom，初始化echarts实例*/
							            var myChart = echarts.init(document.getElementById('reportChartUnqualifiedStatis'));
							            var option = {
							        	         title: {
							        	             text: "不合格统计",
							        	             x: 'center'
							        	         },
							        	         tooltip: {
							        	             trigger: 'item',
							        	             formatter: "{a} <br/>{b} : {c} ({d}%)"
							        	         },
							        	         legend: {
							        	             orient: 'vertical',
							        	             left: 'left',
							        	             data: response.legendData
							        	         },
							        	         series: [{
							        	             name: '数据来源',
							        	             type: 'pie',
							        	             radius: '55%',
							        	             center: ['50%', '60%'],
							        	             data: response.seriesDataList,
							        	             itemStyle: {
							        	                 emphasis: {
							        	                     shadowBlur: 10,
							        	                     shadowOffsetX: 0,
							        	                     shadowColor: 'rgba(0, 0, 0, 0.5)'
							        	                 }
							        	             }
							        	         }]
							        	     };
							            myChart.setOption(option);
							        })
							        .error(function() {
							            submitTips('统计出错!', 'error');
							        });
    }

    load();

    $scope.search = function() {
        load();
    };
    
     
 });