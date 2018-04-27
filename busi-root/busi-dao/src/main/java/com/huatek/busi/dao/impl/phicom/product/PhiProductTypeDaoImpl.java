package com.huatek.busi.dao.impl.phicom.product;
   
import java.util.List;




import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.product.PhiProductTypeDao;
import com.huatek.busi.model.phicom.product.PhiProductType;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiProductTypeDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-28 20:33:09
  * @version: 1.0
  */

@Repository("PhiProductTypeDao")
public class  PhiProductTypeDaoImpl extends AbstractDao<Long,  PhiProductType> implements  PhiProductTypeDao {

    private Logger logger = LoggerFactory.getLogger(PhiProductTypeDaoImpl.class);

    @Override
    public PhiProductType findPhiProductTypeById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiProductType( PhiProductType entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiProductType(PhiProductType entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiProductType(PhiProductType entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiProductType> findAllPhiProductType() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiProductType findPhiProductTypeByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiProductType) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiProductType> getAllPhiProductType(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public List<PhiProductType> findPhiProductTypeByName(String name) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("typeName", name));
		return criteria.list();
	}

	@Override
	public List<PhiProductType> findPhiProductTypeForApp() {
	    String 	hql = "from  PhiProductType t where t.isRecomment ='1' order by showOrder";
	    Query query = super.createQuery(hql);
	    List<PhiProductType> phiProductTypes = query.list();
		return phiProductTypes;
	}

	
}
