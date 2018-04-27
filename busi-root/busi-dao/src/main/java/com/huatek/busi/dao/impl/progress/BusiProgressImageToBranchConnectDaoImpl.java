package com.huatek.busi.dao.impl.progress;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.progress.BusiProgressImageToBranchConnectDao;
import com.huatek.busi.model.progress.BusiProgressImageToBranchConnect;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiProgressImageToBranchConnectDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-12-06 11:30:29
  * @version: Windows 7
  */

@Repository("BusiProgressImageToBranchConnectDao")
public class  BusiProgressImageToBranchConnectDaoImpl extends AbstractDao<Long,  BusiProgressImageToBranchConnect> implements  BusiProgressImageToBranchConnectDao {

    private Logger logger = LoggerFactory.getLogger(BusiProgressImageToBranchConnectDaoImpl.class);

    @Override
    public BusiProgressImageToBranchConnect findBusiProgressImageToBranchConnectById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiProgressImageToBranchConnect( BusiProgressImageToBranchConnect entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiProgressImageToBranchConnect(BusiProgressImageToBranchConnect entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiProgressImageToBranchConnect(BusiProgressImageToBranchConnect entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiProgressImageToBranchConnect> findAllBusiProgressImageToBranchConnect() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiProgressImageToBranchConnect findBusiProgressImageToBranchConnectByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiProgressImageToBranchConnect) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiProgressImageToBranchConnect> getAllBusiProgressImageToBranchConnect(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiProgressImageToBranchConnect> findBusiProgressImageToBranchConnectByCondition(Long[] ids) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.in("id", ids));
		return criteria.list();
	}

	@Override
	public void batchDeleteBusiProgressImageToBranchConnect(List<BusiProgressImageToBranchConnect> list, int count) {
		super.batchDelete(list, count);
	}

}
