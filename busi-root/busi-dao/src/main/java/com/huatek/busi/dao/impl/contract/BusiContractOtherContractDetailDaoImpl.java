package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractOtherContractDetailDao;
import com.huatek.busi.model.contract.BusiContractOtherContractDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiContractOtherContractDetailDaoImpl
  * @Description: 其它合同明细管理DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-27 11:08:20
  * @version: 1.0
  */
@Repository("BusiContractOtherContractDetailDao")
public class  BusiContractOtherContractDetailDaoImpl extends AbstractDao<Long,  BusiContractOtherContractDetail> implements  BusiContractOtherContractDetailDao {

    @Override
    public BusiContractOtherContractDetail findBusiContractOtherContractDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractOtherContractDetail( BusiContractOtherContractDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractOtherContractDetail(BusiContractOtherContractDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractOtherContractDetail(BusiContractOtherContractDetail entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractOtherContractDetail> findAllBusiContractOtherContractDetail() {
        return createEntityCriteria().list();
    }


    @Override
    public DataPage<BusiContractOtherContractDetail> getAllBusiContractOtherContractDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    /**
     * 批量保存
     */
	@Override
	public void batchSaveTreeGridData(List<BusiContractOtherContractDetail> busiContractOtherContractDetailList) {
		super.batchSaveForMerge(busiContractOtherContractDetailList, 500);
	}
	
	/**
	 * 查询所有数据
	 * @param queryPage
	 * @return
	 */
	@Override
	public List<BusiContractOtherContractDetail> findAllBusiContractOtherContractDetail(QueryPage queryPage){
		return super.queryListData(queryPage);
	}
	
	/**
	 * 根据父节点的UUID查询子节点数据
	 * @param parentUUID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BusiContractOtherContractDetail> findChildBusiContractOtherContractDetailByParentUUID(String parentUUID){
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", parentUUID));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        criteria.addOrder(Order.asc("orderNumber"));
        return criteria.list();
	}

	/**
	 * 批量保存
	 */
	@Override
	public void batchSaveBusiContractOtherContractDetailList(List<BusiContractOtherContractDetail> addEntityList) {
		super.batchSave(addEntityList, 500);
	}

	/**
	 * 批量更新
	 */
	@Override
	public void batchUpdateBusiContractOtherContractDetailList(List<BusiContractOtherContractDetail> updateEntityList) {
		super.batchSaveForMerge(updateEntityList, 500);
		
	}

	/**
	 * 批量删除
	 */
	@Override
	public void batchDeleteBusiContractOtherContractDetailList(List<BusiContractOtherContractDetail> deleteEntityList) {
		super.batchDelete(deleteEntityList, 500);
	}

}
