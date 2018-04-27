package com.huatek.busi.service.external;

import java.util.Date;

import com.huatek.busi.dto.external.ExternalResponse;


public interface BusiQualityExternalService {
	String getType();
	ExternalResponse receiveData(String busiType,String appKey,String data,Date timestamp);
	
}
