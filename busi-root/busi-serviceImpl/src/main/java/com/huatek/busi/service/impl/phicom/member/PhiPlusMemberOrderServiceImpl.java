package com.huatek.busi.service.impl.phicom.member;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.member.PhiMemberDao;
import com.huatek.busi.dao.phicom.member.ViewPhiPlusMemberOrderDao;
import com.huatek.busi.dao.phicom.plusmember.PhiPlusMemberOrderDao;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusMemberOrderDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.plusmember.PhiPlusMemberOrder;
import com.huatek.busi.model.phicom.plusmember.ViewPhiPlusMemberOrder;
import com.huatek.busi.service.phicom.member.PhiPlusMemberOrderService;
import com.huatek.file.excel.exp.conversion.BaseConversionService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("PhiPlusMemberOrderServiceImpl")
@Transactional
public class PhiPlusMemberOrderServiceImpl implements PhiPlusMemberOrderService,BaseConversionService{

	private static final Logger log = LoggerFactory
			.getLogger(PhiPlusMemberOrderServiceImpl.class);

	@Autowired
	PhiPlusMemberOrderDao phiPlusMemberOrderDao;
	@Autowired
	PhiMemberDao phiMemberDao; 
	
	@Autowired
	private ViewPhiPlusMemberOrderDao viewPhiPlusMemberOrderDao;

	@Override
	public PhiPlusMemberOrderDto getphiPlusMemberOrder(String orderNo) {
		PhiPlusMemberOrder entity = phiPlusMemberOrderDao.getphiPlusMemberOrder(orderNo);
		PhiPlusMemberOrderDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusMemberOrderDto.class);
		return entityDto;
	}

	@Override
	public void saveOrUpdatePhiPlusMemberOrder(PhiPlusMemberOrderDto entityDto) {
		log.debug("save phiVirtualUserDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusMemberOrder entity = BeanCopy.getInstance().convert(entityDto, PhiPlusMemberOrder.class);
		//保存之前操作
		//进行持久化保存
		phiPlusMemberOrderDao.saveOrUpdatePhiPlusMemberOrder(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	@Override
	public void savePhiPlusMemberOrderDto(PhiPlusMemberOrderDto entityDto)  {
		log.debug("save phiPlusMemberOrderDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiPlusMemberOrder entity = BeanCopy.getInstance().convert(entityDto, PhiPlusMemberOrder.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiPlusMemberOrderDao.persistentPhiPlusMemberOrder(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiPlusMemberOrderDto getPhiPlusMemberOrderDtoById(Long id) {
		log.debug("get phiPlusMemberOrder by id@" + id);
		PhiPlusMemberOrder entity = phiPlusMemberOrderDao.findPhiPlusMemberOrderById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiPlusMemberOrderDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusMemberOrderDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiPlusMemberOrder(Long id, PhiPlusMemberOrderDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiPlusMemberOrder entity = phiPlusMemberOrderDao.findPhiPlusMemberOrderById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiPlusMemberOrderDao.persistentPhiPlusMemberOrder(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusMemberOrder(Long id) {
		log.debug("delete phiPlusMemberOrder by id@" + id);
		beforeRemove(id);
		PhiPlusMemberOrder entity = phiPlusMemberOrderDao.findPhiPlusMemberOrderById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusMemberOrderDao.deletePhiPlusMemberOrder(entity);
	}
	
	@Override
	public DataPage<PhiPlusMemberOrderDto> getAllPhiPlusMemberOrderPage(QueryPage queryPage) {
//		DataPage<PhiPlusMemberOrder> dataPage = phiPlusMemberOrderDao.getAllPhiPlusMemberOrder(queryPage);
		/*if(null!=dataPage.getContent()){
			for(int i=0;i<dataPage.getContent().size();i++){
				PhiPlusMemberOrder plusMemberOrder = dataPage.getContent().get(i);
				if(null!=plusMemberOrder){
					BigDecimal realMoney = new BigDecimal(0);
					if("1".equals(plusMemberOrder.getIsPay())){
						BigDecimal countMoney=plusMemberOrder.getCountMoney(); 
						BigDecimal payMoney=new BigDecimal(plusMemberOrder.getPayMoney()); 
					    realMoney = countMoney.add(payMoney);
					}
					PhiPlusMemberOrderDto plusMemberOrderDto = datPageDto.getContent().get(i);
					plusMemberOrder.setRealMoney(realMoney);
					if(null!=plusMemberOrderDto){
						if(null!=plusMemberOrder.getMemberId()){
							Long memberId = plusMemberOrder.getMemberId();
							PhiMember entity = phiMemberDao.findPhiMemberById(memberId);
							plusMemberOrderDto.setMemberTel(entity.getTel());
						}
						plusMemberOrderDto.setRealMoney(realMoney);
						
					}
					
				}
				
			}
		}*/
		//优化上面代码-减少IO操作  Edit By Mickey 2018-04-08
		/*List<Long> memberIdList = new ArrayList<Long>();
		List<PhiPlusMemberOrder> phiPlusMemberOrderList = dataPage.getContent();
		if(null != phiPlusMemberOrderList && phiPlusMemberOrderList.size() > 0){
        	for(PhiPlusMemberOrder phiPlusMemberOrder:phiPlusMemberOrderList){
        		BigDecimal realPayMoney = new BigDecimal(phiPlusMemberOrder.getPayMoney());//付款金额=实际支付金额+优惠金额(默认为实际支付金额)
        		if(null != phiPlusMemberOrder.getCountMoney()){
        			realPayMoney = realPayMoney.add(phiPlusMemberOrder.getCountMoney());
        		}
        		phiPlusMemberOrder.setRealMoney(realPayMoney);
        		memberIdList.add(phiPlusMemberOrder.getMemberId());
        	}
        }
		DataPage<PhiPlusMemberOrderDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusMemberOrderDto.class);
		Map<Long,PhiMember> phiMemberMapList = phiMemberDao.findPlusPhiMemberByIds(memberIdList);//根据分页查到的会员数据查询会员信息
		List<PhiPlusMemberOrderDto> phiPlusMemberOrderDtoList = datPageDto.getContent();
		if(null != phiPlusMemberOrderDtoList && phiPlusMemberOrderDtoList.size() > 0){
        	for(PhiPlusMemberOrderDto phiPlusMemberOrderDto:phiPlusMemberOrderDtoList){
        		if(phiMemberMapList.containsKey(phiPlusMemberOrderDto.getMemberId())){
        			PhiMember phiMember = phiMemberMapList.get(phiPlusMemberOrderDto.getMemberId());
        			phiPlusMemberOrderDto.setMemberTel(phiMember.getTel());
        			phiPlusMemberOrderDto.setPlusCode("会员399");//货品明细（会员399 字段固定）、
        		}
        	}
		}*/
		//优化上面代码-减少IO操作  Edit By Mickey 2018-04-08
		DataPage<ViewPhiPlusMemberOrder> dataPage = viewPhiPlusMemberOrderDao.getAllViewPhiPlusMemberOrder(queryPage);
		/*List<ViewPhiPlusMemberOrder> phiPlusMemberOrderList = dataPage.getContent();
		if(null != phiPlusMemberOrderList && phiPlusMemberOrderList.size() > 0){
        	for(ViewPhiPlusMemberOrder phiPlusMemberOrder:phiPlusMemberOrderList){
        		BigDecimal realPayMoney = new BigDecimal(phiPlusMemberOrder.getPayMoney());//付款金额=实际支付金额+优惠金额(默认为实际支付金额)
        		if(null != phiPlusMemberOrder.getCountMoney()){
        			realPayMoney = realPayMoney.add(phiPlusMemberOrder.getCountMoney());
        		}
        		phiPlusMemberOrder.setRealMoney(realPayMoney);
        		phiPlusMemberOrder.setPlusCode("会员399");//货品明细（会员399 字段固定）
        	}
        }*/
		DataPage<PhiPlusMemberOrderDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusMemberOrderDto.class);
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusMemberOrderDto> getAllPhiPlusMemberOrderDto() {
		List<PhiPlusMemberOrder> entityList = phiPlusMemberOrderDao.findAllPhiPlusMemberOrder();
		List<PhiPlusMemberOrderDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusMemberOrderDto.class);
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
	* @param    phiPlusMemberOrderDto
	* @param    phiPlusMemberOrder
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusMemberOrderDto entityDto, PhiPlusMemberOrder entity) {

	}

	@Override
	public void saveRemark(Long id, String remark) {
		PhiPlusMemberOrder entity = phiPlusMemberOrderDao.findPhiPlusMemberOrderById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		entity.setRemark(remark);		
		phiPlusMemberOrderDao.saveOrUpdatePhiPlusMemberOrder(entity);
	}
	
	@Override
	public Map<String, Object> conversionParmas(Map<String, Object> parmas) {
		if(parmas!=null && parmas.size()>0){
			if(null!=parmas.get("payTime")){
				String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
				SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
				Date startPayTime = null;
				try {
					startPayTime = format.parse(parmas.get("payTime").toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parmas.put("startPayTime", startPayTime);
				parmas.remove("payTime");
			}
			if(null!=parmas.get("payTime_1")){
				String pattern = "yyy-MM-dd HH:mm:ss"; //首先定义时间格式
				SimpleDateFormat format = new SimpleDateFormat(pattern);//然后创建一个日期格式化类
				Date endPayTime = null;
				try {
					endPayTime = format.parse(parmas.get("payTime_1").toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				parmas.put("endPayTime", endPayTime);
				parmas.remove("payTime_1");
			}
		}
		return parmas;
	}

	@Override
	public List<Object[]> conversionResults(List<Object[]> resultsList,
			Map<String, Object> parmas) {
		if(null != resultsList && resultsList.size() > 0 ){
			for (Object[] objects : resultsList) {
				if (null != objects[3]) {//处理时间
					objects[3] = objects[3].toString().replace(".0", "");
					
				}
				if (null != objects[7]) {//是否为会员转换
					if("1".equals(objects[7].toString())){
						objects[7] = "是";
					}else {
						objects[7] = "否";
					}
					
				}
				if (null != objects[8]) {//是否为会员转换
					if("1".equals(objects[8].toString())){
						objects[8] = "微信";
					}else {
						objects[8] = "支付宝";
					}
					
				}
			}
		}
		return resultsList;
	}

}
