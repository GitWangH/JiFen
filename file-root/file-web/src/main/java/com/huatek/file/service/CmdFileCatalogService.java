package com.huatek.file.service;

import com.huatek.file.dto.CmdFileCatalogDto;


public interface CmdFileCatalogService {
	
	/**
	 * 
	* @Title: getFileCatalogByPath 
	* @Description: 根据路径获取目录 
	* @createDate: 2017年11月8日 下午8:33:14
	* @param   
	* @return  CmdFileCatalogDto 
	* @author cloud_liu   
	* @throws
	 */
	CmdFileCatalogDto getFileCatalogByPath(Long tenantId, String uploadFileDir);
	
	
	
}
