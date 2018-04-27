package com.huatek.busi.service.impl.phicom.product;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDao;
import com.huatek.busi.dao.phicom.coupons.PhiCouponsDetailDao;
import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDao;
import com.huatek.busi.dao.phicom.coupons.PhiThirdPartyCouponsDetailDao;
import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dao.phicom.member.PhiMemberGradeDao;
import com.huatek.busi.dao.phicom.product.PhiProductDao;
import com.huatek.busi.dao.phicom.product.PhiProductTypeDao;
import com.huatek.busi.dto.phicom.product.PhiProductDto;
import com.huatek.busi.dto.phicom.product.PhiProductTypeDto;
import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.busi.model.phicom.coupons.PhiCouponsDetail;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCoupons;
import com.huatek.busi.model.phicom.coupons.PhiThirdPartyCouponsDetail;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.phicom.product.PhiProductType;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.product.PhiProductService;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.dto.CmdInterfaceReceiveMessageDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.cmd.service.CmdInterfaceReceiveMessageService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("phiProductServiceImpl")                
@Transactional
public class PhiProductServiceImpl implements PhiProductService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiProductServiceImpl.class);
	
	@Autowired
	PhiProductDao phiProductDao;
    
	@Autowired
	PhiProductTypeDao phiProductTypeDao;
	
	@Autowired
	PhiMemberGradeDao phiMemberGradeDao;
	
	@Autowired
	PhiCouponsDao  phiCouponsDao;
	
	@Autowired
	PhiThirdPartyCouponsDao phiThirdPartyCouponsDao;
	
	@Autowired
	private CmdFileMangerService cmdFileMangerService;
	
	@Autowired
	PhiMemberDao phiMemberDao; 
	
	@Autowired
	PhiCouponsDetailDao phiCouponsDetailDao;
	
	@Autowired
	PhiThirdPartyCouponsDetailDao phiThirdPartyCouponsDetailDao;
	
	@Autowired
	CmdInterfaceReceiveMessageService cmdInterfaceReceiveMessageService;
	@Override
	public void savePhiProductDto(PhiProductDto entityDto)  {
		log.debug("save phiProductDto @" + entityDto);
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		entityDto.setCreatorId(userInfo.getId());
		entityDto.setProductCode("Prduct"+new Date().getTime());
	    entityDto.setIsShop("0"); 
	    entityDto.setProductStatus("0");
	    entityDto.setWinnerStatus("1");//winnerStatus 1 未开奖2已开奖
		//根据页面传进来的值设置保存的值信息
		PhiProduct  entity = BeanCopy.getInstance().convert(entityDto, PhiProduct.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//调用辰商的接口，同步商品信息
		try{
			JsonObject  jsonObject = postGoodsAddtoChengShang(entity);			 
		//	JSONObject object = JSONObject.fromObject(jsonObject);                  
			System.out.print(jsonObject);
			if(jsonObject != null){
			JsonObject data = jsonObject.get("data").getAsJsonObject();
			Boolean status = jsonObject.get("status").getAsBoolean();
				if(status == true){
					entity.setGoodsId(data.get("goods_id").getAsLong());
					//进行持久化保存
				    log.debug("saved entityDto id is @" + entity.getId());
					phiProductDao.persistentPhiProduct(entity);
				}
				
			}
		}catch(Exception e){
		  e.printStackTrace();
		}
		    log.debug("saved entityDto id is @" + entity.getId());
			phiProductDao.persistentPhiProduct(entity);

		
		
	}
	
	
	
	
	//此方法用来调用接口
    private JsonObject postGoodsAddtoChengShang(PhiProduct  entity) {   	      
      JsonObject jo = new JsonObject();
      jo.addProperty("bn", entity.getProductCode());
      jo.addProperty("name", entity.getProductName());
      jo.addProperty("brief", entity.getRemark());
      if(null!=entity){
 		 if(null == entity.getMoney()){
 			 jo.addProperty("price", 0);
 		 }else{
 			 jo.addProperty("price", entity.getMoney());
 		 }
 	 }

      jo.addProperty("mktprice", entity.getMarketPrice());
      List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(entity.getProductImageHead());
      if(imsges.size()>0){
    	  jo.addProperty("image", PropertyUtil.getConfigValue("img_url")+imsges.get(0).getFilePath());     
      }else {
    	 throw new BusinessRuntimeException("商品主图不能为空", "-1");
	  }	  
      JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"goodsadd", "goods_add",jo.getAsJsonObject().toString());	      
         String isOk = sss.get("status").getAsString();
      try{
			CmdInterfaceReceiveMessageDto entityDtoCmd=new CmdInterfaceReceiveMessageDto();
			entityDtoCmd.setBusiTime(new Date());
			entityDtoCmd.setCode("goods_add");
			entityDtoCmd.setCreateTime(new Date());
			entityDtoCmd.setRequestBody(jo.toString());
			entityDtoCmd.setResult(isOk.equals("true")?200:0);
			entityDtoCmd.setMsg(sss.get("message").getAsString());
			entityDtoCmd.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDtoCmd);
		}catch(Exception e){
			e.printStackTrace();
		}		
      return sss;              
	}
	
   
	
	
	
	@Override
	public PhiProductDto getPhiProductDtoById(Long id) {
		log.debug("get phiProduct by id@" + id);
		PhiProduct entity = phiProductDao.findPhiProductById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}			 
		
		PhiProductDto entityDto = BeanCopy.getInstance().convert(entity, PhiProductDto.class);
		
		return entityDto;
	}
	
	@Override
	public void updatePhiProduct(Long id, PhiProductDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiProduct entity = phiProductDao.findPhiProductById(id);
		 entity.setProductStatus("0");//修改后将商品设置为待审核状态;
		BeanCopy.getInstance().mapIgnoreNull(entityDto, entity);
		//进行持久化保存
		phiProductDao.persistentPhiProduct(entity);
		//调用辰商的接口
		try{
			JsonObject jsonObject = postGoodsUpdateToChengShang(entity);		       
		}catch(Exception e){
			 e.printStackTrace();
		}
		
		
	}
	
	//此方法用来调用修改商品的接口
    private JsonObject postGoodsUpdateToChengShang(PhiProduct  entity) {   	
    	JsonObject jo = new JsonObject();
    	 jo.addProperty("product_id", entity.getId());
    	 jo.addProperty("bn", entity.getProductCode());
    	 jo.addProperty("name", entity.getProductName());
    	 jo.addProperty("brief", entity.getRemark());
    	 List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(entity.getProductImageHead());
    	 if(imsges!= null){
    		 jo.addProperty("image", PropertyUtil.getConfigValue("img_url")+imsges.get(0).getFilePath());
    	 }    	 
    	 if(null!=entity){
     		 if(null == entity.getMoney()){
     			 jo.addProperty("price", 0);
     		 }else{
     			 jo.addProperty("price", entity.getMoney());
     		 }
     	 }
    	 jo.addProperty("mktprice", entity.getMarketPrice()); 	
      JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"goodsmodify", "goods_modify",jo.getAsJsonObject().toString());	
      String isOk = sss.get("status").getAsString();
      try{
			CmdInterfaceReceiveMessageDto entityDtoCmd=new CmdInterfaceReceiveMessageDto();
			entityDtoCmd.setBusiTime(new Date());
			entityDtoCmd.setCode("goods_modify");
			entityDtoCmd.setCreateTime(new Date());
			entityDtoCmd.setRequestBody(jo.toString());
			entityDtoCmd.setResult(isOk.equals("true")?200:0);
			entityDtoCmd.setMsg(sss.get("message").getAsString());
			entityDtoCmd.setResponseBody(new Gson().toJson(sss.toString()));
			cmdInterfaceReceiveMessageService.saveCmdInterfaceReceiveMessageDto(entityDtoCmd);
		}catch(Exception e){
			e.printStackTrace();
		}
      return sss;              
	}
	
	
	
	
	@Override
	public void deletePhiProduct(Long id) {
		log.debug("delete phiProduct by id@" + id);
		beforeRemove(id);
		PhiProduct entity = phiProductDao.findPhiProductById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiProductDao.deletePhiProduct(entity);
		
         try{
        	 JsonObject jsonObject = postGoodsDeleteToChengShang(entity);	 
         }catch(Exception e){
        	      e.printStackTrace();        	      
        		 }	
		
	}
	//此方法用来调用删除商品的接口
    private JsonObject postGoodsDeleteToChengShang(PhiProduct  entity) {   	
    	JsonObject jo = new JsonObject();
    	 jo.addProperty("product_id", entity.getId());	 	
      JsonObject sss = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"goodsdelete", "goods_delete",jo.getAsJsonObject().toString());	      
      return sss;              
	}
	
	@Override
	public DataPage<PhiProductDto> getAllPhiProductPage(QueryPage queryPage) {
		DataPage<PhiProduct> dataPage = phiProductDao.getAllPhiProduct(queryPage);
		DataPage<PhiProductDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiProductDto.class);
		if(null!=dataPage.getContent()){
			for(int i=0;i<dataPage.getContent().size();i++){
				PhiProduct entity = dataPage.getContent().get(i);
				List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(entity.getProductImageHead());
				if(null!=imsges&&imsges.size()>0){
					datPageDto.getContent().get(i).setProductImageHead(imsges.get(0).getFilePath());
					datPageDto.getContent().get(i).setProductCompressImageHead(imsges.get(0).getCompressPath());
				}
			}
		}
		
			
		return datPageDto;
	}
	
	@Override
	public List<PhiProductDto> getAllPhiProductDto() {
		List<PhiProduct> entityList = phiProductDao.findAllPhiProduct();
		List<PhiProductDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiProductDto.class);
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
	* @param    phiProductDto
	* @param    phiProduct
	* @return  void    
	* @
	*/
	private void beforeSave(PhiProductDto entityDto, PhiProduct entity) {
		if(StringUtils.isNotBlank(entityDto.getProductName())){
			if (entityDto.getProductName().length() > 30) {
				throw new BusinessRuntimeException("产品名称不能大于30个字符", "-1");
			}
			/*名称：手动输入，必填，该名称在同一父级下唯一，最多20个字符。*/
			 List<PhiProduct> list1=phiProductDao.findPhiProductByName(entityDto.getProductName());
			 if(list1!=null&&list1.size()>0){
			     throw new BusinessRuntimeException("产品名称【"+entityDto.getProductName()+"】已存在", "-1");
			 }
    	} else {
			throw new BusinessRuntimeException("产品名称不能为空", "-1");
		}
		//优惠券库存判断
		String productStock = entityDto.getProductStock();
		Long coupWayId = entityDto.getCoupWayId();
		if(StringUtils.isNotBlank(productStock) && null!=coupWayId){
			List<PhiCouponsDetail> canUseCouponslist = phiCouponsDetailDao.getUseCouponsDetailsById(coupWayId);//获取可使用优惠券
			if(null!=canUseCouponslist){
				int count = canUseCouponslist.size();
				if(Integer.parseInt(productStock)>count){
					throw new BusinessRuntimeException("库存设置不能大于当前优惠券数量", "-1");
				}
			}else{
				throw new BusinessRuntimeException("此优惠券无库存", "-1");
			}
		}
		//第三方券判断
		String thirdId = entityDto.getThirdId();
		if(StringUtils.isNotBlank(productStock) && StringUtils.isNotBlank(thirdId)){
			List <PhiThirdPartyCouponsDetail> thirdDetials = phiThirdPartyCouponsDetailDao.findCanUseThirdPartyCouponsDetail(thirdId);
			if(null!=thirdDetials){
				int thirdCount = thirdDetials.size();
				if(Integer.parseInt(productStock)>thirdCount){
					throw new BusinessRuntimeException("库存设置不能大于当前第三方券数量", "-1");
				}
			}else{
				throw new BusinessRuntimeException("此第三方券无库存", "-1");
			}
		}
	}





	//商品审核
	@Override
	public void updateProductStauts(Long id,String status) {
		
		PhiProduct product = phiProductDao.findPhiProductById(id);
		 
		if(status.equals("1")){
			if(product.getProductStatus().equals("1")){
				product.setIsShop("1");;
				product.setUpTime(new Date());
			}else {
				throw new BusinessRuntimeException("请审核商品", "-1");
			}
			
		}
		if(status.equals("2")){
			if(product.getIsShop().equals("1")){
				product.setIsShop("2");;
				product.setDownTime(new Date());
			}else {
				throw new BusinessRuntimeException("商品未上架", "-1");
			}
			
		}
	   phiProductDao.persistentPhiProduct(product);
		
	}


	@Override
	public void submitProductCertification(Long id) {		
	       PhiProduct product = phiProductDao.findPhiProductById(id);	
	          product.setProductStatus("0");
	       phiProductDao.saveOrUpdatePhiProduct(product);
		   
	}
	//批量更新商品
	@Override
	public void updatePhiProductStatus(String val,Long... ids) {
		List<PhiProduct> phiPproductlist = new ArrayList();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		  String currentDate = sdf.format(new Date());
		if(val.equals("1")){
			for(Long id : ids){
				  PhiProduct phiProduct = phiProductDao.findPhiProductById(id);	
				  Date upDate = phiProduct.getUpTime();
				  if(upDate != null){
					  String updatesdf = sdf.format(upDate);
					     int x = compare_date(currentDate ,updatesdf);
					     if(x == -1){
					    	 phiProduct.setIsShop("1");//审核完直接上架；
					     } 
				  }			     
				  phiProduct.setProductStatus("1");
				  phiPproductlist.add(phiProduct);
			    }
			}else if(val.equals("2")){
				for(Long id : ids){
					PhiProduct phiProduct = phiProductDao.findPhiProductById(id);
					phiProduct.setProductStatus("2");
				    phiPproductlist.add(phiProduct);
				}
			}	
		phiProductDao.batchUpdate(phiPproductlist);
		
	}
	@Override
	public List<Map<Long, String>> getAllTypeName() {
		
		List<PhiProductType> productype = phiProductTypeDao.findAllPhiProductType();
		List<PhiProductTypeDto> productypeDto = BeanCopy.getInstance().convertList(productype, PhiProductTypeDto.class);
		//创建一个list用来存储商品分类的名称
		List<Map<Long,String>>  typeNamelist = new ArrayList<Map<Long,String>>();
		   for(int i= 0;i<productypeDto.size();i++){
			   Map<Long,String> map =new HashMap<Long,String>();
			   map.put(productypeDto.get(i).getId(), productypeDto.get(i).getTypeName());
			   typeNamelist.add(map);
			   
		   }
		   
		   return typeNamelist;
	
	}
	@Override
	public List<Long> getAllCouponsId() {	
	   List<PhiCoupons> phiCouponslist = 	phiCouponsDao.findAllPhiCoupons();
		  List<Long>  idList = new  ArrayList<Long>();
		    for(PhiCoupons phiCoupons: phiCouponslist){
		    	idList.add(phiCoupons.getCpnsWayId());
		    }		
		
		return idList;
	}
	@Override
	public String getProductValidDateById(Long id) {
	      PhiCoupons   phiCoupons = phiCouponsDao.findPhiCouponsById(id);
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	       String date = sdf.format(phiCoupons.getCpnsValid());	
		return date;
	}




	@Override
	public void productAutoUptoShop() {
		 
		List<PhiProduct> phiProducts = phiProductDao.findAllPhiProduct();
		for(PhiProduct phiProduct : phiProducts){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		 //当前时间
			String currentDate = sdf.format(new Date());
		 //上架时间
			Date upDates= phiProduct.getUpTime();			
			if(upDates != null){
				String  upDate  = sdf.format(upDates);
				int x = compare_date(currentDate ,upDate);
			    if(x== 1){
			    	if(phiProduct.getProductStatus().equals("1")){
			    		phiProduct.setIsShop("1");
				    	phiProductDao.persistentPhiProduct(phiProduct);
			    	}		    	
			    }
			}
	       
		}
		 System.out.print("=============商品上架================");
	}




	@Override
	public PhiProductDto getPhiProductDetialDtoById(Long id, Long memberId) {
		log.debug("get phiProduct by id@" + id);
		PhiProduct entity = phiProductDao.findPhiProductById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}			 
		PhiMember member = phiMemberDao.findPhiMemberById(memberId);
		if(null == member){
			throw new ResourceNotFoundException(memberId);
		}
		PhiProductDto entityDto = BeanCopy.getInstance().convert(entity, PhiProductDto.class);
		if(null!=entityDto){
			BigDecimal num = new BigDecimal("0");//0>库存量
			if(((num.compareTo(entity.getProductStock()))==1)||((num.compareTo(entity.getProductStock()))==0)){
				entityDto.setIsused("0");//0表示不能兑换
			}
			else if((member.getMemberGradeCode().compareTo(entity.getNeedGrade())<0)){
				entityDto.setIsused("0");//0表示不能兑换
			}else{
				entityDto.setIsused("1");//1表示可以兑换
			}
		}
		return entityDto;
	}
	
     public static int compare_date(String DATE1, String DATE2) {              
    	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
		            Date dt1 = df.parse(DATE1);
		            Date dt2 = df.parse(DATE2);
		            if (dt1.getTime() > dt2.getTime()) {
//		                System.out.println("dt1 在dt2前");
		                return 1;
		            } else if (dt1.getTime() < dt2.getTime()) {
//		                System.out.println("dt1在dt2后");
		                return -1;
		            } else {
		                return 0;
		            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
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
	public PhiProductDto getProductDetailForApp(Long id ,Long memberId) {	
		 PhiProduct entity = phiProductDao.findPhiProductById(id);
		     
		 PhiProductDto phiProductDto = BeanCopy.getInstance().convert(entity, PhiProductDto.class);
		  Date downTime = entity.getDownTime();
		  if(downTime != null){
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			    String sysdate1 = sdf.format(new Date());
			    try {
					Date sysDate = sdf.parse(sysdate1);
				    int ss= downTime.compareTo(sysDate);
					if(ss==0||ss == 1){
					   phiProductDto.setDownRemind(true);	
					}else {
					   phiProductDto.setDownRemind(false);
					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		  }
		  	    		   	    
		try{
			//商品主图 
	        List<CmdFileMangerDto> productImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(entity.getProductImageHead());
	          
	                List<String> productImageHeadList = new ArrayList<String>();      
	                List<String> productImageMiniHeadList = new ArrayList<String>();
	                for(CmdFileMangerDto cmdFileMangerDto : productImageHeads){
	                	    String imgStr = "";
	                	   imgStr = cmdFileMangerDto.getFilePath();
	                	   String imgMiniStr = cmdFileMangerDto.getCompressPath();
	                	    productImageHeadList.add(imgStr);
	                	    productImageMiniHeadList.add(imgMiniStr);
	                   }	                
	                 phiProductDto.setProductImageHeadsList(productImageHeadList);
	                 phiProductDto.setProcuctCompressPathImageList(productImageMiniHeadList);      
	            //商品详情图App
	           List<CmdFileMangerDto> productDetailImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImageApp());       
	                 List<String> productDetailImageHeadList = new ArrayList<String>();
	                 List<String> productDetailMiniImageHeadList = new ArrayList<String>();
	                   for(CmdFileMangerDto cmdFileMangerDto :productDetailImageHeads){
	                	    String imgDetailStr = "";
	                	   imgDetailStr =   cmdFileMangerDto.getFilePath();
	                	   String imgDetailMiniStr = cmdFileMangerDto.getCompressPath(); 
	                	   productDetailImageHeadList.add(imgDetailStr);
	                	   productDetailMiniImageHeadList.add(imgDetailMiniStr);
	                   }
	    	           phiProductDto.setProcuctDetailImageList(productDetailImageHeadList);
	    	           phiProductDto.setProcuctCompressDetailAppImageList(productDetailMiniImageHeadList);
	    	      //商品详情图Pc
	           List<CmdFileMangerDto> productDetailPcImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImagePc());       
	                 List<String> productDetailPcImageHeadList = new ArrayList<String>();
	                   for(CmdFileMangerDto cmdFileMangerDto :productDetailPcImageHeads){
	                	    String imgPcDetailStr = "";
	                	    imgPcDetailStr =   cmdFileMangerDto.getFilePath();	      
	                	    productDetailPcImageHeadList.add(imgPcDetailStr);
	                   }
	    	           phiProductDto.setProcuctDetailPcImageList(productDetailPcImageHeadList);
	           
	    	         //是否可兑换isUsed;(判断等级)
		        	    PhiMember member = phiMemberDao.findPhiMemberById(memberId);//当前用户的memberId;
		        	    if(null == member.getMemberGradeCode()){
		        	    	phiProductDto.setIsused("0");//0表示不能兑换
		        	    }else if(((member.getPhiMembergrade().getId()).toString().compareTo(entity.getNeedGrade())<0)){
		        	    	phiProductDto.setIsused("0");//0表示不能兑换
		        	    }else{
		        	    	phiProductDto.setIsused("1");//1表示可以兑换
		        	    }
	        	    
//	   	   			BigDecimal num = new BigDecimal("0");//0>库存量
//	   	   			if(((num.compareTo(entity.getProductStock()))==1)||((num.compareTo(entity.getProductStock()))==0)){
//	   	   				phiProductDto.setIsused("0");//0表示不能兑换
//	   	   			}else if(null == member.getMemberGradeCode()){
//	   	   				phiProductDto.setIsused("0");//0表示不能兑换
//	   	   			}else if((member.getMemberGradeCode().compareTo(entity.getNeedGrade())<0)){
//	   	   				phiProductDto.setIsused("0");//0表示不能兑换
//	   	   			}else{
//	   	   				phiProductDto.setIsused("1");//1表示可以兑换
//	   	   			}
//	   	   	        //判断积分是否不足；
//	    	   		   BigDecimal enableScore = member.getEnableScore();
//	    	   		   BigDecimal ProductScore = entity.getScore();
//	    	   		   if(enableScore != null && ProductScore != null){
//	    	   			  int ss =  enableScore.compareTo(ProductScore);
//	    	   			   if(ss == -1){//会员可用积分<商品积分
//	    	   				   phiProductDto.setIsused("0");
//	    	   			   }else{//会员可用积分>=商品积分
//	    	   				   phiProductDto.setIsused("1");
//	    	   			   }  	   			     	   			      	   			   
//	    	   		   }			
	              //今日是否开奖todayisOpen
	   	   		 //开奖时间
	   	   		 Date today = new Date();
	   	   	     Date openDate = entity.getWinnerTime();
	   	   	     String winnerStatus = entity.getWinnerStatus();
	   	   	     if(openDate != null && winnerStatus !=null){
	   	   	     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 	      	 String openString =   df.format(openDate);
	 	         String todayString = df.format(today); 
	 	         int x =compare_date_(openString,todayString);
	 	        if("2".equals(entity.getWinnerStatus())){
	 	        	   phiProductDto.setTodayisOpen("1");//已开奖；	 
	 	         }else{
	 	        	 if(x<1){//开奖时间是今天;
		 	               phiProductDto.setTodayisOpen("0");//今日开奖；	 
		 	         }
	 	         }
	 	        
	 	         
	   	   	     }
		}catch(Exception e){
			 e.printStackTrace();
			}
		
    	   /*//是否可兑换isUsed;
        	    PhiMember member = phiMemberDao.findPhiMemberById(memberId);//当前用户的memberId;
   	   			BigDecimal num = new BigDecimal("0");//0>库存量
   	   			if(((num.compareTo(entity.getProductStock()))==1)||((num.compareTo(entity.getProductStock()))==0)){
   	   				phiProductDto.setIsused("2");//0表示不能兑换
   	   			}
   	   			else if((member.getMemberGradeCode().compareTo(entity.getNeedGrade())<0)){
   	   				phiProductDto.setIsused("2");//0表示不能兑换
   	   			}else{
   	   				phiProductDto.setIsused("1");//1表示可以兑换
   	   			}*/
   	   		
		return phiProductDto;		
	}




	@Override
	public List<String> getAllThirdPartyId() {		
		List<PhiThirdPartyCoupons> phithirdPartyCouponList = phiThirdPartyCouponsDao.findAllPhiThirdPartyCoupons();
		List<String>  thirdPartIdList = new  ArrayList<String>();
	    for(PhiThirdPartyCoupons phiThirdCoupons: phithirdPartyCouponList){
	    	thirdPartIdList.add(phiThirdCoupons.getCpnsId());
	    }			
    	return thirdPartIdList;
		
	}




	@Override
	public String getThirdPartyValidDateById(String  id) {
	    	PhiThirdPartyCoupons   phiThirdPartyCoupons = phiThirdPartyCouponsDao.findPhiThirdPartyCouponsByCoupId(id);
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
	        String date = "";
	        if(phiThirdPartyCoupons.getCpnsValid() != null){
	        	 date = phiThirdPartyCoupons.getCpnsValid();		
	        }
	       //String date = phiThirdPartyCoupons.getCpnsValid();	
		return date;
	}




	@Override
	public List<PhiProductDto> getRecommendProductForApp() {
		//List<PhiProduct> phiProductsList = phiProductDao.getAllRecommedProductForApp();
		//推荐商品
		List<PhiProduct> phiProducts =	phiProductDao.getAllRecommedProductForApp();
		List<PhiProductDto> phiProductDtolist = BeanCopy.getInstance().convertList(phiProducts, PhiProductDto.class);
		  for(PhiProductDto phiProductDto : phiProductDtolist){
			  //商品主图
			  List<CmdFileMangerDto> productImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImageHead());	          
                  List<String> productImageHeadList = new ArrayList<String>();
                  List<String> productMiniImageHeadList = new ArrayList<String>();
               for(CmdFileMangerDto cmdFileMangerDto : productImageHeads){
              	    String imgStr = "";
              	   imgStr = cmdFileMangerDto.getFilePath();
              	   String imgMiniStr = cmdFileMangerDto.getCompressPath();
              	    productImageHeadList.add(imgStr);
              	    productMiniImageHeadList.add(imgMiniStr);
                 }	                
               phiProductDto.setProductImageHeadsList(productImageHeadList); 
               phiProductDto.setProcuctCompressPathImageList(productMiniImageHeadList);//主图压缩图片
               //商品详情图Pc
	           List<CmdFileMangerDto> productDetailPcImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImagePc());       
	                 List<String> productDetailPcImageHeadList = new ArrayList<String>();
	                   for(CmdFileMangerDto cmdFileMangerDto :productDetailPcImageHeads){
	                	    String imgPcDetailStr = "";
	                	    imgPcDetailStr =   cmdFileMangerDto.getFilePath();	      
	                	    productDetailPcImageHeadList.add(imgPcDetailStr);
	                   }
	    	    phiProductDto.setProcuctDetailPcImageList(productDetailPcImageHeadList); 
	           //商品详情图App
	           List<CmdFileMangerDto> productDetailImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImageApp());       
	                 List<String> productDetailImageHeadList = new ArrayList<String>();
	                 List<String> productDetailMiniImageHeadList = new ArrayList<String>();
	                 for(CmdFileMangerDto cmdFileMangerDto :productDetailImageHeads){
	                	    String imgDetailStr = "";
	                	   imgDetailStr =   cmdFileMangerDto.getFilePath();
	                	 String  imgMiniDetailStr = cmdFileMangerDto.getCompressPath();
	                	   productDetailImageHeadList.add(imgDetailStr);
	                	   productDetailMiniImageHeadList.add(imgMiniDetailStr);
	                   }
	             phiProductDto.setProcuctDetailImageList(productDetailImageHeadList);  
	             phiProductDto.setProcuctCompressDetailAppImageList(productDetailMiniImageHeadList);
	    	           
	    	           
		  }
		//按照销量排序
		List<Map<String, String>> maps = phiProductDao.getRecommendProductForApp();
		List<PhiProductDto> resultDtos = BeanCopy.getInstance().convertList(maps, PhiProductDto.class);
		List<PhiProductDto> phiProductDtos =BeanCopy.getInstance().convertList(resultDtos, PhiProductDto.class);
		               for(PhiProductDto phiProductDto:phiProductDtos){		            	   		            	   
		            	   //商品主图
		     			  List<CmdFileMangerDto> productImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImageHead());	          
		                       List<String> productImageHeadList = new ArrayList<String>();      
		                       List<String> productImageMiniHeadList = new ArrayList<String>();
		                       for(CmdFileMangerDto cmdFileMangerDto : productImageHeads){
		                   	      String imgStr = "";
		                   	     imgStr = cmdFileMangerDto.getFilePath();
		                   	     productImageHeadList.add(imgStr);
		                   	     String imgMiniStr = cmdFileMangerDto.getCompressPath();
		                     	 productImageMiniHeadList.add(imgMiniStr);
		                      }	                
		                    phiProductDto.setProductImageHeadsList(productImageHeadList);
		                    phiProductDto.setProcuctCompressPathImageList(productImageMiniHeadList);
		                    //商品详情图Pc
		     	           List<CmdFileMangerDto> productDetailPcImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImagePc());       
		     	                 List<String> productDetailPcImageHeadList = new ArrayList<String>();
		     	                   for(CmdFileMangerDto cmdFileMangerDto :productDetailPcImageHeads){
		     	                	    String imgPcDetailStr = "";
		     	                	    imgPcDetailStr =   cmdFileMangerDto.getFilePath();	      
		     	                	    productDetailPcImageHeadList.add(imgPcDetailStr);
		     	                   }
		     	    	    phiProductDto.setProcuctDetailPcImageList(productDetailPcImageHeadList); 
		     	           //商品详情图App
		     	           List<CmdFileMangerDto> productDetailImageHeads = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductDto.getProductImageApp());       
		     	                 List<String> productDetailImageHeadList = new ArrayList<String>();
		     	                 List<String> productDetailMiniImageHeadList = new ArrayList<String>();
		     	                   for(CmdFileMangerDto cmdFileMangerDto :productDetailImageHeads){
		     	                	    String imgDetailStr = "";
		     	                	   imgDetailStr =   cmdFileMangerDto.getFilePath();
		     	                	   String imgMiniString = cmdFileMangerDto.getCompressPath();
		     	                	   productDetailImageHeadList.add(imgDetailStr);
		     	                	   productDetailMiniImageHeadList.add(imgMiniString);
		     	                   }
		     	              phiProductDto.setProcuctDetailImageList(productDetailImageHeadList);   		            	   		            	   		            	   
		                      phiProductDto.setProcuctCompressDetailAppImageList(productDetailMiniImageHeadList);
		               }
		//要返回的商品
		List<PhiProductDto> phiProductDtos2 = new ArrayList<PhiProductDto>();
		int j=0;
		     for(int i= 0;i<10;i++){	
		    	 if(i<phiProductDtolist.size()){
		    		 //从热门推荐的商品中取
		    		 PhiProductDto phiProductDto = phiProductDtolist.get(i);
			         phiProductDtos2.add(phiProductDto);
		    	 }else{	
		    		 //按照销量从高到底排列
		    		 if(j<phiProductDtos.size()){
		    			 phiProductDtos2.add(phiProductDtos.get(j++));
		    		 }
		        			        	
		    	 }
		    	 
		     }
		return phiProductDtos2;
	}
	
	/*public static void main(String[] args) {
		
		int x = compare_date("1995-11-12 15:21", "1999-12-11 09:59");
	}*/


	
}
