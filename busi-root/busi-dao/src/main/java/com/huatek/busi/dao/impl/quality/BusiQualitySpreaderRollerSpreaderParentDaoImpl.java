package com.huatek.busi.dao.impl.quality;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySpreaderRollerSpreaderParentDao;
import com.huatek.busi.model.quality.BusiQualitySpreaderRollerSpreaderParent;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiQualitySpreaderRollerSpreaderParentDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-11-03 09:29:54
  * @version: Windows 7
  */

@Repository("BusiQualitySpreaderRollerSpreaderParentDao")
public class  BusiQualitySpreaderRollerSpreaderParentDaoImpl extends AbstractDao<Long,  BusiQualitySpreaderRollerSpreaderParent> implements  BusiQualitySpreaderRollerSpreaderParentDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySpreaderRollerSpreaderParentDaoImpl.class);

    @Override
    public BusiQualitySpreaderRollerSpreaderParent findBusiQualitySpreaderRollerSpreaderParentById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySpreaderRollerSpreaderParent( BusiQualitySpreaderRollerSpreaderParent entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySpreaderRollerSpreaderParent(BusiQualitySpreaderRollerSpreaderParent entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySpreaderRollerSpreaderParent(BusiQualitySpreaderRollerSpreaderParent entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySpreaderRollerSpreaderParent> findAllBusiQualitySpreaderRollerSpreaderParent() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiQualitySpreaderRollerSpreaderParent findBusiQualitySpreaderRollerSpreaderParentByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiQualitySpreaderRollerSpreaderParent) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiQualitySpreaderRollerSpreaderParent> getAllBusiQualitySpreaderRollerSpreaderParent(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<BusiQualitySpreaderRollerSpreaderParent> findBusiQualitySpreaderRollerSpreaderParentByIds(Long[] ids) {
		Criteria criteria = createEntityCriteria();
		if(null != ids && ids.length >0){
			criteria.add(Restrictions.in("id", ids));
		}
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<BusiQualitySpreaderRollerSpreaderParent> spreaderRollerSpreaderParents) {
		super.batchSaveForMerge(spreaderRollerSpreaderParents, 20);
	}

	@Override
	public List<BusiQualitySpreaderRollerSpreaderParent> findBusiQualitySpreaderRollerSpreaderParentByCondition(String condition, String field) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq(field, condition));
        return criteria.list();
	}

}
