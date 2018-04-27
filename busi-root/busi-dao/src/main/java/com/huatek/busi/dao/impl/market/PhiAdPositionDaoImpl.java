package com.huatek.busi.dao.impl.market;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.market.PhiAdPositionDao;
import com.huatek.busi.model.market.PhiAdPosition;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiAdPositionDaoImpl
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-19 13:43:48
  * @version: Windows 7
  */

@Repository("PhiAdPositionDao")
public class  PhiAdPositionDaoImpl extends AbstractDao<Long,  PhiAdPosition> implements  PhiAdPositionDao {

    private Logger logger = LoggerFactory.getLogger(PhiAdPositionDaoImpl.class);

    @Override
    public PhiAdPosition findPhiAdPositionById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiAdPosition( PhiAdPosition entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiAdPosition(PhiAdPosition entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiAdPosition(PhiAdPosition entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiAdPosition> findAllPhiAdPosition() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiAdPosition findPhiAdPositionByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiAdPosition) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiAdPosition> getAllPhiAdPosition(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    //批量添加
    
    public void batchAdd(List<PhiAdPosition> phiAdPositionList) {
    	
    	batchSave(phiAdPositionList,200);
    }

	@Override
	public List<PhiAdPosition> getAdPositionAndPhoInfoByAdCode(String adCode) {
		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("adCode", adCode));
		return criteria.list();
		//return null;
	}
    
}
