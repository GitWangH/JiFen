package com.huatek.busi.dao.impl.phicom.plusmember;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.plusmember.PhiPlusGradeDao;
import com.huatek.busi.model.phicom.coupons.PhiCoupons;
import com.huatek.busi.model.phicom.plusmember.PhiPlusGrade;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiPlusGradeDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-03 19:59:33
  * @version: 1.0
  */

@Repository("PhiPlusGradeDao")
public class  PhiPlusGradeDaoImpl extends AbstractDao<Long,  PhiPlusGrade> implements  PhiPlusGradeDao {

    private Logger logger = LoggerFactory.getLogger(PhiPlusGradeDaoImpl.class);

    @Override
    public PhiPlusGrade findPhiPlusGradeById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPlusGrade( PhiPlusGrade entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPlusGrade(PhiPlusGrade entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPlusGrade(PhiPlusGrade entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPlusGrade> findAllPhiPlusGrade() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPlusGrade findPhiPlusGradeByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPlusGrade) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPlusGrade> getAllPhiPlusGrade(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public PhiPlusGrade findPhiPlusGradeByPlusgrade(String plusGrade) {
		String hql = "from PhiPlusGrade t where t.plusGrade =:plusGrade";
		Query query = super.createQuery(hql);
    	query.setString("plusGrade", plusGrade);
    	PhiPlusGrade 	 entity = (PhiPlusGrade)query.uniqueResult();   	
		return entity;
	}
	@Override
	public PhiPlusGrade findPhiPlusGradeByPlusCode(String plusCode) {
		String hql = "from PhiPlusGrade t where t.plusCode =:plusCode";
		Query query = super.createQuery(hql);
    	query.setString("plusCode", plusCode);
    	PhiPlusGrade 	 entity = (PhiPlusGrade)query.uniqueResult();   	
		return entity;
	}
}
