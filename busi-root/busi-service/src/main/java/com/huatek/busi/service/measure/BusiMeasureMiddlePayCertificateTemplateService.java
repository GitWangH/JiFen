package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateTemplateDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiMeasureMiddlePayCertificateTemplateService {
	
	/** 
	* @Title: saveBusiMeasureMiddlePayCertificateTemplateDto 
	* @Description: 保存BusiMeasureMiddlePayCertificateTemplate信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureMiddlePayCertificateTemplateDto(BusiMeasureMiddlePayCertificateTemplateDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureMiddlePayCertificateTemplate 
	* @Description:  删除BusiMeasureMiddlePayCertificateTemplate信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureMiddlePayCertificateTemplate(Long id) ;

	
	/** 
	* @Title: getBusiMeasureMiddlePayCertificateTemplateDtoById 
	* @Description: 获取BusiMeasureMiddlePayCertificateTemplate的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureMiddlePayCertificateTemplateDto getBusiMeasureMiddlePayCertificateTemplateDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureMiddlePayCertificateTemplate 
	* @Description:  更新BusiMeasureMiddlePayCertificateTemplate信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureMiddlePayCertificateTemplate(Long id, BusiMeasureMiddlePayCertificateTemplateDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureMiddlePayCertificateTemplatePage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureMiddlePayCertificateTemplateDto>    
	*/ 
	DataPage<BusiMeasureMiddlePayCertificateTemplateDto> getAllBusiMeasureMiddlePayCertificateTemplatePage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureMiddlePayCertificateTemplateDto 
	* @Description: 获取所有的BusiMeasureMiddlePayCertificateTemplate
	* @param      
	* @return  List<BusiMeasureMiddlePayCertificateTemplateDto>    
	* @throws 
	*/
	List<BusiMeasureMiddlePayCertificateTemplateDto> getAllBusiMeasureMiddlePayCertificateTemplateDto(Long orgId);
	
}
