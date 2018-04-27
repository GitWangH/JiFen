package com.huatek.busi.service.phicom.order;

import java.util.List;

import com.huatek.busi.dto.phicom.order.Mylogisticdto;
import com.huatek.busi.dto.phicom.order.PhiLogisticDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.ExpressUtils.ExpressVO;

public interface PhiLogisticService {
	
	/** 
	* @Title: savePhiLogisticDto 
	* @Description: 保存PhiLogistic信息
	* @param   entityDto
	* @return  void    
	*/ 
	void savePhiLogisticDto(PhiLogisticDto entityDto) ;

	
	/** 
	* @Title: deletePhiLogistic 
	* @Description:  删除PhiLogistic信息
	* @param    id
	* @return  void    
	*/ 
	void deletePhiLogistic(Long id) ;

	
	/** 
	* @Title: getPhiLogisticDtoById 
	* @Description: 获取PhiLogistic的Dto 
	* @param    id
	* @return  MdmLineBaseInfoDto    
	*/
	PhiLogisticDto getPhiLogisticDtoById(Long id);

	
    /** 
	* @Title: updatePhiLogistic 
	* @Description:  更新PhiLogistic信息 
	* @param   id
	* @param   entityDto
	* @return  void    
	*/
	void updatePhiLogistic(Long id, PhiLogisticDto entityDto) ;

	 
	/** 
	* @Title:  getAllPhiLogisticPage 
	* @Description: 分页查询 
	* @param    queryPage
	* @return  DataPage<PhiLogisticDto>    
	*/ 
	DataPage<PhiLogisticDto> getAllPhiLogisticPage(QueryPage queryPage);
	 
	/** 
	* @Title:  getAllPhiLogisticDto 
	* @Description: 获取所有的PhiLogistic
	* @param      
	* @return  List<PhiLogisticDto>    
	* @throws 
	*/
	List<PhiLogisticDto> getAllPhiLogisticDto();


	void Autologistic();

	/**
	 *调快递100获得物流详情
	 * @param id
	 */
	ExpressVO getLogisticDetial(Long id);

	/**
	 * 解析快递100获得的物流信息
	 * @param logistic
	 * @return
	 */
	List<Mylogisticdto> getjson(ExpressVO logistic);


}
