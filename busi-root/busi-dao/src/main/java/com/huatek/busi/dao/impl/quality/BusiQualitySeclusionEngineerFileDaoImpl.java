package com.huatek.busi.dao.impl.quality;
   
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualitySeclusionEngineerFileDao;
import com.huatek.busi.model.quality.BusiQualitySeclusionEngineerFile;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

 /**
  * @ClassName: BusiQualitySeclusionEngineerFileDaoImpl
  * @Description: 
  * @author: rocky_wei
  * @Email : 
  * @date: 2017-11-09 14:14:22
  * @version: Windows 7
  */

@Repository("BusiQualitySeclusionEngineerFileDao")
public class  BusiQualitySeclusionEngineerFileDaoImpl extends AbstractDao<Long,  BusiQualitySeclusionEngineerFile> implements  BusiQualitySeclusionEngineerFileDao {

    private Logger logger = LoggerFactory.getLogger(BusiQualitySeclusionEngineerFileDaoImpl.class);

    @Override
    public BusiQualitySeclusionEngineerFile findBusiQualitySeclusionEngineerFileById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiQualitySeclusionEngineerFile( BusiQualitySeclusionEngineerFile entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiQualitySeclusionEngineerFile(BusiQualitySeclusionEngineerFile entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiQualitySeclusionEngineerFile(BusiQualitySeclusionEngineerFile entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiQualitySeclusionEngineerFile> findAllBusiQualitySeclusionEngineerFile() {
        return createEntityCriteria().list();
    }

    @Override
    public List<BusiQualitySeclusionEngineerFile> findBusiQualitySeclusionEngineerFileByCondition(String field,String condition) {
        Criteria criteria = createEntityCriteria();
        if(StringUtils.isNoneBlank(field)){
        	if(field.contains(".")){
        		criteria.add(Restrictions.eq(field, Long.valueOf(condition)));
        	}else{
        		criteria.add(Restrictions.eq(field, condition));
        	}
        	return (List<BusiQualitySeclusionEngineerFile>) criteria.list();
        }
        return new ArrayList<BusiQualitySeclusionEngineerFile>();
    }

    @Override
    public DataPage<BusiQualitySeclusionEngineerFile> getAllBusiQualitySeclusionEngineerFile(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

}
