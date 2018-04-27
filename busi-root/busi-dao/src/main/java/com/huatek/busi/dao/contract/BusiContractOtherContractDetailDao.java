package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractOtherContractDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiContractOtherContractDetailDao
  * @Description: 其它合同明细管理DAO接口
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-27 11:08:20
  * @version: 1.0
  */
public interface BusiContractOtherContractDetailDao {

    /** 
    * @Title: findBusiContractOtherContractDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-27 11:08:20
    * @param   id
    * @return  BusiContractOtherContractDetail    
    */ 
    BusiContractOtherContractDetail findBusiContractOtherContractDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiContractOtherContractDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-27 11:08:20
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiContractOtherContractDetail(BusiContractOtherContractDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-27 11:08:20
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiContractOtherContractDetail(BusiContractOtherContractDetail entity);
    
    /** 
    * @Title: deleteBusiContractOtherContractDetail 
    * @Description: 删除对象 
    * @createDate: 2017-10-27 11:08:20
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiContractOtherContractDetail(BusiContractOtherContractDetail entity);
    
    /** 
    * @Title: findAllBusiContractOtherContractDetail 
    * @Description:获取全部对象
    * @createDate:  2017-10-27 11:08:20
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractOtherContractDetail> findAllBusiContractOtherContractDetail();

    /** 
    * @Title: getAllBusiContractOtherContractDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-27 11:08:20
    * @param   queryPage
    * @return  DataPage<BusiContractOtherContractDetail>    
    */ 
    DataPage<BusiContractOtherContractDetail> getAllBusiContractOtherContractDetail(QueryPage queryPage);

    /**
     * 批量保存
     * @param busiContractOtherContractDetailList
     */
	void batchSaveTreeGridData(List<BusiContractOtherContractDetail> busiContractOtherContractDetailList);

	/**
	 * 查询所有顶级节点数据
	 * @param queryPage
	 * @return
	 */
	List<BusiContractOtherContractDetail> findAllBusiContractOtherContractDetail(QueryPage queryPage);

	/**
	 * 根据父节点的UUID查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	List<BusiContractOtherContractDetail> findChildBusiContractOtherContractDetailByParentUUID(String parentUUID);

	/**
	 * 批量保存
	 * @param addEntityList
	 */
	void batchSaveBusiContractOtherContractDetailList(List<BusiContractOtherContractDetail> addEntityList);

	/**
	 * 批量更新
	 * @param updateEntityList
	 */
	void batchUpdateBusiContractOtherContractDetailList(List<BusiContractOtherContractDetail> updateEntityList);

	/**
	 * 批量删除
	 * @param deleteEntityList
	 */
	void batchDeleteBusiContractOtherContractDetailList(List<BusiContractOtherContractDetail> deleteEntityList);
    
}
