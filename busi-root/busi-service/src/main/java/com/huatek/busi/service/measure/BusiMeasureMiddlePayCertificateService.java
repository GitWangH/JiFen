package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiMeasureMiddlePayCertificateService {
	
	/** 
	* @Title: saveBusiMeasureMiddlePayCertificateDto 
	* @Description: 保存BusiMeasureMiddlePayCertificate信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureMiddlePayCertificateDto(BusiMeasureMiddlePayCertificateDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureMiddlePayCertificate 
	* @Description:  删除BusiMeasureMiddlePayCertificate信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureMiddlePayCertificate(Long id) ;

	
	/** 
	* @Title: getBusiMeasureMiddlePayCertificateDtoById 
	* @Description: 获取BusiMeasureMiddlePayCertificate的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureMiddlePayCertificateDto getBusiMeasureMiddlePayCertificateDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureMiddlePayCertificate 
	* @Description:  更新BusiMeasureMiddlePayCertificate信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureMiddlePayCertificate(Long id, BusiMeasureMiddlePayCertificateDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureMiddlePayCertificatePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureMiddlePayCertificateDto>    
	*/ 
	DataPage<BusiMeasureMiddlePayCertificateDto> getAllBusiMeasureMiddlePayCertificatePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureMiddlePayCertificateDto 
	* @Description: 获取所有的BusiMeasureMiddlePayCertificate
	* @param      
	* @return  List<BusiMeasureMiddlePayCertificateDto>    
	* @throws 
	*/
	List<BusiMeasureMiddlePayCertificateDto> getAllBusiMeasureMiddlePayCertificateDto();
	
}
