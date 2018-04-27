package com.huatek.busi.service.external;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.huatek.busi.dto.external.ExternalWithDataResponse;


public interface BusiQualityExternalForPiccomService {
	Map<String,String> getType();
	ExternalWithDataResponse receiveData(String busiType,String appKey,String data,Date timestamp);
	
}
