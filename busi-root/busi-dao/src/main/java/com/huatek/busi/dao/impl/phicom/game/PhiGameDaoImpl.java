package com.huatek.busi.dao.impl.phicom.game;
   
import java.util.List;




import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.game.PhiGameDao;
import com.huatek.busi.model.phicom.game.PhiGame;
import com.huatek.busi.model.phicom.game.PhiGameConfig;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiGameDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-09 10:32:01
  * @version: 1.0
  */

@Repository("PhiGameDao")
public class  PhiGameDaoImpl extends AbstractDao<Long,  PhiGame> implements  PhiGameDao {

    private Logger logger = LoggerFactory.getLogger(PhiGameDaoImpl.class);

    @Override
    public PhiGame findPhiGameById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiGame( PhiGame entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiGame(PhiGame entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiGame(PhiGame entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiGame> findAllPhiGame() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiGame findPhiGameByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiGame) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiGame> getAllPhiGame(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	public void merge(PhiGame entity){
		super.merge(entity);
	}

	@Override
	public PhiGame findPhiGameByType(String type) {
		String hql =  "from PhiGame t where t.type =:type";
		Query query = super.createQuery(hql);
		query.setParameter("type", type);
		PhiGame phiGame = (PhiGame) query.uniqueResult();				
		return phiGame;
	}
}
