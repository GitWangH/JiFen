package com.huatek.busi.service.impl.phicom.coupons;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDao;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDetailsDao;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDto;
import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBag;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.coupons.PhiCouponsService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiCouponsServiceImpl")
@Transactional
public class PhiCouponsServiceImpl implements PhiCouponsService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiCouponsServiceImpl.class);
	
	@Autowired
	private PhiCouponsDao phiCouponsDao;
	
	@Autowired
	private PhiPlusRightGiftBagDao phiPlusRightGiftBagDao;
	@Autowired
	private PhiPlusRightGiftBagDetailsDao phiPlusRightGiftBagDetailsDao;
	
	@Autowired
	private PhiCouponsDetailDao phiCouponsDetailDao;
	
	@Override
	public void savePhiCouponsDto(PhiCouponsDto entityDto)  {
		log.debug("save phiCouponsDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiCoupons entity = BeanCopy.getInstance().convert(entityDto, PhiCoupons.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiCouponsDao.persistentPhiCoupons(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	
	
	@Override
	public PhiCouponsDto getPhiCouponsDtoById(Long id) {
		log.debug("get phiCoupons by id@" + id);
		PhiCoupons entity = phiCouponsDao.findPhiCouponsById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiCouponsDto entityDto = BeanCopy.getInstance().convert(entity, PhiCouponsDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiCoupons(Long id, PhiCouponsDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiCoupons entity = phiCouponsDao.findPhiCouponsById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiCouponsDao.persistentPhiCoupons(entity);
	}
	
	
	
	@Override
	public void deletePhiCoupons(Long id) {
		log.debug("delete phiCoupons by id@" + id);
		beforeRemove(id);
		PhiCoupons entity = phiCouponsDao.findPhiCouponsById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiCouponsDao.deletePhiCoupons(entity);
	}
	
	@Override
	public DataPage<PhiCouponsDto> getAllPhiCouponsPage(QueryPage queryPage) {
		DataPage<PhiCoupons> dataPage = phiCouponsDao.getAllPhiCoupons(queryPage);
		Map<String,String> phiCouponsCountMap = phiCouponsDao.findPhiCouponsCountGroupByWayId();
		DataPage<PhiCouponsDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiCouponsDto.class);
		List<PhiCouponsDto> phiCouponsDtoList = datPageDto.getContent();
		if(null != phiCouponsDtoList && phiCouponsDtoList.size() > 0){
			for(PhiCouponsDto phiCouponsDto:phiCouponsDtoList){
				if(phiCouponsCountMap.containsKey(phiCouponsDto.getCpnsWayId()+"")){
					phiCouponsDto.setCpnsQuantity(phiCouponsCountMap.get(phiCouponsDto.getCpnsWayId()+""));
				}
			}
		}
		return datPageDto;
	}
	
	@Override
	public List<PhiCouponsDto> getAllPhiCouponsDto() {
		List<PhiCoupons> entityList = phiCouponsDao.findAllPhiCoupons();
		List<PhiCouponsDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiCouponsDto.class);
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
	* @param    phiCouponsDto
	* @param    phiCoupons
	* @return  void    
	* @
	*/
	private void beforeSave(PhiCouponsDto entityDto, PhiCoupons entity) {

	}
	
	
	@Override
	public List<PhiCouponsDto> getPhiCoupons(int cpnsMoney) {
	
		List<PhiCoupons> phiCoupons = phiCouponsDao.fingPhiCouponsBycpnsMoney(cpnsMoney);
		
		List<PhiCouponsDto> entityDto = BeanCopy.getInstance().convertList(phiCoupons, PhiCouponsDto.class);
		return entityDto;
	
	}
	
	
	@Override
	public List<PhiCouponsDto> getPhiCouponsByCpnsName(String  cpnsName) {
	
		List<PhiCoupons> phiCoupons = phiCouponsDao.fingPhiCouponsBycpnsName(cpnsName);
		
		List<PhiCouponsDto> entityDto = BeanCopy.getInstance().convertList(phiCoupons, PhiCouponsDto.class);
		return entityDto;
	
	}
	
	//此方法用来调用修改商品的接口 String data="{\"uid\":1231253,\"scheme_id\":5123345,\"cpns_key\":\"YHQ155555222\"}";//必须为转义
    private JsonObject postHasPhiCouponsDetailToChengShang(PhiCouponsDetail couponsDetial) {   	
    	JsonObject jo = new JsonObject();
    	 jo.addProperty("uid", couponsDetial.getMemberId().getUId());
    	 jo.addProperty("scheme_id", couponsDetial.getCoupWayId());//fangan id
    	 jo.addProperty("cpns_key", couponsDetial.getCoupCode());
      JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"goodsmodify", "goods_modify",jo.getAsJsonObject().toString());	      
      return sss;              
	}



    /**
   	 * PLUS会员优惠劵绑定
   	 * @param phiMember
   	 */
	@Override
	public List<PhiCouponsDetail> bindingCouponsDetailOfPlusPhiMember(PhiMember phiMember) {
		List<PhiCouponsDetail> updatePhiCouponsDetailList = null;
		//2.plus会员开通后发放首次发礼包特权
		String giftBagType  = "firstExclusive";//plus权限Code,首次开通才发放礼包
		PhiPlusRightGiftBag plusRightGiftBag = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagByTaskType(giftBagType);
		if(plusRightGiftBag != null){
			List<PhiPlusRightGiftBagDetails> detailsList = phiPlusRightGiftBagDetailsDao.firstOpenCoupons();
			if(detailsList != null && detailsList.size() > 0 ){
				updatePhiCouponsDetailList = new ArrayList<PhiCouponsDetail>();//预批量更新的优惠劵明细对象集合
				for (PhiPlusRightGiftBagDetails phiPlusRightGiftBagDetails : detailsList) {
					List<PhiCouponsDetail> phicouponDetailList = phiCouponsDetailDao.getCouponsDetailsBycouponsIdAndQulity(phiPlusRightGiftBagDetails.getCpnsWayId(), Integer.parseInt(phiPlusRightGiftBagDetails.getCpnsQuantity()));
					/*
					 //模拟测试商城已使用的优惠劵，补推功能
					List<PhiCouponsDetail> phicouponDetailList = new ArrayList<PhiCouponsDetail>();
					List<PhiCouponsDetail> allPhiCouponsDetail = phiCouponsDetailDao.findAllPhiCouponsDetail();
					for(PhiCouponsDetail testPhiCouponsDetail:allPhiCouponsDetail){
						if("BweuwiqueywzE7158000VF".equals(testPhiCouponsDetail.getCoupCode()) 
								|| "Bweuwiqueywz21610000VG".equals(testPhiCouponsDetail.getCoupCode())
								|| "Bweuwiqueywz735E2000VE".equals(testPhiCouponsDetail.getCoupCode())){
							phicouponDetailList.add(testPhiCouponsDetail);
						}
					}*/
					if(null != phicouponDetailList && phicouponDetailList.size() > 0){
						for (PhiCouponsDetail phiCouponsDetail : phicouponDetailList) {
							phiCouponsDetail.setMemberId(phiMember);
							phiCouponsDetail.setExchangeStatus("1");
							updatePhiCouponsDetailList.add(phiCouponsDetail);//此处优化成批量更新 - 2018-03-13 By Mickey
						}
					}
				}
			}
		}
		if(null != updatePhiCouponsDetailList && updatePhiCouponsDetailList.size() > 0){
			phiCouponsDetailDao.mergeCouponsDetail(updatePhiCouponsDetailList);//执行批量更新
		}
		return updatePhiCouponsDetailList;
	}
	
}
