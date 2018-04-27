package com.huatek.busi.dao.impl.phicom.member;

   
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.member.PhiMemberGradeDao;
import com.huatek.busi.model.phicom.member.PhiMemberGrade;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiMemberGradeDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-25 17:52:35
  * @version: 1.0
  */

@Repository("PhiMemberGradeDao")
public class  PhiMemberGradeDaoImpl extends AbstractDao<Long,  PhiMemberGrade> implements  PhiMemberGradeDao {

    private Logger logger = LoggerFactory.getLogger(PhiMemberGradeDaoImpl.class);

    @Override
    public PhiMemberGrade findPhiMemberGradeById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiMemberGrade( PhiMemberGrade entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiMemberGrade(PhiMemberGrade entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiMemberGrade(PhiMemberGrade entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiMemberGrade> findAllPhiMemberGrade() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiMemberGrade findPhiMemberGradeByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiMemberGrade) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiMemberGrade> getAllPhiMemberGrade(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    


	@Override
	public PhiMemberGrade getMemberGrade(BigDecimal allscore) {
		// TODO Auto-generated method stub
		String sql = "from PhiMemberGrade t where   t.scoreMax > :allscore and t.scoreMin <= :allscore order by t.scoreMin desc";
		Query query = this.createQuery(sql);
		query.setParameter("allscore", allscore);
		PhiMemberGrade pmg=(PhiMemberGrade)query.uniqueResult();
		return pmg;
	}

	@Override
	public List<PhiMemberGrade> findPhiMemberGradeBygrade(String memberGrade) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("memberGrade", memberGrade));
		return criteria.list();
	}
	
}
