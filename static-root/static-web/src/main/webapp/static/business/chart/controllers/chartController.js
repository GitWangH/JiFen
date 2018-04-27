angular.module('huatek.controllers')
    .controller('ChartMixingStationController', function($scope, httpService, $rootScope) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/mixingStation', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('chartMixingStation'));
                /*  指定图表的配置项和数据  */
                var option = {
                    title: {
                        text: '拌合站',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: res.legendData
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: false,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: [{
                            value: res.seriesDataList[0].value,
                            name: res.seriesDataList[0].name
                        }, {
                            value: res.seriesDataList[1].value,
                            name: res.seriesDataList[1].name
                        }, {
                            value: res.seriesDataList[2].value,
                            name: res.seriesDataList[2].name
                        }, {
                            value: res.seriesDataList[3].value,
                            name: res.seriesDataList[3].name
                        }],
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
                myChart.on('click', function(param) {
                    var tab = document.getElementById("/busiQualityCementMixingStation/home");
                    if (tab) {
                        tab.click();
                    }
                });
            });
    })
    .controller('ChartRawMaterialController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/rawMaterial', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('chartRawMaterial'));
                /*  指定图表的配置项和数据  */
                var option = {
                    title: {
                        text: '原材料',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: res.legendData
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [{
                            value: res.seriesDataList[0].value,
                            name: res.seriesDataList[0].name
                        }, {
                            value: res.seriesDataList[1].value,
                            name: res.seriesDataList[1].name
                        }, {
                            value: res.seriesDataList[2].value,
                            name: res.seriesDataList[2].name
                        }, {
                            value: res.seriesDataList[3].value,
                            name: res.seriesDataList[3].name
                        }],
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
                myChart.on('click', function(param) {
                    var tab = document.getElementById("busiQualityRawMaterialInspection/home");
                    if (tab) {
                        tab.click();
                    }
                });
            });
    })
    .controller('ChartLaboratoryController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/laboratory', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('chartLaboratory'));
                /*  指定图表的配置项和数据  */
                var option = {
                    title: {
                        text: '实验室',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: res.legendData
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [{
                            value: res.seriesDataList[0].value,
                            name: res.seriesDataList[0].name
                        }, {
                            value: res.seriesDataList[1].value,
                            name: res.seriesDataList[1].name
                        }, {
                            value: res.seriesDataList[2].value,
                            name: res.seriesDataList[2].name
                        }, {
                            value: res.seriesDataList[3].value,
                            name: res.seriesDataList[3].name
                        }],
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
                myChart.on('click', function(param) {
                    var tab = document.getElementById("/busiQualityTestInspection/home");
                    if (tab) {
                        tab.click();
                    }
                });
            });
    })
    .controller('ChartPavementPressureController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/pavementPressure', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('chartPavementPressure'));
                /*  指定图表的配置项和数据  */
                var option = {
                    title: {
                        text: '路面摊压',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: res.legendData
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [{
                            value: res.seriesDataList[0].value,
                            name: res.seriesDataList[0].name
                        }, {
                            value: res.seriesDataList[1].value,
                            name: res.seriesDataList[1].name
                        }, {
                            value: res.seriesDataList[2].value,
                            name: res.seriesDataList[2].name
                        }, {
                            value: res.seriesDataList[3].value,
                            name: res.seriesDataList[3].name
                        }],
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
                myChart.on('click', function(param) {
                    var tab = document.getElementById("/busiQualitySpreaderRollerSpreader/home");
                    if (tab) {
                        tab.click();
                    }
                });
            });
    })
    .controller('ChartTensionGroutingController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/tensionGrouting', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('chartTensionGrouting'));
                /*  指定图表的配置项和数据  */
                var option = {
                    title: {
                        text: '张拉压浆',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: res.legendData
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [{
                            value: res.seriesDataList[0].value,
                            name: res.seriesDataList[0].name
                        }, {
                            value: res.seriesDataList[1].value,
                            name: res.seriesDataList[1].name
                        }, {
                            value: res.seriesDataList[2].value,
                            name: res.seriesDataList[2].name
                        }, {
                            value: res.seriesDataList[3].value,
                            name: res.seriesDataList[3].name
                        }],
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
                myChart.on('click', function(param) {
                    var tab = document.getElementById("/busiQualityPrestressedTensionMain/home");
                    if (tab) {
                        tab.click();
                    }
                });
            });
    })
    .controller('ChartQualityInspectionController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/qualityInspection', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('chartQualityInspection'));
                /*  指定图表的配置项和数据  */
                var option = {
                    title: {
                        text: '质量巡检',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: res.legendData
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [{
                            value: res.seriesDataList[0].value,
                            name: res.seriesDataList[0].name
                        }, {
                            value: res.seriesDataList[1].value,
                            name: res.seriesDataList[1].name
                        }, {
                            value: res.seriesDataList[2].value,
                            name: res.seriesDataList[2].name
                        }, {
                            value: res.seriesDataList[3].value,
                            name: res.seriesDataList[3].name
                        }],
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
                myChart.on('click', function(param) {
                    var tab = document.getElementById("/busiQualityRoutingInspection/home");
                    if (tab) {
                        tab.click();
                    }
                });
            });
    })
    .controller('ChartConstructionInspectionController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/constructionInspection', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('chartConstructionInspection'));
                /*  指定图表的配置项和数据  */
                var option = {
                    title: {
                        text: '施工报检',
                        x: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: res.legendData
                    },
                    series: [{
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [{
                            value: res.seriesDataList[0].value,
                            name: res.seriesDataList[0].name
                        }, {
                            value: res.seriesDataList[1].value,
                            name: res.seriesDataList[1].name
                        }, {
                            value: res.seriesDataList[2].value,
                            name: res.seriesDataList[2].name
                        }, {
                            value: res.seriesDataList[3].value,
                            name: res.seriesDataList[3].name
                        }],
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
                myChart.on('click', function(param) {
                    var tab = document.getElementById("/busiQualityConsInspection/home");
                    if (tab) {
                        tab.click();
                    }
                });
            });
    })
    .controller('ChartUnitEngineeringInspectionUnqualifiedController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/unitEngineeringInspectionUnqualified', postQueryPage).success(
            function(res) {
                /*   基于准备好的dom，初始化echarts实例  */
                var myChart = echarts.init(document.getElementById('unitEngineeringInspectionUnqualified'));
                var option = {
                    title: {
                        text: '单位工程报检不合格数',
                        x: 'center'
                    },
                    color: ['#3398DB'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: { /*  坐标轴指示器，坐标轴触发有效 */
                            type: 'shadow' /*  默认为直线，可选为：'line' | 'shadow' */
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [{
                        type: 'category',
                        data: res.legendData,
                        axisTick: {
                            alignWithLabel: true
                        }
                    }],
                    yAxis: [{
                        type: 'value'
                    }],
                    series: [{
                        name: '单位工程报检不合格数',
                        type: 'bar',
                        barWidth: '60%',
                        data: res.seriesDataList
                    }]
                };
                myChart.setOption(option);
            });
    }).controller('ChartQualitySeverityController', function($scope, httpService) {
        var postQueryPage = copyQueryPage($scope.$parent.queryPage);
        httpService.post($scope, '/api/report/getReportData/qualitySeverity', postQueryPage).success(function(res) {
            var myChart = echarts.init(document.getElementById('qualitySeverity'));
            var option = {
                title: {
                    /* text: '质量问题严重程度情况（单位：个）',*/
                    x: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: { /*  坐标轴指示器，坐标轴触发有效 */
                        type: 'shadow' /*  默认为直线，可选为：'line' | 'shadow' */
                    }
                },
                legend: {
                    data: ['严重（紧急）', '中度（普通）', '轻微（一般）']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [{
                    type: 'category',
                    data: ['拌合站', '质量巡检', '施工报检']
                }],
                yAxis: [{
                    type: 'value'
                }],
                series: [{
                        name: '严重（紧急）',
                        type: 'bar',
                        stack: 'sss',
                        data: res.emergency /*[120, 132, 101]*/
                    },
                    {
                        name: '中度（普通）',
                        type: 'bar',
                        stack: 'sss',
                        data: res.general
                    },
                    {
                        name: '轻微（一般）',
                        type: 'bar',
                        stack: 'sss',
                        data: res.slight
                    }
                ]
            };
            myChart.setOption(option);
        });
    }).controller('ChartQualityDistributionChart', function($scope, httpService, listService) {

        listService.init($scope);

        /** 定义查询页 */
        var queryPage = new QueryPage(1, 1000, "id desc", "false");
        var orgId = new queryParam('机构', 'orgId', 'string', '=');
        orgId.componentType = SEARCH_COMPONENT.SELECT_TREE_SINGLE;
        queryPage.addParam(orgId);
        var timeRanges = new queryParam('时间', 'timeRanges', 'string', '=', '', 'dic.time_ranges');
        timeRanges.componentType = SEARCH_COMPONENT.SELECT;
        timeRanges.selectCallBack = function(item) {
            this.value = item.id;
        }
        queryPage.addParam(timeRanges);
        listService.setQueryPage($scope, queryPage);

        var load = function() {
            var postQueryPage = copyQueryPage($scope.queryPage);
            httpService.post($scope, '/api/report/getReportData/qualityDistributionChart', postQueryPage).success(function(res) {
                var myChart = echarts.init(document.getElementById('qualityDistributionChart'));
                option = {
                    title: {
                        text: '质量分布图',
                        x: 'left'
                    },
                    color: ['#2FC7CB', '#B7A2E0', '#5BB0F0', '#FFB881', '#DA7981'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['原材料', '半成品', '施工过程', '第三方检测']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: { show: true },
                            dataView: { show: true, readOnly: false },
                            magicType: { show: true, type: ['line', 'bar', 'stack', 'tiled'] },
                            restore: { show: true },
                            saveAsImage: { show: true }
                        }
                    },
                    calculable: true,
                    xAxis: [{
                        type: 'category',
                        axisTick: { show: false },
                        data: res.xAxisData
                    }],
                    yAxis: [{
                        type: 'value'
                    }],
                    series: [
                        /*                          {
                                                        name: '总数',
                                                        type: 'bar',
                                                        barGap: 0,
                                                        label: labelOption,
                                                        data: res.seriesSum
                                                    },*/
                        {
                            name: '原材料',
                            type: 'bar',
                            /*label: labelOption,*/
                            data: res.seriesRawMaterial
                        },
                        {
                            name: '半成品',
                            type: 'bar',
                            /*label: labelOption,*/
                            data: res.seriesPartiallyPreparedProducts
                        },
                        {
                            name: '施工过程',
                            type: 'bar',
                            /*label: labelOption,*/
                            data: res.seriesConstructionProcess
                        }, {
                            name: '第三方检测',
                            type: 'bar',
                            /* label: labelOption,*/
                            data: res.seriesThirdParty
                        }
                    ]
                };
                myChart.setOption(option);
            })
        };

        load();

        $scope.search = function() {
            load();
        }


    }).controller('ChartQualityProblemController', function($scope, httpService, listService) {
        listService.init($scope);
        /** 定义查询页 */
        var queryPage = new QueryPage(1, 1000, "id desc", "false");
        var orgId = new queryParam('机构', 'orgId', 'string', '=');
        orgId.componentType = SEARCH_COMPONENT.SELECT_TREE_SINGLE;
        queryPage.addParam(orgId);
        var timeRanges = new queryParam('时间', 'timeRanges', 'string', '=', '', 'dic.time_ranges');
        timeRanges.componentType = SEARCH_COMPONENT.SELECT;
        timeRanges.selectCallBack = function(item) {
            this.value = item.id;
        }
        queryPage.addParam(timeRanges);
        listService.setQueryPage($scope, queryPage);

        var load = function() {
            var postQueryPage = copyQueryPage($scope.queryPage);
            httpService.post($scope, '/api/report/getReportData/qualityProblem', postQueryPage).success(function(res) {
                var myChart = echarts.init(document.getElementById('qualityProblem'));
                var option = {
                    title: {
                        // text: '质量问题严重程度情况（单位：个）',
                        x: 'left'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: { /*  坐标轴指示器，坐标轴触发有效 */
                            type: 'shadow' /*  默认为直线，可选为：'line' | 'shadow' */
                        }
                    },
                    legend: {
                        data: ['已解决', '未解决']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [{
                        type: 'category',
                        data: res.xAxisData
                    }],
                    yAxis: [{
                        type: 'value'
                    }],
                    series: [{
                            name: '已解决',
                            type: 'bar',
                            stack: 'sss',
                            data: res.yijiejue /*[120, 132, 101]*/
                        },
                        {
                            name: '未解决',
                            type: 'bar',
                            stack: 'sss',
                            data: res.weijiejue
                        }
                    ]
                };
                myChart.setOption(option);
            });
        };

        load();

        $scope.search = function() {
            load();
        }


    }).controller('ChartQualityTrendsController', function($scope, httpService,listService) {
        listService.init($scope);
        /** 定义查询页 */
        var queryPage = new QueryPage(1, 1000, "id desc", "false");
        var orgId = new queryParam('机构', 'orgId', 'string', '=');
        orgId.componentType = SEARCH_COMPONENT.SELECT_TREE_SINGLE;
        queryPage.addParam(orgId);
        var timeRanges = new queryParam('时间', 'timeRanges', 'string', '=', '', 'dic.time_ranges');
        timeRanges.componentType = SEARCH_COMPONENT.SELECT;
        timeRanges.selectCallBack = function(item) {
            this.value = item.id;
        }
        queryPage.addParam(timeRanges);
        listService.setQueryPage($scope, queryPage);

        

        var load = function() {
            var postQueryPage = copyQueryPage($scope.queryPage);
             httpService.post($scope, '/api/report/getReportData/qualityTrends', postQueryPage).success(function(res) {
                var myChart = echarts.init(document.getElementById('qualityTrends'));
                option = {
                    title: {
                        text: '质量趋势图',
                        left: 'left'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c}'
                    },
                    legend: {
                        left: 'center',
                        data: res.legendData2 /*res.legendData2*/
                    },
                    xAxis: {
                        type: 'category',
                        name: 'x',
                        splitLine: { show: false },
                        data: res.xAxisData /*res.xAxisData*/
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    yAxis: {
                        type: 'log',
                        name: 'y'
                    },
                    series: res.seriesDataList
                };

                myChart.setOption(option);
             });
        }

        load();

        $scope.search = function(){
            load();
        }

    })