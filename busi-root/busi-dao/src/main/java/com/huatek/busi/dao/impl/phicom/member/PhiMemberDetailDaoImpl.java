package com.huatek.busi.dao.impl.phicom.member;
   
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.member.PhiMemberDetailDao;
import com.huatek.busi.model.phicom.member.PhiMemberDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;



 /**
  * @ClassName: PhiMemberDetailDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2017-12-30 13:31:28
  * @version: 1.0
  */

@Repository("PhiMemberDetailDao")
public class  PhiMemberDetailDaoImpl extends AbstractDao<Long,  PhiMemberDetail> implements  PhiMemberDetailDao {

    private Logger logger = LoggerFactory.getLogger(PhiMemberDetailDaoImpl.class);

    @Override
    public PhiMemberDetail findPhiMemberDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiMemberDetail( PhiMemberDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiMemberDetail(PhiMemberDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiMemberDetail(PhiMemberDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiMemberDetail> findAllPhiMemberDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiMemberDetail findPhiMemberDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiMemberDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiMemberDetail> getAllPhiMemberDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
