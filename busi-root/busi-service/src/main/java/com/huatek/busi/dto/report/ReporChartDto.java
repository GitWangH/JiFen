package com.huatek.busi.dto.report;

import java.util.List;

/**
 * @ClassName: ReporChartDto
 * @Description: 报表统计 Dto
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-15 13:55:35
 * @version: 1.0
 */
public class ReporChartDto {

	private String titleText;// Chart option 名称
	private String[] legendData;// 统计维度
	private String seriesName;// 访问来源
	private List<SeriesData> seriesDataList;// 统计数据
	private List<Integer> emergency;//叠加图表 紧急
	private List<Integer> general; //叠加图表 普通
	private List<Integer> slight; // 轻微 
	private List<String> legendData2;
	
	/** x轴上的数据 */
	private List<String> xAxisData;
	/**总数*/
	private List<Integer> seriesSum;
	/**原材料*/
	private List<Integer> seriesRawMaterial;
	/**半成品*/
	private List<Integer> seriesPartiallyPreparedProducts;
	/**施工过程*/
	private List<Integer> seriesConstructionProcess;
	/**第三方检测*/
	private List<Integer> seriesThirdParty;
	
	/**已解决*/
	private List<Integer> yijiejue;
	/**未解决*/
	private List<Integer> weijiejue;
	

	private Long orgId;// 选择的机构
	private String timeRanges;// 选择的时间范围

	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public String[] getLegendData() {
		return legendData;
	}

	public void setLegendData(String[] legendData) {
		this.legendData = legendData;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public List<SeriesData> getSeriesDataList() {
		return seriesDataList;
	}

	public void setSeriesDataList(List<SeriesData> seriesDataList) {
		this.seriesDataList = seriesDataList;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getTimeRanges() {
		return timeRanges;
	}

	public void setTimeRanges(String timeRanges) {
		this.timeRanges = timeRanges;
	}

	public List<Integer> getEmergency() {
		return emergency;
	}

	public void setEmergency(List<Integer> emergency) {
		this.emergency = emergency;
	}

	public List<Integer> getGeneral() {
		return general;
	}

	public void setGeneral(List<Integer> general) {
		this.general = general;
	}

	public List<Integer> getSlight() {
		return slight;
	}

	public void setSlight(List<Integer> slight) {
		this.slight = slight;
	}

	public List<String> getxAxisData() {
		return xAxisData;
	}

	public void setxAxisData(List<String> xAxisData) {
		this.xAxisData = xAxisData;
	}

	public List<Integer> getSeriesSum() {
		return seriesSum;
	}

	public void setSeriesSum(List<Integer> seriesSum) {
		this.seriesSum = seriesSum;
	}

	public List<Integer> getSeriesRawMaterial() {
		return seriesRawMaterial;
	}

	public void setSeriesRawMaterial(List<Integer> seriesRawMaterial) {
		this.seriesRawMaterial = seriesRawMaterial;
	}

	public List<Integer> getSeriesPartiallyPreparedProducts() {
		return seriesPartiallyPreparedProducts;
	}

	public void setSeriesPartiallyPreparedProducts(
			List<Integer> seriesPartiallyPreparedProducts) {
		this.seriesPartiallyPreparedProducts = seriesPartiallyPreparedProducts;
	}

	public List<Integer> getSeriesConstructionProcess() {
		return seriesConstructionProcess;
	}

	public void setSeriesConstructionProcess(List<Integer> seriesConstructionProcess) {
		this.seriesConstructionProcess = seriesConstructionProcess;
	}

	public List<Integer> getSeriesThirdParty() {
		return seriesThirdParty;
	}

	public void setSeriesThirdParty(List<Integer> seriesThirdParty) {
		this.seriesThirdParty = seriesThirdParty;
	}

	public List<Integer> getYijiejue() {
		return yijiejue;
	}

	public void setYijiejue(List<Integer> yijiejue) {
		this.yijiejue = yijiejue;
	}

	public List<Integer> getWeijiejue() {
		return weijiejue;
	}

	public void setWeijiejue(List<Integer> weijiejue) {
		this.weijiejue = weijiejue;
	}

	public List<String> getLegendData2() {
		return legendData2;
	}

	public void setLegendData2(List<String> legendData2) {
		this.legendData2 = legendData2;
	}


	
}