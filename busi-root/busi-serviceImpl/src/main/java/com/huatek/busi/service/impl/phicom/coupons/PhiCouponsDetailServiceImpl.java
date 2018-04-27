package com.huatek.busi.service.impl.phicom.coupons;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.coupons.PhiCouponsDetailService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiCouponsDetailServiceImpl")
@Transactional
public class PhiCouponsDetailServiceImpl implements PhiCouponsDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiCouponsDetailServiceImpl.class);
	
	@Autowired
	PhiCouponsDetailDao phiCouponsDetailDao;
	
	@Autowired
	PhiMemberDao phiMemberDao; 
	
	@Override
	public void savePhiCouponsDetailDto(PhiCouponsDetailDto entityDto)  {
		log.debug("save phiCouponsDetailDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiCouponsDetail entity = BeanCopy.getInstance().addFieldMap("memberId", "memberId.id").convert(entityDto, PhiCouponsDetail.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiCouponsDetailDao.persistentPhiCouponsDetail(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	
	
	@Override
	public PhiCouponsDetailDto getPhiCouponsDetailDtoById(Long id) {
		log.debug("get phiCouponsDetail by id@" + id);
		PhiCouponsDetail entity = phiCouponsDetailDao.findPhiCouponsDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiCouponsDetailDto entityDto = BeanCopy.getInstance().convert(entity, PhiCouponsDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiCouponsDetail(Long id, PhiCouponsDetailDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiCouponsDetail entity = phiCouponsDetailDao.findPhiCouponsDetailById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiCouponsDetailDao.persistentPhiCouponsDetail(entity);
	}
	
	
	
	@Override
	public void deletePhiCouponsDetail(Long id) {
		log.debug("delete phiCouponsDetail by id@" + id);
		beforeRemove(id);
		PhiCouponsDetail entity = phiCouponsDetailDao.findPhiCouponsDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiCouponsDetailDao.deletePhiCouponsDetail(entity);
	}
	
	@Override
	public DataPage<PhiCouponsDetailDto> getAllPhiCouponsDetailPage(QueryPage queryPage) {
		DataPage<PhiCouponsDetail> dataPage = phiCouponsDetailDao.getAllPhiCouponsDetail(queryPage);
		
		DataPage<PhiCouponsDetailDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiCouponsDetailDto.class);	
		/*for (int i = 0; i < datPageDto.getContent().size(); i++) {
			datPageDto.getContent().get(i).setMemberId(dataPage.getContent().get(i).getMemberId().getRealName());
		}*/
		return datPageDto;
	}
	
	@Override
	public List<PhiCouponsDetailDto> getAllPhiCouponsDetailDto() {
		List<PhiCouponsDetail> entityList = phiCouponsDetailDao.findAllPhiCouponsDetail();
		List<PhiCouponsDetailDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiCouponsDetailDto.class);
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
	* @param    phiCouponsDetailDto
	* @param    phiCouponsDetail
	* @return  void    
	* @
	*/
	private void beforeSave(PhiCouponsDetailDto entityDto, PhiCouponsDetail entity) {

	}


	@Override
	public DataPage<PhiCouponsDetailDto> getPhiCouponsDetailDtoByfatharId(QueryPage queryPage) {
	       /*List<PhiCouponsDetail> list = phiCouponsDetailDao.getCouponsDetailsBycouponsId(id);
	       DataPage<PhiCouponsDetailDto> dataPage = new DataPage<PhiCouponsDetailDto>();
	       dataPage.setContent(phiCouponsDetailDtolist);*/
			DataPage<PhiCouponsDetail> phiCouponPage = phiCouponsDetailDao.getAllPhiCouponsDetail(queryPage);
			DataPage<PhiCouponsDetailDto>  phiCouponsDetailDtoPage = BeanCopy.getInstance().convertPage(phiCouponPage, PhiCouponsDetailDto.class);
	       return phiCouponsDetailDtoPage;
	}




	@Override
	public DataPage<PhiCouponsDetailDto> getPhiCouponsDetailById(Long wayId,QueryPage queryPage) {
		QueryParam queryParam = new QueryParam();
		queryParam.setField("coupWayId");
		queryParam.setLogic("=");
		queryParam.setValue(String.valueOf(wayId));
		queryPage.getQueryParamList().add(queryParam);
		DataPage<PhiCouponsDetail> phiCouponPage = phiCouponsDetailDao.getAllPhiCouponsDetail(queryPage);
		DataPage<PhiCouponsDetailDto>  phiCouponsDetailDtoPage = BeanCopy.getInstance().addFieldMap("memberId.id", "memberId").convertPage(phiCouponPage, PhiCouponsDetailDto.class);
		List<PhiCouponsDetailDto> list = phiCouponsDetailDtoPage.getContent();
		if(null != list && !list.isEmpty()){
			for(PhiCouponsDetailDto dto : list){
				if(null != dto.getMemberId()){
					PhiMember phiMember = phiMemberDao.findPhiMemberById(dto.getMemberId());
					if(null != phiMember){
						dto.setMemberName(phiMember.getTel());
					}
				}
			}
		}
		return phiCouponsDetailDtoPage;
	}


	/**
	 * 根据方案ID获取一条有效的优惠劵
	 * @param coupWayId
	 * @return
	 */
	@Override
	public PhiCouponsDetail getPhiCouponsDetailDtoByCoupWayId(Long coupWayId) {
		List<PhiCouponsDetail> phicouponDetailList = phiCouponsDetailDao.getCouponsDetailsBycouponsIdAndQulity(coupWayId, 1);
		return null != phicouponDetailList && phicouponDetailList.size() >0? phicouponDetailList.get(0) : null;
	}



	/**
	 * PLUS会员开通专用
	 */
	@Override
	public void updatePhiCouponsDetailForOpenPlus(PhiCouponsDetail newCouponsDetial) {
		phiCouponsDetailDao.saveOrUpdatePhiCouponsDetail(newCouponsDetial);
	}
}
