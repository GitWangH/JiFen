package com.huatek.frame.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.frame.FrameUrlConstants;
import com.huatek.frame.service.dto.RecordsUIDto;

@RestController
@RequestMapping(FrameUrlConstants.CONTENT_API)
public class ContentAction {
	
	@RequestMapping(value="/contentAction",method = RequestMethod.GET )
	@ResponseBody
	public ResponseEntity<?> content(){
		Map<String, Object> loginDataMap = new HashMap<String, Object>();
		//构造JSON数据(测试)
    	List<RecordsUIDto> jsonDatas = new ArrayList<RecordsUIDto>();
    	String sale = "本日揽货：5万元";
    	String waitDeal = "2016/02/04 中秋节礼品采购活动";
    	String notice = "关于新增合作网点的公告[2015-09-25]";
    	String annouce = "关于新增合作网点的公告[2015-09-25]";
    	String waitApprove = "徐泾网点搬迁申请";
    	for(int i=0;i<5;i++){
    		RecordsUIDto record = new RecordsUIDto(sale, waitDeal, notice, annouce, waitApprove);
    		jsonDatas.add(record);
    	}
    	loginDataMap.put("jsonDatas", jsonDatas);
		return new ResponseEntity<Map<String, Object>>(loginDataMap, HttpStatus.OK);
	}
}
