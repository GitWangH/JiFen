package com.huatek.busi.dto.phicom.scoreData;

import java.util.List;

public class ScoreDataDto {
	private String gainTotal;// 总获得积分
	private String consumeTotal;// 总消耗积分
	private String[] gainLegendData;// 获得--统计维度
	private List<ScoreSeriesDataDto> gainSeriesData;// 获得数据

	private String[] consumeLegendData;// 消耗--统计维度
	private List<ScoreSeriesDataDto> consumeSeriesData;// 消耗数据

	public String getGainTotal() {
		return gainTotal;
	}

	public void setGainTotal(String gainTotal) {
		this.gainTotal = gainTotal;
	}

	public String getConsumeTotal() {
		return consumeTotal;
	}

	public void setConsumeTotal(String consumeTotal) {
		this.consumeTotal = consumeTotal;
	}

	public String[] getGainLegendData() {
		return gainLegendData;
	}

	public void setGainLegendData(String[] gainLegendData) {
		this.gainLegendData = gainLegendData;
	}

	public List<ScoreSeriesDataDto> getGainSeriesData() {
		return gainSeriesData;
	}

	public void setGainSeriesData(List<ScoreSeriesDataDto> gainSeriesData) {
		this.gainSeriesData = gainSeriesData;
	}

	public String[] getConsumeLegendData() {
		return consumeLegendData;
	}

	public void setConsumeLegendData(String[] consumeLegendData) {
		this.consumeLegendData = consumeLegendData;
	}

	public List<ScoreSeriesDataDto> getConsumeSeriesData() {
		return consumeSeriesData;
	}

	public void setConsumeSeriesData(
			List<ScoreSeriesDataDto> consumeSeriesData) {
		this.consumeSeriesData = consumeSeriesData;
	}

}
