package com.huatek.busi.service.phicom.member;

import java.util.List;

import com.huatek.busi.dto.phicom.member.PhiMemberPrivilegeDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiMemberPrivilegeService {
	
	/** 
	* @Title: savePhiMemberPrivilegeDto 
	* @Description: 保存PhiMemberPrivilege信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiMemberPrivilegeDto(PhiMemberPrivilegeDto entityDto) ;

	
	/** 
	* @Title: deletePhiMemberPrivilege 
	* @Description:  删除PhiMemberPrivilege信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiMemberPrivilege(Long id) ;

	
	/** 
	* @Title: getPhiMemberPrivilegeDtoById 
	* @Description: 获取PhiMemberPrivilege的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiMemberPrivilegeDto getPhiMemberPrivilegeDtoById(Long id);

	
    /** 
	* @Title: updatePhiMemberPrivilege 
	* @Description:  更新PhiMemberPrivilege信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiMemberPrivilege(Long id, PhiMemberPrivilegeDto entityDto);

	 
	/** 
	* @Title:  getAllPhiMemberPrivilegePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiMemberPrivilegeDto>    
	*/ 
	DataPage<PhiMemberPrivilegeDto> getAllPhiMemberPrivilegePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiMemberPrivilegeDto 
	* @Description: 获取所有的PhiMemberPrivilege
	* @param      
	* @return  List<PhiMemberPrivilegeDto>    
	* @throws 
	*/
	List<PhiMemberPrivilegeDto> getAllPhiMemberPrivilegeDto();
	
	/**
	 * @Title:  editPhiMemberPrivilegeState 
	 * @Description:更改会员特权权限状态
	 * @param id
	 * @param state
	 */
	void editPhiMemberPrivilegeState(Long id,Integer state);
	
}
