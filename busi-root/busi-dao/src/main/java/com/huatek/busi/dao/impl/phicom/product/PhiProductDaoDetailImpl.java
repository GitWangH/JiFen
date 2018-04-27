package com.huatek.busi.dao.impl.phicom.product;

import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.product.PhiProductDao;
import com.huatek.busi.dao.phicom.product.PhiProductDetailDao;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.phicom.product.PhiProductDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@Repository("PhiProductDetailDao")
public class PhiProductDaoDetailImpl extends AbstractDao<Long,  PhiProductDetail> implements  PhiProductDetailDao {

private Logger logger = LoggerFactory.getLogger(PhiProductDaoDetailImpl.class);	

	@Override
	public PhiProductDetail findPhiProductDetailById(Long id) {
		return super.getByKey(id);
	}

	@Override
	public void saveOrUpdatePhiProductDetail(PhiProductDetail entity) {
		super.saveOrUpdate(entity);
		
	}

	@Override
	public void persistentPhiProductDetail(PhiProductDetail entity) {
		 super.persistent(entity);
		
	}

	@Override
	public void deletePhiProductDetail(PhiProductDetail entity) {
		super.delete(entity);
		
	}

	@Override
	public List<PhiProductDetail> findAllPhiProductDetail() {		
		return createEntityCriteria().list();
	}

	@Override
	public PhiProductDetail findPhiProductDetailByCondition(String condition) {
		  Criteria criteria = createEntityCriteria();
	      return (PhiProductDetail) criteria.uniqueResult();
	}

	@Override
	public DataPage<PhiProductDetail> getAllPhiProductDetail(QueryPage queryPage) {
	
		return super.queryPageData(queryPage);
	}

	
	
}
