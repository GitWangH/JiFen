package com.huatek.busi.dao.base;

/***
 * 
 * 基础数据初始化 Dao
 * @author eli_cui
 *
 */
public interface BusiBaseInitDao {
	
	/**
	 * 根据 组织机构id 和 租户id 初始化 基础数据
	 * 项目工程量清单
	 * 项目形象清单
	 * 项目分部分项
	 * 分部分项与工程量清单挂接
	 * 形象清单与分部分项挂接
	 * @param orgId 组织机构id
	 * @param tenantId 租户id 
	 */
	void initBaseData(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化前清理 - 分部分项与工程量清单挂接
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData1(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化前清理 - 形象清单与分部分项挂接
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData2(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化前清理 - 分部分项
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData3(Long orgId, Long tenantId) throws Exception;
	/**
	 * 初始化前清理 - 形象清单
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData4(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化前清理 - 工程量清单
	 * @param orgId
	 * @param tenantId
	 */
	public void cleanData5(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化工程量清单
	 */
	public void initBusiBaseEngineeringQuantityList(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化形象清单
	 */
	public void initBusiBaseImageList(Long orgId, Long tenantId) throws Exception;
	/**
	 * 初始化分部分项
	 */
	public void initBusiBaseSubEngineering(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化形象清单和分部分项挂接表
	 */
	public void initBusiBaseImageListSubConnectionTable(Long orgId, Long tenantId) throws Exception;
	
	/**
	 * 初始化分部分项与工程量清单 挂接表
	 */
	public void initBusiBaseQuantityListSubConnectionTable(Long orgId, Long tenantId) throws Exception;
}
