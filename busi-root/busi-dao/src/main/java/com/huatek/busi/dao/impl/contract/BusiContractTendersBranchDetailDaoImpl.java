package com.huatek.busi.dao.impl.contract;
   
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.contract.BusiContractTendersBranchDetailDao;
import com.huatek.busi.model.contract.BusiContractTendersBranchDetail;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiContractTendersBranchDetailDaoImpl
  * @Description: 标段分部分项清单DAO接口实现类
  * @author: mickey_meng
  * @Email : mickey_meng@huatek.com
  * @date: 2017-10-25 15:11:54
  * @version: 1.0
  */
@Repository("BusiContractTendersBranchDetailDao")
public class  BusiContractTendersBranchDetailDaoImpl extends AbstractDao<Long,  BusiContractTendersBranchDetail> implements  BusiContractTendersBranchDetailDao {

    @Override
    public BusiContractTendersBranchDetail findBusiContractTendersBranchDetailById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiContractTendersBranchDetail( BusiContractTendersBranchDetail entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiContractTendersBranchDetail(BusiContractTendersBranchDetail entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiContractTendersBranchDetail(BusiContractTendersBranchDetail entity) {
        super.delete(entity);
        flush();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiContractTendersBranchDetail> findAllBusiContractTendersBranchDetail() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiContractTendersBranchDetail findBusiContractTendersBranchDetailByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiContractTendersBranchDetail) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiContractTendersBranchDetail> getAllBusiContractTendersBranchDetail(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

    /**
     * 根据分部分项主表id软删除明细数据
     */
	@Override
	public void deleteBusiContractTendersBranchDetailByTendersBranchId(Long id) {
		if(null != id){
//			super.executeHsql("UPDATE BusiContractTendersBranchDetail SET isDelete = 1 WHERE busiContractTendersBranch.id =" + id, null);//软删除
			super.executeHsql("DELETE FROM BusiContractTendersBranchDetail WHERE busiContractTendersBranch.id =" + id, null);//软删除
		}
	}
	
	/**
	 * 批量持久化分部分项清单数据
	 * @param persistBusiContractTendersBranchDetailList
	 */
	@Override
	public void batchSaveBusiContractTendersBranchDetailList(List<BusiContractTendersBranchDetail> persistBusiContractTendersBranchDetailList){
		super.batchSaveForMerge(persistBusiContractTendersBranchDetailList, 500);
	}

}
