package com.huatek.busi.dao.contract;
   
import java.util.List;
import java.util.Map;

import com.huatek.busi.model.contract.BusiContractTendersContractDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractTendersContractDetailDao
  * @Description: 标段合同清单(复合清单)DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:02:52
  * @version: 1.0
  */
public interface BusiContractTendersContractDetailDao {

    /** 
    * @Title: findBusiContractTendersContractDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-25 15:02:52
    * @param   id
    * @return  BusiContractTendersContractDetail    
    */ 
    BusiContractTendersContractDetail findBusiContractTendersContractDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractTendersContractDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:02:52
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractTendersContractDetail(BusiContractTendersContractDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:02:52
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractTendersContractDetail(BusiContractTendersContractDetail entity);
    
    /** 
    * @Title: deleteBusiContractTendersContractDetail 
    * @Description: 删除对象 
    * @createDate: 2017-10-25 15:02:52
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractTendersContractDetail(BusiContractTendersContractDetail entity);
    
    /** 
    * @Title: findAllBusiContractTendersContractDetail 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:02:52
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractTendersContractDetail> findAllBusiContractTendersContractDetail();

    /** 
    * @Title: getAllBusiContractTendersContractDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:02:52
    * @param   queryPage
    * @return  DataPage<BusiContractTendersContractDetail>    
    */ 
    DataPage<BusiContractTendersContractDetail> getAllBusiContractTendersContractDetail(QueryPage queryPage);

    /**
     * 批量保存标段合同表 (施工合同)清单列表数据
     * @param busiContractTendersContractDetailList
     */
	void batchSaveTreeGridData(List<BusiContractTendersContractDetail> busiContractTendersContractDetailList);

	/**
	 * 根据机构id删除明细数据
	 * @param orgId
	 */
	void deleteBusiContractTendersContractDetailListByOrgId(Long orgId);

	/**
	 * 根据流程编号查询业务数据
	 * @param processInstanceId
	 * @return
	 */
	BusiContractTendersContractDetail findBusiContractTendersContractDetailByProcessInstanceId(String processInstanceId);

	/**
	 * 查询合同清单列表树数据
	 * @param queryPage
	 * @return
	 */
	List<BusiContractTendersContractDetail> findAllBusiContractTendersContractDetail(QueryPage queryPage);

	/**
	 * 根据父节点的UUID查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	List<BusiContractTendersContractDetail> findChildBusiContractTendersContractDetailDtoByParentUUID(String parentUUID);
	
	/**
	 * 批量保存
	 * @param addEntityList
	 */
	void batchSaveBusiContractTendersContractDetailList(List<BusiContractTendersContractDetail> addEntityList);

	/**
	 * 批量更新
	 * @param updateEntityList
	 */
	void batchUpdateBusiContractTendersContractDetailList(List<BusiContractTendersContractDetail> updateEntityList);

	/**
	 * 批量删除
	 * @param deleteEntityList
	 */
	void batchDeleteBusiContractTendersContractDetailList(List<BusiContractTendersContractDetail> deleteEntityList);

	/**
	 * 根据合同清单编码查询对应的 合同清单数据
	 * @param contractDetailCode
	 * @return
	 */
	BusiContractTendersContractDetail findBusiContractTendersContractDetailByDetailCode(Long orgId,String contractDetailCode);

	/**
	 * 根据机构ID获取合同清单复核审批通过的数据
	 * @param orgId
	 * @return
	 */
	Map<String, BusiContractTendersContractDetail> findCheckPassedBusiContractTendersContractDetailListByOrgId(Long orgId);

	List<BusiContractTendersContractDetail> findBusiContractTendersContractDetailListByOrgId(Long orgId);
    
}
