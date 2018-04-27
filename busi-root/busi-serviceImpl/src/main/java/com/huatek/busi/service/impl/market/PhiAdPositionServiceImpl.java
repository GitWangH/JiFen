package com.huatek.busi.service.impl.market;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.market.PhiAdPositionDao;
import com.huatek.busi.dao.market.PhiPhoInfoDao;
import com.huatek.busi.dto.market.PhiAdPositionDto;
import com.huatek.busi.model.market.PhiAdPosition;
import com.huatek.busi.model.market.PhiPhoInfo;
import com.huatek.busi.service.market.PhiAdPositionService;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiAdPositionServiceImpl")
@Transactional
public class PhiAdPositionServiceImpl implements PhiAdPositionService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiAdPositionServiceImpl.class);
	
	@Autowired
	PhiAdPositionDao phiAdPositionDao;
	
	@Autowired
	PhiPhoInfoDao phiPhoInfoDao;
	
	@Autowired
	CmdFileMangerService cmdFileMangerService;
	
	@Override
	public void savePhiAdPositionDto(PhiAdPositionDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		PhiAdPosition entity = BeanCopy.getInstance().convert(entityDto, PhiAdPosition.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiAdPositionDao.persistentPhiAdPosition(entity);
	}
	
	
	@Override
	public PhiAdPositionDto getPhiAdPositionDtoById(Long id) {
		PhiAdPosition entity = phiAdPositionDao.findPhiAdPositionById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiAdPositionDto entityDto = BeanCopy.getInstance().convert(entity, PhiAdPositionDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiAdPosition(Long id, PhiAdPositionDto entityDto) {
		PhiAdPosition entity = phiAdPositionDao.findPhiAdPositionById(id);
		BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd hh:mm:ss").mapIgnoreId(entityDto, entity);
		//进行持久化保存
		phiAdPositionDao.persistentPhiAdPosition(entity);
	}
	
	
	
	@Override
	public void deletePhiAdPosition(Long id) {
		beforeRemove(id);
		PhiAdPosition entity = phiAdPositionDao.findPhiAdPositionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiAdPositionDao.deletePhiAdPosition(entity);
	}
	
	@Override
	public DataPage<PhiAdPositionDto> getAllPhiAdPositionPage(QueryPage queryPage) {
		DataPage<PhiAdPosition> dataPage = phiAdPositionDao.getAllPhiAdPosition(queryPage);
		DataPage<PhiAdPositionDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiAdPositionDto.class);
		List<PhiAdPositionDto> list = datPageDto.getContent();
		for(PhiAdPositionDto dto : list){
			List<PhiPhoInfo> phiPhoInfoList = phiPhoInfoDao.getAdPositionAndPhoInfoByAdCode(dto.getAdCode());
			if(null != phiPhoInfoList && phiPhoInfoList.size() > 0){
				dto.setPhoLink(phiPhoInfoList.get(0).getPhoLink());
				List<CmdFileMangerDto> cmdFileMangerDtoList = cmdFileMangerService.getCmdFileDtoByBusiId(phiPhoInfoList.get(0).getPhoUuidName());
				if(null != cmdFileMangerDtoList && cmdFileMangerDtoList.size() > 0){
					dto.setPhoUrl(cmdFileMangerDtoList.get(0).getId().toString());
				}else{
					dto.setPhoUrl("");
				}
			}else{
				dto.setPhoLink("");
				dto.setPhoUrl("");
			}
		}
		return datPageDto;
	}
	
	@Override
	public List<PhiAdPositionDto> getAllPhiAdPositionDto() {
		List<PhiAdPosition> entityList = phiAdPositionDao.findAllPhiAdPosition();
		List<PhiAdPositionDto> dtos = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd hh:mm:ss").convertList(entityList, PhiAdPositionDto.class);
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
	* @param    phiAdPositionDto
	* @param    phiAdPosition
	* @return  void    
	* @
	*/
	private void beforeSave(PhiAdPositionDto entityDto, PhiAdPosition entity) {

	}
	/*批量添加*/
	@Override
	public void batchAdd(List<PhiAdPositionDto> phiAdPositionDtoList){
		//List<PhiAdPosition> entityList = phiAdPositionDao.findAllPhiAdPosition();
		List<PhiAdPosition> entityList = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd hh:mm:ss").convertList(phiAdPositionDtoList, PhiAdPosition.class);
		phiAdPositionDao.batchAdd(entityList);
	}


	@Override
	public List<PhiAdPositionDto> getAdPositionAndPhoInfoByAdCode(String adCode) {
		
		List<PhiAdPosition> entityList = phiAdPositionDao.getAdPositionAndPhoInfoByAdCode(adCode);
		List<PhiAdPositionDto> phiAdPositionDtoList = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd hh:mm:ss").convertList(entityList, PhiAdPositionDto.class);
		return phiAdPositionDtoList;
	}
}
