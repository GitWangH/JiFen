package com.huatek.busi.service.contract;

import java.util.List;
import java.util.Map;

import com.huatek.busi.dto.TreeGridAddIdAndUUIDDto;
import com.huatek.busi.dto.contract.BusiContractTendersBranchDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersContractDto
 * @Description: 标段分部分项维护 服务层接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-28 14:19:35
 * @version: 1.0
 */
public interface BusiContractTendersBranchService {
	
	/** 
	* @Title:  getAllBusiContractTendersBranchPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiContractTendersBranchDto>    
	*/ 
	DataPage<BusiContractTendersBranchDto> getAllBusiContractTendersBranchPage(QueryPage queryPage);

	/**
	 * 查询顶级节点列表数据
	 * @param queryPage
	 * @return
	 */
	List<BusiContractTendersBranchDto> getAllTopLevelBusiContractTendersBranchDto(QueryPage queryPage);

	/**
	 * 根据父级ID查询自己节点数据
	 * @param parentUUID
	 * @return
	 */
	List<BusiContractTendersBranchDto> getChildBusiContractTendersBranchDtoByParentUUID(String parentUUID);

	/**
	 * 保存标段分部分项列表数据
	 * @param orgId
	 * @param busiContractTendersBranchDtoList
	 */
	List<TreeGridAddIdAndUUIDDto> saveTreeGridData(Long orgId,List<BusiContractTendersBranchDto> busiContractTendersBranchDtoList);
	
	/**
	 * 分部分项流程申请
	 * @param orgId
	 */
	void applyBusiContractTendersBranchByOrgId(Long orgId);

	/**
	 * 根据机构ID查询审批状态 
	 * @param orgId
	 * @return
	 */
	BusiContractTendersBranchDto getBusiContractTendersBranchDtoByOrgId(Long orgId);

	/**
	 * 生成分部分项[生成规则：根据机构编号、单位工程类型 获取工程标段管理明细数据和 基础数据-项目分部分项 相匹配的数据]
	 * @param orgId
	 */
	void createTendersBranchDataByOrgId(Long orgId);
	/**
	 * 分部分项数据导入
	 * @param filePath  导入excel文件地址
	 * @param orgId  需要导入的组织机构id
	 */
	Map<String, String> importDataByEtl(String filePath,Long orgId) throws Exception;
	/**
	 * 查询已挂接分部分项列表数据
	 * @param tendersContractDetailId
	 * @return
	 */
	List<BusiContractTendersBranchDto> getAllRelationBranchTreeDataListByContractDetailId(Long tendersContractDetailId);
	
	/**
	 * 根据 标段分部分项名称 获取  BusiContractTendersBranchDto
	 * @param tendersBranchName
	 * @return
	 */
	List<BusiContractTendersBranchDto> getBusiContractTendersBranchDtoByTendersBranchName(String tendersBranchName, Long orgId);
	
	/**
	 * 
	 * @param conditionLevelMap
	 * @return
	 */
	List<BusiContractTendersBranchDto> getBusiContractTendersBranchDtoByLevel(Map<String,List<String>> conditionLevelMap, Long orgId);

	/**
	 * 根据orgID和修改时间查询所有修改的分部分项数据(for App)
	 * @param queryPage
	 * @return
	 */
	List<BusiContractTendersBranchDto> getAllLevelOfEditBusiContractTendersBranchDto(QueryPage queryPage);
}