package com.huatek.busi.dao.impl.base;

import org.springframework.stereotype.Repository;

import com.huatek.busi.dao.base.BusiBaseInitDao;
import com.huatek.frame.core.dao.AbstractDao;

@Repository("busiBaseInitDao")
public class BusiBaseInitDaoImpl extends AbstractDao<Long, Object> implements BusiBaseInitDao{
	
	@Override
	public void initBaseData(Long orgId, Long tenantId) throws Exception {
		cleanData1(orgId, tenantId);
		cleanData2(orgId, tenantId);
		cleanData3(orgId, tenantId);
		cleanData4(orgId, tenantId);
		cleanData5(orgId, tenantId);
		initBusiBaseEngineeringQuantityList(orgId, tenantId);
		initBusiBaseImageList(orgId, tenantId);
		initBusiBaseSubEngineering(orgId, tenantId);
		initBusiBaseImageListSubConnectionTable(orgId, tenantId);
		initBusiBaseQuantityListSubConnectionTable(orgId, tenantId);
	}
	/**
	 * 初始化前清理 - 分部分项与工程量清单挂接
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData1(Long orgId, Long tenantId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM busi_base_quantity_list_sub_connection_table  where SUB_ENGINEERING_ID in ( ");
		sql.append(" (select SUB_ENGINEERING_ID from busi_base_sub_engineering where ORG_ID = " + orgId +" and TENANT_ID = " + tenantId +")); ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/**
	 * 初始化前清理 - 形象清单与分部分项挂接
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData2(Long orgId, Long tenantId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM busi_base_image_list_sub_connection_table WHERE SUB_ENGINEERING_ID in ( ");
		sql.append(" (select SUB_ENGINEERING_ID from busi_base_sub_engineering where ORG_ID = " + orgId +"  and TENANT_ID = " + tenantId +")); ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/**
	 * 初始化前清理 - 分部分项
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData3(Long orgId, Long tenantId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM busi_base_sub_engineering where ORG_ID = " + orgId +"  and TENANT_ID = " + tenantId +"; ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/**
	 * 初始化前清理 - 形象清单
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData4(Long orgId, Long tenantId) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM busi_base_image_list WHERE ORG_ID = " + orgId +"  and TENANT_ID = " + tenantId +"; ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/**
	 * 初始化前清理 - 工程量清单
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData5(Long orgId, Long tenantId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM busi_base_engineering_quantity_list WHERE ORG_ID = " + orgId +"  and TENANT_ID = " + tenantId +"; ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	
	
	/**
	 * 初始化工程量清单
	 */
	public void initBusiBaseEngineeringQuantityList(Long orgId, Long tenantId) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO busi_base_engineering_quantity_list ( ");
		sql.append(" NUMBER, NAME, UNIT, REMARK, ");
		sql.append(this.insertCommonField);
		sql.append(" ) ");
		sql.append(" SELECT a.NUMBER, a.NAME, a.UNIT, a.REMARK,  ");
		sql.append( this.getSelectCommonField(orgId, tenantId));
		sql.append(" FROM busi_base_engineering_quantity_list a where a.IS_DELETE = 0 and a.ORG_ID = 1 and a.TENANT_ID is null ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/**
	 * 初始化形象清单
	 */
	public void initBusiBaseImageList(Long orgId, Long tenantId) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO busi_base_image_list ( ");
		sql.append(" NUMBER, NAME, UNIT, REMARK, TYPE, ");
		sql.append(this.insertCommonField);
		sql.append(" ) ");
		sql.append(" SELECT a.NUMBER, a.NAME, a.UNIT, a.REMARK, a.TYPE,  ");
		sql.append( this.getSelectCommonField(orgId, tenantId));
		sql.append(" FROM busi_base_image_list a where a.IS_DELETE = 0 and a.ORG_ID = 1 and a.TENANT_ID is null ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/**
	 * 初始化分部分项
	 */
	public void initBusiBaseSubEngineering(Long orgId, Long tenantId) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO busi_base_sub_engineering ( ");
		sql.append(" NUMBER, NAME, UNIT, REMARK, ");
		sql.append(this.insertCommonField);
		sql.append(" ) ");
		sql.append(" SELECT a.NUMBER, a.NAME, a.UNIT, a.REMARK,  ");
		sql.append( this.getSelectCommonField(orgId, tenantId));
		sql.append(" FROM busi_base_sub_engineering a where a.IS_DELETE = 0 and a.ORG_ID = 1 and a.TENANT_ID is null ");
		this.createSQLQuery(sql.toString()).executeUpdate();
		
	}
	
	/**
	 * 初始化形象清单和分部分项挂接表
	 */
	public void initBusiBaseImageListSubConnectionTable(Long orgId, Long tenantId) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO busi_base_image_list_sub_connection_table (							");
		sql.append("	IMAGE_LIST_ID, SUB_ENGINEERING_ID											");
		sql.append(")																				");
		sql.append("SELECT																			");
		sql.append("	(select IMAGE_LIST_ID from busi_base_image_list where uuid = a.UUID and ORG_ID = "); sql.append(orgId); sql.append("),	");
		sql.append("	(select SUB_ENGINEERING_ID from busi_base_sub_engineering where uuid = c.UUID and org_id =  "); sql.append(orgId); sql.append(")	");
		sql.append("FROM ");
		sql.append("busi_base_image_list a,															");
		sql.append("busi_base_image_list_sub_connection_table b,									");
		sql.append("busi_base_sub_engineering c														");
		sql.append("where a.IMAGE_LIST_ID = b.IMAGE_LIST_ID											");
		sql.append("and c.SUB_ENGINEERING_ID = b.SUB_ENGINEERING_ID									");
		sql.append("and a.ORG_ID = 1 and c.ORG_ID = 1												");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/**
	 * 初始化分部分项与工程量清单 挂接表
	 */
	public void initBusiBaseQuantityListSubConnectionTable(Long orgId, Long tenantId) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into busi_base_quantity_list_sub_connection_table (							");
		sql.append("	ENGINEERING_QUANTITY_ID, SUB_ENGINEERING_ID										");
		sql.append(")																					");
		sql.append("SELECT																				");
		sql.append("	(select ENGINEERING_QUANTITY_ID from  busi_base_engineering_quantity_list where uuid = c.UUID and org_id = "); sql.append(orgId); sql.append("),	");
		sql.append("	(select sub_engineering_id from busi_base_sub_engineering where uuid = a.UUID and org_id = "); sql.append(orgId); sql.append(")	");
		sql.append("FROM																				");
		sql.append("	busi_base_sub_engineering a,													");
		sql.append("	busi_base_quantity_list_sub_connection_table b,									");
		sql.append("	busi_base_engineering_quantity_list c											");
		sql.append("WHERE																				");
		sql.append("	a.SUB_ENGINEERING_ID = b.SUB_ENGINEERING_ID										");
		sql.append("and																				    ");
		sql.append("	c.ENGINEERING_QUANTITY_ID = b.ENGINEERING_QUANTITY_ID						    ");
		sql.append("AND a.ORG_ID = 1 AND c.ORG_ID = 1				                                    ");
		this.createSQLQuery(sql.toString()).executeUpdate();
	}
	
	/*查询的共同数据*/
	private static String insertCommonField = " ORDER_NUMBER, ORG_ID, PARENT_ID, CREATER, CREATER_NAME, CREATE_TIME, LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9, LEVEL_10, GROUP_LEVEL, TENANT_ID, IS_DELETE, IS_LEAF, UUID ";
	/*查询的共同数据*/
	private String getSelectCommonField(Long orgId, Long tenantId){
		StringBuilder selectCommonField = new StringBuilder();
		selectCommonField.append(" a.ORDER_NUMBER, ");
		selectCommonField.append(orgId);
		selectCommonField.append(" , a.PARENT_ID, a.CREATER, ");
		selectCommonField.append(" '初始化', ");//CREATER_NAME
		selectCommonField.append(" now(), ");//CREATE_TIME
		selectCommonField.append(" a.LEVEL_1, a.LEVEL_2, a.LEVEL_3, a.LEVEL_4, a.LEVEL_5, a.LEVEL_6, a.LEVEL_7, a.LEVEL_8, a.LEVEL_9, a.LEVEL_10, a.GROUP_LEVEL, ");
		selectCommonField.append(tenantId);
		selectCommonField.append(" , a.IS_DELETE, a.IS_LEAF, a.UUID ");
		return selectCommonField.toString();
	}
	//" a.ORDER_NUMBER, a.ORG_ID, a.PARENT_ID, a.CREATER, a.CREATER_NAME, a.CREATE_TIME, a.LEVEL_1, a.LEVEL_2, a.LEVEL_3, a.LEVEL_4, a.LEVEL_5, a.LEVEL_6, a.LEVEL_7, a.LEVEL_8, a.LEVEL_9, a.LEVEL_10, a.GROUP_LEVEL, a.TENANT_ID, a.IS_DELETE, a.IS_LEAF, a.UUID";
}
