package com.huatek.frame.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import javax.annotation.PostConstruct;
//import javax.transaction.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.huatek.frame.authority.dto.ApplyScopeDto;
import com.huatek.frame.authority.dto.BusinessMapDto;
import com.huatek.frame.authority.dto.SourceEntityDto;
import com.huatek.frame.authority.service.ConfigurationService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.dao.FwApplyScopeDao;
import com.huatek.frame.dao.FwBusinessMapDao;
import com.huatek.frame.dao.FwSourceEntityDao;
import com.huatek.frame.dao.model.FwApplyScope;
import com.huatek.frame.dao.model.FwBusinessMap;
import com.huatek.frame.dao.model.FwSourceEntity;
import com.huatek.rpc.server.core.RpcService;
@RpcService(ConfigurationService.class)
public class ConfigurationServiceComponent implements ConfigurationService, ConfiguraionRefreshService {
	private static final java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();
	private static final Lock w = lock.writeLock();
	@Autowired
	FwSourceEntityDao fwSourceEntityDao;
	
	@Autowired
	FwApplyScopeDao fwApplyScopeDao;
	
	@Autowired
	FwBusinessMapDao fwBusinessMapDao;
	
	/**
	 * 权限实体Map.
	 */
	private Map<String, SourceEntityDto> sourceEntityDtoMap = null;//new HashMap<String, SourceEntityDto>();;
	
	/**
	 * 业务模块map
	 */
	private Map<String, BusinessMapDto> businessMapDtoMap = null;//new HashMap<String, BusinessMapDto>();

	/***
	 * 字段映射MAP.
	 */
	private   Map<String, List<ApplyScopeDto>> applyScopeDtoMap = null;//new HashMap<String, List<ApplyScopeDto>>();
	
	public Map<String, List<ApplyScopeDto>> getApplyScopeDtoMap(){
		if(applyScopeDtoMap==null){
			try{
				w.lock();
				this.freshFwApplyScopeMap();
			}finally{
				w.unlock();
			}
		}
		return applyScopeDtoMap;
	}

	@Override
	public Map<String, BusinessMapDto> getBusinessMap() {
		if(businessMapDtoMap==null){
			try{
				w.lock();
				this.freshFwBusinessMapMap();
			}finally{
				w.unlock();
			}
		}
		return this.businessMapDtoMap;
	}

	@Override
	public Map<String, SourceEntityDto> getSourceEntityMap() {
		if(sourceEntityDtoMap==null){
			try{
				w.lock();
				this.freshFwSourceEntityMap();
			}finally{
				w.unlock();
			}
		}
		return this.sourceEntityDtoMap;
	}
	@Override
	public void freshFwSourceEntityMap() {
		if(sourceEntityDtoMap!=null){
			return;
		}
		sourceEntityDtoMap = new HashMap<String, SourceEntityDto>();
		List<FwSourceEntity> fwSourceEntityList = fwSourceEntityDao.findAllFwSourceEntity();
		List<SourceEntityDto> fwSourceEntityDtoList  = BeanCopy.getInstance().convertList(fwSourceEntityList, SourceEntityDto.class);
		for (SourceEntityDto fwSourceEntity : fwSourceEntityDtoList) {
			sourceEntityDtoMap.put(fwSourceEntity.getId() + "", fwSourceEntity);
		}
	}
	@Override
	public  void freshFwBusinessMapMap() {
		if(businessMapDtoMap!=null){
			return;
		}
		businessMapDtoMap = new HashMap<String, BusinessMapDto>();
		List<FwBusinessMap> busmapList = fwBusinessMapDao.findAllFwBusinessMap();
		List<BusinessMapDto> fwBusinessMapDtoList = FwBusinessMapServiceImpl.beanToDto.convertList(busmapList, BusinessMapDto.class);
		for(BusinessMapDto busMap : fwBusinessMapDtoList) {
			businessMapDtoMap.put(busMap.getId() + "", busMap);
		}
	}
	@Override
	public void freshFwApplyScopeMap() {
		if(applyScopeDtoMap!=null){
			return;
		}
		applyScopeDtoMap = new HashMap<String,  List<ApplyScopeDto>>();
		List<FwApplyScope> fwApplyScopeMapList = fwApplyScopeDao.findAllFwApplyScope();
		for (FwApplyScope fwApplyScope : fwApplyScopeMapList) {
			BusinessMapDto busMap = getBusinessMap().get(fwApplyScope.getFwBusinessMap().getId() + "");
			Long sourceId = busMap.getFwSourceId();
			
			String mapKey = sourceId + "_" + fwApplyScope.getTargetClass();
			List<ApplyScopeDto> applyScopeList = applyScopeDtoMap.get(mapKey);
			if(applyScopeList == null) {
				applyScopeList = new ArrayList<ApplyScopeDto>();
			}
			applyScopeList.add(FwApplyScopeServiceImpl.beanToDto.convert(fwApplyScope, ApplyScopeDto.class));
			applyScopeDtoMap.put(mapKey, applyScopeList);
		}
		
	}
	
	/***
	 * 判断是否字段应用权限.
	 * 
	 * @param moduleId
	 *            模块ID.
	 * @param targetClassName
	 *            字段所在的Class.
	 * @param fieldName
	 *            字段名.
	 * @return 否或者是.
	 */
	public List<ApplyScopeDto> getApplyScopeList(final String moduleId,
			final String targetClassName){
		String scopeKey = moduleId  + "_" + targetClassName;
		return this.getApplyScopeDtoMap().get(scopeKey);
	}

}
