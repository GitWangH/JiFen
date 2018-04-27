package com.huatek.busi.service.impl.external;

import java.util.Date;

import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.service.external.BusiQualityExternalService;

/**
 * 接口 - 监控量测	
 * @author eli_cui
 *
 */
public class BusiQualityExternalMonitoringMeasurement implements BusiQualityExternalService {
	@Override
	public ExternalResponse receiveData(String busiType, String appKey,	String data, Date timestamp) {
		return null;
	}
	
	@Override
	public String getType() {
		return "monitoringMeasurement";
	}
}
