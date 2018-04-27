package com.huatek.busi.dao.project;
   
import java.util.List;

import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 工程标段管理明细
 * @author eli_cui
 *
 */

public interface BusiProjectManagementOfBidSectionDetailDao {

    /** 
    * @Title: findBusiProjectManagementOfBidSectionDetailById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-27 14:04:01
    * @param   id
    * @return  BusiProjectManagementOfBidSectionDetail    
    */ 
    BusiProjectManagementOfBidSectionDetail findBusiProjectManagementOfBidSectionDetailById(Long id);

    /** 
    * @Title: saveOrUpdateBusiProjectManagementOfBidSectionDetail 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-27 14:04:01
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiProjectManagementOfBidSectionDetail(BusiProjectManagementOfBidSectionDetail entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-27 14:04:01
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiProjectManagementOfBidSectionDetail(BusiProjectManagementOfBidSectionDetail entity);
    
    /** 
    * @Title: deleteBusiProjectManagementOfBidSectionDetail 
    * @Description: 删除对象 
    * @createDate: 2017-10-27 14:04:01
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiProjectManagementOfBidSectionDetail(BusiProjectManagementOfBidSectionDetail entity);
    
    /** 
    * @Title: findAllBusiProjectManagementOfBidSectionDetail 
    * @Description:获取全部对象
    * @createDate:  2017-10-27 14:04:01
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiProjectManagementOfBidSectionDetail> findAllBusiProjectManagementOfBidSectionDetail();

    /** 
    * @Title: findBusiProjectManagementOfBidSectionDetailByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-27 14:04:01
    * @param   condition
    * @return  BusiProjectManagementOfBidSectionDetail    
    */ 
    BusiProjectManagementOfBidSectionDetail findBusiProjectManagementOfBidSectionDetailByCondition(String condition);
    
    /** 
    * @Title: getAllBusiProjectManagementOfBidSectionDetail 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-27 14:04:01
    * @param   queryPage
    * @return  DataPage<BusiProjectManagementOfBidSectionDetail>    
    */ 
    DataPage<BusiProjectManagementOfBidSectionDetail> getAllBusiProjectManagementOfBidSectionDetail(QueryPage queryPage);
    
    /**
     * @Description: 批量保存 或者更新，根据id是否存在来决定是跟新还是保存
     * @param entityList 要保存或更新数据集合
     * @param count	保存或更新的数量
     */
    void batchSave(List<BusiProjectManagementOfBidSectionDetail> entityList, int count);
    
    /**
     * @Description: 批量删除
     * @param entityList 要删除的数据集合
     * @param count	删除的数量
     */
    void batchDelete(List<BusiProjectManagementOfBidSectionDetail> entityList, int count);
    
    /**
     * 根据idList 获取 BusiProjectManagementOfBidSectionDetail 列表
     * @param idList
     * @return
     */
    List<BusiProjectManagementOfBidSectionDetail> findBusiProjectManagementOfBidSectionDetailByIdList(List<Long> idList);
    
}
