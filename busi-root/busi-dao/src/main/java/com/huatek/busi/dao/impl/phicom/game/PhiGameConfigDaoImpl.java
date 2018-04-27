package com.huatek.busi.dao.impl.phicom.game;
   
import java.util.List;




import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.game.PhiGameConfigDao;
import com.huatek.busi.model.phicom.game.PhiGameConfig;
import com.huatek.busi.model.phicom.plusmember.PhiPlusRightGiftBagDetails;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiGameConfigDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-08 18:16:04
  * @version: 1.0
  */

@Repository("PhiGameConfigDao")
public class  PhiGameConfigDaoImpl extends AbstractDao<Long,  PhiGameConfig> implements  PhiGameConfigDao {

    private Logger logger = LoggerFactory.getLogger(PhiGameConfigDaoImpl.class);

    @Override
    public PhiGameConfig findPhiGameConfigById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiGameConfig( PhiGameConfig entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiGameConfig(PhiGameConfig entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiGameConfig(PhiGameConfig entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiGameConfig> findAllPhiGameConfig() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiGameConfig findPhiGameConfigByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiGameConfig) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiGameConfig> getAllPhiGameConfig(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
	public void merge(PhiGameConfig entity){
		super.merge(entity);
	}

	@Override
	public List<PhiGameConfig> findAllPhiGameConfigByGameId(Long id) {
	    String hql = "from PhiGameConfig t where t.gameId =:gameId";
	    Query query = super.createQuery(hql);
	    query.setParameter("gameId", id);
	    List<PhiGameConfig> phiGameConfigs = query.list();
		return phiGameConfigs;
	}
}
