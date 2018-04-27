package com.huatek.busi.service.impl.phicom.order;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDao;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDao;
import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDetailDao;
import com.huatek.busi.dao.phicom.member.PhiMemberAddressDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dao.phicom.order.PhiOrderDao;
import com.huatek.busi.dao.phicom.order.PhiOrderinfoDao;
import com.huatek.busi.dao.phicom.product.PhiProductDao;
import com.huatek.busi.dao.phicom.product.PhiProductTypeDao;
import com.huatek.busi.dao.phicom.winner.PhiWinnersDao;
import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.order.PhiOrderinfoDto;
import com.huatek.busi.dto.phicom.product.PhiProductDto;
import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCoupons;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.member.PhiMemberAddress;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.model.phicom.order.PhiOrderinfo;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.phicom.product.PhiProductType;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.order.PhiOrderinfoService;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.file.excel.exp.conversion.BaseConversionService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.core.util.TimeStampUtil;
import com.huatek.frame.core.util.WanJiaJinFuUtil;
import com.huatek.frame.core.util.WanJiaJinFuUtil.WanJiaJinFuExchangeVO;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("phiOrderServiceImpl")
@Transactional
public class PhiOrderServiceImpl implements PhiOrderService,BaseConversionService{
	
	private static final Logger log = LoggerFactory.getLogger(PhiOrderServiceImpl.class);
	
	@Autowired
    PhiMemberService phiMemberService;
	
	@Autowired
	PhiOrderDao phiOrderDao;
	
	/*@Autowired
	PhiPayInfo phiPayInfo;*/
	
	@Autowired
	PhiMemberDao phiMemberDao;
	
	@Autowired
	PhiOrderinfoDao phiOrderinfoDao;
	
	@Autowired
	PhiProductDao phiProductDao;
	
	@Autowired
	PhiWinnersDao phiWinnersDao;
	
	@Autowired
	PhiCouponsDao phiCouponsDao;
	
	@Autowired
	PhiCouponsDetailDao phiCouponsDetailDao;
	
	@Autowired
	PhiThirdPartyCouponsDao phiThirdPartyCouponsDao;
	
	@Autowired
	PhiThirdPartyCouponsDetailDao phiThirdPartyCouponsDetailDao;
	
	@Autowired
	PhiMemberAddressDao phiMemberAddressDao;
	
	@Autowired
	PhiProductTypeDao phiProductTypeDao;
	
	@Autowired
	private CmdFileMangerService cmdFileMangerService;
	
	@Autowired
	CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	
	
	@Autowired
	PhiOrderinfoService phiOrderinfoService;
	
	@Autowired
	PhiScoreFlowService phiScoreFlowService;
	
	@Override
	public void savePhiOrderDto(PhiOrderDto entityDto)  {
		log.debug("save phiOrderDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiOrder entity = BeanCopy.getInstance().convert(entityDto, PhiOrder.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiOrderDao.persistentPhiOrder(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	@Override
	public PhiOrderDto getPhiOrderDtoById(Long id) {
		log.debug("get phiOrder by id@" + id);
		PhiOrder entity = phiOrderDao.findPhiOrderById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiOrderDto entityDto = BeanCopy.getInstance().convert(entity, PhiOrderDto.class);
		if(null!=entity.getPhiMember()){
			entityDto.setUserName(entity.getPhiMember().getUserName());
		}
		PhiOrderinfoDto orderinfoDto = entityDto.getPhiOrderInfo();
		PhiOrderinfo orderinfo = entity.getPhiOrderInfo();
		if(null!=orderinfo){
			entityDto.setProductName(entity.getPhiOrderInfo().getProductName());
			entityDto.setProductCode(entity.getPhiOrderInfo().getProductCode());
			entityDto.setProductClass(entity.getPhiOrderInfo().getProductClass());
			entityDto.setMoney(entity.getPhiOrderInfo().getMoney());
			entityDto.setMarketPrice(entity.getPhiOrderInfo().getMarketPrice());
			entityDto.setScore(entity.getPhiOrderInfo().getScore());
			entityDto.setProductCount(entity.getPhiOrderInfo().getProductCount());
			entityDto.setProductType(entity.getPhiOrderInfo().getProductType());
			String reciverAddress = entityDto.getProvince()+entityDto.getCity()+entityDto.getArea()+entityDto.getAddressDetail();
			orderinfoDto.setReciverAddress(reciverAddress);
			entityDto.setPhiOrderInfo(orderinfoDto);
			List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(orderinfo.getProductImageHead());
			if(null!=imsges&&imsges.size()>0){
				entityDto.setProductImg(imsges.get(0).getFilePath());
				orderinfoDto.setProductImg(imsges.get(0).getFilePath());
			}
		}
		
		return entityDto;
	}
	@Override
	public void updatePhiOrder(Long id, PhiOrderDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiOrder entity = phiOrderDao.findPhiOrderById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiOrderDao.persistentPhiOrder(entity);
	}
	
	
	@Override
	public void deletePhiOrder(Long id) {
		log.debug("delete phiOrder by id@" + id);
		beforeRemove(id);
		PhiOrder entity = phiOrderDao.findPhiOrderById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiOrderDao.deletePhiOrder(entity);
	}
	@Override
	public DataPage<PhiOrderDto> getAllPhiOrderPage(QueryPage queryPage) {
		DataPage<PhiOrder> dataPage = phiOrderDao.getAllPhiOrder(queryPage);
		int count = dataPage.getTotalRows();
		//List<PhiOrderDto> list = BeanCopy.getInstance().convertList(dataPage.getContent(), PhiOrderDto.class);
		DataPage<PhiOrderDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiOrderDto.class);
		if(null!=dataPage.getContent()){
			for(int i=0;i<dataPage.getContent().size();i++){
				PhiOrder order = dataPage.getContent().get(i);
				if(null!=order){
					PhiOrderinfo entityOrderinfo = order.getPhiOrderInfo();
					PhiMember member = order.getPhiMember();
						if(null!=entityOrderinfo&&null!=member){
							if(!StringUtils.isEmpty(member.getUserName())){
								datPageDto.getContent().get(i).setUserName(member.getUserName());
							}
							datPageDto.getContent().get(i).setProductName(entityOrderinfo.getProductName());
							datPageDto.getContent().get(i).setScore(entityOrderinfo.getScore());
							datPageDto.getContent().get(i).setMarketPrice(entityOrderinfo.getMarketPrice());
							datPageDto.getContent().get(i).setMoney(entityOrderinfo.getMoney());
							datPageDto.getContent().get(i).setIsPlus(member.getIsplusMember());
							List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(entityOrderinfo.getProductImageHead());
							if(null!=imsges&&imsges.size()>0){
								datPageDto.getContent().get(i).getPhiOrderInfo().setProductImg(imsges.get(0).getFilePath());
							}
							if(null!=order.phiLogistic){
								datPageDto.getContent().get(i).setNu(order.phiLogistic.getNu());
							}
							
						}
						
				}
					datPageDto.getContent().get(i).setCode(Long.valueOf(count-(datPageDto.getPage()-1)*datPageDto.getPageSize()-i));		   
		 }
			
	 }
 		//List<PhiOrderDto> list= List<PhiOrderDto> dataPage.getContent();
		/*for(int i=0;i<dataPage.getContent().size();i++){
			Long id = 
			PhiMemberDao phiMember = phiMemberDao.findPhiMemberById(id);
			dataPage.getContent().get(i).setPhiMember(phiMember);
		}*/
		
		return datPageDto;
	}
	
	@Override
	public DataPage<PhiOrderDto> getPhiOrderByStatusPage(QueryPage queryPage) {
		DataPage<PhiOrder> dataPage = phiOrderDao.getAllPhiOrder(queryPage);
		//List<PhiOrderDto> list = BeanCopy.getInstance().convertList(dataPage.getContent(), PhiOrderDto.class);
		DataPage<PhiOrderDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiOrderDto.class);
 		//List<PhiOrderDto> list= List<PhiOrderDto> dataPage.getContent();
		/*for(int i=0;i<dataPage.getContent().size();i++){
			Long id = 
			PhiMemberDao phiMember = phiMemberDao.findPhiMemberById(id);
			dataPage.getContent().get(i).setPhiMember(phiMember);
		}*/
		
		return datPageDto;
	}
	
	
	@Override
	public List<PhiOrderDto> getAllPhiOrderDto() {
		List<PhiOrder> entityList = phiOrderDao.findAllPhiOrder();
		List<PhiOrderDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiOrderDto.class);
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
	* @param    phiOrderDto
	* @param    phiOrder
	* @return  void    
	* @
	*/
	private void beforeSave(PhiOrderDto entityDto, PhiOrder entity) {

	}
	
	@Override
	public void saveNewOrderDto(PhiOrderDto phiOrderDto, PhiProductDto phiProductDto){
		log.debug("save phiOrderDto @" + phiOrderDto);
		PhiOrderinfoDto orderInfoDto =  BeanCopy.getInstance().convert(phiOrderDto, PhiOrderinfoDto.class);
		//显示存储商品Id,
		orderInfoDto.setProductId(phiProductDto.getId());
		orderInfoDto.setProductClass(phiProductDto.getProductClass());
		//orderInfoDto =  BeanCopy.getInstance().convert(phiProductDto, PhiOrderinfoDto.class);
		//orderid、productid另存
		orderInfoDto.setProductId(phiProductDto.getId());
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		phiOrderDto.setMemberId(userInfo.getId());
		phiOrderDto.setStatus("2");
		//orderInfodto转model
		PhiOrderinfo orderInfo = BeanCopy.getInstance().convert(phiOrderDto, PhiOrderinfo.class);
		phiOrderinfoDao.persistentPhiOrderinfo(orderInfo);
		//phiOrderDto.setPhiOrderInfo(orderInfoDto);
		//根据页面传进来的值设置保存的值信息
		PhiOrder entity = BeanCopy.getInstance().convert(phiOrderDto, PhiOrder.class);
		entity.setPhiOrderInfo(orderInfo);
		phiOrderDao.persistentPhiOrder(entity);
		orderInfoDto.setOrderId(entity.getId());
		BeanUtils.copyNotNullProperties(orderInfoDto, orderInfo, 
				new String[] {""});
		PhiMember menberEntity = phiMemberDao.findPhiMemberById(userInfo.getId());
		menberEntity.getUId();
		phiOrderinfoDao.persistentPhiOrderinfo(orderInfo);
		//辰商接口调用
//		JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"regions", "regions", "{uid}");
		
		
//		HttpClientResponseResult sss =PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"order", "order", "{}");
		
		
		log.debug("saved entityDto id is @" + entity.getId());
	}
	


	@Override
	public int getPhiOrderByMemberId(Long memberId) {
		List<PhiOrder> orderLists = phiOrderDao.findPhiOrderByMemberId(memberId);
		if(null == orderLists || orderLists.size()<0 ){
			throw new ResourceNotFoundException(memberId);
		}
		int orderCount = 0;
		for (PhiOrder order:orderLists){
			BigDecimal moneyUse = order.getPhiOrderInfo().getMoney();
			BigDecimal scoreUse = order.getPhiOrderInfo().getScore();
			if(null != scoreUse){
				if(null != moneyUse){
					orderCount ++;
				}else{
					orderCount ++;
				}
			}
		}
		//List<PhiOrderDto> orderDtos = BeanCopy.getInstance().convertList(orderLists, PhiOrderDto.class);
		return orderCount;
	}

	@Override
	public void saveNewOrderDto(PhiOrderDto phiOrderDto, Long productId) {
//		PhiProduct productEntity = phiProductDao.findPhiProductById(productId);
//		PhiOrderinfoDto orderInfoDto =  BeanCopy.getInstance().convert(phiOrderDto, PhiOrderinfoDto.class);
		PhiOrderinfo orderInfo = BeanCopy.getInstance().convert(phiOrderDto, PhiOrderinfo.class);
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		PhiMember menberEntity = phiMemberDao.findPhiMemberById(Long.valueOf(167));
		PhiOrder entity = BeanCopy.getInstance().convert(phiOrderDto, PhiOrder.class);
		entity.setPhiMember(menberEntity);
		entity.setStatus("2");
		phiOrderinfoDao.persistentPhiOrderinfo(orderInfo);
		entity.setPhiOrderInfo(orderInfo);
		entity.setProductId(productId);
		phiOrderDao.persistentPhiOrder(entity);
		//辰商接口调用
//		JsonObject data = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"regions", "regions", "{}");
		JsonObject giveDate=new JsonObject();
		giveDate.addProperty("uid", menberEntity.getUId());
		giveDate.addProperty("nums", orderInfo.getProductCount());
		giveDate.addProperty("payed", orderInfo.getMoney());
		giveDate.addProperty("order_total", orderInfo.getMarketPrice());
		giveDate.addProperty("pmt_order","");
		giveDate.addProperty("integral", orderInfo.getScore());
		giveDate.addProperty("address_id","");
		giveDate.addProperty("express","");
		giveDate.addProperty("cost_freight","");
		giveDate.addProperty("paytype", entity.getPayType());
		giveDate.addProperty("paytime", TimeStampUtil.getLongTimeStamp(orderInfo.getPaytime()));
		
		giveDate.addProperty("cost_freight", orderInfo.getLogisticMoney());
		giveDate.addProperty("invoice", false);
		giveDate.addProperty("invoice_title","");
		giveDate.addProperty("invoice_addon", "");
		String items="[{'goods_id':'"+productId+"','nums':'"+orderInfo.getProductCount()+"','price':'"+orderInfo.getMarketPrice()+"','buy_price':'"+orderInfo.getMoney()+"','integral':'"+orderInfo.getScore()+"'}]";
		giveDate.addProperty("items",items);
//		PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"order", "order", giveDate.getAsJsonObject().getAsString());
		
	}

	@Override
	public void editOrderWin(Long orderId,Long productId) {
		
		PhiOrder entity = phiOrderDao.findPhiOrderById(orderId);
		PhiProduct product = phiProductDao.findPhiProductById(productId);
		if(null!=entity){
			PhiOrderinfo orderInfo = entity.getPhiOrderInfo();
			if(null!=orderInfo){
				orderInfo.setIsWin("1");//使中奖状态设为已中奖
				phiOrderinfoDao.saveOrUpdatePhiOrderinfo(orderInfo);
				entity.setPhiOrderInfo(orderInfo);
				entity.setStatus("2");//已中奖则将订单状态设为代发货
				phiOrderDao.saveOrUpdatePhiOrder(entity);
			}
		}
		List<PhiOrder> orderlist = phiWinnersDao.getAllOrderByProductId(productId);
		if(null!=orderlist&&orderlist.size()>0){
			for(PhiOrder order:orderlist){
				PhiOrderinfo orderInfo = order.getPhiOrderInfo();
				if(null!=orderInfo){
					if(null==orderInfo.getIsWin()||"".equals(orderInfo.getIsWin())){//未中奖设中奖状态为未中奖
						orderInfo.setIsWin("2");//2是未中奖，1是已中奖
					}
					if(null!=product){
						orderInfo.setWinnerTime(product.getWinnerTime());
					}
					orderInfo.setWinnerStatus("2");//使中奖状态设为已开奖
				}
				phiOrderinfoDao.saveOrUpdatePhiOrderinfo(orderInfo);
				order.setPhiOrderInfo(orderInfo);
				order.setStatus("4");//未中奖订单状态设为已完成
				phiOrderDao.saveOrUpdatePhiOrder(order);
			}
		}
		
		
	}

	@SuppressWarnings("unused")
	@Override
	public String addOrder( Long productId,Long memberId) throws RuntimeException{
		PhiProduct product = phiProductDao.findPhiProductById(productId);
//		PhiProduct product = phiProductDao.findPhiProductByIdLock(productId);
		BigDecimal nullmoney = new BigDecimal("0");
		if(null!=product){
			PhiOrderinfo phiOrderInfo = new PhiOrderinfo();
			BeanCopy.getInstance().mapIgnoreId(product, phiOrderInfo);
			if(null!=product.getPhiProductType()){
				PhiProductType productType = phiProductTypeDao.findPhiProductTypeById(product.getPhiProductType());
				if(null!=productType){
					phiOrderInfo.setProductType(productType.getTypeName());
				}
				
			}
			
			//0表示该商品已下架
			if("0".equals(product.getIsShop())){
				return "OutTime";
			}
			//判断商品是否过期（下架）
			if(null!=product.getDownTime()&&!"".equals(product.getDownTime())){
				Date nowDate = new Date();
				int nowtime = TimeStampUtil.getIntTimeStamp(nowDate);
				int downtime = TimeStampUtil.getIntTimeStamp(product.getDownTime());
				if(nowtime>downtime){
					return "OutTime";
				}
			}
			//判断商品是否已达兑换上线
			if(null!=product.getExchangSuperLimit()){
				BigDecimal ExchangQuantity = phiMemberDao.findCountExchange(productId, memberId);
				if(product.getExchangSuperLimit().subtract(ExchangQuantity).intValue()<=0){
					return "overflow";
				}
			}
			
			//今日是否开奖todayisOpen
 	   		  //开奖时间
 	   		 Date today = new Date();
 	   	     Date openDate = product.getWinnerTime();
 	   	     String winnerStatus = product.getWinnerStatus();
 	   	     if(openDate != null && winnerStatus !=null){
 	   	     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      	 String openString =   df.format(openDate);
	         String todayString = df.format(today); 
	         int x =compare_date_(openString,todayString);
	         if(x<1){//开奖时间是今天;
	        	 return "todayopen";//今日开奖；	 
	         }else if(product.getWinnerStatus()== "1"){
	        	 return "opened" ;//已开奖；	 
	         }
	            	   	     
 	   	     }
 	   	     
			BigDecimal productCount = new BigDecimal("1");
			phiOrderInfo.setProductCount(productCount);
			//减商品库存
			if(product.getProductStock().subtract(productCount).intValue()<0){
				return "notenough";
			}
			
			//判断是否为实物类的商品（防止接口调用错误）
			if("2".equals(product.getProductClass())){
				return "throw";
			}
//			PhiOrderinfo phiOrderInfo = BeanCopy.getInstance().convert(product, PhiOrderinfo.class);
			PhiMember phiMember = phiMemberDao.findPhiMemberByIdLock(memberId);
			if (null == phiMember) {
				 throw new ResourceNotFoundException(memberId);
			}			 
				BigDecimal	enableScore = phiMember.getEnableScore();
				BigDecimal  Needscore = product.getScore();
				BigDecimal  restScore = new BigDecimal(0);
				if(null!=enableScore&&null!=Needscore){
					restScore = enableScore.subtract(Needscore);
					if(enableScore.compareTo(Needscore)<0){
						return "Needscore";
					}
				}
				//用户可用积分为空，商品兑换积分不为空
				if(null==enableScore&&null!=Needscore){
					return "Needscore";
				}
//				else if((phiMember.getMemberGradeCode().compareTo(product.getNeedGrade())<0)){
				if(null != phiMember.getPhiMembergrade()&&null!=product.getNeedGrade()){
					if((phiMember.getPhiMembergrade().getId() <Long.valueOf(StringUtils.defaultString(product.getNeedGrade(), "0")))){
						return "NeedGrade";
					}
				}
				//用户等级为空，商品要求的等级不为空
				if(null==(phiMember.getPhiMembergrade())&& !StringUtils.isEmpty(product.getNeedGrade()))
				{
					return "NeedGrade";
				}
			
				
				PhiOrder entity = new PhiOrder();
				BeanCopy.getInstance().mapIgnoreId(phiOrderInfo, entity);
				if(null!=phiOrderInfo && null!=entity && null!=phiMember){
					if("0".equals(product.getProductClass())&& null!=product.getCoupWayId()){//如果购买的是优惠券
						if(null!=product.getCoupWayId()){
							PhiCoupons coupons = phiCouponsDao.findPhiCouponsById(product.getCoupWayId());
//							PhiCoupons coupons = phiCouponsDao.findPhiCouponsByIdLock(product.getCoupWayId());
							if(null!=coupons){
								List<PhiCouponsDetail>	couponslist = phiCouponsDetailDao.getUseCouponsDetailsById(coupons.getCpnsWayId());
								if(null!=couponslist&&couponslist.size()>0){
									PhiCouponsDetail couponsDetial = couponslist.get(0);
									phiOrderInfo.setCoupCode(couponslist.get(0).getCoupCode());
									if((product.getMoney()==null)||((product.getMoney()==null)||nullmoney.compareTo(product.getMoney())>0||nullmoney.compareTo(product.getMoney())==0)){
										//优惠券不需要付款
										couponsDetial.setMemberId(phiMember);
										couponsDetial.setExchangeStatus("1");//1表示已兑换
										phiCouponsDetailDao.saveOrUpdatePhiCouponsDetail(couponsDetial);
										this.postHasPhiCouponsDetailToChengShang(couponsDetial);//推送已兑换的优惠卷给辰商
										phiOrderInfo.setCoupType("优惠券");//直接存储优惠券的类型名称
										phiOrderInfo.setCpnsWayId(couponsDetial.getCpnsId());
									}
									//phiOrderInfo.setCoupType("1");//1代表优惠券
//									phiOrderInfo.setCoupType("优惠券");//直接存储优惠券的类型名称
//									phiOrderInfo.setCpnsWayId(couponsDetial.getCpnsId());
								}else{
									return "notenough";
								}
							}
						}
					}else if("3".equals(product.getProductClass())&& null!=product.getThirdId()){//如果购买的是第三方券
						
						PhiThirdPartyCoupons thirdPart = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsByCoupId(product.getThirdId());
//						PhiThirdPartyCoupons thirdPart = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsByCoupIdLock(product.getThirdId());
						if(null!=thirdPart){
							List <PhiThirdPartyCouponsDetail> thirdDetials = phiThirdPartyCouponsDetailDao.findCanUseThirdPartyCouponsDetail(thirdPart.getCpnsId());
							if(null!=thirdDetials&&thirdDetials.size()>0){
								PhiThirdPartyCouponsDetail third = thirdDetials.get(0);
								phiOrderInfo.setCoupCode(thirdDetials.get(0).getCoupCode());
								if((product.getMoney()==null)||((product.getMoney()==null)||nullmoney.compareTo(product.getMoney())>0||nullmoney.compareTo(product.getMoney())==0)){
									third.setPhiMember(phiMember);
									third.setExchangeStatus("1");//1表示已兑换
									phiThirdPartyCouponsDetailDao.saveOrUpdatePhiThirdPartyCouponsDetail(third);
									WanJiaJinFuUtil wjjf=new WanJiaJinFuUtil();
									WanJiaJinFuExchangeVO exchangeVO = wjjf.exchangePresent(phiMember.getTel(), thirdDetials.get(0).getCoupCode(),"1");
									phiOrderInfo.setCoupType(thirdPart.getCpnsType());//2代表第三方券
									phiOrderInfo.setThirdId(third.getId());
								}
//								BigDecimal	num = new BigDecimal(1);
//								thirdPart.setCpnsQuantity(thirdPart.getCpnsQuantity().subtract(num));//兑换第三方券数量-1
//								phiOrderInfo.setCoupType(thirdPart.getCpnsType());//2代表第三方券
//								phiOrderInfo.setThirdId(third.getId());
//								phiThirdPartyCouponsDao.saveOrUpdatePhiThirdPartyCoupons(thirdPart);//保存第三方券
							}else{
								return "notenough";
							}
						}
						
					}
					java.util.Random random = new java.util.Random();
					int a = random.nextInt(9000) + 1000;
					entity.setOrderCode(TimeStampUtil.getIntTimeStamp(new Date())+String.valueOf(a));//订单编号
					entity.setPhiMember(phiMember);
					entity.setPhiOrderInfo(phiOrderInfo);
					if(("1".equals(product.getProductClass()))&& ((null==product.getMoney())||nullmoney.compareTo(product.getMoney())>0||nullmoney.compareTo(product.getMoney())==0)){
						//中奖类且不需要付款
						entity.setStatus("1");
						phiMember.setEnableScore(restScore);
						phiMemberDao.saveOrUpdatePhiMember(phiMember);
						entity.setPhiMember(phiMember);
						entity.setIsdelete("0");//订单未删除
						//减库存
						BigDecimal newProductStock = product.getProductStock().subtract(productCount);
						product.setProductStock(newProductStock);
						//商品兑换数量+1
//						product.setExchangQuantity(product.getExchangQuantity()+1);
						phiProductDao.saveOrUpdatePhiProduct(product);
						PhiScoreFlow flowentity=new PhiScoreFlow();
						flowentity.setScoreAction("积分兑换");
						flowentity.setCreateTime(new Date());
						flowentity.setMemberId(phiMember.getId());
						flowentity.setOrderCode(entity.getOrderCode());
						flowentity.setScoreType("consume");
						flowentity.setSourcePlatform("phicomm_scoremall");
						flowentity.setScore(Needscore);
						phiScoreFlowService.savePhiScoreFlow(flowentity);
						//积分兑换 ，积分发生变化，给商城推送会员信息
						phiMemberService.pullMemberInfoToChenShang(phiMember,"积分兑换|");
					}else if((("0".equals(product.getProductClass()))||("3".equals(product.getProductClass())))&& ((null==product.getMoney())||nullmoney.compareTo(product.getMoney())>0||nullmoney.compareTo(product.getMoney())==0)){
						//券码类且不需要付款
						phiMember.setEnableScore(restScore);
						phiMemberDao.saveOrUpdatePhiMember(phiMember);
						entity.setPhiMember(phiMember);
						PhiScoreFlow flowentity=new PhiScoreFlow();
						flowentity.setScoreAction("积分兑换");
						flowentity.setCreateTime(new Date());
						flowentity.setMemberId(phiMember.getId());
						flowentity.setOrderCode(entity.getOrderCode());
						flowentity.setScoreType("consume");
						flowentity.setSourcePlatform("phicomm_scoremall");
						flowentity.setScore(Needscore);
						phiScoreFlowService.savePhiScoreFlow(flowentity);
						entity.setStatus("4");//券码类且不需要付款状态置为已完成
						entity.setIsdelete("0");//订单未删除
						//减库存
						BigDecimal newProductStock = product.getProductStock().subtract(productCount);
						//商品兑换数量+1
//						product.setExchangQuantity(product.getExchangQuantity()+1);
						product.setProductStock(newProductStock);
						phiProductDao.saveOrUpdatePhiProduct(product);
						//积分兑换 ，积分发生变化，给商城推送会员信息
						phiMemberService.pullMemberInfoToChenShang(phiMember,"积分兑换|");
					}else{
						entity.setStatus("5");//否则置为5已取消
						entity.setIsdelete("0");
					}
					phiOrderInfo.setProductId(product.getId());
					phiOrderInfo.setProductImageHead(product.getProductImageHead());
					entity.setProductId(product.getId());
					entity.setCreateTime(new Date());
					phiOrderinfoDao.saveOrUpdatePhiOrderinfo(phiOrderInfo);
					entity.setPhiOrderInfo(phiOrderInfo);
					phiOrderDao.saveOrUpdatePhiOrder(entity);
					return String.valueOf(entity.getId());
					
				}
			
		}
		       return "faile";
	}
	
	
	@SuppressWarnings("unused")
	@Override
	public String addAddressOrder( Long productId,Long memberId, Long addressId) throws RuntimeException{
		PhiProduct product = phiProductDao.findPhiProductById(productId);
//		PhiProduct product = phiProductDao.findPhiProductByIdLock(productId);
		BigDecimal nullmoney = new BigDecimal("0");
		if(null!=product){
			PhiOrderinfo phiOrderInfo = new PhiOrderinfo();
			BeanCopy.getInstance().mapIgnoreId(product, phiOrderInfo);
			if(null!=product.getPhiProductType()){
				PhiProductType productType = phiProductTypeDao.findPhiProductTypeById(product.getPhiProductType());
				if(null!=productType){
					phiOrderInfo.setProductType(productType.getTypeName());
				}
				
			}
			
			//0表示该商品已下架
			if("0".equals(product.getIsShop())){
				return "OutTime";
			}
			//判断商品是否过期（下架）
			if(null!=product.getDownTime()&&!"".equals(product.getDownTime())){
				Date nowDate = new Date();
				int nowtime = TimeStampUtil.getIntTimeStamp(nowDate);
				int downtime = TimeStampUtil.getIntTimeStamp(product.getDownTime());
				if(nowtime>downtime){
					return "OutTime";
				}
			}
			
			//判断商品是否已达兑换上线
			if(null!=product.getExchangSuperLimit()){
				BigDecimal ExchangQuantity = phiMemberDao.findCountExchange(productId, memberId);
				if(product.getExchangSuperLimit().subtract(ExchangQuantity).intValue()<=0){
					return "overflow";
				}
			}
			//今日是否开奖todayisOpen
	   		  //开奖时间
	   		 Date today = new Date();
	   	     Date openDate = product.getWinnerTime();
	   	     String winnerStatus = product.getWinnerStatus();
	   	     if(openDate != null && winnerStatus !=null){
	   	     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      	 String openString =   df.format(openDate);
	         String todayString = df.format(today); 
	         int x =compare_date_(openString,todayString);
	         if(x<1){//开奖时间是今天;
	        	 return "todayopen";//今日开奖；	 
	         }else if(product.getWinnerStatus()== "1"){
	        	 return "opened" ;//已开奖；	 
	         }
	            	   	     
	   	     }
	   	     
			BigDecimal productCount = new BigDecimal("1");
			phiOrderInfo.setProductCount(productCount);
			//减商品库存
			if("1".equals(product.getProductClass())||"2".equals(product.getProductClass())){
				if(product.getProductStock().subtract(productCount).intValue()<0){
					return "notenough";
				}
			}
			
			PhiMember phiMember = phiMemberDao.findPhiMemberByIdLock(memberId);
			if (null == phiMember) {
				 throw new ResourceNotFoundException(memberId);
			}			 
				BigDecimal	enableScore = phiMember.getEnableScore();
				BigDecimal  Needscore = product.getScore();
				BigDecimal  restScore = new BigDecimal(0);
				if(null!=enableScore&&null!=Needscore){
					restScore = enableScore.subtract(Needscore);
					if(enableScore.compareTo(Needscore)<0){
						return "Needscore";
					}
				}
				//用户可用积分为空，商品兑换积分不为空
				if(null==enableScore&&null!=Needscore){
					return "Needscore";
				}
//				else if((phiMember.getMemberGradeCode().compareTo(product.getNeedGrade())<0)){
				if(null != phiMember.getPhiMembergrade()&&null!=product.getNeedGrade()){
					if((phiMember.getPhiMembergrade().getId() <Long.valueOf(StringUtils.defaultString(product.getNeedGrade(), "0")))){
						return "NeedGrade";
					}
				}
				//用户等级为空，商品要求的等级不为空
				if(null==(phiMember.getPhiMembergrade())&& !StringUtils.isEmpty(product.getNeedGrade()))
				{
					return "NeedGrade";
				}
				
				PhiOrder entity = new PhiOrder();
				BeanCopy.getInstance().mapIgnoreId(phiOrderInfo, entity);
				if(null!=phiOrderInfo && null!=entity && null!=phiMember){

					java.util.Random random = new java.util.Random();
					int a = random.nextInt(9000) + 1000;
					entity.setOrderCode(TimeStampUtil.getIntTimeStamp(new Date())+String.valueOf(a));//订单编号
					entity.setPhiMember(phiMember);
					//订单收获地址
					PhiMemberAddress address= phiMemberAddressDao.findPhiMemberAddressById(addressId);
					if(null == address){
						throw new ResourceNotFoundException(memberId);
					}
					//收货省份
					phiOrderInfo.setProvince(address.getProvince());
					//收货城市
					phiOrderInfo.setCity(address.getCity());
					//收货区域;注意：辰商存的是country
					phiOrderInfo.setArea(address.getCountry());
					//详细收货地址
					phiOrderInfo.setAddressDetail(address.getAddressDetail());
					//收货人姓名
					phiOrderInfo.setReceiverName(address.getName());
					//收货人电话
					phiOrderInfo.setTel(address.getTel());
					//存收货地址id
					phiOrderInfo.setAddressId(addressId);
					//运费
					phiOrderInfo.setLogisticMoney(product.getLogisticsCost());
					entity.setPhiOrderInfo(phiOrderInfo);
					if(("2".equals(product.getProductClass()))&&((null==product.getMoney())||nullmoney.compareTo(product.getMoney())>0||nullmoney.compareTo(product.getMoney())==0)){
						//实物类且不需要付款状态置为待发货
						entity.setStatus("2");
						entity.setIsdelete("0");//订单未删除
						phiMember.setEnableScore(restScore);
						phiMemberDao.saveOrUpdatePhiMember(phiMember);
						//减库存
						BigDecimal newProductStock = product.getProductStock().subtract(productCount);
						product.setProductStock(newProductStock);
						//商品兑换数量+1
//						product.setExchangQuantity(product.getExchangQuantity()+1);
						phiProductDao.saveOrUpdatePhiProduct(product);
						entity.setPhiMember(phiMember);
						phiOrderInfo.setProductId(product.getId());
						phiOrderInfo.setProductImageHead(product.getProductImageHead());
						entity.setProductId(product.getId());
						entity.setCreateTime(new Date());
						phiOrderinfoDao.saveOrUpdatePhiOrderinfo(phiOrderInfo);
						entity.setPhiOrderInfo(phiOrderInfo);
						phiOrderDao.saveOrUpdatePhiOrder(entity);
						PhiScoreFlow flowentity=new PhiScoreFlow();
						flowentity.setScoreAction("积分兑换");
						flowentity.setCreateTime(new Date());
						flowentity.setMemberId(phiMember.getId());
						flowentity.setOrderCode(entity.getOrderCode());
						flowentity.setScoreType("consume");
						flowentity.setSourcePlatform("phicomm_scoremall");
						flowentity.setScore(Needscore);
						phiScoreFlowService.savePhiScoreFlow(flowentity);
						JsonObject giveDate=new JsonObject();
						if(null==entity.getPayType()||"".equals(entity.getPayType())){
							giveDate.addProperty("paytype", "integral");
						}else{
							switch(entity.getPayType()){
							case "1" :giveDate.addProperty("paytype", "wxpay");
					        	break;
							case "2" :giveDate.addProperty("paytype", "alipay");
				        		break;
							case "3" :giveDate.addProperty("paytype", "jdpay");
				        		break;
							case "4" :giveDate.addProperty("paytype", "chinapay");
			        			break;
							case "5" :giveDate.addProperty("paytype", "COD");
			        			break;
							 default:
								 giveDate.addProperty("paytype", "integral");
						        	break;
						   }
						}
						
						//辰商接口调用
//						JsonObject data = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"regions", "regions", "{}");
						
 						giveDate.addProperty("integral_order_id", entity.getOrderCode());
						giveDate.addProperty("uid", phiMember.getUId());
						giveDate.addProperty("nums", phiOrderInfo.getProductCount());
						giveDate.addProperty("payed", phiOrderInfo.getMoney());
						giveDate.addProperty("order_total", phiOrderInfo.getMarketPrice());
						giveDate.addProperty("pmt_order","");
						giveDate.addProperty("integral", phiOrderInfo.getScore());
						giveDate.addProperty("address_id",addressId);
						giveDate.addProperty("express","1");
						giveDate.addProperty("paytime", TimeStampUtil.getLongTimeStamp(phiOrderInfo.getPaytime()));
						
						giveDate.addProperty("cost_freight", phiOrderInfo.getLogisticMoney());//运费
						giveDate.addProperty("invoice", false);
						giveDate.addProperty("invoice_title","");
						giveDate.addProperty("invoice_addon", "");
						Long goodsId = product.getGoodsId();
						String items="[{\"goods_id\":'"+goodsId+"',\"nums\":'"+phiOrderInfo.getProductCount()+"',\"price\":'"+phiOrderInfo.getMarketPrice()+"',\"buy_price\":'"+phiOrderInfo.getMoney()+"',\"integral\":'"+phiOrderInfo.getScore()+"'}]";
                        JsonElement ss = new JsonParser().parse(items);
//						String items="[{'product_id':'"+productId+"','nums':'"+phiOrderInfo.getProductCount()+"','price':'"+phiOrderInfo.getMarketPrice()+"','buy_price':'"+phiOrderInfo.getMoney()+"','integral':'"+phiOrderInfo.getScore()+"'}]";
//						String items="[{\"product_id\":\""+productId+"\",\"nums\":\""+phiOrderInfo.getProductCount()+"\",\"price\":\""+phiOrderInfo.getMarketPrice()+"\",\"buy_price\":\""+phiOrderInfo.getMoney()+"\",\"integral\":\""+phiOrderInfo.getScore()+"\"}]";
						giveDate.add("items", ss);
						JsonObject sss=PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"order", "order", new Gson().toJson(giveDate));
						JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
						String isOk = dataJson.getString("status");
						/********同步订单信息给辰商*********/
			       	 	try{
							CmdInterfaceReceiveMessageDto entityDto=new CmdInterfaceReceiveMessageDto();
							entityDto.setBusiTime(new Date());
							entityDto.setCode("order");
							entityDto.setCreateTime(new Date());
							entityDto.setRequestBody(giveDate.toString());
							entityDto.setResult(isOk.equals("true")?200:0);
							entityDto.setMsg(dataJson.getString("message"));
							entityDto.setResponseBody(new Gson().toJson(sss.toString()));
							cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
						}catch(Exception e){
							e.printStackTrace();
						}
						//积分兑换 ，积分发生变化，给商城推送会员信息
						phiMemberService.pullMemberInfoToChenShang(phiMember,"积分兑换|");
						return String.valueOf(entity.getId());
					}else{
						entity.setStatus("5");//否则状态置为已取消
						entity.setIsdelete("0");
					}
					
					phiOrderInfo.setProductId(product.getId());
					phiOrderInfo.setProductImageHead(product.getProductImageHead());
					entity.setProductId(product.getId());
					entity.setCreateTime(new Date());
					phiOrderinfoDao.saveOrUpdatePhiOrderinfo(phiOrderInfo);
					entity.setPhiOrderInfo(phiOrderInfo);
					phiOrderDao.saveOrUpdatePhiOrder(entity);
					return String.valueOf(entity.getId());
					
				}
			
		}
		       return "faile";
	}

	@Override
	public void setPhiOrderStatusDto(Long id) {
		PhiOrder entity = phiOrderDao.findPhiOrderById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		entity.setStatus("5");
		entity.setIsdelete("0");
		phiOrderDao.saveOrUpdatePhiOrder(entity);
	}

	@Override
	public void editOrderAfterPayById(Long orderId) {
		PhiOrder  orderEnty = phiOrderDao.findPhiOrderById(orderId);
		if(null==orderEnty){
			throw new ResourceNotFoundException(orderId);
		}
		//获取商品信息
		Long productId = orderEnty.getProductId();
		if(productId==null){
			throw new ResourceNotFoundException(orderId);
		}
		PhiProduct product = phiProductDao.findPhiProductById(productId);
		if(null == product){
			throw new ResourceNotFoundException(productId);
		}
		//获取用户信息
		PhiMember phiMember = orderEnty.getPhiMember();
		if(null == phiMember){
			throw new ResourceNotFoundException(orderId);
		}
		BigDecimal	enableScore = phiMember.getEnableScore();
		BigDecimal  Needscore = product.getScore();
		BigDecimal  restScore = enableScore.subtract(Needscore);
		PhiOrderinfo phiOrderInfo = orderEnty.getPhiOrderInfo();
		if(null ==phiOrderInfo){
			throw new ResourceNotFoundException(orderId);
		}
		if(null != phiOrderInfo.getReceiverName()){
			phiOrderInfo.setOrderStatus(Long.valueOf(2));//若是实物类支付成功状态设为待发货
			
		}else{
			phiOrderInfo.setOrderStatus(Long.valueOf(4));//若是虚拟类则是已完成
			if("0".equals(orderEnty.getProductClass())){
				//若是优惠券
				if(null!=phiOrderInfo.getCpnsWayId()){
					PhiCouponsDetail couponsDetial = phiCouponsDetailDao.findPhiCouponsDetailById(phiOrderInfo.getCpnsWayId());
					couponsDetial.setMemberId(phiMember);
					couponsDetial.setExchangeStatus("1");//1表示已兑换
					phiCouponsDetailDao.saveOrUpdatePhiCouponsDetail(couponsDetial);
					this.postHasPhiCouponsDetailToChengShang(couponsDetial);//推送已兑换的优惠卷给辰商
					
				}
				
			}else if("3".equals(orderEnty.getProductClass())){
				if(null!=phiOrderInfo.getThirdId()){
					PhiThirdPartyCouponsDetail third = phiThirdPartyCouponsDetailDao.findPhiThirdPartyCouponsDetailById(phiOrderInfo.getThirdId());
					third.setPhiMember(phiMember);
					third.setExchangeStatus("1");//1表示已兑换
					phiThirdPartyCouponsDetailDao.saveOrUpdatePhiThirdPartyCouponsDetail(third);
					WanJiaJinFuUtil wjjf=new WanJiaJinFuUtil();
					WanJiaJinFuExchangeVO exchangeVO = wjjf.exchangePresent(phiMember.getTel(), third.getCoupCode(),"1");
				}
			}
		}
		phiMember.setEnableScore(restScore);
		orderEnty.setPhiMember(phiMember);
		phiOrderinfoDao.saveOrUpdatePhiOrderinfo(phiOrderInfo);
		orderEnty.setPhiOrderInfo(phiOrderInfo);
		phiOrderDao.saveOrUpdatePhiOrder(orderEnty);
		phiMemberDao.saveOrUpdatePhiMember(phiMember);
		PhiScoreFlow flowentity=new PhiScoreFlow();
		flowentity.setScoreAction("积分兑换");
		flowentity.setCreateTime(new Date());
		flowentity.setMemberId(phiMember.getId());
		flowentity.setOrderCode(orderEnty.getOrderCode());
		flowentity.setScoreType("consume");
		flowentity.setSourcePlatform("phicomm_scoremall");
		flowentity.setScore(Needscore);
		orderEnty.setPhiOrderInfo(phiOrderInfo);
		orderEnty.setStatus((phiOrderInfo.getOrderStatus()).toString());
		orderEnty.setIsdelete("0");
		phiScoreFlowService.savePhiScoreFlow(flowentity);
		//积分兑换 ，积分发生变化，给商城推送会员信息
		phiMemberService.pullMemberInfoToChenShang(phiMember,"积分兑换|");
	}
	
	@Override
	public void setPhiOrderStatusDtoByOrderNo(String orderNo) {
		PhiOrder entity = phiOrderDao.findPhiOrderByOrderCode(orderNo);
		if(entity==null){
			throw new BusinessRuntimeException("订单不存在", "-1");
		}
		entity.setStatus("5");
		entity.setIsdelete("0");
		phiOrderDao.saveOrUpdatePhiOrder(entity);
	}
	
	@SuppressWarnings("unused")
	@Override
	public void editOrderAfterPayByOrderNo(String orderNo,String payType,String transactionId) {
		PhiOrder  orderEnty = phiOrderDao.findPhiOrderByOrderCode(orderNo);
		BigDecimal nullmoney = new BigDecimal("0");
		if(null==orderEnty){
			throw new BusinessRuntimeException("订单不存在", "-1");
		}
		
		// 设置支付方式
		orderEnty.setPayType(payType);//支付方式
		orderEnty.setIsclose("1");//已支付
		orderEnty.setTransactionId(transactionId);
		orderEnty.setPayTime(new Date());
		//获取商品信息
		Long productId = orderEnty.getProductId();
		if(productId==null){
			throw new BusinessRuntimeException("订单不存在", "-1");
		}
		PhiProduct product = phiProductDao.findPhiProductById(productId);
//		PhiProduct product = phiProductDao.findPhiProductByIdLock(productId);
		if(null == product){
			throw new BusinessRuntimeException("商品不存在", "-1");
		}
		BigDecimal productCount = new BigDecimal("1");
		//判断商品库存
		if(product.getProductStock().subtract(productCount).intValue()<0){
			throw new BusinessRuntimeException("库存不足", "-1");
		}
		//获取用户信息
		PhiMember phiMember = orderEnty.getPhiMember();
		if(null == phiMember){
			throw new BusinessRuntimeException("会员不存在", "-1");
		}
		BigDecimal	enableScore = phiMember.getEnableScore();
		BigDecimal  Needscore = product.getScore();
		BigDecimal  restScore = new BigDecimal(0);
		if(null!=enableScore&&null!=Needscore){
			restScore = enableScore.subtract(Needscore);
			if(enableScore.compareTo(Needscore)<0){
				throw new BusinessRuntimeException("积分不足", "-1");
			}
		}
		//用户可用积分为空，商品兑换积分不为空
		if(null==enableScore&&null!=Needscore){
			throw new BusinessRuntimeException("积分不足", "-1");
		}
		PhiOrderinfo phiOrderInfo = orderEnty.getPhiOrderInfo();
		if(null ==phiOrderInfo){
			throw new BusinessRuntimeException("订单不存在", "-1");
		}
		if(null != phiOrderInfo.getReceiverName()){
			
			phiOrderInfo.setOrderStatus(Long.valueOf(2));//若是实物类支付成功状态设为待发货
			orderEnty.setStatus("2");//若是实物类支付成功状态设为待发货
			//辰商接口调用
//			JsonObject data = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"regions", "regions", "{}");
			JsonObject giveDate=new JsonObject();
			if(null==orderEnty.getPayType()||"".equals(orderEnty.getPayType())){
				giveDate.addProperty("paytype", "integral");
			}else{
				switch(orderEnty.getPayType()){
				case "1" :giveDate.addProperty("paytype", "wxpay");
		        	break;
				case "2" :giveDate.addProperty("paytype", "alipay");
	        		break;
				case "3" :giveDate.addProperty("paytype", "jdpay");
	        		break;
				case "4" :giveDate.addProperty("paytype", "chinapay");
        			break;
				case "5" :giveDate.addProperty("paytype", "COD");
        			break;
				 default:
					 giveDate.addProperty("paytype", "integral");
			        	break;
			   }
			}
			giveDate.addProperty("integral_order_id", orderEnty.getOrderCode());
			giveDate.addProperty("uid", phiMember.getUId());
			giveDate.addProperty("nums", phiOrderInfo.getProductCount());
			giveDate.addProperty("payed", phiOrderInfo.getMoney());
			giveDate.addProperty("order_total", phiOrderInfo.getMarketPrice());
			giveDate.addProperty("pmt_order","");
			giveDate.addProperty("integral", phiOrderInfo.getScore());
			giveDate.addProperty("address_id",phiOrderInfo.getAddressId());
			giveDate.addProperty("express","1");
			giveDate.addProperty("paytime", TimeStampUtil.getLongTimeStamp(phiOrderInfo.getPaytime()));
			
			giveDate.addProperty("cost_freight", phiOrderInfo.getLogisticMoney());
			giveDate.addProperty("invoice", false);
			giveDate.addProperty("invoice_title","");
			giveDate.addProperty("invoice_addon", "");
			Long goodsId = product.getGoodsId();
			String items="[{\"goods_id\":'"+goodsId+"',\"nums\":'"+phiOrderInfo.getProductCount()+"',\"price\":'"+phiOrderInfo.getMarketPrice()+"',\"buy_price\":'"+phiOrderInfo.getMoney()+"',\"integral\":'"+phiOrderInfo.getScore()+"'}]";
            JsonElement ss = new JsonParser().parse(items);
//			String items="[{'product_id':'"+productId+"','nums':'"+phiOrderInfo.getProductCount()+"','price':'"+phiOrderInfo.getMarketPrice()+"','buy_price':'"+phiOrderInfo.getMoney()+"','integral':'"+phiOrderInfo.getScore()+"'}]";
//			String items="[{\"product_id\":\""+productId+"\",\"nums\":\""+phiOrderInfo.getProductCount()+"\",\"price\":\""+phiOrderInfo.getMarketPrice()+"\",\"buy_price\":\""+phiOrderInfo.getMoney()+"\",\"integral\":\""+phiOrderInfo.getScore()+"\"}]";
			giveDate.add("items", ss);
			JsonObject sss=PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"order", "order", new Gson().toJson(giveDate));
			JSONObject dataJson = JSONObject.fromObject(sss.toString()); 
			String isOk = dataJson.getString("status");
			/********同步订单信息给辰商*********/
       	 	try{
				CmdInterfaceReceiveMessageDto entityDto=new CmdInterfaceReceiveMessageDto();
				entityDto.setBusiTime(new Date());
				entityDto.setCode("order");
				entityDto.setCreateTime(new Date());
				entityDto.setRequestBody(giveDate.toString());
				entityDto.setResult(isOk.equals("true")?200:0);
				entityDto.setMsg(dataJson.getString("message"));
				entityDto.setResponseBody(new Gson().toJson(sss.toString()));
				cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			if("0".equals(orderEnty.getProductClass())){
				//如果购买的是优惠券
				if(null!=product.getCoupWayId()){
					PhiCoupons coupons = phiCouponsDao.findPhiCouponsById(product.getCoupWayId());
//					PhiCoupons coupons = phiCouponsDao.findPhiCouponsByIdLock(product.getCoupWayId());
					if(null!=coupons){
						List<PhiCouponsDetail>	couponslist = phiCouponsDetailDao.getUseCouponsDetailsById(coupons.getCpnsWayId());
						if(null!=couponslist&&couponslist.size()>0){
							PhiCouponsDetail couponsDetial = couponslist.get(0);
							phiOrderInfo.setCoupCode(couponslist.get(0).getCoupCode());
								//优惠券不需要付款
								couponsDetial.setMemberId(phiMember);
								couponsDetial.setExchangeStatus("1");//1表示已兑换
								phiCouponsDetailDao.saveOrUpdatePhiCouponsDetail(couponsDetial);
								this.postHasPhiCouponsDetailToChengShang(couponsDetial);//推送已兑换的优惠卷给辰商
								phiOrderInfo.setCoupType("优惠券");//直接存储优惠券的类型名称
								phiOrderInfo.setCpnsWayId(couponsDetial.getCpnsId());
						}else{
							throw new BusinessRuntimeException("优惠券不足", "-1");
						}
					}else{
						throw new BusinessRuntimeException("优惠券不足", "-1");
					}
				}
				
			}else if("3".equals(orderEnty.getProductClass())){
				if(null!=phiOrderInfo.getThirdId()){//如果购买的是第三方券
					
					PhiThirdPartyCoupons thirdPart = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsByCoupId(product.getThirdId());
//					PhiThirdPartyCoupons thirdPart = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsByCoupIdLock(product.getThirdId());
					if(null!=thirdPart){
						List <PhiThirdPartyCouponsDetail> thirdDetials = phiThirdPartyCouponsDetailDao.findCanUseThirdPartyCouponsDetail(thirdPart.getCpnsId());
						if(null!=thirdDetials&&thirdDetials.size()>0){
							PhiThirdPartyCouponsDetail third = thirdDetials.get(0);
							phiOrderInfo.setCoupCode(thirdDetials.get(0).getCoupCode());
							third.setPhiMember(phiMember);
							third.setExchangeStatus("1");//1表示已兑换
							phiThirdPartyCouponsDetailDao.saveOrUpdatePhiThirdPartyCouponsDetail(third);
							WanJiaJinFuUtil wjjf=new WanJiaJinFuUtil();
							WanJiaJinFuExchangeVO exchangeVO = wjjf.exchangePresent(phiMember.getTel(), thirdDetials.get(0).getCoupCode(),"1");
							phiOrderInfo.setCoupType(thirdPart.getCpnsType());//2代表第三方券
							phiOrderInfo.setThirdId(third.getId());
						}
						else{
							throw new BusinessRuntimeException("第三方券不足", "-1");
						}
					}else{
						throw new BusinessRuntimeException("第三方券不足", "-1");
					}
					
				}
				
			}
			phiOrderInfo.setOrderStatus(Long.valueOf(4));//若是虚拟类则是已完成
			orderEnty.setStatus("4");//若是虚拟类则是已完成
		}
		//减商品库存
		BigDecimal newProductStock = product.getProductStock().subtract(productCount);
		//商品兑换数量+1
//		product.setExchangQuantity(product.getExchangQuantity()+1);
		product.setProductStock(newProductStock);
		phiMember.setEnableScore(restScore);
		orderEnty.setPhiMember(phiMember);
		PhiScoreFlow flowentity=new PhiScoreFlow();
		flowentity.setScoreAction("积分兑换");
		flowentity.setCreateTime(new Date());
		flowentity.setMemberId(phiMember.getId());
		flowentity.setOrderCode(orderEnty.getOrderCode());
		flowentity.setScoreType("consume");
		flowentity.setSourcePlatform("phicomm_scoremall");
		flowentity.setScore(Needscore);
		orderEnty.setIsdelete("0");
		phiScoreFlowService.savePhiScoreFlow(flowentity);
		phiOrderinfoDao.saveOrUpdatePhiOrderinfo(phiOrderInfo);
		orderEnty.setPhiOrderInfo(phiOrderInfo);
		phiOrderDao.saveOrUpdatePhiOrder(orderEnty);
		phiProductDao.saveOrUpdatePhiProduct(product);
		phiMemberDao.saveOrUpdatePhiMember(phiMember);
		//积分兑换 ，积分发生变化，给商城推送会员信息
		phiMemberService.pullMemberInfoToChenShang(phiMember,"积分兑换|");
	}

	@Override
	public PhiOrderDto findPhiOrderinfoByOrderNo(String orderNo){
		PhiOrder  orderEnty = phiOrderDao.findPhiOrderByOrderCode(orderNo);
		if(null==orderEnty){
			throw new BusinessRuntimeException("订单不存在", "-1");
		}
		PhiOrderDto entityDto = BeanCopy.getInstance().convert(orderEnty, PhiOrderDto.class);
		entityDto.setMoney(orderEnty.getPhiOrderInfo().getMoney());
		return entityDto;
	}

	@Override
	public PhiOrder findPhiOrderById(Long id) {
		return phiOrderDao.findPhiOrderById(id);
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
    private JsonObject postHasPhiCouponsDetailToChengShang(PhiCouponsDetail couponsDetial) {   	
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
			entityDto.setCode("bindcoupon-兑换");
			entityDto.setCreateTime(new Date());
			entityDto.setRequestBody(jo.toString());
			entityDto.setResult(isOk.equals("true")?200:0);
			entityDto.setMsg(dataJson.getString("message"));
			entityDto.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDto);
		}catch(Exception e){
			e.printStackTrace();
		}
      return sss;              
	}
    
    public static int compare_date_(String DATE1, String DATE2) {              
   	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
		            Date dt1 = df.parse(DATE1);
		            Date dt2 = df.parse(DATE2);
		            if (dt1.getTime() > dt2.getTime()) {
		                System.out.println("dt1 在dt2前");
		                return 1;
		            } else if (dt1.getTime() < dt2.getTime()) {
		                System.out.println("dt1在dt2后");
		                return -1;
		            } else {
		                return 0;
		            }
       } catch (Exception exception) {
           exception.printStackTrace();
       }
       return 0;
   }

	@Override
	public Map<String, Object> conversionParmas(Map<String, Object> parmas) {
		// TODO Auto-generated method stub
		return parmas;
	}

	@Override
	public List<Object[]> conversionResults(List<Object[]> resultsList,
			Map<String, Object> parmas) {
		if(null != resultsList && resultsList.size() > 0 ){
			for (Object[] objects : resultsList) {
				if (null != objects[2]) {//是否为会员转换
					if("1".equals(objects[2].toString())){
						objects[2] = "是";
					}else {
						objects[2] = "否";
					}
					
				}
				if (null != objects[6]) {//处理时间
					objects[6] = objects[6].toString().replace(".0", "");
					
				}
			}
		}
		return resultsList;
	}
	
}
