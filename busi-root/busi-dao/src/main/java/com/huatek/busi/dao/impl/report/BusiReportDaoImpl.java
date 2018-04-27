package com.huatek.busi.dao.impl.report;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.report.BusiReportDao;
import com.huatek.busi.model.report.QualityDistributionChart;
import com.huatek.busi.util.Utils;
import com.huatek.frame.core.dao.AbstractDao;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;

/**
 * @ClassName: BusiReportDao
 * @Description: 报表 DAO接口实现类
 * @author: mickey_meng
 * @Email : mickey_meng@huatek.com
 * @date: 2017-11-15 13:55:35
 * @version: 1.0
 */
@Repository("busiReportDao")
public class BusiReportDaoImpl extends AbstractDao<Long,  QualityDistributionChart> implements BusiReportDao {

	/**
	 * 不合格数据统计
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<Map<String, Object>> findUnqualifiedStatisticsData(QueryPage queryPage) {
		List<QueryParam> queryParamList = null!=queryPage? queryPage.getQueryParamList():null;
		String orgId = "";//机构
		String startDate = "";
//		String endDate = dft.format(new Date()) + " 23:59:59" ;
		if(null != queryParamList && queryParamList.size() > 0){
			for(QueryParam queryParam:queryParamList){
				if("orgId".equals(queryParam.getField())){
					orgId = queryParam.getValue();
				}
				if("timeRanges".equals(queryParam.getField())){
					//时间范围(nearly_one_month、nearly_three_months、nearly_one_year、all_date)
					String timeRanges = queryParam.getValue();
					startDate = this.getStartDateByTimeRanges(timeRanges);
				}
			}
		}
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT  ")
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_water_stable_mixing_station_inspection wse WHERE wse.IS_EXCESSIVE = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND wse.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND wse.SAVE_DATE >= '"+startDate+"'":"")
	 	   .append(") AS WATER_STATION_UNQUALIFIED_COUNT,")/*水稳拌合站不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_cement_mixing_station_inspection cse WHERE cse.IS_EXCESSIVE = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND cse.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND cse.SAVE_DATETIME >= '"+startDate+"'":"")
	 	   .append(") AS CEMENT_STATION_UNQUALIFIED_COUNT,")/*水泥拌合站不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_asphalt_mixing_plant_inspection ape WHERE ape.IS_EXCESSIVE = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND ape.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND ape.SAVE_DATE >= '"+startDate+"'":"")
	 	   .append(") AS ASPHALT_STATION_UNQUALIFIED_COUNT,")/*沥青拌合站不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_raw_material_inspection rmi WHERE rmi.REPORT_CONCLUSION = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND rmi.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND rmi.CHECK_DATE >= '"+startDate+"'":"")
	 	   .append(") AS RAW_MATERIAL_UNQUALIFIED_COUNT,")/*原材料不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_universal_press_machine_parent upm  WHERE  upm.STATUS = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND upm.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND upm.TEST_TIME >= '"+startDate+"'":"")
	 	   .append(") AS PRESS_MACHINE_UNQUALIFIED_COUNT,")/*实验室(压力机、万能机)不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_spreader_roller_exceed sre")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND sre.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND sre.GATHER_DATE >= '"+startDate+"'":"")
	 	   .append(") AS SPREADER_ROLLER_UNQUALIFIED_COUNT,") /*路面摊压不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_prestressed_tension_main ptm  WHERE  ptm.IS_QUALIFIED = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND ptm.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND ptm.GATHER_TIME >= '"+startDate+"'":"")
	 	   .append(") AS PRESTRESSED_TENSION_UNQUALIFIED_COUNT,")/*张拉压浆不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_routing_inspection ri  WHERE ri.CHECK_RESULTS = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND ri.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND ri.CHECK_TIME >= '"+startDate+"'":"")
	 	   .append(") AS ROUTING_INSPECTION_UNQUALIFIED_COUNT,")/*质量巡检不合格数*/
	 	   .append("       (SELECT COUNT(1) FROM busi_quality_construction_inspection ci WHERE ci.QUALITY_STATUS = 0")
	 	   .append(StringUtils.isNotEmpty(orgId)?"     AND ci.ORG_ID = "+orgId:"")
	 	   .append(StringUtils.isNotEmpty(startDate)?" AND ci.INSPECTION_DATE >= '"+startDate+"'":"")
	 	   .append(") AS CONSTRUCTION_INSPECTION_UNQUALIFIED_COUNT")/*施工报检不合格数*/
	 	   .append(" FROM DUAL");
		List<Map<String, Object>> queryResult = this.queryMapListBySql(sql.toString(), null, null);
    	return queryResult;
	}
	
	@Override
	public List<Map<String, Object>> findOverStandardChartData(QueryPage queryPage, String condition, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append("	sum(if(a.isTimeOut = 0 and a.FLOW_RESULT = 5, 1, 0 ))  as RESOLVED_ON_TIME,     ");//-- 按时已解决
		sql.append("	sum(if(a.isTimeOut = 0 and a.FLOW_RESULT <> 5, 1 , 0)) as NOT_RESOLVED_ON_TIME,     ");//-- 按时未解决
		sql.append("	sum(if(a.isTimeOut = 1 and a.FLOW_RESULT = 5, 1 , 0)) as TIMEOUT_RESOLVED,     ");//-- 超时已解决
		sql.append("	sum(if(a.isTimeOut = 1 and a.FLOW_RESULT <> 5, 1 , 0)) as TIMEOUT_NOT_RESOLVED     ");//-- 超时未解决
		sql.append(" from    ");
		sql.append(" (select   ");
		sql.append("  RECTIFICATION_PERIOD, ");
		sql.append("  FLOW_RESULT, "); //-- 审批结果 5 已整改
		sql.append("  if(datediff(IFNULL(a.APPROVAL_TIME, NOW()), a.CREATE_TIME) < a.RECTIFICATION_PERIOD, 0, 1) as isTimeOut   ");//-- 是否超时 0 未超时 1 已超时
		sql.append("  from busi_quality_rectification a  ");
		sql.append("  where DATA_SOURCE in (  ");
		sql.append(condition);
		sql.append("  ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" and ORG_ID in ( ");
		sql.append(getOrgIds(orgId));
		sql.append(" )) a ");
		List<Map<String, Object>> queryResult = this.queryMapListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<Map<String, Object>> findUnitEngineeringInspectionUnqualified(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" 	( ");
		sql.append(" 		SELECT ");
		sql.append(" 			TENDERS_BRANCH_NAME ");
		sql.append(" 		FROM ");
		sql.append(" 			busi_contract_tenders_branch ");//-- 标段分部分项
		sql.append(" 		WHERE ");
		sql.append(" 			UUID = ( ");
		sql.append(" 				SELECT ");
		sql.append(" 					cc.PARENT_ID ");
		sql.append(" 				FROM ");
		sql.append(" 					busi_contract_tenders_branch cc ");
		sql.append(" 				WHERE ");
		sql.append(" 					cc.TENDERS_BRANCH_ID = b.TENDERS_BRANCH_ID ");
		sql.append(" 			) ");
		sql.append(" 	) as NAME, ");
		sql.append(" 	count(1) AS COUNT ");
		sql.append(" FROM ");
		sql.append(" 	busi_quality_rectification a, ");//-- 整改单
		sql.append(" 	busi_quality_construction_inspection b "); // -- 施工报检
		sql.append(" WHERE ");
		sql.append(" 	a.RECTIFICATION_CODE = b.INSPECTION_CODE ");
		sql.append(" AND a.DATA_SOURCE = 'construction_inspection' ");
		sql.append(" AND a.FLOW_RESULT <> 5  ");
		sql.append(" and a.ORG_ID in ( ");
		sql.append(getOrgIds(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY ");
		sql.append(" b.TENDERS_BRANCH_ID ");
		List<Map<String, Object>> queryResult = this.queryMapListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<Map<String, Object>> findQualitySeverityDataData(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select ");
		sql.append(" sum(if(a.DATA_SOURCE in ('water_stable_mixing_station','cement_mixing_station_inspection','asphalt_mixing_station') and a.RECTIFICATION_URGENCY = 0, 1 ,0)) as B0,  "); //-- 拌合站一般
		sql.append(" sum(if(a.DATA_SOURCE in ('water_stable_mixing_station','cement_mixing_station_inspection','asphalt_mixing_station') and a.RECTIFICATION_URGENCY = 1, 1 ,0)) as B1,	 "); // -- 拌合站普通
		sql.append(" sum(if(a.DATA_SOURCE in ('water_stable_mixing_station','cement_mixing_station_inspection','asphalt_mixing_station') and a.RECTIFICATION_URGENCY = 2, 1 ,0)) as B2,  "); //-- 拌合站紧急
		sql.append(" sum(if(a.DATA_SOURCE in ('routing_inspection') and a.RECTIFICATION_URGENCY = 0, 1 ,0)) as Z0,  "); // -- 质量巡检一般
		sql.append(" sum(if(a.DATA_SOURCE in ('routing_inspection') and a.RECTIFICATION_URGENCY = 1, 1 ,0)) as Z1,	"); // -- 质量巡检普通 
		sql.append(" sum(if(a.DATA_SOURCE in ('routing_inspection') and a.RECTIFICATION_URGENCY = 2, 1 ,0)) as Z2,  "); // -- 质量巡检紧急
		sql.append(" sum(if(a.DATA_SOURCE in ('construction_inspection') and a.RECTIFICATION_URGENCY = 0, 1 ,0)) as S0,  "); // -- 施工报检一般
		sql.append(" sum(if(a.DATA_SOURCE in ('construction_inspection') and a.RECTIFICATION_URGENCY = 1, 1 ,0)) as S1,	 "); // -- 施工报检普通
		sql.append(" sum(if(a.DATA_SOURCE in ('construction_inspection') and a.RECTIFICATION_URGENCY = 2, 1 ,0)) as S2  ");//-- 施工报检紧急 
		sql.append(" from busi_quality_rectification a ");
		sql.append(" where a.DATA_SOURCE in ( ");
		sql.append(" 'water_stable_mixing_station','cement_mixing_station_inspection','asphalt_mixing_station', "); // -- //拌合站
		sql.append(" 'routing_inspection', ");// -- 质量巡检
		sql.append(" 'construction_inspection' ");// -- 施工报检
		sql.append(" ) ");
		sql.append(" and a.ORG_ID in (");
		sql.append(getOrgIds(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		List<Map<String, Object>> queryResult = this.queryMapListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	
	
	@Override
	public List<QualityDistributionChart> findRawMaterial(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select (select org_name from fw_org where org_id = raw.orgId) as orgName, sum(sum) as sum from  ");
		sql.append(" (select count(1) as sum, a.ORG_ID as orgId  from busi_quality_universal_press_machine_parent a  ");
		sql.append(" where a.STATUS = 1 AND a.DISPOSE_STATE <> 5 AND  ");
		sql.append(" ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY a.ORG_ID   ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, b.ORG_ID as orgId from busi_quality_raw_material_inspection b  ");
		sql.append(" where  b.report_Conclusion = 0 AND b.DISPOSE_STATE <> 5 AND ");
		sql.append(" ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY b.ORG_ID) raw  ");
		sql.append(" GROUP BY orgId  ");
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<QualityDistributionChart> findConstructionProcess(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(sum) as sum, orgName from  ");
		sql.append(" (select (select org_name from fw_org  where org_id = a.ORG_ID)  as orgName, sum(1) as sum from busi_quality_construction_inspection a where  ");
		sql.append(" QUALITY_STATUS = 0 and  APPROVAL_STATUS <> 5 AND  ");
		sql.append(" ORG_ID in (   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		sql.append(" GROUP BY ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select (select org_name from fw_org  where org_id = b.ORG_ID)  as orgName, sum(1) as sum from busi_quality_routing_inspection  b where  ");
		sql.append(" CHECK_RESULTS = 0 and  DISPOSE_STATE <> 5 AND  ");
		sql.append(" ORG_ID in (   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		sql.append(" GROUP BY ORG_ID ) sg  ");
		sql.append(" GROUP BY orgName  ");
		 
		
		
		
		
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<QualityDistributionChart> findPartiallyPreparedProducts(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select (select org_name from fw_org where org_id = a.org_id) as orgName , sum(sum) as sum   from ( ");
		sql.append(" select count(1) as sum, org_id from busi_quality_water_stable_mixing_station_exceed a  ");
		sql.append(" where a.DISPOSE_STATE <> 5 and ");
		sql.append(" a.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY a.ORG_ID ");
		sql.append(" UNION ");
		sql.append(" select count(1) as sum, org_id from busi_quality_cement_mixing_station_exceed b where b.DISPOSE_STATE <> 5 AND ");
		sql.append(" b.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY b.ORG_ID ");
		sql.append(" UNION ");
		sql.append(" select count(1) as sum, org_id from busi_quality_prestressed_tension_main c where c.IS_QUALIFIED = 1 and c.DISPOSE_STATUS <> 5 and ");
		sql.append(" c.ORG_ID in( ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY c.ORG_ID ");
		sql.append(" UNION ");
		sql.append(" select count(1) as sum, org_id from busi_quality_asphalt_mixing_plant_exceed d where d.DISPOSE_STATE <> 5 and ");
		sql.append(" d.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY d.ORG_ID ");
		sql.append(" UNION ");
		sql.append(" select count(1) as sum, org_id from busi_quality_spreader_roller_spreader_parent e where e.DATA_TYPE = 0 AND 	e.DISPOSE_STATE <> 5 AND ");
		sql.append(" e.ORG_ID in( ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY e.ORG_ID  ");
		sql.append(" UNION ");
		sql.append(" select count(1) as sum, org_id from busi_quality_mortar f where  f.IS_QUALIFIED = 1 AND f.DISPOSE_STATE <> 5 AND ");
		sql.append(" f.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY f.ORG_ID ) a ");
		sql.append(" group by a.org_id ");
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<QualityDistributionChart> findThirdParty(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select  (select org_name from fw_org where ORG_ID = a.org_id) as orgName , sum(a.sum) as sum from (  ");
		sql.append(" select count(1) as sum, org_id from busi_quality_primary_support_thickness_check a where a.qualified_Status = 0 AND  ");
		sql.append(" a.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY a.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, org_id from busi_quality_second_lining_thickness_check b where  b.qualified_Status = 0 AND ");
		sql.append(" b.ORG_ID in(     ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY b.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, org_id from busi_quality_inverted_arch_thickness_check c where c.qualified_Status = 0 AND  ");
		sql.append(" c.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY c.ORG_ID  ");
		sql.append(" UNION   ");
		sql.append(" select count(1) as sum, org_id from busi_quality_primary_support_concrete_thickness_check d where d.qualified_Status = 0 AND  ");
		sql.append(" d.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY d.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, org_id from busi_quality_second_lining_clearance_section_size_check e where e.qualified_Status = 0 AND ");
		sql.append(" e.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY e.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, org_id from busi_quality_primary_support_arch_spacing_check f where f.qualified_Status = 0 AND  ");
		sql.append(" f.ORG_ID in(     ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY f.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, org_id from busi_quality_primary_support_clearance_section_check g where g.qualified_Status = 0 AND   ");
		sql.append(" g.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY g.ORG_ID) a  ");
		sql.append(" GROUP BY a.org_id  ");
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	
	@Override
	public List<QualityDistributionChart> findConstructionProcessQualityProblemChart(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(sg.yijiejue) as yijiejue, sum(sg.weijiejue) as weijiejue , (select org_name from fw_org where ORG_ID = sg.orgId ) as orgName from  ");
		sql.append(" (select sum(if(APPROVAL_STATUS = 5, 1, 0)) as yijiejue, sum(if(APPROVAL_STATUS <> 5 , 1, 0)) as weijiejue, a.ORG_ID as orgId from busi_quality_construction_inspection a where  ");
		sql.append(" QUALITY_STATUS = 0  AND  ");
		sql.append(" ORG_ID in (   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(DISPOSE_STATE <> 5, 1, 0)) as weijiejue,  b.ORG_ID  as orgId from busi_quality_routing_inspection b where  ");
		sql.append(" CHECK_RESULTS = 0  AND  ");
		sql.append(" ORG_ID in (   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY ORG_ID ) sg  ");
		sql.append(" GROUP BY sg.orgId  ");

		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<QualityDistributionChart> findPartiallyPreparedProductsQualityProblemChart(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(a.yijiejue) as yijiejue, sum(a.weijiejue) as weijiejue, (SELECT org_name from fw_org where ORG_ID = a.org_id) as orgName from (  ");
		sql.append(" select sum(if(a.DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(a.DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_water_stable_mixing_station_exceed a  ");
		sql.append(" where  ");
		sql.append(" a.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY a.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(b.DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(b.DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_cement_mixing_station_exceed b where  ");
		sql.append(" b.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY b.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(c.DISPOSE_STATUS = 5, 1, 0)) as yijiejue, sum(if(c.DISPOSE_STATUS <> 5, 1, 0)) as weijiejue, org_id from busi_quality_prestressed_tension_main c where  ");
		sql.append(" c.IS_QUALIFIED = 1 and  ");
		sql.append(" c.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY c.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(d.DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(d.DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id  from busi_quality_asphalt_mixing_plant_exceed d where  ");
		sql.append(" d.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY d.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(e.DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(e.DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id  from busi_quality_spreader_roller_spreader_parent e where  ");
		sql.append(" e.DATA_TYPE = 0 AND  ");
		sql.append(" e.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY e.ORG_ID   ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(f.DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(f.DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_mortar f where  ");
		sql.append(" f.IS_QUALIFIED = 1  AND  ");
		sql.append(" f.ORG_ID in(     ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY f.ORG_ID ) a  ");
		sql.append(" group by a.org_id  ");
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<QualityDistributionChart> findRawMaterialQualityProblemChart(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(raw.yijiejue) as yijiejue , sum(raw.weijiejue) as weijiejue, (select org_name from fw_org where ORG_ID = raw.orgId) as orgName from  ");
		sql.append(" ( select sum(if(a.DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(a.DISPOSE_STATE <> 5, 1, 0))as weijiejue, a.ORG_ID as orgId  from busi_quality_universal_press_machine_parent a  ");
		sql.append("  where a.STATUS = 1   AND    ");
		sql.append("  ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY a.ORG_ID   ");
		sql.append(" UNION    ");
		sql.append(" select sum(if(b.DISPOSE_STATE = 5,1,0)) as yijiejue , sum(if(b.DISPOSE_STATE <> 5 ,1, 0)) as weijiejue, b.ORG_ID as orgId from busi_quality_raw_material_inspection b  ");
		sql.append(" where  b.report_Conclusion = 0  AND  ");
		sql.append(" ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append("  GROUP BY ORG_ID ) raw   ");
		sql.append(" GROUP by raw.orgId  ");
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	@Override
	public List<QualityDistributionChart> findThirdPartyQualityProblemChart(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = Utils.getTime(getStartDateByTimeRanges(map.get("timeRangs")));
		StringBuilder sql = new StringBuilder();
		sql.append(" select sum(a.yijiejue) as yijiejue, sum(a.weijiejue) as weijiejue, (select org_name from fw_org where ORG_ID = a.org_id) as orgName from (  ");
		sql.append(" select sum(if(DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_primary_support_thickness_check a where  ");
		sql.append(" a.qualified_Status = 0 AND  ");
		sql.append(" a.ORG_ID in(     ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append("  GROUP BY a.ORG_ID ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_second_lining_thickness_check b where  ");
		sql.append(" b.qualified_Status = 0  AND  ");
		sql.append(" b.ORG_ID in(     ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY b.ORG_ID  ");
		sql.append(" UNION   ");
		sql.append(" select sum(if(DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_inverted_arch_thickness_check c where  ");
		sql.append(" c.qualified_Status = 0  AND  ");
		sql.append(" c.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY c.ORG_ID  ");
		sql.append(" UNION   ");
		sql.append(" select sum(if(DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(DISPOSE_STATE <> 5,1,0)) as weijiejue, org_id from busi_quality_primary_support_concrete_thickness_check d where  ");
		sql.append(" d.qualified_Status = 0  AND  ");
		sql.append(" d.ORG_ID in( ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY d.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_second_lining_clearance_section_size_check e where  ");
		sql.append(" e.qualified_Status = 0  AND  ");
		sql.append(" e.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY e.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(	DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(	DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_primary_support_arch_spacing_check f where  ");
		sql.append(" f.qualified_Status = 0  AND  ");
		sql.append("  f.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY f.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select sum(if(DISPOSE_STATE = 5, 1, 0)) as yijiejue, sum(if(DISPOSE_STATE <> 5, 1, 0)) as weijiejue, org_id from busi_quality_primary_support_clearance_section_check g where  ");
		sql.append(" g.qualified_Status = 0  AND  ");
		sql.append(" g.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY g.ORG_ID) a  ");
		sql.append(" GROUP BY a.org_id  ");
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	
	@Override
	public List<QualityDistributionChart> findQualityTrends(QueryPage queryPage, Long userOrgId) {
		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
		String time = getStartDateByTimeRanges(map.get("time"));
		StringBuilder sql = new StringBuilder();
		sql.append(" select   sum(sum) as sum, orgName, date  from (  ");
		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = orgId) as orgName ,  date  from  ");
		sql.append(" (select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_construction_inspection a where  ");
		sql.append(" QUALITY_STATUS = 0  AND  ");
		sql.append(" ORG_ID in (   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_routing_inspection b where  ");
		sql.append(" CHECK_RESULTS = 0  AND  ");
		sql.append(" ORG_ID in (   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,ORG_ID ) sg  ");
		sql.append(" GROUP BY date,sg.orgId  "); // 施工报检
		sql.append(" UNION ");
		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = orgId) as orgName ,  date from (  ");
		sql.append(" select count(1) as sum, a.ORG_ID as orgId ,DATE_FORMAT(a.CREATE_TIME, '%Y-%m') as date from busi_quality_water_stable_mixing_station_exceed a  ");
		sql.append(" where  ");
		sql.append(" a.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date, a.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_cement_mixing_station_exceed b where  ");
		sql.append(" b.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date, b.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_prestressed_tension_main c where  ");
		sql.append(" c.IS_QUALIFIED = 1 and  ");
		sql.append(" c.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,c.ORG_ID  ");
		sql.append(" UNION ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date  from busi_quality_asphalt_mixing_plant_exceed d where  ");
		sql.append(" d.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,d.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date  from busi_quality_spreader_roller_spreader_parent e where  ");
		sql.append(" e.DATA_TYPE = 0 AND  ");
		sql.append(" e.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,e.ORG_ID   ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_mortar f where  ");
		sql.append(" f.IS_QUALIFIED = 1  AND  ");
		sql.append(" f.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,f.ORG_ID ) a  ");
		sql.append(" group by date, a.orgId  "); // 半成品
		sql.append(" UNION ");
		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = raw.orgId) as orgName ,  raw.date from  ");
		sql.append("  ( select count(1) as sum, a.ORG_ID as orgId ,DATE_FORMAT(a.CREATE_TIME, '%Y-%m') as date from busi_quality_universal_press_machine_parent a ");
		sql.append("  where a.STATUS = 1   AND    ");
		sql.append(" ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date, a.ORG_ID     ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, b.ORG_ID as orgId  ,DATE_FORMAT(b.CREATE_TIME, '%Y-%m') as date from busi_quality_raw_material_inspection b  ");
		sql.append(" where  b.report_Conclusion = 0  AND   ");
		sql.append(" ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,ORG_ID   ");
		sql.append(" ) raw  ");
		sql.append(" GROUP by date, raw.orgId  "); // 原材料
		sql.append(" UNION ");
		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = orgId) as orgName ,  date  from (  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_thickness_check a where  ");
		sql.append(" a.qualified_Status = 0 AND ");
		sql.append(" a.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date, a.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_second_lining_thickness_check b where  ");
		sql.append(" b.qualified_Status = 0  AND  ");
		sql.append(" b.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date, b.ORG_ID  ");
		sql.append(" UNION   ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_inverted_arch_thickness_check c where  ");
		sql.append(" c.qualified_Status = 0  AND  ");
		sql.append(" c.ORG_ID in(   ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,c.ORG_ID  ");
		sql.append(" UNION   ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_concrete_thickness_check d where  ");
		sql.append(" d.qualified_Status = 0  AND ");
		sql.append(" d.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,d.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_second_lining_clearance_section_size_check e where  ");
		sql.append(" e.qualified_Status = 0  AND  ");
		sql.append(" e.ORG_ID in(  ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,e.ORG_ID  ");
		sql.append(" UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_arch_spacing_check f where  ");
		sql.append(" 	f.qualified_Status = 0  AND  ");
		sql.append(" f.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date,f.ORG_ID  ");
		sql.append(" 	UNION  ");
		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_clearance_section_check g where  ");
		sql.append(" g.qualified_Status = 0  AND  ");
		sql.append(" g.ORG_ID in(    ");
		sql.append(getOrgIdsTypeIn7(orgId));
		sql.append(" ) ");
		if(!time.equals("")){
			sql.append(" and CREATE_TIME >= ").append(time);
		}
		sql.append(" GROUP BY date , g.ORG_ID) a  ");
		sql.append(" GROUP BY date , orgId  "); // 第三方
		
		sql.append(" ) a GROUP BY orgName, date ");
		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
		return queryResult;
	}
	
	
//	@Override
//	public List<QualityDistributionChart> findConstructionProcessQualityTrends(QueryPage queryPage, Long userOrgId) {
//		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
//		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
//		String time = getStartDateByTimeRanges(map.get("time"));
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = orgId) as orgName ,  date  from  ");
//		sql.append(" (select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_construction_inspection a where  ");
//		sql.append(" QUALITY_STATUS = 0  AND  ");
//		sql.append(" ORG_ID in (   ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,ORG_ID  ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_routing_inspection b where  ");
//		sql.append(" CHECK_RESULTS = 0  AND  ");
//		sql.append(" ORG_ID in (   ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,ORG_ID ) sg  ");
//		sql.append(" GROUP BY date,sg.orgId  ");
//		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
//		return queryResult;
//	}
	
//	@Override
//	public List<QualityDistributionChart> findPartiallyPreparedProductsQualityTrends(QueryPage queryPage, Long userOrgId) {
//		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
//		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
//		String time = getStartDateByTimeRanges(map.get("time"));
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = orgId) as orgName ,  date from (  ");
//		sql.append(" select count(1) as sum, a.ORG_ID as orgId ,DATE_FORMAT(a.CREATE_TIME, '%Y-%m') as date from busi_quality_water_stable_mixing_station_exceed a  ");
//		sql.append(" where  ");
//		sql.append(" a.ORG_ID in(   ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date, a.ORG_ID  ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_cement_mixing_station_exceed b where  ");
//		sql.append(" b.ORG_ID in(   ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date, b.ORG_ID  ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_prestressed_tension_main c where  ");
//		sql.append(" c.IS_QUALIFIED = 1 and  ");
//		sql.append(" c.ORG_ID in(  ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,c.ORG_ID  ");
//		sql.append(" UNION ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date  from busi_quality_asphalt_mixing_plant_exceed d where  ");
//		sql.append(" d.ORG_ID in(    ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,d.ORG_ID  ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date  from busi_quality_spreader_roller_spreader_parent e where  ");
//		sql.append(" e.DATA_TYPE = 0 AND  ");
//		sql.append(" e.ORG_ID in(   ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,e.ORG_ID   ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_mortar f where  ");
//		sql.append(" f.IS_QUALIFIED = 1  AND  ");
//		sql.append(" f.ORG_ID in(   ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,f.ORG_ID ) a  ");
//		sql.append(" group by date, a.orgId  ");
//		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
//		return queryResult;
//	}
	
//	@Override
//	public List<QualityDistributionChart> findRawMaterialQualityQualityTrends(QueryPage queryPage, Long userOrgId) {
//		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
//		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
//		String time = getStartDateByTimeRanges(map.get("time"));
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = raw.orgId) as orgName ,  raw.date from  ");
//		sql.append("  ( select count(1) as sum, a.ORG_ID as orgId ,DATE_FORMAT(a.CREATE_TIME, '%Y-%m') as date from busi_quality_universal_press_machine_parent a ");
//		sql.append("  where a.STATUS = 1   AND    ");
//		sql.append(" ORG_ID in(    ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date, a.ORG_ID     ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, b.ORG_ID as orgId  ,DATE_FORMAT(b.CREATE_TIME, '%Y-%m') as date from busi_quality_raw_material_inspection b  ");
//		sql.append(" where  b.report_Conclusion = 0  AND   ");
//		sql.append(" ORG_ID in(    ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,ORG_ID   ");
//		sql.append(" ) raw  ");
//		sql.append(" GROUP by date, raw.orgId  ");
//		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
//		return queryResult;
//	}
	
//	@Override
//	public List<QualityDistributionChart> findThirdPartyQualityQualityTrends(QueryPage queryPage, Long userOrgId) {
//		Map<String, String> map = this.getOrgIdAndTimeByQueryPage(queryPage);
//		Long orgId = map.get("orgId").equals("") ? userOrgId : Long.valueOf(map.get("orgId"));
//		String time = getStartDateByTimeRanges(map.get("time"));
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select sum(sum) as sum , (select org_name from fw_org where ORG_ID = orgId) as orgName ,  date  from (  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_thickness_check a where  ");
//		sql.append(" a.qualified_Status = 0 AND ");
//		sql.append(" a.ORG_ID in(  ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date, a.ORG_ID  ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_second_lining_thickness_check b where  ");
//		sql.append(" b.qualified_Status = 0  AND  ");
//		sql.append(" b.ORG_ID in(  ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date, b.ORG_ID  ");
//		sql.append(" UNION   ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_inverted_arch_thickness_check c where  ");
//		sql.append(" c.qualified_Status = 0  AND  ");
//		sql.append(" c.ORG_ID in(   ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,c.ORG_ID  ");
//		sql.append(" UNION   ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_concrete_thickness_check d where  ");
//		sql.append(" d.qualified_Status = 0  AND ");
//		sql.append(" d.ORG_ID in(    ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,d.ORG_ID  ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_second_lining_clearance_section_size_check e where  ");
//		sql.append(" e.qualified_Status = 0  AND  ");
//		sql.append(" e.ORG_ID in(  ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,e.ORG_ID  ");
//		sql.append(" UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_arch_spacing_check f where  ");
//		sql.append(" 	f.qualified_Status = 0  AND  ");
//		sql.append(" f.ORG_ID in(    ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date,f.ORG_ID  ");
//		sql.append(" 	UNION  ");
//		sql.append(" select count(1) as sum, ORG_ID as orgId ,DATE_FORMAT(CREATE_TIME, '%Y-%m') as date from busi_quality_primary_support_clearance_section_check g where  ");
//		sql.append(" g.qualified_Status = 0  AND  ");
//		sql.append(" g.ORG_ID in(    ");
//		sql.append(getOrgIdsTypeIn7(orgId));
//		sql.append(" ) ");
//		if(!time.equals("")){
//			sql.append(" and CREATE_TIME >= ").append(time);
//		}
//		sql.append(" GROUP BY date , g.ORG_ID) a  ");
//		sql.append(" GROUP BY date , orgId  ");
//		List<QualityDistributionChart> queryResult = this.queryEntityListBySql(sql.toString(), null, null);
//		return queryResult;
//	}
	
	
	/**
	 * 根据时间范围获取起始时间
	 * @param timeRanges
	 * @return
	 */
	private String getStartDateByTimeRanges(String timeRanges){
		String startDate = "";					 //all_date 获取全部数据
		if(StringUtils.isNotEmpty(timeRanges) && !"all_date".equals(timeRanges) && !"".equals(timeRanges)){
			DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			if("nearly_one_month".equals(timeRanges)){//近一月
				calendar.add(Calendar.MONTH, -1);    
				startDate = dft.format(calendar.getTime());
			} else if("nearly_three_months".equals(timeRanges)){//近三月
				calendar.add(Calendar.MONTH, -3);    
				startDate = dft.format(calendar.getTime());
			} else if("nearly_one_year".equals(timeRanges)){//近一年
				calendar.add(Calendar.YEAR, -1);   
				startDate = dft.format(calendar.getTime());
			}
			startDate += " 00:00:00";
		}
		return startDate;
	}
	
	/**
	 * 在queryPage 中 获取 orgId 和 时间区间
	 * @param queryPage
	 * @return {orgId=获取的orgId(String类型), time=获取的时间区间字典（String 类型）}
	 */
	private Map<String, String> getOrgIdAndTimeByQueryPage(QueryPage queryPage){
		if(null == queryPage){
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		for(QueryParam queryParam : queryPage.getQueryParamList()){
			switch (queryParam.getField()) {
			case "orgId" :
				map.put("orgId", queryParam.getValue() == null ? "" : queryParam.getValue());
				break;
			case "timeRanges" :
				map.put("time", queryParam.getValue() == null ? "" : queryParam.getValue());
				break;
			default:
				throw new BusinessRuntimeException("参数传输错误。BusiReportDaoImpl.getOrgIdAndTimeByQueryPage", "1");
			}
		}
		return map;
	}
	
	/**
	 * 获取 level1 - level7 所有组织机构id = orgId 的 组织机构id 多用于上级查询下级的数据
	 * @param orgId
	 * @return
	 */
	private String getOrgIds(Long orgId){
		StringBuilder sb = new StringBuilder();
		sb.append(" ( ");
		sb.append("SELECT ");
		sb.append("	ORG_ID ");
		sb.append("FROM ");
		sb.append("	fw_org ");
		sb.append("WHERE ");
		sb.append("	( ");
		sb.append("		LEVEL_1 = ").append(orgId);
		sb.append("		OR LEVEL_2 = ").append(orgId);
		sb.append("		OR LEVEL_3 = ").append(orgId);
		sb.append("		OR LEVEL_4 = ").append(orgId);
		sb.append("		OR LEVEL_5 = ").append(orgId);
		sb.append("		OR level_6 = ").append(orgId);
		sb.append("		OR LEVEL_7 = ").append(orgId);
		sb.append("	) ");
		sb.append("AND ORG_STATUS = 2 ");
		sb.append(" ) ");
		return sb.toString();
	}
	
	private String getOrgIdsTypeIn7(Long orgId){
		StringBuilder sb = new StringBuilder();
		sb.append(" ( ");
		sb.append("SELECT ");
		sb.append("	ORG_ID ");
		sb.append("FROM ");
		sb.append("	fw_org ");
		sb.append("WHERE ");
		sb.append("	( ");
		sb.append("		LEVEL_1 = ").append(orgId);
		sb.append("		OR LEVEL_2 = ").append(orgId);
		sb.append("		OR LEVEL_3 = ").append(orgId);
		sb.append("		OR LEVEL_4 = ").append(orgId);
		sb.append("		OR LEVEL_5 = ").append(orgId);
		sb.append("		OR level_6 = ").append(orgId);
		sb.append("		OR LEVEL_7 = ").append(orgId);
		sb.append("	) ");
		sb.append("AND ORG_STATUS = 2 and GROUP_LEVEL = 7");
		sb.append(" ) ");
		return sb.toString();
	}
}
