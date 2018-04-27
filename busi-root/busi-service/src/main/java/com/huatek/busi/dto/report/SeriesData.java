package com.huatek.busi.dto.report;

import java.util.List;

/**
 * @ClassName: SeriesData
 * @Description: 报表统计数据传递对象
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-15 13:55:35
 * @version: 1.0
 */
public class SeriesData {

	private String name;
	private String type;
	private String value;
	private List<String> data;
	
	public SeriesData() {
		
	}

	public SeriesData(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}