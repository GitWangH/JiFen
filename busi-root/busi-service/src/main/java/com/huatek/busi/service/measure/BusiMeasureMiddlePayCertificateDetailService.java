package com.huatek.busi.service.measure;

import java.util.List;

import com.huatek.busi.dto.measure.BusiMeasureMiddlePayCertificateDetailDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface BusiMeasureMiddlePayCertificateDetailService {
	
	/** 
	* @Title: saveBusiMeasureMiddlePayCertificateDetailDto 
	* @Description: 保存BusiMeasureMiddlePayCertificateDetail信息
	* @param   entityDto
	* @return  void    
	*/ 
	void saveBusiMeasureMiddlePayCertificateDetailDto(BusiMeasureMiddlePayCertificateDetailDto entityDto) ;

	
	/** 
	* @Title: deleteBusiMeasureMiddlePayCertificateDetail 
	* @Description:  删除BusiMeasureMiddlePayCertificateDetail信息
	* @param    id
	* @return  void    
	*/ 
	void deleteBusiMeasureMiddlePayCertificateDetail(Long id) ;

	
	/** 
	* @Title: getBusiMeasureMiddlePayCertificateDetailDtoById 
	* @Description: 获取BusiMeasureMiddlePayCertificateDetail的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	BusiMeasureMiddlePayCertificateDetailDto getBusiMeasureMiddlePayCertificateDetailDtoById(Long id);

	
    /** 
	* @Title: updateBusiMeasureMiddlePayCertificateDetail 
	* @Description:  更新BusiMeasureMiddlePayCertificateDetail信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updateBusiMeasureMiddlePayCertificateDetail(Long id, BusiMeasureMiddlePayCertificateDetailDto entityDto) ;

	 
	/** 
	* @Title:  getAllBusiMeasureMiddlePayCertificateDetailPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<BusiMeasureMiddlePayCertificateDetailDto>    
	*/ 
	DataPage<BusiMeasureMiddlePayCertificateDetailDto> getAllBusiMeasureMiddlePayCertificateDetailPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllBusiMeasureMiddlePayCertificateDetailDto 
	* @Description: 获取所有的BusiMeasureMiddlePayCertificateDetail
	* @param      
	* @return  List<BusiMeasureMiddlePayCertificateDetailDto>    
	* @throws 
	*/
	List<BusiMeasureMiddlePayCertificateDetailDto> getAllBusiMeasureMiddlePayCertificateDetailDto();
	
}
