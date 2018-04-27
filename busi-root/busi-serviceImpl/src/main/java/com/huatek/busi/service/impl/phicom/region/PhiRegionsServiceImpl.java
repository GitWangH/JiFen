package com.huatek.busi.service.impl.phicom.region;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.huatek.busi.dao.phicom.region.PhiRegionsDao;
import com.huatek.busi.dto.phicom.region.PhiRegionsDto;
import com.huatek.busi.model.phicom.region.PhiRegions;
import com.huatek.busi.service.impl.base.PhiCommApiClient;
import com.huatek.busi.service.phicom.region.PhiRegionsService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.core.page.DataPage;
import com.huatek.frame.core.page.QueryPage;
import com.huatek.frame.core.util.BeanUtils;
import com.huatek.frame.core.util.PropertyUtil;
import com.huatek.frame.exception.ResourceNotFoundException;

@Service("phiRegionsServiceImpl")
@Transactional
public class PhiRegionsServiceImpl implements PhiRegionsService {
	
	private static final Logger log = LoggerFactory.getLogger(PhiRegionsServiceImpl.class);
	
	@Autowired
	PhiRegionsDao phiRegionsDao;
	
	@Override
	public void savePhiRegionsDto(PhiRegionsDto entityDto)  {
		log.debug("save phiRegionsDto @" + entityDto);
		//根据页面传进来的值设置保存的值信息
		PhiRegions entity = BeanCopy.getInstance().convert(entityDto, PhiRegions.class);
		//保存之前操作
		beforeSave(entityDto, entity);
		//进行持久化保存
		phiRegionsDao.persistentPhiRegions(entity);
		log.debug("saved entityDto id is @" + entity.getId());
	}
	
	
	@Override
	public PhiRegionsDto getPhiRegionsDtoById(Long id) {
		log.debug("get phiRegions by id@" + id);
		PhiRegions entity = phiRegionsDao.findPhiRegionsById(id);
		if (null == entity) {
			 throw new ResourceNotFoundException(id);
		}
				 
		PhiRegionsDto entityDto = BeanCopy.getInstance().convert(entity, PhiRegionsDto.class);
		return entityDto;
	}
	
	@Override
	public void updatePhiRegions(Long id, PhiRegionsDto entityDto) {
		log.debug("update entityDto by id@" + id);
		PhiRegions entity = phiRegionsDao.findPhiRegionsById(id);
		BeanUtils.copyNotNullProperties(entityDto, entity, 
				new String[] {""});
		//进行持久化保存
		phiRegionsDao.persistentPhiRegions(entity);
	}
	
	
	
	@Override
	public void deletePhiRegions(Long id) {
		log.debug("delete phiRegions by id@" + id);
		beforeRemove(id);
		PhiRegions entity = phiRegionsDao.findPhiRegionsById(id);
		if (null == entity) {
			throw new ResourceNotFoundException(id);
		}
		phiRegionsDao.deletePhiRegions(entity);
	}
	
	@Override
	public DataPage<PhiRegionsDto> getAllPhiRegionsPage(QueryPage queryPage) {
		DataPage<PhiRegions> dataPage = phiRegionsDao.getAllPhiRegions(queryPage);
		DataPage<PhiRegionsDto> datPageDto = BeanCopy.getInstance().convertPage(dataPage, PhiRegionsDto.class);
		
		return datPageDto;
	}
	
	@Override
	public List<PhiRegionsDto> getAllPhiRegionsDto() {
		List<PhiRegions> entityList = phiRegionsDao.findAllPhiRegionsByOrder();
		List<PhiRegionsDto> dtos = BeanCopy.getInstance().convertList(entityList, PhiRegionsDto.class);
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
	* @param    phiRegionsDto
	* @param    phiRegions
	* @return  void    
	* @
	*/
	private void beforeSave(PhiRegionsDto entityDto, PhiRegions entity) {

	}
	
	private void Save(PhiRegions entity) {
		phiRegionsDao.saveOrUpdatePhiRegions(entity);
	}
	
	public void mysave(){
		List<PhiRegions> regionsList = new ArrayList<PhiRegions>();
		JsonObject reponsedata = PhiCommApiClient.requestPhiCommApi(PropertyUtil.getConfigValue("vmsShop_header")+"regions", "regions", "{}");
		JsonArray 	regions  = null;
		JsonObject data = reponsedata.getAsJsonObject("data");
//		dataJson = JSONObject.fromObject(data); 
		String version = data.get("version").getAsString();
		regions =  data.get("regions").getAsJsonArray();
		if (regions == null || "".equals(regions)) {
//			return null;
		}
		else{
			for(int i=0;i<regions.size();i++){
//				String xianParent = null;
				//第一个对象
//				if((i+1)%2!=0){
					PhiRegions cityEntity =new PhiRegions();
					JsonElement LchildElement = regions.get(i);
					JsonObject LchildData = LchildElement.getAsJsonObject();
					String cityStr= LchildData.get("name").getAsString();//例如："1：北京市"
					String[] strSplit = null;
					String cityName = null;
					String cityCode = null;
					if(null!=cityStr||"".equals(cityStr)){
						strSplit = cityStr.split("\\:");
						cityName = strSplit[1];
						cityCode = strSplit[0];
					}
					cityEntity.setName(cityName);
					cityEntity.setCode(cityCode);
					cityEntity.setLevel(1);//设置级别
					cityEntity.setOrdernume(i);//设置排序编号
					regionsList.add(cityEntity);
//					Save(cityEntity);
					//区一级解析、存储
					JsonArray childObject = LchildData.getAsJsonArray("data");
					int orderNum=0;
						for( JsonElement childElement:childObject){
							JsonObject leve2 = childElement.getAsJsonObject();
							PhiRegions level2Entity =new PhiRegions();
							String level2NameTotal = leve2.get("name").getAsString();
							String[] areaParentSplit = null;
							String level2Name = null;
							String level2Code = null;
							String parentCode = cityCode;//上一级Code
							/*判断解析第一个的name串中的code与名称*/
							if(StringUtils.isNotBlank(level2NameTotal)){
								areaParentSplit = level2NameTotal.split(":");
								level2Name = areaParentSplit[1];
								level2Code = areaParentSplit[0];
//								 if("市辖区".equals(areaParentName)){
//									 parentCode = cityCode;//如果为直辖市，则区的上一级Code为市
//								 }else{
//									 parentCode = areaParentCode;
//								 }
							}
							level2Entity.setName(level2Name);
							level2Entity.setCode(level2Code);
							level2Entity.setParentCode(parentCode);
							level2Entity.setLevel(2);//设置级别
							level2Entity.setOrdernume(orderNum++);//设置排序编号
							regionsList.add(level2Entity);
							JsonArray Level3AreaArray = leve2.getAsJsonArray("data");
							for(int j=0;j<Level3AreaArray.size();j++){
								PhiRegions areaEntity =new PhiRegions();
								String level3AreaString = null;
								if(StringUtils.isNotBlank(Level3AreaArray.get(j).getAsString())){
									 level3AreaString = Level3AreaArray.get(j).getAsString();
								}
								String[] level3AreaSplit = null;
								String level3AreaName = null;
								String level3areaCode = null;
								if(StringUtils.isNotBlank(level3AreaString)){
									level3AreaSplit = level3AreaString.split(":");
									level3AreaName = level3AreaSplit[1];
									level3areaCode = level3AreaSplit[0];
								}
								areaEntity.setCode(level3areaCode);
								areaEntity.setName(level3AreaName);
								areaEntity.setParentCode(level2Code);
								areaEntity.setLevel(3);
								areaEntity.setOrdernume(j);
								regionsList.add(areaEntity);
//								xianParent = areaCode; //为直辖市默认县的parentCode为areaId
//								Save(areaEntity);
							}
						}
//				}else{//第二个对象为县
//					JsonElement LchildElement = regions.get(i);
//					JsonObject RchildData = LchildElement.getAsJsonObject();
//					 String ParentName = RchildData.get("name").getAsString();
//					 String[] strSplit = null;
//					 String parentCode = null;
//					 String parentName = null;
//					 if(!"".equals(ParentName)){
//						 strSplit = ParentName.split(":");
//						  parentCode = strSplit[0];
//						  parentName = strSplit[1];
//						  if("市辖县".equals(parentName)){
//								 parentCode = xianParent;
//							 }
//					 }
//					 
//					JsonArray countyArray = RchildData.getAsJsonArray("data");
//					for(int k=0;k<countyArray.size();k++){
//						PhiRegions countEntity =new PhiRegions();
//						String  countyStr = countyArray.get(k).getAsString();
//						String[] countySplit = null;
//						String countyName = null;
//						String countyCode = null;
//						if(null!=countyStr||!"".equals(countyStr)){
//							countySplit = countyStr.split(":");
//							countyName = countySplit[1];
//							countyCode = countySplit[0];
//						}
//						countEntity.setName(countyName);
//						countEntity.setLevel(3);
//						countEntity.setOrdernume(k);
//						countEntity.setParentCode(parentCode);
//						countEntity.setCode(countyCode);
//						Save(countEntity);
//					}
//				}
			}
		}
		if(!regionsList.isEmpty()){
			phiRegionsDao.batchSaveOrUpdate(regionsList);
		}
	}
}

