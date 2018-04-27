package com.huatek.busi.service.impl.quality;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.quality.BusiQualitySeclusionEngineerFileDao;
import com.huatek.busi.dto.quality.BusiQualityFileDto;
import com.huatek.busi.dto.quality.BusiQualitySeclusionEngineerFileDto;
import com.huatek.busi.model.quality.BusiQualitySeclusionEngineerFile;
import com.huatek.busi.service.quality.BusiQualitySeclusionEngineerFileService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.DTOUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * 隐蔽工程上传文件service 实现
 * @author rocky_wei
 */
@Service("busiQualitySeclusionEngineerFileServiceImpl")
@Transactional
public class BusiQualitySeclusionEngineerFileServiceImpl implements BusiQualitySeclusionEngineerFileService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiQualitySeclusionEngineerFileServiceImpl.class);
	
	@Autowired
	BusiQualitySeclusionEngineerFileDao busiQualitySeclusionEngineerFileDao;
	
	@Override
	public void saveBusiQualitySeclusionEngineerFileDto(BusiQualitySeclusionEngineerFileDto entityDto)  {
		log.debug("save busiQualitySeclusionEngineerFileDto @" + entityDto);
		List<BusiQualitySeclusionEngineerFile> busiQualitySeclusionEngineerFileList = null;
		List<BusiQualityFileDto> fileIdList = entityDto.getFileIdList();
		//如果上传文件大于1 批量保存
		if(null != fileIdList && fileIdList.size() != 1 && !fileIdList.isEmpty()){
			busiQualitySeclusionEngineerFileList = new ArrayList<BusiQualitySeclusionEngineerFile>();
			for(BusiQualityFileDto fileDto: fileIdList){
				BusiQualitySeclusionEngineerFile entity = BeanCopy.getInstance().addFieldMap("tendersBranchId", "tendersBranch")//这里map的key要写model里面的子对象名，不能写子对象的id
						.convert(entityDto, BusiQualitySeclusionEngineerFile.class);
				entity.setFileId(fileDto.getFileId());
				entity.setFileName(fileDto.getName());
				busiQualitySeclusionEngineerFileList.add(entity);
			}
			busiQualitySeclusionEngineerFileDao.batchSave(busiQualitySeclusionEngineerFileList,busiQualitySeclusionEngineerFileList.size());
		} else if(null != fileIdList && fileIdList.size() == 1){
			//根据页面传进来的值设置保存的值信息
			BusiQualitySeclusionEngineerFile entity = BeanCopy.getInstance().addFieldMap("tendersBranchId", "tendersBranch")//这里map的key要写model里面的子对象名，不能写子对象的id
													.convert(entityDto, BusiQualitySeclusionEngineerFile.class);
			entity.setFileId(fileIdList.get(0).getFileId());
			entity.setFileName(fileIdList.get(0).getName());
			//进行持久化保存
			busiQualitySeclusionEngineerFileDao.persistentBusiQualitySeclusionEngineerFile(entity);
		} else {
			return;
		}
		
		//保存之前操作
		//beforeSave(entityDto, entity);
		//
		//log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public BusiQualitySeclusionEngineerFileDto getBusiQualitySeclusionEngineerFileDtoById(Long id) {
		log.debug("get busiQualitySeclusionEngineerFile by id@" + id);
		BusiQualitySeclusionEngineerFile entity = busiQualitySeclusionEngineerFileDao.findBusiQualitySeclusionEngineerFileById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		BusiQualitySeclusionEngineerFileDto entityDto = DTOUtils.map(entity, BusiQualitySeclusionEngineerFileDto.class);
		return entityDto;
	}
	
	@Override
	public void updateBusiQualitySeclusionEngineerFile(Long id, BusiQualitySeclusionEngineerFileDto entityDto) {
		log.debug("upadte entityDto by id@" + id);
		BusiQualitySeclusionEngineerFile entity = busiQualitySeclusionEngineerFileDao.findBusiQualitySeclusionEngineerFileById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		busiQualitySeclusionEngineerFileDao.persistentBusiQualitySeclusionEngineerFile(entity);
	}
	
	
	
	@Override
	public void deleteBusiQualitySeclusionEngineerFile(Long id) {
		log.debug("delete busiQualitySeclusionEngineerFile by id@" + id);
		beforeRemove(id);
		BusiQualitySeclusionEngineerFile entity = busiQualitySeclusionEngineerFileDao.findBusiQualitySeclusionEngineerFileById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiQualitySeclusionEngineerFileDao.deleteBusiQualitySeclusionEngineerFile(entity);
	}
	
	@Override
	public DataPage<BusiQualitySeclusionEngineerFileDto> getAllBusiQualitySeclusionEngineerFilePage(QueryPage queryPage) {
		List<QueryParam> queryParam = queryPage.getQueryParamList();
		if(queryParam!=null && queryParam.size()>0){
			for(QueryParam param : queryParam){
				if("orgId".equals(param.getField())){
					param.setField("tendersBranch.busiFwOrg.id");
					break;
				}
			}
		}
		DataPage<BusiQualitySeclusionEngineerFile> dataPage = busiQualitySeclusionEngineerFileDao.getAllBusiQualitySeclusionEngineerFile(queryPage);
		DataPage<BusiQualitySeclusionEngineerFileDto> dataPageDto = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss")//
								.addFieldMap("tendersBranch.busiFwOrg.id", "orgId").addFieldMap("tendersBranch.busiFwOrg.name", "orgName")//
								.addFieldMap("tendersBranch.tendersBranchName", "tendersBranchName").convertPage(dataPage, BusiQualitySeclusionEngineerFileDto.class);
		return dataPageDto;
	}
	
	@Override
	public List<BusiQualitySeclusionEngineerFileDto> getAllBusiQualitySeclusionEngineerFileDto() {
		List<BusiQualitySeclusionEngineerFile> entityList = busiQualitySeclusionEngineerFileDao.findAllBusiQualitySeclusionEngineerFile();
		List<BusiQualitySeclusionEngineerFileDto> dtos = DTOUtils.mapList(entityList, BusiQualitySeclusionEngineerFileDto.class);
		return dtos;
	}
	
	/** 
	* @Title: beforeRemove 
	* @Description:  删除之前的操作 
	* @param    id
	* @return  void    
	* @throws  Exception
	*/
	private void beforeRemove(Long id) {

	}
	
	/** 
	* @Title: beforeSave 
	* @Description:  保存之前设置保存对象信息 
	* @param    busiQualitySeclusionEngineerFileDto
	* @param    busiQualitySeclusionEngineerFile
	* @return  void    
	* @
	*/
	private void beforeSave(BusiQualitySeclusionEngineerFileDto entityDto, BusiQualitySeclusionEngineerFile entity) {

	}


	@Override
	public List<BusiQualitySeclusionEngineerFileDto> getSeclEngListByTendersId(Long tId) {
		List<BusiQualitySeclusionEngineerFile> list = busiQualitySeclusionEngineerFileDao.findBusiQualitySeclusionEngineerFileByCondition("tendersBranch.id",tId.toString());
		return BeanCopy.getInstance().addFieldMap("tendersBranch.tendersBranchCode", "tendersBranchCode").convertList(list, BusiQualitySeclusionEngineerFileDto.class);
		
	}
}
