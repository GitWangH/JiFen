package com.huatek.busi.service.impl.measure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.measure.BusiMeasureMiddleMeasureDao;
import com.huatek.busi.dto.measure.BusiMeasureMiddleMeasureDto;
import com.huatek.busi.model.measure.BusiMeasureMiddleMeasure;
import com.huatek.busi.service.measure.BusiMeasureMiddleMeasureService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * @ClassName: BusiMeasureMiddleMeasureServiceImpl
 * @Description: 中间计量Service接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-12-05 10:46:45
 * @version: 1.0
 */
@Service("busiMeasureMiddleMeasureServiceImpl")
@Transactional
public class BusiMeasureMiddleMeasureServiceImpl implements BusiMeasureMiddleMeasureService {
	
	@Autowired
	BusiMeasureMiddleMeasureDao busiMeasureMiddleMeasureDao;
	
	@Override
	public void saveBusiMeasureMiddleMeasureDto(BusiMeasureMiddleMeasureDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		BusiMeasureMiddleMeasure entity = BeanCopy.getInstance().convert(entityDto, BusiMeasureMiddleMeasure.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddleMeasureDao.persistentBusiMeasureMiddleMeasure(entity);
	}
	
	
	@Override
	public BusiMeasureMiddleMeasureDto getBusiMeasureMiddleMeasureDtoById(Long id) {
		BusiMeasureMiddleMeasure entity = busiMeasureMiddleMeasureDao.findBusiMeasureMiddleMeasureById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiMeasureMiddleMeasureDto entityDto = BeanCopy.getInstance().convert(entity, BusiMeasureMiddleMeasureDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiMeasureMiddleMeasure(Long id, BusiMeasureMiddleMeasureDto entityDto) {
		BusiMeasureMiddleMeasure entity = busiMeasureMiddleMeasureDao.findBusiMeasureMiddleMeasureById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		busiMeasureMiddleMeasureDao.persistentBusiMeasureMiddleMeasure(entity);
	}
	
	
	
	@Override
	public void deleteBusiMeasureMiddleMeasure(Long id) {
		beforeRemove(id);
		BusiMeasureMiddleMeasure entity = busiMeasureMiddleMeasureDao.findBusiMeasureMiddleMeasureById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiMeasureMiddleMeasureDao.deleteBusiMeasureMiddleMeasure(entity);
	}
	
	@Override
	public DataPage<BusiMeasureMiddleMeasureDto> getAllBusiMeasureMiddleMeasurePage(QueryPage queryPage) {
		DataPage<BusiMeasureMiddleMeasure> dataPage = busiMeasureMiddleMeasureDao.getAllBusiMeasureMiddleMeasure(queryPage);
		DataPage<BusiMeasureMiddleMeasureDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiMeasureMiddleMeasureDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiMeasureMiddleMeasureDto> getAllBusiMeasureMiddleMeasureDto() {
		List<BusiMeasureMiddleMeasure> entityList = busiMeasureMiddleMeasureDao.findAllBusiMeasureMiddleMeasure();
		List<BusiMeasureMiddleMeasureDto> dtos = BeanCopy.getInstance().convertList(entityList, BusiMeasureMiddleMeasureDto.class);
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
	* @param    busiMeasureMiddleMeasureDto
	* @param    busiMeasureMiddleMeasure
	* @return  void    
	* @
	*/
	private void beforeSave(BusiMeasureMiddleMeasureDto entityDto, BusiMeasureMiddleMeasure entity) {

	}
}
