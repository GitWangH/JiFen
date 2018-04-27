package com.huatek.busi.dao.impl.phicom.member;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.member.PhiMemberPrivilegeDao;
import com.huatek.busi.model.phicom.member.PhiMemberPrivilege;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiMemberPrivilegeDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2018-01-22 21:00:56
  * @version: Windows 7
  */

@Repository("PhiMemberPrivilegeDao")
public class  PhiMemberPrivilegeDaoImpl extends AbstractDao<Long,  PhiMemberPrivilege> implements  PhiMemberPrivilegeDao {

    private Logger logger = LoggerFactory.getLogger(PhiMemberPrivilegeDaoImpl.class);

    @Override
    public PhiMemberPrivilege findPhiMemberPrivilegeById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiMemberPrivilege( PhiMemberPrivilege entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiMemberPrivilege(PhiMemberPrivilege entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiMemberPrivilege(PhiMemberPrivilege entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiMemberPrivilege> findAllPhiMemberPrivilege() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiMemberPrivilege findPhiMemberPrivilegeByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiMemberPrivilege) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiMemberPrivilege> getAllPhiMemberPrivilege(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public PhiMemberPrivilege findPhiMemberPrivilegeByMemberGradeId(Long gradeId) {
		 Criteria criteria = createEntityCriteria();
	        //TODO 查询条件
	        criteria.add(Restrictions.eq("phiMemberGrade.id", gradeId));
	        return (PhiMemberPrivilege) criteria.uniqueResult();
	}

	@Override
	public PhiMemberPrivilege findPhiMemberBirthdayPrivilege() {
		 Criteria criteria = createEntityCriteria();
	        //TODO 查询条件
	       criteria.add(Restrictions.eq("privilegeType", "2"));
	        return (PhiMemberPrivilege) criteria.uniqueResult();
	}

}
