package com.huatek.busi.service.impl.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.contract.BusiContractTendersContractDetailApproveFlowInfoDao;
import com.huatek.busi.dao.contract.BusiContractTendersContractDetailDao;
import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractTendersContractDetailDto;
import com.huatek.busi.model.contract.BusiContractTendersContractDetail;
import com.huatek.busi.model.contract.BusiContractTendersContractDetailApproveFlowInfo;
import com.huatek.busi.service.contract.BusiContractTendersContractDetailService;
import com.huatek.cmd.dto.FwPropertyDto;
import com.huatek.cmd.service.FwCategoryService;
import com.huatek.cmd.service.FwpropertyService;
import com.huatek.esb.msg.rpc.RpcProxy;
import com.huatek.file.excel.imp.ImportConfig;
import com.huatek.file.excel.imp.ImportFieldConfig;
import com.huatek.file.excel.imp.persist.IPersistService;
import com.huatek.file.excel.imp.transform.ITransformService;
import com.huatek.file.excel.imp.validate.IValidateService;
import com.huatek.file.excel.imp.validate.ValidateResult;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.service.FwOrgService;
import com.huatek.frame.service.dto.FwOrgDto;
import com.huatek.frame.session.data.UserInfo;
import com.huatek.frame.util.DateUtil;
import com.huatek.workflow.service.IProcessListener;
import com.huatek.workflow.service.WorkFlowRpcService;

/**
 * @ClassName: BusiContractTendersContractDetailServiceImpl
 * @Description: 标段合同表 (施工合同)清单管理服务层接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-01 15:12:35
 * @version: 1.0
 */
@Service("busiContractTendersContractDetailServiceImpl")
@Transactional
public class BusiContractTendersContractDetailServiceImpl implements BusiContractTendersContractDetailService,IProcessListener,IValidateService,ITransformService,IPersistService {
	
	@Autowired
	private BusiContractTendersContractDetailDao busiContractTendersContractDetailDao;
	
	@Autowired
	private BusiContractTendersContractDetailApproveFlowInfoDao busiContractTendersContractDetailApproveFlowInfoDao;
	
	@Autowired
    private RpcProxy rpcProxy;
	
	@Autowired
    private FwCategoryService fwCategoryService;//字典大类Service
	
	@Autowired
    private FwpropertyService fwpropertyService;
	
	@Autowired
    private FwOrgService fwOrgService;//机构服务类
	
	/**
	 * 机构对象的机构ID、机构名称转换至Dto对象的数据常量
	 */
	public static final Map<String,String> entityToFields = new HashMap<String,String>();
	public static final Map<String,String> flowEntityToFields = new HashMap<String,String>();
	static {
		entityToFields.put("busiFwOrg.name", "orgName");
		entityToFields.put("busiFwOrg.id", "orgId");
		
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailApplyTime", "detailApplyTime");// 清单申请时间
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailApplyUserId", "detailApplyUserId");// 清单申请人ID
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailApplyUserName", "detailApplyUserName");// 清单申请人名称
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailApprovalStatus", "detailApprovalStatus");// 清单审批状态
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailApprovalTime", "detailApprovalTime");// 清单审批完成时间
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailApprovalUserId", "detailApprovalUserId");// 清单审批人ID
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailApprovalUserName", "detailApprovalUserName");// 清单审批人名称
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailFlowInstanceId", "detailFlowInstanceId");// 清单流程实例ID
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailFlowResult", "detailFlowResult");// 清单审批结果
		flowEntityToFields.put("contractDetailApproveFlowInfo.detailFlowMessage", "detailFlowMessage");// 清单审批意见
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkApplyTime", "checkApplyTime");// 复核申请时间
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkApplyUserId", "checkApplyUserId");// 复核申请人ID
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkApplyUserName", "checkApplyUserName");// 复核申请人名称
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkApprovalStatus", "checkApprovalStatus");// 复核审批状态
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkApprovalTime", "checkApprovalTime");// 复核审批完成时间
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkApprovalUserId", "checkApprovalUserId");// 复核审批人ID
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkApprovalUserName", "checkApprovalUserName");// 复核审批人名称
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkFlowInstanceId", "checkFlowInstanceId");// 复核流程实例ID
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkFlowResult", "checkFlowResult");// 复核审批结果
		flowEntityToFields.put("contractDetailApproveFlowInfo.checkFlowMessage", "checkFlowMessage");// 复核审批意见
	}
	
	@Override
	public DataPage<BusiContractTendersContractDetailDto> getAllBusiContractTendersContractDetailPage(QueryPage queryPage) {
		DataPage<BusiContractTendersContractDetail> dataPage = busiContractTendersContractDetailDao.getAllBusiContractTendersContractDetail(queryPage);
		DataPage<BusiContractTendersContractDetailDto> datPageDto = BeanCopy.getInstance().addFieldMaps(BusiContractTendersContractServiceImpl.entityToFields).convertPage(dataPage, BusiContractTendersContractDetailDto.class);
		return datPageDto;
	}
	
	/**
	 * 查询合同清单列表树数据
	 * @param queryPage
	 * @return
	 */
	@Override
	public List<BusiContractTendersContractDetailDto> getAllTopLevelBusiContractTendersContractDetailDto(QueryPage queryPage){
		List<QueryParam> paramList = queryPage.getQueryParamList();    	  
  	  	paramList.add(new QueryParam("groupLevel", "long", "=", "1"));
  	    paramList.add(new QueryParam("isDelete", "long", "=", String.valueOf(Constant.DELETE_STATUS_NOT_DELETED)));//未删除
		List<BusiContractTendersContractDetail> busiContractTendersContractDetailList = busiContractTendersContractDetailDao.findAllBusiContractTendersContractDetail(queryPage);
		return BeanCopy.getInstance().addFieldMaps(entityToFields).convertList(busiContractTendersContractDetailList, BusiContractTendersContractDetailDto.class);
	}
	
	/**
	 * 根据父节点查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	@Override
	public List<BusiContractTendersContractDetailDto> getChildBusiContractTendersContractDetailDtoByParentUUID(String parentUUID){
		List<BusiContractTendersContractDetail> busiContractTendersContractDetailList = busiContractTendersContractDetailDao.findChildBusiContractTendersContractDetailDtoByParentUUID(parentUUID);
		return BeanCopy.getInstance().addFieldMaps(entityToFields).convertList(busiContractTendersContractDetailList, BusiContractTendersContractDetailDto.class);
	}
	
	/**
	 * 保存标段合同表 (施工合同)清单列表数据
	 * @param busiContractSupervisorContractDetailDtoList
	 */
	@Override
	public List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId,List<BusiContractTendersContractDetailDto> busiContractTendersContractDetailDtoList,String detaileType)  {
		//根据页面传进来的值设置保存的值信息
		List<BusiContractTendersContractDetailDto> addDtoList = null;
		List<BusiContractTendersContractDetailDto> updateDtoList = null;
		List<BusiContractTendersContractDetailDto> deleteDtoList = null;
		List<BusiContractTendersContractDetail> addEntityList = null;
		if(null != busiContractTendersContractDetailDtoList && busiContractTendersContractDetailDtoList.size() > 0){
			addDtoList = new ArrayList<BusiContractTendersContractDetailDto>();
			updateDtoList = new ArrayList<BusiContractTendersContractDetailDto>();
			deleteDtoList = new ArrayList<BusiContractTendersContractDetailDto>();
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			for(BusiContractTendersContractDetailDto busiContractTendersContractDetailDto:busiContractTendersContractDetailDtoList){
				busiContractTendersContractDetailDto.setOrgId(orgId);
				busiContractTendersContractDetailDto.setWorkabilityQuantity(busiContractTendersContractDetailDto.getQuantity());//新增时，可使用量默认为 合同数量
				busiContractTendersContractDetailDto.setUsedQuantity("0");//已使用量默认0
				
				//默认复核数量为合同数量、复核单价为合同单价、复核总额为合同总额 [0116818: 在合同清单管理中添加的数据，在复核清单管理中应默认把原清单的数据带出 ] Edit By MiCkey 2017-11-28
				busiContractTendersContractDetailDto.setReviewQuantity(busiContractTendersContractDetailDto.getQuantity());
				busiContractTendersContractDetailDto.setReviewUnitPrice(busiContractTendersContractDetailDto.getUnitPrice());
				busiContractTendersContractDetailDto.setReviewTotalPrice(busiContractTendersContractDetailDto.getTotalPrice());
				
				if(busiContractTendersContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_ADD)){
					busiContractTendersContractDetailDto.setDetaileType(detaileType);//复核清单新增
					busiContractTendersContractDetailDto.setCreater(currentUser.getId());// 创建人id
					busiContractTendersContractDetailDto.setCreaterName(currentUser.getUserName());// 创建人姓名
					busiContractTendersContractDetailDto.setCreateTime(DateUtil.timeFormat.format(new Date()));// 创建时间
					busiContractTendersContractDetailDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
					addDtoList.add(busiContractTendersContractDetailDto);
				}else if(busiContractTendersContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_UPDATE)){
					
					busiContractTendersContractDetailDto.setModifer(currentUser.getId());// 修改人id
					busiContractTendersContractDetailDto.setModifierName(currentUser.getUserName());// 修改人姓名
					busiContractTendersContractDetailDto.setModifyTime(DateUtil.timeFormat.format(new Date()));// 修改时间
					updateDtoList.add(busiContractTendersContractDetailDto);
				}else if(busiContractTendersContractDetailDto.getOperation().equals(Constant.OPERATION_TYPE_DELETE)){
					busiContractTendersContractDetailDto.setIsDelete(Constant.DELETE_STATUS_DELETED);//已删除
					deleteDtoList.add(busiContractTendersContractDetailDto);
				}
			}
		}
		//批量保存
		if(null != addDtoList && addDtoList.size() > 0){//批量保存
			addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(addDtoList, BusiContractTendersContractDetail.class);
			//进行持久化保存
			busiContractTendersContractDetailDao.batchSaveBusiContractTendersContractDetailList(addEntityList);;
		}
		//批量更新
		if(null != updateDtoList && updateDtoList.size() > 0){//批量更新
			List<BusiContractTendersContractDetail> updateEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(updateDtoList, BusiContractTendersContractDetail.class);
			//进行持久化保存
			busiContractTendersContractDetailDao.batchUpdateBusiContractTendersContractDetailList(updateEntityList);;
		}
		//批量删除
		if(null != deleteDtoList && deleteDtoList.size() > 0){//批量删除
			List<BusiContractTendersContractDetail> deleteEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(deleteDtoList, BusiContractTendersContractDetail.class);
			//进行持久化保存
			busiContractTendersContractDetailDao.batchDeleteBusiContractTendersContractDetailList(deleteEntityList);;
		}
		//前台返回新增的数据集合
		List<TreeGridAddIdAndUUIDDto> treeGridAddIdAndUUIDDtoList = new ArrayList<TreeGridAddIdAndUUIDDto>();
		if(null != addEntityList && addEntityList.size() > 0){
			for(BusiContractTendersContractDetail entity : addEntityList){
				TreeGridAddIdAndUUIDDto treeGridAddIdAndUUIDDto = new TreeGridAddIdAndUUIDDto();
				treeGridAddIdAndUUIDDto.setId(entity.getId());
				treeGridAddIdAndUUIDDto.setUuid(entity.getUUID());
				treeGridAddIdAndUUIDDtoList.add(treeGridAddIdAndUUIDDto);
			}
			
		} 
		return treeGridAddIdAndUUIDDtoList;
	}

	/**
	 * 保存所选的工程量清单数据
	 */
	@Override
	public List<TreeGridAddIdAndUUIDDto> saveSelecteTreeGridData(Long orgId,List<BusiContractTendersContractDetailDto> busiContractTendersContractDetailDtoList) {
		//清除orgid对应的原始明细数据
		busiContractTendersContractDetailDao.deleteBusiContractTendersContractDetailListByOrgId(orgId);
		//保存之前操作
		for(BusiContractTendersContractDetailDto busiContractTendersContractDetailDto : busiContractTendersContractDetailDtoList){
			//默认复核数量为合同数量、复核单价为合同单价、复核总额为合同总额 [0116818: 在合同清单管理中添加的数据，在复核清单管理中应默认把原清单的数据带出 ] Edit By MiCkey 2017-11-28
			busiContractTendersContractDetailDto.setReviewQuantity(busiContractTendersContractDetailDto.getQuantity());
			busiContractTendersContractDetailDto.setReviewUnitPrice(busiContractTendersContractDetailDto.getUnitPrice());
			busiContractTendersContractDetailDto.setReviewTotalPrice(busiContractTendersContractDetailDto.getTotalPrice());
			
			busiContractTendersContractDetailDto.setWorkabilityQuantity(busiContractTendersContractDetailDto.getQuantity());//新增时，可使用量默认为 合同数量
			busiContractTendersContractDetailDto.setUsedQuantity("0");//已使用量默认0
			beforeSave(orgId, busiContractTendersContractDetailDto,null);
		}
		//根据页面传进来的值设置保存的值信息
		List<BusiContractTendersContractDetail> busiContractTendersContractDetailList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(busiContractTendersContractDetailDtoList, BusiContractTendersContractDetail.class);
		//进行持久化保存
		busiContractTendersContractDetailDao.batchSaveTreeGridData(busiContractTendersContractDetailList);//批量保存
		
		if(busiContractTendersContractDetailList != null){
			List<TreeGridAddIdAndUUIDDto> list = new ArrayList<TreeGridAddIdAndUUIDDto>();
			for(BusiContractTendersContractDetail entity : busiContractTendersContractDetailList){
				TreeGridAddIdAndUUIDDto dto = new TreeGridAddIdAndUUIDDto();
				dto.setId(entity.getId());
				dto.setUuid(entity.getUUID());
				list.add(dto);
			}
			return list;
		} else {
			return null;
		}	
	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiContractTendersContractDto
	* @param    busiContractTendersContract
	* @return  void    
	* @
	*/
	private void beforeSave(Long orgId,BusiContractTendersContractDetailDto busiContractTendersContractDetailDto, BusiContractTendersContractDetail busiContractTendersContractDetail) {
		UserInfo currentUser = ThreadLocalClient.get().getOperator();
		busiContractTendersContractDetailDto.setOrgId(orgId);
		busiContractTendersContractDetailDto.setCreater(currentUser.getId());// 创建人id
		busiContractTendersContractDetailDto.setCreaterName(currentUser.getUserName());// 创建人姓名
		busiContractTendersContractDetailDto.setCreateTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));// 创建时间
		busiContractTendersContractDetailDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
	}
	
	/**
	 * 流程申请
	 * @param id
	 */
	@Override
	public void applyBusiContractTendersContractDetailByOrgId(Long orgId){
		List<BusiContractTendersContractDetail> busiContractTendersContractDetailList = busiContractTendersContractDetailDao.findBusiContractTendersContractDetailListByOrgId(orgId);
		if(null != busiContractTendersContractDetailList && busiContractTendersContractDetailList.size() > 0){
			for(BusiContractTendersContractDetail busiContractTendersContractDetail:busiContractTendersContractDetailList){
				if(busiContractTendersContractDetail.getIsLeaf() == 1 && StringUtils.isEmpty(busiContractTendersContractDetail.getCountUnit())){
					throw new BusinessRuntimeException("提交的合同清单数据包含[单位为空]的叶子节点数据，不可提交申请!", "-1");
				}
			}
		}
		//调用工作流RPC
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
		Map<String,Object> variables = new HashMap<String, Object>();
		FwOrgDto fwOrgDto = fwOrgService.getOrgById(orgId);
		variables.put("title",  "合同清单审批：[标段机构:"+fwOrgDto.getName()+"("+fwOrgDto.getOrgCode()+")]");
		String flowInstanceId = workFlowRpcService.startProcessInstanceByKey("busi_contract_tenders_contract_detail",orgId, variables);
		if(!StringUtils.isEmpty(flowInstanceId)){
			BusiContractTendersContractDetailApproveFlowInfo busiContractTendersContractDetailApproveFlowInfo = busiContractTendersContractDetailApproveFlowInfoDao.findBusiContractTendersContractDetailApproveFlowInfoByOrgId(orgId);
			if(null == busiContractTendersContractDetailApproveFlowInfo){
				busiContractTendersContractDetailApproveFlowInfo = new BusiContractTendersContractDetailApproveFlowInfo();
			}
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractTendersContractDetailApproveFlowInfo.setOrgId(orgId);
			busiContractTendersContractDetailApproveFlowInfo.setDetailApplyTime(new Date());// 申请时间
			busiContractTendersContractDetailApproveFlowInfo.setDetailApplyUserId(currentUser.getId());// 申请人ID
			busiContractTendersContractDetailApproveFlowInfo.setDetailApplyUserName(currentUser.getUserName());// 申请人名称
			busiContractTendersContractDetailApproveFlowInfo.setDetailApprovalStatus(Constant.FLOW_STATUS_INAPPROVAL);// 审批状态-审批中
			busiContractTendersContractDetailApproveFlowInfo.setDetailFlowInstanceId(Long.valueOf(flowInstanceId));// 流程实例ID
			
			busiContractTendersContractDetailApproveFlowInfo.setCreater(currentUser.getId());// 创建人id
			busiContractTendersContractDetailApproveFlowInfo.setCreaterName(currentUser.getUserName());// 创建人姓名
			busiContractTendersContractDetailApproveFlowInfo.setCreateTime(new Date());// 创建时间
			//进行持久化保存
			busiContractTendersContractDetailApproveFlowInfoDao.persistentBusiContractTendersContractDetailApproveFlowInfo(busiContractTendersContractDetailApproveFlowInfo);
		}
	}
	
	/**
	 * 审批流程处理
	 * @param taskId
	 * @param id
	 * @param busiQumClaimWaybillDetailDto
	 */
	public void workflowSubmit(String taskId, Long id,BusiContractTendersContractDetailApproveFlowInfo busiContractTendersContractDetailApproveFlowInfo){
		WorkFlowRpcService workFlowRpcService = rpcProxy.create(WorkFlowRpcService.class);
        Map<String,Object> variables= new HashMap <String, Object>();
        variables.put("result", busiContractTendersContractDetailApproveFlowInfo.getDetailFlowResult());
        variables.put("resultMessage", busiContractTendersContractDetailApproveFlowInfo.getDetailFlowMessage());
        //流程向前推进
        workFlowRpcService.complete(taskId, variables);
	}
	
	/**
	 * 根据结果ID获取流程信息
	 * @param orgId
	 * @return
	 */
	@Override
	public BusiContractTendersContractDetailDto getBusiContractTendersContractDetailDtoByOrgId(Long orgId){
		BusiContractTendersContractDetailApproveFlowInfo busiContractTendersContractDetailApproveFlowInfo = busiContractTendersContractDetailApproveFlowInfoDao.findBusiContractTendersContractDetailApproveFlowInfoByOrgId(orgId);
		return BeanCopy.getInstance().convert(busiContractTendersContractDetailApproveFlowInfo, BusiContractTendersContractDetailDto.class);
	}
	
	/**
	 * 工作流审批结束(IProcessListener接口实现方法)
	 */
	@Override
	public Map<String, Object> onFlowEnd(String processInstanceId,Map<String, Object> variables) {
		BusiContractTendersContractDetailApproveFlowInfo busiContractTendersContractDetailApproveFlowInfo = busiContractTendersContractDetailApproveFlowInfoDao.findBusiContractTendersContractDetailApproveFlowInfoByProcessInstanceIdAndType(Long.valueOf(processInstanceId), "detail");
		if(null == busiContractTendersContractDetailApproveFlowInfo){
			throw new BusinessRuntimeException("合同清单审批:根据流程ID:"+processInstanceId+",查询合同业务数据数据出错", "-1");
		}else{
			if (variables.get("result").equals(true)) {
				busiContractTendersContractDetailApproveFlowInfo.setDetailFlowResult(Constant.FLOW_STATUS_PASSED);// //审批状态-已通过
			}else{
				busiContractTendersContractDetailApproveFlowInfo.setDetailFlowResult(Constant.FLOW_STATUS_REBUT);//审批状态-已驳回
			}
			UserInfo currentUser = ThreadLocalClient.get().getOperator();
			busiContractTendersContractDetailApproveFlowInfo.setDetailApprovalStatus(busiContractTendersContractDetailApproveFlowInfo.getDetailFlowResult());//审批状态-已审批
			busiContractTendersContractDetailApproveFlowInfo.setDetailApprovalTime(new Date());// 审批完成时间
			busiContractTendersContractDetailApproveFlowInfo.setDetailApprovalUserId(currentUser.getId());// 审批人ID
			busiContractTendersContractDetailApproveFlowInfo.setDetailApprovalUserName(currentUser.getUserName());// 审批人名称
			busiContractTendersContractDetailApproveFlowInfo.setDetailFlowMessage((String) variables.get("resultMessage"));// 审批意见
		}
		return null;
	}

	
	/**
	 * 数据转换
	 */
	@Override
	public List<Map<String, Object>> transform(List<Map<String, Object>> listData, ImportConfig config, List<ImportFieldConfig> fieldConfigs, Map<String, String> params, Workbook workbook) {
		return listData;
	}

	/**
	 * Excel数据校验处理
	 */
	@Override
	public ValidateResult checkTotal(List<Map<String, Object>> listData, ImportConfig config, List<ImportFieldConfig> fieldConfigs, Map<String, String> params, Workbook workbook) {
		StringBuffer errMsg = new StringBuffer();//错误信息提示
		Pattern pattern = Pattern.compile("^\\d+$");//非负整数
		for(int i = 0 ;i < listData.size(); i++){
			Map<String, Object> importDataMap = listData.get(i);
			String firstLevelCode = (String) importDataMap.get("firstLevelCode");//第一级编号
			String secondLevelCode = (String) importDataMap.get("secondLevelCode");//第二级编号
			String thirdLevelCode = (String) importDataMap.get("thirdLevelCode");//第三极编号
			String fourthLevelCode = (String) importDataMap.get("fourthLevelCode");//第四极编号
			String quantity = (String) importDataMap.get("quantity");//合同数量
			String unitPrice = (String) importDataMap.get("unitPrice");//合同单价
			
			//校验编号是否有效
			if(StringUtils.isNotEmpty(fourthLevelCode)){
				if(StringUtils.isNotEmpty(thirdLevelCode)){
					if(StringUtils.isNotEmpty(secondLevelCode)){
						if(StringUtils.isEmpty(firstLevelCode)){
							errMsg.append("第" + (i + 1 + config.getStartRow())+ "行,第一级编号不能为空.");
							break;
						}
					} else{
						errMsg.append("第" + (i + 1 + config.getStartRow())+ "行,第二级编号不能为空.");
						break;
					}
				} else{
					errMsg.append("第" + (i + 1 + config.getStartRow())+ "行,第三级编号不能为空.");
					break;
				}
				//最里面的节点 合同数量、合同单价不能为空
				if(StringUtils.isEmpty(quantity)){
					errMsg.append("第" + (i + 1 + config.getStartRow())+ "行,合同数量不能为空.");
					break;
				}
				if(StringUtils.isEmpty(unitPrice)){
					errMsg.append("第" + (i + 1 + config.getStartRow())+ "行,[编号:"+thirdLevelCode+"]合同单价不能为空.");
					break;
				}
			}
			if(StringUtils.isNotEmpty(quantity)){//此处暂时先写一个校验规则示例，具体校验规则需确认后完善
//				Matcher matcher = pattern.matcher(quantity);
//				if (!matcher.matches()) {
//					errMsg.append("第" + (i + 1 + config.getStartRow())+ "行,合同数量必须为正整数.");
//					break;
//				}
			}
		}
		ValidateResult validateResult = new ValidateResult();//校验结果
		if(StringUtils.isNotEmpty(errMsg.toString())){
			validateResult.setMessage(errMsg.toString());
			validateResult.setResult(false);
		}else{
			validateResult.setResult(true);
		}
		return validateResult;
	}
	

	/**
	 * 持久化方法
	 */
	@Override
	public void persist(List<Map<String, Object>> importDataList, String busiCode, UserInfo currentUser, ImportConfig config, List<ImportFieldConfig> fieldConfigs, Map<String, String> params, Workbook workbook) throws Exception {
		String orgId = params.get("orgId");
		//清除orgid对应的原始明细数据
		busiContractTendersContractDetailDao.deleteBusiContractTendersContractDetailListByOrgId(Long.valueOf(orgId));
		Map<String, BusiContractTendersContractDetailDto> preparePersistDataMap = new HashMap<String, BusiContractTendersContractDetailDto>();//封装好的预持久化集合 
		//获得所有导入的数据
        List<BusiContractTendersContractDetailDto> workbookBusiContractTendersContractDetailDtoList = BeanCopy.getInstance().convertList(importDataList, BusiContractTendersContractDetailDto.class);
        /** 导入细节逻辑   *********************** Edit By Mickey 2017-11-20 *****************
         * 该导入逻辑分两大步：
         * 		第一步：默认导入数据都是叶子节点；
         * 		第二步：当前行数据如果根据父级编码能从Map中取到父级对象，则将父级对象改为非叶子节点
         * **************************************************************************/
        int count = 1;
        for(BusiContractTendersContractDetailDto busiContractTendersContractDetailDto: workbookBusiContractTendersContractDetailDtoList){
        	String uuid = UUID.randomUUID().toString().toUpperCase();
			String contractDetailCode = "";//合同清单编号
			Long groupLevel = Long.valueOf(1);//所属级别
			String parentUUID = "0";//父级UUID
			if(StringUtils.isNotEmpty(busiContractTendersContractDetailDto.getFourthLevelCode())){
				//第四级编号不为空，则叶子节点
				contractDetailCode = busiContractTendersContractDetailDto.getFourthLevelCode();
				groupLevel = Long.valueOf(4);
				if(preparePersistDataMap.containsKey(busiContractTendersContractDetailDto.getThirdLevelCode())){
					BusiContractTendersContractDetailDto parentBusiContractTendersContractDetailDto = preparePersistDataMap.get(busiContractTendersContractDetailDto.getThirdLevelCode());
					parentUUID = parentBusiContractTendersContractDetailDto.getUUID();
					parentBusiContractTendersContractDetailDto.setIsLeaf(Integer.valueOf(0));//将父级节点改为非叶子节点
				}
			} else{
				if(StringUtils.isNotEmpty(busiContractTendersContractDetailDto.getThirdLevelCode())){
	        		//第三级编号不为空，则叶子节点
	        		contractDetailCode = busiContractTendersContractDetailDto.getThirdLevelCode();
	        		groupLevel = Long.valueOf(3);
	        		if(preparePersistDataMap.containsKey(busiContractTendersContractDetailDto.getSecondLevelCode())){
						BusiContractTendersContractDetailDto parentBusiContractTendersContractDetailDto = preparePersistDataMap.get(busiContractTendersContractDetailDto.getSecondLevelCode());
						parentUUID = parentBusiContractTendersContractDetailDto.getUUID();
						parentBusiContractTendersContractDetailDto.setIsLeaf(Integer.valueOf(0));//将父级节点改为非叶子节点
					}
				} else {
					if(StringUtils.isNotEmpty(busiContractTendersContractDetailDto.getSecondLevelCode())){
	            		//第三级编号为空，第二级编号不为空，则二级节点
	        			contractDetailCode = busiContractTendersContractDetailDto.getSecondLevelCode();
	        			groupLevel = Long.valueOf(2);
	        			if(preparePersistDataMap.containsKey(busiContractTendersContractDetailDto.getFirstLevelCode())){
							BusiContractTendersContractDetailDto parentBusiContractTendersContractDetailDto = preparePersistDataMap.get(busiContractTendersContractDetailDto.getFirstLevelCode());
							parentUUID = parentBusiContractTendersContractDetailDto.getUUID();
							parentBusiContractTendersContractDetailDto.setIsLeaf(Integer.valueOf(0));//将父级节点改为非叶子节点
						}
	        		}else{
	            		if(StringUtils.isNotEmpty(busiContractTendersContractDetailDto.getFirstLevelCode())){
	            			//顶级节点
	            			contractDetailCode = busiContractTendersContractDetailDto.getFirstLevelCode();
	                	}
	            	}
				}
			}
			busiContractTendersContractDetailDto.setOrderNumber(count++);
			busiContractTendersContractDetailDto.setOrgId(Long.valueOf(orgId));
        	busiContractTendersContractDetailDto.setContractDetailCode(contractDetailCode);
        	busiContractTendersContractDetailDto.setUUID(uuid);//UUID
        	busiContractTendersContractDetailDto.setParentId(parentUUID);//父节点UUID
        	busiContractTendersContractDetailDto.setGroupLevel(groupLevel);
			busiContractTendersContractDetailDto.setIsLeaf(Integer.valueOf(1));//默认是否叶子节点，1是，0不是
			busiContractTendersContractDetailDto.setDetaileType("import");//数据来源
			busiContractTendersContractDetailDto.setCreater(currentUser.getId());// 创建人id
			busiContractTendersContractDetailDto.setCreaterName(currentUser.getUserName());// 创建人姓名
			busiContractTendersContractDetailDto.setCreateTime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));// 创建时间
			busiContractTendersContractDetailDto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);//未删除
			preparePersistDataMap.put(contractDetailCode, busiContractTendersContractDetailDto);
        }
        if(!preparePersistDataMap.isEmpty()){
        	List<FwPropertyDto> fwPropertyDtoList = fwpropertyService.getPropertyByCatKindName("count_unit");//获取字典大类对象
        	List<BusiContractTendersContractDetailDto> busiContractTendersContractDetailDtoList = new ArrayList<BusiContractTendersContractDetailDto>(preparePersistDataMap.values());
        	//循环处理可使用量、已使用量、复核信息
        	for(BusiContractTendersContractDetailDto prepareImportBusiContractTendersContractDetailDto:busiContractTendersContractDetailDtoList){
    			//1，没有复核数量和复核金额时，填写原清单数量和金额。（需求）
    			double totalPrice = convertNullToZero(prepareImportBusiContractTendersContractDetailDto.getQuantity()) * convertNullToZero(prepareImportBusiContractTendersContractDetailDto.getUnitPrice());
    			prepareImportBusiContractTendersContractDetailDto.setTotalPrice(String.valueOf(totalPrice));//合同总额
    			if(StringUtils.isEmpty(prepareImportBusiContractTendersContractDetailDto.getReviewQuantity())){
    				prepareImportBusiContractTendersContractDetailDto.setReviewQuantity(prepareImportBusiContractTendersContractDetailDto.getQuantity());//合同数量
    			}
    			if(StringUtils.isNotEmpty(prepareImportBusiContractTendersContractDetailDto.getReviewUnitPrice()) && 
    					StringUtils.isNotEmpty(prepareImportBusiContractTendersContractDetailDto.getReviewQuantity())){
    				//如果符合金额 和 符合数量不为空，则符合总额 = 符合金额 * 符合数量，反之为 合同总额
    				double reviewTotalPrice = convertNullToZero(prepareImportBusiContractTendersContractDetailDto.getReviewQuantity()) * convertNullToZero(prepareImportBusiContractTendersContractDetailDto.getReviewUnitPrice());
    				prepareImportBusiContractTendersContractDetailDto.setReviewTotalPrice(String.valueOf(reviewTotalPrice));
    			}else{
    				prepareImportBusiContractTendersContractDetailDto.setReviewTotalPrice(String.valueOf(totalPrice));
    			}
    			if(StringUtils.isEmpty(prepareImportBusiContractTendersContractDetailDto.getReviewUnitPrice())){
    				prepareImportBusiContractTendersContractDetailDto.setReviewUnitPrice(prepareImportBusiContractTendersContractDetailDto.getUnitPrice());//合同单价
    			}
    			//字典表转换
    			if(StringUtils.isNotEmpty(prepareImportBusiContractTendersContractDetailDto.getCountUnit())){
    				for(FwPropertyDto fwPropertyDto:fwPropertyDtoList){
    					if(fwPropertyDto.getPropertyName().equals(StringUtils.trimToEmpty(prepareImportBusiContractTendersContractDetailDto.getCountUnit()))){
        					prepareImportBusiContractTendersContractDetailDto.setCountUnit(fwPropertyDto.getPropertyValue());
        				}
    				}
    			}
    			prepareImportBusiContractTendersContractDetailDto.setWorkabilityQuantity(prepareImportBusiContractTendersContractDetailDto.getQuantity());//新增时，可使用量默认为 合同数量
    			prepareImportBusiContractTendersContractDetailDto.setUsedQuantity("0");//已使用量默认0
        	}
        	List<BusiContractTendersContractDetail> addEntityList = BeanCopy.getInstance().addFieldMap("orgId", "busiFwOrg").convertList(busiContractTendersContractDetailDtoList, BusiContractTendersContractDetail.class);
        	//进行持久化保存
			busiContractTendersContractDetailDao.batchSaveBusiContractTendersContractDetailList(addEntityList);;
        }
	}
	
	private static double convertNullToZero(String StringNum){
		return StringUtils.isEmpty(StringNum)?0.00:Double.valueOf(StringNum);
	}
}
