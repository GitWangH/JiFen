package com.huatek.frame.service.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典枚举
 * @author TangWenKuo
 * @date 2016年1月22日 上午10:09:34
 */
public enum DictEnum {
	
	BUSINESS_FLOW(3850L,"业务流程"),
	CHANNEL_CATEGORY(5551L,"渠道类别"),
	MTART(14200L,"物料类型"),
	Z_BRAND(14201L,"品牌"),
	MARKET_LEVEL(17200L,"市场级别"),
	LARGE_AREA(38850L,"所属大区"),
	MARKET_CENTER(40200L,"所属营销中心"),
	IS_SPECIALSHOP(212350L,"是否专卖店"),
	SPECIAL_SHOP_TYPE(40500L,"专卖店类别"),
	MARKET_MODEL(17500L,"营销模式"),
	NET_STANDARD(17600L,"网络制式"),
	COOPERATION_MODEL(17700L,"合作模式"),
	SCORE_STANDARD(211700L,"商家评级标准"),
	ZZCPJD(14100L,"生命周期"),
	ZZCPDC(14101L,"高中低端"),
	MISSION(212351L,"任务口径"),
	AGENT_PROPERTY(209300L,"代理商性质"),
    TIMETYPE(212352L,"时间类型");
	
	private static Map<Long,DictEnum> CACHE = new HashMap<Long,DictEnum>(){
			
			private static final long serialVersionUID = 7891690619201405457L;
			{
			  for(DictEnum enu : DictEnum.values()){
				  put(enu.getStatus(), enu);
			  }
		   }
	 };
	private Long status;
	private String description;
	private DictEnum(Long status,String description) {
		this.status = status;
		this.description = description;
	}
	public Long getStatus() {
		return status;
	}
	public String getDescription() {
		return description;
	}
	public static DictEnum toEnum(Long status){
		return CACHE.get(status);
	}
}
