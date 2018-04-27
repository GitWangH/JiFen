package com.huatek.frame.client;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.authority.service.ConfigurationService;
@Component
public class ConfigurationServiceImpl implements ConfigurationService {
	private static final java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();
	private static final Lock w = lock.writeLock();
	@Autowired
	RpcProxy rpcProxy;
	/**
	 * 权限实体Map.
	 */
	private Map<String, SourceEntityDto> sourceEntityDtoMap = null;
	
	/**
	 * 业务模块map
	 */
	private Map<String, BusinessMapDto> businessMapDtoMap = null;

	/***
	 * 字段映射MAP.
	 */
	private   Map<String, List<ApplyScopeDto>> applyScopeDtoMap = null;
	//@ExecuteCache
	@Override
	public Map<String, List<ApplyScopeDto>> getApplyScopeDtoMap() {
		if(this.applyScopeDtoMap == null ){
			w.lock();
			try{
				if(applyScopeDtoMap==null){
					applyScopeDtoMap  =  getConfigService().getApplyScopeDtoMap();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				w.unlock();
			}
		}
		return applyScopeDtoMap;
	}
	private ConfigurationService getConfigService() {
		ConfigurationService  configurationService = rpcProxy.create(ConfigurationService.class);
		return configurationService;
	}
	//@ExecuteCache
	@Override
	public Map<String, BusinessMapDto> getBusinessMap() {
		if(businessMapDtoMap == null ){
			w.lock();
			try{
				if(businessMapDtoMap==null){
					businessMapDtoMap = getConfigService().getBusinessMap();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				w.unlock();
			}
		}
		return businessMapDtoMap;
	}
	//@ExecuteCache
	@Override
	public Map<String, SourceEntityDto> getSourceEntityMap() {
		if(sourceEntityDtoMap == null){
			w.lock();
			try{
				if(sourceEntityDtoMap==null){
					sourceEntityDtoMap = getConfigService().getSourceEntityMap();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				w.unlock();
			}
		}
		return sourceEntityDtoMap;
	}
	//@ExecuteCache
	@Override
	public List<ApplyScopeDto> getApplyScopeList(String moduleId,
			String targetClassName) {
		String scopeKey = moduleId  + "_" + targetClassName;
		return this.getApplyScopeDtoMap().get(scopeKey);
	}

}
