package com.huatek.file.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huatek.file.dao.CmdFileDao;
import com.huatek.file.dto.CmdFileDto;
import com.huatek.file.modal.CmdFile;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;

@Service("cmdFileService")
@Transactional(propagation = Propagation.REQUIRED)
public class CmdFileServiceImpl implements CmdFileService {
	
	private static final Logger log = LoggerFactory.getLogger(CmdFileServiceImpl.class);
	
	@Autowired
	CmdFileDao cmdFileDao;
	
	/**
	 * 分页查询
	 */
	public DataPage<CmdFileDto> getAllCmdFilePage(QueryPage queryPage) {
		DataPage<CmdFile> dataPage = cmdFileDao.getAllCmdFile(queryPage);
		//根据查询的数据将页面需要的信息返回
		DataPage<CmdFileDto> cmdFilePage = CmdFileDto.transToDtoPage(dataPage);
		return cmdFilePage;
	}
	
	/**
	 * 获取所有的CmdFileDto
	 * 
	 * @return
	 */
	public List<CmdFileDto> getAllCmdFileDto() {
		List<CmdFileDto> dtos = DTOUtils.mapList(cmdFileDao.findAllCmdFile(), CmdFileDto.class);
		return dtos;
	}
	
	
	public List<CmdFileDto> getCmdFileDtoByBusiId(String  businessId) {
		List<CmdFileDto> dtos = DTOUtils.mapList(cmdFileDao.getCmdFileDtoByBusiId(businessId), CmdFileDto.class);
		return dtos;
	}
	
	public List<CmdFileDto> getCmdFileDtoByBusiIds(String[]  businessIds) {
		List<CmdFileDto> dtos = DTOUtils.mapList(cmdFileDao.getCmdFileDtoByBusiIds(businessIds), CmdFileDto.class);
		return dtos;
	}
	
	/**
	 * 保存CmdFile信息
	 * @throws Exception 
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public CmdFileDto saveCmdFileDto(CmdFileDto cmdFileDto) throws Exception {
		log.debug("save cmdFileDto @" + cmdFileDto);
		//根据页面传进来的值设置保存的值信息
		List<CmdFileDto> cmdFileDtoCount=this.getCmdFileDtoByBusiId(cmdFileDto.getBusinessId());
		if(cmdFileDtoCount!=null &&!cmdFileDtoCount.isEmpty()){
			cmdFileDto.setOrder(Long.parseLong((cmdFileDtoCount.size()+1)+""));
		}else{
			cmdFileDto.setOrder(Long.parseLong("1"));
		}
		CmdFile cmdFile = DTOUtils.map(cmdFileDto, CmdFile.class);
		//保存之前操作
		beforeSave(cmdFileDto, cmdFile);
		//进行持久化保存
		cmdFileDao.persistentCmdFile(cmdFile);
		log.debug("saved cmdFile id is @" + cmdFile.getId());
		if(cmdFile.getId()!=null){
			return DTOUtils.map(cmdFile, CmdFileDto.class);
		}
		return null;
		
	}
	
	/**
	 * 保存之前设置保存对象信息
	 * 
	 * @param CmdFileDto
	 * @param model
	 * @throws Exception 
	 */
		private void beforeSave(CmdFileDto cmdFileDto, CmdFile cmdFile) throws Exception{
			//...
		}
	
	/**
	 * 获取CmdFile
	 * @param id
	 * @return
	 */
	public CmdFile getCmdFileById(Long id) {
		Assert.notNull(id, "CmdFileID不能为空");
		log.debug("get cmdFile by id@" + id);
		CmdFile cmdFile = cmdFileDao.findCmdFileById(id);
		if (cmdFile == null) {
			return null;
		}
		return cmdFile;
	}
	
	/**
	 * 获取CmdFile的Dto
	 * 
	 * @param id
	 * @return
	 */
	public CmdFileDto getCmdFileDtoById(Long id) {
		CmdFile file=this.getCmdFileById(id);
		if(file!=null){
			return DTOUtils.map(file, CmdFileDto.class);
		}
		return null;
	}
	
	/**
	 * 更新CmdFile信息
	 * @throws Exception 
	 */
	@Override
	public void updateCmdFile(Long id, CmdFileDto cmdFileDto) throws Exception{
		Assert.notNull(id, "保存CmdFileID不能为空");
		log.debug("save cmdFile by id@" + id);
		CmdFile cmdFile = cmdFileDao.findCmdFileById(id);
		BeanUtils.copyNotNullProperties(cmdFileDto, cmdFile, 
				new String[] {""});
		beforeSave(cmdFileDto, cmdFile);
		//进行持久化保存
		cmdFileDao.persistentCmdFile(cmdFile);
	}
	
	
	
	/**
	 * 删除CmdFile信息
	 * @param id
	 */
	public void deleteCmdFile(Long id) throws Exception{
		Assert.notNull(id, "CmdFileID不能为空");
		log.debug("delete cmdFile by id@" + id);
		beforeRemove(id);
		CmdFile cmdFile = cmdFileDao.findCmdFileById(id);
		if (cmdFile == null) {
			throw new RuntimeException(id+"");
		}
		cmdFileDao.deleteCmdFile(cmdFile);
	}
	
	/**
	 * 删除之前的操作
	 * @param id
	 */
	private void beforeRemove(Long id) throws Exception{
		//...
	}
	
	
}
