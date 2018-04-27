package com.huatek.busi.service.impl.external;

import java.util.Date;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huatek.busi.constants.Constant;
import com.huatek.busi.dao.quality.BusiQualityCementMixingStationExceedDao;
import com.huatek.busi.dao.quality.BusiQualityCementMixingStationInspectionDao;
import com.huatek.busi.dto.external.ExternalResponse;
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationInspectionDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationExceed;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.service.external.BusiQualityExternalService;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationInspectionService;
import com.huatek.cmd.dto.CmdMachineDto;
import com.huatek.cmd.service.CmdMachineService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.service.FwOrgService;

/**
 * 接口 水泥拌合站
 * @author eli_cui
 *
 */
@Service("busiQualityExternalCementMixingPlant")
@Transactional
public class BusiQualityExternalCementMixingPlant implements BusiQualityExternalService{

	@Autowired
	BusiQualityCementMixingStationInspectionService service;
	
	@Autowired
	BusiQualityCementMixingStationInspectionDao dao;
	
	//水泥拌合站超标dao
	@Autowired
	BusiQualityCementMixingStationExceedDao exceedDao;
	
	@Autowired
	private CmdMachineService cmdService;
	
	@Autowired
	private FwOrgService orgService;
	
	@Override
	public ExternalResponse receiveData(String busiType, String appKey, String data, Date timestamp) {
		JSONObject dataJson = null;
		int operateType = 0;
		CmdMachineDto cmdMachineDto = null;
		BusiQualityCementMixingStationInspectionDto dto = null;
		BusiQualityCementMixingStationInspection entity = null;
		//水泥拌合站超标entity
		BusiQualityCementMixingStationExceed exceedEntity = null;
		Long tenantId = null;
		try {
			dataJson = JSONObject.fromObject(data); 
			operateType = dataJson.getInt("operateType");
			//设备注册信息
			cmdMachineDto = cmdService.getCmdMachineDtoByAppKey(appKey);
			//判断设备注册信息
			if(cmdMachineDto == null || cmdMachineDto.getOrgId() == null){
				System.out.println("获取注册信息出错，请检查APPKey。");
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "获取注册信息出错，请检查APPKey。");
			}
			//判断接收字段数量  
			if(!ExternalUtil.checkFieldCountByNameAndCount("busiQualityExternalCementMixingPlant", dataJson.size())){
				System.out.println("接收参数，数量不符合规则。请检查原材料检测传输字段是否过多或过少。");
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "接收参数，数量不符合规则。请检查原材料检测传输字段是否过多或过少。");
			}
		} catch (Exception e) {
			System.out.println("参数解析失败！");
			return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "参数解析失败！，" + e.toString());
		}
		
		// 新增、 修改 、 删除
		try {
			switch (operateType) {
			//新增
			case Constant.OPERATION_INT_TYPE_ADD :
				entity = dao.findBusiQualityCementMixingStationInspectionByUkey(dataJson.getString("ukey"));
				if(entity != null && entity.getIsDelete().intValue() == Constant.DELETE_STATUS_NOT_DELETED){
					System.out.println("水泥拌合站数据已存在不允许重复添加");
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "水泥拌合站数据已存在不允许重复添加");
				} else {
					//租户id
					tenantId = orgService.getOrgById(cmdMachineDto.getOrgId()).getTenantId();
					dto = (BusiQualityCementMixingStationInspectionDto) dataJson.toBean(dataJson, BusiQualityCementMixingStationInspectionDto.class);
					dto.setOrgId(cmdMachineDto.getOrgId());
					dto.setTenantId(tenantId);
					dto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
					dto.setAppKey(appKey);
					dto.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_0);
					service.saveBusiQualityCementMixingStationInspectionDto(dto);
				}
				break;
			// 修改
			case Constant.OPERATION_INT_TYPE_UPDATE  :
				entity = dao.findBusiQualityCementMixingStationInspectionByUkey(dataJson.getString("ukey"));
				if(entity == null){
					System.out.println("未查询到要修改的数据");
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要修改的数据。");
				}
				//判断是否可以修改， 需要判断是否存在水泥超标数据已经 处理中或已处理。此时需要根据 ukey查询 水泥拌合站 超标表。 
				exceedEntity = exceedDao.findBusiQualityCementMixingStationExceedByUkey(entity.getUkey());
				if(exceedEntity != null){
					System.out.println("推送数据已在处理或处理完成，不允许修改。");
					if(Integer.valueOf(exceedEntity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
						return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "推送数据已在处理或处理完成，不允许修改。");
					}
				}
				dto = (BusiQualityCementMixingStationInspectionDto) dataJson.toBean(dataJson, BusiQualityCementMixingStationInspectionDto.class);
				if(entity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_0 || entity.getIsQualitySupervisionBureau() == null){
					BeanCopy.getInstance().mapIgnoreNull(dto, entity);
				} else if(entity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_1){
					BeanCopy.getInstance().mapIgnoreNull(dto, entity);
					entity.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_2);//2已传给质监局并做修改
				}
				dao.saveOrUpdateBusiQualityCementMixingStationInspection(entity);
				break;
			// 删除
			case Constant.OPERATION_INT_TYPE_DELETE :
				entity = dao.findBusiQualityCementMixingStationInspectionByUkey(dataJson.getString("ukey"));
				exceedEntity = exceedDao.findBusiQualityCementMixingStationExceedByUkey(entity.getUkey());
				if(entity != null){
					if(exceedEntity != null){
						//判断子表-超标表-是否处理中 处理中不允许删除数据。
						if(Integer.valueOf(exceedEntity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
							System.out.println("数据已在处理或处理完成，不允许删除");
							return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "数据已在处理或处理完成，不允许删除");
						} else { //不为空删两个 - 超标表中的数据也删掉
							entity.setIsDelete(1);
							exceedEntity.setIsDelete(1);
							dao.saveOrUpdateBusiQualityCementMixingStationInspection(entity);
							exceedDao.saveOrUpdateBusiQualityCementMixingStationExceed(exceedEntity);
						}
					} else {
						entity.setIsDelete(1);
						dao.saveOrUpdateBusiQualityCementMixingStationInspection(entity);
					}
				} else {
					System.out.println("未查询到要删除的数据");
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要删除的数据。");
				}
				break;
			default:
				System.out.println("参数 \"operateType\" 校验出错");
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "参数 \"operateType\" 校验出错。");
			}
		} catch (Exception e) {
			System.out.println("数据保存出错。");
			return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "数据保存出错。");
		}
		
		return ExternalUtil.getExternalResponse(Constant.externalStatusCode.SUCCESS, "");
	}
	
	@Override
	public String getType() {
		return "cementMixingPlant";
	}

}
