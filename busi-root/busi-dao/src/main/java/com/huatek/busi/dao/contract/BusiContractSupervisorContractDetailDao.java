package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractSupervisorContractDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractSupervisorContractDetailDao
  * @Description: 监理合同清单（安全计量设置）DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:10:58
  * @version: 1.0
  */
public interface BusiContractSupervisorContractDetailDao {

    /** 
    * @Title: saveOrUpdateBusiContractSupervisorContractDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-25 15:10:58
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractSupervisorContractDetail(BusiContractSupervisorContractDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-25 15:10:58
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractSupervisorContractDetail(BusiContractSupervisorContractDetail entity);
    
    
    /** 
    * @Title: findAllBusiContractSupervisorContractDetail 
    * @Description:获取全部对象
    * @createDate:  2017-10-25 15:10:58
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractSupervisorContractDetail> findAllBusiContractSupervisorContractDetail();
    
    /** 
    * @Title: getAllBusiContractSupervisorContractDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-25 15:10:58
    * @param   queryPage
    * @return  DataPage<BusiContractSupervisorContractDetail>    
    */ 
    DataPage<BusiContractSupervisorContractDetail> getAllBusiContractSupervisorContractDetail(QueryPage queryPage);

    /**
     * 批量保存
     * @param busiContractSupervisorContractDetailList
     */
	void batchSaveTreeGridData(List<BusiContractSupervisorContractDetail> busiContractSupervisorContractDetailList);

	/**
	 * 查询清单列表数据
	 * @param queryPage
	 * @return
	 */
	List<BusiContractSupervisorContractDetail> findAllBusiContractSupervisorContractDetail(QueryPage queryPage);

	/**
	 * 批量添加
	 * @param addEntityList
	 */
	void batchSaveBusiContractSupervisorContractDetailList(List<BusiContractSupervisorContractDetail> addEntityList);

	/**
	 * 批量更新
	 * @param updateEntityList
	 */
	void batchUpdateBusiContractSupervisorContractDetailList(List<BusiContractSupervisorContractDetail> updateEntityList);

	/**
	 * 批量删除
	 * @param deleteEntityList
	 */
	void batchDeleteBusiContractSupervisorContractDetailList(List<BusiContractSupervisorContractDetail> deleteEntityList);

	/**
	 * 根据父节点查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	List<BusiContractSupervisorContractDetail> findChildBusiContractSupervisorContractDetailDtoByParentUUID(String parentUUID);
    
}
