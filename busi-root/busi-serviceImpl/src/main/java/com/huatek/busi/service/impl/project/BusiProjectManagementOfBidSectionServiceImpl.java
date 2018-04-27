package com.huatek.busi.service.impl.project;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dao.project.BusiProjectManagementOfBidSectionDao;
import com.huatek.busi.dao.project.BusiProjectManagementOfBidSectionDetailDao;
import com.huatek.busi.dao.project.BusiProjectManagementOfBidSectionQueryDao;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDetailDto;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionDto;
import com.huatek.busi.dto.project.BusiProjectManagementOfBidSectionShowDto;
import com.huatek.busi.dto.project.port.BusiProjectManagementOfBidSectionPortDto;
import com.huatek.busi.model.BusiFwOrg;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSection;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionDetail;
import com.huatek.busi.model.project.BusiProjectManagementOfBidSectionShow;
import com.huatek.busi.service.project.BusiProjectManagementOfBidSectionService;
import com.huatek.frame.authority.util.UserUtil;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.exception.BusinessRuntimeException;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.exception.ResourceNotFoundException;

/**
 * 工程标段管理
 * @author eli_cui
 *
 */
@Service("busiProjectManagementOfBidSectionServiceImpl")
@Transactional
public class BusiProjectManagementOfBidSectionServiceImpl implements BusiProjectManagementOfBidSectionService {
	
	private static final Logger log = LoggerFactory.getLogger(BusiProjectManagementOfBidSectionServiceImpl.class);
	
	/** 工程标段管理dao */
	@Autowired
	BusiProjectManagementOfBidSectionDao busiProjectManagementOfBidSectionDao;
	/** 工程标段管理查询dao */
	@Autowired
	BusiProjectManagementOfBidSectionQueryDao busiProjectManagementOfBidSectionQueryDao;
	/** 工程标段管理明细 dao */
	@Autowired
	BusiProjectManagementOfBidSectionDetailDao detailDao;
	
	/**
	 * 保存工程标段管理实体
	 * @param entityDto
	 * @return
	 */
	private BusiProjectManagementOfBidSection saveBusiProjectManagementOfBidSectionDto(BusiProjectManagementOfBidSectionDto entityDto)  {
		//执行保存方法
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		BusiProjectManagementOfBidSection saveEntity = BeanCopy.getInstance().convert(entityDto, BusiProjectManagementOfBidSection.class);
		saveEntity.setUUID(uuid);
		beforeSave(entityDto, saveEntity);
		return saveEntity;
	}

	@Override
	public void updateBusiProjectManagementOfBidSection(BusiProjectManagementOfBidSectionDto entityDto) {
		//工程标段管理 实体
		BusiProjectManagementOfBidSection entity = null;
		Map<Integer, List<BusiProjectManagementOfBidSectionDetailDto>> map = null;
		//明细列表 
		List<BusiProjectManagementOfBidSectionDetailDto> detailEntityList = entityDto.getBusiProjectManagementOfBidSectionDetailDtoList();
		// 要保存或更新或删除的工程标段管理明细数Map对象 map if(key == 0){ value 是要保存的明细数据} if(key == 1) { value 是要更新的明细数据} if(key == 2) { value 是要删除的明细数据} 
		if(detailEntityList != null){
			map = this.getSaveOrUpdateOrDeleteDetailMap(detailEntityList);
		}
		//要保存的明细列表数据
		List<BusiProjectManagementOfBidSectionDetailDto> saveDetailList = map == null ? null : map.get(0);
		//要更新的明细数据列表
		List<BusiProjectManagementOfBidSectionDetailDto> updateDetailList = map == null ? null : map.get(1);
		//要删除的明细列表数据 
		List<BusiProjectManagementOfBidSectionDetailDto> deleteDetailList = map == null ? null : map.get(2);
		
		//如果拿到的id是null 使用保存方法 保存工程标段管理数据
		if(entityDto.getId() == null){
			//保存 工程标段管理
			entity = this.saveBusiProjectManagementOfBidSectionDto(entityDto);
			busiProjectManagementOfBidSectionDao.persistentBusiProjectManagementOfBidSection(entity);
			//保存 工程标段管理明细数据
			if(saveDetailList != null && saveDetailList.size() != 0){
				saveDetailData(entity, saveDetailList);
			}
		} else {//执行更新方法
			//获取要更新的 工程标段管理实体
			entity = busiProjectManagementOfBidSectionDao.findBusiProjectManagementOfBidSectionById(entityDto.getId());
			entity.setInitialPileNumber(entityDto.getInitialPileNumber());
			entity.setEndPileNumber(entityDto.getEndPileNumber());
			busiProjectManagementOfBidSectionDao.saveOrUpdateBusiProjectManagementOfBidSection(entity);
			//保存工程标段管理明细数据
			if(saveDetailList != null && saveDetailList.size() != 0){
				saveDetailData(entity, saveDetailList);
			}
			//修改工程标段管理明细数据
			if(updateDetailList != null && updateDetailList.size() != 0){
				updateAndDelete(entity, updateDetailList);
			}
		}
		if(deleteDetailList != null && deleteDetailList.size() != 0){
			//执行删除方法
			updateAndDelete(entity, deleteDetailList);
		}
		
		
//		log.debug("upadte entityDto by id@" + id);
//		BusiProjectManagementOfBidSection entity = busiProjectManagementOfBidSectionDao.findBusiProjectManagementOfBidSectionById(id);
//		BeanCopy.getInstance().mapIgnoreId(entityDto, entity);
//		//进行持久化保存
//		busiProjectManagementOfBidSectionDao.persistentBusiProjectManagementOfBidSection(entity);
	}

	/**
	 * 删除和修改方法
	 * @param entity
	 * @param deleteDetailList
	 */
	private void updateAndDelete(BusiProjectManagementOfBidSection entity, List<BusiProjectManagementOfBidSectionDetailDto> deleteDetailList) {
		if(deleteDetailList.size() == 1){ //单条数据处理
			BusiProjectManagementOfBidSectionDetail detailEntity = this.getDetailEntity(1, deleteDetailList, entity);
			detailDao.saveOrUpdateBusiProjectManagementOfBidSectionDetail(detailEntity);
		} else { //批量处理
			List<BusiProjectManagementOfBidSectionDetail> detailEntityList4Save = this.getDetailEntityList(1, deleteDetailList, entity);
			detailDao.batchSave(detailEntityList4Save, detailEntityList4Save.size());
		}
	}

	/**
	 * 保存明细数据
	 * @param entity
	 * @param saveDetailList
	 */
	private void saveDetailData(BusiProjectManagementOfBidSection entity, List<BusiProjectManagementOfBidSectionDetailDto> saveDetailList) {
		if(saveDetailList.size() == 1){//单条数据增加
			BusiProjectManagementOfBidSectionDetail detailEntity = this.getDetailEntity(0, saveDetailList, entity);
			detailDao.persistentBusiProjectManagementOfBidSectionDetail(detailEntity);
		} else {//多条数据增加
			List<BusiProjectManagementOfBidSectionDetail> detailEntityList4Save = this.getDetailEntityList(0, saveDetailList, entity);
			detailDao.batchSave(detailEntityList4Save, detailEntityList4Save.size());
		}
	}
	
	

	/**
	 * 获取明细实体列表
	 * @param type 0 保存 1 修改
	 * @param saveOrUpdateDetailList
	 * @param saveEntity
	 * @return
	 */
	private List<BusiProjectManagementOfBidSectionDetail> getDetailEntityList(int type, List<BusiProjectManagementOfBidSectionDetailDto> saveOrUpdateDetailList, BusiProjectManagementOfBidSection saveEntity) {
		List<BusiProjectManagementOfBidSectionDetail> detailEntityList = null;
		if(type == 0) {
			detailEntityList = BeanCopy.getInstance().convertList(saveOrUpdateDetailList, BusiProjectManagementOfBidSectionDetail.class);
			for(BusiProjectManagementOfBidSectionDetail entity :detailEntityList){
				beforSaveForDetail(entity, saveEntity);
			}
		} else {
			String[] onlyTargetFields = {"id","nameOfUnitProject","typeOfUnitProject","initialPileNumber","endPileNumber","longitude","latitude","longKm","isDelete"};
			detailEntityList = new ArrayList<BusiProjectManagementOfBidSectionDetail>();
			for(BusiProjectManagementOfBidSectionDetailDto entity : saveOrUpdateDetailList){
				BusiProjectManagementOfBidSectionDetail detailEntity = detailDao.findBusiProjectManagementOfBidSectionDetailById(entity.getId());
				BeanCopy.getInstance().addOnlyFields(onlyTargetFields).map(entity, detailEntity);
				detailEntityList.add(detailEntity);
			}
		}

		return detailEntityList;
	}



	/**
	 * 获取明细实体
	 * @param type 0 新增 1 修改
	 * @param saveOrUpdateDetailList
	 * @param saveEntity
	 * @param isDelete 1 删除  0 不删除
	 * @return
	 */
	private BusiProjectManagementOfBidSectionDetail getDetailEntity(int type, List<BusiProjectManagementOfBidSectionDetailDto> saveOrUpdateDetailList,BusiProjectManagementOfBidSection saveEntity) {
		BusiProjectManagementOfBidSectionDetail detailEntity = null;
		if(type == 0){//新增的数据
			detailEntity = BeanCopy.getInstance().convert(saveOrUpdateDetailList.get(0), BusiProjectManagementOfBidSectionDetail.class);
			beforSaveForDetail(detailEntity, saveEntity);
		} else {//修改的数据
			String[] onlyTargetFields = {"id","nameOfUnitProject","typeOfUnitProject","initialPileNumber","endPileNumber","longitude","latitude","longKm","isDelete","code"};
			detailEntity = detailDao.findBusiProjectManagementOfBidSectionDetailById(saveOrUpdateDetailList.get(0).getId());
			BeanCopy.getInstance().addOnlyFields(onlyTargetFields).map(saveOrUpdateDetailList.get(0), detailEntity);
		}

		return detailEntity;
	}
	
	/**
	 * 获取要保存或更新或删除的工程标段管理明细数Map对象
	 * @param detailEntityList
	 * @return Map<Integer, List<BusiProjectManagementOfBidSectionDetailDto>> 
	 * map key == 0 保存要保存的 工程标段管理明细 的数据
	 * 	   key == 1 保存要修改的 工程标段管理明细 的数据
	 * 	   key == 2 保存要删除的 工程标段管理明细 的数据
	 */
	private Map<Integer, List<BusiProjectManagementOfBidSectionDetailDto>> getSaveOrUpdateOrDeleteDetailMap(List<BusiProjectManagementOfBidSectionDetailDto> detailEntityList){
		Map<Integer, List<BusiProjectManagementOfBidSectionDetailDto>> resultMap = new HashMap<Integer, List<BusiProjectManagementOfBidSectionDetailDto>>();
		List<BusiProjectManagementOfBidSectionDetailDto> saveDetailList = new ArrayList<BusiProjectManagementOfBidSectionDetailDto>();
		List<BusiProjectManagementOfBidSectionDetailDto> updateDetailList = new ArrayList<BusiProjectManagementOfBidSectionDetailDto>();
		List<BusiProjectManagementOfBidSectionDetailDto> deleteDetailList = new ArrayList<BusiProjectManagementOfBidSectionDetailDto>();
		for(BusiProjectManagementOfBidSectionDetailDto entity : detailEntityList){
			if(entity.getOperation() != null){
				switch (entity.getOperation()) {
				case 0 :
					saveDetailList.add(entity);
					break;
				case 1 :
					updateDetailList.add(entity);
					break;
				case 2:
					entity.setIsDelete(1);
					deleteDetailList.add(entity);
					break;
				default:
					throw new BusinessRuntimeException("BusiProjectManagementOfBidSectionServiceImpl.getSaveOrUpdateOrDeleteDetailMap 参数错误，查看前台js代码。", "-1");
				}
			}
		}
		resultMap.put(0, saveDetailList);
		resultMap.put(1, updateDetailList);
		resultMap.put(2, deleteDetailList);
		return resultMap;
	}
	
	/**
	 * 删除明细
	 * @param id
	 */
	private void deleteBusiProjectManagementOfBidSection(Long id) {
		log.debug("delete busiProjectManagementOfBidSection by id@" + id);
		beforeRemove(id);
		BusiProjectManagementOfBidSection entity = busiProjectManagementOfBidSectionDao.findBusiProjectManagementOfBidSectionById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		busiProjectManagementOfBidSectionDao.deleteBusiProjectManagementOfBidSection(entity);
	}
	
	@Override	    
	public DataPage<BusiProjectManagementOfBidSectionShowDto> getAllBusiProjectManagementOfBidSectionPage(QueryPage queryPage) {
		Long orgId = UserUtil.getUser().getOrgId();
		Long currProId = UserUtil.getUser().getCurrProId();
		
		DataPage<BusiProjectManagementOfBidSectionShow> dataPage = busiProjectManagementOfBidSectionQueryDao.getAllBusiProjectManagementOfBidSectionShow(orgId, currProId, queryPage);
		DataPage<BusiProjectManagementOfBidSectionShowDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, BusiProjectManagementOfBidSectionShowDto.class);
		return datPageDto;
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
	* @param    busiProjectManagementOfBidSectionDto
	* @param    busiProjectManagementOfBidSection
	* @return  void    
	* @
	*/
	private void beforeSave(BusiProjectManagementOfBidSectionDto entityDto, BusiProjectManagementOfBidSection entity) {
		//fwOrg实体类 保存时使用
		BusiFwOrg fwOrgEntity = new BusiFwOrg();
		fwOrgEntity.setId(entityDto.getOrgId());
		entity.setOrgEntity(fwOrgEntity);
		entity.setIsDelete(0);
	}
	
	
	/**
	 * 保存 工程标段管理 明细之前的操作
	 * @param detailEntity
	 * @param saveEntity
	 * @param isDelete 是否删除 0 不删除 1 删除
	 */
	private void beforSaveForDetail(BusiProjectManagementOfBidSectionDetail detailEntity, BusiProjectManagementOfBidSection saveEntity){
		detailEntity.setBusiProjectManagementOfBidSection(saveEntity);
		detailEntity.setIsDelete(0);
	}
	
	/**
	 * 根据机构ID查询监理、建设、合同信息(此接口给【合同管理-施工合同管理新增】使用)
	 */
	@Override
	public BusiProjectManagementOfBidSectionPortDto getInfoForTheContractByOrgId(Long orgId) {
		List<Map<String, Object>> resultDataList = busiProjectManagementOfBidSectionDao.getInfoForTheContractByOrgId(orgId);
		BusiProjectManagementOfBidSectionPortDto resultDto = null;
		if (resultDataList != null && resultDataList.size() > 0) {
			Map<String, Object> resultDataMap = resultDataList.get(0);
			resultDto = new BusiProjectManagementOfBidSectionPortDto();
			resultDto.setStartStakeNo(conventNull(resultDataMap.get("START_STAKE_NO")));
			resultDto.setEndStakeNo(conventNull(resultDataMap.get("END_STAKE_NO")));
			resultDto.setSupervisionCompanyName(conventNull(resultDataMap.get("SUPERVISION_NAME")));
			resultDto.setSupervisionCompanyCode(Long.valueOf(conventNull(resultDataMap.get("SUPERVISION_CODE"))));
			resultDto.setConstructionCompanyName(conventNull(resultDataMap.get("CONSTRUCTION_NAME")));
			resultDto.setConstructionCompanyCode(Long.valueOf(conventNull(resultDataMap.get("CONSTRUCTION_CODE"))));
			resultDto.setContractCode(conventNull(resultDataMap.get("CONTRACT_CODE")));
			resultDto.setBuildCompanyName(conventNull(resultDataMap.get("BUILD_NAME")));
			resultDto.setBuildCompanyCode(Long.valueOf(conventNull(resultDataMap.get("BUILD_CODE"))));
			
		} else {
			return null;
		}
		return resultDto;
	}
	
	private static String conventNull(Object object){
		return object == null? null: object.toString();
	}
	
	@Override
	public DataPage<BusiProjectManagementOfBidSectionDetailDto> getDetailByParentId(Long parentId) {
		DataPage<BusiProjectManagementOfBidSectionDetailDto> page = new DataPage<BusiProjectManagementOfBidSectionDetailDto>();
		List<BusiProjectManagementOfBidSectionDetailDto> list = new ArrayList<BusiProjectManagementOfBidSectionDetailDto>();
		Set<BusiProjectManagementOfBidSectionDetail> detailSet = busiProjectManagementOfBidSectionDao.findBusiProjectManagementOfBidSectionById(parentId).getBusiprojectmanagementofbidsectiondetailSet();
		for(BusiProjectManagementOfBidSectionDetail detailEntity : detailSet){
			if(detailEntity.getIsDelete() == 0){
				list.add(BeanCopy.getInstance().convert(detailEntity, BusiProjectManagementOfBidSectionDetailDto.class));
			}
		}
		list.sort(new Comparator<BusiProjectManagementOfBidSectionDetailDto>() {
			@Override
			public int compare(BusiProjectManagementOfBidSectionDetailDto arg0, BusiProjectManagementOfBidSectionDetailDto arg1) {
				if(arg0.getId() < arg1.getId()){
					return -1;
				} else if(arg0.getId().longValue() == arg1.getId().longValue()){
					return 0;
				} else {
					return 1;
				}
				
			}
		});
		page.setContent(list);
		page.setTotalRows(list.size());
		return page;
	}
	
	@Override
	public String getBusiProjectManagementOfBidSectionDetailListById(List<Long> idList) {
		String result = "1";
		StringBuilder sb = new StringBuilder();
		List<Long> detailIdList = busiProjectManagementOfBidSectionDao.getBindDetailIdBySelectedIdList(idList);
		if(detailIdList != null){
			List<BusiProjectManagementOfBidSectionDetail> detailEntityList = detailDao.findBusiProjectManagementOfBidSectionDetailByIdList(detailIdList);
			for(BusiProjectManagementOfBidSectionDetail entity : detailEntityList){
				sb.append(entity.getNameOfUnitProject());
				sb.append("，已在“标段分部分项维护”中关联，不可删除。</br>");
			}
			result = sb.toString();
		}
		return result;
	}
}
