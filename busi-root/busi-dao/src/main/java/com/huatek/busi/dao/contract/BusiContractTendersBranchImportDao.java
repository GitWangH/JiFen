package com.huatek.busi.dao.contract;
   
import java.util.List;

import com.huatek.busi.model.contract.BusiContractTendersBranchImport;

 /**
  * @ClassName: BusiContractTendersBranchImportDao
  * @Description: 
  * @author: mickey_meng
  * @Email : 
  * @date: 2017-11-21 13:11:17
  * @version: Windows 7
  */

public interface BusiContractTendersBranchImportDao {

    /** 
    * @Title: findAllBusiContractTendersBranchImport 
    * @Description:根据导入批次号获取全部对象
    * @createDate:  2017-11-21 13:11:17
    * @param   
    * @return  List<bean.className>    
    */ 
    List<BusiContractTendersBranchImport> findAllBusiContractTendersBranchImportByBatchNo(String importBatchNo);

}
