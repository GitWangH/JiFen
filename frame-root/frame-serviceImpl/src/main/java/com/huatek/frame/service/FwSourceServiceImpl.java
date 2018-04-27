package com.huatek.frame.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.FieldValues;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.dao.FwAccountRoleDao;
import com.huatek.frame.dao.FwRoleSourceDao;
import com.huatek.frame.dao.FwSourceDao;
import com.huatek.frame.dao.model.FwSource;
import com.huatek.frame.service.dto.SourceDto;

/**
 * @Description: 资源(菜单)服务实现类
 * @author caojun1@hisense.com
 * @date 2016年1月13日 下午12:39:21
 * @version V1.0
 */
@Service("fwSourceServiceImpl")
@Transactional
public class FwSourceServiceImpl implements FwSourceService {

	private static final Logger log = LoggerFactory
			.getLogger(FwSourceServiceImpl.class);
	private static final Map<String, String> dictionaryMap = new HashMap<String, String>();
	private static final BeanCopy beanToDto = BeanCopy.getInstance();
	private static final BeanCopy dtoToBean = BeanCopy.getInstance();
	static {
		dictionaryMap.put("1", "是");
		dictionaryMap.put("-1", "否");
		beanToDto
				.addFieldValuesMap(new FieldValues("isMenu", dictionaryMap),
						"isMenuStr")
				.addFieldValuesMap(new FieldValues("isShow", dictionaryMap),
						"isShowStr").addFieldMap("sourceName", "label")
				.addFieldMap("parent.sourceName", "parentName")
				.addFieldMap("parent.id", "parentId")//
				.addFieldMap("orgLevel", "level")
				.addFieldMap("sourceName", "title")
				.addFieldMap("sourceUrl", "url")
				.addFieldMap("sourceTemplate", "view");

		dtoToBean.addFieldMap("title", "sourceName")
				.addFieldMap("view", "sourceTemplate")
				.addFieldMap("url", "sourceUrl")
				.addFieldMap("parentId", "parent")
				.addFieldMap("systemid", "system");
	}

	@Autowired
	@Qualifier("fwSourceDao")
	private FwSourceDao sourceDao;
	@Autowired
	private FwAccountRoleDao fwAccountdao;
	@Autowired
	FwRoleSourceDao fwRoleSourceDao;

	/**
	 * 加载系统资源Map
	 */
	@PostConstruct
	public void init() {

	}


//	@CacheFlush(factor = SourceDto.class)
	@Override
	public void saveMenu(SourceDto sourceDto) {
		log.debug("save source @" + sourceDto);
		// 如果上级菜单没有填，则需给一个默认值
		if (sourceDto.getParentId() == null) {
			sourceDto.setParentId(0L);
		}
		FwSource model = dtoToBean.convert(sourceDto, FwSource.class);
		//
		beforeSave(sourceDto.getParentId(), model);
		sourceDao.persistent(model);
		/*
		 * sourceDao.flush(); init();
		 */
		log.debug("saved source id is @" + model.getId());
		// updateMenu(model.getId(),sourceDto);
	}

	/**
	 * 
	 * @param sourceDto
	 * @param model
	 */
	private void beforeSave(Long parentId, FwSource model) {
		orgLevel(parentId, model);
	}

	/**
	 * @param parentId
	 * @param model
	 */
	private void orgLevel(Long parentId, FwSource model) {
		/** orglevel、levels */
		if (parentId != null) {
			FwSource parent = this.getMenuById_(parentId);
			model.setOrgLevel(parent.getOrgLevel() + 1);
			FwSource.copyLevels(parent, model);
		} else {
			model.setOrgLevel(1);
			model.setLevel1(model.getId());
		}
	}
//	@CacheMethod(factor = FwSource.class, id="#id")
	@Override
	public void deleteMenu(Long id) {
		Assert.notNull(id, "菜单ID不能为空");
		log.debug("delete source by id@" + id);
		// 采用数据库级联删除
		sourceDao.deleteMenuAndSubMenu(id);
	}
//	@CacheMethod(factor = FwSource.class, id="#id")
	@Override
	public SourceDto getMenuById(Long id) {
		return beanToDto.convert(sourceDao.getByKey(id), SourceDto.class);
	}
//	@CacheFlush(factor = {FwSource.class, SourceDto.class }, id="#id")
	@Override
	public void updateMenu(Long id, SourceDto sourceDto) {
		Assert.notNull(id, "保存菜单ID不能为空");
		log.debug("save source by id@" + id);
		if (sourceDto.getParentId() == null) {
			sourceDto.setParentId(0L);
		}
		FwSource fwMenu = dtoToBean.convert(sourceDto, FwSource.class);
		fwMenu.setId(id);
		orgLevel(sourceDto.getParentId(), fwMenu);
		sourceDao.update(fwMenu);
		/***
		 * 初始化菜单的level数据.
		 */
		/*
		 * for(FwSource fwSource : fwSourceMap.values()){ FwSource parent =
		 * fwSource.getParent(); int i=1; while(parent != null){ parent =
		 * parent.getParent(); i++; } parent = fwSource.getParent(); for(int
		 * k=10; k>0;k--){ try { Method method =
		 * FwSource.class.getMethod("setLevel"+k, new Class[]{int.class});
		 * if(k>i){ method.invoke(fwSource, 0); }else if(k==i){
		 * method.invoke(fwSource, fwSource.getId().intValue()); }else
		 * if(parent!=null){ method.invoke(fwSource, parent.getId().intValue());
		 * parent = parent.getParent(); }else{ method.invoke(fwSource, 0); } }
		 * catch (Exception e) { e.printStackTrace(); } }
		 * sourceDao.update(fwSource); }
		 */
	}
	@Override
	public DataPage<SourceDto> getAllMenu(QueryPage queryPage) {
		return beanToDto.convertPage(sourceDao.getAllMenu(queryPage),
				SourceDto.class);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
//	@CacheMethod(factor = FwSource.class, id="#id")
	private FwSource getMenuById_(Long id) {
		Assert.notNull(id, "菜单ID不能为空");
		log.debug("get source by id@" + id);
		FwSource fwMenu = sourceDao.findById(id);
		return fwMenu;
	}

//	@CacheMethod(factors = {FwSource.class, FwSystem.class})
	@Override
	public List<SourceDto> getAllMenu() {
		List<SourceDto> dtos = beanToDto.convertList(
				sourceDao.findAllMenu(), SourceDto.class);
		return dtos;
	}

	/*// @Cacheable("systemCache")
	@Override
	public List<SourceDto> getAllSource() {
		List<SourceDto> dtos = beanToDto.convertList(sourceDao.findAllSource(),
				SourceDto.class);
		return dtos;
	}
*/
//	@CacheMethod(factor = FwSource.class, id="#fwSourceId")
	@Override
	public SourceDto getFwSourceById(Long fwSourceId) {
		return beanToDto.convert(sourceDao.findById(fwSourceId),
				SourceDto.class);
	}






//	@CacheMethod(factors = {FwAccountRole.class, FwSource.class, FwSystem.class})
	@Override
	public List<SourceDto> getAllMenuByUser() {
		List<FwSource> fwSourceList = sourceDao.getAllSource(1, UserUtil.getUser().getId());
		return beanToDto.convertList(fwSourceList, SourceDto.class);
	}
	
//	@CacheMethod(factors = {FwSource.class, FwSystem.class})
	@Override
	public List<SourceDto> getAllSourceByRoleCode(List<String> roleCode){
		List<FwSource> fwSourceList = sourceDao.getAllSourceByRoleCode(roleCode);
		return beanToDto.convertList(fwSourceList, SourceDto.class);
	}

//	@CacheMethod(factor =FwSource.class, id="@return.getId()")
	@Override
	public SourceDto findMenuByUrl(String url) {
		List<FwSource> fwSourceList = sourceDao.findMenuByUrl(url);
		List<SourceDto> list = beanToDto.convertList(fwSourceList,
				SourceDto.class);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}


	@Override
	public List<SourceDto> getAllSourceByRoleCode(List<String> roleCodes,
			Long tenantId) {
		List<FwSource> fwSourceList = sourceDao.getAllSourceByRoleCode(roleCodes, tenantId);
		return beanToDto.convertList(fwSourceList, SourceDto.class);
	}


	@Override
	public List<SourceDto> getAllSource() {
		List<FwSource> fwSourceList =  sourceDao.getAllSource(0, null);
		return beanToDto.convertList(fwSourceList, SourceDto.class);
	}


	@Override
	public List<SourceDto> getAllUserSource() {
		List<FwSource> fwSourceList =  sourceDao.getAllSource(0, UserUtil.getUser().getId());
		return beanToDto.convertList(fwSourceList, SourceDto.class);
	}


	@Override
	public List<SourceDto> getAll() {
		List<FwSource> fwSourceList =  sourceDao.getAll();
		return beanToDto.convertList(fwSourceList, SourceDto.class);
	}

}
