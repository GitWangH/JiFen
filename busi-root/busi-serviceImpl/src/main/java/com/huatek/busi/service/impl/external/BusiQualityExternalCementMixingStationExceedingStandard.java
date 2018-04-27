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
import com.huatek.busi.dto.quality.BusiQualityCementMixingStationExceedDto;
import com.huatek.busi.dto.quality.BusiQualityRawMaterialInspectionDto;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationExceed;
import com.huatek.busi.model.quality.BusiQualityCementMixingStationInspection;
import com.huatek.busi.service.external.BusiQualityExternalService;
import com.huatek.busi.service.quality.BusiQualityCementMixingStationExceedService;
import com.huatek.cmd.dto.CmdMachineDto;
import com.huatek.cmd.service.CmdMachineService;
import com.huatek.frame.core.beancopy.BeanCopy;
import com.huatek.frame.service.FwOrgService;

/**
 * 接口 - 水泥拌合站超标
 * @author eli_cui
 *
 */
@Service("busiQualityExternalCementMixingStationExceedingStandard")
@Transactional
public class BusiQualityExternalCementMixingStationExceedingStandard implements BusiQualityExternalService {
	
	@Autowired
	private BusiQualityCementMixingStationExceedService service;
	
	@Autowired
	private BusiQualityCementMixingStationExceedDao dao;
	
	//水泥拌合站 dao
	@Autowired
	private BusiQualityCementMixingStationInspectionDao stationDao;
	
	@Autowired
	private CmdMachineService cmdService;
	
	@Autowired
	private FwOrgService orgService;
	@Override
	public ExternalResponse receiveData(String busiType, String appKey, String data, Date timestamp) {
		JSONObject dataJson = null;
		int operateType = 0;
		CmdMachineDto cmdMachineDto = null;
		BusiQualityCementMixingStationExceedDto dto = null;
		BusiQualityCementMixingStationExceed entity = null;
		// 水泥拌合站 实体
		BusiQualityCementMixingStationInspection stationEntity = null;
		Long tenantId = null;
		try {
			dataJson = JSONObject.fromObject(data); 
			operateType = dataJson.getInt("operateType");
			//设备注册信息
			cmdMachineDto = cmdService.getCmdMachineDtoByAppKey(appKey);
			//判断设备注册信息
			if(cmdMachineDto == null || cmdMachineDto.getOrgId() == null){
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "获取注册信息出错，请检查APPKey。");
			}
			//判断接收字段数量  
			if(!ExternalUtil.checkFieldCountByNameAndCount("busiQualityExternalCementMixingStationExceedingStandard", dataJson.size())){
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "接收参数，数量不符合规则。请检查原材料检测传输字段是否过多或过少。");
			}
		} catch (Exception e) {
			return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "参数解析失败！，" + e.toString());
		}
		
		//新增、修改、删除。
		try {
			switch (operateType) {
			//新增
			case Constant.OPERATION_INT_TYPE_ADD :
				//判断是否存在 水泥拌合站数据，如果不存在水泥拌合站数据，不允许新增水泥拌合站超标数据。
				stationEntity = stationDao.findBusiQualityCementMixingStationInspectionByUkey(dataJson.getString("ukey"));
				if(stationEntity == null){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "水泥拌合站数据不存在，不允许新增超标数据。");
				} else {
					//租户id
					tenantId = orgService.getOrgById(cmdMachineDto.getOrgId()).getTenantId();
					dto = (BusiQualityCementMixingStationExceedDto) dataJson.toBean(dataJson, BusiQualityCementMixingStationExceedDto.class);
					dto.setOrgId(cmdMachineDto.getOrgId());
					dto.setTenantId(tenantId);
					dto.setIsDelete(Constant.DELETE_STATUS_NOT_DELETED);
					dto.setAppKey(appKey);
					dto.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_0);
					service.saveBusiQualityCementMixingStationExceedDto(dto);
				}
				break;
			//修改
			case Constant.OPERATION_INT_TYPE_UPDATE  :
				entity = dao.findBusiQualityCementMixingStationExceedByUkey(dataJson.getString("ukey"));
				if(entity == null){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要修改的数据。");
				}
				if(Integer.valueOf(entity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "推送数据已在处理或处理完成，不允许修改。");
				}
				dto = (BusiQualityCementMixingStationExceedDto) dataJson.toBean(dataJson, BusiQualityCementMixingStationExceedDto.class);
				if(entity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_0 || entity.getIsQualitySupervisionBureau() == null){
					BeanCopy.getInstance().mapIgnoreNull(dto, entity);
				} else if(entity.getIsQualitySupervisionBureau().intValue() == Constant.Quality.QUALITY_SUPERVISION_BUREAU_1){
					BeanCopy.getInstance().mapIgnoreNull(dto, entity);
					entity.setIsQualitySupervisionBureau(Constant.Quality.QUALITY_SUPERVISION_BUREAU_2);//2已传给质监局并做修改
				}
				dao.saveOrUpdateBusiQualityCementMixingStationExceed(entity);
				break;
			//删除
			case Constant.OPERATION_INT_TYPE_DELETE :
				entity = dao.findBusiQualityCementMixingStationExceedByUkey(dataJson.getString("ukey"));
				if(entity != null){
					if(Integer.valueOf(entity.getDisposeState()).intValue() != Constant.Quality.STATUS_DEAL_NO ){
						return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "数据已在处理或处理完成，不允许删除");
					} else {
						entity.setIsDelete(1);
						dao.saveOrUpdateBusiQualityCementMixingStationExceed(entity);
					}
				} else {
					return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "未查询到要删除的数据。");
				}
				break;
			default:
				return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "参数 \"operateType\" 校验出错。");
			}
		} catch (Exception e) {
			return ExternalUtil.getExternalResponse(Constant.externalStatusCode.ERROR, "数据保存出错。");
		}
		
		return ExternalUtil.getExternalResponse(Constant.externalStatusCode.SUCCESS, "");
	}
	
	@Override
	public String getType() {
		return "cementMixingStationExceedingStandard";
	}
}
