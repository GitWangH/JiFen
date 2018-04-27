package com.huatek.busi.dao.impl.phicom.game;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.game.PhiGameUserDao;
import com.huatek.busi.model.phicom.game.PhiGameUser;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiGameUserDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-02-09 14:48:27
  * @version: 1.0
  */

@Repository("PhiGameUserDao")
public class  PhiGameUserDaoImpl extends AbstractDao<Long,  PhiGameUser> implements  PhiGameUserDao {

    private Logger logger = LoggerFactory.getLogger(PhiGameUserDaoImpl.class);

    @Override
    public PhiGameUser findPhiGameUserById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiGameUser( PhiGameUser entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiGameUser(PhiGameUser entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiGameUser(PhiGameUser entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiGameUser> findAllPhiGameUser() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiGameUser findPhiGameUserByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiGameUser) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiGameUser> getAllPhiGameUser(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    public List<PhiGameUser> findWheelGameUsersBymemberTel(String  tel){
    	String hql = "from PhiGameUser t where  t.tel =:tel and t.gameType = '大转盘' order by createTime desc";
		Query query = createQuery(hql);
		query.setParameter("tel", tel);
		List<PhiGameUser>  gameUserList = query.list();
		if(gameUserList == null  || gameUserList.size() < 0 ){
			return null;
		}
    	return gameUserList;
    }
    public List<PhiGameUser> findPlacesGameUsersBymemberTel(String  tel){
    	String hql = "from PhiGameUser t where  t.tel =:tel and t.gameType = '九宫格' order by createTime desc";
		Query query = createQuery(hql);
		query.setParameter("tel", tel);
		List<PhiGameUser>  gameUserList = query.list();
		if(gameUserList == null  || gameUserList.size() < 0 ){
			return null;
		}
    	return gameUserList;
    }
}
