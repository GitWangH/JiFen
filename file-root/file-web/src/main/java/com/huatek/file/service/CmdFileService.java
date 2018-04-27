package com.huatek.file.service;

import java.util.List;

import com.huatek.file.dto.CmdFileDto;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;

public interface CmdFileService {
	
	/**
	 * 保存CmdFile信息
	 * @throws Exception 
	 */
	CmdFileDto saveCmdFileDto(CmdFileDto cmdFileDto) throws Exception;

	/**
	 * 删除CmdFile信息
	 * @param id
	 */
	void deleteCmdFile(Long id) throws Exception;

	/**
	 * 获取CmdFile的Dto
	 * 
	 * @param id
	 * @return
	 */
	CmdFileDto getCmdFileDtoById(Long id);


	/**
	 * 更新CmdFile信息
	 * @throws Exception 
	 */
	void updateCmdFile(Long id, CmdFileDto cmdFileDto) throws Exception;

	/**
	 * 分页查询
	 */
	DataPage<CmdFileDto> getAllCmdFilePage(QueryPage queryPage);
	
	/**
	 * 获取所有的CmdFile
	 * 
	 * @return
	 */
	List<CmdFileDto> getAllCmdFileDto();
	
	/**
	 * 
	 * @Description: 通过业务id的查询
	 * @param  businessId 业务id
	 * @param    
	 * @return List<CmdFileDto>  
	 * @throws
	 * @author hobart_ren
	  *@e_mail hobart_ren@huatek.com
	 * @date 2016年5月18日
	 */
	List<CmdFileDto> getCmdFileDtoByBusiId(String  businessId) ;
	
	/**
	 * 根据业务id数组批量取附件
	* @Title: getCmdFileDtoByBusiIds 
	* @Description: TODO 
	* @createDate: 2016年7月13日 下午3:30:11
	* @param   
	* @return  List<CmdFileDto>    
	* @throws
	 */
	List<CmdFileDto> getCmdFileDtoByBusiIds(String[]  businessId) ;
	
	
}
