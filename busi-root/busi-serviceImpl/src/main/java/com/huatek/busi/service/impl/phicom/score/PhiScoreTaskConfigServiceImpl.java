package com.huatek.busi.service.impl.phicom.score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.score.PhiScoreConfigRuleDao;
import com.huatek.busi.dao.phicom.score.PhiScoreTaskConfigDao;
import com.huatek.busi.dto.phicom.score.PhiScoreConfigRuleDto;
import com.huatek.busi.dto.phicom.score.PhiScoreTaskConfigDto;
import com.huatek.busi.model.phicom.score.PhiScoreConfigRule;
import com.huatek.busi.model.phicom.score.PhiScoreTaskConfig;
import com.huatek.busi.service.phicom.score.PhiScoreTaskConfigService;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("phiScoreTaskConfigServiceImpl")
@Transactional
public class PhiScoreTaskConfigServiceImpl implements PhiScoreTaskConfigService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiScoreTaskConfigServiceImpl.class);
	
	@Autowired
	PhiScoreTaskConfigDao phiScoreTaskConfigDao;
	
	@Autowired
	PhiScoreConfigRuleDao phiScoreConfigRuleDao;
	
    @Autowired
	private CmdFileMangerService cmdFileMangerService;
	@Override
	public void savePhiScoreTaskConfigDto(PhiScoreTaskConfigDto entityDto)  {
		log.debug("save phiScoreTaskConfigDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiScoreTaskConfig entity = BeanCopy.getInstance().convert(entityDto, PhiScoreTaskConfig.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiScoreTaskConfigDao.persistentPhiScoreTaskConfig(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@SuppressWarnings("null")
	@Override
	public PhiScoreTaskConfigDto getPhiScoreTaskConfigDtoById(Long id) {
		log.debug("get phiScoreTaskConfig by id@" + id);
		PhiScoreTaskConfig entity = phiScoreTaskConfigDao.findPhiScoreTaskConfigById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
		if(null==entity.getScoreConfigRule()){
			throw new ResourceNotFoundException(id);
		}
		PhiScoreTaskConfigDto entityDto = BeanCopy.getInstance().convert(entity, PhiScoreTaskConfigDto.class);
		Set<PhiScoreConfigRule> PhiScoreConfigRule = entity.getScoreConfigRule();
		Iterator<PhiScoreConfigRule> itruleIterator= PhiScoreConfigRule.iterator();
		List<String> thirdAccount = new ArrayList<String>();
		String[] strSplitText = null;
		Set<PhiScoreConfigRuleDto> dtoset = new HashSet<PhiScoreConfigRuleDto>();
//		List<PhiScoreConfigRuleDto> ruleDtolists = new ArrayList<PhiScoreConfigRuleDto>();
//		Iterator<PhiScoreConfigRuleDto> dtoit= dtoset.iterator();
		while(itruleIterator.hasNext()){
			PhiScoreConfigRule ruleEntityConfigRule = itruleIterator.next();
			PhiScoreConfigRuleDto rlueDto = BeanCopy.getInstance().convert(ruleEntityConfigRule, PhiScoreConfigRuleDto.class);
			if(null!=ruleEntityConfigRule){
				if(StringUtils.isNoneBlank(ruleEntityConfigRule.getExtensing())){
					strSplitText = ruleEntityConfigRule.getExtensing().split("\\|") ;
				}
				/*是否有起始时间进行判断*/
				if(null!=rlueDto){
					if(1==rlueDto.getRuleTimeType()){
						rlueDto.setEndTime(null);
						rlueDto.setStartTime(null);
					}
				}
				dtoset.add(rlueDto);
//				ruleDtolists.add(rlueDto);
			}
			
		}
		if(StringUtils.isNoneBlank(strSplitText)){
			for(String str:strSplitText){
				thirdAccount.add(str);
			}
		}
		entityDto.setThirdAccount(thirdAccount);
//		List<String> thirdAccount = entityDto.getThirdAccount();
//		String thirdAccountStr = StringUtils.join(thirdAccount, "|");
//		Set<PhiScoreConfigRule> entitySet = entity.getScoreConfigRule();
		Set<PhiScoreConfigRuleDto> dt1oset=new HashSet<PhiScoreConfigRuleDto>();
		dt1oset=this.sortByValue(dtoset);
//	
//		Iterator<PhiScoreConfigRuleDto> sss = dt1oset.iterator();
//		while(sss.hasNext()){
//			 System.out.println(sss.next().getId());
//		}
		
		entityDto.setScoreConfigRule(dt1oset);
//		entityDto.setScoreConfigRuleList(ruleDtolists);
		return entityDto;
	}
	private Set<PhiScoreConfigRuleDto> sortByValue(Set<PhiScoreConfigRuleDto> set){  
        List<PhiScoreConfigRuleDto> setList= new ArrayList<PhiScoreConfigRuleDto>(set);  
        Collections.sort(setList, new Comparator<PhiScoreConfigRuleDto>() {  
            @Override  
            public int compare(PhiScoreConfigRuleDto o1, PhiScoreConfigRuleDto o2) {  
            	
            	if ( o1.getId()>o2.getId())  
                {  
                    return 1;  
                }  
                else if ( o1.getId()<o2.getId())  
                {  
                    return -1;  
                }  
                else  
                {  
                    return 0;  
                }  
            	
                
            }  
              
        });  
        set = new LinkedHashSet<PhiScoreConfigRuleDto>(setList);//这里注意使用LinkedHashSet  
        return set;  
    } 
	
	@Override
	public void updatePhiScoreTaskConfig(Long id, PhiScoreTaskConfigDto entityDto) {
		log.debug("update entityDto by id@" + id);
		/*解析复选框数组*/
		List<String> thirdAccount = entityDto.getThirdAccount();
		String thirdAccountStr = null;
		if(null!=thirdAccount&&thirdAccount.size()>0){
			 thirdAccountStr = StringUtils.join(thirdAccount, "|");
		}
		
		/*获取要修改的实体*/
		PhiScoreTaskConfig entity = phiScoreTaskConfigDao.findPhiScoreTaskConfigById(id);
		
//		List<PhiScoreConfigRuleDto> list = entityDto.getScoreConfigRuleList();
		Set<PhiScoreConfigRuleDto> dtoSet = entityDto.getScoreConfigRule();
		Set<PhiScoreConfigRule> entitySet = entity.getScoreConfigRule();
		Set<PhiScoreConfigRule> set = new HashSet<PhiScoreConfigRule>();
		Iterator<PhiScoreConfigRule> it= entitySet.iterator();
		Iterator<PhiScoreConfigRuleDto> dtoit= dtoSet.iterator();
		int RuleTimeType = 1;
		while(it.hasNext()){
			PhiScoreConfigRule ruleEntity=it.next();
			PhiScoreConfigRuleDto ruleDto=dtoit.next();
			BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd HH:mm:ss").mapIgnoreId(ruleDto, ruleEntity);
			ruleEntity.setExtensing(thirdAccountStr);
			set.add(ruleEntity);
			RuleTimeType = ruleEntity.getRuleTimeType();
		}
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		 entity.setTaskTimeType(RuleTimeType);
		 entity.setScoreConfigRule(entitySet);
		 UserInfo userInfo = ThreadLocalClient.get().getOperator();
		 entity.setOperationPerson(userInfo.getUserName());
		 Date now = new Date(); 
//		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		 String hehe = dateFormat.format( now ); 
		 entity.setLastOperationTime(now);
		//进行持久化保存
		phiScoreTaskConfigDao.saveOrUpdatePhiScoreTaskConfig(entity);
		//	保存明细数据
	}
	
	
	
	@Override
	public void deletePhiScoreTaskConfig(Long id) {
		log.debug("delete phiScoreTaskConfig by id@" + id);
		beforeRemove(id);
		PhiScoreTaskConfig entity = phiScoreTaskConfigDao.findPhiScoreTaskConfigById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiScoreTaskConfigDao.deletePhiScoreTaskConfig(entity);
	}
	
	@Override
	public DataPage<PhiScoreTaskConfigDto> getAllPhiScoreTaskConfigPage(QueryPage queryPage) {
		DataPage<PhiScoreTaskConfig> dataPage = phiScoreTaskConfigDao.getAllPhiScoreTaskConfig(queryPage);
		DataPage<PhiScoreTaskConfigDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiScoreTaskConfigDto.class);	
		if(null!=datPageDto){
			for(int i=0;i<datPageDto.getContent().size();i++){
				PhiScoreTaskConfigDto entity = datPageDto.getContent().get(i);
				String img = dataPage.getContent().get(i).getImage();
				if(img != null){
			       List<CmdFileMangerDto> imsges = cmdFileMangerService.getCmdFileDtoByBusiId(img);
			       if(imsges.size()>0){
			    	   datPageDto.getContent().get(i).setImageApp(imsges.get(0).getFilePath());
			       }
				}
				if(null!=entity){					
					datPageDto.getContent().get(i).setCode(Long.valueOf(i+1));		   
				}
			  //设置时间段
				if("forum".equals(dataPage.getContent().get(i).getTaskType())){
					datPageDto.getContent().get(i).setTime("--");
				}else{
					if(1==dataPage.getContent().get(i).getTaskTimeType()){
						datPageDto.getContent().get(i).setTime("永久");
					}else{
						if(dataPage.getContent().get(i).getScoreConfigRule().size()>0){
							Iterator<PhiScoreConfigRule> it= dataPage.getContent().get(i).getScoreConfigRule().iterator();
							while(it.hasNext()){
								PhiScoreConfigRule ruleEntity=it.next();
								StringBuilder showTime = new StringBuilder();
								if(null!=ruleEntity.getStartTime()){
									String startTime = ruleEntity.getStartTime().toString();
									String endTime = ruleEntity.getEndTime().toString();
									showTime.append(startTime);
									showTime.append("-");
									showTime.append(endTime);
								}else{
									showTime.append("-");
								}
								datPageDto.getContent().get(i).setTime(showTime.toString());
							}
							
						}
					}
				}
				
			}
		}
		
		return datPageDto;
	}
	
	@Override
	public List<PhiScoreTaskConfigDto> getAllPhiScoreTaskConfigDto() {
		List<PhiScoreTaskConfig> entityList = phiScoreTaskConfigDao.findAllPhiScoreTaskConfig();
		List<PhiScoreTaskConfigDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiScoreTaskConfigDto.class);
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
	* @param    phiScoreTaskConfigDto
	* @param    phiScoreTaskConfig
	* @return  void    
	* @
	*/
	private void beforeSave(PhiScoreTaskConfigDto entityDto, PhiScoreTaskConfig entity) {

	}


	@Override
	public void editPhiScoreswitch(Long id,String  taskSwitch) {
		/*获取要修改的实体*/
		PhiScoreTaskConfig entity = phiScoreTaskConfigDao.findPhiScoreTaskConfigById(id);
		entity.setTaskSwitch(taskSwitch);
		phiScoreTaskConfigDao.saveOrUpdatePhiScoreTaskConfig(entity);
	}
}
