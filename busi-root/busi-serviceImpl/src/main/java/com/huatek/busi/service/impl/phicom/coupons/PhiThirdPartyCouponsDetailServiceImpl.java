package com.huatek.busi.service.impl.phicom.coupons;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDetailDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dto.phicom.coupons.PhiThirdPartyCouponsDetailDto;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.coupons.PhiThirdPartyCouponsDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiThirdPartyCouponsDetailServiceImpl")
@Transactional
public class PhiThirdPartyCouponsDetailServiceImpl implements PhiThirdPartyCouponsDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiThirdPartyCouponsDetailServiceImpl.class);
	
	@Autowired
	PhiThirdPartyCouponsDetailDao phiThirdPartyCouponsDetailDao;
	
	@Autowired
	PhiMemberDao phiMemberDao; 
	
	@Override
	public void savePhiThirdPartyCouponsDetailDto(PhiThirdPartyCouponsDetailDto entityDto)  {
		log.debug("save phiThirdPartyCouponsDetailDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiThirdPartyCouponsDetail entity = DTOUtils.map(entityDto, PhiThirdPartyCouponsDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiThirdPartyCouponsDetailDao.persistentPhiThirdPartyCouponsDetail(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiThirdPartyCouponsDetailDto getPhiThirdPartyCouponsDetailDtoById(Long id) {
		log.debug("get phiThirdPartyCouponsDetail by id@" + id);
		PhiThirdPartyCouponsDetail entity = phiThirdPartyCouponsDetailDao.findPhiThirdPartyCouponsDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiThirdPartyCouponsDetailDto entityDto = DTOUtils.map(entity, PhiThirdPartyCouponsDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiThirdPartyCouponsDetail(Long id, PhiThirdPartyCouponsDetailDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		PhiThirdPartyCouponsDetail entity = phiThirdPartyCouponsDetailDao.findPhiThirdPartyCouponsDetailById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiThirdPartyCouponsDetailDao.persistentPhiThirdPartyCouponsDetail(entity);
	}
	
	
	
	@Override
	public void deletePhiThirdPartyCouponsDetail(Long id) {
		log.debug("delete phiThirdPartyCouponsDetail by id@" + id);
		beforeRemove(id);
		PhiThirdPartyCouponsDetail entity = phiThirdPartyCouponsDetailDao.findPhiThirdPartyCouponsDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiThirdPartyCouponsDetailDao.deletePhiThirdPartyCouponsDetail(entity);
	}
	
	@Override
	public DataPage<PhiThirdPartyCouponsDetailDto> getAllPhiThirdPartyCouponsDetailPage(QueryPage queryPage) {
		DataPage<PhiThirdPartyCouponsDetail> dataPage = phiThirdPartyCouponsDetailDao.getAllPhiThirdPartyCouponsDetail(queryPage);
		DataPage<PhiThirdPartyCouponsDetailDto> datPageDto = DTOUtils.mapPage(dataPage, PhiThirdPartyCouponsDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiThirdPartyCouponsDetailDto> getAllPhiThirdPartyCouponsDetailDto() {
		List<PhiThirdPartyCouponsDetail> entityList = phiThirdPartyCouponsDetailDao.findAllPhiThirdPartyCouponsDetail();
		List<PhiThirdPartyCouponsDetailDto> dtos = DTOUtils.mapList(entityList, PhiThirdPartyCouponsDetailDto.class);
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
	* @param    phiThirdPartyCouponsDetailDto
	* @param    phiThirdPartyCouponsDetail
	* @return  void    
	* @
	*/
	private void beforeSave(PhiThirdPartyCouponsDetailDto entityDto, PhiThirdPartyCouponsDetail entity) {

	}


	@Override
	public DataPage<PhiThirdPartyCouponsDetailDto> getThirdPartyPhiCouponsDetailById(String cpnsId, QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("coupId");
		queryParam.setLogic("=");
		queryParam.setValue(cpnsId);
		queryPage.getQueryParamList().add(queryParam);
		DataPage<PhiThirdPartyCouponsDetail> dataPage = phiThirdPartyCouponsDetailDao.getAllPhiThirdPartyCouponsDetail(queryPage);
		DataPage<PhiThirdPartyCouponsDetailDto> datPageDto = BeanCopy.getInstance().addFieldMap("phiMember.id", "memberId").convertPage(dataPage, PhiThirdPartyCouponsDetailDto.class);
		List<PhiThirdPartyCouponsDetailDto> list = datPageDto.getContent();
		if(null != list && !list.isEmpty()){
			for(PhiThirdPartyCouponsDetailDto dto : list){
				if(null != dto.getMemberId()){
					PhiMember phiMember = phiMemberDao.findPhiMemberById(dto.getMemberId());
					if(null != phiMember){
						dto.setMemberName(phiMember.getTel());
					}
				}
			}
		}
		return datPageDto;
		
	}
}
