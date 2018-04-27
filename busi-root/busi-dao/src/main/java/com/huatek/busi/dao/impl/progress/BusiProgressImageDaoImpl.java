package com.huatek.busi.dao.impl.progress;
   
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.progress.BusiProgressImageDao;
import com.huatek.busi.model.progress.BusiProgressImage;
import com.huatek.busi.model.report.QualityDistributionChart;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: BusiProgressImageDaoImpl
  * @Description: 
  * @author: jordan_li
  * @Email : 
  * @date: 2017-12-06 10:58:13
  * @version: Windows 7
  */

@Repository("BusiProgressImageDao")
public class  BusiProgressImageDaoImpl extends AbstractDao<Long,  BusiProgressImage> implements  BusiProgressImageDao {

    private Logger logger = LoggerFactory.getLogger(BusiProgressImageDaoImpl.class);

    @Override
    public BusiProgressImage findBusiProgressImageById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdateBusiProgressImage( BusiProgressImage entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentBusiProgressImage(BusiProgressImage entity) {
        super.persistent(entity);
    }


    @Override
    public void deleteBusiProgressImage(BusiProgressImage entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BusiProgressImage> findAllBusiProgressImage() {
        return createEntityCriteria().list();
    }

    @Override
    public BusiProgressImage findBusiProgressImageByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (BusiProgressImage) criteria.uniqueResult();
    }

    @Override
    public DataPage<BusiProgressImage> getAllBusiProgressImage(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }
    
    @Override
    public List<BusiProgressImage> getChildqNodesByParentUUIDAndOrgId(String uuid, Long orgId) {
		Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("parentId", uuid));
        criteria.add(Restrictions.eq("busiFwOrg.id", orgId));
        criteria.add(Restrictions.eq("isDelete", 0));//未删除的数据
        criteria.addOrder(Order.asc("orderNumber"));
        return criteria.list();
    }

	@Override
	public void initProgressImage(Long orgId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO busi_progress_image ( ");
		sql.append(" CODE, NAME, UNIT,KEY_PROJECT,REMARK,BASE_IMAGE_ID, ");
		sql.append(this.insertCommonField);
		sql.append(" ) ");
		sql.append(" SELECT a.NUMBER, a.NAME, a.UNIT,0,a.REMARK,a.IMAGE_LIST_ID, ");
		sql.append( this.getSelectCommonField(orgId));
		sql.append(" FROM busi_base_image_list a LEFT JOIN fw_org f on a.org_id = f.level_2 where a.IS_DELETE = 0 and f.org_id = "+orgId+"");
		this.createSQLQuery(sql.toString()).executeUpdate();
		
	}
    
	/*查询的共同数据*/
	private static String insertCommonField = " ORDER_NUMBER, ORG_ID, PARENT_ID, CREATER, CREATER_NAME, CREATE_TIME, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9, LEVEL_10, GROUP_LEVEL,IS_DELETE, IS_LEAF, UUID ";
	/*查询的共同数据*/
	private String getSelectCommonField(Long orgId){
		StringBuilder selectCommonField = new StringBuilder();
		selectCommonField.append(" a.ORDER_NUMBER, ");
		selectCommonField.append(orgId);
		selectCommonField.append(" , a.PARENT_ID, a.CREATER, ");
		selectCommonField.append(" '初始化', ");//CREATER_NAME
		selectCommonField.append(" now(), ");//CREATE_TIME
		selectCommonField.append(" a.LEVEL_1, a.LEVEL_2, a.LEVEL_3, a.LEVEL_4, a.LEVEL_5, a.LEVEL_6, a.LEVEL_7, a.LEVEL_8, a.LEVEL_9, a.LEVEL_10, a.GROUP_LEVEL, ");
		/*selectCommonField.append(tenantId);*/
		selectCommonField.append(" a.IS_DELETE, a.IS_LEAF, a.UUID ");
		return selectCommonField.toString();
	}
	
	@Override
	public List<BusiProgressImage> getDesignQuantityByImageId(Long imageId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select CONCAT(a.IMAGE_ID,'') as imageId, CONCAT(a.BRANCH_ID, '') as branchId, sum(b.DESIGN_TOTAL_PRICE) as sum  from  ");
		sb.append(" (select c.IMAGE_ID, c.BRANCH_ID from busi_progress_image_to_branch_connect c where c.IMAGE_ID in (  ");
		sb.append(" (select IMAGE_ID from busi_progress_image a where a.LEVEL_1 =  ");
		sb.append(" (select UUID from busi_progress_image where IMAGE_ID =   ");sb.append(imageId); sb.append(" ) ");
		sb.append(" AND a.GROUP_LEVEL <> 1 and a.IS_DELETE <> 1))) a LEFT JOIN busi_contract_tenders_branch_detail b  ");
		sb.append(" on a.BRANCH_ID = b.TENDERS_BRANCH_ID  ");
		sb.append(" GROUP BY a.IMAGE_ID, a.BRANCH_ID  ");
		List<BusiProgressImage> queryResult = this.queryEntityListBySql(sb.toString(), null, null);
		return queryResult;		 
	}
}
