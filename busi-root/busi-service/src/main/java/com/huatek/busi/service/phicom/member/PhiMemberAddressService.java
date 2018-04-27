package com.huatek.busi.service.phicom.member;

import java.util.List;

import com.huatek.busi.dto.phicom.member.PhiMemberAddressDto;
import com.huatek.busi.dto.phicom.member.PhiMemberDto;
import com.huatek.busi.dto.phicom.member.addAddressDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiMemberAddressService {
	
	/** 
	* @Title: savePhiMemberAddressDto 
	* @Description: 保存PhiMemberAddress信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiMemberAddressDto(PhiMemberAddressDto entityDto,int Uid) ;

	
	/** 
	* @Title: deletePhiMemberAddress 
	* @Description:  删除PhiMemberAddress信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiMemberAddress(Long id) ;

	
	/** 
	* @Title: getPhiMemberAddressDtoById 
	* @Description: 获取PhiMemberAddress的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiMemberAddressDto getPhiMemberAddressDtoById(Long id);

	
    /** 
	* @Title: updatePhiMemberAddress 
	* @Description:  更新PhiMemberAddress信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiMemberAddress(Long id, PhiMemberAddressDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiMemberAddressPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiMemberAddressDto>    
	*/ 
	DataPage<PhiMemberAddressDto> getAllPhiMemberAddressPage(QueryPage queryPage);
	 
	
	DataPage<PhiMemberAddressDto> getAllPhiMemberAddressPageByMemberId(QueryPage queryPage,Long id);
	/** 
	* @Title:  getAllPhiMemberAddressDto 
	* @Description: 获取所有的PhiMemberAddress
	* @param      
	* @return  List<PhiMemberAddressDto>    
	* @throws 
	*/
	List<PhiMemberAddressDto> getAllPhiMemberAddressDto();
	
	/** 
	* @Title: getPhiMemberAddressDtoById 
	* @Description: 获取PhiMemberAddress的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	List<PhiMemberAddressDto> getPhiMemberAddressDtoByMemberId(int UId);
	
	/** 
	* @Title: getPhiMemberAddressDtoById 
	* @Description: 获取PhiMemberAddress的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	void getPhiMemberAddressDtoByUId(int UId);

	void saveAddadressDto(addAddressDto addressdto);
	
	boolean saveMemberAddressDtoForApp(PhiMemberAddressDto entityDto, Long memberId,int Uid); 
	
}
