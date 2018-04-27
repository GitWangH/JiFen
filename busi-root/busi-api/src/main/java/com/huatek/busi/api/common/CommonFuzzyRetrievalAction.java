package com.huatek.busi.api.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huatek.busi.BusiUrlConstants;
import com.huatek.busi.dto.common.CommonFuzzyRetrievalDto;
import com.huatek.busi.service.common.CommonFuzzyRetrievalService;

/**
 * 公用 模糊检索 api
 * @author eli_cui
 */
@RestController
@RequestMapping(value = BusiUrlConstants.BUSI_COMMON_INTERFACE_API)
public class CommonFuzzyRetrievalAction {
	
	@Autowired
	CommonFuzzyRetrievalService service;
	
	@RequestMapping(value = "/getTendersBranchByKeywordAndOrgId/{keyword}/{orgId}", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonFuzzyRetrievalDto> getTendersBranchByKeywordAndOrgId(@PathVariable("keyword") String keyword, @PathVariable("orgId") Long orgId){
		List<CommonFuzzyRetrievalDto> dtoList = service.getTendersBranchByKeywordAndOrgId(keyword, orgId);
		return dtoList;
	}

}
