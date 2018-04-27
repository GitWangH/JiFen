package com.huatek.file.dao;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.file.modal.CmdFile;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: CmdFileDaoImpl
  * @Description: 
  * @author: hobart_ren
  * @date: 2016-05-05 06:27:01
  * @version: 1.0
  */

@Repository("cmdFileDao")
public class  CmdFileDaoImpl extends AbstractDao<Long,  CmdFile> implements  CmdFileDao {

    private Logger logger = LoggerFactory.getLogger(CmdFileDaoImpl.class);

    @Override
    public CmdFile findCmdFileById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateCmdFile( CmdFile entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentCmdFile(CmdFile entity) {
        super.save(entity);
    	//super.persistent(entity);
    }


    @Override
    public void deleteCmdFile(CmdFile entity) {
        super.delete(entity);
        super.flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CmdFile> findAllCmdFile() {
        return createEntityCriteria().list();
    }

    @Override
    public CmdFile findCmdFileByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (CmdFile) criteria.uniqueResult();
    }

    @Override
    public DataPage<CmdFile> getAllCmdFile(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	/* (non-Javadoc)
	 * @see com.huatek.file.imp.dao.CmdFileDao#getCmdFileDtoByBusiId()
	 */
	@Override
	public List<CmdFile> getCmdFileDtoByBusiId(String businessId) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("businessId", businessId));
        return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmdFile> getCmdFileDtoByBusiIds(String[] businessIds) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.in("businessId", businessIds));
        return criteria.list();
	}
}
