package com.huatek.busi.api.phicom.order;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.StaleObjectStateException;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.order.PhiOrderinfoDto;
import com.huatek.busi.dto.phicom.winner.PhiWinnersListDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.model.phicom.order.PhiOrderinfo;
import com.huatek.busi.service.phicom.coupons.PhiCouponsService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.order.PhiOrderinfoService;
import com.huatek.busi.service.phicom.product.PhiProductService;
import com.huatek.busi.service.phicom.winners.PhiWinnersService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.PhicommCloudUtil.CloudMember;
import com.huatek.frame.session.util.SessionKey;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIORDER_API)
public class PhiOrderAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiOrderAction.class);

    @Autowired
    private PhiOrderService phiOrderService;
    
    @Autowired
    private PhiOrderinfoService phiOrderinfoService;
    
    
    @Autowired
    private PhiProductService phiProductService;

    @Autowired
    private PhiWinnersService phiWinnersService;
    
    @Autowired
    private PhiCouponsService phiCouponsService;
    
    @Autowired
    private PhiMemberService phiMemberService;
    /** 
    * @Title: getAllPhiOrder 
    * @Description:  翻页查询PhiOrder信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiOrderDto>>    
    */
    @RequestMapping(value = "/Allquery")
    @ResponseBody
    public ResponseEntity<DataPage<PhiOrderDto>> getAllPhiOrder(@RequestBody QueryPage queryPage)  { 	
        log.debug("get all phiOrder of param " + queryPage.getQueryInfo());
        List<QueryParam> params = queryPage.getQueryParamList();
        QueryParam queryParam = new QueryParam();
        queryParam.setField("isdelete");
        queryParam.setLogic("=");
        queryParam.setValue("0");
        params.add(queryParam);
        queryPage.setQueryParamList(params);
        DataPage<PhiOrderDto> phiOrderPages = phiOrderService.getAllPhiOrderPage(queryPage);
        log.debug("get phiOrder size @" + phiOrderPages.getContent().size());
        return new ResponseEntity<>(phiOrderPages, HttpStatus.OK);
       
    }
    
    
    /** 
     * @Title: getAllPhiOrder 
     * @Description:  翻页查询PhiOrder信息.
     * @param   queryPage
     * @return  ResponseEntity<DataPage<PhiOrderDto>>    
     */
     @RequestMapping(value = "/query")
     @ResponseBody
     public ResponseEntity<DataPage<PhiOrderDto>> getAllPhiOrderByMemberId(@RequestBody QueryPage queryPage,HttpSession session,HttpServletRequest request )  { 
    	 String clientType=request.getHeader("clientType");//1：app请求，2：pc请求
         log.debug("get all phiOrder of param " + queryPage.getQueryInfo());
         CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
         String uid=member.getUid();
         PhiMember phiMember = phiMemberService.GetMemberPhiMemberByUid(Integer.valueOf(uid));
//         PhiMember phiMember = phiMemberService.findPhiMemberByUid(Integer.valueOf(uid));
         Long memberId =  phiMember.getId();
         List<QueryParam> params = queryPage.getQueryParamList();
         QueryParam queryParam = new QueryParam();
         queryParam.setField("phiMember.id");
         queryParam.setLogic("=");
         queryParam.setValue(memberId.toString());
         params.add(queryParam);
         QueryParam myqueryParam = new QueryParam();
         myqueryParam.setField("isdelete");
         myqueryParam.setLogic("=");
         myqueryParam.setValue("0");
         params.add(myqueryParam);
         DataPage<PhiOrderDto> phiOrderPages = phiOrderService.getAllPhiOrderPage(queryPage);
         log.debug("get phiOrder size @" + phiOrderPages.getContent().size());
         return new ResponseEntity<>(phiOrderPages, HttpStatus.OK);
        
     }
    
    /** 
     * @Title: createPhiOrderDto 
     * @Description: 添加PhiOrder 
     * @param    phiOrderDto
     * @return   ResponseEntity<ResponseMessage>    
     */ 
     @RequestMapping(value = "/add/{id}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> createPhiOrderDto(@PathVariable("id") Long productId,HttpSession session) {
         CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
         String uid=member.getUid();
         PhiMember phiMember = phiMemberService.GetMemberPhiMemberByUid(Integer.valueOf(uid));
    	 Long memberId =  phiMember.getId();
         try{
        	 String statusString = phiOrderService.addOrder(productId,memberId);
        	 if("OutTime".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("该商品已下架"), HttpStatus.OK);
        	 }else if("todayopen".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("等待开奖中，该商品已无法购买，下次早点来哟！"), HttpStatus.OK);
        	 }else if("opened".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("已开奖，该商品已无法购买，下次早点来哟！"), HttpStatus.OK);
        	 }else if("overflow".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("兑换已达上限"), HttpStatus.OK);
        	 }else if("Needscore".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("积分不足"), HttpStatus.OK);
        	 }else if("NeedGrade".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning(("会员等级不足")), HttpStatus.OK);
        	 }else if("faile".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning(("数据异常")), HttpStatus.OK);
        	 }else if("notenough".equals(statusString)){
	     		 return new ResponseEntity<>(ResponseMessage.warning("库存不足"), HttpStatus.OK);
        	 }else if("throw".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("订单异常，请稍后操作！"), HttpStatus.BAD_REQUEST);
        	 }else{
        		 PhiOrder order = phiOrderService.findPhiOrderById(Long.valueOf(statusString));
    	   		 PhiOrderinfo orderinfo = order.getPhiOrderInfo();
//    	   		 PhiOrderinfo orderinfo = phiOrderinfoService.findPhiOrderById(Long.valueOf(statusString));
//    	   		 JsonArray objects = new JsonArray();
    	    	 JsonObject jsonObject = new  JsonObject();
        	     jsonObject.addProperty("orderPrice",orderinfo.getMoney());
        	     jsonObject.addProperty("goodsName",orderinfo.getProductName());
//        	     jsonObject.addProperty("memberId",memberId);
        	     jsonObject.addProperty("orderNo",order.getOrderCode());
        	     jsonObject.addProperty("plusCode","");
    	   	     return new ResponseEntity<>(ResponseMessage.success(new Gson().toJson(jsonObject)), HttpStatus.CREATED);
        	 }
         }catch(StaleObjectStateException e){
			 return new ResponseEntity<>(ResponseMessage.success("订单异常，请稍后操作！"), HttpStatus.BAD_REQUEST);

         }
         
     }
        //测试
//	    @RequestMapping(value = "/add/{id}/{memberId}", method = RequestMethod.POST)
//	    @ResponseBody
//	    public ResponseEntity<ResponseMessage> createPhiOrderDto(@PathVariable("id") Long productId,@PathVariable("memberId") Long memberId) {
//	   	 String statusString = phiOrderService.addOrder(productId,memberId);
//	   	 if("Needscore".equals(statusString)){
//	   		 return new ResponseEntity<>(ResponseMessage.warning("积分不足"), HttpStatus.OK);
//	   	 }else if("NeedGrade".equals(statusString)){
//	   		 return new ResponseEntity<>(ResponseMessage.warning(("会员等级不足")), HttpStatus.OK);
//	   	 }else if("faile".equals(statusString)){
//	   		 return new ResponseEntity<>(ResponseMessage.warning(("数据异常")), HttpStatus.OK);
//	   	 }else{
//	   		 PhiOrder order = phiOrderService.findPhiOrderById(Long.valueOf(statusString));
//	   		 PhiOrderinfo orderinfo = order.getPhiOrderInfo();
////	   		 PhiOrderinfo orderinfo = phiOrderinfoService.findPhiOrderById(Long.valueOf(statusString));
////	   		 JsonArray objects = new JsonArray();
//	    	 JsonObject jsonObject = new  JsonObject();
//    	     jsonObject.addProperty("orderPrice",orderinfo.getMoney());
//    	     jsonObject.addProperty("goodsName",orderinfo.getProductName());
//    	     jsonObject.addProperty("memberId",memberId);
//    	     jsonObject.addProperty("orderNo",order.getOrderCode());
//    	     jsonObject.addProperty("plusCode","");
//	   	     return new ResponseEntity<>(ResponseMessage.success(new Gson().toJson(jsonObject)), HttpStatus.CREATED);
//	   	 }
//	    }
     
     /** 
      * @Title: createPhiOrderDto 
      * @Description: 添加PhiOrder (有收获地址)
      * @param    phiOrderDto
      * @return   ResponseEntity<ResponseMessage>    
      */ 
      @RequestMapping(value = "/addOrderAddress/{id}/{addressId}", method = RequestMethod.POST)
      @ResponseBody
      public ResponseEntity<ResponseMessage> createPhiOrderDto(@PathVariable("id") Long productId,@PathVariable("addressId") Long addressId,HttpSession session) {
//      	PhiProductDto phiProductDto =phiProductService.getPhiProductDtoById(productId);
    	  CloudMember member = (CloudMember) session.getAttribute(SessionKey.currentMember);
          String uid=member.getUid();
          PhiMember phiMember = phiMemberService.GetMemberPhiMemberByUid(Integer.valueOf(uid));
          Long memberId =  phiMember.getId();
     	 String statusString;
		try {
			statusString = phiOrderService.addAddressOrder(productId,memberId,addressId);
				if("OutTime".equals(statusString)){
	       		 return new ResponseEntity<>(ResponseMessage.warning("该商品已下架"), HttpStatus.OK);
	       	 }else if("todayopen".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("等待开奖中，该商品已无法购买，下次早点来哟！"), HttpStatus.OK);
        	 }else if("opened".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("已开奖，该商品已无法购买，下次早点来哟！"), HttpStatus.OK);
        	 }else if("overflow".equals(statusString)){
        		 return new ResponseEntity<>(ResponseMessage.warning("兑换已达上限"), HttpStatus.OK);
        	 }else if("Needscore".equals(statusString)){
	     		 return new ResponseEntity<>(ResponseMessage.warning("积分不足"), HttpStatus.OK);
	     	 }else if("NeedGrade".equals(statusString)){
	     		 return new ResponseEntity<>(ResponseMessage.warning("会员等级不足"), HttpStatus.OK);
	     	 }else if("faile".equals(statusString)){
	     		 return new ResponseEntity<>(ResponseMessage.warning("数据异常"), HttpStatus.OK);
	     	 }else if("notenough".equals(statusString)){
	     		 return new ResponseEntity<>(ResponseMessage.warning("库存不足"), HttpStatus.OK);
	     	 }else{
	     		 PhiOrder order = phiOrderService.findPhiOrderById(Long.valueOf(statusString));
		   		 PhiOrderinfo orderinfo = order.getPhiOrderInfo();
//		   		 PhiOrderinfo orderinfo = phiOrderinfoService.findPhiOrderById(Long.valueOf(statusString));
		    	 JsonObject jsonObject = new  JsonObject();
		   	     jsonObject.addProperty("orderPrice",orderinfo.getMoney());
		   	     jsonObject.addProperty("goodsName",orderinfo.getProductName());
//		   	     jsonObject.addProperty("memberId",memberId);
		   	     jsonObject.addProperty("orderNo",order.getOrderCode());
		   	     jsonObject.addProperty("plusCode","");
		   	     return new ResponseEntity<>(ResponseMessage.success(new Gson().toJson(jsonObject)), HttpStatus.CREATED);
	     	 }
		} catch (StaleObjectStateException e) {
			 return new ResponseEntity<>(ResponseMessage.success("订单异常，请稍后操作！"), HttpStatus.BAD_REQUEST);
		}
     	
     		
     	 
      }
        //测试
//	    @RequestMapping(value = "/add/{id}/{memberId}/{addressId}", method = RequestMethod.POST)
//	      @ResponseBody
//	      public ResponseEntity<ResponseMessage> createPhiOrderDto(@PathVariable("id") Long productId,@PathVariable("memberId") Long memberId,@PathVariable("addressId") Long addressId) {
//	     	 String statusString = phiOrderService.addAddressOrder(productId,memberId,addressId);
//	     	 if("Needscore".equals(statusString)){
//	     		 return new ResponseEntity<>(ResponseMessage.warning("积分不足"), HttpStatus.OK);
//	     	 }else if("NeedGrade".equals(statusString)){
//	     		 return new ResponseEntity<>(ResponseMessage.warning("会员等级不足"), HttpStatus.OK);
//	     	 }else if("faile".equals(statusString)){
//	     		 return new ResponseEntity<>(ResponseMessage.warning("数据异常"), HttpStatus.OK);
//	     	 }else{
//	     		PhiOrder order = phiOrderService.findPhiOrderById(Long.valueOf(statusString));
//		   		 PhiOrderinfo orderinfo = order.getPhiOrderInfo();
////		   		 PhiOrderinfo orderinfo = phiOrderinfoService.findPhiOrderById(Long.valueOf(statusString));
//		    	 JsonObject jsonObject = new  JsonObject();
//	    	     jsonObject.addProperty("orderPrice",orderinfo.getMoney());
//	    	     jsonObject.addProperty("goodsName",orderinfo.getProductName());
//	    	     jsonObject.addProperty("memberId",memberId);
//	    	     jsonObject.addProperty("orderNo",order.getOrderCode());
//	    	     jsonObject.addProperty("plusCode","");
//		   	     return new ResponseEntity<>(ResponseMessage.success(new Gson().toJson(jsonObject)), HttpStatus.CREATED);
//	     	 }
//	      }
      /** 
       * @Title: createPhiOrderDto 
       * @Description: 支付成功
       * @param    phiOrderDto
       * @return   ResponseEntity<ResponseMessage>    
       */ 
       @RequestMapping(value = "/paySuccess/{id}", method = RequestMethod.POST)
       @ResponseBody
       public ResponseEntity<ResponseMessage> paySuccessPhiOrderDto(@PathVariable("id") Long orderId) {
//       	PhiProductDto phiProductDto =phiProductService.getPhiProductDtoById(productId);
            phiOrderService.editOrderAfterPayById(orderId);
      		return new ResponseEntity<>(ResponseMessage.success("兑换成功"), HttpStatus.CREATED);
      	 
       }
      
      /** 
       * @Title: getPhiOrderDto 
       * @Description: 支付失败修改订单状态
       * @createDate: 2016年4月25日 下午1:49:40
       * @param    id
       * @return   ResponseEntity<PhiOrderDto>    
       */ 
       @RequestMapping(value = "/cancel/{id}", method = RequestMethod.GET)
       @ResponseBody
       public ResponseEntity<ResponseMessage> setPhiOrderDto(@PathVariable("id") Long id) {
    	   phiOrderService.setPhiOrderStatusDto(id);
           return new ResponseEntity<>(ResponseMessage.success("订单已取消"), HttpStatus.OK);
       }
       
       
       
    /** 
    * @Title: getPhiOrderDto 
    * @Description: 获取需要修改 订单信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiOrderDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiOrderDto> getPhiOrderDto(@PathVariable("id") Long id) {
    	PhiOrderDto phiOrderDto = phiOrderService.getPhiOrderDtoById(id);
        return new ResponseEntity<>(phiOrderDto, HttpStatus.OK);
    }
    
    /** 
    * @Title: editPhiOrder 
    * @Description:修改PhiOrder信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiOrderDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiOrder(@PathVariable("id") Long id, @RequestBody PhiOrderDto phiOrderDto) {
        phiOrderService.updatePhiOrder(id, phiOrderDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiOrderById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiOrderById(@PathVariable("id") Long id) {
        phiOrderService.deletePhiOrder(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    
    /**
     * @Description: 根据订单状态类型查找 
     * @param queryPage
     * @return
     */
    @RequestMapping(value = "/select/{status}", method = RequestMethod.POST)
    public ResponseEntity<DataPage<PhiOrderDto>> getPhiOrderByStatus(@RequestBody QueryPage queryPage,@PathVariable("status") String status)  { 	
        log.debug("get all phiOrder of param " + queryPage.getQueryInfo());
        List<QueryParam> params = queryPage.getQueryParamList();
        QueryParam queryParam = new QueryParam();
        queryParam.setField("status");
        queryParam.setLogic("=");
        queryParam.setValue(status);
        params.add(queryParam);
        DataPage<PhiOrderDto> phiOrderPages = phiOrderService.getPhiOrderByStatusPage(queryPage);
        log.debug("get phiOrder size @" + phiOrderPages.getContent().size());
        return new ResponseEntity<>(phiOrderPages, HttpStatus.OK);
    }
    /** 
     * @Title: getPhiOrderDto 
     * @Description: 获取中奖名单
     * @createDate: 2016年4月25日 下午1:49:40
     * @param    id
     * @return   ResponseEntity<PhiOrderDto>    
     */ 
     @RequestMapping(value = "/winnerList/{id}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<DataPage<PhiWinnersListDto>> getWinnerDto(@RequestBody QueryPage queryPage,@PathVariable("id") Long id) {
//     	List<PhiWinnersListDto> PhiWinnersDto = phiWinnersService.getAllPhiWinnersByproductIdDto(id);
    	 List<QueryParam> params = queryPage.getQueryParamList();
         QueryParam queryParam = new QueryParam();
         queryParam.setField("productId");
         queryParam.setLogic("=");
         queryParam.setValue(id.toString());
         params.add(queryParam);
    	 DataPage<PhiWinnersListDto> PhiWinnersDtoPage = phiWinnersService.getAllPhiWinnersPageforApp(queryPage);
    	 
         return new ResponseEntity<>(PhiWinnersDtoPage, HttpStatus.OK);

     }
     
     @RequestMapping(value = "/winnerListForBack/{id}", method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<DataPage<PhiWinnersListDto>> getwinnerListForBack(@RequestBody QueryPage queryPage,@PathVariable("id") Long id) {
//     	List<PhiWinnersListDto> PhiWinnersDto = phiWinnersService.getAllPhiWinnersByproductIdDto(id);
    	 List<QueryParam> params = queryPage.getQueryParamList();
         QueryParam queryParam = new QueryParam();
         queryParam.setField("productId");
         queryParam.setLogic("=");
         queryParam.setValue(id.toString());
         params.add(queryParam);
    	 DataPage<PhiWinnersListDto> PhiWinnersDtoPage = phiWinnersService.getAllPhiWinnersPage(queryPage);
    	 
         return new ResponseEntity<>(PhiWinnersDtoPage, HttpStatus.OK);

     }
     
     /** 
      * @Title: getPhiOrderDto 
      * @Description: 获取券码信息
      * @createDate: 2016年4月25日 下午1:49:40
      * @param    id
      * @return   ResponseEntity<PhiOrderDto>    
      */ 
      @RequestMapping(value = "/couponsDetial/{id}", method = RequestMethod.GET)
      @ResponseBody
      public ResponseEntity<PhiOrderinfoDto> getCouponsDetialDto(@PathVariable("id") Long id) {
    	  PhiOrderinfoDto phiOrderinfoDto = phiOrderinfoService.getPhiOrderinfoDtoById(id);
//    	  PhiCouponsDto phiCoupons = phiCouponsService.getPhiCouponsDtoById(id);
          return new ResponseEntity<>(phiOrderinfoDto, HttpStatus.OK);
      }
    
    
}
