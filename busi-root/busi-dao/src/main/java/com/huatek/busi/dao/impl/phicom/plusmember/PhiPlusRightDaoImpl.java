package com.huatek.busi.dao.impl.phicom.plusmember;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.plusmember.PhiPlusRightDao;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRight;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiPlusRightDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-10 15:19:58
  * @version: 1.0
  */

@Repository("PhiPlusRightDao")
public class  PhiPlusRightDaoImpl extends AbstractDao<Long,  PhiPlusRight> implements  PhiPlusRightDao {

    private Logger logger = LoggerFactory.getLogger(PhiPlusRightDaoImpl.class);

    @Override
    public PhiPlusRight findPhiPlusRightById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPlusRight( PhiPlusRight entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPlusRight(PhiPlusRight entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPlusRight(PhiPlusRight entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPlusRight> findAllPhiPlusRight() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPlusRight findPhiPlusRightByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("tasktype", condition));
        return (PhiPlusRight) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPlusRight> getAllPhiPlusRight(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    /***
     * 查看开启的plus特权
     */
	@Override
	public PhiPlusRight findPhiPlusRightByTaskType(String  tasktype) {
		String sql = "from PhiPlusRight t where t.taskswitch = 'on' and t.tasktype = :tasktype ";
		Query query = this.createQuery(sql);
		query.setParameter("tasktype", tasktype);
		PhiPlusRight plusRight  = null;
		List<PhiPlusRight> rights= query.list();
		if(rights != null && rights.size()>0 ){
			plusRight = rights.get(0);
		}
		return plusRight;
		
	}

}
