package com.huatek.busi.service.phicom.member;


import java.util.List;

import com.huatek.busi.dto.phicom.member.PhiMemberGradeDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiMemberGradeService {
	
	/** 
	* @Title: savePhiMemberGradeDto 
	* @Description: 保存PhiMemberGrade信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiMemberGradeDto(PhiMemberGradeDto entityDto) ;

	
	/** 
	* @Title: deletePhiMemberGrade 
	* @Description:  删除PhiMemberGrade信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiMemberGrade(Long id) ;

	
	/** 
	* @Title: getPhiMemberGradeDtoById 
	* @Description: 获取PhiMemberGrade的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiMemberGradeDto getPhiMemberGradeDtoById(Long id);

	
    /** 
	* @Title: updatePhiMemberGrade 
	* @Description:  更新PhiMemberGrade信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiMemberGrade(Long id, PhiMemberGradeDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiMemberGradePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiMemberGradeDto>    
	*/ 
	DataPage<PhiMemberGradeDto> getAllPhiMemberGradePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiMemberGradeDto 
	* @Description: 获取所有的PhiMemberGrade
	* @param      
	* @return  List<PhiMemberGradeDto>    
	* @throws 
	*/
	List<PhiMemberGradeDto> getAllPhiMemberGradeDto();
	
}
