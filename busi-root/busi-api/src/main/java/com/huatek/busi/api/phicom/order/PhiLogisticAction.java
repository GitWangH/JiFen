package com.huatek.busi.api.phicom.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.huatek.busi.dto.phicom.order.Mylogisticdto;
import com.huatek.busi.dto.phicom.order.PhiLogisticDto;
import com.huatek.busi.service.phicom.order.PhiLogisticService;
import com.huatek.busi.service.phicom.order.PhiOrderService;
import com.huatek.frame.core.ResponseMessage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.ExpressUtils.ExpressVO;

@RestController
@RequestMapping(value = BusiUrlConstants.PHILOGISTIC_API)
public class PhiLogisticAction {

	private static final Logger log = LoggerFactory
			.getLogger(PhiLogisticAction.class);

	@Autowired
	private PhiLogisticService phiLogisticService;
	
	@Autowired
    private PhiOrderService phiOrderService;

	/**
	 * @Title: getAllPhiLogistic
	 * @Description: 翻页查询PhiLogistic信息.
	 * @param queryPage
	 * @return ResponseEntity<DataPage<PhiLogisticDto>>
	 */
	@RequestMapping(value = "/query")
	@ResponseBody
	public ResponseEntity<DataPage<PhiLogisticDto>> getAllPhiLogistic(
			@RequestBody QueryPage queryPage) {
		log.debug("get all phiLogistic of param " + queryPage.getQueryInfo());
		DataPage<PhiLogisticDto> phiLogisticPages = phiLogisticService
				.getAllPhiLogisticPage(queryPage);
		log.debug("get phiLogistic size @"
				+ phiLogisticPages.getContent().size());
		return new ResponseEntity<>(phiLogisticPages, HttpStatus.OK);

	}


	/**
	 * @Title: createPhiLogisticDto
	 * @Description: 添加PhiLogistic
	 * @param phiLogisticDto
	 * @return ResponseEntity<ResponseMessage>
	 */
	@RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PhiLogisticDto> createPhiLogisticDto(
			@PathVariable("id") Long id) {
		PhiLogisticDto phiLogisticDto = phiLogisticService
				.getPhiLogisticDtoById(id);
		return new ResponseEntity<>(phiLogisticDto, HttpStatus.OK);
	}

	/**
	 * @Title: getPhiLogisticDto
	 * @Description: 获取需要修改 MdmLineBaseInfo信息
	 * @createDate: 2016年4月25日 下午1:49:40
	 * @param id
	 * @return ResponseEntity<PhiLogisticDto>
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PhiLogisticDto> getPhiLogisticDto(
			@PathVariable("id") Long id) {
		PhiLogisticDto phiLogisticDto = phiLogisticService
				.getPhiLogisticDtoById(id);
		return new ResponseEntity<>(phiLogisticDto, HttpStatus.OK);
	}

	/**
	 * @Title: getPhiLogisticDto
	 * @Description: 获取需要修改 MdmLineBaseInfo信息
	 * @createDate: 2016年4月25日 下午1:49:40
	 * @param id
	 * @return ResponseEntity<PhiLogisticDto>
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ExpressVO getPhiLogisticDetailApp(@PathVariable("id") Long Orderid) {
		ExpressVO logistic = phiLogisticService.getLogisticDetial(Orderid);
		if(logistic!=null){
			logistic.setCom(getExpressName(logistic.getCom()));
		}
		return logistic;
	}

	/**
	 * @Title: getPhiLogisticDto
	 * @Description: 获取需要修改 MdmLineBaseInfo信息
	 * @createDate: 2016年4月25日 下午1:49:40
	 * @param id
	 * @return ResponseEntity<PhiLogisticDto>
	 */
	@RequestMapping(value = "/detailForAdmin/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<PhiLogisticDto> getPhiLogisticDetail(
			@PathVariable("id") Long id) {
		
		PhiLogisticDto phiLogisticDto = phiLogisticService
				.getPhiLogisticDtoById(id);
		if (null != phiLogisticDto) {
			Long orderId = phiLogisticDto.getOrderId();
			if (null != orderId) {
				ExpressVO logistic = phiLogisticService
						.getLogisticDetial(orderId);
				List<Mylogisticdto> mylogisticdto = phiLogisticService
						.getjson(logistic);
				phiLogisticDto.setMylogisticdto(mylogisticdto);
			}
		}

		return new ResponseEntity<>(phiLogisticDto, HttpStatus.OK);
	}

	/**
	 * @Title: editPhiLogistic
	 * @Description:修改PhiLogistic信息
	 * @createDate: 2016年4月25日 下午1:49:25
	 * @param id
	 * @param phiLogisticDto
	 * @return ResponseEntity<ResponseMessage>
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> editPhiLogistic(
			@PathVariable("id") Long id,
			@RequestBody PhiLogisticDto phiLogisticDto) {
		phiLogisticService.updatePhiLogistic(id, phiLogisticDto);
		return new ResponseEntity<>(ResponseMessage.success("修改成功"),
				HttpStatus.OK);
	}

	/**
	 * @Title: deletePhiLogisticById
	 * @Description: 根据ID删除MdmLineBaseInfo信息.
	 * @param id
	 * @return ResponseEntity<ResponseMessage>
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<ResponseMessage> deletePhiLogisticById(
			@PathVariable("id") Long id) {
		phiLogisticService.deletePhiLogistic(id);
		return new ResponseEntity<>(ResponseMessage.success("删除成功"),
				HttpStatus.OK);
	}
	
	
	public String getExpressName(String key){
		Map<String, String> map=new HashMap<String, String>();
		map.put("youzhengguonei", "邮政包裹/平邮");
		map.put("ems", "EMS");
		map.put("shunfeng", "顺丰");
		map.put("shentong", "申通");
		map.put("yuantong", "圆通");
		map.put("zhongtong", "中通");
		map.put("huitongkuaidi", "汇通");
		map.put("yunda", "韵达");
		map.put("zhaijisong", "宅急送");
		map.put("tiantian", "天天");
		return map.get(key);
	}
}
