package com.huatek.file.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.file.dao.CmdFileCatalogDao;
import com.huatek.file.dto.CmdFileCatalogDto;
import com.huatek.file.modal.CmdFileCatalogManager;
import com.huatek.frame.core.beancopy.BeanCopy;


@Service("cmdFileCatalogServiceImpl")
@Transactional
public class CmdFileCatalogServiceImpl implements CmdFileCatalogService {

	@Autowired
	private CmdFileCatalogDao cmdFileCatalogDao;

	@Override
	public CmdFileCatalogDto getFileCatalogByPath(
			Long tenantId, String path) {
		CmdFileCatalogManager catalog = cmdFileCatalogDao.getCmdFileCatalogByPath(path, tenantId);
		return BeanCopy.getInstance().convert(catalog, CmdFileCatalogDto.class);
	}
	
	
	
	
}
