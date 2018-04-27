package com.huatek.busi.service.impl.phicom.member;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.phicom.member.PhiMemberAddressDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dto.phicom.member.PhiMemberAddressDto;
import com.huatek.busi.dto.phicom.member.addAddressDto;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.member.PhiMemberAddress;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.member.PhiMemberAddressService;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.service.ExceptionLogService;
import com.huatek.frame.service.dto.ExceptionLogDto;
import com.huatek.frame.util.DateUtil;

@Service("phiMemberAddressServiceImpl")
@Transactional
public class PhiMemberAddressServiceImpl implements PhiMemberAddressService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiMemberAddressServiceImpl.class);
	
	@Autowired
	PhiMemberAddressDao phiMemberAddressDao;
	@Autowired
	PhiMemberDao phiMemberDao; 
	
	@Autowired
	CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	@Autowired
    private ExceptionLogService exceptionLogService;//异常日志服务类
	
	@Override
	public boolean  saveMemberAddressDtoForApp(PhiMemberAddressDto entityDto,Long memberId,int Uid)  {
		log.debug("save phiMemberDto @" + entityDto);
		boolean addAddress ;
		String defaultState = entityDto.getDefaultState();
		List<PhiMemberAddress> addressesList = phiMemberAddressDao.findPhiMemberAddressByMemberId(memberId);
	     if(addressesList.size() >= 10){
	    	 return addAddress = false;
	    	 //throw new BusinessRuntimeException("新增地址不得超过10个", "-1");
	    	
	     }else {
		     if(defaultState.equals("1")){
		    	 //修改原来的默认地址
		    	 PhiMemberAddress phiMemberAddress = phiMemberAddressDao.fingDefaultAddressByMemberId(memberId);
		    	 if(phiMemberAddress != null){
		    		 phiMemberAddress.setDefaultState("0");
		    		 phiMemberAddress.setMemberId(memberId);
		    		 phiMemberAddressDao.saveOrUpdatePhiMemberAddress(phiMemberAddress);
		    	 }
		    	 //保存现在的地址
			     /*PhiMemberAddress entity = BeanCopy.getInstance().convert(entityDto, PhiMemberAddress.class);
			     entity.setMemberId(memberId);
			     phiMemberAddressDao.persistentPhiMemberAddress(entity);*/
			     //新增会员收获地址时在此调用辰商的接口同步地址信息；(新增默认地址)
			     try{
			    	 //PhiMemberAddressDto entityDtos = BeanCopy.getInstance().convert(entity, PhiMemberAddressDto.class);   
				     savePhiMemberAddressDto(entityDto,Uid);		    	   	 
			     }catch(Exception e){
			    	  e.printStackTrace();
			     }
			      	
		     }else if(defaultState.equals("0")){
		    	//新增会员收获地址时在此调用辰商的接口同步地址信息;
		    	 //PhiMemberAddress entity = BeanCopy.getInstance().convert(entityDto, PhiMemberAddress.class);
		    	 //entity.setMemberId(memberId);
		    	 //phiMemberAddressDao.persistentPhiMemberAddress(entity);
		    	 try{
			    	 //PhiMemberAddressDto entityDtos = BeanCopy.getInstance().convert(entity, PhiMemberAddressDto.class);   
				     savePhiMemberAddressDto(entityDto,Uid);		    	   	 
			     }catch(Exception e){
			    	  e.printStackTrace();
			     }
		     }
		     return addAddress = true;
	    }
	}
	
	
	/**
	 * @param entityDto
	 */
	@Override
	public void savePhiMemberAddressDto(PhiMemberAddressDto entityDto,int Uid)  {
		log.debug("save phiMemberAddressDto @" + entityDto);
		PhiMemberAddress entity = new PhiMemberAddress();
/*		entityDto.setUId(12345);
		entityDto.setAddressDetail("文吉路99号");
		entityDto.setCity("市辖区");
		entityDto.setCountry("东城区");
		entityDto.setDefaultState("is_default");
		entityDto.setName("王志成");
		entityDto.setTel("13370268197");
		entityDto.setProvince("北京市");*/
		//entityDto.setUId(12345);*/
		JsonObject jo=new JsonObject();
		jo.addProperty("address", entityDto.getAddressDetail());
		jo.addProperty("city", entityDto.getCity());
		jo.addProperty("county", entityDto.getCountry());
		jo.addProperty("is_default", entityDto.getDefaultState());
		jo.addProperty("name", entityDto.getName());
		jo.addProperty("phone", entityDto.getTel());
		jo.addProperty("province", entityDto.getProvince());
		//jo.addProperty("uid", entityDto.getUId());1232249
		jo.addProperty("uid", Uid);
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"addressadd", "address_add", jo.toString());
		JsonObject returnData = sss.getAsJsonObject();
		JsonObject jsonObject=new JsonObject();
		JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
		String isOk = dataJson.getString("status");
		/********同步地址信息给辰商*********/
   	 	try{
			CmdInterfaceReceiveMessageDto entityDt=new CmdInterfaceReceiveMessageDto();
			entityDt.setBusiTime(new Date());
			entityDt.setCode("address");
			entityDt.setCreateTime(new Date());
			entityDt.setRequestBody(jo.toString());
			entityDt.setResult(isOk.equals("true")?200:0);
			entityDt.setMsg(dataJson.getString("message"));
			entityDt.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDt);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(returnData.isJsonObject()){
			jsonObject=returnData.getAsJsonObject();
			JsonObject addressObject = new JsonParser().parse(jsonObject.get("data").toString()).getAsJsonObject();
			int UId = addressObject.get("uid").getAsInt();
			PhiMember phiMember = phiMemberDao.findPhiMemberByUid(UId);  
			entityDto = new PhiMemberAddressDto();
			entityDto.setId(addressObject.get("address_id").getAsLong());
			entityDto.setAddressDetail(addressObject.get("address").getAsString());
			String defaultValue = addressObject.get("is_default").getAsString(); 
			if("true".equals(defaultValue)){
				entityDto.setDefaultState("1");
			}else if("false".equals(defaultValue)){
				entityDto.setDefaultState("0");
			}else {
				entityDto.setDefaultState(defaultValue);
			}
			entityDto.setName(addressObject.get("name").getAsString());
			entityDto.setCreateTime(addressObject.get("created_time").getAsString());
			entityDto.setProvince(addressObject.get("province").getAsString());
			entityDto.setCountry(addressObject.get("county").getAsString());
			entityDto.setCity(addressObject.get("city").getAsString());
			entityDto.setTel(addressObject.get("phone").getAsString());
			//entityDto.setMemberId(addressObject.get("user_id").getAsLong());
			entityDto.setUId(addressObject.get("uid").getAsInt());
			entityDto.setMemberId(phiMember.getId());
			
			//根据页面传进来的值设置保存的值信息
			 entity = BeanCopy.getInstance().convert(entityDto, PhiMemberAddress.class);
			//保存之前操作
			beforeSave(entityDto, entity);
			//进行持久化保存
			phiMemberAddressDao.persistentPhiMemberAddress(entity);
			//log.debug("saved entityDto id is @" + entity.getId());
		}
		//JsonObject addressObject = jsonObject.get("data").getAsJsonObject();
		
		
		
		

	}
	
	
	@Override
	public PhiMemberAddressDto getPhiMemberAddressDtoById(Long id) {
		log.debug("get phiMemberAddress by id@" + id);
		PhiMemberAddress entity = phiMemberAddressDao.findPhiMemberAddressById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiMemberAddressDto entityDto = BeanCopy.getInstance().convert(entity, PhiMemberAddressDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiMemberAddress(Long id, PhiMemberAddressDto entityDto) {
		log.debug("update entityDto by id@" + id);

		PhiMemberAddress entity = phiMemberAddressDao.findPhiMemberAddressById(id);
		entityDto.setId(entity.getId());
		entityDto.setAddressDetail(entity.getAddressDetail());
		entityDto.setCity(entity.getCity());
		entityDto.setCountry(entity.getCountry());
		entityDto.setDefaultState(entity.getDefaultState());
		entityDto.setName(entity.getName());
		entityDto.setTel(entity.getTel());
		entityDto.setProvince(entity.getProvince());
		entityDto.setUId(entity.getUId());
		JsonObject jo=new JsonObject();
		jo.addProperty("address", entityDto.getAddressDetail());
		jo.addProperty("city", entityDto.getCity());
		jo.addProperty("county", entityDto.getCountry());
		jo.addProperty("is_default", entityDto.getDefaultState());
		jo.addProperty("name", entityDto.getName());
		jo.addProperty("phone", entityDto.getTel());
		jo.addProperty("province", entityDto.getProvince());
		jo.addProperty("uid", entityDto.getUId());
		jo.addProperty("address_id", entityDto.getId());
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"addresssave", "address_save", jo.toString());
		JsonObject returnData = sss.getAsJsonObject();
		JsonObject jsonObject=new JsonObject();
		if(returnData.isJsonObject()){
			jsonObject=returnData.getAsJsonObject();
		}
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiMemberAddressDao.persistentPhiMemberAddress(entity);
	}
	
	
	
	@Override
	public void deletePhiMemberAddress(Long id) {
		log.debug("delete phiMemberAddress by id@" + id);
		beforeRemove(id);
/*		PhiMemberDto phimember = new PhiMemberDto();
		phimember.setUId(12345);*/
		PhiMemberAddress entity = phiMemberAddressDao.findPhiMemberAddressById(id);
		PhiMemberAddressDto entityDto = BeanCopy.getInstance().convert(entity, PhiMemberAddressDto.class);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		entityDto.setUId(entity.getUId());
		entityDto.setId(id);
		JsonObject jo=new JsonObject();
		jo.addProperty("uid", entityDto.getUId());
		jo.addProperty("address_id", entityDto.getId());
		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"addressdelete", "address_delete", jo.toString());
		JsonObject returnData = sss.getAsJsonObject();
		JsonObject jsonObject=new JsonObject();
		if(returnData.isJsonObject()){
			jsonObject=returnData.getAsJsonObject();
		}
		try {
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
			/**************删除地址信息同步给辰商成功****************/
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
		
		phiMemberAddressDao.deletePhiMemberAddress(entity);

	}
	
	@Override
	public DataPage<PhiMemberAddressDto> getAllPhiMemberAddressPage(QueryPage queryPage) {
		DataPage<PhiMemberAddress> dataPage = phiMemberAddressDao.getAllPhiMemberAddress(queryPage);
					
		DataPage<PhiMemberAddressDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiMemberAddressDto.class);
		if(dataPage != null && dataPage.getContent() != null){
			for(int i= 0;i<dataPage.getContent().size();i++){
				//拼一个地址详情给app端
		         String HarvestAddress = dataPage.getContent().get(i).getProvince()
		           +dataPage.getContent().get(i).getCity()+dataPage.getContent().get(i)
		          .getCountry()+dataPage.getContent().get(i).getAddressDetail();       
		           datPageDto.getContent().get(i).setHarvestAddress(HarvestAddress);	         
				}
		}	
		return datPageDto;
	}
	
	@Override
	public List<PhiMemberAddressDto> getAllPhiMemberAddressDto() {
		List<PhiMemberAddress> entityList = phiMemberAddressDao.findAllPhiMemberAddress();
		List<PhiMemberAddressDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiMemberAddressDto.class);
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
	* @param    phiMemberAddressDto
	* @param    phiMemberAddress
	* @return  void    
	* @
	*/
	private void beforeSave(PhiMemberAddressDto entityDto, PhiMemberAddress entity) {

	}


	/**
	* @Title: getPhiMemberAddressDtoByMemberId 
	* @Description:  根据memberId查找会员收货地址信息
	* @param    phiMemberAddressDto
	* @param    phiMemberAddress
	* @return  void  
	*/
	@Override
	public List<PhiMemberAddressDto> getPhiMemberAddressDtoByMemberId(int UId) {
    	
		List<PhiMemberAddress> addressList = phiMemberAddressDao.findPhiMemberAddressByUId(UId); 
		
		List<PhiMemberAddressDto> entityDto = BeanCopy.getInstance().convertList(addressList, PhiMemberAddressDto.class);
		return entityDto;
		
	}


	/***
	 * 获取用户地址信息接口
	 */
	@Override
	public void getPhiMemberAddressDtoByUId(int UId) {
		PhiMember phiMember = phiMemberDao.findPhiMemberByUid(UId);
		Long memberId  = phiMember.getId();
		List<PhiMemberAddress> addressList = phiMemberAddressDao.findPhiMemberAddressByUId(UId); 
		if(addressList != null && addressList.size() > 0){
			phiMemberAddressDao.batchDeleteAddress(addressList);
	   		 /*for (PhiMemberAddress phiMemberAddress : addressList) {
	   			phiMemberAddressDao.deletePhiMemberAddress(phiMemberAddress);
			}*/
  	  	}
		PhiMemberAddress addresses = new PhiMemberAddress();
		/* test Start*/
		PhiMemberAddressDto addressDto =  new PhiMemberAddressDto();
		//addressDto.setUId(12345);
		JsonObject jo=new JsonObject();
		jo.addProperty("uid",UId);
		JsonObject sss = null;
		try {
			sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"addresslist", "address_list", jo.toString());
			//JsonObject mm = new JsonParser().parse(newJjString).getAsJsonObject();
			JsonObject returnData = new JsonParser().parse(sss.toString()).getAsJsonObject();
			JSONObject dataJson = JSONObject.fromObject(sss.toString());
			//JsonElement returnData = new JsonParser().parse(sss.getResponseContent());
			JsonObject jsonObject=new JsonObject();
			if(returnData.isJsonObject()){
				jsonObject=returnData.getAsJsonObject();
				 JsonArray addressArray = new JsonParser().parse(jsonObject.get("data").toString()).getAsJsonArray();
				 Iterator<JsonElement> addressEIterator = addressArray.iterator();
			      while (addressEIterator.hasNext()) {
			    	  JsonElement addressElement = addressEIterator.next();
			    	  JsonObject addressObject = new JsonParser().parse(addressElement.toString()).getAsJsonObject();
			    	  Long addressId = addressObject.get("address_id").getAsLong();
			    	  
			    		  addressDto =  new PhiMemberAddressDto();
				    	  addressDto.setId(addressId);
				    	  addressDto.setAddressDetail(addressObject.get("address").getAsString());
				    	  String defaultValue = addressObject.get("is_default").getAsString(); 
							if("true".equals(defaultValue)){
								addressDto.setDefaultState("1");
							}else if("false".equals(defaultValue)){
								addressDto.setDefaultState("0");
							}else {
								addressDto.setDefaultState(defaultValue);
							}
				    	  addressDto.setName(addressObject.get("name").getAsString());
				    	  addressDto.setCreateTime(addressObject.get("created_time").getAsString());
				    	  addressDto.setProvince(addressObject.get("province").getAsString());
				    	  addressDto.setCountry(addressObject.get("county").getAsString());
				    	  addressDto.setCity(addressObject.get("city").getAsString());
				    	  addressDto.setTel(addressObject.get("phone").getAsString());
				    	  addressDto.setMemberId(memberId);
				    	  addressDto.setUId(UId);
				    	  addresses = (PhiMemberAddress)BeanCopy.getInstance().convert(addressDto, PhiMemberAddress.class);
				    	  //进行持久化保存
				    	  phiMemberAddressDao.persistentPhiMemberAddress(addresses);
				}
			}
			String isOk = dataJson.getString("status");
			/**************开通plus会员信息同步给辰商成功****************/
			CmdInterfaceReceiveMessageDto entityDto = new CmdInterfaceReceiveMessageDto();
			entityDto.setBusiTime(new Date());
			entityDto.setCode("addresslist");
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(jo.toString());
			entityDto.setResult(isOk.equals("true")?200:0);
			entityDto.setMsg(dataJson.getString("message"));
			entityDto.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
		} catch (Exception e) {
			log.error("开通plus会员推送参数解析失败！", sss.toString(), "memberinfomodify");
			ExceptionLogDto exceptionLogDto = new ExceptionLogDto();
			exceptionLogDto.setEcptMessage(phiMember.getTel() + "同步会员地址信息失败addresslist,推送数据："+ sss.toString());
			exceptionLogDto.setEcptModule("同步会员地址");
			exceptionLogDto.setCreateTime(DateUtil.timeFormat.format(new Date()));
			exceptionLogDto.setAcctName(phiMember.getTel());
			exceptionLogDto.setEcptCode("addresslist");
			exceptionLogDto.setEcptStack(e.toString());
			exceptionLogService.saveExceptionLog(exceptionLogDto);
		} 
	}
	
	
	@Override
	public void saveAddadressDto(addAddressDto entityDto) {
		
	}


	@Override
	public DataPage<PhiMemberAddressDto> getAllPhiMemberAddressPageByMemberId(
			QueryPage queryPage, Long id) {
		return null;
	}


	

}
