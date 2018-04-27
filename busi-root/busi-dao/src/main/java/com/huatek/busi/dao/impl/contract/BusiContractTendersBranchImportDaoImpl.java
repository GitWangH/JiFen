package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractTendersBranchImportDao;
import com.huatek.busi.model.contract.BusiContractTendersBranchImport;
import com.huatek.frame.core.dao.AbstractDao;


 /**
  * @ClassName: BusiContractTendersBranchImportDaoImpl
  * @Description: 
  * @author: mickey_meng
  * @Email : 
  * @date: 2017-11-21 13:11:17
  * @version: Windows 7
  */

@Repository("busiContractTendersBranchImportDao")
public class  BusiContractTendersBranchImportDaoImpl extends AbstractDao<Long,  BusiContractTendersBranchImport> implements  BusiContractTendersBranchImportDao {

    /**
     * 根据导入批次号获取全部对象
     * @param importBatchNo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public  List<BusiContractTendersBranchImport> findAllBusiContractTendersBranchImportByBatchNo(String importBatchNo) {
    	Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("importBatchNo", importBatchNo));
    	return criteria.list();
    }
}
