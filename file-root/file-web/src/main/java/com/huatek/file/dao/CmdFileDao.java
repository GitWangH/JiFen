package com.huatek.file.dao;
   
import java.util.List;

import com.huatek.file.modal.CmdFile;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * 代码自动生成dao接口类.
  * @ClassName: CmdFileDao
  * @Description: 
  * @author: hobart_ren
  * @date: 2016-05-05 06:27:01
  * @version: 1.0
  */

public interface CmdFileDao {

    /** 
    * @Title: findCmdFileById 
    * @Description: TODO 
    * @createDate:  2016-05-05 06:27:01
    * @param   
    * @return  CmdFile    
    * @throws 
    */ 
    CmdFile findCmdFileById(Long id);

    /** 
    * @Title: saveOrUpdateCmdFile 
    * @Description: TODO 
    * @createDate:  2016-05-05 06:27:01
    * @param   
    * @return  void    
    * @throws 
    */ 
    void saveOrUpdateCmdFile(CmdFile entity);
    
    /** 
    * @Title: persistent 
    * @Description: TODO 
    * @createDate:  2016-05-05 06:27:01
    * @param   
    * @return  void    
    * @throws 
    */ 
	void persistentCmdFile(CmdFile entity);
    
    /** 
    * @Title: deleteSnakerForm 
    * @Description: TODO
    * @createDate: 2016-05-05 06:27:01
    * @param   
    * @return  void    
    * @throws 
    */ 
    void deleteCmdFile(CmdFile entity);
    
    /** 
    * @Title: findAllCmdFile 
    * @Description:TODO
    * @createDate:  2016-05-05 06:27:01
    * @param   
    * @return  List<SnakerForm>    
    * @throws 
    */ 
    List<CmdFile> findAllCmdFile();

    /** 
    * @Title: findCmdFileByCondition 
    * @Description: TODO 
    * @createDate: 2016-05-05 06:27:01
    * @param   
    * @return  CmdFile    
    * @throws 
    */ 
    CmdFile findCmdFileByCondition(String name);
    
    /** 
    * @Title: getAllCmdFile 
    * @Description:TODO
    * @createDate: 2016-05-05 06:27:01
    * @param   
    * @return  DataPage<CmdFile>    
    * @throws 
    */ 
    DataPage<CmdFile> getAllCmdFile(QueryPage queryPage);

	/**
	 * @Description: 获取文件通过业务id
	 * @param  businessId 业务id  
	 * @return List  
	 * @throws
	 * @author hobart_ren
	  *@e_mail hobart_ren@huatek.com
	 * @date 2016年5月18日
	 */
	
	List<CmdFile> getCmdFileDtoByBusiId(String businessId);
    
	/**
	 * 根据业务id的数组批量取附件
	* @Title: getCmdFileDtoByBusiIds 
	* @Description: TODO 
	* @createDate: 2016年7月13日 下午3:21:24
	* @param   
	* @return  List<CmdFile>    
	* @throws
	 */
	List<CmdFile> getCmdFileDtoByBusiIds(String[] businessIds);
}
