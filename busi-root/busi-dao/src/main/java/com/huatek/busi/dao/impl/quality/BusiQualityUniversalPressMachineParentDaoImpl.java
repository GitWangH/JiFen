package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityUniversalPressMachineParentDao;
import com.huatek.busi.model.quality.BusiQualityUniversalPressMachineParent;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualityUniversalPressMachineParentDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:14:11
  * @version: Windows 7
  */

@Repository("BusiQualityUniversalPressMachineParentDao")
public class  BusiQualityUniversalPressMachineParentDaoImpl extends AbstractDao<Long,  BusiQualityUniversalPressMachineParent> implements  BusiQualityUniversalPressMachineParentDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualityUniversalPressMachineParentDaoImpl.class);

    @Override
    public BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualityUniversalPressMachineParent( BusiQualityUniversalPressMachineParent entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualityUniversalPressMachineParent(BusiQualityUniversalPressMachineParent entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualityUniversalPressMachineParent(BusiQualityUniversalPressMachineParent entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualityUniversalPressMachineParent> findAllBusiQualityUniversalPressMachineParent() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualityUniversalPressMachineParent) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualityUniversalPressMachineParent> getAllBusiQualityUniversalPressMachineParent(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualityUniversalPressMachineParent> findBusiQualityUniversalPressMachineParentByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<BusiQualityUniversalPressMachineParent> universalPressMachineParents) {
		super.batchSaveForMerge(universalPressMachineParents, 20);
		
	}

	@Override
	public List<BusiQualityUniversalPressMachineParent> findBusiQualityUniversalPressMachineParentByCondition(String condition, String field) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
	}
	
	@Override
	public BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentByUniversalMachineId(Long id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("busiQualityUniversalMachine.id", id));
		return (BusiQualityUniversalPressMachineParent) criteria.uniqueResult();
	}

	@Override
	public BusiQualityUniversalPressMachineParent findBusiQualityUniversalPressMachineParentByBusiQualityPressMachineId(Long id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("busiQualityPressMachine.id", id));
		return (BusiQualityUniversalPressMachineParent) criteria.uniqueResult();
	}
}
