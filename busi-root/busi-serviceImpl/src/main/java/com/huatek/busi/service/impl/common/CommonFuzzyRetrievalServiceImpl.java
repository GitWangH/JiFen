package com.huatek.busi.service.impl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.dto.common.CommonFuzzyRetrievalDto;
import com.huatek.busi.dto.contract.BusiContractTendersBranchDto;
import com.huatek.busi.service.common.CommonFuzzyRetrievalService;
import com.huatek.busi.service.contract.BusiContractTendersBranchService;

@Service("commonFuzzyRetrievalServiceImpl")
@Transactional
public class CommonFuzzyRetrievalServiceImpl implements CommonFuzzyRetrievalService{
	
	//标段分部分项 service
	@Autowired
	BusiContractTendersBranchService busiContractTendersBranchService;
	
	@Override
	public List<CommonFuzzyRetrievalDto> getTendersBranchByKeywordAndOrgId(String keyword, Long orgId) {
		List<BusiContractTendersBranchDto> dtoList = busiContractTendersBranchService.getBusiContractTendersBranchDtoByTendersBranchName(keyword, orgId);
		if(dtoList == null){
			return null;
		} 
		List<CommonFuzzyRetrievalDto> resultList = new ArrayList<CommonFuzzyRetrievalDto>();
		Map<String, String> groupLevelMap = null;
		int groupLevel = 0;
		Map<String,List<String>> conditionLevelMap = getMapByDtoList(dtoList);
		List<BusiContractTendersBranchDto> parentDtoList = busiContractTendersBranchService.getBusiContractTendersBranchDtoByLevel(conditionLevelMap, orgId);
		Map<String, String> parentMap = getParentMap(parentDtoList); 
		
		
		for(BusiContractTendersBranchDto dto : dtoList){
			if("0".equals(dto.getParentId())){
				CommonFuzzyRetrievalDto resultDto = new CommonFuzzyRetrievalDto();
				resultDto.setId(dto.getId());
				resultDto.setValue(dto.getTendersBranchName());
				resultList.add(resultDto);
			} else {
				groupLevel = Integer.valueOf(dto.getGroupLevel().toString());
				groupLevelMap = getGroupLevelMap(dto);
				StringBuilder sb = new StringBuilder();
				for(int i = 1; i < groupLevel; i++){
					sb.append(parentMap.get(groupLevelMap.get(getGroupLevelMapKey(i))));
					sb.append("-");
				}
				CommonFuzzyRetrievalDto resultDto = new CommonFuzzyRetrievalDto();
				resultDto.setId(dto.getId());
				resultDto.setValue(sb.append(dto.getTendersBranchName()).toString());
				resultList.add(resultDto);
			}
		}
		return resultList;
	}
	
	/**
	 * 获取 grouplevel map 的key
	 * @param i
	 * @return
	 */
	private String getGroupLevelMapKey(int i){
		StringBuilder sb = new StringBuilder("level");
		sb.append(i);
		return sb.toString();
	}
	/**
	 * 获取 grouplevel map
	 * @param dto
	 * @return
	 */
	private Map<String, String> getGroupLevelMap(BusiContractTendersBranchDto dto){
		Map<String, String> map = new HashMap<String, String>();
		switch (dto.getGroupLevel().toString()) {
		case "1":
			map.put("level1", dto.getLevel1());
			return map;
		case "2":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			return map;
		case "3":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			return map;
		case "4":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			map.put("level4", dto.getLevel4());
			return map;
		case "5":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			map.put("level4", dto.getLevel4());
			map.put("level5", dto.getLevel5());
			return map;
		case "6":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			map.put("level4", dto.getLevel4());
			map.put("level5", dto.getLevel5());
			map.put("level6", dto.getLevel6());
			return map;
		case "7":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			map.put("level4", dto.getLevel4());
			map.put("level5", dto.getLevel5());
			map.put("level6", dto.getLevel6());
			map.put("level7", dto.getLevel7());
			return map;
		case "8":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			map.put("level4", dto.getLevel4());
			map.put("level5", dto.getLevel5());
			map.put("level6", dto.getLevel6());
			map.put("level7", dto.getLevel7());
			map.put("level8", dto.getLevel8());
			return map;
		case "9":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			map.put("level4", dto.getLevel4());
			map.put("level5", dto.getLevel5());
			map.put("level6", dto.getLevel6());
			map.put("level7", dto.getLevel7());
			map.put("level8", dto.getLevel8());
			map.put("level9", dto.getLevel9());
			return map;
		case "10":
			map.put("level1", dto.getLevel1());
			map.put("level2", dto.getLevel2());
			map.put("level3", dto.getLevel3());
			map.put("level4", dto.getLevel4());
			map.put("level5", dto.getLevel5());
			map.put("level6", dto.getLevel6());
			map.put("level7", dto.getLevel7());
			map.put("level8", dto.getLevel8());
			map.put("level9", dto.getLevel9());
			map.put("level10", dto.getLevel10());
			return map;
		default:
			return map;
		}
		
	}
	
	/**
	 * 父级Map
	 * @param parentDtoList
	 * @return
	 */
	private Map<String, String> getParentMap(List<BusiContractTendersBranchDto> parentDtoList){
		Map<String, String> map = new HashMap<String, String>();
		if(parentDtoList!=null){
			for(BusiContractTendersBranchDto dto : parentDtoList){
				map.put(dto.getUUID(), dto.getTendersBranchName());
			}
		}
		return map;
	}
	
	/**
	 * 根据 dtoList 获取一个map<level,List<uuid>>
	 * value 存储level1-10字段的uuid
	 * @param dtoList
	 * @return
	 */
	private Map<String, List<String>> getMapByDtoList(List<BusiContractTendersBranchDto> dtoList){
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Set<String> level1 = new HashSet<String>();
		Set<String> level2 = new HashSet<String>();
		Set<String> level3 = new HashSet<String>();
		Set<String> level4 = new HashSet<String>();
		Set<String> level5 = null;
		Set<String> level6 = null;
		Set<String> level7 = null;
		Set<String> level8 = null;
		Set<String> level9 = null;
		Set<String> level10 = null;
		
		for(BusiContractTendersBranchDto dto : dtoList){
			level1.add(dto.getLevel1());
			if(StringUtils.isNotBlank(dto.getLevel2())){
				level2.add(dto.getLevel2());
			} else if(StringUtils.isNotBlank(dto.getLevel3())){
				level3.add(dto.getLevel3());
			} else if(StringUtils.isNotBlank(dto.getLevel4())){
				level4.add(dto.getLevel4());
			} else if(StringUtils.isNotBlank(dto.getLevel5())){
				if(level5 == null){
					level5 = new HashSet<String>();
					level5.add(dto.getLevel5());
				} else {
					level5.add(dto.getLevel5());
				}
			} else if(StringUtils.isNotBlank(dto.getLevel6())){
				if(level6 == null){
					level6 = new HashSet<String>();
					level6.add(dto.getLevel6());
				} else {
					level6.add(dto.getLevel6());
				}
			} else if(StringUtils.isNotBlank(dto.getLevel7())){
				if(level7 == null){
					level7 = new HashSet<String>();
					level7.add(dto.getLevel7());
				} else {
					level7.add(dto.getLevel7());
				}
			} else if(StringUtils.isNotBlank(dto.getLevel8())){
				if(level8 == null){
					level8 = new HashSet<String>();
					level8.add(dto.getLevel8());
				} else {
					level8.add(dto.getLevel8());
				}
			} else if(StringUtils.isNotBlank(dto.getLevel9())){
				if(level9 == null){
					level9 = new HashSet<String>();
					level9.add(dto.getLevel9());
				} else {
					level9.add(dto.getLevel9());
				}
			} else if(StringUtils.isNotBlank(dto.getLevel10())){
				if(level10 == null){
					level10 = new HashSet<String>();
					level10.add(dto.getLevel10());
				} else {
					level10.add(dto.getLevel10());
				}
			}
		}
		
		map.put("level1", new ArrayList<String>(level1));
		if(level2 != null){
			map.put("level2", new ArrayList<String>(level2));
		}
		if(level3 != null){
			map.put("level3", new ArrayList<String>(level3));
		}
		if(level4 != null){
			map.put("level4", new ArrayList<String>(level4));
		}
		if(level5 != null){
			map.put("level5", new ArrayList<String>(level5));
		}
		if(level6 != null){
			map.put("level6", new ArrayList<String>(level6));
		}
		if(level7 != null){
			map.put("level7", new ArrayList<String>(level7));
		}
		if(level8 != null){
			map.put("level8", new ArrayList<String>(level8));
		}
		if(level9 != null){
			map.put("level9", new ArrayList<String>(level9));
		}
		if(level10 != null){
			map.put("level10", new ArrayList<String>(level10));
		}
		return map;
	}

	
}
