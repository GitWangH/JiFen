package com.huatek.frame.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.rpc.server.core.RpcService;


@RpcService(value=FwOrgRpcService.class)
public class FwOrgRpcServiceImpl implements FwOrgRpcService {

	@Autowired
	private FwOrgService fwOrgService;
	
	@Override
	public List<FwOrgDto> getAllOrg() {
		return fwOrgService.getAllOrg();
	}

	@Override
	public FwOrgDto getOrgById(Long id) {
		// TODO Auto-generated method stub
		return fwOrgService.getOrgById(id);
	}

}
