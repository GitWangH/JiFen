package com.huatek.busi.service.impl.phicom.score;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.score.PhiScoreConfigRuleDao;
import com.huatek.busi.dao.phicom.score.ScoreTaskDao;
import com.huatek.busi.dto.phicom.score.PhiScoreConfigRuleDto;
import com.huatek.busi.model.phicom.score.PhiScoreConfigRule;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.busi.service.phicom.score.PhiScoreConfigRuleService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiScoreConfigRuleServiceImpl")
@Transactional
public class PhiScoreConfigRuleServiceImpl implements PhiScoreConfigRuleService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiScoreConfigRuleServiceImpl.class);
	
	@Autowired
	ScoreTaskDao scoreTaskDao;
	
	@Autowired
	PhiScoreConfigRuleDao phiScoreConfigRuleDao;
	
	@Override
	public void savePhiScoreConfigRuleDto(PhiScoreConfigRuleDto entityDto)  {
		log.debug("save phiScoreConfigRuleDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiScoreConfigRule entity = BeanCopy.getInstance().convert(entityDto, PhiScoreConfigRule.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiScoreConfigRuleDao.persistentPhiScoreConfigRule(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiScoreConfigRuleDto getPhiScoreConfigRuleDtoById(Long id) {
		log.debug("get phiScoreConfigRule by id@" + id);
		PhiScoreConfigRule entity = phiScoreConfigRuleDao.findPhiScoreConfigRuleById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiScoreConfigRuleDto entityDto = BeanCopy.getInstance().convert(entity, PhiScoreConfigRuleDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiScoreConfigRule(Long id, PhiScoreConfigRuleDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiScoreConfigRule entity = phiScoreConfigRuleDao.findPhiScoreConfigRuleById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiScoreConfigRuleDao.persistentPhiScoreConfigRule(entity);
	}
	
	
	
	@Override
	public void deletePhiScoreConfigRule(Long id) {
		log.debug("delete phiScoreConfigRule by id@" + id);
		beforeRemove(id);
		PhiScoreConfigRule entity = phiScoreConfigRuleDao.findPhiScoreConfigRuleById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiScoreConfigRuleDao.deletePhiScoreConfigRule(entity);
	}
	
	@Override
	public DataPage<PhiScoreConfigRuleDto> getAllPhiScoreConfigRulePage(QueryPage queryPage) {
		DataPage<PhiScoreConfigRule> dataPage = phiScoreConfigRuleDao.getAllPhiScoreConfigRule(queryPage);
		DataPage<PhiScoreConfigRuleDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiScoreConfigRuleDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiScoreConfigRuleDto> getAllPhiScoreConfigRuleDto() {
		List<PhiScoreConfigRule> entityList = phiScoreConfigRuleDao.findAllPhiScoreConfigRule();
		List<PhiScoreConfigRuleDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiScoreConfigRuleDto.class);
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
	* @param    phiScoreConfigRuleDto
	* @param    phiScoreConfigRule
	* @return  void    
	* @
	*/
	private void beforeSave(PhiScoreConfigRuleDto entityDto, PhiScoreConfigRule entity) {

	}


	@Override
	public PhiScoreConfigRuleDto getConfigRuleDtoForApp() {
		String type = "forCheckinPoints";
		PhiScoreTaskConfig phiScoreConfig = scoreTaskDao.findPhiScoreTaskConfigByCondition(type);
		PhiScoreConfigRule phiScoreConfigRule = phiScoreConfigRuleDao.findphiScoreConfigRuleByStcId(phiScoreConfig.getId());
		PhiScoreConfigRuleDto phiScoreConfigRuleDto =BeanCopy.getInstance().convert(phiScoreConfigRule, PhiScoreConfigRuleDto.class);
		return phiScoreConfigRuleDto;
	}
}
