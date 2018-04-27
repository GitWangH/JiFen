package com.huatek.busi.dao.impl.phicom.plusmember;

import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.member.ViewPhiPlusMemberOrderDao;
import com.huatek.busi.model.phicom.plusmember.PhiPlusMemberOrder;
import com.huatek.busi.model.phicom.plusmember.ViewPhiPlusMemberOrder;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

@Repository("ViewPhiPlusMemberOrderDaoImpl")
public class ViewPhiPlusMemberOrderDaoImpl extends
AbstractDao<Long, ViewPhiPlusMemberOrder> implements ViewPhiPlusMemberOrderDao {

	@Override
	public DataPage<ViewPhiPlusMemberOrder> getAllViewPhiPlusMemberOrder(QueryPage queryPage) {
		return super.queryPageData(queryPage);
	}

}
