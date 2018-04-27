package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractContractChangeDetailDao;
import com.huatek.busi.model.contract.BusiContractContractChangeDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiContractContractChangeDetailDaoImpl
  * @Description: 合同变更明细DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:12:40
  * @version: 1.0
  */
@Repository("BusiContractContractChangeDetailDao")
public class  BusiContractContractChangeDetailDaoImpl extends AbstractDao<Long,  BusiContractContractChangeDetail> implements  BusiContractContractChangeDetailDao {

    @Override
    public BusiContractContractChangeDetail findBusiContractContractChangeDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractContractChangeDetail( BusiContractContractChangeDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractContractChangeDetail(BusiContractContractChangeDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractContractChangeDetail(BusiContractContractChangeDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractContractChangeDetail> findAllBusiContractContractChangeDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractContractChangeDetail findBusiContractContractChangeDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiContractContractChangeDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractContractChangeDetail> getAllBusiContractContractChangeDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
