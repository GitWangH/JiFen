package com.huatek.busi.dto.report;

import java.util.ArrayList;
import java.util.List;

/**
 * 质量分布图表 dto
 * @author eli_cui
 */
public class QualityDistributionChartDto {
	
	/**图表说明字段 - 图表上暂时写死-此字段不在数据库中查询*/
	//private List<String> legendData = new ArrayList<String>();
	/** x轴上的数据 */
	private List<String> xAxisData;
	/**总数*/
	private List<String> seriesSum;
	/**原材料*/
	private List<String> seriesRawMaterial;
	/**半成品*/
	private List<String> seriesPartiallyPreparedProducts;
	/**施工过程*/
	private List<String> seriesConstructionProcess;
	/**第三方检测*/
	private List<String> seriesThirdParty;
	
	
	public List<String> getxAxisData() {
		return xAxisData;
	}
	public void setxAxisData(List<String> xAxisData) {
		this.xAxisData = xAxisData;
	}
	public List<String> getSeriesSum() {
		return seriesSum;
	}
	public void setSeriesSum(List<String> seriesSum) {
		this.seriesSum = seriesSum;
	}
	public List<String> getSeriesRawMaterial() {
		return seriesRawMaterial;
	}
	public void setSeriesRawMaterial(List<String> seriesRawMaterial) {
		this.seriesRawMaterial = seriesRawMaterial;
	}
	public List<String> getSeriesPartiallyPreparedProducts() {
		return seriesPartiallyPreparedProducts;
	}
	public void setSeriesPartiallyPreparedProducts(
			List<String> seriesPartiallyPreparedProducts) {
		this.seriesPartiallyPreparedProducts = seriesPartiallyPreparedProducts;
	}
	public List<String> getSeriesConstructionProcess() {
		return seriesConstructionProcess;
	}
	public void setSeriesConstructionProcess(List<String> seriesConstructionProcess) {
		this.seriesConstructionProcess = seriesConstructionProcess;
	}
	public List<String> getSeriesThirdParty() {
		return seriesThirdParty;
	}
	public void setSeriesThirdParty(List<String> seriesThirdParty) {
		this.seriesThirdParty = seriesThirdParty;
	}

}
