package com.huatek.busi.service.report;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dto.report.ReporChartDto;
import com.huatek.frame.core.page.QueryPage;


/**
 * @ClassName: BusiReportAction
 * @Description: 报表 服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-15 13:55:35
 * @version: 1.0
 * @memuInfo [报表管理]
 */
public interface BusiReportService {

	/**
	 * 不合格数据统计
	 * @param queryPage
	 * @return
	 */
	ReporChartDto getUnqualifiedStatisticsData(QueryPage queryPage);
	
	/**
	 * 获取首页7个超标等图表的方法
	 * @param dataSource 数据来源..原材料..实验室等详情查看 Constant.Report
	 * @param queryPage 
	 * @return
	 */
	ReporChartDto getOverStandardChartData(String dataSource, QueryPage queryPage);
	
	/**
	 * 获取单位工程报检不合格 图表
	 * @param queryPage
	 * @return
	 */
	ReporChartDto getUnitEngineeringInspectionUnqualified(QueryPage queryPage);
	
	/**
	 * 获取质量问题严重程度情况  图表
	 * @param queryPage
	 * @return
	 */
	ReporChartDto getQualitySeverityData(QueryPage queryPage);
	
	/**
	 * 获取质量分布图表
	 * @param queryPage
	 * @return
	 */
	ReporChartDto getQualityDistributionChart(QueryPage queryPage);
	
	/**
	 * 获取质量问题 图表
	 * @param queryPage
	 * @return
	 */
	ReporChartDto getQualityProblem(QueryPage queryPage);
	
	/**
	 * 获取质量趋势图
	 * @param queryPage
	 * @return
	 */
	ReporChartDto getQualityTrends(QueryPage queryPage);
	
}