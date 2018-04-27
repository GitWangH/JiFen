package com.huatek.busi.service.phicom.score;

import java.util.List;

import com.huatek.busi.dto.phicom.score.PhiScoreFlowDto;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiScoreFlowService {
	
	/** 
	* @Title: savePhiScoreFlowDto 
	* @Description: 保存PhiScoreFlow信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiScoreFlowDto(PhiScoreFlowDto entityDto) ;

	
	/** 
	* @Title: deletePhiScoreFlow 
	* @Description:  删除PhiScoreFlow信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiScoreFlow(Long id) ;

	
	/** 
	* @Title: getPhiScoreFlowDtoById 
	* @Description: 获取PhiScoreFlow的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiScoreFlowDto getPhiScoreFlowDtoById(Long id);

	
    /** 
	* @Title: updatePhiScoreFlow 
	* @Description:  更新PhiScoreFlow信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiScoreFlow(Long id, PhiScoreFlowDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiScoreFlowPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiScoreFlowDto>    
	*/ 
	DataPage<PhiScoreFlowDto> getAllPhiScoreFlowPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiScoreFlowDto 
	* @Description: 获取所有的PhiScoreFlow
	* @param      
	* @return  List<PhiScoreFlowDto>    
	* @throws 
	*/
	List<PhiScoreFlowDto> getAllPhiScoreFlowDto();


	void savePhiScoreFlow(PhiScoreFlow entity);
	int getSoonFallDueScore(Long memberId);
	PhiScoreFlow findPhiScoreFlowByCondition(Long memberId,String orderCode);
	 List<PhiScoreFlow> findPhiScoreFlowByCondition(Long memberId);


	PhiScoreFlow findPhiScoreFlowByRCondition(long memberId, String refundCode);
	/**
	 * 获取剩余积分数
	 * @param memberId
	 * @return
	 */
	int getRemainScore(Long memberId);
	
}
