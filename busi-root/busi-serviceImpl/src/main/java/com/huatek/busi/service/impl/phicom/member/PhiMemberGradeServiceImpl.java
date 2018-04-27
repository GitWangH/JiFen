package com.huatek.busi.service.impl.phicom.member;


import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.member.PhiMemberGradeDao;
import com.huatek.busi.dto.phicom.member.PhiMemberGradeDto;
import com.huatek.busi.model.phicom.member.PhiMemberGrade;
import com.huatek.busi.model.phicom.product.PhiProduct;
import com.huatek.busi.service.phicom.member.PhiMemberGradeService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiMemberGradeServiceImpl")
@Transactional
public class PhiMemberGradeServiceImpl implements PhiMemberGradeService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiMemberGradeServiceImpl.class);
	
	@Autowired
	PhiMemberGradeDao phiMemberGradeDao;
	
	@Override
	public void savePhiMemberGradeDto(PhiMemberGradeDto entityDto)  {
		log.debug("save phiMemberGradeDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiMemberGrade entity = BeanCopy.getInstance().convert(entityDto, PhiMemberGrade.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		entity.setConditionsMeet("<= 经验积分 <");
		//进行持久化保存
		phiMemberGradeDao.persistentPhiMemberGrade(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiMemberGradeDto getPhiMemberGradeDtoById(Long id) {
		log.debug("get phiMemberGrade by id@" + id);
		PhiMemberGrade entity = phiMemberGradeDao.findPhiMemberGradeById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiMemberGradeDto entityDto = BeanCopy.getInstance().convert(entity, PhiMemberGradeDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiMemberGrade(Long id, PhiMemberGradeDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiMemberGrade entity = phiMemberGradeDao.findPhiMemberGradeById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		entity.setConditionsMeet("<= 经验积分 <");
		//进行持久化保存
		phiMemberGradeDao.persistentPhiMemberGrade(entity);
	}
	
	
	
	@Override
	public void deletePhiMemberGrade(Long id) {
		log.debug("delete phiMemberGrade by id@" + id);
		beforeRemove(id);
		PhiMemberGrade entity = phiMemberGradeDao.findPhiMemberGradeById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiMemberGradeDao.deletePhiMemberGrade(entity);
	}
	
	@Override
	public DataPage<PhiMemberGradeDto> getAllPhiMemberGradePage(QueryPage queryPage) {
		DataPage<PhiMemberGrade> dataPage = phiMemberGradeDao.getAllPhiMemberGrade(queryPage);
		DataPage<PhiMemberGradeDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiMemberGradeDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiMemberGradeDto> getAllPhiMemberGradeDto() {
		List<PhiMemberGrade> entityList = phiMemberGradeDao.findAllPhiMemberGrade();
		List<PhiMemberGradeDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiMemberGradeDto.class);
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
	* @param    phiMemberGradeDto
	* @param    phiMemberGrade
	* @return  void    
	* @
	*/
	private void beforeSave(PhiMemberGradeDto entityDto, PhiMemberGrade entity) {
		BigDecimal Zero = new BigDecimal("0");
		if(StringUtils.isNotBlank(entityDto.getMemberGrade())){
			if (entityDto.getMemberGrade().length() > 30) {
				throw new BusinessRuntimeException("会员等级名称不能大于30个字符", "-1");
			}
			/*名称：手动输入，必填，该名称在同一父级下唯一，最多20个字符。*/
			List<PhiMemberGrade> gradeList = phiMemberGradeDao.findPhiMemberGradeBygrade(entityDto.getMemberGrade());
			if(gradeList != null&&gradeList.size() > 0){
			     throw new BusinessRuntimeException("等级名称【"+entityDto.getMemberGrade()+"】已存在", "-1");
			 }
		}else{
			throw new BusinessRuntimeException("等级名称不能为空", "-1");
		}
		
		/* 积分下限 scoreMin < 0 */
		if (null == entityDto.getScoreMax() ||  entityDto.getScoreMax().compareTo(Zero) == -1 ) {
			throw new BusinessRuntimeException("积分上限不能小于0", "-1");
		}
		/* scoreMin < 0 */
		if(null == entityDto.getScoreMin() || entityDto.getScoreMin().compareTo(Zero) == -1 ){
			throw new BusinessRuntimeException("积分上限不能小于0", "-1");
		}
		/* scoreMax < scoreMin  或者 scoreMax = scoreMin 积分上限必须大于积分上限 */
		if(entityDto.getScoreMin().compareTo(entityDto.getScoreMax()) == 1 || entityDto.getScoreMin().compareTo(entityDto.getScoreMax()) == 1 ){
			throw new BusinessRuntimeException("积分上限不能小于积分下限", "-1");
		}	

	}
}
