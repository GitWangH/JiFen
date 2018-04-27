   package com.huatek.busi.service.impl.phicom.winner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dao.phicom.order.PhiOrderDao;
import com.huatek.busi.dao.phicom.product.PhiProductDao;
import com.huatek.busi.dao.phicom.winner.PhiWinnersDao;
import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.dto.phicom.winner.PhiVirtualUser;
import com.huatek.busi.dto.phicom.winner.PhiWinnersListDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.phicom.winner.PhiWinners;
import com.huatek.busi.model.phicom.winner.VirtualUser;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.busi.service.phicom.winners.PhiWinnersService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiWinnersServiceImpl")
@Transactional
public class PhiWinnersServiceImpl implements PhiWinnersService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiWinnersServiceImpl.class);
	
	
	@Autowired
	PhiProductDao phiProductDao;
	
	@Autowired
	PhiWinnersDao phiWinnersDao;
	
	@Autowired
	PhiMemberDao phiMemberDao;
	
	@Autowired
	PhiOrderDao  phiOrderDao;
	
	@Autowired
    PhiOrderService phiOrderService;
	
	@Override
	public void savePhiWinnersDto(PhiWinnersListDto entityDto)  {
		log.debug("save phiWinnersDto @" + entityDto);
		
		//根据页面传进来的值设置保存的值信息
		PhiWinners entity = BeanCopy.getInstance().convert(entityDto, PhiWinners.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiWinnersDao.persistentPhiWinners(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiWinnersListDto getPhiWinnersDtoById(Long id) {
		log.debug("get phiWinners by id@" + id);
		PhiWinners entity = phiWinnersDao.findPhiWinnersById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiWinnersListDto entityDto = BeanCopy.getInstance().convert(entity, PhiWinnersListDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiWinners(Long id, PhiWinnersListDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiWinners entity = phiWinnersDao.findPhiWinnersById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiWinnersDao.persistentPhiWinners(entity);
	}
	
	
	
	@Override
	public void deletePhiWinners(Long id) {
		log.debug("delete phiWinners by id@" + id);
		beforeRemove(id);
		PhiWinners entity = phiWinnersDao.findPhiWinnersById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiWinnersDao.deletePhiWinners(entity);
	}
	
	@Override
	public DataPage<PhiWinnersListDto> getAllPhiWinnersPage(QueryPage queryPage) {
		DataPage<PhiWinners> dataPage = phiWinnersDao.getAllPhiWinners(queryPage);		
		DataPage<PhiWinnersListDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiWinnersListDto.class);

		   if(dataPage != null && dataPage.getContent() != null){
			   for(int i = 0;i<dataPage.getContent().size();i++){
				    if(null != dataPage.getContent().get(i).getId()){
				    	datPageDto.getContent().get(i).setId( dataPage.getContent().get(i).getId()); 	
				    }
				   
			   }
		   }
		 

		for(int i=0;i<datPageDto.getContent().size();i++){
			Long productId = datPageDto.getContent().get(i).getProductId();
			PhiProduct phiProduct = phiProductDao.findPhiProductById(productId);
			if(null!=phiProduct){
				datPageDto.getContent().get(i).setProductName(phiProduct.getProductName());
			}
		}

		return datPageDto;
	}
	
	@Override
	public List<PhiWinnersListDto> getAllPhiWinnersDto() {
		List<PhiWinners> entityList = phiWinnersDao.findAllPhiWinners();
		List<PhiWinnersListDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiWinnersListDto.class);
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
	* @param    phiWinnersDto
	* @param    phiWinners
	* @return  void    
	* @
	*/
	private void beforeSave(PhiWinnersListDto entityDto, PhiWinners entity) {

	}


	@Override
	public void saveAllWinnersList(List<PhiWinnersListDto> list) {
		List<PhiWinners>  listPhiWinners=	BeanCopy.getInstance().convertList(list, PhiWinners.class);
		phiWinnersDao.saveWinnerList(listPhiWinners);		
	}


	@Override
	public List<Map<Long,String>> gerAllMemberByProductId(Long productId) {
	       List<PhiMember> orderlist = phiWinnersDao.getAllMemberByProductId(productId);
	      // List<PhiOrderDto> orderDtolist =  BeanCopy.getInstance().convertList(orderlist, PhiOrderDto.class);
		    List<Map<Long, String>>  orderMemberList = new ArrayList<Map<Long,String>>();
	           for(PhiMember phiMember : orderlist){
	        	  Map map = new HashMap<Long, String>();
	        	  if(phiMember.getId() !=null){
	        		  map.put(phiMember.getId(), phiMember.getTel());
		        	  orderMemberList.add(map);
	        	  }
	        	 
	           }
	       return orderMemberList;
	}
	


	@Override
	public List<PhiOrderDto> getAllOrderById(Long id) {
		List<PhiOrder> orderlist = phiWinnersDao.getAllOrderByProductId(id);
		List<PhiOrderDto> phiOrderDtoList = new ArrayList<PhiOrderDto>();
        BeanCopy.getInstance().convertList(orderlist, PhiOrderDto.class);
		return phiOrderDtoList;
	}


	@Override
	public List<PhiVirtualUser> getVirtualUsersByCount(int count) {
		
	List<VirtualUser> virtualUsers = phiWinnersDao.getAllVirtualUsers();
	        /*if(virtualUsers.size()<count){
	        	throw new BusinessRuntimeException("虚拟用户人数过多", "-1");
	        }*/
	  List<VirtualUser> ret = new ArrayList<VirtualUser>();
	  List<Integer> integers = new ArrayList<Integer>();
	        if(virtualUsers.size()>0){//虚拟用户人数不为空
	    	   Random r = new Random();  	    	   
		                Integer index = 0;		                
		                for (int i = 0; i < count; i++) {	
		                	if(virtualUsers.size() >i){
						                    index = r.nextInt(virtualUsers.size());
						                	if(integers.contains(index)){		                	
						                		--i;
						                		continue;
						                	}else {
						                		integers.add(index);
					                		    ret.add(virtualUsers.get(index));
						                	}
		                	   }
		                		
		                	}		                    		                    	       	              	       
		                }else{
	    	   throw new BusinessRuntimeException("虚拟用户人数为零", "-1");
	       }
	               
	                
	                
     List<PhiVirtualUser> phiVirtualUsers = BeanCopy.getInstance().convertList(ret, PhiVirtualUser.class);
	        
		return phiVirtualUsers;
	}


	@Override
	public void deleteAllWinnersByProductId(Long id) {
		phiWinnersDao.cleanAllWinnersByProductId(id);
	}


	@Override
	public int getOrderCountByProductId(Long id) {
    List<PhiMember> orderslist = phiWinnersDao.getAllMemberByProductId(id);       
		return orderslist.size();
	}


	@Override
	public void updateProductWinnersStatus(Long id) {
	   PhiProduct phiProduct = 	phiProductDao.findPhiProductById(id);
	       phiProduct.setWinnerStatus("2");//winnerStatus 1 未开奖2已开奖 
	   phiProductDao.persistentPhiProduct(phiProduct);
		
	}


	@Override
	public List<PhiWinnersListDto> getWinnersByProductId(Long id) {
		List<PhiWinners> phiWinners =   phiWinnersDao.getAllRandomMemberByProductId(id);
		List<PhiWinnersListDto> phiWinnersListDtoList = BeanCopy.getInstance().convertList(phiWinners,PhiWinnersListDto.class);
		return phiWinnersListDtoList;
	}



	@Override
	public List<PhiOrderDto> gerAllRandomMemberByProductId(Long productId) {
		 List<PhiOrder> phiOrderslist =  phiWinnersDao.getRandomMemberByProductId(productId);
		 List<PhiOrderDto> phiOrderDaos = BeanCopy.getInstance().convertList(phiOrderslist, PhiOrderDto.class);
		
		 return phiOrderDaos;
	}


	@Override
	public List<PhiWinnersListDto> getMemberByProductIdandMemberId(Long productId,
			Long MemberId) {
		List<PhiWinners>  phiWinnerslistList =  phiWinnersDao.getPhiWinnersByProductIdAndMemberId(productId,MemberId);
		List<PhiWinnersListDto> phiWinnersListDtoList = BeanCopy.getInstance().convertList(phiWinnerslistList,PhiWinnersListDto.class);
		return phiWinnersListDtoList;
	}


	@Override
	public List<PhiWinnersListDto> getRandomWinners(Long productId,List<Long> idsList,int count) {
		List<PhiMember> memberList =  phiMemberDao.getRandomWinners(productId,idsList,count);
		List<PhiWinnersListDto> winnerDtoList = new ArrayList<PhiWinnersListDto>();
		if(memberList != null && memberList.size() > 0){
			for(int i = 0 ; i < memberList.size() ; i ++){
				PhiWinnersListDto dto = new PhiWinnersListDto();
				dto.setMemberId(memberList.get(i).getId());
				dto.setMobile(memberList.get(i).getTel());
				dto.setProductId(productId);
				dto.setUserName(memberList.get(i).getUserName());
				dto.setUserType("0");
				dto.setWinnersType("1");//winnerType 
				winnerDtoList.add(dto);
			}
		}
		return winnerDtoList;
	}



	@Override
	public List<PhiWinnersListDto> getAllPhiWinnersByproductIdDto(Long productId) {
		List<PhiWinners> phiWinners =   phiWinnersDao.getAllPhiWinnersByproductId(productId);
		List<PhiWinnersListDto> phiWinnersListDtoList = BeanCopy.getInstance().convertList(phiWinners,PhiWinnersListDto.class);
		return phiWinnersListDtoList;
	}


	@Override
	public void updateOrderStatus(Long productId, List<PhiWinnersListDto> winnerlist) {
		List<PhiOrder> phiOrders = new ArrayList<PhiOrder>();
		 if(winnerlist.size()>0){
		     for(PhiWinnersListDto phiWinnersListDto :winnerlist){
		    	 Long memId = phiWinnersListDto.getMemberId();
		    	 if(memId != null){
		    		 //虚拟用户的memId是null
		    		 PhiOrder phiOrder = 	phiOrderDao.findPhiOrderByMemberIdAndProductId(productId, phiWinnersListDto.getMemberId());
			           phiOrders.add(phiOrder);
		    	 }		      
             }			 
		 }
		 for(PhiOrder phiOrder :phiOrders ){
			 if(phiOrder.getId()!= null){			
				 phiOrderService.editOrderWin(phiOrder.getId(), productId);
			 }
			 
		 }
		
	        
	}


	@Override
	public DataPage<PhiWinnersListDto> getAllPhiWinnersPageforApp(
			QueryPage queryPage) {
		DataPage<PhiWinners> dataPage = phiWinnersDao.getAllPhiWinners(queryPage);		
		DataPage<PhiWinnersListDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiWinnersListDto.class);
		   if(dataPage != null && dataPage.getContent() != null){
			   for(int i = 0;i<dataPage.getContent().size();i++){
				    if(null != dataPage.getContent().get(i).getId()){
				    	datPageDto.getContent().get(i).setId( dataPage.getContent().get(i).getId()); 
				    	datPageDto.getContent().get(i).setCode(i+1);
				    	 String str = "****";
				    	 PhiWinners winners = dataPage.getContent().get(i);
				    	 if(null!=winners){
				    		 String tel = winners.getMobile();
				    		 //电话号码加*
				    		 if(StringUtils.isNotBlank(tel)){
				    			 StringBuilder sb = new StringBuilder(tel);
					              sb.replace(3, 7, str);
					              datPageDto.getContent().get(i).setMobile(sb.toString());
				    		 }
				    		//姓名加*
				    		 String ustr = "**";
				    		String userNameString =  winners.getUserName();
				    		if(StringUtils.isNotBlank(userNameString)){
				    			  StringBuilder usb = new StringBuilder(userNameString);
				    			  if(2==userNameString.length()){
				    				  usb.replace(1, usb.length(), "*");
				    			  }
				    			  else if(1==userNameString.length()){
				    				  
				    			  }else{
				    				  usb.replace(1, userNameString.length()-1, ustr);
						              
				    			  }
				    			  datPageDto.getContent().get(i).setUserName(usb.toString()); 
				    		 }
				    	 }
				    	 
				    }
				   
			   }
		   }
		 

		for(int i=0;i<datPageDto.getContent().size();i++){
			Long productId = datPageDto.getContent().get(i).getProductId();
			PhiProduct phiProduct = phiProductDao.findPhiProductById(productId);
			if(null!=phiProduct){
				datPageDto.getContent().get(i).setProductName(phiProduct.getProductName());
			}
		}

		return datPageDto;
	}

}
