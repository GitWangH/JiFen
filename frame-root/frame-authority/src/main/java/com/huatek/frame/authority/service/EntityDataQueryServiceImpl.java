package com.huatek.frame.authority.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.huatek.frame.authority.dto.PropertyDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.rpc.server.core.RpcService;
@RpcService(EntityDataQueryService.class)
public class EntityDataQueryServiceImpl implements  EntityDataQueryService{
	@Autowired
	private EntityDataQueryComponentImpl componentImpl;
	@Override
	public DataPage<PropertyDto> queryEntityObjectPageData(
			QueryPage queryPage, SourceEntityDto sourceEntityDto) {
		
		return componentImpl.queryEntityObjectPageData(queryPage, sourceEntityDto);
	}

}
