package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractSupervisorContractDao;
import com.huatek.busi.model.contract.BusiContractSupervisorContract;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

 /**
  * @ClassName: BusiContractSupervisorContractDaoImpl
  * @Description: 监理合同DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-24 11:23:41
  * @version: 1.0
  */
@Repository("BusiContractSupervisorContractDao")
public class  BusiContractSupervisorContractDaoImpl extends AbstractDao<Long,  BusiContractSupervisorContract> implements  BusiContractSupervisorContractDao {

    @Override
    public BusiContractSupervisorContract findBusiContractSupervisorContractById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractSupervisorContract( BusiContractSupervisorContract entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractSupervisorContract(BusiContractSupervisorContract entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractSupervisorContract(BusiContractSupervisorContract entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractSupervisorContract> findAllBusiContractSupervisorContract() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractSupervisorContract findBusiContractSupervisorContractByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        return (BusiContractSupervisorContract) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractSupervisorContract> getAllBusiContractSupervisorContract(QueryPage queryPage) {
    	List<QueryParam> paramList = queryPage.getQueryParamList();    	  
		paramList.add(new QueryParam("isDelete", "long", "=", "0"));//未删除的数据
        return super.queryPageData(queryPage);
    }

    /**
     * 根据流程ID获取对应的业务数据
     * @param processInstanceId
     * @author: mickey_meng
     * @Email : mickey_meng@huatek.com
     * @date: 2017-10-24 16:36:35
     * @version: 1.0
     * @return
     */
    @Override
    public BusiContractSupervisorContract findBusiContractSupervisorContractByProcessInstanceId(String flowInstanceId){
    	if(StringUtils.isNotEmpty(flowInstanceId)){
    		Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("flowInstanceId", Long.valueOf(flowInstanceId)));
            return (BusiContractSupervisorContract) criteria.uniqueResult();
    	}else{
    		return null;
    	}
	}
}
