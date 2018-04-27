package com.huatek.busi.service.impl.external;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDao;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dto.external.ExternalWithDataResponse;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDetailDto;
import com.huatek.busi.dto.phicom.coupons.PhiCouponsDto;
import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.service.external.BusiQualityExternalForPiccomService;
import com.huatek.frame.core.beancopy.BeanCopy;

/**
 * 优惠卷同步
* @ClassName: PhiCouponsExternal 
* @Description: 优惠卷同步 
* @author eden_sun
* @date Feb 3, 2018 2:44:32 AM 
 */
@Service("phiCouponsExternal")
@Transactional
public class PhiCouponsExternal implements BusiQualityExternalForPiccomService {
	@Autowired
	PhiCouponsDetailDao phiCouponsDetailDao;
	
	@Autowired
	PhiCouponsDao phiCouponsDao;
	
	/*@Autowired
	private PhiCouponsDetailService phiCouponsDetailService;
	
	@Autowired
	private PhiCouponsService phiCouponsService;*/
	
	@Override
	public ExternalWithDataResponse receiveData(String busiType, String appKey,String data, Date timestamp) {
		    JSONObject dataJson = null;
		    JsonArray 	jsonArray  = null;
		    PhiCoupons  entity = null;
		    List<PhiCoupons> list = new ArrayList();
		    try{ 
		    	if(busiType.equals("synchronousCoupon")){
		    		jsonArray = new JsonParser().parse(data).getAsJsonArray();
		    	}else{
		    		dataJson = JSONObject.fromObject(data);
		    	}
		    }catch(Exception e){
		    	System.out.println("参数解析失败！");
				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "参数解析失败！，"+e.getMessage());
		    }    
			switch(busiType){
				case "synchronousCoupon":
					try{
						Iterator<JsonElement> iterator=jsonArray.iterator();
						  while (iterator.hasNext()) {
							JsonElement jElement=iterator.next();
						     JsonObject jObject =jElement.getAsJsonObject();
						     long wayId = jObject.get("scheme_id").getAsLong();
						     PhiCoupons isPhiCoupons = phiCouponsDao.findPhiCouponsById(wayId);
						     List<PhiCouponsDetailDto> couponsDetailDtoslist = new ArrayList<PhiCouponsDetailDto>();
						     int countTemp=0;
				              if(isPhiCoupons ==null){//新增
				            	  PhiCouponsDto  phiCouponsDto = new PhiCouponsDto();       
							         phiCouponsDto.setCpnsWayId(jObject.get("scheme_id").getAsLong());
							         phiCouponsDto.setCpnsName(jObject.get("scheme_name").getAsString());
									 phiCouponsDto.setCpnsQuantity(jObject.get("scheme_quantity").getAsString());
									 phiCouponsDto.setCpnsType(jObject.get("scheme_type").getAsString());
									 phiCouponsDto.setCponWay(jObject.get("scheme_desc").getAsString());
									 phiCouponsDto.setCpnsValid(jObject.get("scheme_validity").getAsString());
							         PhiCoupons phiCoupons =BeanCopy.getInstance().convert(phiCouponsDto, PhiCoupons.class);
									 phiCouponsDao.persistentPhiCoupons(phiCoupons);
									 JsonArray  couponsrArray =jObject.get("coupons").getAsJsonArray();
									 for(int i=0;i<couponsrArray.size();i++){
										    JsonObject jsonObject =  couponsrArray.get(i).getAsJsonObject();
											 PhiCouponsDetailDto  phiCouponsDetailDto = new PhiCouponsDetailDto();
											 phiCouponsDetailDto.setCoupCode(jsonObject.get("cpns_key").getAsString());
											 phiCouponsDetailDto.setCpnsId(jsonObject.get("cpns_id").getAsLong());
											 phiCouponsDetailDto.setCoupWayId(jObject.get("scheme_id").getAsLong());
											 phiCouponsDetailDto.setExchangeStatus(jsonObject.get("exchange_status").getAsString());
											 phiCouponsDetailDto.setCoupStatus(jsonObject.get("cpns_status").getAsString());
											 phiCouponsDetailDto.setStartTime(jsonObject.get("from_time").getAsString());
	//										 phiCouponsDetailDto.setEndTime(jsonObject.get("rule_status").getAsString());//此字段改为end_time
											 phiCouponsDetailDto.setEndTime(jsonObject.get("end_time").getAsString());
	//										 phiCouponsDetailDto.setCoupUid(jsonObject.get("uid").getAsLong());//辰商反应不要此字段
											 couponsDetailDtoslist.add(phiCouponsDetailDto);
											 countTemp+=1;
									 }
							  }else{//修改								   
 								   List<PhiCouponsDetail> phiCouponsDetailList = phiCouponsDetailDao.getCouponsDetailsBycouponsId(wayId); 
								   Set<Long> cpnsIds = new HashSet<Long>();
								   int couponsDetailSize= phiCouponsDetailList.size();
								   countTemp=couponsDetailSize;
								   if(phiCouponsDetailList != null && couponsDetailSize > 0){
									   for(PhiCouponsDetail phiCouponsDetail:phiCouponsDetailList){
										   cpnsIds.add(phiCouponsDetail.getCpnsId());
									   }
								   }
								   JsonArray  couponsrArray =jObject.get("coupons").getAsJsonArray();
								   for(int i=0;i<couponsrArray.size();i++){
									    JsonObject jsonObject =  couponsrArray.get(i).getAsJsonObject();
									    long cpnsId = jsonObject.get("cpns_id").getAsLong();//字表主键
									    if(!cpnsIds.contains(cpnsId)){//排除已经存在的优惠券
									    	PhiCouponsDetailDto  phiCouponsDetailDto = new PhiCouponsDetailDto();
									    	 phiCouponsDetailDto.setCpnsId(cpnsId);
											 phiCouponsDetailDto.setCoupCode(jsonObject.get("cpns_key").getAsString());
											 phiCouponsDetailDto.setCoupWayId(jObject.get("scheme_id").getAsLong());
											 phiCouponsDetailDto.setExchangeStatus(jsonObject.get("exchange_status").getAsString());
											 phiCouponsDetailDto.setCoupStatus(jsonObject.get("cpns_status").getAsString());
											 phiCouponsDetailDto.setStartTime(jsonObject.get("from_time").getAsString());
//											 phiCouponsDetailDto.setEndTime(jsonObject.get("rule_status").getAsString());//此字段改为end_time
											 phiCouponsDetailDto.setEndTime(jsonObject.get("end_time").getAsString());
//											 phiCouponsDetailDto.setCoupUid(jsonObject.get("uid").getAsLong());//辰商反应不要此字段
											 couponsDetailDtoslist.add(phiCouponsDetailDto);
											 countTemp+=1;
									    }
									}
							  }
				              if(null != couponsDetailDtoslist && couponsDetailDtoslist.size() > 0){
				            	 List<PhiCouponsDetail>  phiCouponsDetaillist = BeanCopy.getInstance().convertList(couponsDetailDtoslist,PhiCouponsDetail.class);				    
								 phiCouponsDetailDao.saveBatchPhiCouponsDetail(phiCouponsDetaillist);
								 //修改优惠券数量和相关信息
								   PhiCoupons phiCoupons = phiCouponsDao.findPhiCouponsById(wayId);
								   phiCoupons.setCpnsQuantity(new BigDecimal(countTemp));
								   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
								   Date date = new Date();
								   phiCoupons.setCpnsWayId(wayId);
								   phiCoupons.setCpnsName(jObject.get("scheme_name").getAsString());
								   phiCoupons.setCponWay(jObject.get("scheme_desc").getAsString());
								   phiCoupons.setCpnsValid(sdf.parse(jObject.get("scheme_validity").getAsString()));
								   phiCouponsDao.saveOrUpdatePhiCoupons(phiCoupons);
				              }
			              }
						  return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "同步优惠券成功");
					}catch (Exception e) {
						e.printStackTrace();
					}
					//同步优惠劵
				case "getCouponByMember":
					//找到用户所有的券码   
					List<PhiCouponsDetail>  detailslist = phiCouponsDetailDao.findPhiCouponsDetailsByUid(dataJson.getLong("UID"));
					SimpleDateFormat  fmt2 =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					       List<JSONObject>  objectlist =  new ArrayList<JSONObject>();         
					   for(PhiCouponsDetail pcCouponsDetail : detailslist){
					           JSONObject obj = new JSONObject();
					           PhiCoupons phiCoupons2 = phiCouponsDetailDao.fingPhiCouponsByWayId(pcCouponsDetail.getCoupWayId());
					         //  obj.put("scheme_id", phiCoupons2.getCpnsWayId()== null ? "" :phiCoupons2.getCpnsWayId());
					           obj.put("coupon_id", pcCouponsDetail.getCpnsId()== null ? "" :pcCouponsDetail.getCpnsId());					           
					           obj.put("coupon_name", phiCoupons2.getCpnsName() == null ? "" :phiCoupons2.getCpnsName() );
					           obj.put("coupon_status", pcCouponsDetail.getCoupStatus() == null ? "":pcCouponsDetail.getCoupStatus());
					           obj.put("coupon_amount ", phiCoupons2.getCpnsQuantity() == null ? "": phiCoupons2.getCpnsQuantity());
					           obj.put("coupon_desc", phiCoupons2.getCpnsDesc() == null ? "" : phiCoupons2.getCpnsDesc());
					           obj.put("coupon_valid", phiCoupons2.getCpnsValid() == null ? "" :fmt2.format(phiCoupons2.getCpnsValid()));
					           objectlist.add(obj);
					        }
					
				    return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "获取优惠券成功",objectlist);
				    //更新优惠券的数量
				case "updateCouponByMember":
		             /*JSONArray coupArray = dataJson.getJSONArray("coupons_info");
		             for (int i = 0; i < coupArray.size(); i++) {       
		            	 JSONObject jsonObject =coupArray.getJSONObject(i);
		              PhiCoupons phiCoupons2 = phiCouponsDao.findPhiCouponsById(jsonObject.getLong("scheme_id"));
		              if(phiCoupons2 ==null){
		            	  return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "没有该优惠劵");
		              }
		              phiCoupons2.setCpnsQuantity(phiCoupons2.getCpnsQuantity().subtract(BigDecimal.valueOf(1)));
		                    phiCouponsDao.saveOrUpdatePhiCoupons(phiCoupons2);
					  PhiCouponsDetail couponsDetail = phiCouponsDetailDao.findPhiCouponsDetailById(jsonObject.getLong("cpns_id"));
					        couponsDetail.setCoupStatus("1");
					        phiCouponsDetailDao.saveOrUpdatePhiCouponsDetail(couponsDetail);
					}		*/
					 JSONObject  object = dataJson.getJSONObject("coupons_info");
					 PhiCouponsDetail phiCoupons = phiCouponsDetailDao.findPhiCouponsDetailById(object.getLong("cpns_id"));
		              if(phiCoupons ==null){
		            	  return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "没有该优惠劵");
		              }else {
		            	  phiCoupons.setCoupStatus("1");
		            	  phiCoupons.setExchangeStatus("1");
					        phiCouponsDetailDao.saveOrUpdatePhiCouponsDetail(phiCoupons);
				     }
		  return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "优惠券已使用");				
		}   
		return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS,"");
	}
	

	
	
	@Override
	public Map<String, String> getType() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("synchronousCoupon","synchronousCoupon");	       
		map.put("getCouponByMember","getCouponByMember");
		map.put("updateCouponByMember","updateCouponByMember");
		return  map;
	}

	
	public static void main(String[] args) {
		String data="[{'scheme_id':' Q12345','scheme_name ': '50元优惠券','scheme_type ': '1',' scheme_quantity ': '30',' scheme_validity': '2017-12-28 17:45:19',' scheme_desc ': '50元优惠券满100可用','coupons':[{'cpns_id': '1111','cpns_key': 'YHQ155555222 ','cpns_status': '1','exchange_status': '1','cpns_point': '20','from_time': '2017-12-27 17:45:19','rule_status': '2017-12-28 17:45:19','uid': 'xxxxx'}]},{'scheme_id':' Q123444445','scheme_name ': '50元优惠券','scheme_type ': '1',' scheme_quantity ': '30',' scheme_validity': '2017-12-28 17:45:19',' scheme_desc ': '50元优惠券满100可用','coupons':[{'cpns_id': '1111','cpns_key': 'YHQ155555222 ','cpns_status': '1','exchange_status': '1','cpns_point': '20','from_time': '2017-12-27 17:45:19','rule_status': '2017-12-28 17:45:19','uid': 'xxxxx'}]}]";
		 JsonArray returnData = new JsonParser().parse(data).getAsJsonArray();
		Iterator<JsonElement> iterator=returnData.iterator();
		while (iterator.hasNext()) {
			JsonElement ssElement=iterator.next();
			System.out.println(ssElement);
			
		}
		
		
//		System.out.println(aaa.get(0));
	}
}
