package com.huatek.busi.api.phicom.product;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.product.PhiProductDto;
import com.huatek.busi.dto.phicom.product.PhiProductTypeDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.product.PhiProductService;
import com.huatek.busi.service.phicom.product.PhiProductTypeService;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.dto.ParamDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIPRODUCT_API)
public class PhiProductAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiProductAction.class);

    @Autowired
    private PhiProductService phiProductService;
 
    @Autowired
    private PhiProductTypeService phiProductTypeService;;
    
    @Autowired
	private CmdFileMangerService cmdFileMangerService;
    
    @Autowired
    private PhiOrderService phiOrderService;
    
    @Autowired
    private PhiMemberService phiMemberService;

   
    /** 
    * @Title: getAllPhiProduct 
    * @Description:  翻页查询PhiProduct信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiProductDto>>    
    */
    @RequestMapping(value = "/query")
    @ResponseBody
    public ResponseEntity<DataPage<PhiProductDto>> getAllPhiProductForAPP(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiProduct of param " + queryPage.getQueryInfo());
        List<QueryParam> params = queryPage.getQueryParamList();
        QueryParam moneyParam = new QueryParam();
        StringBuilder sb = new StringBuilder();
        sb.append( "productStatus='1' and isShop ='1'");
        if(null!=params&&params.size()>0){
        	for(int i=0;i<params.size();i++){
        		moneyParam = params.get(i);
        		if("upTime".equals(moneyParam.getField())){
                	Date nowDate = new Date();
              	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
              	    Calendar calendar = Calendar.getInstance();  
              	    calendar.setTime(nowDate);  
              	    calendar.add(Calendar.DAY_OF_WEEK, -3);//+1今天的时间加一天  （当前日期的前一天）
              	    Date lastdate = calendar.getTime();
              	    String lastString = sdf.format(lastdate);
                	queryPage.setSqlCondition(" upTime >= "+"'"+lastString+"'");
                	queryPage.getQueryParamList().remove(moneyParam);
                }
        		 if("money".equals(moneyParam.getField())){
        			 if(null!= moneyParam.getValue()){
        				 if(!"0".equals(moneyParam.getValue())){
            				 sb.append("and (money is not null and money !=0)");
                          	 queryPage.getQueryParamList().remove(moneyParam);
            			 }
            			 else{
            				 sb.append("and (money is null or money = 0)");
            				 queryPage.getQueryParamList().remove(moneyParam);
            			 }
        			 }
        			
        		 }
        	}
            
        }
        queryPage.setSqlCondition(sb.toString());
        DataPage<PhiProductDto> phiProductPages = phiProductService.getAllPhiProductPage(queryPage);
        log.debug("get phiProduct size @" + phiProductPages.getContent().size());
        return new ResponseEntity<>(phiProductPages, HttpStatus.OK);
    }
    
    
    
    /** 
     * @Title: getAllPhiProduct 
     * @Description:  翻页查询PhiProduct信息.
     * @param   queryPage
     * @return  ResponseEntity<DataPage<PhiProductDto>>    
     */
     @RequestMapping(value = "/queryList")
     @ResponseBody
     public ResponseEntity<DataPage<PhiProductDto>> getAllPhiProduct(@RequestBody QueryPage queryPage)  { 	
         log.debug("get all phiProduct of param " + queryPage.getQueryInfo());
         QueryParam queryParam = new QueryParam();
 	 	queryParam.setField("productStatus");
 		queryParam.setLogic("!=");
 		queryParam.setValue("1");
        List<QueryParam> queryList = queryPage.getQueryParamList();
        //当选择类型是全部时设置值为空
         for(QueryParam queryParams: queryList){
        	 if("productClass".endsWith(queryParams.getField())){
        		 if(null!=queryParams.getValue()&&"4".endsWith(queryParams.getValue())){
        			 queryParams.setValue("");
        		 }
        	 }
        	 if("productStatus".endsWith(queryParams.getField())){
        		 if(null!=queryParams.getValue()&&"3".endsWith(queryParams.getValue())){
        			 queryParams.setValue("");
        		 }
        	 }
        	 
         }
 		DataPage<PhiProductDto> phiProductPages = phiProductService.getAllPhiProductPage(queryPage);
         log.debug("get phiProduct size @" + phiProductPages.getContent().size());
         return new ResponseEntity<>(phiProductPages, HttpStatus.OK);
     }
    
    /** 
     * @Title: getRecommendProductDto
     * @Description: 查看商品的热门推荐
     * 如果不足10个
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    id
     * @return   ResponseEntity<PhiOrderDto>    
     */ 
     @RequestMapping(value = "/queryRecommend", method = RequestMethod.POST)
     @ResponseBody
     public List<PhiProductDto> getRecommendProductDto() {
    	 //log.debug("get all phiProduct of param " + queryPage.getQueryInfo());
    	 List<PhiProductDto> phiProductDtos = phiProductService.getRecommendProductForApp();
         //log.debug("get phiProduct size @" + phiProductPages.getContent().size());
         return phiProductDtos;
     }
     /** 
      * @Title: getproductByqueryDto
      * @Description: 1新品有先 2积分从低到高 3积分从高到低
      * @createDate: 2016年4月25日 下午1:49:40
      * @param    id
      * @return   ResponseEntity<PhiOrderDto>    
      */ 
      @RequestMapping(value = "/query/{sortBy}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<DataPage<PhiProductDto>> getproductByqueryDto(@RequestBody QueryPage queryPage,@PathVariable("sortBy") Long sortBy) {
     	 log.debug("get all phiProduct of param " + queryPage.getQueryInfo());
     	 if(1==sortBy){
     		 queryPage.setOrderBy("upTime desc");
     	 }else if(2==sortBy){
     		 queryPage.setOrderBy("score asc");
     	 }else{
     		 queryPage.setOrderBy("score desc");
     	 }
          DataPage<PhiProductDto> phiProductPages = phiProductService.getAllPhiProductPage(queryPage);
          log.debug("get phiProduct size @" + phiProductPages.getContent().size());
          return new ResponseEntity<>(phiProductPages, HttpStatus.OK);
      }
     
     /**                                                    
      * @Title: getproductByqueryDto
      * @Description: 筛选商品通过分类id
      * @createDate: 2016年4月25日 下午1:49:40
      * @param    id
      * @return   ResponseEntity<PhiOrderDto>    
      */ 
      @RequestMapping(value = "/queryByType/{id}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<DataPage<PhiProductDto>> getproductByTypeIdDto(@RequestBody QueryPage queryPage,@PathVariable("id") Long typeId) {
     	 log.debug("get all phiProduct of param " + queryPage.getQueryInfo());
          QueryParam queryParam = new QueryParam();
	  		queryParam.setField("score");
	  		queryParam.setLogic("<");
	  		queryParam.setValue("300");
	  		queryPage.getQueryParamList().add(queryParam);
          DataPage<PhiProductDto> phiProductPages = phiProductService.getAllPhiProductPage(queryPage);
          log.debug("get phiProduct size @" + phiProductPages.getContent().size());
          return new ResponseEntity<>(phiProductPages, HttpStatus.OK);
      }
      
      @RequestMapping(value = "/queryByType2/{id}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<DataPage<PhiProductDto>> get1(@RequestBody QueryPage queryPage,@PathVariable("id") Long typeId) {
     	 log.debug("get all phiProduct of param " + queryPage.getQueryInfo());
          QueryParam queryParam = new QueryParam();
	  		queryParam.setField("score");
	  		queryParam.setLogic(">");
	  		queryParam.setValue("300");
	  		queryPage.getQueryParamList().add(queryParam);
          DataPage<PhiProductDto> phiProductPages = phiProductService.getAllPhiProductPage(queryPage);
          log.debug("get phiProduct size @" + phiProductPages.getContent().size());
          return new ResponseEntity<>(phiProductPages, HttpStatus.OK);
      }
      
     //通过id查找商品的详情
      @RequestMapping(value = "/queryProductDetail/{id}", method = RequestMethod.POST)
      @ResponseBody
      public PhiProductDto getproductDetailDtoForApp(@PathVariable("id") Long id,HttpSession session) {
    	  CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
          String uid=member.getUid();
          PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
          Long memberId =  phiMember.getId();
    	  PhiProductDto phiProductDto = phiProductService.getProductDetailForApp(id, memberId);    	
    	                 
          return phiProductDto;
      }
      /**
       * 查找所有的类型
       * @param queryPage
       * @return
       */
      @RequestMapping(value = "/queryAllType/{type}")
      @ResponseBody
      public List<ParamDto> getAllProductTypeForApp(@PathVariable("type") String type){ 	
    	  List<PhiProductTypeDto> productTypeList  =  phiProductTypeService.getAllProductTypeRecommendForApp();
    	  List<ParamDto> paramsDtolist = new ArrayList<ParamDto>();
    	  if(productTypeList.size()>0){
    	   if(type != null && type.equals("home")){
    		   //如果type=home则只展示6个
    	    		  for(int i=0; i<6;i++){
    	            	  ParamDto paramDto = new ParamDto();
    	            	  paramDto.setName(productTypeList.get(i).getTypeName());
    	            	  paramDto.setCode(productTypeList.get(i).getId()+"");
    	            	  List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(productTypeList.get(i).getTypeIcon());        	  
    	            	  if(imsges != null&& imsges.size()>0){
    	            		  paramDto.setRemark(imsges.get(0).getFilePath());  
    	            	  }     	  
    	            	  paramsDtolist.add(paramDto);
    	              }    	    	       
    	   }
    	   if(type != null && type.equals("list")){
    		  //如果type为空则返回全部类型
    		   for(PhiProductTypeDto phiProductTypeDto : productTypeList){
    			   ParamDto paramDto = new ParamDto();
	            	  paramDto.setName(phiProductTypeDto.getTypeName());
	            	  paramDto.setCode(phiProductTypeDto.getId()+"");
	            	  List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(phiProductTypeDto.getTypeIcon());        	  
	            	  if(imsges != null&& imsges.size()>0){
	            		  paramDto.setRemark(imsges.get(0).getFilePath());  
	            	  }     	  
	            	  paramsDtolist.add(paramDto);   			 
    		   }
    	     }
    	  }  	  
    	     
          return paramsDtolist;
      }
       
      /**
     * 查找所有的类型
     * @param queryPage
     * @return
     */
    @RequestMapping(value = "/queryType")
    @ResponseBody
    public List<ParamDto> getAllPhiProductType(){ 	
        List<Map<Long,String>> list = phiProductService.getAllTypeName();
         List<ParamDto> paramsDtolist = new ArrayList();
			    for(Map map:list){
			    	ParamDto paramDto = new ParamDto();  
			    	Iterator<Map.Entry<Long, String>> it = map.entrySet().iterator();
			        while (it.hasNext()) {
			              Map.Entry<Long, String> entry = it.next();
			                System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			                paramDto.setCode(entry.getKey().toString());
					    	paramDto.setName(entry.getValue());
					    	paramsDtolist.add(paramDto) ;	
			         }
			    	
			    }
        
        return paramsDtolist;
    }
    
    
    /**
   * 查找所有的类型
   * @param queryPage
   * @return
   */
  @RequestMapping(value = "/queryCouponsId")
  @ResponseBody
  public List<ParamDto> getAllPhiProductCouponsId(){
	  List<Long> idList =   phiProductService.getAllCouponsId();
	  List<ParamDto> paramsDtolist = new ArrayList();
	   for(Long id :idList){
		   ParamDto paramDto = new ParamDto();
	       paramDto.setName(id.toString());
	       paramDto.setCode(id.toString());
		   paramsDtolist.add(paramDto);
	   }
	return paramsDtolist; 	
     
  }
  
  @RequestMapping(value = "/queryThirdPartyId")
  @ResponseBody
  public List<ParamDto> getAllPhiProductThirdPartyId(){
	  List<String> idList =   phiProductService.getAllThirdPartyId();
	  List<ParamDto> paramsDtolist = new ArrayList();
	   for(String id :idList){
		   ParamDto paramDto = new ParamDto();
	       paramDto.setName(id.toString());
	       paramDto.setCode(id.toString());
		   paramsDtolist.add(paramDto);
	   }
	return paramsDtolist; 	
     
  }
  /**
   * 查找所有的类型
   * @param queryPage
   * @return
   */
  @RequestMapping(value = "/queryThirdPartyValidDate/{id}",method = RequestMethod.GET)
  @ResponseBody
  public ParamDto getThirdPartyValidDateById(@PathVariable("id")String  id){
         String validdate =   phiProductService.getThirdPartyValidDateById(id);
         ParamDto paramDto = new ParamDto();
         paramDto.setName(validdate);
	return paramDto; 	
     
  }
  /**
   * 查找所有的类型
   * @param queryPage
   * @return
   */
  @RequestMapping(value = "/queryValidDate/{id}",method = RequestMethod.GET)
  @ResponseBody
  public ParamDto getProductValidDateById(@PathVariable("id")Long id){
         String validdate =   phiProductService.getProductValidDateById(id);
         ParamDto paramDto = new ParamDto();
         paramDto.setName(validdate);
	return paramDto; 	
     
  }
    /** 
    * @Title: getAllPhiProduct 
    * @Description:  翻页查询PhiProduct信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiProductDto>>    
    */
    @RequestMapping(value = "/queryAuditStatus")
    @ResponseBody
    public ResponseEntity<DataPage<PhiProductDto>> getAllPhiProductStatus(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiProduct of param " + queryPage.getQueryInfo());
        QueryParam queryParam = new QueryParam();
		queryParam.setField("productStatus");
		queryParam.setLogic("=");
		queryParam.setValue("0");
		queryPage.getQueryParamList().add(queryParam);
        DataPage<PhiProductDto> phiProductPages = phiProductService.getAllPhiProductPage(queryPage);
        log.debug("get phiProduct size @" + phiProductPages.getContent().size());
        return new ResponseEntity<>(phiProductPages, HttpStatus.OK);
    }
    
       
     /** 
    * @Title: createPhiProductDto 
    * @Description: 添加PhiProduct 
    * @param    phiProductDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiProductDto(@RequestBody PhiProductDto phiProductDto) {    	
        phiProductService.savePhiProductDto(phiProductDto);	
        return new ResponseEntity<>(ResponseMessage.success("PhiProduct创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiProductDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiProductDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiProductDto> getPhiProductDto(@PathVariable("id") Long id) {
    	PhiProductDto phiProductDto = phiProductService.getPhiProductDtoById(id);
        return new ResponseEntity<>(phiProductDto, HttpStatus.OK);
    }
    
    /** 
     * @Title: getPhiProductDto 
     * @Description: 查看商品详细信息
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    id
     * @return   ResponseEntity<PhiProductDto>    
     */ 
     @RequestMapping(value = "/detial/{id}/{memberId}", method = RequestMethod.GET)
     @ResponseBody
     public ResponseEntity<PhiProductDto> getPhiProductDetialDto(@PathVariable("id") Long id,@PathVariable("memberId") Long memberId) {
     	PhiProductDto phiProductDto = phiProductService.getPhiProductDetialDtoById(id,memberId);
         return new ResponseEntity<>(phiProductDto, HttpStatus.OK);
     }
    
    /** 
     * @Title: updatePhiProductDto 
     * @Description: 更新产品的上架和信息
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    id
     * @return   ResponseEntity<PhiProductDto>    
     */ 
     @RequestMapping(value = "/productIsShop/{id}/{val}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> updatePhiProductDto(@PathVariable("id") Long id,@PathVariable("val") String status) {
            phiProductService.updateProductStauts(id, status);
            String msg = "";
            if(status.equals("1")){
            	msg = "商品已上架";
            }else if(status.equals("2")){
            	msg = "商品已下架";
            }
         return new ResponseEntity<>(ResponseMessage.success(msg), HttpStatus.OK);
     }
    /** 
    * @Title: editPhiProduct 
    * @Description:修改PhiProduct信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiProductDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiProduct(@PathVariable("id") Long id, @RequestBody PhiProductDto phiProductDto) {
        
    	phiProductService.updatePhiProduct(id, phiProductDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiProductById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiProductById(@PathVariable("id") Long id) {
        phiProductService.deletePhiProduct(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
       
    
    /** 
     * @Title: deletePhiProductById 
     * @Description: 根据ID删除MdmLineBaseInfo信息. 
     * @param   id
     * @return  ResponseEntity<ResponseMessage>    
     */
     @RequestMapping(value = "/submitProduct/{id}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> submitPhiProductById(@PathVariable("id") Long id) {
         phiProductService.submitProductCertification(id);
         return new ResponseEntity<>(ResponseMessage.success("已提交"), HttpStatus.OK);
     }
     
     /** 
      * @Title: updatePhiProductDto 
      * @Description: 更新产品的上架和信息
      * @createDate: 2016年4月25日 下午1:49:40
      * @param    id
      * @return   ResponseEntity<PhiProductDto>    
      */ 
      @RequestMapping(value = "/productStatus/{val}/{ids}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<ResponseMessage> updatePhiProductDtoStatus(@PathVariable("val") String val,@PathVariable("ids") Long[] ids) {
            
    	     phiProductService.updatePhiProductStatus(val, ids);
             String msg = "";
             if(val.equals("1")){
             	msg = "商品已审批";
             }else if(val.equals("2")){
             	msg = "商品已驳回";
             }
          return new ResponseEntity<>(ResponseMessage.success(msg), HttpStatus.OK);
      }
     
     
}
