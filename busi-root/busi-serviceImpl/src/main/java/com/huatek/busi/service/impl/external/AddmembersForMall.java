package com.huatek.busi.service.impl.external;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.phicom.member.PhiMemberAddressDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dao.phicom.member.PhiMemberGradeDao;
import com.huatek.busi.dto.external.ExternalWithDataResponse;
import com.huatek.busi.dto.phicom.member.PhiMemberGradeDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.member.PhiMemberGrade;
import com.huatek.busi.service.external.BusiQualityExternalForPiccomService;
import com.huatek.busi.service.phicom.member.PhiMemberAddressService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.cmd.dto.FwPropertyDto;
import com.huatek.cmd.service.FwpropertyService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.util.TimeStampUtil;

/**
 * 接口 同步会员信息方法
 * @author eli_cui
 *
 */
@Service("addmembersForMall")
@Transactional
public class AddmembersForMall implements BusiQualityExternalForPiccomService{

	@Autowired
	PhiMemberService service;
	
	@Autowired
	PhiMemberDao dao;
	
	@Autowired
	PhiMemberAddressDao addressdao;

	@Autowired
	PhiMemberGradeDao gradedao;
	
	
	@Autowired
	private PhiMemberAddressService addressService;
	
	@Autowired
	private FwpropertyService fwpropertyService;
	

	/**
	 * 同步会员信息
	 */
	@Override
	public ExternalWithDataResponse receiveData(String busiType, String appKey, String data, Date timestamp) {
		JSONObject dataJson = null;
		JSONArray infoObj = null; 
		PhiMember phiMember = null;
		try {
			dataJson = JSONObject.fromObject(data); 
			infoObj = dataJson.getJSONArray("members");
		} catch (Exception e) {
			System.out.println("参数解析失败！");
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "参数解析失败！，" + e.toString());
		}
		BigDecimal enableScore = new BigDecimal(0);
		// 新增 Or 修改
		for (int i = 0; i < infoObj.size(); i++) {
			JSONObject memberJson = infoObj.getJSONObject(i);
			phiMember = dao.findPhiMemberByUid(memberJson.getInt("UID"));
			if(phiMember == null){//新增
				phiMember = new PhiMember();
			} else{//修改
				enableScore = phiMember.getEnableScore();
			}
			this.saveOrUpdateMember(memberJson,phiMember);
		 }
		PhiMemberGrade memberGrades =gradedao.getMemberGrade(enableScore);
		if (memberGrades == null){
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "");
		}
		PhiMemberGradeDto memberGrade = BeanCopy.getInstance().convert(memberGrades, PhiMemberGradeDto.class);
		String membergrade = memberGrade.getMemberGrade();
		String membergradeName = "";
		String image = memberGrade.getImage();
		String gradecode =memberGrade.getMemberGradeCode();
	    JSONObject  object2 = new JSONObject();
	    if(StringUtils.isNotBlank(membergrade)){
	    	FwPropertyDto fwPropertyDto = fwpropertyService.getPropertyByPropertyValue(membergrade);
	    	if(null != fwPropertyDto){
	    		membergradeName = fwPropertyDto.getPropertyName();
	    	}
	    }
	    object2.put("member_grade", membergradeName);
	    object2.put("image", image);
	    object2.put("member_grade_code", gradecode);
	    JSONObject  object = new JSONObject();
	    object.put("point", enableScore);
	    object.put("grade",object2 );
        String msg = new Gson().toJson(object); 
//		return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS,"会员信息同步成功！",object.toString());
		return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS,"会员信息同步成功！",msg);
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
	private void saveOrUpdateMember(JSONObject memberJson,PhiMember phiMember){
		int UId = memberJson.getInt("UID"); 
		phiMember.setUId(UId);
		String birthday=memberJson.getString("birthday");
		phiMember.setUserName(memberJson.getString("member_name"));
		phiMember.setRealName(memberJson.getString("real_name"));
		phiMember.setSex(memberJson.getString("sex"));
		phiMember.setPortrait(memberJson.getString("portrait"));
		phiMember.setBirthday(TimeStampUtil.StrToDate(birthday));
		phiMember.setTel(memberJson.getString("tel"));
		phiMember.setPayCode(memberJson.getString("pay_code"));
		phiMember.setBlacklist(memberJson.getString("blacklist"));
//		phiMember.setState(memberJson.getString("state"));
		
//		phiMember.setCreateTime(new Date(Long.valueOf(memberJson.getString("create_time"))));
		try {
			phiMember.setCreateTime(com.huatek.frame.util.DateUtil.timeFormat.parse(memberJson.getString("create_time")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//有则已积分商城的为准，没有则更新商城的会员信息
		if(StringUtils.isEmpty(phiMember.getMemberGradeCode())){
			phiMember.setMemberGradeCode(memberJson.getString("member_grade_code"));
		}
//		phiMember.setPlusCode(memberJson.getString("plus_code"));
		
		//以下转换是为了处理咱们系统和斐讯会员编码不一致的问题
		if(StringUtils.isNotEmpty(phiMember.getMemberGradeCode())){
			PhiMemberGrade phiMembergrade = new PhiMemberGrade();
			switch(phiMember.getMemberGradeCode()){
				case "pt":
					phiMembergrade.setId(13L);
					break;
				case "by":
					phiMembergrade.setId(14L);
					break;
				case "bj":
					phiMembergrade.setId(15L);
					break;
				case "zs":
					phiMembergrade.setId(17L);
					break;
				case "zz":
					phiMembergrade.setId(20L);
					break;
			}
			phiMember.setPhiMembergrade(phiMembergrade);
		}
		dao.saveOrUpdatePhiMember(phiMember);
		//dao.saveOrUpdatePhiMember(entity);
		//service.savePhiMemberDto(memberdto);
		/*Long memberId = phiMember.getId();
		JSONArray  addess =  memberJson.getJSONArray("cnee_info");
		List<PhiMemberAddress> addressList = new ArrayList<PhiMemberAddress>();
		for(int j = 0 ;j < addess.size();j++){
			PhiMemberAddress address = new PhiMemberAddress();
			JSONObject addressdata = addess.getJSONObject(j);
			address.setMemberId(memberId);
			address.setUId(UId);
			address.setName(addressdata.getString("name"));
			address.setTel(addressdata.getString("tel"));
			address.setAddressDetail(addressdata.getString("addr"));
			addressList.add(address);
//			addressdao.persistentPhiMemberAddress(address);
			//addressdao.saveOrUpdatePhiMemberAddress(address);
		}
		if(addressList.size() > 0 && memberId != null){
			addressdao.bacthSaveAddress(addressList);
		}*/
	}
	
	@Override
	public Map<String, String> getType() {
		 Map<String, String> map = new HashMap<String, String>();  
		 map.put( "synchronousMember", Constant.MEMBER_INFO_ADD); 
		return map;
	}
}
