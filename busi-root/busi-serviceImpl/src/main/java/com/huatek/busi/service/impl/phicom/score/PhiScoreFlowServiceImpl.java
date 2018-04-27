package com.huatek.busi.service.impl.phicom.score;

import java.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.phicom.score.PhiScoreFlowDao;
import com.huatek.busi.dto.phicom.score.PhiScoreFlowDto;
import com.huatek.busi.model.phicom.score.PhiScoreFlow;
import com.huatek.busi.model.phicom.score.PhiYearLedger;
import com.huatek.busi.service.phicom.score.PhiScoreFlowService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.task.service.AbstractJob;
import com.huatek.task.service.TaskContext;

@Service("phiScoreFlowServiceImpl")
@Transactional
public class PhiScoreFlowServiceImpl extends AbstractJob implements PhiScoreFlowService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiScoreFlowServiceImpl.class);
	
	@Autowired
	PhiScoreFlowDao phiScoreFlowDao;
	
	@Override
	public void savePhiScoreFlowDto(PhiScoreFlowDto entityDto)  {
		log.debug("save phiScoreFlowDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiScoreFlow entity = BeanCopy.getInstance().convert(entityDto, PhiScoreFlow.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiScoreFlowDao.persistentPhiScoreFlow(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	@Override
	public void savePhiScoreFlow(PhiScoreFlow entity)  {
		//进行持久化保存
		if(entity.getEnableTime()==null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date strtodate = sdf.parse("1900-01-01 00:00:00");
				entity.setEnableTime(strtodate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		phiScoreFlowDao.saveOrUpdatePhiScoreFlow(entity);
	}
	
	@Override
	public PhiScoreFlowDto getPhiScoreFlowDtoById(Long id) {
		log.debug("get phiScoreFlow by id@" + id);
		PhiScoreFlow entity = phiScoreFlowDao.findPhiScoreFlowById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiScoreFlowDto entityDto = BeanCopy.getInstance().convert(entity, PhiScoreFlowDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiScoreFlow(Long id, PhiScoreFlowDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiScoreFlow entity = phiScoreFlowDao.findPhiScoreFlowById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiScoreFlowDao.persistentPhiScoreFlow(entity);
	}
	
	
	
	@Override
	public void deletePhiScoreFlow(Long id) {
		log.debug("delete phiScoreFlow by id@" + id);
		beforeRemove(id);
		PhiScoreFlow entity = phiScoreFlowDao.findPhiScoreFlowById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiScoreFlowDao.deletePhiScoreFlow(entity);
	}
	
	@Override
	public DataPage<PhiScoreFlowDto> getAllPhiScoreFlowPage(QueryPage queryPage) {
		DataPage<PhiScoreFlow> dataPage = phiScoreFlowDao.getAllPhiScoreFlow(queryPage);
		DataPage<PhiScoreFlowDto> datePageDto = BeanCopy.getInstance().convertPage(dataPage, PhiScoreFlowDto.class);
	    //拼接+-
		if(null!=datePageDto.getContent()){
			for(int i=0;i<datePageDto.getContent().size();i++){
				PhiScoreFlowDto scoreflowdto = datePageDto.getContent().get(i);
				StringBuilder sb = new StringBuilder();
				if("gain".equals(scoreflowdto.getScoreType())){
					sb.append("+");
					sb.append(scoreflowdto.getScore());
				}
				if("consume".equals(scoreflowdto.getScoreType())){
					sb.append("-");
					sb.append(scoreflowdto.getScore());
				}
				scoreflowdto.setScore(sb.toString());
					
	      }
	   }
		
		return datePageDto;
	}
	
	@Override
	public List<PhiScoreFlowDto> getAllPhiScoreFlowDto() {
		List<PhiScoreFlow> entityList = phiScoreFlowDao.findAllPhiScoreFlow();
		List<PhiScoreFlowDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiScoreFlowDto.class);
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
	* @param    phiScoreFlowDto
	* @param    phiScoreFlow
	* @return  void    
	* @
	*/
	private void beforeSave(PhiScoreFlowDto entityDto, PhiScoreFlow entity) {

	}

	@Override
	public void excute(TaskContext context) {
		phiScoreFlowDao.expireLastYearScore();
		
	}

	@Override
	public int getSoonFallDueScore(Long memberId) {
		return phiScoreFlowDao.getSoonFallDueScore(memberId);
	}
	
	/**
	 * 获取剩余积分数
	 * @param memberId
	 * @return
	 */
	public int getRemainScore(Long memberId){
		return phiScoreFlowDao.getRemainScore(memberId);
	}


	@Override
	public PhiScoreFlow findPhiScoreFlowByCondition(Long memberId,
			String orderCode) {
		// TODO Auto-generated method stub
		return phiScoreFlowDao.findPhiScoreFlowByCondition(memberId, orderCode);
	}

	@Override
	public List<PhiScoreFlow> findPhiScoreFlowByCondition(Long memberId) {
		// TODO Auto-generated method stub
		return  phiScoreFlowDao.findPhiScoreFlowByCondition(memberId);
	}

	@Override
	public PhiScoreFlow findPhiScoreFlowByRCondition(long memberId,
			String refundCode) {
		// TODO Auto-generated method stub
		return phiScoreFlowDao.findPhiScoreFlowByRCondition(memberId, refundCode);
	}

}
