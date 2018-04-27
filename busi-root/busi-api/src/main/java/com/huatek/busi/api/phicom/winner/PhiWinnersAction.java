package com.huatek.busi.api.phicom.winner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dao.phicom.order.PhiOrderDao;
import com.huatek.busi.dto.phicom.member.PhiMemberDto;
import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.product.PhiProductDto;
import com.huatek.busi.dto.phicom.winner.PhiVirtualUser;
import com.huatek.busi.dto.phicom.winner.PhiWinnersListDto;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.product.PhiProductService;
import com.huatek.busi.service.phicom.winners.PhiWinnersService;
import com.huatek.cmd.dto.ParamDto;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@RestController
@RequestMapping(value = BusiUrlConstants.PHIWINNERS_API)
public class PhiWinnersAction {

    private static final Logger log = LoggerFactory
            .getLogger(PhiWinnersAction.class);

    @Autowired
    private PhiWinnersService phiWinnersService;
    
    @Autowired
    private PhiMemberService phiMemberService;
     
    @Autowired
    private PhiProductService phiProductService;
    
    @Autowired
    private PhiOrderService phiOrderService;
    /** 
    * @Title: getAllPhiWinners 
    * @Description:  翻页查询PhiWinners信息.
    * @param   queryPage
    * @return  ResponseEntity<DataPage<PhiWinnersDto>>    
    */
    @RequestMapping(value = "/query/{id}")
    @ResponseBody
    public ResponseEntity<DataPage<PhiWinnersListDto>> getAllPhiWinners(@PathVariable("id") Long id)  { 
    	QueryPage queryPage = new QueryPage();
        log.debug("get all phiWinners of param " + queryPage.getQueryInfo());
        queryPage.setSqlCondition(" productId = "+id);
        DataPage<PhiWinnersListDto> phiWinnersPages = phiWinnersService.getAllPhiWinnersPage(queryPage);  
        
        log.debug("get phiWinners size @" + phiWinnersPages.getContent().size());
        return new ResponseEntity<>(phiWinnersPages, HttpStatus.OK);
       
    }
    
    /** 
     * @Title: getAllPhiWinners 
     * @Description:  已开奖后更新产品的状态
     * @param   queryPage
     * @return  ResponseEntity<DataPage<PhiWinnersDto>>    
     */
     @RequestMapping(value = "/updateWinnerStatus/{id}")
     @ResponseBody
     public ResponseEntity<ResponseMessage> updateProductWinnerStatus(@PathVariable("id") Long id,@RequestBody PhiProductDto productdto)  { 	             
    	/* PhiProductDto product = phiProductService.getPhiProductDtoById(id); 
    	    product.setWinnerStatus("1"); */
    	    phiProductService.updatePhiProduct(id, productdto); 
    	    //获得所有的中奖名单
    	    List<PhiWinnersListDto> phiWinnersListDtos = productdto.getPhiWinnersListDtoList();
    	    //保存中奖名单
    	    phiWinnersService.saveAllWinnersList(phiWinnersListDtos);   
    	    //根据商品的id和中奖名单的MemberId更新订单的状态
    	    phiWinnersService.updateOrderStatus(id, phiWinnersListDtos);
    	 return new ResponseEntity<>(ResponseMessage.success("开奖完成"), HttpStatus.CREATED);
        
     }
    /** 
     * @Title: getAllPhiWinners 
     * @Description:  翻页查询PhiWinners信息.
     * @param   queryPage
     * @return  ResponseEntity<DataPage<PhiWinnersDto>>    
     */
     @RequestMapping(value = "/Manual/{id}/{MemberId}", method = RequestMethod.POST)
     @ResponseBody
     public PhiWinnersListDto ManualToWinner(@PathVariable("id") Long id,@PathVariable("MemberId") Long MemberId)  { 	             
             PhiMemberDto phiMemberDto = phiMemberService.getPhiMemberDtoById(MemberId);
             PhiWinnersListDto phiWinnersListDto = new PhiWinnersListDto();
             if(phiMemberDto != null){
            	 phiWinnersListDto.setMobile(phiMemberDto.getTel());
                 phiWinnersListDto.setMemberId(phiMemberDto.getId());
                 phiWinnersListDto.setProductId(id);
                 phiWinnersListDto.setUserName(phiMemberDto.getUserName());
                 phiWinnersListDto.setUserType("0");
                 phiWinnersListDto.setWinnersType("0");
             }
//           List<PhiWinnersListDto>  phiWinnersListDtoslistDtos =  phiWinnersService.getMemberByProductIdandMemberId(id, MemberId);
//             PhiWinnersListDto phiWinnersListDto = new PhiWinnersListDto();
//             if(phiWinnersListDtoslistDtos.size()>0){
//                 return null; 
//               }else{
//            	  
//                   phiWinnersListDto.setMobile(phiMemberDto.getTel());
//                   phiWinnersListDto.setMemberId(phiMemberDto.getId());
//                   phiWinnersListDto.setProductId(id);
//                   phiWinnersListDto.setUserName(phiMemberDto.getRealName());
//                   phiWinnersListDto.setUserType("0");
//                   phiWinnersListDto.setWinnersType("0");
//                                
//               }              
         return phiWinnersListDto;     
     }
     
     

     
     @RequestMapping(value = "/queryAllOrder/{id}", method = RequestMethod.POST)
     @ResponseBody
     public int getAllOrderByProductId(@PathVariable("id") Long id)  { 	             
    	   int count = phiWinnersService.getOrderCountByProductId(id);        
           return count;        
     }
     
     @RequestMapping(value = "/Random/{id}/{count}",method = RequestMethod.POST)
     @ResponseBody
     public  List<PhiWinnersListDto> RandomToWinner(@PathVariable("id") Long id,@PathVariable("count") int count,@RequestBody List<PhiWinnersListDto> dtoList)  { 
    	 List<Long> idList = new ArrayList<Long>();
    	 if(dtoList != null && dtoList.size() > 0){
    		 for(int i = 0 ; i < dtoList.size() ; i ++){
    			 if(dtoList.get(i).getMemberId()!= null){
    				 idList.add(dtoList.get(i).getMemberId());
    			 }
    		 }
    	 }
     return phiWinnersService.getRandomWinners(id, idList,count);
//    	 List<PhiOrderDto> phiWinnersList = phiWinnersService.gerAllRandomMemberByProductId(id);   	   	
//    	 List<PhiOrderDto> differenceList = phiWinnersList.removeAll(dtoList);
//    	 List<PhiWinnersListDto> phiWinnersListDtoList = new ArrayList<PhiWinnersListDto>();
//    	       if(phiWinnersList.size()<count){
//    	            return null;
//    	        }
//    	        Random random=new Random();
//    	        List<Integer> tempList=new ArrayList<Integer>();
//    	        List<PhiOrderDto> newList=new ArrayList<PhiOrderDto>();
//    	        int temp=0;
//    	        for(int i=0;i<count;i++){
//    	            temp=random.nextInt(phiWinnersList.size());//将产生的随机数作为被抽list的索引
//    	            if(!tempList.contains(temp)){
//    	                tempList.add(temp);
//    	                newList.add(phiWinnersList.get(temp));    	                
//    	            }
//    	            else{
//    	                i--;
//    	            }    	            
//    	        }
//    	       for(PhiOrderDto phiOrderDto:newList){   	    	  
//    	    	   PhiMemberDto phiMemberDto = phiMemberService.getPhiMemberDtoById(phiOrderDto.getPhiMember().getId());
//    	    	   PhiWinnersListDto phiWinnersListDto = new PhiWinnersListDto();     	    	   
//    	    	   phiWinnersListDto.setMobile(phiMemberDto.getTel());
//    	    	   phiWinnersListDto.setMemberId(phiMemberDto.getId());
//    	    	   phiWinnersListDto.setProductId(id);
//    	    	   phiWinnersListDto.setUserName(phiMemberDto.getRealName());
//    	    	   phiWinnersListDto.setUserType("0");
//    	    	   phiWinnersListDto.setWinnersType("1");//随机用户    	    	       	    	  
//    	    	   phiWinnersListDtoList.add(phiWinnersListDto);   	           
//    	           }     	    	
//    	       
//               phiWinnersService.saveAllWinnersList(phiWinnersListDtoList);
               
//         return null;        
     }
     
     
     @RequestMapping(value = "/fake/{id}/{count}")
     @ResponseBody
     public List<PhiWinnersListDto> fakeToWinner(@PathVariable("id") Long id,@PathVariable("count") int count)  { 	             
    	 List<PhiWinnersListDto> phiWinnersListDtoList = new ArrayList<PhiWinnersListDto>();
    	 //找的所有的虚拟用户
    	 List<PhiVirtualUser> phiVirtualUsers = phiWinnersService.getVirtualUsersByCount(count);
            for(PhiVirtualUser phiVirtualUser : phiVirtualUsers){
            	PhiWinnersListDto phiWinnersListDto = new PhiWinnersListDto();
            	phiWinnersListDto.setProductId(id);
            	phiWinnersListDto.setUserName(phiVirtualUser.getUserName());
            	phiWinnersListDto.setMobile(phiVirtualUser.getMobile());
            	phiWinnersListDto.setUserType("1");
            	phiWinnersListDto.setWinnersType("2");
            	phiWinnersListDtoList.add(phiWinnersListDto);
            }       	 
//            phiWinnersService.saveAllWinnersList(phiWinnersListDtoList); 
         return  phiWinnersListDtoList;      
     }
  
     @RequestMapping(value = "/clean/{id}",method = RequestMethod.POST)
     @ResponseBody
     public ResponseEntity<ResponseMessage> cleanWinners(@PathVariable("id") Long id)  { 	             
    	 phiWinnersService.deleteAllWinnersByProductId(id);  	    	 
         return new ResponseEntity<>(ResponseMessage.success("已重置"), HttpStatus.CREATED);        
     }
     /** 
    * @Title: createPhiWinnersDto 
    * @Description: 添加PhiWinners 
    * @param    phiWinnersDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> createPhiWinnersDto(@RequestBody PhiWinnersListDto phiWinnersDto) {
        phiWinnersService.savePhiWinnersDto(phiWinnersDto);
        return new ResponseEntity<>(ResponseMessage.success("PhiWinners创建成功"), HttpStatus.CREATED);
    }
    
    /** 
    * @Title: getPhiWinnersDto 
    * @Description: 获取需要修改 MdmLineBaseInfo信息
    * @createDate: 2016年4月25日 下午1:49:40
    * @param    id
    * @return   ResponseEntity<PhiWinnersDto>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PhiWinnersListDto> getPhiWinnersDto(@PathVariable("id") Long id) {
    	PhiWinnersListDto phiWinnersDto = phiWinnersService.getPhiWinnersDtoById(id);
        return new ResponseEntity<>(phiWinnersDto, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/queryMember/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<ParamDto> getAllMemberDto(@PathVariable("id") Long id) {
    	   List<ParamDto> paramDtolist = new ArrayList<ParamDto>();    	   
    	   List<Map<Long, String>> phiWinnersDto = phiWinnersService.gerAllMemberByProductId(id);
    	        for(Map map : phiWinnersDto){
    	        	ParamDto paramDto = new ParamDto(); 
    	        	Iterator<Map.Entry<Long, String>> it = map.entrySet().iterator();
			        while (it.hasNext()) {
			              Map.Entry<Long, String> entry = it.next();			             
			                paramDto.setCode(entry.getKey().toString());
					    	paramDto.setName(entry.getValue());
					    	paramDtolist.add(paramDto) ;	
    	        }
         
       }
    	    return paramDtolist;
    }
    
    /** 
    * @Title: editPhiWinners 
    * @Description:修改PhiWinners信息 
    * @createDate: 2016年4月25日 下午1:49:25
    * @param    id
    * @param    phiWinnersDto
    * @return   ResponseEntity<ResponseMessage>    
    */ 
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseMessage> editPhiWinners(@PathVariable("id") Long id, @RequestBody PhiWinnersListDto phiWinnersDto) {
        phiWinnersService.updatePhiWinners(id, phiWinnersDto);
        return new ResponseEntity<>(ResponseMessage.success("修改成功"), HttpStatus.OK);
    }

    /** 
    * @Title: deletePhiWinnersById 
    * @Description: 根据ID删除MdmLineBaseInfo信息. 
    * @param   id
    * @return  ResponseEntity<ResponseMessage>    
    */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<ResponseMessage> deletePhiWinnersById(@PathVariable("id") Long id) {
        phiWinnersService.deletePhiWinners(id);
        return new ResponseEntity<>(ResponseMessage.success("删除成功"), HttpStatus.OK);
    }
    
    
    
}
