package com.huatek.busi.service.phicom.score;

import java.util.List;

import com.huatek.busi.dto.phicom.score.PhiScoreTaskConfigDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiScoreTaskConfigService {
	
	/** 
	* @Title: savePhiScoreTaskConfigDto 
	* @Description: 保存PhiScoreTaskConfig信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiScoreTaskConfigDto(PhiScoreTaskConfigDto entityDto) ;

	
	/** 
	* @Title: deletePhiScoreTaskConfig 
	* @Description:  删除PhiScoreTaskConfig信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiScoreTaskConfig(Long id) ;

	
	/** 
	* @Title: getPhiScoreTaskConfigDtoById 
	* @Description: 获取PhiScoreTaskConfig的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiScoreTaskConfigDto getPhiScoreTaskConfigDtoById(Long id);

	
    /** 
	* @Title: updatePhiScoreTaskConfig 
	* @Description:  更新PhiScoreTaskConfig信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiScoreTaskConfig(Long id, PhiScoreTaskConfigDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiScoreTaskConfigPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiScoreTaskConfigDto>    
	*/ 
	DataPage<PhiScoreTaskConfigDto> getAllPhiScoreTaskConfigPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiScoreTaskConfigDto 
	* @Description: 获取所有的PhiScoreTaskConfig
	* @param      
	* @return  List<PhiScoreTaskConfigDto>    
	* @throws 
	*/
	List<PhiScoreTaskConfigDto> getAllPhiScoreTaskConfigDto();


	/**
	 * 更改任务状态
	 * @param id
	 * @param taskSwitch
	 */
	void editPhiScoreswitch(Long id,String  taskSwitch);
	
}
