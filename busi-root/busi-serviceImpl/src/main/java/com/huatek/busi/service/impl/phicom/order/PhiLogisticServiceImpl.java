package com.huatek.busi.service.impl.phicom.order;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.order.PhiLogisticDao;
import com.huatek.busi.dao.phicom.order.PhiOrderDao;
import com.huatek.busi.dto.phicom.order.Mylogisticdto;
import com.huatek.busi.dto.phicom.order.PhiLogisticDto;
import com.huatek.busi.model.phicom.order.PhiLogistic;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.service.phicom.order.PhiLogisticService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.ExpressUtils;
import com.huatek.frame.core.util.ExpressUtils.ExpressItemVO;
import com.huatek.frame.core.util.ExpressUtils.ExpressVO;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiLogisticServiceImpl")
@Transactional
public class PhiLogisticServiceImpl implements PhiLogisticService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiLogisticServiceImpl.class);
	
	@Autowired
	PhiLogisticDao phiLogisticDao;
	
	@Autowired
	PhiOrderDao phiOrderDao;
	
	@Override
	public void savePhiLogisticDto(PhiLogisticDto entityDto)  {
		log.debug("save phiLogisticDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiLogistic entity = BeanCopy.getInstance().convert(entityDto, PhiLogistic.class);
		//保存之前操作
		//beforeSave(entityDto, entity);
		//进行持久化保存
		/*PhiLogistic entitys = new  PhiLogistic();
		entitys.setCom("kjkdjkjd");
		entitys.setComname("shunfeng");
		entitys.setMemberId(Long.parseLong("1"));
		entitys.setSendPhone("1111111");*/
	 	phiLogisticDao.persistentPhiLogistic(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiLogisticDto getPhiLogisticDtoById(Long id) {
		log.debug("get phiLogistic by id@" + id);
		PhiLogistic entity = phiLogisticDao.findPhiLogisticById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		PhiLogisticDto entityDto = BeanCopy.getInstance().convert(entity, PhiLogisticDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiLogistic(Long id, PhiLogisticDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiLogistic entity = phiLogisticDao.findPhiLogisticById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiLogisticDao.persistentPhiLogistic(entity);
	}
	
	
	
	@Override
	public void deletePhiLogistic(Long id) {
		log.debug("delete phiLogistic by id@" + id);
		beforeRemove(id);
		PhiLogistic entity = phiLogisticDao.findPhiLogisticById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiLogisticDao.deletePhiLogistic(entity);
	}
	
	@Override
	public DataPage<PhiLogisticDto> getAllPhiLogisticPage(QueryPage queryPage) {
		DataPage<PhiLogistic> dataPage = phiLogisticDao.getAllPhiLogistic(queryPage);
		DataPage<PhiLogisticDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiLogisticDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiLogisticDto> getAllPhiLogisticDto() {
		List<PhiLogistic> entityList = phiLogisticDao.findAllPhiLogistic();
		List<PhiLogisticDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiLogisticDto.class);
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
	* @param    phiLogisticDto
	* @param    phiLogistic
	* @return  void    
	* @
	*/
	private void beforeSave(PhiLogisticDto entityDto, PhiLogistic entity) {

	}


	@Override
	public void Autologistic() {
		
		List<PhiLogistic> entityList = phiLogisticDao.findAllPhiLogistic();
		ExpressUtils express = new ExpressUtils();
		for(PhiLogistic philogistic : entityList){
			Long orderId = philogistic.getOrderId();
			PhiOrder order = phiOrderDao.findPhiOrderById(orderId);//找到订单修改相应订单状态
			String com= philogistic.getCom();//获取快递公司编号
			String nu= philogistic.getNu();//获取快递编号
			ExpressVO data = express.query(com, nu);//获取物流信息
			/**
	         * 查询结果状态：
	         * 0：物流单暂无结果，
	         * 1：查询成功，
	         * 2：接口出现异常，
	         */
			if(null!=data){
				if("2".equals(data.getStatus())){
					System.out.print("=============接口出现异常================");
				}
				if("1".equals(data.getStatus())){
					/**
			         * 快递单当前的状态 ：
			         * 0：在途，即货物处于运输过程中；
			         * 1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息；
			         * 2：疑难，货物寄送过程出了问题；
			         * 3：签收，收件人已签收；
			         * 4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收；
			         * 5：派件，即快递正在进行同城派件；
			         * 6：退回，货物正处于退回发件人的途中；
			         */
					if("3".equals(data.getState())){
						//如果为订单签收，则修改订单状态为已完成
						if(null!=order){
							order.setStatus("4"); //状态修改为已完成
							phiOrderDao.saveOrUpdatePhiOrder(order);
						}
					}
				}
			}
			
		}

		System.out.print("=============查看物流更新订单状态================");
		
	}
	
	public static void main(String[] args) {
		ExpressUtils express = new ExpressUtils();
		express.query("zhongtong", "475773672056");
		System.out.println(express.query("zhongtong", "475773672056"));

	}


	@Override
	public ExpressVO getLogisticDetial(Long id) {
		PhiLogistic entity = phiLogisticDao.findPhiLogisticByOrderId(id);
		if(null!=entity){
			ExpressUtils express = new ExpressUtils();
			String com= entity.getCom();//获取快递公司编号
			String nu= entity.getNu();//获取快递编号
			ExpressVO data = express.query(com, nu);//获取物流信息
			return data;
		}
		return null;
	}

	/**
	 * 解析快递100的物流信息
	 */
	@Override
	public List<Mylogisticdto> getjson(ExpressVO logistic) {
		List<Mylogisticdto> mylogisticList = new ArrayList<Mylogisticdto>();
		if("200".equals(logistic.getStatus())){
			List<ExpressItemVO> data = logistic.getData();
			for(ExpressItemVO item:data){
				Mylogisticdto mylogisticdto = new Mylogisticdto();
				mylogisticdto.setStates(logistic.getState());
				mylogisticdto.setTime(item.getTime());
				mylogisticdto.setContext(item.getContext());
				mylogisticList.add(mylogisticdto);
			}
			return mylogisticList;
		}
		return null;
	}


}
