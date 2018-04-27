package com.huatek.busi.service.impl.base;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.base.BusiBaseQuantityListSubConnectionTableDao;
import com.huatek.busi.dao.base.BusiBaseQuantityListSubConnectionTableShowDao;
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableDto;
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableShowDto;
import com.huatek.busi.dto.base.BusiBaseQuantityListSubConnectionTableDto;
import com.huatek.busi.dto.base.paramEntity.BusiBaseQuantityListSubConnectionTableParamEntity;
import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTable;
import com.huatek.busi.model.base.BusiBaseQuantityListSubConnectionTableShow;
import com.huatek.busi.service.base.BusiBaseQuantityListSubConnectionTableService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * 工程量清单和分部分项挂接
 * @author eli_cui
 *
 */
@Service("busiBaseQuantityListSubConnectionTableServiceImpl")
@Transactional
public class BusiBaseQuantityListSubConnectionTableServiceImpl implements BusiBaseQuantityListSubConnectionTableService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiBaseQuantityListSubConnectionTableServiceImpl.class);
	
	@Autowired
	BusiBaseQuantityListSubConnectionTableDao busiBaseQuantityListSubConnectionTableDao;
	
	@Autowired
	BusiBaseQuantityListSubConnectionTableShowDao busiBaseQuantityListSubConnectionTableShowDao;
	
	@Override
	public void saveBusiBaseQuantityListSubConnectionTableDto(BusiBaseQuantityListSubConnectionTableDto entityDto)  {
		log.debug("save busiBaseQuantityListSubConnectionTableDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiBaseQuantityListSubConnectionTable entity = BeanCopy.getInstance().convert(entityDto, BusiBaseQuantityListSubConnectionTable.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiBaseQuantityListSubConnectionTableDao.persistentBusiBaseQuantityListSubConnectionTable(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiBaseQuantityListSubConnectionTableDto getBusiBaseQuantityListSubConnectionTableDtoById(Long id) {
		log.debug("get busiBaseQuantityListSubConnectionTable by id@" + id);
		BusiBaseQuantityListSubConnectionTable entity = busiBaseQuantityListSubConnectionTableDao.findBusiBaseQuantityListSubConnectionTableById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiBaseQuantityListSubConnectionTableDto entityDto = BeanCopy.getInstance().convert(entity, BusiBaseQuantityListSubConnectionTableDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiBaseQuantityListSubConnectionTable(Long id, BusiBaseQuantityListSubConnectionTableDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiBaseQuantityListSubConnectionTable entity = busiBaseQuantityListSubConnectionTableDao.findBusiBaseQuantityListSubConnectionTableById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiBaseQuantityListSubConnectionTableDao.persistentBusiBaseQuantityListSubConnectionTable(entity);
	}
	
	
	
	@Override
	public void deleteBusiBaseQuantityListSubConnectionTable(List<Long> selectedIdList) {
		//log.debug("delete busiBaseQuantityListSubConnectionTable by id@" + id);
		//beforeRemove(id); batchDelete
		int size = selectedIdList.size();
		if(size == 1){
			BusiBaseQuantityListSubConnectionTable entity = busiBaseQuantityListSubConnectionTableDao.findBusiBaseQuantityListSubConnectionTableById(selectedIdList.get(0));
			if (null == entity) {
				throw new ResourceNotFoundException(selectedIdList.get(0));
			}
			busiBaseQuantityListSubConnectionTableDao.deleteBusiBaseQuantityListSubConnectionTable(entity);
		} else {
			List<BusiBaseQuantityListSubConnectionTable> entityList = busiBaseQuantityListSubConnectionTableDao.getBusiBaseQuantityListSubConnectionTableListBySelectedId(selectedIdList);
			busiBaseQuantityListSubConnectionTableDao.batchDelete(entityList, size);
		}
	}
	
	@Override
	public DataPage<BusiBaseImageListSubConnectionTableDto> getAllBusiBaseQuantityListSubConnectionTablePage(QueryPage queryPage, Long id) {
		DataPage<BusiBaseQuantityListSubConnectionTableShow> dataPage = busiBaseQuantityListSubConnectionTableShowDao.getAllBusiBaseQuantityListSubConnectionTableShow(queryPage, id);
		DataPage<BusiBaseImageListSubConnectionTableDto> dataPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiBaseImageListSubConnectionTableDto.class); 
		return dataPageDto;
	}
	
	@Override
	public List<BusiBaseQuantityListSubConnectionTableDto> getAllBusiBaseQuantityListSubConnectionTableDto() {
		List<BusiBaseQuantityListSubConnectionTable> entityList = busiBaseQuantityListSubConnectionTableDao.findAllBusiBaseQuantityListSubConnectionTable();
		List<BusiBaseQuantityListSubConnectionTableDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiBaseQuantityListSubConnectionTableDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(Long id) {

	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiBaseQuantityListSubConnectionTableDto
	* @param    busiBaseQuantityListSubConnectionTable
	* @return  void    
	* @
	*/
	private void beforeSave(BusiBaseQuantityListSubConnectionTableDto entityDto, BusiBaseQuantityListSubConnectionTable entity) {

	}
	
	@Override
	public void saveBusiBaseQuantityListSubConnectionTable(BusiBaseQuantityListSubConnectionTableParamEntity entity) {
		Long subEngineeringId = entity.getSubEngineeringId();
		List<Long> engineeringQuantityIdList = entity.getEngineeringQuantityIdList();
		for(Long engineeringId : engineeringQuantityIdList){
			BusiBaseQuantityListSubConnectionTable busiBaseQuantityListSubConnectionTable = new BusiBaseQuantityListSubConnectionTable();
			busiBaseQuantityListSubConnectionTable.setEngineeringQuantityId(engineeringId);
			busiBaseQuantityListSubConnectionTable.setSubEngineeringId(subEngineeringId);
			busiBaseQuantityListSubConnectionTableDao.saveOrUpdateBusiBaseQuantityListSubConnectionTable(busiBaseQuantityListSubConnectionTable);
		}
	}
}
