package com.huatek.frame.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.model.FwSourceEntity;

@Repository("FwSourceEntity")
@Transactional
public class FwSourceEntityDaoImpl extends AbstractDao<Long, FwSourceEntity> implements FwSourceEntityDao {
	
	Logger logger =  LoggerFactory.getLogger(FwSourceEntityDaoImpl.class);
	
	public DataPage<FwSourceEntity> getAllFwSourceEntity(QueryPage queryPage){
		return queryPageData(queryPage);
	}
	
	@SuppressWarnings("unchecked")
	public List<FwSourceEntity> findAllFwSourceEntity() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	/**
	 * 持久化保存
	 */
	@Override
	public void persistent(FwSourceEntity fwSourceEntity) {
		super.persistent(fwSourceEntity);
	}

	/**
	 * 根据Id找到数据角色
	 * @param id
	 * @return
	 */
	public FwSourceEntity findById(Long id) {
		return super.getByKey(id);
	}
	
	/**
	 * 删除数据角色信息
	 */
	public void deleteFwSourceEntity(FwSourceEntity fwSourceEntity) {
		super.delete(fwSourceEntity);
	}

	public FwSourceEntity getFwSourceEntityByName(String name) {
		String hql = "from FwSourceEntity t where t.name=?";
		return (FwSourceEntity)super.createQuery(hql).setString(0, name).uniqueResult();
	}

}
