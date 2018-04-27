package com.huatek.busi.service.impl.quality;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualityRectificationDao;
import com.huatek.busi.dao.quality.BusiQualityRectificationDetailDao;
import com.huatek.busi.dto.quality.BusiQualityRectificationDetailDto;
import com.huatek.busi.model.quality.BusiQualityRectificationDetail;
import com.huatek.busi.service.quality.BusiQualityRectificationDetailService;
import com.huatek.cmd.service.FwCategoryService;
import com.huatek.cmd.service.FwpropertyService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.frame.authority.service.OperationService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.workflow.service.WorkFlowRpcService;
import com.huatek.workflow.service.WorkflowService;

@Service("busiQualityRectificationDetailServiceImpl")
@Transactional
public class BusiQualityRectificationDetailServiceImpl implements BusiQualityRectificationDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualityRectificationDetailServiceImpl.class);
	
	@Autowired
	private BusiQualityRectificationDetailDao busiQualityRectificationDetailDao;
	@Autowired
	private RpcProxy rpcProxy;
	@Autowired
	private OperationService operationService;
	
	@Override
	public void saveBusiQualityRectificationDetailDto(BusiQualityRectificationDetailDto entityDto)  {
		log.debug("save busiQualityRectificationDetailDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		BusiQualityRectificationDetail entity = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
						.addFieldMap("rectificationId", "rectification")//
//						.addConvertParam(ConvertParam.falseValue, "0").addConvertParam(ConvertParam.trueValue, "1")//
						.convert(entityDto, BusiQualityRectificationDetail.class);
		entity.setTenantId(ThreadLocalClient.get().getOperator().getTenantId());
		//进行持久化保存
		busiQualityRectificationDetailDao.persistentBusiQualityRectificationDetail(entity);
		operationService.logOperation("整改明细保存");
		log.debug("saved entityDto id is @" + entity.getId());
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("result", entityDto.getDetailFlowResult());
		variables.put("resultMessage", entityDto.getRectificationDescription());
		//流程推进
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
		workFlowRpcService.complete(entityDto.getTaskId(), variables);
	}
	
	
	@Override
	public BusiQualityRectificationDetailDto getBusiQualityRectificationDetailDtoById(Long id) {
		log.debug("get busiQualityRectificationDetail by id@" + id);
		BusiQualityRectificationDetail entity = busiQualityRectificationDetailDao.findBusiQualityRectificationDetailById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualityRectificationDetailDto entityDto = DTOUtils.map(entity, BusiQualityRectificationDetailDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualityRectificationDetail(Long id, BusiQualityRectificationDetailDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualityRectificationDetail entity = busiQualityRectificationDetailDao.findBusiQualityRectificationDetailById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiQualityRectificationDetailDao.persistentBusiQualityRectificationDetail(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualityRectificationDetail(Long id) {
		log.debug("delete busiQualityRectificationDetail by id@" + id);
		beforeRemove(id);
		BusiQualityRectificationDetail entity = busiQualityRectificationDetailDao.findBusiQualityRectificationDetailById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualityRectificationDetailDao.deleteBusiQualityRectificationDetail(entity);
	}
	
	@Override
	public DataPage<BusiQualityRectificationDetailDto> getAllBusiQualityRectificationDetailPage(QueryPage queryPage) {
		DataPage<BusiQualityRectificationDetail> dataPage = busiQualityRectificationDetailDao.getAllBusiQualityRectificationDetail(queryPage);
		DataPage<BusiQualityRectificationDetailDto> datPageDto = DTOUtils.mapPage(dataPage, BusiQualityRectificationDetailDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<BusiQualityRectificationDetailDto> getAllBusiQualityRectificationDetailDto() {
		List<BusiQualityRectificationDetail> entityList = busiQualityRectificationDetailDao.findAllBusiQualityRectificationDetail();
		List<BusiQualityRectificationDetailDto> dtos = DTOUtils.mapList(entityList, BusiQualityRectificationDetailDto.class);
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
	* @param    busiQualityRectificationDetailDto
	* @param    busiQualityRectificationDetail
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualityRectificationDetailDto entityDto, BusiQualityRectificationDetail entity) {

	}


	@Override
	public List<BusiQualityRectificationDetailDto> getBusiQualityDetailRecordByRectId(Long rid) {
		List<BusiQualityRectificationDetail> busiQualityRectificationDetail = busiQualityRectificationDetailDao.findQualityDetailRecordByRectId(rid);
		List<BusiQualityRectificationDetailDto> list = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd")//
						.addConvertParam(ConvertParam.falseValue, "0").addConvertParam(ConvertParam.trueValue, "1")//
						.convertList(busiQualityRectificationDetail, BusiQualityRectificationDetailDto.class);
		return list;
	}
}
