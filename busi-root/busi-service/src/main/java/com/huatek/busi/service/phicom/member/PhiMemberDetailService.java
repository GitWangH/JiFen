package com.huatek.busi.service.phicom.member;

import java.util.List;

import com.huatek.busi.dto.phicom.member.PhiMemberDetailDto;
import com.huatek.busi.model.phicom.member.PhiMemberDetail;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiMemberDetailService {
	
	/** 
	* @Title: savePhiMemberDetailDto 
	* @Description: 保存PhiMemberDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiMemberDetailDto(PhiMemberDetailDto entityDto) ;

	
	/** 
	* @Title: deletePhiMemberDetail 
	* @Description:  删除PhiMemberDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiMemberDetail(Long id) ;

	
	/** 
	* @Title: getPhiMemberDetailDtoById 
	* @Description: 获取PhiMemberDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiMemberDetailDto getPhiMemberDetailDtoById(Long id);

	
    /** 
	* @Title: updatePhiMemberDetail 
	* @Description:  更新PhiMemberDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiMemberDetail(Long id, PhiMemberDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiMemberDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiMemberDetailDto>    
	*/ 
	DataPage<PhiMemberDetailDto> getAllPhiMemberDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiMemberDetailDto 
	* @Description: 获取所有的PhiMemberDetail
	* @param      
	* @return  List<PhiMemberDetailDto>    
	* @throws 
	*/
	List<PhiMemberDetailDto> getAllPhiMemberDetailDto();


	PhiMemberDetail getPhiMemberDetailById(Long id);
	
}
