package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractContractChangeDao;
import com.huatek.busi.model.contract.BusiContractContractChange;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiContractContractChangeDaoImpl
  * @Description: 合同变更DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:12:22
  * @version: 1.0
  */
@Repository("BusiContractContractChangeDao")
public class  BusiContractContractChangeDaoImpl extends AbstractDao<Long,  BusiContractContractChange> implements  BusiContractContractChangeDao {

    @Override
    public BusiContractContractChange findBusiContractContractChangeById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractContractChange( BusiContractContractChange entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractContractChange(BusiContractContractChange entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractContractChange(BusiContractContractChange entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractContractChange> findAllBusiContractContractChange() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractContractChange findBusiContractContractChangeByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiContractContractChange) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractContractChange> getAllBusiContractContractChange(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public BusiContractContractChange findBusiContractContractChangeByProcessInstanceId(String flowInstanceId) {
		if(StringUtils.isNotEmpty(flowInstanceId)){
    		Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("flowInstanceId", Long.valueOf(flowInstanceId)));
            return (BusiContractContractChange) criteria.uniqueResult();
    	}else{
    		return null;
    	}
	}

}
