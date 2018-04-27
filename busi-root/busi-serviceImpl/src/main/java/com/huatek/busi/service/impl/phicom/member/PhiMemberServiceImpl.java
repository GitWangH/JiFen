package com.huatek.busi.service.impl.phicom.member;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dao.phicom.member.PhiMemberGradeDao;
import com.huatek.busi.dao.phicom.order.PhiOrderDao;
import com.huatek.busi.dao.phicom.order.PhiOrderinfoDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightGiftBagDetailsDao;
import com.huatek.busi.dao.phicom.score.PhiScoreFlowDao;
import com.huatek.busi.dto.phicom.member.PhiMemberDto;
import com.huatek.busi.dto.phicom.member.PhiMemberGradeDto;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.member.PhiMemberGrade;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBag;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.coupons.PhiCouponsService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.order.PhiOrderinfoService;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.busi.service.phicom.support.InterfaceApiService;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.file.excel.exp.conversion.BaseConversionService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.core.util.TimeStampUtil;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.ExceptionLogService;

/**
 * @author sweety_tian
 */
@Service("phiMemberServiceImpl")
@Transactional
public class PhiMemberServiceImpl implements PhiMemberService,BaseConversionService {

	private static final Logger log = LoggerFactory.getLogger(PhiMemberServiceImpl.class);
	
	@Autowired
	PhiMemberDao phiMemberDao; 
	@Autowired
	PhiMemberGradeDao phiMemberGradeDao;
	@Autowired
	PhiOrderinfoDao phiOrderinfoDao;
	@Autowired
	PhiOrderDao phiOrderDao;
	@Autowired
	PhiScoreFlowDao phiScoreFlowDao;
    @Autowired
    private PhiOrderService phiOrderService;
    @Autowired
    private PhiOrderinfoService phiOrderinfoService;
    
    @Autowired
	PhiScoreFlowService phiScoreFlowService;
	@Autowired
	PhiPlusRightGiftBagDao phiPlusRightGiftBagDao;
	@Autowired
	PhiPlusRightGiftBagDetailsDao phiPlusRightGiftBagDetailsDao;
	@Autowired
	PhiCouponsDetailDao phiCouponsDetailDao;
	@Autowired
	CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	
	@Autowired
    private ExceptionLogService exceptionLogService;//异常日志服务类
	
	@Autowired
	private PhiCouponsService phiCouponsService;//优惠劵服务类
	
	@Autowired
	private InterfaceApiService interfaceApiService;//第三方接口Api服务类
	
	
	@Override
	public void savePhiMemberDto(PhiMemberDto entityDto)  {
		log.debug("save phiMemberDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiMember entity = BeanCopy.getInstance().convert(entityDto, PhiMember.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiMemberDao.persistentPhiMember(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiMemberDto getPhiMemberDtoById(Long id) {
		log.debug("get phiMember by id@" + id);
		PhiMember entity = phiMemberDao.findPhiMemberById(id);
		
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiMemberDto entityDto = BeanCopy.getInstance().convert(entity, PhiMemberDto.class);
		return entityDto;
	}
	
	@Override
	public PhiMemberDto getPlusPhiMemberDtoById(Long id) {
		log.debug("get phiMember by id@" + id);
		PhiMember entity = phiMemberDao.findPlusPhiMemberById(id);
		/*if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}*/
				 
		PhiMemberDto entityDto = BeanCopy.getInstance().convert(entity, PhiMemberDto.class);
		return entityDto;
	}
	
	
	@Override
	public void updatePhiMember(Long id, PhiMemberDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiMember entity = phiMemberDao.findPhiMemberById(id);
		PhiMemberGrade phiMembergrade = phiMemberGradeDao.findPhiMemberGradeById(entity.getPhiMembergrade().getId());
		entityDto.setUId(entity.getUId());
		entityDto.setEnableScore(entity.getEnableScore());
		entityDto.setMemberGrade(phiMembergrade.getMemberGrade());
		entityDto.setDescInfo(entity.getDescInfo());
		
		JsonObject jo=new JsonObject();
		jo.addProperty("uid", entityDto.getUId());
		jo.addProperty("integral", entityDto.getEnableScore().add(entityDto.getDisableScore()));
		jo.addProperty("member_lv_code", phiMembergrade.getMemberGradeCode());
		jo.addProperty("desc", entityDto.getDescInfo());
		jo.addProperty("plus", entity.getIsplusMember());
		BigDecimal allScore  = entity.getAllScore();
		if(null != allScore ){
			updateMemberGradeByAllScore(id);
		}
		BeanCopy.getInstance().addDepthField("phiMembergrade").convert(entity, PhiMemberDto.class);
		//进行持久化保存
		phiMemberDao.persistentPhiMember(entity);
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "member_info_modify", jo.toString());
		JSONObject memberJson = null;
		try {
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
			/**************开通plus会员信息同步给辰商成功****************/
			try{
				CmdInterfaceReceiveMessageDto cmdEntityDto=new CmdInterfaceReceiveMessageDto();
				cmdEntityDto.setBusiTime(new Date());
				cmdEntityDto.setCode("memberinfomodify");
				cmdEntityDto.setCreateTime(new Date());
				cmdEntityDto.setRequestBody(jo.toString());
				cmdEntityDto.setResult(isOk.equals("true")?200:0);
				cmdEntityDto.setMsg("修改会员信息|"+dataJson.getString("message"));
				cmdEntityDto.setResponseBody(new Gson().toJson(sss.toString()));
				cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(cmdEntityDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void deletePhiMember(Long id) {
		log.debug("delete phiMember by id@" + id);
		beforeRemove(id);
		PhiMember entity = phiMemberDao.findPhiMemberById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiMemberDao.deletePhiMember(entity);
	}
	
	@Override
	public DataPage<PhiMemberDto> getAllPhiMemberPage(QueryPage queryPage) {
		DataPage<PhiMember> dataPage = phiMemberDao.getAllPhiMember(queryPage);
		
		DataPage<PhiMemberDto> datPageDto = BeanCopy.getInstance().addFieldMap("phiMembergrade.id", "memberGrade").convertPage(dataPage, PhiMemberDto.class);
		
		for(int i=0;i<datPageDto.getContent().size();i++){
			PhiMember phiMember =  dataPage.getContent().get(i);
			Long memberId = phiMember.getId();
//			Object obj = phiScoreFlowDao.getPhiScoreFlowAllScore(memberId);
//			Map<String, BigDecimal> map = (Map<String, BigDecimal>) obj;
//			BigDecimal totalScore = map.get("scoreTotal");
//			if(totalScore == null){
//				phiMember.setAllScore(new BigDecimal("0"));
//			}else{
//				phiMember.setAllScore(totalScore);
//			}
			/*累计积分*/
			BigDecimal allScore = phiMember.getAllScore();
			
			PhiMemberGrade memberGradeList = phiMemberGradeDao.getMemberGrade(allScore);
			
			dataPage.getContent().get(i).setPhiMembergrade(memberGradeList);
			PhiMemberGradeDto gradeDto = BeanCopy.getInstance().convert(dataPage.getContent().get(i).getPhiMembergrade(), PhiMemberGradeDto.class);
			datPageDto.getContent().get(i).setPhiMembergrade(gradeDto);
			if(null!=gradeDto){
				datPageDto.getContent().get(i).setMemberGrade(gradeDto.getMemberGrade());
			}
			
			int ordercounts;
			List<PhiOrder>  orderList =  phiOrderDao.findPhiOrderByMemberId(memberId);
			if(null == orderList ||orderList.size() < 0){
				ordercounts = 0;
			}
			ordercounts = phiOrderService.getPhiOrderByMemberId(memberId);
			
			phiMember.setOrderCount(ordercounts);
			
			int plusYears = 0;
			//int  validateYear = 0;
			String plusOpentype =  phiMember.getPlusOpenType();
			Date currentDate = new Date();
			String pluscode = phiMember.getPlusCode();
			String isplus = phiMember.getIsplusMember();
			Date plusvaliDate = phiMember.getValidTime(); 
			Date plusOpenDate = phiMember.getPlusOpenDate();
			Date plusrenewDate = phiMember.getPlusRenewDate();
			//首次开通
			if("firstOpen".equals(plusOpentype)){
				int calYears = yearsBetween(plusOpenDate,currentDate);
				int calDays = daysBetween(plusOpenDate,currentDate);
				int calHours = hoursBetween(plusOpenDate,currentDate);
				//首次开通plus1年内
				if(calYears < 1){
					//问题数据
					if(calDays < 0){
						phiMember.setPlusYears(plusYears);
					}else if(0 < calHours&& calHours < 24) {
						phiMember.setPlusYears(1);
					}else {
						//plus会员开通多少天
						phiMember.setPlusYears(calDays);
						plusYears =  calDays;
					}
				}else {
					phiMember.setPlusYears(calYears);
				}
			}else if("renewOpen".equals(plusOpentype)){
				//plus首次开通时间：1年
				int validateYear = yearsBetween(plusOpenDate,plusvaliDate);
				int calrenewYears = yearsBetween(plusrenewDate,currentDate);
				plusYears = validateYear+calrenewYears;
				phiMember.setPlusYears(plusYears);
			}else {
				phiMember.setPlusYears(plusYears);
			}	
		}
		
		return datPageDto;
	}
	
	
	
	
	@Override
	public List<PhiMemberDto> getAllPhiMemberDto() {
		
		List<PhiMember> entityList = phiMemberDao.findAllPhiMember();
		List<PhiMemberDto> dtos = BeanCopy.getInstance().convertList(entityList,PhiMemberDto.class);
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
	* @param    phiMemberDto
	* @param    phiMember
	* @return  void    
	* @
	*/
	private void beforeSave(PhiMemberDto entityDto, PhiMember entity) {
	}

	/**
	 * @Description: 拉黑会员
	 * @param id
	 * @param status
	 */
	@Override
	public void updateBackList(Long id, String status,PhiMemberDto entityDto) {
		//PhiMember phiMember = phiMemberDao.findPhiMemberById(id);
		PhiMember entity = phiMemberDao.findPhiMemberById(id);
		if("1".equals(status)){
			entity.setBlacklist(status);
		}else if ("0".equals(status)){
			entity.setBlacklist(status);
		}
		PhiMemberGrade phiMembergrade = phiMemberGradeDao.findPhiMemberGradeById(entity.getPhiMembergrade().getId());
		entityDto.setUId(entity.getUId());
		entityDto.setEnableScore(entity.getEnableScore());
		entityDto.setMemberGrade(phiMembergrade.getMemberGrade());
		entityDto.setDescInfo(entity.getDescInfo());
		
		JsonObject jo=new JsonObject();
		jo.addProperty("uid", entityDto.getUId());
		jo.addProperty("integral", entityDto.getEnableScore().add(entityDto.getDisableScore()));
		jo.addProperty("member_lv_code", phiMembergrade.getMemberGradeCode());
		jo.addProperty("desc", entityDto.getDescInfo());
		jo.addProperty("plus", entity.getIsplusMember());
		BigDecimal allScore  = entity.getAllScore();
		if(null != allScore ){
			updateMemberGradeByAllScore(id);
		}
		BeanCopy.getInstance().addDepthField("phiMembergrade").convert(entity, PhiMemberDto.class);
		//进行持久化保存
		phiMemberDao.persistentPhiMember(entity);
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(
				PropertyUtil.getConfigValue("vmsShop_header")
						+ "memberinfomodify", "member_info_modify", jo.toString());
//		JsonObject returnData = sss.getAsJsonObject();
//		JsonObject jsonObject=new JsonObject();
//		if(returnData.isJsonObject()){
//			jsonObject=returnData.getAsJsonObject();
//		}
		JSONObject memberJson = null;
		try {
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
			/**************开通plus会员信息同步给辰商成功****************/
			try{
				CmdInterfaceReceiveMessageDto cmdEntityDto=new CmdInterfaceReceiveMessageDto();
				cmdEntityDto.setBusiTime(new Date());
				cmdEntityDto.setCode("memberinfomodify");
				cmdEntityDto.setCreateTime(new Date());
				cmdEntityDto.setRequestBody(jo.toString());
				cmdEntityDto.setResult(isOk.equals("true")?200:0);
				cmdEntityDto.setMsg("拉黑会员|"+dataJson.getString("message"));
				cmdEntityDto.setResponseBody(new Gson().toJson(sss.toString()));
				cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(cmdEntityDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//phiMemberDao.persistentPhiMember(phiMember);
	}


	
	@Override
	public void updateMemberGradeByAllScore(Long memberId) {
		//查找所有会员
		PhiMember phiMember = phiMemberDao.findPhiMemberById(memberId) ;
		BigDecimal allScore = phiMember.getAllScore();
		PhiMemberGrade memberGradeList = phiMemberGradeDao.getMemberGrade(allScore);
		
		phiMember.setPhiMembergrade(memberGradeList);
		
		BeanCopy.getInstance().addDepthField("phiMembergrade").convert(phiMember, PhiMemberDto.class);
	
		phiMemberDao.saveOrUpdatePhiMember(phiMember);
	}


	@Override
	public void updateOrderCount(Long memberId) {
		int ordercounts = 0; 
		List<PhiOrder>  orderList =  phiOrderDao.findPhiOrderByMemberId(memberId);
		if(null == orderList ||orderList.size() < 0){
			ordercounts = 0;
		}
		ordercounts = phiOrderService.getPhiOrderByMemberId(memberId);

		PhiMember phiMember = phiMemberDao.findPhiMemberById(memberId) ;
		phiMember.setOrderCount(ordercounts);
		
		BeanCopy.getInstance().convert(phiMember, PhiMemberDto.class);
		
		phiMemberDao.saveOrUpdatePhiMember(phiMember);
		
	}



	@Override
	public PhiMember findPhiMemberByUid(int UId) {
		PhiMember member=phiMemberDao.findPhiMemberByUid(UId);
		if(member == null ){
			member = new PhiMember();
//			throw new BusinessRuntimeException("积分商城没有此用户，请同步会员信息！", "-1");
		}
		return synchroMember(String.valueOf(UId),member);
//		return member;
	}

	/**
	 * 新增或更新会员信息
	 * @author eden  
	 * @date Jan 31, 2018 7:50:44 PM
	 * @desc TODO(用一句话描述本方法的作用)  
	 * @param:   
	 * @return: void      
	 * @throws
	 */
	private PhiMember  synchroMember(String  uid,PhiMember phiMember){
		if(phiMember == null){
			phiMember = new PhiMember();
		}
		//phiMember.setVersion(0);
		JsonObject jo= new JsonObject();
		jo.addProperty("uid", uid);
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfo", "member_info", jo.toString());
		JSONObject memberJson = null;
		try {
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			memberJson = dataJson.getJSONObject("data");
			memberJson.getString("uid"); 
//			infoObj = dataJson.getJSONArray("data");
			
			String isOk = dataJson.getString("status");
			/**************开通plus会员信息同步给辰商成功****************/
			try{
				CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
				entityDto.setBusiTime(new Date());
				entityDto.setCode("memberinfo");
				entityDto.setCreateTime(new Date());
				entityDto.setRequestBody(jo.toString());
				entityDto.setResult(isOk.equals("true")?200:0);
				entityDto.setMsg(dataJson.getString("message"));
				entityDto.setResponseBody(new Gson().toJson(sss.toString()));
				cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		} catch (Exception e) {
			log.error("参数解析失败！", sss.toString(), "memberinfo");
			memberJson=null;
		}
		// 新增 Or 修改
		if (null != memberJson&&!"null".equals(memberJson)&&!"".equals(memberJson)) {
			log.info("++++++++++++++++++++++++++++++++++++++++++");
			log.info(memberJson.toString());
			String UId = memberJson.getString("uid"); 
			phiMember.setUId(Integer.valueOf(UId));
			String birthday=memberJson.getString("birthday");
			phiMember.setUserName(memberJson.getString("nickname"));
			phiMember.setRealName(memberJson.get("name")!=null?memberJson.getString("name"):"");
			String sex = memberJson.getString("sex");
			//phiMember.setPortrait(sex);
			phiMember.setSex(sex);
			
				switch(sex){
					case "male":
						phiMember.setSex("male");
						break;
					case "female":
						phiMember.setSex("female");
						break;
					default:
						phiMember.setSex("unknown");
				}
			
			String portrait = memberJson.getString("avatar");
			if(StringUtils.isNotEmpty(portrait) &&  ("null")!=portrait){
				phiMember.setPortrait(portrait);
			}else {
				phiMember.setPortrait("https://mall.phicomm.com/Uploads/default/default.jpg");
			}
			phiMember.setBirthday(TimeStampUtil.StrToDate(birthday));
			phiMember.setTel(memberJson.getString("mobile"));
//			phiMember.setPayCode(memberJson.getString("pay_code"));
//			phiMember.setBlacklist(memberJson.getString("blacklist"));
//			phiMember.setState(memberJson.getString("state"));
			try {
				phiMember.setCreateTime(new Date(Long.valueOf(memberJson.getString("regtime"))));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//有则已积分商城的为准，没有则更新商城的会员信息
			if(StringUtils.isEmpty(phiMember.getMemberGradeCode())){
				phiMember.setMemberGradeCode(memberJson.getString("member_lv_code"));
			}
//			phiMember.setPlusCode(memberJson.getString("plus_code"));
			//以下转换是为了处理咱们系统和斐讯会员编码不一致的问题
			if(StringUtils.isNotEmpty(phiMember.getMemberGradeCode())){
//				PhiMemberGrade phiMembergrade = new PhiMemberGrade();
				Long gradeIdLong = null;
				switch(phiMember.getMemberGradeCode()){
					case "pt":
						gradeIdLong = 13L;
						break;
					case "by":
						gradeIdLong = 14L;
						break;
					case "bj":
						gradeIdLong = 15L;
						break;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
					case "zs":
						gradeIdLong = 17L;
						break;
					case "zz":
						gradeIdLong = 20L;
						break;
					default:
						gradeIdLong = 13L;
						break;
				}
				PhiMemberGrade phiMembergrade = phiMemberGradeDao.findPhiMemberGradeById(gradeIdLong);
				if(phiMembergrade == null){
					phiMembergrade = new PhiMemberGrade();
					phiMembergrade.setId(gradeIdLong);
					phiMemberGradeDao.saveOrUpdatePhiMemberGrade(phiMembergrade);
				}
				phiMember.setPhiMembergrade(phiMembergrade);
			}
			phiMemberDao.saveOrUpdatePhiMember(phiMember);
		}
		return phiMember;
	}



	@Override
	public void updatePhiMemberInfo(int UId, PhiMemberDto entityDto) {
		PhiMember phiMember = phiMemberDao.findPhiMemberByUid(UId);
		BigDecimal allScore  = phiMember.getAllScore();
		PhiMemberGrade phiMembergrade = phiMemberGradeDao.getMemberGrade(allScore);
		entityDto.setUId(UId);
		entityDto.setEnableScore(phiMember.getEnableScore());
		entityDto.setMemberGrade(phiMembergrade.getMemberGrade());
		entityDto.setDescInfo(phiMember.getDescInfo());
		entityDto.setIsplusMember(phiMember.getIsplusMember());
		//entityDto.setMemberGrade(phiMembergrade.getMemberGrade());
		//phiMembergrade.set
		JsonObject jo=new JsonObject();
		jo.addProperty("uid", entityDto.getUId());
		jo.addProperty("integral", entityDto.getEnableScore().add(entityDto.getDisableScore()));
		jo.addProperty("member_lv_code", phiMembergrade.getMemberGradeCode());
		jo.addProperty("desc", entityDto.getDescInfo());
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "modify_info_modify", jo.toString());
		JsonObject returnData = sss.getAsJsonObject();
		JsonObject jsonObject=new JsonObject();
		if(returnData.isJsonObject()){
			jsonObject=returnData.getAsJsonObject();
		}
		BeanCopy.getInstance().addDepthField("phiMembergrade").convert(phiMember, PhiMemberDto.class);
		//进行持久化保存
		phiMemberDao.persistentPhiMember(phiMember);
		
	}

	


	@Override
	public PhiMemberDto getPhiMemberDtoByUId(int UId) {
		
		PhiMemberDto entityDto = new PhiMemberDto();
		entityDto.setUId(UId);
		JsonObject jo=new JsonObject();
		jo.addProperty("uid", entityDto.getUId());
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfo", "member_info", jo.toString());
		JsonObject returnData = sss.getAsJsonObject();
		JsonObject jsonObject=new JsonObject();
		System.out.println(returnData);
		if(returnData.isJsonObject()){
			jsonObject=returnData.getAsJsonObject();
			JsonObject memberObject = new JsonParser().parse(jsonObject.get("data").toString()).getAsJsonObject();
			entityDto = new PhiMemberDto();
			if( memberObject.get("avatar").isJsonNull()){
				entityDto.setPortrait(null);
			}else{
				entityDto.setPortrait(memberObject.get("avatar").getAsString());
			}
	    	if(memberObject.get("name").isJsonNull()){
	    		entityDto.setRealName(null);
	    	}else{
	    		entityDto.setRealName(memberObject.get("name").getAsString());	
	    	}
	    	if(memberObject.get("nickname").isJsonNull()){
	    		entityDto.setUserName(null);
	    	}else{
	    		  entityDto.setUserName(memberObject.get("nickname").getAsString());  
	    	}
	    	if(memberObject.get("mobile").isJsonNull()){
	    		  entityDto.setTel(null);
	    	  }else{
	    		  entityDto.setTel(memberObject.get("mobile").getAsString());  
	    	  }
	    	 if(memberObject.get("sex").isJsonNull()){
		    		entityDto.setSex(null);
	    	 }else{
		    		entityDto.setSex(memberObject.get("sex").getAsString());
	    	 }
	    	 if(memberObject.get("regtime").isJsonNull()){
	    		 entityDto.setCreateTime(null);
	    	 }else{
	    		 entityDto.setCreateTime(memberObject.get("regtime").getAsString());
	    	 }
	    	 if(memberObject.get("integral").isJsonNull()){
	    		 entityDto.setEnableScore(new BigDecimal("0"));
	    	 }else{
	    		 entityDto.setEnableScore(memberObject.get("integral").getAsBigDecimal());
	    	 }
	    	  entityDto.setUId(memberObject.get("uid").getAsInt());
	    	 // PhiMember phiMember = BeanCopy.getInstance().addDepthField("phiMembergrade").convert(entityDto, PhiMember.class);
	    	  BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss").addDepthField("phiMembergrade").map(entityDto, PhiMember.class);
	    	// phiMemberDao.persistentPhiMember(phiMember);
		}
		return entityDto;
	}

	@Override
	public PhiMemberDto getPhiMemberVOByUId(int UId) {
		PhiMember phiMember = phiMemberDao.findPhiMemberByUid(UId);
		PhiMemberDto phiMemberDto=BeanCopy.getInstance().addDepthField("phiMembergrade").convert(phiMember, PhiMemberDto.class);
		return phiMemberDto;
	}
	
	
	@Override
	public PhiMember findPhiMemberById(Long id) {
		// TODO Auto-generated method stub
		return phiMemberDao.findPhiMemberById(id);
	}
	 public void saveOrUpdatePhiMember(PhiMember entity){
		BigDecimal allScore = entity.getAllScore();
		if(allScore != null){
			PhiMemberGrade memberGradeList = phiMemberGradeDao.getMemberGrade(allScore);
			if(null != memberGradeList){
				entity.setPhiMembergrade(memberGradeList);
				entity.setMemberGradeCode(memberGradeList.getMemberGradeCode());
			}
		}
		 phiMemberDao.saveOrUpdatePhiMember(entity);
	 }
	
	/**
	 *plus会员到期（修改会员信息）  
	 */
	public void memberAutoOpen() {
		System.out.print("============开始执行==============");
		//当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm");  
		String currentDate = sdf.format(new Date());
		List<PhiMember> members = phiMemberDao.findPlusPhiMember();
		for (PhiMember phiMember : members) {
			String plusvalidate = sdf.format(phiMember.getValidTime());
			//Date sendDate = phiMember.getSendTime();
			String sendTime  = sdf.format(phiMember.getSendTime());
			
			int x = compare_date(plusvalidate,currentDate);
			int y = compare_date(currentDate, sendTime);
			//plus会员到期失效，plus会员标识更改
			if(x == -1){
				//isPlusmember:是否是plus会员：0：否；1：是
				phiMember.setIsplusMember("0");
				//非plus会员，开通plus类型置为空
				phiMember.setPlusOpenType("");
				JsonObject jo= new JsonObject();
				jo.addProperty("uid", phiMember.getUId());
				jo.addProperty("integral", phiMember.getEnableScore());//可用积分
				jo.addProperty("member_lv_code", phiMember.getMemberGradeCode());
				jo.addProperty("desc", phiMember.getDescInfo());
				jo.addProperty("plus", phiMember.getIsplusMember());
				JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "member_info_modify", jo.toString());
				JSONObject dataJson = null;
				try {
					dataJson = JSONObject.fromObject(sss.toString()); 
					String isOk = dataJson.getString("status");
					/**************plus会员信息到期后同步给辰商成功****************/
					try{
						CmdInterfaceReceiveMessageDto entityDto=new CmdInterfaceReceiveMessageDto();
						entityDto.setBusiTime(new Date());
						entityDto.setCode("plusmemberinfoexpired");
						entityDto.setCreateTime(new Date());
						entityDto.setRequestBody(jo.toString());
						entityDto.setResult(isOk.equals("true")?200:0);
						entityDto.setMsg(dataJson.getString("message"));
						entityDto.setResponseBody(new Gson().toJson(sss.toString()));
						cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
					}catch(Exception e){
						e.printStackTrace();
					}
				} catch (Exception e) {
					log.error("plus会员信息到期推送参数解析失败！", sss.toString(), "plusmemberinfoexpired");
				}
			}else {
				if(y == 0){
					Calendar c = Calendar.getInstance();
						c.add(Calendar.MONTH,1);//计算1个月后的时间  每月1日凌晨3点开始发放，从开通成功后的次月开始
						c.set(Calendar.DAY_OF_MONTH,1);
						c.set(Calendar.HOUR_OF_DAY,3);
						c.set(Calendar.MINUTE,0);
						c.set(Calendar.SECOND,0);	
					phiMember.setSendTime(c.getTime());
				}
			}
		}
		System.out.print("============结束执行==============");
	}
	
	
    public static int compare_date(String DATE1, String DATE2) {              
      	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   		try {
   		            Date dt1 = df.parse(DATE1);
   		            Date dt2 = df.parse(DATE2);
   		            if (dt1.getTime() > dt2.getTime()) {
//   		                System.out.println("dt1 在dt2前");
   		                return 1;
   		            } else if (dt1.getTime() < dt2.getTime()) {
//   		                System.out.println("dt1在dt2后");
   		                return -1;
   		            } else {
   		                return 0;
   		            }
          } catch (Exception exception) {
              exception.printStackTrace();
          }
          return 0;
      }

    /**
     * plus会员开通后更新会员信息
     * plus会员有效期：1年，比如今年1.23开通，就到明年1.22 23:59:59
     */

	@Override
	public void UpdatePhimember(Long memberId) {
		PhiMember phiMember = phiMemberDao.findPhiMemberById(memberId) ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		Date currDate = new Date();
		String currTime = sdf.format(currDate);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(FORMATTER.parse(currTime));
			c.add(Calendar.MONTH,1);//计算1个月后的时间
		} catch (ParseException e) {
			e.printStackTrace();
		}
		phiMember.setPlusOpenDate(currDate);
		phiMember.setSendTime(c.getTime());
		phiMember.setIsplusMember("1");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(FORMATTER.parse(currTime));
			cal.add(Calendar.YEAR, 1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//plus会员有效期
		phiMember.setValidTime(cal.getTime());
		phiMemberDao.saveOrUpdatePhiMember(phiMember);
	}


	@Override
	public void UpdateAllScore(Long memberId) {
		Object obj = phiScoreFlowDao.getPhiScoreFlowAllScore(memberId);
		PhiMember phiMember = phiMemberDao.findPhiMemberById(memberId) ;
		Map<String, BigDecimal> map = (Map<String, BigDecimal>) obj;
		BigDecimal totalScore = map.get("scoreTotal");
		if(totalScore == null){
			phiMember.setAllScore(new BigDecimal("0"));
		}else{
			phiMember.setAllScore(totalScore);
		}
	}


	@Override
	public void OpenPhimember(Long memberId,String plusCode) {
		// 1.plus会员开通后更新会员信息
		PhiMember phiMember = phiMemberDao.findPhiMemberById(memberId) ;
		if (null == phiMember) {
			throw new ResourceNotFoundException(memberId);
		}
		//plusCode已存在 plus会员续费 isplusMember = "0"
		if("0".equals(phiMember.getIsplusMember())&&phiMember.getPlusCode() != null && plusCode.equals(phiMember.getPlusCode()) ){
			Calendar c = Calendar.getInstance();
				c.add(Calendar.MONTH,1);//计算1个月后的时间  每月1日凌晨3点开始发放，从开通成功后的次月开始
				c.set(Calendar.DAY_OF_MONTH,1);
				c.set(Calendar.HOUR_OF_DAY,3);
				c.set(Calendar.MINUTE,0);
				c.set(Calendar.SECOND,0);	
			
			phiMember.setSendTime(c.getTime());
			//plus开通类型：续费
			phiMember.setPlusOpenType("renewOpen");
			phiMember.setPlusRenewDate(new Date());
			phiMember.setIsplusMember("1");
			Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, 1);
			
			//plus会员有效期
			phiMember.setValidTime(cal.getTime());
			phiMemberDao.saveOrUpdatePhiMember(phiMember);
			//普通会员（isplusMember = "0"），plusCode为null,首次开通plus会员
		}else if("0".equals(phiMember.getIsplusMember())&& (phiMember.getPlusCode() == null||"".equals(phiMember.getPlusCode()) )){
			Calendar c = Calendar.getInstance();
			c.add(Calendar.MONTH,1);//计算1个月后的时间  每月1日凌晨3点开始发放，从开通成功后的次月开始
			c.set(Calendar.DAY_OF_MONTH,1);
			c.set(Calendar.HOUR_OF_DAY,3);
			c.set(Calendar.MINUTE,0);
			c.set(Calendar.SECOND,0);		
			phiMember.setSendTime(c.getTime());
			phiMember.setIsplusMember("1");
			phiMember.setPlusCode(plusCode);
			Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.YEAR, 1);
		     //plus开通类型：首次开通
		    phiMember.setPlusOpenType("firstOpen");
		    //plus首次开通时间
		    phiMember.setPlusOpenDate(new Date());
		//plus会员有效期
		    phiMember.setValidTime(cal.getTime());
			phiMemberDao.saveOrUpdatePhiMember(phiMember);
			
			/*********1.将开通plus会员信息同步给辰商 Start**********/
//			String data="{\"uid\":1231515,\"integral\":102,\"member_lv_code\":\"zc\",\"desc\":\"说明33\",\"plus\":\"0\"}";//必须为转义
			JsonObject jo= new JsonObject();
			jo.addProperty("uid", phiMember.getUId());
			jo.addProperty("integral", phiMember.getEnableScore().add(phiMember.getDisableScore()));//可用积分
			jo.addProperty("member_lv_code", phiMember.getMemberGradeCode());
			jo.addProperty("desc", phiMember.getDescInfo());
			jo.addProperty("plus", phiMember.getIsplusMember());
			JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "member_info_modify", jo.toString());
			JSONObject memberJson = null;
			try {
				JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
				String isOk = dataJson.getString("status");
				/**************开通plus会员信息同步给辰商成功****************/
				try{
					CmdInterfaceReceiveMessageDto entityDto=new CmdInterfaceReceiveMessageDto();
					entityDto.setBusiTime(new Date());
					entityDto.setCode("memberinfomodify");
					entityDto.setCreateTime(new Date());
					entityDto.setRequestBody(jo.toString());
					entityDto.setResult(isOk.equals("true")?200:0);
					entityDto.setMsg(dataJson.getString("message"));
					entityDto.setResponseBody(new Gson().toJson(sss.toString()));
					cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				//2.plus会员开通后发放首次发礼包特权
				List<PhiCouponsDetail> mergeList = new ArrayList<PhiCouponsDetail>();
				PhiMember mmemberMember = phiMemberDao.findPhiMemberById(memberId);
				//plus权限Code,首次开通才发放礼包
				String giftBagType  = "firstExclusive";
				PhiPlusRightGiftBag plusRightGiftBag = phiPlusRightGiftBagDao.findPhiPlusRightGiftBagByTaskType(giftBagType);
				if(plusRightGiftBag != null){
					List<PhiPlusRightGiftBagDetails> detailsList = phiPlusRightGiftBagDetailsDao.firstOpenCoupons();
					if(detailsList != null && detailsList.size() > 0 ){
						for (PhiPlusRightGiftBagDetails phiPlusRightGiftBagDetails : detailsList) {
							List<PhiCouponsDetail> phicouponDetailList = phiCouponsDetailDao.getCouponsDetailsBycouponsIdAndQulity(phiPlusRightGiftBagDetails.getCpnsWayId(), Integer.parseInt(phiPlusRightGiftBagDetails.getCpnsQuantity()));
							if(phicouponDetailList.size() > 0){
								for (int i = 0; i < phicouponDetailList.size(); i++) {
									PhiCouponsDetail phicouponDetail=phicouponDetailList.get(i);
									phicouponDetail.setMemberId(mmemberMember);
									phicouponDetail.setExchangeStatus("1");
									phiCouponsDetailDao.saveOrUpdatePhiCouponsDetail(phicouponDetail);
									((AbstractDao)phiCouponsDetailDao).flush();
								}
								mergeList.addAll(phicouponDetailList);
							}
						}
					}
					this.postHasPhiCouponsDetailToChengShang(mergeList);
				}
				
			} catch (Exception e) {
				log.error("开通plus会员推送参数解析失败！", sss.toString(), "memberinfomodify");
			}
		}
	}

	/**
	 * Plus会员开通(重构方法)
	 * @author mickey_meng
	 * @date 2018-03-13 20:50:25
	 */
	@Override
	public void openPhimember(Long memberId,String plusCode) {
		PhiMember phiMember = phiMemberDao.findPhiMemberById(memberId);
		if (phiMember != null) {
			/*********************** 1、开通plus会员 *********************************/
			//plusCode已存在 plus会员续费 isplusMember = "0"
			if("0".equals(phiMember.getIsplusMember())&& //非plus
					StringUtils.isNotEmpty(phiMember.getPlusCode()) && 
								plusCode.equals(phiMember.getPlusCode())){
				phiMember.setPlusOpenType("renewOpen");
				phiMember.setPlusRenewDate(new Date());
				phiMember = this.setPhiMemberInfo(phiMember);//设置会员信息
		        phiMemberDao.saveOrUpdatePhiMember(phiMember);
				//首次开通plus会员-普通会员（isplusMember = "0"），plusCode为null,
			}else if("0".equals(phiMember.getIsplusMember())&& //非plus
					StringUtils.isEmpty(phiMember.getPlusCode())){
				phiMember.setPlusOpenType("firstOpen");
				//plus首次开通时间
			    phiMember.setPlusOpenDate(new Date());
			    phiMember.setPlusCode(plusCode);
			    phiMember = this.setPhiMemberInfo(phiMember);//设置会员信息
		        phiMemberDao.saveOrUpdatePhiMember(phiMember);
		        try {
		        	/********************** 2、 绑定优惠劵 *********************************/
		        	List<PhiCouponsDetail> bindingCouponsDetailList = phiCouponsService.bindingCouponsDetailOfPlusPhiMember(phiMember);//PLUS会员绑定优惠劵
		        	/********************** 3、 推送已绑定的优惠劵 & 推送会员信息给辰商 *********************************/
			        interfaceApiService.pushBindingPhiCouponsAndPlusPhiMemberToChengShang(memberId,bindingCouponsDetailList);
		        }catch (Exception e) {
					log.error(phiMember.getTel() + "开通plus会员绑定优惠劵失败(PhiMemberServiceImpl_862)失败!");
					log.error(e.toString());
		        }
			}
		}else{
			throw new ResourceNotFoundException(memberId);
		}
	}
	
	/**
	 * 设置会员信息
	 * @param phiMember
	 * @return
	 */
	private PhiMember setPhiMemberInfo(PhiMember phiMember){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH,1);//计算1个月后的时间  每月1日凌晨3点开始发放，从开通成功后的次月开始
		c.set(Calendar.DAY_OF_MONTH,1);
		c.set(Calendar.HOUR_OF_DAY,3);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);		
		phiMember.setSendTime(c.getTime());
		phiMember.setIsplusMember("1");
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.YEAR, 1);
	    //plus会员有效期
	    phiMember.setValidTime(cal.getTime());
		return phiMember;
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
       	 	jo.addProperty("uid", couponsDetial.getMemberId().getUId());
       	 	jo.addProperty("cpns_id", couponsDetial.getCoupWayId());//fangan id
       	 	jo.addProperty("code", couponsDetial.getCoupCode());
       	 	JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"bindcoupon", "bindcoupon",new Gson().toJson(jo));
       	 	JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
       	 	/********绑定已赠送plus会员优惠卷成功*********/
       	 	try{
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
	public List<PhiMemberDto> getAllPlusPhiMemberDto() {
		List<PhiMember> plusList = phiMemberDao.findPlusPhiMember();
		List<PhiMemberDto> plusDtos = BeanCopy.getInstance().convertList(plusList,PhiMemberDto.class);
		return plusDtos;
	}

	
	/**
	 * @author eden  
	 * @date Feb 1, 2018 11:58:07 PM
	 * @desc 推送积分商城会员信息给辰商商城
	 * @param: @param phiMember  
	 * @return: void      
	 * @throws
	 */
	@Override
	public void pullMemberInfoToChenShang(PhiMember phiMember,String modelName){
		/*********1.将开通plus会员信息同步给辰商 Start**********/
//		String data="{\"uid\":1231515,\"integral\":102,\"member_lv_code\":\"zc\",\"desc\":\"说明33\",\"plus\":\"0\"}";//必须为转义
		JsonObject jo= new JsonObject();
		jo.addProperty("uid", phiMember.getUId());
		jo.addProperty("integral", defaultBigDecimal(phiMember.getEnableScore()).add(defaultBigDecimal(phiMember.getDisableScore())));//可用积分
		jo.addProperty("member_lv_code", phiMember.getMemberGradeCode());
		jo.addProperty("desc", phiMember.getDescInfo());
		jo.addProperty("plus", phiMember.getIsplusMember());
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"memberinfomodify", "member_info_modify", jo.toString());
		log.error("辰商接口地址：",PropertyUtil.getConfigValue("vmsShop_header"), "memberinfomodify");
		log.error("辰商接口地址："+PropertyUtil.getConfigValue("vmsShop_header"));
		JSONObject memberJson = null;
		try {
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
			/**************开通plus会员信息同步给辰商成功****************/
			try{
				CmdInterfaceReceiveMessageDto entityDto=new CmdInterfaceReceiveMessageDto();
				entityDto.setBusiTime(new Date());
				entityDto.setCode("memberinfomodify");
				entityDto.setCreateTime(new Date());
				entityDto.setRequestBody(jo.toString());
				entityDto.setResult(isOk.equals("true")?200:0);
				entityDto.setMsg(modelName+dataJson.getString("message"));
				entityDto.setResponseBody(new Gson().toJson(sss.toString()));
				cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static BigDecimal defaultBigDecimal(BigDecimal bigDecimal){
		return (bigDecimal == null) ?new BigDecimal("0"): bigDecimal;
	}

	@Override
	public PhiMember GetMemberPhiMemberByUid(Integer UId) {
		PhiMember member=phiMemberDao.findPhiMemberByUid(UId);
		if(member == null ){
			member = synchroMember(String.valueOf(UId),member);
//			throw new BusinessRuntimeException("积分商城没有此用户，请同步会员信息！", "-1");
		}
//		return synchroMember(String.valueOf(UId),member);
		return member;
	}

	@Override
	public boolean calcMemberAllScore() {
		// TODO Auto-generated method stub
		try{
	 List<PhiMember> memberList = phiMemberDao.findAllPhiMember();
	 Iterator<PhiMember> memberIt=memberList.iterator();
	 while(memberIt.hasNext()){
		 PhiMember phiMember = memberIt.next();
			Object obj = phiScoreFlowDao.getPhiScoreFlowAllScore(phiMember.getId());
			Map<String, BigDecimal> map = (Map<String, BigDecimal>) obj;
			BigDecimal totalScore = map.get("scoreTotal");
			if(totalScore == null){
				phiMember.setAllScore(new BigDecimal("0"));
			}else{
				phiMember.setAllScore(totalScore);
			}
	 }
	 }catch(Exception e){
		 e.printStackTrace();
		 return false;
	}
		return true;
	}

	/**
	 * 根据手机号码查询会员信息
	 * @param valueOf
	 * @return
	 */
	@Override
	public PhiMember findPhiMemberByTelNumber(String telNumber) {
		return phiMemberDao.findPhiMemberByTel(telNumber);
	}
	
	public PhiMember calcEnableScore(PhiMember member){
		List<PhiScoreFlow> pscoreflow = phiScoreFlowService.findPhiScoreFlowByCondition(member.getId());
		BigDecimal enableScore= new BigDecimal(0);
		if(pscoreflow.size()>0){
			 Iterator<PhiScoreFlow>  it= pscoreflow.iterator();
			 BigDecimal score= BigDecimal.ZERO;
			while(it.hasNext()){
				PhiScoreFlow psf=it.next();
				psf.setIsEnable(1);
				score=score.add(psf.getScore());
				phiScoreFlowService.savePhiScoreFlow(psf);
			}
			
			BigDecimal allScore= new BigDecimal(0);
			if(member.getEnableScore()==null){
				enableScore= enableScore.add(score);
			}else{
				enableScore= member.getEnableScore().add(score);
			}
			member.setDisableScore(member.getDisableScore().subtract(score));
			if(member.getAllScore()==null){
				allScore= allScore.add(score);
			}else{
				allScore= member.getAllScore().add(score);
			}
			
			member.setEnableScore(enableScore);
			member.setAllScore(allScore);
			saveOrUpdatePhiMember(member);
			pullMemberInfoToChenShang(member,"冻结积分计算|");
		}
		return member;
	}


	@Override
	public Map<String, Object> conversionParmas(Map<String, Object> parmas) {
		if(null!=parmas.get("plusOpenDate")){
			String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
			SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
			Date plusOpenDate_start = null;
			try {
				plusOpenDate_start = format.parse(parmas.get("plusOpenDate").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("plusOpenDate_start", plusOpenDate_start);
			parmas.remove("plusOpenDate");
		}
		if(null!=parmas.get("plusOpenDate_1")){
			String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
			SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
			Date plusOpenDate_end = null;
			try {
				plusOpenDate_end = format.parse(parmas.get("plusOpenDate_1").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("plusOpenDate_end", plusOpenDate_end);
			parmas.remove("plusOpenDate_1");
		}
		if(null!=parmas.get("createTime")){
			String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
			SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
			Date createTime_start = null;
			try {
				createTime_start = format.parse(parmas.get("createTime").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("createTime_start", createTime_start);
			parmas.remove("createTime");
		}
		if(null!=parmas.get("createTime_1")){
			String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
			SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
			Date createTime_end = null;
			try {
				createTime_end = format.parse(parmas.get("createTime_1").toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("createTime_end", createTime_end);
			parmas.remove("createTime_1");
		}
		
		if(null!=parmas.get("allScore")){
			BigDecimal allScore_min  = null;
			try {
				allScore_min = new BigDecimal(parmas.get("allScore").toString()) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("allScore_min", allScore_min);
			parmas.remove("allScore");
		}
		if(null!=parmas.get("allScore_1")){
			BigDecimal allScore_max = null;
			try {
				allScore_max = new BigDecimal(parmas.get("allScore_1").toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("allScore_max", allScore_max);
			parmas.remove("allScore_1");
		}
      if(null!=parmas.get("enableScore")){
			
			BigDecimal enableScore_min  = null;
			try {
				enableScore_min = new BigDecimal(parmas.get("enableScore").toString()) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("enableScore_min", enableScore_min);
			parmas.remove("enableScore");
		}
		if(null!=parmas.get("enableScore_1")){
			BigDecimal enableScore_max = null;
			try {
				enableScore_max = new BigDecimal(parmas.get("enableScore_1").toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parmas.put("enableScore_max", enableScore_max);
			parmas.remove("enableScore_1");
		}
		return parmas;
	}


	@Override
	public List<Object[]> conversionResults(List<Object[]> resultsList,
			Map<String, Object> parmas) {
	    //System.out.print("000000000000000000"+ resultsList.size());
		if(null != resultsList && resultsList.size() > 0 ){
			for (Object[] objects : resultsList) {
				if (null != objects[0]) {//是否为会员转换
					if("null".endsWith(objects[0].toString())){
						objects[0] ="";						
					}					
				}
				if (null != objects[6]) {//是否为会员转换
					if("1".equals(objects[6].toString())){
						objects[6] = "是";
					}else {
						objects[6] = "否";
					}
					
				}
				if (null != objects[2]) {//是否为会员转换
					if("male".equals(objects[2].toString())){
						objects[2] = "男";
					}else if("female".equals(objects[2].toString())){
						objects[2] = "女";
					}else{
						objects[2] = "未知";
					}
					
				}
				if (null != objects[7]) {//处理时间
					objects[7] = objects[7].toString().replace(".0", "");
					
				}
				if (null != objects[8]) {//处理时间
					objects[8] = objects[8].toString().replace(".0", "");
					
				}
				if (null != objects[10]) {//处理时间
					objects[10] = objects[10].toString().replace(".0", "");
					
				}
				if (null != objects[11]) {//处理时间
					if("0".endsWith(objects[11].toString())){
						objects[11] = "否";
					}else {
						objects[11] = "是";
					}		
				}
			}
		}
		return resultsList;
	}
	
	/**
	 * 根据会员id查询plus会员年龄
	 * 备注：plus会员年限显示规则：plus龄：n天/年 
		当年限<1年，显示n天（如：plus开通15天，Plus龄：15天；开通360天，Plus龄：360天）
		当年限>=1年，显示n年（如：plus开通1年3个月，Plus龄：1年；开通2年11个月22天，Plus龄：2年
		plus会员计算的是累计时长，不包含中断期间（如：首次开通2014.02.28，时长1年，2015.02.27到期，中断，2016.02.29再次续费2年，在2016.05.21时，用户Plus龄：1年；在2017.03.26，用户Plus龄：2年）
	 * @param memberId
	 * @return
	 */
	@SuppressWarnings("unused")
	@Override
	public int getPhiMemberPlusYear(Long memberId) {
		PhiMember phiMember = phiMemberDao.findPlusPhiMemberById(memberId);
		int plusYears = 0;
		String plusOpentype =  phiMember.getPlusOpenType() == null?"":phiMember.getPlusOpenType();
		Date currentDate = new Date();
		if(null != phiMember){
			String isplus = phiMember.getIsplusMember();
			Date plusvaliDate = phiMember.getValidTime(); 
			Date plusOpenDate = phiMember.getPlusOpenDate();
			Date plusrenewDate = phiMember.getPlusRenewDate();
			int validateYear = 0;
			int calYears = 0;
			int calDays = 0;
			int calHours = 0;
			if(plusOpenDate != null && plusvaliDate != null){
				validateYear = yearsBetween(plusOpenDate,plusvaliDate);//plus会员有效期1年
				calYears = yearsBetween(plusOpenDate,currentDate);
				calDays = daysBetween(plusOpenDate,currentDate);
				calHours = hoursBetween(plusOpenDate,currentDate);
			}
			
			//首次开通
			if("firstOpen".equals(plusOpentype)){
				//首次开通plus1年内
				if(calYears < 1){
					//问题数据
					if(calDays < 0){
						phiMember.setPlusYears(plusYears);
						
					}else if(0 < calHours&& calHours < 24) {
						phiMember.setPlusYears(1);
					}else {
						//plus会员开通多少天
						phiMember.setPlusYears(calDays);
						plusYears =  calDays;
					}
				}else if(calYears == 1) {
					phiMember.setPlusYears(calYears);
					plusYears =  calYears;
				}
			}else if("renewOpen".equals(plusOpentype)){
				//plus首次开通时间：1年
				int calrenewYears = yearsBetween(plusrenewDate,currentDate);
				//续费plus时间是已存在的plus龄加上现在开通时间
				plusYears = plusYears + calrenewYears;
				phiMember.setPlusYears(plusYears);
			}
		}else {
			phiMember.setPlusYears(plusYears);
		}
		return plusYears;
			
	}
	
	 public static int hoursBetween(Date date1,Date date2)  
	    {  
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(date1);  
	        long time1 = cal.getTimeInMillis();               
	        cal.setTime(date2);  
	        long time2 = cal.getTimeInMillis();       
	        long between_days=(time2-time1)/(1000*3600);  
	           
	       return Integer.parseInt(String.valueOf(between_days));         
	    }
	
	
	/** 
     * 计算两个日期之间相差的天数 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int daysBetween(Date date1,Date date2)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);  
           
       return Integer.parseInt(String.valueOf(between_days));         
    }
	
    
	/** 
     * 计算两个日期之间相差的年数 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int yearsBetween(Date date1,Date date2)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(date2);  
        long time2 = cal.getTimeInMillis();       
        long between_years=(time2-time1)/(31536000000l);  
       return Integer.parseInt(String.valueOf(between_years));         
    }
}
	
