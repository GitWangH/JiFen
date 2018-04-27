package com.huatek.busi.service.impl.pluspagelayout;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.pluspageLayout.PhiPlusPagelaoutDetailDao;
import com.huatek.busi.dao.pluspageLayout.PhiPlusPagelayoutDao;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelaoutDetailDto;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelayoutDto;
import com.huatek.busi.dto.pluspagelayout.PhiPlusPagelayoutshowDto;
import com.huatek.busi.model.pluspageLayout.PhiPlusPagelaoutDetail;
import com.huatek.busi.model.pluspageLayout.PhiPlusPagelayout;
import com.huatek.busi.service.pluspagelayout.PhiPlusPagelayoutService;
import com.huatek.cmd.dto.CmdFileMangerDto;
import com.huatek.cmd.dto.CmdMachineDto;
import com.huatek.cmd.service.CmdFileMangerService;
import com.huatek.frame.authority.util.ThreadLocalClient;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.beancopy.ConvertParam;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.page.QueryParam;
import com.huatek.frame.exception.ResourceNotFoundException;
import com.huatek.frame.session.data.UserInfo;

@Service("phiPlusPagelayoutServiceImpl")
@Transactional
public class PhiPlusPagelayoutServiceImpl implements PhiPlusPagelayoutService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiPlusPagelayoutServiceImpl.class);
	
	@Autowired
	PhiPlusPagelayoutDao phiPlusPagelayoutDao;
	@Autowired
	PhiPlusPagelaoutDetailDao phiPlusPagelaoutDetailDao;
	@Autowired
	private CmdFileMangerService cmdFileMangerService;
	@Override
	public void savePhiPlusPagelayoutDto(PhiPlusPagelayoutDto entityDto) {
		//根据页面传进来的值设置保存的值信息
		PhiPlusPagelayout entity = phiPlusPagelayoutDao.findPhiPlusPagelayoutById(entityDto.getId());
		entity.setTitle(entityDto.getTitle());
		entity.setMorelink(entityDto.getMorelink());
		UserInfo userInfo = ThreadLocalClient.get().getOperator();
		entity.setOperationperson(userInfo.getUserName());
		Date currDate = new Date(); 
		entity.setEndoperationtime(currDate);
		phiPlusPagelayoutDao.saveOrUpdatePhiPlusPagelayout(entity);
		
		List<PhiPlusPagelaoutDetail> detailsList = BeanCopy.getInstance().convertList(entityDto.getPhiPlusPagelaoutDetail(), PhiPlusPagelaoutDetail.class);
		phiPlusPagelaoutDetailDao.batchUpdate(detailsList);
		
	}
	
	@Override
	public List<PhiPlusPagelaoutDetailDto> getPhiPlusPagelayoutDtoById(Long id) {
//		PhiPlusPagelayout entity = phiPlusPagelayoutDao.findPhiPlusPagelayoutById(id);
		List<PhiPlusPagelaoutDetail> detailsList = phiPlusPagelaoutDetailDao.getByParentId(id); 
//		PhiPlusPagelayoutDto entityDto = BeanCopy.getInstance().convert(entity, PhiPlusPagelayoutDto.class);
		List<PhiPlusPagelaoutDetailDto> dtoList = BeanCopy.getInstance().convertList(detailsList, PhiPlusPagelaoutDetailDto.class);
//		entityDto.set
		return dtoList;
	}
	
	@Override
	public void updatePhiPlusPagelayout(Long id, PhiPlusPagelayoutDto entityDto) {
		PhiPlusPagelayout entity = phiPlusPagelayoutDao.findPhiPlusPagelayoutById(id);
		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
		//进行持久化保存
		phiPlusPagelayoutDao.persistentPhiPlusPagelayout(entity);
	}
	
	
	
	@Override
	public void deletePhiPlusPagelayout(Long id) {
		beforeRemove(id);
		PhiPlusPagelayout entity = phiPlusPagelayoutDao.findPhiPlusPagelayoutById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiPlusPagelayoutDao.deletePhiPlusPagelayout(entity);
	}
	
	@Override
	public DataPage<PhiPlusPagelayoutDto> getAllPhiPlusPagelayoutPage(QueryPage queryPage) {
/*		QueryParam param = new QueryParam();
		param.setField("id");
		param.setLogic("<=");
		param.setValue("8");
		queryPage.getQueryParamList().add(param);*/
		DataPage<PhiPlusPagelayout> dataPage = phiPlusPagelayoutDao.getAllPhiPlusPagelayout(queryPage);
		DataPage<PhiPlusPagelayoutDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiPlusPagelayoutDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiPlusPagelayoutDto> getAllPhiPlusPagelayoutDto() {
		List<PhiPlusPagelayout> entityList = phiPlusPagelayoutDao.findAllPhiPlusPagelayout();
		List<PhiPlusPagelayoutDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiPlusPagelayoutDto.class);
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
	* @param    phiPlusPagelayoutDto
	* @param    phiPlusPagelayout
	* @return  void    
	* @
	*/
	private void beforeSave(PhiPlusPagelayoutDto entityDto, PhiPlusPagelayout entity) {

	}


	@Override
	public List<PhiPlusPagelayoutDto> getCountByParentId(Long id) {
		List<PhiPlusPagelayout> entityList = phiPlusPagelayoutDao.getCountByParentId(id);
		List<PhiPlusPagelayoutDto> dtos = BeanCopy.getInstance().addConvertParam(ConvertParam.dateFormatPatten, "yyyy-MM-dd").convertList(entityList, PhiPlusPagelayoutDto.class);
		Map<Long,String> map = new HashMap<Long,String>();
//		for(int i=0;i<dtos.size();i++){
//			map.put(dtos.get(i).getId(), dtos.get(i).getPhouuidname());
//		}
//		
//		for(int i=0;i<dtos.size();i++){
//			if(i==0){
//				dtos.get(i).setPhouuidname(map.get(dtos.get(i).getId()));
//				
//			}
//			if(i==1){
//				dtos.get(i).setPhouuidname_1(map.get(dtos.get(i).getId()));
//			}
//		}
		return dtos;
	}

	@Override
	public void reset(Long id) {
		phiPlusPagelaoutDetailDao.reset(id);
	}

	@Override
	public List<PhiPlusPagelayoutshowDto> getAllPhiPlusPagelayoutshowDto() {
		List<PhiPlusPagelayout> phiPlusPagelayouts = phiPlusPagelayoutDao.findAllPhiPlusPagelayout();
		List<PhiPlusPagelayoutshowDto> showDtos = BeanCopy.getInstance().convertList(phiPlusPagelayouts, PhiPlusPagelayoutshowDto.class);
		
		PhiPlusPagelayout phiPlusPagelayout = new PhiPlusPagelayout();
		
		List<PhiPlusPagelaoutDetailDto> phiPlusPagelaoutDetails = new ArrayList<PhiPlusPagelaoutDetailDto>();
		
		if(phiPlusPagelayouts != null && phiPlusPagelayouts.size() > 0){
			for (PhiPlusPagelayoutshowDto phiPlusPagelayoutshowDto : showDtos) {
			
			for (int i = 0; i < phiPlusPagelayouts.size(); i++) {
				phiPlusPagelayout = phiPlusPagelayouts.get(i);
				Long plusPagelayoutId = phiPlusPagelayout.getId();
				List<PhiPlusPagelaoutDetail> pagelaoutDetails =  phiPlusPagelaoutDetailDao.getByParentId(plusPagelayoutId);
				if(pagelaoutDetails != null){
					phiPlusPagelaoutDetails =  BeanCopy.getInstance().convertList(pagelaoutDetails, PhiPlusPagelaoutDetailDto.class);
				}
			}
			phiPlusPagelayoutshowDto.setPhiPlusPagelaoutDetail(phiPlusPagelaoutDetails);
			}
			
		}
		
		return showDtos;
	}

	/***
	 * pc端/app
	 * @param code
	 * @return
	 */
	@Override
	public PhiPlusPagelayoutshowDto getAllPhiplusRightSummary(String code,String clientType) {
		PhiPlusPagelayout plusPagelayout = phiPlusPagelayoutDao.findPhiPlusPagelayoutByCode(code,clientType);
		PhiPlusPagelayoutshowDto showDtos = BeanCopy.getInstance().convert(plusPagelayout, PhiPlusPagelayoutshowDto.class);
		Long parentId = plusPagelayout.getId();
		List<PhiPlusPagelaoutDetail> pagelaoutDetails =  phiPlusPagelaoutDetailDao.getByParentId(parentId);
		List<PhiPlusPagelaoutDetailDto> pagelaoutDetailDtos= BeanCopy.getInstance().convertList(pagelaoutDetails, PhiPlusPagelaoutDetailDto.class);
		if(pagelaoutDetails != null && pagelaoutDetails.size() > 0){
			for (int i = 0; i < pagelaoutDetails.size(); i++) {
				PhiPlusPagelaoutDetail pagelaoutDetail = pagelaoutDetails.get(i);
				List<CmdFileMangerDto> cmdImage = cmdFileMangerService.getCmdFileDtoByBusiId(pagelaoutDetail.getRightPicture());
				if(null!=cmdImage&&cmdImage.size()>0){
					pagelaoutDetailDtos.get(i).setRightPicture(cmdImage.get(0).getFilePath());
				}
				showDtos.setPhiPlusPagelaoutDetail(pagelaoutDetailDtos);
			}
			
		}
		//List<PhiPlusPagelayoutshowDto> showDtos = BeanCopy.getInstance().convertList(pagelaoutDetails, PhiPlusPagelayoutshowDto.class);
		return showDtos;
	}



}
