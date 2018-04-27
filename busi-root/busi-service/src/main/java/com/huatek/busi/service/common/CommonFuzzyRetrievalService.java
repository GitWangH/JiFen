package com.huatek.busi.service.common;

import java.util.List;

import com.huatek.busi.dto.common.CommonFuzzyRetrievalDto;

/**
 * 公共 - 模糊搜索
 * @author eli_cui
 *
 */
public interface CommonFuzzyRetrievalService {
	
	/**
	 * 根据orgId 和 keyword 获取 ｛标段分部分项｝模糊检索的数据
	 * @param keyword
	 * @param orgId
	 * @return
	 */
	List<CommonFuzzyRetrievalDto> getTendersBranchByKeywordAndOrgId(String keyword, Long orgId);
	
}
