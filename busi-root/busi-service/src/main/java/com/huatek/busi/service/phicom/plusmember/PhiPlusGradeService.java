package com.huatek.busi.service.phicom.plusmember;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.phicom.plusmember.PhiPlusGradeDto;
import com.huatek.busi.model.phicom.plusmember.PhiPlusGrade;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusGradeService {
	
	/** 
	* @Title: savePhiPlusGradeDto 
	* @Description: 保存PhiPlusGrade信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiPlusGradeDto(PhiPlusGradeDto entityDto) ;

	
	/** 
	* @Title: deletePhiPlusGrade 
	* @Description:  删除PhiPlusGrade信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiPlusGrade(Long id) ;

	
	/** 
	* @Title: getPhiPlusGradeDtoById 
	* @Description: 获取PhiPlusGrade的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusGradeDto getPhiPlusGradeDtoById(Long id);

	
    /** 
	* @Title: updatePhiPlusGrade 
	* @Description:  更新PhiPlusGrade信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusGrade(Long id, PhiPlusGradeDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiPlusGradePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusGradeDto>    
	*/ 
	DataPage<PhiPlusGradeDto> getAllPhiPlusGradePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusGradeDto 
	* @Description: 获取所有的PhiPlusGrade
	* @param      
	* @return  List<PhiPlusGradeDto>    
	* @throws 
	*/
	List<PhiPlusGradeDto> getAllPhiPlusGradeDto();
	
	PhiPlusGradeDto findPhiPlusGradeByPlusCode(String plusCode);
	
}
