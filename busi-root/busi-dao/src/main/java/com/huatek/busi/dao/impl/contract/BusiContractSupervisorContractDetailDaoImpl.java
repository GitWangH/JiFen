package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractSupervisorContractDetailDao;
import com.huatek.busi.model.contract.BusiContractSupervisorContractDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiContractSupervisorContractDetailDaoImpl
  * @Description: 监理合同清单（安全计量设置）DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:10:58
  * @version: 1.0
  */
@Repository("BusiContractSupervisorContractDetailDao")
public class  BusiContractSupervisorContractDetailDaoImpl extends AbstractDao<Long,  BusiContractSupervisorContractDetail> implements  BusiContractSupervisorContractDetailDao {


    @Override
    public void saveOrUpdateBusiContractSupervisorContractDetail( BusiContractSupervisorContractDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractSupervisorContractDetail(BusiContractSupervisorContractDetail entity) {
        super.persistent(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractSupervisorContractDetail> findAllBusiContractSupervisorContractDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public DataPage<BusiContractSupervisorContractDetail> getAllBusiContractSupervisorContractDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    /**
     * 批量保存
     * @param busiContractSupervisorContractDetailList
     */
    @Override
    public void batchSaveTreeGridData(List<BusiContractSupervisorContractDetail> busiContractSupervisorContractDetailList){
    	super.batchSaveForMerge(busiContractSupervisorContractDetailList, 500);
	}

    /**
     * 查询清单列表数据
     */
	@Override
	public List<BusiContractSupervisorContractDetail> findAllBusiContractSupervisorContractDetail(QueryPage queryPage) {
		return super.queryListData(queryPage);
	}

	/**
	 * 批量保存
	 */
	@Override
	public void batchSaveBusiContractSupervisorContractDetailList(List<BusiContractSupervisorContractDetail> addEntityList) {
		super.batchSaveForMerge(addEntityList, 500);
		
	}

	/**
	 * 批量更新
	 */
	@Override
	public void batchUpdateBusiContractSupervisorContractDetailList(List<BusiContractSupervisorContractDetail> updateEntityList) {
		super.batchSaveForMerge(updateEntityList, 500);
		
	}

	/**
	 * 批量删除
	 */
	@Override
	public void batchDeleteBusiContractSupervisorContractDetailList(List<BusiContractSupervisorContractDetail> deleteEntityList) {
		super.batchDelete(deleteEntityList, 500);
		
	}

	/**
	 * 根据父节点查询子节点数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiContractSupervisorContractDetail> findChildBusiContractSupervisorContractDetailDtoByParentUUID(String parentUUID) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", parentUUID));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        criteria.addOrder(Order.asc("orderNumber"));
        return criteria.list();
	}

}
