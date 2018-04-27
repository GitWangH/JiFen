package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractTendersContractDao;
import com.huatek.busi.model.contract.BusiContractTendersContract;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

/**
 * @ClassName: BusiContractTendersContractDaoImpl
 * @Description: 标段合同表 (施工合同)DAO实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-10-24 10:02:29
 * @version: 1.0
 */
@Repository("BusiContractTendersContractDao")
public class  BusiContractTendersContractDaoImpl extends AbstractDao<Long,  BusiContractTendersContract> implements  BusiContractTendersContractDao {

    @Override
    public BusiContractTendersContract findBusiContractTendersContractById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractTendersContract( BusiContractTendersContract entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractTendersContract(BusiContractTendersContract entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractTendersContract(BusiContractTendersContract entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractTendersContract> findAllBusiContractTendersContract() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractTendersContract findBusiContractTendersContractByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        return (BusiContractTendersContract) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractTendersContract> getAllBusiContractTendersContract(QueryPage queryPage) {
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
    public BusiContractTendersContract findBusiContractTendersContractByProcessInstanceId(String flowInstanceId){
    	if(StringUtils.isNotEmpty(flowInstanceId)){
    		Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("flowInstanceId", Long.valueOf(flowInstanceId)));
            criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
            return (BusiContractTendersContract) criteria.uniqueResult();
    	}else{
    		return null;
    	}
	}
}
