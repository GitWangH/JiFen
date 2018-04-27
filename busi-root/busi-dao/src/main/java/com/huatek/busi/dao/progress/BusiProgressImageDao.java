package com.huatek.busi.dao.progress;
   
import java.util.List;

import com.huatek.busi.model.base.BusiBaseImageList;
import com.huatek.busi.model.progress.BusiProgressImage;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiProgressImageDao
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-12-06 10:58:13
  * @version: Windows 7
  */

public interface BusiProgressImageDao {

    /** 
    * @Title: findBusiProgressImageById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-12-06 10:58:13
    * @param   id
    * @return  BusiProgressImage    
    */ 
    BusiProgressImage findBusiProgressImageById(Long id);

    /** 
    * @Title: saveOrUpdateBusiProgressImage 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-12-06 10:58:13
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiProgressImage(BusiProgressImage entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-12-06 10:58:13
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiProgressImage(BusiProgressImage entity);
    
    /** 
    * @Title: deleteBusiProgressImage 
    * @Description: 删除对象 
    * @createDate: 2017-12-06 10:58:13
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiProgressImage(BusiProgressImage entity);
    
    /** 
    * @Title: findAllBusiProgressImage 
    * @Description:获取全部对象
    * @createDate:  2017-12-06 10:58:13
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiProgressImage> findAllBusiProgressImage();

    /** 
    * @Title: findBusiProgressImageByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-12-06 10:58:13
    * @param   condition
    * @return  BusiProgressImage    
    */ 
    BusiProgressImage findBusiProgressImageByCondition(String condition);
    
    /** 
    * @Title: getAllBusiProgressImage 
    * @Description:获取对象翻页信息
    * @createDate: 2017-12-06 10:58:13
    * @param   queryPage
    * @return  DataPage<BusiProgressImage>    
    */ 
    DataPage<BusiProgressImage> getAllBusiProgressImage(QueryPage queryPage);
    
    /**
     * 根据uuid 和 orgId 获取子节点数据
     * @param uuid
     * @param orgId
     * @return
     */
    List<BusiProgressImage> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId);
    
    /**
     * 批量保存 修改 删除
     * @param addEntityList
     * @param count
     */
    void batchSave(List<BusiProgressImage> addEntityList, int count);
    /**
     * 生成形象清单
     * @param orgId
     */
    void initProgressImage(Long orgId);
    
    /**
     * 根据imageId 获取设计金额
     * @param imageId
     * @return
     */
    List<BusiProgressImage> getDesignQuantityByImageId(Long imageId);
}
