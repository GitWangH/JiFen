package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractOtherContractDao;
import com.huatek.busi.model.contract.BusiContractOtherContract;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;


 /**
  * @ClassName: BusiContractOtherContractDaoImpl
  * @Description:  其它合同管理DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-27 11:07:02
  * @version: 1.0
  */
@Repository("BusiContractOtherContractDao")
public class  BusiContractOtherContractDaoImpl extends AbstractDao<Long,  BusiContractOtherContract> implements  BusiContractOtherContractDao {

    @Override
    public BusiContractOtherContract findBusiContractOtherContractById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractOtherContract( BusiContractOtherContract entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractOtherContract(BusiContractOtherContract entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractOtherContract(BusiContractOtherContract entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractOtherContract> findAllBusiContractOtherContract() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractOtherContract findBusiContractOtherContractByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        return (BusiContractOtherContract) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractOtherContract> getAllBusiContractOtherContract(QueryPage queryPage) {
    	List<QueryParam> paramList = queryPage.getQueryParamList();    	  
		paramList.add(new QueryParam("isDelete", "long", "=", "0"));//未删除的数据
        return super.queryPageData(queryPage);
    }

}
