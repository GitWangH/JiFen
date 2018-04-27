package com.huatek.busi.dao.impl.pluspageLayout;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.pluspageLayout.PhiPlusPagelayoutDao;
import com.huatek.busi.model.pluspageLayout.PhiPlusPagelayout;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiPlusPagelayoutDaoImpl
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-23 15:00:36
  * @version: Windows 7
  */

@Repository("PhiPlusPagelayoutDao")
public class  PhiPlusPagelayoutDaoImpl extends AbstractDao<Long,  PhiPlusPagelayout> implements  PhiPlusPagelayoutDao {

    private Logger logger = LoggerFactory.getLogger(PhiPlusPagelayoutDaoImpl.class);

    @Override
    public PhiPlusPagelayout findPhiPlusPagelayoutById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPlusPagelayout( PhiPlusPagelayout entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPlusPagelayout(PhiPlusPagelayout entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPlusPagelayout(PhiPlusPagelayout entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPlusPagelayout> findAllPhiPlusPagelayout() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPlusPagelayout findPhiPlusPagelayoutByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPlusPagelayout) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPlusPagelayout> getAllPhiPlusPagelayout(QueryPage queryPage) {
        return super.queryPageData(queryPage, false);
    }

	@Override
	public List<PhiPlusPagelayout> getCountByParentId(Long id) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", id));
		return criteria.list();
	}

	@Override
	public void batchAdd(List<PhiPlusPagelayout> entityList) {
		batchSave(entityList,200);
		
	}

	/***
	 *clientType 1:移动端；2:PC端
	 */
	@Override
	public PhiPlusPagelayout findPhiPlusPagelayoutByCode(String code,String clientType) {
		String clentStr = null;
		if("1".equals(clientType)){
			clentStr = new String("移动端");
		}else if("2".equals(clientType)){
			clentStr = new String("PC端");
		}
		String hql = "from PhiPlusPagelayout t where t.code =:code and t.client =:clentStr";
		Query query = super.createQuery(hql);
    	query.setString("code", code);
    	query.setString("clentStr", clentStr);
    	PhiPlusPagelayout pagelayout = (PhiPlusPagelayout) query.uniqueResult();
		return pagelayout;
		
		
	}

}
