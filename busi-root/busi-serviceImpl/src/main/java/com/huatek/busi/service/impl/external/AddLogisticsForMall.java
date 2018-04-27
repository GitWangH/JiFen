package com.huatek.busi.service.impl.external;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.phicom.order.PhiLogisticDao;
import com.huatek.busi.dao.phicom.order.PhiOrderDao;
import com.huatek.busi.dto.external.ExternalWithDataResponse;
import com.huatek.busi.dto.phicom.order.PhiLogisticDto;
import com.huatek.busi.dto.phicom.order.PhiOrderDto;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.order.PhiLogistic;
import com.huatek.busi.model.phicom.order.PhiOrder;
import com.huatek.busi.service.external.BusiQualityExternalForPiccomService;
import com.huatek.busi.service.phicom.member.PhiMemberService;
import com.huatek.busi.service.phicom.order.PhiLogisticService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.frame.core.beancopy.BeanCopy;

/**
 * 接口 订单物流添加
 * @author eli_cui
 *
 */
@Service("AddLogisticsForMall")
@Transactional
public class AddLogisticsForMall implements BusiQualityExternalForPiccomService{
	
	@Autowired
	PhiLogisticService logisticService;
	
	@Autowired
	PhiOrderService orderService;
	
	@Autowired
	PhiLogisticDao logisticDao;
	
	
	@Autowired
	PhiOrderDao orderDao;
	
	@Autowired
	PhiMemberService phiMemberService;

	
	@SuppressWarnings({ "null" })
	@Override
	public ExternalWithDataResponse receiveData(String busiType, String appKey, String logisticJson, Date timestamp) {
		JSONObject dataJson = null;
		PhiOrderDto orderdto = null;
		PhiLogisticDto logisticdto = null;
		//PhiLogistic logisticEntity = null;
		PhiOrder orderEntity = null;
		try {
			dataJson = JSONObject.fromObject(logisticJson); 
		} catch (Exception e) {
			System.out.println("参数解析失败！");
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "参数解析失败！，" + e.toString());
		}
		
		try {
			switch (busiType) {
			//新增(订单物流添加)
			case Constant.LOGISTIC_ADD :
				//查看添加物流的订单信息是否存在
				orderEntity = orderDao.findPhiOrderByOrderCode(dataJson.getString("Order_code"));
				//orderEntity = orderDao.findPhiOrderById(dataJson.getLong("Order_code"));
				//如果要添加的物流信息对应订单不存在
				if(null == orderEntity||"1".equals(orderEntity.getIsdelete())){
					System.out.println("该订单不存在");
					return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "该订单不存在无法添加相应物流信息");
				}
				//订单物流信息已经存在且该订单未被取消
				else if(null!=orderEntity.getPhiLogistic()&&"0".equals(orderEntity.getIsdelete())){
					System.out.println("订单数据已存在不允许重复添加");
					return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "订单数据已存在不允许重复添加");
				}else{
					//保存订单物流信息
					logisticdto = (PhiLogisticDto) dataJson.toBean(dataJson, PhiLogisticDto.class);
					logisticdto.setOrderId(orderEntity.getId());
					logisticdto.setOrderinfoId(orderEntity.getPhiOrderInfo().getId());
					int uid = dataJson.getInt("UID");
				    PhiMember phiMember = phiMemberService.GetMemberPhiMemberByUid(uid);
		            Long memberId =  phiMember.getId();
					logisticdto.setMemberId(memberId);
					logisticdto.setComname(dataJson.getString("comname"));
					logisticdto.setCom(dataJson.getString("com"));
					logisticdto.setNu(dataJson.getString("nu"));
					logisticdto.setSender(dataJson.getString("Senderperson"));
					logisticdto.setSendPhone(dataJson.getString("Sendertel"));
					logisticdto.setSendTime(dataJson.getString("sendertime"));
					PhiLogistic entity = BeanCopy.getInstance().convert(logisticdto, PhiLogistic.class);
					logisticDao.saveOrUpdatePhiLogistic(entity);
//					PhiLogisticDto savelogisticdto = BeanCopy.getInstance().convert(entity, PhiLogisticDto.class);
					//logisticService.savePhiLogisticDto(logisticdto);//新增一条物流信息
					orderEntity.setPhiLogistic(entity);
					orderEntity.setStatus("3");//修改订单状态为代收货
//					orderdto = orderService.getPhiOrderDtoById(dataJson.getLong("Order_code"));
//					orderdto.setPhiLogistic(savelogisticdto);
//					orderdto.setStatus("3");//修改订单状态为代收货
//					orderService.updatePhiOrder(dataJson.getLong("Order_code"),orderdto);
//					BeanCopy.getInstance().mapIgnoreNull(orderdto, orderEntity);
					orderDao.saveOrUpdatePhiOrder(orderEntity);//更新订单信息
					break;
					
				}
			default:
				System.out.println("参数 \"operateType\" 校验出错");
				return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "参数 \"operateType\" 校验出错。");
			}
		} catch (Exception e) {
			System.out.println("数据保存出错。");
			return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.ERROR, "数据保存出错。");
		}
		
		return ExternalUtil.getExternalWithDataResponse(Constant.externalStatusCode.SUCCESS, "同步物流信息成功");
	}
	
	@Override
	public Map<String,String>  getType() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("synchLogistics", Constant.LOGISTIC_ADD);
		return map;
	}

}
