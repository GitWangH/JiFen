package com.huatek.busi.dao.contract;

import java.util.List;
import java.util.Map;

import com.huatek.busi.model.contract.BusiContractTendersBranch;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: BusiContractTendersBranchDao
 * @Description: 标段分部分项DAO接口
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 11:05:49
 * @version: 1.0
 */
public interface BusiContractTendersBranchDao {

	/**
	 * @Title: findBusiContractTendersBranchById
	 * @Description: 根据ID获取对象
	 * @createDate: 2017-10-24 11:05:49
	 * @param id
	 * @return BusiContractTendersBranch
	 */
	BusiContractTendersBranch findBusiContractTendersBranchById(Long id);

	/**
	 * @Title: saveOrUpdateBusiContractTendersBranch
	 * @Description: 保存或者修改对象
	 * @createDate: 2017-10-24 11:05:49
	 * @param entity
	 * @return void
	 */
	void saveOrUpdateBusiContractTendersBranch(BusiContractTendersBranch entity);

	/**
	 * @Title: persistent
	 * @Description: 持久化对象
	 * @createDate: 2017-10-24 11:05:49
	 * @param entity
	 * @return void
	 */
	void persistentBusiContractTendersBranch(BusiContractTendersBranch entity);

	/**
	 * @Title: deleteBusiContractTendersBranch
	 * @Description: 删除对象
	 * @createDate: 2017-10-24 11:05:49
	 * @param entity
	 * @return void
	 */
	void deleteBusiContractTendersBranch(BusiContractTendersBranch entity);

	/**
	 * @Title: findAllBusiContractTendersBranch
	 * @Description:获取全部对象
	 * @createDate: 2017-10-24 11:05:49
	 * @param
	 * @return List<bean.className>
	 */
	List<BusiContractTendersBranch> findAllBusiContractTendersBranch();

	/**
	 * @Title: findBusiContractTendersBranchByCondition
	 * @Description: 根据条件获取对象
	 * @createDate: 2017-10-24 11:05:49
	 * @param condition
	 * @return BusiContractTendersBranch
	 */
	BusiContractTendersBranch findBusiContractTendersBranchByCondition(
			String condition);

	/**
	 * @Title: getAllBusiContractTendersBranch
	 * @Description:获取对象翻页信息
	 * @createDate: 2017-10-24 11:05:49
	 * @param queryPage
	 * @return DataPage<BusiContractTendersBranch>
	 */
	DataPage<BusiContractTendersBranch> getAllBusiContractTendersBranch(QueryPage queryPage);
	
	/**
	 * 根据条件查询所有数据
	 * @param queryPage
	 * @return
	 */
	List<BusiContractTendersBranch> findAllBusiContractTendersBranch(QueryPage queryPage);
	
	/**
	 * 根据父节点的UUID查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	public List<BusiContractTendersBranch> findChildBusiContractTendersBranchByParentUUID(String parentUUID);

	/**
	 * 批量保存
	 */
	public void batchSaveBusiContractTendersBranchList(List<BusiContractTendersBranch> addEntityList);

	/**
	 * 批量更新
	 */
	public void batchUpdateBusiContractTendersBranchList(List<BusiContractTendersBranch> updateEntityList);

	/**
	 * 批量删除
	 */
	public void batchDeleteBusiContractTendersBranchList(List<BusiContractTendersBranch> deleteEntityList);

	/**
	 * 生成分部分项[生成规则：根据机构编号、单位工程类型 获取工程标段管理明细数据和 基础数据-项目分部分项 相匹配的数据]
	 * @param orgId
	 */
	void createTendersBranchDataByOrgId(Long orgId, Long tenantId, Long currentUserId, String currentUserName);

	/**
	 * 根据机构id删除数据
	 * @param orgId
	 */
	void deleteBusiContractTendersBranchListByOrgId(Long orgId);
	
	/**
	 * 查询已挂接分部分项列表数据
	 * @param tendersContractDetailId
	 * @return
	 */
	List<Map<String, Object>> findAllRelationBranchTreeListBusiContractTendersContractDetailDtoByContractDetailId(Long tendersContractDetailId);

	/**
	 * 根据orgI的查询分部分项数据
	 * @param orgId
	 * @return
	 */
	List<BusiContractTendersBranch> findBusiContractTendersBranchListByOrgId(Long orgId);
    
}
