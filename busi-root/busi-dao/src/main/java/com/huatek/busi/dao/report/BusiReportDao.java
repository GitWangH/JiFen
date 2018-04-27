package com.huatek.busi.dao.report;

import java.util.List;
import java.util.Map;

import com.huatek.busi.model.report.QualityDistributionChart;
import com.huatek.frame.core.page.QueryPage;


/**
 * @ClassName: BusiReportDao
 * @Description: 报表 DAO接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-15 13:55:35
 * @version: 1.0
 */
public interface BusiReportDao {

	/**
	 * 不合格数据统计
	 * @param queryPage
	 * @return
	 */
	List<Map<String, Object>> findUnqualifiedStatisticsData(QueryPage queryPage);
	

	/**
	 * 获取超标的图标数据
	 * @param queryPage
	 * @param condition 需要查询的busi_quality_rectification 表的 DATA_SOURCE
	 * @return
	 */
	List<Map<String, Object>> findOverStandardChartData(QueryPage queryPage, String condition, Long orgId);
	
	/**
	 * 获取 单位工程报检不合格 数据
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<Map<String, Object>> findUnitEngineeringInspectionUnqualified(QueryPage queryPage, Long orgId);

	/**
	 * 获取质量问题严重程度情况 数据
	 * @param queryPage
	 * @return
	 */
	List<Map<String, Object>> findQualitySeverityDataData(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取 原材料 数据 - 质量分布图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findRawMaterial(QueryPage queryPage, Long orgId);
	
	/***
	 * 获取 施工报检 数据 - 质量分布图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findConstructionProcess(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取 半成品检测 数据 - 质量分布图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findPartiallyPreparedProducts(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取 第三方检测 数据 - 质量分布图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findThirdParty(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取原材料数据 - 质量问题图表
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findRawMaterialQualityProblemChart(QueryPage queryPage, Long orgId);
	
	/**
	 * 
	 * 获取 施工报检 数据 质量问题图表
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findConstructionProcessQualityProblemChart(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取半成品检测数据 质量问题图表
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findPartiallyPreparedProductsQualityProblemChart(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取 第三方检测 数据 质量问题图表
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findThirdPartyQualityProblemChart(QueryPage queryPage, Long orgId);
	
	
	
	/**
	 * 获取质量趋势图 数据
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	List<QualityDistributionChart> findQualityTrends(QueryPage queryPage, Long orgId);
	
	
	/**
	 * 获取原材料数据 - 质量趋势图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	//List<QualityDistributionChart> findRawMaterialQualityQualityTrends(QueryPage queryPage, Long orgId);
	
	/**
	 * 
	 * 获取 施工报检 数据 质量趋势图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	//List<QualityDistributionChart> findConstructionProcessQualityTrends(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取半成品检测数据 质量趋势图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	//List<QualityDistributionChart> findPartiallyPreparedProductsQualityTrends(QueryPage queryPage, Long orgId);
	
	/**
	 * 获取 第三方检测 数据 质量趋势图
	 * @param queryPage
	 * @param orgId
	 * @return
	 */
	//List<QualityDistributionChart> findThirdPartyQualityQualityTrends(QueryPage queryPage, Long orgId);
}
