package com.huatek.busi.service.impl.base;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.base.BusiBaseImageListSubConnectionTableDao;
import com.huatek.busi.dao.base.BusiBaseImageListSubConnectionTableShowDao;
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableDto;
import com.huatek.busi.dto.base.BusiBaseImageListSubConnectionTableShowDto;
import com.huatek.busi.dto.base.paramEntity.BusiBaseImageListSubConnectionTableParamEntity;
import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTable;
import com.huatek.busi.model.base.BusiBaseImageListSubConnectionTableShow;
import com.huatek.busi.service.base.BusiBaseImageListSubConnectionTableService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * 形象清单和分部分项挂接
 * @author eli_cui
 *
 */
@Service("busiBaseImageListSubConnectionTableServiceImpl")
@Transactional
public class BusiBaseImageListSubConnectionTableServiceImpl implements BusiBaseImageListSubConnectionTableService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiBaseImageListSubConnectionTableServiceImpl.class);
	
	@Autowired
	BusiBaseImageListSubConnectionTableDao busiBaseImageListSubConnectionTableDao;
	
	
	@Autowired //形象清单与分部分项挂接Dao
	BusiBaseImageListSubConnectionTableShowDao busiBaseImageListSubConnectionTableShowDao;
	
	@Override
	public void saveBusiBaseImageListSubConnectionTableDto(BusiBaseImageListSubConnectionTableDto entityDto)  {
		log.debug("save busiBaseImageListSubConnectionTableDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiBaseImageListSubConnectionTable entity = BeanCopy.getInstance().convert(entityDto, BusiBaseImageListSubConnectionTable.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiBaseImageListSubConnectionTableDao.persistentBusiBaseImageListSubConnectionTable(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiBaseImageListSubConnectionTableDto getBusiBaseImageListSubConnectionTableDtoById(Long id) {
		log.debug("get busiBaseImageListSubConnectionTable by id@" + id);
		BusiBaseImageListSubConnectionTable entity = busiBaseImageListSubConnectionTableDao.findBusiBaseImageListSubConnectionTableById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiBaseImageListSubConnectionTableDto entityDto = BeanCopy.getInstance().convert(entity, BusiBaseImageListSubConnectionTableDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiBaseImageListSubConnectionTable(Long id, BusiBaseImageListSubConnectionTableDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiBaseImageListSubConnectionTable entity = busiBaseImageListSubConnectionTableDao.findBusiBaseImageListSubConnectionTableById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiBaseImageListSubConnectionTableDao.persistentBusiBaseImageListSubConnectionTable(entity);
	}
	
	
	
	@Override
	public void deleteBusiBaseImageListSubConnectionTable(List<Long> selectedIdList) {
		//log.debug("delete busiBaseImageListSubConnectionTable by id@" + id);
		//beforeRemove(id);
		int size = selectedIdList.size();
		if(size == 1){
			BusiBaseImageListSubConnectionTable entity = busiBaseImageListSubConnectionTableDao.findBusiBaseImageListSubConnectionTableById(selectedIdList.get(0));
			if (null == entity) {
				throw new ResourceNotFoundException(selectedIdList.get(0));
			}
			busiBaseImageListSubConnectionTableDao.deleteBusiBaseImageListSubConnectionTable(entity);
		} else {
			List<BusiBaseImageListSubConnectionTable> entityList = busiBaseImageListSubConnectionTableDao.getBusiBaseImageListSubConnectionTableBySelectedId(selectedIdList);
			busiBaseImageListSubConnectionTableDao.batchDelete(entityList, size);
		}
	}
	
	@Override
	public DataPage<BusiBaseImageListSubConnectionTableShowDto> getAllBusiBaseImageListSubConnectionTablePage(QueryPage queryPage, Long imageId) {
		DataPage<BusiBaseImageListSubConnectionTableShow> dataPage = busiBaseImageListSubConnectionTableShowDao.getAllBusiBaseImageListSubConnectionTable(queryPage, imageId);
		DataPage<BusiBaseImageListSubConnectionTableShowDto> dataPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiBaseImageListSubConnectionTableShowDto.class);
		return dataPageDto;
	}
	
	@Override
	public List<BusiBaseImageListSubConnectionTableDto> getAllBusiBaseImageListSubConnectionTableDto() {
		List<BusiBaseImageListSubConnectionTable> entityList = busiBaseImageListSubConnectionTableDao.findAllBusiBaseImageListSubConnectionTable();
		List<BusiBaseImageListSubConnectionTableDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiBaseImageListSubConnectionTableDto.class);
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
	* @param    busiBaseImageListSubConnectionTableDto
	* @param    busiBaseImageListSubConnectionTable
	* @return  void    
	* @
	*/
	private void beforeSave(BusiBaseImageListSubConnectionTableDto entityDto, BusiBaseImageListSubConnectionTable entity) {

	}
	
	@Override
	public void saveBusiBaseImageListSubConnectionTable(BusiBaseImageListSubConnectionTableParamEntity entity) {
		/**形象清单id*/
		Long imageId = entity.getImageId();
		/**分部分项id列表*/
		List<Long> SubEngineeringIdList = entity.getSubEngineeringIdList();
		for(Long subEngineeringId : SubEngineeringIdList){
			BusiBaseImageListSubConnectionTable busiBaseImageListSubConnectionTable = new BusiBaseImageListSubConnectionTable();
			busiBaseImageListSubConnectionTable.setSubEngineeringId(subEngineeringId);
			busiBaseImageListSubConnectionTable.setImageListId(imageId);
			busiBaseImageListSubConnectionTableDao.persistentBusiBaseImageListSubConnectionTable(busiBaseImageListSubConnectionTable);
		}
	}
}
