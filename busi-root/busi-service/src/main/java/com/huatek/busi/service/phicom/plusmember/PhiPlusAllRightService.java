package com.huatek.busi.service.phicom.plusmember;

import java.text.ParseException;
import java.util.List;

import com.huatek.busi.dto.phicom.plusmember.PhiPlusAllRightDto;
import com.huatek.busi.dto.phicom.plusmember.PhiPlusRightDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface PhiPlusAllRightService {

	 
	/** 
	* @Title:  getAllPhiPlusRightPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiPlusRightDto>    
	*/ 
	DataPage<PhiPlusAllRightDto> getAllPhiPlusRightPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiPlusRightDto 
	* @Description: 获取所有的PhiPlusRight
	* @param      
	* @return  List<PhiPlusRightDto>    
	*/
	List<PhiPlusAllRightDto> getAllPhiPlusRightDto();
	
	/** 
	* @Title:  savePhiPlusAllRightDto 
	* @Description: 保存Dto
	* @param      PhiPlusAllRightDto
	*/
	void savePhiPlusAllRightDto(PhiPlusAllRightDto entityDto);
	
	
	
	/** 
	* @Title: getPhiPlusAllRightDtoById 
	* @Description: 获取PhiPlusAllRightDto
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiPlusAllRightDto getPhiPlusAllRightDtoById(Long id);
	
	
    /** 
	* @Title: updatePhiPlusAllRight 
	* @Description:  更新PhiPlusAllRight信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiPlusAllRight(Long id, PhiPlusAllRightDto entityDto) ;
	
	/**
	 * @Title: updateIsValidate 
	 * @Description:  任务关闭
	 * @param id
	 * @param isValidate
	 */
	
	void updateIsValidate(Long id, String  isValidate );
	
	/**
	 * @Title:couponsAutoUptoGrant
	 * @Description:  任务关闭
	 * 
	 */
	void couponsAutoUptoGrant();
	
	void couponsAutoUptoGrantByMickey();
	
	
	void deletePhiAllPlusRight(Long detailId);
}
