package com.huatek.busi.service.impl.phicom.plusmember;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightDetailsDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDetailsDao;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusAllRightDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDetailsDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightGiftBagDetailsDto;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRight;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightDetails;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBag;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.plusmember.PhiPlusAllRightService;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;


@Service("phiPlusAllRightServiceImpl")
@Transactional
public class PhiPlusAllRightServiceImpl implements PhiPlusAllRightService {
	 
	private static final Logger log = LoggerFactory.getLogger(PhiPlusAllRightServiceImpl.class);

	@Autowired
	CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	 
	@Autowired
	PhiPlusRightDao phiPlusRightDao;
	
	@Autowired
	PhiPlusRightGiftBagDao phiPlusRightGiftBagDao;
	
	@Autowired
	PhiPlusRightDetailsDao phiPlusRightDetailsDao;
	@Autowired
	PhiPlusRightGiftBagDetailsDao phiPlusRightGiftBagDetailsDao;
	@Autowired
	PhiCouponsDetailDao phiCouponsDetailDao;
	@Autowired
	PhiMemberDao phiMemberDao;
	
	@Override
	public DataPage<PhiPlusAllRightDto> getAllPhiPlusRightPage(QueryPage queryPage) {
		DataPage<PhiPlusRightGiftBag> giftBagdataPage = phiPlusRightGiftBagDao.getAllPhiPlusRightGiftBag(queryPage);
		DataPage<PhiPlusAllRightDto> datPageDto = BeanCopy.getInstance().convertPage(giftBagdataPage, PhiPlusAllRightDto.class);
		
		DataPage<PhiPlusRight> rightPage = phiPlusRightDao.getAllPhiPlusRight(queryPage);
		 datPageDto = BeanCopy.getInstance().convertPage(rightPage, PhiPlusAllRightDto.class);
				
		//DataPage<PhiPlusRight> dataPage = PhiPlusAllRightDto.getAllPhiPlusRight(queryPage);
		//DataPage<PhiPlusRightDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusRightDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusAllRightDto> getAllPhiPlusRightDto() {
		List<PhiPlusRight> rightsList = phiPlusRightDao.findAllPhiPlusRight();
		PhiPlusRight PhiPlusRight = rightsList.get(0);
		PhiPlusRightDetails plusRightDetails = PhiPlusRight.getPlusRightDetail();
		int scoreOrMutiply = plusRightDetails.getScoreOrMutiply();
		List<PhiPlusAllRightDto> rightsListDtos = new ArrayList<PhiPlusAllRightDto>();
		if(0 == scoreOrMutiply ){
			//奖励的值(翻倍)
			rightsListDtos = BeanCopy.getInstance().addFieldMap("taskname", "rightName")
					.addFieldMap("taskremark", "remark")
					.addFieldMap("taskswitch", "isValidate")
					.addFieldMap("plusRightDetail.scoreValue2", "award").convertList(rightsList, PhiPlusAllRightDto.class);
			
		}else if(1 == scoreOrMutiply  ){
			//奖励的（值额外的）
			rightsListDtos = BeanCopy.getInstance().addFieldMap("taskname", "rightName")
					.addFieldMap("taskremark", "remark")
					.addFieldMap("taskswitch", "isValidate")
					.addFieldMap("plusRightDetail.scoreValue1", "award").convertList(rightsList, PhiPlusAllRightDto.class);
		}

		List<PhiPlusRightGiftBag> giftBagsList = phiPlusRightGiftBagDao.findAllPhiPlusRightGiftBag();
		
		List<PhiPlusAllRightDto> allRightDtos = new ArrayList<PhiPlusAllRightDto>();

			allRightDtos = BeanCopy.getInstance().addFieldMap("giftBagType", "tasktype").addFieldMap("giftPackageMoney", "award").convertList(giftBagsList, PhiPlusAllRightDto.class);
			
			rightsListDtos.addAll(allRightDtos);
			
			return rightsListDtos;
	}

	
	
	@Override
	public void savePhiPlusAllRightDto(PhiPlusAllRightDto entityDto)  {
		log.debug("save PhiPlusAllRightDto @" + entityDto);
		PhiPlusRight plusRight = BeanCopy.getInstance().addFieldMap("rightName", "taskname").addFieldMap("remark", "taskremark").addFieldMap("isValidate", "taskswitch").convert(entityDto, PhiPlusRight.class);
		
		PhiPlusRightGiftBag plusRightGiftBag = BeanCopy.getInstance().addFieldMap("tasktype", "giftBagType").convert(entityDto, PhiPlusRightGiftBag.class);
		phiPlusRightGiftBagDao.persistentPhiPlusRightGiftBag(plusRightGiftBag);
		//保存之前操作
		//beforeSave(entityDto, entity);
		//进行持久化保存
		String tasktype = entityDto.getTasktype();
		String award  = null ;
		phiPlusRightDao.persistentPhiPlusRight(plusRight);
		if("forPayPoints".equals(tasktype)){
			
		}else if("firstExclusive".equals(tasktype)) {
			
		}else if ("everyMothExclusive".equals(tasktype)) {
			
		}
		int scoreOrMutiply =  entityDto.getScoreOrMutiply();
		if(1 == scoreOrMutiply){
			award = entityDto.getScorevalue1();
		}else if (0 == scoreOrMutiply) {
			 award = entityDto.getScorevalue2();
		}
		//根据页面传进来的值设置保存的值信息
		PhiPlusRightDetails plusRightDetails = BeanCopy.getInstance().convert(entityDto, PhiPlusRightDetails.class);
		//保存之前操作
		beforeSave(entityDto, plusRightDetails);
		//进行持久化保存
		phiPlusRightDetailsDao.persistentPhiPlusRightDetails(plusRightDetails);
	}
	
	
	
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    phiPlusRightDetailsDto
	* @param    phiPlusRightDetails
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusAllRightDto entityDto, PhiPlusRightDetails entity) {

	}

	@Override
	public PhiPlusAllRightDto getPhiPlusAllRightDtoById(Long id) {
		log.debug("get phiPlusRightGiftBag by id@" + id);
		PhiPlusRightGiftBag plusRightGiftBag = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagById(id);
		PhiPlusRight plusRight = phiPlusRightDao.findPhiPlusRightById(id);
		if(null == plusRight){
			if (null == plusRightGiftBag) {
				 throw new ResourceNotFoundException(id);
			}
			//礼包实体转换到会员所有权益的dto中
			PhiPlusAllRightDto giftBagRightDto = BeanCopy.getInstance().addFieldMap("giftBagType", "tasktype").addFieldMap("giftPackageMoney", "award").convert(plusRightGiftBag, PhiPlusAllRightDto.class);
			//获得礼包特权的优惠券信息
			List<PhiPlusRightGiftBagDetails>  giftBagDetails = plusRightGiftBag.getPlusRightGiftBagDetailsList();
			List<PhiPlusRightGiftBagDetailsDto> giftBagDetailsDto = BeanCopy.getInstance().addFieldMap("id", "detailId").convertList(giftBagDetails, PhiPlusRightGiftBagDetailsDto.class);
			giftBagRightDto.setPlusRightGiftBagDetailsList(giftBagDetailsDto);
			
			return giftBagRightDto;
		}else {			 
		
			PhiPlusAllRightDto plusAllRightDto1 = BeanCopy.getInstance().addFieldMap("taskname", "rightName").addFieldMap("taskremark", "remark").addFieldMap("taskswitch", "isValidate").convert(plusRight, PhiPlusAllRightDto.class);
			
			PhiPlusRightDetails plusRightDetail = plusRight.getPlusRightDetail();
			if(null != plusRightDetail){
				Long detailId = plusRightDetail.getId();
				plusAllRightDto1.setDetailId(detailId);
				PhiPlusRightDetailsDto phiPlusRightDetailsDto = new PhiPlusRightDetailsDto();
				/** @Fields scoreOrMutiply : 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分） */ 
				int scoreOrMutiply = plusRightDetail.getScoreOrMutiply();
				plusAllRightDto1.setScoreOrMutiply(scoreOrMutiply);
				if(0 == scoreOrMutiply){
					   /** @Fields scoreValue2 :倍数（翻倍时）*/ 
					plusAllRightDto1.setScorevalue2(String.valueOf(plusRightDetail.getScoreValue2()));
					phiPlusRightDetailsDto = BeanCopy.getInstance().addFieldMap("scoreOrMutiply", "scoreOrMutiply").addFieldMap("scoreValue2", "scorevalue2").convert(plusRightDetail, PhiPlusRightDetailsDto.class);
				}else if(1 == scoreOrMutiply){
					  /** @Fields scoreValue1 :积分值1(额外积分数值)*/ 
					plusAllRightDto1.setScorevalue1(String.valueOf(plusRightDetail.getScoreValue1()));
					phiPlusRightDetailsDto = BeanCopy.getInstance().addFieldMap("scoreOrMutiply", "scoreOrMutiply").addFieldMap("scoreValue1", "scorevalue1").convert(plusRightDetail, PhiPlusRightDetailsDto.class);
				}
				plusAllRightDto1.setPlusRightDetail(phiPlusRightDetailsDto);
			}
	
			return plusAllRightDto1;
		}
	}

	@Override
	public void updatePhiPlusAllRight(Long id, PhiPlusAllRightDto entityDto) {
		PhiPlusRight plusRight = phiPlusRightDao.findPhiPlusRightById(id);
		PhiPlusRightGiftBag plusRightGiftBag = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagById(id);
		String tasktype = entityDto.getTasktype();
		String isValidate = entityDto.getIsValidate();
		String award  = null ;
		if(null != plusRight){
			int scoreOrMutiply = entityDto.getScoreOrMutiply();
			PhiPlusRightDetails plusRightDetail = plusRight.getPlusRightDetail();
			if(null != plusRightDetail){
				plusRightDetail.setScoreOrMutiply(scoreOrMutiply);
				if(0 == scoreOrMutiply){
					award = entityDto.getScorevalue2();
					plusRightDetail.setScoreValue2(Integer.valueOf(entityDto.getScorevalue2()));
					
				}else if (1 == scoreOrMutiply) {
					award = entityDto.getScorevalue1();
					plusRightDetail.setScoreValue1(Integer.valueOf(entityDto.getScorevalue1()));
				}
				phiPlusRightDetailsDao.saveOrUpdatePhiPlusRightDetails(plusRightDetail);
			}
			plusRight.setTaskswitch(isValidate);
			plusRight.setTaskremark(entityDto.getRemark());
			
			//BeanCopy.getInstance().mapIgnoreId(entityDto, plusRight);
			
			plusRight.setPlusRightDetail(plusRightDetail);
			UserInfo userInfo = ThreadLocalClient.get().getOperator();
			plusRight.setOperationperson(userInfo.getUserName());
			Date currDate = new Date(); 
			plusRight.setLastoperationtime(currDate);
			 
			phiPlusRightDao.saveOrUpdatePhiPlusRight(plusRight);
			
		}else {
			 tasktype = plusRightGiftBag.getGiftBagType();
			String packMoney = plusRightGiftBag.getGiftPackageMoney();
			//获取优惠券信息
			List<PhiPlusRightGiftBagDetails> phiPlusRightGiftBagDetails = plusRightGiftBag.getPlusRightGiftBagDetailsList();
			//从所有权益Dto获得优惠券dto信息
//			Set<PhiPlusRightGiftBagDetailsDto> giftBagDetailsDtos = entityDto.getPlusRightGiftBagDetails();
			List<PhiPlusRightGiftBagDetailsDto> giftBagDetailsDtosList = entityDto.getPlusRightGiftBagDetailsList();
			List<PhiPlusRightGiftBagDetails> entityList = BeanCopy.getInstance().addFieldMap("detailId", "id").convertList(giftBagDetailsDtosList, PhiPlusRightGiftBagDetails.class);
			List<PhiPlusRightGiftBagDetails> entityListForSave = new ArrayList<PhiPlusRightGiftBagDetails>();
			if(entityList != null && entityList.size() >0){
//				phiPlusRightGiftBagDetailsDao.batchSave(entityList);
				for (int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).getId() != null){
						phiPlusRightGiftBagDetailsDao.merge(entityList.get(i));
					}else {
						entityListForSave.add(entityList.get(i));
					}
				}
			}
			if(entityListForSave.size() > 0){
				phiPlusRightGiftBagDetailsDao.batchSave(entityListForSave);
			}
		//	phiPlusRightGiftBagDetailsDao.batchSave(entityList);
			
			award =entityDto.getAward();
			if(! packMoney.equals(award)){
				plusRightGiftBag.setGiftPackageMoney(award);
			}
			plusRightGiftBag.setIsValidate(isValidate);
			plusRightGiftBag.setRemark(entityDto.getRemark());
			UserInfo userInfo = ThreadLocalClient.get().getOperator();
			plusRightGiftBag.setOperationperson(userInfo.getUserName());
			Date currDate = new Date(); 
			plusRightGiftBag.setLastoperationtime(currDate);
			
			phiPlusRightGiftBagDao.saveOrUpdatePhiPlusRightGiftBag(plusRightGiftBag);
		}
	}

	@Override
	public void updateIsValidate(Long id, String isValidate) {
		PhiPlusRightGiftBag plusRightGiftBag = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagById(id);
		PhiPlusRight plusRight = phiPlusRightDao.findPhiPlusRightById(id);
		if(null == plusRight){
			if (null == plusRightGiftBag) {
				 throw new ResourceNotFoundException(id);
			}
			if(("on".equals(isValidate))){
				plusRightGiftBag.setIsValidate("off");
			}else if(("off".equals(isValidate))){
				plusRightGiftBag.setIsValidate("on");
			}
			UserInfo userInfo = ThreadLocalClient.get().getOperator();
			plusRightGiftBag.setOperationperson(userInfo.getUserName());
			Date currDate = new Date(); 
			plusRightGiftBag.setLastoperationtime(currDate);
			
			phiPlusRightGiftBagDao.saveOrUpdatePhiPlusRightGiftBag(plusRightGiftBag);
			//礼包实体转换到会员所有权益的dto中
			PhiPlusAllRightDto giftBagRightDto = BeanCopy.getInstance().addFieldMap("giftBagType", "tasktype").addFieldMap("giftPackageMoney", "award").convert(plusRightGiftBag, PhiPlusAllRightDto.class);
		}else{
			
			if(("on".equals(isValidate))){
				plusRight.setTaskswitch("off");
			}else if(("off".equals(isValidate))){
				plusRight.setTaskswitch("on");
			}
			UserInfo userInfo = ThreadLocalClient.get().getOperator();
			plusRight.setOperationperson(userInfo.getUserName());
			Date currDate = new Date(); 
			plusRight.setLastoperationtime(currDate);
			phiPlusRightDao.saveOrUpdatePhiPlusRight(plusRight);
			PhiPlusAllRightDto plusAllRightDto1 = BeanCopy.getInstance().addFieldMap("taskname", "rightName").addFieldMap("taskremark", "remark").addFieldMap("taskswitch", "isValidate").convert(plusRight, PhiPlusAllRightDto.class);
		}

	}
	

	/**
	 *定时查看plus会员权限发放时间，发放每月专享礼包 
	 * */
	@Override
	public void couponsAutoUptoGrant() {
		log.error("-------------发放每月专享礼包 -----------------");
		List<PhiCouponsDetail> mergeList = new ArrayList<PhiCouponsDetail>();
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = sdf.format(new Date());
		/***
		 *获取plus会员开通的特权（根据taskType判断处于已开启的特权),查找每月专享特权
		 *1、消费类(forPayPoints):根据scoreOrMutiply : 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分）
		 *10、首次开通plus会员（firstExclusive）专享礼包
		 *11、每月专享（everyMothExclusive）:开通会员后次月开始发放每月专享礼包
		 */
		String giftBagType  = "everyMothExclusive";
		PhiPlusRightGiftBag plusRightGiftBag = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagByTaskType(giftBagType);
		if(plusRightGiftBag != null){
			List<PhiMember> members = phiMemberDao.findAllPlusPhiMember(currentDate);
//			List<PhiMember> members = phiMemberDao.findPlusPhiMember();
			//PhiMember mmemberMember = phiMemberDao.findPhiMemberById(memberId);
			List<PhiCouponsDetailDto> listDtobatch = new ArrayList<PhiCouponsDetailDto>();
			
			log.error("推送的会员数="+members.size());
			
			if(members.size() > 0){
				//查看plus会员开通后所享受的权益（任务处于开启状态）
				List<PhiPlusRightGiftBagDetails> detailsList = phiPlusRightGiftBagDetailsDao.everyMonthOpenCoupons();
				if(detailsList != null && detailsList.size() > 0 ){
					for (PhiPlusRightGiftBagDetails phiPlusRightGiftBagDetails : detailsList) {
						List<PhiCouponsDetail> phicouponDetailList = phiCouponsDetailDao.getEveryCouponsDetailsBycouponsIdAndQulity(phiPlusRightGiftBagDetails.getCpnsWayId(), Integer.parseInt(phiPlusRightGiftBagDetails.getCpnsQuantity())*members.size());
						List<PhiCouponsDetailDto> phicouponDetailDtoList = BeanCopy.getInstance().addFieldMap("memberId.UId", "coupUid").convertList(phicouponDetailList, PhiCouponsDetailDto.class);
						if(phicouponDetailDtoList != null && phicouponDetailDtoList.size() > 0){
							for (int i = 0,k = 0 ; i < phicouponDetailDtoList.size() ; k++,i += Integer.parseInt(phiPlusRightGiftBagDetails.getCpnsQuantity())){
								if(i < phicouponDetailDtoList.size()){
									for(int j = 0 ; j < Integer.parseInt(phiPlusRightGiftBagDetails.getCpnsQuantity()) ; j ++){
										if(i + j < members.size()){
											phicouponDetailDtoList.get(i + j).setMemberId(members.get(k).getId());
											phicouponDetailDtoList.get(i + j).setCoupUid(Long.valueOf(members.get(k).getUId()));
											phicouponDetailDtoList.get(i + j).setExchangeStatus("1");
										}
									}
								}
							}
							listDtobatch.addAll(phicouponDetailDtoList);
						}
					}
				}
			}
			log.error("绑定的的优惠劵数："+listDtobatch.size());
			long savestart=System.currentTimeMillis();
			log.error("=======batchListStart=======");
			if(listDtobatch.size()>0){
				List<PhiCouponsDetail> batchSaveList = new ArrayList<PhiCouponsDetail>();
				for (int i = 0; i < listDtobatch.size(); i++) {
					PhiCouponsDetail entity = new PhiCouponsDetail();
					entity.setCpnsId(listDtobatch.get(i).getCpnsId());
					entity.setExchangeStatus("1");
					PhiMember member = new PhiMember();
					member.setId(listDtobatch.get(i).getMemberId());
					if(null != listDtobatch.get(i).getCoupUid()){
						member.setUId(Integer.parseInt(listDtobatch.get(i).getCoupUid()+""));
					}else{
						log.error("用户UID为空==》member_id："+listDtobatch.get(i).getMemberId());
					}
					entity.setMemberId(member);
					entity.setCoupWayId(listDtobatch.get(i).getCoupWayId());
					entity.setCoupCode(listDtobatch.get(i).getCoupCode());
					batchSaveList.add(entity);
				}
				phiCouponsDetailDao.batchSaveSql(batchSaveList);
				
				long saveend=System.currentTimeMillis();
				log.error("=======batchListEnd======="+(saveend-savestart)+"ms");
				
				long start=System.currentTimeMillis();
				/*log.error("=======synVMCStart=======");*/
				if(batchSaveList.size()>0){
					log.error("开始推送月礼包优惠劵到商城："+listDtobatch.size());
					this.postHasPhiCouponsDetailToChengShang(batchSaveList);
				}
				long end=System.currentTimeMillis();
				log.error("=======synVMCSEnd======="+(end-start)+"ms");
			}
			
		}
	}
	
	
	/**
	 * 定时查看plus会员权限发放时间，发放每月专享礼包 
	 * 针对couponsAutoUptoGrant 重构 -Edit By Mickey 2018-04-10
	 */
	@Override
	public void couponsAutoUptoGrantByMickey() {
		log.error("=========== 发放每月专享礼包 Start =================");
		/***
		 *获取plus会员开通的特权（根据taskType判断处于已开启的特权),查找每月专享特权
		 *1、消费类(forPayPoints):根据scoreOrMutiply : 积分翻倍或者额外增加积分值（0：翻倍；1：额外积分）
		 *10、首次开通plus会员（firstExclusive）专享礼包
		 *11、每月专享（everyMothExclusive）:开通会员后次月开始发放每月专享礼包
		 */
		//查询是否开启每月专享礼包 
		PhiPlusRightGiftBag plusRightGiftBag = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagByTaskType("everyMothExclusive");
		List<PhiCouponsDetail> mergeCouponsDetailList = new ArrayList<PhiCouponsDetail>();
		if(null != plusRightGiftBag){
			List<PhiMember> plusMemberList = phiMemberDao.findAllPlusPhiMember(null);
			int plusMemberCount = plusMemberList.size();
			if(null != plusMemberList && plusMemberCount > 0){
				log.error("每月专享礼包推送的会员数:"+plusMemberCount);
				//查看plus会员开通后所享受的权益（任务处于开启状态）
				List<PhiPlusRightGiftBagDetails> plusRightGiftBagDetailList = phiPlusRightGiftBagDetailsDao.everyMonthOpenCoupons();
				if(plusRightGiftBagDetailList != null && plusRightGiftBagDetailList.size() > 0 ){
					for (PhiPlusRightGiftBagDetails phiPlusRightGiftBagDetails : plusRightGiftBagDetailList) {
						int cpnsQuantity = StringUtils.isNotEmpty(phiPlusRightGiftBagDetails.getCpnsQuantity())?Integer.parseInt(phiPlusRightGiftBagDetails.getCpnsQuantity()):0;
						List<PhiCouponsDetail> phiCouponDetailList = null;
						if(cpnsQuantity > 0){
							phiCouponDetailList = phiCouponsDetailDao.getEveryCouponsDetailsBycouponsIdAndQulity(phiPlusRightGiftBagDetails.getCpnsWayId(), cpnsQuantity * plusMemberCount);
						}
						if(phiCouponDetailList != null && phiCouponDetailList.size() > 0){
							int couponIndex = 0;//优惠劵下标
							for (int i = 1;i <= cpnsQuantity;i++){
								System.out.println("PLUS会员第"+i+"次绑定优惠劵方案["+phiPlusRightGiftBagDetails.getCpnsWayId()+"] ");
								for(int j = 0; j < plusMemberList.size();j++){
//									System.out.println("循环次数："+i+"   优惠劵下标："+couponIndex+"   会员下标:"+j);
									phiCouponDetailList.get(couponIndex).setMemberId(plusMemberList.get(j));
									phiCouponDetailList.get(couponIndex).setExchangeStatus("1");
									couponIndex++;
								}
							}
							mergeCouponsDetailList.addAll(phiCouponDetailList);
						}
					}
				}
			}
			log.error("绑定的的优惠劵数："+mergeCouponsDetailList.size());
			if(null != mergeCouponsDetailList && mergeCouponsDetailList.size()>0){
				log.error("======= 执行批量绑定优惠劵 =======");
//				phiCouponsDetailDao.batchSaveSql(mergeCouponsDetailList);
				phiCouponsDetailDao.mergeCouponsDetail(mergeCouponsDetailList);
				log.error("开始推送月礼包优惠劵到商城");
				this.postHasPhiCouponsDetailToChengShang(mergeCouponsDetailList);
			}
		}
		log.error("=========== 发放每月专享礼包 End =================");
	}

	/**
	 * 推送已兑换的优惠卷给辰商
	 * @author eden  
	 * @date Jan 31, 2018 3:09:56 PM
	 * @desc 推送已兑换的优惠卷给辰商
	 * @param: @param couponsDetial
	 * @param: @return  
	 * @return: JsonObject      
	 * @throws
	 */
    private void postHasPhiCouponsDetailToChengShang(List<PhiCouponsDetail> couponsDetialList) {   	
    	for(PhiCouponsDetail couponsDetial:couponsDetialList){
    		JsonObject jo = new JsonObject();
    		jo.addProperty("soure", "发放每月优惠劵礼包");
    		if(null != couponsDetial.getMemberId()){
    			jo.addProperty("tel_number", couponsDetial.getMemberId().getTel());
           	    jo.addProperty("uid", couponsDetial.getMemberId().getUId());
    		}
       	 	jo.addProperty("cpns_id", couponsDetial.getCoupWayId());//fangan id
       	 	jo.addProperty("code", couponsDetial.getCoupCode());
       	 	/********绑定已赠送plus会员优惠卷成功*********/
       	 	try{
	       	 	JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"bindcoupon", "bindcoupon",new Gson().toJson(jo));
	       	 	JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
				String isOk = dataJson.getString("status");
			
				CmdInterfaceReceiveMessageDto entityDto=new CmdInterfaceReceiveMessageDto();
				entityDto.setBusiTime(new Date());
				entityDto.setCode("bindcoupon");
				entityDto.setCreateTime(new Date());
				entityDto.setRequestBody(jo.toString());
				entityDto.setResult(isOk.equals("true")?200:0);
				entityDto.setMsg(dataJson.getString("message"));
				entityDto.setResponseBody(new Gson().toJson(sss.toString()));
				cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
			}catch(Exception e){
				e.printStackTrace();
			}
    	}
	}    
	
	@Override
	public void deletePhiAllPlusRight(Long detailId) {
		beforeRemove(detailId);
		PhiPlusRightGiftBagDetails entity = phiPlusRightGiftBagDetailsDao.findPhiPlusRightGiftBagDetailsById(detailId);
		
		phiPlusRightGiftBagDetailsDao.deletePhiPlusRightGiftBagDetails(entity);
		
		
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
	
	public static void main(String[] aaa){
		int cpnsQuantity = 2;
		int plusMember = 20;
		int k = 0;
		for (int i = 1;i <= cpnsQuantity;i++){
			for(int j = 0; j < plusMember;j++){
				System.out.println("循环次数："+i+"   优惠劵下标："+k+"   会员下标:"+j);
				k++;
			}
			System.out.println("============================");
		}
	}
}
