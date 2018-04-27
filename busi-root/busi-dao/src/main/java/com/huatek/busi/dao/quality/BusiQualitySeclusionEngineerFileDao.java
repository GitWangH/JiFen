package com.huatek.busi.dao.quality;
   
import java.util.List;

import com.huatek.busi.model.quality.BusiQualitySeclusionEngineerFile;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySeclusionEngineerFileDao
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-09 14:14:22
  * @version: Windows 7
  */

public interface BusiQualitySeclusionEngineerFileDao {

    /** 
    * @Title: findBusiQualitySeclusionEngineerFileById 
    * @Description: 根据ID获取对象 
    * @createDate:  2017-11-09 14:14:22
    * @param   id
    * @return  BusiQualitySeclusionEngineerFile    
    */ 
    BusiQualitySeclusionEngineerFile findBusiQualitySeclusionEngineerFileById(Long id);

    /** 
    * @Title: saveOrUpdateBusiQualitySeclusionEngineerFile 
    * @Description: 保存或者修改对象  
    * @createDate:  2017-11-09 14:14:22
    * @param   entity
    * @return  void    
    */ 
    void saveOrUpdateBusiQualitySeclusionEngineerFile(BusiQualitySeclusionEngineerFile entity);
    
    /** 
    * @Title: persistent 
    * @Description: 持久化对象  
    * @createDate:  2017-11-09 14:14:22
    * @param   entity
    * @return  void    
    */ 
	void persistentBusiQualitySeclusionEngineerFile(BusiQualitySeclusionEngineerFile entity);
    
    /** 
    * @Title: deleteBusiQualitySeclusionEngineerFile 
    * @Description: 删除对象 
    * @createDate: 2017-11-09 14:14:22
    * @param   entity
    * @return  void    
    */ 
    void deleteBusiQualitySeclusionEngineerFile(BusiQualitySeclusionEngineerFile entity);
    
    /** 
    * @Title: findAllBusiQualitySeclusionEngineerFile 
    * @Description:获取全部对象
    * @createDate:  2017-11-09 14:14:22
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiQualitySeclusionEngineerFile> findAllBusiQualitySeclusionEngineerFile();

    /** 
    * @Title: findBusiQualitySeclusionEngineerFileByCondition 
    * @Description: 根据条件获取对象 
    * @createDate: 2017-11-09 14:14:22
    * @param   field 字段名称
    * @param   condition 字段值
    * @return  List<BusiQualitySeclusionEngineerFile>
    */ 
    List<BusiQualitySeclusionEngineerFile> findBusiQualitySeclusionEngineerFileByCondition(String field,String condition);
    
    /** 
    * @Title: getAllBusiQualitySeclusionEngineerFile 
    * @Description:获取对象翻页信息
    * @createDate: 2017-11-09 14:14:22
    * @param   queryPage
    * @return  DataPage<BusiQualitySeclusionEngineerFile>    
    */ 
    DataPage<BusiQualitySeclusionEngineerFile> getAllBusiQualitySeclusionEngineerFile(QueryPage queryPage);
    
    /**
     * 批量增加
     * @param file
     * @param count
     */
    void batchSave(List<BusiQualitySeclusionEngineerFile> file, int count);
    
}
