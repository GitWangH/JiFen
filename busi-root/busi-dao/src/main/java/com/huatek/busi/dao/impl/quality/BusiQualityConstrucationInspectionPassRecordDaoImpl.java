package com.huatek.busi.dao.impl.quality;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.quality.BusiQualityConstrucationInspectionPassRecordDao;
import com.huatek.busi.model.quality.BusiQualityConstrucationInspectionPassRecord;
import com.huatek.busi.model.quality.BusiQualityConstructionInspection;
import com.huatek.frame.core.dao.AbstractDao;
/**
 * 施工报检审批通过记录dao实现
 * @author rocky_wei
 *
 */
@Repository("busiQualityConstrucationInspectionPassRecordDaoImpl")
public class BusiQualityConstrucationInspectionPassRecordDaoImpl extends AbstractDao<Long, BusiQualityConstrucationInspectionPassRecord>
		implements BusiQualityConstrucationInspectionPassRecordDao {
	
	@Override
	public void saveConstrucationInspectionPassRecord(BusiQualityConstrucationInspectionPassRecord entity) {
		super.save(entity);
	}

	@Override
	public List<BusiQualityConstrucationInspectionPassRecord> findConstrucationInspectionPassRecords(Long constrctionId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("constructionInspectionId", constrctionId));
		criteria.addOrder(Order.asc("approverTime"));
		return criteria.list();
	}

}
