package com.huatek.busi.dao.impl.market;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.market.PhiPhoInfoDao;
import com.huatek.busi.model.market.PhiAdPosition;
import com.huatek.busi.model.market.PhiPhoInfo;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiPhoInfoDaoImpl
  * @Description: 
  * @author: nemo_wang
  * @Email : 
  * @date: 2018-01-19 13:43:48
  * @version: Windows 7
  */

@Repository("PhiPhoInfoDao")
public class  PhiPhoInfoDaoImpl extends AbstractDao<Long,  PhiPhoInfo> implements  PhiPhoInfoDao {

    private Logger logger = LoggerFactory.getLogger(PhiPhoInfoDaoImpl.class);

    @Override
    public PhiPhoInfo findPhiPhoInfoById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiPhoInfo( PhiPhoInfo entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiPhoInfo(PhiPhoInfo entity) {
        super.persistent(entity);
    }


    @Override
    public void deletePhiPhoInfo(PhiPhoInfo entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiPhoInfo> findAllPhiPhoInfo() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiPhoInfo findPhiPhoInfoByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiPhoInfo) criteria.uniqueResult();
    }

    @Override
    public DataPage<PhiPhoInfo> getAllPhiPhoInfo(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    /*批量保存*/
    @Override
	public void batchAdd(List<PhiPhoInfo> phiPhoInfoList) {
	    	batchSave(phiPhoInfoList,200);
	  }

	@Override
	public List<PhiPhoInfo> getAdPositionAndPhoInfoByAdCode(String adCode) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("adCode", adCode));
		return criteria.list();
		//return null;
	}

	@Override
	public List<PhiPhoInfo> getPhiPhoInfoByAdCode(String adCode) {
		return null;
	}

	@Override
	public void batchDelete(List<PhiPhoInfo> phiPhoInfoList) {
		batchDelete(phiPhoInfoList,200);
		
	}
}
