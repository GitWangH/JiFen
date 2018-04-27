package com.huatek.busi.dao.impl.phicom.scoreData;
   
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.scoreData.PhiScoreDataDao;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;


 /**
  * @ClassName: PhiScoreFlowDaoImpl
  * @Description: 
  * @author: Ken Bai
  * @Email : Ken_Bai@huatek.com
  * @date: 2018-01-08 13:35:12
  * @version: 1.0
  */

@Repository("PhiScoreDataDao")
public class  PhiScoreDataDaoImpl extends AbstractDao<Long,  Object> implements  PhiScoreDataDao {

    private Logger logger = LoggerFactory.getLogger(PhiScoreDataDaoImpl.class);


	@Override
	public List<Object> getPhiScoreFlowTotalByScoreType(String type) {
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("SELECT SUM(score) AS scoreTotal,score_type as scoreType FROM phi_score_flow ");
		if("0".equals(type)){//上一年
			sbBuffer.append(" where year(create_time)=year(date_sub(now(),interval 1 year)) ");
		}else if("1".equals(type)){//今年
			sbBuffer.append("	 where YEAR(create_time)=YEAR(NOW()) ");
		}
		sbBuffer.append(" GROUP BY score_type ");
		Query query = this.createSQLQuery(sbBuffer.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Object>  list = query.list();
		return list;
	}

	@Override
	public List<Object> getPhiScoreFlowTotalByScoreTypeAndTaskId(String type) {
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(" SELECT SUM(score) AS scoreTotal,score_type as scoreType,task_name as taskName FROM phi_score_flow ");
		if("0".equals(type)){//上一年
			sbBuffer.append(" where year(create_time)=year(date_sub(now(),interval 1 year)) ");
		}else if("1".equals(type)){//今年
			sbBuffer.append(" where YEAR(create_time)=YEAR(NOW()) ");
		}
		sbBuffer.append(" GROUP BY score_type,task_id ");
		Query query = this.createSQLQuery(sbBuffer.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Object>  list = query.list();
		return list;
	}

	@Override
	public DataPage<Object> getAllPhiScoreCoups(QueryPage queryPage) {
		StringBuffer sbBuffer = new StringBuffer();
		StringBuffer countSb = new StringBuffer();
		sbBuffer.append(" select * from ");
	    sbBuffer.append("  (SELECT typeCode,cpnsId,cpnsName,cnpsType,coupMemCount,totalCount,coupCount,FORMAT((coupCount/totalCount)*100,2) AS coupThan,");
	    sbBuffer.append("  useCount ,FORMAT((useCount/totalCount)*100,2) AS useThan ");
	    sbBuffer.append("  FROM (");
		sbBuffer.append("    SELECT '-1' AS typeCode,b.`cpns_way_id` AS cpnsId,b.`cpns_name` AS cpnsName,IF(b.`cpns_type`=0,'暗号类','红包类') AS cnpsType,");
		sbBuffer.append("    (SELECT COUNT(member_id) FROM phi_coupons_detail  a1 WHERE a1.coup_way_id= a.`coup_way_id` AND a1.`exchange_status`='1' ) AS coupMemCount,");
		sbBuffer.append("    COUNT(1) AS totalCount,");
		sbBuffer.append("    (SELECT COUNT(1) FROM phi_coupons_detail  a1 WHERE a1.coup_way_id= a.`coup_way_id` AND a1.`coup_status`='1') AS useCount,");
		sbBuffer.append("    (SELECT COUNT(1) FROM phi_coupons_detail  a1 WHERE a1.coup_way_id= a.`coup_way_id` AND a1.`exchange_status`='1') AS coupCount");
		sbBuffer.append("    FROM `phi_coupons_detail` a ");
		sbBuffer.append("    LEFT JOIN `phi_coupons` b ON a.`coup_way_id`=b.`cpns_way_id`");
		sbBuffer.append("    GROUP BY a.`coup_way_id`");
		sbBuffer.append("  ) AS c ");
		sbBuffer.append("  UNION ALL");
		sbBuffer.append("  SELECT typeCode,cpnsId,cpnsName,cnpsType,coupMemCount,totalCount,coupCount,FORMAT((coupCount/totalCount)*100,2) AS coupThan, ");
		sbBuffer.append("  useCount ,FORMAT((useCount/totalCount)*100,2) AS useThan   ");
		sbBuffer.append("  FROM ( ");
		sbBuffer.append("  	 SELECT b.`cpns_type` AS typeCode,a.`coup_id` AS cpnsId,b.`cpns_name` AS cpnsName,");
		sbBuffer.append("  	 IF(CHAR_LENGTH(b.cpns_valid)=19,'绝对有效期','相对有效期') AS cnpsType,");
		sbBuffer.append("  	 (SELECT COUNT(member_id) FROM phi_third_party_coupons_detail  a1 WHERE a1.coup_id= a.`coup_id` AND a1.`exchange_status`='1' ) AS coupMemCount,");
		sbBuffer.append("  	 COUNT(1) totalCount,");
		sbBuffer.append("  	 (SELECT COUNT(1) FROM phi_third_party_coupons_detail  a1 WHERE a1.coup_id= a.`coup_id` AND a1.`coup_status`='1') AS useCount,");
	    sbBuffer.append("  	 (SELECT COUNT(1) FROM phi_third_party_coupons_detail  a1 WHERE a1.coup_id= a.`coup_id` AND a1.`exchange_status`='1') AS coupCount");
	    sbBuffer.append("  	 FROM `phi_third_party_coupons_detail` a ");
	    sbBuffer.append("  	 LEFT JOIN phi_third_party_coupons b ON a.`coup_id`=b.`cpns_id`");
	    sbBuffer.append("  	 GROUP BY  a.`coup_id`");
	    sbBuffer.append("  ) AS c");
	    sbBuffer.append(" ) as sc where 1=1 ");
	    
	    countSb.append(" select count(1) from ");
	    countSb.append("  (SELECT typeCode,cpnsId,cpnsName,cnpsType,coupMemCount,totalCount,coupCount,FORMAT((coupCount/totalCount)*100,2) AS coupThan,");
	    countSb.append("  useCount ,'0' AS useThan ");
	    countSb.append("  FROM (");
		countSb.append("    SELECT '-1' AS typeCode,b.`cpns_way_id` AS cpnsId,b.`cpns_name` AS cpnsName,IF(b.`cpns_type`=0,'暗号类','红包类') AS cnpsType,");
		countSb.append("    (SELECT COUNT(member_id) FROM phi_coupons_detail  a1 WHERE a1.coup_way_id= a.`coup_way_id` AND a1.`exchange_status`='1' ) AS coupMemCount,");
		countSb.append("    COUNT(1) AS totalCount,");
		countSb.append("    '0' AS useCount,");
		countSb.append("    (SELECT COUNT(1) FROM phi_coupons_detail  a1 WHERE a1.coup_way_id= a.`coup_way_id` AND a1.`exchange_status`='1') AS coupCount");
		countSb.append("    FROM `phi_coupons_detail` a ");
		countSb.append("    LEFT JOIN `phi_coupons` b ON a.`coup_way_id`=b.`cpns_way_id`");
		countSb.append("    GROUP BY a.`coup_way_id`");
		countSb.append("  ) AS c ");
		countSb.append("  UNION ALL");
		countSb.append("  SELECT typeCode,cpnsId,cpnsName,cnpsType,coupMemCount,totalCount,coupCount,FORMAT((coupCount/totalCount)*100,2) AS coupThan, ");
		countSb.append("  useCount ,FORMAT((useCount/totalCount)*100,2) AS useThan   ");
		countSb.append("  FROM ( ");
		countSb.append("  	 SELECT b.`cpns_type` AS typeCode,a.`coup_id` AS cpnsId,b.`cpns_name` AS cpnsName,");
		countSb.append("  	 IF(CHAR_LENGTH(b.cpns_valid)=19,'绝对有效期','相对有效期') AS cnpsType,");
		countSb.append("  	 (SELECT COUNT(member_id) FROM phi_third_party_coupons_detail  a1 WHERE a1.coup_id= a.`coup_id` AND a1.`exchange_status`='1' ) AS coupMemCount,");
		countSb.append("  	 COUNT(1) totalCount,");
		countSb.append("  	 (SELECT COUNT(1) FROM phi_third_party_coupons_detail  a1 WHERE a1.coup_id= a.`coup_id` AND a1.`coup_status`='1') AS useCount,");
	    countSb.append("  	 (SELECT COUNT(1) FROM phi_third_party_coupons_detail  a1 WHERE a1.coup_id= a.`coup_id` AND a1.`exchange_status`='1') AS coupCount");
	    countSb.append("  	 FROM `phi_third_party_coupons_detail` a ");
	    countSb.append("  	 LEFT JOIN phi_third_party_coupons b ON a.`coup_id`=b.`cpns_id`");
	    countSb.append("  	 GROUP BY  a.`coup_id`");
	    countSb.append("  ) AS c");
	    countSb.append(" ) as sc where 1=1 ");
		return super.querySQLPageData(sbBuffer.toString(),countSb.toString(),queryPage);
	}

}
