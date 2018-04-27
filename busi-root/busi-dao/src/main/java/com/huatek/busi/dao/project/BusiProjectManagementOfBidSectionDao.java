package com.huatek.busi.dao.project;
   
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.huatek.busi.model.project.BusiProjectManagementOfBidSection;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * 工程标段管理
 * @author eli_cui
 *
 */

public interface BusiProjectManagementOfBidSectionDao {

    /** 
    * @Title: findBusiProjectManagementOfBidSectionById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-10-27 14:03:46
    * @param   id
    * @return  BusiProjectManagementOfBidSection    
    */ 
    BusiProjectManagementOfBidSection findBusiProjectManagementOfBidSectionById(Long id);

    /** 
    * @Title: saveOrUpdateBusiProjectManagementOfBidSection 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-10-27 14:03:46
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSection entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-10-27 14:03:46
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSection entity);
    
    /** 
    * @Title: deleteBusiProjectManagementOfBidSection 
    * @Description: 删除对象 
    * @createDate: 2017-10-27 14:03:46
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSection entity);
    
    /** 
    * @Title: findAllBusiProjectManagementOfBidSection 
    * @Description:获取全部对象
    * @createDate:  2017-10-27 14:03:46
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiProjectManagementOfBidSection> findAllBusiProjectManagementOfBidSection();

    /** 
    * @Title: findBusiProjectManagementOfBidSectionByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-10-27 14:03:46
    * @param   condition
    * @return  BusiProjectManagementOfBidSection    
    */ 
    BusiProjectManagementOfBidSection findBusiProjectManagementOfBidSectionByCondition(String condition);
    
    /** 
    * @Title: getAllBusiProjectManagementOfBidSection 
    * @Description:获取对象翻页信息
    * @createDate: 2017-10-27 14:03:46
    * @param   queryPage
    * @return  DataPage<BusiProjectManagementOfBidSection>    
    */ 
    DataPage<BusiProjectManagementOfBidSection> getAllBusiProjectManagementOfBidSection(QueryPage queryPage);
    
    /**
     * 根据机构ID查询监理、建设、合同信息(此接口给【合同管理-施工合同管理新增】使用)
     * @param orgId
     * @return
     */
    public List<Map<String, Object>> getInfoForTheContractByOrgId(Long orgId);
    
    /**
     * 根据uuid 获取工程标段管理实体类
     * @param uuid
     * @return
     */
    BusiProjectManagementOfBidSection getBusiProjectManagementOfBidSectionByUUID(String UUID);
    
    /**
     * 查询 合同管理-标段分部分项维护 是否关联了 工程标段管理中的数据
     * @param idList
     * @return 返回关联的标段管理明细id 或 返回null
     */
    List<Long> getBindDetailIdBySelectedIdList(List<Long> idList);
    
}
