package com.huatek.busi.service.phicom.score;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.phicom.score.PhiScoreConfigRuleDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiScoreConfigRuleService {
	
	/** 
	* @Title: savePhiScoreConfigRuleDto 
	* @Description: 保存PhiScoreConfigRule信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiScoreConfigRuleDto(PhiScoreConfigRuleDto entityDto) ;

	
	/** 
	* @Title: deletePhiScoreConfigRule 
	* @Description:  删除PhiScoreConfigRule信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiScoreConfigRule(Long id) ;

	
	/** 
	* @Title: getPhiScoreConfigRuleDtoById 
	* @Description: 获取PhiScoreConfigRule的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiScoreConfigRuleDto getPhiScoreConfigRuleDtoById(Long id);

	
    /** 
	* @Title: updatePhiScoreConfigRule 
	* @Description:  更新PhiScoreConfigRule信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiScoreConfigRule(Long id, PhiScoreConfigRuleDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiScoreConfigRulePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiScoreConfigRuleDto>    
	*/ 
	DataPage<PhiScoreConfigRuleDto> getAllPhiScoreConfigRulePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiScoreConfigRuleDto 
	* @Description: 获取所有的PhiScoreConfigRule
	* @param      
	* @return  List<PhiScoreConfigRuleDto>    
	* @throws 
	*/
	List<PhiScoreConfigRuleDto> getAllPhiScoreConfigRuleDto();
	
	
	
	PhiScoreConfigRuleDto getConfigRuleDtoForApp();
	
	
	
	
}
