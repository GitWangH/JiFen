package com.huatek.busi.dao.impl.phicom.score;
   
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.phicom.score.PhiScoreFlowDao;
import com.huatek.busi.model.phicom.member.PhiMember;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.model.phicom.score.PhiYearLedger;
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

@Repository("PhiScoreFlowDao")
public class  PhiScoreFlowDaoImpl extends AbstractDao<Long,  PhiScoreFlow> implements  PhiScoreFlowDao {

    private Logger logger = LoggerFactory.getLogger(PhiScoreFlowDaoImpl.class);

    @Override
    public PhiScoreFlow findPhiScoreFlowById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public void saveOrUpdatePhiScoreFlow( PhiScoreFlow entity) {
        super.saveOrUpdate(entity);
    }

    @Override
    public void persistentPhiScoreFlow(PhiScoreFlow entity) {
    	int year=Calendar.getInstance().get(Calendar.YEAR);
    	if(entity.getScoreType().equals("gain")){
			String hsql=" from PhiYearLedger where memberId= ? and year= ?";
			PhiYearLedger phiYearLedger=(PhiYearLedger)this.createQuery(hsql).setLong(0, entity.getMemberId()).setInteger(1, year).uniqueResult();
			if(phiYearLedger==null){
				phiYearLedger=new PhiYearLedger();
				phiYearLedger.setMemberId(entity.getMemberId());
				phiYearLedger.setTotalScore(0);
				phiYearLedger.setConsumeScoure(0);
				phiYearLedger.setRemainScore(0);
				phiYearLedger.setYear(year);
			}
			phiYearLedger.setTotalScore(phiYearLedger.getTotalScore().intValue()+entity.getScore().intValue());
			phiYearLedger.setRemainScore(phiYearLedger.getRemainScore().intValue()+entity.getScore().intValue());
			this.getSession().saveOrUpdate(phiYearLedger);
		}else{
			String hsql=" from PhiYearLedger where memberId= ? and remainScore>0 order by year asc";
			List<PhiYearLedger> ledgers=this.createQuery(hsql).setLong(0, entity.getMemberId()).list();
			int score=entity.getScore().intValue();
			for(PhiYearLedger ledger:ledgers){
				if(ledger.getRemainScore()>score){
					ledger.setConsumeScoure(ledger.getConsumeScoure().intValue()+score);
					ledger.setRemainScore(ledger.getRemainScore().intValue()-score);
					this.getSession().persist(ledger);
					break;
				}else{
					ledger.setConsumeScoure(ledger.getConsumeScoure().intValue()+ledger.getRemainScore());
					score=score-ledger.getRemainScore();
					ledger.setRemainScore(0);
					this.getSession().persist(ledger);
				}
			}
		}
        super.persistent(entity);
    }


    @Override
    public void deletePhiScoreFlow(PhiScoreFlow entity) {
        super.delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PhiScoreFlow> findAllPhiScoreFlow() {
        return createEntityCriteria().list();
    }

    @Override
    public PhiScoreFlow findPhiScoreFlowByCondition(String condition) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        //criteria.add(Restrictions.eq("name", condition));
        return (PhiScoreFlow) criteria.uniqueResult();
    }

    @Override
    public PhiScoreFlow findPhiScoreFlowByCondition(Long memberId,String orderCode) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("memberId", memberId));
        criteria.add(Restrictions.eq("orderCode", orderCode));
        criteria.add(Restrictions.eq("scoreType", "gain"));
        List<PhiScoreFlow> list = criteria.list();
        return (null != list && list.size() >0)?list.get(0):null;
    }
    @SuppressWarnings("unchecked")
    public List<PhiScoreFlow> findPhiScoreFlowByCondition(Long memberId) {
        Criteria criteria = createEntityCriteria();
        //TODO 查询条件
        criteria.add(Restrictions.eq("memberId", memberId));
        criteria.add(Restrictions.eq("isEnable", 0));
        criteria.add(Restrictions.eq("scoreType", "gain"));
        criteria.add(Restrictions.eq("isRefund", 0));
        criteria.add(Restrictions.le("enableTime",new Date()));
		List<PhiScoreFlow> list = criteria.list();
		return list;
    }
    
    @Override
    public DataPage<PhiScoreFlow> getAllPhiScoreFlow(QueryPage queryPage) {
        return super.queryPageData(queryPage);
    }

	@Override
	public void expireLastYearScore() {
		Calendar c=Calendar.getInstance();
		this.createQuery("update PhiYearLedger set remainScore=0 where year< ?").setInteger(0, c.get(Calendar.YEAR)-1).executeUpdate();
		
	}

	@Override
	public int getSoonFallDueScore(Long memberId) {
		// TODO Auto-generated method stub
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH);
		if(month<11){
			return 0;
		}
		getRemainScore(memberId);
		Object temp=this.createQuery(" select sum(remainScore) from PhiYearLedger where memberId=? and year<"+year+" ").setLong(0, memberId).uniqueResult();
		if(temp!=null){
			return Integer.valueOf(temp.toString());
		}else{
			return 0;
		}
	}

	
	@Override
	public Object getPhiScoreFlowAllScore(Long memberId) {
		StringBuffer sbBuffer = new StringBuffer();
		Date curreDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");  
		String currentDate = sdf.format(curreDate);
		Calendar c = Calendar.getInstance();
		c.setTime(curreDate);
		c.add(Calendar.DATE, -365);
		String beforeDate = sdf.format(c.getTime());
		sbBuffer.append(" SELECT SUM(score) AS scoreTotal FROM phi_score_flow t where t.score_type ='gain'and t.member_id = ");
		sbBuffer.append(memberId);
		sbBuffer.append(" and  t.create_time > '").append(beforeDate).append(" 00:00:00'");
		sbBuffer.append(" and t.create_time <= ' ").append(currentDate).append(" 23:59:59 '");
		Query query = this.createSQLQuery(sbBuffer.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Object list = query.uniqueResult();
	
		return list;
	}
	
	
	@Override
	public int getRemainScore(Long memberId) {
		StringBuffer sb = new StringBuffer();
		BigDecimal remainScore = null;
		BigDecimal scoreGain = null;
		StringBuffer sbBuffer = new StringBuffer();
		int nowyear=Calendar.getInstance().get(Calendar.YEAR);
		int year = nowyear -1;
		Date curreDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String currentDate = sdf.format(curreDate);
		Calendar c = Calendar.getInstance();
		c.setTime(curreDate);
		c.add(Calendar.YEAR, -1);
		String beforeDate = sdf.format(c.getTime());
		sb.append("SELECT SUM(score) AS scoreGain FROM phi_score_flow t where t.score_type ='gain'and t.member_id = ") ;
		sb.append(memberId);
		sb.append(" and  t.create_time >='").append(beforeDate).append("'");
		sb.append(" and t.create_time < '").append(currentDate).append("'");
		Query query1 = this.createSQLQuery(sb.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Object list1 = query1.uniqueResult();
		if(null == list1 ){
			scoreGain = new BigDecimal(0);
		}else {
			Map<String, BigDecimal> map = (Map<String, BigDecimal>) list1;
			 scoreGain = map.get("scoreGain");
			if(scoreGain == null){
				scoreGain = new BigDecimal(0);
			}
		}
		sbBuffer.append("SELECT SUM(score) AS scoreConsum FROM phi_score_flow t where t.score_type ='consume'and t.member_id = ") ;
		sbBuffer.append(memberId);
		sbBuffer.append(" and  t.create_time >='").append(beforeDate).append("'");
		sbBuffer.append(" and  t.create_time < '").append(currentDate).append("'");
		Query query2 = this.createSQLQuery(sbBuffer.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Object list2 = query2.uniqueResult();
		
		Map<String, BigDecimal> map1 = (Map<String, BigDecimal>) list2;
		BigDecimal scoreConsum = map1.get("scoreConsum");
		if(scoreConsum == null){
			scoreConsum = new BigDecimal(0);
		}
		remainScore = scoreGain.subtract(scoreConsum);
		
		String hsql=" from PhiYearLedger where memberId= ? and year= ?";
		PhiYearLedger phiYearLedger=(PhiYearLedger)this.createQuery(hsql).setLong(0, memberId).setInteger(1, year).uniqueResult();
		if(phiYearLedger==null){
			phiYearLedger=new PhiYearLedger();
			phiYearLedger.setMemberId(memberId);
			phiYearLedger.setTotalScore(scoreGain.intValue());
			phiYearLedger.setConsumeScoure(scoreConsum.intValue());
			phiYearLedger.setRemainScore(remainScore.intValue());
			phiYearLedger.setYear(year);
		}
		phiYearLedger.setTotalScore(scoreGain.intValue());
		phiYearLedger.setConsumeScoure(scoreConsum.intValue());
		phiYearLedger.setRemainScore(remainScore.intValue());
		this.getSession().saveOrUpdate(phiYearLedger);
		
		return remainScore.intValue();
	}

	@Override
	public PhiScoreFlow findPhiScoreFlowByRCondition(long memberId,	String refundCode) {
		Criteria criteria = createEntityCriteria();
		//TODO 查询条件
        criteria.add(Restrictions.eq("memberId", memberId));
        criteria.add(Restrictions.eq("refundCode", refundCode));
        criteria.add(Restrictions.eq("scoreType", "consume"));
        List<PhiScoreFlow> list = criteria.list();
        return (null != list && list.size() >0)?list.get(0):null;
	}
	
	

}
