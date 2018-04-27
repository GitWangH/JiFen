package com.huatek.busi.dao.impl.phicom.product;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.product.PhiProductDao;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.model.progress.BusiProgressImage;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

/**
 * @ClassName: PhiProductDaoImpl
 * @Description:
 * @author: Ken Bai
 * @Email : Ken_Bai@huatek.com
 * @date: 2017-12-18 15:44:42
 * @version: 1.0
 */

@Repository("PhiProductDao")
public class PhiProductDaoImpl extends AbstractDao<Long, PhiProduct> implements
		PhiProductDao {

	private Logger logger = LoggerFactory.getLogger(PhiProductDaoImpl.class);

	@Override
	public PhiProduct findPhiProductById(Long id) {
		return super.getByKey(id);
	}

	@Override
	public void saveOrUpdatePhiProduct(PhiProduct entity) {
		super.saveOrUpdate(entity);
	}

	@Override
	public void persistentPhiProduct(PhiProduct entity) {
		super.persistent(entity);
	}

	@Override
	public void deletePhiProduct(PhiProduct entity) {
		super.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhiProduct> findAllPhiProduct() {
		return createEntityCriteria().list();
	}

	@Override
	public PhiProduct findPhiProductByCondition(String condition) {
		Criteria criteria = createEntityCriteria();
		return (PhiProduct) criteria.uniqueResult();
	}

	@Override
	public DataPage<PhiProduct> getAllPhiProduct(QueryPage queryPage) {
		return super.queryPageData(queryPage);
	}

	@Override
	public List<PhiProduct> findPhiProductByName(String name) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("productName", name));
		return criteria.list();
	}

	@Override
	public List<PhiProduct> findPhiProductByStatus(String AuditStatus) {
		Criteria criteria = createCriteriaUncheck();
		criteria.add(Restrictions.eq("productStatus", AuditStatus));
		return criteria.list();
	}

	@Override
	public void batchUpdate(List<PhiProduct> PhiProductlist) {
		super.batchSaveForMerge(PhiProductlist, 20);

	}

	@Override
	public List<Map<String, String>> getRecommendProductForApp() {
		String sql = " SELECT "
				+ " v.product_id AS id, "
				+ " v.prouduct_type_id AS phiProductType, "
				+ " v.third_id AS thirdId, "
				+ " v.coupons_way_id AS couponsWayId, "
				+ " v.product_name AS productName, "
				+ " v.product_code AS productCode, "
				+ " v.product_class AS productClass, "
				+ " v.product_status AS productStatus, "
				+ " v.valid_time AS validTime, "
				+ " v.product_stock AS productStock, "
				+ " v.product_image_head AS productImageHead, "
				+ " v.product_image_pc AS productImagePc, "
				+ " v.product_image_app AS productImageApp, "
				+ " v.up_time AS upTime, "
				+ " v.down_time AS downTime, "
				+ " v.remark AS remark, "
				+ " v.create_time AS createTime, "
				+ " v.creator_id AS creatorId, "
				+ " v.quantity AS quantity, "
				+ " v.score AS score, "
				+ " v.market_price AS marketPrice, "
				+ " v.money AS money, "
				+ " v.logistics_cost AS logisticsCost, "
				+ " v.exchang_super_limit AS exchangSuperLimit, "
				+ " v.isShop AS isShop, "
				+ " v.need_grade AS needGrade, "
				+ " v.w_order AS wOrder, "
				+ " v.isRecommend AS isRecommend, "
				+ " v.discount AS discount, "
				+ " v.use_times AS useTimes, "
				+ " v.winner_time AS winnerTime, "
				+ " v.winner_status AS winnerStatus, "
				+ " v.winner_counts AS winnerCounts, "
				+ " v.Manual_counts AS ManualCounts, "
				+ " v.Random_counts AS RandomCounts, "
				+ " v.fake_counts AS fakeCounts "
				+ " FROM "
				+ " ( "
				+ " SELECT "
				+ " count(1) AS orderCount, "
				+ " t.* "
				+ " FROM "
				+ " phi_product t "
				+ " LEFT JOIN phi_order o ON o.product_id = t.product_id "
				+ " WHERE t.product_status = '1' and t.isShop = '1' and t.isRecommend = '0'"
				+ " GROUP BY "
				+ " t.product_id "
				+ " ) v "
				+ " ORDER BY "
				+ " v.orderCount DESC ";

		Query query = super.createSQLQuery(sql).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, String>> phiProductsMap = query.list();
		return phiProductsMap;
	}

	@Override
	public List<PhiProduct> getAllRecommedProductForApp() {
		String hql = "from PhiProduct t where t.isRecommend = '1' and t.productStatus = '1' and t.isShop = '1'";
		Query query = super.createQuery(hql);
		List<PhiProduct> phiProducts = query.list();
		return phiProducts;
	}

	@Override
	public List<PhiProduct> getProductListByTypeId(Long id) {
	    String sql = "from PhiProduct t where t.phiProductType =:phiProductTypeId";
        Query query = super.createQuery(sql);
        query.setParameter("phiProductTypeId", id);
        List<PhiProduct> phiProducts = query.list();
		return phiProducts;
	}

	@Override
	public PhiProduct findPhiProductByIdLock(Long id) {
		StringBuilder sbSql = new StringBuilder();
		List<PhiProduct> phiProductList = null;
		if(id != null){
			sbSql.append("select * from phi_product t where t.product_id=").append(id).append(" for update");
			List<Map<String, Object>> queryResult = this.queryMapListBySql(sbSql.toString(), null, null);
			phiProductList = BeanCopy.getInstance().convertList(queryResult, PhiProduct.class);
		}
		return (phiProductList != null && phiProductList.size() > 0) ? phiProductList.get(0):null;
//		return super.lockData(id);
	}

}
