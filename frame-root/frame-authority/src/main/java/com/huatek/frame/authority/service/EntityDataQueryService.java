package com.huatek.frame.authority.service;

import com.huatek.frame.authority.dto.PropertyDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface EntityDataQueryService {
	/***
	 * 根据实体名查询实体用于分配的数据.
	 * @param queryPage 查询条件
	 * @param businessMapDto 要查询的实体.
	 * @return 返回查展示的数据实体.
	 */
	DataPage<PropertyDto> queryEntityObjectPageData(QueryPage queryPage, SourceEntityDto sourceEntityDto);
}
