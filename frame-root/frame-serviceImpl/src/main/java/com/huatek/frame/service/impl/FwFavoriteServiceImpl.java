package com.huatek.frame.service.impl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwFavoriteDao;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.dto.FwFavoriteDto;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.model.FwFavorite;
import com.huatek.frame.service.FwFavoriteService;

@Service("fwFavoriteServiceImpl")
@Transactional
public class FwFavoriteServiceImpl implements FwFavoriteService {
	
	private static final Logger log = LoggerFactory.getLogger(FwFavoriteServiceImpl.class);
	
	@Autowired
	FwFavoriteDao fwFavoriteDao;
	
	@Override
	public void saveFwFavoriteDto(FwFavoriteDto entityDto)  {
		//根据页面传进来的值设置保存的值信息
		FwFavorite entity = BeanCopy.getInstance().convert(entityDto, FwFavorite.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		fwFavoriteDao.persistentFwFavorite(entity);
	}
	
	
	@Override
	public FwFavoriteDto getFwFavoriteDtoById(Long id) {
		FwFavorite entity = fwFavoriteDao.findFwFavoriteById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		FwFavoriteDto entityDto = BeanCopy.getInstance().convert(entity, FwFavoriteDto.class);
		return entityDto;
	}
	
	@Override
	public void updateFwFavorite(Long id, FwFavoriteDto entityDto) {
		FwFavorite entity = fwFavoriteDao.findFwFavoriteById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		fwFavoriteDao.persistentFwFavorite(entity);
	}
	
	
	
	@Override
	public void deleteFwFavorite(Long id) {
		beforeRemove(id);
		FwFavorite entity = fwFavoriteDao.findFwFavoriteById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		fwFavoriteDao.deleteFwFavorite(entity);
	}
	
	@Override
	public DataPage<FwFavoriteDto> getAllFwFavoritePage(QueryPage queryPage) {
		DataPage<FwFavorite> dataPage = fwFavoriteDao.getAllFwFavorite(queryPage);
		DataPage<FwFavoriteDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, FwFavoriteDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<FwFavoriteDto> getAllFwFavoriteDto() {
		List<FwFavorite> entityList = fwFavoriteDao.findAllFwFavorite();
		List<FwFavoriteDto> dtos = BeanCopy.getInstance().convertList(entityList, FwFavoriteDto.class);
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
	* @param    fwFavoriteDto
	* @param    fwFavorite
	* @return  void    
	* @
	*/
	private void beforeSave(FwFavoriteDto entityDto, FwFavorite entity) {

	}


	@Override
	public List<FwFavoriteDto> getUserFavourite(Long id) {
		List<FwFavorite> fwFavorites = fwFavoriteDao.getUserFwFavorite(id);
		return BeanCopy.getInstance().addFieldMap("source.id", "sourceId").addFieldMap("source.sourceName", "sourceName").addFieldMap("source.icon", "icon").addFieldMap("source.controller", "controller").addFieldMap("source.sourceTemplate", "view").addFieldMap("source.sourceUrl", "url").convertList(fwFavorites, FwFavoriteDto.class);
	}


	@Override
	public void saveFavouriteSource(Long acctId, String[] dataArr) {
		
		// 如果没有分配任何数据，那么删除掉对应角色的所分配的菜单数据.
		if (dataArr.length == 0) {
			fwFavoriteDao.deleteFwFavoriteByAcctId(acctId);
		    return;
		}
		Set<String> postDataSet = new HashSet<String>();
		for (int i = 0; i < dataArr.length; i++) {
		    postDataSet.add(dataArr[i]);
		}
		// 获取用户所有已添加的常用功能
		List<FwFavorite> fwFavouriteList = fwFavoriteDao.getUserFwFavorite(acctId);
		Map<String, FwSource> roleFwSourceMap = new HashMap<String, FwSource>();
		int size = fwFavouriteList.size();
		for (int i = 0; i < size; i++) {
		    String sourceId = fwFavouriteList.get(i).getSource().getId().toString();
		    // 本次提交没有包含该数据，则删除掉
		    if (!postDataSet.contains(sourceId)) {
		    	fwFavoriteDao.deleteFwFavorite(fwFavouriteList.get(i));
		    	continue;
		    }
		    roleFwSourceMap.put(sourceId, fwFavouriteList.get(i).getSource());
		}
		Iterator<String> it = postDataSet.iterator();
		while (it.hasNext()) {
		    String sourceId = it.next();
		    if (roleFwSourceMap.get(sourceId) == null) {
				FwSource fwSource = new FwSource();
				fwSource.setId(Long.valueOf(sourceId));
				FwFavorite fwFavorite = new FwFavorite();
				fwFavorite.setAcctId(acctId);
				fwFavorite.setSource(fwSource);
				fwFavoriteDao.saveOrUpdateFwFavorite(fwFavorite);
		    }
		}
		
	}
}
